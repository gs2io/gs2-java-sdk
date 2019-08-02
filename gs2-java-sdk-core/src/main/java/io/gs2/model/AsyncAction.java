package io.gs2.model;

public interface AsyncAction<T extends AsyncResult> {

    void callback(T result);

}
