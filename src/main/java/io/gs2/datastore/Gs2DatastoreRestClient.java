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

package io.gs2.datastore;

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
import io.gs2.datastore.request.*;
import io.gs2.datastore.result.*;
import io.gs2.datastore.model.*;

/**
 * GS2 Datastore API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2DatastoreRestClient extends AbstractGs2Client<Gs2DatastoreRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2DatastoreRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "datastore")
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
                .replace("{service}", "datastore")
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
            if (this.request.getLogSetting() != null) {
                try {
                    json.put("logSetting", new JSONObject(mapper.writeValueAsString(this.request.getLogSetting())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getDoneUploadScript() != null) {
                try {
                    json.put("doneUploadScript", new JSONObject(mapper.writeValueAsString(this.request.getDoneUploadScript())));
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
                .replace("{service}", "datastore")
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
                .replace("{service}", "datastore")
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getLogSetting() != null) {
                try {
                    json.put("logSetting", new JSONObject(mapper.writeValueAsString(this.request.getLogSetting())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getDoneUploadScript() != null) {
                try {
                    json.put("doneUploadScript", new JSONObject(mapper.writeValueAsString(this.request.getDoneUploadScript())));
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
                .replace("{service}", "datastore")
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

    class DescribeDataObjectsTask extends Gs2RestSessionTask<DescribeDataObjectsResult> {
        private DescribeDataObjectsRequest request;

        public DescribeDataObjectsTask(
            DescribeDataObjectsRequest request,
            AsyncAction<AsyncResult<DescribeDataObjectsResult>> userCallback,
            Class<DescribeDataObjectsResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/data";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getStatus() != null) {
                queryStrings.add("status=" + EncodingUtil.urlEncode((String.valueOf(this.request.getStatus()))));
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
     * データオブジェクトの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeDataObjectsAsync(
            DescribeDataObjectsRequest request,
            AsyncAction<AsyncResult<DescribeDataObjectsResult>> callback
    ) {
        DescribeDataObjectsTask task = new DescribeDataObjectsTask(request, callback, DescribeDataObjectsResult.class);
        session.execute(task);
    }

    /**
     * データオブジェクトの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeDataObjectsResult describeDataObjects(
            DescribeDataObjectsRequest request
    ) {
        final AsyncResult<DescribeDataObjectsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeDataObjectsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeDataObjectsByUserIdTask extends Gs2RestSessionTask<DescribeDataObjectsByUserIdResult> {
        private DescribeDataObjectsByUserIdRequest request;

        public DescribeDataObjectsByUserIdTask(
            DescribeDataObjectsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeDataObjectsByUserIdResult>> userCallback,
            Class<DescribeDataObjectsByUserIdResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/data";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getStatus() != null) {
                queryStrings.add("status=" + EncodingUtil.urlEncode((String.valueOf(this.request.getStatus()))));
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
     * オーナーIDを指定してデータオブジェクトの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeDataObjectsByUserIdAsync(
            DescribeDataObjectsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeDataObjectsByUserIdResult>> callback
    ) {
        DescribeDataObjectsByUserIdTask task = new DescribeDataObjectsByUserIdTask(request, callback, DescribeDataObjectsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * オーナーIDを指定してデータオブジェクトの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeDataObjectsByUserIdResult describeDataObjectsByUserId(
            DescribeDataObjectsByUserIdRequest request
    ) {
        final AsyncResult<DescribeDataObjectsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeDataObjectsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PrepareUploadTask extends Gs2RestSessionTask<PrepareUploadResult> {
        private PrepareUploadRequest request;

        public PrepareUploadTask(
            PrepareUploadRequest request,
            AsyncAction<AsyncResult<PrepareUploadResult>> userCallback,
            Class<PrepareUploadResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/data/file";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getContentType() != null) {
                json.put("contentType", this.request.getContentType());
            }
            if (this.request.getScope() != null) {
                json.put("scope", this.request.getScope());
            }
            if (this.request.getAllowUserIds() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getAllowUserIds())
                {
                    array.put(item);
                }
                json.put("allowUserIds", array);
            }
            if (this.request.getUpdateIfExists() != null) {
                json.put("updateIfExists", this.request.getUpdateIfExists());
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
     * データオブジェクトをアップロード準備する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void prepareUploadAsync(
            PrepareUploadRequest request,
            AsyncAction<AsyncResult<PrepareUploadResult>> callback
    ) {
        PrepareUploadTask task = new PrepareUploadTask(request, callback, PrepareUploadResult.class);
        session.execute(task);
    }

    /**
     * データオブジェクトをアップロード準備する<br>
     *
     * @param request リクエストパラメータ
     */
    public PrepareUploadResult prepareUpload(
            PrepareUploadRequest request
    ) {
        final AsyncResult<PrepareUploadResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareUploadAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PrepareUploadByUserIdTask extends Gs2RestSessionTask<PrepareUploadByUserIdResult> {
        private PrepareUploadByUserIdRequest request;

        public PrepareUploadByUserIdTask(
            PrepareUploadByUserIdRequest request,
            AsyncAction<AsyncResult<PrepareUploadByUserIdResult>> userCallback,
            Class<PrepareUploadByUserIdResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/data/file";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getContentType() != null) {
                json.put("contentType", this.request.getContentType());
            }
            if (this.request.getScope() != null) {
                json.put("scope", this.request.getScope());
            }
            if (this.request.getAllowUserIds() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getAllowUserIds())
                {
                    array.put(item);
                }
                json.put("allowUserIds", array);
            }
            if (this.request.getUpdateIfExists() != null) {
                json.put("updateIfExists", this.request.getUpdateIfExists());
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
     * ユーザIDを指定してデータオブジェクトをアップロード準備する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void prepareUploadByUserIdAsync(
            PrepareUploadByUserIdRequest request,
            AsyncAction<AsyncResult<PrepareUploadByUserIdResult>> callback
    ) {
        PrepareUploadByUserIdTask task = new PrepareUploadByUserIdTask(request, callback, PrepareUploadByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してデータオブジェクトをアップロード準備する<br>
     *
     * @param request リクエストパラメータ
     */
    public PrepareUploadByUserIdResult prepareUploadByUserId(
            PrepareUploadByUserIdRequest request
    ) {
        final AsyncResult<PrepareUploadByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareUploadByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateDataObjectTask extends Gs2RestSessionTask<UpdateDataObjectResult> {
        private UpdateDataObjectRequest request;

        public UpdateDataObjectTask(
            UpdateDataObjectRequest request,
            AsyncAction<AsyncResult<UpdateDataObjectResult>> userCallback,
            Class<UpdateDataObjectResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/data/{dataObjectName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getScope() != null) {
                json.put("scope", this.request.getScope());
            }
            if (this.request.getAllowUserIds() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getAllowUserIds())
                {
                    array.put(item);
                }
                json.put("allowUserIds", array);
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
     * データオブジェクトを更新する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateDataObjectAsync(
            UpdateDataObjectRequest request,
            AsyncAction<AsyncResult<UpdateDataObjectResult>> callback
    ) {
        UpdateDataObjectTask task = new UpdateDataObjectTask(request, callback, UpdateDataObjectResult.class);
        session.execute(task);
    }

    /**
     * データオブジェクトを更新する<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateDataObjectResult updateDataObject(
            UpdateDataObjectRequest request
    ) {
        final AsyncResult<UpdateDataObjectResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateDataObjectAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateDataObjectByUserIdTask extends Gs2RestSessionTask<UpdateDataObjectByUserIdResult> {
        private UpdateDataObjectByUserIdRequest request;

        public UpdateDataObjectByUserIdTask(
            UpdateDataObjectByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateDataObjectByUserIdResult>> userCallback,
            Class<UpdateDataObjectByUserIdResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/data/{dataObjectName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getScope() != null) {
                json.put("scope", this.request.getScope());
            }
            if (this.request.getAllowUserIds() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getAllowUserIds())
                {
                    array.put(item);
                }
                json.put("allowUserIds", array);
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
     * ユーザIDを指定してデータオブジェクトを更新する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateDataObjectByUserIdAsync(
            UpdateDataObjectByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateDataObjectByUserIdResult>> callback
    ) {
        UpdateDataObjectByUserIdTask task = new UpdateDataObjectByUserIdTask(request, callback, UpdateDataObjectByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してデータオブジェクトを更新する<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateDataObjectByUserIdResult updateDataObjectByUserId(
            UpdateDataObjectByUserIdRequest request
    ) {
        final AsyncResult<UpdateDataObjectByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateDataObjectByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PrepareReUploadTask extends Gs2RestSessionTask<PrepareReUploadResult> {
        private PrepareReUploadRequest request;

        public PrepareReUploadTask(
            PrepareReUploadRequest request,
            AsyncAction<AsyncResult<PrepareReUploadResult>> userCallback,
            Class<PrepareReUploadResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/data/{dataObjectName}/file";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getContentType() != null) {
                json.put("contentType", this.request.getContentType());
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
     * データオブジェクトを再アップロード準備する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void prepareReUploadAsync(
            PrepareReUploadRequest request,
            AsyncAction<AsyncResult<PrepareReUploadResult>> callback
    ) {
        PrepareReUploadTask task = new PrepareReUploadTask(request, callback, PrepareReUploadResult.class);
        session.execute(task);
    }

    /**
     * データオブジェクトを再アップロード準備する<br>
     *
     * @param request リクエストパラメータ
     */
    public PrepareReUploadResult prepareReUpload(
            PrepareReUploadRequest request
    ) {
        final AsyncResult<PrepareReUploadResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareReUploadAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PrepareReUploadByUserIdTask extends Gs2RestSessionTask<PrepareReUploadByUserIdResult> {
        private PrepareReUploadByUserIdRequest request;

        public PrepareReUploadByUserIdTask(
            PrepareReUploadByUserIdRequest request,
            AsyncAction<AsyncResult<PrepareReUploadByUserIdResult>> userCallback,
            Class<PrepareReUploadByUserIdResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/data/{dataObjectName}/file";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getContentType() != null) {
                json.put("contentType", this.request.getContentType());
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
     * ユーザIDを指定してデータオブジェクトを再アップロード準備する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void prepareReUploadByUserIdAsync(
            PrepareReUploadByUserIdRequest request,
            AsyncAction<AsyncResult<PrepareReUploadByUserIdResult>> callback
    ) {
        PrepareReUploadByUserIdTask task = new PrepareReUploadByUserIdTask(request, callback, PrepareReUploadByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してデータオブジェクトを再アップロード準備する<br>
     *
     * @param request リクエストパラメータ
     */
    public PrepareReUploadByUserIdResult prepareReUploadByUserId(
            PrepareReUploadByUserIdRequest request
    ) {
        final AsyncResult<PrepareReUploadByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareReUploadByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DoneUploadTask extends Gs2RestSessionTask<DoneUploadResult> {
        private DoneUploadRequest request;

        public DoneUploadTask(
            DoneUploadRequest request,
            AsyncAction<AsyncResult<DoneUploadResult>> userCallback,
            Class<DoneUploadResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/data/{dataObjectName}/done";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * データオブジェクトのアップロード完了を報告する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void doneUploadAsync(
            DoneUploadRequest request,
            AsyncAction<AsyncResult<DoneUploadResult>> callback
    ) {
        DoneUploadTask task = new DoneUploadTask(request, callback, DoneUploadResult.class);
        session.execute(task);
    }

    /**
     * データオブジェクトのアップロード完了を報告する<br>
     *
     * @param request リクエストパラメータ
     */
    public DoneUploadResult doneUpload(
            DoneUploadRequest request
    ) {
        final AsyncResult<DoneUploadResult>[] resultAsyncResult = new AsyncResult[]{null};
        doneUploadAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DoneUploadByUserIdTask extends Gs2RestSessionTask<DoneUploadByUserIdResult> {
        private DoneUploadByUserIdRequest request;

        public DoneUploadByUserIdTask(
            DoneUploadByUserIdRequest request,
            AsyncAction<AsyncResult<DoneUploadByUserIdResult>> userCallback,
            Class<DoneUploadByUserIdResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/data/{dataObjectName}/done";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * ユーザIDを指定してデータオブジェクトのアップロード完了を報告する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void doneUploadByUserIdAsync(
            DoneUploadByUserIdRequest request,
            AsyncAction<AsyncResult<DoneUploadByUserIdResult>> callback
    ) {
        DoneUploadByUserIdTask task = new DoneUploadByUserIdTask(request, callback, DoneUploadByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してデータオブジェクトのアップロード完了を報告する<br>
     *
     * @param request リクエストパラメータ
     */
    public DoneUploadByUserIdResult doneUploadByUserId(
            DoneUploadByUserIdRequest request
    ) {
        final AsyncResult<DoneUploadByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        doneUploadByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteDataObjectTask extends Gs2RestSessionTask<DeleteDataObjectResult> {
        private DeleteDataObjectRequest request;

        public DeleteDataObjectTask(
            DeleteDataObjectRequest request,
            AsyncAction<AsyncResult<DeleteDataObjectResult>> userCallback,
            Class<DeleteDataObjectResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/data/{dataObjectName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));

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
     * データオブジェクトを削除する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteDataObjectAsync(
            DeleteDataObjectRequest request,
            AsyncAction<AsyncResult<DeleteDataObjectResult>> callback
    ) {
        DeleteDataObjectTask task = new DeleteDataObjectTask(request, callback, DeleteDataObjectResult.class);
        session.execute(task);
    }

    /**
     * データオブジェクトを削除する<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteDataObjectResult deleteDataObject(
            DeleteDataObjectRequest request
    ) {
        final AsyncResult<DeleteDataObjectResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteDataObjectAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteDataObjectByUserIdTask extends Gs2RestSessionTask<DeleteDataObjectByUserIdResult> {
        private DeleteDataObjectByUserIdRequest request;

        public DeleteDataObjectByUserIdTask(
            DeleteDataObjectByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteDataObjectByUserIdResult>> userCallback,
            Class<DeleteDataObjectByUserIdResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/data/{dataObjectName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));

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
     * ユーザIDを指定してデータオブジェクトを削除する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteDataObjectByUserIdAsync(
            DeleteDataObjectByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteDataObjectByUserIdResult>> callback
    ) {
        DeleteDataObjectByUserIdTask task = new DeleteDataObjectByUserIdTask(request, callback, DeleteDataObjectByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してデータオブジェクトを削除する<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteDataObjectByUserIdResult deleteDataObjectByUserId(
            DeleteDataObjectByUserIdRequest request
    ) {
        final AsyncResult<DeleteDataObjectByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteDataObjectByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PrepareDownloadTask extends Gs2RestSessionTask<PrepareDownloadResult> {
        private PrepareDownloadRequest request;

        public PrepareDownloadTask(
            PrepareDownloadRequest request,
            AsyncAction<AsyncResult<PrepareDownloadResult>> userCallback,
            Class<PrepareDownloadResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/file";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDataObjectId() != null) {
                json.put("dataObjectId", this.request.getDataObjectId());
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
     * データオブジェクトをダウンロード準備する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void prepareDownloadAsync(
            PrepareDownloadRequest request,
            AsyncAction<AsyncResult<PrepareDownloadResult>> callback
    ) {
        PrepareDownloadTask task = new PrepareDownloadTask(request, callback, PrepareDownloadResult.class);
        session.execute(task);
    }

    /**
     * データオブジェクトをダウンロード準備する<br>
     *
     * @param request リクエストパラメータ
     */
    public PrepareDownloadResult prepareDownload(
            PrepareDownloadRequest request
    ) {
        final AsyncResult<PrepareDownloadResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareDownloadAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PrepareDownloadByUserIdTask extends Gs2RestSessionTask<PrepareDownloadByUserIdResult> {
        private PrepareDownloadByUserIdRequest request;

        public PrepareDownloadByUserIdTask(
            PrepareDownloadByUserIdRequest request,
            AsyncAction<AsyncResult<PrepareDownloadByUserIdResult>> userCallback,
            Class<PrepareDownloadByUserIdResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/file";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDataObjectId() != null) {
                json.put("dataObjectId", this.request.getDataObjectId());
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
     * ユーザIDを指定してデータオブジェクトをダウンロード準備する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void prepareDownloadByUserIdAsync(
            PrepareDownloadByUserIdRequest request,
            AsyncAction<AsyncResult<PrepareDownloadByUserIdResult>> callback
    ) {
        PrepareDownloadByUserIdTask task = new PrepareDownloadByUserIdTask(request, callback, PrepareDownloadByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してデータオブジェクトをダウンロード準備する<br>
     *
     * @param request リクエストパラメータ
     */
    public PrepareDownloadByUserIdResult prepareDownloadByUserId(
            PrepareDownloadByUserIdRequest request
    ) {
        final AsyncResult<PrepareDownloadByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareDownloadByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PrepareDownloadByGenerationTask extends Gs2RestSessionTask<PrepareDownloadByGenerationResult> {
        private PrepareDownloadByGenerationRequest request;

        public PrepareDownloadByGenerationTask(
            PrepareDownloadByGenerationRequest request,
            AsyncAction<AsyncResult<PrepareDownloadByGenerationResult>> userCallback,
            Class<PrepareDownloadByGenerationResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/file/generation/{generation}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{generation}", this.request.getGeneration() == null|| this.request.getGeneration().length() == 0 ? "null" : String.valueOf(this.request.getGeneration()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDataObjectId() != null) {
                json.put("dataObjectId", this.request.getDataObjectId());
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
     * データオブジェクトを世代を指定してダウンロード準備する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void prepareDownloadByGenerationAsync(
            PrepareDownloadByGenerationRequest request,
            AsyncAction<AsyncResult<PrepareDownloadByGenerationResult>> callback
    ) {
        PrepareDownloadByGenerationTask task = new PrepareDownloadByGenerationTask(request, callback, PrepareDownloadByGenerationResult.class);
        session.execute(task);
    }

    /**
     * データオブジェクトを世代を指定してダウンロード準備する<br>
     *
     * @param request リクエストパラメータ
     */
    public PrepareDownloadByGenerationResult prepareDownloadByGeneration(
            PrepareDownloadByGenerationRequest request
    ) {
        final AsyncResult<PrepareDownloadByGenerationResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareDownloadByGenerationAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PrepareDownloadByGenerationAndUserIdTask extends Gs2RestSessionTask<PrepareDownloadByGenerationAndUserIdResult> {
        private PrepareDownloadByGenerationAndUserIdRequest request;

        public PrepareDownloadByGenerationAndUserIdTask(
            PrepareDownloadByGenerationAndUserIdRequest request,
            AsyncAction<AsyncResult<PrepareDownloadByGenerationAndUserIdResult>> userCallback,
            Class<PrepareDownloadByGenerationAndUserIdResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/file/generation/{generation}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{generation}", this.request.getGeneration() == null|| this.request.getGeneration().length() == 0 ? "null" : String.valueOf(this.request.getGeneration()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDataObjectId() != null) {
                json.put("dataObjectId", this.request.getDataObjectId());
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
     * ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void prepareDownloadByGenerationAndUserIdAsync(
            PrepareDownloadByGenerationAndUserIdRequest request,
            AsyncAction<AsyncResult<PrepareDownloadByGenerationAndUserIdResult>> callback
    ) {
        PrepareDownloadByGenerationAndUserIdTask task = new PrepareDownloadByGenerationAndUserIdTask(request, callback, PrepareDownloadByGenerationAndUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する<br>
     *
     * @param request リクエストパラメータ
     */
    public PrepareDownloadByGenerationAndUserIdResult prepareDownloadByGenerationAndUserId(
            PrepareDownloadByGenerationAndUserIdRequest request
    ) {
        final AsyncResult<PrepareDownloadByGenerationAndUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareDownloadByGenerationAndUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PrepareDownloadOwnDataTask extends Gs2RestSessionTask<PrepareDownloadOwnDataResult> {
        private PrepareDownloadOwnDataRequest request;

        public PrepareDownloadOwnDataTask(
            PrepareDownloadOwnDataRequest request,
            AsyncAction<AsyncResult<PrepareDownloadOwnDataResult>> userCallback,
            Class<PrepareDownloadOwnDataResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/file";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * データオブジェクトをダウンロード準備する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void prepareDownloadOwnDataAsync(
            PrepareDownloadOwnDataRequest request,
            AsyncAction<AsyncResult<PrepareDownloadOwnDataResult>> callback
    ) {
        PrepareDownloadOwnDataTask task = new PrepareDownloadOwnDataTask(request, callback, PrepareDownloadOwnDataResult.class);
        session.execute(task);
    }

    /**
     * データオブジェクトをダウンロード準備する<br>
     *
     * @param request リクエストパラメータ
     */
    public PrepareDownloadOwnDataResult prepareDownloadOwnData(
            PrepareDownloadOwnDataRequest request
    ) {
        final AsyncResult<PrepareDownloadOwnDataResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareDownloadOwnDataAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PrepareDownloadByUserIdAndDataObjectNameTask extends Gs2RestSessionTask<PrepareDownloadByUserIdAndDataObjectNameResult> {
        private PrepareDownloadByUserIdAndDataObjectNameRequest request;

        public PrepareDownloadByUserIdAndDataObjectNameTask(
            PrepareDownloadByUserIdAndDataObjectNameRequest request,
            AsyncAction<AsyncResult<PrepareDownloadByUserIdAndDataObjectNameResult>> userCallback,
            Class<PrepareDownloadByUserIdAndDataObjectNameResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/data/{dataObjectName}/file";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));

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
     * ユーザIDとオブジェクト名を指定してデータオブジェクトをダウンロード準備する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void prepareDownloadByUserIdAndDataObjectNameAsync(
            PrepareDownloadByUserIdAndDataObjectNameRequest request,
            AsyncAction<AsyncResult<PrepareDownloadByUserIdAndDataObjectNameResult>> callback
    ) {
        PrepareDownloadByUserIdAndDataObjectNameTask task = new PrepareDownloadByUserIdAndDataObjectNameTask(request, callback, PrepareDownloadByUserIdAndDataObjectNameResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDとオブジェクト名を指定してデータオブジェクトをダウンロード準備する<br>
     *
     * @param request リクエストパラメータ
     */
    public PrepareDownloadByUserIdAndDataObjectNameResult prepareDownloadByUserIdAndDataObjectName(
            PrepareDownloadByUserIdAndDataObjectNameRequest request
    ) {
        final AsyncResult<PrepareDownloadByUserIdAndDataObjectNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareDownloadByUserIdAndDataObjectNameAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PrepareDownloadOwnDataByGenerationTask extends Gs2RestSessionTask<PrepareDownloadOwnDataByGenerationResult> {
        private PrepareDownloadOwnDataByGenerationRequest request;

        public PrepareDownloadOwnDataByGenerationTask(
            PrepareDownloadOwnDataByGenerationRequest request,
            AsyncAction<AsyncResult<PrepareDownloadOwnDataByGenerationResult>> userCallback,
            Class<PrepareDownloadOwnDataByGenerationResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/data/{dataObjectName}/generation/{generation}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));
            url = url.replace("{generation}", this.request.getGeneration() == null|| this.request.getGeneration().length() == 0 ? "null" : String.valueOf(this.request.getGeneration()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * データオブジェクトを世代を指定してダウンロード準備する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void prepareDownloadOwnDataByGenerationAsync(
            PrepareDownloadOwnDataByGenerationRequest request,
            AsyncAction<AsyncResult<PrepareDownloadOwnDataByGenerationResult>> callback
    ) {
        PrepareDownloadOwnDataByGenerationTask task = new PrepareDownloadOwnDataByGenerationTask(request, callback, PrepareDownloadOwnDataByGenerationResult.class);
        session.execute(task);
    }

    /**
     * データオブジェクトを世代を指定してダウンロード準備する<br>
     *
     * @param request リクエストパラメータ
     */
    public PrepareDownloadOwnDataByGenerationResult prepareDownloadOwnDataByGeneration(
            PrepareDownloadOwnDataByGenerationRequest request
    ) {
        final AsyncResult<PrepareDownloadOwnDataByGenerationResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareDownloadOwnDataByGenerationAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PrepareDownloadByUserIdAndDataObjectNameAndGenerationTask extends Gs2RestSessionTask<PrepareDownloadByUserIdAndDataObjectNameAndGenerationResult> {
        private PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest request;

        public PrepareDownloadByUserIdAndDataObjectNameAndGenerationTask(
            PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest request,
            AsyncAction<AsyncResult<PrepareDownloadByUserIdAndDataObjectNameAndGenerationResult>> userCallback,
            Class<PrepareDownloadByUserIdAndDataObjectNameAndGenerationResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/data/{dataObjectName}/generation/{generation}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));
            url = url.replace("{generation}", this.request.getGeneration() == null|| this.request.getGeneration().length() == 0 ? "null" : String.valueOf(this.request.getGeneration()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void prepareDownloadByUserIdAndDataObjectNameAndGenerationAsync(
            PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest request,
            AsyncAction<AsyncResult<PrepareDownloadByUserIdAndDataObjectNameAndGenerationResult>> callback
    ) {
        PrepareDownloadByUserIdAndDataObjectNameAndGenerationTask task = new PrepareDownloadByUserIdAndDataObjectNameAndGenerationTask(request, callback, PrepareDownloadByUserIdAndDataObjectNameAndGenerationResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する<br>
     *
     * @param request リクエストパラメータ
     */
    public PrepareDownloadByUserIdAndDataObjectNameAndGenerationResult prepareDownloadByUserIdAndDataObjectNameAndGeneration(
            PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest request
    ) {
        final AsyncResult<PrepareDownloadByUserIdAndDataObjectNameAndGenerationResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareDownloadByUserIdAndDataObjectNameAndGenerationAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RestoreDataObjectTask extends Gs2RestSessionTask<RestoreDataObjectResult> {
        private RestoreDataObjectRequest request;

        public RestoreDataObjectTask(
            RestoreDataObjectRequest request,
            AsyncAction<AsyncResult<RestoreDataObjectResult>> userCallback,
            Class<RestoreDataObjectResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/file/restore";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDataObjectId() != null) {
                json.put("dataObjectId", this.request.getDataObjectId());
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
     * データオブジェクトの管理情報を修復する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void restoreDataObjectAsync(
            RestoreDataObjectRequest request,
            AsyncAction<AsyncResult<RestoreDataObjectResult>> callback
    ) {
        RestoreDataObjectTask task = new RestoreDataObjectTask(request, callback, RestoreDataObjectResult.class);
        session.execute(task);
    }

    /**
     * データオブジェクトの管理情報を修復する<br>
     *
     * @param request リクエストパラメータ
     */
    public RestoreDataObjectResult restoreDataObject(
            RestoreDataObjectRequest request
    ) {
        final AsyncResult<RestoreDataObjectResult>[] resultAsyncResult = new AsyncResult[]{null};
        restoreDataObjectAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeDataObjectHistoriesTask extends Gs2RestSessionTask<DescribeDataObjectHistoriesResult> {
        private DescribeDataObjectHistoriesRequest request;

        public DescribeDataObjectHistoriesTask(
            DescribeDataObjectHistoriesRequest request,
            AsyncAction<AsyncResult<DescribeDataObjectHistoriesResult>> userCallback,
            Class<DescribeDataObjectHistoriesResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/data/{dataObjectName}/history";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));

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
     * データオブジェクト履歴の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeDataObjectHistoriesAsync(
            DescribeDataObjectHistoriesRequest request,
            AsyncAction<AsyncResult<DescribeDataObjectHistoriesResult>> callback
    ) {
        DescribeDataObjectHistoriesTask task = new DescribeDataObjectHistoriesTask(request, callback, DescribeDataObjectHistoriesResult.class);
        session.execute(task);
    }

    /**
     * データオブジェクト履歴の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeDataObjectHistoriesResult describeDataObjectHistories(
            DescribeDataObjectHistoriesRequest request
    ) {
        final AsyncResult<DescribeDataObjectHistoriesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeDataObjectHistoriesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeDataObjectHistoriesByUserIdTask extends Gs2RestSessionTask<DescribeDataObjectHistoriesByUserIdResult> {
        private DescribeDataObjectHistoriesByUserIdRequest request;

        public DescribeDataObjectHistoriesByUserIdTask(
            DescribeDataObjectHistoriesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeDataObjectHistoriesByUserIdResult>> userCallback,
            Class<DescribeDataObjectHistoriesByUserIdResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/data/{dataObjectName}/history";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));

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
     * ユーザIDを指定してデータオブジェクト履歴の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeDataObjectHistoriesByUserIdAsync(
            DescribeDataObjectHistoriesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeDataObjectHistoriesByUserIdResult>> callback
    ) {
        DescribeDataObjectHistoriesByUserIdTask task = new DescribeDataObjectHistoriesByUserIdTask(request, callback, DescribeDataObjectHistoriesByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してデータオブジェクト履歴の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeDataObjectHistoriesByUserIdResult describeDataObjectHistoriesByUserId(
            DescribeDataObjectHistoriesByUserIdRequest request
    ) {
        final AsyncResult<DescribeDataObjectHistoriesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeDataObjectHistoriesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetDataObjectHistoryTask extends Gs2RestSessionTask<GetDataObjectHistoryResult> {
        private GetDataObjectHistoryRequest request;

        public GetDataObjectHistoryTask(
            GetDataObjectHistoryRequest request,
            AsyncAction<AsyncResult<GetDataObjectHistoryResult>> userCallback,
            Class<GetDataObjectHistoryResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/data/{dataObjectName}/history/{generation}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));
            url = url.replace("{generation}", this.request.getGeneration() == null|| this.request.getGeneration().length() == 0 ? "null" : String.valueOf(this.request.getGeneration()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * データオブジェクト履歴を取得する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getDataObjectHistoryAsync(
            GetDataObjectHistoryRequest request,
            AsyncAction<AsyncResult<GetDataObjectHistoryResult>> callback
    ) {
        GetDataObjectHistoryTask task = new GetDataObjectHistoryTask(request, callback, GetDataObjectHistoryResult.class);
        session.execute(task);
    }

    /**
     * データオブジェクト履歴を取得する<br>
     *
     * @param request リクエストパラメータ
     */
    public GetDataObjectHistoryResult getDataObjectHistory(
            GetDataObjectHistoryRequest request
    ) {
        final AsyncResult<GetDataObjectHistoryResult>[] resultAsyncResult = new AsyncResult[]{null};
        getDataObjectHistoryAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetDataObjectHistoryByUserIdTask extends Gs2RestSessionTask<GetDataObjectHistoryByUserIdResult> {
        private GetDataObjectHistoryByUserIdRequest request;

        public GetDataObjectHistoryByUserIdTask(
            GetDataObjectHistoryByUserIdRequest request,
            AsyncAction<AsyncResult<GetDataObjectHistoryByUserIdResult>> userCallback,
            Class<GetDataObjectHistoryByUserIdResult> clazz
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
                .replace("{service}", "datastore")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/data/{dataObjectName}/history/{generation}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{dataObjectName}", this.request.getDataObjectName() == null|| this.request.getDataObjectName().length() == 0 ? "null" : String.valueOf(this.request.getDataObjectName()));
            url = url.replace("{generation}", this.request.getGeneration() == null|| this.request.getGeneration().length() == 0 ? "null" : String.valueOf(this.request.getGeneration()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * ユーザIDを指定してデータオブジェクト履歴を取得する<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getDataObjectHistoryByUserIdAsync(
            GetDataObjectHistoryByUserIdRequest request,
            AsyncAction<AsyncResult<GetDataObjectHistoryByUserIdResult>> callback
    ) {
        GetDataObjectHistoryByUserIdTask task = new GetDataObjectHistoryByUserIdTask(request, callback, GetDataObjectHistoryByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してデータオブジェクト履歴を取得する<br>
     *
     * @param request リクエストパラメータ
     */
    public GetDataObjectHistoryByUserIdResult getDataObjectHistoryByUserId(
            GetDataObjectHistoryByUserIdRequest request
    ) {
        final AsyncResult<GetDataObjectHistoryByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getDataObjectHistoryByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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