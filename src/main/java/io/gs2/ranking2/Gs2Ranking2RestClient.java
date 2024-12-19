
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

package io.gs2.ranking2;

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
import io.gs2.ranking2.request.*;
import io.gs2.ranking2.result.*;
import io.gs2.ranking2.model.*;public class Gs2Ranking2RestClient extends AbstractGs2Client<Gs2Ranking2RestClient> {

	public Gs2Ranking2RestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "ranking2")
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
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
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
                .replace("{service}", "ranking2")
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
                .replace("{service}", "ranking2")
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
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
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
                .replace("{service}", "ranking2")
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
                .replace("{service}", "ranking2")
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
                .replace("{service}", "ranking2")
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
                .replace("{service}", "ranking2")
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
                .replace("{service}", "ranking2")
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
                .replace("{service}", "ranking2")
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
                .replace("{service}", "ranking2")
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
                .replace("{service}", "ranking2")
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

    class DescribeGlobalRankingModelsTask extends Gs2RestSessionTask<DescribeGlobalRankingModelsResult> {
        private DescribeGlobalRankingModelsRequest request;

        public DescribeGlobalRankingModelsTask(
            DescribeGlobalRankingModelsRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeGlobalRankingModelsResult parse(JsonNode data) {
            return DescribeGlobalRankingModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/global";

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

    public void describeGlobalRankingModelsAsync(
            DescribeGlobalRankingModelsRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingModelsResult>> callback
    ) {
        DescribeGlobalRankingModelsTask task = new DescribeGlobalRankingModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeGlobalRankingModelsResult describeGlobalRankingModels(
            DescribeGlobalRankingModelsRequest request
    ) {
        final AsyncResult<DescribeGlobalRankingModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeGlobalRankingModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetGlobalRankingModelTask extends Gs2RestSessionTask<GetGlobalRankingModelResult> {
        private GetGlobalRankingModelRequest request;

        public GetGlobalRankingModelTask(
            GetGlobalRankingModelRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetGlobalRankingModelResult parse(JsonNode data) {
            return GetGlobalRankingModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/global/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

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

    public void getGlobalRankingModelAsync(
            GetGlobalRankingModelRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingModelResult>> callback
    ) {
        GetGlobalRankingModelTask task = new GetGlobalRankingModelTask(request, callback);
        session.execute(task);
    }

    public GetGlobalRankingModelResult getGlobalRankingModel(
            GetGlobalRankingModelRequest request
    ) {
        final AsyncResult<GetGlobalRankingModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGlobalRankingModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeGlobalRankingModelMastersTask extends Gs2RestSessionTask<DescribeGlobalRankingModelMastersResult> {
        private DescribeGlobalRankingModelMastersRequest request;

        public DescribeGlobalRankingModelMastersTask(
            DescribeGlobalRankingModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeGlobalRankingModelMastersResult parse(JsonNode data) {
            return DescribeGlobalRankingModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/global";

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

    public void describeGlobalRankingModelMastersAsync(
            DescribeGlobalRankingModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingModelMastersResult>> callback
    ) {
        DescribeGlobalRankingModelMastersTask task = new DescribeGlobalRankingModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeGlobalRankingModelMastersResult describeGlobalRankingModelMasters(
            DescribeGlobalRankingModelMastersRequest request
    ) {
        final AsyncResult<DescribeGlobalRankingModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeGlobalRankingModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateGlobalRankingModelMasterTask extends Gs2RestSessionTask<CreateGlobalRankingModelMasterResult> {
        private CreateGlobalRankingModelMasterRequest request;

        public CreateGlobalRankingModelMasterTask(
            CreateGlobalRankingModelMasterRequest request,
            AsyncAction<AsyncResult<CreateGlobalRankingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateGlobalRankingModelMasterResult parse(JsonNode data) {
            return CreateGlobalRankingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/global";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("minimumValue", request.getMinimumValue());
                    put("maximumValue", request.getMaximumValue());
                    put("sum", request.getSum());
                    put("orderDirection", request.getOrderDirection());
                    put("rankingRewards", request.getRankingRewards() == null ? null :
                        request.getRankingRewards().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("rewardCalculationIndex", request.getRewardCalculationIndex());
                    put("entryPeriodEventId", request.getEntryPeriodEventId());
                    put("accessPeriodEventId", request.getAccessPeriodEventId());
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

    public void createGlobalRankingModelMasterAsync(
            CreateGlobalRankingModelMasterRequest request,
            AsyncAction<AsyncResult<CreateGlobalRankingModelMasterResult>> callback
    ) {
        CreateGlobalRankingModelMasterTask task = new CreateGlobalRankingModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateGlobalRankingModelMasterResult createGlobalRankingModelMaster(
            CreateGlobalRankingModelMasterRequest request
    ) {
        final AsyncResult<CreateGlobalRankingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createGlobalRankingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetGlobalRankingModelMasterTask extends Gs2RestSessionTask<GetGlobalRankingModelMasterResult> {
        private GetGlobalRankingModelMasterRequest request;

        public GetGlobalRankingModelMasterTask(
            GetGlobalRankingModelMasterRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetGlobalRankingModelMasterResult parse(JsonNode data) {
            return GetGlobalRankingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/global/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

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

    public void getGlobalRankingModelMasterAsync(
            GetGlobalRankingModelMasterRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingModelMasterResult>> callback
    ) {
        GetGlobalRankingModelMasterTask task = new GetGlobalRankingModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetGlobalRankingModelMasterResult getGlobalRankingModelMaster(
            GetGlobalRankingModelMasterRequest request
    ) {
        final AsyncResult<GetGlobalRankingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGlobalRankingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateGlobalRankingModelMasterTask extends Gs2RestSessionTask<UpdateGlobalRankingModelMasterResult> {
        private UpdateGlobalRankingModelMasterRequest request;

        public UpdateGlobalRankingModelMasterTask(
            UpdateGlobalRankingModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateGlobalRankingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateGlobalRankingModelMasterResult parse(JsonNode data) {
            return UpdateGlobalRankingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/global/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("minimumValue", request.getMinimumValue());
                    put("maximumValue", request.getMaximumValue());
                    put("sum", request.getSum());
                    put("orderDirection", request.getOrderDirection());
                    put("rankingRewards", request.getRankingRewards() == null ? null :
                        request.getRankingRewards().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("rewardCalculationIndex", request.getRewardCalculationIndex());
                    put("entryPeriodEventId", request.getEntryPeriodEventId());
                    put("accessPeriodEventId", request.getAccessPeriodEventId());
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

    public void updateGlobalRankingModelMasterAsync(
            UpdateGlobalRankingModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateGlobalRankingModelMasterResult>> callback
    ) {
        UpdateGlobalRankingModelMasterTask task = new UpdateGlobalRankingModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateGlobalRankingModelMasterResult updateGlobalRankingModelMaster(
            UpdateGlobalRankingModelMasterRequest request
    ) {
        final AsyncResult<UpdateGlobalRankingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateGlobalRankingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteGlobalRankingModelMasterTask extends Gs2RestSessionTask<DeleteGlobalRankingModelMasterResult> {
        private DeleteGlobalRankingModelMasterRequest request;

        public DeleteGlobalRankingModelMasterTask(
            DeleteGlobalRankingModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteGlobalRankingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteGlobalRankingModelMasterResult parse(JsonNode data) {
            return DeleteGlobalRankingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/global/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

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

    public void deleteGlobalRankingModelMasterAsync(
            DeleteGlobalRankingModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteGlobalRankingModelMasterResult>> callback
    ) {
        DeleteGlobalRankingModelMasterTask task = new DeleteGlobalRankingModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteGlobalRankingModelMasterResult deleteGlobalRankingModelMaster(
            DeleteGlobalRankingModelMasterRequest request
    ) {
        final AsyncResult<DeleteGlobalRankingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteGlobalRankingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeGlobalRankingScoresTask extends Gs2RestSessionTask<DescribeGlobalRankingScoresResult> {
        private DescribeGlobalRankingScoresRequest request;

        public DescribeGlobalRankingScoresTask(
            DescribeGlobalRankingScoresRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingScoresResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeGlobalRankingScoresResult parse(JsonNode data) {
            return DescribeGlobalRankingScoresResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/score/global";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRankingName() != null) {
                queryStrings.add("rankingName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRankingName()))));
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

    public void describeGlobalRankingScoresAsync(
            DescribeGlobalRankingScoresRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingScoresResult>> callback
    ) {
        DescribeGlobalRankingScoresTask task = new DescribeGlobalRankingScoresTask(request, callback);
        session.execute(task);
    }

    public DescribeGlobalRankingScoresResult describeGlobalRankingScores(
            DescribeGlobalRankingScoresRequest request
    ) {
        final AsyncResult<DescribeGlobalRankingScoresResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeGlobalRankingScoresAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeGlobalRankingScoresByUserIdTask extends Gs2RestSessionTask<DescribeGlobalRankingScoresByUserIdResult> {
        private DescribeGlobalRankingScoresByUserIdRequest request;

        public DescribeGlobalRankingScoresByUserIdTask(
            DescribeGlobalRankingScoresByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingScoresByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeGlobalRankingScoresByUserIdResult parse(JsonNode data) {
            return DescribeGlobalRankingScoresByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/global";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRankingName() != null) {
                queryStrings.add("rankingName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRankingName()))));
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

    public void describeGlobalRankingScoresByUserIdAsync(
            DescribeGlobalRankingScoresByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingScoresByUserIdResult>> callback
    ) {
        DescribeGlobalRankingScoresByUserIdTask task = new DescribeGlobalRankingScoresByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeGlobalRankingScoresByUserIdResult describeGlobalRankingScoresByUserId(
            DescribeGlobalRankingScoresByUserIdRequest request
    ) {
        final AsyncResult<DescribeGlobalRankingScoresByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeGlobalRankingScoresByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PutGlobalRankingScoreTask extends Gs2RestSessionTask<PutGlobalRankingScoreResult> {
        private PutGlobalRankingScoreRequest request;

        public PutGlobalRankingScoreTask(
            PutGlobalRankingScoreRequest request,
            AsyncAction<AsyncResult<PutGlobalRankingScoreResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PutGlobalRankingScoreResult parse(JsonNode data) {
            return PutGlobalRankingScoreResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/score/global/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("score", request.getScore());
                    put("metadata", request.getMetadata());
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

    public void putGlobalRankingScoreAsync(
            PutGlobalRankingScoreRequest request,
            AsyncAction<AsyncResult<PutGlobalRankingScoreResult>> callback
    ) {
        PutGlobalRankingScoreTask task = new PutGlobalRankingScoreTask(request, callback);
        session.execute(task);
    }

    public PutGlobalRankingScoreResult putGlobalRankingScore(
            PutGlobalRankingScoreRequest request
    ) {
        final AsyncResult<PutGlobalRankingScoreResult>[] resultAsyncResult = new AsyncResult[]{null};
        putGlobalRankingScoreAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PutGlobalRankingScoreByUserIdTask extends Gs2RestSessionTask<PutGlobalRankingScoreByUserIdResult> {
        private PutGlobalRankingScoreByUserIdRequest request;

        public PutGlobalRankingScoreByUserIdTask(
            PutGlobalRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<PutGlobalRankingScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PutGlobalRankingScoreByUserIdResult parse(JsonNode data) {
            return PutGlobalRankingScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/global/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("score", request.getScore());
                    put("metadata", request.getMetadata());
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

    public void putGlobalRankingScoreByUserIdAsync(
            PutGlobalRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<PutGlobalRankingScoreByUserIdResult>> callback
    ) {
        PutGlobalRankingScoreByUserIdTask task = new PutGlobalRankingScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public PutGlobalRankingScoreByUserIdResult putGlobalRankingScoreByUserId(
            PutGlobalRankingScoreByUserIdRequest request
    ) {
        final AsyncResult<PutGlobalRankingScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        putGlobalRankingScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetGlobalRankingScoreTask extends Gs2RestSessionTask<GetGlobalRankingScoreResult> {
        private GetGlobalRankingScoreRequest request;

        public GetGlobalRankingScoreTask(
            GetGlobalRankingScoreRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingScoreResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetGlobalRankingScoreResult parse(JsonNode data) {
            return GetGlobalRankingScoreResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/score/global/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getGlobalRankingScoreAsync(
            GetGlobalRankingScoreRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingScoreResult>> callback
    ) {
        GetGlobalRankingScoreTask task = new GetGlobalRankingScoreTask(request, callback);
        session.execute(task);
    }

    public GetGlobalRankingScoreResult getGlobalRankingScore(
            GetGlobalRankingScoreRequest request
    ) {
        final AsyncResult<GetGlobalRankingScoreResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGlobalRankingScoreAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetGlobalRankingScoreByUserIdTask extends Gs2RestSessionTask<GetGlobalRankingScoreByUserIdResult> {
        private GetGlobalRankingScoreByUserIdRequest request;

        public GetGlobalRankingScoreByUserIdTask(
            GetGlobalRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetGlobalRankingScoreByUserIdResult parse(JsonNode data) {
            return GetGlobalRankingScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/global/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getGlobalRankingScoreByUserIdAsync(
            GetGlobalRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingScoreByUserIdResult>> callback
    ) {
        GetGlobalRankingScoreByUserIdTask task = new GetGlobalRankingScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetGlobalRankingScoreByUserIdResult getGlobalRankingScoreByUserId(
            GetGlobalRankingScoreByUserIdRequest request
    ) {
        final AsyncResult<GetGlobalRankingScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGlobalRankingScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteGlobalRankingScoreByUserIdTask extends Gs2RestSessionTask<DeleteGlobalRankingScoreByUserIdResult> {
        private DeleteGlobalRankingScoreByUserIdRequest request;

        public DeleteGlobalRankingScoreByUserIdTask(
            DeleteGlobalRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteGlobalRankingScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteGlobalRankingScoreByUserIdResult parse(JsonNode data) {
            return DeleteGlobalRankingScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/global/{rankingName}/{season}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void deleteGlobalRankingScoreByUserIdAsync(
            DeleteGlobalRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteGlobalRankingScoreByUserIdResult>> callback
    ) {
        DeleteGlobalRankingScoreByUserIdTask task = new DeleteGlobalRankingScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteGlobalRankingScoreByUserIdResult deleteGlobalRankingScoreByUserId(
            DeleteGlobalRankingScoreByUserIdRequest request
    ) {
        final AsyncResult<DeleteGlobalRankingScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteGlobalRankingScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyGlobalRankingScoreTask extends Gs2RestSessionTask<VerifyGlobalRankingScoreResult> {
        private VerifyGlobalRankingScoreRequest request;

        public VerifyGlobalRankingScoreTask(
            VerifyGlobalRankingScoreRequest request,
            AsyncAction<AsyncResult<VerifyGlobalRankingScoreResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyGlobalRankingScoreResult parse(JsonNode data) {
            return VerifyGlobalRankingScoreResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/global/{rankingName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("season", request.getSeason());
                    put("score", request.getScore());
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

    public void verifyGlobalRankingScoreAsync(
            VerifyGlobalRankingScoreRequest request,
            AsyncAction<AsyncResult<VerifyGlobalRankingScoreResult>> callback
    ) {
        VerifyGlobalRankingScoreTask task = new VerifyGlobalRankingScoreTask(request, callback);
        session.execute(task);
    }

    public VerifyGlobalRankingScoreResult verifyGlobalRankingScore(
            VerifyGlobalRankingScoreRequest request
    ) {
        final AsyncResult<VerifyGlobalRankingScoreResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyGlobalRankingScoreAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyGlobalRankingScoreByUserIdTask extends Gs2RestSessionTask<VerifyGlobalRankingScoreByUserIdResult> {
        private VerifyGlobalRankingScoreByUserIdRequest request;

        public VerifyGlobalRankingScoreByUserIdTask(
            VerifyGlobalRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyGlobalRankingScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyGlobalRankingScoreByUserIdResult parse(JsonNode data) {
            return VerifyGlobalRankingScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/global/{rankingName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("season", request.getSeason());
                    put("score", request.getScore());
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void verifyGlobalRankingScoreByUserIdAsync(
            VerifyGlobalRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyGlobalRankingScoreByUserIdResult>> callback
    ) {
        VerifyGlobalRankingScoreByUserIdTask task = new VerifyGlobalRankingScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public VerifyGlobalRankingScoreByUserIdResult verifyGlobalRankingScoreByUserId(
            VerifyGlobalRankingScoreByUserIdRequest request
    ) {
        final AsyncResult<VerifyGlobalRankingScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyGlobalRankingScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyGlobalRankingScoreByStampTaskTask extends Gs2RestSessionTask<VerifyGlobalRankingScoreByStampTaskResult> {
        private VerifyGlobalRankingScoreByStampTaskRequest request;

        public VerifyGlobalRankingScoreByStampTaskTask(
            VerifyGlobalRankingScoreByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyGlobalRankingScoreByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyGlobalRankingScoreByStampTaskResult parse(JsonNode data) {
            return VerifyGlobalRankingScoreByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/global/score/verify";

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

    public void verifyGlobalRankingScoreByStampTaskAsync(
            VerifyGlobalRankingScoreByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyGlobalRankingScoreByStampTaskResult>> callback
    ) {
        VerifyGlobalRankingScoreByStampTaskTask task = new VerifyGlobalRankingScoreByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifyGlobalRankingScoreByStampTaskResult verifyGlobalRankingScoreByStampTask(
            VerifyGlobalRankingScoreByStampTaskRequest request
    ) {
        final AsyncResult<VerifyGlobalRankingScoreByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyGlobalRankingScoreByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeGlobalRankingReceivedRewardsTask extends Gs2RestSessionTask<DescribeGlobalRankingReceivedRewardsResult> {
        private DescribeGlobalRankingReceivedRewardsRequest request;

        public DescribeGlobalRankingReceivedRewardsTask(
            DescribeGlobalRankingReceivedRewardsRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingReceivedRewardsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeGlobalRankingReceivedRewardsResult parse(JsonNode data) {
            return DescribeGlobalRankingReceivedRewardsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/global/reward/received";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRankingName() != null) {
                queryStrings.add("rankingName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRankingName()))));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void describeGlobalRankingReceivedRewardsAsync(
            DescribeGlobalRankingReceivedRewardsRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingReceivedRewardsResult>> callback
    ) {
        DescribeGlobalRankingReceivedRewardsTask task = new DescribeGlobalRankingReceivedRewardsTask(request, callback);
        session.execute(task);
    }

    public DescribeGlobalRankingReceivedRewardsResult describeGlobalRankingReceivedRewards(
            DescribeGlobalRankingReceivedRewardsRequest request
    ) {
        final AsyncResult<DescribeGlobalRankingReceivedRewardsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeGlobalRankingReceivedRewardsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeGlobalRankingReceivedRewardsByUserIdTask extends Gs2RestSessionTask<DescribeGlobalRankingReceivedRewardsByUserIdResult> {
        private DescribeGlobalRankingReceivedRewardsByUserIdRequest request;

        public DescribeGlobalRankingReceivedRewardsByUserIdTask(
            DescribeGlobalRankingReceivedRewardsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingReceivedRewardsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeGlobalRankingReceivedRewardsByUserIdResult parse(JsonNode data) {
            return DescribeGlobalRankingReceivedRewardsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/global/reward/received";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRankingName() != null) {
                queryStrings.add("rankingName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRankingName()))));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void describeGlobalRankingReceivedRewardsByUserIdAsync(
            DescribeGlobalRankingReceivedRewardsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingReceivedRewardsByUserIdResult>> callback
    ) {
        DescribeGlobalRankingReceivedRewardsByUserIdTask task = new DescribeGlobalRankingReceivedRewardsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeGlobalRankingReceivedRewardsByUserIdResult describeGlobalRankingReceivedRewardsByUserId(
            DescribeGlobalRankingReceivedRewardsByUserIdRequest request
    ) {
        final AsyncResult<DescribeGlobalRankingReceivedRewardsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeGlobalRankingReceivedRewardsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateGlobalRankingReceivedRewardTask extends Gs2RestSessionTask<CreateGlobalRankingReceivedRewardResult> {
        private CreateGlobalRankingReceivedRewardRequest request;

        public CreateGlobalRankingReceivedRewardTask(
            CreateGlobalRankingReceivedRewardRequest request,
            AsyncAction<AsyncResult<CreateGlobalRankingReceivedRewardResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateGlobalRankingReceivedRewardResult parse(JsonNode data) {
            return CreateGlobalRankingReceivedRewardResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/global/reward/received/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("season", request.getSeason());
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

    public void createGlobalRankingReceivedRewardAsync(
            CreateGlobalRankingReceivedRewardRequest request,
            AsyncAction<AsyncResult<CreateGlobalRankingReceivedRewardResult>> callback
    ) {
        CreateGlobalRankingReceivedRewardTask task = new CreateGlobalRankingReceivedRewardTask(request, callback);
        session.execute(task);
    }

    public CreateGlobalRankingReceivedRewardResult createGlobalRankingReceivedReward(
            CreateGlobalRankingReceivedRewardRequest request
    ) {
        final AsyncResult<CreateGlobalRankingReceivedRewardResult>[] resultAsyncResult = new AsyncResult[]{null};
        createGlobalRankingReceivedRewardAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateGlobalRankingReceivedRewardByUserIdTask extends Gs2RestSessionTask<CreateGlobalRankingReceivedRewardByUserIdResult> {
        private CreateGlobalRankingReceivedRewardByUserIdRequest request;

        public CreateGlobalRankingReceivedRewardByUserIdTask(
            CreateGlobalRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<CreateGlobalRankingReceivedRewardByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateGlobalRankingReceivedRewardByUserIdResult parse(JsonNode data) {
            return CreateGlobalRankingReceivedRewardByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/global/reward/received/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("season", request.getSeason());
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

    public void createGlobalRankingReceivedRewardByUserIdAsync(
            CreateGlobalRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<CreateGlobalRankingReceivedRewardByUserIdResult>> callback
    ) {
        CreateGlobalRankingReceivedRewardByUserIdTask task = new CreateGlobalRankingReceivedRewardByUserIdTask(request, callback);
        session.execute(task);
    }

    public CreateGlobalRankingReceivedRewardByUserIdResult createGlobalRankingReceivedRewardByUserId(
            CreateGlobalRankingReceivedRewardByUserIdRequest request
    ) {
        final AsyncResult<CreateGlobalRankingReceivedRewardByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        createGlobalRankingReceivedRewardByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ReceiveGlobalRankingReceivedRewardTask extends Gs2RestSessionTask<ReceiveGlobalRankingReceivedRewardResult> {
        private ReceiveGlobalRankingReceivedRewardRequest request;

        public ReceiveGlobalRankingReceivedRewardTask(
            ReceiveGlobalRankingReceivedRewardRequest request,
            AsyncAction<AsyncResult<ReceiveGlobalRankingReceivedRewardResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ReceiveGlobalRankingReceivedRewardResult parse(JsonNode data) {
            return ReceiveGlobalRankingReceivedRewardResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/global/reward/received/{rankingName}/{season}/reward/receive";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{season}", this.request.getSeason() == null  ? "null" : String.valueOf(this.request.getSeason()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("config", request.getConfig() == null ? null :
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

    public void receiveGlobalRankingReceivedRewardAsync(
            ReceiveGlobalRankingReceivedRewardRequest request,
            AsyncAction<AsyncResult<ReceiveGlobalRankingReceivedRewardResult>> callback
    ) {
        ReceiveGlobalRankingReceivedRewardTask task = new ReceiveGlobalRankingReceivedRewardTask(request, callback);
        session.execute(task);
    }

    public ReceiveGlobalRankingReceivedRewardResult receiveGlobalRankingReceivedReward(
            ReceiveGlobalRankingReceivedRewardRequest request
    ) {
        final AsyncResult<ReceiveGlobalRankingReceivedRewardResult>[] resultAsyncResult = new AsyncResult[]{null};
        receiveGlobalRankingReceivedRewardAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ReceiveGlobalRankingReceivedRewardByUserIdTask extends Gs2RestSessionTask<ReceiveGlobalRankingReceivedRewardByUserIdResult> {
        private ReceiveGlobalRankingReceivedRewardByUserIdRequest request;

        public ReceiveGlobalRankingReceivedRewardByUserIdTask(
            ReceiveGlobalRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<ReceiveGlobalRankingReceivedRewardByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ReceiveGlobalRankingReceivedRewardByUserIdResult parse(JsonNode data) {
            return ReceiveGlobalRankingReceivedRewardByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/global/reward/received/{rankingName}/{season}/reward/receive";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{season}", this.request.getSeason() == null  ? "null" : String.valueOf(this.request.getSeason()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("config", request.getConfig() == null ? null :
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

    public void receiveGlobalRankingReceivedRewardByUserIdAsync(
            ReceiveGlobalRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<ReceiveGlobalRankingReceivedRewardByUserIdResult>> callback
    ) {
        ReceiveGlobalRankingReceivedRewardByUserIdTask task = new ReceiveGlobalRankingReceivedRewardByUserIdTask(request, callback);
        session.execute(task);
    }

    public ReceiveGlobalRankingReceivedRewardByUserIdResult receiveGlobalRankingReceivedRewardByUserId(
            ReceiveGlobalRankingReceivedRewardByUserIdRequest request
    ) {
        final AsyncResult<ReceiveGlobalRankingReceivedRewardByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        receiveGlobalRankingReceivedRewardByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetGlobalRankingReceivedRewardTask extends Gs2RestSessionTask<GetGlobalRankingReceivedRewardResult> {
        private GetGlobalRankingReceivedRewardRequest request;

        public GetGlobalRankingReceivedRewardTask(
            GetGlobalRankingReceivedRewardRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingReceivedRewardResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetGlobalRankingReceivedRewardResult parse(JsonNode data) {
            return GetGlobalRankingReceivedRewardResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/global/reward/received/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getGlobalRankingReceivedRewardAsync(
            GetGlobalRankingReceivedRewardRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingReceivedRewardResult>> callback
    ) {
        GetGlobalRankingReceivedRewardTask task = new GetGlobalRankingReceivedRewardTask(request, callback);
        session.execute(task);
    }

    public GetGlobalRankingReceivedRewardResult getGlobalRankingReceivedReward(
            GetGlobalRankingReceivedRewardRequest request
    ) {
        final AsyncResult<GetGlobalRankingReceivedRewardResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGlobalRankingReceivedRewardAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetGlobalRankingReceivedRewardByUserIdTask extends Gs2RestSessionTask<GetGlobalRankingReceivedRewardByUserIdResult> {
        private GetGlobalRankingReceivedRewardByUserIdRequest request;

        public GetGlobalRankingReceivedRewardByUserIdTask(
            GetGlobalRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingReceivedRewardByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetGlobalRankingReceivedRewardByUserIdResult parse(JsonNode data) {
            return GetGlobalRankingReceivedRewardByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/global/reward/received/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getGlobalRankingReceivedRewardByUserIdAsync(
            GetGlobalRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingReceivedRewardByUserIdResult>> callback
    ) {
        GetGlobalRankingReceivedRewardByUserIdTask task = new GetGlobalRankingReceivedRewardByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetGlobalRankingReceivedRewardByUserIdResult getGlobalRankingReceivedRewardByUserId(
            GetGlobalRankingReceivedRewardByUserIdRequest request
    ) {
        final AsyncResult<GetGlobalRankingReceivedRewardByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGlobalRankingReceivedRewardByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteGlobalRankingReceivedRewardByUserIdTask extends Gs2RestSessionTask<DeleteGlobalRankingReceivedRewardByUserIdResult> {
        private DeleteGlobalRankingReceivedRewardByUserIdRequest request;

        public DeleteGlobalRankingReceivedRewardByUserIdTask(
            DeleteGlobalRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteGlobalRankingReceivedRewardByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteGlobalRankingReceivedRewardByUserIdResult parse(JsonNode data) {
            return DeleteGlobalRankingReceivedRewardByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/global/reward/received/{rankingName}/{season}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void deleteGlobalRankingReceivedRewardByUserIdAsync(
            DeleteGlobalRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteGlobalRankingReceivedRewardByUserIdResult>> callback
    ) {
        DeleteGlobalRankingReceivedRewardByUserIdTask task = new DeleteGlobalRankingReceivedRewardByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteGlobalRankingReceivedRewardByUserIdResult deleteGlobalRankingReceivedRewardByUserId(
            DeleteGlobalRankingReceivedRewardByUserIdRequest request
    ) {
        final AsyncResult<DeleteGlobalRankingReceivedRewardByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteGlobalRankingReceivedRewardByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateGlobalRankingReceivedRewardByStampTaskTask extends Gs2RestSessionTask<CreateGlobalRankingReceivedRewardByStampTaskResult> {
        private CreateGlobalRankingReceivedRewardByStampTaskRequest request;

        public CreateGlobalRankingReceivedRewardByStampTaskTask(
            CreateGlobalRankingReceivedRewardByStampTaskRequest request,
            AsyncAction<AsyncResult<CreateGlobalRankingReceivedRewardByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateGlobalRankingReceivedRewardByStampTaskResult parse(JsonNode data) {
            return CreateGlobalRankingReceivedRewardByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/ranking/global/reward/receive";

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

    public void createGlobalRankingReceivedRewardByStampTaskAsync(
            CreateGlobalRankingReceivedRewardByStampTaskRequest request,
            AsyncAction<AsyncResult<CreateGlobalRankingReceivedRewardByStampTaskResult>> callback
    ) {
        CreateGlobalRankingReceivedRewardByStampTaskTask task = new CreateGlobalRankingReceivedRewardByStampTaskTask(request, callback);
        session.execute(task);
    }

    public CreateGlobalRankingReceivedRewardByStampTaskResult createGlobalRankingReceivedRewardByStampTask(
            CreateGlobalRankingReceivedRewardByStampTaskRequest request
    ) {
        final AsyncResult<CreateGlobalRankingReceivedRewardByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        createGlobalRankingReceivedRewardByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeGlobalRankingsTask extends Gs2RestSessionTask<DescribeGlobalRankingsResult> {
        private DescribeGlobalRankingsRequest request;

        public DescribeGlobalRankingsTask(
            DescribeGlobalRankingsRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeGlobalRankingsResult parse(JsonNode data) {
            return DescribeGlobalRankingsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ranking/global/{rankingName}/user/me";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void describeGlobalRankingsAsync(
            DescribeGlobalRankingsRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingsResult>> callback
    ) {
        DescribeGlobalRankingsTask task = new DescribeGlobalRankingsTask(request, callback);
        session.execute(task);
    }

    public DescribeGlobalRankingsResult describeGlobalRankings(
            DescribeGlobalRankingsRequest request
    ) {
        final AsyncResult<DescribeGlobalRankingsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeGlobalRankingsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeGlobalRankingsByUserIdTask extends Gs2RestSessionTask<DescribeGlobalRankingsByUserIdResult> {
        private DescribeGlobalRankingsByUserIdRequest request;

        public DescribeGlobalRankingsByUserIdTask(
            DescribeGlobalRankingsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeGlobalRankingsByUserIdResult parse(JsonNode data) {
            return DescribeGlobalRankingsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ranking/global/{rankingName}/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void describeGlobalRankingsByUserIdAsync(
            DescribeGlobalRankingsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeGlobalRankingsByUserIdResult>> callback
    ) {
        DescribeGlobalRankingsByUserIdTask task = new DescribeGlobalRankingsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeGlobalRankingsByUserIdResult describeGlobalRankingsByUserId(
            DescribeGlobalRankingsByUserIdRequest request
    ) {
        final AsyncResult<DescribeGlobalRankingsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeGlobalRankingsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetGlobalRankingTask extends Gs2RestSessionTask<GetGlobalRankingResult> {
        private GetGlobalRankingRequest request;

        public GetGlobalRankingTask(
            GetGlobalRankingRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetGlobalRankingResult parse(JsonNode data) {
            return GetGlobalRankingResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ranking/global/{rankingName}/user/me/rank";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getGlobalRankingAsync(
            GetGlobalRankingRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingResult>> callback
    ) {
        GetGlobalRankingTask task = new GetGlobalRankingTask(request, callback);
        session.execute(task);
    }

    public GetGlobalRankingResult getGlobalRanking(
            GetGlobalRankingRequest request
    ) {
        final AsyncResult<GetGlobalRankingResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGlobalRankingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetGlobalRankingByUserIdTask extends Gs2RestSessionTask<GetGlobalRankingByUserIdResult> {
        private GetGlobalRankingByUserIdRequest request;

        public GetGlobalRankingByUserIdTask(
            GetGlobalRankingByUserIdRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetGlobalRankingByUserIdResult parse(JsonNode data) {
            return GetGlobalRankingByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ranking/global/{rankingName}/user/{userId}/rank";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getGlobalRankingByUserIdAsync(
            GetGlobalRankingByUserIdRequest request,
            AsyncAction<AsyncResult<GetGlobalRankingByUserIdResult>> callback
    ) {
        GetGlobalRankingByUserIdTask task = new GetGlobalRankingByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetGlobalRankingByUserIdResult getGlobalRankingByUserId(
            GetGlobalRankingByUserIdRequest request
    ) {
        final AsyncResult<GetGlobalRankingByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGlobalRankingByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeClusterRankingModelsTask extends Gs2RestSessionTask<DescribeClusterRankingModelsResult> {
        private DescribeClusterRankingModelsRequest request;

        public DescribeClusterRankingModelsTask(
            DescribeClusterRankingModelsRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeClusterRankingModelsResult parse(JsonNode data) {
            return DescribeClusterRankingModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/cluster";

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

    public void describeClusterRankingModelsAsync(
            DescribeClusterRankingModelsRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingModelsResult>> callback
    ) {
        DescribeClusterRankingModelsTask task = new DescribeClusterRankingModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeClusterRankingModelsResult describeClusterRankingModels(
            DescribeClusterRankingModelsRequest request
    ) {
        final AsyncResult<DescribeClusterRankingModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeClusterRankingModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetClusterRankingModelTask extends Gs2RestSessionTask<GetClusterRankingModelResult> {
        private GetClusterRankingModelRequest request;

        public GetClusterRankingModelTask(
            GetClusterRankingModelRequest request,
            AsyncAction<AsyncResult<GetClusterRankingModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetClusterRankingModelResult parse(JsonNode data) {
            return GetClusterRankingModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/cluster/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

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

    public void getClusterRankingModelAsync(
            GetClusterRankingModelRequest request,
            AsyncAction<AsyncResult<GetClusterRankingModelResult>> callback
    ) {
        GetClusterRankingModelTask task = new GetClusterRankingModelTask(request, callback);
        session.execute(task);
    }

    public GetClusterRankingModelResult getClusterRankingModel(
            GetClusterRankingModelRequest request
    ) {
        final AsyncResult<GetClusterRankingModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getClusterRankingModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeClusterRankingModelMastersTask extends Gs2RestSessionTask<DescribeClusterRankingModelMastersResult> {
        private DescribeClusterRankingModelMastersRequest request;

        public DescribeClusterRankingModelMastersTask(
            DescribeClusterRankingModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeClusterRankingModelMastersResult parse(JsonNode data) {
            return DescribeClusterRankingModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/cluster";

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

    public void describeClusterRankingModelMastersAsync(
            DescribeClusterRankingModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingModelMastersResult>> callback
    ) {
        DescribeClusterRankingModelMastersTask task = new DescribeClusterRankingModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeClusterRankingModelMastersResult describeClusterRankingModelMasters(
            DescribeClusterRankingModelMastersRequest request
    ) {
        final AsyncResult<DescribeClusterRankingModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeClusterRankingModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateClusterRankingModelMasterTask extends Gs2RestSessionTask<CreateClusterRankingModelMasterResult> {
        private CreateClusterRankingModelMasterRequest request;

        public CreateClusterRankingModelMasterTask(
            CreateClusterRankingModelMasterRequest request,
            AsyncAction<AsyncResult<CreateClusterRankingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateClusterRankingModelMasterResult parse(JsonNode data) {
            return CreateClusterRankingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/cluster";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("clusterType", request.getClusterType());
                    put("minimumValue", request.getMinimumValue());
                    put("maximumValue", request.getMaximumValue());
                    put("sum", request.getSum());
                    put("scoreTtlDays", request.getScoreTtlDays());
                    put("orderDirection", request.getOrderDirection());
                    put("rankingRewards", request.getRankingRewards() == null ? null :
                        request.getRankingRewards().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("rewardCalculationIndex", request.getRewardCalculationIndex());
                    put("entryPeriodEventId", request.getEntryPeriodEventId());
                    put("accessPeriodEventId", request.getAccessPeriodEventId());
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

    public void createClusterRankingModelMasterAsync(
            CreateClusterRankingModelMasterRequest request,
            AsyncAction<AsyncResult<CreateClusterRankingModelMasterResult>> callback
    ) {
        CreateClusterRankingModelMasterTask task = new CreateClusterRankingModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateClusterRankingModelMasterResult createClusterRankingModelMaster(
            CreateClusterRankingModelMasterRequest request
    ) {
        final AsyncResult<CreateClusterRankingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createClusterRankingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetClusterRankingModelMasterTask extends Gs2RestSessionTask<GetClusterRankingModelMasterResult> {
        private GetClusterRankingModelMasterRequest request;

        public GetClusterRankingModelMasterTask(
            GetClusterRankingModelMasterRequest request,
            AsyncAction<AsyncResult<GetClusterRankingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetClusterRankingModelMasterResult parse(JsonNode data) {
            return GetClusterRankingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/cluster/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

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

    public void getClusterRankingModelMasterAsync(
            GetClusterRankingModelMasterRequest request,
            AsyncAction<AsyncResult<GetClusterRankingModelMasterResult>> callback
    ) {
        GetClusterRankingModelMasterTask task = new GetClusterRankingModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetClusterRankingModelMasterResult getClusterRankingModelMaster(
            GetClusterRankingModelMasterRequest request
    ) {
        final AsyncResult<GetClusterRankingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getClusterRankingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateClusterRankingModelMasterTask extends Gs2RestSessionTask<UpdateClusterRankingModelMasterResult> {
        private UpdateClusterRankingModelMasterRequest request;

        public UpdateClusterRankingModelMasterTask(
            UpdateClusterRankingModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateClusterRankingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateClusterRankingModelMasterResult parse(JsonNode data) {
            return UpdateClusterRankingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/cluster/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("clusterType", request.getClusterType());
                    put("minimumValue", request.getMinimumValue());
                    put("maximumValue", request.getMaximumValue());
                    put("sum", request.getSum());
                    put("scoreTtlDays", request.getScoreTtlDays());
                    put("orderDirection", request.getOrderDirection());
                    put("rankingRewards", request.getRankingRewards() == null ? null :
                        request.getRankingRewards().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("rewardCalculationIndex", request.getRewardCalculationIndex());
                    put("entryPeriodEventId", request.getEntryPeriodEventId());
                    put("accessPeriodEventId", request.getAccessPeriodEventId());
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

    public void updateClusterRankingModelMasterAsync(
            UpdateClusterRankingModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateClusterRankingModelMasterResult>> callback
    ) {
        UpdateClusterRankingModelMasterTask task = new UpdateClusterRankingModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateClusterRankingModelMasterResult updateClusterRankingModelMaster(
            UpdateClusterRankingModelMasterRequest request
    ) {
        final AsyncResult<UpdateClusterRankingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateClusterRankingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteClusterRankingModelMasterTask extends Gs2RestSessionTask<DeleteClusterRankingModelMasterResult> {
        private DeleteClusterRankingModelMasterRequest request;

        public DeleteClusterRankingModelMasterTask(
            DeleteClusterRankingModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteClusterRankingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteClusterRankingModelMasterResult parse(JsonNode data) {
            return DeleteClusterRankingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/cluster/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

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

    public void deleteClusterRankingModelMasterAsync(
            DeleteClusterRankingModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteClusterRankingModelMasterResult>> callback
    ) {
        DeleteClusterRankingModelMasterTask task = new DeleteClusterRankingModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteClusterRankingModelMasterResult deleteClusterRankingModelMaster(
            DeleteClusterRankingModelMasterRequest request
    ) {
        final AsyncResult<DeleteClusterRankingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteClusterRankingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeClusterRankingScoresTask extends Gs2RestSessionTask<DescribeClusterRankingScoresResult> {
        private DescribeClusterRankingScoresRequest request;

        public DescribeClusterRankingScoresTask(
            DescribeClusterRankingScoresRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingScoresResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeClusterRankingScoresResult parse(JsonNode data) {
            return DescribeClusterRankingScoresResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/score/cluster";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRankingName() != null) {
                queryStrings.add("rankingName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRankingName()))));
            }
            if (this.request.getClusterName() != null) {
                queryStrings.add("clusterName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getClusterName()))));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void describeClusterRankingScoresAsync(
            DescribeClusterRankingScoresRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingScoresResult>> callback
    ) {
        DescribeClusterRankingScoresTask task = new DescribeClusterRankingScoresTask(request, callback);
        session.execute(task);
    }

    public DescribeClusterRankingScoresResult describeClusterRankingScores(
            DescribeClusterRankingScoresRequest request
    ) {
        final AsyncResult<DescribeClusterRankingScoresResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeClusterRankingScoresAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeClusterRankingScoresByUserIdTask extends Gs2RestSessionTask<DescribeClusterRankingScoresByUserIdResult> {
        private DescribeClusterRankingScoresByUserIdRequest request;

        public DescribeClusterRankingScoresByUserIdTask(
            DescribeClusterRankingScoresByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingScoresByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeClusterRankingScoresByUserIdResult parse(JsonNode data) {
            return DescribeClusterRankingScoresByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/cluster";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRankingName() != null) {
                queryStrings.add("rankingName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRankingName()))));
            }
            if (this.request.getClusterName() != null) {
                queryStrings.add("clusterName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getClusterName()))));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void describeClusterRankingScoresByUserIdAsync(
            DescribeClusterRankingScoresByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingScoresByUserIdResult>> callback
    ) {
        DescribeClusterRankingScoresByUserIdTask task = new DescribeClusterRankingScoresByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeClusterRankingScoresByUserIdResult describeClusterRankingScoresByUserId(
            DescribeClusterRankingScoresByUserIdRequest request
    ) {
        final AsyncResult<DescribeClusterRankingScoresByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeClusterRankingScoresByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PutClusterRankingScoreTask extends Gs2RestSessionTask<PutClusterRankingScoreResult> {
        private PutClusterRankingScoreRequest request;

        public PutClusterRankingScoreTask(
            PutClusterRankingScoreRequest request,
            AsyncAction<AsyncResult<PutClusterRankingScoreResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PutClusterRankingScoreResult parse(JsonNode data) {
            return PutClusterRankingScoreResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/score/cluster/{rankingName}/{clusterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("score", request.getScore());
                    put("metadata", request.getMetadata());
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

    public void putClusterRankingScoreAsync(
            PutClusterRankingScoreRequest request,
            AsyncAction<AsyncResult<PutClusterRankingScoreResult>> callback
    ) {
        PutClusterRankingScoreTask task = new PutClusterRankingScoreTask(request, callback);
        session.execute(task);
    }

    public PutClusterRankingScoreResult putClusterRankingScore(
            PutClusterRankingScoreRequest request
    ) {
        final AsyncResult<PutClusterRankingScoreResult>[] resultAsyncResult = new AsyncResult[]{null};
        putClusterRankingScoreAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PutClusterRankingScoreByUserIdTask extends Gs2RestSessionTask<PutClusterRankingScoreByUserIdResult> {
        private PutClusterRankingScoreByUserIdRequest request;

        public PutClusterRankingScoreByUserIdTask(
            PutClusterRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<PutClusterRankingScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PutClusterRankingScoreByUserIdResult parse(JsonNode data) {
            return PutClusterRankingScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/cluster/{rankingName}/{clusterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("score", request.getScore());
                    put("metadata", request.getMetadata());
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

    public void putClusterRankingScoreByUserIdAsync(
            PutClusterRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<PutClusterRankingScoreByUserIdResult>> callback
    ) {
        PutClusterRankingScoreByUserIdTask task = new PutClusterRankingScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public PutClusterRankingScoreByUserIdResult putClusterRankingScoreByUserId(
            PutClusterRankingScoreByUserIdRequest request
    ) {
        final AsyncResult<PutClusterRankingScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        putClusterRankingScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetClusterRankingScoreTask extends Gs2RestSessionTask<GetClusterRankingScoreResult> {
        private GetClusterRankingScoreRequest request;

        public GetClusterRankingScoreTask(
            GetClusterRankingScoreRequest request,
            AsyncAction<AsyncResult<GetClusterRankingScoreResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetClusterRankingScoreResult parse(JsonNode data) {
            return GetClusterRankingScoreResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/score/cluster/{rankingName}/{clusterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getClusterRankingScoreAsync(
            GetClusterRankingScoreRequest request,
            AsyncAction<AsyncResult<GetClusterRankingScoreResult>> callback
    ) {
        GetClusterRankingScoreTask task = new GetClusterRankingScoreTask(request, callback);
        session.execute(task);
    }

    public GetClusterRankingScoreResult getClusterRankingScore(
            GetClusterRankingScoreRequest request
    ) {
        final AsyncResult<GetClusterRankingScoreResult>[] resultAsyncResult = new AsyncResult[]{null};
        getClusterRankingScoreAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetClusterRankingScoreByUserIdTask extends Gs2RestSessionTask<GetClusterRankingScoreByUserIdResult> {
        private GetClusterRankingScoreByUserIdRequest request;

        public GetClusterRankingScoreByUserIdTask(
            GetClusterRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<GetClusterRankingScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetClusterRankingScoreByUserIdResult parse(JsonNode data) {
            return GetClusterRankingScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/cluster/{rankingName}/{clusterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getClusterRankingScoreByUserIdAsync(
            GetClusterRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<GetClusterRankingScoreByUserIdResult>> callback
    ) {
        GetClusterRankingScoreByUserIdTask task = new GetClusterRankingScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetClusterRankingScoreByUserIdResult getClusterRankingScoreByUserId(
            GetClusterRankingScoreByUserIdRequest request
    ) {
        final AsyncResult<GetClusterRankingScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getClusterRankingScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteClusterRankingScoreByUserIdTask extends Gs2RestSessionTask<DeleteClusterRankingScoreByUserIdResult> {
        private DeleteClusterRankingScoreByUserIdRequest request;

        public DeleteClusterRankingScoreByUserIdTask(
            DeleteClusterRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteClusterRankingScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteClusterRankingScoreByUserIdResult parse(JsonNode data) {
            return DeleteClusterRankingScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/cluster/{rankingName}/{clusterName}/{season}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void deleteClusterRankingScoreByUserIdAsync(
            DeleteClusterRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteClusterRankingScoreByUserIdResult>> callback
    ) {
        DeleteClusterRankingScoreByUserIdTask task = new DeleteClusterRankingScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteClusterRankingScoreByUserIdResult deleteClusterRankingScoreByUserId(
            DeleteClusterRankingScoreByUserIdRequest request
    ) {
        final AsyncResult<DeleteClusterRankingScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteClusterRankingScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyClusterRankingScoreTask extends Gs2RestSessionTask<VerifyClusterRankingScoreResult> {
        private VerifyClusterRankingScoreRequest request;

        public VerifyClusterRankingScoreTask(
            VerifyClusterRankingScoreRequest request,
            AsyncAction<AsyncResult<VerifyClusterRankingScoreResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyClusterRankingScoreResult parse(JsonNode data) {
            return VerifyClusterRankingScoreResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/cluster/{rankingName}/{clusterName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("season", request.getSeason());
                    put("score", request.getScore());
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

    public void verifyClusterRankingScoreAsync(
            VerifyClusterRankingScoreRequest request,
            AsyncAction<AsyncResult<VerifyClusterRankingScoreResult>> callback
    ) {
        VerifyClusterRankingScoreTask task = new VerifyClusterRankingScoreTask(request, callback);
        session.execute(task);
    }

    public VerifyClusterRankingScoreResult verifyClusterRankingScore(
            VerifyClusterRankingScoreRequest request
    ) {
        final AsyncResult<VerifyClusterRankingScoreResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyClusterRankingScoreAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyClusterRankingScoreByUserIdTask extends Gs2RestSessionTask<VerifyClusterRankingScoreByUserIdResult> {
        private VerifyClusterRankingScoreByUserIdRequest request;

        public VerifyClusterRankingScoreByUserIdTask(
            VerifyClusterRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyClusterRankingScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyClusterRankingScoreByUserIdResult parse(JsonNode data) {
            return VerifyClusterRankingScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/cluster/{rankingName}/{clusterName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("season", request.getSeason());
                    put("score", request.getScore());
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void verifyClusterRankingScoreByUserIdAsync(
            VerifyClusterRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<VerifyClusterRankingScoreByUserIdResult>> callback
    ) {
        VerifyClusterRankingScoreByUserIdTask task = new VerifyClusterRankingScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public VerifyClusterRankingScoreByUserIdResult verifyClusterRankingScoreByUserId(
            VerifyClusterRankingScoreByUserIdRequest request
    ) {
        final AsyncResult<VerifyClusterRankingScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyClusterRankingScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifyClusterRankingScoreByStampTaskTask extends Gs2RestSessionTask<VerifyClusterRankingScoreByStampTaskResult> {
        private VerifyClusterRankingScoreByStampTaskRequest request;

        public VerifyClusterRankingScoreByStampTaskTask(
            VerifyClusterRankingScoreByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyClusterRankingScoreByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyClusterRankingScoreByStampTaskResult parse(JsonNode data) {
            return VerifyClusterRankingScoreByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/cluster/score/verify";

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

    public void verifyClusterRankingScoreByStampTaskAsync(
            VerifyClusterRankingScoreByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifyClusterRankingScoreByStampTaskResult>> callback
    ) {
        VerifyClusterRankingScoreByStampTaskTask task = new VerifyClusterRankingScoreByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifyClusterRankingScoreByStampTaskResult verifyClusterRankingScoreByStampTask(
            VerifyClusterRankingScoreByStampTaskRequest request
    ) {
        final AsyncResult<VerifyClusterRankingScoreByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyClusterRankingScoreByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeClusterRankingReceivedRewardsTask extends Gs2RestSessionTask<DescribeClusterRankingReceivedRewardsResult> {
        private DescribeClusterRankingReceivedRewardsRequest request;

        public DescribeClusterRankingReceivedRewardsTask(
            DescribeClusterRankingReceivedRewardsRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingReceivedRewardsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeClusterRankingReceivedRewardsResult parse(JsonNode data) {
            return DescribeClusterRankingReceivedRewardsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/cluster/reward/received";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRankingName() != null) {
                queryStrings.add("rankingName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRankingName()))));
            }
            if (this.request.getClusterName() != null) {
                queryStrings.add("clusterName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getClusterName()))));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void describeClusterRankingReceivedRewardsAsync(
            DescribeClusterRankingReceivedRewardsRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingReceivedRewardsResult>> callback
    ) {
        DescribeClusterRankingReceivedRewardsTask task = new DescribeClusterRankingReceivedRewardsTask(request, callback);
        session.execute(task);
    }

    public DescribeClusterRankingReceivedRewardsResult describeClusterRankingReceivedRewards(
            DescribeClusterRankingReceivedRewardsRequest request
    ) {
        final AsyncResult<DescribeClusterRankingReceivedRewardsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeClusterRankingReceivedRewardsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeClusterRankingReceivedRewardsByUserIdTask extends Gs2RestSessionTask<DescribeClusterRankingReceivedRewardsByUserIdResult> {
        private DescribeClusterRankingReceivedRewardsByUserIdRequest request;

        public DescribeClusterRankingReceivedRewardsByUserIdTask(
            DescribeClusterRankingReceivedRewardsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingReceivedRewardsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeClusterRankingReceivedRewardsByUserIdResult parse(JsonNode data) {
            return DescribeClusterRankingReceivedRewardsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/cluster/reward/received";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRankingName() != null) {
                queryStrings.add("rankingName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRankingName()))));
            }
            if (this.request.getClusterName() != null) {
                queryStrings.add("clusterName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getClusterName()))));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void describeClusterRankingReceivedRewardsByUserIdAsync(
            DescribeClusterRankingReceivedRewardsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingReceivedRewardsByUserIdResult>> callback
    ) {
        DescribeClusterRankingReceivedRewardsByUserIdTask task = new DescribeClusterRankingReceivedRewardsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeClusterRankingReceivedRewardsByUserIdResult describeClusterRankingReceivedRewardsByUserId(
            DescribeClusterRankingReceivedRewardsByUserIdRequest request
    ) {
        final AsyncResult<DescribeClusterRankingReceivedRewardsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeClusterRankingReceivedRewardsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateClusterRankingReceivedRewardTask extends Gs2RestSessionTask<CreateClusterRankingReceivedRewardResult> {
        private CreateClusterRankingReceivedRewardRequest request;

        public CreateClusterRankingReceivedRewardTask(
            CreateClusterRankingReceivedRewardRequest request,
            AsyncAction<AsyncResult<CreateClusterRankingReceivedRewardResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateClusterRankingReceivedRewardResult parse(JsonNode data) {
            return CreateClusterRankingReceivedRewardResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/cluster/reward/received/{rankingName}/{clusterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("season", request.getSeason());
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

    public void createClusterRankingReceivedRewardAsync(
            CreateClusterRankingReceivedRewardRequest request,
            AsyncAction<AsyncResult<CreateClusterRankingReceivedRewardResult>> callback
    ) {
        CreateClusterRankingReceivedRewardTask task = new CreateClusterRankingReceivedRewardTask(request, callback);
        session.execute(task);
    }

    public CreateClusterRankingReceivedRewardResult createClusterRankingReceivedReward(
            CreateClusterRankingReceivedRewardRequest request
    ) {
        final AsyncResult<CreateClusterRankingReceivedRewardResult>[] resultAsyncResult = new AsyncResult[]{null};
        createClusterRankingReceivedRewardAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateClusterRankingReceivedRewardByUserIdTask extends Gs2RestSessionTask<CreateClusterRankingReceivedRewardByUserIdResult> {
        private CreateClusterRankingReceivedRewardByUserIdRequest request;

        public CreateClusterRankingReceivedRewardByUserIdTask(
            CreateClusterRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<CreateClusterRankingReceivedRewardByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateClusterRankingReceivedRewardByUserIdResult parse(JsonNode data) {
            return CreateClusterRankingReceivedRewardByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/cluster/reward/received/{rankingName}/{clusterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("season", request.getSeason());
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

    public void createClusterRankingReceivedRewardByUserIdAsync(
            CreateClusterRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<CreateClusterRankingReceivedRewardByUserIdResult>> callback
    ) {
        CreateClusterRankingReceivedRewardByUserIdTask task = new CreateClusterRankingReceivedRewardByUserIdTask(request, callback);
        session.execute(task);
    }

    public CreateClusterRankingReceivedRewardByUserIdResult createClusterRankingReceivedRewardByUserId(
            CreateClusterRankingReceivedRewardByUserIdRequest request
    ) {
        final AsyncResult<CreateClusterRankingReceivedRewardByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        createClusterRankingReceivedRewardByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ReceiveClusterRankingReceivedRewardTask extends Gs2RestSessionTask<ReceiveClusterRankingReceivedRewardResult> {
        private ReceiveClusterRankingReceivedRewardRequest request;

        public ReceiveClusterRankingReceivedRewardTask(
            ReceiveClusterRankingReceivedRewardRequest request,
            AsyncAction<AsyncResult<ReceiveClusterRankingReceivedRewardResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ReceiveClusterRankingReceivedRewardResult parse(JsonNode data) {
            return ReceiveClusterRankingReceivedRewardResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/cluster/reward/received/{rankingName}/{clusterName}/{season}/reward/receive";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));
            url = url.replace("{season}", this.request.getSeason() == null  ? "null" : String.valueOf(this.request.getSeason()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("config", request.getConfig() == null ? null :
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

    public void receiveClusterRankingReceivedRewardAsync(
            ReceiveClusterRankingReceivedRewardRequest request,
            AsyncAction<AsyncResult<ReceiveClusterRankingReceivedRewardResult>> callback
    ) {
        ReceiveClusterRankingReceivedRewardTask task = new ReceiveClusterRankingReceivedRewardTask(request, callback);
        session.execute(task);
    }

    public ReceiveClusterRankingReceivedRewardResult receiveClusterRankingReceivedReward(
            ReceiveClusterRankingReceivedRewardRequest request
    ) {
        final AsyncResult<ReceiveClusterRankingReceivedRewardResult>[] resultAsyncResult = new AsyncResult[]{null};
        receiveClusterRankingReceivedRewardAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ReceiveClusterRankingReceivedRewardByUserIdTask extends Gs2RestSessionTask<ReceiveClusterRankingReceivedRewardByUserIdResult> {
        private ReceiveClusterRankingReceivedRewardByUserIdRequest request;

        public ReceiveClusterRankingReceivedRewardByUserIdTask(
            ReceiveClusterRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<ReceiveClusterRankingReceivedRewardByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ReceiveClusterRankingReceivedRewardByUserIdResult parse(JsonNode data) {
            return ReceiveClusterRankingReceivedRewardByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/cluster/reward/received/{rankingName}/{clusterName}/{season}/reward/receive";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));
            url = url.replace("{season}", this.request.getSeason() == null  ? "null" : String.valueOf(this.request.getSeason()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("config", request.getConfig() == null ? null :
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

    public void receiveClusterRankingReceivedRewardByUserIdAsync(
            ReceiveClusterRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<ReceiveClusterRankingReceivedRewardByUserIdResult>> callback
    ) {
        ReceiveClusterRankingReceivedRewardByUserIdTask task = new ReceiveClusterRankingReceivedRewardByUserIdTask(request, callback);
        session.execute(task);
    }

    public ReceiveClusterRankingReceivedRewardByUserIdResult receiveClusterRankingReceivedRewardByUserId(
            ReceiveClusterRankingReceivedRewardByUserIdRequest request
    ) {
        final AsyncResult<ReceiveClusterRankingReceivedRewardByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        receiveClusterRankingReceivedRewardByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetClusterRankingReceivedRewardTask extends Gs2RestSessionTask<GetClusterRankingReceivedRewardResult> {
        private GetClusterRankingReceivedRewardRequest request;

        public GetClusterRankingReceivedRewardTask(
            GetClusterRankingReceivedRewardRequest request,
            AsyncAction<AsyncResult<GetClusterRankingReceivedRewardResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetClusterRankingReceivedRewardResult parse(JsonNode data) {
            return GetClusterRankingReceivedRewardResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/cluster/reward/received/{rankingName}/{clusterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getClusterRankingReceivedRewardAsync(
            GetClusterRankingReceivedRewardRequest request,
            AsyncAction<AsyncResult<GetClusterRankingReceivedRewardResult>> callback
    ) {
        GetClusterRankingReceivedRewardTask task = new GetClusterRankingReceivedRewardTask(request, callback);
        session.execute(task);
    }

    public GetClusterRankingReceivedRewardResult getClusterRankingReceivedReward(
            GetClusterRankingReceivedRewardRequest request
    ) {
        final AsyncResult<GetClusterRankingReceivedRewardResult>[] resultAsyncResult = new AsyncResult[]{null};
        getClusterRankingReceivedRewardAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetClusterRankingReceivedRewardByUserIdTask extends Gs2RestSessionTask<GetClusterRankingReceivedRewardByUserIdResult> {
        private GetClusterRankingReceivedRewardByUserIdRequest request;

        public GetClusterRankingReceivedRewardByUserIdTask(
            GetClusterRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<GetClusterRankingReceivedRewardByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetClusterRankingReceivedRewardByUserIdResult parse(JsonNode data) {
            return GetClusterRankingReceivedRewardByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/cluster/reward/received/{rankingName}/{clusterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getClusterRankingReceivedRewardByUserIdAsync(
            GetClusterRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<GetClusterRankingReceivedRewardByUserIdResult>> callback
    ) {
        GetClusterRankingReceivedRewardByUserIdTask task = new GetClusterRankingReceivedRewardByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetClusterRankingReceivedRewardByUserIdResult getClusterRankingReceivedRewardByUserId(
            GetClusterRankingReceivedRewardByUserIdRequest request
    ) {
        final AsyncResult<GetClusterRankingReceivedRewardByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getClusterRankingReceivedRewardByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteClusterRankingReceivedRewardByUserIdTask extends Gs2RestSessionTask<DeleteClusterRankingReceivedRewardByUserIdResult> {
        private DeleteClusterRankingReceivedRewardByUserIdRequest request;

        public DeleteClusterRankingReceivedRewardByUserIdTask(
            DeleteClusterRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteClusterRankingReceivedRewardByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteClusterRankingReceivedRewardByUserIdResult parse(JsonNode data) {
            return DeleteClusterRankingReceivedRewardByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/cluster/reward/received/{rankingName}/{clusterName}/{season}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void deleteClusterRankingReceivedRewardByUserIdAsync(
            DeleteClusterRankingReceivedRewardByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteClusterRankingReceivedRewardByUserIdResult>> callback
    ) {
        DeleteClusterRankingReceivedRewardByUserIdTask task = new DeleteClusterRankingReceivedRewardByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteClusterRankingReceivedRewardByUserIdResult deleteClusterRankingReceivedRewardByUserId(
            DeleteClusterRankingReceivedRewardByUserIdRequest request
    ) {
        final AsyncResult<DeleteClusterRankingReceivedRewardByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteClusterRankingReceivedRewardByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateClusterRankingReceivedRewardByStampTaskTask extends Gs2RestSessionTask<CreateClusterRankingReceivedRewardByStampTaskResult> {
        private CreateClusterRankingReceivedRewardByStampTaskRequest request;

        public CreateClusterRankingReceivedRewardByStampTaskTask(
            CreateClusterRankingReceivedRewardByStampTaskRequest request,
            AsyncAction<AsyncResult<CreateClusterRankingReceivedRewardByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateClusterRankingReceivedRewardByStampTaskResult parse(JsonNode data) {
            return CreateClusterRankingReceivedRewardByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/ranking/cluster/reward/receive";

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

    public void createClusterRankingReceivedRewardByStampTaskAsync(
            CreateClusterRankingReceivedRewardByStampTaskRequest request,
            AsyncAction<AsyncResult<CreateClusterRankingReceivedRewardByStampTaskResult>> callback
    ) {
        CreateClusterRankingReceivedRewardByStampTaskTask task = new CreateClusterRankingReceivedRewardByStampTaskTask(request, callback);
        session.execute(task);
    }

    public CreateClusterRankingReceivedRewardByStampTaskResult createClusterRankingReceivedRewardByStampTask(
            CreateClusterRankingReceivedRewardByStampTaskRequest request
    ) {
        final AsyncResult<CreateClusterRankingReceivedRewardByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        createClusterRankingReceivedRewardByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeClusterRankingsTask extends Gs2RestSessionTask<DescribeClusterRankingsResult> {
        private DescribeClusterRankingsRequest request;

        public DescribeClusterRankingsTask(
            DescribeClusterRankingsRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeClusterRankingsResult parse(JsonNode data) {
            return DescribeClusterRankingsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ranking/cluster/{rankingName}/{clusterName}/user/me";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void describeClusterRankingsAsync(
            DescribeClusterRankingsRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingsResult>> callback
    ) {
        DescribeClusterRankingsTask task = new DescribeClusterRankingsTask(request, callback);
        session.execute(task);
    }

    public DescribeClusterRankingsResult describeClusterRankings(
            DescribeClusterRankingsRequest request
    ) {
        final AsyncResult<DescribeClusterRankingsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeClusterRankingsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeClusterRankingsByUserIdTask extends Gs2RestSessionTask<DescribeClusterRankingsByUserIdResult> {
        private DescribeClusterRankingsByUserIdRequest request;

        public DescribeClusterRankingsByUserIdTask(
            DescribeClusterRankingsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeClusterRankingsByUserIdResult parse(JsonNode data) {
            return DescribeClusterRankingsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ranking/cluster/{rankingName}/{clusterName}/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void describeClusterRankingsByUserIdAsync(
            DescribeClusterRankingsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeClusterRankingsByUserIdResult>> callback
    ) {
        DescribeClusterRankingsByUserIdTask task = new DescribeClusterRankingsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeClusterRankingsByUserIdResult describeClusterRankingsByUserId(
            DescribeClusterRankingsByUserIdRequest request
    ) {
        final AsyncResult<DescribeClusterRankingsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeClusterRankingsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetClusterRankingTask extends Gs2RestSessionTask<GetClusterRankingResult> {
        private GetClusterRankingRequest request;

        public GetClusterRankingTask(
            GetClusterRankingRequest request,
            AsyncAction<AsyncResult<GetClusterRankingResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetClusterRankingResult parse(JsonNode data) {
            return GetClusterRankingResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ranking/cluster/{rankingName}/{clusterName}/user/me/rank";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getClusterRankingAsync(
            GetClusterRankingRequest request,
            AsyncAction<AsyncResult<GetClusterRankingResult>> callback
    ) {
        GetClusterRankingTask task = new GetClusterRankingTask(request, callback);
        session.execute(task);
    }

    public GetClusterRankingResult getClusterRanking(
            GetClusterRankingRequest request
    ) {
        final AsyncResult<GetClusterRankingResult>[] resultAsyncResult = new AsyncResult[]{null};
        getClusterRankingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetClusterRankingByUserIdTask extends Gs2RestSessionTask<GetClusterRankingByUserIdResult> {
        private GetClusterRankingByUserIdRequest request;

        public GetClusterRankingByUserIdTask(
            GetClusterRankingByUserIdRequest request,
            AsyncAction<AsyncResult<GetClusterRankingByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetClusterRankingByUserIdResult parse(JsonNode data) {
            return GetClusterRankingByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ranking/cluster/{rankingName}/{clusterName}/user/{userId}/rank";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{clusterName}", this.request.getClusterName() == null || this.request.getClusterName().length() == 0 ? "null" : String.valueOf(this.request.getClusterName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getClusterRankingByUserIdAsync(
            GetClusterRankingByUserIdRequest request,
            AsyncAction<AsyncResult<GetClusterRankingByUserIdResult>> callback
    ) {
        GetClusterRankingByUserIdTask task = new GetClusterRankingByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetClusterRankingByUserIdResult getClusterRankingByUserId(
            GetClusterRankingByUserIdRequest request
    ) {
        final AsyncResult<GetClusterRankingByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getClusterRankingByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribeRankingModelsTask extends Gs2RestSessionTask<DescribeSubscribeRankingModelsResult> {
        private DescribeSubscribeRankingModelsRequest request;

        public DescribeSubscribeRankingModelsTask(
            DescribeSubscribeRankingModelsRequest request,
            AsyncAction<AsyncResult<DescribeSubscribeRankingModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSubscribeRankingModelsResult parse(JsonNode data) {
            return DescribeSubscribeRankingModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/subscribe";

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

    public void describeSubscribeRankingModelsAsync(
            DescribeSubscribeRankingModelsRequest request,
            AsyncAction<AsyncResult<DescribeSubscribeRankingModelsResult>> callback
    ) {
        DescribeSubscribeRankingModelsTask task = new DescribeSubscribeRankingModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeSubscribeRankingModelsResult describeSubscribeRankingModels(
            DescribeSubscribeRankingModelsRequest request
    ) {
        final AsyncResult<DescribeSubscribeRankingModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribeRankingModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscribeRankingModelTask extends Gs2RestSessionTask<GetSubscribeRankingModelResult> {
        private GetSubscribeRankingModelRequest request;

        public GetSubscribeRankingModelTask(
            GetSubscribeRankingModelRequest request,
            AsyncAction<AsyncResult<GetSubscribeRankingModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSubscribeRankingModelResult parse(JsonNode data) {
            return GetSubscribeRankingModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/subscribe/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

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

    public void getSubscribeRankingModelAsync(
            GetSubscribeRankingModelRequest request,
            AsyncAction<AsyncResult<GetSubscribeRankingModelResult>> callback
    ) {
        GetSubscribeRankingModelTask task = new GetSubscribeRankingModelTask(request, callback);
        session.execute(task);
    }

    public GetSubscribeRankingModelResult getSubscribeRankingModel(
            GetSubscribeRankingModelRequest request
    ) {
        final AsyncResult<GetSubscribeRankingModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscribeRankingModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribeRankingModelMastersTask extends Gs2RestSessionTask<DescribeSubscribeRankingModelMastersResult> {
        private DescribeSubscribeRankingModelMastersRequest request;

        public DescribeSubscribeRankingModelMastersTask(
            DescribeSubscribeRankingModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeSubscribeRankingModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSubscribeRankingModelMastersResult parse(JsonNode data) {
            return DescribeSubscribeRankingModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/subscribe";

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

    public void describeSubscribeRankingModelMastersAsync(
            DescribeSubscribeRankingModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeSubscribeRankingModelMastersResult>> callback
    ) {
        DescribeSubscribeRankingModelMastersTask task = new DescribeSubscribeRankingModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeSubscribeRankingModelMastersResult describeSubscribeRankingModelMasters(
            DescribeSubscribeRankingModelMastersRequest request
    ) {
        final AsyncResult<DescribeSubscribeRankingModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribeRankingModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateSubscribeRankingModelMasterTask extends Gs2RestSessionTask<CreateSubscribeRankingModelMasterResult> {
        private CreateSubscribeRankingModelMasterRequest request;

        public CreateSubscribeRankingModelMasterTask(
            CreateSubscribeRankingModelMasterRequest request,
            AsyncAction<AsyncResult<CreateSubscribeRankingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateSubscribeRankingModelMasterResult parse(JsonNode data) {
            return CreateSubscribeRankingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/subscribe";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("minimumValue", request.getMinimumValue());
                    put("maximumValue", request.getMaximumValue());
                    put("sum", request.getSum());
                    put("scoreTtlDays", request.getScoreTtlDays());
                    put("orderDirection", request.getOrderDirection());
                    put("entryPeriodEventId", request.getEntryPeriodEventId());
                    put("accessPeriodEventId", request.getAccessPeriodEventId());
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

    public void createSubscribeRankingModelMasterAsync(
            CreateSubscribeRankingModelMasterRequest request,
            AsyncAction<AsyncResult<CreateSubscribeRankingModelMasterResult>> callback
    ) {
        CreateSubscribeRankingModelMasterTask task = new CreateSubscribeRankingModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateSubscribeRankingModelMasterResult createSubscribeRankingModelMaster(
            CreateSubscribeRankingModelMasterRequest request
    ) {
        final AsyncResult<CreateSubscribeRankingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createSubscribeRankingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscribeRankingModelMasterTask extends Gs2RestSessionTask<GetSubscribeRankingModelMasterResult> {
        private GetSubscribeRankingModelMasterRequest request;

        public GetSubscribeRankingModelMasterTask(
            GetSubscribeRankingModelMasterRequest request,
            AsyncAction<AsyncResult<GetSubscribeRankingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSubscribeRankingModelMasterResult parse(JsonNode data) {
            return GetSubscribeRankingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/subscribe/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

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

    public void getSubscribeRankingModelMasterAsync(
            GetSubscribeRankingModelMasterRequest request,
            AsyncAction<AsyncResult<GetSubscribeRankingModelMasterResult>> callback
    ) {
        GetSubscribeRankingModelMasterTask task = new GetSubscribeRankingModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetSubscribeRankingModelMasterResult getSubscribeRankingModelMaster(
            GetSubscribeRankingModelMasterRequest request
    ) {
        final AsyncResult<GetSubscribeRankingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscribeRankingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateSubscribeRankingModelMasterTask extends Gs2RestSessionTask<UpdateSubscribeRankingModelMasterResult> {
        private UpdateSubscribeRankingModelMasterRequest request;

        public UpdateSubscribeRankingModelMasterTask(
            UpdateSubscribeRankingModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateSubscribeRankingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateSubscribeRankingModelMasterResult parse(JsonNode data) {
            return UpdateSubscribeRankingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/subscribe/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("minimumValue", request.getMinimumValue());
                    put("maximumValue", request.getMaximumValue());
                    put("sum", request.getSum());
                    put("scoreTtlDays", request.getScoreTtlDays());
                    put("orderDirection", request.getOrderDirection());
                    put("entryPeriodEventId", request.getEntryPeriodEventId());
                    put("accessPeriodEventId", request.getAccessPeriodEventId());
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

    public void updateSubscribeRankingModelMasterAsync(
            UpdateSubscribeRankingModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateSubscribeRankingModelMasterResult>> callback
    ) {
        UpdateSubscribeRankingModelMasterTask task = new UpdateSubscribeRankingModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateSubscribeRankingModelMasterResult updateSubscribeRankingModelMaster(
            UpdateSubscribeRankingModelMasterRequest request
    ) {
        final AsyncResult<UpdateSubscribeRankingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateSubscribeRankingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteSubscribeRankingModelMasterTask extends Gs2RestSessionTask<DeleteSubscribeRankingModelMasterResult> {
        private DeleteSubscribeRankingModelMasterRequest request;

        public DeleteSubscribeRankingModelMasterTask(
            DeleteSubscribeRankingModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteSubscribeRankingModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteSubscribeRankingModelMasterResult parse(JsonNode data) {
            return DeleteSubscribeRankingModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/subscribe/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

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

    public void deleteSubscribeRankingModelMasterAsync(
            DeleteSubscribeRankingModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteSubscribeRankingModelMasterResult>> callback
    ) {
        DeleteSubscribeRankingModelMasterTask task = new DeleteSubscribeRankingModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteSubscribeRankingModelMasterResult deleteSubscribeRankingModelMaster(
            DeleteSubscribeRankingModelMasterRequest request
    ) {
        final AsyncResult<DeleteSubscribeRankingModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteSubscribeRankingModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribesTask extends Gs2RestSessionTask<DescribeSubscribesResult> {
        private DescribeSubscribesRequest request;

        public DescribeSubscribesTask(
            DescribeSubscribesRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSubscribesResult parse(JsonNode data) {
            return DescribeSubscribesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscribe/score";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRankingName() != null) {
                queryStrings.add("rankingName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRankingName()))));
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

    public void describeSubscribesAsync(
            DescribeSubscribesRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesResult>> callback
    ) {
        DescribeSubscribesTask task = new DescribeSubscribesTask(request, callback);
        session.execute(task);
    }

    public DescribeSubscribesResult describeSubscribes(
            DescribeSubscribesRequest request
    ) {
        final AsyncResult<DescribeSubscribesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribesByUserIdTask extends Gs2RestSessionTask<DescribeSubscribesByUserIdResult> {
        private DescribeSubscribesByUserIdRequest request;

        public DescribeSubscribesByUserIdTask(
            DescribeSubscribesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSubscribesByUserIdResult parse(JsonNode data) {
            return DescribeSubscribesByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscribe/score";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRankingName() != null) {
                queryStrings.add("rankingName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRankingName()))));
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

    public void describeSubscribesByUserIdAsync(
            DescribeSubscribesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByUserIdResult>> callback
    ) {
        DescribeSubscribesByUserIdTask task = new DescribeSubscribesByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeSubscribesByUserIdResult describeSubscribesByUserId(
            DescribeSubscribesByUserIdRequest request
    ) {
        final AsyncResult<DescribeSubscribesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddSubscribeTask extends Gs2RestSessionTask<AddSubscribeResult> {
        private AddSubscribeRequest request;

        public AddSubscribeTask(
            AddSubscribeRequest request,
            AsyncAction<AsyncResult<AddSubscribeResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddSubscribeResult parse(JsonNode data) {
            return AddSubscribeResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscribe/{rankingName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
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

    public void addSubscribeAsync(
            AddSubscribeRequest request,
            AsyncAction<AsyncResult<AddSubscribeResult>> callback
    ) {
        AddSubscribeTask task = new AddSubscribeTask(request, callback);
        session.execute(task);
    }

    public AddSubscribeResult addSubscribe(
            AddSubscribeRequest request
    ) {
        final AsyncResult<AddSubscribeResult>[] resultAsyncResult = new AsyncResult[]{null};
        addSubscribeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddSubscribeByUserIdTask extends Gs2RestSessionTask<AddSubscribeByUserIdResult> {
        private AddSubscribeByUserIdRequest request;

        public AddSubscribeByUserIdTask(
            AddSubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<AddSubscribeByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AddSubscribeByUserIdResult parse(JsonNode data) {
            return AddSubscribeByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscribe/{rankingName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void addSubscribeByUserIdAsync(
            AddSubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<AddSubscribeByUserIdResult>> callback
    ) {
        AddSubscribeByUserIdTask task = new AddSubscribeByUserIdTask(request, callback);
        session.execute(task);
    }

    public AddSubscribeByUserIdResult addSubscribeByUserId(
            AddSubscribeByUserIdRequest request
    ) {
        final AsyncResult<AddSubscribeByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        addSubscribeByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribeRankingScoresTask extends Gs2RestSessionTask<DescribeSubscribeRankingScoresResult> {
        private DescribeSubscribeRankingScoresRequest request;

        public DescribeSubscribeRankingScoresTask(
            DescribeSubscribeRankingScoresRequest request,
            AsyncAction<AsyncResult<DescribeSubscribeRankingScoresResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSubscribeRankingScoresResult parse(JsonNode data) {
            return DescribeSubscribeRankingScoresResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/score/subscribe";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRankingName() != null) {
                queryStrings.add("rankingName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRankingName()))));
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

    public void describeSubscribeRankingScoresAsync(
            DescribeSubscribeRankingScoresRequest request,
            AsyncAction<AsyncResult<DescribeSubscribeRankingScoresResult>> callback
    ) {
        DescribeSubscribeRankingScoresTask task = new DescribeSubscribeRankingScoresTask(request, callback);
        session.execute(task);
    }

    public DescribeSubscribeRankingScoresResult describeSubscribeRankingScores(
            DescribeSubscribeRankingScoresRequest request
    ) {
        final AsyncResult<DescribeSubscribeRankingScoresResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribeRankingScoresAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribeRankingScoresByUserIdTask extends Gs2RestSessionTask<DescribeSubscribeRankingScoresByUserIdResult> {
        private DescribeSubscribeRankingScoresByUserIdRequest request;

        public DescribeSubscribeRankingScoresByUserIdTask(
            DescribeSubscribeRankingScoresByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscribeRankingScoresByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSubscribeRankingScoresByUserIdResult parse(JsonNode data) {
            return DescribeSubscribeRankingScoresByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/subscribe";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRankingName() != null) {
                queryStrings.add("rankingName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRankingName()))));
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

    public void describeSubscribeRankingScoresByUserIdAsync(
            DescribeSubscribeRankingScoresByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscribeRankingScoresByUserIdResult>> callback
    ) {
        DescribeSubscribeRankingScoresByUserIdTask task = new DescribeSubscribeRankingScoresByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeSubscribeRankingScoresByUserIdResult describeSubscribeRankingScoresByUserId(
            DescribeSubscribeRankingScoresByUserIdRequest request
    ) {
        final AsyncResult<DescribeSubscribeRankingScoresByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribeRankingScoresByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PutSubscribeRankingScoreTask extends Gs2RestSessionTask<PutSubscribeRankingScoreResult> {
        private PutSubscribeRankingScoreRequest request;

        public PutSubscribeRankingScoreTask(
            PutSubscribeRankingScoreRequest request,
            AsyncAction<AsyncResult<PutSubscribeRankingScoreResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PutSubscribeRankingScoreResult parse(JsonNode data) {
            return PutSubscribeRankingScoreResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/score/subscribe/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("score", request.getScore());
                    put("metadata", request.getMetadata());
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

    public void putSubscribeRankingScoreAsync(
            PutSubscribeRankingScoreRequest request,
            AsyncAction<AsyncResult<PutSubscribeRankingScoreResult>> callback
    ) {
        PutSubscribeRankingScoreTask task = new PutSubscribeRankingScoreTask(request, callback);
        session.execute(task);
    }

    public PutSubscribeRankingScoreResult putSubscribeRankingScore(
            PutSubscribeRankingScoreRequest request
    ) {
        final AsyncResult<PutSubscribeRankingScoreResult>[] resultAsyncResult = new AsyncResult[]{null};
        putSubscribeRankingScoreAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PutSubscribeRankingScoreByUserIdTask extends Gs2RestSessionTask<PutSubscribeRankingScoreByUserIdResult> {
        private PutSubscribeRankingScoreByUserIdRequest request;

        public PutSubscribeRankingScoreByUserIdTask(
            PutSubscribeRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<PutSubscribeRankingScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PutSubscribeRankingScoreByUserIdResult parse(JsonNode data) {
            return PutSubscribeRankingScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/subscribe/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("score", request.getScore());
                    put("metadata", request.getMetadata());
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

    public void putSubscribeRankingScoreByUserIdAsync(
            PutSubscribeRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<PutSubscribeRankingScoreByUserIdResult>> callback
    ) {
        PutSubscribeRankingScoreByUserIdTask task = new PutSubscribeRankingScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public PutSubscribeRankingScoreByUserIdResult putSubscribeRankingScoreByUserId(
            PutSubscribeRankingScoreByUserIdRequest request
    ) {
        final AsyncResult<PutSubscribeRankingScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        putSubscribeRankingScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscribeRankingScoreTask extends Gs2RestSessionTask<GetSubscribeRankingScoreResult> {
        private GetSubscribeRankingScoreRequest request;

        public GetSubscribeRankingScoreTask(
            GetSubscribeRankingScoreRequest request,
            AsyncAction<AsyncResult<GetSubscribeRankingScoreResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSubscribeRankingScoreResult parse(JsonNode data) {
            return GetSubscribeRankingScoreResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/score/subscribe/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getSubscribeRankingScoreAsync(
            GetSubscribeRankingScoreRequest request,
            AsyncAction<AsyncResult<GetSubscribeRankingScoreResult>> callback
    ) {
        GetSubscribeRankingScoreTask task = new GetSubscribeRankingScoreTask(request, callback);
        session.execute(task);
    }

    public GetSubscribeRankingScoreResult getSubscribeRankingScore(
            GetSubscribeRankingScoreRequest request
    ) {
        final AsyncResult<GetSubscribeRankingScoreResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscribeRankingScoreAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscribeRankingScoreByUserIdTask extends Gs2RestSessionTask<GetSubscribeRankingScoreByUserIdResult> {
        private GetSubscribeRankingScoreByUserIdRequest request;

        public GetSubscribeRankingScoreByUserIdTask(
            GetSubscribeRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<GetSubscribeRankingScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSubscribeRankingScoreByUserIdResult parse(JsonNode data) {
            return GetSubscribeRankingScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/subscribe/{rankingName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void getSubscribeRankingScoreByUserIdAsync(
            GetSubscribeRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<GetSubscribeRankingScoreByUserIdResult>> callback
    ) {
        GetSubscribeRankingScoreByUserIdTask task = new GetSubscribeRankingScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetSubscribeRankingScoreByUserIdResult getSubscribeRankingScoreByUserId(
            GetSubscribeRankingScoreByUserIdRequest request
    ) {
        final AsyncResult<GetSubscribeRankingScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscribeRankingScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteSubscribeRankingScoreByUserIdTask extends Gs2RestSessionTask<DeleteSubscribeRankingScoreByUserIdResult> {
        private DeleteSubscribeRankingScoreByUserIdRequest request;

        public DeleteSubscribeRankingScoreByUserIdTask(
            DeleteSubscribeRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteSubscribeRankingScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteSubscribeRankingScoreByUserIdResult parse(JsonNode data) {
            return DeleteSubscribeRankingScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/subscribe/{rankingName}/{season}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void deleteSubscribeRankingScoreByUserIdAsync(
            DeleteSubscribeRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteSubscribeRankingScoreByUserIdResult>> callback
    ) {
        DeleteSubscribeRankingScoreByUserIdTask task = new DeleteSubscribeRankingScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteSubscribeRankingScoreByUserIdResult deleteSubscribeRankingScoreByUserId(
            DeleteSubscribeRankingScoreByUserIdRequest request
    ) {
        final AsyncResult<DeleteSubscribeRankingScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteSubscribeRankingScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifySubscribeRankingScoreTask extends Gs2RestSessionTask<VerifySubscribeRankingScoreResult> {
        private VerifySubscribeRankingScoreRequest request;

        public VerifySubscribeRankingScoreTask(
            VerifySubscribeRankingScoreRequest request,
            AsyncAction<AsyncResult<VerifySubscribeRankingScoreResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifySubscribeRankingScoreResult parse(JsonNode data) {
            return VerifySubscribeRankingScoreResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/subscribe/{rankingName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("season", request.getSeason());
                    put("score", request.getScore());
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

    public void verifySubscribeRankingScoreAsync(
            VerifySubscribeRankingScoreRequest request,
            AsyncAction<AsyncResult<VerifySubscribeRankingScoreResult>> callback
    ) {
        VerifySubscribeRankingScoreTask task = new VerifySubscribeRankingScoreTask(request, callback);
        session.execute(task);
    }

    public VerifySubscribeRankingScoreResult verifySubscribeRankingScore(
            VerifySubscribeRankingScoreRequest request
    ) {
        final AsyncResult<VerifySubscribeRankingScoreResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifySubscribeRankingScoreAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifySubscribeRankingScoreByUserIdTask extends Gs2RestSessionTask<VerifySubscribeRankingScoreByUserIdResult> {
        private VerifySubscribeRankingScoreByUserIdRequest request;

        public VerifySubscribeRankingScoreByUserIdTask(
            VerifySubscribeRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<VerifySubscribeRankingScoreByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifySubscribeRankingScoreByUserIdResult parse(JsonNode data) {
            return VerifySubscribeRankingScoreByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/score/subscribe/{rankingName}/verify/{verifyType}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{verifyType}", this.request.getVerifyType() == null || this.request.getVerifyType().length() == 0 ? "null" : String.valueOf(this.request.getVerifyType()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("season", request.getSeason());
                    put("score", request.getScore());
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void verifySubscribeRankingScoreByUserIdAsync(
            VerifySubscribeRankingScoreByUserIdRequest request,
            AsyncAction<AsyncResult<VerifySubscribeRankingScoreByUserIdResult>> callback
    ) {
        VerifySubscribeRankingScoreByUserIdTask task = new VerifySubscribeRankingScoreByUserIdTask(request, callback);
        session.execute(task);
    }

    public VerifySubscribeRankingScoreByUserIdResult verifySubscribeRankingScoreByUserId(
            VerifySubscribeRankingScoreByUserIdRequest request
    ) {
        final AsyncResult<VerifySubscribeRankingScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifySubscribeRankingScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class VerifySubscribeRankingScoreByStampTaskTask extends Gs2RestSessionTask<VerifySubscribeRankingScoreByStampTaskResult> {
        private VerifySubscribeRankingScoreByStampTaskRequest request;

        public VerifySubscribeRankingScoreByStampTaskTask(
            VerifySubscribeRankingScoreByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifySubscribeRankingScoreByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifySubscribeRankingScoreByStampTaskResult parse(JsonNode data) {
            return VerifySubscribeRankingScoreByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/subscribe/score/verify";

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

    public void verifySubscribeRankingScoreByStampTaskAsync(
            VerifySubscribeRankingScoreByStampTaskRequest request,
            AsyncAction<AsyncResult<VerifySubscribeRankingScoreByStampTaskResult>> callback
    ) {
        VerifySubscribeRankingScoreByStampTaskTask task = new VerifySubscribeRankingScoreByStampTaskTask(request, callback);
        session.execute(task);
    }

    public VerifySubscribeRankingScoreByStampTaskResult verifySubscribeRankingScoreByStampTask(
            VerifySubscribeRankingScoreByStampTaskRequest request
    ) {
        final AsyncResult<VerifySubscribeRankingScoreByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifySubscribeRankingScoreByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribeRankingsTask extends Gs2RestSessionTask<DescribeSubscribeRankingsResult> {
        private DescribeSubscribeRankingsRequest request;

        public DescribeSubscribeRankingsTask(
            DescribeSubscribeRankingsRequest request,
            AsyncAction<AsyncResult<DescribeSubscribeRankingsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSubscribeRankingsResult parse(JsonNode data) {
            return DescribeSubscribeRankingsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ranking/subscribe/{rankingName}/user/me";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void describeSubscribeRankingsAsync(
            DescribeSubscribeRankingsRequest request,
            AsyncAction<AsyncResult<DescribeSubscribeRankingsResult>> callback
    ) {
        DescribeSubscribeRankingsTask task = new DescribeSubscribeRankingsTask(request, callback);
        session.execute(task);
    }

    public DescribeSubscribeRankingsResult describeSubscribeRankings(
            DescribeSubscribeRankingsRequest request
    ) {
        final AsyncResult<DescribeSubscribeRankingsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribeRankingsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribeRankingsByUserIdTask extends Gs2RestSessionTask<DescribeSubscribeRankingsByUserIdResult> {
        private DescribeSubscribeRankingsByUserIdRequest request;

        public DescribeSubscribeRankingsByUserIdTask(
            DescribeSubscribeRankingsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscribeRankingsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSubscribeRankingsByUserIdResult parse(JsonNode data) {
            return DescribeSubscribeRankingsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ranking/subscribe/{rankingName}/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
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

    public void describeSubscribeRankingsByUserIdAsync(
            DescribeSubscribeRankingsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscribeRankingsByUserIdResult>> callback
    ) {
        DescribeSubscribeRankingsByUserIdTask task = new DescribeSubscribeRankingsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeSubscribeRankingsByUserIdResult describeSubscribeRankingsByUserId(
            DescribeSubscribeRankingsByUserIdRequest request
    ) {
        final AsyncResult<DescribeSubscribeRankingsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribeRankingsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscribeRankingTask extends Gs2RestSessionTask<GetSubscribeRankingResult> {
        private GetSubscribeRankingRequest request;

        public GetSubscribeRankingTask(
            GetSubscribeRankingRequest request,
            AsyncAction<AsyncResult<GetSubscribeRankingResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSubscribeRankingResult parse(JsonNode data) {
            return GetSubscribeRankingResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ranking/subscribe/{rankingName}/user/me/rank";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
            }
            if (this.request.getScorerUserId() != null) {
                queryStrings.add("scorerUserId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getScorerUserId()))));
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

    public void getSubscribeRankingAsync(
            GetSubscribeRankingRequest request,
            AsyncAction<AsyncResult<GetSubscribeRankingResult>> callback
    ) {
        GetSubscribeRankingTask task = new GetSubscribeRankingTask(request, callback);
        session.execute(task);
    }

    public GetSubscribeRankingResult getSubscribeRanking(
            GetSubscribeRankingRequest request
    ) {
        final AsyncResult<GetSubscribeRankingResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscribeRankingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscribeRankingByUserIdTask extends Gs2RestSessionTask<GetSubscribeRankingByUserIdResult> {
        private GetSubscribeRankingByUserIdRequest request;

        public GetSubscribeRankingByUserIdTask(
            GetSubscribeRankingByUserIdRequest request,
            AsyncAction<AsyncResult<GetSubscribeRankingByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSubscribeRankingByUserIdResult parse(JsonNode data) {
            return GetSubscribeRankingByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/ranking/subscribe/{rankingName}/user/{userId}/rank";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getSeason() != null) {
                queryStrings.add("season=" + String.valueOf(this.request.getSeason()));
            }
            if (this.request.getScorerUserId() != null) {
                queryStrings.add("scorerUserId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getScorerUserId()))));
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

    public void getSubscribeRankingByUserIdAsync(
            GetSubscribeRankingByUserIdRequest request,
            AsyncAction<AsyncResult<GetSubscribeRankingByUserIdResult>> callback
    ) {
        GetSubscribeRankingByUserIdTask task = new GetSubscribeRankingByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetSubscribeRankingByUserIdResult getSubscribeRankingByUserId(
            GetSubscribeRankingByUserIdRequest request
    ) {
        final AsyncResult<GetSubscribeRankingByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscribeRankingByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "ranking2")
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

    class GetCurrentRankingMasterTask extends Gs2RestSessionTask<GetCurrentRankingMasterResult> {
        private GetCurrentRankingMasterRequest request;

        public GetCurrentRankingMasterTask(
            GetCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentRankingMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentRankingMasterResult parse(JsonNode data) {
            return GetCurrentRankingMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
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

    public void getCurrentRankingMasterAsync(
            GetCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentRankingMasterResult>> callback
    ) {
        GetCurrentRankingMasterTask task = new GetCurrentRankingMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentRankingMasterResult getCurrentRankingMaster(
            GetCurrentRankingMasterRequest request
    ) {
        final AsyncResult<GetCurrentRankingMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentRankingMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentRankingMasterTask extends Gs2RestSessionTask<UpdateCurrentRankingMasterResult> {
        private UpdateCurrentRankingMasterRequest request;

        public UpdateCurrentRankingMasterTask(
            UpdateCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRankingMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentRankingMasterResult parse(JsonNode data) {
            return UpdateCurrentRankingMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
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

    public void updateCurrentRankingMasterAsync(
            UpdateCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRankingMasterResult>> callback
    ) {
        UpdateCurrentRankingMasterTask task = new UpdateCurrentRankingMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentRankingMasterResult updateCurrentRankingMaster(
            UpdateCurrentRankingMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentRankingMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentRankingMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentRankingMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentRankingMasterFromGitHubResult> {
        private UpdateCurrentRankingMasterFromGitHubRequest request;

        public UpdateCurrentRankingMasterFromGitHubTask(
            UpdateCurrentRankingMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRankingMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentRankingMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentRankingMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
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

    public void updateCurrentRankingMasterFromGitHubAsync(
            UpdateCurrentRankingMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRankingMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentRankingMasterFromGitHubTask task = new UpdateCurrentRankingMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentRankingMasterFromGitHubResult updateCurrentRankingMasterFromGitHub(
            UpdateCurrentRankingMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentRankingMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentRankingMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscribeTask extends Gs2RestSessionTask<GetSubscribeResult> {
        private GetSubscribeRequest request;

        public GetSubscribeTask(
            GetSubscribeRequest request,
            AsyncAction<AsyncResult<GetSubscribeResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSubscribeResult parse(JsonNode data) {
            return GetSubscribeResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscribe/{rankingName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void getSubscribeAsync(
            GetSubscribeRequest request,
            AsyncAction<AsyncResult<GetSubscribeResult>> callback
    ) {
        GetSubscribeTask task = new GetSubscribeTask(request, callback);
        session.execute(task);
    }

    public GetSubscribeResult getSubscribe(
            GetSubscribeRequest request
    ) {
        final AsyncResult<GetSubscribeResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscribeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscribeByUserIdTask extends Gs2RestSessionTask<GetSubscribeByUserIdResult> {
        private GetSubscribeByUserIdRequest request;

        public GetSubscribeByUserIdTask(
            GetSubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<GetSubscribeByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSubscribeByUserIdResult parse(JsonNode data) {
            return GetSubscribeByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscribe/{rankingName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null || this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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

    public void getSubscribeByUserIdAsync(
            GetSubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<GetSubscribeByUserIdResult>> callback
    ) {
        GetSubscribeByUserIdTask task = new GetSubscribeByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetSubscribeByUserIdResult getSubscribeByUserId(
            GetSubscribeByUserIdRequest request
    ) {
        final AsyncResult<GetSubscribeByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscribeByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteSubscribeTask extends Gs2RestSessionTask<DeleteSubscribeResult> {
        private DeleteSubscribeRequest request;

        public DeleteSubscribeTask(
            DeleteSubscribeRequest request,
            AsyncAction<AsyncResult<DeleteSubscribeResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteSubscribeResult parse(JsonNode data) {
            return DeleteSubscribeResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscribe/{rankingName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
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

    public void deleteSubscribeAsync(
            DeleteSubscribeRequest request,
            AsyncAction<AsyncResult<DeleteSubscribeResult>> callback
    ) {
        DeleteSubscribeTask task = new DeleteSubscribeTask(request, callback);
        session.execute(task);
    }

    public DeleteSubscribeResult deleteSubscribe(
            DeleteSubscribeRequest request
    ) {
        final AsyncResult<DeleteSubscribeResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteSubscribeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteSubscribeByUserIdTask extends Gs2RestSessionTask<DeleteSubscribeByUserIdResult> {
        private DeleteSubscribeByUserIdRequest request;

        public DeleteSubscribeByUserIdTask(
            DeleteSubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteSubscribeByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteSubscribeByUserIdResult parse(JsonNode data) {
            return DeleteSubscribeByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking2")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscribe/{rankingName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rankingName}", this.request.getRankingName() == null || this.request.getRankingName().length() == 0 ? "null" : String.valueOf(this.request.getRankingName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
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

    public void deleteSubscribeByUserIdAsync(
            DeleteSubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteSubscribeByUserIdResult>> callback
    ) {
        DeleteSubscribeByUserIdTask task = new DeleteSubscribeByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteSubscribeByUserIdResult deleteSubscribeByUserId(
            DeleteSubscribeByUserIdRequest request
    ) {
        final AsyncResult<DeleteSubscribeByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteSubscribeByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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