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

package io.gs2.version;

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
import io.gs2.version.request.*;
import io.gs2.version.result.*;
import io.gs2.version.model.*;

/**
 * GS2 Version API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2VersionRestClient extends AbstractGs2Client<Gs2VersionRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2VersionRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "version")
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
                .replace("{service}", "version")
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
            if (this.request.getAssumeUserId() != null) {
                json.put("assumeUserId", this.request.getAssumeUserId());
            }
            if (this.request.getAcceptVersionScript() != null) {
                try {
                    json.put("acceptVersionScript", new JSONObject(mapper.writeValueAsString(this.request.getAcceptVersionScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getCheckVersionTriggerScriptId() != null) {
                json.put("checkVersionTriggerScriptId", this.request.getCheckVersionTriggerScriptId());
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
                .replace("{service}", "version")
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
                .replace("{service}", "version")
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getAssumeUserId() != null) {
                json.put("assumeUserId", this.request.getAssumeUserId());
            }
            if (this.request.getAcceptVersionScript() != null) {
                try {
                    json.put("acceptVersionScript", new JSONObject(mapper.writeValueAsString(this.request.getAcceptVersionScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getCheckVersionTriggerScriptId() != null) {
                json.put("checkVersionTriggerScriptId", this.request.getCheckVersionTriggerScriptId());
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
                .replace("{service}", "version")
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

    class DescribeVersionModelMastersTask extends Gs2RestSessionTask<DescribeVersionModelMastersResult> {
        private DescribeVersionModelMastersRequest request;

        public DescribeVersionModelMastersTask(
            DescribeVersionModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeVersionModelMastersResult>> userCallback,
            Class<DescribeVersionModelMastersResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/version";

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
     * バージョンマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeVersionModelMastersAsync(
            DescribeVersionModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeVersionModelMastersResult>> callback
    ) {
        DescribeVersionModelMastersTask task = new DescribeVersionModelMastersTask(request, callback, DescribeVersionModelMastersResult.class);
        session.execute(task);
    }

    /**
     * バージョンマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeVersionModelMastersResult describeVersionModelMasters(
            DescribeVersionModelMastersRequest request
    ) {
        final AsyncResult<DescribeVersionModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeVersionModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateVersionModelMasterTask extends Gs2RestSessionTask<CreateVersionModelMasterResult> {
        private CreateVersionModelMasterRequest request;

        public CreateVersionModelMasterTask(
            CreateVersionModelMasterRequest request,
            AsyncAction<AsyncResult<CreateVersionModelMasterResult>> userCallback,
            Class<CreateVersionModelMasterResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/version";

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
            if (this.request.getWarningVersion() != null) {
                try {
                    json.put("warningVersion", new JSONObject(mapper.writeValueAsString(this.request.getWarningVersion())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getErrorVersion() != null) {
                try {
                    json.put("errorVersion", new JSONObject(mapper.writeValueAsString(this.request.getErrorVersion())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getScope() != null) {
                json.put("scope", this.request.getScope());
            }
            if (this.request.getCurrentVersion() != null) {
                try {
                    json.put("currentVersion", new JSONObject(mapper.writeValueAsString(this.request.getCurrentVersion())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getNeedSignature() != null) {
                json.put("needSignature", this.request.getNeedSignature());
            }
            if (this.request.getSignatureKeyId() != null) {
                json.put("signatureKeyId", this.request.getSignatureKeyId());
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
     * バージョンマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createVersionModelMasterAsync(
            CreateVersionModelMasterRequest request,
            AsyncAction<AsyncResult<CreateVersionModelMasterResult>> callback
    ) {
        CreateVersionModelMasterTask task = new CreateVersionModelMasterTask(request, callback, CreateVersionModelMasterResult.class);
        session.execute(task);
    }

    /**
     * バージョンマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateVersionModelMasterResult createVersionModelMaster(
            CreateVersionModelMasterRequest request
    ) {
        final AsyncResult<CreateVersionModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createVersionModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetVersionModelMasterTask extends Gs2RestSessionTask<GetVersionModelMasterResult> {
        private GetVersionModelMasterRequest request;

        public GetVersionModelMasterTask(
            GetVersionModelMasterRequest request,
            AsyncAction<AsyncResult<GetVersionModelMasterResult>> userCallback,
            Class<GetVersionModelMasterResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/version/{versionName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{versionName}", this.request.getVersionName() == null|| this.request.getVersionName().length() == 0 ? "null" : String.valueOf(this.request.getVersionName()));

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
     * バージョンマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getVersionModelMasterAsync(
            GetVersionModelMasterRequest request,
            AsyncAction<AsyncResult<GetVersionModelMasterResult>> callback
    ) {
        GetVersionModelMasterTask task = new GetVersionModelMasterTask(request, callback, GetVersionModelMasterResult.class);
        session.execute(task);
    }

    /**
     * バージョンマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetVersionModelMasterResult getVersionModelMaster(
            GetVersionModelMasterRequest request
    ) {
        final AsyncResult<GetVersionModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getVersionModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateVersionModelMasterTask extends Gs2RestSessionTask<UpdateVersionModelMasterResult> {
        private UpdateVersionModelMasterRequest request;

        public UpdateVersionModelMasterTask(
            UpdateVersionModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateVersionModelMasterResult>> userCallback,
            Class<UpdateVersionModelMasterResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/version/{versionName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{versionName}", this.request.getVersionName() == null|| this.request.getVersionName().length() == 0 ? "null" : String.valueOf(this.request.getVersionName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getWarningVersion() != null) {
                try {
                    json.put("warningVersion", new JSONObject(mapper.writeValueAsString(this.request.getWarningVersion())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getErrorVersion() != null) {
                try {
                    json.put("errorVersion", new JSONObject(mapper.writeValueAsString(this.request.getErrorVersion())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getScope() != null) {
                json.put("scope", this.request.getScope());
            }
            if (this.request.getCurrentVersion() != null) {
                try {
                    json.put("currentVersion", new JSONObject(mapper.writeValueAsString(this.request.getCurrentVersion())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getNeedSignature() != null) {
                json.put("needSignature", this.request.getNeedSignature());
            }
            if (this.request.getSignatureKeyId() != null) {
                json.put("signatureKeyId", this.request.getSignatureKeyId());
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
     * バージョンマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateVersionModelMasterAsync(
            UpdateVersionModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateVersionModelMasterResult>> callback
    ) {
        UpdateVersionModelMasterTask task = new UpdateVersionModelMasterTask(request, callback, UpdateVersionModelMasterResult.class);
        session.execute(task);
    }

    /**
     * バージョンマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateVersionModelMasterResult updateVersionModelMaster(
            UpdateVersionModelMasterRequest request
    ) {
        final AsyncResult<UpdateVersionModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateVersionModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteVersionModelMasterTask extends Gs2RestSessionTask<DeleteVersionModelMasterResult> {
        private DeleteVersionModelMasterRequest request;

        public DeleteVersionModelMasterTask(
            DeleteVersionModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteVersionModelMasterResult>> userCallback,
            Class<DeleteVersionModelMasterResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/version/{versionName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{versionName}", this.request.getVersionName() == null|| this.request.getVersionName().length() == 0 ? "null" : String.valueOf(this.request.getVersionName()));

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
     * バージョンマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteVersionModelMasterAsync(
            DeleteVersionModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteVersionModelMasterResult>> callback
    ) {
        DeleteVersionModelMasterTask task = new DeleteVersionModelMasterTask(request, callback, DeleteVersionModelMasterResult.class);
        session.execute(task);
    }

    /**
     * バージョンマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteVersionModelMasterResult deleteVersionModelMaster(
            DeleteVersionModelMasterRequest request
    ) {
        final AsyncResult<DeleteVersionModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteVersionModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeVersionModelsTask extends Gs2RestSessionTask<DescribeVersionModelsResult> {
        private DescribeVersionModelsRequest request;

        public DescribeVersionModelsTask(
            DescribeVersionModelsRequest request,
            AsyncAction<AsyncResult<DescribeVersionModelsResult>> userCallback,
            Class<DescribeVersionModelsResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/version";

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
     * バージョン設定の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeVersionModelsAsync(
            DescribeVersionModelsRequest request,
            AsyncAction<AsyncResult<DescribeVersionModelsResult>> callback
    ) {
        DescribeVersionModelsTask task = new DescribeVersionModelsTask(request, callback, DescribeVersionModelsResult.class);
        session.execute(task);
    }

    /**
     * バージョン設定の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeVersionModelsResult describeVersionModels(
            DescribeVersionModelsRequest request
    ) {
        final AsyncResult<DescribeVersionModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeVersionModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetVersionModelTask extends Gs2RestSessionTask<GetVersionModelResult> {
        private GetVersionModelRequest request;

        public GetVersionModelTask(
            GetVersionModelRequest request,
            AsyncAction<AsyncResult<GetVersionModelResult>> userCallback,
            Class<GetVersionModelResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/version/{versionName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{versionName}", this.request.getVersionName() == null|| this.request.getVersionName().length() == 0 ? "null" : String.valueOf(this.request.getVersionName()));

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
     * バージョン設定を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getVersionModelAsync(
            GetVersionModelRequest request,
            AsyncAction<AsyncResult<GetVersionModelResult>> callback
    ) {
        GetVersionModelTask task = new GetVersionModelTask(request, callback, GetVersionModelResult.class);
        session.execute(task);
    }

    /**
     * バージョン設定を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetVersionModelResult getVersionModel(
            GetVersionModelRequest request
    ) {
        final AsyncResult<GetVersionModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getVersionModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeAcceptVersionsTask extends Gs2RestSessionTask<DescribeAcceptVersionsResult> {
        private DescribeAcceptVersionsRequest request;

        public DescribeAcceptVersionsTask(
            DescribeAcceptVersionsRequest request,
            AsyncAction<AsyncResult<DescribeAcceptVersionsResult>> userCallback,
            Class<DescribeAcceptVersionsResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/acceptVersion";

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
     * 承認したバージョンの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeAcceptVersionsAsync(
            DescribeAcceptVersionsRequest request,
            AsyncAction<AsyncResult<DescribeAcceptVersionsResult>> callback
    ) {
        DescribeAcceptVersionsTask task = new DescribeAcceptVersionsTask(request, callback, DescribeAcceptVersionsResult.class);
        session.execute(task);
    }

    /**
     * 承認したバージョンの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeAcceptVersionsResult describeAcceptVersions(
            DescribeAcceptVersionsRequest request
    ) {
        final AsyncResult<DescribeAcceptVersionsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeAcceptVersionsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeAcceptVersionsByUserIdTask extends Gs2RestSessionTask<DescribeAcceptVersionsByUserIdResult> {
        private DescribeAcceptVersionsByUserIdRequest request;

        public DescribeAcceptVersionsByUserIdTask(
            DescribeAcceptVersionsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeAcceptVersionsByUserIdResult>> userCallback,
            Class<DescribeAcceptVersionsByUserIdResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/acceptVersion";

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
     * 承認したバージョンの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeAcceptVersionsByUserIdAsync(
            DescribeAcceptVersionsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeAcceptVersionsByUserIdResult>> callback
    ) {
        DescribeAcceptVersionsByUserIdTask task = new DescribeAcceptVersionsByUserIdTask(request, callback, DescribeAcceptVersionsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 承認したバージョンの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeAcceptVersionsByUserIdResult describeAcceptVersionsByUserId(
            DescribeAcceptVersionsByUserIdRequest request
    ) {
        final AsyncResult<DescribeAcceptVersionsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeAcceptVersionsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcceptTask extends Gs2RestSessionTask<AcceptResult> {
        private AcceptRequest request;

        public AcceptTask(
            AcceptRequest request,
            AsyncAction<AsyncResult<AcceptResult>> userCallback,
            Class<AcceptResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/acceptVersion";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getVersionName() != null) {
                json.put("versionName", this.request.getVersionName());
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
     * 現在のバージョンを承認<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void acceptAsync(
            AcceptRequest request,
            AsyncAction<AsyncResult<AcceptResult>> callback
    ) {
        AcceptTask task = new AcceptTask(request, callback, AcceptResult.class);
        session.execute(task);
    }

    /**
     * 現在のバージョンを承認<br>
     *
     * @param request リクエストパラメータ
     */
    public AcceptResult accept(
            AcceptRequest request
    ) {
        final AsyncResult<AcceptResult>[] resultAsyncResult = new AsyncResult[]{null};
        acceptAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AcceptByUserIdTask extends Gs2RestSessionTask<AcceptByUserIdResult> {
        private AcceptByUserIdRequest request;

        public AcceptByUserIdTask(
            AcceptByUserIdRequest request,
            AsyncAction<AsyncResult<AcceptByUserIdResult>> userCallback,
            Class<AcceptByUserIdResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/acceptVersion";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getVersionName() != null) {
                json.put("versionName", this.request.getVersionName());
            }
            if (this.request.getUserId() != null) {
                json.put("userId", this.request.getUserId());
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
     * ユーザIDを指定して現在のバージョンを承認<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void acceptByUserIdAsync(
            AcceptByUserIdRequest request,
            AsyncAction<AsyncResult<AcceptByUserIdResult>> callback
    ) {
        AcceptByUserIdTask task = new AcceptByUserIdTask(request, callback, AcceptByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して現在のバージョンを承認<br>
     *
     * @param request リクエストパラメータ
     */
    public AcceptByUserIdResult acceptByUserId(
            AcceptByUserIdRequest request
    ) {
        final AsyncResult<AcceptByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        acceptByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetAcceptVersionTask extends Gs2RestSessionTask<GetAcceptVersionResult> {
        private GetAcceptVersionRequest request;

        public GetAcceptVersionTask(
            GetAcceptVersionRequest request,
            AsyncAction<AsyncResult<GetAcceptVersionResult>> userCallback,
            Class<GetAcceptVersionResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/{versionName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{versionName}", this.request.getVersionName() == null|| this.request.getVersionName().length() == 0 ? "null" : String.valueOf(this.request.getVersionName()));

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
     * 承認したバージョンを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getAcceptVersionAsync(
            GetAcceptVersionRequest request,
            AsyncAction<AsyncResult<GetAcceptVersionResult>> callback
    ) {
        GetAcceptVersionTask task = new GetAcceptVersionTask(request, callback, GetAcceptVersionResult.class);
        session.execute(task);
    }

    /**
     * 承認したバージョンを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetAcceptVersionResult getAcceptVersion(
            GetAcceptVersionRequest request
    ) {
        final AsyncResult<GetAcceptVersionResult>[] resultAsyncResult = new AsyncResult[]{null};
        getAcceptVersionAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetAcceptVersionByUserIdTask extends Gs2RestSessionTask<GetAcceptVersionByUserIdResult> {
        private GetAcceptVersionByUserIdRequest request;

        public GetAcceptVersionByUserIdTask(
            GetAcceptVersionByUserIdRequest request,
            AsyncAction<AsyncResult<GetAcceptVersionByUserIdResult>> userCallback,
            Class<GetAcceptVersionByUserIdResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/{versionName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{versionName}", this.request.getVersionName() == null|| this.request.getVersionName().length() == 0 ? "null" : String.valueOf(this.request.getVersionName()));

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
     * ユーザーIDを指定して承認したバージョンを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getAcceptVersionByUserIdAsync(
            GetAcceptVersionByUserIdRequest request,
            AsyncAction<AsyncResult<GetAcceptVersionByUserIdResult>> callback
    ) {
        GetAcceptVersionByUserIdTask task = new GetAcceptVersionByUserIdTask(request, callback, GetAcceptVersionByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定して承認したバージョンを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetAcceptVersionByUserIdResult getAcceptVersionByUserId(
            GetAcceptVersionByUserIdRequest request
    ) {
        final AsyncResult<GetAcceptVersionByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getAcceptVersionByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteAcceptVersionTask extends Gs2RestSessionTask<DeleteAcceptVersionResult> {
        private DeleteAcceptVersionRequest request;

        public DeleteAcceptVersionTask(
            DeleteAcceptVersionRequest request,
            AsyncAction<AsyncResult<DeleteAcceptVersionResult>> userCallback,
            Class<DeleteAcceptVersionResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/{versionName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{versionName}", this.request.getVersionName() == null|| this.request.getVersionName().length() == 0 ? "null" : String.valueOf(this.request.getVersionName()));

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
     * 承認したバージョンを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteAcceptVersionAsync(
            DeleteAcceptVersionRequest request,
            AsyncAction<AsyncResult<DeleteAcceptVersionResult>> callback
    ) {
        DeleteAcceptVersionTask task = new DeleteAcceptVersionTask(request, callback, DeleteAcceptVersionResult.class);
        session.execute(task);
    }

    /**
     * 承認したバージョンを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteAcceptVersionResult deleteAcceptVersion(
            DeleteAcceptVersionRequest request
    ) {
        final AsyncResult<DeleteAcceptVersionResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteAcceptVersionAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteAcceptVersionByUserIdTask extends Gs2RestSessionTask<DeleteAcceptVersionByUserIdResult> {
        private DeleteAcceptVersionByUserIdRequest request;

        public DeleteAcceptVersionByUserIdTask(
            DeleteAcceptVersionByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteAcceptVersionByUserIdResult>> userCallback,
            Class<DeleteAcceptVersionByUserIdResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/{versionName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{versionName}", this.request.getVersionName() == null|| this.request.getVersionName().length() == 0 ? "null" : String.valueOf(this.request.getVersionName()));

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
     * ユーザーIDを指定して承認したバージョンを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteAcceptVersionByUserIdAsync(
            DeleteAcceptVersionByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteAcceptVersionByUserIdResult>> callback
    ) {
        DeleteAcceptVersionByUserIdTask task = new DeleteAcceptVersionByUserIdTask(request, callback, DeleteAcceptVersionByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定して承認したバージョンを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteAcceptVersionByUserIdResult deleteAcceptVersionByUserId(
            DeleteAcceptVersionByUserIdRequest request
    ) {
        final AsyncResult<DeleteAcceptVersionByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteAcceptVersionByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CheckVersionTask extends Gs2RestSessionTask<CheckVersionResult> {
        private CheckVersionRequest request;

        public CheckVersionTask(
            CheckVersionRequest request,
            AsyncAction<AsyncResult<CheckVersionResult>> userCallback,
            Class<CheckVersionResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/check";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getTargetVersions() != null) {
                JSONArray array = new JSONArray();
                for(TargetVersion item : this.request.getTargetVersions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("targetVersions", array);
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
     * バージョンチェック<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void checkVersionAsync(
            CheckVersionRequest request,
            AsyncAction<AsyncResult<CheckVersionResult>> callback
    ) {
        CheckVersionTask task = new CheckVersionTask(request, callback, CheckVersionResult.class);
        session.execute(task);
    }

    /**
     * バージョンチェック<br>
     *
     * @param request リクエストパラメータ
     */
    public CheckVersionResult checkVersion(
            CheckVersionRequest request
    ) {
        final AsyncResult<CheckVersionResult>[] resultAsyncResult = new AsyncResult[]{null};
        checkVersionAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CheckVersionByUserIdTask extends Gs2RestSessionTask<CheckVersionByUserIdResult> {
        private CheckVersionByUserIdRequest request;

        public CheckVersionByUserIdTask(
            CheckVersionByUserIdRequest request,
            AsyncAction<AsyncResult<CheckVersionByUserIdResult>> userCallback,
            Class<CheckVersionByUserIdResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/check";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getTargetVersions() != null) {
                JSONArray array = new JSONArray();
                for(TargetVersion item : this.request.getTargetVersions())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("targetVersions", array);
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
     * バージョンチェック<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void checkVersionByUserIdAsync(
            CheckVersionByUserIdRequest request,
            AsyncAction<AsyncResult<CheckVersionByUserIdResult>> callback
    ) {
        CheckVersionByUserIdTask task = new CheckVersionByUserIdTask(request, callback, CheckVersionByUserIdResult.class);
        session.execute(task);
    }

    /**
     * バージョンチェック<br>
     *
     * @param request リクエストパラメータ
     */
    public CheckVersionByUserIdResult checkVersionByUserId(
            CheckVersionByUserIdRequest request
    ) {
        final AsyncResult<CheckVersionByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        checkVersionByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CalculateSignatureTask extends Gs2RestSessionTask<CalculateSignatureResult> {
        private CalculateSignatureRequest request;

        public CalculateSignatureTask(
            CalculateSignatureRequest request,
            AsyncAction<AsyncResult<CalculateSignatureResult>> userCallback,
            Class<CalculateSignatureResult> clazz
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
                .replace("{service}", "version")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/version/{versionName}/calculate/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{versionName}", this.request.getVersionName() == null|| this.request.getVersionName().length() == 0 ? "null" : String.valueOf(this.request.getVersionName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getVersion() != null) {
                try {
                    json.put("version", new JSONObject(mapper.writeValueAsString(this.request.getVersion())));
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
     * スタンプシートのタスクを実行する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void calculateSignatureAsync(
            CalculateSignatureRequest request,
            AsyncAction<AsyncResult<CalculateSignatureResult>> callback
    ) {
        CalculateSignatureTask task = new CalculateSignatureTask(request, callback, CalculateSignatureResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートのタスクを実行する<br>
     *
     * @param request リクエストパラメータ
     */
    public CalculateSignatureResult calculateSignature(
            CalculateSignatureRequest request
    ) {
        final AsyncResult<CalculateSignatureResult>[] resultAsyncResult = new AsyncResult[]{null};
        calculateSignatureAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "version")
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
     * 現在有効なバージョンのマスターデータをエクスポートします<br>
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
     * 現在有効なバージョンのマスターデータをエクスポートします<br>
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

    class GetCurrentVersionMasterTask extends Gs2RestSessionTask<GetCurrentVersionMasterResult> {
        private GetCurrentVersionMasterRequest request;

        public GetCurrentVersionMasterTask(
            GetCurrentVersionMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentVersionMasterResult>> userCallback,
            Class<GetCurrentVersionMasterResult> clazz
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
                .replace("{service}", "version")
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
     * 現在有効なバージョンを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentVersionMasterAsync(
            GetCurrentVersionMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentVersionMasterResult>> callback
    ) {
        GetCurrentVersionMasterTask task = new GetCurrentVersionMasterTask(request, callback, GetCurrentVersionMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なバージョンを取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCurrentVersionMasterResult getCurrentVersionMaster(
            GetCurrentVersionMasterRequest request
    ) {
        final AsyncResult<GetCurrentVersionMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentVersionMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentVersionMasterTask extends Gs2RestSessionTask<UpdateCurrentVersionMasterResult> {
        private UpdateCurrentVersionMasterRequest request;

        public UpdateCurrentVersionMasterTask(
            UpdateCurrentVersionMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentVersionMasterResult>> userCallback,
            Class<UpdateCurrentVersionMasterResult> clazz
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
                .replace("{service}", "version")
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
     * 現在有効なバージョンを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentVersionMasterAsync(
            UpdateCurrentVersionMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentVersionMasterResult>> callback
    ) {
        UpdateCurrentVersionMasterTask task = new UpdateCurrentVersionMasterTask(request, callback, UpdateCurrentVersionMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なバージョンを更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentVersionMasterResult updateCurrentVersionMaster(
            UpdateCurrentVersionMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentVersionMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentVersionMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentVersionMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentVersionMasterFromGitHubResult> {
        private UpdateCurrentVersionMasterFromGitHubRequest request;

        public UpdateCurrentVersionMasterFromGitHubTask(
            UpdateCurrentVersionMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentVersionMasterFromGitHubResult>> userCallback,
            Class<UpdateCurrentVersionMasterFromGitHubResult> clazz
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
                .replace("{service}", "version")
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
     * 現在有効なバージョンを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentVersionMasterFromGitHubAsync(
            UpdateCurrentVersionMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentVersionMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentVersionMasterFromGitHubTask task = new UpdateCurrentVersionMasterFromGitHubTask(request, callback, UpdateCurrentVersionMasterFromGitHubResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なバージョンを更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentVersionMasterFromGitHubResult updateCurrentVersionMasterFromGitHub(
            UpdateCurrentVersionMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentVersionMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentVersionMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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