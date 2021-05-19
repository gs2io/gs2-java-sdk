package io.gs2.core.model;

import io.gs2.core.exception.Gs2Exception;

public class AsyncResult<T extends IResult> {

    private T result;
    private Gs2Exception error;

    public AsyncResult(T result, Gs2Exception error) {
        this.result = result;
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public Gs2Exception getError() {
        return error;
    }
}
