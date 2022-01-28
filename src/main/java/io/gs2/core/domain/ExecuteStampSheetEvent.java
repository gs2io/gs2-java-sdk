package io.gs2.core.domain;

public interface ExecuteStampSheetEvent {
    void onEvent(CacheDatabase cache, String action, String request, String result);
}
