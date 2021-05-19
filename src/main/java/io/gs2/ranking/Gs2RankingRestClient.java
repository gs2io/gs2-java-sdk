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

package io.gs2.ranking;

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
import io.gs2.ranking.request.*;
import io.gs2.ranking.result.*;
import io.gs2.ranking.model.*;

/**
 * GS2 Ranking API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2RankingRestClient extends AbstractGs2Client<Gs2RankingRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2RankingRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "ranking")
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
                .replace("{service}", "ranking")
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
                .replace("{service}", "ranking")
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
                .replace("{service}", "ranking")
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
                .replace("{service}", "ranking")
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
                .replace("{service}", "ranking")
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

    class DescribeCategoryModelsTask extends Gs2RestSessionTask<DescribeCategoryModelsResult> {
        private DescribeCategoryModelsRequest request;

        public DescribeCategoryModelsTask(
            DescribeCategoryModelsRequest request,
            AsyncAction<AsyncResult<DescribeCategoryModelsResult>> userCallback,
            Class<DescribeCategoryModelsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/category";

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
     * カテゴリの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeCategoryModelsAsync(
            DescribeCategoryModelsRequest request,
            AsyncAction<AsyncResult<DescribeCategoryModelsResult>> callback
    ) {
        DescribeCategoryModelsTask task = new DescribeCategoryModelsTask(request, callback, DescribeCategoryModelsResult.class);
        session.execute(task);
    }

    /**
     * カテゴリの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeCategoryModelsResult describeCategoryModels(
            DescribeCategoryModelsRequest request
    ) {
        final AsyncResult<DescribeCategoryModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCategoryModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCategoryModelTask extends Gs2RestSessionTask<GetCategoryModelResult> {
        private GetCategoryModelRequest request;

        public GetCategoryModelTask(
            GetCategoryModelRequest request,
            AsyncAction<AsyncResult<GetCategoryModelResult>> userCallback,
            Class<GetCategoryModelResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/category/{categoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

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
     * カテゴリを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCategoryModelAsync(
            GetCategoryModelRequest request,
            AsyncAction<AsyncResult<GetCategoryModelResult>> callback
    ) {
        GetCategoryModelTask task = new GetCategoryModelTask(request, callback, GetCategoryModelResult.class);
        session.execute(task);
    }

    /**
     * カテゴリを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCategoryModelResult getCategoryModel(
            GetCategoryModelRequest request
    ) {
        final AsyncResult<GetCategoryModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCategoryModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeCategoryModelMastersTask extends Gs2RestSessionTask<DescribeCategoryModelMastersResult> {
        private DescribeCategoryModelMastersRequest request;

        public DescribeCategoryModelMastersTask(
            DescribeCategoryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeCategoryModelMastersResult>> userCallback,
            Class<DescribeCategoryModelMastersResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/category";

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
     * カテゴリマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeCategoryModelMastersAsync(
            DescribeCategoryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeCategoryModelMastersResult>> callback
    ) {
        DescribeCategoryModelMastersTask task = new DescribeCategoryModelMastersTask(request, callback, DescribeCategoryModelMastersResult.class);
        session.execute(task);
    }

    /**
     * カテゴリマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeCategoryModelMastersResult describeCategoryModelMasters(
            DescribeCategoryModelMastersRequest request
    ) {
        final AsyncResult<DescribeCategoryModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCategoryModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateCategoryModelMasterTask extends Gs2RestSessionTask<CreateCategoryModelMasterResult> {
        private CreateCategoryModelMasterRequest request;

        public CreateCategoryModelMasterTask(
            CreateCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateCategoryModelMasterResult>> userCallback,
            Class<CreateCategoryModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/category";

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
            if (this.request.getMinimumValue() != null) {
                json.put("minimumValue", this.request.getMinimumValue());
            }
            if (this.request.getMaximumValue() != null) {
                json.put("maximumValue", this.request.getMaximumValue());
            }
            if (this.request.getOrderDirection() != null) {
                json.put("orderDirection", this.request.getOrderDirection());
            }
            if (this.request.getScope() != null) {
                json.put("scope", this.request.getScope());
            }
            if (this.request.getUniqueByUserId() != null) {
                json.put("uniqueByUserId", this.request.getUniqueByUserId());
            }
            if (this.request.getCalculateFixedTimingHour() != null) {
                json.put("calculateFixedTimingHour", this.request.getCalculateFixedTimingHour());
            }
            if (this.request.getCalculateFixedTimingMinute() != null) {
                json.put("calculateFixedTimingMinute", this.request.getCalculateFixedTimingMinute());
            }
            if (this.request.getCalculateIntervalMinutes() != null) {
                json.put("calculateIntervalMinutes", this.request.getCalculateIntervalMinutes());
            }
            if (this.request.getEntryPeriodEventId() != null) {
                json.put("entryPeriodEventId", this.request.getEntryPeriodEventId());
            }
            if (this.request.getAccessPeriodEventId() != null) {
                json.put("accessPeriodEventId", this.request.getAccessPeriodEventId());
            }
            if (this.request.getGeneration() != null) {
                json.put("generation", this.request.getGeneration());
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
     * カテゴリマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createCategoryModelMasterAsync(
            CreateCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateCategoryModelMasterResult>> callback
    ) {
        CreateCategoryModelMasterTask task = new CreateCategoryModelMasterTask(request, callback, CreateCategoryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * カテゴリマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateCategoryModelMasterResult createCategoryModelMaster(
            CreateCategoryModelMasterRequest request
    ) {
        final AsyncResult<CreateCategoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createCategoryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCategoryModelMasterTask extends Gs2RestSessionTask<GetCategoryModelMasterResult> {
        private GetCategoryModelMasterRequest request;

        public GetCategoryModelMasterTask(
            GetCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<GetCategoryModelMasterResult>> userCallback,
            Class<GetCategoryModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/category/{categoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

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
     * カテゴリマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCategoryModelMasterAsync(
            GetCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<GetCategoryModelMasterResult>> callback
    ) {
        GetCategoryModelMasterTask task = new GetCategoryModelMasterTask(request, callback, GetCategoryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * カテゴリマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCategoryModelMasterResult getCategoryModelMaster(
            GetCategoryModelMasterRequest request
    ) {
        final AsyncResult<GetCategoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCategoryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCategoryModelMasterTask extends Gs2RestSessionTask<UpdateCategoryModelMasterResult> {
        private UpdateCategoryModelMasterRequest request;

        public UpdateCategoryModelMasterTask(
            UpdateCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCategoryModelMasterResult>> userCallback,
            Class<UpdateCategoryModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/category/{categoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getMinimumValue() != null) {
                json.put("minimumValue", this.request.getMinimumValue());
            }
            if (this.request.getMaximumValue() != null) {
                json.put("maximumValue", this.request.getMaximumValue());
            }
            if (this.request.getOrderDirection() != null) {
                json.put("orderDirection", this.request.getOrderDirection());
            }
            if (this.request.getScope() != null) {
                json.put("scope", this.request.getScope());
            }
            if (this.request.getUniqueByUserId() != null) {
                json.put("uniqueByUserId", this.request.getUniqueByUserId());
            }
            if (this.request.getCalculateFixedTimingHour() != null) {
                json.put("calculateFixedTimingHour", this.request.getCalculateFixedTimingHour());
            }
            if (this.request.getCalculateFixedTimingMinute() != null) {
                json.put("calculateFixedTimingMinute", this.request.getCalculateFixedTimingMinute());
            }
            if (this.request.getCalculateIntervalMinutes() != null) {
                json.put("calculateIntervalMinutes", this.request.getCalculateIntervalMinutes());
            }
            if (this.request.getEntryPeriodEventId() != null) {
                json.put("entryPeriodEventId", this.request.getEntryPeriodEventId());
            }
            if (this.request.getAccessPeriodEventId() != null) {
                json.put("accessPeriodEventId", this.request.getAccessPeriodEventId());
            }
            if (this.request.getGeneration() != null) {
                json.put("generation", this.request.getGeneration());
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
     * カテゴリマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCategoryModelMasterAsync(
            UpdateCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateCategoryModelMasterResult>> callback
    ) {
        UpdateCategoryModelMasterTask task = new UpdateCategoryModelMasterTask(request, callback, UpdateCategoryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * カテゴリマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCategoryModelMasterResult updateCategoryModelMaster(
            UpdateCategoryModelMasterRequest request
    ) {
        final AsyncResult<UpdateCategoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCategoryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteCategoryModelMasterTask extends Gs2RestSessionTask<DeleteCategoryModelMasterResult> {
        private DeleteCategoryModelMasterRequest request;

        public DeleteCategoryModelMasterTask(
            DeleteCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteCategoryModelMasterResult>> userCallback,
            Class<DeleteCategoryModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/category/{categoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

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
     * カテゴリマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteCategoryModelMasterAsync(
            DeleteCategoryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteCategoryModelMasterResult>> callback
    ) {
        DeleteCategoryModelMasterTask task = new DeleteCategoryModelMasterTask(request, callback, DeleteCategoryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * カテゴリマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteCategoryModelMasterResult deleteCategoryModelMaster(
            DeleteCategoryModelMasterRequest request
    ) {
        final AsyncResult<DeleteCategoryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteCategoryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribesByCategoryNameTask extends Gs2RestSessionTask<DescribeSubscribesByCategoryNameResult> {
        private DescribeSubscribesByCategoryNameRequest request;

        public DescribeSubscribesByCategoryNameTask(
            DescribeSubscribesByCategoryNameRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByCategoryNameResult>> userCallback,
            Class<DescribeSubscribesByCategoryNameResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscribe/category/{categoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

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
     * 購読しているユーザIDの一覧取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeSubscribesByCategoryNameAsync(
            DescribeSubscribesByCategoryNameRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByCategoryNameResult>> callback
    ) {
        DescribeSubscribesByCategoryNameTask task = new DescribeSubscribesByCategoryNameTask(request, callback, DescribeSubscribesByCategoryNameResult.class);
        session.execute(task);
    }

    /**
     * 購読しているユーザIDの一覧取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeSubscribesByCategoryNameResult describeSubscribesByCategoryName(
            DescribeSubscribesByCategoryNameRequest request
    ) {
        final AsyncResult<DescribeSubscribesByCategoryNameResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribesByCategoryNameAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSubscribesByCategoryNameAndUserIdTask extends Gs2RestSessionTask<DescribeSubscribesByCategoryNameAndUserIdResult> {
        private DescribeSubscribesByCategoryNameAndUserIdRequest request;

        public DescribeSubscribesByCategoryNameAndUserIdTask(
            DescribeSubscribesByCategoryNameAndUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByCategoryNameAndUserIdResult>> userCallback,
            Class<DescribeSubscribesByCategoryNameAndUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscribe/category/{categoryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
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
     * ユーザIDを指定して購読しているユーザIDの一覧取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeSubscribesByCategoryNameAndUserIdAsync(
            DescribeSubscribesByCategoryNameAndUserIdRequest request,
            AsyncAction<AsyncResult<DescribeSubscribesByCategoryNameAndUserIdResult>> callback
    ) {
        DescribeSubscribesByCategoryNameAndUserIdTask task = new DescribeSubscribesByCategoryNameAndUserIdTask(request, callback, DescribeSubscribesByCategoryNameAndUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して購読しているユーザIDの一覧取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeSubscribesByCategoryNameAndUserIdResult describeSubscribesByCategoryNameAndUserId(
            DescribeSubscribesByCategoryNameAndUserIdRequest request
    ) {
        final AsyncResult<DescribeSubscribesByCategoryNameAndUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSubscribesByCategoryNameAndUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SubscribeTask extends Gs2RestSessionTask<SubscribeResult> {
        private SubscribeRequest request;

        public SubscribeTask(
            SubscribeRequest request,
            AsyncAction<AsyncResult<SubscribeResult>> userCallback,
            Class<SubscribeResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscribe/category/{categoryName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * ユーザIDを購読<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void subscribeAsync(
            SubscribeRequest request,
            AsyncAction<AsyncResult<SubscribeResult>> callback
    ) {
        SubscribeTask task = new SubscribeTask(request, callback, SubscribeResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを購読<br>
     *
     * @param request リクエストパラメータ
     */
    public SubscribeResult subscribe(
            SubscribeRequest request
    ) {
        final AsyncResult<SubscribeResult>[] resultAsyncResult = new AsyncResult[]{null};
        subscribeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SubscribeByUserIdTask extends Gs2RestSessionTask<SubscribeByUserIdResult> {
        private SubscribeByUserIdRequest request;

        public SubscribeByUserIdTask(
            SubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<SubscribeByUserIdResult>> userCallback,
            Class<SubscribeByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscribe/category/{categoryName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * ユーザIDを指定してユーザIDを購読<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void subscribeByUserIdAsync(
            SubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<SubscribeByUserIdResult>> callback
    ) {
        SubscribeByUserIdTask task = new SubscribeByUserIdTask(request, callback, SubscribeByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してユーザIDを購読<br>
     *
     * @param request リクエストパラメータ
     */
    public SubscribeByUserIdResult subscribeByUserId(
            SubscribeByUserIdRequest request
    ) {
        final AsyncResult<SubscribeByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        subscribeByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscribeTask extends Gs2RestSessionTask<GetSubscribeResult> {
        private GetSubscribeRequest request;

        public GetSubscribeTask(
            GetSubscribeRequest request,
            AsyncAction<AsyncResult<GetSubscribeResult>> userCallback,
            Class<GetSubscribeResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscribe/category/{categoryName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * 購読を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getSubscribeAsync(
            GetSubscribeRequest request,
            AsyncAction<AsyncResult<GetSubscribeResult>> callback
    ) {
        GetSubscribeTask task = new GetSubscribeTask(request, callback, GetSubscribeResult.class);
        session.execute(task);
    }

    /**
     * 購読を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetSubscribeResult getSubscribe(
            GetSubscribeRequest request
    ) {
        final AsyncResult<GetSubscribeResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscribeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSubscribeByUserIdTask extends Gs2RestSessionTask<GetSubscribeByUserIdResult> {
        private GetSubscribeByUserIdRequest request;

        public GetSubscribeByUserIdTask(
            GetSubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<GetSubscribeByUserIdResult>> userCallback,
            Class<GetSubscribeByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscribe/category/{categoryName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * ユーザIDを指定して購読を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getSubscribeByUserIdAsync(
            GetSubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<GetSubscribeByUserIdResult>> callback
    ) {
        GetSubscribeByUserIdTask task = new GetSubscribeByUserIdTask(request, callback, GetSubscribeByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して購読を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetSubscribeByUserIdResult getSubscribeByUserId(
            GetSubscribeByUserIdRequest request
    ) {
        final AsyncResult<GetSubscribeByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSubscribeByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UnsubscribeTask extends Gs2RestSessionTask<UnsubscribeResult> {
        private UnsubscribeRequest request;

        public UnsubscribeTask(
            UnsubscribeRequest request,
            AsyncAction<AsyncResult<UnsubscribeResult>> userCallback,
            Class<UnsubscribeResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/subscribe/category/{categoryName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * 購読の購読を解除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void unsubscribeAsync(
            UnsubscribeRequest request,
            AsyncAction<AsyncResult<UnsubscribeResult>> callback
    ) {
        UnsubscribeTask task = new UnsubscribeTask(request, callback, UnsubscribeResult.class);
        session.execute(task);
    }

    /**
     * 購読の購読を解除<br>
     *
     * @param request リクエストパラメータ
     */
    public UnsubscribeResult unsubscribe(
            UnsubscribeRequest request
    ) {
        final AsyncResult<UnsubscribeResult>[] resultAsyncResult = new AsyncResult[]{null};
        unsubscribeAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UnsubscribeByUserIdTask extends Gs2RestSessionTask<UnsubscribeByUserIdResult> {
        private UnsubscribeByUserIdRequest request;

        public UnsubscribeByUserIdTask(
            UnsubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<UnsubscribeByUserIdResult>> userCallback,
            Class<UnsubscribeByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/subscribe/category/{categoryName}/target/{targetUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{targetUserId}", this.request.getTargetUserId() == null|| this.request.getTargetUserId().length() == 0 ? "null" : String.valueOf(this.request.getTargetUserId()));

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
     * ユーザIDを指定して購読の購読を解除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void unsubscribeByUserIdAsync(
            UnsubscribeByUserIdRequest request,
            AsyncAction<AsyncResult<UnsubscribeByUserIdResult>> callback
    ) {
        UnsubscribeByUserIdTask task = new UnsubscribeByUserIdTask(request, callback, UnsubscribeByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して購読の購読を解除<br>
     *
     * @param request リクエストパラメータ
     */
    public UnsubscribeByUserIdResult unsubscribeByUserId(
            UnsubscribeByUserIdRequest request
    ) {
        final AsyncResult<UnsubscribeByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        unsubscribeByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeScoresTask extends Gs2RestSessionTask<DescribeScoresResult> {
        private DescribeScoresRequest request;

        public DescribeScoresTask(
            DescribeScoresRequest request,
            AsyncAction<AsyncResult<DescribeScoresResult>> userCallback,
            Class<DescribeScoresResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/category/{categoryName}/scorer/{scorerUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{scorerUserId}", this.request.getScorerUserId() == null|| this.request.getScorerUserId().length() == 0 ? "null" : String.valueOf(this.request.getScorerUserId()));

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
     * スコアの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeScoresAsync(
            DescribeScoresRequest request,
            AsyncAction<AsyncResult<DescribeScoresResult>> callback
    ) {
        DescribeScoresTask task = new DescribeScoresTask(request, callback, DescribeScoresResult.class);
        session.execute(task);
    }

    /**
     * スコアの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeScoresResult describeScores(
            DescribeScoresRequest request
    ) {
        final AsyncResult<DescribeScoresResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeScoresAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeScoresByUserIdTask extends Gs2RestSessionTask<DescribeScoresByUserIdResult> {
        private DescribeScoresByUserIdRequest request;

        public DescribeScoresByUserIdTask(
            DescribeScoresByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeScoresByUserIdResult>> userCallback,
            Class<DescribeScoresByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/category/{categoryName}/scorer/{scorerUserId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{scorerUserId}", this.request.getScorerUserId() == null|| this.request.getScorerUserId().length() == 0 ? "null" : String.valueOf(this.request.getScorerUserId()));

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
     * スコアの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeScoresByUserIdAsync(
            DescribeScoresByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeScoresByUserIdResult>> callback
    ) {
        DescribeScoresByUserIdTask task = new DescribeScoresByUserIdTask(request, callback, DescribeScoresByUserIdResult.class);
        session.execute(task);
    }

    /**
     * スコアの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeScoresByUserIdResult describeScoresByUserId(
            DescribeScoresByUserIdRequest request
    ) {
        final AsyncResult<DescribeScoresByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeScoresByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetScoreTask extends Gs2RestSessionTask<GetScoreResult> {
        private GetScoreRequest request;

        public GetScoreTask(
            GetScoreRequest request,
            AsyncAction<AsyncResult<GetScoreResult>> userCallback,
            Class<GetScoreResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/category/{categoryName}/scorer/{scorerUserId}/score/{uniqueId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{scorerUserId}", this.request.getScorerUserId() == null|| this.request.getScorerUserId().length() == 0 ? "null" : String.valueOf(this.request.getScorerUserId()));
            url = url.replace("{uniqueId}", this.request.getUniqueId() == null|| this.request.getUniqueId().length() == 0 ? "null" : String.valueOf(this.request.getUniqueId()));

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
     * スコアを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getScoreAsync(
            GetScoreRequest request,
            AsyncAction<AsyncResult<GetScoreResult>> callback
    ) {
        GetScoreTask task = new GetScoreTask(request, callback, GetScoreResult.class);
        session.execute(task);
    }

    /**
     * スコアを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetScoreResult getScore(
            GetScoreRequest request
    ) {
        final AsyncResult<GetScoreResult>[] resultAsyncResult = new AsyncResult[]{null};
        getScoreAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetScoreByUserIdTask extends Gs2RestSessionTask<GetScoreByUserIdResult> {
        private GetScoreByUserIdRequest request;

        public GetScoreByUserIdTask(
            GetScoreByUserIdRequest request,
            AsyncAction<AsyncResult<GetScoreByUserIdResult>> userCallback,
            Class<GetScoreByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/category/{categoryName}/scorer/{scorerUserId}/score/{uniqueId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{scorerUserId}", this.request.getScorerUserId() == null|| this.request.getScorerUserId().length() == 0 ? "null" : String.valueOf(this.request.getScorerUserId()));
            url = url.replace("{uniqueId}", this.request.getUniqueId() == null|| this.request.getUniqueId().length() == 0 ? "null" : String.valueOf(this.request.getUniqueId()));

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
     * スコアを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getScoreByUserIdAsync(
            GetScoreByUserIdRequest request,
            AsyncAction<AsyncResult<GetScoreByUserIdResult>> callback
    ) {
        GetScoreByUserIdTask task = new GetScoreByUserIdTask(request, callback, GetScoreByUserIdResult.class);
        session.execute(task);
    }

    /**
     * スコアを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetScoreByUserIdResult getScoreByUserId(
            GetScoreByUserIdRequest request
    ) {
        final AsyncResult<GetScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRankingsTask extends Gs2RestSessionTask<DescribeRankingsResult> {
        private DescribeRankingsRequest request;

        public DescribeRankingsTask(
            DescribeRankingsRequest request,
            AsyncAction<AsyncResult<DescribeRankingsResult>> userCallback,
            Class<DescribeRankingsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/category/{categoryName}/ranking";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getStartIndex() != null) {
                queryStrings.add("startIndex=" + String.valueOf(this.request.getStartIndex()));
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
     * ランキングを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeRankingsAsync(
            DescribeRankingsRequest request,
            AsyncAction<AsyncResult<DescribeRankingsResult>> callback
    ) {
        DescribeRankingsTask task = new DescribeRankingsTask(request, callback, DescribeRankingsResult.class);
        session.execute(task);
    }

    /**
     * ランキングを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeRankingsResult describeRankings(
            DescribeRankingsRequest request
    ) {
        final AsyncResult<DescribeRankingsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRankingsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeRankingssByUserIdTask extends Gs2RestSessionTask<DescribeRankingssByUserIdResult> {
        private DescribeRankingssByUserIdRequest request;

        public DescribeRankingssByUserIdTask(
            DescribeRankingssByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRankingssByUserIdResult>> userCallback,
            Class<DescribeRankingssByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/category/{categoryName}/ranking";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getStartIndex() != null) {
                queryStrings.add("startIndex=" + String.valueOf(this.request.getStartIndex()));
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
     * ユーザIDを指定してランキングを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeRankingssByUserIdAsync(
            DescribeRankingssByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeRankingssByUserIdResult>> callback
    ) {
        DescribeRankingssByUserIdTask task = new DescribeRankingssByUserIdTask(request, callback, DescribeRankingssByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してランキングを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeRankingssByUserIdResult describeRankingssByUserId(
            DescribeRankingssByUserIdRequest request
    ) {
        final AsyncResult<DescribeRankingssByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeRankingssByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeNearRankingsTask extends Gs2RestSessionTask<DescribeNearRankingsResult> {
        private DescribeNearRankingsRequest request;

        public DescribeNearRankingsTask(
            DescribeNearRankingsRequest request,
            AsyncAction<AsyncResult<DescribeNearRankingsResult>> userCallback,
            Class<DescribeNearRankingsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/category/{categoryName}/ranking/near";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getScore() != null) {
                queryStrings.add("score=" + String.valueOf(this.request.getScore()));
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
     * 指定したスコア付近のランキングを取得<br>
     *   <br>
     *   このAPIはグローバルランキングのときのみ使用できます<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeNearRankingsAsync(
            DescribeNearRankingsRequest request,
            AsyncAction<AsyncResult<DescribeNearRankingsResult>> callback
    ) {
        DescribeNearRankingsTask task = new DescribeNearRankingsTask(request, callback, DescribeNearRankingsResult.class);
        session.execute(task);
    }

    /**
     * 指定したスコア付近のランキングを取得<br>
     *   <br>
     *   このAPIはグローバルランキングのときのみ使用できます<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeNearRankingsResult describeNearRankings(
            DescribeNearRankingsRequest request
    ) {
        final AsyncResult<DescribeNearRankingsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeNearRankingsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRankingTask extends Gs2RestSessionTask<GetRankingResult> {
        private GetRankingRequest request;

        public GetRankingTask(
            GetRankingRequest request,
            AsyncAction<AsyncResult<GetRankingResult>> userCallback,
            Class<GetRankingResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/category/{categoryName}/ranking/scorer/{scorerUserId}/score/{uniqueId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{scorerUserId}", this.request.getScorerUserId() == null|| this.request.getScorerUserId().length() == 0 ? "null" : String.valueOf(this.request.getScorerUserId()));
            url = url.replace("{uniqueId}", this.request.getUniqueId() == null|| this.request.getUniqueId().length() == 0 ? "null" : String.valueOf(this.request.getUniqueId()));

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
     * ランキングを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getRankingAsync(
            GetRankingRequest request,
            AsyncAction<AsyncResult<GetRankingResult>> callback
    ) {
        GetRankingTask task = new GetRankingTask(request, callback, GetRankingResult.class);
        session.execute(task);
    }

    /**
     * ランキングを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetRankingResult getRanking(
            GetRankingRequest request
    ) {
        final AsyncResult<GetRankingResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRankingAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRankingByUserIdTask extends Gs2RestSessionTask<GetRankingByUserIdResult> {
        private GetRankingByUserIdRequest request;

        public GetRankingByUserIdTask(
            GetRankingByUserIdRequest request,
            AsyncAction<AsyncResult<GetRankingByUserIdResult>> userCallback,
            Class<GetRankingByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/category/{categoryName}/ranking/scorer/{scorerUserId}/score/{uniqueId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{scorerUserId}", this.request.getScorerUserId() == null|| this.request.getScorerUserId().length() == 0 ? "null" : String.valueOf(this.request.getScorerUserId()));
            url = url.replace("{uniqueId}", this.request.getUniqueId() == null|| this.request.getUniqueId().length() == 0 ? "null" : String.valueOf(this.request.getUniqueId()));

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
     * ランキングを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getRankingByUserIdAsync(
            GetRankingByUserIdRequest request,
            AsyncAction<AsyncResult<GetRankingByUserIdResult>> callback
    ) {
        GetRankingByUserIdTask task = new GetRankingByUserIdTask(request, callback, GetRankingByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ランキングを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetRankingByUserIdResult getRankingByUserId(
            GetRankingByUserIdRequest request
    ) {
        final AsyncResult<GetRankingByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRankingByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PutScoreTask extends Gs2RestSessionTask<PutScoreResult> {
        private PutScoreRequest request;

        public PutScoreTask(
            PutScoreRequest request,
            AsyncAction<AsyncResult<PutScoreResult>> userCallback,
            Class<PutScoreResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/category/{categoryName}/ranking";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getScore() != null) {
                json.put("score", this.request.getScore());
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
     * スコアを登録<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void putScoreAsync(
            PutScoreRequest request,
            AsyncAction<AsyncResult<PutScoreResult>> callback
    ) {
        PutScoreTask task = new PutScoreTask(request, callback, PutScoreResult.class);
        session.execute(task);
    }

    /**
     * スコアを登録<br>
     *
     * @param request リクエストパラメータ
     */
    public PutScoreResult putScore(
            PutScoreRequest request
    ) {
        final AsyncResult<PutScoreResult>[] resultAsyncResult = new AsyncResult[]{null};
        putScoreAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class PutScoreByUserIdTask extends Gs2RestSessionTask<PutScoreByUserIdResult> {
        private PutScoreByUserIdRequest request;

        public PutScoreByUserIdTask(
            PutScoreByUserIdRequest request,
            AsyncAction<AsyncResult<PutScoreByUserIdResult>> userCallback,
            Class<PutScoreByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/category/{categoryName}/ranking";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{categoryName}", this.request.getCategoryName() == null|| this.request.getCategoryName().length() == 0 ? "null" : String.valueOf(this.request.getCategoryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getScore() != null) {
                json.put("score", this.request.getScore());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }

            builder
                .build()
                .send();
        }
    }

    /**
     * ユーザーIDを指定してスコアを登録<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void putScoreByUserIdAsync(
            PutScoreByUserIdRequest request,
            AsyncAction<AsyncResult<PutScoreByUserIdResult>> callback
    ) {
        PutScoreByUserIdTask task = new PutScoreByUserIdTask(request, callback, PutScoreByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してスコアを登録<br>
     *
     * @param request リクエストパラメータ
     */
    public PutScoreByUserIdResult putScoreByUserId(
            PutScoreByUserIdRequest request
    ) {
        final AsyncResult<PutScoreByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        putScoreByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "ranking")
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
     * 現在有効なランキング設定のマスターデータをエクスポートします<br>
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
     * 現在有効なランキング設定のマスターデータをエクスポートします<br>
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

    class GetCurrentRankingMasterTask extends Gs2RestSessionTask<GetCurrentRankingMasterResult> {
        private GetCurrentRankingMasterRequest request;

        public GetCurrentRankingMasterTask(
            GetCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentRankingMasterResult>> userCallback,
            Class<GetCurrentRankingMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
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
     * 現在有効なランキング設定を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentRankingMasterAsync(
            GetCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentRankingMasterResult>> callback
    ) {
        GetCurrentRankingMasterTask task = new GetCurrentRankingMasterTask(request, callback, GetCurrentRankingMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なランキング設定を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCurrentRankingMasterResult getCurrentRankingMaster(
            GetCurrentRankingMasterRequest request
    ) {
        final AsyncResult<GetCurrentRankingMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentRankingMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentRankingMasterTask extends Gs2RestSessionTask<UpdateCurrentRankingMasterResult> {
        private UpdateCurrentRankingMasterRequest request;

        public UpdateCurrentRankingMasterTask(
            UpdateCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRankingMasterResult>> userCallback,
            Class<UpdateCurrentRankingMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
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
     * 現在有効なランキング設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentRankingMasterAsync(
            UpdateCurrentRankingMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRankingMasterResult>> callback
    ) {
        UpdateCurrentRankingMasterTask task = new UpdateCurrentRankingMasterTask(request, callback, UpdateCurrentRankingMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なランキング設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentRankingMasterResult updateCurrentRankingMaster(
            UpdateCurrentRankingMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentRankingMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentRankingMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentRankingMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentRankingMasterFromGitHubResult> {
        private UpdateCurrentRankingMasterFromGitHubRequest request;

        public UpdateCurrentRankingMasterFromGitHubTask(
            UpdateCurrentRankingMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRankingMasterFromGitHubResult>> userCallback,
            Class<UpdateCurrentRankingMasterFromGitHubResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "ranking")
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
     * 現在有効なランキング設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentRankingMasterFromGitHubAsync(
            UpdateCurrentRankingMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentRankingMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentRankingMasterFromGitHubTask task = new UpdateCurrentRankingMasterFromGitHubTask(request, callback, UpdateCurrentRankingMasterFromGitHubResult.class);
        session.execute(task);
    }

    /**
     * 現在有効なランキング設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentRankingMasterFromGitHubResult updateCurrentRankingMasterFromGitHub(
            UpdateCurrentRankingMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentRankingMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentRankingMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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