
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

package io.gs2.guild;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
import io.gs2.guild.request.*;
import io.gs2.guild.result.*;
import io.gs2.guild.model.*;public class Gs2GuildRestClient extends AbstractGs2Client<Gs2GuildRestClient> {

	public Gs2GuildRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "guild")
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
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("joinNotification", request.getJoinNotification() != null ? request.getJoinNotification().toJson() : null);
                    put("leaveNotification", request.getLeaveNotification() != null ? request.getLeaveNotification().toJson() : null);
                    put("changeMemberNotification", request.getChangeMemberNotification() != null ? request.getChangeMemberNotification().toJson() : null);
                    put("receiveRequestNotification", request.getReceiveRequestNotification() != null ? request.getReceiveRequestNotification().toJson() : null);
                    put("removeRequestNotification", request.getRemoveRequestNotification() != null ? request.getRemoveRequestNotification().toJson() : null);
                    put("createGuildScript", request.getCreateGuildScript() != null ? request.getCreateGuildScript().toJson() : null);
                    put("joinGuildScript", request.getJoinGuildScript() != null ? request.getJoinGuildScript().toJson() : null);
                    put("leaveGuildScript", request.getLeaveGuildScript() != null ? request.getLeaveGuildScript().toJson() : null);
                    put("changeRoleScript", request.getChangeRoleScript() != null ? request.getChangeRoleScript().toJson() : null);
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
                .replace("{service}", "guild")
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
                .replace("{service}", "guild")
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
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("joinNotification", request.getJoinNotification() != null ? request.getJoinNotification().toJson() : null);
                    put("leaveNotification", request.getLeaveNotification() != null ? request.getLeaveNotification().toJson() : null);
                    put("changeMemberNotification", request.getChangeMemberNotification() != null ? request.getChangeMemberNotification().toJson() : null);
                    put("receiveRequestNotification", request.getReceiveRequestNotification() != null ? request.getReceiveRequestNotification().toJson() : null);
                    put("removeRequestNotification", request.getRemoveRequestNotification() != null ? request.getRemoveRequestNotification().toJson() : null);
                    put("createGuildScript", request.getCreateGuildScript() != null ? request.getCreateGuildScript().toJson() : null);
                    put("joinGuildScript", request.getJoinGuildScript() != null ? request.getJoinGuildScript().toJson() : null);
                    put("leaveGuildScript", request.getLeaveGuildScript() != null ? request.getLeaveGuildScript().toJson() : null);
                    put("changeRoleScript", request.getChangeRoleScript() != null ? request.getChangeRoleScript().toJson() : null);
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
                .replace("{service}", "guild")
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
                .replace("{service}", "guild")
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
                .replace("{service}", "guild")
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
                .replace("{service}", "guild")
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
                .replace("{service}", "guild")
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
                .replace("{service}", "guild")
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
                .replace("{service}", "guild")
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
                .replace("{service}", "guild")
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

    class DescribeGuildModelMastersTask extends Gs2RestSessionTask<DescribeGuildModelMastersResult> {
        private DescribeGuildModelMastersRequest request;

        public DescribeGuildModelMastersTask(
            DescribeGuildModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeGuildModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeGuildModelMastersResult parse(JsonNode data) {
            return DescribeGuildModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model";

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

    public void describeGuildModelMastersAsync(
            DescribeGuildModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeGuildModelMastersResult>> callback
    ) {
        DescribeGuildModelMastersTask task = new DescribeGuildModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeGuildModelMastersResult describeGuildModelMasters(
            DescribeGuildModelMastersRequest request
    ) {
        final AsyncResult<DescribeGuildModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeGuildModelMastersAsync(
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

    class CreateGuildModelMasterTask extends Gs2RestSessionTask<CreateGuildModelMasterResult> {
        private CreateGuildModelMasterRequest request;

        public CreateGuildModelMasterTask(
            CreateGuildModelMasterRequest request,
            AsyncAction<AsyncResult<CreateGuildModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateGuildModelMasterResult parse(JsonNode data) {
            return CreateGuildModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("defaultMaximumMemberCount", request.getDefaultMaximumMemberCount());
                    put("maximumMemberCount", request.getMaximumMemberCount());
                    put("inactivityPeriodDays", request.getInactivityPeriodDays());
                    put("roles", request.getRoles() == null ? new ArrayList<RoleModel>() :
                        request.getRoles().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("guildMasterRole", request.getGuildMasterRole());
                    put("guildMemberDefaultRole", request.getGuildMemberDefaultRole());
                    put("rejoinCoolTimeMinutes", request.getRejoinCoolTimeMinutes());
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

    public void createGuildModelMasterAsync(
            CreateGuildModelMasterRequest request,
            AsyncAction<AsyncResult<CreateGuildModelMasterResult>> callback
    ) {
        CreateGuildModelMasterTask task = new CreateGuildModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateGuildModelMasterResult createGuildModelMaster(
            CreateGuildModelMasterRequest request
    ) {
        final AsyncResult<CreateGuildModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createGuildModelMasterAsync(
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

    class GetGuildModelMasterTask extends Gs2RestSessionTask<GetGuildModelMasterResult> {
        private GetGuildModelMasterRequest request;

        public GetGuildModelMasterTask(
            GetGuildModelMasterRequest request,
            AsyncAction<AsyncResult<GetGuildModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetGuildModelMasterResult parse(JsonNode data) {
            return GetGuildModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{guildModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

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

    public void getGuildModelMasterAsync(
            GetGuildModelMasterRequest request,
            AsyncAction<AsyncResult<GetGuildModelMasterResult>> callback
    ) {
        GetGuildModelMasterTask task = new GetGuildModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetGuildModelMasterResult getGuildModelMaster(
            GetGuildModelMasterRequest request
    ) {
        final AsyncResult<GetGuildModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGuildModelMasterAsync(
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

    class UpdateGuildModelMasterTask extends Gs2RestSessionTask<UpdateGuildModelMasterResult> {
        private UpdateGuildModelMasterRequest request;

        public UpdateGuildModelMasterTask(
            UpdateGuildModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateGuildModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateGuildModelMasterResult parse(JsonNode data) {
            return UpdateGuildModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{guildModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("defaultMaximumMemberCount", request.getDefaultMaximumMemberCount());
                    put("maximumMemberCount", request.getMaximumMemberCount());
                    put("inactivityPeriodDays", request.getInactivityPeriodDays());
                    put("roles", request.getRoles() == null ? new ArrayList<RoleModel>() :
                        request.getRoles().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("guildMasterRole", request.getGuildMasterRole());
                    put("guildMemberDefaultRole", request.getGuildMemberDefaultRole());
                    put("rejoinCoolTimeMinutes", request.getRejoinCoolTimeMinutes());
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

    public void updateGuildModelMasterAsync(
            UpdateGuildModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateGuildModelMasterResult>> callback
    ) {
        UpdateGuildModelMasterTask task = new UpdateGuildModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateGuildModelMasterResult updateGuildModelMaster(
            UpdateGuildModelMasterRequest request
    ) {
        final AsyncResult<UpdateGuildModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateGuildModelMasterAsync(
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

    class DeleteGuildModelMasterTask extends Gs2RestSessionTask<DeleteGuildModelMasterResult> {
        private DeleteGuildModelMasterRequest request;

        public DeleteGuildModelMasterTask(
            DeleteGuildModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteGuildModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteGuildModelMasterResult parse(JsonNode data) {
            return DeleteGuildModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{guildModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

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

    public void deleteGuildModelMasterAsync(
            DeleteGuildModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteGuildModelMasterResult>> callback
    ) {
        DeleteGuildModelMasterTask task = new DeleteGuildModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteGuildModelMasterResult deleteGuildModelMaster(
            DeleteGuildModelMasterRequest request
    ) {
        final AsyncResult<DeleteGuildModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteGuildModelMasterAsync(
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

    class DescribeGuildModelsTask extends Gs2RestSessionTask<DescribeGuildModelsResult> {
        private DescribeGuildModelsRequest request;

        public DescribeGuildModelsTask(
            DescribeGuildModelsRequest request,
            AsyncAction<AsyncResult<DescribeGuildModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeGuildModelsResult parse(JsonNode data) {
            return DescribeGuildModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model";

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

    public void describeGuildModelsAsync(
            DescribeGuildModelsRequest request,
            AsyncAction<AsyncResult<DescribeGuildModelsResult>> callback
    ) {
        DescribeGuildModelsTask task = new DescribeGuildModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeGuildModelsResult describeGuildModels(
            DescribeGuildModelsRequest request
    ) {
        final AsyncResult<DescribeGuildModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeGuildModelsAsync(
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

    class GetGuildModelTask extends Gs2RestSessionTask<GetGuildModelResult> {
        private GetGuildModelRequest request;

        public GetGuildModelTask(
            GetGuildModelRequest request,
            AsyncAction<AsyncResult<GetGuildModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetGuildModelResult parse(JsonNode data) {
            return GetGuildModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/{guildModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

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

    public void getGuildModelAsync(
            GetGuildModelRequest request,
            AsyncAction<AsyncResult<GetGuildModelResult>> callback
    ) {
        GetGuildModelTask task = new GetGuildModelTask(request, callback);
        session.execute(task);
    }

    public GetGuildModelResult getGuildModel(
            GetGuildModelRequest request
    ) {
        final AsyncResult<GetGuildModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGuildModelAsync(
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

    class SearchGuildsTask extends Gs2RestSessionTask<SearchGuildsResult> {
        private SearchGuildsRequest request;

        public SearchGuildsTask(
            SearchGuildsRequest request,
            AsyncAction<AsyncResult<SearchGuildsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SearchGuildsResult parse(JsonNode data) {
            return SearchGuildsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/guild/{guildModelName}/search";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("displayName", request.getDisplayName());
                    put("attributes1", request.getAttributes1() == null ? new ArrayList<Integer>() :
                        request.getAttributes1().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("attributes2", request.getAttributes2() == null ? new ArrayList<Integer>() :
                        request.getAttributes2().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("attributes3", request.getAttributes3() == null ? new ArrayList<Integer>() :
                        request.getAttributes3().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("attributes4", request.getAttributes4() == null ? new ArrayList<Integer>() :
                        request.getAttributes4().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("attributes5", request.getAttributes5() == null ? new ArrayList<Integer>() :
                        request.getAttributes5().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("joinPolicies", request.getJoinPolicies() == null ? new ArrayList<String>() :
                        request.getJoinPolicies().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("includeFullMembersGuild", request.getIncludeFullMembersGuild());
                    put("pageToken", request.getPageToken());
                    put("limit", request.getLimit());
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

    public void searchGuildsAsync(
            SearchGuildsRequest request,
            AsyncAction<AsyncResult<SearchGuildsResult>> callback
    ) {
        SearchGuildsTask task = new SearchGuildsTask(request, callback);
        session.execute(task);
    }

    public SearchGuildsResult searchGuilds(
            SearchGuildsRequest request
    ) {
        final AsyncResult<SearchGuildsResult>[] resultAsyncResult = new AsyncResult[]{null};
        searchGuildsAsync(
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

    class SearchGuildsByUserIdTask extends Gs2RestSessionTask<SearchGuildsByUserIdResult> {
        private SearchGuildsByUserIdRequest request;

        public SearchGuildsByUserIdTask(
            SearchGuildsByUserIdRequest request,
            AsyncAction<AsyncResult<SearchGuildsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SearchGuildsByUserIdResult parse(JsonNode data) {
            return SearchGuildsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/guild/{guildModelName}/search";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("displayName", request.getDisplayName());
                    put("attributes1", request.getAttributes1() == null ? new ArrayList<Integer>() :
                        request.getAttributes1().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("attributes2", request.getAttributes2() == null ? new ArrayList<Integer>() :
                        request.getAttributes2().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("attributes3", request.getAttributes3() == null ? new ArrayList<Integer>() :
                        request.getAttributes3().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("attributes4", request.getAttributes4() == null ? new ArrayList<Integer>() :
                        request.getAttributes4().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("attributes5", request.getAttributes5() == null ? new ArrayList<Integer>() :
                        request.getAttributes5().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("joinPolicies", request.getJoinPolicies() == null ? new ArrayList<String>() :
                        request.getJoinPolicies().stream().map(item -> {
                            return item;
                        }
                    ).collect(Collectors.toList()));
                    put("includeFullMembersGuild", request.getIncludeFullMembersGuild());
                    put("pageToken", request.getPageToken());
                    put("limit", request.getLimit());
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

    public void searchGuildsByUserIdAsync(
            SearchGuildsByUserIdRequest request,
            AsyncAction<AsyncResult<SearchGuildsByUserIdResult>> callback
    ) {
        SearchGuildsByUserIdTask task = new SearchGuildsByUserIdTask(request, callback);
        session.execute(task);
    }

    public SearchGuildsByUserIdResult searchGuildsByUserId(
            SearchGuildsByUserIdRequest request
    ) {
        final AsyncResult<SearchGuildsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        searchGuildsByUserIdAsync(
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

    class CreateGuildTask extends Gs2RestSessionTask<CreateGuildResult> {
        private CreateGuildRequest request;

        public CreateGuildTask(
            CreateGuildRequest request,
            AsyncAction<AsyncResult<CreateGuildResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateGuildResult parse(JsonNode data) {
            return CreateGuildResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/guild/{guildModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("displayName", request.getDisplayName());
                    put("attribute1", request.getAttribute1());
                    put("attribute2", request.getAttribute2());
                    put("attribute3", request.getAttribute3());
                    put("attribute4", request.getAttribute4());
                    put("attribute5", request.getAttribute5());
                    put("joinPolicy", request.getJoinPolicy());
                    put("customRoles", request.getCustomRoles() == null ? new ArrayList<RoleModel>() :
                        request.getCustomRoles().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("guildMemberDefaultRole", request.getGuildMemberDefaultRole());
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

    public void createGuildAsync(
            CreateGuildRequest request,
            AsyncAction<AsyncResult<CreateGuildResult>> callback
    ) {
        CreateGuildTask task = new CreateGuildTask(request, callback);
        session.execute(task);
    }

    public CreateGuildResult createGuild(
            CreateGuildRequest request
    ) {
        final AsyncResult<CreateGuildResult>[] resultAsyncResult = new AsyncResult[]{null};
        createGuildAsync(
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

    class CreateGuildByUserIdTask extends Gs2RestSessionTask<CreateGuildByUserIdResult> {
        private CreateGuildByUserIdRequest request;

        public CreateGuildByUserIdTask(
            CreateGuildByUserIdRequest request,
            AsyncAction<AsyncResult<CreateGuildByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateGuildByUserIdResult parse(JsonNode data) {
            return CreateGuildByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/guild/{guildModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("displayName", request.getDisplayName());
                    put("attribute1", request.getAttribute1());
                    put("attribute2", request.getAttribute2());
                    put("attribute3", request.getAttribute3());
                    put("attribute4", request.getAttribute4());
                    put("attribute5", request.getAttribute5());
                    put("joinPolicy", request.getJoinPolicy());
                    put("customRoles", request.getCustomRoles() == null ? new ArrayList<RoleModel>() :
                        request.getCustomRoles().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("guildMemberDefaultRole", request.getGuildMemberDefaultRole());
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

    public void createGuildByUserIdAsync(
            CreateGuildByUserIdRequest request,
            AsyncAction<AsyncResult<CreateGuildByUserIdResult>> callback
    ) {
        CreateGuildByUserIdTask task = new CreateGuildByUserIdTask(request, callback);
        session.execute(task);
    }

    public CreateGuildByUserIdResult createGuildByUserId(
            CreateGuildByUserIdRequest request
    ) {
        final AsyncResult<CreateGuildByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        createGuildByUserIdAsync(
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

    class GetGuildTask extends Gs2RestSessionTask<GetGuildResult> {
        private GetGuildRequest request;

        public GetGuildTask(
            GetGuildRequest request,
            AsyncAction<AsyncResult<GetGuildResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetGuildResult parse(JsonNode data) {
            return GetGuildResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/guild/{guildModelName}/{guildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

    public void getGuildAsync(
            GetGuildRequest request,
            AsyncAction<AsyncResult<GetGuildResult>> callback
    ) {
        GetGuildTask task = new GetGuildTask(request, callback);
        session.execute(task);
    }

    public GetGuildResult getGuild(
            GetGuildRequest request
    ) {
        final AsyncResult<GetGuildResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGuildAsync(
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

    class GetGuildByUserIdTask extends Gs2RestSessionTask<GetGuildByUserIdResult> {
        private GetGuildByUserIdRequest request;

        public GetGuildByUserIdTask(
            GetGuildByUserIdRequest request,
            AsyncAction<AsyncResult<GetGuildByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetGuildByUserIdResult parse(JsonNode data) {
            return GetGuildByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/guild/{guildModelName}/{guildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

    public void getGuildByUserIdAsync(
            GetGuildByUserIdRequest request,
            AsyncAction<AsyncResult<GetGuildByUserIdResult>> callback
    ) {
        GetGuildByUserIdTask task = new GetGuildByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetGuildByUserIdResult getGuildByUserId(
            GetGuildByUserIdRequest request
    ) {
        final AsyncResult<GetGuildByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGuildByUserIdAsync(
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

    class UpdateGuildTask extends Gs2RestSessionTask<UpdateGuildResult> {
        private UpdateGuildRequest request;

        public UpdateGuildTask(
            UpdateGuildRequest request,
            AsyncAction<AsyncResult<UpdateGuildResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateGuildResult parse(JsonNode data) {
            return UpdateGuildResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("displayName", request.getDisplayName());
                    put("attribute1", request.getAttribute1());
                    put("attribute2", request.getAttribute2());
                    put("attribute3", request.getAttribute3());
                    put("attribute4", request.getAttribute4());
                    put("attribute5", request.getAttribute5());
                    put("joinPolicy", request.getJoinPolicy());
                    put("customRoles", request.getCustomRoles() == null ? new ArrayList<RoleModel>() :
                        request.getCustomRoles().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("guildMemberDefaultRole", request.getGuildMemberDefaultRole());
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

    public void updateGuildAsync(
            UpdateGuildRequest request,
            AsyncAction<AsyncResult<UpdateGuildResult>> callback
    ) {
        UpdateGuildTask task = new UpdateGuildTask(request, callback);
        session.execute(task);
    }

    public UpdateGuildResult updateGuild(
            UpdateGuildRequest request
    ) {
        final AsyncResult<UpdateGuildResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateGuildAsync(
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

    class UpdateGuildByGuildNameTask extends Gs2RestSessionTask<UpdateGuildByGuildNameResult> {
        private UpdateGuildByGuildNameRequest request;

        public UpdateGuildByGuildNameTask(
            UpdateGuildByGuildNameRequest request,
            AsyncAction<AsyncResult<UpdateGuildByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateGuildByGuildNameResult parse(JsonNode data) {
            return UpdateGuildByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("displayName", request.getDisplayName());
                    put("attribute1", request.getAttribute1());
                    put("attribute2", request.getAttribute2());
                    put("attribute3", request.getAttribute3());
                    put("attribute4", request.getAttribute4());
                    put("attribute5", request.getAttribute5());
                    put("joinPolicy", request.getJoinPolicy());
                    put("customRoles", request.getCustomRoles() == null ? new ArrayList<RoleModel>() :
                        request.getCustomRoles().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("guildMemberDefaultRole", request.getGuildMemberDefaultRole());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void updateGuildByGuildNameAsync(
            UpdateGuildByGuildNameRequest request,
            AsyncAction<AsyncResult<UpdateGuildByGuildNameResult>> callback
    ) {
        UpdateGuildByGuildNameTask task = new UpdateGuildByGuildNameTask(request, callback);
        session.execute(task);
    }

    public UpdateGuildByGuildNameResult updateGuildByGuildName(
            UpdateGuildByGuildNameRequest request
    ) {
        final AsyncResult<UpdateGuildByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateGuildByGuildNameAsync(
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

    class DeleteMemberTask extends Gs2RestSessionTask<DeleteMemberResult> {
        private DeleteMemberRequest request;

        public DeleteMemberTask(
            DeleteMemberRequest request,
            AsyncAction<AsyncResult<DeleteMemberResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteMemberResult parse(JsonNode data) {
            return DeleteMemberResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me/member/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void deleteMemberAsync(
            DeleteMemberRequest request,
            AsyncAction<AsyncResult<DeleteMemberResult>> callback
    ) {
        DeleteMemberTask task = new DeleteMemberTask(request, callback);
        session.execute(task);
    }

    public DeleteMemberResult deleteMember(
            DeleteMemberRequest request
    ) {
        final AsyncResult<DeleteMemberResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMemberAsync(
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

    class DeleteMemberByGuildNameTask extends Gs2RestSessionTask<DeleteMemberByGuildNameResult> {
        private DeleteMemberByGuildNameRequest request;

        public DeleteMemberByGuildNameTask(
            DeleteMemberByGuildNameRequest request,
            AsyncAction<AsyncResult<DeleteMemberByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteMemberByGuildNameResult parse(JsonNode data) {
            return DeleteMemberByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/member/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void deleteMemberByGuildNameAsync(
            DeleteMemberByGuildNameRequest request,
            AsyncAction<AsyncResult<DeleteMemberByGuildNameResult>> callback
    ) {
        DeleteMemberByGuildNameTask task = new DeleteMemberByGuildNameTask(request, callback);
        session.execute(task);
    }

    public DeleteMemberByGuildNameResult deleteMemberByGuildName(
            DeleteMemberByGuildNameRequest request
    ) {
        final AsyncResult<DeleteMemberByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMemberByGuildNameAsync(
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

    class UpdateMemberRoleTask extends Gs2RestSessionTask<UpdateMemberRoleResult> {
        private UpdateMemberRoleRequest request;

        public UpdateMemberRoleTask(
            UpdateMemberRoleRequest request,
            AsyncAction<AsyncResult<UpdateMemberRoleResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateMemberRoleResult parse(JsonNode data) {
            return UpdateMemberRoleResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me/member/{targetUserId}/role";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("roleName", request.getRoleName());
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

    public void updateMemberRoleAsync(
            UpdateMemberRoleRequest request,
            AsyncAction<AsyncResult<UpdateMemberRoleResult>> callback
    ) {
        UpdateMemberRoleTask task = new UpdateMemberRoleTask(request, callback);
        session.execute(task);
    }

    public UpdateMemberRoleResult updateMemberRole(
            UpdateMemberRoleRequest request
    ) {
        final AsyncResult<UpdateMemberRoleResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateMemberRoleAsync(
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

    class UpdateMemberRoleByGuildNameTask extends Gs2RestSessionTask<UpdateMemberRoleByGuildNameResult> {
        private UpdateMemberRoleByGuildNameRequest request;

        public UpdateMemberRoleByGuildNameTask(
            UpdateMemberRoleByGuildNameRequest request,
            AsyncAction<AsyncResult<UpdateMemberRoleByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateMemberRoleByGuildNameResult parse(JsonNode data) {
            return UpdateMemberRoleByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/member/{targetUserId}/role";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("roleName", request.getRoleName());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void updateMemberRoleByGuildNameAsync(
            UpdateMemberRoleByGuildNameRequest request,
            AsyncAction<AsyncResult<UpdateMemberRoleByGuildNameResult>> callback
    ) {
        UpdateMemberRoleByGuildNameTask task = new UpdateMemberRoleByGuildNameTask(request, callback);
        session.execute(task);
    }

    public UpdateMemberRoleByGuildNameResult updateMemberRoleByGuildName(
            UpdateMemberRoleByGuildNameRequest request
    ) {
        final AsyncResult<UpdateMemberRoleByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateMemberRoleByGuildNameAsync(
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

    class DeleteGuildTask extends Gs2RestSessionTask<DeleteGuildResult> {
        private DeleteGuildRequest request;

        public DeleteGuildTask(
            DeleteGuildRequest request,
            AsyncAction<AsyncResult<DeleteGuildResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteGuildResult parse(JsonNode data) {
            return DeleteGuildResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

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

    public void deleteGuildAsync(
            DeleteGuildRequest request,
            AsyncAction<AsyncResult<DeleteGuildResult>> callback
    ) {
        DeleteGuildTask task = new DeleteGuildTask(request, callback);
        session.execute(task);
    }

    public DeleteGuildResult deleteGuild(
            DeleteGuildRequest request
    ) {
        final AsyncResult<DeleteGuildResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteGuildAsync(
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

    class DeleteGuildByGuildNameTask extends Gs2RestSessionTask<DeleteGuildByGuildNameResult> {
        private DeleteGuildByGuildNameRequest request;

        public DeleteGuildByGuildNameTask(
            DeleteGuildByGuildNameRequest request,
            AsyncAction<AsyncResult<DeleteGuildByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteGuildByGuildNameResult parse(JsonNode data) {
            return DeleteGuildByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

            builder
                .build()
                .send();
        }
    }

    public void deleteGuildByGuildNameAsync(
            DeleteGuildByGuildNameRequest request,
            AsyncAction<AsyncResult<DeleteGuildByGuildNameResult>> callback
    ) {
        DeleteGuildByGuildNameTask task = new DeleteGuildByGuildNameTask(request, callback);
        session.execute(task);
    }

    public DeleteGuildByGuildNameResult deleteGuildByGuildName(
            DeleteGuildByGuildNameRequest request
    ) {
        final AsyncResult<DeleteGuildByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteGuildByGuildNameAsync(
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

    class IncreaseMaximumCurrentMaximumMemberCountByGuildNameTask extends Gs2RestSessionTask<IncreaseMaximumCurrentMaximumMemberCountByGuildNameResult> {
        private IncreaseMaximumCurrentMaximumMemberCountByGuildNameRequest request;

        public IncreaseMaximumCurrentMaximumMemberCountByGuildNameTask(
            IncreaseMaximumCurrentMaximumMemberCountByGuildNameRequest request,
            AsyncAction<AsyncResult<IncreaseMaximumCurrentMaximumMemberCountByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IncreaseMaximumCurrentMaximumMemberCountByGuildNameResult parse(JsonNode data) {
            return IncreaseMaximumCurrentMaximumMemberCountByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/currentMaximumMemberCount/increase";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("value", request.getValue());
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

            builder
                .build()
                .send();
        }
    }

    public void increaseMaximumCurrentMaximumMemberCountByGuildNameAsync(
            IncreaseMaximumCurrentMaximumMemberCountByGuildNameRequest request,
            AsyncAction<AsyncResult<IncreaseMaximumCurrentMaximumMemberCountByGuildNameResult>> callback
    ) {
        IncreaseMaximumCurrentMaximumMemberCountByGuildNameTask task = new IncreaseMaximumCurrentMaximumMemberCountByGuildNameTask(request, callback);
        session.execute(task);
    }

    public IncreaseMaximumCurrentMaximumMemberCountByGuildNameResult increaseMaximumCurrentMaximumMemberCountByGuildName(
            IncreaseMaximumCurrentMaximumMemberCountByGuildNameRequest request
    ) {
        final AsyncResult<IncreaseMaximumCurrentMaximumMemberCountByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        increaseMaximumCurrentMaximumMemberCountByGuildNameAsync(
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

    class DecreaseMaximumCurrentMaximumMemberCountByGuildNameTask extends Gs2RestSessionTask<DecreaseMaximumCurrentMaximumMemberCountByGuildNameResult> {
        private DecreaseMaximumCurrentMaximumMemberCountByGuildNameRequest request;

        public DecreaseMaximumCurrentMaximumMemberCountByGuildNameTask(
            DecreaseMaximumCurrentMaximumMemberCountByGuildNameRequest request,
            AsyncAction<AsyncResult<DecreaseMaximumCurrentMaximumMemberCountByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DecreaseMaximumCurrentMaximumMemberCountByGuildNameResult parse(JsonNode data) {
            return DecreaseMaximumCurrentMaximumMemberCountByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/currentMaximumMemberCount/decrease";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("value", request.getValue());
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

            builder
                .build()
                .send();
        }
    }

    public void decreaseMaximumCurrentMaximumMemberCountByGuildNameAsync(
            DecreaseMaximumCurrentMaximumMemberCountByGuildNameRequest request,
            AsyncAction<AsyncResult<DecreaseMaximumCurrentMaximumMemberCountByGuildNameResult>> callback
    ) {
        DecreaseMaximumCurrentMaximumMemberCountByGuildNameTask task = new DecreaseMaximumCurrentMaximumMemberCountByGuildNameTask(request, callback);
        session.execute(task);
    }

    public DecreaseMaximumCurrentMaximumMemberCountByGuildNameResult decreaseMaximumCurrentMaximumMemberCountByGuildName(
            DecreaseMaximumCurrentMaximumMemberCountByGuildNameRequest request
    ) {
        final AsyncResult<DecreaseMaximumCurrentMaximumMemberCountByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        decreaseMaximumCurrentMaximumMemberCountByGuildNameAsync(
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

    class VerifyCurrentMaximumMemberCountTask extends Gs2RestSessionTask<VerifyCurrentMaximumMemberCountResult> {
        private VerifyCurrentMaximumMemberCountRequest request;

        public VerifyCurrentMaximumMemberCountTask(
            VerifyCurrentMaximumMemberCountRequest request,
            AsyncAction<AsyncResult<VerifyCurrentMaximumMemberCountResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyCurrentMaximumMemberCountResult parse(JsonNode data) {
            return VerifyCurrentMaximumMemberCountResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me/currentMaximumMemberCount/verify";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("verifyType", request.getVerifyType());
                    put("value", request.getValue());
                    put("multiplyValueSpecifyingQuantity", request.getMultiplyValueSpecifyingQuantity());
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

    public void verifyCurrentMaximumMemberCountAsync(
            VerifyCurrentMaximumMemberCountRequest request,
            AsyncAction<AsyncResult<VerifyCurrentMaximumMemberCountResult>> callback
    ) {
        VerifyCurrentMaximumMemberCountTask task = new VerifyCurrentMaximumMemberCountTask(request, callback);
        session.execute(task);
    }

    public VerifyCurrentMaximumMemberCountResult verifyCurrentMaximumMemberCount(
            VerifyCurrentMaximumMemberCountRequest request
    ) {
        final AsyncResult<VerifyCurrentMaximumMemberCountResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyCurrentMaximumMemberCountAsync(
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

    class VerifyCurrentMaximumMemberCountByGuildNameTask extends Gs2RestSessionTask<VerifyCurrentMaximumMemberCountByGuildNameResult> {
        private VerifyCurrentMaximumMemberCountByGuildNameRequest request;

        public VerifyCurrentMaximumMemberCountByGuildNameTask(
            VerifyCurrentMaximumMemberCountByGuildNameRequest request,
            AsyncAction<AsyncResult<VerifyCurrentMaximumMemberCountByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyCurrentMaximumMemberCountByGuildNameResult parse(JsonNode data) {
            return VerifyCurrentMaximumMemberCountByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/currentMaximumMemberCount/verify";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("verifyType", request.getVerifyType());
                    put("value", request.getValue());
                    put("multiplyValueSpecifyingQuantity", request.getMultiplyValueSpecifyingQuantity());
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

            builder
                .build()
                .send();
        }
    }

    public void verifyCurrentMaximumMemberCountByGuildNameAsync(
            VerifyCurrentMaximumMemberCountByGuildNameRequest request,
            AsyncAction<AsyncResult<VerifyCurrentMaximumMemberCountByGuildNameResult>> callback
    ) {
        VerifyCurrentMaximumMemberCountByGuildNameTask task = new VerifyCurrentMaximumMemberCountByGuildNameTask(request, callback);
        session.execute(task);
    }

    public VerifyCurrentMaximumMemberCountByGuildNameResult verifyCurrentMaximumMemberCountByGuildName(
            VerifyCurrentMaximumMemberCountByGuildNameRequest request
    ) {
        final AsyncResult<VerifyCurrentMaximumMemberCountByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyCurrentMaximumMemberCountByGuildNameAsync(
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

    class VerifyIncludeMemberTask extends Gs2RestSessionTask<VerifyIncludeMemberResult> {
        private VerifyIncludeMemberRequest request;

        public VerifyIncludeMemberTask(
            VerifyIncludeMemberRequest request,
            AsyncAction<AsyncResult<VerifyIncludeMemberResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyIncludeMemberResult parse(JsonNode data) {
            return VerifyIncludeMemberResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/member/me/verify";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

    public void verifyIncludeMemberAsync(
            VerifyIncludeMemberRequest request,
            AsyncAction<AsyncResult<VerifyIncludeMemberResult>> callback
    ) {
        VerifyIncludeMemberTask task = new VerifyIncludeMemberTask(request, callback);
        session.execute(task);
    }

    public VerifyIncludeMemberResult verifyIncludeMember(
            VerifyIncludeMemberRequest request
    ) {
        final AsyncResult<VerifyIncludeMemberResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyIncludeMemberAsync(
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

    class VerifyIncludeMemberByUserIdTask extends Gs2RestSessionTask<VerifyIncludeMemberByUserIdResult> {
        private VerifyIncludeMemberByUserIdRequest request;

        public VerifyIncludeMemberByUserIdTask(
            VerifyIncludeMemberByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyIncludeMemberByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyIncludeMemberByUserIdResult parse(JsonNode data) {
            return VerifyIncludeMemberByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/member/{userId}/verify";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));
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

    public void verifyIncludeMemberByUserIdAsync(
            VerifyIncludeMemberByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyIncludeMemberByUserIdResult>> callback
    ) {
        VerifyIncludeMemberByUserIdTask task = new VerifyIncludeMemberByUserIdTask(request, callback);
        session.execute(task);
    }

    public VerifyIncludeMemberByUserIdResult verifyIncludeMemberByUserId(
            VerifyIncludeMemberByUserIdRequest request
    ) {
        final AsyncResult<VerifyIncludeMemberByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyIncludeMemberByUserIdAsync(
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

    class SetMaximumCurrentMaximumMemberCountByGuildNameTask extends Gs2RestSessionTask<SetMaximumCurrentMaximumMemberCountByGuildNameResult> {
        private SetMaximumCurrentMaximumMemberCountByGuildNameRequest request;

        public SetMaximumCurrentMaximumMemberCountByGuildNameTask(
            SetMaximumCurrentMaximumMemberCountByGuildNameRequest request,
            AsyncAction<AsyncResult<SetMaximumCurrentMaximumMemberCountByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetMaximumCurrentMaximumMemberCountByGuildNameResult parse(JsonNode data) {
            return SetMaximumCurrentMaximumMemberCountByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/currentMaximumMemberCount";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("value", request.getValue());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void setMaximumCurrentMaximumMemberCountByGuildNameAsync(
            SetMaximumCurrentMaximumMemberCountByGuildNameRequest request,
            AsyncAction<AsyncResult<SetMaximumCurrentMaximumMemberCountByGuildNameResult>> callback
    ) {
        SetMaximumCurrentMaximumMemberCountByGuildNameTask task = new SetMaximumCurrentMaximumMemberCountByGuildNameTask(request, callback);
        session.execute(task);
    }

    public SetMaximumCurrentMaximumMemberCountByGuildNameResult setMaximumCurrentMaximumMemberCountByGuildName(
            SetMaximumCurrentMaximumMemberCountByGuildNameRequest request
    ) {
        final AsyncResult<SetMaximumCurrentMaximumMemberCountByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        setMaximumCurrentMaximumMemberCountByGuildNameAsync(
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

    class AssumeTask extends Gs2RestSessionTask<AssumeResult> {
        private AssumeRequest request;

        public AssumeTask(
            AssumeRequest request,
            AsyncAction<AsyncResult<AssumeResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AssumeResult parse(JsonNode data) {
            return AssumeResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/guild/{guildModelName}/{guildName}/assume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

    public void assumeAsync(
            AssumeRequest request,
            AsyncAction<AsyncResult<AssumeResult>> callback
    ) {
        AssumeTask task = new AssumeTask(request, callback);
        session.execute(task);
    }

    public AssumeResult assume(
            AssumeRequest request
    ) {
        final AsyncResult<AssumeResult>[] resultAsyncResult = new AsyncResult[]{null};
        assumeAsync(
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

    class AssumeByUserIdTask extends Gs2RestSessionTask<AssumeByUserIdResult> {
        private AssumeByUserIdRequest request;

        public AssumeByUserIdTask(
            AssumeByUserIdRequest request,
            AsyncAction<AsyncResult<AssumeByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AssumeByUserIdResult parse(JsonNode data) {
            return AssumeByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/guild/{guildModelName}/{guildName}/assume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

    public void assumeByUserIdAsync(
            AssumeByUserIdRequest request,
            AsyncAction<AsyncResult<AssumeByUserIdResult>> callback
    ) {
        AssumeByUserIdTask task = new AssumeByUserIdTask(request, callback);
        session.execute(task);
    }

    public AssumeByUserIdResult assumeByUserId(
            AssumeByUserIdRequest request
    ) {
        final AsyncResult<AssumeByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        assumeByUserIdAsync(
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

    class IncreaseMaximumCurrentMaximumMemberCountByStampSheetTask extends Gs2RestSessionTask<IncreaseMaximumCurrentMaximumMemberCountByStampSheetResult> {
        private IncreaseMaximumCurrentMaximumMemberCountByStampSheetRequest request;

        public IncreaseMaximumCurrentMaximumMemberCountByStampSheetTask(
            IncreaseMaximumCurrentMaximumMemberCountByStampSheetRequest request,
            AsyncAction<AsyncResult<IncreaseMaximumCurrentMaximumMemberCountByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IncreaseMaximumCurrentMaximumMemberCountByStampSheetResult parse(JsonNode data) {
            return IncreaseMaximumCurrentMaximumMemberCountByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/guild/currentMaximumMemberCount/add";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stampSheet", request.getStampSheet());
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

    public void increaseMaximumCurrentMaximumMemberCountByStampSheetAsync(
            IncreaseMaximumCurrentMaximumMemberCountByStampSheetRequest request,
            AsyncAction<AsyncResult<IncreaseMaximumCurrentMaximumMemberCountByStampSheetResult>> callback
    ) {
        IncreaseMaximumCurrentMaximumMemberCountByStampSheetTask task = new IncreaseMaximumCurrentMaximumMemberCountByStampSheetTask(request, callback);
        session.execute(task);
    }

    public IncreaseMaximumCurrentMaximumMemberCountByStampSheetResult increaseMaximumCurrentMaximumMemberCountByStampSheet(
            IncreaseMaximumCurrentMaximumMemberCountByStampSheetRequest request
    ) {
        final AsyncResult<IncreaseMaximumCurrentMaximumMemberCountByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        increaseMaximumCurrentMaximumMemberCountByStampSheetAsync(
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

    class DecreaseMaximumCurrentMaximumMemberCountByStampTaskTask extends Gs2RestSessionTask<DecreaseMaximumCurrentMaximumMemberCountByStampTaskResult> {
        private DecreaseMaximumCurrentMaximumMemberCountByStampTaskRequest request;

        public DecreaseMaximumCurrentMaximumMemberCountByStampTaskTask(
            DecreaseMaximumCurrentMaximumMemberCountByStampTaskRequest request,
            AsyncAction<AsyncResult<DecreaseMaximumCurrentMaximumMemberCountByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DecreaseMaximumCurrentMaximumMemberCountByStampTaskResult parse(JsonNode data) {
            return DecreaseMaximumCurrentMaximumMemberCountByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/guild/currentMaximumMemberCount/sub";

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

    public void decreaseMaximumCurrentMaximumMemberCountByStampTaskAsync(
            DecreaseMaximumCurrentMaximumMemberCountByStampTaskRequest request,
            AsyncAction<AsyncResult<DecreaseMaximumCurrentMaximumMemberCountByStampTaskResult>> callback
    ) {
        DecreaseMaximumCurrentMaximumMemberCountByStampTaskTask task = new DecreaseMaximumCurrentMaximumMemberCountByStampTaskTask(request, callback);
        session.execute(task);
    }

    public DecreaseMaximumCurrentMaximumMemberCountByStampTaskResult decreaseMaximumCurrentMaximumMemberCountByStampTask(
            DecreaseMaximumCurrentMaximumMemberCountByStampTaskRequest request
    ) {
        final AsyncResult<DecreaseMaximumCurrentMaximumMemberCountByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        decreaseMaximumCurrentMaximumMemberCountByStampTaskAsync(
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

    class SetMaximumCurrentMaximumMemberCountByStampSheetTask extends Gs2RestSessionTask<SetMaximumCurrentMaximumMemberCountByStampSheetResult> {
        private SetMaximumCurrentMaximumMemberCountByStampSheetRequest request;

        public SetMaximumCurrentMaximumMemberCountByStampSheetTask(
            SetMaximumCurrentMaximumMemberCountByStampSheetRequest request,
            AsyncAction<AsyncResult<SetMaximumCurrentMaximumMemberCountByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SetMaximumCurrentMaximumMemberCountByStampSheetResult parse(JsonNode data) {
            return SetMaximumCurrentMaximumMemberCountByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/guild/currentMaximumMemberCount/set";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stampSheet", request.getStampSheet());
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

    public void setMaximumCurrentMaximumMemberCountByStampSheetAsync(
            SetMaximumCurrentMaximumMemberCountByStampSheetRequest request,
            AsyncAction<AsyncResult<SetMaximumCurrentMaximumMemberCountByStampSheetResult>> callback
    ) {
        SetMaximumCurrentMaximumMemberCountByStampSheetTask task = new SetMaximumCurrentMaximumMemberCountByStampSheetTask(request, callback);
        session.execute(task);
    }

    public SetMaximumCurrentMaximumMemberCountByStampSheetResult setMaximumCurrentMaximumMemberCountByStampSheet(
            SetMaximumCurrentMaximumMemberCountByStampSheetRequest request
    ) {
        final AsyncResult<SetMaximumCurrentMaximumMemberCountByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        setMaximumCurrentMaximumMemberCountByStampSheetAsync(
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

    class VerifyCurrentMaximumMemberCountByStampTaskTask extends Gs2RestSessionTask<VerifyCurrentMaximumMemberCountByStampTaskResult> {
        private VerifyCurrentMaximumMemberCountByStampTaskRequest request;

        public VerifyCurrentMaximumMemberCountByStampTaskTask(
            VerifyCurrentMaximumMemberCountByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyCurrentMaximumMemberCountByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyCurrentMaximumMemberCountByStampTaskResult parse(JsonNode data) {
            return VerifyCurrentMaximumMemberCountByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/guild/currentMaximumMemberCount/verify";

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

    public void verifyCurrentMaximumMemberCountByStampTaskAsync(
            VerifyCurrentMaximumMemberCountByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyCurrentMaximumMemberCountByStampTaskResult>> callback
    ) {
        VerifyCurrentMaximumMemberCountByStampTaskTask task = new VerifyCurrentMaximumMemberCountByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifyCurrentMaximumMemberCountByStampTaskResult verifyCurrentMaximumMemberCountByStampTask(
            VerifyCurrentMaximumMemberCountByStampTaskRequest request
    ) {
        final AsyncResult<VerifyCurrentMaximumMemberCountByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyCurrentMaximumMemberCountByStampTaskAsync(
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

    class VerifyIncludeMemberByStampTaskTask extends Gs2RestSessionTask<VerifyIncludeMemberByStampTaskResult> {
        private VerifyIncludeMemberByStampTaskRequest request;

        public VerifyIncludeMemberByStampTaskTask(
            VerifyIncludeMemberByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyIncludeMemberByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyIncludeMemberByStampTaskResult parse(JsonNode data) {
            return VerifyIncludeMemberByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/guild/member/verify";

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

    public void verifyIncludeMemberByStampTaskAsync(
            VerifyIncludeMemberByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyIncludeMemberByStampTaskResult>> callback
    ) {
        VerifyIncludeMemberByStampTaskTask task = new VerifyIncludeMemberByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifyIncludeMemberByStampTaskResult verifyIncludeMemberByStampTask(
            VerifyIncludeMemberByStampTaskRequest request
    ) {
        final AsyncResult<VerifyIncludeMemberByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyIncludeMemberByStampTaskAsync(
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

    class DescribeJoinedGuildsTask extends Gs2RestSessionTask<DescribeJoinedGuildsResult> {
        private DescribeJoinedGuildsRequest request;

        public DescribeJoinedGuildsTask(
            DescribeJoinedGuildsRequest request,
            AsyncAction<AsyncResult<DescribeJoinedGuildsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeJoinedGuildsResult parse(JsonNode data) {
            return DescribeJoinedGuildsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/joined";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getGuildModelName() != null) {
                queryStrings.add("guildModelName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getGuildModelName()))));
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

    public void describeJoinedGuildsAsync(
            DescribeJoinedGuildsRequest request,
            AsyncAction<AsyncResult<DescribeJoinedGuildsResult>> callback
    ) {
        DescribeJoinedGuildsTask task = new DescribeJoinedGuildsTask(request, callback);
        session.execute(task);
    }

    public DescribeJoinedGuildsResult describeJoinedGuilds(
            DescribeJoinedGuildsRequest request
    ) {
        final AsyncResult<DescribeJoinedGuildsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeJoinedGuildsAsync(
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

    class DescribeJoinedGuildsByUserIdTask extends Gs2RestSessionTask<DescribeJoinedGuildsByUserIdResult> {
        private DescribeJoinedGuildsByUserIdRequest request;

        public DescribeJoinedGuildsByUserIdTask(
            DescribeJoinedGuildsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeJoinedGuildsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeJoinedGuildsByUserIdResult parse(JsonNode data) {
            return DescribeJoinedGuildsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/joined";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getGuildModelName() != null) {
                queryStrings.add("guildModelName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getGuildModelName()))));
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

    public void describeJoinedGuildsByUserIdAsync(
            DescribeJoinedGuildsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeJoinedGuildsByUserIdResult>> callback
    ) {
        DescribeJoinedGuildsByUserIdTask task = new DescribeJoinedGuildsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeJoinedGuildsByUserIdResult describeJoinedGuildsByUserId(
            DescribeJoinedGuildsByUserIdRequest request
    ) {
        final AsyncResult<DescribeJoinedGuildsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeJoinedGuildsByUserIdAsync(
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

    class GetJoinedGuildTask extends Gs2RestSessionTask<GetJoinedGuildResult> {
        private GetJoinedGuildRequest request;

        public GetJoinedGuildTask(
            GetJoinedGuildRequest request,
            AsyncAction<AsyncResult<GetJoinedGuildResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetJoinedGuildResult parse(JsonNode data) {
            return GetJoinedGuildResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/joined/{guildModelName}/{guildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

    public void getJoinedGuildAsync(
            GetJoinedGuildRequest request,
            AsyncAction<AsyncResult<GetJoinedGuildResult>> callback
    ) {
        GetJoinedGuildTask task = new GetJoinedGuildTask(request, callback);
        session.execute(task);
    }

    public GetJoinedGuildResult getJoinedGuild(
            GetJoinedGuildRequest request
    ) {
        final AsyncResult<GetJoinedGuildResult>[] resultAsyncResult = new AsyncResult[]{null};
        getJoinedGuildAsync(
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

    class GetJoinedGuildByUserIdTask extends Gs2RestSessionTask<GetJoinedGuildByUserIdResult> {
        private GetJoinedGuildByUserIdRequest request;

        public GetJoinedGuildByUserIdTask(
            GetJoinedGuildByUserIdRequest request,
            AsyncAction<AsyncResult<GetJoinedGuildByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetJoinedGuildByUserIdResult parse(JsonNode data) {
            return GetJoinedGuildByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/joined/{guildModelName}/{guildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

    public void getJoinedGuildByUserIdAsync(
            GetJoinedGuildByUserIdRequest request,
            AsyncAction<AsyncResult<GetJoinedGuildByUserIdResult>> callback
    ) {
        GetJoinedGuildByUserIdTask task = new GetJoinedGuildByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetJoinedGuildByUserIdResult getJoinedGuildByUserId(
            GetJoinedGuildByUserIdRequest request
    ) {
        final AsyncResult<GetJoinedGuildByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getJoinedGuildByUserIdAsync(
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

    class WithdrawalTask extends Gs2RestSessionTask<WithdrawalResult> {
        private WithdrawalRequest request;

        public WithdrawalTask(
            WithdrawalRequest request,
            AsyncAction<AsyncResult<WithdrawalResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public WithdrawalResult parse(JsonNode data) {
            return WithdrawalResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/joined/{guildModelName}/{guildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

    public void withdrawalAsync(
            WithdrawalRequest request,
            AsyncAction<AsyncResult<WithdrawalResult>> callback
    ) {
        WithdrawalTask task = new WithdrawalTask(request, callback);
        session.execute(task);
    }

    public WithdrawalResult withdrawal(
            WithdrawalRequest request
    ) {
        final AsyncResult<WithdrawalResult>[] resultAsyncResult = new AsyncResult[]{null};
        withdrawalAsync(
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

    class WithdrawalByUserIdTask extends Gs2RestSessionTask<WithdrawalByUserIdResult> {
        private WithdrawalByUserIdRequest request;

        public WithdrawalByUserIdTask(
            WithdrawalByUserIdRequest request,
            AsyncAction<AsyncResult<WithdrawalByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public WithdrawalByUserIdResult parse(JsonNode data) {
            return WithdrawalByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/joined/{guildModelName}/{guildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

    public void withdrawalByUserIdAsync(
            WithdrawalByUserIdRequest request,
            AsyncAction<AsyncResult<WithdrawalByUserIdResult>> callback
    ) {
        WithdrawalByUserIdTask task = new WithdrawalByUserIdTask(request, callback);
        session.execute(task);
    }

    public WithdrawalByUserIdResult withdrawalByUserId(
            WithdrawalByUserIdRequest request
    ) {
        final AsyncResult<WithdrawalByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        withdrawalByUserIdAsync(
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

    class GetLastGuildMasterActivityTask extends Gs2RestSessionTask<GetLastGuildMasterActivityResult> {
        private GetLastGuildMasterActivityRequest request;

        public GetLastGuildMasterActivityTask(
            GetLastGuildMasterActivityRequest request,
            AsyncAction<AsyncResult<GetLastGuildMasterActivityResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetLastGuildMasterActivityResult parse(JsonNode data) {
            return GetLastGuildMasterActivityResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me/activity/guildMaster/last";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

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

    public void getLastGuildMasterActivityAsync(
            GetLastGuildMasterActivityRequest request,
            AsyncAction<AsyncResult<GetLastGuildMasterActivityResult>> callback
    ) {
        GetLastGuildMasterActivityTask task = new GetLastGuildMasterActivityTask(request, callback);
        session.execute(task);
    }

    public GetLastGuildMasterActivityResult getLastGuildMasterActivity(
            GetLastGuildMasterActivityRequest request
    ) {
        final AsyncResult<GetLastGuildMasterActivityResult>[] resultAsyncResult = new AsyncResult[]{null};
        getLastGuildMasterActivityAsync(
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

    class GetLastGuildMasterActivityByGuildNameTask extends Gs2RestSessionTask<GetLastGuildMasterActivityByGuildNameResult> {
        private GetLastGuildMasterActivityByGuildNameRequest request;

        public GetLastGuildMasterActivityByGuildNameTask(
            GetLastGuildMasterActivityByGuildNameRequest request,
            AsyncAction<AsyncResult<GetLastGuildMasterActivityByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetLastGuildMasterActivityByGuildNameResult parse(JsonNode data) {
            return GetLastGuildMasterActivityByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/activity/guildMaster/last";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

    public void getLastGuildMasterActivityByGuildNameAsync(
            GetLastGuildMasterActivityByGuildNameRequest request,
            AsyncAction<AsyncResult<GetLastGuildMasterActivityByGuildNameResult>> callback
    ) {
        GetLastGuildMasterActivityByGuildNameTask task = new GetLastGuildMasterActivityByGuildNameTask(request, callback);
        session.execute(task);
    }

    public GetLastGuildMasterActivityByGuildNameResult getLastGuildMasterActivityByGuildName(
            GetLastGuildMasterActivityByGuildNameRequest request
    ) {
        final AsyncResult<GetLastGuildMasterActivityByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        getLastGuildMasterActivityByGuildNameAsync(
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

    class PromoteSeniorMemberTask extends Gs2RestSessionTask<PromoteSeniorMemberResult> {
        private PromoteSeniorMemberRequest request;

        public PromoteSeniorMemberTask(
            PromoteSeniorMemberRequest request,
            AsyncAction<AsyncResult<PromoteSeniorMemberResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PromoteSeniorMemberResult parse(JsonNode data) {
            return PromoteSeniorMemberResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me/promote";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

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

    public void promoteSeniorMemberAsync(
            PromoteSeniorMemberRequest request,
            AsyncAction<AsyncResult<PromoteSeniorMemberResult>> callback
    ) {
        PromoteSeniorMemberTask task = new PromoteSeniorMemberTask(request, callback);
        session.execute(task);
    }

    public PromoteSeniorMemberResult promoteSeniorMember(
            PromoteSeniorMemberRequest request
    ) {
        final AsyncResult<PromoteSeniorMemberResult>[] resultAsyncResult = new AsyncResult[]{null};
        promoteSeniorMemberAsync(
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

    class PromoteSeniorMemberByGuildNameTask extends Gs2RestSessionTask<PromoteSeniorMemberByGuildNameResult> {
        private PromoteSeniorMemberByGuildNameRequest request;

        public PromoteSeniorMemberByGuildNameTask(
            PromoteSeniorMemberByGuildNameRequest request,
            AsyncAction<AsyncResult<PromoteSeniorMemberByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PromoteSeniorMemberByGuildNameResult parse(JsonNode data) {
            return PromoteSeniorMemberByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/promote";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

            builder
                .build()
                .send();
        }
    }

    public void promoteSeniorMemberByGuildNameAsync(
            PromoteSeniorMemberByGuildNameRequest request,
            AsyncAction<AsyncResult<PromoteSeniorMemberByGuildNameResult>> callback
    ) {
        PromoteSeniorMemberByGuildNameTask task = new PromoteSeniorMemberByGuildNameTask(request, callback);
        session.execute(task);
    }

    public PromoteSeniorMemberByGuildNameResult promoteSeniorMemberByGuildName(
            PromoteSeniorMemberByGuildNameRequest request
    ) {
        final AsyncResult<PromoteSeniorMemberByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        promoteSeniorMemberByGuildNameAsync(
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
                .replace("{service}", "guild")
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

    class GetCurrentGuildMasterTask extends Gs2RestSessionTask<GetCurrentGuildMasterResult> {
        private GetCurrentGuildMasterRequest request;

        public GetCurrentGuildMasterTask(
            GetCurrentGuildMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentGuildMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentGuildMasterResult parse(JsonNode data) {
            return GetCurrentGuildMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
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

    public void getCurrentGuildMasterAsync(
            GetCurrentGuildMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentGuildMasterResult>> callback
    ) {
        GetCurrentGuildMasterTask task = new GetCurrentGuildMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentGuildMasterResult getCurrentGuildMaster(
            GetCurrentGuildMasterRequest request
    ) {
        final AsyncResult<GetCurrentGuildMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentGuildMasterAsync(
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

    class UpdateCurrentGuildMasterTask extends Gs2RestSessionTask<UpdateCurrentGuildMasterResult> {
        private UpdateCurrentGuildMasterRequest request;

        public UpdateCurrentGuildMasterTask(
            UpdateCurrentGuildMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentGuildMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentGuildMasterResult parse(JsonNode data) {
            return UpdateCurrentGuildMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("settings", request.getSettings());
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

    public void updateCurrentGuildMasterAsync(
            UpdateCurrentGuildMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentGuildMasterResult>> callback
    ) {
        UpdateCurrentGuildMasterTask task = new UpdateCurrentGuildMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentGuildMasterResult updateCurrentGuildMaster(
            UpdateCurrentGuildMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentGuildMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentGuildMasterAsync(
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

    class UpdateCurrentGuildMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentGuildMasterFromGitHubResult> {
        private UpdateCurrentGuildMasterFromGitHubRequest request;

        public UpdateCurrentGuildMasterFromGitHubTask(
            UpdateCurrentGuildMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentGuildMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentGuildMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentGuildMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
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

    public void updateCurrentGuildMasterFromGitHubAsync(
            UpdateCurrentGuildMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentGuildMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentGuildMasterFromGitHubTask task = new UpdateCurrentGuildMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentGuildMasterFromGitHubResult updateCurrentGuildMasterFromGitHub(
            UpdateCurrentGuildMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentGuildMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentGuildMasterFromGitHubAsync(
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

    class DescribeReceiveRequestsTask extends Gs2RestSessionTask<DescribeReceiveRequestsResult> {
        private DescribeReceiveRequestsRequest request;

        public DescribeReceiveRequestsTask(
            DescribeReceiveRequestsRequest request,
            AsyncAction<AsyncResult<DescribeReceiveRequestsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeReceiveRequestsResult parse(JsonNode data) {
            return DescribeReceiveRequestsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me/inbox";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

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

    public void describeReceiveRequestsAsync(
            DescribeReceiveRequestsRequest request,
            AsyncAction<AsyncResult<DescribeReceiveRequestsResult>> callback
    ) {
        DescribeReceiveRequestsTask task = new DescribeReceiveRequestsTask(request, callback);
        session.execute(task);
    }

    public DescribeReceiveRequestsResult describeReceiveRequests(
            DescribeReceiveRequestsRequest request
    ) {
        final AsyncResult<DescribeReceiveRequestsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeReceiveRequestsAsync(
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

    class DescribeReceiveRequestsByGuildNameTask extends Gs2RestSessionTask<DescribeReceiveRequestsByGuildNameResult> {
        private DescribeReceiveRequestsByGuildNameRequest request;

        public DescribeReceiveRequestsByGuildNameTask(
            DescribeReceiveRequestsByGuildNameRequest request,
            AsyncAction<AsyncResult<DescribeReceiveRequestsByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeReceiveRequestsByGuildNameResult parse(JsonNode data) {
            return DescribeReceiveRequestsByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/inbox";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

    public void describeReceiveRequestsByGuildNameAsync(
            DescribeReceiveRequestsByGuildNameRequest request,
            AsyncAction<AsyncResult<DescribeReceiveRequestsByGuildNameResult>> callback
    ) {
        DescribeReceiveRequestsByGuildNameTask task = new DescribeReceiveRequestsByGuildNameTask(request, callback);
        session.execute(task);
    }

    public DescribeReceiveRequestsByGuildNameResult describeReceiveRequestsByGuildName(
            DescribeReceiveRequestsByGuildNameRequest request
    ) {
        final AsyncResult<DescribeReceiveRequestsByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeReceiveRequestsByGuildNameAsync(
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

    class GetReceiveRequestTask extends Gs2RestSessionTask<GetReceiveRequestResult> {
        private GetReceiveRequestRequest request;

        public GetReceiveRequestTask(
            GetReceiveRequestRequest request,
            AsyncAction<AsyncResult<GetReceiveRequestResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetReceiveRequestResult parse(JsonNode data) {
            return GetReceiveRequestResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null || this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

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

    public void getReceiveRequestAsync(
            GetReceiveRequestRequest request,
            AsyncAction<AsyncResult<GetReceiveRequestResult>> callback
    ) {
        GetReceiveRequestTask task = new GetReceiveRequestTask(request, callback);
        session.execute(task);
    }

    public GetReceiveRequestResult getReceiveRequest(
            GetReceiveRequestRequest request
    ) {
        final AsyncResult<GetReceiveRequestResult>[] resultAsyncResult = new AsyncResult[]{null};
        getReceiveRequestAsync(
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

    class GetReceiveRequestByGuildNameTask extends Gs2RestSessionTask<GetReceiveRequestByGuildNameResult> {
        private GetReceiveRequestByGuildNameRequest request;

        public GetReceiveRequestByGuildNameTask(
            GetReceiveRequestByGuildNameRequest request,
            AsyncAction<AsyncResult<GetReceiveRequestByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetReceiveRequestByGuildNameResult parse(JsonNode data) {
            return GetReceiveRequestByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null || this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

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

    public void getReceiveRequestByGuildNameAsync(
            GetReceiveRequestByGuildNameRequest request,
            AsyncAction<AsyncResult<GetReceiveRequestByGuildNameResult>> callback
    ) {
        GetReceiveRequestByGuildNameTask task = new GetReceiveRequestByGuildNameTask(request, callback);
        session.execute(task);
    }

    public GetReceiveRequestByGuildNameResult getReceiveRequestByGuildName(
            GetReceiveRequestByGuildNameRequest request
    ) {
        final AsyncResult<GetReceiveRequestByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        getReceiveRequestByGuildNameAsync(
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

    class AcceptRequestTask extends Gs2RestSessionTask<AcceptRequestResult> {
        private AcceptRequestRequest request;

        public AcceptRequestTask(
            AcceptRequestRequest request,
            AsyncAction<AsyncResult<AcceptRequestResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcceptRequestResult parse(JsonNode data) {
            return AcceptRequestResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null || this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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

    public void acceptRequestAsync(
            AcceptRequestRequest request,
            AsyncAction<AsyncResult<AcceptRequestResult>> callback
    ) {
        AcceptRequestTask task = new AcceptRequestTask(request, callback);
        session.execute(task);
    }

    public AcceptRequestResult acceptRequest(
            AcceptRequestRequest request
    ) {
        final AsyncResult<AcceptRequestResult>[] resultAsyncResult = new AsyncResult[]{null};
        acceptRequestAsync(
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

    class AcceptRequestByGuildNameTask extends Gs2RestSessionTask<AcceptRequestByGuildNameResult> {
        private AcceptRequestByGuildNameRequest request;

        public AcceptRequestByGuildNameTask(
            AcceptRequestByGuildNameRequest request,
            AsyncAction<AsyncResult<AcceptRequestByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcceptRequestByGuildNameResult parse(JsonNode data) {
            return AcceptRequestByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null || this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void acceptRequestByGuildNameAsync(
            AcceptRequestByGuildNameRequest request,
            AsyncAction<AsyncResult<AcceptRequestByGuildNameResult>> callback
    ) {
        AcceptRequestByGuildNameTask task = new AcceptRequestByGuildNameTask(request, callback);
        session.execute(task);
    }

    public AcceptRequestByGuildNameResult acceptRequestByGuildName(
            AcceptRequestByGuildNameRequest request
    ) {
        final AsyncResult<AcceptRequestByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        acceptRequestByGuildNameAsync(
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

    class RejectRequestTask extends Gs2RestSessionTask<RejectRequestResult> {
        private RejectRequestRequest request;

        public RejectRequestTask(
            RejectRequestRequest request,
            AsyncAction<AsyncResult<RejectRequestResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RejectRequestResult parse(JsonNode data) {
            return RejectRequestResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null || this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

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

    public void rejectRequestAsync(
            RejectRequestRequest request,
            AsyncAction<AsyncResult<RejectRequestResult>> callback
    ) {
        RejectRequestTask task = new RejectRequestTask(request, callback);
        session.execute(task);
    }

    public RejectRequestResult rejectRequest(
            RejectRequestRequest request
    ) {
        final AsyncResult<RejectRequestResult>[] resultAsyncResult = new AsyncResult[]{null};
        rejectRequestAsync(
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

    class RejectRequestByGuildNameTask extends Gs2RestSessionTask<RejectRequestByGuildNameResult> {
        private RejectRequestByGuildNameRequest request;

        public RejectRequestByGuildNameTask(
            RejectRequestByGuildNameRequest request,
            AsyncAction<AsyncResult<RejectRequestByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RejectRequestByGuildNameResult parse(JsonNode data) {
            return RejectRequestByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/inbox/{fromUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));
            url = url.replace("{fromUserId}", this.request.getFromUserId() == null || this.request.getFromUserId().length() == 0 ? "null" : String.valueOf(this.request.getFromUserId()));

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

            builder
                .build()
                .send();
        }
    }

    public void rejectRequestByGuildNameAsync(
            RejectRequestByGuildNameRequest request,
            AsyncAction<AsyncResult<RejectRequestByGuildNameResult>> callback
    ) {
        RejectRequestByGuildNameTask task = new RejectRequestByGuildNameTask(request, callback);
        session.execute(task);
    }

    public RejectRequestByGuildNameResult rejectRequestByGuildName(
            RejectRequestByGuildNameRequest request
    ) {
        final AsyncResult<RejectRequestByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        rejectRequestByGuildNameAsync(
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

    class DescribeSendRequestsTask extends Gs2RestSessionTask<DescribeSendRequestsResult> {
        private DescribeSendRequestsRequest request;

        public DescribeSendRequestsTask(
            DescribeSendRequestsRequest request,
            AsyncAction<AsyncResult<DescribeSendRequestsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSendRequestsResult parse(JsonNode data) {
            return DescribeSendRequestsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/sendBox/guild/{guildModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

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

    public void describeSendRequestsAsync(
            DescribeSendRequestsRequest request,
            AsyncAction<AsyncResult<DescribeSendRequestsResult>> callback
    ) {
        DescribeSendRequestsTask task = new DescribeSendRequestsTask(request, callback);
        session.execute(task);
    }

    public DescribeSendRequestsResult describeSendRequests(
            DescribeSendRequestsRequest request
    ) {
        final AsyncResult<DescribeSendRequestsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSendRequestsAsync(
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

    class DescribeSendRequestsByUserIdTask extends Gs2RestSessionTask<DescribeSendRequestsByUserIdResult> {
        private DescribeSendRequestsByUserIdRequest request;

        public DescribeSendRequestsByUserIdTask(
            DescribeSendRequestsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSendRequestsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSendRequestsByUserIdResult parse(JsonNode data) {
            return DescribeSendRequestsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/sendBox/guild/{guildModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

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

    public void describeSendRequestsByUserIdAsync(
            DescribeSendRequestsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSendRequestsByUserIdResult>> callback
    ) {
        DescribeSendRequestsByUserIdTask task = new DescribeSendRequestsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeSendRequestsByUserIdResult describeSendRequestsByUserId(
            DescribeSendRequestsByUserIdRequest request
    ) {
        final AsyncResult<DescribeSendRequestsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSendRequestsByUserIdAsync(
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

    class GetSendRequestTask extends Gs2RestSessionTask<GetSendRequestResult> {
        private GetSendRequestRequest request;

        public GetSendRequestTask(
            GetSendRequestRequest request,
            AsyncAction<AsyncResult<GetSendRequestResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSendRequestResult parse(JsonNode data) {
            return GetSendRequestResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/sendBox/guild/{guildModelName}/{targetGuildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{targetGuildName}", this.request.getTargetGuildName() == null || this.request.getTargetGuildName().length() == 0 ? "null" : String.valueOf(this.request.getTargetGuildName()));

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

    public void getSendRequestAsync(
            GetSendRequestRequest request,
            AsyncAction<AsyncResult<GetSendRequestResult>> callback
    ) {
        GetSendRequestTask task = new GetSendRequestTask(request, callback);
        session.execute(task);
    }

    public GetSendRequestResult getSendRequest(
            GetSendRequestRequest request
    ) {
        final AsyncResult<GetSendRequestResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSendRequestAsync(
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

    class GetSendRequestByUserIdTask extends Gs2RestSessionTask<GetSendRequestByUserIdResult> {
        private GetSendRequestByUserIdRequest request;

        public GetSendRequestByUserIdTask(
            GetSendRequestByUserIdRequest request,
            AsyncAction<AsyncResult<GetSendRequestByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSendRequestByUserIdResult parse(JsonNode data) {
            return GetSendRequestByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/sendBox/guild/{guildModelName}/{targetGuildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{targetGuildName}", this.request.getTargetGuildName() == null || this.request.getTargetGuildName().length() == 0 ? "null" : String.valueOf(this.request.getTargetGuildName()));

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

    public void getSendRequestByUserIdAsync(
            GetSendRequestByUserIdRequest request,
            AsyncAction<AsyncResult<GetSendRequestByUserIdResult>> callback
    ) {
        GetSendRequestByUserIdTask task = new GetSendRequestByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetSendRequestByUserIdResult getSendRequestByUserId(
            GetSendRequestByUserIdRequest request
    ) {
        final AsyncResult<GetSendRequestByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSendRequestByUserIdAsync(
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

    class SendRequestTask extends Gs2RestSessionTask<SendRequestResult> {
        private SendRequestRequest request;

        public SendRequestTask(
            SendRequestRequest request,
            AsyncAction<AsyncResult<SendRequestResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SendRequestResult parse(JsonNode data) {
            return SendRequestResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/sendBox/guild/{guildModelName}/{targetGuildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{targetGuildName}", this.request.getTargetGuildName() == null || this.request.getTargetGuildName().length() == 0 ? "null" : String.valueOf(this.request.getTargetGuildName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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

    public void sendRequestAsync(
            SendRequestRequest request,
            AsyncAction<AsyncResult<SendRequestResult>> callback
    ) {
        SendRequestTask task = new SendRequestTask(request, callback);
        session.execute(task);
    }

    public SendRequestResult sendRequest(
            SendRequestRequest request
    ) {
        final AsyncResult<SendRequestResult>[] resultAsyncResult = new AsyncResult[]{null};
        sendRequestAsync(
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

    class SendRequestByUserIdTask extends Gs2RestSessionTask<SendRequestByUserIdResult> {
        private SendRequestByUserIdRequest request;

        public SendRequestByUserIdTask(
            SendRequestByUserIdRequest request,
            AsyncAction<AsyncResult<SendRequestByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SendRequestByUserIdResult parse(JsonNode data) {
            return SendRequestByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/sendBox/guild/{guildModelName}/{targetGuildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{targetGuildName}", this.request.getTargetGuildName() == null || this.request.getTargetGuildName().length() == 0 ? "null" : String.valueOf(this.request.getTargetGuildName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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

    public void sendRequestByUserIdAsync(
            SendRequestByUserIdRequest request,
            AsyncAction<AsyncResult<SendRequestByUserIdResult>> callback
    ) {
        SendRequestByUserIdTask task = new SendRequestByUserIdTask(request, callback);
        session.execute(task);
    }

    public SendRequestByUserIdResult sendRequestByUserId(
            SendRequestByUserIdRequest request
    ) {
        final AsyncResult<SendRequestByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        sendRequestByUserIdAsync(
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

    class DeleteRequestTask extends Gs2RestSessionTask<DeleteRequestResult> {
        private DeleteRequestRequest request;

        public DeleteRequestTask(
            DeleteRequestRequest request,
            AsyncAction<AsyncResult<DeleteRequestResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteRequestResult parse(JsonNode data) {
            return DeleteRequestResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/sendBox/guild/{guildModelName}/{targetGuildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{targetGuildName}", this.request.getTargetGuildName() == null || this.request.getTargetGuildName().length() == 0 ? "null" : String.valueOf(this.request.getTargetGuildName()));

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

    public void deleteRequestAsync(
            DeleteRequestRequest request,
            AsyncAction<AsyncResult<DeleteRequestResult>> callback
    ) {
        DeleteRequestTask task = new DeleteRequestTask(request, callback);
        session.execute(task);
    }

    public DeleteRequestResult deleteRequest(
            DeleteRequestRequest request
    ) {
        final AsyncResult<DeleteRequestResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRequestAsync(
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

    class DeleteRequestByUserIdTask extends Gs2RestSessionTask<DeleteRequestByUserIdResult> {
        private DeleteRequestByUserIdRequest request;

        public DeleteRequestByUserIdTask(
            DeleteRequestByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteRequestByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteRequestByUserIdResult parse(JsonNode data) {
            return DeleteRequestByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/sendBox/guild/{guildModelName}/{targetGuildName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{targetGuildName}", this.request.getTargetGuildName() == null || this.request.getTargetGuildName().length() == 0 ? "null" : String.valueOf(this.request.getTargetGuildName()));

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

    public void deleteRequestByUserIdAsync(
            DeleteRequestByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteRequestByUserIdResult>> callback
    ) {
        DeleteRequestByUserIdTask task = new DeleteRequestByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteRequestByUserIdResult deleteRequestByUserId(
            DeleteRequestByUserIdRequest request
    ) {
        final AsyncResult<DeleteRequestByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRequestByUserIdAsync(
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

    class DescribeIgnoreUsersTask extends Gs2RestSessionTask<DescribeIgnoreUsersResult> {
        private DescribeIgnoreUsersRequest request;

        public DescribeIgnoreUsersTask(
            DescribeIgnoreUsersRequest request,
            AsyncAction<AsyncResult<DescribeIgnoreUsersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeIgnoreUsersResult parse(JsonNode data) {
            return DescribeIgnoreUsersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me/ignore/user";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));

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

    public void describeIgnoreUsersAsync(
            DescribeIgnoreUsersRequest request,
            AsyncAction<AsyncResult<DescribeIgnoreUsersResult>> callback
    ) {
        DescribeIgnoreUsersTask task = new DescribeIgnoreUsersTask(request, callback);
        session.execute(task);
    }

    public DescribeIgnoreUsersResult describeIgnoreUsers(
            DescribeIgnoreUsersRequest request
    ) {
        final AsyncResult<DescribeIgnoreUsersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeIgnoreUsersAsync(
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

    class DescribeIgnoreUsersByGuildNameTask extends Gs2RestSessionTask<DescribeIgnoreUsersByGuildNameResult> {
        private DescribeIgnoreUsersByGuildNameRequest request;

        public DescribeIgnoreUsersByGuildNameTask(
            DescribeIgnoreUsersByGuildNameRequest request,
            AsyncAction<AsyncResult<DescribeIgnoreUsersByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeIgnoreUsersByGuildNameResult parse(JsonNode data) {
            return DescribeIgnoreUsersByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/ignore/user";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));

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

    public void describeIgnoreUsersByGuildNameAsync(
            DescribeIgnoreUsersByGuildNameRequest request,
            AsyncAction<AsyncResult<DescribeIgnoreUsersByGuildNameResult>> callback
    ) {
        DescribeIgnoreUsersByGuildNameTask task = new DescribeIgnoreUsersByGuildNameTask(request, callback);
        session.execute(task);
    }

    public DescribeIgnoreUsersByGuildNameResult describeIgnoreUsersByGuildName(
            DescribeIgnoreUsersByGuildNameRequest request
    ) {
        final AsyncResult<DescribeIgnoreUsersByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeIgnoreUsersByGuildNameAsync(
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

    class GetIgnoreUserTask extends Gs2RestSessionTask<GetIgnoreUserResult> {
        private GetIgnoreUserRequest request;

        public GetIgnoreUserTask(
            GetIgnoreUserRequest request,
            AsyncAction<AsyncResult<GetIgnoreUserResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetIgnoreUserResult parse(JsonNode data) {
            return GetIgnoreUserResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me/ignore/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
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
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void getIgnoreUserAsync(
            GetIgnoreUserRequest request,
            AsyncAction<AsyncResult<GetIgnoreUserResult>> callback
    ) {
        GetIgnoreUserTask task = new GetIgnoreUserTask(request, callback);
        session.execute(task);
    }

    public GetIgnoreUserResult getIgnoreUser(
            GetIgnoreUserRequest request
    ) {
        final AsyncResult<GetIgnoreUserResult>[] resultAsyncResult = new AsyncResult[]{null};
        getIgnoreUserAsync(
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

    class GetIgnoreUserByGuildNameTask extends Gs2RestSessionTask<GetIgnoreUserByGuildNameResult> {
        private GetIgnoreUserByGuildNameRequest request;

        public GetIgnoreUserByGuildNameTask(
            GetIgnoreUserByGuildNameRequest request,
            AsyncAction<AsyncResult<GetIgnoreUserByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetIgnoreUserByGuildNameResult parse(JsonNode data) {
            return GetIgnoreUserByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/ignore/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));
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

    public void getIgnoreUserByGuildNameAsync(
            GetIgnoreUserByGuildNameRequest request,
            AsyncAction<AsyncResult<GetIgnoreUserByGuildNameResult>> callback
    ) {
        GetIgnoreUserByGuildNameTask task = new GetIgnoreUserByGuildNameTask(request, callback);
        session.execute(task);
    }

    public GetIgnoreUserByGuildNameResult getIgnoreUserByGuildName(
            GetIgnoreUserByGuildNameRequest request
    ) {
        final AsyncResult<GetIgnoreUserByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        getIgnoreUserByGuildNameAsync(
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

    class AddIgnoreUserTask extends Gs2RestSessionTask<AddIgnoreUserResult> {
        private AddIgnoreUserRequest request;

        public AddIgnoreUserTask(
            AddIgnoreUserRequest request,
            AsyncAction<AsyncResult<AddIgnoreUserResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddIgnoreUserResult parse(JsonNode data) {
            return AddIgnoreUserResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me/ignore/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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

    public void addIgnoreUserAsync(
            AddIgnoreUserRequest request,
            AsyncAction<AsyncResult<AddIgnoreUserResult>> callback
    ) {
        AddIgnoreUserTask task = new AddIgnoreUserTask(request, callback);
        session.execute(task);
    }

    public AddIgnoreUserResult addIgnoreUser(
            AddIgnoreUserRequest request
    ) {
        final AsyncResult<AddIgnoreUserResult>[] resultAsyncResult = new AsyncResult[]{null};
        addIgnoreUserAsync(
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

    class AddIgnoreUserByGuildNameTask extends Gs2RestSessionTask<AddIgnoreUserByGuildNameResult> {
        private AddIgnoreUserByGuildNameRequest request;

        public AddIgnoreUserByGuildNameTask(
            AddIgnoreUserByGuildNameRequest request,
            AsyncAction<AsyncResult<AddIgnoreUserByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddIgnoreUserByGuildNameResult parse(JsonNode data) {
            return AddIgnoreUserByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/ignore/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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

    public void addIgnoreUserByGuildNameAsync(
            AddIgnoreUserByGuildNameRequest request,
            AsyncAction<AsyncResult<AddIgnoreUserByGuildNameResult>> callback
    ) {
        AddIgnoreUserByGuildNameTask task = new AddIgnoreUserByGuildNameTask(request, callback);
        session.execute(task);
    }

    public AddIgnoreUserByGuildNameResult addIgnoreUserByGuildName(
            AddIgnoreUserByGuildNameRequest request
    ) {
        final AsyncResult<AddIgnoreUserByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        addIgnoreUserByGuildNameAsync(
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

    class DeleteIgnoreUserTask extends Gs2RestSessionTask<DeleteIgnoreUserResult> {
        private DeleteIgnoreUserRequest request;

        public DeleteIgnoreUserTask(
            DeleteIgnoreUserRequest request,
            AsyncAction<AsyncResult<DeleteIgnoreUserResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteIgnoreUserResult parse(JsonNode data) {
            return DeleteIgnoreUserResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/me/ignore/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
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

    public void deleteIgnoreUserAsync(
            DeleteIgnoreUserRequest request,
            AsyncAction<AsyncResult<DeleteIgnoreUserResult>> callback
    ) {
        DeleteIgnoreUserTask task = new DeleteIgnoreUserTask(request, callback);
        session.execute(task);
    }

    public DeleteIgnoreUserResult deleteIgnoreUser(
            DeleteIgnoreUserRequest request
    ) {
        final AsyncResult<DeleteIgnoreUserResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteIgnoreUserAsync(
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

    class DeleteIgnoreUserByGuildNameTask extends Gs2RestSessionTask<DeleteIgnoreUserByGuildNameResult> {
        private DeleteIgnoreUserByGuildNameRequest request;

        public DeleteIgnoreUserByGuildNameTask(
            DeleteIgnoreUserByGuildNameRequest request,
            AsyncAction<AsyncResult<DeleteIgnoreUserByGuildNameResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteIgnoreUserByGuildNameResult parse(JsonNode data) {
            return DeleteIgnoreUserByGuildNameResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "guild")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/guild/{guildModelName}/{guildName}/ignore/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{guildModelName}", this.request.getGuildModelName() == null || this.request.getGuildModelName().length() == 0 ? "null" : String.valueOf(this.request.getGuildModelName()));
            url = url.replace("{guildName}", this.request.getGuildName() == null || this.request.getGuildName().length() == 0 ? "null" : String.valueOf(this.request.getGuildName()));
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

    public void deleteIgnoreUserByGuildNameAsync(
            DeleteIgnoreUserByGuildNameRequest request,
            AsyncAction<AsyncResult<DeleteIgnoreUserByGuildNameResult>> callback
    ) {
        DeleteIgnoreUserByGuildNameTask task = new DeleteIgnoreUserByGuildNameTask(request, callback);
        session.execute(task);
    }

    public DeleteIgnoreUserByGuildNameResult deleteIgnoreUserByGuildName(
            DeleteIgnoreUserByGuildNameRequest request
    ) {
        final AsyncResult<DeleteIgnoreUserByGuildNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteIgnoreUserByGuildNameAsync(
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