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

package io.gs2.dictionary;

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
import io.gs2.dictionary.request.*;
import io.gs2.dictionary.result.*;
import io.gs2.dictionary.model.*;

/**
 * GS2 Dictionary API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2DictionaryRestClient extends AbstractGs2Client<Gs2DictionaryRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2DictionaryRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "dictionary")
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
                .replace("{service}", "dictionary")
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
            if (this.request.getEntryScript() != null) {
                try {
                    json.put("entryScript", new JSONObject(mapper.writeValueAsString(this.request.getEntryScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getDuplicateEntryScript() != null) {
                try {
                    json.put("duplicateEntryScript", new JSONObject(mapper.writeValueAsString(this.request.getDuplicateEntryScript())));
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
                .replace("{service}", "dictionary")
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
                .replace("{service}", "dictionary")
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
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getEntryScript() != null) {
                try {
                    json.put("entryScript", new JSONObject(mapper.writeValueAsString(this.request.getEntryScript())));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.request.getDuplicateEntryScript() != null) {
                try {
                    json.put("duplicateEntryScript", new JSONObject(mapper.writeValueAsString(this.request.getDuplicateEntryScript())));
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
                .replace("{service}", "dictionary")
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

    class DescribeEntryModelsTask extends Gs2RestSessionTask<DescribeEntryModelsResult> {
        private DescribeEntryModelsRequest request;

        public DescribeEntryModelsTask(
            DescribeEntryModelsRequest request,
            AsyncAction<AsyncResult<DescribeEntryModelsResult>> userCallback,
            Class<DescribeEntryModelsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
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
     * エントリーモデルの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeEntryModelsAsync(
            DescribeEntryModelsRequest request,
            AsyncAction<AsyncResult<DescribeEntryModelsResult>> callback
    ) {
        DescribeEntryModelsTask task = new DescribeEntryModelsTask(request, callback, DescribeEntryModelsResult.class);
        session.execute(task);
    }

    /**
     * エントリーモデルの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeEntryModelsResult describeEntryModels(
            DescribeEntryModelsRequest request
    ) {
        final AsyncResult<DescribeEntryModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeEntryModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetEntryModelTask extends Gs2RestSessionTask<GetEntryModelResult> {
        private GetEntryModelRequest request;

        public GetEntryModelTask(
            GetEntryModelRequest request,
            AsyncAction<AsyncResult<GetEntryModelResult>> userCallback,
            Class<GetEntryModelResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/model/{entryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{entryName}", this.request.getEntryName() == null|| this.request.getEntryName().length() == 0 ? "null" : String.valueOf(this.request.getEntryName()));

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
     * エントリーモデルを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getEntryModelAsync(
            GetEntryModelRequest request,
            AsyncAction<AsyncResult<GetEntryModelResult>> callback
    ) {
        GetEntryModelTask task = new GetEntryModelTask(request, callback, GetEntryModelResult.class);
        session.execute(task);
    }

    /**
     * エントリーモデルを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetEntryModelResult getEntryModel(
            GetEntryModelRequest request
    ) {
        final AsyncResult<GetEntryModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getEntryModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeEntryModelMastersTask extends Gs2RestSessionTask<DescribeEntryModelMastersResult> {
        private DescribeEntryModelMastersRequest request;

        public DescribeEntryModelMastersTask(
            DescribeEntryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeEntryModelMastersResult>> userCallback,
            Class<DescribeEntryModelMastersResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
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
     * エントリーモデルマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeEntryModelMastersAsync(
            DescribeEntryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeEntryModelMastersResult>> callback
    ) {
        DescribeEntryModelMastersTask task = new DescribeEntryModelMastersTask(request, callback, DescribeEntryModelMastersResult.class);
        session.execute(task);
    }

    /**
     * エントリーモデルマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeEntryModelMastersResult describeEntryModelMasters(
            DescribeEntryModelMastersRequest request
    ) {
        final AsyncResult<DescribeEntryModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeEntryModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateEntryModelMasterTask extends Gs2RestSessionTask<CreateEntryModelMasterResult> {
        private CreateEntryModelMasterRequest request;

        public CreateEntryModelMasterTask(
            CreateEntryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateEntryModelMasterResult>> userCallback,
            Class<CreateEntryModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
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
     * エントリーモデルマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createEntryModelMasterAsync(
            CreateEntryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateEntryModelMasterResult>> callback
    ) {
        CreateEntryModelMasterTask task = new CreateEntryModelMasterTask(request, callback, CreateEntryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * エントリーモデルマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateEntryModelMasterResult createEntryModelMaster(
            CreateEntryModelMasterRequest request
    ) {
        final AsyncResult<CreateEntryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createEntryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetEntryModelMasterTask extends Gs2RestSessionTask<GetEntryModelMasterResult> {
        private GetEntryModelMasterRequest request;

        public GetEntryModelMasterTask(
            GetEntryModelMasterRequest request,
            AsyncAction<AsyncResult<GetEntryModelMasterResult>> userCallback,
            Class<GetEntryModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{entryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{entryName}", this.request.getEntryName() == null|| this.request.getEntryName().length() == 0 ? "null" : String.valueOf(this.request.getEntryName()));

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
     * エントリーモデルマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getEntryModelMasterAsync(
            GetEntryModelMasterRequest request,
            AsyncAction<AsyncResult<GetEntryModelMasterResult>> callback
    ) {
        GetEntryModelMasterTask task = new GetEntryModelMasterTask(request, callback, GetEntryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * エントリーモデルマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetEntryModelMasterResult getEntryModelMaster(
            GetEntryModelMasterRequest request
    ) {
        final AsyncResult<GetEntryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getEntryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateEntryModelMasterTask extends Gs2RestSessionTask<UpdateEntryModelMasterResult> {
        private UpdateEntryModelMasterRequest request;

        public UpdateEntryModelMasterTask(
            UpdateEntryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateEntryModelMasterResult>> userCallback,
            Class<UpdateEntryModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{entryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{entryName}", this.request.getEntryName() == null|| this.request.getEntryName().length() == 0 ? "null" : String.valueOf(this.request.getEntryName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
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
     * エントリーモデルマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateEntryModelMasterAsync(
            UpdateEntryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateEntryModelMasterResult>> callback
    ) {
        UpdateEntryModelMasterTask task = new UpdateEntryModelMasterTask(request, callback, UpdateEntryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * エントリーモデルマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateEntryModelMasterResult updateEntryModelMaster(
            UpdateEntryModelMasterRequest request
    ) {
        final AsyncResult<UpdateEntryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateEntryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteEntryModelMasterTask extends Gs2RestSessionTask<DeleteEntryModelMasterResult> {
        private DeleteEntryModelMasterRequest request;

        public DeleteEntryModelMasterTask(
            DeleteEntryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteEntryModelMasterResult>> userCallback,
            Class<DeleteEntryModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/model/{entryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{entryName}", this.request.getEntryName() == null|| this.request.getEntryName().length() == 0 ? "null" : String.valueOf(this.request.getEntryName()));

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
     * エントリーモデルマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteEntryModelMasterAsync(
            DeleteEntryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteEntryModelMasterResult>> callback
    ) {
        DeleteEntryModelMasterTask task = new DeleteEntryModelMasterTask(request, callback, DeleteEntryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * エントリーモデルマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteEntryModelMasterResult deleteEntryModelMaster(
            DeleteEntryModelMasterRequest request
    ) {
        final AsyncResult<DeleteEntryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteEntryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeEntriesTask extends Gs2RestSessionTask<DescribeEntriesResult> {
        private DescribeEntriesRequest request;

        public DescribeEntriesTask(
            DescribeEntriesRequest request,
            AsyncAction<AsyncResult<DescribeEntriesResult>> userCallback,
            Class<DescribeEntriesResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/entry";

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
     * エントリーの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeEntriesAsync(
            DescribeEntriesRequest request,
            AsyncAction<AsyncResult<DescribeEntriesResult>> callback
    ) {
        DescribeEntriesTask task = new DescribeEntriesTask(request, callback, DescribeEntriesResult.class);
        session.execute(task);
    }

    /**
     * エントリーの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeEntriesResult describeEntries(
            DescribeEntriesRequest request
    ) {
        final AsyncResult<DescribeEntriesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeEntriesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeEntriesByUserIdTask extends Gs2RestSessionTask<DescribeEntriesByUserIdResult> {
        private DescribeEntriesByUserIdRequest request;

        public DescribeEntriesByUserIdTask(
            DescribeEntriesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeEntriesByUserIdResult>> userCallback,
            Class<DescribeEntriesByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/entry";

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
     * ユーザIDを指定してエントリーの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeEntriesByUserIdAsync(
            DescribeEntriesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeEntriesByUserIdResult>> callback
    ) {
        DescribeEntriesByUserIdTask task = new DescribeEntriesByUserIdTask(request, callback, DescribeEntriesByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してエントリーの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeEntriesByUserIdResult describeEntriesByUserId(
            DescribeEntriesByUserIdRequest request
    ) {
        final AsyncResult<DescribeEntriesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeEntriesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddEntriesByUserIdTask extends Gs2RestSessionTask<AddEntriesByUserIdResult> {
        private AddEntriesByUserIdRequest request;

        public AddEntriesByUserIdTask(
            AddEntriesByUserIdRequest request,
            AsyncAction<AsyncResult<AddEntriesByUserIdResult>> userCallback,
            Class<AddEntriesByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/entry";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getEntryModelNames() != null) {
                JSONArray array = new JSONArray();
                for(String item : this.request.getEntryModelNames())
                {
                    array.put(item);
                }
                json.put("entryModelNames", array);
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
     * ユーザIDを指定してエントリーを入手済みとして登録<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void addEntriesByUserIdAsync(
            AddEntriesByUserIdRequest request,
            AsyncAction<AsyncResult<AddEntriesByUserIdResult>> callback
    ) {
        AddEntriesByUserIdTask task = new AddEntriesByUserIdTask(request, callback, AddEntriesByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してエントリーを入手済みとして登録<br>
     *
     * @param request リクエストパラメータ
     */
    public AddEntriesByUserIdResult addEntriesByUserId(
            AddEntriesByUserIdRequest request
    ) {
        final AsyncResult<AddEntriesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        addEntriesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetEntryTask extends Gs2RestSessionTask<GetEntryResult> {
        private GetEntryRequest request;

        public GetEntryTask(
            GetEntryRequest request,
            AsyncAction<AsyncResult<GetEntryResult>> userCallback,
            Class<GetEntryResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/entry/{entryModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{entryModelName}", this.request.getEntryModelName() == null|| this.request.getEntryModelName().length() == 0 ? "null" : String.valueOf(this.request.getEntryModelName()));

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
     * エントリーを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getEntryAsync(
            GetEntryRequest request,
            AsyncAction<AsyncResult<GetEntryResult>> callback
    ) {
        GetEntryTask task = new GetEntryTask(request, callback, GetEntryResult.class);
        session.execute(task);
    }

    /**
     * エントリーを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetEntryResult getEntry(
            GetEntryRequest request
    ) {
        final AsyncResult<GetEntryResult>[] resultAsyncResult = new AsyncResult[]{null};
        getEntryAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetEntryByUserIdTask extends Gs2RestSessionTask<GetEntryByUserIdResult> {
        private GetEntryByUserIdRequest request;

        public GetEntryByUserIdTask(
            GetEntryByUserIdRequest request,
            AsyncAction<AsyncResult<GetEntryByUserIdResult>> userCallback,
            Class<GetEntryByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/entry/{entryModelName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{entryModelName}", this.request.getEntryModelName() == null|| this.request.getEntryModelName().length() == 0 ? "null" : String.valueOf(this.request.getEntryModelName()));

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
     * ユーザIDを指定してエントリーを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getEntryByUserIdAsync(
            GetEntryByUserIdRequest request,
            AsyncAction<AsyncResult<GetEntryByUserIdResult>> callback
    ) {
        GetEntryByUserIdTask task = new GetEntryByUserIdTask(request, callback, GetEntryByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してエントリーを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetEntryByUserIdResult getEntryByUserId(
            GetEntryByUserIdRequest request
    ) {
        final AsyncResult<GetEntryByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getEntryByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetEntryWithSignatureTask extends Gs2RestSessionTask<GetEntryWithSignatureResult> {
        private GetEntryWithSignatureRequest request;

        public GetEntryWithSignatureTask(
            GetEntryWithSignatureRequest request,
            AsyncAction<AsyncResult<GetEntryWithSignatureResult>> userCallback,
            Class<GetEntryWithSignatureResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/entry/{entryModelName}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{entryModelName}", this.request.getEntryModelName() == null|| this.request.getEntryModelName().length() == 0 ? "null" : String.valueOf(this.request.getEntryModelName()));

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
     * エントリーを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getEntryWithSignatureAsync(
            GetEntryWithSignatureRequest request,
            AsyncAction<AsyncResult<GetEntryWithSignatureResult>> callback
    ) {
        GetEntryWithSignatureTask task = new GetEntryWithSignatureTask(request, callback, GetEntryWithSignatureResult.class);
        session.execute(task);
    }

    /**
     * エントリーを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetEntryWithSignatureResult getEntryWithSignature(
            GetEntryWithSignatureRequest request
    ) {
        final AsyncResult<GetEntryWithSignatureResult>[] resultAsyncResult = new AsyncResult[]{null};
        getEntryWithSignatureAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetEntryWithSignatureByUserIdTask extends Gs2RestSessionTask<GetEntryWithSignatureByUserIdResult> {
        private GetEntryWithSignatureByUserIdRequest request;

        public GetEntryWithSignatureByUserIdTask(
            GetEntryWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetEntryWithSignatureByUserIdResult>> userCallback,
            Class<GetEntryWithSignatureByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/entry/{entryModelName}/signature";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{entryModelName}", this.request.getEntryModelName() == null|| this.request.getEntryModelName().length() == 0 ? "null" : String.valueOf(this.request.getEntryModelName()));

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
     * ユーザIDを指定してエントリーを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getEntryWithSignatureByUserIdAsync(
            GetEntryWithSignatureByUserIdRequest request,
            AsyncAction<AsyncResult<GetEntryWithSignatureByUserIdResult>> callback
    ) {
        GetEntryWithSignatureByUserIdTask task = new GetEntryWithSignatureByUserIdTask(request, callback, GetEntryWithSignatureByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してエントリーを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetEntryWithSignatureByUserIdResult getEntryWithSignatureByUserId(
            GetEntryWithSignatureByUserIdRequest request
    ) {
        final AsyncResult<GetEntryWithSignatureByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getEntryWithSignatureByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ResetByUserIdTask extends Gs2RestSessionTask<ResetByUserIdResult> {
        private ResetByUserIdRequest request;

        public ResetByUserIdTask(
            ResetByUserIdRequest request,
            AsyncAction<AsyncResult<ResetByUserIdResult>> userCallback,
            Class<ResetByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/entry";

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
     * エントリーをリセット<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void resetByUserIdAsync(
            ResetByUserIdRequest request,
            AsyncAction<AsyncResult<ResetByUserIdResult>> callback
    ) {
        ResetByUserIdTask task = new ResetByUserIdTask(request, callback, ResetByUserIdResult.class);
        session.execute(task);
    }

    /**
     * エントリーをリセット<br>
     *
     * @param request リクエストパラメータ
     */
    public ResetByUserIdResult resetByUserId(
            ResetByUserIdRequest request
    ) {
        final AsyncResult<ResetByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        resetByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AddEntriesByStampSheetTask extends Gs2RestSessionTask<AddEntriesByStampSheetResult> {
        private AddEntriesByStampSheetRequest request;

        public AddEntriesByStampSheetTask(
            AddEntriesByStampSheetRequest request,
            AsyncAction<AsyncResult<AddEntriesByStampSheetResult>> userCallback,
            Class<AddEntriesByStampSheetResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/entry/add";

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
     * スタンプシートでエントリーを追加<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void addEntriesByStampSheetAsync(
            AddEntriesByStampSheetRequest request,
            AsyncAction<AsyncResult<AddEntriesByStampSheetResult>> callback
    ) {
        AddEntriesByStampSheetTask task = new AddEntriesByStampSheetTask(request, callback, AddEntriesByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートでエントリーを追加<br>
     *
     * @param request リクエストパラメータ
     */
    public AddEntriesByStampSheetResult addEntriesByStampSheet(
            AddEntriesByStampSheetRequest request
    ) {
        final AsyncResult<AddEntriesByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        addEntriesByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "dictionary")
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
     * 現在有効なエントリー設定のマスターデータをエクスポートします<br>
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
     * 現在有効なエントリー設定のマスターデータをエクスポートします<br>
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

    class GetCurrentEntryMasterTask extends Gs2RestSessionTask<GetCurrentEntryMasterResult> {
        private GetCurrentEntryMasterRequest request;

        public GetCurrentEntryMasterTask(
            GetCurrentEntryMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentEntryMasterResult>> userCallback,
            Class<GetCurrentEntryMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
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
     * 現在有効なエントリー設定を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentEntryMasterAsync(
            GetCurrentEntryMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentEntryMasterResult>> callback
    ) {
        GetCurrentEntryMasterTask task = new GetCurrentEntryMasterTask(request, callback, GetCurrentEntryMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なエントリー設定を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCurrentEntryMasterResult getCurrentEntryMaster(
            GetCurrentEntryMasterRequest request
    ) {
        final AsyncResult<GetCurrentEntryMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentEntryMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentEntryMasterTask extends Gs2RestSessionTask<UpdateCurrentEntryMasterResult> {
        private UpdateCurrentEntryMasterRequest request;

        public UpdateCurrentEntryMasterTask(
            UpdateCurrentEntryMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentEntryMasterResult>> userCallback,
            Class<UpdateCurrentEntryMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
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
     * 現在有効なエントリー設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentEntryMasterAsync(
            UpdateCurrentEntryMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentEntryMasterResult>> callback
    ) {
        UpdateCurrentEntryMasterTask task = new UpdateCurrentEntryMasterTask(request, callback, UpdateCurrentEntryMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なエントリー設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentEntryMasterResult updateCurrentEntryMaster(
            UpdateCurrentEntryMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentEntryMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentEntryMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentEntryMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentEntryMasterFromGitHubResult> {
        private UpdateCurrentEntryMasterFromGitHubRequest request;

        public UpdateCurrentEntryMasterFromGitHubTask(
            UpdateCurrentEntryMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentEntryMasterFromGitHubResult>> userCallback,
            Class<UpdateCurrentEntryMasterFromGitHubResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "dictionary")
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
     * 現在有効なエントリー設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentEntryMasterFromGitHubAsync(
            UpdateCurrentEntryMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentEntryMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentEntryMasterFromGitHubTask task = new UpdateCurrentEntryMasterFromGitHubTask(request, callback, UpdateCurrentEntryMasterFromGitHubResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なエントリー設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentEntryMasterFromGitHubResult updateCurrentEntryMasterFromGitHub(
            UpdateCurrentEntryMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentEntryMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentEntryMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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