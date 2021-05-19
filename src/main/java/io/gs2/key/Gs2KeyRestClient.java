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

package io.gs2.key;

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
import io.gs2.key.request.*;
import io.gs2.key.result.*;
import io.gs2.key.model.*;

/**
 * GS2 Key API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2KeyRestClient extends AbstractGs2Client<Gs2KeyRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2KeyRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "key")
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
                .replace("{service}", "key")
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
                .replace("{service}", "key")
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
                .replace("{service}", "key")
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
                .replace("{service}", "key")
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
                .replace("{service}", "key")
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

    class DescribeKeysTask extends Gs2RestSessionTask<DescribeKeysResult> {
        private DescribeKeysRequest request;

        public DescribeKeysTask(
            DescribeKeysRequest request,
            AsyncAction<AsyncResult<DescribeKeysResult>> userCallback,
            Class<DescribeKeysResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/key";

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
     * 暗号鍵の一覧を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeKeysAsync(
            DescribeKeysRequest request,
            AsyncAction<AsyncResult<DescribeKeysResult>> callback
    ) {
        DescribeKeysTask task = new DescribeKeysTask(request, callback, DescribeKeysResult.class);
        session.execute(task);
    }

    /**
     * 暗号鍵の一覧を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeKeysResult describeKeys(
            DescribeKeysRequest request
    ) {
        final AsyncResult<DescribeKeysResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeKeysAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateKeyTask extends Gs2RestSessionTask<CreateKeyResult> {
        private CreateKeyRequest request;

        public CreateKeyTask(
            CreateKeyRequest request,
            AsyncAction<AsyncResult<CreateKeyResult>> userCallback,
            Class<CreateKeyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/key";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
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
     * 暗号鍵を新規作成します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createKeyAsync(
            CreateKeyRequest request,
            AsyncAction<AsyncResult<CreateKeyResult>> callback
    ) {
        CreateKeyTask task = new CreateKeyTask(request, callback, CreateKeyResult.class);
        session.execute(task);
    }

    /**
     * 暗号鍵を新規作成します<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateKeyResult createKey(
            CreateKeyRequest request
    ) {
        final AsyncResult<CreateKeyResult>[] resultAsyncResult = new AsyncResult[]{null};
        createKeyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateKeyTask extends Gs2RestSessionTask<UpdateKeyResult> {
        private UpdateKeyRequest request;

        public UpdateKeyTask(
            UpdateKeyRequest request,
            AsyncAction<AsyncResult<UpdateKeyResult>> userCallback,
            Class<UpdateKeyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/key/{keyName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{keyName}", this.request.getKeyName() == null|| this.request.getKeyName().length() == 0 ? "null" : String.valueOf(this.request.getKeyName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
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
     * 暗号鍵を更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateKeyAsync(
            UpdateKeyRequest request,
            AsyncAction<AsyncResult<UpdateKeyResult>> callback
    ) {
        UpdateKeyTask task = new UpdateKeyTask(request, callback, UpdateKeyResult.class);
        session.execute(task);
    }

    /**
     * 暗号鍵を更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateKeyResult updateKey(
            UpdateKeyRequest request
    ) {
        final AsyncResult<UpdateKeyResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateKeyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetKeyTask extends Gs2RestSessionTask<GetKeyResult> {
        private GetKeyRequest request;

        public GetKeyTask(
            GetKeyRequest request,
            AsyncAction<AsyncResult<GetKeyResult>> userCallback,
            Class<GetKeyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/key/{keyName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{keyName}", this.request.getKeyName() == null|| this.request.getKeyName().length() == 0 ? "null" : String.valueOf(this.request.getKeyName()));

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
     * 暗号鍵を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getKeyAsync(
            GetKeyRequest request,
            AsyncAction<AsyncResult<GetKeyResult>> callback
    ) {
        GetKeyTask task = new GetKeyTask(request, callback, GetKeyResult.class);
        session.execute(task);
    }

    /**
     * 暗号鍵を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetKeyResult getKey(
            GetKeyRequest request
    ) {
        final AsyncResult<GetKeyResult>[] resultAsyncResult = new AsyncResult[]{null};
        getKeyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteKeyTask extends Gs2RestSessionTask<DeleteKeyResult> {
        private DeleteKeyRequest request;

        public DeleteKeyTask(
            DeleteKeyRequest request,
            AsyncAction<AsyncResult<DeleteKeyResult>> userCallback,
            Class<DeleteKeyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/key/{keyName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{keyName}", this.request.getKeyName() == null|| this.request.getKeyName().length() == 0 ? "null" : String.valueOf(this.request.getKeyName()));

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
     * 暗号鍵を削除します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteKeyAsync(
            DeleteKeyRequest request,
            AsyncAction<AsyncResult<DeleteKeyResult>> callback
    ) {
        DeleteKeyTask task = new DeleteKeyTask(request, callback, DeleteKeyResult.class);
        session.execute(task);
    }

    /**
     * 暗号鍵を削除します<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteKeyResult deleteKey(
            DeleteKeyRequest request
    ) {
        final AsyncResult<DeleteKeyResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteKeyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class EncryptTask extends Gs2RestSessionTask<EncryptResult> {
        private EncryptRequest request;

        public EncryptTask(
            EncryptRequest request,
            AsyncAction<AsyncResult<EncryptResult>> userCallback,
            Class<EncryptResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/key/{keyName}/encrypt";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{keyName}", this.request.getKeyName() == null|| this.request.getKeyName().length() == 0 ? "null" : String.valueOf(this.request.getKeyName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getData() != null) {
                json.put("data", this.request.getData());
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
     * データを暗号化します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void encryptAsync(
            EncryptRequest request,
            AsyncAction<AsyncResult<EncryptResult>> callback
    ) {
        EncryptTask task = new EncryptTask(request, callback, EncryptResult.class);
        session.execute(task);
    }

    /**
     * データを暗号化します<br>
     *
     * @param request リクエストパラメータ
     */
    public EncryptResult encrypt(
            EncryptRequest request
    ) {
        final AsyncResult<EncryptResult>[] resultAsyncResult = new AsyncResult[]{null};
        encryptAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DecryptTask extends Gs2RestSessionTask<DecryptResult> {
        private DecryptRequest request;

        public DecryptTask(
            DecryptRequest request,
            AsyncAction<AsyncResult<DecryptResult>> userCallback,
            Class<DecryptResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/key/{keyName}/decrypt";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{keyName}", this.request.getKeyName() == null|| this.request.getKeyName().length() == 0 ? "null" : String.valueOf(this.request.getKeyName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getData() != null) {
                json.put("data", this.request.getData());
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
     * データを復号します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void decryptAsync(
            DecryptRequest request,
            AsyncAction<AsyncResult<DecryptResult>> callback
    ) {
        DecryptTask task = new DecryptTask(request, callback, DecryptResult.class);
        session.execute(task);
    }

    /**
     * データを復号します<br>
     *
     * @param request リクエストパラメータ
     */
    public DecryptResult decrypt(
            DecryptRequest request
    ) {
        final AsyncResult<DecryptResult>[] resultAsyncResult = new AsyncResult[]{null};
        decryptAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeGitHubApiKeysTask extends Gs2RestSessionTask<DescribeGitHubApiKeysResult> {
        private DescribeGitHubApiKeysRequest request;

        public DescribeGitHubApiKeysTask(
            DescribeGitHubApiKeysRequest request,
            AsyncAction<AsyncResult<DescribeGitHubApiKeysResult>> userCallback,
            Class<DescribeGitHubApiKeysResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/github";

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
     * GitHub のAPIキーの一覧を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeGitHubApiKeysAsync(
            DescribeGitHubApiKeysRequest request,
            AsyncAction<AsyncResult<DescribeGitHubApiKeysResult>> callback
    ) {
        DescribeGitHubApiKeysTask task = new DescribeGitHubApiKeysTask(request, callback, DescribeGitHubApiKeysResult.class);
        session.execute(task);
    }

    /**
     * GitHub のAPIキーの一覧を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeGitHubApiKeysResult describeGitHubApiKeys(
            DescribeGitHubApiKeysRequest request
    ) {
        final AsyncResult<DescribeGitHubApiKeysResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeGitHubApiKeysAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateGitHubApiKeyTask extends Gs2RestSessionTask<CreateGitHubApiKeyResult> {
        private CreateGitHubApiKeyRequest request;

        public CreateGitHubApiKeyTask(
            CreateGitHubApiKeyRequest request,
            AsyncAction<AsyncResult<CreateGitHubApiKeyResult>> userCallback,
            Class<CreateGitHubApiKeyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/github";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getApiKey() != null) {
                json.put("apiKey", this.request.getApiKey());
            }
            if (this.request.getEncryptionKeyName() != null) {
                json.put("encryptionKeyName", this.request.getEncryptionKeyName());
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
     * GitHub のAPIキーを新規作成します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createGitHubApiKeyAsync(
            CreateGitHubApiKeyRequest request,
            AsyncAction<AsyncResult<CreateGitHubApiKeyResult>> callback
    ) {
        CreateGitHubApiKeyTask task = new CreateGitHubApiKeyTask(request, callback, CreateGitHubApiKeyResult.class);
        session.execute(task);
    }

    /**
     * GitHub のAPIキーを新規作成します<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateGitHubApiKeyResult createGitHubApiKey(
            CreateGitHubApiKeyRequest request
    ) {
        final AsyncResult<CreateGitHubApiKeyResult>[] resultAsyncResult = new AsyncResult[]{null};
        createGitHubApiKeyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateGitHubApiKeyTask extends Gs2RestSessionTask<UpdateGitHubApiKeyResult> {
        private UpdateGitHubApiKeyRequest request;

        public UpdateGitHubApiKeyTask(
            UpdateGitHubApiKeyRequest request,
            AsyncAction<AsyncResult<UpdateGitHubApiKeyResult>> userCallback,
            Class<UpdateGitHubApiKeyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/github/{apiKeyName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{apiKeyName}", this.request.getApiKeyName() == null|| this.request.getApiKeyName().length() == 0 ? "null" : String.valueOf(this.request.getApiKeyName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getApiKey() != null) {
                json.put("apiKey", this.request.getApiKey());
            }
            if (this.request.getEncryptionKeyName() != null) {
                json.put("encryptionKeyName", this.request.getEncryptionKeyName());
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
     * GitHub のAPIキーを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateGitHubApiKeyAsync(
            UpdateGitHubApiKeyRequest request,
            AsyncAction<AsyncResult<UpdateGitHubApiKeyResult>> callback
    ) {
        UpdateGitHubApiKeyTask task = new UpdateGitHubApiKeyTask(request, callback, UpdateGitHubApiKeyResult.class);
        session.execute(task);
    }

    /**
     * GitHub のAPIキーを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateGitHubApiKeyResult updateGitHubApiKey(
            UpdateGitHubApiKeyRequest request
    ) {
        final AsyncResult<UpdateGitHubApiKeyResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateGitHubApiKeyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetGitHubApiKeyTask extends Gs2RestSessionTask<GetGitHubApiKeyResult> {
        private GetGitHubApiKeyRequest request;

        public GetGitHubApiKeyTask(
            GetGitHubApiKeyRequest request,
            AsyncAction<AsyncResult<GetGitHubApiKeyResult>> userCallback,
            Class<GetGitHubApiKeyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/github/{apiKeyName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{apiKeyName}", this.request.getApiKeyName() == null|| this.request.getApiKeyName().length() == 0 ? "null" : String.valueOf(this.request.getApiKeyName()));

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
     * GitHub のAPIキーを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getGitHubApiKeyAsync(
            GetGitHubApiKeyRequest request,
            AsyncAction<AsyncResult<GetGitHubApiKeyResult>> callback
    ) {
        GetGitHubApiKeyTask task = new GetGitHubApiKeyTask(request, callback, GetGitHubApiKeyResult.class);
        session.execute(task);
    }

    /**
     * GitHub のAPIキーを取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetGitHubApiKeyResult getGitHubApiKey(
            GetGitHubApiKeyRequest request
    ) {
        final AsyncResult<GetGitHubApiKeyResult>[] resultAsyncResult = new AsyncResult[]{null};
        getGitHubApiKeyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteGitHubApiKeyTask extends Gs2RestSessionTask<DeleteGitHubApiKeyResult> {
        private DeleteGitHubApiKeyRequest request;

        public DeleteGitHubApiKeyTask(
            DeleteGitHubApiKeyRequest request,
            AsyncAction<AsyncResult<DeleteGitHubApiKeyResult>> userCallback,
            Class<DeleteGitHubApiKeyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "key")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/github/{apiKeyName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{apiKeyName}", this.request.getApiKeyName() == null|| this.request.getApiKeyName().length() == 0 ? "null" : String.valueOf(this.request.getApiKeyName()));

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
     * GitHub のAPIキーを削除します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteGitHubApiKeyAsync(
            DeleteGitHubApiKeyRequest request,
            AsyncAction<AsyncResult<DeleteGitHubApiKeyResult>> callback
    ) {
        DeleteGitHubApiKeyTask task = new DeleteGitHubApiKeyTask(request, callback, DeleteGitHubApiKeyResult.class);
        session.execute(task);
    }

    /**
     * GitHub のAPIキーを削除します<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteGitHubApiKeyResult deleteGitHubApiKey(
            DeleteGitHubApiKeyRequest request
    ) {
        final AsyncResult<DeleteGitHubApiKeyResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteGitHubApiKeyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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