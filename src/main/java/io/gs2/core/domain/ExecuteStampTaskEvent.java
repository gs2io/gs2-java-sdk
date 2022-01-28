package io.gs2.core.domain;

public interface ExecuteStampTaskEvent {
    void onEvent(CacheDatabase cache, String action, String request, String result);
}
