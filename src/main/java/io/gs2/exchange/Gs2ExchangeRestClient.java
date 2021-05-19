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
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.json.JSONArray;

import io.gs2.core.model.AsyncAction;
import io.gs2.core.model.AsyncResult;
import io.gs2.core.exception.*;
import io.gs2.core.net.*;
import io.gs2.core.util.EncodingUtil;

import io.gs2.core.AbstractGs2Client;
import io.gs2.exchange.request.*;
import io.gs2.exchange.result.*;
import io.gs2.exchange.model.*;

/**
 * GS2 Exchange API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2ExchangeRestClient extends AbstractGs2Client<Gs2ExchangeRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2ExchangeRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class DescribeNamespacesTask extends Gs2RestSessionTask<DescribeNamespacesResult> {
        private DescribeNamespacesRequest request;

        public DescribeNamespacesTask(
            DescribeNamespacesRequest request,
            AsyncAction<AsyncResult<DescribeNamespacesResult>> userCallback,
            Class<DescribeNamespacesResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
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

    /**
     * ネームスペースの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeNamespacesAsync(
            DescribeNamespacesRequest request,
            AsyncAction<AsyncResult<DescribeNamespacesResult>> callback
    ) {
        DescribeNamespacesTask task = new DescribeNamespacesTask(request, callback, DescribeNamespacesResult.class);
        session.execute(task);
    }

    /**
     * ネームスペースの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<CreateNamespaceResult>> userCallback,
            Class<CreateNamespaceResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getEnableAwaitExchange() != null) {
                json.put("enableAwaitExchange", this.request.getEnableAwaitExchange());
            }
            if (this.request.getEnableDirectExchange() != null) {
                json.put("enableDirectExchange", this.request.getEnableDirectExchange());
            }
            if (this.request.getQueueNamespaceId() != null) {
                json.put("queueNamespaceId", this.request.getQueueNamespaceId());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getLogSetting() != null) {
                try {
                    json.put("logSetting", new JSONObject(mapper.writeValueAsString(this.request.getLogSetting())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * ネームスペースを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createNamespaceAsync(
            CreateNamespaceRequest request,
            AsyncAction<AsyncResult<CreateNamespaceResult>> callback
    ) {
        CreateNamespaceTask task = new CreateNamespaceTask(request, callback, CreateNamespaceResult.class);
        session.execute(task);
    }

    /**
     * ネームスペースを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetNamespaceStatusResult>> userCallback,
            Class<GetNamespaceStatusResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/status";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * ネームスペースを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getNamespaceStatusAsync(
            GetNamespaceStatusRequest request,
            AsyncAction<AsyncResult<GetNamespaceStatusResult>> callback
    ) {
        GetNamespaceStatusTask task = new GetNamespaceStatusTask(request, callback, GetNamespaceStatusResult.class);
        session.execute(task);
    }

    /**
     * ネームスペースを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetNamespaceResult>> userCallback,
            Class<GetNamespaceResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * ネームスペースを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getNamespaceAsync(
            GetNamespaceRequest request,
            AsyncAction<AsyncResult<GetNamespaceResult>> callback
    ) {
        GetNamespaceTask task = new GetNamespaceTask(request, callback, GetNamespaceResult.class);
        session.execute(task);
    }

    /**
     * ネームスペースを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<UpdateNamespaceResult>> userCallback,
            Class<UpdateNamespaceResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getEnableAwaitExchange() != null) {
                json.put("enableAwaitExchange", this.request.getEnableAwaitExchange());
            }
            if (this.request.getEnableDirectExchange() != null) {
                json.put("enableDirectExchange", this.request.getEnableDirectExchange());
            }
            if (this.request.getQueueNamespaceId() != null) {
                json.put("queueNamespaceId", this.request.getQueueNamespaceId());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getLogSetting() != null) {
                try {
                    json.put("logSetting", new JSONObject(mapper.writeValueAsString(this.request.getLogSetting())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * ネームスペースを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateNamespaceAsync(
            UpdateNamespaceRequest request,
            AsyncAction<AsyncResult<UpdateNamespaceResult>> callback
    ) {
        UpdateNamespaceTask task = new UpdateNamespaceTask(request, callback, UpdateNamespaceResult.class);
        session.execute(task);
    }

    /**
     * ネームスペースを更新<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DeleteNamespaceResult>> userCallback,
            Class<DeleteNamespaceResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * ネームスペースを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteNamespaceAsync(
            DeleteNamespaceRequest request,
            AsyncAction<AsyncResult<DeleteNamespaceResult>> callback
    ) {
        DeleteNamespaceTask task = new DeleteNamespaceTask(request, callback, DeleteNamespaceResult.class);
        session.execute(task);
    }

    /**
     * ネームスペースを削除<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DescribeRateModelsResult>> userCallback,
            Class<DescribeRateModelsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * 交換レートモデルの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeRateModelsAsync(
            DescribeRateModelsRequest request,
            AsyncAction<AsyncResult<DescribeRateModelsResult>> callback
    ) {
        DescribeRateModelsTask task = new DescribeRateModelsTask(request, callback, DescribeRateModelsResult.class);
        session.execute(task);
    }

    /**
     * 交換レートモデルの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetRateModelResult>> userCallback,
            Class<GetRateModelResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

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

    /**
     * 交換レートモデルを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getRateModelAsync(
            GetRateModelRequest request,
            AsyncAction<AsyncResult<GetRateModelResult>> callback
    ) {
        GetRateModelTask task = new GetRateModelTask(request, callback, GetRateModelResult.class);
        session.execute(task);
    }

    /**
     * 交換レートモデルを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DescribeRateModelMastersResult>> userCallback,
            Class<DescribeRateModelMastersResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * 交換レートマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeRateModelMastersAsync(
            DescribeRateModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeRateModelMastersResult>> callback
    ) {
        DescribeRateModelMastersTask task = new DescribeRateModelMastersTask(request, callback, DescribeRateModelMastersResult.class);
        session.execute(task);
    }

    /**
     * 交換レートマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<CreateRateModelMasterResult>> userCallback,
            Class<CreateRateModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getTimingType() != null) {
                json.put("timingType", this.request.getTimingType());
            }
            if (this.request.getLockTime() != null) {
                json.put("lockTime", this.request.getLockTime());
            }
            if (this.request.getEnableSkip() != null) {
                json.put("enableSkip", this.request.getEnableSkip());
            }
            if (this.request.getSkipConsumeActions() != null) {
                JSONArray array = new JSONArray();
                for(ConsumeAction item : this.request.getSkipConsumeActions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("skipConsumeActions", array);
            }
            if (this.request.getAcquireActions() != null) {
                JSONArray array = new JSONArray();
                for(AcquireAction item : this.request.getAcquireActions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("acquireActions", array);
            }
            if (this.request.getConsumeActions() != null) {
                JSONArray array = new JSONArray();
                for(ConsumeAction item : this.request.getConsumeActions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("consumeActions", array);
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 交換レートマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createRateModelMasterAsync(
            CreateRateModelMasterRequest request,
            AsyncAction<AsyncResult<CreateRateModelMasterResult>> callback
    ) {
        CreateRateModelMasterTask task = new CreateRateModelMasterTask(request, callback, CreateRateModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 交換レートマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetRateModelMasterResult>> userCallback,
            Class<GetRateModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

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

    /**
     * 交換レートマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getRateModelMasterAsync(
            GetRateModelMasterRequest request,
            AsyncAction<AsyncResult<GetRateModelMasterResult>> callback
    ) {
        GetRateModelMasterTask task = new GetRateModelMasterTask(request, callback, GetRateModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 交換レートマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<UpdateRateModelMasterResult>> userCallback,
            Class<UpdateRateModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getTimingType() != null) {
                json.put("timingType", this.request.getTimingType());
            }
            if (this.request.getLockTime() != null) {
                json.put("lockTime", this.request.getLockTime());
            }
            if (this.request.getEnableSkip() != null) {
                json.put("enableSkip", this.request.getEnableSkip());
            }
            if (this.request.getSkipConsumeActions() != null) {
                JSONArray array = new JSONArray();
                for(ConsumeAction item : this.request.getSkipConsumeActions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("skipConsumeActions", array);
            }
            if (this.request.getAcquireActions() != null) {
                JSONArray array = new JSONArray();
                for(AcquireAction item : this.request.getAcquireActions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("acquireActions", array);
            }
            if (this.request.getConsumeActions() != null) {
                JSONArray array = new JSONArray();
                for(ConsumeAction item : this.request.getConsumeActions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("consumeActions", array);
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 交換レートマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateRateModelMasterAsync(
            UpdateRateModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateRateModelMasterResult>> callback
    ) {
        UpdateRateModelMasterTask task = new UpdateRateModelMasterTask(request, callback, UpdateRateModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 交換レートマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DeleteRateModelMasterResult>> userCallback,
            Class<DeleteRateModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

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

    /**
     * 交換レートマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteRateModelMasterAsync(
            DeleteRateModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteRateModelMasterResult>> callback
    ) {
        DeleteRateModelMasterTask task = new DeleteRateModelMasterTask(request, callback, DeleteRateModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 交換レートマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<ExchangeResult>> userCallback,
            Class<ExchangeResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/exchange/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCount() != null) {
                json.put("count", this.request.getCount());
            }
            if (this.request.getConfig() != null) {
                JSONArray array = new JSONArray();
                for(Config item : this.request.getConfig())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("config", array);
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 交換を実行<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void exchangeAsync(
            ExchangeRequest request,
            AsyncAction<AsyncResult<ExchangeResult>> callback
    ) {
        ExchangeTask task = new ExchangeTask(request, callback, ExchangeResult.class);
        session.execute(task);
    }

    /**
     * 交換を実行<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<ExchangeByUserIdResult>> userCallback,
            Class<ExchangeByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCount() != null) {
                json.put("count", this.request.getCount());
            }
            if (this.request.getConfig() != null) {
                JSONArray array = new JSONArray();
                for(Config item : this.request.getConfig())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("config", array);
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * ユーザIDを指定して交換を実行<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void exchangeByUserIdAsync(
            ExchangeByUserIdRequest request,
            AsyncAction<AsyncResult<ExchangeByUserIdResult>> callback
    ) {
        ExchangeByUserIdTask task = new ExchangeByUserIdTask(request, callback, ExchangeByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して交換を実行<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<ExchangeByStampSheetResult>> userCallback,
            Class<ExchangeByStampSheetResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/exchange";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getStampSheet() != null) {
                json.put("stampSheet", this.request.getStampSheet());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * スタンプシートで交換を実行<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void exchangeByStampSheetAsync(
            ExchangeByStampSheetRequest request,
            AsyncAction<AsyncResult<ExchangeByStampSheetResult>> callback
    ) {
        ExchangeByStampSheetTask task = new ExchangeByStampSheetTask(request, callback, ExchangeByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートで交換を実行<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<ExportMasterResult>> userCallback,
            Class<ExportMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/export";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * 現在有効な交換レート設定のマスターデータをエクスポートします<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void exportMasterAsync(
            ExportMasterRequest request,
            AsyncAction<AsyncResult<ExportMasterResult>> callback
    ) {
        ExportMasterTask task = new ExportMasterTask(request, callback, ExportMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な交換レート設定のマスターデータをエクスポートします<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetCurrentRateMasterResult>> userCallback,
            Class<GetCurrentRateMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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

    /**
     * 現在有効な交換レート設定を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentRateMasterAsync(
            GetCurrentRateMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentRateMasterResult>> callback
    ) {
        GetCurrentRateMasterTask task = new GetCurrentRateMasterTask(request, callback, GetCurrentRateMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な交換レート設定を取得します<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<UpdateCurrentRateMasterResult>> userCallback,
            Class<UpdateCurrentRateMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getSettings() != null) {
                json.put("settings", this.request.getSettings());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 現在有効な交換レート設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentRateMasterAsync(
            UpdateCurrentRateMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRateMasterResult>> callback
    ) {
        UpdateCurrentRateMasterTask task = new UpdateCurrentRateMasterTask(request, callback, UpdateCurrentRateMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な交換レート設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<UpdateCurrentRateMasterFromGitHubResult>> userCallback,
            Class<UpdateCurrentRateMasterFromGitHubResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/from_git_hub";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCheckoutSetting() != null) {
                try {
                    json.put("checkoutSetting", new JSONObject(mapper.writeValueAsString(this.request.getCheckoutSetting())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 現在有効な交換レート設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentRateMasterFromGitHubAsync(
            UpdateCurrentRateMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRateMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentRateMasterFromGitHubTask task = new UpdateCurrentRateMasterFromGitHubTask(request, callback, UpdateCurrentRateMasterFromGitHubResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な交換レート設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<CreateAwaitByUserIdResult>> userCallback,
            Class<CreateAwaitByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/{rateName}/await";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCount() != null) {
                json.put("count", this.request.getCount());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 交換待機を作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createAwaitByUserIdAsync(
            CreateAwaitByUserIdRequest request,
            AsyncAction<AsyncResult<CreateAwaitByUserIdResult>> callback
    ) {
        CreateAwaitByUserIdTask task = new CreateAwaitByUserIdTask(request, callback, CreateAwaitByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 交換待機を作成<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DescribeAwaitsResult>> userCallback,
            Class<DescribeAwaitsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/exchange/await";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * 交換待機の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeAwaitsAsync(
            DescribeAwaitsRequest request,
            AsyncAction<AsyncResult<DescribeAwaitsResult>> callback
    ) {
        DescribeAwaitsTask task = new DescribeAwaitsTask(request, callback, DescribeAwaitsResult.class);
        session.execute(task);
    }

    /**
     * 交換待機の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DescribeAwaitsByUserIdResult>> userCallback,
            Class<DescribeAwaitsByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/await";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * 交換待機の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeAwaitsByUserIdAsync(
            DescribeAwaitsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeAwaitsByUserIdResult>> callback
    ) {
        DescribeAwaitsByUserIdTask task = new DescribeAwaitsByUserIdTask(request, callback, DescribeAwaitsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 交換待機の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetAwaitResult>> userCallback,
            Class<GetAwaitResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/exchange/{rateName}/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null|| this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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

    /**
     * 交換待機を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getAwaitAsync(
            GetAwaitRequest request,
            AsyncAction<AsyncResult<GetAwaitResult>> callback
    ) {
        GetAwaitTask task = new GetAwaitTask(request, callback, GetAwaitResult.class);
        session.execute(task);
    }

    /**
     * 交換待機を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetAwaitByUserIdResult>> userCallback,
            Class<GetAwaitByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/{rateName}/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null|| this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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

            builder
                .build()
                .send();
        }
    }

    /**
     * 交換待機を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getAwaitByUserIdAsync(
            GetAwaitByUserIdRequest request,
            AsyncAction<AsyncResult<GetAwaitByUserIdResult>> callback
    ) {
        GetAwaitByUserIdTask task = new GetAwaitByUserIdTask(request, callback, GetAwaitByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 交換待機を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<AcquireResult>> userCallback,
            Class<AcquireResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/exchange/{rateName}/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null|| this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getConfig() != null) {
                JSONArray array = new JSONArray();
                for(Config item : this.request.getConfig())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("config", array);
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 交換待機の報酬を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void acquireAsync(
            AcquireRequest request,
            AsyncAction<AsyncResult<AcquireResult>> callback
    ) {
        AcquireTask task = new AcquireTask(request, callback, AcquireResult.class);
        session.execute(task);
    }

    /**
     * 交換待機の報酬を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<AcquireByUserIdResult>> userCallback,
            Class<AcquireByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/{rateName}/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null|| this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getConfig() != null) {
                JSONArray array = new JSONArray();
                for(Config item : this.request.getConfig())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("config", array);
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 交換待機の報酬を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void acquireByUserIdAsync(
            AcquireByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireByUserIdResult>> callback
    ) {
        AcquireByUserIdTask task = new AcquireByUserIdTask(request, callback, AcquireByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 交換待機の報酬を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<AcquireForceByUserIdResult>> userCallback,
            Class<AcquireForceByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/{rateName}/await/{awaitName}/force";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null|| this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getConfig() != null) {
                JSONArray array = new JSONArray();
                for(Config item : this.request.getConfig())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("config", array);
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 交換待機の報酬を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void acquireForceByUserIdAsync(
            AcquireForceByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireForceByUserIdResult>> callback
    ) {
        AcquireForceByUserIdTask task = new AcquireForceByUserIdTask(request, callback, AcquireForceByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 交換待機の報酬を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<SkipResult>> userCallback,
            Class<SkipResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/exchange/{rateName}/await/{awaitName}/skip";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null|| this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getConfig() != null) {
                JSONArray array = new JSONArray();
                for(Config item : this.request.getConfig())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("config", array);
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 交換待機を対価を払ってスキップ<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void skipAsync(
            SkipRequest request,
            AsyncAction<AsyncResult<SkipResult>> callback
    ) {
        SkipTask task = new SkipTask(request, callback, SkipResult.class);
        session.execute(task);
    }

    /**
     * 交換待機を対価を払ってスキップ<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<SkipByUserIdResult>> userCallback,
            Class<SkipByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/{rateName}/await/{awaitName}/skip";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null|| this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getConfig() != null) {
                JSONArray array = new JSONArray();
                for(Config item : this.request.getConfig())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("config", array);
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * 交換待機を対価を払ってスキップ<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void skipByUserIdAsync(
            SkipByUserIdRequest request,
            AsyncAction<AsyncResult<SkipByUserIdResult>> callback
    ) {
        SkipByUserIdTask task = new SkipByUserIdTask(request, callback, SkipByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 交換待機を対価を払ってスキップ<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DeleteAwaitResult>> userCallback,
            Class<DeleteAwaitResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/exchange/{rateName}/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null|| this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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

    /**
     * 交換待機を削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteAwaitAsync(
            DeleteAwaitRequest request,
            AsyncAction<AsyncResult<DeleteAwaitResult>> callback
    ) {
        DeleteAwaitTask task = new DeleteAwaitTask(request, callback, DeleteAwaitResult.class);
        session.execute(task);
    }

    /**
     * 交換待機を削除<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DeleteAwaitByUserIdResult>> userCallback,
            Class<DeleteAwaitByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/exchange/{rateName}/await/{awaitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{awaitName}", this.request.getAwaitName() == null|| this.request.getAwaitName().length() == 0 ? "null" : String.valueOf(this.request.getAwaitName()));

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

    /**
     * 交換待機を削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteAwaitByUserIdAsync(
            DeleteAwaitByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteAwaitByUserIdResult>> callback
    ) {
        DeleteAwaitByUserIdTask task = new DeleteAwaitByUserIdTask(request, callback, DeleteAwaitByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 交換待機を削除<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<CreateAwaitByStampSheetResult>> userCallback,
            Class<CreateAwaitByStampSheetResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/await/create";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getStampSheet() != null) {
                json.put("stampSheet", this.request.getStampSheet());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * スタンプシートで交換待機 を作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createAwaitByStampSheetAsync(
            CreateAwaitByStampSheetRequest request,
            AsyncAction<AsyncResult<CreateAwaitByStampSheetResult>> callback
    ) {
        CreateAwaitByStampSheetTask task = new CreateAwaitByStampSheetTask(request, callback, CreateAwaitByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートで交換待機 を作成<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DeleteAwaitByStampTaskResult>> userCallback,
            Class<DeleteAwaitByStampTaskResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "exchange")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/await/delete";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getStampTask() != null) {
                json.put("stampTask", this.request.getStampTask());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getContextStack() != null) {
                json.put("contextStack", this.request.getContextStack());
            }

            builder.setBody(json.toString().getBytes());

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

    /**
     * スタンプタスクで 交換待機 を削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteAwaitByStampTaskAsync(
            DeleteAwaitByStampTaskRequest request,
            AsyncAction<AsyncResult<DeleteAwaitByStampTaskResult>> callback
    ) {
        DeleteAwaitByStampTaskTask task = new DeleteAwaitByStampTaskTask(request, callback, DeleteAwaitByStampTaskResult.class);
        session.execute(task);
    }

    /**
     * スタンプタスクで 交換待機 を削除<br>
     *
     * @param request リクエストパラメータ
     */
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