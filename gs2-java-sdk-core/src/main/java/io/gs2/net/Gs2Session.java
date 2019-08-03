package io.gs2.net;

import io.gs2.exception.Gs2Exception;
import io.gs2.exception.SessionNotOpenException;
import io.gs2.exception.UnknownException;
import io.gs2.model.AsyncAction;
import io.gs2.model.AsyncResult;
import io.gs2.model.BasicGs2Credential;
import io.gs2.model.Region;
import io.gs2.result.CloseResult;
import io.gs2.result.OpenResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Gs2Session {

    private enum State {
        Idle,
        Opening,
        CancellingOpen,
        Available,
        CancellingTasks,
        Closing,
    }

    ;

    private ReentrantLock m_Mutex = new ReentrantLock();

    private State m_State;

    private final BasicGs2Credential m_Gs2Credential;
    private Region m_Region;

    private String m_ProjectToken;

    private List<AsyncAction<AsyncResult<OpenResult>>> openCallbackList = new ArrayList<>();
    private List<AsyncAction<AsyncResult<CloseResult>>> closeCallbackList = new ArrayList<>();
    private List<Gs2SessionTask> gs2SessionTaskList = new ArrayList<>();

    private AsyncAction<AsyncResult<CloseResult>> m_OnClose;

    private Gs2SessionTaskId.Generator m_Gs2SessionIdTaskGenerator = new Gs2SessionTaskId.Generator();

    private static void triggerOpenCallback(List<AsyncAction<AsyncResult<OpenResult>>> openCallbackList, Gs2Exception result) {
        for (AsyncAction<AsyncResult<OpenResult>> pOpenCallback : openCallbackList) {
            pOpenCallback.callback(new AsyncResult<>(
                    new OpenResult(),
                    result
            ));
        }
    }

    private static void triggerCloseCallback(List<AsyncAction<AsyncResult<CloseResult>>> closeCallbackList, Gs2Exception result) {
        for (AsyncAction<AsyncResult<CloseResult>> pCloseCallback : closeCallbackList) {
            pCloseCallback.callback(new AsyncResult<>(
                    new CloseResult(),
                    result
            ));
        }
    }

    private static void triggerCancelTasksCallback(List<Gs2SessionTask> gs2SessionTaskList, Gs2Exception gs2Exception) {
        for (Gs2SessionTask pGs2SessionTask : gs2SessionTaskList) {
            pGs2SessionTask.triggerUserCallback(new Gs2ClientErrorResponse(gs2Exception));  // notifyComplete() は不要なのでユーザコールバックのみ呼ぶ
        }
    }

    private void enterStateLock() {
        m_Mutex.lock();
    }

    private void exitStateLock() {
        m_Mutex.unlock();
    }

    ;

    private void changeStateToIdle() {
        // 外部要因による切断がありうるので、どの状態からでも遷移しうる

        assert (openCallbackList.isEmpty());     // すべてコールバックされ（るために取り出され）ているべき
        assert (closeCallbackList.isEmpty());    // すべてコールバックされ（るために取り出され）ているべき
        assert (gs2SessionTaskList.isEmpty());         // Available になる前に登録はできない

        m_State = State.Idle;

        exitStateLock();
    }

    private void changeStateToOpening() {
        assert (m_State == State.Idle || m_State == State.Closing);

        assert (!openCallbackList.isEmpty());    // open() タスクが登録されているときのみ遷移する
        assert (closeCallbackList.isEmpty());    // すべてコールバックされ（るために取り出され）ているべき
        assert (gs2SessionTaskList.isEmpty());         // Available になる前に登録はできない

        m_State = State.Opening;

        openImpl();

        exitStateLock();
    }

    private void changeStateToCancellingOpen() {
        assert (m_State == State.Opening);

        assert (!openCallbackList.isEmpty());    // Opening は open() タスクが必ず存在する
        assert (!closeCallbackList.isEmpty());   // 接続処理中の close() によってのみ遷移する
        assert (gs2SessionTaskList.isEmpty());         // Available になる前に登録はできない

        m_State = State.CancellingOpen;

        cancelOpenImpl();

        exitStateLock();
    }

    private void changeStateToAvailable(String projectToken) {
        assert (m_State == State.Opening);

        assert (openCallbackList.isEmpty());     // すべてコールバックされ（るために取り出され）ているべき
        assert (closeCallbackList.isEmpty());    // close() が呼ばれている場合は Closing に遷移しなければならない
        assert (gs2SessionTaskList.isEmpty());         // Available になる前に登録はできない

        m_ProjectToken = projectToken;

        m_State = State.Available;

        exitStateLock();
    }

    private void changeStateToCancellingTasks() {
        assert (m_State == State.Available);

        assert (openCallbackList.isEmpty());     // Available のあいだの open() は即時返却される
        // 外部要因による切断の場合に close() を呼ばなくても遷移することがある
        assert (!gs2SessionTaskList.isEmpty());        // キャンセルしたいタスクがあるから遷移するのである

        m_State = State.CancellingTasks;

        exitStateLock();
    }

    private void changeStateToClosing() {
        assert (m_State == State.Opening || m_State == State.CancellingOpen || m_State == State.Available || m_State == State.CancellingTasks);

        // CancellingTasks のあいだには次の open() が積まれることがある
        // 外部要因による切断の場合に close() を呼ばなくても遷移することがある
        assert (gs2SessionTaskList.isEmpty());         // タスクがなくなったときに遷移する

        m_ProjectToken = null;

        m_State = State.Closing;

        boolean isCloseInstant = closeImpl();

        if (isCloseInstant) {
            // Idle か Opening に遷移しているはずだけど、ロックから出てしまっているので検証はしない
        } else {
            exitStateLock();
        }
    }

    private void keepCurrentState() {
        exitStateLock();
    }

    // Gs2SessionTask から利用
    public void execute(Gs2SessionTask gs2SessionTask) {
        enterStateLock();

        if (m_State == State.Available) {
            gs2SessionTask.gs2SessionTaskId = m_Gs2SessionIdTaskGenerator.issue();

            gs2SessionTask.prepareImpl();

            gs2SessionTaskList.add(gs2SessionTask);

            keepCurrentState();

            gs2SessionTask.executeImpl();
        } else {
            keepCurrentState();

            gs2SessionTask.callback(new Gs2ClientErrorResponse(new SessionNotOpenException("")));
        }
    }

    void notifyComplete(Gs2SessionTask gs2SessionTask) {
        enterStateLock();

        gs2SessionTaskList.remove(gs2SessionTask);

        if (m_State == State.CancellingTasks && gs2SessionTaskList.isEmpty()) {
            changeStateToClosing();
        } else {
            keepCurrentState();
        }
    }

    protected String getProjectToken() {
        return m_ProjectToken;
    }

    public void openCallback(String pProjectToken, Gs2Exception exception) {
        // 接続完了コールバック

        enterStateLock();

        if (exception == null) {
            // ログイン処理がエラーなく応答された場合

            if (pProjectToken != null) {
                List<AsyncAction<AsyncResult<OpenResult>>> openCallbackList = new ArrayList<>(this.openCallbackList);
                this.openCallbackList.clear();

                if (closeCallbackList.isEmpty()) {
                    changeStateToAvailable(pProjectToken);
                } else {
                    changeStateToClosing();
                }

                triggerOpenCallback(openCallbackList, null);
            } else {
                // 応答からプロジェクトトークンが取得できなかった場合
                // ただし、ここには来ないように派生クラスを実装しなければならない

                List<AsyncAction<AsyncResult<OpenResult>>> openCallbackList = new ArrayList<>(this.openCallbackList);
                this.openCallbackList.clear();

                List<AsyncAction<AsyncResult<CloseResult>>> closeCallbackList = new ArrayList<>(this.closeCallbackList);
                this.closeCallbackList.clear();

                changeStateToIdle();

                triggerOpenCallback(openCallbackList, new UnknownException(""));
                triggerCloseCallback(closeCallbackList, new UnknownException(""));
            }
        } else {
            // ログイン処理がエラーになった場合

            List<AsyncAction<AsyncResult<OpenResult>>> openCallbackList = new ArrayList<>(this.openCallbackList);
            this.openCallbackList.clear();

            List<AsyncAction<AsyncResult<CloseResult>>> closeCallbackList = new ArrayList<>(this.closeCallbackList);
            this.closeCallbackList.clear();

            changeStateToIdle();

            triggerOpenCallback(openCallbackList, exception);
            triggerCloseCallback(closeCallbackList, exception);
        }
    }

    public void closeCallback(Gs2Exception gs2Exception, boolean isCloseInstant) {
        if (!isCloseInstant) {
            enterStateLock();
        }

        AsyncAction<AsyncResult<CloseResult>> onClose = m_OnClose;

        List<Gs2SessionTask> gs2SessionTaskList = new ArrayList<>(this.gs2SessionTaskList);
        this.gs2SessionTaskList.clear();

        List<AsyncAction<AsyncResult<CloseResult>>> closeCallbackList = new ArrayList<>(this.closeCallbackList);
        this.closeCallbackList.clear();

        if (openCallbackList.isEmpty()) {
            changeStateToIdle();
        } else {
            changeStateToOpening();
        }

        triggerCancelTasksCallback(gs2SessionTaskList, gs2Exception);

        if (onClose != null) {
            onClose.callback(null);
        }
        triggerCloseCallback(closeCallbackList, null);
    }

    protected void cancelTasksCallback(Gs2Exception gs2Exception) {
        enterStateLock();

        List<Gs2SessionTask> gs2SessionTaskList = new ArrayList<>(this.gs2SessionTaskList);
        this.gs2SessionTaskList.clear();

        keepCurrentState();

        triggerCancelTasksCallback(gs2SessionTaskList, gs2Exception);
    }

    protected Gs2SessionTask findGs2SessionTask(final Gs2SessionTaskId gs2SessionTaskId) {
        m_Mutex.lock();

        for (Gs2SessionTask sessionTask : gs2SessionTaskList) {
            if (sessionTask.gs2SessionTaskId == gs2SessionTaskId) {
                return sessionTask;
            }
        }

        return null;
    }

    public Gs2Session(final BasicGs2Credential gs2Credential) {

        m_State = State.Idle;
        m_Gs2Credential = gs2Credential;
        m_Region = Region.AP_NORTHEAST_1;
    }

    public Gs2Session(final BasicGs2Credential gs2Credential, final Region region) {
        m_State = State.Idle;
        m_Gs2Credential = gs2Credential;
        m_Region = region;

    }

    public Gs2Session(final BasicGs2Credential gs2Credential, final String region) {
        m_State = State.Idle;
        m_Gs2Credential = gs2Credential;
        m_Region = Region.prettyValueOf(region);
    }

    public BasicGs2Credential getGs2Credential() {
        return m_Gs2Credential;
    }

    public Region getRegion() {
        return m_Region;
    }

    public void openAsync(AsyncAction<AsyncResult<OpenResult>> callback) {
        enterStateLock();

        switch (m_State) {
            case Idle:
                openCallbackList.add(callback);
                changeStateToOpening();
                break;

            case Opening:
            case CancellingOpen:
            case CancellingTasks:    // 切断処理が終わってから実行される
            case Closing:            // 切断処理が終わってから実行される
                openCallbackList.add(callback);
                keepCurrentState();
                break;

            case Available:
                keepCurrentState();
                callback.callback(null);
                break;
        }
    }

    public void open() {
        AtomicReference<AsyncResult<OpenResult>> asyncResult = new AtomicReference<>();
        openAsync(r -> {
            asyncResult.set(r);
        });
        while(asyncResult.get() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
        }
    }

    public void closeAsync(AsyncAction<AsyncResult<CloseResult>> callback) {
        enterStateLock();

        if (m_State == State.Idle) {
            // 即コールバック
            keepCurrentState();

            callback.callback(null);
        } else {
            closeCallbackList.add(callback);

            switch (m_State) {
                case Opening:
                    changeStateToCancellingOpen();
                    break;

                case Available:
                    if (gs2SessionTaskList.isEmpty()) {
                        changeStateToClosing();
                    } else {
                        changeStateToCancellingTasks();
                    }
                    break;

                case Idle:   // ここには来ない
                case CancellingOpen:
                case CancellingTasks:
                case Closing:
                    keepCurrentState();
                    break;
            }
        }
    }

    public void close() {
        closeAsync(r -> {});
    }

    public void setOnClose(AsyncAction<AsyncResult<CloseResult>> callback) {
        m_Mutex.lock();

        m_OnClose = callback;
    }

    // 以下の関数は m_Mutex のロック内から呼ばれます
    abstract void openImpl();

    abstract void cancelOpenImpl();

    abstract boolean closeImpl();   // 中で closeCallback() を呼んだ場合は true を返すこと
}
