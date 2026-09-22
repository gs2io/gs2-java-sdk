package io.gs2.core.net;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gs2.core.model.AsyncAction;
import io.gs2.core.model.AsyncResult;
import io.gs2.core.model.IResult;

import java.io.IOException;


public abstract class Gs2WebSocketSessionTask<T extends IResult> extends Gs2SessionTask {

    protected HttpTaskBuilder builder;
    private AsyncAction<AsyncResult<T>> callback;

    public Gs2WebSocketSessionTask(
            Gs2WebSocketSession gs2WebSocketSession,
            AsyncAction<AsyncResult<T>> callback
    ) {

        super(gs2WebSocketSession);

        this.builder = HttpTaskBuilder.create();
        if (gs2WebSocketSession != null) {
            // ★Steady: 生成クライアントは Gs2WebSocketSession.EndpointHost から URL を組むので、
            // 組み上がった URL を build() で <steady>/<service> に差し替える（生成物は触らない）。
            // ★template を実行時に書き換える運用（dev 向け）では、タスクを作る前に書き換えておくこと。
            this.builder.setSteadyEndpoint(
                    gs2WebSocketSession.getSteadyEndpoint(),
                    Gs2WebSocketSession.EndpointHost,
                    gs2WebSocketSession.getRegion().getName()
            );
        }
        this.callback = callback;
    }

    public abstract T parse(JsonNode data);

    protected void prepareImpl() {
        this.builder.setHeader("X-GS2-CLIENT-ID", getGs2Session().getGs2Credential().getClientId());
        this.builder.setHeader("Authorization", "Bearer " + getProjectToken());
    }

    protected void executeImpl() {
        this.builder.build().send();
    }

    protected void triggerUserCallback(Gs2Response gs2Response) {
        try {
            if(gs2Response.getGs2Exception() == null) {
                T result = parse(new ObjectMapper().readTree(gs2Response.message));
                AsyncResult<T> asyncResult = new AsyncResult<>(result, gs2Response.getGs2Exception());
                callback.callback(asyncResult);
            } else {
                AsyncResult<T> asyncResult = new AsyncResult<>(null, gs2Response.getGs2Exception());
                callback.callback(asyncResult);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
