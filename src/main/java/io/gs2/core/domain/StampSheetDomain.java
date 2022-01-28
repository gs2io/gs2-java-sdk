package io.gs2.core.domain;

import io.gs2.core.net.Gs2RestSession;
import io.gs2.distributor.Gs2DistributorRestClient;
import io.gs2.distributor.request.RunStampSheetRequest;
import io.gs2.distributor.request.RunStampSheetWithoutNamespaceRequest;
import io.gs2.distributor.request.RunStampTaskRequest;
import io.gs2.distributor.request.RunStampTaskWithoutNamespaceRequest;
import io.gs2.distributor.result.RunStampSheetResult;
import io.gs2.distributor.result.RunStampSheetWithoutNamespaceResult;
import io.gs2.distributor.result.RunStampTaskResult;
import io.gs2.distributor.result.RunStampTaskWithoutNamespaceResult;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

@SuppressWarnings("deprecation")
public class StampSheetDomain {

    private final CacheDatabase cache;
    private final JobQueueDomain jobQueueDomain;
    private final Gs2RestSession session;
    private final String stampSheet;
    private final String stampSheetEncryptionKeyId;
    private final String namespaceName;
    private final ExecuteStampTaskEvent stampTaskEvent;
    private final ExecuteStampSheetEvent stampSheetEvent;

    public StampSheetDomain(
            CacheDatabase cache,
            JobQueueDomain jobQueueDomain,
            Gs2RestSession session,
            String stampSheet,
            String stampSheetEncryptionKeyId,
            String namespaceName,
            ExecuteStampTaskEvent stampTaskEvent,
            ExecuteStampSheetEvent stampSheetEvent
    ) {
        this.cache = cache;
        this.jobQueueDomain = jobQueueDomain;
        this.session = session;
        this.stampSheet = stampSheet;
        this.stampSheetEncryptionKeyId = stampSheetEncryptionKeyId;
        this.namespaceName = namespaceName;
        this.stampTaskEvent = stampTaskEvent;
        this.stampSheetEvent = stampSheetEvent;
    }

    public void run(
    ) {
        Gs2DistributorRestClient client = new Gs2DistributorRestClient(
                session
        );
        JSONObject stampSheetJson = new JSONObject(stampSheet);
        String stampSheetPayload = stampSheetJson.getString("body");
        JSONObject stampSheetPayloadJson = new JSONObject(stampSheetPayload);
        JSONArray stampTasks = stampSheetPayloadJson.getJSONArray("tasks");
        String contextStack = null;
        for (int i=0; i<stampTasks.length(); i++) {
            String stampTask = stampTasks.getString(i);
            JSONObject stampTaskJson = new JSONObject(stampTasks.getString(i));
            String stampTaskPayload = stampTaskJson.getString("body");
            JSONObject stampTaskPayloadJson = new JSONObject(stampTaskPayload);
            if (namespaceName == null) {
                RunStampTaskWithoutNamespaceResult result = client.runStampTaskWithoutNamespace(
                        new RunStampTaskWithoutNamespaceRequest()
                                .withContextStack(contextStack)
                                .withStampTask(stampTasks.getString(i))
                                .withKeyId(stampSheetEncryptionKeyId)
                );
                contextStack = result.getContextStack();
                stampTaskEvent.onEvent(
                        cache,
                        stampTaskPayloadJson.getString("action"),
                        stampTaskPayloadJson.getString("args"),
                        result.getResult()
                );
            } else {
                RunStampTaskResult result = client.runStampTask(
                        new RunStampTaskRequest()
                                .withContextStack(contextStack)
                                .withNamespaceName(namespaceName)
                                .withStampTask(stampTasks.getString(i))
                                .withKeyId(stampSheetEncryptionKeyId)
                );
                contextStack = result.getContextStack();
                stampTaskEvent.onEvent(
                        cache,
                        stampTaskPayloadJson.getString("action"),
                        stampTaskPayloadJson.getString("args"),
                        result.getResult()
                );
            }
        }

        String action = null;
        JSONObject requestJson = null;
        JSONObject resultJson = null;
        if (namespaceName == null) {
            RunStampSheetWithoutNamespaceResult result = client.runStampSheetWithoutNamespace(
                    new RunStampSheetWithoutNamespaceRequest()
                            .withContextStack(contextStack)
                            .withStampSheet(stampSheet)
                            .withKeyId(stampSheetEncryptionKeyId)
            );
            stampSheetEvent.onEvent(
                    cache,
                    stampSheetPayloadJson.getString("action"),
                    stampSheetPayloadJson.getString("args"),
                    result.getResult()
            );
            action = stampSheetPayloadJson.getString("action");
            requestJson = new JSONObject(stampSheetPayloadJson.getString("args"));
            resultJson = new JSONObject(result.getResult().length() != 0 ? result.getResult() : "{}");
        } else {
            RunStampSheetResult result = client.runStampSheet(
                    new RunStampSheetRequest()
                            .withContextStack(contextStack)
                            .withNamespaceName(namespaceName)
                            .withStampSheet(stampSheet)
                            .withKeyId(stampSheetEncryptionKeyId)
            );
            stampSheetEvent.onEvent(
                    cache,
                    stampSheetPayloadJson.getString("action"),
                    stampSheetPayloadJson.getString("args"),
                    result.getResult()
            );
            action = stampSheetPayloadJson.getString("action");
            requestJson = new JSONObject(stampSheetPayloadJson.getString("args"));
            resultJson = new JSONObject(result.getResult().length() != 0 ? result.getResult() : "{}");
        }

        if (resultJson.has("stampSheet")) {
            StampSheetDomain newStampSheet = new StampSheetDomain(
                    cache,
                    jobQueueDomain,
                    session,
                    resultJson.getString("stampSheet"),
                    resultJson.getString("stampSheetEncryptionKeyId"),
                    namespaceName,
                    stampTaskEvent,
                    stampSheetEvent
            );
            newStampSheet.run();
        }

        if (Objects.equals(action, "Gs2JobQueue:PushByUserId")) {
            Gs2.pushJobQueue(
                    jobQueueDomain,
                    requestJson.getString("namespaceName")
            );
        }
    }
}
