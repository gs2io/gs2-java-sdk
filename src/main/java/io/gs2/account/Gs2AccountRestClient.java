
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

package io.gs2.account;

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
import io.gs2.account.request.*;
import io.gs2.account.result.*;
import io.gs2.account.model.*;public class Gs2AccountRestClient extends AbstractGs2Client<Gs2AccountRestClient> {

	public Gs2AccountRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("changePasswordIfTakeOver", request.getChangePasswordIfTakeOver());
                    put("differentUserIdForLoginAndDataRetention", request.getDifferentUserIdForLoginAndDataRetention());
                    put("createAccountScript", request.getCreateAccountScript() != null ? request.getCreateAccountScript().toJson() : null);
                    put("authenticationScript", request.getAuthenticationScript() != null ? request.getAuthenticationScript().toJson() : null);
                    put("createTakeOverScript", request.getCreateTakeOverScript() != null ? request.getCreateTakeOverScript().toJson() : null);
                    put("doTakeOverScript", request.getDoTakeOverScript() != null ? request.getDoTakeOverScript().toJson() : null);
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("changePasswordIfTakeOver", request.getChangePasswordIfTakeOver());
                    put("createAccountScript", request.getCreateAccountScript() != null ? request.getCreateAccountScript().toJson() : null);
                    put("authenticationScript", request.getAuthenticationScript() != null ? request.getAuthenticationScript().toJson() : null);
                    put("createTakeOverScript", request.getCreateTakeOverScript() != null ? request.getCreateTakeOverScript().toJson() : null);
                    put("doTakeOverScript", request.getDoTakeOverScript() != null ? request.getDoTakeOverScript().toJson() : null);
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
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

    class DescribeAccountsTask extends Gs2RestSessionTask<DescribeAccountsResult> {
        private DescribeAccountsRequest request;

        public DescribeAccountsTask(
            DescribeAccountsRequest request,
            AsyncAction<AsyncResult<DescribeAccountsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeAccountsResult parse(JsonNode data) {
            return DescribeAccountsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account";

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

    public void describeAccountsAsync(
            DescribeAccountsRequest request,
            AsyncAction<AsyncResult<DescribeAccountsResult>> callback
    ) {
        DescribeAccountsTask task = new DescribeAccountsTask(request, callback);
        session.execute(task);
    }

    public DescribeAccountsResult describeAccounts(
            DescribeAccountsRequest request
    ) {
        final AsyncResult<DescribeAccountsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeAccountsAsync(
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

    class CreateAccountTask extends Gs2RestSessionTask<CreateAccountResult> {
        private CreateAccountRequest request;

        public CreateAccountTask(
            CreateAccountRequest request,
            AsyncAction<AsyncResult<CreateAccountResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateAccountResult parse(JsonNode data) {
            return CreateAccountResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account";

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

    public void createAccountAsync(
            CreateAccountRequest request,
            AsyncAction<AsyncResult<CreateAccountResult>> callback
    ) {
        CreateAccountTask task = new CreateAccountTask(request, callback);
        session.execute(task);
    }

    public CreateAccountResult createAccount(
            CreateAccountRequest request
    ) {
        final AsyncResult<CreateAccountResult>[] resultAsyncResult = new AsyncResult[]{null};
        createAccountAsync(
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

    class UpdateTimeOffsetTask extends Gs2RestSessionTask<UpdateTimeOffsetResult> {
        private UpdateTimeOffsetRequest request;

        public UpdateTimeOffsetTask(
            UpdateTimeOffsetRequest request,
            AsyncAction<AsyncResult<UpdateTimeOffsetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateTimeOffsetResult parse(JsonNode data) {
            return UpdateTimeOffsetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/time_offset";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("timeOffset", request.getTimeOffset());
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

    public void updateTimeOffsetAsync(
            UpdateTimeOffsetRequest request,
            AsyncAction<AsyncResult<UpdateTimeOffsetResult>> callback
    ) {
        UpdateTimeOffsetTask task = new UpdateTimeOffsetTask(request, callback);
        session.execute(task);
    }

    public UpdateTimeOffsetResult updateTimeOffset(
            UpdateTimeOffsetRequest request
    ) {
        final AsyncResult<UpdateTimeOffsetResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateTimeOffsetAsync(
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

    class UpdateBannedTask extends Gs2RestSessionTask<UpdateBannedResult> {
        private UpdateBannedRequest request;

        public UpdateBannedTask(
            UpdateBannedRequest request,
            AsyncAction<AsyncResult<UpdateBannedResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateBannedResult parse(JsonNode data) {
            return UpdateBannedResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/banned";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("banned", request.getBanned());
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

    public void updateBannedAsync(
            UpdateBannedRequest request,
            AsyncAction<AsyncResult<UpdateBannedResult>> callback
    ) {
        UpdateBannedTask task = new UpdateBannedTask(request, callback);
        session.execute(task);
    }

    public UpdateBannedResult updateBanned(
            UpdateBannedRequest request
    ) {
        final AsyncResult<UpdateBannedResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateBannedAsync(
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

    class AddBanTask extends Gs2RestSessionTask<AddBanResult> {
        private AddBanRequest request;

        public AddBanTask(
            AddBanRequest request,
            AsyncAction<AsyncResult<AddBanResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddBanResult parse(JsonNode data) {
            return AddBanResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/ban";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("banStatus", request.getBanStatus() != null ? request.getBanStatus().toJson() : null);
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

    public void addBanAsync(
            AddBanRequest request,
            AsyncAction<AsyncResult<AddBanResult>> callback
    ) {
        AddBanTask task = new AddBanTask(request, callback);
        session.execute(task);
    }

    public AddBanResult addBan(
            AddBanRequest request
    ) {
        final AsyncResult<AddBanResult>[] resultAsyncResult = new AsyncResult[]{null};
        addBanAsync(
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

    class RemoveBanTask extends Gs2RestSessionTask<RemoveBanResult> {
        private RemoveBanRequest request;

        public RemoveBanTask(
            RemoveBanRequest request,
            AsyncAction<AsyncResult<RemoveBanResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RemoveBanResult parse(JsonNode data) {
            return RemoveBanResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/ban/{banStatusName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{banStatusName}", this.request.getBanStatusName() == null || this.request.getBanStatusName().length() == 0 ? "null" : String.valueOf(this.request.getBanStatusName()));

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

    public void removeBanAsync(
            RemoveBanRequest request,
            AsyncAction<AsyncResult<RemoveBanResult>> callback
    ) {
        RemoveBanTask task = new RemoveBanTask(request, callback);
        session.execute(task);
    }

    public RemoveBanResult removeBan(
            RemoveBanRequest request
    ) {
        final AsyncResult<RemoveBanResult>[] resultAsyncResult = new AsyncResult[]{null};
        removeBanAsync(
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

    class GetAccountTask extends Gs2RestSessionTask<GetAccountResult> {
        private GetAccountRequest request;

        public GetAccountTask(
            GetAccountRequest request,
            AsyncAction<AsyncResult<GetAccountResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetAccountResult parse(JsonNode data) {
            return GetAccountResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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

    public void getAccountAsync(
            GetAccountRequest request,
            AsyncAction<AsyncResult<GetAccountResult>> callback
    ) {
        GetAccountTask task = new GetAccountTask(request, callback);
        session.execute(task);
    }

    public GetAccountResult getAccount(
            GetAccountRequest request
    ) {
        final AsyncResult<GetAccountResult>[] resultAsyncResult = new AsyncResult[]{null};
        getAccountAsync(
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

    class DeleteAccountTask extends Gs2RestSessionTask<DeleteAccountResult> {
        private DeleteAccountRequest request;

        public DeleteAccountTask(
            DeleteAccountRequest request,
            AsyncAction<AsyncResult<DeleteAccountResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteAccountResult parse(JsonNode data) {
            return DeleteAccountResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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

    public void deleteAccountAsync(
            DeleteAccountRequest request,
            AsyncAction<AsyncResult<DeleteAccountResult>> callback
    ) {
        DeleteAccountTask task = new DeleteAccountTask(request, callback);
        session.execute(task);
    }

    public DeleteAccountResult deleteAccount(
            DeleteAccountRequest request
    ) {
        final AsyncResult<DeleteAccountResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteAccountAsync(
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

    class AuthenticationTask extends Gs2RestSessionTask<AuthenticationResult> {
        private AuthenticationRequest request;

        public AuthenticationTask(
            AuthenticationRequest request,
            AsyncAction<AsyncResult<AuthenticationResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AuthenticationResult parse(JsonNode data) {
            return AuthenticationResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("keyId", request.getKeyId());
                    put("password", request.getPassword());
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

    public void authenticationAsync(
            AuthenticationRequest request,
            AsyncAction<AsyncResult<AuthenticationResult>> callback
    ) {
        AuthenticationTask task = new AuthenticationTask(request, callback);
        session.execute(task);
    }

    public AuthenticationResult authentication(
            AuthenticationRequest request
    ) {
        final AsyncResult<AuthenticationResult>[] resultAsyncResult = new AsyncResult[]{null};
        authenticationAsync(
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

    class DescribeTakeOversTask extends Gs2RestSessionTask<DescribeTakeOversResult> {
        private DescribeTakeOversRequest request;

        public DescribeTakeOversTask(
            DescribeTakeOversRequest request,
            AsyncAction<AsyncResult<DescribeTakeOversResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeTakeOversResult parse(JsonNode data) {
            return DescribeTakeOversResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/takeover";

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

    public void describeTakeOversAsync(
            DescribeTakeOversRequest request,
            AsyncAction<AsyncResult<DescribeTakeOversResult>> callback
    ) {
        DescribeTakeOversTask task = new DescribeTakeOversTask(request, callback);
        session.execute(task);
    }

    public DescribeTakeOversResult describeTakeOvers(
            DescribeTakeOversRequest request
    ) {
        final AsyncResult<DescribeTakeOversResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeTakeOversAsync(
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

    class DescribeTakeOversByUserIdTask extends Gs2RestSessionTask<DescribeTakeOversByUserIdResult> {
        private DescribeTakeOversByUserIdRequest request;

        public DescribeTakeOversByUserIdTask(
            DescribeTakeOversByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeTakeOversByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeTakeOversByUserIdResult parse(JsonNode data) {
            return DescribeTakeOversByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/takeover";

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

    public void describeTakeOversByUserIdAsync(
            DescribeTakeOversByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeTakeOversByUserIdResult>> callback
    ) {
        DescribeTakeOversByUserIdTask task = new DescribeTakeOversByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeTakeOversByUserIdResult describeTakeOversByUserId(
            DescribeTakeOversByUserIdRequest request
    ) {
        final AsyncResult<DescribeTakeOversByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeTakeOversByUserIdAsync(
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

    class CreateTakeOverTask extends Gs2RestSessionTask<CreateTakeOverResult> {
        private CreateTakeOverRequest request;

        public CreateTakeOverTask(
            CreateTakeOverRequest request,
            AsyncAction<AsyncResult<CreateTakeOverResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateTakeOverResult parse(JsonNode data) {
            return CreateTakeOverResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/takeover";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("type", request.getType());
                    put("userIdentifier", request.getUserIdentifier());
                    put("password", request.getPassword());
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

    public void createTakeOverAsync(
            CreateTakeOverRequest request,
            AsyncAction<AsyncResult<CreateTakeOverResult>> callback
    ) {
        CreateTakeOverTask task = new CreateTakeOverTask(request, callback);
        session.execute(task);
    }

    public CreateTakeOverResult createTakeOver(
            CreateTakeOverRequest request
    ) {
        final AsyncResult<CreateTakeOverResult>[] resultAsyncResult = new AsyncResult[]{null};
        createTakeOverAsync(
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

    class CreateTakeOverByUserIdTask extends Gs2RestSessionTask<CreateTakeOverByUserIdResult> {
        private CreateTakeOverByUserIdRequest request;

        public CreateTakeOverByUserIdTask(
            CreateTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<CreateTakeOverByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateTakeOverByUserIdResult parse(JsonNode data) {
            return CreateTakeOverByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/takeover";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("type", request.getType());
                    put("userIdentifier", request.getUserIdentifier());
                    put("password", request.getPassword());
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

    public void createTakeOverByUserIdAsync(
            CreateTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<CreateTakeOverByUserIdResult>> callback
    ) {
        CreateTakeOverByUserIdTask task = new CreateTakeOverByUserIdTask(request, callback);
        session.execute(task);
    }

    public CreateTakeOverByUserIdResult createTakeOverByUserId(
            CreateTakeOverByUserIdRequest request
    ) {
        final AsyncResult<CreateTakeOverByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        createTakeOverByUserIdAsync(
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

    class CreateTakeOverOpenIdConnectTask extends Gs2RestSessionTask<CreateTakeOverOpenIdConnectResult> {
        private CreateTakeOverOpenIdConnectRequest request;

        public CreateTakeOverOpenIdConnectTask(
            CreateTakeOverOpenIdConnectRequest request,
            AsyncAction<AsyncResult<CreateTakeOverOpenIdConnectResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateTakeOverOpenIdConnectResult parse(JsonNode data) {
            return CreateTakeOverOpenIdConnectResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/takeover/openIdConnect";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("type", request.getType());
                    put("idToken", request.getIdToken());
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

    public void createTakeOverOpenIdConnectAsync(
            CreateTakeOverOpenIdConnectRequest request,
            AsyncAction<AsyncResult<CreateTakeOverOpenIdConnectResult>> callback
    ) {
        CreateTakeOverOpenIdConnectTask task = new CreateTakeOverOpenIdConnectTask(request, callback);
        session.execute(task);
    }

    public CreateTakeOverOpenIdConnectResult createTakeOverOpenIdConnect(
            CreateTakeOverOpenIdConnectRequest request
    ) {
        final AsyncResult<CreateTakeOverOpenIdConnectResult>[] resultAsyncResult = new AsyncResult[]{null};
        createTakeOverOpenIdConnectAsync(
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

    class CreateTakeOverOpenIdConnectAndByUserIdTask extends Gs2RestSessionTask<CreateTakeOverOpenIdConnectAndByUserIdResult> {
        private CreateTakeOverOpenIdConnectAndByUserIdRequest request;

        public CreateTakeOverOpenIdConnectAndByUserIdTask(
            CreateTakeOverOpenIdConnectAndByUserIdRequest request,
            AsyncAction<AsyncResult<CreateTakeOverOpenIdConnectAndByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateTakeOverOpenIdConnectAndByUserIdResult parse(JsonNode data) {
            return CreateTakeOverOpenIdConnectAndByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/takeover/openIdConnect";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("type", request.getType());
                    put("idToken", request.getIdToken());
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

    public void createTakeOverOpenIdConnectAndByUserIdAsync(
            CreateTakeOverOpenIdConnectAndByUserIdRequest request,
            AsyncAction<AsyncResult<CreateTakeOverOpenIdConnectAndByUserIdResult>> callback
    ) {
        CreateTakeOverOpenIdConnectAndByUserIdTask task = new CreateTakeOverOpenIdConnectAndByUserIdTask(request, callback);
        session.execute(task);
    }

    public CreateTakeOverOpenIdConnectAndByUserIdResult createTakeOverOpenIdConnectAndByUserId(
            CreateTakeOverOpenIdConnectAndByUserIdRequest request
    ) {
        final AsyncResult<CreateTakeOverOpenIdConnectAndByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        createTakeOverOpenIdConnectAndByUserIdAsync(
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

    class GetTakeOverTask extends Gs2RestSessionTask<GetTakeOverResult> {
        private GetTakeOverRequest request;

        public GetTakeOverTask(
            GetTakeOverRequest request,
            AsyncAction<AsyncResult<GetTakeOverResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetTakeOverResult parse(JsonNode data) {
            return GetTakeOverResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/takeover/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

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

    public void getTakeOverAsync(
            GetTakeOverRequest request,
            AsyncAction<AsyncResult<GetTakeOverResult>> callback
    ) {
        GetTakeOverTask task = new GetTakeOverTask(request, callback);
        session.execute(task);
    }

    public GetTakeOverResult getTakeOver(
            GetTakeOverRequest request
    ) {
        final AsyncResult<GetTakeOverResult>[] resultAsyncResult = new AsyncResult[]{null};
        getTakeOverAsync(
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

    class GetTakeOverByUserIdTask extends Gs2RestSessionTask<GetTakeOverByUserIdResult> {
        private GetTakeOverByUserIdRequest request;

        public GetTakeOverByUserIdTask(
            GetTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<GetTakeOverByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetTakeOverByUserIdResult parse(JsonNode data) {
            return GetTakeOverByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/takeover/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

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

    public void getTakeOverByUserIdAsync(
            GetTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<GetTakeOverByUserIdResult>> callback
    ) {
        GetTakeOverByUserIdTask task = new GetTakeOverByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetTakeOverByUserIdResult getTakeOverByUserId(
            GetTakeOverByUserIdRequest request
    ) {
        final AsyncResult<GetTakeOverByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getTakeOverByUserIdAsync(
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

    class UpdateTakeOverTask extends Gs2RestSessionTask<UpdateTakeOverResult> {
        private UpdateTakeOverRequest request;

        public UpdateTakeOverTask(
            UpdateTakeOverRequest request,
            AsyncAction<AsyncResult<UpdateTakeOverResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateTakeOverResult parse(JsonNode data) {
            return UpdateTakeOverResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/takeover/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("oldPassword", request.getOldPassword());
                    put("password", request.getPassword());
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

    public void updateTakeOverAsync(
            UpdateTakeOverRequest request,
            AsyncAction<AsyncResult<UpdateTakeOverResult>> callback
    ) {
        UpdateTakeOverTask task = new UpdateTakeOverTask(request, callback);
        session.execute(task);
    }

    public UpdateTakeOverResult updateTakeOver(
            UpdateTakeOverRequest request
    ) {
        final AsyncResult<UpdateTakeOverResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateTakeOverAsync(
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

    class UpdateTakeOverByUserIdTask extends Gs2RestSessionTask<UpdateTakeOverByUserIdResult> {
        private UpdateTakeOverByUserIdRequest request;

        public UpdateTakeOverByUserIdTask(
            UpdateTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateTakeOverByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateTakeOverByUserIdResult parse(JsonNode data) {
            return UpdateTakeOverByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/takeover/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("oldPassword", request.getOldPassword());
                    put("password", request.getPassword());
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

    public void updateTakeOverByUserIdAsync(
            UpdateTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateTakeOverByUserIdResult>> callback
    ) {
        UpdateTakeOverByUserIdTask task = new UpdateTakeOverByUserIdTask(request, callback);
        session.execute(task);
    }

    public UpdateTakeOverByUserIdResult updateTakeOverByUserId(
            UpdateTakeOverByUserIdRequest request
    ) {
        final AsyncResult<UpdateTakeOverByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateTakeOverByUserIdAsync(
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

    class DeleteTakeOverTask extends Gs2RestSessionTask<DeleteTakeOverResult> {
        private DeleteTakeOverRequest request;

        public DeleteTakeOverTask(
            DeleteTakeOverRequest request,
            AsyncAction<AsyncResult<DeleteTakeOverResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteTakeOverResult parse(JsonNode data) {
            return DeleteTakeOverResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/takeover/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

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

    public void deleteTakeOverAsync(
            DeleteTakeOverRequest request,
            AsyncAction<AsyncResult<DeleteTakeOverResult>> callback
    ) {
        DeleteTakeOverTask task = new DeleteTakeOverTask(request, callback);
        session.execute(task);
    }

    public DeleteTakeOverResult deleteTakeOver(
            DeleteTakeOverRequest request
    ) {
        final AsyncResult<DeleteTakeOverResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteTakeOverAsync(
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

    class DeleteTakeOverByUserIdentifierTask extends Gs2RestSessionTask<DeleteTakeOverByUserIdentifierResult> {
        private DeleteTakeOverByUserIdentifierRequest request;

        public DeleteTakeOverByUserIdentifierTask(
            DeleteTakeOverByUserIdentifierRequest request,
            AsyncAction<AsyncResult<DeleteTakeOverByUserIdentifierResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteTakeOverByUserIdentifierResult parse(JsonNode data) {
            return DeleteTakeOverByUserIdentifierResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/takeover/type/{type}/userIdentifier/{userIdentifier}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));
            url = url.replace("{userIdentifier}", this.request.getUserIdentifier() == null || this.request.getUserIdentifier().length() == 0 ? "null" : String.valueOf(this.request.getUserIdentifier()));

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

    public void deleteTakeOverByUserIdentifierAsync(
            DeleteTakeOverByUserIdentifierRequest request,
            AsyncAction<AsyncResult<DeleteTakeOverByUserIdentifierResult>> callback
    ) {
        DeleteTakeOverByUserIdentifierTask task = new DeleteTakeOverByUserIdentifierTask(request, callback);
        session.execute(task);
    }

    public DeleteTakeOverByUserIdentifierResult deleteTakeOverByUserIdentifier(
            DeleteTakeOverByUserIdentifierRequest request
    ) {
        final AsyncResult<DeleteTakeOverByUserIdentifierResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteTakeOverByUserIdentifierAsync(
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

    class DeleteTakeOverByUserIdTask extends Gs2RestSessionTask<DeleteTakeOverByUserIdResult> {
        private DeleteTakeOverByUserIdRequest request;

        public DeleteTakeOverByUserIdTask(
            DeleteTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteTakeOverByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteTakeOverByUserIdResult parse(JsonNode data) {
            return DeleteTakeOverByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/takeover/type/{type}/takeover";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

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

    public void deleteTakeOverByUserIdAsync(
            DeleteTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteTakeOverByUserIdResult>> callback
    ) {
        DeleteTakeOverByUserIdTask task = new DeleteTakeOverByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteTakeOverByUserIdResult deleteTakeOverByUserId(
            DeleteTakeOverByUserIdRequest request
    ) {
        final AsyncResult<DeleteTakeOverByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteTakeOverByUserIdAsync(
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

    class DoTakeOverTask extends Gs2RestSessionTask<DoTakeOverResult> {
        private DoTakeOverRequest request;

        public DoTakeOverTask(
            DoTakeOverRequest request,
            AsyncAction<AsyncResult<DoTakeOverResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DoTakeOverResult parse(JsonNode data) {
            return DoTakeOverResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/takeover/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userIdentifier", request.getUserIdentifier());
                    put("password", request.getPassword());
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

    public void doTakeOverAsync(
            DoTakeOverRequest request,
            AsyncAction<AsyncResult<DoTakeOverResult>> callback
    ) {
        DoTakeOverTask task = new DoTakeOverTask(request, callback);
        session.execute(task);
    }

    public DoTakeOverResult doTakeOver(
            DoTakeOverRequest request
    ) {
        final AsyncResult<DoTakeOverResult>[] resultAsyncResult = new AsyncResult[]{null};
        doTakeOverAsync(
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

    class DoTakeOverOpenIdConnectTask extends Gs2RestSessionTask<DoTakeOverOpenIdConnectResult> {
        private DoTakeOverOpenIdConnectRequest request;

        public DoTakeOverOpenIdConnectTask(
            DoTakeOverOpenIdConnectRequest request,
            AsyncAction<AsyncResult<DoTakeOverOpenIdConnectResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DoTakeOverOpenIdConnectResult parse(JsonNode data) {
            return DoTakeOverOpenIdConnectResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/takeover/type/{type}/openIdConnect";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("idToken", request.getIdToken());
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

    public void doTakeOverOpenIdConnectAsync(
            DoTakeOverOpenIdConnectRequest request,
            AsyncAction<AsyncResult<DoTakeOverOpenIdConnectResult>> callback
    ) {
        DoTakeOverOpenIdConnectTask task = new DoTakeOverOpenIdConnectTask(request, callback);
        session.execute(task);
    }

    public DoTakeOverOpenIdConnectResult doTakeOverOpenIdConnect(
            DoTakeOverOpenIdConnectRequest request
    ) {
        final AsyncResult<DoTakeOverOpenIdConnectResult>[] resultAsyncResult = new AsyncResult[]{null};
        doTakeOverOpenIdConnectAsync(
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

    class GetAuthorizationUrlTask extends Gs2RestSessionTask<GetAuthorizationUrlResult> {
        private GetAuthorizationUrlRequest request;

        public GetAuthorizationUrlTask(
            GetAuthorizationUrlRequest request,
            AsyncAction<AsyncResult<GetAuthorizationUrlResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetAuthorizationUrlResult parse(JsonNode data) {
            return GetAuthorizationUrlResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/type/{type}/authorization/url";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

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

    public void getAuthorizationUrlAsync(
            GetAuthorizationUrlRequest request,
            AsyncAction<AsyncResult<GetAuthorizationUrlResult>> callback
    ) {
        GetAuthorizationUrlTask task = new GetAuthorizationUrlTask(request, callback);
        session.execute(task);
    }

    public GetAuthorizationUrlResult getAuthorizationUrl(
            GetAuthorizationUrlRequest request
    ) {
        final AsyncResult<GetAuthorizationUrlResult>[] resultAsyncResult = new AsyncResult[]{null};
        getAuthorizationUrlAsync(
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

    class DescribePlatformIdsTask extends Gs2RestSessionTask<DescribePlatformIdsResult> {
        private DescribePlatformIdsRequest request;

        public DescribePlatformIdsTask(
            DescribePlatformIdsRequest request,
            AsyncAction<AsyncResult<DescribePlatformIdsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribePlatformIdsResult parse(JsonNode data) {
            return DescribePlatformIdsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/platformId";

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

    public void describePlatformIdsAsync(
            DescribePlatformIdsRequest request,
            AsyncAction<AsyncResult<DescribePlatformIdsResult>> callback
    ) {
        DescribePlatformIdsTask task = new DescribePlatformIdsTask(request, callback);
        session.execute(task);
    }

    public DescribePlatformIdsResult describePlatformIds(
            DescribePlatformIdsRequest request
    ) {
        final AsyncResult<DescribePlatformIdsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describePlatformIdsAsync(
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

    class DescribePlatformIdsByUserIdTask extends Gs2RestSessionTask<DescribePlatformIdsByUserIdResult> {
        private DescribePlatformIdsByUserIdRequest request;

        public DescribePlatformIdsByUserIdTask(
            DescribePlatformIdsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribePlatformIdsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribePlatformIdsByUserIdResult parse(JsonNode data) {
            return DescribePlatformIdsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/platformId";

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

    public void describePlatformIdsByUserIdAsync(
            DescribePlatformIdsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribePlatformIdsByUserIdResult>> callback
    ) {
        DescribePlatformIdsByUserIdTask task = new DescribePlatformIdsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribePlatformIdsByUserIdResult describePlatformIdsByUserId(
            DescribePlatformIdsByUserIdRequest request
    ) {
        final AsyncResult<DescribePlatformIdsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describePlatformIdsByUserIdAsync(
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

    class CreatePlatformIdTask extends Gs2RestSessionTask<CreatePlatformIdResult> {
        private CreatePlatformIdRequest request;

        public CreatePlatformIdTask(
            CreatePlatformIdRequest request,
            AsyncAction<AsyncResult<CreatePlatformIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreatePlatformIdResult parse(JsonNode data) {
            return CreatePlatformIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/platformId";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("type", request.getType());
                    put("userIdentifier", request.getUserIdentifier());
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

    public void createPlatformIdAsync(
            CreatePlatformIdRequest request,
            AsyncAction<AsyncResult<CreatePlatformIdResult>> callback
    ) {
        CreatePlatformIdTask task = new CreatePlatformIdTask(request, callback);
        session.execute(task);
    }

    public CreatePlatformIdResult createPlatformId(
            CreatePlatformIdRequest request
    ) {
        final AsyncResult<CreatePlatformIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        createPlatformIdAsync(
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

    class CreatePlatformIdByUserIdTask extends Gs2RestSessionTask<CreatePlatformIdByUserIdResult> {
        private CreatePlatformIdByUserIdRequest request;

        public CreatePlatformIdByUserIdTask(
            CreatePlatformIdByUserIdRequest request,
            AsyncAction<AsyncResult<CreatePlatformIdByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreatePlatformIdByUserIdResult parse(JsonNode data) {
            return CreatePlatformIdByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/platformId";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("type", request.getType());
                    put("userIdentifier", request.getUserIdentifier());
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

    public void createPlatformIdByUserIdAsync(
            CreatePlatformIdByUserIdRequest request,
            AsyncAction<AsyncResult<CreatePlatformIdByUserIdResult>> callback
    ) {
        CreatePlatformIdByUserIdTask task = new CreatePlatformIdByUserIdTask(request, callback);
        session.execute(task);
    }

    public CreatePlatformIdByUserIdResult createPlatformIdByUserId(
            CreatePlatformIdByUserIdRequest request
    ) {
        final AsyncResult<CreatePlatformIdByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        createPlatformIdByUserIdAsync(
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

    class GetPlatformIdTask extends Gs2RestSessionTask<GetPlatformIdResult> {
        private GetPlatformIdRequest request;

        public GetPlatformIdTask(
            GetPlatformIdRequest request,
            AsyncAction<AsyncResult<GetPlatformIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPlatformIdResult parse(JsonNode data) {
            return GetPlatformIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/platformId/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

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

    public void getPlatformIdAsync(
            GetPlatformIdRequest request,
            AsyncAction<AsyncResult<GetPlatformIdResult>> callback
    ) {
        GetPlatformIdTask task = new GetPlatformIdTask(request, callback);
        session.execute(task);
    }

    public GetPlatformIdResult getPlatformId(
            GetPlatformIdRequest request
    ) {
        final AsyncResult<GetPlatformIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPlatformIdAsync(
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

    class GetPlatformIdByUserIdTask extends Gs2RestSessionTask<GetPlatformIdByUserIdResult> {
        private GetPlatformIdByUserIdRequest request;

        public GetPlatformIdByUserIdTask(
            GetPlatformIdByUserIdRequest request,
            AsyncAction<AsyncResult<GetPlatformIdByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPlatformIdByUserIdResult parse(JsonNode data) {
            return GetPlatformIdByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/platformId/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

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

    public void getPlatformIdByUserIdAsync(
            GetPlatformIdByUserIdRequest request,
            AsyncAction<AsyncResult<GetPlatformIdByUserIdResult>> callback
    ) {
        GetPlatformIdByUserIdTask task = new GetPlatformIdByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetPlatformIdByUserIdResult getPlatformIdByUserId(
            GetPlatformIdByUserIdRequest request
    ) {
        final AsyncResult<GetPlatformIdByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPlatformIdByUserIdAsync(
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

    class FindPlatformIdTask extends Gs2RestSessionTask<FindPlatformIdResult> {
        private FindPlatformIdRequest request;

        public FindPlatformIdTask(
            FindPlatformIdRequest request,
            AsyncAction<AsyncResult<FindPlatformIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public FindPlatformIdResult parse(JsonNode data) {
            return FindPlatformIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/platformId/type/{type}/userIdentifier/{userIdentifier}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));
            url = url.replace("{userIdentifier}", this.request.getUserIdentifier() == null || this.request.getUserIdentifier().length() == 0 ? "null" : String.valueOf(this.request.getUserIdentifier()));

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    public void findPlatformIdAsync(
            FindPlatformIdRequest request,
            AsyncAction<AsyncResult<FindPlatformIdResult>> callback
    ) {
        FindPlatformIdTask task = new FindPlatformIdTask(request, callback);
        session.execute(task);
    }

    public FindPlatformIdResult findPlatformId(
            FindPlatformIdRequest request
    ) {
        final AsyncResult<FindPlatformIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        findPlatformIdAsync(
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

    class FindPlatformIdByUserIdTask extends Gs2RestSessionTask<FindPlatformIdByUserIdResult> {
        private FindPlatformIdByUserIdRequest request;

        public FindPlatformIdByUserIdTask(
            FindPlatformIdByUserIdRequest request,
            AsyncAction<AsyncResult<FindPlatformIdByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public FindPlatformIdByUserIdResult parse(JsonNode data) {
            return FindPlatformIdByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/platformId/type/{type}/userIdentifier/{userIdentifier}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));
            url = url.replace("{userIdentifier}", this.request.getUserIdentifier() == null || this.request.getUserIdentifier().length() == 0 ? "null" : String.valueOf(this.request.getUserIdentifier()));

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

    public void findPlatformIdByUserIdAsync(
            FindPlatformIdByUserIdRequest request,
            AsyncAction<AsyncResult<FindPlatformIdByUserIdResult>> callback
    ) {
        FindPlatformIdByUserIdTask task = new FindPlatformIdByUserIdTask(request, callback);
        session.execute(task);
    }

    public FindPlatformIdByUserIdResult findPlatformIdByUserId(
            FindPlatformIdByUserIdRequest request
    ) {
        final AsyncResult<FindPlatformIdByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        findPlatformIdByUserIdAsync(
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

    class DeletePlatformIdTask extends Gs2RestSessionTask<DeletePlatformIdResult> {
        private DeletePlatformIdRequest request;

        public DeletePlatformIdTask(
            DeletePlatformIdRequest request,
            AsyncAction<AsyncResult<DeletePlatformIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeletePlatformIdResult parse(JsonNode data) {
            return DeletePlatformIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/platformId/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getUserIdentifier() != null) {
                queryStrings.add("userIdentifier=" + EncodingUtil.urlEncode((String.valueOf(this.request.getUserIdentifier()))));
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

    public void deletePlatformIdAsync(
            DeletePlatformIdRequest request,
            AsyncAction<AsyncResult<DeletePlatformIdResult>> callback
    ) {
        DeletePlatformIdTask task = new DeletePlatformIdTask(request, callback);
        session.execute(task);
    }

    public DeletePlatformIdResult deletePlatformId(
            DeletePlatformIdRequest request
    ) {
        final AsyncResult<DeletePlatformIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deletePlatformIdAsync(
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

    class DeletePlatformIdByUserIdentifierTask extends Gs2RestSessionTask<DeletePlatformIdByUserIdentifierResult> {
        private DeletePlatformIdByUserIdentifierRequest request;

        public DeletePlatformIdByUserIdentifierTask(
            DeletePlatformIdByUserIdentifierRequest request,
            AsyncAction<AsyncResult<DeletePlatformIdByUserIdentifierResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeletePlatformIdByUserIdentifierResult parse(JsonNode data) {
            return DeletePlatformIdByUserIdentifierResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/platformId/type/{type}/userIdentifier/{userIdentifier}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));
            url = url.replace("{userIdentifier}", this.request.getUserIdentifier() == null || this.request.getUserIdentifier().length() == 0 ? "null" : String.valueOf(this.request.getUserIdentifier()));

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

    public void deletePlatformIdByUserIdentifierAsync(
            DeletePlatformIdByUserIdentifierRequest request,
            AsyncAction<AsyncResult<DeletePlatformIdByUserIdentifierResult>> callback
    ) {
        DeletePlatformIdByUserIdentifierTask task = new DeletePlatformIdByUserIdentifierTask(request, callback);
        session.execute(task);
    }

    public DeletePlatformIdByUserIdentifierResult deletePlatformIdByUserIdentifier(
            DeletePlatformIdByUserIdentifierRequest request
    ) {
        final AsyncResult<DeletePlatformIdByUserIdentifierResult>[] resultAsyncResult = new AsyncResult[]{null};
        deletePlatformIdByUserIdentifierAsync(
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

    class DeletePlatformIdByUserIdTask extends Gs2RestSessionTask<DeletePlatformIdByUserIdResult> {
        private DeletePlatformIdByUserIdRequest request;

        public DeletePlatformIdByUserIdTask(
            DeletePlatformIdByUserIdRequest request,
            AsyncAction<AsyncResult<DeletePlatformIdByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeletePlatformIdByUserIdResult parse(JsonNode data) {
            return DeletePlatformIdByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/platformId/type/{type}/platformId";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

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

    public void deletePlatformIdByUserIdAsync(
            DeletePlatformIdByUserIdRequest request,
            AsyncAction<AsyncResult<DeletePlatformIdByUserIdResult>> callback
    ) {
        DeletePlatformIdByUserIdTask task = new DeletePlatformIdByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeletePlatformIdByUserIdResult deletePlatformIdByUserId(
            DeletePlatformIdByUserIdRequest request
    ) {
        final AsyncResult<DeletePlatformIdByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deletePlatformIdByUserIdAsync(
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

    class GetDataOwnerByUserIdTask extends Gs2RestSessionTask<GetDataOwnerByUserIdResult> {
        private GetDataOwnerByUserIdRequest request;

        public GetDataOwnerByUserIdTask(
            GetDataOwnerByUserIdRequest request,
            AsyncAction<AsyncResult<GetDataOwnerByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetDataOwnerByUserIdResult parse(JsonNode data) {
            return GetDataOwnerByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/dataOwner";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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

    public void getDataOwnerByUserIdAsync(
            GetDataOwnerByUserIdRequest request,
            AsyncAction<AsyncResult<GetDataOwnerByUserIdResult>> callback
    ) {
        GetDataOwnerByUserIdTask task = new GetDataOwnerByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetDataOwnerByUserIdResult getDataOwnerByUserId(
            GetDataOwnerByUserIdRequest request
    ) {
        final AsyncResult<GetDataOwnerByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getDataOwnerByUserIdAsync(
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

    class DeleteDataOwnerByUserIdTask extends Gs2RestSessionTask<DeleteDataOwnerByUserIdResult> {
        private DeleteDataOwnerByUserIdRequest request;

        public DeleteDataOwnerByUserIdTask(
            DeleteDataOwnerByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteDataOwnerByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteDataOwnerByUserIdResult parse(JsonNode data) {
            return DeleteDataOwnerByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/dataOwner";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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

    public void deleteDataOwnerByUserIdAsync(
            DeleteDataOwnerByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteDataOwnerByUserIdResult>> callback
    ) {
        DeleteDataOwnerByUserIdTask task = new DeleteDataOwnerByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteDataOwnerByUserIdResult deleteDataOwnerByUserId(
            DeleteDataOwnerByUserIdRequest request
    ) {
        final AsyncResult<DeleteDataOwnerByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteDataOwnerByUserIdAsync(
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

    class DescribeTakeOverTypeModelsTask extends Gs2RestSessionTask<DescribeTakeOverTypeModelsResult> {
        private DescribeTakeOverTypeModelsRequest request;

        public DescribeTakeOverTypeModelsTask(
            DescribeTakeOverTypeModelsRequest request,
            AsyncAction<AsyncResult<DescribeTakeOverTypeModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeTakeOverTypeModelsResult parse(JsonNode data) {
            return DescribeTakeOverTypeModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
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

    public void describeTakeOverTypeModelsAsync(
            DescribeTakeOverTypeModelsRequest request,
            AsyncAction<AsyncResult<DescribeTakeOverTypeModelsResult>> callback
    ) {
        DescribeTakeOverTypeModelsTask task = new DescribeTakeOverTypeModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeTakeOverTypeModelsResult describeTakeOverTypeModels(
            DescribeTakeOverTypeModelsRequest request
    ) {
        final AsyncResult<DescribeTakeOverTypeModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeTakeOverTypeModelsAsync(
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

    class GetTakeOverTypeModelTask extends Gs2RestSessionTask<GetTakeOverTypeModelResult> {
        private GetTakeOverTypeModelRequest request;

        public GetTakeOverTypeModelTask(
            GetTakeOverTypeModelRequest request,
            AsyncAction<AsyncResult<GetTakeOverTypeModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetTakeOverTypeModelResult parse(JsonNode data) {
            return GetTakeOverTypeModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

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

    public void getTakeOverTypeModelAsync(
            GetTakeOverTypeModelRequest request,
            AsyncAction<AsyncResult<GetTakeOverTypeModelResult>> callback
    ) {
        GetTakeOverTypeModelTask task = new GetTakeOverTypeModelTask(request, callback);
        session.execute(task);
    }

    public GetTakeOverTypeModelResult getTakeOverTypeModel(
            GetTakeOverTypeModelRequest request
    ) {
        final AsyncResult<GetTakeOverTypeModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getTakeOverTypeModelAsync(
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

    class DescribeTakeOverTypeModelMastersTask extends Gs2RestSessionTask<DescribeTakeOverTypeModelMastersResult> {
        private DescribeTakeOverTypeModelMastersRequest request;

        public DescribeTakeOverTypeModelMastersTask(
            DescribeTakeOverTypeModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeTakeOverTypeModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeTakeOverTypeModelMastersResult parse(JsonNode data) {
            return DescribeTakeOverTypeModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
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

    public void describeTakeOverTypeModelMastersAsync(
            DescribeTakeOverTypeModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeTakeOverTypeModelMastersResult>> callback
    ) {
        DescribeTakeOverTypeModelMastersTask task = new DescribeTakeOverTypeModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeTakeOverTypeModelMastersResult describeTakeOverTypeModelMasters(
            DescribeTakeOverTypeModelMastersRequest request
    ) {
        final AsyncResult<DescribeTakeOverTypeModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeTakeOverTypeModelMastersAsync(
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

    class CreateTakeOverTypeModelMasterTask extends Gs2RestSessionTask<CreateTakeOverTypeModelMasterResult> {
        private CreateTakeOverTypeModelMasterRequest request;

        public CreateTakeOverTypeModelMasterTask(
            CreateTakeOverTypeModelMasterRequest request,
            AsyncAction<AsyncResult<CreateTakeOverTypeModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateTakeOverTypeModelMasterResult parse(JsonNode data) {
            return CreateTakeOverTypeModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("type", request.getType());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("openIdConnectSetting", request.getOpenIdConnectSetting() != null ? request.getOpenIdConnectSetting().toJson() : null);
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

    public void createTakeOverTypeModelMasterAsync(
            CreateTakeOverTypeModelMasterRequest request,
            AsyncAction<AsyncResult<CreateTakeOverTypeModelMasterResult>> callback
    ) {
        CreateTakeOverTypeModelMasterTask task = new CreateTakeOverTypeModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateTakeOverTypeModelMasterResult createTakeOverTypeModelMaster(
            CreateTakeOverTypeModelMasterRequest request
    ) {
        final AsyncResult<CreateTakeOverTypeModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createTakeOverTypeModelMasterAsync(
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

    class GetTakeOverTypeModelMasterTask extends Gs2RestSessionTask<GetTakeOverTypeModelMasterResult> {
        private GetTakeOverTypeModelMasterRequest request;

        public GetTakeOverTypeModelMasterTask(
            GetTakeOverTypeModelMasterRequest request,
            AsyncAction<AsyncResult<GetTakeOverTypeModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetTakeOverTypeModelMasterResult parse(JsonNode data) {
            return GetTakeOverTypeModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

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

    public void getTakeOverTypeModelMasterAsync(
            GetTakeOverTypeModelMasterRequest request,
            AsyncAction<AsyncResult<GetTakeOverTypeModelMasterResult>> callback
    ) {
        GetTakeOverTypeModelMasterTask task = new GetTakeOverTypeModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetTakeOverTypeModelMasterResult getTakeOverTypeModelMaster(
            GetTakeOverTypeModelMasterRequest request
    ) {
        final AsyncResult<GetTakeOverTypeModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getTakeOverTypeModelMasterAsync(
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

    class UpdateTakeOverTypeModelMasterTask extends Gs2RestSessionTask<UpdateTakeOverTypeModelMasterResult> {
        private UpdateTakeOverTypeModelMasterRequest request;

        public UpdateTakeOverTypeModelMasterTask(
            UpdateTakeOverTypeModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateTakeOverTypeModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateTakeOverTypeModelMasterResult parse(JsonNode data) {
            return UpdateTakeOverTypeModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("openIdConnectSetting", request.getOpenIdConnectSetting() != null ? request.getOpenIdConnectSetting().toJson() : null);
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

    public void updateTakeOverTypeModelMasterAsync(
            UpdateTakeOverTypeModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateTakeOverTypeModelMasterResult>> callback
    ) {
        UpdateTakeOverTypeModelMasterTask task = new UpdateTakeOverTypeModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateTakeOverTypeModelMasterResult updateTakeOverTypeModelMaster(
            UpdateTakeOverTypeModelMasterRequest request
    ) {
        final AsyncResult<UpdateTakeOverTypeModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateTakeOverTypeModelMasterAsync(
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

    class DeleteTakeOverTypeModelMasterTask extends Gs2RestSessionTask<DeleteTakeOverTypeModelMasterResult> {
        private DeleteTakeOverTypeModelMasterRequest request;

        public DeleteTakeOverTypeModelMasterTask(
            DeleteTakeOverTypeModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteTakeOverTypeModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteTakeOverTypeModelMasterResult parse(JsonNode data) {
            return DeleteTakeOverTypeModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null  ? "null" : String.valueOf(this.request.getType()));

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

    public void deleteTakeOverTypeModelMasterAsync(
            DeleteTakeOverTypeModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteTakeOverTypeModelMasterResult>> callback
    ) {
        DeleteTakeOverTypeModelMasterTask task = new DeleteTakeOverTypeModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteTakeOverTypeModelMasterResult deleteTakeOverTypeModelMaster(
            DeleteTakeOverTypeModelMasterRequest request
    ) {
        final AsyncResult<DeleteTakeOverTypeModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteTakeOverTypeModelMasterAsync(
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
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

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
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
                .replace("{service}", "account")
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
}