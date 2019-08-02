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
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.json.JSONArray;

import io.gs2.model.AsyncAction;
import io.gs2.model.AsyncResult;
import io.gs2.exception.*;
import io.gs2.net.*;
import io.gs2.util.EncodingUtil;

import io.gs2.AbstractGs2Client;
import io.gs2.showcase.request.*;
import io.gs2.showcase.result.*;
import io.gs2.showcase.model.*;

/**
 * GS2 Showcase API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2ShowcaseRestClient extends AbstractGs2Client<Gs2ShowcaseRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2ShowcaseRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "showcase")
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
            if (this.request.getQueueNamespaceId() != null) {
                json.put("queueNamespaceId", this.request.getQueueNamespaceId());
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
                .replace("{service}", "showcase")
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
     * ネームスペースの状態を取得<br>
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
     * ネームスペースの状態を取得<br>
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
                .replace("{service}", "showcase")
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getQueueNamespaceId() != null) {
                json.put("queueNamespaceId", this.request.getQueueNamespaceId());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
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
                .replace("{service}", "showcase")
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

    class DescribeSalesItemMastersTask extends Gs2RestSessionTask<DescribeSalesItemMastersResult> {
        private DescribeSalesItemMastersRequest request;

        public DescribeSalesItemMastersTask(
            DescribeSalesItemMastersRequest request,
            AsyncAction<AsyncResult<DescribeSalesItemMastersResult>> userCallback,
            Class<DescribeSalesItemMastersResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/salesItem";

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
     * 商品マスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeSalesItemMastersAsync(
            DescribeSalesItemMastersRequest request,
            AsyncAction<AsyncResult<DescribeSalesItemMastersResult>> callback
    ) {
        DescribeSalesItemMastersTask task = new DescribeSalesItemMastersTask(request, callback, DescribeSalesItemMastersResult.class);
        session.execute(task);
    }

    /**
     * 商品マスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<CreateSalesItemMasterResult>> userCallback,
            Class<CreateSalesItemMasterResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/salesItem";

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
     * 商品マスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createSalesItemMasterAsync(
            CreateSalesItemMasterRequest request,
            AsyncAction<AsyncResult<CreateSalesItemMasterResult>> callback
    ) {
        CreateSalesItemMasterTask task = new CreateSalesItemMasterTask(request, callback, CreateSalesItemMasterResult.class);
        session.execute(task);
    }

    /**
     * 商品マスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetSalesItemMasterResult>> userCallback,
            Class<GetSalesItemMasterResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/salesItem/{salesItemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{salesItemName}", this.request.getSalesItemName() == null|| this.request.getSalesItemName().length() == 0 ? "null" : String.valueOf(this.request.getSalesItemName()));

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
     * 商品マスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getSalesItemMasterAsync(
            GetSalesItemMasterRequest request,
            AsyncAction<AsyncResult<GetSalesItemMasterResult>> callback
    ) {
        GetSalesItemMasterTask task = new GetSalesItemMasterTask(request, callback, GetSalesItemMasterResult.class);
        session.execute(task);
    }

    /**
     * 商品マスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<UpdateSalesItemMasterResult>> userCallback,
            Class<UpdateSalesItemMasterResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/salesItem/{salesItemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{salesItemName}", this.request.getSalesItemName() == null|| this.request.getSalesItemName().length() == 0 ? "null" : String.valueOf(this.request.getSalesItemName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
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
     * 商品マスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateSalesItemMasterAsync(
            UpdateSalesItemMasterRequest request,
            AsyncAction<AsyncResult<UpdateSalesItemMasterResult>> callback
    ) {
        UpdateSalesItemMasterTask task = new UpdateSalesItemMasterTask(request, callback, UpdateSalesItemMasterResult.class);
        session.execute(task);
    }

    /**
     * 商品マスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DeleteSalesItemMasterResult>> userCallback,
            Class<DeleteSalesItemMasterResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/salesItem/{salesItemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{salesItemName}", this.request.getSalesItemName() == null|| this.request.getSalesItemName().length() == 0 ? "null" : String.valueOf(this.request.getSalesItemName()));

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
     * 商品マスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteSalesItemMasterAsync(
            DeleteSalesItemMasterRequest request,
            AsyncAction<AsyncResult<DeleteSalesItemMasterResult>> callback
    ) {
        DeleteSalesItemMasterTask task = new DeleteSalesItemMasterTask(request, callback, DeleteSalesItemMasterResult.class);
        session.execute(task);
    }

    /**
     * 商品マスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DescribeSalesItemGroupMastersResult>> userCallback,
            Class<DescribeSalesItemGroupMastersResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group";

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
     * 商品グループマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeSalesItemGroupMastersAsync(
            DescribeSalesItemGroupMastersRequest request,
            AsyncAction<AsyncResult<DescribeSalesItemGroupMastersResult>> callback
    ) {
        DescribeSalesItemGroupMastersTask task = new DescribeSalesItemGroupMastersTask(request, callback, DescribeSalesItemGroupMastersResult.class);
        session.execute(task);
    }

    /**
     * 商品グループマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<CreateSalesItemGroupMasterResult>> userCallback,
            Class<CreateSalesItemGroupMasterResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group";

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
            if (this.request.getSalesItemNames() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getSalesItemNames())
                {
                    array.put(item);
                }
                json.put("salesItemNames", array);
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
     * 商品グループマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createSalesItemGroupMasterAsync(
            CreateSalesItemGroupMasterRequest request,
            AsyncAction<AsyncResult<CreateSalesItemGroupMasterResult>> callback
    ) {
        CreateSalesItemGroupMasterTask task = new CreateSalesItemGroupMasterTask(request, callback, CreateSalesItemGroupMasterResult.class);
        session.execute(task);
    }

    /**
     * 商品グループマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetSalesItemGroupMasterResult>> userCallback,
            Class<GetSalesItemGroupMasterResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{salesItemGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{salesItemGroupName}", this.request.getSalesItemGroupName() == null|| this.request.getSalesItemGroupName().length() == 0 ? "null" : String.valueOf(this.request.getSalesItemGroupName()));

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
     * 商品グループマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getSalesItemGroupMasterAsync(
            GetSalesItemGroupMasterRequest request,
            AsyncAction<AsyncResult<GetSalesItemGroupMasterResult>> callback
    ) {
        GetSalesItemGroupMasterTask task = new GetSalesItemGroupMasterTask(request, callback, GetSalesItemGroupMasterResult.class);
        session.execute(task);
    }

    /**
     * 商品グループマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<UpdateSalesItemGroupMasterResult>> userCallback,
            Class<UpdateSalesItemGroupMasterResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{salesItemGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{salesItemGroupName}", this.request.getSalesItemGroupName() == null|| this.request.getSalesItemGroupName().length() == 0 ? "null" : String.valueOf(this.request.getSalesItemGroupName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getSalesItemNames() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getSalesItemNames())
                {
                    array.put(item);
                }
                json.put("salesItemNames", array);
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
     * 商品グループマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateSalesItemGroupMasterAsync(
            UpdateSalesItemGroupMasterRequest request,
            AsyncAction<AsyncResult<UpdateSalesItemGroupMasterResult>> callback
    ) {
        UpdateSalesItemGroupMasterTask task = new UpdateSalesItemGroupMasterTask(request, callback, UpdateSalesItemGroupMasterResult.class);
        session.execute(task);
    }

    /**
     * 商品グループマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DeleteSalesItemGroupMasterResult>> userCallback,
            Class<DeleteSalesItemGroupMasterResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/group/{salesItemGroupName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{salesItemGroupName}", this.request.getSalesItemGroupName() == null|| this.request.getSalesItemGroupName().length() == 0 ? "null" : String.valueOf(this.request.getSalesItemGroupName()));

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
     * 商品グループマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteSalesItemGroupMasterAsync(
            DeleteSalesItemGroupMasterRequest request,
            AsyncAction<AsyncResult<DeleteSalesItemGroupMasterResult>> callback
    ) {
        DeleteSalesItemGroupMasterTask task = new DeleteSalesItemGroupMasterTask(request, callback, DeleteSalesItemGroupMasterResult.class);
        session.execute(task);
    }

    /**
     * 商品グループマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DescribeShowcaseMastersResult>> userCallback,
            Class<DescribeShowcaseMastersResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/showcase";

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
     * 陳列棚マスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeShowcaseMastersAsync(
            DescribeShowcaseMastersRequest request,
            AsyncAction<AsyncResult<DescribeShowcaseMastersResult>> callback
    ) {
        DescribeShowcaseMastersTask task = new DescribeShowcaseMastersTask(request, callback, DescribeShowcaseMastersResult.class);
        session.execute(task);
    }

    /**
     * 陳列棚マスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<CreateShowcaseMasterResult>> userCallback,
            Class<CreateShowcaseMasterResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/showcase";

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
            if (this.request.getDisplayItems() != null) {
                JSONArray array = new JSONArray();
                for(DisplayItemMaster item : this.request.getDisplayItems())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("displayItems", array);
            }
            if (this.request.getSalesPeriodEventId() != null) {
                json.put("salesPeriodEventId", this.request.getSalesPeriodEventId());
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
     * 陳列棚マスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createShowcaseMasterAsync(
            CreateShowcaseMasterRequest request,
            AsyncAction<AsyncResult<CreateShowcaseMasterResult>> callback
    ) {
        CreateShowcaseMasterTask task = new CreateShowcaseMasterTask(request, callback, CreateShowcaseMasterResult.class);
        session.execute(task);
    }

    /**
     * 陳列棚マスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetShowcaseMasterResult>> userCallback,
            Class<GetShowcaseMasterResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null|| this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));

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
     * 陳列棚マスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getShowcaseMasterAsync(
            GetShowcaseMasterRequest request,
            AsyncAction<AsyncResult<GetShowcaseMasterResult>> callback
    ) {
        GetShowcaseMasterTask task = new GetShowcaseMasterTask(request, callback, GetShowcaseMasterResult.class);
        session.execute(task);
    }

    /**
     * 陳列棚マスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<UpdateShowcaseMasterResult>> userCallback,
            Class<UpdateShowcaseMasterResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null|| this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getDisplayItems() != null) {
                JSONArray array = new JSONArray();
                for(DisplayItemMaster item : this.request.getDisplayItems())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("displayItems", array);
            }
            if (this.request.getSalesPeriodEventId() != null) {
                json.put("salesPeriodEventId", this.request.getSalesPeriodEventId());
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
     * 陳列棚マスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateShowcaseMasterAsync(
            UpdateShowcaseMasterRequest request,
            AsyncAction<AsyncResult<UpdateShowcaseMasterResult>> callback
    ) {
        UpdateShowcaseMasterTask task = new UpdateShowcaseMasterTask(request, callback, UpdateShowcaseMasterResult.class);
        session.execute(task);
    }

    /**
     * 陳列棚マスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DeleteShowcaseMasterResult>> userCallback,
            Class<DeleteShowcaseMasterResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null|| this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));

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
     * 陳列棚マスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteShowcaseMasterAsync(
            DeleteShowcaseMasterRequest request,
            AsyncAction<AsyncResult<DeleteShowcaseMasterResult>> callback
    ) {
        DeleteShowcaseMasterTask task = new DeleteShowcaseMasterTask(request, callback, DeleteShowcaseMasterResult.class);
        session.execute(task);
    }

    /**
     * 陳列棚マスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
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
                .replace("{service}", "showcase")
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
     * 現在有効な陳列棚マスターのマスターデータをエクスポートします<br>
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
     * 現在有効な陳列棚マスターのマスターデータをエクスポートします<br>
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

    class GetCurrentShowcaseMasterTask extends Gs2RestSessionTask<GetCurrentShowcaseMasterResult> {
        private GetCurrentShowcaseMasterRequest request;

        public GetCurrentShowcaseMasterTask(
            GetCurrentShowcaseMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentShowcaseMasterResult>> userCallback,
            Class<GetCurrentShowcaseMasterResult> clazz
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
                .replace("{service}", "showcase")
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
     * 現在有効な現在有効な陳列棚マスターを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentShowcaseMasterAsync(
            GetCurrentShowcaseMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentShowcaseMasterResult>> callback
    ) {
        GetCurrentShowcaseMasterTask task = new GetCurrentShowcaseMasterTask(request, callback, GetCurrentShowcaseMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な現在有効な陳列棚マスターを取得します<br>
     *
     * @param request リクエストパラメータ
     */
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

    class UpdateCurrentShowcaseMasterTask extends Gs2RestSessionTask<UpdateCurrentShowcaseMasterResult> {
        private UpdateCurrentShowcaseMasterRequest request;

        public UpdateCurrentShowcaseMasterTask(
            UpdateCurrentShowcaseMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentShowcaseMasterResult>> userCallback,
            Class<UpdateCurrentShowcaseMasterResult> clazz
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
                .replace("{service}", "showcase")
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
     * 現在有効な現在有効な陳列棚マスターを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentShowcaseMasterAsync(
            UpdateCurrentShowcaseMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentShowcaseMasterResult>> callback
    ) {
        UpdateCurrentShowcaseMasterTask task = new UpdateCurrentShowcaseMasterTask(request, callback, UpdateCurrentShowcaseMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な現在有効な陳列棚マスターを更新します<br>
     *
     * @param request リクエストパラメータ
     */
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

    class DescribeShowcasesTask extends Gs2RestSessionTask<DescribeShowcasesResult> {
        private DescribeShowcasesRequest request;

        public DescribeShowcasesTask(
            DescribeShowcasesRequest request,
            AsyncAction<AsyncResult<DescribeShowcasesResult>> userCallback,
            Class<DescribeShowcasesResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/showcase";

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
     * 陳列棚の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeShowcasesAsync(
            DescribeShowcasesRequest request,
            AsyncAction<AsyncResult<DescribeShowcasesResult>> callback
    ) {
        DescribeShowcasesTask task = new DescribeShowcasesTask(request, callback, DescribeShowcasesResult.class);
        session.execute(task);
    }

    /**
     * 陳列棚の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<DescribeShowcasesByUserIdResult>> userCallback,
            Class<DescribeShowcasesByUserIdResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/showcase";

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
     * ユーザIDを指定して陳列棚の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeShowcasesByUserIdAsync(
            DescribeShowcasesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeShowcasesByUserIdResult>> callback
    ) {
        DescribeShowcasesByUserIdTask task = new DescribeShowcasesByUserIdTask(request, callback, DescribeShowcasesByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して陳列棚の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetShowcaseResult>> userCallback,
            Class<GetShowcaseResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null|| this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));

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
     * 陳列棚を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getShowcaseAsync(
            GetShowcaseRequest request,
            AsyncAction<AsyncResult<GetShowcaseResult>> callback
    ) {
        GetShowcaseTask task = new GetShowcaseTask(request, callback, GetShowcaseResult.class);
        session.execute(task);
    }

    /**
     * 陳列棚を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<GetShowcaseByUserIdResult>> userCallback,
            Class<GetShowcaseByUserIdResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null|| this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
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
     * ユーザIDを指定して陳列棚を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getShowcaseByUserIdAsync(
            GetShowcaseByUserIdRequest request,
            AsyncAction<AsyncResult<GetShowcaseByUserIdResult>> callback
    ) {
        GetShowcaseByUserIdTask task = new GetShowcaseByUserIdTask(request, callback, GetShowcaseByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して陳列棚を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<BuyResult>> userCallback,
            Class<BuyResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null|| this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
            url = url.replace("{displayItemId}", this.request.getDisplayItemId() == null|| this.request.getDisplayItemId().length() == 0 ? "null" : String.valueOf(this.request.getDisplayItemId()));

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
     * 陳列棚を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void buyAsync(
            BuyRequest request,
            AsyncAction<AsyncResult<BuyResult>> callback
    ) {
        BuyTask task = new BuyTask(request, callback, BuyResult.class);
        session.execute(task);
    }

    /**
     * 陳列棚を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
            AsyncAction<AsyncResult<BuyByUserIdResult>> userCallback,
            Class<BuyByUserIdResult> clazz
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
                .replace("{service}", "showcase")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/showcase/{showcaseName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{showcaseName}", this.request.getShowcaseName() == null|| this.request.getShowcaseName().length() == 0 ? "null" : String.valueOf(this.request.getShowcaseName()));
            url = url.replace("{displayItemId}", this.request.getDisplayItemId() == null|| this.request.getDisplayItemId().length() == 0 ? "null" : String.valueOf(this.request.getDisplayItemId()));
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
     * ユーザIDを指定して陳列棚を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void buyByUserIdAsync(
            BuyByUserIdRequest request,
            AsyncAction<AsyncResult<BuyByUserIdResult>> callback
    ) {
        BuyByUserIdTask task = new BuyByUserIdTask(request, callback, BuyByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して陳列棚を取得<br>
     *
     * @param request リクエストパラメータ
     */
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
}