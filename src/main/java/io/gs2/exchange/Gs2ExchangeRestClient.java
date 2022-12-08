
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
import io.gs2.exchange.model.*;public class Gs2ExchangeRestClient extends AbstractGs2Client<Gs2ExchangeRestClient> {

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
                    put("enableSkip", request.getEnableSkip());
                    put("skipConsumeActions", request.getSkipConsumeActions() == null ? new ArrayList<ConsumeAction>() :
                        request.getSkipConsumeActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("acquireActions", request.getAcquireActions() == null ? new ArrayList<AcquireAction>() :
                        request.getAcquireActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("consumeActions", request.getConsumeActions() == null ? new ArrayList<ConsumeAction>() :
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
                    put("enableSkip", request.getEnableSkip());
                    put("skipConsumeActions", request.getSkipConsumeActions() == null ? new ArrayList<ConsumeAction>() :
                        request.getSkipConsumeActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("acquireActions", request.getAcquireActions() == null ? new ArrayList<AcquireAction>() :
                        request.getAcquireActions().stream().map(item -> {
                            //noinspection Convert2MethodRef
                            return item.toJson();
                        }
                    ).collect(Collectors.toList()));
                    put("consumeActions", request.getConsumeActions() == null ? new ArrayList<ConsumeAction>() :
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

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
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
                + "/{namespaceName}/user/me/exchange/{rateName}/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
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
                + "/{namespaceName}/user/{userId}/exchange/{rateName}/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
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
                + "/{namespaceName}/user/me/exchange/{rateName}/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null || this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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
                + "/{namespaceName}/user/{userId}/exchange/{rateName}/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null || this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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
                + "/{namespaceName}/user/{userId}/exchange/{rateName}/await/{awaitName}/force";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null || this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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

    class SkipTask extends Gs2RestSessionTask<SkipResult> {
        private SkipRequest request;

        public SkipTask(
            SkipRequest request,
            AsyncAction<AsyncResult<SkipResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SkipResult parse(JsonNode data) {
            return SkipResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/exchange/{rateName}/await/{awaitName}/skip";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null || this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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

    public void skipAsync(
            SkipRequest request,
            AsyncAction<AsyncResult<SkipResult>> callback
    ) {
        SkipTask task = new SkipTask(request, callback);
        session.execute(task);
    }

    public SkipResult skip(
            SkipRequest request
    ) {
        final AsyncResult<SkipResult>[] resultAsyncResult = new AsyncResult[]{null};
        skipAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                + "/{namespaceName}/user/{userId}/exchange/{rateName}/await/{awaitName}/skip";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null || this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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
                + "/{namespaceName}/user/me/exchange/{rateName}/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
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
                + "/{namespaceName}/user/{userId}/exchange/{rateName}/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null || this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rateName}", this.request.getRateName() == null || this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
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