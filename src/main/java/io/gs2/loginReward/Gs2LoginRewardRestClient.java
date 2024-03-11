
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

package io.gs2.loginReward;

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
import io.gs2.loginReward.request.*;
import io.gs2.loginReward.result.*;
import io.gs2.loginReward.model.*;public class Gs2LoginRewardRestClient extends AbstractGs2Client<Gs2LoginRewardRestClient> {

	public Gs2LoginRewardRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "login-reward")
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
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("receiveScript", request.getReceiveScript() != null ? request.getReceiveScript().toJson() : null);
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
                .replace("{service}", "login-reward")
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
                .replace("{service}", "login-reward")
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
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("receiveScript", request.getReceiveScript() != null ? request.getReceiveScript().toJson() : null);
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
                .replace("{service}", "login-reward")
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
                .replace("{service}", "login-reward")
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
                .replace("{service}", "login-reward")
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
                .replace("{service}", "login-reward")
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
                .replace("{service}", "login-reward")
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
                .replace("{service}", "login-reward")
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
                .replace("{service}", "login-reward")
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
                .replace("{service}", "login-reward")
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

    class DescribeBonusModelMastersTask extends Gs2RestSessionTask<DescribeBonusModelMastersResult> {
        private DescribeBonusModelMastersRequest request;

        public DescribeBonusModelMastersTask(
            DescribeBonusModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeBonusModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBonusModelMastersResult parse(JsonNode data) {
            return DescribeBonusModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/bonusModel";

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

    public void describeBonusModelMastersAsync(
            DescribeBonusModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeBonusModelMastersResult>> callback
    ) {
        DescribeBonusModelMastersTask task = new DescribeBonusModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeBonusModelMastersResult describeBonusModelMasters(
            DescribeBonusModelMastersRequest request
    ) {
        final AsyncResult<DescribeBonusModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBonusModelMastersAsync(
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

    class CreateBonusModelMasterTask extends Gs2RestSessionTask<CreateBonusModelMasterResult> {
        private CreateBonusModelMasterRequest request;

        public CreateBonusModelMasterTask(
            CreateBonusModelMasterRequest request,
            AsyncAction<AsyncResult<CreateBonusModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateBonusModelMasterResult parse(JsonNode data) {
            return CreateBonusModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/bonusModel";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("mode", request.getMode());
                    put("periodEventId", request.getPeriodEventId());
                    put("resetHour", request.getResetHour());
                    put("repeat", request.getRepeat());
                    put("rewards", request.getRewards() == null ? new ArrayList<Reward>() :
                        request.getRewards().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("missedReceiveRelief", request.getMissedReceiveRelief());
                    put("missedReceiveReliefConsumeActions", request.getMissedReceiveReliefConsumeActions() == null ? new ArrayList<ConsumeAction>() :
                        request.getMissedReceiveReliefConsumeActions().stream().map(item -> {
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

    public void createBonusModelMasterAsync(
            CreateBonusModelMasterRequest request,
            AsyncAction<AsyncResult<CreateBonusModelMasterResult>> callback
    ) {
        CreateBonusModelMasterTask task = new CreateBonusModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateBonusModelMasterResult createBonusModelMaster(
            CreateBonusModelMasterRequest request
    ) {
        final AsyncResult<CreateBonusModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createBonusModelMasterAsync(
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

    class GetBonusModelMasterTask extends Gs2RestSessionTask<GetBonusModelMasterResult> {
        private GetBonusModelMasterRequest request;

        public GetBonusModelMasterTask(
            GetBonusModelMasterRequest request,
            AsyncAction<AsyncResult<GetBonusModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBonusModelMasterResult parse(JsonNode data) {
            return GetBonusModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/bonusModel/{bonusModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));

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

    public void getBonusModelMasterAsync(
            GetBonusModelMasterRequest request,
            AsyncAction<AsyncResult<GetBonusModelMasterResult>> callback
    ) {
        GetBonusModelMasterTask task = new GetBonusModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetBonusModelMasterResult getBonusModelMaster(
            GetBonusModelMasterRequest request
    ) {
        final AsyncResult<GetBonusModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBonusModelMasterAsync(
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

    class UpdateBonusModelMasterTask extends Gs2RestSessionTask<UpdateBonusModelMasterResult> {
        private UpdateBonusModelMasterRequest request;

        public UpdateBonusModelMasterTask(
            UpdateBonusModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateBonusModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateBonusModelMasterResult parse(JsonNode data) {
            return UpdateBonusModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/bonusModel/{bonusModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("mode", request.getMode());
                    put("periodEventId", request.getPeriodEventId());
                    put("resetHour", request.getResetHour());
                    put("repeat", request.getRepeat());
                    put("rewards", request.getRewards() == null ? new ArrayList<Reward>() :
                        request.getRewards().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("missedReceiveRelief", request.getMissedReceiveRelief());
                    put("missedReceiveReliefConsumeActions", request.getMissedReceiveReliefConsumeActions() == null ? new ArrayList<ConsumeAction>() :
                        request.getMissedReceiveReliefConsumeActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
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

    public void updateBonusModelMasterAsync(
            UpdateBonusModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateBonusModelMasterResult>> callback
    ) {
        UpdateBonusModelMasterTask task = new UpdateBonusModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateBonusModelMasterResult updateBonusModelMaster(
            UpdateBonusModelMasterRequest request
    ) {
        final AsyncResult<UpdateBonusModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateBonusModelMasterAsync(
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

    class DeleteBonusModelMasterTask extends Gs2RestSessionTask<DeleteBonusModelMasterResult> {
        private DeleteBonusModelMasterRequest request;

        public DeleteBonusModelMasterTask(
            DeleteBonusModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteBonusModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteBonusModelMasterResult parse(JsonNode data) {
            return DeleteBonusModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/bonusModel/{bonusModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));

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

    public void deleteBonusModelMasterAsync(
            DeleteBonusModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteBonusModelMasterResult>> callback
    ) {
        DeleteBonusModelMasterTask task = new DeleteBonusModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteBonusModelMasterResult deleteBonusModelMaster(
            DeleteBonusModelMasterRequest request
    ) {
        final AsyncResult<DeleteBonusModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteBonusModelMasterAsync(
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
                .replace("{service}", "login-reward")
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

    class GetCurrentBonusMasterTask extends Gs2RestSessionTask<GetCurrentBonusMasterResult> {
        private GetCurrentBonusMasterRequest request;

        public GetCurrentBonusMasterTask(
            GetCurrentBonusMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentBonusMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentBonusMasterResult parse(JsonNode data) {
            return GetCurrentBonusMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
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

    public void getCurrentBonusMasterAsync(
            GetCurrentBonusMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentBonusMasterResult>> callback
    ) {
        GetCurrentBonusMasterTask task = new GetCurrentBonusMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentBonusMasterResult getCurrentBonusMaster(
            GetCurrentBonusMasterRequest request
    ) {
        final AsyncResult<GetCurrentBonusMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentBonusMasterAsync(
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

    class UpdateCurrentBonusMasterTask extends Gs2RestSessionTask<UpdateCurrentBonusMasterResult> {
        private UpdateCurrentBonusMasterRequest request;

        public UpdateCurrentBonusMasterTask(
            UpdateCurrentBonusMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentBonusMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentBonusMasterResult parse(JsonNode data) {
            return UpdateCurrentBonusMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
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

    public void updateCurrentBonusMasterAsync(
            UpdateCurrentBonusMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentBonusMasterResult>> callback
    ) {
        UpdateCurrentBonusMasterTask task = new UpdateCurrentBonusMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentBonusMasterResult updateCurrentBonusMaster(
            UpdateCurrentBonusMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentBonusMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentBonusMasterAsync(
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

    class UpdateCurrentBonusMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentBonusMasterFromGitHubResult> {
        private UpdateCurrentBonusMasterFromGitHubRequest request;

        public UpdateCurrentBonusMasterFromGitHubTask(
            UpdateCurrentBonusMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentBonusMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentBonusMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentBonusMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
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

    public void updateCurrentBonusMasterFromGitHubAsync(
            UpdateCurrentBonusMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentBonusMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentBonusMasterFromGitHubTask task = new UpdateCurrentBonusMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentBonusMasterFromGitHubResult updateCurrentBonusMasterFromGitHub(
            UpdateCurrentBonusMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentBonusMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentBonusMasterFromGitHubAsync(
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

    class DescribeBonusModelsTask extends Gs2RestSessionTask<DescribeBonusModelsResult> {
        private DescribeBonusModelsRequest request;

        public DescribeBonusModelsTask(
            DescribeBonusModelsRequest request,
            AsyncAction<AsyncResult<DescribeBonusModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBonusModelsResult parse(JsonNode data) {
            return DescribeBonusModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/bonusModel";

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

    public void describeBonusModelsAsync(
            DescribeBonusModelsRequest request,
            AsyncAction<AsyncResult<DescribeBonusModelsResult>> callback
    ) {
        DescribeBonusModelsTask task = new DescribeBonusModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeBonusModelsResult describeBonusModels(
            DescribeBonusModelsRequest request
    ) {
        final AsyncResult<DescribeBonusModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBonusModelsAsync(
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

    class GetBonusModelTask extends Gs2RestSessionTask<GetBonusModelResult> {
        private GetBonusModelRequest request;

        public GetBonusModelTask(
            GetBonusModelRequest request,
            AsyncAction<AsyncResult<GetBonusModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBonusModelResult parse(JsonNode data) {
            return GetBonusModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/bonusModel/{bonusModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));

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

    public void getBonusModelAsync(
            GetBonusModelRequest request,
            AsyncAction<AsyncResult<GetBonusModelResult>> callback
    ) {
        GetBonusModelTask task = new GetBonusModelTask(request, callback);
        session.execute(task);
    }

    public GetBonusModelResult getBonusModel(
            GetBonusModelRequest request
    ) {
        final AsyncResult<GetBonusModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBonusModelAsync(
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

    class ReceiveTask extends Gs2RestSessionTask<ReceiveResult> {
        private ReceiveRequest request;

        public ReceiveTask(
            ReceiveRequest request,
            AsyncAction<AsyncResult<ReceiveResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ReceiveResult parse(JsonNode data) {
            return ReceiveResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/bonus/{bonusModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("config", request.getConfig() == null ? new ArrayList<Config>() :
                        request.getConfig().stream().map(item -> {
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

    public void receiveAsync(
            ReceiveRequest request,
            AsyncAction<AsyncResult<ReceiveResult>> callback
    ) {
        ReceiveTask task = new ReceiveTask(request, callback);
        session.execute(task);
    }

    public ReceiveResult receive(
            ReceiveRequest request
    ) {
        final AsyncResult<ReceiveResult>[] resultAsyncResult = new AsyncResult[]{null};
        receiveAsync(
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

    class ReceiveByUserIdTask extends Gs2RestSessionTask<ReceiveByUserIdResult> {
        private ReceiveByUserIdRequest request;

        public ReceiveByUserIdTask(
            ReceiveByUserIdRequest request,
            AsyncAction<AsyncResult<ReceiveByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ReceiveByUserIdResult parse(JsonNode data) {
            return ReceiveByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/bonus/{bonusModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("config", request.getConfig() == null ? new ArrayList<Config>() :
                        request.getConfig().stream().map(item -> {
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

    public void receiveByUserIdAsync(
            ReceiveByUserIdRequest request,
            AsyncAction<AsyncResult<ReceiveByUserIdResult>> callback
    ) {
        ReceiveByUserIdTask task = new ReceiveByUserIdTask(request, callback);
        session.execute(task);
    }

    public ReceiveByUserIdResult receiveByUserId(
            ReceiveByUserIdRequest request
    ) {
        final AsyncResult<ReceiveByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        receiveByUserIdAsync(
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

    class MissedReceiveTask extends Gs2RestSessionTask<MissedReceiveResult> {
        private MissedReceiveRequest request;

        public MissedReceiveTask(
            MissedReceiveRequest request,
            AsyncAction<AsyncResult<MissedReceiveResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public MissedReceiveResult parse(JsonNode data) {
            return MissedReceiveResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/bonus/{bonusModelName}/missed";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stepNumber", request.getStepNumber());
                    put("config", request.getConfig() == null ? new ArrayList<Config>() :
                        request.getConfig().stream().map(item -> {
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

    public void missedReceiveAsync(
            MissedReceiveRequest request,
            AsyncAction<AsyncResult<MissedReceiveResult>> callback
    ) {
        MissedReceiveTask task = new MissedReceiveTask(request, callback);
        session.execute(task);
    }

    public MissedReceiveResult missedReceive(
            MissedReceiveRequest request
    ) {
        final AsyncResult<MissedReceiveResult>[] resultAsyncResult = new AsyncResult[]{null};
        missedReceiveAsync(
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

    class MissedReceiveByUserIdTask extends Gs2RestSessionTask<MissedReceiveByUserIdResult> {
        private MissedReceiveByUserIdRequest request;

        public MissedReceiveByUserIdTask(
            MissedReceiveByUserIdRequest request,
            AsyncAction<AsyncResult<MissedReceiveByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public MissedReceiveByUserIdResult parse(JsonNode data) {
            return MissedReceiveByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/bonus/{bonusModelName}/missed";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stepNumber", request.getStepNumber());
                    put("config", request.getConfig() == null ? new ArrayList<Config>() :
                        request.getConfig().stream().map(item -> {
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

    public void missedReceiveByUserIdAsync(
            MissedReceiveByUserIdRequest request,
            AsyncAction<AsyncResult<MissedReceiveByUserIdResult>> callback
    ) {
        MissedReceiveByUserIdTask task = new MissedReceiveByUserIdTask(request, callback);
        session.execute(task);
    }

    public MissedReceiveByUserIdResult missedReceiveByUserId(
            MissedReceiveByUserIdRequest request
    ) {
        final AsyncResult<MissedReceiveByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        missedReceiveByUserIdAsync(
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

    class DescribeReceiveStatusesTask extends Gs2RestSessionTask<DescribeReceiveStatusesResult> {
        private DescribeReceiveStatusesRequest request;

        public DescribeReceiveStatusesTask(
            DescribeReceiveStatusesRequest request,
            AsyncAction<AsyncResult<DescribeReceiveStatusesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeReceiveStatusesResult parse(JsonNode data) {
            return DescribeReceiveStatusesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/login_reward";

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

    public void describeReceiveStatusesAsync(
            DescribeReceiveStatusesRequest request,
            AsyncAction<AsyncResult<DescribeReceiveStatusesResult>> callback
    ) {
        DescribeReceiveStatusesTask task = new DescribeReceiveStatusesTask(request, callback);
        session.execute(task);
    }

    public DescribeReceiveStatusesResult describeReceiveStatuses(
            DescribeReceiveStatusesRequest request
    ) {
        final AsyncResult<DescribeReceiveStatusesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeReceiveStatusesAsync(
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

    class DescribeReceiveStatusesByUserIdTask extends Gs2RestSessionTask<DescribeReceiveStatusesByUserIdResult> {
        private DescribeReceiveStatusesByUserIdRequest request;

        public DescribeReceiveStatusesByUserIdTask(
            DescribeReceiveStatusesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeReceiveStatusesByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeReceiveStatusesByUserIdResult parse(JsonNode data) {
            return DescribeReceiveStatusesByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/login_reward";

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

    public void describeReceiveStatusesByUserIdAsync(
            DescribeReceiveStatusesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeReceiveStatusesByUserIdResult>> callback
    ) {
        DescribeReceiveStatusesByUserIdTask task = new DescribeReceiveStatusesByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeReceiveStatusesByUserIdResult describeReceiveStatusesByUserId(
            DescribeReceiveStatusesByUserIdRequest request
    ) {
        final AsyncResult<DescribeReceiveStatusesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeReceiveStatusesByUserIdAsync(
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

    class GetReceiveStatusTask extends Gs2RestSessionTask<GetReceiveStatusResult> {
        private GetReceiveStatusRequest request;

        public GetReceiveStatusTask(
            GetReceiveStatusRequest request,
            AsyncAction<AsyncResult<GetReceiveStatusResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetReceiveStatusResult parse(JsonNode data) {
            return GetReceiveStatusResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/receiveStatus/{bonusModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));

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

    public void getReceiveStatusAsync(
            GetReceiveStatusRequest request,
            AsyncAction<AsyncResult<GetReceiveStatusResult>> callback
    ) {
        GetReceiveStatusTask task = new GetReceiveStatusTask(request, callback);
        session.execute(task);
    }

    public GetReceiveStatusResult getReceiveStatus(
            GetReceiveStatusRequest request
    ) {
        final AsyncResult<GetReceiveStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        getReceiveStatusAsync(
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

    class GetReceiveStatusByUserIdTask extends Gs2RestSessionTask<GetReceiveStatusByUserIdResult> {
        private GetReceiveStatusByUserIdRequest request;

        public GetReceiveStatusByUserIdTask(
            GetReceiveStatusByUserIdRequest request,
            AsyncAction<AsyncResult<GetReceiveStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetReceiveStatusByUserIdResult parse(JsonNode data) {
            return GetReceiveStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/receiveStatus/{bonusModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));
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

    public void getReceiveStatusByUserIdAsync(
            GetReceiveStatusByUserIdRequest request,
            AsyncAction<AsyncResult<GetReceiveStatusByUserIdResult>> callback
    ) {
        GetReceiveStatusByUserIdTask task = new GetReceiveStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetReceiveStatusByUserIdResult getReceiveStatusByUserId(
            GetReceiveStatusByUserIdRequest request
    ) {
        final AsyncResult<GetReceiveStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getReceiveStatusByUserIdAsync(
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

    class DeleteReceiveStatusByUserIdTask extends Gs2RestSessionTask<DeleteReceiveStatusByUserIdResult> {
        private DeleteReceiveStatusByUserIdRequest request;

        public DeleteReceiveStatusByUserIdTask(
            DeleteReceiveStatusByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteReceiveStatusByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteReceiveStatusByUserIdResult parse(JsonNode data) {
            return DeleteReceiveStatusByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/receiveStatus/{bonusModelName}/delete";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));
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

    public void deleteReceiveStatusByUserIdAsync(
            DeleteReceiveStatusByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteReceiveStatusByUserIdResult>> callback
    ) {
        DeleteReceiveStatusByUserIdTask task = new DeleteReceiveStatusByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteReceiveStatusByUserIdResult deleteReceiveStatusByUserId(
            DeleteReceiveStatusByUserIdRequest request
    ) {
        final AsyncResult<DeleteReceiveStatusByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteReceiveStatusByUserIdAsync(
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

    class DeleteReceiveStatusByStampSheetTask extends Gs2RestSessionTask<DeleteReceiveStatusByStampSheetResult> {
        private DeleteReceiveStatusByStampSheetRequest request;

        public DeleteReceiveStatusByStampSheetTask(
            DeleteReceiveStatusByStampSheetRequest request,
            AsyncAction<AsyncResult<DeleteReceiveStatusByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteReceiveStatusByStampSheetResult parse(JsonNode data) {
            return DeleteReceiveStatusByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/receiveStatus/delete";

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

    public void deleteReceiveStatusByStampSheetAsync(
            DeleteReceiveStatusByStampSheetRequest request,
            AsyncAction<AsyncResult<DeleteReceiveStatusByStampSheetResult>> callback
    ) {
        DeleteReceiveStatusByStampSheetTask task = new DeleteReceiveStatusByStampSheetTask(request, callback);
        session.execute(task);
    }

    public DeleteReceiveStatusByStampSheetResult deleteReceiveStatusByStampSheet(
            DeleteReceiveStatusByStampSheetRequest request
    ) {
        final AsyncResult<DeleteReceiveStatusByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteReceiveStatusByStampSheetAsync(
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

    class MarkReceivedTask extends Gs2RestSessionTask<MarkReceivedResult> {
        private MarkReceivedRequest request;

        public MarkReceivedTask(
            MarkReceivedRequest request,
            AsyncAction<AsyncResult<MarkReceivedResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public MarkReceivedResult parse(JsonNode data) {
            return MarkReceivedResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/receiveStatus/{bonusModelName}/mark";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stepNumber", request.getStepNumber());
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

    public void markReceivedAsync(
            MarkReceivedRequest request,
            AsyncAction<AsyncResult<MarkReceivedResult>> callback
    ) {
        MarkReceivedTask task = new MarkReceivedTask(request, callback);
        session.execute(task);
    }

    public MarkReceivedResult markReceived(
            MarkReceivedRequest request
    ) {
        final AsyncResult<MarkReceivedResult>[] resultAsyncResult = new AsyncResult[]{null};
        markReceivedAsync(
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

    class MarkReceivedByUserIdTask extends Gs2RestSessionTask<MarkReceivedByUserIdResult> {
        private MarkReceivedByUserIdRequest request;

        public MarkReceivedByUserIdTask(
            MarkReceivedByUserIdRequest request,
            AsyncAction<AsyncResult<MarkReceivedByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public MarkReceivedByUserIdResult parse(JsonNode data) {
            return MarkReceivedByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/receiveStatus/{bonusModelName}/mark";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stepNumber", request.getStepNumber());
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

    public void markReceivedByUserIdAsync(
            MarkReceivedByUserIdRequest request,
            AsyncAction<AsyncResult<MarkReceivedByUserIdResult>> callback
    ) {
        MarkReceivedByUserIdTask task = new MarkReceivedByUserIdTask(request, callback);
        session.execute(task);
    }

    public MarkReceivedByUserIdResult markReceivedByUserId(
            MarkReceivedByUserIdRequest request
    ) {
        final AsyncResult<MarkReceivedByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        markReceivedByUserIdAsync(
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

    class UnmarkReceivedByUserIdTask extends Gs2RestSessionTask<UnmarkReceivedByUserIdResult> {
        private UnmarkReceivedByUserIdRequest request;

        public UnmarkReceivedByUserIdTask(
            UnmarkReceivedByUserIdRequest request,
            AsyncAction<AsyncResult<UnmarkReceivedByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UnmarkReceivedByUserIdResult parse(JsonNode data) {
            return UnmarkReceivedByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/receiveStatus/{bonusModelName}/unmark";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{bonusModelName}", this.request.getBonusModelName() == null || this.request.getBonusModelName().length() == 0 ? "null" : String.valueOf(this.request.getBonusModelName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("stepNumber", request.getStepNumber());
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

    public void unmarkReceivedByUserIdAsync(
            UnmarkReceivedByUserIdRequest request,
            AsyncAction<AsyncResult<UnmarkReceivedByUserIdResult>> callback
    ) {
        UnmarkReceivedByUserIdTask task = new UnmarkReceivedByUserIdTask(request, callback);
        session.execute(task);
    }

    public UnmarkReceivedByUserIdResult unmarkReceivedByUserId(
            UnmarkReceivedByUserIdRequest request
    ) {
        final AsyncResult<UnmarkReceivedByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        unmarkReceivedByUserIdAsync(
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

    class MarkReceivedByStampTaskTask extends Gs2RestSessionTask<MarkReceivedByStampTaskResult> {
        private MarkReceivedByStampTaskRequest request;

        public MarkReceivedByStampTaskTask(
            MarkReceivedByStampTaskRequest request,
            AsyncAction<AsyncResult<MarkReceivedByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public MarkReceivedByStampTaskResult parse(JsonNode data) {
            return MarkReceivedByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/receiveStatus/mark";

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

    public void markReceivedByStampTaskAsync(
            MarkReceivedByStampTaskRequest request,
            AsyncAction<AsyncResult<MarkReceivedByStampTaskResult>> callback
    ) {
        MarkReceivedByStampTaskTask task = new MarkReceivedByStampTaskTask(request, callback);
        session.execute(task);
    }

    public MarkReceivedByStampTaskResult markReceivedByStampTask(
            MarkReceivedByStampTaskRequest request
    ) {
        final AsyncResult<MarkReceivedByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        markReceivedByStampTaskAsync(
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

    class UnmarkReceivedByStampSheetTask extends Gs2RestSessionTask<UnmarkReceivedByStampSheetResult> {
        private UnmarkReceivedByStampSheetRequest request;

        public UnmarkReceivedByStampSheetTask(
            UnmarkReceivedByStampSheetRequest request,
            AsyncAction<AsyncResult<UnmarkReceivedByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UnmarkReceivedByStampSheetResult parse(JsonNode data) {
            return UnmarkReceivedByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "login-reward")
                .replace("{region}", session.getRegion().getName())
                + "/receiveStatus/unmark";

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

    public void unmarkReceivedByStampSheetAsync(
            UnmarkReceivedByStampSheetRequest request,
            AsyncAction<AsyncResult<UnmarkReceivedByStampSheetResult>> callback
    ) {
        UnmarkReceivedByStampSheetTask task = new UnmarkReceivedByStampSheetTask(request, callback);
        session.execute(task);
    }

    public UnmarkReceivedByStampSheetResult unmarkReceivedByStampSheet(
            UnmarkReceivedByStampSheetRequest request
    ) {
        final AsyncResult<UnmarkReceivedByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        unmarkReceivedByStampSheetAsync(
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