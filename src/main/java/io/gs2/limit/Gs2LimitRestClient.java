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

package io.gs2.limit;

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
import io.gs2.limit.request.*;
import io.gs2.limit.result.*;
import io.gs2.limit.model.*;

/**
 * GS2 Limit API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2LimitRestClient extends AbstractGs2Client<Gs2LimitRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2LimitRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "limit")
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
                .replace("{service}", "limit")
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
                .replace("{service}", "limit")
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
                .replace("{service}", "limit")
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
                .replace("{service}", "limit")
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
                .replace("{service}", "limit")
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

    class DescribeCountersTask extends Gs2RestSessionTask<DescribeCountersResult> {
        private DescribeCountersRequest request;

        public DescribeCountersTask(
            DescribeCountersRequest request,
            AsyncAction<AsyncResult<DescribeCountersResult>> userCallback,
            Class<DescribeCountersResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/counter";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getLimitName() != null) {
                queryStrings.add("limitName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getLimitName()))));
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
     * カウンターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeCountersAsync(
            DescribeCountersRequest request,
            AsyncAction<AsyncResult<DescribeCountersResult>> callback
    ) {
        DescribeCountersTask task = new DescribeCountersTask(request, callback, DescribeCountersResult.class);
        session.execute(task);
    }

    /**
     * カウンターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeCountersResult describeCounters(
            DescribeCountersRequest request
    ) {
        final AsyncResult<DescribeCountersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCountersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeCountersByUserIdTask extends Gs2RestSessionTask<DescribeCountersByUserIdResult> {
        private DescribeCountersByUserIdRequest request;

        public DescribeCountersByUserIdTask(
            DescribeCountersByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeCountersByUserIdResult>> userCallback,
            Class<DescribeCountersByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getLimitName() != null) {
                queryStrings.add("limitName=" + EncodingUtil.urlEncode((String.valueOf(this.request.getLimitName()))));
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
     * カウンターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeCountersByUserIdAsync(
            DescribeCountersByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeCountersByUserIdResult>> callback
    ) {
        DescribeCountersByUserIdTask task = new DescribeCountersByUserIdTask(request, callback, DescribeCountersByUserIdResult.class);
        session.execute(task);
    }

    /**
     * カウンターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeCountersByUserIdResult describeCountersByUserId(
            DescribeCountersByUserIdRequest request
    ) {
        final AsyncResult<DescribeCountersByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCountersByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCounterTask extends Gs2RestSessionTask<GetCounterResult> {
        private GetCounterRequest request;

        public GetCounterTask(
            GetCounterRequest request,
            AsyncAction<AsyncResult<GetCounterResult>> userCallback,
            Class<GetCounterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/counter/{limitName}/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{limitName}", this.request.getLimitName() == null|| this.request.getLimitName().length() == 0 ? "null" : String.valueOf(this.request.getLimitName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null|| this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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
     * カウンターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCounterAsync(
            GetCounterRequest request,
            AsyncAction<AsyncResult<GetCounterResult>> callback
    ) {
        GetCounterTask task = new GetCounterTask(request, callback, GetCounterResult.class);
        session.execute(task);
    }

    /**
     * カウンターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCounterResult getCounter(
            GetCounterRequest request
    ) {
        final AsyncResult<GetCounterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCounterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetCounterByUserIdTask extends Gs2RestSessionTask<GetCounterByUserIdResult> {
        private GetCounterByUserIdRequest request;

        public GetCounterByUserIdTask(
            GetCounterByUserIdRequest request,
            AsyncAction<AsyncResult<GetCounterByUserIdResult>> userCallback,
            Class<GetCounterByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter/{limitName}/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{limitName}", this.request.getLimitName() == null|| this.request.getLimitName().length() == 0 ? "null" : String.valueOf(this.request.getLimitName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{counterName}", this.request.getCounterName() == null|| this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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
     * ユーザIDを指定してカウンターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCounterByUserIdAsync(
            GetCounterByUserIdRequest request,
            AsyncAction<AsyncResult<GetCounterByUserIdResult>> callback
    ) {
        GetCounterByUserIdTask task = new GetCounterByUserIdTask(request, callback, GetCounterByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してカウンターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCounterByUserIdResult getCounterByUserId(
            GetCounterByUserIdRequest request
    ) {
        final AsyncResult<GetCounterByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCounterByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CountUpTask extends Gs2RestSessionTask<CountUpResult> {
        private CountUpRequest request;

        public CountUpTask(
            CountUpRequest request,
            AsyncAction<AsyncResult<CountUpResult>> userCallback,
            Class<CountUpResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/counter/{limitName}/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{limitName}", this.request.getLimitName() == null|| this.request.getLimitName().length() == 0 ? "null" : String.valueOf(this.request.getLimitName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null|| this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCountUpValue() != null) {
                json.put("countUpValue", this.request.getCountUpValue());
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
     * カウントアップ<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void countUpAsync(
            CountUpRequest request,
            AsyncAction<AsyncResult<CountUpResult>> callback
    ) {
        CountUpTask task = new CountUpTask(request, callback, CountUpResult.class);
        session.execute(task);
    }

    /**
     * カウントアップ<br>
     *
     * @param request リクエストパラメータ
     */
    public CountUpResult countUp(
            CountUpRequest request
    ) {
        final AsyncResult<CountUpResult>[] resultAsyncResult = new AsyncResult[]{null};
        countUpAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CountUpByUserIdTask extends Gs2RestSessionTask<CountUpByUserIdResult> {
        private CountUpByUserIdRequest request;

        public CountUpByUserIdTask(
            CountUpByUserIdRequest request,
            AsyncAction<AsyncResult<CountUpByUserIdResult>> userCallback,
            Class<CountUpByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter/{limitName}/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{limitName}", this.request.getLimitName() == null|| this.request.getLimitName().length() == 0 ? "null" : String.valueOf(this.request.getLimitName()));
            url = url.replace("{counterName}", this.request.getCounterName() == null|| this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCountUpValue() != null) {
                json.put("countUpValue", this.request.getCountUpValue());
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
     * ユーザIDを指定してカウントアップ<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void countUpByUserIdAsync(
            CountUpByUserIdRequest request,
            AsyncAction<AsyncResult<CountUpByUserIdResult>> callback
    ) {
        CountUpByUserIdTask task = new CountUpByUserIdTask(request, callback, CountUpByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してカウントアップ<br>
     *
     * @param request リクエストパラメータ
     */
    public CountUpByUserIdResult countUpByUserId(
            CountUpByUserIdRequest request
    ) {
        final AsyncResult<CountUpByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        countUpByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteCounterByUserIdTask extends Gs2RestSessionTask<DeleteCounterByUserIdResult> {
        private DeleteCounterByUserIdRequest request;

        public DeleteCounterByUserIdTask(
            DeleteCounterByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteCounterByUserIdResult>> userCallback,
            Class<DeleteCounterByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/counter/{limitName}/{counterName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{limitName}", this.request.getLimitName() == null|| this.request.getLimitName().length() == 0 ? "null" : String.valueOf(this.request.getLimitName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{counterName}", this.request.getCounterName() == null|| this.request.getCounterName().length() == 0 ? "null" : String.valueOf(this.request.getCounterName()));

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
     * ユーザIDを指定してカウンターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteCounterByUserIdAsync(
            DeleteCounterByUserIdRequest request,
            AsyncAction<AsyncResult<DeleteCounterByUserIdResult>> callback
    ) {
        DeleteCounterByUserIdTask task = new DeleteCounterByUserIdTask(request, callback, DeleteCounterByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してカウンターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteCounterByUserIdResult deleteCounterByUserId(
            DeleteCounterByUserIdRequest request
    ) {
        final AsyncResult<DeleteCounterByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteCounterByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CountUpByStampTaskTask extends Gs2RestSessionTask<CountUpByStampTaskResult> {
        private CountUpByStampTaskRequest request;

        public CountUpByStampTaskTask(
            CountUpByStampTaskRequest request,
            AsyncAction<AsyncResult<CountUpByStampTaskResult>> userCallback,
            Class<CountUpByStampTaskResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/counter/increase";

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
     * スタンプシートでカウントアップ<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void countUpByStampTaskAsync(
            CountUpByStampTaskRequest request,
            AsyncAction<AsyncResult<CountUpByStampTaskResult>> callback
    ) {
        CountUpByStampTaskTask task = new CountUpByStampTaskTask(request, callback, CountUpByStampTaskResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートでカウントアップ<br>
     *
     * @param request リクエストパラメータ
     */
    public CountUpByStampTaskResult countUpByStampTask(
            CountUpByStampTaskRequest request
    ) {
        final AsyncResult<CountUpByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        countUpByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteByStampSheetTask extends Gs2RestSessionTask<DeleteByStampSheetResult> {
        private DeleteByStampSheetRequest request;

        public DeleteByStampSheetTask(
            DeleteByStampSheetRequest request,
            AsyncAction<AsyncResult<DeleteByStampSheetResult>> userCallback,
            Class<DeleteByStampSheetResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/counter/delete";

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
     * スタンプシートでカウンターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteByStampSheetAsync(
            DeleteByStampSheetRequest request,
            AsyncAction<AsyncResult<DeleteByStampSheetResult>> callback
    ) {
        DeleteByStampSheetTask task = new DeleteByStampSheetTask(request, callback, DeleteByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートでカウンターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteByStampSheetResult deleteByStampSheet(
            DeleteByStampSheetRequest request
    ) {
        final AsyncResult<DeleteByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeLimitModelMastersTask extends Gs2RestSessionTask<DescribeLimitModelMastersResult> {
        private DescribeLimitModelMastersRequest request;

        public DescribeLimitModelMastersTask(
            DescribeLimitModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeLimitModelMastersResult>> userCallback,
            Class<DescribeLimitModelMastersResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/limit";

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
     * 回数制限の種類マスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeLimitModelMastersAsync(
            DescribeLimitModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeLimitModelMastersResult>> callback
    ) {
        DescribeLimitModelMastersTask task = new DescribeLimitModelMastersTask(request, callback, DescribeLimitModelMastersResult.class);
        session.execute(task);
    }

    /**
     * 回数制限の種類マスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeLimitModelMastersResult describeLimitModelMasters(
            DescribeLimitModelMastersRequest request
    ) {
        final AsyncResult<DescribeLimitModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeLimitModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateLimitModelMasterTask extends Gs2RestSessionTask<CreateLimitModelMasterResult> {
        private CreateLimitModelMasterRequest request;

        public CreateLimitModelMasterTask(
            CreateLimitModelMasterRequest request,
            AsyncAction<AsyncResult<CreateLimitModelMasterResult>> userCallback,
            Class<CreateLimitModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/limit";

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
            if (this.request.getResetType() != null) {
                json.put("resetType", this.request.getResetType());
            }
            if (this.request.getResetDayOfMonth() != null) {
                json.put("resetDayOfMonth", this.request.getResetDayOfMonth());
            }
            if (this.request.getResetDayOfWeek() != null) {
                json.put("resetDayOfWeek", this.request.getResetDayOfWeek());
            }
            if (this.request.getResetHour() != null) {
                json.put("resetHour", this.request.getResetHour());
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
     * 回数制限の種類マスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createLimitModelMasterAsync(
            CreateLimitModelMasterRequest request,
            AsyncAction<AsyncResult<CreateLimitModelMasterResult>> callback
    ) {
        CreateLimitModelMasterTask task = new CreateLimitModelMasterTask(request, callback, CreateLimitModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 回数制限の種類マスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateLimitModelMasterResult createLimitModelMaster(
            CreateLimitModelMasterRequest request
    ) {
        final AsyncResult<CreateLimitModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createLimitModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetLimitModelMasterTask extends Gs2RestSessionTask<GetLimitModelMasterResult> {
        private GetLimitModelMasterRequest request;

        public GetLimitModelMasterTask(
            GetLimitModelMasterRequest request,
            AsyncAction<AsyncResult<GetLimitModelMasterResult>> userCallback,
            Class<GetLimitModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/limit/{limitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{limitName}", this.request.getLimitName() == null|| this.request.getLimitName().length() == 0 ? "null" : String.valueOf(this.request.getLimitName()));

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
     * 回数制限の種類マスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getLimitModelMasterAsync(
            GetLimitModelMasterRequest request,
            AsyncAction<AsyncResult<GetLimitModelMasterResult>> callback
    ) {
        GetLimitModelMasterTask task = new GetLimitModelMasterTask(request, callback, GetLimitModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 回数制限の種類マスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetLimitModelMasterResult getLimitModelMaster(
            GetLimitModelMasterRequest request
    ) {
        final AsyncResult<GetLimitModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getLimitModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateLimitModelMasterTask extends Gs2RestSessionTask<UpdateLimitModelMasterResult> {
        private UpdateLimitModelMasterRequest request;

        public UpdateLimitModelMasterTask(
            UpdateLimitModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateLimitModelMasterResult>> userCallback,
            Class<UpdateLimitModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/limit/{limitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{limitName}", this.request.getLimitName() == null|| this.request.getLimitName().length() == 0 ? "null" : String.valueOf(this.request.getLimitName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getResetType() != null) {
                json.put("resetType", this.request.getResetType());
            }
            if (this.request.getResetDayOfMonth() != null) {
                json.put("resetDayOfMonth", this.request.getResetDayOfMonth());
            }
            if (this.request.getResetDayOfWeek() != null) {
                json.put("resetDayOfWeek", this.request.getResetDayOfWeek());
            }
            if (this.request.getResetHour() != null) {
                json.put("resetHour", this.request.getResetHour());
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
     * 回数制限の種類マスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateLimitModelMasterAsync(
            UpdateLimitModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateLimitModelMasterResult>> callback
    ) {
        UpdateLimitModelMasterTask task = new UpdateLimitModelMasterTask(request, callback, UpdateLimitModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 回数制限の種類マスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateLimitModelMasterResult updateLimitModelMaster(
            UpdateLimitModelMasterRequest request
    ) {
        final AsyncResult<UpdateLimitModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateLimitModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteLimitModelMasterTask extends Gs2RestSessionTask<DeleteLimitModelMasterResult> {
        private DeleteLimitModelMasterRequest request;

        public DeleteLimitModelMasterTask(
            DeleteLimitModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteLimitModelMasterResult>> userCallback,
            Class<DeleteLimitModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/limit/{limitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{limitName}", this.request.getLimitName() == null|| this.request.getLimitName().length() == 0 ? "null" : String.valueOf(this.request.getLimitName()));

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
     * 回数制限の種類マスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteLimitModelMasterAsync(
            DeleteLimitModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteLimitModelMasterResult>> callback
    ) {
        DeleteLimitModelMasterTask task = new DeleteLimitModelMasterTask(request, callback, DeleteLimitModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 回数制限の種類マスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteLimitModelMasterResult deleteLimitModelMaster(
            DeleteLimitModelMasterRequest request
    ) {
        final AsyncResult<DeleteLimitModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteLimitModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "limit")
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
     * 現在有効な回数制限設定のマスターデータをエクスポートします<br>
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
     * 現在有効な回数制限設定のマスターデータをエクスポートします<br>
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

    class GetCurrentLimitMasterTask extends Gs2RestSessionTask<GetCurrentLimitMasterResult> {
        private GetCurrentLimitMasterRequest request;

        public GetCurrentLimitMasterTask(
            GetCurrentLimitMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentLimitMasterResult>> userCallback,
            Class<GetCurrentLimitMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
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
     * 現在有効な回数制限設定を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentLimitMasterAsync(
            GetCurrentLimitMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentLimitMasterResult>> callback
    ) {
        GetCurrentLimitMasterTask task = new GetCurrentLimitMasterTask(request, callback, GetCurrentLimitMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な回数制限設定を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCurrentLimitMasterResult getCurrentLimitMaster(
            GetCurrentLimitMasterRequest request
    ) {
        final AsyncResult<GetCurrentLimitMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentLimitMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentLimitMasterTask extends Gs2RestSessionTask<UpdateCurrentLimitMasterResult> {
        private UpdateCurrentLimitMasterRequest request;

        public UpdateCurrentLimitMasterTask(
            UpdateCurrentLimitMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentLimitMasterResult>> userCallback,
            Class<UpdateCurrentLimitMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
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
     * 現在有効な回数制限設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentLimitMasterAsync(
            UpdateCurrentLimitMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentLimitMasterResult>> callback
    ) {
        UpdateCurrentLimitMasterTask task = new UpdateCurrentLimitMasterTask(request, callback, UpdateCurrentLimitMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な回数制限設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentLimitMasterResult updateCurrentLimitMaster(
            UpdateCurrentLimitMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentLimitMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentLimitMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentLimitMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentLimitMasterFromGitHubResult> {
        private UpdateCurrentLimitMasterFromGitHubRequest request;

        public UpdateCurrentLimitMasterFromGitHubTask(
            UpdateCurrentLimitMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentLimitMasterFromGitHubResult>> userCallback,
            Class<UpdateCurrentLimitMasterFromGitHubResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
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
     * 現在有効な回数制限設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentLimitMasterFromGitHubAsync(
            UpdateCurrentLimitMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentLimitMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentLimitMasterFromGitHubTask task = new UpdateCurrentLimitMasterFromGitHubTask(request, callback, UpdateCurrentLimitMasterFromGitHubResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な回数制限設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentLimitMasterFromGitHubResult updateCurrentLimitMasterFromGitHub(
            UpdateCurrentLimitMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentLimitMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentLimitMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeLimitModelsTask extends Gs2RestSessionTask<DescribeLimitModelsResult> {
        private DescribeLimitModelsRequest request;

        public DescribeLimitModelsTask(
            DescribeLimitModelsRequest request,
            AsyncAction<AsyncResult<DescribeLimitModelsResult>> userCallback,
            Class<DescribeLimitModelsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/limit";

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
     * 回数制限の種類の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeLimitModelsAsync(
            DescribeLimitModelsRequest request,
            AsyncAction<AsyncResult<DescribeLimitModelsResult>> callback
    ) {
        DescribeLimitModelsTask task = new DescribeLimitModelsTask(request, callback, DescribeLimitModelsResult.class);
        session.execute(task);
    }

    /**
     * 回数制限の種類の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeLimitModelsResult describeLimitModels(
            DescribeLimitModelsRequest request
    ) {
        final AsyncResult<DescribeLimitModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeLimitModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetLimitModelTask extends Gs2RestSessionTask<GetLimitModelResult> {
        private GetLimitModelRequest request;

        public GetLimitModelTask(
            GetLimitModelRequest request,
            AsyncAction<AsyncResult<GetLimitModelResult>> userCallback,
            Class<GetLimitModelResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "limit")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/limit/{limitName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{limitName}", this.request.getLimitName() == null|| this.request.getLimitName().length() == 0 ? "null" : String.valueOf(this.request.getLimitName()));

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
     * 回数制限の種類を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getLimitModelAsync(
            GetLimitModelRequest request,
            AsyncAction<AsyncResult<GetLimitModelResult>> callback
    ) {
        GetLimitModelTask task = new GetLimitModelTask(request, callback, GetLimitModelResult.class);
        session.execute(task);
    }

    /**
     * 回数制限の種類を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetLimitModelResult getLimitModel(
            GetLimitModelRequest request
    ) {
        final AsyncResult<GetLimitModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getLimitModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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