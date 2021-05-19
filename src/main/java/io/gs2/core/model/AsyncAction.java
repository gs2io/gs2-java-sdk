package io.gs2.core.model;

public interface AsyncAction<T extends AsyncResult> {

    void callback(T result);

}
