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

package io.gs2.inventory;

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
import io.gs2.inventory.request.*;
import io.gs2.inventory.result.*;
import io.gs2.inventory.model.*;

/**
 * GS2 Inventory API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2InventoryRestClient extends AbstractGs2Client<Gs2InventoryRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2InventoryRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "inventory")
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
                .replace("{service}", "inventory")
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
            if (this.request.getAcquireTriggerScriptId() != null) {
                json.put("acquireTriggerScriptId", this.request.getAcquireTriggerScriptId());
            }
            if (this.request.getAcquireDoneTriggerScriptId() != null) {
                json.put("acquireDoneTriggerScriptId", this.request.getAcquireDoneTriggerScriptId());
            }
            if (this.request.getAcquireDoneTriggerQueueNamespaceId() != null) {
                json.put("acquireDoneTriggerQueueNamespaceId", this.request.getAcquireDoneTriggerQueueNamespaceId());
            }
            if (this.request.getOverflowTriggerScriptId() != null) {
                json.put("overflowTriggerScriptId", this.request.getOverflowTriggerScriptId());
            }
            if (this.request.getOverflowTriggerQueueNamespaceId() != null) {
                json.put("overflowTriggerQueueNamespaceId", this.request.getOverflowTriggerQueueNamespaceId());
            }
            if (this.request.getConsumeTriggerScriptId() != null) {
                json.put("consumeTriggerScriptId", this.request.getConsumeTriggerScriptId());
            }
            if (this.request.getConsumeDoneTriggerScriptId() != null) {
                json.put("consumeDoneTriggerScriptId", this.request.getConsumeDoneTriggerScriptId());
            }
            if (this.request.getConsumeDoneTriggerQueueNamespaceId() != null) {
                json.put("consumeDoneTriggerQueueNamespaceId", this.request.getConsumeDoneTriggerQueueNamespaceId());
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
                .replace("{service}", "inventory")
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
                .replace("{service}", "inventory")
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getAcquireTriggerScriptId() != null) {
                json.put("acquireTriggerScriptId", this.request.getAcquireTriggerScriptId());
            }
            if (this.request.getAcquireDoneTriggerScriptId() != null) {
                json.put("acquireDoneTriggerScriptId", this.request.getAcquireDoneTriggerScriptId());
            }
            if (this.request.getAcquireDoneTriggerQueueNamespaceId() != null) {
                json.put("acquireDoneTriggerQueueNamespaceId", this.request.getAcquireDoneTriggerQueueNamespaceId());
            }
            if (this.request.getOverflowTriggerScriptId() != null) {
                json.put("overflowTriggerScriptId", this.request.getOverflowTriggerScriptId());
            }
            if (this.request.getOverflowTriggerQueueNamespaceId() != null) {
                json.put("overflowTriggerQueueNamespaceId", this.request.getOverflowTriggerQueueNamespaceId());
            }
            if (this.request.getConsumeTriggerScriptId() != null) {
                json.put("consumeTriggerScriptId", this.request.getConsumeTriggerScriptId());
            }
            if (this.request.getConsumeDoneTriggerScriptId() != null) {
                json.put("consumeDoneTriggerScriptId", this.request.getConsumeDoneTriggerScriptId());
            }
            if (this.request.getConsumeDoneTriggerQueueNamespaceId() != null) {
                json.put("consumeDoneTriggerQueueNamespaceId", this.request.getConsumeDoneTriggerQueueNamespaceId());
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
                .replace("{service}", "inventory")
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

    class DescribeInventoryModelMastersTask extends Gs2RestSessionTask<DescribeInventoryModelMastersResult> {
        private DescribeInventoryModelMastersRequest request;

        public DescribeInventoryModelMastersTask(
            DescribeInventoryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeInventoryModelMastersResult>> userCallback,
            Class<DescribeInventoryModelMastersResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory";

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
     * インベントリモデルマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeInventoryModelMastersAsync(
            DescribeInventoryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeInventoryModelMastersResult>> callback
    ) {
        DescribeInventoryModelMastersTask task = new DescribeInventoryModelMastersTask(request, callback, DescribeInventoryModelMastersResult.class);
        session.execute(task);
    }

    /**
     * インベントリモデルマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeInventoryModelMastersResult describeInventoryModelMasters(
            DescribeInventoryModelMastersRequest request
    ) {
        final AsyncResult<DescribeInventoryModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeInventoryModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateInventoryModelMasterTask extends Gs2RestSessionTask<CreateInventoryModelMasterResult> {
        private CreateInventoryModelMasterRequest request;

        public CreateInventoryModelMasterTask(
            CreateInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateInventoryModelMasterResult>> userCallback,
            Class<CreateInventoryModelMasterResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory";

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
            if (this.request.getInitialCapacity() != null) {
                json.put("initialCapacity", this.request.getInitialCapacity());
            }
            if (this.request.getMaxCapacity() != null) {
                json.put("maxCapacity", this.request.getMaxCapacity());
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
     * インベントリモデルマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createInventoryModelMasterAsync(
            CreateInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateInventoryModelMasterResult>> callback
    ) {
        CreateInventoryModelMasterTask task = new CreateInventoryModelMasterTask(request, callback, CreateInventoryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * インベントリモデルマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateInventoryModelMasterResult createInventoryModelMaster(
            CreateInventoryModelMasterRequest request
    ) {
        final AsyncResult<CreateInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createInventoryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetInventoryModelMasterTask extends Gs2RestSessionTask<GetInventoryModelMasterResult> {
        private GetInventoryModelMasterRequest request;

        public GetInventoryModelMasterTask(
            GetInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<GetInventoryModelMasterResult>> userCallback,
            Class<GetInventoryModelMasterResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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
     * インベントリモデルマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getInventoryModelMasterAsync(
            GetInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<GetInventoryModelMasterResult>> callback
    ) {
        GetInventoryModelMasterTask task = new GetInventoryModelMasterTask(request, callback, GetInventoryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * インベントリモデルマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetInventoryModelMasterResult getInventoryModelMaster(
            GetInventoryModelMasterRequest request
    ) {
        final AsyncResult<GetInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getInventoryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateInventoryModelMasterTask extends Gs2RestSessionTask<UpdateInventoryModelMasterResult> {
        private UpdateInventoryModelMasterRequest request;

        public UpdateInventoryModelMasterTask(
            UpdateInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateInventoryModelMasterResult>> userCallback,
            Class<UpdateInventoryModelMasterResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getInitialCapacity() != null) {
                json.put("initialCapacity", this.request.getInitialCapacity());
            }
            if (this.request.getMaxCapacity() != null) {
                json.put("maxCapacity", this.request.getMaxCapacity());
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
     * インベントリモデルマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateInventoryModelMasterAsync(
            UpdateInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateInventoryModelMasterResult>> callback
    ) {
        UpdateInventoryModelMasterTask task = new UpdateInventoryModelMasterTask(request, callback, UpdateInventoryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * インベントリモデルマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateInventoryModelMasterResult updateInventoryModelMaster(
            UpdateInventoryModelMasterRequest request
    ) {
        final AsyncResult<UpdateInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateInventoryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteInventoryModelMasterTask extends Gs2RestSessionTask<DeleteInventoryModelMasterResult> {
        private DeleteInventoryModelMasterRequest request;

        public DeleteInventoryModelMasterTask(
            DeleteInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteInventoryModelMasterResult>> userCallback,
            Class<DeleteInventoryModelMasterResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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
     * インベントリモデルマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteInventoryModelMasterAsync(
            DeleteInventoryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteInventoryModelMasterResult>> callback
    ) {
        DeleteInventoryModelMasterTask task = new DeleteInventoryModelMasterTask(request, callback, DeleteInventoryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * インベントリモデルマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteInventoryModelMasterResult deleteInventoryModelMaster(
            DeleteInventoryModelMasterRequest request
    ) {
        final AsyncResult<DeleteInventoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteInventoryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeInventoryModelsTask extends Gs2RestSessionTask<DescribeInventoryModelsResult> {
        private DescribeInventoryModelsRequest request;

        public DescribeInventoryModelsTask(
            DescribeInventoryModelsRequest request,
            AsyncAction<AsyncResult<DescribeInventoryModelsResult>> userCallback,
            Class<DescribeInventoryModelsResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/inventory";

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
     * インベントリモデルの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeInventoryModelsAsync(
            DescribeInventoryModelsRequest request,
            AsyncAction<AsyncResult<DescribeInventoryModelsResult>> callback
    ) {
        DescribeInventoryModelsTask task = new DescribeInventoryModelsTask(request, callback, DescribeInventoryModelsResult.class);
        session.execute(task);
    }

    /**
     * インベントリモデルの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeInventoryModelsResult describeInventoryModels(
            DescribeInventoryModelsRequest request
    ) {
        final AsyncResult<DescribeInventoryModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeInventoryModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetInventoryModelTask extends Gs2RestSessionTask<GetInventoryModelResult> {
        private GetInventoryModelRequest request;

        public GetInventoryModelTask(
            GetInventoryModelRequest request,
            AsyncAction<AsyncResult<GetInventoryModelResult>> userCallback,
            Class<GetInventoryModelResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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
     * インベントリモデルを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getInventoryModelAsync(
            GetInventoryModelRequest request,
            AsyncAction<AsyncResult<GetInventoryModelResult>> callback
    ) {
        GetInventoryModelTask task = new GetInventoryModelTask(request, callback, GetInventoryModelResult.class);
        session.execute(task);
    }

    /**
     * インベントリモデルを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetInventoryModelResult getInventoryModel(
            GetInventoryModelRequest request
    ) {
        final AsyncResult<GetInventoryModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getInventoryModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeItemModelMastersTask extends Gs2RestSessionTask<DescribeItemModelMastersResult> {
        private DescribeItemModelMastersRequest request;

        public DescribeItemModelMastersTask(
            DescribeItemModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeItemModelMastersResult>> userCallback,
            Class<DescribeItemModelMastersResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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
     * アイテムモデルマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeItemModelMastersAsync(
            DescribeItemModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeItemModelMastersResult>> callback
    ) {
        DescribeItemModelMastersTask task = new DescribeItemModelMastersTask(request, callback, DescribeItemModelMastersResult.class);
        session.execute(task);
    }

    /**
     * アイテムモデルマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeItemModelMastersResult describeItemModelMasters(
            DescribeItemModelMastersRequest request
    ) {
        final AsyncResult<DescribeItemModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeItemModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateItemModelMasterTask extends Gs2RestSessionTask<CreateItemModelMasterResult> {
        private CreateItemModelMasterRequest request;

        public CreateItemModelMasterTask(
            CreateItemModelMasterRequest request,
            AsyncAction<AsyncResult<CreateItemModelMasterResult>> userCallback,
            Class<CreateItemModelMasterResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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
            if (this.request.getMaxCount() != null) {
                json.put("maxCount", this.request.getMaxCount());
            }
            if (this.request.getSortValue() != null) {
                json.put("sortValue", this.request.getSortValue());
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
     * アイテムモデルマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createItemModelMasterAsync(
            CreateItemModelMasterRequest request,
            AsyncAction<AsyncResult<CreateItemModelMasterResult>> callback
    ) {
        CreateItemModelMasterTask task = new CreateItemModelMasterTask(request, callback, CreateItemModelMasterResult.class);
        session.execute(task);
    }

    /**
     * アイテムモデルマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateItemModelMasterResult createItemModelMaster(
            CreateItemModelMasterRequest request
    ) {
        final AsyncResult<CreateItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createItemModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetItemModelMasterTask extends Gs2RestSessionTask<GetItemModelMasterResult> {
        private GetItemModelMasterRequest request;

        public GetItemModelMasterTask(
            GetItemModelMasterRequest request,
            AsyncAction<AsyncResult<GetItemModelMasterResult>> userCallback,
            Class<GetItemModelMasterResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null|| this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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
     * アイテムモデルマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getItemModelMasterAsync(
            GetItemModelMasterRequest request,
            AsyncAction<AsyncResult<GetItemModelMasterResult>> callback
    ) {
        GetItemModelMasterTask task = new GetItemModelMasterTask(request, callback, GetItemModelMasterResult.class);
        session.execute(task);
    }

    /**
     * アイテムモデルマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetItemModelMasterResult getItemModelMaster(
            GetItemModelMasterRequest request
    ) {
        final AsyncResult<GetItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getItemModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateItemModelMasterTask extends Gs2RestSessionTask<UpdateItemModelMasterResult> {
        private UpdateItemModelMasterRequest request;

        public UpdateItemModelMasterTask(
            UpdateItemModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateItemModelMasterResult>> userCallback,
            Class<UpdateItemModelMasterResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null|| this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getMaxCount() != null) {
                json.put("maxCount", this.request.getMaxCount());
            }
            if (this.request.getSortValue() != null) {
                json.put("sortValue", this.request.getSortValue());
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
     * アイテムモデルマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateItemModelMasterAsync(
            UpdateItemModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateItemModelMasterResult>> callback
    ) {
        UpdateItemModelMasterTask task = new UpdateItemModelMasterTask(request, callback, UpdateItemModelMasterResult.class);
        session.execute(task);
    }

    /**
     * アイテムモデルマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateItemModelMasterResult updateItemModelMaster(
            UpdateItemModelMasterRequest request
    ) {
        final AsyncResult<UpdateItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateItemModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteItemModelMasterTask extends Gs2RestSessionTask<DeleteItemModelMasterResult> {
        private DeleteItemModelMasterRequest request;

        public DeleteItemModelMasterTask(
            DeleteItemModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteItemModelMasterResult>> userCallback,
            Class<DeleteItemModelMasterResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null|| this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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
     * アイテムモデルマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteItemModelMasterAsync(
            DeleteItemModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteItemModelMasterResult>> callback
    ) {
        DeleteItemModelMasterTask task = new DeleteItemModelMasterTask(request, callback, DeleteItemModelMasterResult.class);
        session.execute(task);
    }

    /**
     * アイテムモデルマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteItemModelMasterResult deleteItemModelMaster(
            DeleteItemModelMasterRequest request
    ) {
        final AsyncResult<DeleteItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteItemModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeItemModelsTask extends Gs2RestSessionTask<DescribeItemModelsResult> {
        private DescribeItemModelsRequest request;

        public DescribeItemModelsTask(
            DescribeItemModelsRequest request,
            AsyncAction<AsyncResult<DescribeItemModelsResult>> userCallback,
            Class<DescribeItemModelsResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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
     * Noneの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeItemModelsAsync(
            DescribeItemModelsRequest request,
            AsyncAction<AsyncResult<DescribeItemModelsResult>> callback
    ) {
        DescribeItemModelsTask task = new DescribeItemModelsTask(request, callback, DescribeItemModelsResult.class);
        session.execute(task);
    }

    /**
     * Noneの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeItemModelsResult describeItemModels(
            DescribeItemModelsRequest request
    ) {
        final AsyncResult<DescribeItemModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeItemModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetItemModelTask extends Gs2RestSessionTask<GetItemModelResult> {
        private GetItemModelRequest request;

        public GetItemModelTask(
            GetItemModelRequest request,
            AsyncAction<AsyncResult<GetItemModelResult>> userCallback,
            Class<GetItemModelResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null|| this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

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
     * Noneを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getItemModelAsync(
            GetItemModelRequest request,
            AsyncAction<AsyncResult<GetItemModelResult>> callback
    ) {
        GetItemModelTask task = new GetItemModelTask(request, callback, GetItemModelResult.class);
        session.execute(task);
    }

    /**
     * Noneを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetItemModelResult getItemModel(
            GetItemModelRequest request
    ) {
        final AsyncResult<GetItemModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getItemModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "inventory")
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
     * 現在有効な所持品マスターのマスターデータをエクスポートします<br>
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
     * 現在有効な所持品マスターのマスターデータをエクスポートします<br>
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

    class GetCurrentItemModelMasterTask extends Gs2RestSessionTask<GetCurrentItemModelMasterResult> {
        private GetCurrentItemModelMasterRequest request;

        public GetCurrentItemModelMasterTask(
            GetCurrentItemModelMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentItemModelMasterResult>> userCallback,
            Class<GetCurrentItemModelMasterResult> clazz
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
                .replace("{service}", "inventory")
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
     * 現在有効な現在有効な所持品マスターを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentItemModelMasterAsync(
            GetCurrentItemModelMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentItemModelMasterResult>> callback
    ) {
        GetCurrentItemModelMasterTask task = new GetCurrentItemModelMasterTask(request, callback, GetCurrentItemModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な現在有効な所持品マスターを取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCurrentItemModelMasterResult getCurrentItemModelMaster(
            GetCurrentItemModelMasterRequest request
    ) {
        final AsyncResult<GetCurrentItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentItemModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentItemModelMasterTask extends Gs2RestSessionTask<UpdateCurrentItemModelMasterResult> {
        private UpdateCurrentItemModelMasterRequest request;

        public UpdateCurrentItemModelMasterTask(
            UpdateCurrentItemModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentItemModelMasterResult>> userCallback,
            Class<UpdateCurrentItemModelMasterResult> clazz
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
                .replace("{service}", "inventory")
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
     * 現在有効な現在有効な所持品マスターを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentItemModelMasterAsync(
            UpdateCurrentItemModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentItemModelMasterResult>> callback
    ) {
        UpdateCurrentItemModelMasterTask task = new UpdateCurrentItemModelMasterTask(request, callback, UpdateCurrentItemModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な現在有効な所持品マスターを更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentItemModelMasterResult updateCurrentItemModelMaster(
            UpdateCurrentItemModelMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentItemModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentItemModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeInventoriesTask extends Gs2RestSessionTask<DescribeInventoriesResult> {
        private DescribeInventoriesRequest request;

        public DescribeInventoriesTask(
            DescribeInventoriesRequest request,
            AsyncAction<AsyncResult<DescribeInventoriesResult>> userCallback,
            Class<DescribeInventoriesResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory";

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
     * インベントリの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeInventoriesAsync(
            DescribeInventoriesRequest request,
            AsyncAction<AsyncResult<DescribeInventoriesResult>> callback
    ) {
        DescribeInventoriesTask task = new DescribeInventoriesTask(request, callback, DescribeInventoriesResult.class);
        session.execute(task);
    }

    /**
     * インベントリの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeInventoriesResult describeInventories(
            DescribeInventoriesRequest request
    ) {
        final AsyncResult<DescribeInventoriesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeInventoriesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeInventoriesByUserIdTask extends Gs2RestSessionTask<DescribeInventoriesByUserIdResult> {
        private DescribeInventoriesByUserIdRequest request;

        public DescribeInventoriesByUserIdTask(
            DescribeInventoriesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeInventoriesByUserIdResult>> userCallback,
            Class<DescribeInventoriesByUserIdResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * インベントリの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeInventoriesByUserIdAsync(
            DescribeInventoriesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeInventoriesByUserIdResult>> callback
    ) {
        DescribeInventoriesByUserIdTask task = new DescribeInventoriesByUserIdTask(request, callback, DescribeInventoriesByUserIdResult.class);
        session.execute(task);
    }

    /**
     * インベントリの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeInventoriesByUserIdResult describeInventoriesByUserId(
            DescribeInventoriesByUserIdRequest request
    ) {
        final AsyncResult<DescribeInventoriesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeInventoriesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetInventoryTask extends Gs2RestSessionTask<GetInventoryResult> {
        private GetInventoryRequest request;

        public GetInventoryTask(
            GetInventoryRequest request,
            AsyncAction<AsyncResult<GetInventoryResult>> userCallback,
            Class<GetInventoryResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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
     * インベントリを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getInventoryAsync(
            GetInventoryRequest request,
            AsyncAction<AsyncResult<GetInventoryResult>> callback
    ) {
        GetInventoryTask task = new GetInventoryTask(request, callback, GetInventoryResult.class);
        session.execute(task);
    }

    /**
     * インベントリを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetInventoryResult getInventory(
            GetInventoryRequest request
    ) {
        final AsyncResult<GetInventoryResult>[] resultAsyncResult = new AsyncResult[]{null};
        getInventoryAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetInventoryByUserIdTask extends Gs2RestSessionTask<GetInventoryByUserIdResult> {
        private GetInventoryByUserIdRequest request;

        public GetInventoryByUserIdTask(
            GetInventoryByUserIdRequest request,
            AsyncAction<AsyncResult<GetInventoryByUserIdResult>> userCallback,
            Class<GetInventoryByUserIdResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
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
     * インベントリを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getInventoryByUserIdAsync(
            GetInventoryByUserIdRequest request,
            AsyncAction<AsyncResult<GetInventoryByUserIdResult>> callback
    ) {
        GetInventoryByUserIdTask task = new GetInventoryByUserIdTask(request, callback, GetInventoryByUserIdResult.class);
        session.execute(task);
    }

    /**
     * インベントリを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetInventoryByUserIdResult getInventoryByUserId(
            GetInventoryByUserIdRequest request
    ) {
        final AsyncResult<GetInventoryByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getInventoryByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddCapacityByUserIdTask extends Gs2RestSessionTask<AddCapacityByUserIdResult> {
        private AddCapacityByUserIdRequest request;

        public AddCapacityByUserIdTask(
            AddCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<AddCapacityByUserIdResult>> userCallback,
            Class<AddCapacityByUserIdResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/capacity";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getAddCapacityValue() != null) {
                json.put("addCapacityValue", this.request.getAddCapacityValue());
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
     * キャパシティサイズを加算<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void addCapacityByUserIdAsync(
            AddCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<AddCapacityByUserIdResult>> callback
    ) {
        AddCapacityByUserIdTask task = new AddCapacityByUserIdTask(request, callback, AddCapacityByUserIdResult.class);
        session.execute(task);
    }

    /**
     * キャパシティサイズを加算<br>
     *
     * @param request リクエストパラメータ
     */
    public AddCapacityByUserIdResult addCapacityByUserId(
            AddCapacityByUserIdRequest request
    ) {
        final AsyncResult<AddCapacityByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        addCapacityByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetCapacityByUserIdTask extends Gs2RestSessionTask<SetCapacityByUserIdResult> {
        private SetCapacityByUserIdRequest request;

        public SetCapacityByUserIdTask(
            SetCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<SetCapacityByUserIdResult>> userCallback,
            Class<SetCapacityByUserIdResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/capacity";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getNewCapacityValue() != null) {
                json.put("newCapacityValue", this.request.getNewCapacityValue());
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
     * キャパシティサイズを設定<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setCapacityByUserIdAsync(
            SetCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<SetCapacityByUserIdResult>> callback
    ) {
        SetCapacityByUserIdTask task = new SetCapacityByUserIdTask(request, callback, SetCapacityByUserIdResult.class);
        session.execute(task);
    }

    /**
     * キャパシティサイズを設定<br>
     *
     * @param request リクエストパラメータ
     */
    public SetCapacityByUserIdResult setCapacityByUserId(
            SetCapacityByUserIdRequest request
    ) {
        final AsyncResult<SetCapacityByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setCapacityByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteInventoryByUserIdTask extends Gs2RestSessionTask<DeleteInventoryByUserIdResult> {
        private DeleteInventoryByUserIdRequest request;

        public DeleteInventoryByUserIdTask(
            DeleteInventoryByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteInventoryByUserIdResult>> userCallback,
            Class<DeleteInventoryByUserIdResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
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
     * インベントリを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteInventoryByUserIdAsync(
            DeleteInventoryByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteInventoryByUserIdResult>> callback
    ) {
        DeleteInventoryByUserIdTask task = new DeleteInventoryByUserIdTask(request, callback, DeleteInventoryByUserIdResult.class);
        session.execute(task);
    }

    /**
     * インベントリを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteInventoryByUserIdResult deleteInventoryByUserId(
            DeleteInventoryByUserIdRequest request
    ) {
        final AsyncResult<DeleteInventoryByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteInventoryByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddCapacityByStampSheetTask extends Gs2RestSessionTask<AddCapacityByStampSheetResult> {
        private AddCapacityByStampSheetRequest request;

        public AddCapacityByStampSheetTask(
            AddCapacityByStampSheetRequest request,
            AsyncAction<AsyncResult<AddCapacityByStampSheetResult>> userCallback,
            Class<AddCapacityByStampSheetResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/inventory/capacity/add";

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
     * スタンプシートでキャパシティサイズを加算<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void addCapacityByStampSheetAsync(
            AddCapacityByStampSheetRequest request,
            AsyncAction<AsyncResult<AddCapacityByStampSheetResult>> callback
    ) {
        AddCapacityByStampSheetTask task = new AddCapacityByStampSheetTask(request, callback, AddCapacityByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートでキャパシティサイズを加算<br>
     *
     * @param request リクエストパラメータ
     */
    public AddCapacityByStampSheetResult addCapacityByStampSheet(
            AddCapacityByStampSheetRequest request
    ) {
        final AsyncResult<AddCapacityByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        addCapacityByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetCapacityByStampSheetTask extends Gs2RestSessionTask<SetCapacityByStampSheetResult> {
        private SetCapacityByStampSheetRequest request;

        public SetCapacityByStampSheetTask(
            SetCapacityByStampSheetRequest request,
            AsyncAction<AsyncResult<SetCapacityByStampSheetResult>> userCallback,
            Class<SetCapacityByStampSheetResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/inventory/capacity/set";

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
     * スタンプシートでキャパシティサイズを設定<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setCapacityByStampSheetAsync(
            SetCapacityByStampSheetRequest request,
            AsyncAction<AsyncResult<SetCapacityByStampSheetResult>> callback
    ) {
        SetCapacityByStampSheetTask task = new SetCapacityByStampSheetTask(request, callback, SetCapacityByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートでキャパシティサイズを設定<br>
     *
     * @param request リクエストパラメータ
     */
    public SetCapacityByStampSheetResult setCapacityByStampSheet(
            SetCapacityByStampSheetRequest request
    ) {
        final AsyncResult<SetCapacityByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        setCapacityByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeItemSetsTask extends Gs2RestSessionTask<DescribeItemSetsResult> {
        private DescribeItemSetsRequest request;

        public DescribeItemSetsTask(
            DescribeItemSetsRequest request,
            AsyncAction<AsyncResult<DescribeItemSetsResult>> userCallback,
            Class<DescribeItemSetsResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * 有効期限ごとのアイテム所持数量の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeItemSetsAsync(
            DescribeItemSetsRequest request,
            AsyncAction<AsyncResult<DescribeItemSetsResult>> callback
    ) {
        DescribeItemSetsTask task = new DescribeItemSetsTask(request, callback, DescribeItemSetsResult.class);
        session.execute(task);
    }

    /**
     * 有効期限ごとのアイテム所持数量の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeItemSetsResult describeItemSets(
            DescribeItemSetsRequest request
    ) {
        final AsyncResult<DescribeItemSetsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeItemSetsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeItemSetsByUserIdTask extends Gs2RestSessionTask<DescribeItemSetsByUserIdResult> {
        private DescribeItemSetsByUserIdRequest request;

        public DescribeItemSetsByUserIdTask(
            DescribeItemSetsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeItemSetsByUserIdResult>> userCallback,
            Class<DescribeItemSetsByUserIdResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * 有効期限ごとのアイテム所持数量の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeItemSetsByUserIdAsync(
            DescribeItemSetsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeItemSetsByUserIdResult>> callback
    ) {
        DescribeItemSetsByUserIdTask task = new DescribeItemSetsByUserIdTask(request, callback, DescribeItemSetsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 有効期限ごとのアイテム所持数量の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeItemSetsByUserIdResult describeItemSetsByUserId(
            DescribeItemSetsByUserIdRequest request
    ) {
        final AsyncResult<DescribeItemSetsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeItemSetsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetItemSetTask extends Gs2RestSessionTask<GetItemSetResult> {
        private GetItemSetRequest request;

        public GetItemSetTask(
            GetItemSetRequest request,
            AsyncAction<AsyncResult<GetItemSetResult>> userCallback,
            Class<GetItemSetResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null|| this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getExpiresAt() != null) {
                queryStrings.add("expiresAt=" + String.valueOf(this.request.getExpiresAt()));
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
     * 有効期限ごとのアイテム所持数量を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getItemSetAsync(
            GetItemSetRequest request,
            AsyncAction<AsyncResult<GetItemSetResult>> callback
    ) {
        GetItemSetTask task = new GetItemSetTask(request, callback, GetItemSetResult.class);
        session.execute(task);
    }

    /**
     * 有効期限ごとのアイテム所持数量を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetItemSetResult getItemSet(
            GetItemSetRequest request
    ) {
        final AsyncResult<GetItemSetResult>[] resultAsyncResult = new AsyncResult[]{null};
        getItemSetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetItemSetByUserIdTask extends Gs2RestSessionTask<GetItemSetByUserIdResult> {
        private GetItemSetByUserIdRequest request;

        public GetItemSetByUserIdTask(
            GetItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<GetItemSetByUserIdResult>> userCallback,
            Class<GetItemSetByUserIdResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null|| this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{expiresAt}", this.request.getExpiresAt() == null ? "null" : String.valueOf(this.request.getExpiresAt()));

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
     * 有効期限ごとのアイテム所持数量を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getItemSetByUserIdAsync(
            GetItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<GetItemSetByUserIdResult>> callback
    ) {
        GetItemSetByUserIdTask task = new GetItemSetByUserIdTask(request, callback, GetItemSetByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 有効期限ごとのアイテム所持数量を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetItemSetByUserIdResult getItemSetByUserId(
            GetItemSetByUserIdRequest request
    ) {
        final AsyncResult<GetItemSetByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getItemSetByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquireItemSetByUserIdTask extends Gs2RestSessionTask<AcquireItemSetByUserIdResult> {
        private AcquireItemSetByUserIdRequest request;

        public AcquireItemSetByUserIdTask(
            AcquireItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireItemSetByUserIdResult>> userCallback,
            Class<AcquireItemSetByUserIdResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}/acquire";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null|| this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getAcquireCount() != null) {
                json.put("acquireCount", this.request.getAcquireCount());
            }
            if (this.request.getExpiresAt() != null) {
                json.put("expiresAt", this.request.getExpiresAt());
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
     * アイテムをインベントリに追加<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void acquireItemSetByUserIdAsync(
            AcquireItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<AcquireItemSetByUserIdResult>> callback
    ) {
        AcquireItemSetByUserIdTask task = new AcquireItemSetByUserIdTask(request, callback, AcquireItemSetByUserIdResult.class);
        session.execute(task);
    }

    /**
     * アイテムをインベントリに追加<br>
     *
     * @param request リクエストパラメータ
     */
    public AcquireItemSetByUserIdResult acquireItemSetByUserId(
            AcquireItemSetByUserIdRequest request
    ) {
        final AsyncResult<AcquireItemSetByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireItemSetByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ConsumeItemSetTask extends Gs2RestSessionTask<ConsumeItemSetResult> {
        private ConsumeItemSetRequest request;

        public ConsumeItemSetTask(
            ConsumeItemSetRequest request,
            AsyncAction<AsyncResult<ConsumeItemSetResult>> userCallback,
            Class<ConsumeItemSetResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/inventory/{inventoryName}/item/{itemName}/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{itemName}", this.request.getItemName() == null|| this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getConsumeCount() != null) {
                json.put("consumeCount", this.request.getConsumeCount());
            }
            if (this.request.getExpiresAt() != null) {
                json.put("expiresAt", this.request.getExpiresAt());
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
     * インベントリのアイテムを消費<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void consumeItemSetAsync(
            ConsumeItemSetRequest request,
            AsyncAction<AsyncResult<ConsumeItemSetResult>> callback
    ) {
        ConsumeItemSetTask task = new ConsumeItemSetTask(request, callback, ConsumeItemSetResult.class);
        session.execute(task);
    }

    /**
     * インベントリのアイテムを消費<br>
     *
     * @param request リクエストパラメータ
     */
    public ConsumeItemSetResult consumeItemSet(
            ConsumeItemSetRequest request
    ) {
        final AsyncResult<ConsumeItemSetResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeItemSetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ConsumeItemSetByUserIdTask extends Gs2RestSessionTask<ConsumeItemSetByUserIdResult> {
        private ConsumeItemSetByUserIdRequest request;

        public ConsumeItemSetByUserIdTask(
            ConsumeItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumeItemSetByUserIdResult>> userCallback,
            Class<ConsumeItemSetByUserIdResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null|| this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getConsumeCount() != null) {
                json.put("consumeCount", this.request.getConsumeCount());
            }
            if (this.request.getExpiresAt() != null) {
                json.put("expiresAt", this.request.getExpiresAt());
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
     * インベントリのアイテムを消費<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void consumeItemSetByUserIdAsync(
            ConsumeItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumeItemSetByUserIdResult>> callback
    ) {
        ConsumeItemSetByUserIdTask task = new ConsumeItemSetByUserIdTask(request, callback, ConsumeItemSetByUserIdResult.class);
        session.execute(task);
    }

    /**
     * インベントリのアイテムを消費<br>
     *
     * @param request リクエストパラメータ
     */
    public ConsumeItemSetByUserIdResult consumeItemSetByUserId(
            ConsumeItemSetByUserIdRequest request
    ) {
        final AsyncResult<ConsumeItemSetByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeItemSetByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteItemSetByUserIdTask extends Gs2RestSessionTask<DeleteItemSetByUserIdResult> {
        private DeleteItemSetByUserIdRequest request;

        public DeleteItemSetByUserIdTask(
            DeleteItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteItemSetByUserIdResult>> userCallback,
            Class<DeleteItemSetByUserIdResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/inventory/{inventoryName}/item/{itemName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{inventoryName}", this.request.getInventoryName() == null|| this.request.getInventoryName().length() == 0 ? "null" : String.valueOf(this.request.getInventoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{itemName}", this.request.getItemName() == null|| this.request.getItemName().length() == 0 ? "null" : String.valueOf(this.request.getItemName()));
            url = url.replace("{expiresAt}", this.request.getExpiresAt() == null ? "null" : String.valueOf(this.request.getExpiresAt()));

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
     * 有効期限ごとのアイテム所持数量を削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteItemSetByUserIdAsync(
            DeleteItemSetByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteItemSetByUserIdResult>> callback
    ) {
        DeleteItemSetByUserIdTask task = new DeleteItemSetByUserIdTask(request, callback, DeleteItemSetByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 有効期限ごとのアイテム所持数量を削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteItemSetByUserIdResult deleteItemSetByUserId(
            DeleteItemSetByUserIdRequest request
    ) {
        final AsyncResult<DeleteItemSetByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteItemSetByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquireItemSetByStampSheetTask extends Gs2RestSessionTask<AcquireItemSetByStampSheetResult> {
        private AcquireItemSetByStampSheetRequest request;

        public AcquireItemSetByStampSheetTask(
            AcquireItemSetByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireItemSetByStampSheetResult>> userCallback,
            Class<AcquireItemSetByStampSheetResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/item/acquire";

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
     * スタンプシートでアイテムをインベントリに追加<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void acquireItemSetByStampSheetAsync(
            AcquireItemSetByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireItemSetByStampSheetResult>> callback
    ) {
        AcquireItemSetByStampSheetTask task = new AcquireItemSetByStampSheetTask(request, callback, AcquireItemSetByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートでアイテムをインベントリに追加<br>
     *
     * @param request リクエストパラメータ
     */
    public AcquireItemSetByStampSheetResult acquireItemSetByStampSheet(
            AcquireItemSetByStampSheetRequest request
    ) {
        final AsyncResult<AcquireItemSetByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireItemSetByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ConsumeItemSetByStampTaskTask extends Gs2RestSessionTask<ConsumeItemSetByStampTaskResult> {
        private ConsumeItemSetByStampTaskRequest request;

        public ConsumeItemSetByStampTaskTask(
            ConsumeItemSetByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumeItemSetByStampTaskResult>> userCallback,
            Class<ConsumeItemSetByStampTaskResult> clazz
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
                .replace("{service}", "inventory")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/item/consume";

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
     * スタンプシートでインベントリのアイテムを消費<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void consumeItemSetByStampTaskAsync(
            ConsumeItemSetByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumeItemSetByStampTaskResult>> callback
    ) {
        ConsumeItemSetByStampTaskTask task = new ConsumeItemSetByStampTaskTask(request, callback, ConsumeItemSetByStampTaskResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートでインベントリのアイテムを消費<br>
     *
     * @param request リクエストパラメータ
     */
    public ConsumeItemSetByStampTaskResult consumeItemSetByStampTask(
            ConsumeItemSetByStampTaskRequest request
    ) {
        final AsyncResult<ConsumeItemSetByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeItemSetByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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