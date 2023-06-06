
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

package io.gs2.lottery;

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
import io.gs2.lottery.request.*;
import io.gs2.lottery.result.*;
import io.gs2.lottery.model.*;public class Gs2LotteryRestClient extends AbstractGs2Client<Gs2LotteryRestClient> {

	public Gs2LotteryRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "lottery")
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
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("lotteryTriggerScriptId", request.getLotteryTriggerScriptId());
                    put("choicePrizeTableScriptId", request.getChoicePrizeTableScriptId());
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
                .replace("{service}", "lottery")
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
                .replace("{service}", "lottery")
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
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("transactionSetting", request.getTransactionSetting() != null ? request.getTransactionSetting().toJson() : null);
                    put("lotteryTriggerScriptId", request.getLotteryTriggerScriptId());
                    put("choicePrizeTableScriptId", request.getChoicePrizeTableScriptId());
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
                .replace("{service}", "lottery")
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

    class DescribeLotteryModelMastersTask extends Gs2RestSessionTask<DescribeLotteryModelMastersResult> {
        private DescribeLotteryModelMastersRequest request;

        public DescribeLotteryModelMastersTask(
            DescribeLotteryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeLotteryModelMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeLotteryModelMastersResult parse(JsonNode data) {
            return DescribeLotteryModelMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/lottery";

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

    public void describeLotteryModelMastersAsync(
            DescribeLotteryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeLotteryModelMastersResult>> callback
    ) {
        DescribeLotteryModelMastersTask task = new DescribeLotteryModelMastersTask(request, callback);
        session.execute(task);
    }

    public DescribeLotteryModelMastersResult describeLotteryModelMasters(
            DescribeLotteryModelMastersRequest request
    ) {
        final AsyncResult<DescribeLotteryModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeLotteryModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateLotteryModelMasterTask extends Gs2RestSessionTask<CreateLotteryModelMasterResult> {
        private CreateLotteryModelMasterRequest request;

        public CreateLotteryModelMasterTask(
            CreateLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateLotteryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateLotteryModelMasterResult parse(JsonNode data) {
            return CreateLotteryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/lottery";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("mode", request.getMode());
                    put("method", request.getMethod());
                    put("prizeTableName", request.getPrizeTableName());
                    put("choicePrizeTableScriptId", request.getChoicePrizeTableScriptId());
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

    public void createLotteryModelMasterAsync(
            CreateLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateLotteryModelMasterResult>> callback
    ) {
        CreateLotteryModelMasterTask task = new CreateLotteryModelMasterTask(request, callback);
        session.execute(task);
    }

    public CreateLotteryModelMasterResult createLotteryModelMaster(
            CreateLotteryModelMasterRequest request
    ) {
        final AsyncResult<CreateLotteryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createLotteryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetLotteryModelMasterTask extends Gs2RestSessionTask<GetLotteryModelMasterResult> {
        private GetLotteryModelMasterRequest request;

        public GetLotteryModelMasterTask(
            GetLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<GetLotteryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetLotteryModelMasterResult parse(JsonNode data) {
            return GetLotteryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/lottery/{lotteryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null || this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));

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

    public void getLotteryModelMasterAsync(
            GetLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<GetLotteryModelMasterResult>> callback
    ) {
        GetLotteryModelMasterTask task = new GetLotteryModelMasterTask(request, callback);
        session.execute(task);
    }

    public GetLotteryModelMasterResult getLotteryModelMaster(
            GetLotteryModelMasterRequest request
    ) {
        final AsyncResult<GetLotteryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getLotteryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateLotteryModelMasterTask extends Gs2RestSessionTask<UpdateLotteryModelMasterResult> {
        private UpdateLotteryModelMasterRequest request;

        public UpdateLotteryModelMasterTask(
            UpdateLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateLotteryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateLotteryModelMasterResult parse(JsonNode data) {
            return UpdateLotteryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/lottery/{lotteryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null || this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("mode", request.getMode());
                    put("method", request.getMethod());
                    put("prizeTableName", request.getPrizeTableName());
                    put("choicePrizeTableScriptId", request.getChoicePrizeTableScriptId());
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

    public void updateLotteryModelMasterAsync(
            UpdateLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateLotteryModelMasterResult>> callback
    ) {
        UpdateLotteryModelMasterTask task = new UpdateLotteryModelMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateLotteryModelMasterResult updateLotteryModelMaster(
            UpdateLotteryModelMasterRequest request
    ) {
        final AsyncResult<UpdateLotteryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateLotteryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteLotteryModelMasterTask extends Gs2RestSessionTask<DeleteLotteryModelMasterResult> {
        private DeleteLotteryModelMasterRequest request;

        public DeleteLotteryModelMasterTask(
            DeleteLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteLotteryModelMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteLotteryModelMasterResult parse(JsonNode data) {
            return DeleteLotteryModelMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/lottery/{lotteryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null || this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));

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

    public void deleteLotteryModelMasterAsync(
            DeleteLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteLotteryModelMasterResult>> callback
    ) {
        DeleteLotteryModelMasterTask task = new DeleteLotteryModelMasterTask(request, callback);
        session.execute(task);
    }

    public DeleteLotteryModelMasterResult deleteLotteryModelMaster(
            DeleteLotteryModelMasterRequest request
    ) {
        final AsyncResult<DeleteLotteryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteLotteryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribePrizeTableMastersTask extends Gs2RestSessionTask<DescribePrizeTableMastersResult> {
        private DescribePrizeTableMastersRequest request;

        public DescribePrizeTableMastersTask(
            DescribePrizeTableMastersRequest request,
            AsyncAction<AsyncResult<DescribePrizeTableMastersResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribePrizeTableMastersResult parse(JsonNode data) {
            return DescribePrizeTableMastersResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/table";

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

    public void describePrizeTableMastersAsync(
            DescribePrizeTableMastersRequest request,
            AsyncAction<AsyncResult<DescribePrizeTableMastersResult>> callback
    ) {
        DescribePrizeTableMastersTask task = new DescribePrizeTableMastersTask(request, callback);
        session.execute(task);
    }

    public DescribePrizeTableMastersResult describePrizeTableMasters(
            DescribePrizeTableMastersRequest request
    ) {
        final AsyncResult<DescribePrizeTableMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describePrizeTableMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreatePrizeTableMasterTask extends Gs2RestSessionTask<CreatePrizeTableMasterResult> {
        private CreatePrizeTableMasterRequest request;

        public CreatePrizeTableMasterTask(
            CreatePrizeTableMasterRequest request,
            AsyncAction<AsyncResult<CreatePrizeTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreatePrizeTableMasterResult parse(JsonNode data) {
            return CreatePrizeTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/table";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("prizes", request.getPrizes() == null ? new ArrayList<Prize>() :
                        request.getPrizes().stream().map(item -> {
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

    public void createPrizeTableMasterAsync(
            CreatePrizeTableMasterRequest request,
            AsyncAction<AsyncResult<CreatePrizeTableMasterResult>> callback
    ) {
        CreatePrizeTableMasterTask task = new CreatePrizeTableMasterTask(request, callback);
        session.execute(task);
    }

    public CreatePrizeTableMasterResult createPrizeTableMaster(
            CreatePrizeTableMasterRequest request
    ) {
        final AsyncResult<CreatePrizeTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createPrizeTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPrizeTableMasterTask extends Gs2RestSessionTask<GetPrizeTableMasterResult> {
        private GetPrizeTableMasterRequest request;

        public GetPrizeTableMasterTask(
            GetPrizeTableMasterRequest request,
            AsyncAction<AsyncResult<GetPrizeTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPrizeTableMasterResult parse(JsonNode data) {
            return GetPrizeTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/table/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null || this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));

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

    public void getPrizeTableMasterAsync(
            GetPrizeTableMasterRequest request,
            AsyncAction<AsyncResult<GetPrizeTableMasterResult>> callback
    ) {
        GetPrizeTableMasterTask task = new GetPrizeTableMasterTask(request, callback);
        session.execute(task);
    }

    public GetPrizeTableMasterResult getPrizeTableMaster(
            GetPrizeTableMasterRequest request
    ) {
        final AsyncResult<GetPrizeTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPrizeTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdatePrizeTableMasterTask extends Gs2RestSessionTask<UpdatePrizeTableMasterResult> {
        private UpdatePrizeTableMasterRequest request;

        public UpdatePrizeTableMasterTask(
            UpdatePrizeTableMasterRequest request,
            AsyncAction<AsyncResult<UpdatePrizeTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdatePrizeTableMasterResult parse(JsonNode data) {
            return UpdatePrizeTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/table/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null || this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("description", request.getDescription());
                    put("metadata", request.getMetadata());
                    put("prizes", request.getPrizes() == null ? new ArrayList<Prize>() :
                        request.getPrizes().stream().map(item -> {
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

    public void updatePrizeTableMasterAsync(
            UpdatePrizeTableMasterRequest request,
            AsyncAction<AsyncResult<UpdatePrizeTableMasterResult>> callback
    ) {
        UpdatePrizeTableMasterTask task = new UpdatePrizeTableMasterTask(request, callback);
        session.execute(task);
    }

    public UpdatePrizeTableMasterResult updatePrizeTableMaster(
            UpdatePrizeTableMasterRequest request
    ) {
        final AsyncResult<UpdatePrizeTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updatePrizeTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeletePrizeTableMasterTask extends Gs2RestSessionTask<DeletePrizeTableMasterResult> {
        private DeletePrizeTableMasterRequest request;

        public DeletePrizeTableMasterTask(
            DeletePrizeTableMasterRequest request,
            AsyncAction<AsyncResult<DeletePrizeTableMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeletePrizeTableMasterResult parse(JsonNode data) {
            return DeletePrizeTableMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/table/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null || this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));

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

    public void deletePrizeTableMasterAsync(
            DeletePrizeTableMasterRequest request,
            AsyncAction<AsyncResult<DeletePrizeTableMasterResult>> callback
    ) {
        DeletePrizeTableMasterTask task = new DeletePrizeTableMasterTask(request, callback);
        session.execute(task);
    }

    public DeletePrizeTableMasterResult deletePrizeTableMaster(
            DeletePrizeTableMasterRequest request
    ) {
        final AsyncResult<DeletePrizeTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deletePrizeTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeLotteryModelsTask extends Gs2RestSessionTask<DescribeLotteryModelsResult> {
        private DescribeLotteryModelsRequest request;

        public DescribeLotteryModelsTask(
            DescribeLotteryModelsRequest request,
            AsyncAction<AsyncResult<DescribeLotteryModelsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeLotteryModelsResult parse(JsonNode data) {
            return DescribeLotteryModelsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/lottery";

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

    public void describeLotteryModelsAsync(
            DescribeLotteryModelsRequest request,
            AsyncAction<AsyncResult<DescribeLotteryModelsResult>> callback
    ) {
        DescribeLotteryModelsTask task = new DescribeLotteryModelsTask(request, callback);
        session.execute(task);
    }

    public DescribeLotteryModelsResult describeLotteryModels(
            DescribeLotteryModelsRequest request
    ) {
        final AsyncResult<DescribeLotteryModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeLotteryModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetLotteryModelTask extends Gs2RestSessionTask<GetLotteryModelResult> {
        private GetLotteryModelRequest request;

        public GetLotteryModelTask(
            GetLotteryModelRequest request,
            AsyncAction<AsyncResult<GetLotteryModelResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetLotteryModelResult parse(JsonNode data) {
            return GetLotteryModelResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/lottery/{lotteryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null || this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));

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

    public void getLotteryModelAsync(
            GetLotteryModelRequest request,
            AsyncAction<AsyncResult<GetLotteryModelResult>> callback
    ) {
        GetLotteryModelTask task = new GetLotteryModelTask(request, callback);
        session.execute(task);
    }

    public GetLotteryModelResult getLotteryModel(
            GetLotteryModelRequest request
    ) {
        final AsyncResult<GetLotteryModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getLotteryModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribePrizeTablesTask extends Gs2RestSessionTask<DescribePrizeTablesResult> {
        private DescribePrizeTablesRequest request;

        public DescribePrizeTablesTask(
            DescribePrizeTablesRequest request,
            AsyncAction<AsyncResult<DescribePrizeTablesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribePrizeTablesResult parse(JsonNode data) {
            return DescribePrizeTablesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/table";

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

    public void describePrizeTablesAsync(
            DescribePrizeTablesRequest request,
            AsyncAction<AsyncResult<DescribePrizeTablesResult>> callback
    ) {
        DescribePrizeTablesTask task = new DescribePrizeTablesTask(request, callback);
        session.execute(task);
    }

    public DescribePrizeTablesResult describePrizeTables(
            DescribePrizeTablesRequest request
    ) {
        final AsyncResult<DescribePrizeTablesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describePrizeTablesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPrizeTableTask extends Gs2RestSessionTask<GetPrizeTableResult> {
        private GetPrizeTableRequest request;

        public GetPrizeTableTask(
            GetPrizeTableRequest request,
            AsyncAction<AsyncResult<GetPrizeTableResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPrizeTableResult parse(JsonNode data) {
            return GetPrizeTableResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/table/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null || this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));

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

    public void getPrizeTableAsync(
            GetPrizeTableRequest request,
            AsyncAction<AsyncResult<GetPrizeTableResult>> callback
    ) {
        GetPrizeTableTask task = new GetPrizeTableTask(request, callback);
        session.execute(task);
    }

    public GetPrizeTableResult getPrizeTable(
            GetPrizeTableRequest request
    ) {
        final AsyncResult<GetPrizeTableResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPrizeTableAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DrawByUserIdTask extends Gs2RestSessionTask<DrawByUserIdResult> {
        private DrawByUserIdRequest request;

        public DrawByUserIdTask(
            DrawByUserIdRequest request,
            AsyncAction<AsyncResult<DrawByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DrawByUserIdResult parse(JsonNode data) {
            return DrawByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/lottery/{lotteryName}/draw";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null || this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("count", request.getCount());
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

            builder
                .build()
                .send();
        }
    }

    public void drawByUserIdAsync(
            DrawByUserIdRequest request,
            AsyncAction<AsyncResult<DrawByUserIdResult>> callback
    ) {
        DrawByUserIdTask task = new DrawByUserIdTask(request, callback);
        session.execute(task);
    }

    public DrawByUserIdResult drawByUserId(
            DrawByUserIdRequest request
    ) {
        final AsyncResult<DrawByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        drawByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PredictionTask extends Gs2RestSessionTask<PredictionResult> {
        private PredictionRequest request;

        public PredictionTask(
            PredictionRequest request,
            AsyncAction<AsyncResult<PredictionResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PredictionResult parse(JsonNode data) {
            return PredictionResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/lottery/{lotteryName}/prediction";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null || this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("randomSeed", request.getRandomSeed());
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

            builder
                .build()
                .send();
        }
    }

    public void predictionAsync(
            PredictionRequest request,
            AsyncAction<AsyncResult<PredictionResult>> callback
    ) {
        PredictionTask task = new PredictionTask(request, callback);
        session.execute(task);
    }

    public PredictionResult prediction(
            PredictionRequest request
    ) {
        final AsyncResult<PredictionResult>[] resultAsyncResult = new AsyncResult[]{null};
        predictionAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PredictionByUserIdTask extends Gs2RestSessionTask<PredictionByUserIdResult> {
        private PredictionByUserIdRequest request;

        public PredictionByUserIdTask(
            PredictionByUserIdRequest request,
            AsyncAction<AsyncResult<PredictionByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PredictionByUserIdResult parse(JsonNode data) {
            return PredictionByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/lottery/{lotteryName}/prediction";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null || this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("randomSeed", request.getRandomSeed());
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

            builder
                .build()
                .send();
        }
    }

    public void predictionByUserIdAsync(
            PredictionByUserIdRequest request,
            AsyncAction<AsyncResult<PredictionByUserIdResult>> callback
    ) {
        PredictionByUserIdTask task = new PredictionByUserIdTask(request, callback);
        session.execute(task);
    }

    public PredictionByUserIdResult predictionByUserId(
            PredictionByUserIdRequest request
    ) {
        final AsyncResult<PredictionByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        predictionByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DrawWithRandomSeedByUserIdTask extends Gs2RestSessionTask<DrawWithRandomSeedByUserIdResult> {
        private DrawWithRandomSeedByUserIdRequest request;

        public DrawWithRandomSeedByUserIdTask(
            DrawWithRandomSeedByUserIdRequest request,
            AsyncAction<AsyncResult<DrawWithRandomSeedByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DrawWithRandomSeedByUserIdResult parse(JsonNode data) {
            return DrawWithRandomSeedByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/lottery/{lotteryName}/draw/withSeed";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null || this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("randomSeed", request.getRandomSeed());
                    put("count", request.getCount());
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

            builder
                .build()
                .send();
        }
    }

    public void drawWithRandomSeedByUserIdAsync(
            DrawWithRandomSeedByUserIdRequest request,
            AsyncAction<AsyncResult<DrawWithRandomSeedByUserIdResult>> callback
    ) {
        DrawWithRandomSeedByUserIdTask task = new DrawWithRandomSeedByUserIdTask(request, callback);
        session.execute(task);
    }

    public DrawWithRandomSeedByUserIdResult drawWithRandomSeedByUserId(
            DrawWithRandomSeedByUserIdRequest request
    ) {
        final AsyncResult<DrawWithRandomSeedByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        drawWithRandomSeedByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DrawByStampSheetTask extends Gs2RestSessionTask<DrawByStampSheetResult> {
        private DrawByStampSheetRequest request;

        public DrawByStampSheetTask(
            DrawByStampSheetRequest request,
            AsyncAction<AsyncResult<DrawByStampSheetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DrawByStampSheetResult parse(JsonNode data) {
            return DrawByStampSheetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/draw";

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

    public void drawByStampSheetAsync(
            DrawByStampSheetRequest request,
            AsyncAction<AsyncResult<DrawByStampSheetResult>> callback
    ) {
        DrawByStampSheetTask task = new DrawByStampSheetTask(request, callback);
        session.execute(task);
    }

    public DrawByStampSheetResult drawByStampSheet(
            DrawByStampSheetRequest request
    ) {
        final AsyncResult<DrawByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        drawByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeProbabilitiesTask extends Gs2RestSessionTask<DescribeProbabilitiesResult> {
        private DescribeProbabilitiesRequest request;

        public DescribeProbabilitiesTask(
            DescribeProbabilitiesRequest request,
            AsyncAction<AsyncResult<DescribeProbabilitiesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeProbabilitiesResult parse(JsonNode data) {
            return DescribeProbabilitiesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/lottery/{lotteryName}/probability";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null || this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));

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

    public void describeProbabilitiesAsync(
            DescribeProbabilitiesRequest request,
            AsyncAction<AsyncResult<DescribeProbabilitiesResult>> callback
    ) {
        DescribeProbabilitiesTask task = new DescribeProbabilitiesTask(request, callback);
        session.execute(task);
    }

    public DescribeProbabilitiesResult describeProbabilities(
            DescribeProbabilitiesRequest request
    ) {
        final AsyncResult<DescribeProbabilitiesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeProbabilitiesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeProbabilitiesByUserIdTask extends Gs2RestSessionTask<DescribeProbabilitiesByUserIdResult> {
        private DescribeProbabilitiesByUserIdRequest request;

        public DescribeProbabilitiesByUserIdTask(
            DescribeProbabilitiesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeProbabilitiesByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeProbabilitiesByUserIdResult parse(JsonNode data) {
            return DescribeProbabilitiesByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/lottery/{lotteryName}/probability";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null || this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));
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

            builder
                .build()
                .send();
        }
    }

    public void describeProbabilitiesByUserIdAsync(
            DescribeProbabilitiesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeProbabilitiesByUserIdResult>> callback
    ) {
        DescribeProbabilitiesByUserIdTask task = new DescribeProbabilitiesByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeProbabilitiesByUserIdResult describeProbabilitiesByUserId(
            DescribeProbabilitiesByUserIdRequest request
    ) {
        final AsyncResult<DescribeProbabilitiesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeProbabilitiesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "lottery")
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

    class GetCurrentLotteryMasterTask extends Gs2RestSessionTask<GetCurrentLotteryMasterResult> {
        private GetCurrentLotteryMasterRequest request;

        public GetCurrentLotteryMasterTask(
            GetCurrentLotteryMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentLotteryMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCurrentLotteryMasterResult parse(JsonNode data) {
            return GetCurrentLotteryMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
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

    public void getCurrentLotteryMasterAsync(
            GetCurrentLotteryMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentLotteryMasterResult>> callback
    ) {
        GetCurrentLotteryMasterTask task = new GetCurrentLotteryMasterTask(request, callback);
        session.execute(task);
    }

    public GetCurrentLotteryMasterResult getCurrentLotteryMaster(
            GetCurrentLotteryMasterRequest request
    ) {
        final AsyncResult<GetCurrentLotteryMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentLotteryMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentLotteryMasterTask extends Gs2RestSessionTask<UpdateCurrentLotteryMasterResult> {
        private UpdateCurrentLotteryMasterRequest request;

        public UpdateCurrentLotteryMasterTask(
            UpdateCurrentLotteryMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentLotteryMasterResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentLotteryMasterResult parse(JsonNode data) {
            return UpdateCurrentLotteryMasterResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
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

    public void updateCurrentLotteryMasterAsync(
            UpdateCurrentLotteryMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentLotteryMasterResult>> callback
    ) {
        UpdateCurrentLotteryMasterTask task = new UpdateCurrentLotteryMasterTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentLotteryMasterResult updateCurrentLotteryMaster(
            UpdateCurrentLotteryMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentLotteryMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentLotteryMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentLotteryMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentLotteryMasterFromGitHubResult> {
        private UpdateCurrentLotteryMasterFromGitHubRequest request;

        public UpdateCurrentLotteryMasterFromGitHubTask(
            UpdateCurrentLotteryMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentLotteryMasterFromGitHubResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateCurrentLotteryMasterFromGitHubResult parse(JsonNode data) {
            return UpdateCurrentLotteryMasterFromGitHubResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
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

    public void updateCurrentLotteryMasterFromGitHubAsync(
            UpdateCurrentLotteryMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentLotteryMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentLotteryMasterFromGitHubTask task = new UpdateCurrentLotteryMasterFromGitHubTask(request, callback);
        session.execute(task);
    }

    public UpdateCurrentLotteryMasterFromGitHubResult updateCurrentLotteryMasterFromGitHub(
            UpdateCurrentLotteryMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentLotteryMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentLotteryMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribePrizeLimitsTask extends Gs2RestSessionTask<DescribePrizeLimitsResult> {
        private DescribePrizeLimitsRequest request;

        public DescribePrizeLimitsTask(
            DescribePrizeLimitsRequest request,
            AsyncAction<AsyncResult<DescribePrizeLimitsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribePrizeLimitsResult parse(JsonNode data) {
            return DescribePrizeLimitsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/prizeLimit/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null || this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));

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

    public void describePrizeLimitsAsync(
            DescribePrizeLimitsRequest request,
            AsyncAction<AsyncResult<DescribePrizeLimitsResult>> callback
    ) {
        DescribePrizeLimitsTask task = new DescribePrizeLimitsTask(request, callback);
        session.execute(task);
    }

    public DescribePrizeLimitsResult describePrizeLimits(
            DescribePrizeLimitsRequest request
    ) {
        final AsyncResult<DescribePrizeLimitsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describePrizeLimitsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPrizeLimitTask extends Gs2RestSessionTask<GetPrizeLimitResult> {
        private GetPrizeLimitRequest request;

        public GetPrizeLimitTask(
            GetPrizeLimitRequest request,
            AsyncAction<AsyncResult<GetPrizeLimitResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetPrizeLimitResult parse(JsonNode data) {
            return GetPrizeLimitResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/prizeLimit/{prizeTableName}/{prizeId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null || this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));
            url = url.replace("{prizeId}", this.request.getPrizeId() == null || this.request.getPrizeId().length() == 0 ? "null" : String.valueOf(this.request.getPrizeId()));

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

    public void getPrizeLimitAsync(
            GetPrizeLimitRequest request,
            AsyncAction<AsyncResult<GetPrizeLimitResult>> callback
    ) {
        GetPrizeLimitTask task = new GetPrizeLimitTask(request, callback);
        session.execute(task);
    }

    public GetPrizeLimitResult getPrizeLimit(
            GetPrizeLimitRequest request
    ) {
        final AsyncResult<GetPrizeLimitResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPrizeLimitAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ResetPrizeLimitTask extends Gs2RestSessionTask<ResetPrizeLimitResult> {
        private ResetPrizeLimitRequest request;

        public ResetPrizeLimitTask(
            ResetPrizeLimitRequest request,
            AsyncAction<AsyncResult<ResetPrizeLimitResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ResetPrizeLimitResult parse(JsonNode data) {
            return ResetPrizeLimitResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/prizeLimit/{prizeTableName}/{prizeId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null || this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));
            url = url.replace("{prizeId}", this.request.getPrizeId() == null || this.request.getPrizeId().length() == 0 ? "null" : String.valueOf(this.request.getPrizeId()));

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

    public void resetPrizeLimitAsync(
            ResetPrizeLimitRequest request,
            AsyncAction<AsyncResult<ResetPrizeLimitResult>> callback
    ) {
        ResetPrizeLimitTask task = new ResetPrizeLimitTask(request, callback);
        session.execute(task);
    }

    public ResetPrizeLimitResult resetPrizeLimit(
            ResetPrizeLimitRequest request
    ) {
        final AsyncResult<ResetPrizeLimitResult>[] resultAsyncResult = new AsyncResult[]{null};
        resetPrizeLimitAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeBoxesTask extends Gs2RestSessionTask<DescribeBoxesResult> {
        private DescribeBoxesRequest request;

        public DescribeBoxesTask(
            DescribeBoxesRequest request,
            AsyncAction<AsyncResult<DescribeBoxesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBoxesResult parse(JsonNode data) {
            return DescribeBoxesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/box";

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

    public void describeBoxesAsync(
            DescribeBoxesRequest request,
            AsyncAction<AsyncResult<DescribeBoxesResult>> callback
    ) {
        DescribeBoxesTask task = new DescribeBoxesTask(request, callback);
        session.execute(task);
    }

    public DescribeBoxesResult describeBoxes(
            DescribeBoxesRequest request
    ) {
        final AsyncResult<DescribeBoxesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBoxesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeBoxesByUserIdTask extends Gs2RestSessionTask<DescribeBoxesByUserIdResult> {
        private DescribeBoxesByUserIdRequest request;

        public DescribeBoxesByUserIdTask(
            DescribeBoxesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeBoxesByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBoxesByUserIdResult parse(JsonNode data) {
            return DescribeBoxesByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/box";

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

            builder
                .build()
                .send();
        }
    }

    public void describeBoxesByUserIdAsync(
            DescribeBoxesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeBoxesByUserIdResult>> callback
    ) {
        DescribeBoxesByUserIdTask task = new DescribeBoxesByUserIdTask(request, callback);
        session.execute(task);
    }

    public DescribeBoxesByUserIdResult describeBoxesByUserId(
            DescribeBoxesByUserIdRequest request
    ) {
        final AsyncResult<DescribeBoxesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBoxesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetBoxTask extends Gs2RestSessionTask<GetBoxResult> {
        private GetBoxRequest request;

        public GetBoxTask(
            GetBoxRequest request,
            AsyncAction<AsyncResult<GetBoxResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBoxResult parse(JsonNode data) {
            return GetBoxResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/box/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null || this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));

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

    public void getBoxAsync(
            GetBoxRequest request,
            AsyncAction<AsyncResult<GetBoxResult>> callback
    ) {
        GetBoxTask task = new GetBoxTask(request, callback);
        session.execute(task);
    }

    public GetBoxResult getBox(
            GetBoxRequest request
    ) {
        final AsyncResult<GetBoxResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBoxAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetBoxByUserIdTask extends Gs2RestSessionTask<GetBoxByUserIdResult> {
        private GetBoxByUserIdRequest request;

        public GetBoxByUserIdTask(
            GetBoxByUserIdRequest request,
            AsyncAction<AsyncResult<GetBoxByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBoxByUserIdResult parse(JsonNode data) {
            return GetBoxByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/box/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null || this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));
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

            builder
                .build()
                .send();
        }
    }

    public void getBoxByUserIdAsync(
            GetBoxByUserIdRequest request,
            AsyncAction<AsyncResult<GetBoxByUserIdResult>> callback
    ) {
        GetBoxByUserIdTask task = new GetBoxByUserIdTask(request, callback);
        session.execute(task);
    }

    public GetBoxByUserIdResult getBoxByUserId(
            GetBoxByUserIdRequest request
    ) {
        final AsyncResult<GetBoxByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBoxByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ResetBoxTask extends Gs2RestSessionTask<ResetBoxResult> {
        private ResetBoxRequest request;

        public ResetBoxTask(
            ResetBoxRequest request,
            AsyncAction<AsyncResult<ResetBoxResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ResetBoxResult parse(JsonNode data) {
            return ResetBoxResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/box/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null || this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));

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

    public void resetBoxAsync(
            ResetBoxRequest request,
            AsyncAction<AsyncResult<ResetBoxResult>> callback
    ) {
        ResetBoxTask task = new ResetBoxTask(request, callback);
        session.execute(task);
    }

    public ResetBoxResult resetBox(
            ResetBoxRequest request
    ) {
        final AsyncResult<ResetBoxResult>[] resultAsyncResult = new AsyncResult[]{null};
        resetBoxAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ResetBoxByUserIdTask extends Gs2RestSessionTask<ResetBoxByUserIdResult> {
        private ResetBoxByUserIdRequest request;

        public ResetBoxByUserIdTask(
            ResetBoxByUserIdRequest request,
            AsyncAction<AsyncResult<ResetBoxByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ResetBoxByUserIdResult parse(JsonNode data) {
            return ResetBoxByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/box/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null || this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));
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

            builder
                .build()
                .send();
        }
    }

    public void resetBoxByUserIdAsync(
            ResetBoxByUserIdRequest request,
            AsyncAction<AsyncResult<ResetBoxByUserIdResult>> callback
    ) {
        ResetBoxByUserIdTask task = new ResetBoxByUserIdTask(request, callback);
        session.execute(task);
    }

    public ResetBoxByUserIdResult resetBoxByUserId(
            ResetBoxByUserIdRequest request
    ) {
        final AsyncResult<ResetBoxByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        resetBoxByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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