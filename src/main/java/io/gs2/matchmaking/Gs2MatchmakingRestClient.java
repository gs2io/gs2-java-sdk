
/*
 * Copyright 2016 Game Server Services, Inc. or its affiliates. All Rights
 * Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package io.gs2.matchmaking;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.concurrent.atomic.AtomicReference;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import io.gs2.core.model.AsyncAction;
import io.gs2.core.model.AsyncResult;
import io.gs2.core.exception.*;
import io.gs2.core.net.*;
import io.gs2.core.util.EncodingUtil;

import io.gs2.core.AbstractGs2Client;
import io.gs2.matchmaking.request.*;
import io.gs2.matchmaking.result.*;
import io.gs2.matchmaking.model.*;

public class Gs2MatchmakingRestClient extends AbstractGs2Client<Gs2MatchmakingRestClient> {

	public Gs2MatchmakingRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class DescribeNamespacesTask extends Gs2RestSessionTask<DescribeNamespacesResult> {
        private DescribeNamespacesRequest request;

        public DescribeNamespacesTask(
            DescribeNamespacesRequest request,
            AsyncAction<AsyncResult<DescribeNamespacesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeNamespacesResult parse(JsonNode data) {
            return DescribeNamespacesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/";

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getPageToken() != null) {
                queryStrings.add("pageToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPageToken()))));
            }
            if (this.request.getLimit() != null) {
                queryStrings.add("limit=" + String.valueOf(this.request.getLimit()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeNamespacesAsync(
            DescribeNamespacesRequest request,
            AsyncAction<AsyncResult<DescribeNamespacesResult>> callback
    ) {
        DescribeNamespacesTask task = new DescribeNamespacesTask(request, callback);
        session.execute(task);
    }

    public DescribeNamespacesResult describeNamespaces(
            DescribeNamespacesRequest request
    ) {
        final AsyncResult<DescribeNamespacesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeNamespacesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateNamespaceTask extends Gs2RestSessionTask<CreateNamespaceResult> {
        private CreateNamespaceRequest request;

        public CreateNamespaceTask(
            CreateNamespaceRequest request,
            AsyncAction<AsyncResult<CreateNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateNamespaceResult parse(JsonNode data) {
            return CreateNamespaceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("enableRating", request.getEnableRating());
                    put("enableDisconnectDetection", request.getEnableDisconnectDetection());
                    put("disconnectDetectionTimeoutSeconds", request.getDisconnectDetectionTimeoutSeconds());
                    put("createGatheringTriggerType", request.getCreateGatheringTriggerType());
                    put("createGatheringTriggerRealtimeNamespaceId", request.getCreateGatheringTriggerRealtimeNamespaceId());
                    put("createGatheringTriggerScriptId", request.getCreateGatheringTriggerScriptId());
                    put("completeMatchmakingTriggerType", request.getCompleteMatchmakingTriggerType());
                    put("completeMatchmakingTriggerRealtimeNamespaceId", request.getCompleteMatchmakingTriggerRealtimeNamespaceId());
                    put("completeMatchmakingTriggerScriptId", request.getCompleteMatchmakingTriggerScriptId());
                    put("enableCollaborateSeasonRating", request.getEnableCollaborateSeasonRating());
                    put("collaborateSeasonRatingNamespaceId", request.getCollaborateSeasonRatingNamespaceId());
                    put("collaborateSeasonRatingTtl", request.getCollaborateSeasonRatingTtl());
                    put("changeRatingScript", request.getChangeRatingScript() != null ? request.getChangeRatingScript().toJson() : null);
                    put("joinNotification", request.getJoinNotification() != null ? request.getJoinNotification().toJson() : null);
                    put("leaveNotification", request.getLeaveNotification() != null ? request.getLeaveNotification().toJson() : null);
                    put("completeNotification", request.getCompleteNotification() != null ? request.getCompleteNotification().toJson() : null);
                    put("changeRatingNotification", request.getChangeRatingNotification() != null ? request.getChangeRatingNotification().toJson() : null);
                    put("logSetting", request.getLogSetting() != null ? request.getLogSetting().toJson() : null);
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void createNamespaceAsync(
            CreateNamespaceRequest request,
            AsyncAction<AsyncResult<CreateNamespaceResult>> callback
    ) {
        CreateNamespaceTask task = new CreateNamespaceTask(request, callback);
        session.execute(task);
    }

    public CreateNamespaceResult createNamespace(
            CreateNamespaceRequest request
    ) {
        final AsyncResult<CreateNamespaceResult>[] resultAsyncResult = new AsyncResult[]{null};
        createNamespaceAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetNamespaceStatusTask extends Gs2RestSessionTask<GetNamespaceStatusResult> {
        private GetNamespaceStatusRequest request;

        public GetNamespaceStatusTask(
            GetNamespaceStatusRequest request,
            AsyncAction<AsyncResult<GetNamespaceStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetNamespaceStatusResult parse(JsonNode data) {
            return GetNamespaceStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/status";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void getNamespaceStatusAsync(
            GetNamespaceStatusRequest request,
            AsyncAction<AsyncResult<GetNamespaceStatusResult>> callback
    ) {
        GetNamespaceStatusTask task = new GetNamespaceStatusTask(request, callback);
        session.execute(task);
    }

    public GetNamespaceStatusResult getNamespaceStatus(
            GetNamespaceStatusRequest request
    ) {
        final AsyncResult<GetNamespaceStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        getNamespaceStatusAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetNamespaceTask extends Gs2RestSessionTask<GetNamespaceResult> {
        private GetNamespaceRequest request;

        public GetNamespaceTask(
            GetNamespaceRequest request,
            AsyncAction<AsyncResult<GetNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetNamespaceResult parse(JsonNode data) {
            return GetNamespaceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void getNamespaceAsync(
            GetNamespaceRequest request,
            AsyncAction<AsyncResult<GetNamespaceResult>> callback
    ) {
        GetNamespaceTask task = new GetNamespaceTask(request, callback);
        session.execute(task);
    }

    public GetNamespaceResult getNamespace(
            GetNamespaceRequest request
    ) {
        final AsyncResult<GetNamespaceResult>[] resultAsyncResult = new AsyncResult[]{null};
        getNamespaceAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateNamespaceTask extends Gs2RestSessionTask<UpdateNamespaceResult> {
        private UpdateNamespaceRequest request;

        public UpdateNamespaceTask(
            UpdateNamespaceRequest request,
            AsyncAction<AsyncResult<UpdateNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateNamespaceResult parse(JsonNode data) {
            return UpdateNamespaceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("enableRating", request.getEnableRating());
                    put("enableDisconnectDetection", request.getEnableDisconnectDetection());
                    put("disconnectDetectionTimeoutSeconds", request.getDisconnectDetectionTimeoutSeconds());
                    put("createGatheringTriggerType", request.getCreateGatheringTriggerType());
                    put("createGatheringTriggerRealtimeNamespaceId", request.getCreateGatheringTriggerRealtimeNamespaceId());
                    put("createGatheringTriggerScriptId", request.getCreateGatheringTriggerScriptId());
                    put("completeMatchmakingTriggerType", request.getCompleteMatchmakingTriggerType());
                    put("completeMatchmakingTriggerRealtimeNamespaceId", request.getCompleteMatchmakingTriggerRealtimeNamespaceId());
                    put("completeMatchmakingTriggerScriptId", request.getCompleteMatchmakingTriggerScriptId());
                    put("enableCollaborateSeasonRating", request.getEnableCollaborateSeasonRating());
                    put("collaborateSeasonRatingNamespaceId", request.getCollaborateSeasonRatingNamespaceId());
                    put("collaborateSeasonRatingTtl", request.getCollaborateSeasonRatingTtl());
                    put("changeRatingScript", request.getChangeRatingScript() != null ? request.getChangeRatingScript().toJson() : null);
                    put("joinNotification", request.getJoinNotification() != null ? request.getJoinNotification().toJson() : null);
                    put("leaveNotification", request.getLeaveNotification() != null ? request.getLeaveNotification().toJson() : null);
                    put("completeNotification", request.getCompleteNotification() != null ? request.getCompleteNotification().toJson() : null);
                    put("changeRatingNotification", request.getChangeRatingNotification() != null ? request.getChangeRatingNotification().toJson() : null);
                    put("logSetting", request.getLogSetting() != null ? request.getLogSetting().toJson() : null);
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.PUT)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void updateNamespaceAsync(
            UpdateNamespaceRequest request,
            AsyncAction<AsyncResult<UpdateNamespaceResult>> callback
    ) {
        UpdateNamespaceTask task = new UpdateNamespaceTask(request, callback);
        session.execute(task);
    }

    public UpdateNamespaceResult updateNamespace(
            UpdateNamespaceRequest request
    ) {
        final AsyncResult<UpdateNamespaceResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateNamespaceAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteNamespaceTask extends Gs2RestSessionTask<DeleteNamespaceResult> {
        private DeleteNamespaceRequest request;

        public DeleteNamespaceTask(
            DeleteNamespaceRequest request,
            AsyncAction<AsyncResult<DeleteNamespaceResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteNamespaceResult parse(JsonNode data) {
            return DeleteNamespaceResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.DELETE)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteNamespaceAsync(
            DeleteNamespaceRequest request,
            AsyncAction<AsyncResult<DeleteNamespaceResult>> callback
    ) {
        DeleteNamespaceTask task = new DeleteNamespaceTask(request, callback);
        session.execute(task);
    }

    public DeleteNamespaceResult deleteNamespace(
            DeleteNamespaceRequest request
    ) {
        final AsyncResult<DeleteNamespaceResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteNamespaceAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DumpUserDataByUserIdTask extends Gs2RestSessionTask<DumpUserDataByUserIdResult> {
        private DumpUserDataByUserIdRequest request;

        public DumpUserDataByUserIdTask(
            DumpUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<DumpUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DumpUserDataByUserIdResult parse(JsonNode data) {
            return DumpUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/system/dump/user/{userId}";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void dumpUserDataByUserIdAsync(
            DumpUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<DumpUserDataByUserIdResult>> callback
    ) {
        DumpUserDataByUserIdTask task = new DumpUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public DumpUserDataByUserIdResult dumpUserDataByUserId(
            DumpUserDataByUserIdRequest request
    ) {
        final AsyncResult<DumpUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        dumpUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CheckDumpUserDataByUserIdTask extends Gs2RestSessionTask<CheckDumpUserDataByUserIdResult> {
        private CheckDumpUserDataByUserIdRequest request;

        public CheckDumpUserDataByUserIdTask(
            CheckDumpUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CheckDumpUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CheckDumpUserDataByUserIdResult parse(JsonNode data) {
            return CheckDumpUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/system/dump/user/{userId}";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void checkDumpUserDataByUserIdAsync(
            CheckDumpUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CheckDumpUserDataByUserIdResult>> callback
    ) {
        CheckDumpUserDataByUserIdTask task = new CheckDumpUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public CheckDumpUserDataByUserIdResult checkDumpUserDataByUserId(
            CheckDumpUserDataByUserIdRequest request
    ) {
        final AsyncResult<CheckDumpUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        checkDumpUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CleanUserDataByUserIdTask extends Gs2RestSessionTask<CleanUserDataByUserIdResult> {
        private CleanUserDataByUserIdRequest request;

        public CleanUserDataByUserIdTask(
            CleanUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CleanUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CleanUserDataByUserIdResult parse(JsonNode data) {
            return CleanUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/system/clean/user/{userId}";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void cleanUserDataByUserIdAsync(
            CleanUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CleanUserDataByUserIdResult>> callback
    ) {
        CleanUserDataByUserIdTask task = new CleanUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public CleanUserDataByUserIdResult cleanUserDataByUserId(
            CleanUserDataByUserIdRequest request
    ) {
        final AsyncResult<CleanUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        cleanUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CheckCleanUserDataByUserIdTask extends Gs2RestSessionTask<CheckCleanUserDataByUserIdResult> {
        private CheckCleanUserDataByUserIdRequest request;

        public CheckCleanUserDataByUserIdTask(
            CheckCleanUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CheckCleanUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CheckCleanUserDataByUserIdResult parse(JsonNode data) {
            return CheckCleanUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/system/clean/user/{userId}";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void checkCleanUserDataByUserIdAsync(
            CheckCleanUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CheckCleanUserDataByUserIdResult>> callback
    ) {
        CheckCleanUserDataByUserIdTask task = new CheckCleanUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public CheckCleanUserDataByUserIdResult checkCleanUserDataByUserId(
            CheckCleanUserDataByUserIdRequest request
    ) {
        final AsyncResult<CheckCleanUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        checkCleanUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PrepareImportUserDataByUserIdTask extends Gs2RestSessionTask<PrepareImportUserDataByUserIdResult> {
        private PrepareImportUserDataByUserIdRequest request;

        public PrepareImportUserDataByUserIdTask(
            PrepareImportUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<PrepareImportUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PrepareImportUserDataByUserIdResult parse(JsonNode data) {
            return PrepareImportUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/system/import/user/{userId}/prepare";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void prepareImportUserDataByUserIdAsync(
            PrepareImportUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<PrepareImportUserDataByUserIdResult>> callback
    ) {
        PrepareImportUserDataByUserIdTask task = new PrepareImportUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public PrepareImportUserDataByUserIdResult prepareImportUserDataByUserId(
            PrepareImportUserDataByUserIdRequest request
    ) {
        final AsyncResult<PrepareImportUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareImportUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ImportUserDataByUserIdTask extends Gs2RestSessionTask<ImportUserDataByUserIdResult> {
        private ImportUserDataByUserIdRequest request;

        public ImportUserDataByUserIdTask(
            ImportUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<ImportUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ImportUserDataByUserIdResult parse(JsonNode data) {
            return ImportUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/system/import/user/{userId}";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("uploadToken", request.getUploadToken());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void importUserDataByUserIdAsync(
            ImportUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<ImportUserDataByUserIdResult>> callback
    ) {
        ImportUserDataByUserIdTask task = new ImportUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public ImportUserDataByUserIdResult importUserDataByUserId(
            ImportUserDataByUserIdRequest request
    ) {
        final AsyncResult<ImportUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        importUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CheckImportUserDataByUserIdTask extends Gs2RestSessionTask<CheckImportUserDataByUserIdResult> {
        private CheckImportUserDataByUserIdRequest request;

        public CheckImportUserDataByUserIdTask(
            CheckImportUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CheckImportUserDataByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CheckImportUserDataByUserIdResult parse(JsonNode data) {
            return CheckImportUserDataByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/system/import/user/{userId}/{uploadToken}";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{uploadToken}", this.request.getUploadToken() == null || this.request.getUploadToken().length() == 0 ? "null" : String.valueOf(this.request.getUploadToken()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void checkImportUserDataByUserIdAsync(
            CheckImportUserDataByUserIdRequest request,
            AsyncAction<AsyncResult<CheckImportUserDataByUserIdResult>> callback
    ) {
        CheckImportUserDataByUserIdTask task = new CheckImportUserDataByUserIdTask(request, callback);
        session.execute(task);
    }

    public CheckImportUserDataByUserIdResult checkImportUserDataByUserId(
            CheckImportUserDataByUserIdRequest request
    ) {
        final AsyncResult<CheckImportUserDataByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        checkImportUserDataByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeGatheringsTask extends Gs2RestSessionTask<DescribeGatheringsResult> {
        private DescribeGatheringsRequest request;

        public DescribeGatheringsTask(
            DescribeGatheringsRequest request,
            AsyncAction<AsyncResult<DescribeGatheringsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeGatheringsResult parse(JsonNode data) {
            return DescribeGatheringsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getPageToken() != null) {
                queryStrings.add("pageToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPageToken()))));
            }
            if (this.request.getLimit() != null) {
                queryStrings.add("limit=" + String.valueOf(this.request.getLimit()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeGatheringsAsync(
            DescribeGatheringsRequest request,
            AsyncAction<AsyncResult<DescribeGatheringsResult>> callback
    ) {
        DescribeGatheringsTask task = new DescribeGatheringsTask(request, callback);
        session.execute(task);
    }

    public DescribeGatheringsResult describeGatherings(
            DescribeGatheringsRequest request
    ) {
        final AsyncResult<DescribeGatheringsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeGatheringsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateGatheringTask extends Gs2RestSessionTask<CreateGatheringResult> {
        private CreateGatheringRequest request;

        public CreateGatheringTask(
            CreateGatheringRequest request,
            AsyncAction<AsyncResult<CreateGatheringResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateGatheringResult parse(JsonNode data) {
            return CreateGatheringResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("player", request.getPlayer() != null ? request.getPlayer().toJson() : null);
                    put("attributeRanges", request.getAttributeRanges() == null ? null :
                        request.getAttributeRanges().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("capacityOfRoles", request.getCapacityOfRoles() == null ? null :
                        request.getCapacityOfRoles().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("allowUserIds", request.getAllowUserIds() == null ? null :
                        request.getAllowUserIds().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("expiresAt", request.getExpiresAt());
                    put("expiresAtTimeSpan", request.getExpiresAtTimeSpan() != null ? request.getExpiresAtTimeSpan().toJson() : null);
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void createGatheringAsync(
            CreateGatheringRequest request,
            AsyncAction<AsyncResult<CreateGatheringResult>> callback
    ) {
        CreateGatheringTask task = new CreateGatheringTask(request, callback);
        session.execute(task);
    }

    public CreateGatheringResult createGathering(
            CreateGatheringRequest request
    ) {
        final AsyncResult<CreateGatheringResult>[] resultAsyncResult = new AsyncResult[]{null};
        createGatheringAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateGatheringByUserIdTask extends Gs2RestSessionTask<CreateGatheringByUserIdResult> {
        private CreateGatheringByUserIdRequest request;

        public CreateGatheringByUserIdTask(
            CreateGatheringByUserIdRequest request,
            AsyncAction<AsyncResult<CreateGatheringByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateGatheringByUserIdResult parse(JsonNode data) {
            return CreateGatheringByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("player", request.getPlayer() != null ? request.getPlayer().toJson() : null);
                    put("attributeRanges", request.getAttributeRanges() == null ? null :
                        request.getAttributeRanges().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("capacityOfRoles", request.getCapacityOfRoles() == null ? null :
                        request.getCapacityOfRoles().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("allowUserIds", request.getAllowUserIds() == null ? null :
                        request.getAllowUserIds().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("expiresAt", request.getExpiresAt());
                    put("expiresAtTimeSpan", request.getExpiresAtTimeSpan() != null ? request.getExpiresAtTimeSpan().toJson() : null);
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void createGatheringByUserIdAsync(
            CreateGatheringByUserIdRequest request,
            AsyncAction<AsyncResult<CreateGatheringByUserIdResult>> callback
    ) {
        CreateGatheringByUserIdTask task = new CreateGatheringByUserIdTask(request, callback);
        session.execute(task);
    }

    public CreateGatheringByUserIdResult createGatheringByUserId(
            CreateGatheringByUserIdRequest request
    ) {
        final AsyncResult<CreateGatheringByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        createGatheringByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateGatheringTask extends Gs2RestSessionTask<UpdateGatheringResult> {
        private UpdateGatheringRequest request;

        public UpdateGatheringTask(
            UpdateGatheringRequest request,
            AsyncAction<AsyncResult<UpdateGatheringResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateGatheringResult parse(JsonNode data) {
            return UpdateGatheringResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null || this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("attributeRanges", request.getAttributeRanges() == null ? null :
                        request.getAttributeRanges().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void updateGatheringAsync(
            UpdateGatheringRequest request,
            AsyncAction<AsyncResult<UpdateGatheringResult>> callback
    ) {
        UpdateGatheringTask task = new UpdateGatheringTask(request, callback);
        session.execute(task);
    }

    public UpdateGatheringResult updateGathering(
            UpdateGatheringRequest request
    ) {
        final AsyncResult<UpdateGatheringResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateGatheringAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateGatheringByUserIdTask extends Gs2RestSessionTask<UpdateGatheringByUserIdResult> {
        private UpdateGatheringByUserIdRequest request;

        public UpdateGatheringByUserIdTask(
            UpdateGatheringByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateGatheringByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateGatheringByUserIdResult parse(JsonNode data) {
            return UpdateGatheringByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null || this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("attributeRanges", request.getAttributeRanges() == null ? null :
                        request.getAttributeRanges().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void updateGatheringByUserIdAsync(
            UpdateGatheringByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateGatheringByUserIdResult>> callback
    ) {
        UpdateGatheringByUserIdTask task = new UpdateGatheringByUserIdTask(request, callback);
        session.execute(task);
    }

    public UpdateGatheringByUserIdResult updateGatheringByUserId(
            UpdateGatheringByUserIdRequest request
    ) {
        final AsyncResult<UpdateGatheringByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateGatheringByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DoMatchmakingByPlayerTask extends Gs2RestSessionTask<DoMatchmakingByPlayerResult> {
        private DoMatchmakingByPlayerRequest request;

        public DoMatchmakingByPlayerTask(
            DoMatchmakingByPlayerRequest request,
            AsyncAction<AsyncResult<DoMatchmakingByPlayerResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DoMatchmakingByPlayerResult parse(JsonNode data) {
            return DoMatchmakingByPlayerResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/player/do";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("player", request.getPlayer() != null ? request.getPlayer().toJson() : null);
                    put("matchmakingContextToken", request.getMatchmakingContextToken());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void doMatchmakingByPlayerAsync(
            DoMatchmakingByPlayerRequest request,
            AsyncAction<AsyncResult<DoMatchmakingByPlayerResult>> callback
    ) {
        DoMatchmakingByPlayerTask task = new DoMatchmakingByPlayerTask(request, callback);
        session.execute(task);
    }

    public DoMatchmakingByPlayerResult doMatchmakingByPlayer(
            DoMatchmakingByPlayerRequest request
    ) {
        final AsyncResult<DoMatchmakingByPlayerResult>[] resultAsyncResult = new AsyncResult[]{null};
        doMatchmakingByPlayerAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DoMatchmakingTask extends Gs2RestSessionTask<DoMatchmakingResult> {
        private DoMatchmakingRequest request;

        public DoMatchmakingTask(
            DoMatchmakingRequest request,
            AsyncAction<AsyncResult<DoMatchmakingResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DoMatchmakingResult parse(JsonNode data) {
            return DoMatchmakingResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/do";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("player", request.getPlayer() != null ? request.getPlayer().toJson() : null);
                    put("matchmakingContextToken", request.getMatchmakingContextToken());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void doMatchmakingAsync(
            DoMatchmakingRequest request,
            AsyncAction<AsyncResult<DoMatchmakingResult>> callback
    ) {
        DoMatchmakingTask task = new DoMatchmakingTask(request, callback);
        session.execute(task);
    }

    public DoMatchmakingResult doMatchmaking(
            DoMatchmakingRequest request
    ) {
        final AsyncResult<DoMatchmakingResult>[] resultAsyncResult = new AsyncResult[]{null};
        doMatchmakingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DoMatchmakingByUserIdTask extends Gs2RestSessionTask<DoMatchmakingByUserIdResult> {
        private DoMatchmakingByUserIdRequest request;

        public DoMatchmakingByUserIdTask(
            DoMatchmakingByUserIdRequest request,
            AsyncAction<AsyncResult<DoMatchmakingByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DoMatchmakingByUserIdResult parse(JsonNode data) {
            return DoMatchmakingByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/gathering/do";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("player", request.getPlayer() != null ? request.getPlayer().toJson() : null);
                    put("matchmakingContextToken", request.getMatchmakingContextToken());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void doMatchmakingByUserIdAsync(
            DoMatchmakingByUserIdRequest request,
            AsyncAction<AsyncResult<DoMatchmakingByUserIdResult>> callback
    ) {
        DoMatchmakingByUserIdTask task = new DoMatchmakingByUserIdTask(request, callback);
        session.execute(task);
    }

    public DoMatchmakingByUserIdResult doMatchmakingByUserId(
            DoMatchmakingByUserIdRequest request
    ) {
        final AsyncResult<DoMatchmakingByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        doMatchmakingByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PingTask extends Gs2RestSessionTask<PingResult> {
        private PingRequest request;

        public PingTask(
            PingRequest request,
            AsyncAction<AsyncResult<PingResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PingResult parse(JsonNode data) {
            return PingResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}/ping";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null || this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void pingAsync(
            PingRequest request,
            AsyncAction<AsyncResult<PingResult>> callback
    ) {
        PingTask task = new PingTask(request, callback);
        session.execute(task);
    }

    public PingResult ping(
            PingRequest request
    ) {
        final AsyncResult<PingResult>[] resultAsyncResult = new AsyncResult[]{null};
        pingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PingByUserIdTask extends Gs2RestSessionTask<PingByUserIdResult> {
        private PingByUserIdRequest request;

        public PingByUserIdTask(
            PingByUserIdRequest request,
            AsyncAction<AsyncResult<PingByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PingByUserIdResult parse(JsonNode data) {
            return PingByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}/user/{userId}/ping";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null || this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void pingByUserIdAsync(
            PingByUserIdRequest request,
            AsyncAction<AsyncResult<PingByUserIdResult>> callback
    ) {
        PingByUserIdTask task = new PingByUserIdTask(request, callback);
        session.execute(task);
    }

    public PingByUserIdResult pingByUserId(
            PingByUserIdRequest request
    ) {
        final AsyncResult<PingByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        pingByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetGatheringTask extends Gs2RestSessionTask<GetGatheringResult> {
        private GetGatheringRequest request;

        public GetGatheringTask(
            GetGatheringRequest request,
            AsyncAction<AsyncResult<GetGatheringResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetGatheringResult parse(JsonNode data) {
            return GetGatheringResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null || this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void getGatheringAsync(
            GetGatheringRequest request,
            AsyncAction<AsyncResult<GetGatheringResult>> callback
    ) {
        GetGatheringTask task = new GetGatheringTask(request, callback);
        session.execute(task);
    }

    public GetGatheringResult getGathering(
            GetGatheringRequest request
    ) {
        final AsyncResult<GetGatheringResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGatheringAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CancelMatchmakingTask extends Gs2RestSessionTask<CancelMatchmakingResult> {
        private CancelMatchmakingRequest request;

        public CancelMatchmakingTask(
            CancelMatchmakingRequest request,
            AsyncAction<AsyncResult<CancelMatchmakingResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CancelMatchmakingResult parse(JsonNode data) {
            return CancelMatchmakingResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}/user/me";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null || this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.DELETE)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void cancelMatchmakingAsync(
            CancelMatchmakingRequest request,
            AsyncAction<AsyncResult<CancelMatchmakingResult>> callback
    ) {
        CancelMatchmakingTask task = new CancelMatchmakingTask(request, callback);
        session.execute(task);
    }

    public CancelMatchmakingResult cancelMatchmaking(
            CancelMatchmakingRequest request
    ) {
        final AsyncResult<CancelMatchmakingResult>[] resultAsyncResult = new AsyncResult[]{null};
        cancelMatchmakingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CancelMatchmakingByUserIdTask extends Gs2RestSessionTask<CancelMatchmakingByUserIdResult> {
        private CancelMatchmakingByUserIdRequest request;

        public CancelMatchmakingByUserIdTask(
            CancelMatchmakingByUserIdRequest request,
            AsyncAction<AsyncResult<CancelMatchmakingByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CancelMatchmakingByUserIdResult parse(JsonNode data) {
            return CancelMatchmakingByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null || this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.DELETE)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void cancelMatchmakingByUserIdAsync(
            CancelMatchmakingByUserIdRequest request,
            AsyncAction<AsyncResult<CancelMatchmakingByUserIdResult>> callback
    ) {
        CancelMatchmakingByUserIdTask task = new CancelMatchmakingByUserIdTask(request, callback);
        session.execute(task);
    }

    public CancelMatchmakingByUserIdResult cancelMatchmakingByUserId(
            CancelMatchmakingByUserIdRequest request
    ) {
        final AsyncResult<CancelMatchmakingByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        cancelMatchmakingByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class EarlyCompleteTask extends Gs2RestSessionTask<EarlyCompleteResult> {
        private EarlyCompleteRequest request;

        public EarlyCompleteTask(
            EarlyCompleteRequest request,
            AsyncAction<AsyncResult<EarlyCompleteResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public EarlyCompleteResult parse(JsonNode data) {
            return EarlyCompleteResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}/user/me/early";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null || this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.DELETE)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void earlyCompleteAsync(
            EarlyCompleteRequest request,
            AsyncAction<AsyncResult<EarlyCompleteResult>> callback
    ) {
        EarlyCompleteTask task = new EarlyCompleteTask(request, callback);
        session.execute(task);
    }

    public EarlyCompleteResult earlyComplete(
            EarlyCompleteRequest request
    ) {
        final AsyncResult<EarlyCompleteResult>[] resultAsyncResult = new AsyncResult[]{null};
        earlyCompleteAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class EarlyCompleteByUserIdTask extends Gs2RestSessionTask<EarlyCompleteByUserIdResult> {
        private EarlyCompleteByUserIdRequest request;

        public EarlyCompleteByUserIdTask(
            EarlyCompleteByUserIdRequest request,
            AsyncAction<AsyncResult<EarlyCompleteByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public EarlyCompleteByUserIdResult parse(JsonNode data) {
            return EarlyCompleteByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}/user/{userId}/early";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null || this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.DELETE)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void earlyCompleteByUserIdAsync(
            EarlyCompleteByUserIdRequest request,
            AsyncAction<AsyncResult<EarlyCompleteByUserIdResult>> callback
    ) {
        EarlyCompleteByUserIdTask task = new EarlyCompleteByUserIdTask(request, callback);
        session.execute(task);
    }

    public EarlyCompleteByUserIdResult earlyCompleteByUserId(
            EarlyCompleteByUserIdRequest request
    ) {
        final AsyncResult<EarlyCompleteByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        earlyCompleteByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteGatheringTask extends Gs2RestSessionTask<DeleteGatheringResult> {
        private DeleteGatheringRequest request;

        public DeleteGatheringTask(
            DeleteGatheringRequest request,
            AsyncAction<AsyncResult<DeleteGatheringResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteGatheringResult parse(JsonNode data) {
            return DeleteGatheringResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/gathering/{gatheringName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null || this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.DELETE)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteGatheringAsync(
            DeleteGatheringRequest request,
            AsyncAction<AsyncResult<DeleteGatheringResult>> callback
    ) {
        DeleteGatheringTask task = new DeleteGatheringTask(request, callback);
        session.execute(task);
    }

    public DeleteGatheringResult deleteGathering(
            DeleteGatheringRequest request
    ) {
        final AsyncResult<DeleteGatheringResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteGatheringAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRatingModelMastersTask extends Gs2RestSessionTask<DescribeRatingModelMastersResult> {
        private DescribeRatingModelMastersRequest request;

        public DescribeRatingModelMastersTask(
            DescribeRatingModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeRatingModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRatingModelMastersResult parse(JsonNode data) {
            return DescribeRatingModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/rating";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getPageToken() != null) {
                queryStrings.add("pageToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPageToken()))));
            }
            if (this.request.getLimit() != null) {
                queryStrings.add("limit=" + String.valueOf(this.request.getLimit()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeRatingModelMastersAsync(
            DescribeRatingModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeRatingModelMastersResult>> callback
    ) {
        DescribeRatingModelMastersTask task = new DescribeRatingModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeRatingModelMastersResult describeRatingModelMasters(
            DescribeRatingModelMastersRequest request
    ) {
        final AsyncResult<DescribeRatingModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRatingModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateRatingModelMasterTask extends Gs2RestSessionTask<CreateRatingModelMasterResult> {
        private CreateRatingModelMasterRequest request;

        public CreateRatingModelMasterTask(
            CreateRatingModelMasterRequest request,
            AsyncAction<AsyncResult<CreateRatingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateRatingModelMasterResult parse(JsonNode data) {
            return CreateRatingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/rating";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("initialValue", request.getInitialValue());
                    put("volatility", request.getVolatility());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void createRatingModelMasterAsync(
            CreateRatingModelMasterRequest request,
            AsyncAction<AsyncResult<CreateRatingModelMasterResult>> callback
    ) {
        CreateRatingModelMasterTask task = new CreateRatingModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateRatingModelMasterResult createRatingModelMaster(
            CreateRatingModelMasterRequest request
    ) {
        final AsyncResult<CreateRatingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createRatingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRatingModelMasterTask extends Gs2RestSessionTask<GetRatingModelMasterResult> {
        private GetRatingModelMasterRequest request;

        public GetRatingModelMasterTask(
            GetRatingModelMasterRequest request,
            AsyncAction<AsyncResult<GetRatingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRatingModelMasterResult parse(JsonNode data) {
            return GetRatingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null || this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void getRatingModelMasterAsync(
            GetRatingModelMasterRequest request,
            AsyncAction<AsyncResult<GetRatingModelMasterResult>> callback
    ) {
        GetRatingModelMasterTask task = new GetRatingModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetRatingModelMasterResult getRatingModelMaster(
            GetRatingModelMasterRequest request
    ) {
        final AsyncResult<GetRatingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRatingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateRatingModelMasterTask extends Gs2RestSessionTask<UpdateRatingModelMasterResult> {
        private UpdateRatingModelMasterRequest request;

        public UpdateRatingModelMasterTask(
            UpdateRatingModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateRatingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateRatingModelMasterResult parse(JsonNode data) {
            return UpdateRatingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null || this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("initialValue", request.getInitialValue());
                    put("volatility", request.getVolatility());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.PUT)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void updateRatingModelMasterAsync(
            UpdateRatingModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateRatingModelMasterResult>> callback
    ) {
        UpdateRatingModelMasterTask task = new UpdateRatingModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateRatingModelMasterResult updateRatingModelMaster(
            UpdateRatingModelMasterRequest request
    ) {
        final AsyncResult<UpdateRatingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateRatingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRatingModelMasterTask extends Gs2RestSessionTask<DeleteRatingModelMasterResult> {
        private DeleteRatingModelMasterRequest request;

        public DeleteRatingModelMasterTask(
            DeleteRatingModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteRatingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteRatingModelMasterResult parse(JsonNode data) {
            return DeleteRatingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null || this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.DELETE)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteRatingModelMasterAsync(
            DeleteRatingModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteRatingModelMasterResult>> callback
    ) {
        DeleteRatingModelMasterTask task = new DeleteRatingModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteRatingModelMasterResult deleteRatingModelMaster(
            DeleteRatingModelMasterRequest request
    ) {
        final AsyncResult<DeleteRatingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRatingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRatingModelsTask extends Gs2RestSessionTask<DescribeRatingModelsResult> {
        private DescribeRatingModelsRequest request;

        public DescribeRatingModelsTask(
            DescribeRatingModelsRequest request,
            AsyncAction<AsyncResult<DescribeRatingModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRatingModelsResult parse(JsonNode data) {
            return DescribeRatingModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/rating";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeRatingModelsAsync(
            DescribeRatingModelsRequest request,
            AsyncAction<AsyncResult<DescribeRatingModelsResult>> callback
    ) {
        DescribeRatingModelsTask task = new DescribeRatingModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeRatingModelsResult describeRatingModels(
            DescribeRatingModelsRequest request
    ) {
        final AsyncResult<DescribeRatingModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRatingModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRatingModelTask extends Gs2RestSessionTask<GetRatingModelResult> {
        private GetRatingModelRequest request;

        public GetRatingModelTask(
            GetRatingModelRequest request,
            AsyncAction<AsyncResult<GetRatingModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRatingModelResult parse(JsonNode data) {
            return GetRatingModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null || this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void getRatingModelAsync(
            GetRatingModelRequest request,
            AsyncAction<AsyncResult<GetRatingModelResult>> callback
    ) {
        GetRatingModelTask task = new GetRatingModelTask(request, callback);
        session.execute(task);
    }

    public GetRatingModelResult getRatingModel(
            GetRatingModelRequest request
    ) {
        final AsyncResult<GetRatingModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRatingModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ExportMasterTask extends Gs2RestSessionTask<ExportMasterResult> {
        private ExportMasterRequest request;

        public ExportMasterTask(
            ExportMasterRequest request,
            AsyncAction<AsyncResult<ExportMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ExportMasterResult parse(JsonNode data) {
            return ExportMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/export";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void exportMasterAsync(
            ExportMasterRequest request,
            AsyncAction<AsyncResult<ExportMasterResult>> callback
    ) {
        ExportMasterTask task = new ExportMasterTask(request, callback);
        session.execute(task);
    }

    public ExportMasterResult exportMaster(
            ExportMasterRequest request
    ) {
        final AsyncResult<ExportMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        exportMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCurrentModelMasterTask extends Gs2RestSessionTask<GetCurrentModelMasterResult> {
        private GetCurrentModelMasterRequest request;

        public GetCurrentModelMasterTask(
            GetCurrentModelMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentModelMasterResult parse(JsonNode data) {
            return GetCurrentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void getCurrentModelMasterAsync(
            GetCurrentModelMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentModelMasterResult>> callback
    ) {
        GetCurrentModelMasterTask task = new GetCurrentModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentModelMasterResult getCurrentModelMaster(
            GetCurrentModelMasterRequest request
    ) {
        final AsyncResult<GetCurrentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PreUpdateCurrentModelMasterTask extends Gs2RestSessionTask<PreUpdateCurrentModelMasterResult> {
        private PreUpdateCurrentModelMasterRequest request;

        public PreUpdateCurrentModelMasterTask(
            PreUpdateCurrentModelMasterRequest request,
            AsyncAction<AsyncResult<PreUpdateCurrentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PreUpdateCurrentModelMasterResult parse(JsonNode data) {
            return PreUpdateCurrentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void preUpdateCurrentModelMasterAsync(
            PreUpdateCurrentModelMasterRequest request,
            AsyncAction<AsyncResult<PreUpdateCurrentModelMasterResult>> callback
    ) {
        PreUpdateCurrentModelMasterTask task = new PreUpdateCurrentModelMasterTask(request, callback);
        session.execute(task);
    }

    public PreUpdateCurrentModelMasterResult preUpdateCurrentModelMaster(
            PreUpdateCurrentModelMasterRequest request
    ) {
        final AsyncResult<PreUpdateCurrentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        preUpdateCurrentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentModelMasterTask extends Gs2RestSessionTask<UpdateCurrentModelMasterResult> {
        private UpdateCurrentModelMasterRequest request;

        public UpdateCurrentModelMasterTask(
            UpdateCurrentModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentModelMasterResult parse(JsonNode data) {
            return UpdateCurrentModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {
            if (request.getSettings() != null) {
                AtomicReference<AsyncResult<PreUpdateCurrentModelMasterResult>> resultAsyncResult = new AtomicReference<>();
                PreUpdateCurrentModelMasterTask task = new PreUpdateCurrentModelMasterTask(
                        new PreUpdateCurrentModelMasterRequest()
                                .withContextStack(request.getContextStack())
                                .withNamespaceName(request.getNamespaceName()),
                        result -> resultAsyncResult.set(result)
                );
                session.execute(task);
                while (resultAsyncResult.get() == null) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {}
                }
                if (resultAsyncResult.get().getError() != null) {
                    throw resultAsyncResult.get().getError();
                }
                {
                    byte[] b = request.getSettings().getBytes();
                    try (org.apache.http.impl.client.CloseableHttpClient client = org.apache.http.impl.client.HttpClients.createDefault()) {
                        org.apache.http.client.methods.HttpPut request = new org.apache.http.client.methods.HttpPut(resultAsyncResult.get().getResult().getUploadUrl());
                        request.addHeader("Content-Type", "application/json");
                        org.apache.http.entity.BasicHttpEntity entity = new org.apache.http.entity.BasicHttpEntity();
                        entity.setContent(new java.io.ByteArrayInputStream(b));
                        entity.setContentLength(b.length);
                        request.setEntity(entity);
                        org.apache.http.HttpResponse result = client.execute(request);
                    } catch (java.io.IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                request = request
                        .withMode("preUpload")
                        .withUploadToken(resultAsyncResult.get().getResult().getUploadToken())
                        .withSettings(null);
            }

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("mode", request.getMode());
                    put("settings", request.getSettings());
                    put("uploadToken", request.getUploadToken());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.PUT)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void updateCurrentModelMasterAsync(
            UpdateCurrentModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentModelMasterResult>> callback
    ) {
        UpdateCurrentModelMasterTask task = new UpdateCurrentModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentModelMasterResult updateCurrentModelMaster(
            UpdateCurrentModelMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentModelMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentModelMasterFromGitHubResult> {
        private UpdateCurrentModelMasterFromGitHubRequest request;

        public UpdateCurrentModelMasterFromGitHubTask(
            UpdateCurrentModelMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentModelMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentModelMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentModelMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/from_git_hub";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("checkoutSetting", request.getCheckoutSetting() != null ? request.getCheckoutSetting().toJson() : null);
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.PUT)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void updateCurrentModelMasterFromGitHubAsync(
            UpdateCurrentModelMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentModelMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentModelMasterFromGitHubTask task = new UpdateCurrentModelMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentModelMasterFromGitHubResult updateCurrentModelMasterFromGitHub(
            UpdateCurrentModelMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentModelMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentModelMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSeasonModelsTask extends Gs2RestSessionTask<DescribeSeasonModelsResult> {
        private DescribeSeasonModelsRequest request;

        public DescribeSeasonModelsTask(
            DescribeSeasonModelsRequest request,
            AsyncAction<AsyncResult<DescribeSeasonModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSeasonModelsResult parse(JsonNode data) {
            return DescribeSeasonModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/season";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeSeasonModelsAsync(
            DescribeSeasonModelsRequest request,
            AsyncAction<AsyncResult<DescribeSeasonModelsResult>> callback
    ) {
        DescribeSeasonModelsTask task = new DescribeSeasonModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeSeasonModelsResult describeSeasonModels(
            DescribeSeasonModelsRequest request
    ) {
        final AsyncResult<DescribeSeasonModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSeasonModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSeasonModelTask extends Gs2RestSessionTask<GetSeasonModelResult> {
        private GetSeasonModelRequest request;

        public GetSeasonModelTask(
            GetSeasonModelRequest request,
            AsyncAction<AsyncResult<GetSeasonModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSeasonModelResult parse(JsonNode data) {
            return GetSeasonModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/season/{seasonName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void getSeasonModelAsync(
            GetSeasonModelRequest request,
            AsyncAction<AsyncResult<GetSeasonModelResult>> callback
    ) {
        GetSeasonModelTask task = new GetSeasonModelTask(request, callback);
        session.execute(task);
    }

    public GetSeasonModelResult getSeasonModel(
            GetSeasonModelRequest request
    ) {
        final AsyncResult<GetSeasonModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSeasonModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSeasonModelMastersTask extends Gs2RestSessionTask<DescribeSeasonModelMastersResult> {
        private DescribeSeasonModelMastersRequest request;

        public DescribeSeasonModelMastersTask(
            DescribeSeasonModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeSeasonModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSeasonModelMastersResult parse(JsonNode data) {
            return DescribeSeasonModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/season";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getPageToken() != null) {
                queryStrings.add("pageToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPageToken()))));
            }
            if (this.request.getLimit() != null) {
                queryStrings.add("limit=" + String.valueOf(this.request.getLimit()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeSeasonModelMastersAsync(
            DescribeSeasonModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeSeasonModelMastersResult>> callback
    ) {
        DescribeSeasonModelMastersTask task = new DescribeSeasonModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeSeasonModelMastersResult describeSeasonModelMasters(
            DescribeSeasonModelMastersRequest request
    ) {
        final AsyncResult<DescribeSeasonModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSeasonModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateSeasonModelMasterTask extends Gs2RestSessionTask<CreateSeasonModelMasterResult> {
        private CreateSeasonModelMasterRequest request;

        public CreateSeasonModelMasterTask(
            CreateSeasonModelMasterRequest request,
            AsyncAction<AsyncResult<CreateSeasonModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateSeasonModelMasterResult parse(JsonNode data) {
            return CreateSeasonModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/season";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("maximumParticipants", request.getMaximumParticipants());
                    put("experienceModelId", request.getExperienceModelId());
                    put("challengePeriodEventId", request.getChallengePeriodEventId());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void createSeasonModelMasterAsync(
            CreateSeasonModelMasterRequest request,
            AsyncAction<AsyncResult<CreateSeasonModelMasterResult>> callback
    ) {
        CreateSeasonModelMasterTask task = new CreateSeasonModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateSeasonModelMasterResult createSeasonModelMaster(
            CreateSeasonModelMasterRequest request
    ) {
        final AsyncResult<CreateSeasonModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createSeasonModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSeasonModelMasterTask extends Gs2RestSessionTask<GetSeasonModelMasterResult> {
        private GetSeasonModelMasterRequest request;

        public GetSeasonModelMasterTask(
            GetSeasonModelMasterRequest request,
            AsyncAction<AsyncResult<GetSeasonModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSeasonModelMasterResult parse(JsonNode data) {
            return GetSeasonModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/season/{seasonName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void getSeasonModelMasterAsync(
            GetSeasonModelMasterRequest request,
            AsyncAction<AsyncResult<GetSeasonModelMasterResult>> callback
    ) {
        GetSeasonModelMasterTask task = new GetSeasonModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetSeasonModelMasterResult getSeasonModelMaster(
            GetSeasonModelMasterRequest request
    ) {
        final AsyncResult<GetSeasonModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSeasonModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateSeasonModelMasterTask extends Gs2RestSessionTask<UpdateSeasonModelMasterResult> {
        private UpdateSeasonModelMasterRequest request;

        public UpdateSeasonModelMasterTask(
            UpdateSeasonModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateSeasonModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateSeasonModelMasterResult parse(JsonNode data) {
            return UpdateSeasonModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/season/{seasonName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("maximumParticipants", request.getMaximumParticipants());
                    put("experienceModelId", request.getExperienceModelId());
                    put("challengePeriodEventId", request.getChallengePeriodEventId());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.PUT)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void updateSeasonModelMasterAsync(
            UpdateSeasonModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateSeasonModelMasterResult>> callback
    ) {
        UpdateSeasonModelMasterTask task = new UpdateSeasonModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateSeasonModelMasterResult updateSeasonModelMaster(
            UpdateSeasonModelMasterRequest request
    ) {
        final AsyncResult<UpdateSeasonModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateSeasonModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteSeasonModelMasterTask extends Gs2RestSessionTask<DeleteSeasonModelMasterResult> {
        private DeleteSeasonModelMasterRequest request;

        public DeleteSeasonModelMasterTask(
            DeleteSeasonModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteSeasonModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteSeasonModelMasterResult parse(JsonNode data) {
            return DeleteSeasonModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/season/{seasonName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.DELETE)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteSeasonModelMasterAsync(
            DeleteSeasonModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteSeasonModelMasterResult>> callback
    ) {
        DeleteSeasonModelMasterTask task = new DeleteSeasonModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteSeasonModelMasterResult deleteSeasonModelMaster(
            DeleteSeasonModelMasterRequest request
    ) {
        final AsyncResult<DeleteSeasonModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteSeasonModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSeasonGatheringsTask extends Gs2RestSessionTask<DescribeSeasonGatheringsResult> {
        private DescribeSeasonGatheringsRequest request;

        public DescribeSeasonGatheringsTask(
            DescribeSeasonGatheringsRequest request,
            AsyncAction<AsyncResult<DescribeSeasonGatheringsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSeasonGatheringsResult parse(JsonNode data) {
            return DescribeSeasonGatheringsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/season/{seasonName}/{season}/gathering";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));
            url = url.replace("{season}", this.request.getSeason() == null  ? "null" : String.valueOf(this.request.getSeason()));
            url = url.replace("{tier}", this.request.getTier() == null  ? "null" : String.valueOf(this.request.getTier()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getPageToken() != null) {
                queryStrings.add("pageToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPageToken()))));
            }
            if (this.request.getLimit() != null) {
                queryStrings.add("limit=" + String.valueOf(this.request.getLimit()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeSeasonGatheringsAsync(
            DescribeSeasonGatheringsRequest request,
            AsyncAction<AsyncResult<DescribeSeasonGatheringsResult>> callback
    ) {
        DescribeSeasonGatheringsTask task = new DescribeSeasonGatheringsTask(request, callback);
        session.execute(task);
    }

    public DescribeSeasonGatheringsResult describeSeasonGatherings(
            DescribeSeasonGatheringsRequest request
    ) {
        final AsyncResult<DescribeSeasonGatheringsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSeasonGatheringsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMatchmakingSeasonGatheringsTask extends Gs2RestSessionTask<DescribeMatchmakingSeasonGatheringsResult> {
        private DescribeMatchmakingSeasonGatheringsRequest request;

        public DescribeMatchmakingSeasonGatheringsTask(
            DescribeMatchmakingSeasonGatheringsRequest request,
            AsyncAction<AsyncResult<DescribeMatchmakingSeasonGatheringsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeMatchmakingSeasonGatheringsResult parse(JsonNode data) {
            return DescribeMatchmakingSeasonGatheringsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/season/{seasonName}/{season}/gathering/matchmaking";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));
            url = url.replace("{season}", this.request.getSeason() == null  ? "null" : String.valueOf(this.request.getSeason()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getTier() != null) {
                queryStrings.add("tier=" + String.valueOf(this.request.getTier()));
            }
            if (this.request.getPageToken() != null) {
                queryStrings.add("pageToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPageToken()))));
            }
            if (this.request.getLimit() != null) {
                queryStrings.add("limit=" + String.valueOf(this.request.getLimit()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeMatchmakingSeasonGatheringsAsync(
            DescribeMatchmakingSeasonGatheringsRequest request,
            AsyncAction<AsyncResult<DescribeMatchmakingSeasonGatheringsResult>> callback
    ) {
        DescribeMatchmakingSeasonGatheringsTask task = new DescribeMatchmakingSeasonGatheringsTask(request, callback);
        session.execute(task);
    }

    public DescribeMatchmakingSeasonGatheringsResult describeMatchmakingSeasonGatherings(
            DescribeMatchmakingSeasonGatheringsRequest request
    ) {
        final AsyncResult<DescribeMatchmakingSeasonGatheringsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMatchmakingSeasonGatheringsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DoSeasonMatchmakingTask extends Gs2RestSessionTask<DoSeasonMatchmakingResult> {
        private DoSeasonMatchmakingRequest request;

        public DoSeasonMatchmakingTask(
            DoSeasonMatchmakingRequest request,
            AsyncAction<AsyncResult<DoSeasonMatchmakingResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DoSeasonMatchmakingResult parse(JsonNode data) {
            return DoSeasonMatchmakingResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/season/{seasonName}/gathering/do";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("matchmakingContextToken", request.getMatchmakingContextToken());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void doSeasonMatchmakingAsync(
            DoSeasonMatchmakingRequest request,
            AsyncAction<AsyncResult<DoSeasonMatchmakingResult>> callback
    ) {
        DoSeasonMatchmakingTask task = new DoSeasonMatchmakingTask(request, callback);
        session.execute(task);
    }

    public DoSeasonMatchmakingResult doSeasonMatchmaking(
            DoSeasonMatchmakingRequest request
    ) {
        final AsyncResult<DoSeasonMatchmakingResult>[] resultAsyncResult = new AsyncResult[]{null};
        doSeasonMatchmakingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DoSeasonMatchmakingByUserIdTask extends Gs2RestSessionTask<DoSeasonMatchmakingByUserIdResult> {
        private DoSeasonMatchmakingByUserIdRequest request;

        public DoSeasonMatchmakingByUserIdTask(
            DoSeasonMatchmakingByUserIdRequest request,
            AsyncAction<AsyncResult<DoSeasonMatchmakingByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DoSeasonMatchmakingByUserIdResult parse(JsonNode data) {
            return DoSeasonMatchmakingByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/season/{seasonName}/gathering/do";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("matchmakingContextToken", request.getMatchmakingContextToken());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void doSeasonMatchmakingByUserIdAsync(
            DoSeasonMatchmakingByUserIdRequest request,
            AsyncAction<AsyncResult<DoSeasonMatchmakingByUserIdResult>> callback
    ) {
        DoSeasonMatchmakingByUserIdTask task = new DoSeasonMatchmakingByUserIdTask(request, callback);
        session.execute(task);
    }

    public DoSeasonMatchmakingByUserIdResult doSeasonMatchmakingByUserId(
            DoSeasonMatchmakingByUserIdRequest request
    ) {
        final AsyncResult<DoSeasonMatchmakingByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        doSeasonMatchmakingByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSeasonGatheringTask extends Gs2RestSessionTask<GetSeasonGatheringResult> {
        private GetSeasonGatheringRequest request;

        public GetSeasonGatheringTask(
            GetSeasonGatheringRequest request,
            AsyncAction<AsyncResult<GetSeasonGatheringResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSeasonGatheringResult parse(JsonNode data) {
            return GetSeasonGatheringResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/season/{seasonName}/{season}/{tier}/gathering/{seasonGatheringName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));
            url = url.replace("{season}", this.request.getSeason() == null  ? "null" : String.valueOf(this.request.getSeason()));
            url = url.replace("{tier}", this.request.getTier() == null  ? "null" : String.valueOf(this.request.getTier()));
            url = url.replace("{seasonGatheringName}", this.request.getSeasonGatheringName() == null || this.request.getSeasonGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonGatheringName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void getSeasonGatheringAsync(
            GetSeasonGatheringRequest request,
            AsyncAction<AsyncResult<GetSeasonGatheringResult>> callback
    ) {
        GetSeasonGatheringTask task = new GetSeasonGatheringTask(request, callback);
        session.execute(task);
    }

    public GetSeasonGatheringResult getSeasonGathering(
            GetSeasonGatheringRequest request
    ) {
        final AsyncResult<GetSeasonGatheringResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSeasonGatheringAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyIncludeParticipantTask extends Gs2RestSessionTask<VerifyIncludeParticipantResult> {
        private VerifyIncludeParticipantRequest request;

        public VerifyIncludeParticipantTask(
            VerifyIncludeParticipantRequest request,
            AsyncAction<AsyncResult<VerifyIncludeParticipantResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyIncludeParticipantResult parse(JsonNode data) {
            return VerifyIncludeParticipantResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/season/{seasonName}/{season}/{tier}/gathering/{seasonGatheringName}/participant/me/verify";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));
            url = url.replace("{season}", this.request.getSeason() == null  ? "null" : String.valueOf(this.request.getSeason()));
            url = url.replace("{tier}", this.request.getTier() == null  ? "null" : String.valueOf(this.request.getTier()));
            url = url.replace("{seasonGatheringName}", this.request.getSeasonGatheringName() == null || this.request.getSeasonGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonGatheringName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("verifyType", request.getVerifyType());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void verifyIncludeParticipantAsync(
            VerifyIncludeParticipantRequest request,
            AsyncAction<AsyncResult<VerifyIncludeParticipantResult>> callback
    ) {
        VerifyIncludeParticipantTask task = new VerifyIncludeParticipantTask(request, callback);
        session.execute(task);
    }

    public VerifyIncludeParticipantResult verifyIncludeParticipant(
            VerifyIncludeParticipantRequest request
    ) {
        final AsyncResult<VerifyIncludeParticipantResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyIncludeParticipantAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyIncludeParticipantByUserIdTask extends Gs2RestSessionTask<VerifyIncludeParticipantByUserIdResult> {
        private VerifyIncludeParticipantByUserIdRequest request;

        public VerifyIncludeParticipantByUserIdTask(
            VerifyIncludeParticipantByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyIncludeParticipantByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyIncludeParticipantByUserIdResult parse(JsonNode data) {
            return VerifyIncludeParticipantByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/season/{seasonName}/{season}/{tier}/gathering/{seasonGatheringName}/participant/{userId}/verify";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));
            url = url.replace("{season}", this.request.getSeason() == null  ? "null" : String.valueOf(this.request.getSeason()));
            url = url.replace("{tier}", this.request.getTier() == null  ? "null" : String.valueOf(this.request.getTier()));
            url = url.replace("{seasonGatheringName}", this.request.getSeasonGatheringName() == null || this.request.getSeasonGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonGatheringName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("verifyType", request.getVerifyType());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void verifyIncludeParticipantByUserIdAsync(
            VerifyIncludeParticipantByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyIncludeParticipantByUserIdResult>> callback
    ) {
        VerifyIncludeParticipantByUserIdTask task = new VerifyIncludeParticipantByUserIdTask(request, callback);
        session.execute(task);
    }

    public VerifyIncludeParticipantByUserIdResult verifyIncludeParticipantByUserId(
            VerifyIncludeParticipantByUserIdRequest request
    ) {
        final AsyncResult<VerifyIncludeParticipantByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyIncludeParticipantByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteSeasonGatheringTask extends Gs2RestSessionTask<DeleteSeasonGatheringResult> {
        private DeleteSeasonGatheringRequest request;

        public DeleteSeasonGatheringTask(
            DeleteSeasonGatheringRequest request,
            AsyncAction<AsyncResult<DeleteSeasonGatheringResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteSeasonGatheringResult parse(JsonNode data) {
            return DeleteSeasonGatheringResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/season/{seasonName}/{season}/{tier}/gathering/{seasonGatheringName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));
            url = url.replace("{season}", this.request.getSeason() == null  ? "null" : String.valueOf(this.request.getSeason()));
            url = url.replace("{tier}", this.request.getTier() == null  ? "null" : String.valueOf(this.request.getTier()));
            url = url.replace("{seasonGatheringName}", this.request.getSeasonGatheringName() == null || this.request.getSeasonGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonGatheringName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.DELETE)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteSeasonGatheringAsync(
            DeleteSeasonGatheringRequest request,
            AsyncAction<AsyncResult<DeleteSeasonGatheringResult>> callback
    ) {
        DeleteSeasonGatheringTask task = new DeleteSeasonGatheringTask(request, callback);
        session.execute(task);
    }

    public DeleteSeasonGatheringResult deleteSeasonGathering(
            DeleteSeasonGatheringRequest request
    ) {
        final AsyncResult<DeleteSeasonGatheringResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteSeasonGatheringAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyIncludeParticipantByStampTaskTask extends Gs2RestSessionTask<VerifyIncludeParticipantByStampTaskResult> {
        private VerifyIncludeParticipantByStampTaskRequest request;

        public VerifyIncludeParticipantByStampTaskTask(
            VerifyIncludeParticipantByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyIncludeParticipantByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyIncludeParticipantByStampTaskResult parse(JsonNode data) {
            return VerifyIncludeParticipantByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/season/gathering/participant/verify";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stampTask", request.getStampTask());
                    put("keyId", request.getKeyId());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void verifyIncludeParticipantByStampTaskAsync(
            VerifyIncludeParticipantByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyIncludeParticipantByStampTaskResult>> callback
    ) {
        VerifyIncludeParticipantByStampTaskTask task = new VerifyIncludeParticipantByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifyIncludeParticipantByStampTaskResult verifyIncludeParticipantByStampTask(
            VerifyIncludeParticipantByStampTaskRequest request
    ) {
        final AsyncResult<VerifyIncludeParticipantByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyIncludeParticipantByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeJoinedSeasonGatheringsTask extends Gs2RestSessionTask<DescribeJoinedSeasonGatheringsResult> {
        private DescribeJoinedSeasonGatheringsRequest request;

        public DescribeJoinedSeasonGatheringsTask(
            DescribeJoinedSeasonGatheringsRequest request,
            AsyncAction<AsyncResult<DescribeJoinedSeasonGatheringsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeJoinedSeasonGatheringsResult parse(JsonNode data) {
            return DescribeJoinedSeasonGatheringsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/season/{seasonName}/gathering/join";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getPageToken() != null) {
                queryStrings.add("pageToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPageToken()))));
            }
            if (this.request.getLimit() != null) {
                queryStrings.add("limit=" + String.valueOf(this.request.getLimit()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeJoinedSeasonGatheringsAsync(
            DescribeJoinedSeasonGatheringsRequest request,
            AsyncAction<AsyncResult<DescribeJoinedSeasonGatheringsResult>> callback
    ) {
        DescribeJoinedSeasonGatheringsTask task = new DescribeJoinedSeasonGatheringsTask(request, callback);
        session.execute(task);
    }

    public DescribeJoinedSeasonGatheringsResult describeJoinedSeasonGatherings(
            DescribeJoinedSeasonGatheringsRequest request
    ) {
        final AsyncResult<DescribeJoinedSeasonGatheringsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeJoinedSeasonGatheringsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeJoinedSeasonGatheringsByUserIdTask extends Gs2RestSessionTask<DescribeJoinedSeasonGatheringsByUserIdResult> {
        private DescribeJoinedSeasonGatheringsByUserIdRequest request;

        public DescribeJoinedSeasonGatheringsByUserIdTask(
            DescribeJoinedSeasonGatheringsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeJoinedSeasonGatheringsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeJoinedSeasonGatheringsByUserIdResult parse(JsonNode data) {
            return DescribeJoinedSeasonGatheringsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/season/{seasonName}/gathering/join";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getPageToken() != null) {
                queryStrings.add("pageToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPageToken()))));
            }
            if (this.request.getLimit() != null) {
                queryStrings.add("limit=" + String.valueOf(this.request.getLimit()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeJoinedSeasonGatheringsByUserIdAsync(
            DescribeJoinedSeasonGatheringsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeJoinedSeasonGatheringsByUserIdResult>> callback
    ) {
        DescribeJoinedSeasonGatheringsByUserIdTask task = new DescribeJoinedSeasonGatheringsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeJoinedSeasonGatheringsByUserIdResult describeJoinedSeasonGatheringsByUserId(
            DescribeJoinedSeasonGatheringsByUserIdRequest request
    ) {
        final AsyncResult<DescribeJoinedSeasonGatheringsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeJoinedSeasonGatheringsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetJoinedSeasonGatheringTask extends Gs2RestSessionTask<GetJoinedSeasonGatheringResult> {
        private GetJoinedSeasonGatheringRequest request;

        public GetJoinedSeasonGatheringTask(
            GetJoinedSeasonGatheringRequest request,
            AsyncAction<AsyncResult<GetJoinedSeasonGatheringResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetJoinedSeasonGatheringResult parse(JsonNode data) {
            return GetJoinedSeasonGatheringResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/season/{seasonName}/gathering/join/{season}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));
            url = url.replace("{season}", this.request.getSeason() == null  ? "null" : String.valueOf(this.request.getSeason()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void getJoinedSeasonGatheringAsync(
            GetJoinedSeasonGatheringRequest request,
            AsyncAction<AsyncResult<GetJoinedSeasonGatheringResult>> callback
    ) {
        GetJoinedSeasonGatheringTask task = new GetJoinedSeasonGatheringTask(request, callback);
        session.execute(task);
    }

    public GetJoinedSeasonGatheringResult getJoinedSeasonGathering(
            GetJoinedSeasonGatheringRequest request
    ) {
        final AsyncResult<GetJoinedSeasonGatheringResult>[] resultAsyncResult = new AsyncResult[]{null};
        getJoinedSeasonGatheringAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetJoinedSeasonGatheringByUserIdTask extends Gs2RestSessionTask<GetJoinedSeasonGatheringByUserIdResult> {
        private GetJoinedSeasonGatheringByUserIdRequest request;

        public GetJoinedSeasonGatheringByUserIdTask(
            GetJoinedSeasonGatheringByUserIdRequest request,
            AsyncAction<AsyncResult<GetJoinedSeasonGatheringByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetJoinedSeasonGatheringByUserIdResult parse(JsonNode data) {
            return GetJoinedSeasonGatheringByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/season/{seasonName}/gathering/join/{season}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{seasonName}", this.request.getSeasonName() == null || this.request.getSeasonName().length() == 0 ? "null" : String.valueOf(this.request.getSeasonName()));
            url = url.replace("{season}", this.request.getSeason() == null  ? "null" : String.valueOf(this.request.getSeason()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void getJoinedSeasonGatheringByUserIdAsync(
            GetJoinedSeasonGatheringByUserIdRequest request,
            AsyncAction<AsyncResult<GetJoinedSeasonGatheringByUserIdResult>> callback
    ) {
        GetJoinedSeasonGatheringByUserIdTask task = new GetJoinedSeasonGatheringByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetJoinedSeasonGatheringByUserIdResult getJoinedSeasonGatheringByUserId(
            GetJoinedSeasonGatheringByUserIdRequest request
    ) {
        final AsyncResult<GetJoinedSeasonGatheringByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getJoinedSeasonGatheringByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRatingsTask extends Gs2RestSessionTask<DescribeRatingsResult> {
        private DescribeRatingsRequest request;

        public DescribeRatingsTask(
            DescribeRatingsRequest request,
            AsyncAction<AsyncResult<DescribeRatingsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRatingsResult parse(JsonNode data) {
            return DescribeRatingsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/rating";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getPageToken() != null) {
                queryStrings.add("pageToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPageToken()))));
            }
            if (this.request.getLimit() != null) {
                queryStrings.add("limit=" + String.valueOf(this.request.getLimit()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeRatingsAsync(
            DescribeRatingsRequest request,
            AsyncAction<AsyncResult<DescribeRatingsResult>> callback
    ) {
        DescribeRatingsTask task = new DescribeRatingsTask(request, callback);
        session.execute(task);
    }

    public DescribeRatingsResult describeRatings(
            DescribeRatingsRequest request
    ) {
        final AsyncResult<DescribeRatingsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRatingsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRatingsByUserIdTask extends Gs2RestSessionTask<DescribeRatingsByUserIdResult> {
        private DescribeRatingsByUserIdRequest request;

        public DescribeRatingsByUserIdTask(
            DescribeRatingsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRatingsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRatingsByUserIdResult parse(JsonNode data) {
            return DescribeRatingsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/rating";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getPageToken() != null) {
                queryStrings.add("pageToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getPageToken()))));
            }
            if (this.request.getLimit() != null) {
                queryStrings.add("limit=" + String.valueOf(this.request.getLimit()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeRatingsByUserIdAsync(
            DescribeRatingsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRatingsByUserIdResult>> callback
    ) {
        DescribeRatingsByUserIdTask task = new DescribeRatingsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeRatingsByUserIdResult describeRatingsByUserId(
            DescribeRatingsByUserIdRequest request
    ) {
        final AsyncResult<DescribeRatingsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRatingsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRatingTask extends Gs2RestSessionTask<GetRatingResult> {
        private GetRatingRequest request;

        public GetRatingTask(
            GetRatingRequest request,
            AsyncAction<AsyncResult<GetRatingResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRatingResult parse(JsonNode data) {
            return GetRatingResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null || this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void getRatingAsync(
            GetRatingRequest request,
            AsyncAction<AsyncResult<GetRatingResult>> callback
    ) {
        GetRatingTask task = new GetRatingTask(request, callback);
        session.execute(task);
    }

    public GetRatingResult getRating(
            GetRatingRequest request
    ) {
        final AsyncResult<GetRatingResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRatingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRatingByUserIdTask extends Gs2RestSessionTask<GetRatingByUserIdResult> {
        private GetRatingByUserIdRequest request;

        public GetRatingByUserIdTask(
            GetRatingByUserIdRequest request,
            AsyncAction<AsyncResult<GetRatingByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRatingByUserIdResult parse(JsonNode data) {
            return GetRatingByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null || this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.GET)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void getRatingByUserIdAsync(
            GetRatingByUserIdRequest request,
            AsyncAction<AsyncResult<GetRatingByUserIdResult>> callback
    ) {
        GetRatingByUserIdTask task = new GetRatingByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetRatingByUserIdResult getRatingByUserId(
            GetRatingByUserIdRequest request
    ) {
        final AsyncResult<GetRatingByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRatingByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PutResultTask extends Gs2RestSessionTask<PutResultResult> {
        private PutResultRequest request;

        public PutResultTask(
            PutResultRequest request,
            AsyncAction<AsyncResult<PutResultResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PutResultResult parse(JsonNode data) {
            return PutResultResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/rating/{ratingName}/vote";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null || this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("gameResults", request.getGameResults() == null ? null :
                        request.getGameResults().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void putResultAsync(
            PutResultRequest request,
            AsyncAction<AsyncResult<PutResultResult>> callback
    ) {
        PutResultTask task = new PutResultTask(request, callback);
        session.execute(task);
    }

    public PutResultResult putResult(
            PutResultRequest request
    ) {
        final AsyncResult<PutResultResult>[] resultAsyncResult = new AsyncResult[]{null};
        putResultAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRatingTask extends Gs2RestSessionTask<DeleteRatingResult> {
        private DeleteRatingRequest request;

        public DeleteRatingTask(
            DeleteRatingRequest request,
            AsyncAction<AsyncResult<DeleteRatingResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteRatingResult parse(JsonNode data) {
            return DeleteRatingResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/rating/{ratingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null || this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            url += "?" + String.join("&", queryStrings);

            builder
                .setMethod(HttpTask.Method.DELETE)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteRatingAsync(
            DeleteRatingRequest request,
            AsyncAction<AsyncResult<DeleteRatingResult>> callback
    ) {
        DeleteRatingTask task = new DeleteRatingTask(request, callback);
        session.execute(task);
    }

    public DeleteRatingResult deleteRating(
            DeleteRatingRequest request
    ) {
        final AsyncResult<DeleteRatingResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRatingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetBallotTask extends Gs2RestSessionTask<GetBallotResult> {
        private GetBallotRequest request;

        public GetBallotTask(
            GetBallotRequest request,
            AsyncAction<AsyncResult<GetBallotResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBallotResult parse(JsonNode data) {
            return GetBallotResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/vote/{ratingName}/{gatheringName}/ballot";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null || this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null || this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("numberOfPlayer", request.getNumberOfPlayer());
                    put("keyId", request.getKeyId());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void getBallotAsync(
            GetBallotRequest request,
            AsyncAction<AsyncResult<GetBallotResult>> callback
    ) {
        GetBallotTask task = new GetBallotTask(request, callback);
        session.execute(task);
    }

    public GetBallotResult getBallot(
            GetBallotRequest request
    ) {
        final AsyncResult<GetBallotResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBallotAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetBallotByUserIdTask extends Gs2RestSessionTask<GetBallotByUserIdResult> {
        private GetBallotByUserIdRequest request;

        public GetBallotByUserIdTask(
            GetBallotByUserIdRequest request,
            AsyncAction<AsyncResult<GetBallotByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBallotByUserIdResult parse(JsonNode data) {
            return GetBallotByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/vote/{ratingName}/{gatheringName}/ballot";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null || this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null || this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("numberOfPlayer", request.getNumberOfPlayer());
                    put("keyId", request.getKeyId());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void getBallotByUserIdAsync(
            GetBallotByUserIdRequest request,
            AsyncAction<AsyncResult<GetBallotByUserIdResult>> callback
    ) {
        GetBallotByUserIdTask task = new GetBallotByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetBallotByUserIdResult getBallotByUserId(
            GetBallotByUserIdRequest request
    ) {
        final AsyncResult<GetBallotByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBallotByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VoteTask extends Gs2RestSessionTask<VoteResult> {
        private VoteRequest request;

        public VoteTask(
            VoteRequest request,
            AsyncAction<AsyncResult<VoteResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VoteResult parse(JsonNode data) {
            return VoteResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/action/vote";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("ballotBody", request.getBallotBody());
                    put("ballotSignature", request.getBallotSignature());
                    put("gameResults", request.getGameResults() == null ? null :
                        request.getGameResults().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("keyId", request.getKeyId());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void voteAsync(
            VoteRequest request,
            AsyncAction<AsyncResult<VoteResult>> callback
    ) {
        VoteTask task = new VoteTask(request, callback);
        session.execute(task);
    }

    public VoteResult vote(
            VoteRequest request
    ) {
        final AsyncResult<VoteResult>[] resultAsyncResult = new AsyncResult[]{null};
        voteAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VoteMultipleTask extends Gs2RestSessionTask<VoteMultipleResult> {
        private VoteMultipleRequest request;

        public VoteMultipleTask(
            VoteMultipleRequest request,
            AsyncAction<AsyncResult<VoteMultipleResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VoteMultipleResult parse(JsonNode data) {
            return VoteMultipleResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/action/vote/multiple";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("signedBallots", request.getSignedBallots() == null ? null :
                        request.getSignedBallots().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("gameResults", request.getGameResults() == null ? null :
                        request.getGameResults().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("keyId", request.getKeyId());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void voteMultipleAsync(
            VoteMultipleRequest request,
            AsyncAction<AsyncResult<VoteMultipleResult>> callback
    ) {
        VoteMultipleTask task = new VoteMultipleTask(request, callback);
        session.execute(task);
    }

    public VoteMultipleResult voteMultiple(
            VoteMultipleRequest request
    ) {
        final AsyncResult<VoteMultipleResult>[] resultAsyncResult = new AsyncResult[]{null};
        voteMultipleAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CommitVoteTask extends Gs2RestSessionTask<CommitVoteResult> {
        private CommitVoteRequest request;

        public CommitVoteTask(
            CommitVoteRequest request,
            AsyncAction<AsyncResult<CommitVoteResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CommitVoteResult parse(JsonNode data) {
            return CommitVoteResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "matchmaking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/vote/{ratingName}/{gatheringName}/action/vote/commit";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{ratingName}", this.request.getRatingName() == null || this.request.getRatingName().length() == 0 ? "null" : String.valueOf(this.request.getRatingName()));
            url = url.replace("{gatheringName}", this.request.getGatheringName() == null || this.request.getGatheringName().length() == 0 ? "null" : String.valueOf(this.request.getGatheringName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

            builder
                .setMethod(HttpTask.Method.POST)
                .setUrl(url)
                .setHeader("Content-Type", "application/json")
                .setHttpResponseHandler(this);

            if (this.request.getRequestId() != null) {
                builder.setHeader("X-GS2-REQUEST-ID", this.request.getRequestId());
            }

            builder
                .build()
                .send();
        }
    }

    public void commitVoteAsync(
            CommitVoteRequest request,
            AsyncAction<AsyncResult<CommitVoteResult>> callback
    ) {
        CommitVoteTask task = new CommitVoteTask(request, callback);
        session.execute(task);
    }

    public CommitVoteResult commitVote(
            CommitVoteRequest request
    ) {
        final AsyncResult<CommitVoteResult>[] resultAsyncResult = new AsyncResult[]{null};
        commitVoteAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }
}