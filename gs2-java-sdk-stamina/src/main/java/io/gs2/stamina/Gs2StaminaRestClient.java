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

package io.gs2.stamina;

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
import io.gs2.stamina.request.*;
import io.gs2.stamina.result.*;
import io.gs2.stamina.model.*;

/**
 * GS2 Stamina API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2StaminaRestClient extends AbstractGs2Client<Gs2StaminaRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2StaminaRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "stamina")
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
                .replace("{service}", "stamina")
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
            if (this.request.getOverflowTriggerScriptId() != null) {
                json.put("overflowTriggerScriptId", this.request.getOverflowTriggerScriptId());
            }
            if (this.request.getOverflowTriggerNamespaceId() != null) {
                json.put("overflowTriggerNamespaceId", this.request.getOverflowTriggerNamespaceId());
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
                .replace("{service}", "stamina")
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
                .replace("{service}", "stamina")
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getOverflowTriggerScriptId() != null) {
                json.put("overflowTriggerScriptId", this.request.getOverflowTriggerScriptId());
            }
            if (this.request.getOverflowTriggerNamespaceId() != null) {
                json.put("overflowTriggerNamespaceId", this.request.getOverflowTriggerNamespaceId());
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
                .replace("{service}", "stamina")
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

    class DescribeStaminaModelMastersTask extends Gs2RestSessionTask<DescribeStaminaModelMastersResult> {
        private DescribeStaminaModelMastersRequest request;

        public DescribeStaminaModelMastersTask(
            DescribeStaminaModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeStaminaModelMastersResult>> userCallback,
            Class<DescribeStaminaModelMastersResult> clazz
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
                .replace("{service}", "stamina")
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
     * スタミナモデルマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeStaminaModelMastersAsync(
            DescribeStaminaModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeStaminaModelMastersResult>> callback
    ) {
        DescribeStaminaModelMastersTask task = new DescribeStaminaModelMastersTask(request, callback, DescribeStaminaModelMastersResult.class);
        session.execute(task);
    }

    /**
     * スタミナモデルマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeStaminaModelMastersResult describeStaminaModelMasters(
            DescribeStaminaModelMastersRequest request
    ) {
        final AsyncResult<DescribeStaminaModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStaminaModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateStaminaModelMasterTask extends Gs2RestSessionTask<CreateStaminaModelMasterResult> {
        private CreateStaminaModelMasterRequest request;

        public CreateStaminaModelMasterTask(
            CreateStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<CreateStaminaModelMasterResult>> userCallback,
            Class<CreateStaminaModelMasterResult> clazz
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
                .replace("{service}", "stamina")
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
            if (this.request.getRecoverIntervalMinutes() != null) {
                json.put("recoverIntervalMinutes", this.request.getRecoverIntervalMinutes());
            }
            if (this.request.getRecoverValue() != null) {
                json.put("recoverValue", this.request.getRecoverValue());
            }
            if (this.request.getInitialCapacity() != null) {
                json.put("initialCapacity", this.request.getInitialCapacity());
            }
            if (this.request.getIsOverflow() != null) {
                json.put("isOverflow", this.request.getIsOverflow());
            }
            if (this.request.getMaxCapacity() != null) {
                json.put("maxCapacity", this.request.getMaxCapacity());
            }
            if (this.request.getMaxStaminaTableId() != null) {
                json.put("maxStaminaTableId", this.request.getMaxStaminaTableId());
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
     * スタミナモデルマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createStaminaModelMasterAsync(
            CreateStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<CreateStaminaModelMasterResult>> callback
    ) {
        CreateStaminaModelMasterTask task = new CreateStaminaModelMasterTask(request, callback, CreateStaminaModelMasterResult.class);
        session.execute(task);
    }

    /**
     * スタミナモデルマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateStaminaModelMasterResult createStaminaModelMaster(
            CreateStaminaModelMasterRequest request
    ) {
        final AsyncResult<CreateStaminaModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createStaminaModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStaminaModelMasterTask extends Gs2RestSessionTask<GetStaminaModelMasterResult> {
        private GetStaminaModelMasterRequest request;

        public GetStaminaModelMasterTask(
            GetStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<GetStaminaModelMasterResult>> userCallback,
            Class<GetStaminaModelMasterResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

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
     * スタミナモデルマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getStaminaModelMasterAsync(
            GetStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<GetStaminaModelMasterResult>> callback
    ) {
        GetStaminaModelMasterTask task = new GetStaminaModelMasterTask(request, callback, GetStaminaModelMasterResult.class);
        session.execute(task);
    }

    /**
     * スタミナモデルマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetStaminaModelMasterResult getStaminaModelMaster(
            GetStaminaModelMasterRequest request
    ) {
        final AsyncResult<GetStaminaModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStaminaModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateStaminaModelMasterTask extends Gs2RestSessionTask<UpdateStaminaModelMasterResult> {
        private UpdateStaminaModelMasterRequest request;

        public UpdateStaminaModelMasterTask(
            UpdateStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateStaminaModelMasterResult>> userCallback,
            Class<UpdateStaminaModelMasterResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getRecoverIntervalMinutes() != null) {
                json.put("recoverIntervalMinutes", this.request.getRecoverIntervalMinutes());
            }
            if (this.request.getRecoverValue() != null) {
                json.put("recoverValue", this.request.getRecoverValue());
            }
            if (this.request.getInitialCapacity() != null) {
                json.put("initialCapacity", this.request.getInitialCapacity());
            }
            if (this.request.getIsOverflow() != null) {
                json.put("isOverflow", this.request.getIsOverflow());
            }
            if (this.request.getMaxCapacity() != null) {
                json.put("maxCapacity", this.request.getMaxCapacity());
            }
            if (this.request.getMaxStaminaTableId() != null) {
                json.put("maxStaminaTableId", this.request.getMaxStaminaTableId());
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
     * スタミナモデルマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateStaminaModelMasterAsync(
            UpdateStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateStaminaModelMasterResult>> callback
    ) {
        UpdateStaminaModelMasterTask task = new UpdateStaminaModelMasterTask(request, callback, UpdateStaminaModelMasterResult.class);
        session.execute(task);
    }

    /**
     * スタミナモデルマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateStaminaModelMasterResult updateStaminaModelMaster(
            UpdateStaminaModelMasterRequest request
    ) {
        final AsyncResult<UpdateStaminaModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateStaminaModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteStaminaModelMasterTask extends Gs2RestSessionTask<DeleteStaminaModelMasterResult> {
        private DeleteStaminaModelMasterRequest request;

        public DeleteStaminaModelMasterTask(
            DeleteStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteStaminaModelMasterResult>> userCallback,
            Class<DeleteStaminaModelMasterResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

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
     * スタミナモデルマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteStaminaModelMasterAsync(
            DeleteStaminaModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteStaminaModelMasterResult>> callback
    ) {
        DeleteStaminaModelMasterTask task = new DeleteStaminaModelMasterTask(request, callback, DeleteStaminaModelMasterResult.class);
        session.execute(task);
    }

    /**
     * スタミナモデルマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteStaminaModelMasterResult deleteStaminaModelMaster(
            DeleteStaminaModelMasterRequest request
    ) {
        final AsyncResult<DeleteStaminaModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteStaminaModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMaxStaminaTableMastersTask extends Gs2RestSessionTask<DescribeMaxStaminaTableMastersResult> {
        private DescribeMaxStaminaTableMastersRequest request;

        public DescribeMaxStaminaTableMastersTask(
            DescribeMaxStaminaTableMastersRequest request,
            AsyncAction<AsyncResult<DescribeMaxStaminaTableMastersResult>> userCallback,
            Class<DescribeMaxStaminaTableMastersResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/maxStaminaTable";

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
     * スタミナの最大値テーブルマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeMaxStaminaTableMastersAsync(
            DescribeMaxStaminaTableMastersRequest request,
            AsyncAction<AsyncResult<DescribeMaxStaminaTableMastersResult>> callback
    ) {
        DescribeMaxStaminaTableMastersTask task = new DescribeMaxStaminaTableMastersTask(request, callback, DescribeMaxStaminaTableMastersResult.class);
        session.execute(task);
    }

    /**
     * スタミナの最大値テーブルマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeMaxStaminaTableMastersResult describeMaxStaminaTableMasters(
            DescribeMaxStaminaTableMastersRequest request
    ) {
        final AsyncResult<DescribeMaxStaminaTableMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMaxStaminaTableMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateMaxStaminaTableMasterTask extends Gs2RestSessionTask<CreateMaxStaminaTableMasterResult> {
        private CreateMaxStaminaTableMasterRequest request;

        public CreateMaxStaminaTableMasterTask(
            CreateMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<CreateMaxStaminaTableMasterResult>> userCallback,
            Class<CreateMaxStaminaTableMasterResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/maxStaminaTable";

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
            if (this.request.getExperienceModelId() != null) {
                json.put("experienceModelId", this.request.getExperienceModelId());
            }
            if (this.request.getValues() != null) {
                JSONArray array = new JSONArray();
                for(Integer item : this.request.getValues())
                {
                    array.put(item);
                }
                json.put("values", array);
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
     * スタミナの最大値テーブルマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createMaxStaminaTableMasterAsync(
            CreateMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<CreateMaxStaminaTableMasterResult>> callback
    ) {
        CreateMaxStaminaTableMasterTask task = new CreateMaxStaminaTableMasterTask(request, callback, CreateMaxStaminaTableMasterResult.class);
        session.execute(task);
    }

    /**
     * スタミナの最大値テーブルマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateMaxStaminaTableMasterResult createMaxStaminaTableMaster(
            CreateMaxStaminaTableMasterRequest request
    ) {
        final AsyncResult<CreateMaxStaminaTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createMaxStaminaTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMaxStaminaTableMasterTask extends Gs2RestSessionTask<GetMaxStaminaTableMasterResult> {
        private GetMaxStaminaTableMasterRequest request;

        public GetMaxStaminaTableMasterTask(
            GetMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<GetMaxStaminaTableMasterResult>> userCallback,
            Class<GetMaxStaminaTableMasterResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/maxStaminaTable/{maxStaminaTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{maxStaminaTableName}", this.request.getMaxStaminaTableName() == null|| this.request.getMaxStaminaTableName().length() == 0 ? "null" : String.valueOf(this.request.getMaxStaminaTableName()));

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
     * スタミナの最大値テーブルマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getMaxStaminaTableMasterAsync(
            GetMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<GetMaxStaminaTableMasterResult>> callback
    ) {
        GetMaxStaminaTableMasterTask task = new GetMaxStaminaTableMasterTask(request, callback, GetMaxStaminaTableMasterResult.class);
        session.execute(task);
    }

    /**
     * スタミナの最大値テーブルマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetMaxStaminaTableMasterResult getMaxStaminaTableMaster(
            GetMaxStaminaTableMasterRequest request
    ) {
        final AsyncResult<GetMaxStaminaTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMaxStaminaTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateMaxStaminaTableMasterTask extends Gs2RestSessionTask<UpdateMaxStaminaTableMasterResult> {
        private UpdateMaxStaminaTableMasterRequest request;

        public UpdateMaxStaminaTableMasterTask(
            UpdateMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<UpdateMaxStaminaTableMasterResult>> userCallback,
            Class<UpdateMaxStaminaTableMasterResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/maxStaminaTable/{maxStaminaTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{maxStaminaTableName}", this.request.getMaxStaminaTableName() == null|| this.request.getMaxStaminaTableName().length() == 0 ? "null" : String.valueOf(this.request.getMaxStaminaTableName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getExperienceModelId() != null) {
                json.put("experienceModelId", this.request.getExperienceModelId());
            }
            if (this.request.getValues() != null) {
                JSONArray array = new JSONArray();
                for(Integer item : this.request.getValues())
                {
                    array.put(item);
                }
                json.put("values", array);
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
     * スタミナの最大値テーブルマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateMaxStaminaTableMasterAsync(
            UpdateMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<UpdateMaxStaminaTableMasterResult>> callback
    ) {
        UpdateMaxStaminaTableMasterTask task = new UpdateMaxStaminaTableMasterTask(request, callback, UpdateMaxStaminaTableMasterResult.class);
        session.execute(task);
    }

    /**
     * スタミナの最大値テーブルマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateMaxStaminaTableMasterResult updateMaxStaminaTableMaster(
            UpdateMaxStaminaTableMasterRequest request
    ) {
        final AsyncResult<UpdateMaxStaminaTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateMaxStaminaTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteMaxStaminaTableMasterTask extends Gs2RestSessionTask<DeleteMaxStaminaTableMasterResult> {
        private DeleteMaxStaminaTableMasterRequest request;

        public DeleteMaxStaminaTableMasterTask(
            DeleteMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<DeleteMaxStaminaTableMasterResult>> userCallback,
            Class<DeleteMaxStaminaTableMasterResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/maxStaminaTable/{maxStaminaTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{maxStaminaTableName}", this.request.getMaxStaminaTableName() == null|| this.request.getMaxStaminaTableName().length() == 0 ? "null" : String.valueOf(this.request.getMaxStaminaTableName()));

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
     * スタミナの最大値テーブルマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteMaxStaminaTableMasterAsync(
            DeleteMaxStaminaTableMasterRequest request,
            AsyncAction<AsyncResult<DeleteMaxStaminaTableMasterResult>> callback
    ) {
        DeleteMaxStaminaTableMasterTask task = new DeleteMaxStaminaTableMasterTask(request, callback, DeleteMaxStaminaTableMasterResult.class);
        session.execute(task);
    }

    /**
     * スタミナの最大値テーブルマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteMaxStaminaTableMasterResult deleteMaxStaminaTableMaster(
            DeleteMaxStaminaTableMasterRequest request
    ) {
        final AsyncResult<DeleteMaxStaminaTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMaxStaminaTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "stamina")
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
     * 現在有効なスタミナマスターのマスターデータをエクスポートします<br>
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
     * 現在有効なスタミナマスターのマスターデータをエクスポートします<br>
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

    class GetCurrentStaminaMasterTask extends Gs2RestSessionTask<GetCurrentStaminaMasterResult> {
        private GetCurrentStaminaMasterRequest request;

        public GetCurrentStaminaMasterTask(
            GetCurrentStaminaMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentStaminaMasterResult>> userCallback,
            Class<GetCurrentStaminaMasterResult> clazz
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
                .replace("{service}", "stamina")
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
     * 現在有効な現在有効なスタミナマスターを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentStaminaMasterAsync(
            GetCurrentStaminaMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentStaminaMasterResult>> callback
    ) {
        GetCurrentStaminaMasterTask task = new GetCurrentStaminaMasterTask(request, callback, GetCurrentStaminaMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な現在有効なスタミナマスターを取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCurrentStaminaMasterResult getCurrentStaminaMaster(
            GetCurrentStaminaMasterRequest request
    ) {
        final AsyncResult<GetCurrentStaminaMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentStaminaMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentStaminaMasterTask extends Gs2RestSessionTask<UpdateCurrentStaminaMasterResult> {
        private UpdateCurrentStaminaMasterRequest request;

        public UpdateCurrentStaminaMasterTask(
            UpdateCurrentStaminaMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentStaminaMasterResult>> userCallback,
            Class<UpdateCurrentStaminaMasterResult> clazz
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
                .replace("{service}", "stamina")
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
     * 現在有効な現在有効なスタミナマスターを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentStaminaMasterAsync(
            UpdateCurrentStaminaMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentStaminaMasterResult>> callback
    ) {
        UpdateCurrentStaminaMasterTask task = new UpdateCurrentStaminaMasterTask(request, callback, UpdateCurrentStaminaMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な現在有効なスタミナマスターを更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentStaminaMasterResult updateCurrentStaminaMaster(
            UpdateCurrentStaminaMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentStaminaMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentStaminaMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeStaminaModelsTask extends Gs2RestSessionTask<DescribeStaminaModelsResult> {
        private DescribeStaminaModelsRequest request;

        public DescribeStaminaModelsTask(
            DescribeStaminaModelsRequest request,
            AsyncAction<AsyncResult<DescribeStaminaModelsResult>> userCallback,
            Class<DescribeStaminaModelsResult> clazz
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
                .replace("{service}", "stamina")
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
     * スタミナモデルの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeStaminaModelsAsync(
            DescribeStaminaModelsRequest request,
            AsyncAction<AsyncResult<DescribeStaminaModelsResult>> callback
    ) {
        DescribeStaminaModelsTask task = new DescribeStaminaModelsTask(request, callback, DescribeStaminaModelsResult.class);
        session.execute(task);
    }

    /**
     * スタミナモデルの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeStaminaModelsResult describeStaminaModels(
            DescribeStaminaModelsRequest request
    ) {
        final AsyncResult<DescribeStaminaModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStaminaModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStaminaModelTask extends Gs2RestSessionTask<GetStaminaModelResult> {
        private GetStaminaModelRequest request;

        public GetStaminaModelTask(
            GetStaminaModelRequest request,
            AsyncAction<AsyncResult<GetStaminaModelResult>> userCallback,
            Class<GetStaminaModelResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

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
     * スタミナモデルを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getStaminaModelAsync(
            GetStaminaModelRequest request,
            AsyncAction<AsyncResult<GetStaminaModelResult>> callback
    ) {
        GetStaminaModelTask task = new GetStaminaModelTask(request, callback, GetStaminaModelResult.class);
        session.execute(task);
    }

    /**
     * スタミナモデルを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetStaminaModelResult getStaminaModel(
            GetStaminaModelRequest request
    ) {
        final AsyncResult<GetStaminaModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStaminaModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeStaminasTask extends Gs2RestSessionTask<DescribeStaminasResult> {
        private DescribeStaminasRequest request;

        public DescribeStaminasTask(
            DescribeStaminasRequest request,
            AsyncAction<AsyncResult<DescribeStaminasResult>> userCallback,
            Class<DescribeStaminasResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/stamina";

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
     * スタミナを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeStaminasAsync(
            DescribeStaminasRequest request,
            AsyncAction<AsyncResult<DescribeStaminasResult>> callback
    ) {
        DescribeStaminasTask task = new DescribeStaminasTask(request, callback, DescribeStaminasResult.class);
        session.execute(task);
    }

    /**
     * スタミナを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeStaminasResult describeStaminas(
            DescribeStaminasRequest request
    ) {
        final AsyncResult<DescribeStaminasResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStaminasAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeStaminasByUserIdTask extends Gs2RestSessionTask<DescribeStaminasByUserIdResult> {
        private DescribeStaminasByUserIdRequest request;

        public DescribeStaminasByUserIdTask(
            DescribeStaminasByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeStaminasByUserIdResult>> userCallback,
            Class<DescribeStaminasByUserIdResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina";

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
     * ユーザIDを指定してスタミナを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeStaminasByUserIdAsync(
            DescribeStaminasByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeStaminasByUserIdResult>> callback
    ) {
        DescribeStaminasByUserIdTask task = new DescribeStaminasByUserIdTask(request, callback, DescribeStaminasByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してスタミナを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeStaminasByUserIdResult describeStaminasByUserId(
            DescribeStaminasByUserIdRequest request
    ) {
        final AsyncResult<DescribeStaminasByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeStaminasByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStaminaTask extends Gs2RestSessionTask<GetStaminaResult> {
        private GetStaminaRequest request;

        public GetStaminaTask(
            GetStaminaRequest request,
            AsyncAction<AsyncResult<GetStaminaResult>> userCallback,
            Class<GetStaminaResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/stamina/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

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
     * スタミナを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getStaminaAsync(
            GetStaminaRequest request,
            AsyncAction<AsyncResult<GetStaminaResult>> callback
    ) {
        GetStaminaTask task = new GetStaminaTask(request, callback, GetStaminaResult.class);
        session.execute(task);
    }

    /**
     * スタミナを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetStaminaResult getStamina(
            GetStaminaRequest request
    ) {
        final AsyncResult<GetStaminaResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStaminaAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetStaminaByUserIdTask extends Gs2RestSessionTask<GetStaminaByUserIdResult> {
        private GetStaminaByUserIdRequest request;

        public GetStaminaByUserIdTask(
            GetStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<GetStaminaByUserIdResult>> userCallback,
            Class<GetStaminaByUserIdResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
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
     * ユーザIDを指定してスタミナを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getStaminaByUserIdAsync(
            GetStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<GetStaminaByUserIdResult>> callback
    ) {
        GetStaminaByUserIdTask task = new GetStaminaByUserIdTask(request, callback, GetStaminaByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してスタミナを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetStaminaByUserIdResult getStaminaByUserId(
            GetStaminaByUserIdRequest request
    ) {
        final AsyncResult<GetStaminaByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getStaminaByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateStaminaByUserIdTask extends Gs2RestSessionTask<UpdateStaminaByUserIdResult> {
        private UpdateStaminaByUserIdRequest request;

        public UpdateStaminaByUserIdTask(
            UpdateStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateStaminaByUserIdResult>> userCallback,
            Class<UpdateStaminaByUserIdResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getValue() != null) {
                json.put("value", this.request.getValue());
            }
            if (this.request.getMaxValue() != null) {
                json.put("maxValue", this.request.getMaxValue());
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
     * ユーザIDを指定してスタミナを作成・更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateStaminaByUserIdAsync(
            UpdateStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateStaminaByUserIdResult>> callback
    ) {
        UpdateStaminaByUserIdTask task = new UpdateStaminaByUserIdTask(request, callback, UpdateStaminaByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してスタミナを作成・更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateStaminaByUserIdResult updateStaminaByUserId(
            UpdateStaminaByUserIdRequest request
    ) {
        final AsyncResult<UpdateStaminaByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateStaminaByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ConsumeStaminaTask extends Gs2RestSessionTask<ConsumeStaminaResult> {
        private ConsumeStaminaRequest request;

        public ConsumeStaminaTask(
            ConsumeStaminaRequest request,
            AsyncAction<AsyncResult<ConsumeStaminaResult>> userCallback,
            Class<ConsumeStaminaResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/stamina/{staminaName}/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getConsumeValue() != null) {
                json.put("consumeValue", this.request.getConsumeValue());
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
     * スタミナを消費<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void consumeStaminaAsync(
            ConsumeStaminaRequest request,
            AsyncAction<AsyncResult<ConsumeStaminaResult>> callback
    ) {
        ConsumeStaminaTask task = new ConsumeStaminaTask(request, callback, ConsumeStaminaResult.class);
        session.execute(task);
    }

    /**
     * スタミナを消費<br>
     *
     * @param request リクエストパラメータ
     */
    public ConsumeStaminaResult consumeStamina(
            ConsumeStaminaRequest request
    ) {
        final AsyncResult<ConsumeStaminaResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeStaminaAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ConsumeStaminaByUserIdTask extends Gs2RestSessionTask<ConsumeStaminaByUserIdResult> {
        private ConsumeStaminaByUserIdRequest request;

        public ConsumeStaminaByUserIdTask(
            ConsumeStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumeStaminaByUserIdResult>> userCallback,
            Class<ConsumeStaminaByUserIdResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}/consume";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getConsumeValue() != null) {
                json.put("consumeValue", this.request.getConsumeValue());
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
     * ユーザIDを指定してスタミナを消費<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void consumeStaminaByUserIdAsync(
            ConsumeStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<ConsumeStaminaByUserIdResult>> callback
    ) {
        ConsumeStaminaByUserIdTask task = new ConsumeStaminaByUserIdTask(request, callback, ConsumeStaminaByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してスタミナを消費<br>
     *
     * @param request リクエストパラメータ
     */
    public ConsumeStaminaByUserIdResult consumeStaminaByUserId(
            ConsumeStaminaByUserIdRequest request
    ) {
        final AsyncResult<ConsumeStaminaByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeStaminaByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RecoverStaminaByUserIdTask extends Gs2RestSessionTask<RecoverStaminaByUserIdResult> {
        private RecoverStaminaByUserIdRequest request;

        public RecoverStaminaByUserIdTask(
            RecoverStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<RecoverStaminaByUserIdResult>> userCallback,
            Class<RecoverStaminaByUserIdResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}/recover";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getRecoverValue() != null) {
                json.put("recoverValue", this.request.getRecoverValue());
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
     * ユーザIDを指定してスタミナを回復<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void recoverStaminaByUserIdAsync(
            RecoverStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<RecoverStaminaByUserIdResult>> callback
    ) {
        RecoverStaminaByUserIdTask task = new RecoverStaminaByUserIdTask(request, callback, RecoverStaminaByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してスタミナを回復<br>
     *
     * @param request リクエストパラメータ
     */
    public RecoverStaminaByUserIdResult recoverStaminaByUserId(
            RecoverStaminaByUserIdRequest request
    ) {
        final AsyncResult<RecoverStaminaByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        recoverStaminaByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RaiseMaxValueByUserIdTask extends Gs2RestSessionTask<RaiseMaxValueByUserIdResult> {
        private RaiseMaxValueByUserIdRequest request;

        public RaiseMaxValueByUserIdTask(
            RaiseMaxValueByUserIdRequest request,
            AsyncAction<AsyncResult<RaiseMaxValueByUserIdResult>> userCallback,
            Class<RaiseMaxValueByUserIdResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}/raise";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getRaiseValue() != null) {
                json.put("raiseValue", this.request.getRaiseValue());
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
     * ユーザIDを指定してスタミナの最大値を加算<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void raiseMaxValueByUserIdAsync(
            RaiseMaxValueByUserIdRequest request,
            AsyncAction<AsyncResult<RaiseMaxValueByUserIdResult>> callback
    ) {
        RaiseMaxValueByUserIdTask task = new RaiseMaxValueByUserIdTask(request, callback, RaiseMaxValueByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してスタミナの最大値を加算<br>
     *
     * @param request リクエストパラメータ
     */
    public RaiseMaxValueByUserIdResult raiseMaxValueByUserId(
            RaiseMaxValueByUserIdRequest request
    ) {
        final AsyncResult<RaiseMaxValueByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        raiseMaxValueByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetMaxValueByUserIdTask extends Gs2RestSessionTask<SetMaxValueByUserIdResult> {
        private SetMaxValueByUserIdRequest request;

        public SetMaxValueByUserIdTask(
            SetMaxValueByUserIdRequest request,
            AsyncAction<AsyncResult<SetMaxValueByUserIdResult>> userCallback,
            Class<SetMaxValueByUserIdResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}/set";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getMaxValue() != null) {
                json.put("maxValue", this.request.getMaxValue());
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
     * ユーザIDを指定してスタミナの最大値を更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setMaxValueByUserIdAsync(
            SetMaxValueByUserIdRequest request,
            AsyncAction<AsyncResult<SetMaxValueByUserIdResult>> callback
    ) {
        SetMaxValueByUserIdTask task = new SetMaxValueByUserIdTask(request, callback, SetMaxValueByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してスタミナの最大値を更新<br>
     *
     * @param request リクエストパラメータ
     */
    public SetMaxValueByUserIdResult setMaxValueByUserId(
            SetMaxValueByUserIdRequest request
    ) {
        final AsyncResult<SetMaxValueByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setMaxValueByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetMaxValueByStatusTask extends Gs2RestSessionTask<SetMaxValueByStatusResult> {
        private SetMaxValueByStatusRequest request;

        public SetMaxValueByStatusTask(
            SetMaxValueByStatusRequest request,
            AsyncAction<AsyncResult<SetMaxValueByStatusResult>> userCallback,
            Class<SetMaxValueByStatusResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/stamina/{staminaName}/set";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getSignedStatusBody() != null) {
                json.put("signedStatusBody", this.request.getSignedStatusBody());
            }
            if (this.request.getSignedStatusSignature() != null) {
                json.put("signedStatusSignature", this.request.getSignedStatusSignature());
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
     * スタミナの最大値をGS2-Experienceのステータスを使用して更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setMaxValueByStatusAsync(
            SetMaxValueByStatusRequest request,
            AsyncAction<AsyncResult<SetMaxValueByStatusResult>> callback
    ) {
        SetMaxValueByStatusTask task = new SetMaxValueByStatusTask(request, callback, SetMaxValueByStatusResult.class);
        session.execute(task);
    }

    /**
     * スタミナの最大値をGS2-Experienceのステータスを使用して更新<br>
     *
     * @param request リクエストパラメータ
     */
    public SetMaxValueByStatusResult setMaxValueByStatus(
            SetMaxValueByStatusRequest request
    ) {
        final AsyncResult<SetMaxValueByStatusResult>[] resultAsyncResult = new AsyncResult[]{null};
        setMaxValueByStatusAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteStaminaByUserIdTask extends Gs2RestSessionTask<DeleteStaminaByUserIdResult> {
        private DeleteStaminaByUserIdRequest request;

        public DeleteStaminaByUserIdTask(
            DeleteStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteStaminaByUserIdResult>> userCallback,
            Class<DeleteStaminaByUserIdResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/stamina/{staminaName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{staminaName}", this.request.getStaminaName() == null|| this.request.getStaminaName().length() == 0 ? "null" : String.valueOf(this.request.getStaminaName()));
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
     * ユーザIDを指定してスタミナを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteStaminaByUserIdAsync(
            DeleteStaminaByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteStaminaByUserIdResult>> callback
    ) {
        DeleteStaminaByUserIdTask task = new DeleteStaminaByUserIdTask(request, callback, DeleteStaminaByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してスタミナを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteStaminaByUserIdResult deleteStaminaByUserId(
            DeleteStaminaByUserIdRequest request
    ) {
        final AsyncResult<DeleteStaminaByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteStaminaByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RecoverStaminaByStampSheetTask extends Gs2RestSessionTask<RecoverStaminaByStampSheetResult> {
        private RecoverStaminaByStampSheetRequest request;

        public RecoverStaminaByStampSheetTask(
            RecoverStaminaByStampSheetRequest request,
            AsyncAction<AsyncResult<RecoverStaminaByStampSheetResult>> userCallback,
            Class<RecoverStaminaByStampSheetResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/stamina/recover";

            url = url.replace("{stampSheet}", this.request.getStampSheet() == null|| this.request.getStampSheet().length() == 0 ? "null" : String.valueOf(this.request.getStampSheet()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * スタンプシートを使用してスタミナを回復<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void recoverStaminaByStampSheetAsync(
            RecoverStaminaByStampSheetRequest request,
            AsyncAction<AsyncResult<RecoverStaminaByStampSheetResult>> callback
    ) {
        RecoverStaminaByStampSheetTask task = new RecoverStaminaByStampSheetTask(request, callback, RecoverStaminaByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートを使用してスタミナを回復<br>
     *
     * @param request リクエストパラメータ
     */
    public RecoverStaminaByStampSheetResult recoverStaminaByStampSheet(
            RecoverStaminaByStampSheetRequest request
    ) {
        final AsyncResult<RecoverStaminaByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        recoverStaminaByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RaiseMaxValueByStampSheetTask extends Gs2RestSessionTask<RaiseMaxValueByStampSheetResult> {
        private RaiseMaxValueByStampSheetRequest request;

        public RaiseMaxValueByStampSheetTask(
            RaiseMaxValueByStampSheetRequest request,
            AsyncAction<AsyncResult<RaiseMaxValueByStampSheetResult>> userCallback,
            Class<RaiseMaxValueByStampSheetResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/stamina/raise";

            url = url.replace("{stampSheet}", this.request.getStampSheet() == null|| this.request.getStampSheet().length() == 0 ? "null" : String.valueOf(this.request.getStampSheet()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * スタンプシートでスタミナの最大値を加算<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void raiseMaxValueByStampSheetAsync(
            RaiseMaxValueByStampSheetRequest request,
            AsyncAction<AsyncResult<RaiseMaxValueByStampSheetResult>> callback
    ) {
        RaiseMaxValueByStampSheetTask task = new RaiseMaxValueByStampSheetTask(request, callback, RaiseMaxValueByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートでスタミナの最大値を加算<br>
     *
     * @param request リクエストパラメータ
     */
    public RaiseMaxValueByStampSheetResult raiseMaxValueByStampSheet(
            RaiseMaxValueByStampSheetRequest request
    ) {
        final AsyncResult<RaiseMaxValueByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        raiseMaxValueByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetMaxValueByStampSheetTask extends Gs2RestSessionTask<SetMaxValueByStampSheetResult> {
        private SetMaxValueByStampSheetRequest request;

        public SetMaxValueByStampSheetTask(
            SetMaxValueByStampSheetRequest request,
            AsyncAction<AsyncResult<SetMaxValueByStampSheetResult>> userCallback,
            Class<SetMaxValueByStampSheetResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/stamina/max/set";

            url = url.replace("{stampSheet}", this.request.getStampSheet() == null|| this.request.getStampSheet().length() == 0 ? "null" : String.valueOf(this.request.getStampSheet()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * スタンプシートでスタミナの最大値を更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setMaxValueByStampSheetAsync(
            SetMaxValueByStampSheetRequest request,
            AsyncAction<AsyncResult<SetMaxValueByStampSheetResult>> callback
    ) {
        SetMaxValueByStampSheetTask task = new SetMaxValueByStampSheetTask(request, callback, SetMaxValueByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートでスタミナの最大値を更新<br>
     *
     * @param request リクエストパラメータ
     */
    public SetMaxValueByStampSheetResult setMaxValueByStampSheet(
            SetMaxValueByStampSheetRequest request
    ) {
        final AsyncResult<SetMaxValueByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        setMaxValueByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ConsumeStaminaByStampTaskTask extends Gs2RestSessionTask<ConsumeStaminaByStampTaskResult> {
        private ConsumeStaminaByStampTaskRequest request;

        public ConsumeStaminaByStampTaskTask(
            ConsumeStaminaByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumeStaminaByStampTaskResult>> userCallback,
            Class<ConsumeStaminaByStampTaskResult> clazz
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
                .replace("{service}", "stamina")
                .replace("{region}", session.getRegion().getName())
                + "/stamina/consume";

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
     * スタンプタスクを使用してスタミナを消費<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void consumeStaminaByStampTaskAsync(
            ConsumeStaminaByStampTaskRequest request,
            AsyncAction<AsyncResult<ConsumeStaminaByStampTaskResult>> callback
    ) {
        ConsumeStaminaByStampTaskTask task = new ConsumeStaminaByStampTaskTask(request, callback, ConsumeStaminaByStampTaskResult.class);
        session.execute(task);
    }

    /**
     * スタンプタスクを使用してスタミナを消費<br>
     *
     * @param request リクエストパラメータ
     */
    public ConsumeStaminaByStampTaskResult consumeStaminaByStampTask(
            ConsumeStaminaByStampTaskRequest request
    ) {
        final AsyncResult<ConsumeStaminaByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        consumeStaminaByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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