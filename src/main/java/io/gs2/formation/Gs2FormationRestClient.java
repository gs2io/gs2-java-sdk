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

package io.gs2.formation;

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
import io.gs2.formation.request.*;
import io.gs2.formation.result.*;
import io.gs2.formation.model.*;

/**
 * GS2 Formation API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2FormationRestClient extends AbstractGs2Client<Gs2FormationRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2FormationRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "formation")
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
                .replace("{service}", "formation")
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
            if (this.request.getUpdateMoldScript() != null) {
                try {
                    json.put("updateMoldScript", new JSONObject(mapper.writeValueAsString(this.request.getUpdateMoldScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getUpdateFormScript() != null) {
                try {
                    json.put("updateFormScript", new JSONObject(mapper.writeValueAsString(this.request.getUpdateFormScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
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
                .replace("{service}", "formation")
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
                .replace("{service}", "formation")
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getUpdateMoldScript() != null) {
                try {
                    json.put("updateMoldScript", new JSONObject(mapper.writeValueAsString(this.request.getUpdateMoldScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getUpdateFormScript() != null) {
                try {
                    json.put("updateFormScript", new JSONObject(mapper.writeValueAsString(this.request.getUpdateFormScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
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
                .replace("{service}", "formation")
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

    class DescribeFormModelMastersTask extends Gs2RestSessionTask<DescribeFormModelMastersResult> {
        private DescribeFormModelMastersRequest request;

        public DescribeFormModelMastersTask(
            DescribeFormModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeFormModelMastersResult>> userCallback,
            Class<DescribeFormModelMastersResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/form";

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
     * フォームマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeFormModelMastersAsync(
            DescribeFormModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeFormModelMastersResult>> callback
    ) {
        DescribeFormModelMastersTask task = new DescribeFormModelMastersTask(request, callback, DescribeFormModelMastersResult.class);
        session.execute(task);
    }

    /**
     * フォームマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeFormModelMastersResult describeFormModelMasters(
            DescribeFormModelMastersRequest request
    ) {
        final AsyncResult<DescribeFormModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeFormModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateFormModelMasterTask extends Gs2RestSessionTask<CreateFormModelMasterResult> {
        private CreateFormModelMasterRequest request;

        public CreateFormModelMasterTask(
            CreateFormModelMasterRequest request,
            AsyncAction<AsyncResult<CreateFormModelMasterResult>> userCallback,
            Class<CreateFormModelMasterResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/form";

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
            if (this.request.getSlots() != null) {
                JSONArray array = new JSONArray();
                for(SlotModel item : this.request.getSlots())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("slots", array);
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
     * フォームマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createFormModelMasterAsync(
            CreateFormModelMasterRequest request,
            AsyncAction<AsyncResult<CreateFormModelMasterResult>> callback
    ) {
        CreateFormModelMasterTask task = new CreateFormModelMasterTask(request, callback, CreateFormModelMasterResult.class);
        session.execute(task);
    }

    /**
     * フォームマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateFormModelMasterResult createFormModelMaster(
            CreateFormModelMasterRequest request
    ) {
        final AsyncResult<CreateFormModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createFormModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFormModelMasterTask extends Gs2RestSessionTask<GetFormModelMasterResult> {
        private GetFormModelMasterRequest request;

        public GetFormModelMasterTask(
            GetFormModelMasterRequest request,
            AsyncAction<AsyncResult<GetFormModelMasterResult>> userCallback,
            Class<GetFormModelMasterResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/form/{formModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{formModelName}", this.request.getFormModelName() == null|| this.request.getFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getFormModelName()));

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
     * フォームマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getFormModelMasterAsync(
            GetFormModelMasterRequest request,
            AsyncAction<AsyncResult<GetFormModelMasterResult>> callback
    ) {
        GetFormModelMasterTask task = new GetFormModelMasterTask(request, callback, GetFormModelMasterResult.class);
        session.execute(task);
    }

    /**
     * フォームマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetFormModelMasterResult getFormModelMaster(
            GetFormModelMasterRequest request
    ) {
        final AsyncResult<GetFormModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFormModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateFormModelMasterTask extends Gs2RestSessionTask<UpdateFormModelMasterResult> {
        private UpdateFormModelMasterRequest request;

        public UpdateFormModelMasterTask(
            UpdateFormModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateFormModelMasterResult>> userCallback,
            Class<UpdateFormModelMasterResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/form/{formModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{formModelName}", this.request.getFormModelName() == null|| this.request.getFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getFormModelName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getSlots() != null) {
                JSONArray array = new JSONArray();
                for(SlotModel item : this.request.getSlots())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("slots", array);
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
     * フォームマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateFormModelMasterAsync(
            UpdateFormModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateFormModelMasterResult>> callback
    ) {
        UpdateFormModelMasterTask task = new UpdateFormModelMasterTask(request, callback, UpdateFormModelMasterResult.class);
        session.execute(task);
    }

    /**
     * フォームマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateFormModelMasterResult updateFormModelMaster(
            UpdateFormModelMasterRequest request
    ) {
        final AsyncResult<UpdateFormModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateFormModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteFormModelMasterTask extends Gs2RestSessionTask<DeleteFormModelMasterResult> {
        private DeleteFormModelMasterRequest request;

        public DeleteFormModelMasterTask(
            DeleteFormModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteFormModelMasterResult>> userCallback,
            Class<DeleteFormModelMasterResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/form/{formModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{formModelName}", this.request.getFormModelName() == null|| this.request.getFormModelName().length() == 0 ? "null" : String.valueOf(this.request.getFormModelName()));

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
     * フォームマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteFormModelMasterAsync(
            DeleteFormModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteFormModelMasterResult>> callback
    ) {
        DeleteFormModelMasterTask task = new DeleteFormModelMasterTask(request, callback, DeleteFormModelMasterResult.class);
        session.execute(task);
    }

    /**
     * フォームマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteFormModelMasterResult deleteFormModelMaster(
            DeleteFormModelMasterRequest request
    ) {
        final AsyncResult<DeleteFormModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteFormModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMoldModelsTask extends Gs2RestSessionTask<DescribeMoldModelsResult> {
        private DescribeMoldModelsRequest request;

        public DescribeMoldModelsTask(
            DescribeMoldModelsRequest request,
            AsyncAction<AsyncResult<DescribeMoldModelsResult>> userCallback,
            Class<DescribeMoldModelsResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/mold";

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
     * フォームの保存領域の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeMoldModelsAsync(
            DescribeMoldModelsRequest request,
            AsyncAction<AsyncResult<DescribeMoldModelsResult>> callback
    ) {
        DescribeMoldModelsTask task = new DescribeMoldModelsTask(request, callback, DescribeMoldModelsResult.class);
        session.execute(task);
    }

    /**
     * フォームの保存領域の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeMoldModelsResult describeMoldModels(
            DescribeMoldModelsRequest request
    ) {
        final AsyncResult<DescribeMoldModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMoldModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMoldModelTask extends Gs2RestSessionTask<GetMoldModelResult> {
        private GetMoldModelRequest request;

        public GetMoldModelTask(
            GetMoldModelRequest request,
            AsyncAction<AsyncResult<GetMoldModelResult>> userCallback,
            Class<GetMoldModelResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/mold/{moldName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));

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
     * フォームの保存領域を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getMoldModelAsync(
            GetMoldModelRequest request,
            AsyncAction<AsyncResult<GetMoldModelResult>> callback
    ) {
        GetMoldModelTask task = new GetMoldModelTask(request, callback, GetMoldModelResult.class);
        session.execute(task);
    }

    /**
     * フォームの保存領域を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetMoldModelResult getMoldModel(
            GetMoldModelRequest request
    ) {
        final AsyncResult<GetMoldModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMoldModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMoldModelMastersTask extends Gs2RestSessionTask<DescribeMoldModelMastersResult> {
        private DescribeMoldModelMastersRequest request;

        public DescribeMoldModelMastersTask(
            DescribeMoldModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeMoldModelMastersResult>> userCallback,
            Class<DescribeMoldModelMastersResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/mold";

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
     * フォームの保存領域マスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeMoldModelMastersAsync(
            DescribeMoldModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeMoldModelMastersResult>> callback
    ) {
        DescribeMoldModelMastersTask task = new DescribeMoldModelMastersTask(request, callback, DescribeMoldModelMastersResult.class);
        session.execute(task);
    }

    /**
     * フォームの保存領域マスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeMoldModelMastersResult describeMoldModelMasters(
            DescribeMoldModelMastersRequest request
    ) {
        final AsyncResult<DescribeMoldModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMoldModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateMoldModelMasterTask extends Gs2RestSessionTask<CreateMoldModelMasterResult> {
        private CreateMoldModelMasterRequest request;

        public CreateMoldModelMasterTask(
            CreateMoldModelMasterRequest request,
            AsyncAction<AsyncResult<CreateMoldModelMasterResult>> userCallback,
            Class<CreateMoldModelMasterResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/mold";

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
            if (this.request.getFormModelName() != null) {
                json.put("formModelName", this.request.getFormModelName());
            }
            if (this.request.getInitialMaxCapacity() != null) {
                json.put("initialMaxCapacity", this.request.getInitialMaxCapacity());
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
     * フォームの保存領域マスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createMoldModelMasterAsync(
            CreateMoldModelMasterRequest request,
            AsyncAction<AsyncResult<CreateMoldModelMasterResult>> callback
    ) {
        CreateMoldModelMasterTask task = new CreateMoldModelMasterTask(request, callback, CreateMoldModelMasterResult.class);
        session.execute(task);
    }

    /**
     * フォームの保存領域マスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateMoldModelMasterResult createMoldModelMaster(
            CreateMoldModelMasterRequest request
    ) {
        final AsyncResult<CreateMoldModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createMoldModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMoldModelMasterTask extends Gs2RestSessionTask<GetMoldModelMasterResult> {
        private GetMoldModelMasterRequest request;

        public GetMoldModelMasterTask(
            GetMoldModelMasterRequest request,
            AsyncAction<AsyncResult<GetMoldModelMasterResult>> userCallback,
            Class<GetMoldModelMasterResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/mold/{moldName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));

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
     * フォームの保存領域マスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getMoldModelMasterAsync(
            GetMoldModelMasterRequest request,
            AsyncAction<AsyncResult<GetMoldModelMasterResult>> callback
    ) {
        GetMoldModelMasterTask task = new GetMoldModelMasterTask(request, callback, GetMoldModelMasterResult.class);
        session.execute(task);
    }

    /**
     * フォームの保存領域マスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetMoldModelMasterResult getMoldModelMaster(
            GetMoldModelMasterRequest request
    ) {
        final AsyncResult<GetMoldModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMoldModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateMoldModelMasterTask extends Gs2RestSessionTask<UpdateMoldModelMasterResult> {
        private UpdateMoldModelMasterRequest request;

        public UpdateMoldModelMasterTask(
            UpdateMoldModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateMoldModelMasterResult>> userCallback,
            Class<UpdateMoldModelMasterResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/mold/{moldName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getFormModelName() != null) {
                json.put("formModelName", this.request.getFormModelName());
            }
            if (this.request.getInitialMaxCapacity() != null) {
                json.put("initialMaxCapacity", this.request.getInitialMaxCapacity());
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
     * フォームの保存領域マスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateMoldModelMasterAsync(
            UpdateMoldModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateMoldModelMasterResult>> callback
    ) {
        UpdateMoldModelMasterTask task = new UpdateMoldModelMasterTask(request, callback, UpdateMoldModelMasterResult.class);
        session.execute(task);
    }

    /**
     * フォームの保存領域マスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateMoldModelMasterResult updateMoldModelMaster(
            UpdateMoldModelMasterRequest request
    ) {
        final AsyncResult<UpdateMoldModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateMoldModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteMoldModelMasterTask extends Gs2RestSessionTask<DeleteMoldModelMasterResult> {
        private DeleteMoldModelMasterRequest request;

        public DeleteMoldModelMasterTask(
            DeleteMoldModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteMoldModelMasterResult>> userCallback,
            Class<DeleteMoldModelMasterResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/mold/{moldName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));

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
     * フォームの保存領域マスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteMoldModelMasterAsync(
            DeleteMoldModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteMoldModelMasterResult>> callback
    ) {
        DeleteMoldModelMasterTask task = new DeleteMoldModelMasterTask(request, callback, DeleteMoldModelMasterResult.class);
        session.execute(task);
    }

    /**
     * フォームの保存領域マスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteMoldModelMasterResult deleteMoldModelMaster(
            DeleteMoldModelMasterRequest request
    ) {
        final AsyncResult<DeleteMoldModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMoldModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "formation")
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
     * 現在有効なフォーム設定のマスターデータをエクスポートします<br>
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
     * 現在有効なフォーム設定のマスターデータをエクスポートします<br>
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

    class GetCurrentFormMasterTask extends Gs2RestSessionTask<GetCurrentFormMasterResult> {
        private GetCurrentFormMasterRequest request;

        public GetCurrentFormMasterTask(
            GetCurrentFormMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentFormMasterResult>> userCallback,
            Class<GetCurrentFormMasterResult> clazz
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
                .replace("{service}", "formation")
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
     * 現在有効なフォーム設定を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentFormMasterAsync(
            GetCurrentFormMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentFormMasterResult>> callback
    ) {
        GetCurrentFormMasterTask task = new GetCurrentFormMasterTask(request, callback, GetCurrentFormMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なフォーム設定を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCurrentFormMasterResult getCurrentFormMaster(
            GetCurrentFormMasterRequest request
    ) {
        final AsyncResult<GetCurrentFormMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentFormMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentFormMasterTask extends Gs2RestSessionTask<UpdateCurrentFormMasterResult> {
        private UpdateCurrentFormMasterRequest request;

        public UpdateCurrentFormMasterTask(
            UpdateCurrentFormMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentFormMasterResult>> userCallback,
            Class<UpdateCurrentFormMasterResult> clazz
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
                .replace("{service}", "formation")
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
     * 現在有効なフォーム設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentFormMasterAsync(
            UpdateCurrentFormMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentFormMasterResult>> callback
    ) {
        UpdateCurrentFormMasterTask task = new UpdateCurrentFormMasterTask(request, callback, UpdateCurrentFormMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なフォーム設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentFormMasterResult updateCurrentFormMaster(
            UpdateCurrentFormMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentFormMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentFormMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentFormMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentFormMasterFromGitHubResult> {
        private UpdateCurrentFormMasterFromGitHubRequest request;

        public UpdateCurrentFormMasterFromGitHubTask(
            UpdateCurrentFormMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentFormMasterFromGitHubResult>> userCallback,
            Class<UpdateCurrentFormMasterFromGitHubResult> clazz
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
                .replace("{service}", "formation")
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
     * 現在有効なフォーム設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentFormMasterFromGitHubAsync(
            UpdateCurrentFormMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentFormMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentFormMasterFromGitHubTask task = new UpdateCurrentFormMasterFromGitHubTask(request, callback, UpdateCurrentFormMasterFromGitHubResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なフォーム設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentFormMasterFromGitHubResult updateCurrentFormMasterFromGitHub(
            UpdateCurrentFormMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentFormMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentFormMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMoldsTask extends Gs2RestSessionTask<DescribeMoldsResult> {
        private DescribeMoldsRequest request;

        public DescribeMoldsTask(
            DescribeMoldsRequest request,
            AsyncAction<AsyncResult<DescribeMoldsResult>> userCallback,
            Class<DescribeMoldsResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold";

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
     * 保存したフォームの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeMoldsAsync(
            DescribeMoldsRequest request,
            AsyncAction<AsyncResult<DescribeMoldsResult>> callback
    ) {
        DescribeMoldsTask task = new DescribeMoldsTask(request, callback, DescribeMoldsResult.class);
        session.execute(task);
    }

    /**
     * 保存したフォームの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeMoldsResult describeMolds(
            DescribeMoldsRequest request
    ) {
        final AsyncResult<DescribeMoldsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMoldsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeMoldsByUserIdTask extends Gs2RestSessionTask<DescribeMoldsByUserIdResult> {
        private DescribeMoldsByUserIdRequest request;

        public DescribeMoldsByUserIdTask(
            DescribeMoldsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeMoldsByUserIdResult>> userCallback,
            Class<DescribeMoldsByUserIdResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold";

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
     * ユーザIDを指定して保存したフォームの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeMoldsByUserIdAsync(
            DescribeMoldsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeMoldsByUserIdResult>> callback
    ) {
        DescribeMoldsByUserIdTask task = new DescribeMoldsByUserIdTask(request, callback, DescribeMoldsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して保存したフォームの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeMoldsByUserIdResult describeMoldsByUserId(
            DescribeMoldsByUserIdRequest request
    ) {
        final AsyncResult<DescribeMoldsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeMoldsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMoldTask extends Gs2RestSessionTask<GetMoldResult> {
        private GetMoldRequest request;

        public GetMoldTask(
            GetMoldRequest request,
            AsyncAction<AsyncResult<GetMoldResult>> userCallback,
            Class<GetMoldResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));

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
     * 保存したフォームを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getMoldAsync(
            GetMoldRequest request,
            AsyncAction<AsyncResult<GetMoldResult>> callback
    ) {
        GetMoldTask task = new GetMoldTask(request, callback, GetMoldResult.class);
        session.execute(task);
    }

    /**
     * 保存したフォームを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetMoldResult getMold(
            GetMoldRequest request
    ) {
        final AsyncResult<GetMoldResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMoldAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetMoldByUserIdTask extends Gs2RestSessionTask<GetMoldByUserIdResult> {
        private GetMoldByUserIdRequest request;

        public GetMoldByUserIdTask(
            GetMoldByUserIdRequest request,
            AsyncAction<AsyncResult<GetMoldByUserIdResult>> userCallback,
            Class<GetMoldByUserIdResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));

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
     * ユーザIDを指定して保存したフォームを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getMoldByUserIdAsync(
            GetMoldByUserIdRequest request,
            AsyncAction<AsyncResult<GetMoldByUserIdResult>> callback
    ) {
        GetMoldByUserIdTask task = new GetMoldByUserIdTask(request, callback, GetMoldByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して保存したフォームを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetMoldByUserIdResult getMoldByUserId(
            GetMoldByUserIdRequest request
    ) {
        final AsyncResult<GetMoldByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getMoldByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetMoldCapacityByUserIdTask extends Gs2RestSessionTask<SetMoldCapacityByUserIdResult> {
        private SetMoldCapacityByUserIdRequest request;

        public SetMoldCapacityByUserIdTask(
            SetMoldCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<SetMoldCapacityByUserIdResult>> userCallback,
            Class<SetMoldCapacityByUserIdResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCapacity() != null) {
                json.put("capacity", this.request.getCapacity());
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
     * ユーザIDを指定して保存したフォームのキャパシティを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setMoldCapacityByUserIdAsync(
            SetMoldCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<SetMoldCapacityByUserIdResult>> callback
    ) {
        SetMoldCapacityByUserIdTask task = new SetMoldCapacityByUserIdTask(request, callback, SetMoldCapacityByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して保存したフォームのキャパシティを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public SetMoldCapacityByUserIdResult setMoldCapacityByUserId(
            SetMoldCapacityByUserIdRequest request
    ) {
        final AsyncResult<SetMoldCapacityByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setMoldCapacityByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddMoldCapacityByUserIdTask extends Gs2RestSessionTask<AddMoldCapacityByUserIdResult> {
        private AddMoldCapacityByUserIdRequest request;

        public AddMoldCapacityByUserIdTask(
            AddMoldCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<AddMoldCapacityByUserIdResult>> userCallback,
            Class<AddMoldCapacityByUserIdResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCapacity() != null) {
                json.put("capacity", this.request.getCapacity());
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
     * ユーザIDを指定して保存したフォームのキャパシティを追加<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void addMoldCapacityByUserIdAsync(
            AddMoldCapacityByUserIdRequest request,
            AsyncAction<AsyncResult<AddMoldCapacityByUserIdResult>> callback
    ) {
        AddMoldCapacityByUserIdTask task = new AddMoldCapacityByUserIdTask(request, callback, AddMoldCapacityByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して保存したフォームのキャパシティを追加<br>
     *
     * @param request リクエストパラメータ
     */
    public AddMoldCapacityByUserIdResult addMoldCapacityByUserId(
            AddMoldCapacityByUserIdRequest request
    ) {
        final AsyncResult<AddMoldCapacityByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        addMoldCapacityByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteMoldTask extends Gs2RestSessionTask<DeleteMoldResult> {
        private DeleteMoldRequest request;

        public DeleteMoldTask(
            DeleteMoldRequest request,
            AsyncAction<AsyncResult<DeleteMoldResult>> userCallback,
            Class<DeleteMoldResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));

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
     * 保存したフォームを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteMoldAsync(
            DeleteMoldRequest request,
            AsyncAction<AsyncResult<DeleteMoldResult>> callback
    ) {
        DeleteMoldTask task = new DeleteMoldTask(request, callback, DeleteMoldResult.class);
        session.execute(task);
    }

    /**
     * 保存したフォームを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteMoldResult deleteMold(
            DeleteMoldRequest request
    ) {
        final AsyncResult<DeleteMoldResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMoldAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteMoldByUserIdTask extends Gs2RestSessionTask<DeleteMoldByUserIdResult> {
        private DeleteMoldByUserIdRequest request;

        public DeleteMoldByUserIdTask(
            DeleteMoldByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteMoldByUserIdResult>> userCallback,
            Class<DeleteMoldByUserIdResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));

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
     * ユーザIDを指定して保存したフォームを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteMoldByUserIdAsync(
            DeleteMoldByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteMoldByUserIdResult>> callback
    ) {
        DeleteMoldByUserIdTask task = new DeleteMoldByUserIdTask(request, callback, DeleteMoldByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して保存したフォームを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteMoldByUserIdResult deleteMoldByUserId(
            DeleteMoldByUserIdRequest request
    ) {
        final AsyncResult<DeleteMoldByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteMoldByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/mold/capacity/add";

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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/mold/capacity/set";

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

    class DescribeFormsTask extends Gs2RestSessionTask<DescribeFormsResult> {
        private DescribeFormsRequest request;

        public DescribeFormsTask(
            DescribeFormsRequest request,
            AsyncAction<AsyncResult<DescribeFormsResult>> userCallback,
            Class<DescribeFormsResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldName}/form";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));

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
     * フォームの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeFormsAsync(
            DescribeFormsRequest request,
            AsyncAction<AsyncResult<DescribeFormsResult>> callback
    ) {
        DescribeFormsTask task = new DescribeFormsTask(request, callback, DescribeFormsResult.class);
        session.execute(task);
    }

    /**
     * フォームの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeFormsResult describeForms(
            DescribeFormsRequest request
    ) {
        final AsyncResult<DescribeFormsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeFormsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeFormsByUserIdTask extends Gs2RestSessionTask<DescribeFormsByUserIdResult> {
        private DescribeFormsByUserIdRequest request;

        public DescribeFormsByUserIdTask(
            DescribeFormsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeFormsByUserIdResult>> userCallback,
            Class<DescribeFormsByUserIdResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldName}/form";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));
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
     * ユーザIDを指定してフォームの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeFormsByUserIdAsync(
            DescribeFormsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeFormsByUserIdResult>> callback
    ) {
        DescribeFormsByUserIdTask task = new DescribeFormsByUserIdTask(request, callback, DescribeFormsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してフォームの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeFormsByUserIdResult describeFormsByUserId(
            DescribeFormsByUserIdRequest request
    ) {
        final AsyncResult<DescribeFormsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeFormsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFormTask extends Gs2RestSessionTask<GetFormResult> {
        private GetFormRequest request;

        public GetFormTask(
            GetFormRequest request,
            AsyncAction<AsyncResult<GetFormResult>> userCallback,
            Class<GetFormResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldName}/form/{index}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));
            url = url.replace("{index}", this.request.getIndex() == null ? "null" : String.valueOf(this.request.getIndex()));

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
     * フォームを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getFormAsync(
            GetFormRequest request,
            AsyncAction<AsyncResult<GetFormResult>> callback
    ) {
        GetFormTask task = new GetFormTask(request, callback, GetFormResult.class);
        session.execute(task);
    }

    /**
     * フォームを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetFormResult getForm(
            GetFormRequest request
    ) {
        final AsyncResult<GetFormResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFormAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFormByUserIdTask extends Gs2RestSessionTask<GetFormByUserIdResult> {
        private GetFormByUserIdRequest request;

        public GetFormByUserIdTask(
            GetFormByUserIdRequest request,
            AsyncAction<AsyncResult<GetFormByUserIdResult>> userCallback,
            Class<GetFormByUserIdResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldName}/form/{index}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));
            url = url.replace("{index}", this.request.getIndex() == null ? "null" : String.valueOf(this.request.getIndex()));

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
     * ユーザIDを指定してフォームを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getFormByUserIdAsync(
            GetFormByUserIdRequest request,
            AsyncAction<AsyncResult<GetFormByUserIdResult>> callback
    ) {
        GetFormByUserIdTask task = new GetFormByUserIdTask(request, callback, GetFormByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してフォームを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetFormByUserIdResult getFormByUserId(
            GetFormByUserIdRequest request
    ) {
        final AsyncResult<GetFormByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFormByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFormWithSignatureTask extends Gs2RestSessionTask<GetFormWithSignatureResult> {
        private GetFormWithSignatureRequest request;

        public GetFormWithSignatureTask(
            GetFormWithSignatureRequest request,
            AsyncAction<AsyncResult<GetFormWithSignatureResult>> userCallback,
            Class<GetFormWithSignatureResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldName}/form/{index}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));
            url = url.replace("{index}", this.request.getIndex() == null ? "null" : String.valueOf(this.request.getIndex()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getKeyId() != null) {
                queryStrings.add("keyId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getKeyId()))));
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
     * 署名付きフォームを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getFormWithSignatureAsync(
            GetFormWithSignatureRequest request,
            AsyncAction<AsyncResult<GetFormWithSignatureResult>> callback
    ) {
        GetFormWithSignatureTask task = new GetFormWithSignatureTask(request, callback, GetFormWithSignatureResult.class);
        session.execute(task);
    }

    /**
     * 署名付きフォームを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetFormWithSignatureResult getFormWithSignature(
            GetFormWithSignatureRequest request
    ) {
        final AsyncResult<GetFormWithSignatureResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFormWithSignatureAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFormWithSignatureByUserIdTask extends Gs2RestSessionTask<GetFormWithSignatureByUserIdResult> {
        private GetFormWithSignatureByUserIdRequest request;

        public GetFormWithSignatureByUserIdTask(
            GetFormWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetFormWithSignatureByUserIdResult>> userCallback,
            Class<GetFormWithSignatureByUserIdResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldName}/form/{index}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));
            url = url.replace("{index}", this.request.getIndex() == null ? "null" : String.valueOf(this.request.getIndex()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getKeyId() != null) {
                queryStrings.add("keyId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getKeyId()))));
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
     * ユーザIDを指定して署名付きフォームを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getFormWithSignatureByUserIdAsync(
            GetFormWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetFormWithSignatureByUserIdResult>> callback
    ) {
        GetFormWithSignatureByUserIdTask task = new GetFormWithSignatureByUserIdTask(request, callback, GetFormWithSignatureByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して署名付きフォームを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetFormWithSignatureByUserIdResult getFormWithSignatureByUserId(
            GetFormWithSignatureByUserIdRequest request
    ) {
        final AsyncResult<GetFormWithSignatureByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFormWithSignatureByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetFormByUserIdTask extends Gs2RestSessionTask<SetFormByUserIdResult> {
        private SetFormByUserIdRequest request;

        public SetFormByUserIdTask(
            SetFormByUserIdRequest request,
            AsyncAction<AsyncResult<SetFormByUserIdResult>> userCallback,
            Class<SetFormByUserIdResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldName}/form/{index}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));
            url = url.replace("{index}", this.request.getIndex() == null ? "null" : String.valueOf(this.request.getIndex()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getSlots() != null) {
                JSONArray array = new JSONArray();
                for(Slot item : this.request.getSlots())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("slots", array);
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
     * ユーザIDを指定してフォームを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setFormByUserIdAsync(
            SetFormByUserIdRequest request,
            AsyncAction<AsyncResult<SetFormByUserIdResult>> callback
    ) {
        SetFormByUserIdTask task = new SetFormByUserIdTask(request, callback, SetFormByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してフォームを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public SetFormByUserIdResult setFormByUserId(
            SetFormByUserIdRequest request
    ) {
        final AsyncResult<SetFormByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setFormByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetFormWithSignatureTask extends Gs2RestSessionTask<SetFormWithSignatureResult> {
        private SetFormWithSignatureRequest request;

        public SetFormWithSignatureTask(
            SetFormWithSignatureRequest request,
            AsyncAction<AsyncResult<SetFormWithSignatureResult>> userCallback,
            Class<SetFormWithSignatureResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldName}/form/{index}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));
            url = url.replace("{index}", this.request.getIndex() == null ? "null" : String.valueOf(this.request.getIndex()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getSlots() != null) {
                JSONArray array = new JSONArray();
                for(SlotWithSignature item : this.request.getSlots())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("slots", array);
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
     * 署名付きスロットを使ってフォームを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setFormWithSignatureAsync(
            SetFormWithSignatureRequest request,
            AsyncAction<AsyncResult<SetFormWithSignatureResult>> callback
    ) {
        SetFormWithSignatureTask task = new SetFormWithSignatureTask(request, callback, SetFormWithSignatureResult.class);
        session.execute(task);
    }

    /**
     * 署名付きスロットを使ってフォームを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public SetFormWithSignatureResult setFormWithSignature(
            SetFormWithSignatureRequest request
    ) {
        final AsyncResult<SetFormWithSignatureResult>[] resultAsyncResult = new AsyncResult[]{null};
        setFormWithSignatureAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquireActionsToFormPropertiesTask extends Gs2RestSessionTask<AcquireActionsToFormPropertiesResult> {
        private AcquireActionsToFormPropertiesRequest request;

        public AcquireActionsToFormPropertiesTask(
            AcquireActionsToFormPropertiesRequest request,
            AsyncAction<AsyncResult<AcquireActionsToFormPropertiesResult>> userCallback,
            Class<AcquireActionsToFormPropertiesResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldName}/form/{index}/stamp/delegate";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));
            url = url.replace("{index}", this.request.getIndex() == null ? "null" : String.valueOf(this.request.getIndex()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getAcquireAction() != null) {
                try {
                    json.put("acquireAction", new JSONObject(mapper.writeValueAsString(this.request.getAcquireAction())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getQueueNamespaceId() != null) {
                json.put("queueNamespaceId", this.request.getQueueNamespaceId());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getConfig() != null) {
                JSONArray array = new JSONArray();
                for(AcquireActionConfig item : this.request.getConfig())
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
     * 署名付きスロットを使ってフォームを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void acquireActionsToFormPropertiesAsync(
            AcquireActionsToFormPropertiesRequest request,
            AsyncAction<AsyncResult<AcquireActionsToFormPropertiesResult>> callback
    ) {
        AcquireActionsToFormPropertiesTask task = new AcquireActionsToFormPropertiesTask(request, callback, AcquireActionsToFormPropertiesResult.class);
        session.execute(task);
    }

    /**
     * 署名付きスロットを使ってフォームを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public AcquireActionsToFormPropertiesResult acquireActionsToFormProperties(
            AcquireActionsToFormPropertiesRequest request
    ) {
        final AsyncResult<AcquireActionsToFormPropertiesResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireActionsToFormPropertiesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteFormTask extends Gs2RestSessionTask<DeleteFormResult> {
        private DeleteFormRequest request;

        public DeleteFormTask(
            DeleteFormRequest request,
            AsyncAction<AsyncResult<DeleteFormResult>> userCallback,
            Class<DeleteFormResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/mold/{moldName}/form/{index}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));
            url = url.replace("{index}", this.request.getIndex() == null ? "null" : String.valueOf(this.request.getIndex()));

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
     * フォームを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteFormAsync(
            DeleteFormRequest request,
            AsyncAction<AsyncResult<DeleteFormResult>> callback
    ) {
        DeleteFormTask task = new DeleteFormTask(request, callback, DeleteFormResult.class);
        session.execute(task);
    }

    /**
     * フォームを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteFormResult deleteForm(
            DeleteFormRequest request
    ) {
        final AsyncResult<DeleteFormResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteFormAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteFormByUserIdTask extends Gs2RestSessionTask<DeleteFormByUserIdResult> {
        private DeleteFormByUserIdRequest request;

        public DeleteFormByUserIdTask(
            DeleteFormByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteFormByUserIdResult>> userCallback,
            Class<DeleteFormByUserIdResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/mold/{moldName}/form/{index}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{moldName}", this.request.getMoldName() == null|| this.request.getMoldName().length() == 0 ? "null" : String.valueOf(this.request.getMoldName()));
            url = url.replace("{index}", this.request.getIndex() == null ? "null" : String.valueOf(this.request.getIndex()));

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
     * ユーザIDを指定してフォームを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteFormByUserIdAsync(
            DeleteFormByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteFormByUserIdResult>> callback
    ) {
        DeleteFormByUserIdTask task = new DeleteFormByUserIdTask(request, callback, DeleteFormByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してフォームを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteFormByUserIdResult deleteFormByUserId(
            DeleteFormByUserIdRequest request
    ) {
        final AsyncResult<DeleteFormByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteFormByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcquireActionToFormPropertiesByStampSheetTask extends Gs2RestSessionTask<AcquireActionToFormPropertiesByStampSheetResult> {
        private AcquireActionToFormPropertiesByStampSheetRequest request;

        public AcquireActionToFormPropertiesByStampSheetTask(
            AcquireActionToFormPropertiesByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireActionToFormPropertiesByStampSheetResult>> userCallback,
            Class<AcquireActionToFormPropertiesByStampSheetResult> clazz
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
                .replace("{service}", "formation")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/form/acquire";

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
    public void acquireActionToFormPropertiesByStampSheetAsync(
            AcquireActionToFormPropertiesByStampSheetRequest request,
            AsyncAction<AsyncResult<AcquireActionToFormPropertiesByStampSheetResult>> callback
    ) {
        AcquireActionToFormPropertiesByStampSheetTask task = new AcquireActionToFormPropertiesByStampSheetTask(request, callback, AcquireActionToFormPropertiesByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートでアイテムをインベントリに追加<br>
     *
     * @param request リクエストパラメータ
     */
    public AcquireActionToFormPropertiesByStampSheetResult acquireActionToFormPropertiesByStampSheet(
            AcquireActionToFormPropertiesByStampSheetRequest request
    ) {
        final AsyncResult<AcquireActionToFormPropertiesByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        acquireActionToFormPropertiesByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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