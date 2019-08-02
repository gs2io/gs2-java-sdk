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
        IDLE,
        OPENING,
        CANCELLING_OPEN,
        AVAILABLE,
        CANCELLING_TASKS,
        CLOSING,
    }

    private ReentrantLock lock = new ReentrantLock();

    private State state;

    private final BasicGs2Credential credential;
    private Region region;

    private String projectToken;

    private List<AsyncAction<AsyncResult<OpenResult>>> openCallbackList = new ArrayList<>();
    private List<AsyncAction<AsyncResult<CloseResult>>> closeCallbackList = new ArrayList<>();
    private List<Gs2SessionTask> sessionTaskList = new ArrayList<>();

    private AsyncAction<AsyncResult<CloseResult>> onCloseHandler;

    private Gs2SessionTaskId.Generator sessionTaskIdGenerator = new Gs2SessionTaskId.Generator();

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
        lock.lock();
    }

    private void exitStateLock() {
        lock.unlock();
    }

    private void changeStateToIdle() {
        // 外部要因による切断がありうるので、どの状態からでも遷移しうる

        assert (openCallbackList.isEmpty());     // すべてコールバックされ（るために取り出され）ているべき
        assert (closeCallbackList.isEmpty());    // すべてコールバックされ（るために取り出され）ているべき
        assert (sessionTaskList.isEmpty());         // AVAILABLE になる前に登録はできない

        state = State.IDLE;

        exitStateLock();
    }

    private void changeStateToOpening() {
        assert (state == State.IDLE || state == State.CLOSING);

        assert (!openCallbackList.isEmpty());    // open() タスクが登録されているときのみ遷移する
        assert (closeCallbackList.isEmpty());    // すべてコールバックされ（るために取り出され）ているべき
        assert (sessionTaskList.isEmpty());         // AVAILABLE になる前に登録はできない

        state = State.OPENING;

        openImpl();

        exitStateLock();
    }

    private void changeStateToCancellingOpen() {
        assert (state == State.OPENING);

        assert (!openCallbackList.isEmpty());    // OPENING は open() タスクが必ず存在する
        assert (!closeCallbackList.isEmpty());   // 接続処理中の close() によってのみ遷移する
        assert (sessionTaskList.isEmpty());         // AVAILABLE になる前に登録はできない

        state = State.CANCELLING_OPEN;

        cancelOpenImpl();

        exitStateLock();
    }

    private void changeStateToAvailable(String projectToken) {
        assert (state == State.OPENING);

        assert (openCallbackList.isEmpty());     // すべてコールバックされ（るために取り出され）ているべき
        assert (closeCallbackList.isEmpty());    // close() が呼ばれている場合は CLOSING に遷移しなければならない
        assert (sessionTaskList.isEmpty());         // AVAILABLE になる前に登録はできない

        this.projectToken = projectToken;

        state = State.AVAILABLE;

        exitStateLock();
    }

    private void changeStateToCancellingTasks() {
        assert (state == State.AVAILABLE);

        assert (openCallbackList.isEmpty());     // AVAILABLE のあいだの open() は即時返却される
        // 外部要因による切断の場合に close() を呼ばなくても遷移することがある
        assert (!sessionTaskList.isEmpty());        // キャンセルしたいタスクがあるから遷移するのである

        state = State.CANCELLING_TASKS;

        exitStateLock();
    }

    private void changeStateToClosing() {
        assert (state == State.OPENING || state == State.CANCELLING_OPEN || state == State.AVAILABLE || state == State.CANCELLING_TASKS);

        // CANCELLING_TASKS のあいだには次の open() が積まれることがある
        // 外部要因による切断の場合に close() を呼ばなくても遷移することがある
        assert (sessionTaskList.isEmpty());         // タスクがなくなったときに遷移する

        projectToken = null;

        state = State.CLOSING;

        boolean isCloseInstant = closeImpl();

        if (isCloseInstant) {
            // IDLE か OPENING に遷移しているはずだけど、ロックから出てしまっているので検証はしない
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

        if (state == State.AVAILABLE) {
            gs2SessionTask.sessionTaskId = sessionTaskIdGenerator.issue();

            gs2SessionTask.prepareImpl();

            sessionTaskList.add(gs2SessionTask);

            keepCurrentState();

            gs2SessionTask.executeImpl();
        } else {
            keepCurrentState();

            gs2SessionTask.callback(new Gs2ClientErrorResponse(new SessionNotOpenException("")));
        }
    }

    void notifyComplete(Gs2SessionTask gs2SessionTask) {
        enterStateLock();

        sessionTaskList.remove(gs2SessionTask);

        if (state == State.CANCELLING_TASKS && sessionTaskList.isEmpty()) {
            changeStateToClosing();
        } else {
            keepCurrentState();
        }
    }

    protected String getProjectToken() {
        return projectToken;
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

        AsyncAction<AsyncResult<CloseResult>> onClose = onCloseHandler;

        List<Gs2SessionTask> gs2SessionTaskList = new ArrayList<>(this.sessionTaskList);
        this.sessionTaskList.clear();

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

        List<Gs2SessionTask> gs2SessionTaskList = new ArrayList<>(this.sessionTaskList);
        this.sessionTaskList.clear();

        keepCurrentState();

        triggerCancelTasksCallback(gs2SessionTaskList, gs2Exception);
    }

    protected Gs2SessionTask findGs2SessionTask(final Gs2SessionTaskId gs2SessionTaskId) {
        lock.lock();

        for (Gs2SessionTask sessionTask : sessionTaskList) {
            if (sessionTask.sessionTaskId == gs2SessionTaskId) {
                return sessionTask;
            }
        }

        return null;
    }

    public Gs2Session(final BasicGs2Credential gs2Credential) {

        state = State.IDLE;
        credential = gs2Credential;
        region = Region.AP_NORTHEAST_1;
    }

    public Gs2Session(final BasicGs2Credential gs2Credential, final Region region) {
        state = State.IDLE;
        credential = gs2Credential;
        this.region = region;

    }

    public Gs2Session(final BasicGs2Credential gs2Credential, final String region) {
        state = State.IDLE;
        credential = gs2Credential;
        this.region = Region.prettyValueOf(region);
    }

    public BasicGs2Credential getGs2Credential() {
        return credential;
    }

    public Region getRegion() {
        return region;
    }

    public void openAsync(AsyncAction<AsyncResult<OpenResult>> callback) {
        enterStateLock();

        switch (state) {
            case IDLE:
                openCallbackList.add(callback);
                changeStateToOpening();
                break;

            case OPENING:
            case CANCELLING_OPEN:
            case CANCELLING_TASKS:    // 切断処理が終わってから実行される
            case CLOSING:            // 切断処理が終わってから実行される
                openCallbackList.add(callback);
                keepCurrentState();
                break;

            case AVAILABLE:
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
        while (asyncResult.get() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void closeAsync(AsyncAction<AsyncResult<CloseResult>> callback) {
        enterStateLock();

        if (state == State.IDLE) {
            // 即コールバック
            keepCurrentState();

            callback.callback(null);
        } else {
            closeCallbackList.add(callback);

            switch (state) {
                case OPENING:
                    changeStateToCancellingOpen();
                    break;

                case AVAILABLE:
                    if (sessionTaskList.isEmpty()) {
                        changeStateToClosing();
                    } else {
                        changeStateToCancellingTasks();
                    }
                    break;

                case IDLE:   // ここには来ない
                case CANCELLING_OPEN:
                case CANCELLING_TASKS:
                case CLOSING:
                    keepCurrentState();
                    break;
            }
        }
    }

    public void close() {
        closeAsync(r -> {
        });
    }

    public void setOnClose(AsyncAction<AsyncResult<CloseResult>> callback) {
        lock.lock();

        onCloseHandler = callback;
    }

    // 以下の関数は lock のロック内から呼ばれます
    abstract void openImpl();

    abstract void cancelOpenImpl();

    abstract boolean closeImpl();   // 中で closeCallback() を呼んだ場合は true を返すこと
}
