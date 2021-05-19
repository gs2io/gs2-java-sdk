package io.gs2.core.net;

import java.io.IOException;

public abstract class Gs2SessionTask implements IResponseHandler {
    Gs2Session gs2Session;
    Gs2SessionTaskId gs2SessionTaskId;

    protected Gs2Session getGs2Session()
    {
        return gs2Session;
    }

    protected Gs2SessionTaskId getGs2SessionTaskId()
    {
        return gs2SessionTaskId;
    }

    String getProjectToken()
    {
        return gs2Session.getProjectToken();
    }

    abstract void triggerUserCallback(Gs2Response gs2Response);

    // Gs2Session::execute() から利用
    abstract void prepareImpl();     // ロックの内側から呼ばれるので gs2Session のプライベートメンバに安全にアクセス可能
    abstract void executeImpl();     // ロックの外側から呼ばれるので直接コールバックを呼び出し可能

    public Gs2SessionTask(Gs2Session gs2Session)
    {
        this.gs2Session = gs2Session;
    }

    @Override
    public void callback(Gs2Response gs2Response)
    {
        triggerUserCallback(gs2Response);
        gs2Session.notifyComplete(this);
    }

    public void execute() throws IOException {
        gs2Session.execute(this);
    }

}
