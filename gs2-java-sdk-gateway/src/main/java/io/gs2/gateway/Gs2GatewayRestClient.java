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

package io.gs2.gateway;

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
import io.gs2.gateway.request.*;
import io.gs2.gateway.result.*;
import io.gs2.gateway.model.*;

/**
 * GS2 Gateway API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2GatewayRestClient extends AbstractGs2Client<Gs2GatewayRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2GatewayRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "gateway")
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
                .replace("{service}", "gateway")
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
            if (this.request.getFirebaseSecret() != null) {
                json.put("firebaseSecret", this.request.getFirebaseSecret());
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
                .replace("{service}", "gateway")
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
                .replace("{service}", "gateway")
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
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getFirebaseSecret() != null) {
                json.put("firebaseSecret", this.request.getFirebaseSecret());
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
                .replace("{service}", "gateway")
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

    class DescribeWebSocketSessionsTask extends Gs2RestSessionTask<DescribeWebSocketSessionsResult> {
        private DescribeWebSocketSessionsRequest request;

        public DescribeWebSocketSessionsTask(
            DescribeWebSocketSessionsRequest request,
            AsyncAction<AsyncResult<DescribeWebSocketSessionsResult>> userCallback,
            Class<DescribeWebSocketSessionsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/session/user/me";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

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
     * Websocketセッションの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeWebSocketSessionsAsync(
            DescribeWebSocketSessionsRequest request,
            AsyncAction<AsyncResult<DescribeWebSocketSessionsResult>> callback
    ) {
        DescribeWebSocketSessionsTask task = new DescribeWebSocketSessionsTask(request, callback, DescribeWebSocketSessionsResult.class);
        session.execute(task);
    }

    /**
     * Websocketセッションの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeWebSocketSessionsResult describeWebSocketSessions(
            DescribeWebSocketSessionsRequest request
    ) {
        final AsyncResult<DescribeWebSocketSessionsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeWebSocketSessionsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeWebSocketSessionsByUserIdTask extends Gs2RestSessionTask<DescribeWebSocketSessionsByUserIdResult> {
        private DescribeWebSocketSessionsByUserIdRequest request;

        public DescribeWebSocketSessionsByUserIdTask(
            DescribeWebSocketSessionsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeWebSocketSessionsByUserIdResult>> userCallback,
            Class<DescribeWebSocketSessionsByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/session/user/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
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
     * ユーザIDを指定してWebsocketセッションの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeWebSocketSessionsByUserIdAsync(
            DescribeWebSocketSessionsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeWebSocketSessionsByUserIdResult>> callback
    ) {
        DescribeWebSocketSessionsByUserIdTask task = new DescribeWebSocketSessionsByUserIdTask(request, callback, DescribeWebSocketSessionsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してWebsocketセッションの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeWebSocketSessionsByUserIdResult describeWebSocketSessionsByUserId(
            DescribeWebSocketSessionsByUserIdRequest request
    ) {
        final AsyncResult<DescribeWebSocketSessionsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeWebSocketSessionsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetUserIdTask extends Gs2RestSessionTask<SetUserIdResult> {
        private SetUserIdRequest request;

        public SetUserIdTask(
            SetUserIdRequest request,
            AsyncAction<AsyncResult<SetUserIdResult>> userCallback,
            Class<SetUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/session/user/me/user";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getAllowConcurrentAccess() != null) {
                json.put("allowConcurrentAccess", this.request.getAllowConcurrentAccess());
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
     * WebsocketセッションにユーザIDを設定<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setUserIdAsync(
            SetUserIdRequest request,
            AsyncAction<AsyncResult<SetUserIdResult>> callback
    ) {
        SetUserIdTask task = new SetUserIdTask(request, callback, SetUserIdResult.class);
        session.execute(task);
    }

    /**
     * WebsocketセッションにユーザIDを設定<br>
     *
     * @param request リクエストパラメータ
     */
    public SetUserIdResult setUserId(
            SetUserIdRequest request
    ) {
        final AsyncResult<SetUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetUserIdByUserIdTask extends Gs2RestSessionTask<SetUserIdByUserIdResult> {
        private SetUserIdByUserIdRequest request;

        public SetUserIdByUserIdTask(
            SetUserIdByUserIdRequest request,
            AsyncAction<AsyncResult<SetUserIdByUserIdResult>> userCallback,
            Class<SetUserIdByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/session/user/{userId}/user";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getAllowConcurrentAccess() != null) {
                json.put("allowConcurrentAccess", this.request.getAllowConcurrentAccess());
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
     * WebsocketセッションにユーザIDを設定<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setUserIdByUserIdAsync(
            SetUserIdByUserIdRequest request,
            AsyncAction<AsyncResult<SetUserIdByUserIdResult>> callback
    ) {
        SetUserIdByUserIdTask task = new SetUserIdByUserIdTask(request, callback, SetUserIdByUserIdResult.class);
        session.execute(task);
    }

    /**
     * WebsocketセッションにユーザIDを設定<br>
     *
     * @param request リクエストパラメータ
     */
    public SetUserIdByUserIdResult setUserIdByUserId(
            SetUserIdByUserIdRequest request
    ) {
        final AsyncResult<SetUserIdByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setUserIdByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetWebSocketSessionTask extends Gs2RestSessionTask<GetWebSocketSessionResult> {
        private GetWebSocketSessionRequest request;

        public GetWebSocketSessionTask(
            GetWebSocketSessionRequest request,
            AsyncAction<AsyncResult<GetWebSocketSessionResult>> userCallback,
            Class<GetWebSocketSessionResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/session";

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
     * Websocketセッションを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getWebSocketSessionAsync(
            GetWebSocketSessionRequest request,
            AsyncAction<AsyncResult<GetWebSocketSessionResult>> callback
    ) {
        GetWebSocketSessionTask task = new GetWebSocketSessionTask(request, callback, GetWebSocketSessionResult.class);
        session.execute(task);
    }

    /**
     * Websocketセッションを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetWebSocketSessionResult getWebSocketSession(
            GetWebSocketSessionRequest request
    ) {
        final AsyncResult<GetWebSocketSessionResult>[] resultAsyncResult = new AsyncResult[]{null};
        getWebSocketSessionAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetWebSocketSessionByConnectionIdTask extends Gs2RestSessionTask<GetWebSocketSessionByConnectionIdResult> {
        private GetWebSocketSessionByConnectionIdRequest request;

        public GetWebSocketSessionByConnectionIdTask(
            GetWebSocketSessionByConnectionIdRequest request,
            AsyncAction<AsyncResult<GetWebSocketSessionByConnectionIdResult>> userCallback,
            Class<GetWebSocketSessionByConnectionIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/session/{connectionId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{connectionId}", this.request.getConnectionId() == null|| this.request.getConnectionId().length() == 0 ? "null" : String.valueOf(this.request.getConnectionId()));

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
     * ユーザIDを指定してWebsocketセッションを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getWebSocketSessionByConnectionIdAsync(
            GetWebSocketSessionByConnectionIdRequest request,
            AsyncAction<AsyncResult<GetWebSocketSessionByConnectionIdResult>> callback
    ) {
        GetWebSocketSessionByConnectionIdTask task = new GetWebSocketSessionByConnectionIdTask(request, callback, GetWebSocketSessionByConnectionIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してWebsocketセッションを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetWebSocketSessionByConnectionIdResult getWebSocketSessionByConnectionId(
            GetWebSocketSessionByConnectionIdRequest request
    ) {
        final AsyncResult<GetWebSocketSessionByConnectionIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getWebSocketSessionByConnectionIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SendNotificationTask extends Gs2RestSessionTask<SendNotificationResult> {
        private SendNotificationRequest request;

        public SendNotificationTask(
            SendNotificationRequest request,
            AsyncAction<AsyncResult<SendNotificationResult>> userCallback,
            Class<SendNotificationResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/session/user/{userId}/notification";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getSubject() != null) {
                json.put("subject", this.request.getSubject());
            }
            if (this.request.getPayload() != null) {
                json.put("payload", this.request.getPayload());
            }
            if (this.request.getEnableTransferMobileNotification() != null) {
                json.put("enableTransferMobileNotification", this.request.getEnableTransferMobileNotification());
            }
            if (this.request.getSound() != null) {
                json.put("sound", this.request.getSound());
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
     * 通知を送信<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void sendNotificationAsync(
            SendNotificationRequest request,
            AsyncAction<AsyncResult<SendNotificationResult>> callback
    ) {
        SendNotificationTask task = new SendNotificationTask(request, callback, SendNotificationResult.class);
        session.execute(task);
    }

    /**
     * 通知を送信<br>
     *
     * @param request リクエストパラメータ
     */
    public SendNotificationResult sendNotification(
            SendNotificationRequest request
    ) {
        final AsyncResult<SendNotificationResult>[] resultAsyncResult = new AsyncResult[]{null};
        sendNotificationAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetFirebaseTokenTask extends Gs2RestSessionTask<SetFirebaseTokenResult> {
        private SetFirebaseTokenRequest request;

        public SetFirebaseTokenTask(
            SetFirebaseTokenRequest request,
            AsyncAction<AsyncResult<SetFirebaseTokenResult>> userCallback,
            Class<SetFirebaseTokenResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/firebase/token";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getToken() != null) {
                json.put("token", this.request.getToken());
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
     * デバイストークンを設定<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setFirebaseTokenAsync(
            SetFirebaseTokenRequest request,
            AsyncAction<AsyncResult<SetFirebaseTokenResult>> callback
    ) {
        SetFirebaseTokenTask task = new SetFirebaseTokenTask(request, callback, SetFirebaseTokenResult.class);
        session.execute(task);
    }

    /**
     * デバイストークンを設定<br>
     *
     * @param request リクエストパラメータ
     */
    public SetFirebaseTokenResult setFirebaseToken(
            SetFirebaseTokenRequest request
    ) {
        final AsyncResult<SetFirebaseTokenResult>[] resultAsyncResult = new AsyncResult[]{null};
        setFirebaseTokenAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SetFirebaseTokenByUserIdTask extends Gs2RestSessionTask<SetFirebaseTokenByUserIdResult> {
        private SetFirebaseTokenByUserIdRequest request;

        public SetFirebaseTokenByUserIdTask(
            SetFirebaseTokenByUserIdRequest request,
            AsyncAction<AsyncResult<SetFirebaseTokenByUserIdResult>> userCallback,
            Class<SetFirebaseTokenByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/firebase/token";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getToken() != null) {
                json.put("token", this.request.getToken());
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
     * ユーザIDを指定してデバイストークンを設定<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void setFirebaseTokenByUserIdAsync(
            SetFirebaseTokenByUserIdRequest request,
            AsyncAction<AsyncResult<SetFirebaseTokenByUserIdResult>> callback
    ) {
        SetFirebaseTokenByUserIdTask task = new SetFirebaseTokenByUserIdTask(request, callback, SetFirebaseTokenByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してデバイストークンを設定<br>
     *
     * @param request リクエストパラメータ
     */
    public SetFirebaseTokenByUserIdResult setFirebaseTokenByUserId(
            SetFirebaseTokenByUserIdRequest request
    ) {
        final AsyncResult<SetFirebaseTokenByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        setFirebaseTokenByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFirebaseTokenTask extends Gs2RestSessionTask<GetFirebaseTokenResult> {
        private GetFirebaseTokenRequest request;

        public GetFirebaseTokenTask(
            GetFirebaseTokenRequest request,
            AsyncAction<AsyncResult<GetFirebaseTokenResult>> userCallback,
            Class<GetFirebaseTokenResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/firebase/token";

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
     * Firebaseデバイストークンを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getFirebaseTokenAsync(
            GetFirebaseTokenRequest request,
            AsyncAction<AsyncResult<GetFirebaseTokenResult>> callback
    ) {
        GetFirebaseTokenTask task = new GetFirebaseTokenTask(request, callback, GetFirebaseTokenResult.class);
        session.execute(task);
    }

    /**
     * Firebaseデバイストークンを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetFirebaseTokenResult getFirebaseToken(
            GetFirebaseTokenRequest request
    ) {
        final AsyncResult<GetFirebaseTokenResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFirebaseTokenAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetFirebaseTokenByUserIdTask extends Gs2RestSessionTask<GetFirebaseTokenByUserIdResult> {
        private GetFirebaseTokenByUserIdRequest request;

        public GetFirebaseTokenByUserIdTask(
            GetFirebaseTokenByUserIdRequest request,
            AsyncAction<AsyncResult<GetFirebaseTokenByUserIdResult>> userCallback,
            Class<GetFirebaseTokenByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/firebase/token";

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
     * ユーザIDを指定してFirebaseデバイストークンを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getFirebaseTokenByUserIdAsync(
            GetFirebaseTokenByUserIdRequest request,
            AsyncAction<AsyncResult<GetFirebaseTokenByUserIdResult>> callback
    ) {
        GetFirebaseTokenByUserIdTask task = new GetFirebaseTokenByUserIdTask(request, callback, GetFirebaseTokenByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してFirebaseデバイストークンを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetFirebaseTokenByUserIdResult getFirebaseTokenByUserId(
            GetFirebaseTokenByUserIdRequest request
    ) {
        final AsyncResult<GetFirebaseTokenByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getFirebaseTokenByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteFirebaseTokenTask extends Gs2RestSessionTask<DeleteFirebaseTokenResult> {
        private DeleteFirebaseTokenRequest request;

        public DeleteFirebaseTokenTask(
            DeleteFirebaseTokenRequest request,
            AsyncAction<AsyncResult<DeleteFirebaseTokenResult>> userCallback,
            Class<DeleteFirebaseTokenResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/firebase/token";

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
     * Firebaseデバイストークンを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteFirebaseTokenAsync(
            DeleteFirebaseTokenRequest request,
            AsyncAction<AsyncResult<DeleteFirebaseTokenResult>> callback
    ) {
        DeleteFirebaseTokenTask task = new DeleteFirebaseTokenTask(request, callback, DeleteFirebaseTokenResult.class);
        session.execute(task);
    }

    /**
     * Firebaseデバイストークンを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteFirebaseTokenResult deleteFirebaseToken(
            DeleteFirebaseTokenRequest request
    ) {
        final AsyncResult<DeleteFirebaseTokenResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteFirebaseTokenAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteFirebaseTokenByUserIdTask extends Gs2RestSessionTask<DeleteFirebaseTokenByUserIdResult> {
        private DeleteFirebaseTokenByUserIdRequest request;

        public DeleteFirebaseTokenByUserIdTask(
            DeleteFirebaseTokenByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteFirebaseTokenByUserIdResult>> userCallback,
            Class<DeleteFirebaseTokenByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/firebase/token";

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
     * ユーザIDを指定してFirebaseデバイストークンを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteFirebaseTokenByUserIdAsync(
            DeleteFirebaseTokenByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteFirebaseTokenByUserIdResult>> callback
    ) {
        DeleteFirebaseTokenByUserIdTask task = new DeleteFirebaseTokenByUserIdTask(request, callback, DeleteFirebaseTokenByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してFirebaseデバイストークンを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteFirebaseTokenByUserIdResult deleteFirebaseTokenByUserId(
            DeleteFirebaseTokenByUserIdRequest request
    ) {
        final AsyncResult<DeleteFirebaseTokenByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteFirebaseTokenByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SendMobileNotificationByUserIdTask extends Gs2RestSessionTask<SendMobileNotificationByUserIdResult> {
        private SendMobileNotificationByUserIdRequest request;

        public SendMobileNotificationByUserIdTask(
            SendMobileNotificationByUserIdRequest request,
            AsyncAction<AsyncResult<SendMobileNotificationByUserIdResult>> userCallback,
            Class<SendMobileNotificationByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "gateway")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/firebase/token/notification";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getSubject() != null) {
                json.put("subject", this.request.getSubject());
            }
            if (this.request.getPayload() != null) {
                json.put("payload", this.request.getPayload());
            }
            if (this.request.getSound() != null) {
                json.put("sound", this.request.getSound());
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
     * モバイルプッシュ通知を送信<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void sendMobileNotificationByUserIdAsync(
            SendMobileNotificationByUserIdRequest request,
            AsyncAction<AsyncResult<SendMobileNotificationByUserIdResult>> callback
    ) {
        SendMobileNotificationByUserIdTask task = new SendMobileNotificationByUserIdTask(request, callback, SendMobileNotificationByUserIdResult.class);
        session.execute(task);
    }

    /**
     * モバイルプッシュ通知を送信<br>
     *
     * @param request リクエストパラメータ
     */
    public SendMobileNotificationByUserIdResult sendMobileNotificationByUserId(
            SendMobileNotificationByUserIdRequest request
    ) {
        final AsyncResult<SendMobileNotificationByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        sendMobileNotificationByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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