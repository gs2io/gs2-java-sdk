
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

package io.gs2.exchange;

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
import io.gs2.exchange.request.*;
import io.gs2.exchange.result.*;
import io.gs2.exchange.model.*;

public class Gs2ExchangeRestClient extends AbstractGs2Client<Gs2ExchangeRestClient> {

	public Gs2ExchangeRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "exchange")
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
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("enableAwaitExchange", request.getEnableAwaitExchange());
                    put("enableDirectExchange", request.getEnableDirectExchange());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("exchangeScript", request.getExchangeScript() != null ? request.getExchangeScript().toJson() : null);
                    put("incrementalExchangeScript", request.getIncrementalExchangeScript() != null ? request.getIncrementalExchangeScript().toJson() : null);
                    put("acquireAwaitScript", request.getAcquireAwaitScript() != null ? request.getAcquireAwaitScript().toJson() : null);
                    put("logSetting", request.getLogSetting() != null ? request.getLogSetting().toJson() : null);
                    put("queueNamespaceId", request.getQueueNamespaceId());
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
                .replace("{service}", "exchange")
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
                .replace("{service}", "exchange")
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
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("enableAwaitExchange", request.getEnableAwaitExchange());
                    put("enableDirectExchange", request.getEnableDirectExchange());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("exchangeScript", request.getExchangeScript() != null ? request.getExchangeScript().toJson() : null);
                    put("incrementalExchangeScript", request.getIncrementalExchangeScript() != null ? request.getIncrementalExchangeScript().toJson() : null);
                    put("acquireAwaitScript", request.getAcquireAwaitScript() != null ? request.getAcquireAwaitScript().toJson() : null);
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
                .replace("{service}", "exchange")
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
                .replace("{service}", "exchange")
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
                .replace("{service}", "exchange")
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
                .replace("{service}", "exchange")
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
                .replace("{service}", "exchange")
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
                .replace("{service}", "exchange")
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
                .replace("{service}", "exchange")
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
                .replace("{service}", "exchange")
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

    class DescribeRateModelsTask extends Gs2RestSessionTask<DescribeRateModelsResult> {
        private DescribeRateModelsRequest request;

        public DescribeRateModelsTask(
            DescribeRateModelsRequest request,
            AsyncAction<AsyncResult<DescribeRateModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRateModelsResult parse(JsonNode data) {
            return DescribeRateModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
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

    public void describeRateModelsAsync(
            DescribeRateModelsRequest request,
            AsyncAction<AsyncResult<DescribeRateModelsResult>> callback
    ) {
        DescribeRateModelsTask task = new DescribeRateModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeRateModelsResult describeRateModels(
            DescribeRateModelsRequest request
    ) {
        final AsyncResult<DescribeRateModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRateModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRateModelTask extends Gs2RestSessionTask<GetRateModelResult> {
        private GetRateModelRequest request;

        public GetRateModelTask(
            GetRateModelRequest request,
            AsyncAction<AsyncResult<GetRateModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRateModelResult parse(JsonNode data) {
            return GetRateModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

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

    public void getRateModelAsync(
            GetRateModelRequest request,
            AsyncAction<AsyncResult<GetRateModelResult>> callback
    ) {
        GetRateModelTask task = new GetRateModelTask(request, callback);
        session.execute(task);
    }

    public GetRateModelResult getRateModel(
            GetRateModelRequest request
    ) {
        final AsyncResult<GetRateModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRateModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRateModelMastersTask extends Gs2RestSessionTask<DescribeRateModelMastersResult> {
        private DescribeRateModelMastersRequest request;

        public DescribeRateModelMastersTask(
            DescribeRateModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeRateModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeRateModelMastersResult parse(JsonNode data) {
            return DescribeRateModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
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

    public void describeRateModelMastersAsync(
            DescribeRateModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeRateModelMastersResult>> callback
    ) {
        DescribeRateModelMastersTask task = new DescribeRateModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeRateModelMastersResult describeRateModelMasters(
            DescribeRateModelMastersRequest request
    ) {
        final AsyncResult<DescribeRateModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRateModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateRateModelMasterTask extends Gs2RestSessionTask<CreateRateModelMasterResult> {
        private CreateRateModelMasterRequest request;

        public CreateRateModelMasterTask(
            CreateRateModelMasterRequest request,
            AsyncAction<AsyncResult<CreateRateModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateRateModelMasterResult parse(JsonNode data) {
            return CreateRateModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("timingType", request.getTimingType());
                    put("lockTime", request.getLockTime());
                    put("acquireActions", request.getAcquireActions() == null ? null :
                        request.getAcquireActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
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

    public void createRateModelMasterAsync(
            CreateRateModelMasterRequest request,
            AsyncAction<AsyncResult<CreateRateModelMasterResult>> callback
    ) {
        CreateRateModelMasterTask task = new CreateRateModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateRateModelMasterResult createRateModelMaster(
            CreateRateModelMasterRequest request
    ) {
        final AsyncResult<CreateRateModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createRateModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRateModelMasterTask extends Gs2RestSessionTask<GetRateModelMasterResult> {
        private GetRateModelMasterRequest request;

        public GetRateModelMasterTask(
            GetRateModelMasterRequest request,
            AsyncAction<AsyncResult<GetRateModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetRateModelMasterResult parse(JsonNode data) {
            return GetRateModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

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

    public void getRateModelMasterAsync(
            GetRateModelMasterRequest request,
            AsyncAction<AsyncResult<GetRateModelMasterResult>> callback
    ) {
        GetRateModelMasterTask task = new GetRateModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetRateModelMasterResult getRateModelMaster(
            GetRateModelMasterRequest request
    ) {
        final AsyncResult<GetRateModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRateModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateRateModelMasterTask extends Gs2RestSessionTask<UpdateRateModelMasterResult> {
        private UpdateRateModelMasterRequest request;

        public UpdateRateModelMasterTask(
            UpdateRateModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateRateModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateRateModelMasterResult parse(JsonNode data) {
            return UpdateRateModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("timingType", request.getTimingType());
                    put("lockTime", request.getLockTime());
                    put("acquireActions", request.getAcquireActions() == null ? null :
                        request.getAcquireActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
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

    public void updateRateModelMasterAsync(
            UpdateRateModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateRateModelMasterResult>> callback
    ) {
        UpdateRateModelMasterTask task = new UpdateRateModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateRateModelMasterResult updateRateModelMaster(
            UpdateRateModelMasterRequest request
    ) {
        final AsyncResult<UpdateRateModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateRateModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteRateModelMasterTask extends Gs2RestSessionTask<DeleteRateModelMasterResult> {
        private DeleteRateModelMasterRequest request;

        public DeleteRateModelMasterTask(
            DeleteRateModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteRateModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteRateModelMasterResult parse(JsonNode data) {
            return DeleteRateModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

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

    public void deleteRateModelMasterAsync(
            DeleteRateModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteRateModelMasterResult>> callback
    ) {
        DeleteRateModelMasterTask task = new DeleteRateModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteRateModelMasterResult deleteRateModelMaster(
            DeleteRateModelMasterRequest request
    ) {
        final AsyncResult<DeleteRateModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteRateModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeIncrementalRateModelsTask extends Gs2RestSessionTask<DescribeIncrementalRateModelsResult> {
        private DescribeIncrementalRateModelsRequest request;

        public DescribeIncrementalRateModelsTask(
            DescribeIncrementalRateModelsRequest request,
            AsyncAction<AsyncResult<DescribeIncrementalRateModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeIncrementalRateModelsResult parse(JsonNode data) {
            return DescribeIncrementalRateModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/incremental/model";

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

    public void describeIncrementalRateModelsAsync(
            DescribeIncrementalRateModelsRequest request,
            AsyncAction<AsyncResult<DescribeIncrementalRateModelsResult>> callback
    ) {
        DescribeIncrementalRateModelsTask task = new DescribeIncrementalRateModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeIncrementalRateModelsResult describeIncrementalRateModels(
            DescribeIncrementalRateModelsRequest request
    ) {
        final AsyncResult<DescribeIncrementalRateModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeIncrementalRateModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetIncrementalRateModelTask extends Gs2RestSessionTask<GetIncrementalRateModelResult> {
        private GetIncrementalRateModelRequest request;

        public GetIncrementalRateModelTask(
            GetIncrementalRateModelRequest request,
            AsyncAction<AsyncResult<GetIncrementalRateModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetIncrementalRateModelResult parse(JsonNode data) {
            return GetIncrementalRateModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/incremental/model/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

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

    public void getIncrementalRateModelAsync(
            GetIncrementalRateModelRequest request,
            AsyncAction<AsyncResult<GetIncrementalRateModelResult>> callback
    ) {
        GetIncrementalRateModelTask task = new GetIncrementalRateModelTask(request, callback);
        session.execute(task);
    }

    public GetIncrementalRateModelResult getIncrementalRateModel(
            GetIncrementalRateModelRequest request
    ) {
        final AsyncResult<GetIncrementalRateModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getIncrementalRateModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeIncrementalRateModelMastersTask extends Gs2RestSessionTask<DescribeIncrementalRateModelMastersResult> {
        private DescribeIncrementalRateModelMastersRequest request;

        public DescribeIncrementalRateModelMastersTask(
            DescribeIncrementalRateModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeIncrementalRateModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeIncrementalRateModelMastersResult parse(JsonNode data) {
            return DescribeIncrementalRateModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/incremental/master/model";

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

    public void describeIncrementalRateModelMastersAsync(
            DescribeIncrementalRateModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeIncrementalRateModelMastersResult>> callback
    ) {
        DescribeIncrementalRateModelMastersTask task = new DescribeIncrementalRateModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeIncrementalRateModelMastersResult describeIncrementalRateModelMasters(
            DescribeIncrementalRateModelMastersRequest request
    ) {
        final AsyncResult<DescribeIncrementalRateModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeIncrementalRateModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateIncrementalRateModelMasterTask extends Gs2RestSessionTask<CreateIncrementalRateModelMasterResult> {
        private CreateIncrementalRateModelMasterRequest request;

        public CreateIncrementalRateModelMasterTask(
            CreateIncrementalRateModelMasterRequest request,
            AsyncAction<AsyncResult<CreateIncrementalRateModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateIncrementalRateModelMasterResult parse(JsonNode data) {
            return CreateIncrementalRateModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/incremental/master/model";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("consumeAction", request.getConsumeAction() != null ? request.getConsumeAction().toJson() : null);
                    put("calculateType", request.getCalculateType());
                    put("baseValue", request.getBaseValue());
                    put("coefficientValue", request.getCoefficientValue());
                    put("calculateScriptId", request.getCalculateScriptId());
                    put("exchangeCountId", request.getExchangeCountId());
                    put("maximumExchangeCount", request.getMaximumExchangeCount());
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

    public void createIncrementalRateModelMasterAsync(
            CreateIncrementalRateModelMasterRequest request,
            AsyncAction<AsyncResult<CreateIncrementalRateModelMasterResult>> callback
    ) {
        CreateIncrementalRateModelMasterTask task = new CreateIncrementalRateModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateIncrementalRateModelMasterResult createIncrementalRateModelMaster(
            CreateIncrementalRateModelMasterRequest request
    ) {
        final AsyncResult<CreateIncrementalRateModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createIncrementalRateModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetIncrementalRateModelMasterTask extends Gs2RestSessionTask<GetIncrementalRateModelMasterResult> {
        private GetIncrementalRateModelMasterRequest request;

        public GetIncrementalRateModelMasterTask(
            GetIncrementalRateModelMasterRequest request,
            AsyncAction<AsyncResult<GetIncrementalRateModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetIncrementalRateModelMasterResult parse(JsonNode data) {
            return GetIncrementalRateModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/incremental/master/model/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

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

    public void getIncrementalRateModelMasterAsync(
            GetIncrementalRateModelMasterRequest request,
            AsyncAction<AsyncResult<GetIncrementalRateModelMasterResult>> callback
    ) {
        GetIncrementalRateModelMasterTask task = new GetIncrementalRateModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetIncrementalRateModelMasterResult getIncrementalRateModelMaster(
            GetIncrementalRateModelMasterRequest request
    ) {
        final AsyncResult<GetIncrementalRateModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getIncrementalRateModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateIncrementalRateModelMasterTask extends Gs2RestSessionTask<UpdateIncrementalRateModelMasterResult> {
        private UpdateIncrementalRateModelMasterRequest request;

        public UpdateIncrementalRateModelMasterTask(
            UpdateIncrementalRateModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateIncrementalRateModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateIncrementalRateModelMasterResult parse(JsonNode data) {
            return UpdateIncrementalRateModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/incremental/master/model/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("consumeAction", request.getConsumeAction() != null ? request.getConsumeAction().toJson() : null);
                    put("calculateType", request.getCalculateType());
                    put("baseValue", request.getBaseValue());
                    put("coefficientValue", request.getCoefficientValue());
                    put("calculateScriptId", request.getCalculateScriptId());
                    put("exchangeCountId", request.getExchangeCountId());
                    put("maximumExchangeCount", request.getMaximumExchangeCount());
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

    public void updateIncrementalRateModelMasterAsync(
            UpdateIncrementalRateModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateIncrementalRateModelMasterResult>> callback
    ) {
        UpdateIncrementalRateModelMasterTask task = new UpdateIncrementalRateModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateIncrementalRateModelMasterResult updateIncrementalRateModelMaster(
            UpdateIncrementalRateModelMasterRequest request
    ) {
        final AsyncResult<UpdateIncrementalRateModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateIncrementalRateModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteIncrementalRateModelMasterTask extends Gs2RestSessionTask<DeleteIncrementalRateModelMasterResult> {
        private DeleteIncrementalRateModelMasterRequest request;

        public DeleteIncrementalRateModelMasterTask(
            DeleteIncrementalRateModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteIncrementalRateModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteIncrementalRateModelMasterResult parse(JsonNode data) {
            return DeleteIncrementalRateModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/incremental/master/model/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

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

    public void deleteIncrementalRateModelMasterAsync(
            DeleteIncrementalRateModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteIncrementalRateModelMasterResult>> callback
    ) {
        DeleteIncrementalRateModelMasterTask task = new DeleteIncrementalRateModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteIncrementalRateModelMasterResult deleteIncrementalRateModelMaster(
            DeleteIncrementalRateModelMasterRequest request
    ) {
        final AsyncResult<DeleteIncrementalRateModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteIncrementalRateModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ExchangeTask extends Gs2RestSessionTask<ExchangeResult> {
        private ExchangeRequest request;

        public ExchangeTask(
            ExchangeRequest request,
            AsyncAction<AsyncResult<ExchangeResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ExchangeResult parse(JsonNode data) {
            return ExchangeResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/exchange/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("count", request.getCount());
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

    public void exchangeAsync(
            ExchangeRequest request,
            AsyncAction<AsyncResult<ExchangeResult>> callback
    ) {
        ExchangeTask task = new ExchangeTask(request, callback);
        session.execute(task);
    }

    public ExchangeResult exchange(
            ExchangeRequest request
    ) {
        final AsyncResult<ExchangeResult>[] resultAsyncResult = new AsyncResult[]{null};
        exchangeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ExchangeByUserIdTask extends Gs2RestSessionTask<ExchangeByUserIdResult> {
        private ExchangeByUserIdRequest request;

        public ExchangeByUserIdTask(
            ExchangeByUserIdRequest request,
            AsyncAction<AsyncResult<ExchangeByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ExchangeByUserIdResult parse(JsonNode data) {
            return ExchangeByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("count", request.getCount());
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

    public void exchangeByUserIdAsync(
            ExchangeByUserIdRequest request,
            AsyncAction<AsyncResult<ExchangeByUserIdResult>> callback
    ) {
        ExchangeByUserIdTask task = new ExchangeByUserIdTask(request, callback);
        session.execute(task);
    }

    public ExchangeByUserIdResult exchangeByUserId(
            ExchangeByUserIdRequest request
    ) {
        final AsyncResult<ExchangeByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        exchangeByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ExchangeByStampSheetTask extends Gs2RestSessionTask<ExchangeByStampSheetResult> {
        private ExchangeByStampSheetRequest request;

        public ExchangeByStampSheetTask(
            ExchangeByStampSheetRequest request,
            AsyncAction<AsyncResult<ExchangeByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ExchangeByStampSheetResult parse(JsonNode data) {
            return ExchangeByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/exchange";

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

    public void exchangeByStampSheetAsync(
            ExchangeByStampSheetRequest request,
            AsyncAction<AsyncResult<ExchangeByStampSheetResult>> callback
    ) {
        ExchangeByStampSheetTask task = new ExchangeByStampSheetTask(request, callback);
        session.execute(task);
    }

    public ExchangeByStampSheetResult exchangeByStampSheet(
            ExchangeByStampSheetRequest request
    ) {
        final AsyncResult<ExchangeByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        exchangeByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class IncrementalExchangeTask extends Gs2RestSessionTask<IncrementalExchangeResult> {
        private IncrementalExchangeRequest request;

        public IncrementalExchangeTask(
            IncrementalExchangeRequest request,
            AsyncAction<AsyncResult<IncrementalExchangeResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IncrementalExchangeResult parse(JsonNode data) {
            return IncrementalExchangeResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/incremental/exchange/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("count", request.getCount());
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

    public void incrementalExchangeAsync(
            IncrementalExchangeRequest request,
            AsyncAction<AsyncResult<IncrementalExchangeResult>> callback
    ) {
        IncrementalExchangeTask task = new IncrementalExchangeTask(request, callback);
        session.execute(task);
    }

    public IncrementalExchangeResult incrementalExchange(
            IncrementalExchangeRequest request
    ) {
        final AsyncResult<IncrementalExchangeResult>[] resultAsyncResult = new AsyncResult[]{null};
        incrementalExchangeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class IncrementalExchangeByUserIdTask extends Gs2RestSessionTask<IncrementalExchangeByUserIdResult> {
        private IncrementalExchangeByUserIdRequest request;

        public IncrementalExchangeByUserIdTask(
            IncrementalExchangeByUserIdRequest request,
            AsyncAction<AsyncResult<IncrementalExchangeByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IncrementalExchangeByUserIdResult parse(JsonNode data) {
            return IncrementalExchangeByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/incremental/exchange/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("count", request.getCount());
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

    public void incrementalExchangeByUserIdAsync(
            IncrementalExchangeByUserIdRequest request,
            AsyncAction<AsyncResult<IncrementalExchangeByUserIdResult>> callback
    ) {
        IncrementalExchangeByUserIdTask task = new IncrementalExchangeByUserIdTask(request, callback);
        session.execute(task);
    }

    public IncrementalExchangeByUserIdResult incrementalExchangeByUserId(
            IncrementalExchangeByUserIdRequest request
    ) {
        final AsyncResult<IncrementalExchangeByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        incrementalExchangeByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class IncrementalExchangeByStampSheetTask extends Gs2RestSessionTask<IncrementalExchangeByStampSheetResult> {
        private IncrementalExchangeByStampSheetRequest request;

        public IncrementalExchangeByStampSheetTask(
            IncrementalExchangeByStampSheetRequest request,
            AsyncAction<AsyncResult<IncrementalExchangeByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IncrementalExchangeByStampSheetResult parse(JsonNode data) {
            return IncrementalExchangeByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/incremental/exchange";

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

    public void incrementalExchangeByStampSheetAsync(
            IncrementalExchangeByStampSheetRequest request,
            AsyncAction<AsyncResult<IncrementalExchangeByStampSheetResult>> callback
    ) {
        IncrementalExchangeByStampSheetTask task = new IncrementalExchangeByStampSheetTask(request, callback);
        session.execute(task);
    }

    public IncrementalExchangeByStampSheetResult incrementalExchangeByStampSheet(
            IncrementalExchangeByStampSheetRequest request
    ) {
        final AsyncResult<IncrementalExchangeByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        incrementalExchangeByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "exchange")
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

    class GetCurrentRateMasterTask extends Gs2RestSessionTask<GetCurrentRateMasterResult> {
        private GetCurrentRateMasterRequest request;

        public GetCurrentRateMasterTask(
            GetCurrentRateMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentRateMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentRateMasterResult parse(JsonNode data) {
            return GetCurrentRateMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
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

    public void getCurrentRateMasterAsync(
            GetCurrentRateMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentRateMasterResult>> callback
    ) {
        GetCurrentRateMasterTask task = new GetCurrentRateMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentRateMasterResult getCurrentRateMaster(
            GetCurrentRateMasterRequest request
    ) {
        final AsyncResult<GetCurrentRateMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentRateMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PreUpdateCurrentRateMasterTask extends Gs2RestSessionTask<PreUpdateCurrentRateMasterResult> {
        private PreUpdateCurrentRateMasterRequest request;

        public PreUpdateCurrentRateMasterTask(
            PreUpdateCurrentRateMasterRequest request,
            AsyncAction<AsyncResult<PreUpdateCurrentRateMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PreUpdateCurrentRateMasterResult parse(JsonNode data) {
            return PreUpdateCurrentRateMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
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

    public void preUpdateCurrentRateMasterAsync(
            PreUpdateCurrentRateMasterRequest request,
            AsyncAction<AsyncResult<PreUpdateCurrentRateMasterResult>> callback
    ) {
        PreUpdateCurrentRateMasterTask task = new PreUpdateCurrentRateMasterTask(request, callback);
        session.execute(task);
    }

    public PreUpdateCurrentRateMasterResult preUpdateCurrentRateMaster(
            PreUpdateCurrentRateMasterRequest request
    ) {
        final AsyncResult<PreUpdateCurrentRateMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        preUpdateCurrentRateMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentRateMasterTask extends Gs2RestSessionTask<UpdateCurrentRateMasterResult> {
        private UpdateCurrentRateMasterRequest request;

        public UpdateCurrentRateMasterTask(
            UpdateCurrentRateMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRateMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentRateMasterResult parse(JsonNode data) {
            return UpdateCurrentRateMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {
            if (request.getSettings() != null) {
                AtomicReference<AsyncResult<PreUpdateCurrentRateMasterResult>> resultAsyncResult = new AtomicReference<>();
                PreUpdateCurrentRateMasterTask task = new PreUpdateCurrentRateMasterTask(
                        new PreUpdateCurrentRateMasterRequest()
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
                .replace("{service}", "exchange")
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

    public void updateCurrentRateMasterAsync(
            UpdateCurrentRateMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRateMasterResult>> callback
    ) {
        UpdateCurrentRateMasterTask task = new UpdateCurrentRateMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentRateMasterResult updateCurrentRateMaster(
            UpdateCurrentRateMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentRateMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentRateMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentRateMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentRateMasterFromGitHubResult> {
        private UpdateCurrentRateMasterFromGitHubRequest request;

        public UpdateCurrentRateMasterFromGitHubTask(
            UpdateCurrentRateMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRateMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentRateMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentRateMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
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

    public void updateCurrentRateMasterFromGitHubAsync(
            UpdateCurrentRateMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRateMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentRateMasterFromGitHubTask task = new UpdateCurrentRateMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentRateMasterFromGitHubResult updateCurrentRateMasterFromGitHub(
            UpdateCurrentRateMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentRateMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentRateMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateAwaitByUserIdTask extends Gs2RestSessionTask<CreateAwaitByUserIdResult> {
        private CreateAwaitByUserIdRequest request;

        public CreateAwaitByUserIdTask(
            CreateAwaitByUserIdRequest request,
            AsyncAction<AsyncResult<CreateAwaitByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateAwaitByUserIdResult parse(JsonNode data) {
            return CreateAwaitByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/{rateName}/await";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("count", request.getCount());
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

    public void createAwaitByUserIdAsync(
            CreateAwaitByUserIdRequest request,
            AsyncAction<AsyncResult<CreateAwaitByUserIdResult>> callback
    ) {
        CreateAwaitByUserIdTask task = new CreateAwaitByUserIdTask(request, callback);
        session.execute(task);
    }

    public CreateAwaitByUserIdResult createAwaitByUserId(
            CreateAwaitByUserIdRequest request
    ) {
        final AsyncResult<CreateAwaitByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        createAwaitByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeAwaitsTask extends Gs2RestSessionTask<DescribeAwaitsResult> {
        private DescribeAwaitsRequest request;

        public DescribeAwaitsTask(
            DescribeAwaitsRequest request,
            AsyncAction<AsyncResult<DescribeAwaitsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeAwaitsResult parse(JsonNode data) {
            return DescribeAwaitsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/exchange/await";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRateName() != null) {
                queryStrings.add("rateName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRateName()))));
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

    public void describeAwaitsAsync(
            DescribeAwaitsRequest request,
            AsyncAction<AsyncResult<DescribeAwaitsResult>> callback
    ) {
        DescribeAwaitsTask task = new DescribeAwaitsTask(request, callback);
        session.execute(task);
    }

    public DescribeAwaitsResult describeAwaits(
            DescribeAwaitsRequest request
    ) {
        final AsyncResult<DescribeAwaitsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeAwaitsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeAwaitsByUserIdTask extends Gs2RestSessionTask<DescribeAwaitsByUserIdResult> {
        private DescribeAwaitsByUserIdRequest request;

        public DescribeAwaitsByUserIdTask(
            DescribeAwaitsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeAwaitsByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeAwaitsByUserIdResult parse(JsonNode data) {
            return DescribeAwaitsByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/await";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getRateName() != null) {
                queryStrings.add("rateName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRateName()))));
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

    public void describeAwaitsByUserIdAsync(
            DescribeAwaitsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeAwaitsByUserIdResult>> callback
    ) {
        DescribeAwaitsByUserIdTask task = new DescribeAwaitsByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeAwaitsByUserIdResult describeAwaitsByUserId(
            DescribeAwaitsByUserIdRequest request
    ) {
        final AsyncResult<DescribeAwaitsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeAwaitsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetAwaitTask extends Gs2RestSessionTask<GetAwaitResult> {
        private GetAwaitRequest request;

        public GetAwaitTask(
            GetAwaitRequest request,
            AsyncAction<AsyncResult<GetAwaitResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetAwaitResult parse(JsonNode data) {
            return GetAwaitResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/exchange/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null || this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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

    public void getAwaitAsync(
            GetAwaitRequest request,
            AsyncAction<AsyncResult<GetAwaitResult>> callback
    ) {
        GetAwaitTask task = new GetAwaitTask(request, callback);
        session.execute(task);
    }

    public GetAwaitResult getAwait(
            GetAwaitRequest request
    ) {
        final AsyncResult<GetAwaitResult>[] resultAsyncResult = new AsyncResult[]{null};
        getAwaitAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetAwaitByUserIdTask extends Gs2RestSessionTask<GetAwaitByUserIdResult> {
        private GetAwaitByUserIdRequest request;

        public GetAwaitByUserIdTask(
            GetAwaitByUserIdRequest request,
            AsyncAction<AsyncResult<GetAwaitByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetAwaitByUserIdResult parse(JsonNode data) {
            return GetAwaitByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null || this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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

    public void getAwaitByUserIdAsync(
            GetAwaitByUserIdRequest request,
            AsyncAction<AsyncResult<GetAwaitByUserIdResult>> callback
    ) {
        GetAwaitByUserIdTask task = new GetAwaitByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetAwaitByUserIdResult getAwaitByUserId(
            GetAwaitByUserIdRequest request
    ) {
        final AsyncResult<GetAwaitByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getAwaitByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquireTask extends Gs2RestSessionTask<AcquireResult> {
        private AcquireRequest request;

        public AcquireTask(
            AcquireRequest request,
            AsyncAction<AsyncResult<AcquireResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireResult parse(JsonNode data) {
            return AcquireResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/exchange/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null || this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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

    public void acquireAsync(
            AcquireRequest request,
            AsyncAction<AsyncResult<AcquireResult>> callback
    ) {
        AcquireTask task = new AcquireTask(request, callback);
        session.execute(task);
    }

    public AcquireResult acquire(
            AcquireRequest request
    ) {
        final AsyncResult<AcquireResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquireByUserIdTask extends Gs2RestSessionTask<AcquireByUserIdResult> {
        private AcquireByUserIdRequest request;

        public AcquireByUserIdTask(
            AcquireByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireByUserIdResult parse(JsonNode data) {
            return AcquireByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null || this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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

    public void acquireByUserIdAsync(
            AcquireByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireByUserIdResult>> callback
    ) {
        AcquireByUserIdTask task = new AcquireByUserIdTask(request, callback);
        session.execute(task);
    }

    public AcquireByUserIdResult acquireByUserId(
            AcquireByUserIdRequest request
    ) {
        final AsyncResult<AcquireByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquireForceByUserIdTask extends Gs2RestSessionTask<AcquireForceByUserIdResult> {
        private AcquireForceByUserIdRequest request;

        public AcquireForceByUserIdTask(
            AcquireForceByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireForceByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireForceByUserIdResult parse(JsonNode data) {
            return AcquireForceByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/await/{awaitName}/force";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null || this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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

    public void acquireForceByUserIdAsync(
            AcquireForceByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireForceByUserIdResult>> callback
    ) {
        AcquireForceByUserIdTask task = new AcquireForceByUserIdTask(request, callback);
        session.execute(task);
    }

    public AcquireForceByUserIdResult acquireForceByUserId(
            AcquireForceByUserIdRequest request
    ) {
        final AsyncResult<AcquireForceByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireForceByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SkipByUserIdTask extends Gs2RestSessionTask<SkipByUserIdResult> {
        private SkipByUserIdRequest request;

        public SkipByUserIdTask(
            SkipByUserIdRequest request,
            AsyncAction<AsyncResult<SkipByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SkipByUserIdResult parse(JsonNode data) {
            return SkipByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/await/{awaitName}/skip";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null || this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("skipType", request.getSkipType());
                    put("minutes", request.getMinutes());
                    put("rate", request.getRate());
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

    public void skipByUserIdAsync(
            SkipByUserIdRequest request,
            AsyncAction<AsyncResult<SkipByUserIdResult>> callback
    ) {
        SkipByUserIdTask task = new SkipByUserIdTask(request, callback);
        session.execute(task);
    }

    public SkipByUserIdResult skipByUserId(
            SkipByUserIdRequest request
    ) {
        final AsyncResult<SkipByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        skipByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteAwaitTask extends Gs2RestSessionTask<DeleteAwaitResult> {
        private DeleteAwaitRequest request;

        public DeleteAwaitTask(
            DeleteAwaitRequest request,
            AsyncAction<AsyncResult<DeleteAwaitResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteAwaitResult parse(JsonNode data) {
            return DeleteAwaitResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/exchange/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null || this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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

    public void deleteAwaitAsync(
            DeleteAwaitRequest request,
            AsyncAction<AsyncResult<DeleteAwaitResult>> callback
    ) {
        DeleteAwaitTask task = new DeleteAwaitTask(request, callback);
        session.execute(task);
    }

    public DeleteAwaitResult deleteAwait(
            DeleteAwaitRequest request
    ) {
        final AsyncResult<DeleteAwaitResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteAwaitAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteAwaitByUserIdTask extends Gs2RestSessionTask<DeleteAwaitByUserIdResult> {
        private DeleteAwaitByUserIdRequest request;

        public DeleteAwaitByUserIdTask(
            DeleteAwaitByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteAwaitByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteAwaitByUserIdResult parse(JsonNode data) {
            return DeleteAwaitByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null || this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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

    public void deleteAwaitByUserIdAsync(
            DeleteAwaitByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteAwaitByUserIdResult>> callback
    ) {
        DeleteAwaitByUserIdTask task = new DeleteAwaitByUserIdTask(request, callback);
        session.execute(task);
    }

    public DeleteAwaitByUserIdResult deleteAwaitByUserId(
            DeleteAwaitByUserIdRequest request
    ) {
        final AsyncResult<DeleteAwaitByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteAwaitByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateAwaitByStampSheetTask extends Gs2RestSessionTask<CreateAwaitByStampSheetResult> {
        private CreateAwaitByStampSheetRequest request;

        public CreateAwaitByStampSheetTask(
            CreateAwaitByStampSheetRequest request,
            AsyncAction<AsyncResult<CreateAwaitByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateAwaitByStampSheetResult parse(JsonNode data) {
            return CreateAwaitByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/await/create";

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

    public void createAwaitByStampSheetAsync(
            CreateAwaitByStampSheetRequest request,
            AsyncAction<AsyncResult<CreateAwaitByStampSheetResult>> callback
    ) {
        CreateAwaitByStampSheetTask task = new CreateAwaitByStampSheetTask(request, callback);
        session.execute(task);
    }

    public CreateAwaitByStampSheetResult createAwaitByStampSheet(
            CreateAwaitByStampSheetRequest request
    ) {
        final AsyncResult<CreateAwaitByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        createAwaitByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquireForceByStampSheetTask extends Gs2RestSessionTask<AcquireForceByStampSheetResult> {
        private AcquireForceByStampSheetRequest request;

        public AcquireForceByStampSheetTask(
            AcquireForceByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireForceByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public AcquireForceByStampSheetResult parse(JsonNode data) {
            return AcquireForceByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/await/acquire/force";

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

    public void acquireForceByStampSheetAsync(
            AcquireForceByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireForceByStampSheetResult>> callback
    ) {
        AcquireForceByStampSheetTask task = new AcquireForceByStampSheetTask(request, callback);
        session.execute(task);
    }

    public AcquireForceByStampSheetResult acquireForceByStampSheet(
            AcquireForceByStampSheetRequest request
    ) {
        final AsyncResult<AcquireForceByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireForceByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SkipByStampSheetTask extends Gs2RestSessionTask<SkipByStampSheetResult> {
        private SkipByStampSheetRequest request;

        public SkipByStampSheetTask(
            SkipByStampSheetRequest request,
            AsyncAction<AsyncResult<SkipByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SkipByStampSheetResult parse(JsonNode data) {
            return SkipByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/await/skip";

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

    public void skipByStampSheetAsync(
            SkipByStampSheetRequest request,
            AsyncAction<AsyncResult<SkipByStampSheetResult>> callback
    ) {
        SkipByStampSheetTask task = new SkipByStampSheetTask(request, callback);
        session.execute(task);
    }

    public SkipByStampSheetResult skipByStampSheet(
            SkipByStampSheetRequest request
    ) {
        final AsyncResult<SkipByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        skipByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteAwaitByStampTaskTask extends Gs2RestSessionTask<DeleteAwaitByStampTaskResult> {
        private DeleteAwaitByStampTaskRequest request;

        public DeleteAwaitByStampTaskTask(
            DeleteAwaitByStampTaskRequest request,
            AsyncAction<AsyncResult<DeleteAwaitByStampTaskResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteAwaitByStampTaskResult parse(JsonNode data) {
            return DeleteAwaitByStampTaskResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/await/delete";

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

    public void deleteAwaitByStampTaskAsync(
            DeleteAwaitByStampTaskRequest request,
            AsyncAction<AsyncResult<DeleteAwaitByStampTaskResult>> callback
    ) {
        DeleteAwaitByStampTaskTask task = new DeleteAwaitByStampTaskTask(request, callback);
        session.execute(task);
    }

    public DeleteAwaitByStampTaskResult deleteAwaitByStampTask(
            DeleteAwaitByStampTaskRequest request
    ) {
        final AsyncResult<DeleteAwaitByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteAwaitByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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