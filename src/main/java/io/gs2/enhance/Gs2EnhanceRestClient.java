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

package io.gs2.enhance;

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
import io.gs2.enhance.request.*;
import io.gs2.enhance.result.*;
import io.gs2.enhance.model.*;

/**
 * GS2 Enhance API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2EnhanceRestClient extends AbstractGs2Client<Gs2EnhanceRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2EnhanceRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "enhance")
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
                .replace("{service}", "enhance")
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
            if (this.request.getEnableDirectEnhance() != null) {
                json.put("enableDirectEnhance", this.request.getEnableDirectEnhance());
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
                .replace("{service}", "enhance")
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
                .replace("{service}", "enhance")
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getEnableDirectEnhance() != null) {
                json.put("enableDirectEnhance", this.request.getEnableDirectEnhance());
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
                .replace("{service}", "enhance")
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
                .replace("{service}", "enhance")
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
     * 強化レートモデルの一覧を取得<br>
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
     * 強化レートモデルの一覧を取得<br>
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
                .replace("{service}", "enhance")
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
     * 強化レートモデルを取得<br>
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
     * 強化レートモデルを取得<br>
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
                .replace("{service}", "enhance")
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
     * 強化レートマスターの一覧を取得<br>
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
     * 強化レートマスターの一覧を取得<br>
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
                .replace("{service}", "enhance")
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
            if (this.request.getTargetInventoryModelId() != null) {
                json.put("targetInventoryModelId", this.request.getTargetInventoryModelId());
            }
            if (this.request.getAcquireExperienceSuffix() != null) {
                json.put("acquireExperienceSuffix", this.request.getAcquireExperienceSuffix());
            }
            if (this.request.getMaterialInventoryModelId() != null) {
                json.put("materialInventoryModelId", this.request.getMaterialInventoryModelId());
            }
            if (this.request.getAcquireExperienceHierarchy() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getAcquireExperienceHierarchy())
                {
                    array.put(item);
                }
                json.put("acquireExperienceHierarchy", array);
            }
            if (this.request.getExperienceModelId() != null) {
                json.put("experienceModelId", this.request.getExperienceModelId());
            }
            if (this.request.getBonusRates() != null) {
                JSONArray array = new JSONArray();
                for(BonusRate item : this.request.getBonusRates())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("bonusRates", array);
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
     * 強化レートマスターを新規作成<br>
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
     * 強化レートマスターを新規作成<br>
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
                .replace("{service}", "enhance")
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
     * 強化レートマスターを取得<br>
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
     * 強化レートマスターを取得<br>
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
                .replace("{service}", "enhance")
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
            if (this.request.getTargetInventoryModelId() != null) {
                json.put("targetInventoryModelId", this.request.getTargetInventoryModelId());
            }
            if (this.request.getAcquireExperienceSuffix() != null) {
                json.put("acquireExperienceSuffix", this.request.getAcquireExperienceSuffix());
            }
            if (this.request.getMaterialInventoryModelId() != null) {
                json.put("materialInventoryModelId", this.request.getMaterialInventoryModelId());
            }
            if (this.request.getAcquireExperienceHierarchy() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getAcquireExperienceHierarchy())
                {
                    array.put(item);
                }
                json.put("acquireExperienceHierarchy", array);
            }
            if (this.request.getExperienceModelId() != null) {
                json.put("experienceModelId", this.request.getExperienceModelId());
            }
            if (this.request.getBonusRates() != null) {
                JSONArray array = new JSONArray();
                for(BonusRate item : this.request.getBonusRates())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("bonusRates", array);
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
     * 強化レートマスターを更新<br>
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
     * 強化レートマスターを更新<br>
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
                .replace("{service}", "enhance")
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
     * 強化レートマスターを削除<br>
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
     * 強化レートマスターを削除<br>
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

    class DirectEnhanceTask extends Gs2RestSessionTask<DirectEnhanceResult> {
        private DirectEnhanceRequest request;

        public DirectEnhanceTask(
            DirectEnhanceRequest request,
            AsyncAction<AsyncResult<DirectEnhanceResult>> userCallback,
            Class<DirectEnhanceResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/enhance/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getTargetItemSetId() != null) {
                json.put("targetItemSetId", this.request.getTargetItemSetId());
            }
            if (this.request.getMaterials() != null) {
                JSONArray array = new JSONArray();
                for(Material item : this.request.getMaterials())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("materials", array);
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
     * 強化を実行<br>
     *   <br>
     *   このAPIは実行速度を最適化する代わりにセキュリティ的に課題を抱えています。<br>
     *   <br>
     *   スタンプシートの発行と同時に「成功」「大成功」を表現するためのボーナスレートも応答していますが、<br>
     *   それによって、スタンプシートを発行だけして実行しないことで、選別が可能となっています。<br>
     *   選別を出来ないようにするには、Progress にある Start / End APIを利用することで、<br>
     *   Start を呼び出したタイミングで強化素材を消費し、ボーナスレートを確定し<br>
     *   End を呼び出したタイミングで経験値を得るためのスタンプシートを発行するようにすることができます。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void directEnhanceAsync(
            DirectEnhanceRequest request,
            AsyncAction<AsyncResult<DirectEnhanceResult>> callback
    ) {
        DirectEnhanceTask task = new DirectEnhanceTask(request, callback, DirectEnhanceResult.class);
        session.execute(task);
    }

    /**
     * 強化を実行<br>
     *   <br>
     *   このAPIは実行速度を最適化する代わりにセキュリティ的に課題を抱えています。<br>
     *   <br>
     *   スタンプシートの発行と同時に「成功」「大成功」を表現するためのボーナスレートも応答していますが、<br>
     *   それによって、スタンプシートを発行だけして実行しないことで、選別が可能となっています。<br>
     *   選別を出来ないようにするには、Progress にある Start / End APIを利用することで、<br>
     *   Start を呼び出したタイミングで強化素材を消費し、ボーナスレートを確定し<br>
     *   End を呼び出したタイミングで経験値を得るためのスタンプシートを発行するようにすることができます。<br>
     *
     * @param request リクエストパラメータ
     */
    public DirectEnhanceResult directEnhance(
            DirectEnhanceRequest request
    ) {
        final AsyncResult<DirectEnhanceResult>[] resultAsyncResult = new AsyncResult[]{null};
        directEnhanceAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DirectEnhanceByUserIdTask extends Gs2RestSessionTask<DirectEnhanceByUserIdResult> {
        private DirectEnhanceByUserIdRequest request;

        public DirectEnhanceByUserIdTask(
            DirectEnhanceByUserIdRequest request,
            AsyncAction<AsyncResult<DirectEnhanceByUserIdResult>> userCallback,
            Class<DirectEnhanceByUserIdResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/enhance/{rateName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getTargetItemSetId() != null) {
                json.put("targetItemSetId", this.request.getTargetItemSetId());
            }
            if (this.request.getMaterials() != null) {
                JSONArray array = new JSONArray();
                for(Material item : this.request.getMaterials())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("materials", array);
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
     * ユーザIDを指定して強化を実行<br>
     *   <br>
     *   このAPIは実行速度を最適化する代わりにセキュリティ的に課題を抱えています。<br>
     *   <br>
     *   スタンプシートの発行と同時に「成功」「大成功」を表現するためのボーナスレートも応答していますが、<br>
     *   それによって、スタンプシートを発行だけして実行しないことで、選別が可能となっています。<br>
     *   選別を出来ないようにするには、Progress にある Start / End APIを利用することで、<br>
     *   Start を呼び出したタイミングで強化素材を消費し、ボーナスレートを確定し<br>
     *   End を呼び出したタイミングで経験値を得るためのスタンプシートを発行するようにすることができます。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void directEnhanceByUserIdAsync(
            DirectEnhanceByUserIdRequest request,
            AsyncAction<AsyncResult<DirectEnhanceByUserIdResult>> callback
    ) {
        DirectEnhanceByUserIdTask task = new DirectEnhanceByUserIdTask(request, callback, DirectEnhanceByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して強化を実行<br>
     *   <br>
     *   このAPIは実行速度を最適化する代わりにセキュリティ的に課題を抱えています。<br>
     *   <br>
     *   スタンプシートの発行と同時に「成功」「大成功」を表現するためのボーナスレートも応答していますが、<br>
     *   それによって、スタンプシートを発行だけして実行しないことで、選別が可能となっています。<br>
     *   選別を出来ないようにするには、Progress にある Start / End APIを利用することで、<br>
     *   Start を呼び出したタイミングで強化素材を消費し、ボーナスレートを確定し<br>
     *   End を呼び出したタイミングで経験値を得るためのスタンプシートを発行するようにすることができます。<br>
     *
     * @param request リクエストパラメータ
     */
    public DirectEnhanceByUserIdResult directEnhanceByUserId(
            DirectEnhanceByUserIdRequest request
    ) {
        final AsyncResult<DirectEnhanceByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        directEnhanceByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DirectEnhanceByStampSheetTask extends Gs2RestSessionTask<DirectEnhanceByStampSheetResult> {
        private DirectEnhanceByStampSheetRequest request;

        public DirectEnhanceByStampSheetTask(
            DirectEnhanceByStampSheetRequest request,
            AsyncAction<AsyncResult<DirectEnhanceByStampSheetResult>> userCallback,
            Class<DirectEnhanceByStampSheetResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/enhance/direct";

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
     * スタンプシートで強化を実行<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void directEnhanceByStampSheetAsync(
            DirectEnhanceByStampSheetRequest request,
            AsyncAction<AsyncResult<DirectEnhanceByStampSheetResult>> callback
    ) {
        DirectEnhanceByStampSheetTask task = new DirectEnhanceByStampSheetTask(request, callback, DirectEnhanceByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートで強化を実行<br>
     *
     * @param request リクエストパラメータ
     */
    public DirectEnhanceByStampSheetResult directEnhanceByStampSheet(
            DirectEnhanceByStampSheetRequest request
    ) {
        final AsyncResult<DirectEnhanceByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        directEnhanceByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeProgressesByUserIdTask extends Gs2RestSessionTask<DescribeProgressesByUserIdResult> {
        private DescribeProgressesByUserIdRequest request;

        public DescribeProgressesByUserIdTask(
            DescribeProgressesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeProgressesByUserIdResult>> userCallback,
            Class<DescribeProgressesByUserIdResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/progress";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getUserId() != null) {
                queryStrings.add("userId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getUserId()))));
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
     * 強化実行の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeProgressesByUserIdAsync(
            DescribeProgressesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeProgressesByUserIdResult>> callback
    ) {
        DescribeProgressesByUserIdTask task = new DescribeProgressesByUserIdTask(request, callback, DescribeProgressesByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 強化実行の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeProgressesByUserIdResult describeProgressesByUserId(
            DescribeProgressesByUserIdRequest request
    ) {
        final AsyncResult<DescribeProgressesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeProgressesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateProgressByUserIdTask extends Gs2RestSessionTask<CreateProgressByUserIdResult> {
        private CreateProgressByUserIdRequest request;

        public CreateProgressByUserIdTask(
            CreateProgressByUserIdRequest request,
            AsyncAction<AsyncResult<CreateProgressByUserIdResult>> userCallback,
            Class<CreateProgressByUserIdResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/progress";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getRateName() != null) {
                json.put("rateName", this.request.getRateName());
            }
            if (this.request.getTargetItemSetId() != null) {
                json.put("targetItemSetId", this.request.getTargetItemSetId());
            }
            if (this.request.getMaterials() != null) {
                JSONArray array = new JSONArray();
                for(Material item : this.request.getMaterials())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("materials", array);
            }
            if (this.request.getForce() != null) {
                json.put("force", this.request.getForce());
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
     * ユーザIDを指定して強化実行を作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createProgressByUserIdAsync(
            CreateProgressByUserIdRequest request,
            AsyncAction<AsyncResult<CreateProgressByUserIdResult>> callback
    ) {
        CreateProgressByUserIdTask task = new CreateProgressByUserIdTask(request, callback, CreateProgressByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して強化実行を作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateProgressByUserIdResult createProgressByUserId(
            CreateProgressByUserIdRequest request
    ) {
        final AsyncResult<CreateProgressByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        createProgressByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetProgressTask extends Gs2RestSessionTask<GetProgressResult> {
        private GetProgressRequest request;

        public GetProgressTask(
            GetProgressRequest request,
            AsyncAction<AsyncResult<GetProgressResult>> userCallback,
            Class<GetProgressResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/progress";

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
     * 強化実行を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getProgressAsync(
            GetProgressRequest request,
            AsyncAction<AsyncResult<GetProgressResult>> callback
    ) {
        GetProgressTask task = new GetProgressTask(request, callback, GetProgressResult.class);
        session.execute(task);
    }

    /**
     * 強化実行を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetProgressResult getProgress(
            GetProgressRequest request
    ) {
        final AsyncResult<GetProgressResult>[] resultAsyncResult = new AsyncResult[]{null};
        getProgressAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetProgressByUserIdTask extends Gs2RestSessionTask<GetProgressByUserIdResult> {
        private GetProgressByUserIdRequest request;

        public GetProgressByUserIdTask(
            GetProgressByUserIdRequest request,
            AsyncAction<AsyncResult<GetProgressByUserIdResult>> userCallback,
            Class<GetProgressByUserIdResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/progress";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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
     * ユーザIDを指定して強化実行を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getProgressByUserIdAsync(
            GetProgressByUserIdRequest request,
            AsyncAction<AsyncResult<GetProgressByUserIdResult>> callback
    ) {
        GetProgressByUserIdTask task = new GetProgressByUserIdTask(request, callback, GetProgressByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して強化実行を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetProgressByUserIdResult getProgressByUserId(
            GetProgressByUserIdRequest request
    ) {
        final AsyncResult<GetProgressByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getProgressByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class StartTask extends Gs2RestSessionTask<StartResult> {
        private StartRequest request;

        public StartTask(
            StartRequest request,
            AsyncAction<AsyncResult<StartResult>> userCallback,
            Class<StartResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/progress/rate/{rateName}/start";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getTargetItemSetId() != null) {
                json.put("targetItemSetId", this.request.getTargetItemSetId());
            }
            if (this.request.getMaterials() != null) {
                JSONArray array = new JSONArray();
                for(Material item : this.request.getMaterials())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("materials", array);
            }
            if (this.request.getForce() != null) {
                json.put("force", this.request.getForce());
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
     * 強化を開始<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void startAsync(
            StartRequest request,
            AsyncAction<AsyncResult<StartResult>> callback
    ) {
        StartTask task = new StartTask(request, callback, StartResult.class);
        session.execute(task);
    }

    /**
     * 強化を開始<br>
     *
     * @param request リクエストパラメータ
     */
    public StartResult start(
            StartRequest request
    ) {
        final AsyncResult<StartResult>[] resultAsyncResult = new AsyncResult[]{null};
        startAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class StartByUserIdTask extends Gs2RestSessionTask<StartByUserIdResult> {
        private StartByUserIdRequest request;

        public StartByUserIdTask(
            StartByUserIdRequest request,
            AsyncAction<AsyncResult<StartByUserIdResult>> userCallback,
            Class<StartByUserIdResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/progress/rate/{rateName}/start";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{rateName}", this.request.getRateName() == null|| this.request.getRateName().length() == 0 ? "null" : String.valueOf(this.request.getRateName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getTargetItemSetId() != null) {
                json.put("targetItemSetId", this.request.getTargetItemSetId());
            }
            if (this.request.getMaterials() != null) {
                JSONArray array = new JSONArray();
                for(Material item : this.request.getMaterials())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("materials", array);
            }
            if (this.request.getForce() != null) {
                json.put("force", this.request.getForce());
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
     * ユーザIDを指定して強化を開始<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void startByUserIdAsync(
            StartByUserIdRequest request,
            AsyncAction<AsyncResult<StartByUserIdResult>> callback
    ) {
        StartByUserIdTask task = new StartByUserIdTask(request, callback, StartByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して強化を開始<br>
     *
     * @param request リクエストパラメータ
     */
    public StartByUserIdResult startByUserId(
            StartByUserIdRequest request
    ) {
        final AsyncResult<StartByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        startByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class EndTask extends Gs2RestSessionTask<EndResult> {
        private EndRequest request;

        public EndTask(
            EndRequest request,
            AsyncAction<AsyncResult<EndResult>> userCallback,
            Class<EndResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/progress/end";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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
     * 強化を完了<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void endAsync(
            EndRequest request,
            AsyncAction<AsyncResult<EndResult>> callback
    ) {
        EndTask task = new EndTask(request, callback, EndResult.class);
        session.execute(task);
    }

    /**
     * 強化を完了<br>
     *
     * @param request リクエストパラメータ
     */
    public EndResult end(
            EndRequest request
    ) {
        final AsyncResult<EndResult>[] resultAsyncResult = new AsyncResult[]{null};
        endAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class EndByUserIdTask extends Gs2RestSessionTask<EndByUserIdResult> {
        private EndByUserIdRequest request;

        public EndByUserIdTask(
            EndByUserIdRequest request,
            AsyncAction<AsyncResult<EndByUserIdResult>> userCallback,
            Class<EndByUserIdResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/progress/end";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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
     * ユーザIDを指定して強化を完了<br>
     *   <br>
     *   開始時に受け取った強化において入手可能な報酬とその数量の"最大値"のうち、強化内で実際に入手した報酬を rewards で報告します。<br>
     *   isComplete には強化をクリアできたかを報告します。強化に失敗した場合、rewards の値は無視して強化に設定された失敗した場合の報酬が付与されます。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void endByUserIdAsync(
            EndByUserIdRequest request,
            AsyncAction<AsyncResult<EndByUserIdResult>> callback
    ) {
        EndByUserIdTask task = new EndByUserIdTask(request, callback, EndByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して強化を完了<br>
     *   <br>
     *   開始時に受け取った強化において入手可能な報酬とその数量の"最大値"のうち、強化内で実際に入手した報酬を rewards で報告します。<br>
     *   isComplete には強化をクリアできたかを報告します。強化に失敗した場合、rewards の値は無視して強化に設定された失敗した場合の報酬が付与されます。<br>
     *
     * @param request リクエストパラメータ
     */
    public EndByUserIdResult endByUserId(
            EndByUserIdRequest request
    ) {
        final AsyncResult<EndByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        endByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteProgressTask extends Gs2RestSessionTask<DeleteProgressResult> {
        private DeleteProgressRequest request;

        public DeleteProgressTask(
            DeleteProgressRequest request,
            AsyncAction<AsyncResult<DeleteProgressResult>> userCallback,
            Class<DeleteProgressResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/progress";

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
     * 強化実行を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteProgressAsync(
            DeleteProgressRequest request,
            AsyncAction<AsyncResult<DeleteProgressResult>> callback
    ) {
        DeleteProgressTask task = new DeleteProgressTask(request, callback, DeleteProgressResult.class);
        session.execute(task);
    }

    /**
     * 強化実行を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteProgressResult deleteProgress(
            DeleteProgressRequest request
    ) {
        final AsyncResult<DeleteProgressResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteProgressAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteProgressByUserIdTask extends Gs2RestSessionTask<DeleteProgressByUserIdResult> {
        private DeleteProgressByUserIdRequest request;

        public DeleteProgressByUserIdTask(
            DeleteProgressByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteProgressByUserIdResult>> userCallback,
            Class<DeleteProgressByUserIdResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/progress";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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
     * ユーザIDを指定して強化実行を削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteProgressByUserIdAsync(
            DeleteProgressByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteProgressByUserIdResult>> callback
    ) {
        DeleteProgressByUserIdTask task = new DeleteProgressByUserIdTask(request, callback, DeleteProgressByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して強化実行を削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteProgressByUserIdResult deleteProgressByUserId(
            DeleteProgressByUserIdRequest request
    ) {
        final AsyncResult<DeleteProgressByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteProgressByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateProgressByStampSheetTask extends Gs2RestSessionTask<CreateProgressByStampSheetResult> {
        private CreateProgressByStampSheetRequest request;

        public CreateProgressByStampSheetTask(
            CreateProgressByStampSheetRequest request,
            AsyncAction<AsyncResult<CreateProgressByStampSheetResult>> userCallback,
            Class<CreateProgressByStampSheetResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/progress/create";

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
     * スタンプシートで強化を開始<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createProgressByStampSheetAsync(
            CreateProgressByStampSheetRequest request,
            AsyncAction<AsyncResult<CreateProgressByStampSheetResult>> callback
    ) {
        CreateProgressByStampSheetTask task = new CreateProgressByStampSheetTask(request, callback, CreateProgressByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートで強化を開始<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateProgressByStampSheetResult createProgressByStampSheet(
            CreateProgressByStampSheetRequest request
    ) {
        final AsyncResult<CreateProgressByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        createProgressByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteProgressByStampTaskTask extends Gs2RestSessionTask<DeleteProgressByStampTaskResult> {
        private DeleteProgressByStampTaskRequest request;

        public DeleteProgressByStampTaskTask(
            DeleteProgressByStampTaskRequest request,
            AsyncAction<AsyncResult<DeleteProgressByStampTaskResult>> userCallback,
            Class<DeleteProgressByStampTaskResult> clazz
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
                .replace("{service}", "enhance")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/progress/delete";

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
     * スタンプタスクで 強化実行 を削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteProgressByStampTaskAsync(
            DeleteProgressByStampTaskRequest request,
            AsyncAction<AsyncResult<DeleteProgressByStampTaskResult>> callback
    ) {
        DeleteProgressByStampTaskTask task = new DeleteProgressByStampTaskTask(request, callback, DeleteProgressByStampTaskResult.class);
        session.execute(task);
    }

    /**
     * スタンプタスクで 強化実行 を削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteProgressByStampTaskResult deleteProgressByStampTask(
            DeleteProgressByStampTaskRequest request
    ) {
        final AsyncResult<DeleteProgressByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteProgressByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "enhance")
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
     * 現在有効な強化レート設定のマスターデータをエクスポートします<br>
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
     * 現在有効な強化レート設定のマスターデータをエクスポートします<br>
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
                .replace("{service}", "enhance")
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
     * 現在有効な強化レート設定を取得します<br>
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
     * 現在有効な強化レート設定を取得します<br>
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
                .replace("{service}", "enhance")
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
     * 現在有効な強化レート設定を更新します<br>
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
     * 現在有効な強化レート設定を更新します<br>
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
                .replace("{service}", "enhance")
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
     * 現在有効な強化レート設定を更新します<br>
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
     * 現在有効な強化レート設定を更新します<br>
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
}