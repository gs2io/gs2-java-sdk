
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

package io.gs2.showcase;

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
import io.gs2.showcase.request.*;
import io.gs2.showcase.result.*;
import io.gs2.showcase.model.*;

public class Gs2ShowcaseRestClient extends AbstractGs2Client<Gs2ShowcaseRestClient> {

	public Gs2ShowcaseRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "showcase")
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("buyScript", request.getBuyScript() != null ? request.getBuyScript().toJson() : null);
                    put("queueNamespaceId", request.getQueueNamespaceId());
                    put("keyId", request.getKeyId());
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
                .replace("{service}", "showcase")
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
                .replace("{service}", "showcase")
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("buyScript", request.getBuyScript() != null ? request.getBuyScript().toJson() : null);
                    put("logSetting", request.getLogSetting() != null ? request.getLogSetting().toJson() : null);
                    put("queueNamespaceId", request.getQueueNamespaceId());
                    put("keyId", request.getKeyId());
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
                .replace("{service}", "showcase")
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
                .replace("{service}", "showcase")
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
                .replace("{service}", "showcase")
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
                .replace("{service}", "showcase")
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
                .replace("{service}", "showcase")
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
                .replace("{service}", "showcase")
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
                .replace("{service}", "showcase")
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
                .replace("{service}", "showcase")
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

    class DescribeSalesItemMastersTask extends Gs2RestSessionTask<DescribeSalesItemMastersResult> {
        private DescribeSalesItemMastersRequest request;

        public DescribeSalesItemMastersTask(
            DescribeSalesItemMastersRequest request,
            AsyncAction<AsyncResult<DescribeSalesItemMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSalesItemMastersResult parse(JsonNode data) {
            return DescribeSalesItemMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/salesItem";

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

    public void describeSalesItemMastersAsync(
            DescribeSalesItemMastersRequest request,
            AsyncAction<AsyncResult<DescribeSalesItemMastersResult>> callback
    ) {
        DescribeSalesItemMastersTask task = new DescribeSalesItemMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeSalesItemMastersResult describeSalesItemMasters(
            DescribeSalesItemMastersRequest request
    ) {
        final AsyncResult<DescribeSalesItemMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSalesItemMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateSalesItemMasterTask extends Gs2RestSessionTask<CreateSalesItemMasterResult> {
        private CreateSalesItemMasterRequest request;

        public CreateSalesItemMasterTask(
            CreateSalesItemMasterRequest request,
            AsyncAction<AsyncResult<CreateSalesItemMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateSalesItemMasterResult parse(JsonNode data) {
            return CreateSalesItemMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/salesItem";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("verifyActions", request.getVerifyActions() == null ? null :
                        request.getVerifyActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("consumeActions", request.getConsumeActions() == null ? null :
                        request.getConsumeActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("acquireActions", request.getAcquireActions() == null ? null :
                        request.getAcquireActions().stream().map(item -> {
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

    public void createSalesItemMasterAsync(
            CreateSalesItemMasterRequest request,
            AsyncAction<AsyncResult<CreateSalesItemMasterResult>> callback
    ) {
        CreateSalesItemMasterTask task = new CreateSalesItemMasterTask(request, callback);
        session.execute(task);
    }

    public CreateSalesItemMasterResult createSalesItemMaster(
            CreateSalesItemMasterRequest request
    ) {
        final AsyncResult<CreateSalesItemMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createSalesItemMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSalesItemMasterTask extends Gs2RestSessionTask<GetSalesItemMasterResult> {
        private GetSalesItemMasterRequest request;

        public GetSalesItemMasterTask(
            GetSalesItemMasterRequest request,
            AsyncAction<AsyncResult<GetSalesItemMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSalesItemMasterResult parse(JsonNode data) {
            return GetSalesItemMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/salesItem/{salesItemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{salesItemName}", this.request.getSalesItemName() == null || this.request.getSalesItemName().length() == 0 ? "null" : String.valueOf(this.request.getSalesItemName()));

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

    public void getSalesItemMasterAsync(
            GetSalesItemMasterRequest request,
            AsyncAction<AsyncResult<GetSalesItemMasterResult>> callback
    ) {
        GetSalesItemMasterTask task = new GetSalesItemMasterTask(request, callback);
        session.execute(task);
    }

    public GetSalesItemMasterResult getSalesItemMaster(
            GetSalesItemMasterRequest request
    ) {
        final AsyncResult<GetSalesItemMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSalesItemMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateSalesItemMasterTask extends Gs2RestSessionTask<UpdateSalesItemMasterResult> {
        private UpdateSalesItemMasterRequest request;

        public UpdateSalesItemMasterTask(
            UpdateSalesItemMasterRequest request,
            AsyncAction<AsyncResult<UpdateSalesItemMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateSalesItemMasterResult parse(JsonNode data) {
            return UpdateSalesItemMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/salesItem/{salesItemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{salesItemName}", this.request.getSalesItemName() == null || this.request.getSalesItemName().length() == 0 ? "null" : String.valueOf(this.request.getSalesItemName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("verifyActions", request.getVerifyActions() == null ? null :
                        request.getVerifyActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("consumeActions", request.getConsumeActions() == null ? null :
                        request.getConsumeActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("acquireActions", request.getAcquireActions() == null ? null :
                        request.getAcquireActions().stream().map(item -> {
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

    public void updateSalesItemMasterAsync(
            UpdateSalesItemMasterRequest request,
            AsyncAction<AsyncResult<UpdateSalesItemMasterResult>> callback
    ) {
        UpdateSalesItemMasterTask task = new UpdateSalesItemMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateSalesItemMasterResult updateSalesItemMaster(
            UpdateSalesItemMasterRequest request
    ) {
        final AsyncResult<UpdateSalesItemMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateSalesItemMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteSalesItemMasterTask extends Gs2RestSessionTask<DeleteSalesItemMasterResult> {
        private DeleteSalesItemMasterRequest request;

        public DeleteSalesItemMasterTask(
            DeleteSalesItemMasterRequest request,
            AsyncAction<AsyncResult<DeleteSalesItemMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteSalesItemMasterResult parse(JsonNode data) {
            return DeleteSalesItemMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/salesItem/{salesItemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{salesItemName}", this.request.getSalesItemName() == null || this.request.getSalesItemName().length() == 0 ? "null" : String.valueOf(this.request.getSalesItemName()));

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

    public void deleteSalesItemMasterAsync(
            DeleteSalesItemMasterRequest request,
            AsyncAction<AsyncResult<DeleteSalesItemMasterResult>> callback
    ) {
        DeleteSalesItemMasterTask task = new DeleteSalesItemMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteSalesItemMasterResult deleteSalesItemMaster(
            DeleteSalesItemMasterRequest request
    ) {
        final AsyncResult<DeleteSalesItemMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteSalesItemMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSalesItemGroupMastersTask extends Gs2RestSessionTask<DescribeSalesItemGroupMastersResult> {
        private DescribeSalesItemGroupMastersRequest request;

        public DescribeSalesItemGroupMastersTask(
            DescribeSalesItemGroupMastersRequest request,
            AsyncAction<AsyncResult<DescribeSalesItemGroupMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeSalesItemGroupMastersResult parse(JsonNode data) {
            return DescribeSalesItemGroupMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group";

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

    public void describeSalesItemGroupMastersAsync(
            DescribeSalesItemGroupMastersRequest request,
            AsyncAction<AsyncResult<DescribeSalesItemGroupMastersResult>> callback
    ) {
        DescribeSalesItemGroupMastersTask task = new DescribeSalesItemGroupMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeSalesItemGroupMastersResult describeSalesItemGroupMasters(
            DescribeSalesItemGroupMastersRequest request
    ) {
        final AsyncResult<DescribeSalesItemGroupMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSalesItemGroupMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateSalesItemGroupMasterTask extends Gs2RestSessionTask<CreateSalesItemGroupMasterResult> {
        private CreateSalesItemGroupMasterRequest request;

        public CreateSalesItemGroupMasterTask(
            CreateSalesItemGroupMasterRequest request,
            AsyncAction<AsyncResult<CreateSalesItemGroupMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateSalesItemGroupMasterResult parse(JsonNode data) {
            return CreateSalesItemGroupMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("salesItemNames", request.getSalesItemNames() == null ? null :
                        request.getSalesItemNames().stream().map(item -> {
                            return item;
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

    public void createSalesItemGroupMasterAsync(
            CreateSalesItemGroupMasterRequest request,
            AsyncAction<AsyncResult<CreateSalesItemGroupMasterResult>> callback
    ) {
        CreateSalesItemGroupMasterTask task = new CreateSalesItemGroupMasterTask(request, callback);
        session.execute(task);
    }

    public CreateSalesItemGroupMasterResult createSalesItemGroupMaster(
            CreateSalesItemGroupMasterRequest request
    ) {
        final AsyncResult<CreateSalesItemGroupMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createSalesItemGroupMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSalesItemGroupMasterTask extends Gs2RestSessionTask<GetSalesItemGroupMasterResult> {
        private GetSalesItemGroupMasterRequest request;

        public GetSalesItemGroupMasterTask(
            GetSalesItemGroupMasterRequest request,
            AsyncAction<AsyncResult<GetSalesItemGroupMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetSalesItemGroupMasterResult parse(JsonNode data) {
            return GetSalesItemGroupMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{salesItemGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{salesItemGroupName}", this.request.getSalesItemGroupName() == null || this.request.getSalesItemGroupName().length() == 0 ? "null" : String.valueOf(this.request.getSalesItemGroupName()));

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

    public void getSalesItemGroupMasterAsync(
            GetSalesItemGroupMasterRequest request,
            AsyncAction<AsyncResult<GetSalesItemGroupMasterResult>> callback
    ) {
        GetSalesItemGroupMasterTask task = new GetSalesItemGroupMasterTask(request, callback);
        session.execute(task);
    }

    public GetSalesItemGroupMasterResult getSalesItemGroupMaster(
            GetSalesItemGroupMasterRequest request
    ) {
        final AsyncResult<GetSalesItemGroupMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSalesItemGroupMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateSalesItemGroupMasterTask extends Gs2RestSessionTask<UpdateSalesItemGroupMasterResult> {
        private UpdateSalesItemGroupMasterRequest request;

        public UpdateSalesItemGroupMasterTask(
            UpdateSalesItemGroupMasterRequest request,
            AsyncAction<AsyncResult<UpdateSalesItemGroupMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateSalesItemGroupMasterResult parse(JsonNode data) {
            return UpdateSalesItemGroupMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{salesItemGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{salesItemGroupName}", this.request.getSalesItemGroupName() == null || this.request.getSalesItemGroupName().length() == 0 ? "null" : String.valueOf(this.request.getSalesItemGroupName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("salesItemNames", request.getSalesItemNames() == null ? null :
                        request.getSalesItemNames().stream().map(item -> {
                            return item;
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

    public void updateSalesItemGroupMasterAsync(
            UpdateSalesItemGroupMasterRequest request,
            AsyncAction<AsyncResult<UpdateSalesItemGroupMasterResult>> callback
    ) {
        UpdateSalesItemGroupMasterTask task = new UpdateSalesItemGroupMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateSalesItemGroupMasterResult updateSalesItemGroupMaster(
            UpdateSalesItemGroupMasterRequest request
    ) {
        final AsyncResult<UpdateSalesItemGroupMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateSalesItemGroupMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteSalesItemGroupMasterTask extends Gs2RestSessionTask<DeleteSalesItemGroupMasterResult> {
        private DeleteSalesItemGroupMasterRequest request;

        public DeleteSalesItemGroupMasterTask(
            DeleteSalesItemGroupMasterRequest request,
            AsyncAction<AsyncResult<DeleteSalesItemGroupMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteSalesItemGroupMasterResult parse(JsonNode data) {
            return DeleteSalesItemGroupMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{salesItemGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{salesItemGroupName}", this.request.getSalesItemGroupName() == null || this.request.getSalesItemGroupName().length() == 0 ? "null" : String.valueOf(this.request.getSalesItemGroupName()));

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

    public void deleteSalesItemGroupMasterAsync(
            DeleteSalesItemGroupMasterRequest request,
            AsyncAction<AsyncResult<DeleteSalesItemGroupMasterResult>> callback
    ) {
        DeleteSalesItemGroupMasterTask task = new DeleteSalesItemGroupMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteSalesItemGroupMasterResult deleteSalesItemGroupMaster(
            DeleteSalesItemGroupMasterRequest request
    ) {
        final AsyncResult<DeleteSalesItemGroupMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteSalesItemGroupMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeShowcaseMastersTask extends Gs2RestSessionTask<DescribeShowcaseMastersResult> {
        private DescribeShowcaseMastersRequest request;

        public DescribeShowcaseMastersTask(
            DescribeShowcaseMastersRequest request,
            AsyncAction<AsyncResult<DescribeShowcaseMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeShowcaseMastersResult parse(JsonNode data) {
            return DescribeShowcaseMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/showcase";

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

    public void describeShowcaseMastersAsync(
            DescribeShowcaseMastersRequest request,
            AsyncAction<AsyncResult<DescribeShowcaseMastersResult>> callback
    ) {
        DescribeShowcaseMastersTask task = new DescribeShowcaseMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeShowcaseMastersResult describeShowcaseMasters(
            DescribeShowcaseMastersRequest request
    ) {
        final AsyncResult<DescribeShowcaseMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeShowcaseMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateShowcaseMasterTask extends Gs2RestSessionTask<CreateShowcaseMasterResult> {
        private CreateShowcaseMasterRequest request;

        public CreateShowcaseMasterTask(
            CreateShowcaseMasterRequest request,
            AsyncAction<AsyncResult<CreateShowcaseMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateShowcaseMasterResult parse(JsonNode data) {
            return CreateShowcaseMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/showcase";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("displayItems", request.getDisplayItems() == null ? null :
                        request.getDisplayItems().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("salesPeriodEventId", request.getSalesPeriodEventId());
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

    public void createShowcaseMasterAsync(
            CreateShowcaseMasterRequest request,
            AsyncAction<AsyncResult<CreateShowcaseMasterResult>> callback
    ) {
        CreateShowcaseMasterTask task = new CreateShowcaseMasterTask(request, callback);
        session.execute(task);
    }

    public CreateShowcaseMasterResult createShowcaseMaster(
            CreateShowcaseMasterRequest request
    ) {
        final AsyncResult<CreateShowcaseMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createShowcaseMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetShowcaseMasterTask extends Gs2RestSessionTask<GetShowcaseMasterResult> {
        private GetShowcaseMasterRequest request;

        public GetShowcaseMasterTask(
            GetShowcaseMasterRequest request,
            AsyncAction<AsyncResult<GetShowcaseMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetShowcaseMasterResult parse(JsonNode data) {
            return GetShowcaseMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));

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

    public void getShowcaseMasterAsync(
            GetShowcaseMasterRequest request,
            AsyncAction<AsyncResult<GetShowcaseMasterResult>> callback
    ) {
        GetShowcaseMasterTask task = new GetShowcaseMasterTask(request, callback);
        session.execute(task);
    }

    public GetShowcaseMasterResult getShowcaseMaster(
            GetShowcaseMasterRequest request
    ) {
        final AsyncResult<GetShowcaseMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getShowcaseMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateShowcaseMasterTask extends Gs2RestSessionTask<UpdateShowcaseMasterResult> {
        private UpdateShowcaseMasterRequest request;

        public UpdateShowcaseMasterTask(
            UpdateShowcaseMasterRequest request,
            AsyncAction<AsyncResult<UpdateShowcaseMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateShowcaseMasterResult parse(JsonNode data) {
            return UpdateShowcaseMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("displayItems", request.getDisplayItems() == null ? null :
                        request.getDisplayItems().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("salesPeriodEventId", request.getSalesPeriodEventId());
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

    public void updateShowcaseMasterAsync(
            UpdateShowcaseMasterRequest request,
            AsyncAction<AsyncResult<UpdateShowcaseMasterResult>> callback
    ) {
        UpdateShowcaseMasterTask task = new UpdateShowcaseMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateShowcaseMasterResult updateShowcaseMaster(
            UpdateShowcaseMasterRequest request
    ) {
        final AsyncResult<UpdateShowcaseMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateShowcaseMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteShowcaseMasterTask extends Gs2RestSessionTask<DeleteShowcaseMasterResult> {
        private DeleteShowcaseMasterRequest request;

        public DeleteShowcaseMasterTask(
            DeleteShowcaseMasterRequest request,
            AsyncAction<AsyncResult<DeleteShowcaseMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteShowcaseMasterResult parse(JsonNode data) {
            return DeleteShowcaseMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));

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

    public void deleteShowcaseMasterAsync(
            DeleteShowcaseMasterRequest request,
            AsyncAction<AsyncResult<DeleteShowcaseMasterResult>> callback
    ) {
        DeleteShowcaseMasterTask task = new DeleteShowcaseMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteShowcaseMasterResult deleteShowcaseMaster(
            DeleteShowcaseMasterRequest request
    ) {
        final AsyncResult<DeleteShowcaseMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteShowcaseMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "showcase")
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

    class GetCurrentShowcaseMasterTask extends Gs2RestSessionTask<GetCurrentShowcaseMasterResult> {
        private GetCurrentShowcaseMasterRequest request;

        public GetCurrentShowcaseMasterTask(
            GetCurrentShowcaseMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentShowcaseMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentShowcaseMasterResult parse(JsonNode data) {
            return GetCurrentShowcaseMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
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

    public void getCurrentShowcaseMasterAsync(
            GetCurrentShowcaseMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentShowcaseMasterResult>> callback
    ) {
        GetCurrentShowcaseMasterTask task = new GetCurrentShowcaseMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentShowcaseMasterResult getCurrentShowcaseMaster(
            GetCurrentShowcaseMasterRequest request
    ) {
        final AsyncResult<GetCurrentShowcaseMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentShowcaseMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PreUpdateCurrentShowcaseMasterTask extends Gs2RestSessionTask<PreUpdateCurrentShowcaseMasterResult> {
        private PreUpdateCurrentShowcaseMasterRequest request;

        public PreUpdateCurrentShowcaseMasterTask(
            PreUpdateCurrentShowcaseMasterRequest request,
            AsyncAction<AsyncResult<PreUpdateCurrentShowcaseMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PreUpdateCurrentShowcaseMasterResult parse(JsonNode data) {
            return PreUpdateCurrentShowcaseMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
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

    public void preUpdateCurrentShowcaseMasterAsync(
            PreUpdateCurrentShowcaseMasterRequest request,
            AsyncAction<AsyncResult<PreUpdateCurrentShowcaseMasterResult>> callback
    ) {
        PreUpdateCurrentShowcaseMasterTask task = new PreUpdateCurrentShowcaseMasterTask(request, callback);
        session.execute(task);
    }

    public PreUpdateCurrentShowcaseMasterResult preUpdateCurrentShowcaseMaster(
            PreUpdateCurrentShowcaseMasterRequest request
    ) {
        final AsyncResult<PreUpdateCurrentShowcaseMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        preUpdateCurrentShowcaseMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentShowcaseMasterTask extends Gs2RestSessionTask<UpdateCurrentShowcaseMasterResult> {
        private UpdateCurrentShowcaseMasterRequest request;

        public UpdateCurrentShowcaseMasterTask(
            UpdateCurrentShowcaseMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentShowcaseMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentShowcaseMasterResult parse(JsonNode data) {
            return UpdateCurrentShowcaseMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {
            if (request.getSettings() != null) {
                AtomicReference<AsyncResult<PreUpdateCurrentShowcaseMasterResult>> resultAsyncResult = new AtomicReference<>();
                PreUpdateCurrentShowcaseMasterTask task = new PreUpdateCurrentShowcaseMasterTask(
                        new PreUpdateCurrentShowcaseMasterRequest()
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
                .replace("{service}", "showcase")
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

    public void updateCurrentShowcaseMasterAsync(
            UpdateCurrentShowcaseMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentShowcaseMasterResult>> callback
    ) {
        UpdateCurrentShowcaseMasterTask task = new UpdateCurrentShowcaseMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentShowcaseMasterResult updateCurrentShowcaseMaster(
            UpdateCurrentShowcaseMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentShowcaseMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentShowcaseMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentShowcaseMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentShowcaseMasterFromGitHubResult> {
        private UpdateCurrentShowcaseMasterFromGitHubRequest request;

        public UpdateCurrentShowcaseMasterFromGitHubTask(
            UpdateCurrentShowcaseMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentShowcaseMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentShowcaseMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentShowcaseMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
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

    public void updateCurrentShowcaseMasterFromGitHubAsync(
            UpdateCurrentShowcaseMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentShowcaseMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentShowcaseMasterFromGitHubTask task = new UpdateCurrentShowcaseMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentShowcaseMasterFromGitHubResult updateCurrentShowcaseMasterFromGitHub(
            UpdateCurrentShowcaseMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentShowcaseMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentShowcaseMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeShowcasesTask extends Gs2RestSessionTask<DescribeShowcasesResult> {
        private DescribeShowcasesRequest request;

        public DescribeShowcasesTask(
            DescribeShowcasesRequest request,
            AsyncAction<AsyncResult<DescribeShowcasesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeShowcasesResult parse(JsonNode data) {
            return DescribeShowcasesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/showcase";

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
            if (this.request.getAccessToken() != null) {
                builder.setHeader("X-GS2-ACCESS-TOKEN", this.request.getAccessToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void describeShowcasesAsync(
            DescribeShowcasesRequest request,
            AsyncAction<AsyncResult<DescribeShowcasesResult>> callback
    ) {
        DescribeShowcasesTask task = new DescribeShowcasesTask(request, callback);
        session.execute(task);
    }

    public DescribeShowcasesResult describeShowcases(
            DescribeShowcasesRequest request
    ) {
        final AsyncResult<DescribeShowcasesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeShowcasesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeShowcasesByUserIdTask extends Gs2RestSessionTask<DescribeShowcasesByUserIdResult> {
        private DescribeShowcasesByUserIdRequest request;

        public DescribeShowcasesByUserIdTask(
            DescribeShowcasesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeShowcasesByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeShowcasesByUserIdResult parse(JsonNode data) {
            return DescribeShowcasesByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/showcase";

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

    public void describeShowcasesByUserIdAsync(
            DescribeShowcasesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeShowcasesByUserIdResult>> callback
    ) {
        DescribeShowcasesByUserIdTask task = new DescribeShowcasesByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeShowcasesByUserIdResult describeShowcasesByUserId(
            DescribeShowcasesByUserIdRequest request
    ) {
        final AsyncResult<DescribeShowcasesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeShowcasesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetShowcaseTask extends Gs2RestSessionTask<GetShowcaseResult> {
        private GetShowcaseRequest request;

        public GetShowcaseTask(
            GetShowcaseRequest request,
            AsyncAction<AsyncResult<GetShowcaseResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetShowcaseResult parse(JsonNode data) {
            return GetShowcaseResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));

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

    public void getShowcaseAsync(
            GetShowcaseRequest request,
            AsyncAction<AsyncResult<GetShowcaseResult>> callback
    ) {
        GetShowcaseTask task = new GetShowcaseTask(request, callback);
        session.execute(task);
    }

    public GetShowcaseResult getShowcase(
            GetShowcaseRequest request
    ) {
        final AsyncResult<GetShowcaseResult>[] resultAsyncResult = new AsyncResult[]{null};
        getShowcaseAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetShowcaseByUserIdTask extends Gs2RestSessionTask<GetShowcaseByUserIdResult> {
        private GetShowcaseByUserIdRequest request;

        public GetShowcaseByUserIdTask(
            GetShowcaseByUserIdRequest request,
            AsyncAction<AsyncResult<GetShowcaseByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetShowcaseByUserIdResult parse(JsonNode data) {
            return GetShowcaseByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
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

    public void getShowcaseByUserIdAsync(
            GetShowcaseByUserIdRequest request,
            AsyncAction<AsyncResult<GetShowcaseByUserIdResult>> callback
    ) {
        GetShowcaseByUserIdTask task = new GetShowcaseByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetShowcaseByUserIdResult getShowcaseByUserId(
            GetShowcaseByUserIdRequest request
    ) {
        final AsyncResult<GetShowcaseByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getShowcaseByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class BuyTask extends Gs2RestSessionTask<BuyResult> {
        private BuyRequest request;

        public BuyTask(
            BuyRequest request,
            AsyncAction<AsyncResult<BuyResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public BuyResult parse(JsonNode data) {
            return BuyResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/showcase/{showcaseName}/{displayItemId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
            url = url.replace("{displayItemId}", this.request.getDisplayItemId() == null || this.request.getDisplayItemId().length() == 0 ? "null" : String.valueOf(this.request.getDisplayItemId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("quantity", request.getQuantity());
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

    public void buyAsync(
            BuyRequest request,
            AsyncAction<AsyncResult<BuyResult>> callback
    ) {
        BuyTask task = new BuyTask(request, callback);
        session.execute(task);
    }

    public BuyResult buy(
            BuyRequest request
    ) {
        final AsyncResult<BuyResult>[] resultAsyncResult = new AsyncResult[]{null};
        buyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class BuyByUserIdTask extends Gs2RestSessionTask<BuyByUserIdResult> {
        private BuyByUserIdRequest request;

        public BuyByUserIdTask(
            BuyByUserIdRequest request,
            AsyncAction<AsyncResult<BuyByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public BuyByUserIdResult parse(JsonNode data) {
            return BuyByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/showcase/{showcaseName}/{displayItemId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
            url = url.replace("{displayItemId}", this.request.getDisplayItemId() == null || this.request.getDisplayItemId().length() == 0 ? "null" : String.valueOf(this.request.getDisplayItemId()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("quantity", request.getQuantity());
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

    public void buyByUserIdAsync(
            BuyByUserIdRequest request,
            AsyncAction<AsyncResult<BuyByUserIdResult>> callback
    ) {
        BuyByUserIdTask task = new BuyByUserIdTask(request, callback);
        session.execute(task);
    }

    public BuyByUserIdResult buyByUserId(
            BuyByUserIdRequest request
    ) {
        final AsyncResult<BuyByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        buyByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRandomShowcaseMastersTask extends Gs2RestSessionTask<DescribeRandomShowcaseMastersResult> {
        private DescribeRandomShowcaseMastersRequest request;

        public DescribeRandomShowcaseMastersTask(
            DescribeRandomShowcaseMastersRequest request,
            AsyncAction<AsyncResult<DescribeRandomShowcaseMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRandomShowcaseMastersResult parse(JsonNode data) {
            return DescribeRandomShowcaseMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/random/showcase";

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

    public void describeRandomShowcaseMastersAsync(
            DescribeRandomShowcaseMastersRequest request,
            AsyncAction<AsyncResult<DescribeRandomShowcaseMastersResult>> callback
    ) {
        DescribeRandomShowcaseMastersTask task = new DescribeRandomShowcaseMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeRandomShowcaseMastersResult describeRandomShowcaseMasters(
            DescribeRandomShowcaseMastersRequest request
    ) {
        final AsyncResult<DescribeRandomShowcaseMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRandomShowcaseMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateRandomShowcaseMasterTask extends Gs2RestSessionTask<CreateRandomShowcaseMasterResult> {
        private CreateRandomShowcaseMasterRequest request;

        public CreateRandomShowcaseMasterTask(
            CreateRandomShowcaseMasterRequest request,
            AsyncAction<AsyncResult<CreateRandomShowcaseMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateRandomShowcaseMasterResult parse(JsonNode data) {
            return CreateRandomShowcaseMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/random/showcase";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("maximumNumberOfChoice", request.getMaximumNumberOfChoice());
                    put("displayItems", request.getDisplayItems() == null ? null :
                        request.getDisplayItems().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("baseTimestamp", request.getBaseTimestamp());
                    put("resetIntervalHours", request.getResetIntervalHours());
                    put("salesPeriodEventId", request.getSalesPeriodEventId());
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

    public void createRandomShowcaseMasterAsync(
            CreateRandomShowcaseMasterRequest request,
            AsyncAction<AsyncResult<CreateRandomShowcaseMasterResult>> callback
    ) {
        CreateRandomShowcaseMasterTask task = new CreateRandomShowcaseMasterTask(request, callback);
        session.execute(task);
    }

    public CreateRandomShowcaseMasterResult createRandomShowcaseMaster(
            CreateRandomShowcaseMasterRequest request
    ) {
        final AsyncResult<CreateRandomShowcaseMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createRandomShowcaseMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRandomShowcaseMasterTask extends Gs2RestSessionTask<GetRandomShowcaseMasterResult> {
        private GetRandomShowcaseMasterRequest request;

        public GetRandomShowcaseMasterTask(
            GetRandomShowcaseMasterRequest request,
            AsyncAction<AsyncResult<GetRandomShowcaseMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRandomShowcaseMasterResult parse(JsonNode data) {
            return GetRandomShowcaseMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/random/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));

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

    public void getRandomShowcaseMasterAsync(
            GetRandomShowcaseMasterRequest request,
            AsyncAction<AsyncResult<GetRandomShowcaseMasterResult>> callback
    ) {
        GetRandomShowcaseMasterTask task = new GetRandomShowcaseMasterTask(request, callback);
        session.execute(task);
    }

    public GetRandomShowcaseMasterResult getRandomShowcaseMaster(
            GetRandomShowcaseMasterRequest request
    ) {
        final AsyncResult<GetRandomShowcaseMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRandomShowcaseMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateRandomShowcaseMasterTask extends Gs2RestSessionTask<UpdateRandomShowcaseMasterResult> {
        private UpdateRandomShowcaseMasterRequest request;

        public UpdateRandomShowcaseMasterTask(
            UpdateRandomShowcaseMasterRequest request,
            AsyncAction<AsyncResult<UpdateRandomShowcaseMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateRandomShowcaseMasterResult parse(JsonNode data) {
            return UpdateRandomShowcaseMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/random/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("maximumNumberOfChoice", request.getMaximumNumberOfChoice());
                    put("displayItems", request.getDisplayItems() == null ? null :
                        request.getDisplayItems().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("baseTimestamp", request.getBaseTimestamp());
                    put("resetIntervalHours", request.getResetIntervalHours());
                    put("salesPeriodEventId", request.getSalesPeriodEventId());
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

    public void updateRandomShowcaseMasterAsync(
            UpdateRandomShowcaseMasterRequest request,
            AsyncAction<AsyncResult<UpdateRandomShowcaseMasterResult>> callback
    ) {
        UpdateRandomShowcaseMasterTask task = new UpdateRandomShowcaseMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateRandomShowcaseMasterResult updateRandomShowcaseMaster(
            UpdateRandomShowcaseMasterRequest request
    ) {
        final AsyncResult<UpdateRandomShowcaseMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateRandomShowcaseMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRandomShowcaseMasterTask extends Gs2RestSessionTask<DeleteRandomShowcaseMasterResult> {
        private DeleteRandomShowcaseMasterRequest request;

        public DeleteRandomShowcaseMasterTask(
            DeleteRandomShowcaseMasterRequest request,
            AsyncAction<AsyncResult<DeleteRandomShowcaseMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteRandomShowcaseMasterResult parse(JsonNode data) {
            return DeleteRandomShowcaseMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/random/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));

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

    public void deleteRandomShowcaseMasterAsync(
            DeleteRandomShowcaseMasterRequest request,
            AsyncAction<AsyncResult<DeleteRandomShowcaseMasterResult>> callback
    ) {
        DeleteRandomShowcaseMasterTask task = new DeleteRandomShowcaseMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteRandomShowcaseMasterResult deleteRandomShowcaseMaster(
            DeleteRandomShowcaseMasterRequest request
    ) {
        final AsyncResult<DeleteRandomShowcaseMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRandomShowcaseMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class IncrementPurchaseCountTask extends Gs2RestSessionTask<IncrementPurchaseCountResult> {
        private IncrementPurchaseCountRequest request;

        public IncrementPurchaseCountTask(
            IncrementPurchaseCountRequest request,
            AsyncAction<AsyncResult<IncrementPurchaseCountResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IncrementPurchaseCountResult parse(JsonNode data) {
            return IncrementPurchaseCountResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/random/showcase/user/me/status/{showcaseName}/{displayItemName}/purchase/count";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
            url = url.replace("{displayItemName}", this.request.getDisplayItemName() == null || this.request.getDisplayItemName().length() == 0 ? "null" : String.valueOf(this.request.getDisplayItemName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("count", request.getCount());
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

    public void incrementPurchaseCountAsync(
            IncrementPurchaseCountRequest request,
            AsyncAction<AsyncResult<IncrementPurchaseCountResult>> callback
    ) {
        IncrementPurchaseCountTask task = new IncrementPurchaseCountTask(request, callback);
        session.execute(task);
    }

    public IncrementPurchaseCountResult incrementPurchaseCount(
            IncrementPurchaseCountRequest request
    ) {
        final AsyncResult<IncrementPurchaseCountResult>[] resultAsyncResult = new AsyncResult[]{null};
        incrementPurchaseCountAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class IncrementPurchaseCountByUserIdTask extends Gs2RestSessionTask<IncrementPurchaseCountByUserIdResult> {
        private IncrementPurchaseCountByUserIdRequest request;

        public IncrementPurchaseCountByUserIdTask(
            IncrementPurchaseCountByUserIdRequest request,
            AsyncAction<AsyncResult<IncrementPurchaseCountByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IncrementPurchaseCountByUserIdResult parse(JsonNode data) {
            return IncrementPurchaseCountByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/random/showcase/user/{userId}/status/{showcaseName}/{displayItemName}/purchase/count";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
            url = url.replace("{displayItemName}", this.request.getDisplayItemName() == null || this.request.getDisplayItemName().length() == 0 ? "null" : String.valueOf(this.request.getDisplayItemName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("count", request.getCount());
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

    public void incrementPurchaseCountByUserIdAsync(
            IncrementPurchaseCountByUserIdRequest request,
            AsyncAction<AsyncResult<IncrementPurchaseCountByUserIdResult>> callback
    ) {
        IncrementPurchaseCountByUserIdTask task = new IncrementPurchaseCountByUserIdTask(request, callback);
        session.execute(task);
    }

    public IncrementPurchaseCountByUserIdResult incrementPurchaseCountByUserId(
            IncrementPurchaseCountByUserIdRequest request
    ) {
        final AsyncResult<IncrementPurchaseCountByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        incrementPurchaseCountByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DecrementPurchaseCountByUserIdTask extends Gs2RestSessionTask<DecrementPurchaseCountByUserIdResult> {
        private DecrementPurchaseCountByUserIdRequest request;

        public DecrementPurchaseCountByUserIdTask(
            DecrementPurchaseCountByUserIdRequest request,
            AsyncAction<AsyncResult<DecrementPurchaseCountByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DecrementPurchaseCountByUserIdResult parse(JsonNode data) {
            return DecrementPurchaseCountByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/random/showcase/user/{userId}/status/{showcaseName}/{displayItemName}/purchase/count/decrease";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
            url = url.replace("{displayItemName}", this.request.getDisplayItemName() == null || this.request.getDisplayItemName().length() == 0 ? "null" : String.valueOf(this.request.getDisplayItemName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("count", request.getCount());
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

    public void decrementPurchaseCountByUserIdAsync(
            DecrementPurchaseCountByUserIdRequest request,
            AsyncAction<AsyncResult<DecrementPurchaseCountByUserIdResult>> callback
    ) {
        DecrementPurchaseCountByUserIdTask task = new DecrementPurchaseCountByUserIdTask(request, callback);
        session.execute(task);
    }

    public DecrementPurchaseCountByUserIdResult decrementPurchaseCountByUserId(
            DecrementPurchaseCountByUserIdRequest request
    ) {
        final AsyncResult<DecrementPurchaseCountByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        decrementPurchaseCountByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class IncrementPurchaseCountByStampTaskTask extends Gs2RestSessionTask<IncrementPurchaseCountByStampTaskResult> {
        private IncrementPurchaseCountByStampTaskRequest request;

        public IncrementPurchaseCountByStampTaskTask(
            IncrementPurchaseCountByStampTaskRequest request,
            AsyncAction<AsyncResult<IncrementPurchaseCountByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IncrementPurchaseCountByStampTaskResult parse(JsonNode data) {
            return IncrementPurchaseCountByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/random/showcase/status/purchase/count";

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

    public void incrementPurchaseCountByStampTaskAsync(
            IncrementPurchaseCountByStampTaskRequest request,
            AsyncAction<AsyncResult<IncrementPurchaseCountByStampTaskResult>> callback
    ) {
        IncrementPurchaseCountByStampTaskTask task = new IncrementPurchaseCountByStampTaskTask(request, callback);
        session.execute(task);
    }

    public IncrementPurchaseCountByStampTaskResult incrementPurchaseCountByStampTask(
            IncrementPurchaseCountByStampTaskRequest request
    ) {
        final AsyncResult<IncrementPurchaseCountByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        incrementPurchaseCountByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DecrementPurchaseCountByStampSheetTask extends Gs2RestSessionTask<DecrementPurchaseCountByStampSheetResult> {
        private DecrementPurchaseCountByStampSheetRequest request;

        public DecrementPurchaseCountByStampSheetTask(
            DecrementPurchaseCountByStampSheetRequest request,
            AsyncAction<AsyncResult<DecrementPurchaseCountByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DecrementPurchaseCountByStampSheetResult parse(JsonNode data) {
            return DecrementPurchaseCountByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/random/showcase/status/purchase/count/decrease";

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

    public void decrementPurchaseCountByStampSheetAsync(
            DecrementPurchaseCountByStampSheetRequest request,
            AsyncAction<AsyncResult<DecrementPurchaseCountByStampSheetResult>> callback
    ) {
        DecrementPurchaseCountByStampSheetTask task = new DecrementPurchaseCountByStampSheetTask(request, callback);
        session.execute(task);
    }

    public DecrementPurchaseCountByStampSheetResult decrementPurchaseCountByStampSheet(
            DecrementPurchaseCountByStampSheetRequest request
    ) {
        final AsyncResult<DecrementPurchaseCountByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        decrementPurchaseCountByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ForceReDrawByUserIdTask extends Gs2RestSessionTask<ForceReDrawByUserIdResult> {
        private ForceReDrawByUserIdRequest request;

        public ForceReDrawByUserIdTask(
            ForceReDrawByUserIdRequest request,
            AsyncAction<AsyncResult<ForceReDrawByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ForceReDrawByUserIdResult parse(JsonNode data) {
            return ForceReDrawByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/random/showcase/{showcaseName}/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
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

    public void forceReDrawByUserIdAsync(
            ForceReDrawByUserIdRequest request,
            AsyncAction<AsyncResult<ForceReDrawByUserIdResult>> callback
    ) {
        ForceReDrawByUserIdTask task = new ForceReDrawByUserIdTask(request, callback);
        session.execute(task);
    }

    public ForceReDrawByUserIdResult forceReDrawByUserId(
            ForceReDrawByUserIdRequest request
    ) {
        final AsyncResult<ForceReDrawByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        forceReDrawByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ForceReDrawByUserIdByStampSheetTask extends Gs2RestSessionTask<ForceReDrawByUserIdByStampSheetResult> {
        private ForceReDrawByUserIdByStampSheetRequest request;

        public ForceReDrawByUserIdByStampSheetTask(
            ForceReDrawByUserIdByStampSheetRequest request,
            AsyncAction<AsyncResult<ForceReDrawByUserIdByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ForceReDrawByUserIdByStampSheetResult parse(JsonNode data) {
            return ForceReDrawByUserIdByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/random/showcase/status/redraw";

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

    public void forceReDrawByUserIdByStampSheetAsync(
            ForceReDrawByUserIdByStampSheetRequest request,
            AsyncAction<AsyncResult<ForceReDrawByUserIdByStampSheetResult>> callback
    ) {
        ForceReDrawByUserIdByStampSheetTask task = new ForceReDrawByUserIdByStampSheetTask(request, callback);
        session.execute(task);
    }

    public ForceReDrawByUserIdByStampSheetResult forceReDrawByUserIdByStampSheet(
            ForceReDrawByUserIdByStampSheetRequest request
    ) {
        final AsyncResult<ForceReDrawByUserIdByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        forceReDrawByUserIdByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRandomDisplayItemsTask extends Gs2RestSessionTask<DescribeRandomDisplayItemsResult> {
        private DescribeRandomDisplayItemsRequest request;

        public DescribeRandomDisplayItemsTask(
            DescribeRandomDisplayItemsRequest request,
            AsyncAction<AsyncResult<DescribeRandomDisplayItemsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRandomDisplayItemsResult parse(JsonNode data) {
            return DescribeRandomDisplayItemsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/random/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));

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

    public void describeRandomDisplayItemsAsync(
            DescribeRandomDisplayItemsRequest request,
            AsyncAction<AsyncResult<DescribeRandomDisplayItemsResult>> callback
    ) {
        DescribeRandomDisplayItemsTask task = new DescribeRandomDisplayItemsTask(request, callback);
        session.execute(task);
    }

    public DescribeRandomDisplayItemsResult describeRandomDisplayItems(
            DescribeRandomDisplayItemsRequest request
    ) {
        final AsyncResult<DescribeRandomDisplayItemsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRandomDisplayItemsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRandomDisplayItemsByUserIdTask extends Gs2RestSessionTask<DescribeRandomDisplayItemsByUserIdResult> {
        private DescribeRandomDisplayItemsByUserIdRequest request;

        public DescribeRandomDisplayItemsByUserIdTask(
            DescribeRandomDisplayItemsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRandomDisplayItemsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRandomDisplayItemsByUserIdResult parse(JsonNode data) {
            return DescribeRandomDisplayItemsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/random/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
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

    public void describeRandomDisplayItemsByUserIdAsync(
            DescribeRandomDisplayItemsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRandomDisplayItemsByUserIdResult>> callback
    ) {
        DescribeRandomDisplayItemsByUserIdTask task = new DescribeRandomDisplayItemsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeRandomDisplayItemsByUserIdResult describeRandomDisplayItemsByUserId(
            DescribeRandomDisplayItemsByUserIdRequest request
    ) {
        final AsyncResult<DescribeRandomDisplayItemsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRandomDisplayItemsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRandomDisplayItemTask extends Gs2RestSessionTask<GetRandomDisplayItemResult> {
        private GetRandomDisplayItemRequest request;

        public GetRandomDisplayItemTask(
            GetRandomDisplayItemRequest request,
            AsyncAction<AsyncResult<GetRandomDisplayItemResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRandomDisplayItemResult parse(JsonNode data) {
            return GetRandomDisplayItemResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/random/showcase/{showcaseName}/displayItem/{displayItemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
            url = url.replace("{displayItemName}", this.request.getDisplayItemName() == null || this.request.getDisplayItemName().length() == 0 ? "null" : String.valueOf(this.request.getDisplayItemName()));

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

    public void getRandomDisplayItemAsync(
            GetRandomDisplayItemRequest request,
            AsyncAction<AsyncResult<GetRandomDisplayItemResult>> callback
    ) {
        GetRandomDisplayItemTask task = new GetRandomDisplayItemTask(request, callback);
        session.execute(task);
    }

    public GetRandomDisplayItemResult getRandomDisplayItem(
            GetRandomDisplayItemRequest request
    ) {
        final AsyncResult<GetRandomDisplayItemResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRandomDisplayItemAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRandomDisplayItemByUserIdTask extends Gs2RestSessionTask<GetRandomDisplayItemByUserIdResult> {
        private GetRandomDisplayItemByUserIdRequest request;

        public GetRandomDisplayItemByUserIdTask(
            GetRandomDisplayItemByUserIdRequest request,
            AsyncAction<AsyncResult<GetRandomDisplayItemByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRandomDisplayItemByUserIdResult parse(JsonNode data) {
            return GetRandomDisplayItemByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/random/showcase/{showcaseName}/displayItem/{displayItemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
            url = url.replace("{displayItemName}", this.request.getDisplayItemName() == null || this.request.getDisplayItemName().length() == 0 ? "null" : String.valueOf(this.request.getDisplayItemName()));
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

    public void getRandomDisplayItemByUserIdAsync(
            GetRandomDisplayItemByUserIdRequest request,
            AsyncAction<AsyncResult<GetRandomDisplayItemByUserIdResult>> callback
    ) {
        GetRandomDisplayItemByUserIdTask task = new GetRandomDisplayItemByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetRandomDisplayItemByUserIdResult getRandomDisplayItemByUserId(
            GetRandomDisplayItemByUserIdRequest request
    ) {
        final AsyncResult<GetRandomDisplayItemByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRandomDisplayItemByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RandomShowcaseBuyTask extends Gs2RestSessionTask<RandomShowcaseBuyResult> {
        private RandomShowcaseBuyRequest request;

        public RandomShowcaseBuyTask(
            RandomShowcaseBuyRequest request,
            AsyncAction<AsyncResult<RandomShowcaseBuyResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RandomShowcaseBuyResult parse(JsonNode data) {
            return RandomShowcaseBuyResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/random/showcase/{showcaseName}/{displayItemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
            url = url.replace("{displayItemName}", this.request.getDisplayItemName() == null || this.request.getDisplayItemName().length() == 0 ? "null" : String.valueOf(this.request.getDisplayItemName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("quantity", request.getQuantity());
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

    public void randomShowcaseBuyAsync(
            RandomShowcaseBuyRequest request,
            AsyncAction<AsyncResult<RandomShowcaseBuyResult>> callback
    ) {
        RandomShowcaseBuyTask task = new RandomShowcaseBuyTask(request, callback);
        session.execute(task);
    }

    public RandomShowcaseBuyResult randomShowcaseBuy(
            RandomShowcaseBuyRequest request
    ) {
        final AsyncResult<RandomShowcaseBuyResult>[] resultAsyncResult = new AsyncResult[]{null};
        randomShowcaseBuyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RandomShowcaseBuyByUserIdTask extends Gs2RestSessionTask<RandomShowcaseBuyByUserIdResult> {
        private RandomShowcaseBuyByUserIdRequest request;

        public RandomShowcaseBuyByUserIdTask(
            RandomShowcaseBuyByUserIdRequest request,
            AsyncAction<AsyncResult<RandomShowcaseBuyByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public RandomShowcaseBuyByUserIdResult parse(JsonNode data) {
            return RandomShowcaseBuyByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/random/showcase/{showcaseName}/{displayItemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null || this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
            url = url.replace("{displayItemName}", this.request.getDisplayItemName() == null || this.request.getDisplayItemName().length() == 0 ? "null" : String.valueOf(this.request.getDisplayItemName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("quantity", request.getQuantity());
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

    public void randomShowcaseBuyByUserIdAsync(
            RandomShowcaseBuyByUserIdRequest request,
            AsyncAction<AsyncResult<RandomShowcaseBuyByUserIdResult>> callback
    ) {
        RandomShowcaseBuyByUserIdTask task = new RandomShowcaseBuyByUserIdTask(request, callback);
        session.execute(task);
    }

    public RandomShowcaseBuyByUserIdResult randomShowcaseBuyByUserId(
            RandomShowcaseBuyByUserIdRequest request
    ) {
        final AsyncResult<RandomShowcaseBuyByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        randomShowcaseBuyByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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