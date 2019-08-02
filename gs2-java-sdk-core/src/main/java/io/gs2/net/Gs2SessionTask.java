package io.gs2.net;

import java.io.IOException;

public abstract class Gs2SessionTask implements IResponseHandler {
    private Gs2Session session;
    protected Gs2SessionTaskId sessionTaskId;

    protected Gs2Session getSession() {
        return session;
    }

    protected Gs2SessionTaskId getSessionTaskId() {
        return sessionTaskId;
    }

    String getProjectToken() {
        return session.getProjectToken();
    }

    abstract void triggerUserCallback(Gs2Response gs2Response);

    // Gs2Session::execute() から利用
    abstract void prepareImpl();     // ロックの内側から呼ばれるので session のプライベートメンバに安全にアクセス可能

    abstract void executeImpl();     // ロックの外側から呼ばれるので直接コールバックを呼び出し可能

    public Gs2SessionTask(Gs2Session session) {
        this.session = session;
    }

    @Override
    public void callback(Gs2Response gs2Response) {
        triggerUserCallback(gs2Response);
        session.notifyComplete(this);
    }

    public void execute() throws IOException {
        session.execute(this);
    }

}
