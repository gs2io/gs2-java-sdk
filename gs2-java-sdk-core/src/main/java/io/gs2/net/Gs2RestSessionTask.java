package io.gs2.net;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.gs2.model.AsyncAction;
import io.gs2.model.AsyncResult;
import io.gs2.model.IResult;

import java.io.IOException;


public class Gs2RestSessionTask<T extends IResult> extends Gs2SessionTask {

    protected HttpTaskBuilder builder;
    private AsyncAction<AsyncResult<T>> callback;
    private Class<T> clazz;

    public Gs2RestSessionTask(
            Gs2RestSession gs2RestSession,
            AsyncAction<AsyncResult<T>> callback,
            Class<T> clazz) {

        super(gs2RestSession);

        this.builder = HttpTaskBuilder.create();
        this.callback = callback;
        this.clazz = clazz;
    }

    protected void prepareImpl() {
        this.builder.setHeader("X-GS2-CLIENT-ID", getGs2Session().getGs2Credential().getClientId());
        this.builder.setHeader("Authorization", getProjectToken());
    }

    protected void executeImpl() {
        this.builder.build().send();
    }

    protected void triggerUserCallback(Gs2Response gs2Response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if(gs2Response.getGs2Exception() == null) {
                T result = mapper.readValue(gs2Response.message, clazz);
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
