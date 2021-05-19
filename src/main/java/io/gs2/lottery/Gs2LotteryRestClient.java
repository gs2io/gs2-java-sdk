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

package io.gs2.lottery;

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
import io.gs2.lottery.request.*;
import io.gs2.lottery.result.*;
import io.gs2.lottery.model.*;

/**
 * GS2 Lottery API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2LotteryRestClient extends AbstractGs2Client<Gs2LotteryRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2LotteryRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "lottery")
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
                .replace("{service}", "lottery")
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
            if (this.request.getQueueNamespaceId() != null) {
                json.put("queueNamespaceId", this.request.getQueueNamespaceId());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getLotteryTriggerScriptId() != null) {
                json.put("lotteryTriggerScriptId", this.request.getLotteryTriggerScriptId());
            }
            if (this.request.getChoicePrizeTableScriptId() != null) {
                json.put("choicePrizeTableScriptId", this.request.getChoicePrizeTableScriptId());
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
                .replace("{service}", "lottery")
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
                .replace("{service}", "lottery")
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
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getQueueNamespaceId() != null) {
                json.put("queueNamespaceId", this.request.getQueueNamespaceId());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getLotteryTriggerScriptId() != null) {
                json.put("lotteryTriggerScriptId", this.request.getLotteryTriggerScriptId());
            }
            if (this.request.getChoicePrizeTableScriptId() != null) {
                json.put("choicePrizeTableScriptId", this.request.getChoicePrizeTableScriptId());
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
                .replace("{service}", "lottery")
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

    class DescribeLotteryModelMastersTask extends Gs2RestSessionTask<DescribeLotteryModelMastersResult> {
        private DescribeLotteryModelMastersRequest request;

        public DescribeLotteryModelMastersTask(
            DescribeLotteryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeLotteryModelMastersResult>> userCallback,
            Class<DescribeLotteryModelMastersResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/lottery";

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
     * 抽選の種類マスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeLotteryModelMastersAsync(
            DescribeLotteryModelMastersRequest request,
            AsyncAction<AsyncResult<DescribeLotteryModelMastersResult>> callback
    ) {
        DescribeLotteryModelMastersTask task = new DescribeLotteryModelMastersTask(request, callback, DescribeLotteryModelMastersResult.class);
        session.execute(task);
    }

    /**
     * 抽選の種類マスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeLotteryModelMastersResult describeLotteryModelMasters(
            DescribeLotteryModelMastersRequest request
    ) {
        final AsyncResult<DescribeLotteryModelMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeLotteryModelMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateLotteryModelMasterTask extends Gs2RestSessionTask<CreateLotteryModelMasterResult> {
        private CreateLotteryModelMasterRequest request;

        public CreateLotteryModelMasterTask(
            CreateLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateLotteryModelMasterResult>> userCallback,
            Class<CreateLotteryModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/lottery";

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
            if (this.request.getMode() != null) {
                json.put("mode", this.request.getMode());
            }
            if (this.request.getMethod() != null) {
                json.put("method", this.request.getMethod());
            }
            if (this.request.getPrizeTableName() != null) {
                json.put("prizeTableName", this.request.getPrizeTableName());
            }
            if (this.request.getChoicePrizeTableScriptId() != null) {
                json.put("choicePrizeTableScriptId", this.request.getChoicePrizeTableScriptId());
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
     * 抽選の種類マスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createLotteryModelMasterAsync(
            CreateLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<CreateLotteryModelMasterResult>> callback
    ) {
        CreateLotteryModelMasterTask task = new CreateLotteryModelMasterTask(request, callback, CreateLotteryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 抽選の種類マスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateLotteryModelMasterResult createLotteryModelMaster(
            CreateLotteryModelMasterRequest request
    ) {
        final AsyncResult<CreateLotteryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createLotteryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetLotteryModelMasterTask extends Gs2RestSessionTask<GetLotteryModelMasterResult> {
        private GetLotteryModelMasterRequest request;

        public GetLotteryModelMasterTask(
            GetLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<GetLotteryModelMasterResult>> userCallback,
            Class<GetLotteryModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/lottery/{lotteryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null|| this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));

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
     * 抽選の種類マスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getLotteryModelMasterAsync(
            GetLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<GetLotteryModelMasterResult>> callback
    ) {
        GetLotteryModelMasterTask task = new GetLotteryModelMasterTask(request, callback, GetLotteryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 抽選の種類マスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetLotteryModelMasterResult getLotteryModelMaster(
            GetLotteryModelMasterRequest request
    ) {
        final AsyncResult<GetLotteryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getLotteryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateLotteryModelMasterTask extends Gs2RestSessionTask<UpdateLotteryModelMasterResult> {
        private UpdateLotteryModelMasterRequest request;

        public UpdateLotteryModelMasterTask(
            UpdateLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateLotteryModelMasterResult>> userCallback,
            Class<UpdateLotteryModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/lottery/{lotteryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null|| this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getMode() != null) {
                json.put("mode", this.request.getMode());
            }
            if (this.request.getMethod() != null) {
                json.put("method", this.request.getMethod());
            }
            if (this.request.getPrizeTableName() != null) {
                json.put("prizeTableName", this.request.getPrizeTableName());
            }
            if (this.request.getChoicePrizeTableScriptId() != null) {
                json.put("choicePrizeTableScriptId", this.request.getChoicePrizeTableScriptId());
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
     * 抽選の種類マスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateLotteryModelMasterAsync(
            UpdateLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<UpdateLotteryModelMasterResult>> callback
    ) {
        UpdateLotteryModelMasterTask task = new UpdateLotteryModelMasterTask(request, callback, UpdateLotteryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 抽選の種類マスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateLotteryModelMasterResult updateLotteryModelMaster(
            UpdateLotteryModelMasterRequest request
    ) {
        final AsyncResult<UpdateLotteryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateLotteryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteLotteryModelMasterTask extends Gs2RestSessionTask<DeleteLotteryModelMasterResult> {
        private DeleteLotteryModelMasterRequest request;

        public DeleteLotteryModelMasterTask(
            DeleteLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteLotteryModelMasterResult>> userCallback,
            Class<DeleteLotteryModelMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/lottery/{lotteryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null|| this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));

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
     * 抽選の種類マスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteLotteryModelMasterAsync(
            DeleteLotteryModelMasterRequest request,
            AsyncAction<AsyncResult<DeleteLotteryModelMasterResult>> callback
    ) {
        DeleteLotteryModelMasterTask task = new DeleteLotteryModelMasterTask(request, callback, DeleteLotteryModelMasterResult.class);
        session.execute(task);
    }

    /**
     * 抽選の種類マスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteLotteryModelMasterResult deleteLotteryModelMaster(
            DeleteLotteryModelMasterRequest request
    ) {
        final AsyncResult<DeleteLotteryModelMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteLotteryModelMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribePrizeTableMastersTask extends Gs2RestSessionTask<DescribePrizeTableMastersResult> {
        private DescribePrizeTableMastersRequest request;

        public DescribePrizeTableMastersTask(
            DescribePrizeTableMastersRequest request,
            AsyncAction<AsyncResult<DescribePrizeTableMastersResult>> userCallback,
            Class<DescribePrizeTableMastersResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/table";

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
     * 排出確率テーブルマスターの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describePrizeTableMastersAsync(
            DescribePrizeTableMastersRequest request,
            AsyncAction<AsyncResult<DescribePrizeTableMastersResult>> callback
    ) {
        DescribePrizeTableMastersTask task = new DescribePrizeTableMastersTask(request, callback, DescribePrizeTableMastersResult.class);
        session.execute(task);
    }

    /**
     * 排出確率テーブルマスターの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribePrizeTableMastersResult describePrizeTableMasters(
            DescribePrizeTableMastersRequest request
    ) {
        final AsyncResult<DescribePrizeTableMastersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describePrizeTableMastersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreatePrizeTableMasterTask extends Gs2RestSessionTask<CreatePrizeTableMasterResult> {
        private CreatePrizeTableMasterRequest request;

        public CreatePrizeTableMasterTask(
            CreatePrizeTableMasterRequest request,
            AsyncAction<AsyncResult<CreatePrizeTableMasterResult>> userCallback,
            Class<CreatePrizeTableMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/table";

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
            if (this.request.getPrizes() != null) {
                JSONArray array = new JSONArray();
                for(Prize item : this.request.getPrizes())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("prizes", array);
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
     * 排出確率テーブルマスターを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createPrizeTableMasterAsync(
            CreatePrizeTableMasterRequest request,
            AsyncAction<AsyncResult<CreatePrizeTableMasterResult>> callback
    ) {
        CreatePrizeTableMasterTask task = new CreatePrizeTableMasterTask(request, callback, CreatePrizeTableMasterResult.class);
        session.execute(task);
    }

    /**
     * 排出確率テーブルマスターを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreatePrizeTableMasterResult createPrizeTableMaster(
            CreatePrizeTableMasterRequest request
    ) {
        final AsyncResult<CreatePrizeTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        createPrizeTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPrizeTableMasterTask extends Gs2RestSessionTask<GetPrizeTableMasterResult> {
        private GetPrizeTableMasterRequest request;

        public GetPrizeTableMasterTask(
            GetPrizeTableMasterRequest request,
            AsyncAction<AsyncResult<GetPrizeTableMasterResult>> userCallback,
            Class<GetPrizeTableMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/table/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null|| this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));

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
     * 排出確率テーブルマスターを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getPrizeTableMasterAsync(
            GetPrizeTableMasterRequest request,
            AsyncAction<AsyncResult<GetPrizeTableMasterResult>> callback
    ) {
        GetPrizeTableMasterTask task = new GetPrizeTableMasterTask(request, callback, GetPrizeTableMasterResult.class);
        session.execute(task);
    }

    /**
     * 排出確率テーブルマスターを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetPrizeTableMasterResult getPrizeTableMaster(
            GetPrizeTableMasterRequest request
    ) {
        final AsyncResult<GetPrizeTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPrizeTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdatePrizeTableMasterTask extends Gs2RestSessionTask<UpdatePrizeTableMasterResult> {
        private UpdatePrizeTableMasterRequest request;

        public UpdatePrizeTableMasterTask(
            UpdatePrizeTableMasterRequest request,
            AsyncAction<AsyncResult<UpdatePrizeTableMasterResult>> userCallback,
            Class<UpdatePrizeTableMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/table/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null|| this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getMetadata() != null) {
                json.put("metadata", this.request.getMetadata());
            }
            if (this.request.getPrizes() != null) {
                JSONArray array = new JSONArray();
                for(Prize item : this.request.getPrizes())
                {
                    try {
                        array.put(new JSONObject(mapper.writeValueAsString(item)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.put("prizes", array);
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
     * 排出確率テーブルマスターを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updatePrizeTableMasterAsync(
            UpdatePrizeTableMasterRequest request,
            AsyncAction<AsyncResult<UpdatePrizeTableMasterResult>> callback
    ) {
        UpdatePrizeTableMasterTask task = new UpdatePrizeTableMasterTask(request, callback, UpdatePrizeTableMasterResult.class);
        session.execute(task);
    }

    /**
     * 排出確率テーブルマスターを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdatePrizeTableMasterResult updatePrizeTableMaster(
            UpdatePrizeTableMasterRequest request
    ) {
        final AsyncResult<UpdatePrizeTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updatePrizeTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeletePrizeTableMasterTask extends Gs2RestSessionTask<DeletePrizeTableMasterResult> {
        private DeletePrizeTableMasterRequest request;

        public DeletePrizeTableMasterTask(
            DeletePrizeTableMasterRequest request,
            AsyncAction<AsyncResult<DeletePrizeTableMasterResult>> userCallback,
            Class<DeletePrizeTableMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/master/table/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null|| this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));

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
     * 排出確率テーブルマスターを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deletePrizeTableMasterAsync(
            DeletePrizeTableMasterRequest request,
            AsyncAction<AsyncResult<DeletePrizeTableMasterResult>> callback
    ) {
        DeletePrizeTableMasterTask task = new DeletePrizeTableMasterTask(request, callback, DeletePrizeTableMasterResult.class);
        session.execute(task);
    }

    /**
     * 排出確率テーブルマスターを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeletePrizeTableMasterResult deletePrizeTableMaster(
            DeletePrizeTableMasterRequest request
    ) {
        final AsyncResult<DeletePrizeTableMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        deletePrizeTableMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeBoxesTask extends Gs2RestSessionTask<DescribeBoxesResult> {
        private DescribeBoxesRequest request;

        public DescribeBoxesTask(
            DescribeBoxesRequest request,
            AsyncAction<AsyncResult<DescribeBoxesResult>> userCallback,
            Class<DescribeBoxesResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/box";

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
     * ボックスの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeBoxesAsync(
            DescribeBoxesRequest request,
            AsyncAction<AsyncResult<DescribeBoxesResult>> callback
    ) {
        DescribeBoxesTask task = new DescribeBoxesTask(request, callback, DescribeBoxesResult.class);
        session.execute(task);
    }

    /**
     * ボックスの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeBoxesResult describeBoxes(
            DescribeBoxesRequest request
    ) {
        final AsyncResult<DescribeBoxesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBoxesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeBoxesByUserIdTask extends Gs2RestSessionTask<DescribeBoxesByUserIdResult> {
        private DescribeBoxesByUserIdRequest request;

        public DescribeBoxesByUserIdTask(
            DescribeBoxesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeBoxesByUserIdResult>> userCallback,
            Class<DescribeBoxesByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/box";

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
     * ユーザIDを指定してボックスの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeBoxesByUserIdAsync(
            DescribeBoxesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeBoxesByUserIdResult>> callback
    ) {
        DescribeBoxesByUserIdTask task = new DescribeBoxesByUserIdTask(request, callback, DescribeBoxesByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してボックスの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeBoxesByUserIdResult describeBoxesByUserId(
            DescribeBoxesByUserIdRequest request
    ) {
        final AsyncResult<DescribeBoxesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBoxesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetBoxTask extends Gs2RestSessionTask<GetBoxResult> {
        private GetBoxRequest request;

        public GetBoxTask(
            GetBoxRequest request,
            AsyncAction<AsyncResult<GetBoxResult>> userCallback,
            Class<GetBoxResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/box/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null|| this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));

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
     * ボックスを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getBoxAsync(
            GetBoxRequest request,
            AsyncAction<AsyncResult<GetBoxResult>> callback
    ) {
        GetBoxTask task = new GetBoxTask(request, callback, GetBoxResult.class);
        session.execute(task);
    }

    /**
     * ボックスを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetBoxResult getBox(
            GetBoxRequest request
    ) {
        final AsyncResult<GetBoxResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBoxAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetBoxByUserIdTask extends Gs2RestSessionTask<GetBoxByUserIdResult> {
        private GetBoxByUserIdRequest request;

        public GetBoxByUserIdTask(
            GetBoxByUserIdRequest request,
            AsyncAction<AsyncResult<GetBoxByUserIdResult>> userCallback,
            Class<GetBoxByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/box/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null|| this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));
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
     * ユーザIDを指定してボックスを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getBoxByUserIdAsync(
            GetBoxByUserIdRequest request,
            AsyncAction<AsyncResult<GetBoxByUserIdResult>> callback
    ) {
        GetBoxByUserIdTask task = new GetBoxByUserIdTask(request, callback, GetBoxByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してボックスを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetBoxByUserIdResult getBoxByUserId(
            GetBoxByUserIdRequest request
    ) {
        final AsyncResult<GetBoxByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBoxByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetRawBoxByUserIdTask extends Gs2RestSessionTask<GetRawBoxByUserIdResult> {
        private GetRawBoxByUserIdRequest request;

        public GetRawBoxByUserIdTask(
            GetRawBoxByUserIdRequest request,
            AsyncAction<AsyncResult<GetRawBoxByUserIdResult>> userCallback,
            Class<GetRawBoxByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/box/{prizeTableName}/raw";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null|| this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));
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
     * ユーザIDを指定してボックスを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getRawBoxByUserIdAsync(
            GetRawBoxByUserIdRequest request,
            AsyncAction<AsyncResult<GetRawBoxByUserIdResult>> callback
    ) {
        GetRawBoxByUserIdTask task = new GetRawBoxByUserIdTask(request, callback, GetRawBoxByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してボックスを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetRawBoxByUserIdResult getRawBoxByUserId(
            GetRawBoxByUserIdRequest request
    ) {
        final AsyncResult<GetRawBoxByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getRawBoxByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ResetBoxTask extends Gs2RestSessionTask<ResetBoxResult> {
        private ResetBoxRequest request;

        public ResetBoxTask(
            ResetBoxRequest request,
            AsyncAction<AsyncResult<ResetBoxResult>> userCallback,
            Class<ResetBoxResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/box/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null|| this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));

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
     * ボックスをリセット<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void resetBoxAsync(
            ResetBoxRequest request,
            AsyncAction<AsyncResult<ResetBoxResult>> callback
    ) {
        ResetBoxTask task = new ResetBoxTask(request, callback, ResetBoxResult.class);
        session.execute(task);
    }

    /**
     * ボックスをリセット<br>
     *
     * @param request リクエストパラメータ
     */
    public ResetBoxResult resetBox(
            ResetBoxRequest request
    ) {
        final AsyncResult<ResetBoxResult>[] resultAsyncResult = new AsyncResult[]{null};
        resetBoxAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ResetBoxByUserIdTask extends Gs2RestSessionTask<ResetBoxByUserIdResult> {
        private ResetBoxByUserIdRequest request;

        public ResetBoxByUserIdTask(
            ResetBoxByUserIdRequest request,
            AsyncAction<AsyncResult<ResetBoxByUserIdResult>> userCallback,
            Class<ResetBoxByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/box/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null|| this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));
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
     * ユーザIDを指定してボックスをリセット<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void resetBoxByUserIdAsync(
            ResetBoxByUserIdRequest request,
            AsyncAction<AsyncResult<ResetBoxByUserIdResult>> callback
    ) {
        ResetBoxByUserIdTask task = new ResetBoxByUserIdTask(request, callback, ResetBoxByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定してボックスをリセット<br>
     *
     * @param request リクエストパラメータ
     */
    public ResetBoxByUserIdResult resetBoxByUserId(
            ResetBoxByUserIdRequest request
    ) {
        final AsyncResult<ResetBoxByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        resetBoxByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeLotteryModelsTask extends Gs2RestSessionTask<DescribeLotteryModelsResult> {
        private DescribeLotteryModelsRequest request;

        public DescribeLotteryModelsTask(
            DescribeLotteryModelsRequest request,
            AsyncAction<AsyncResult<DescribeLotteryModelsResult>> userCallback,
            Class<DescribeLotteryModelsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/lottery";

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
     * 抽選の種類の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeLotteryModelsAsync(
            DescribeLotteryModelsRequest request,
            AsyncAction<AsyncResult<DescribeLotteryModelsResult>> callback
    ) {
        DescribeLotteryModelsTask task = new DescribeLotteryModelsTask(request, callback, DescribeLotteryModelsResult.class);
        session.execute(task);
    }

    /**
     * 抽選の種類の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeLotteryModelsResult describeLotteryModels(
            DescribeLotteryModelsRequest request
    ) {
        final AsyncResult<DescribeLotteryModelsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeLotteryModelsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetLotteryModelTask extends Gs2RestSessionTask<GetLotteryModelResult> {
        private GetLotteryModelRequest request;

        public GetLotteryModelTask(
            GetLotteryModelRequest request,
            AsyncAction<AsyncResult<GetLotteryModelResult>> userCallback,
            Class<GetLotteryModelResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/lottery/{lotteryName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null|| this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));

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
     * 抽選の種類を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getLotteryModelAsync(
            GetLotteryModelRequest request,
            AsyncAction<AsyncResult<GetLotteryModelResult>> callback
    ) {
        GetLotteryModelTask task = new GetLotteryModelTask(request, callback, GetLotteryModelResult.class);
        session.execute(task);
    }

    /**
     * 抽選の種類を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetLotteryModelResult getLotteryModel(
            GetLotteryModelRequest request
    ) {
        final AsyncResult<GetLotteryModelResult>[] resultAsyncResult = new AsyncResult[]{null};
        getLotteryModelAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribePrizeTablesTask extends Gs2RestSessionTask<DescribePrizeTablesResult> {
        private DescribePrizeTablesRequest request;

        public DescribePrizeTablesTask(
            DescribePrizeTablesRequest request,
            AsyncAction<AsyncResult<DescribePrizeTablesResult>> userCallback,
            Class<DescribePrizeTablesResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/table";

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
     * 排出確率テーブルの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describePrizeTablesAsync(
            DescribePrizeTablesRequest request,
            AsyncAction<AsyncResult<DescribePrizeTablesResult>> callback
    ) {
        DescribePrizeTablesTask task = new DescribePrizeTablesTask(request, callback, DescribePrizeTablesResult.class);
        session.execute(task);
    }

    /**
     * 排出確率テーブルの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribePrizeTablesResult describePrizeTables(
            DescribePrizeTablesRequest request
    ) {
        final AsyncResult<DescribePrizeTablesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describePrizeTablesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPrizeTableTask extends Gs2RestSessionTask<GetPrizeTableResult> {
        private GetPrizeTableRequest request;

        public GetPrizeTableTask(
            GetPrizeTableRequest request,
            AsyncAction<AsyncResult<GetPrizeTableResult>> userCallback,
            Class<GetPrizeTableResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/table/{prizeTableName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{prizeTableName}", this.request.getPrizeTableName() == null|| this.request.getPrizeTableName().length() == 0 ? "null" : String.valueOf(this.request.getPrizeTableName()));

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
     * 排出確率テーブルを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getPrizeTableAsync(
            GetPrizeTableRequest request,
            AsyncAction<AsyncResult<GetPrizeTableResult>> callback
    ) {
        GetPrizeTableTask task = new GetPrizeTableTask(request, callback, GetPrizeTableResult.class);
        session.execute(task);
    }

    /**
     * 排出確率テーブルを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetPrizeTableResult getPrizeTable(
            GetPrizeTableRequest request
    ) {
        final AsyncResult<GetPrizeTableResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPrizeTableAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DrawByUserIdTask extends Gs2RestSessionTask<DrawByUserIdResult> {
        private DrawByUserIdRequest request;

        public DrawByUserIdTask(
            DrawByUserIdRequest request,
            AsyncAction<AsyncResult<DrawByUserIdResult>> userCallback,
            Class<DrawByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/lottery/{lotteryName}/draw";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null|| this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCount() != null) {
                json.put("count", this.request.getCount());
            }
            if (this.request.getConfig() != null) {
                JSONArray array = new JSONArray();
                for(Config item : this.request.getConfig())
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
     * ユーザIDを指定して抽選を実行<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void drawByUserIdAsync(
            DrawByUserIdRequest request,
            AsyncAction<AsyncResult<DrawByUserIdResult>> callback
    ) {
        DrawByUserIdTask task = new DrawByUserIdTask(request, callback, DrawByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザIDを指定して抽選を実行<br>
     *
     * @param request リクエストパラメータ
     */
    public DrawByUserIdResult drawByUserId(
            DrawByUserIdRequest request
    ) {
        final AsyncResult<DrawByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        drawByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeProbabilitiesTask extends Gs2RestSessionTask<DescribeProbabilitiesResult> {
        private DescribeProbabilitiesRequest request;

        public DescribeProbabilitiesTask(
            DescribeProbabilitiesRequest request,
            AsyncAction<AsyncResult<DescribeProbabilitiesResult>> userCallback,
            Class<DescribeProbabilitiesResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/lottery/{lotteryName}/probability";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null|| this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));

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
     * 排出確率を取得<br>
     *   <br>
     *   通常抽選ではすべてのゲームプレイヤーに対して同じ確率を応答します。<br>
     *   ボックス抽選ではボックスの中身の残りを考慮したゲームプレイヤーごとに異なる確率を応答します。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeProbabilitiesAsync(
            DescribeProbabilitiesRequest request,
            AsyncAction<AsyncResult<DescribeProbabilitiesResult>> callback
    ) {
        DescribeProbabilitiesTask task = new DescribeProbabilitiesTask(request, callback, DescribeProbabilitiesResult.class);
        session.execute(task);
    }

    /**
     * 排出確率を取得<br>
     *   <br>
     *   通常抽選ではすべてのゲームプレイヤーに対して同じ確率を応答します。<br>
     *   ボックス抽選ではボックスの中身の残りを考慮したゲームプレイヤーごとに異なる確率を応答します。<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeProbabilitiesResult describeProbabilities(
            DescribeProbabilitiesRequest request
    ) {
        final AsyncResult<DescribeProbabilitiesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeProbabilitiesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeProbabilitiesByUserIdTask extends Gs2RestSessionTask<DescribeProbabilitiesByUserIdResult> {
        private DescribeProbabilitiesByUserIdRequest request;

        public DescribeProbabilitiesByUserIdTask(
            DescribeProbabilitiesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeProbabilitiesByUserIdResult>> userCallback,
            Class<DescribeProbabilitiesByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/lottery/{lotteryName}/probability";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{lotteryName}", this.request.getLotteryName() == null|| this.request.getLotteryName().length() == 0 ? "null" : String.valueOf(this.request.getLotteryName()));
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
     * 排出確率を取得<br>
     *   <br>
     *   通常抽選ではすべてのゲームプレイヤーに対して同じ確率を応答します。<br>
     *   ボックス抽選ではボックスの中身の残りを考慮したゲームプレイヤーごとに異なる確率を応答します。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeProbabilitiesByUserIdAsync(
            DescribeProbabilitiesByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeProbabilitiesByUserIdResult>> callback
    ) {
        DescribeProbabilitiesByUserIdTask task = new DescribeProbabilitiesByUserIdTask(request, callback, DescribeProbabilitiesByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 排出確率を取得<br>
     *   <br>
     *   通常抽選ではすべてのゲームプレイヤーに対して同じ確率を応答します。<br>
     *   ボックス抽選ではボックスの中身の残りを考慮したゲームプレイヤーごとに異なる確率を応答します。<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeProbabilitiesByUserIdResult describeProbabilitiesByUserId(
            DescribeProbabilitiesByUserIdRequest request
    ) {
        final AsyncResult<DescribeProbabilitiesByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeProbabilitiesByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DrawByStampSheetTask extends Gs2RestSessionTask<DrawByStampSheetResult> {
        private DrawByStampSheetRequest request;

        public DrawByStampSheetTask(
            DrawByStampSheetRequest request,
            AsyncAction<AsyncResult<DrawByStampSheetResult>> userCallback,
            Class<DrawByStampSheetResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/draw";

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
     * スタンプシートを使用して抽選処理を実行<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void drawByStampSheetAsync(
            DrawByStampSheetRequest request,
            AsyncAction<AsyncResult<DrawByStampSheetResult>> callback
    ) {
        DrawByStampSheetTask task = new DrawByStampSheetTask(request, callback, DrawByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートを使用して抽選処理を実行<br>
     *
     * @param request リクエストパラメータ
     */
    public DrawByStampSheetResult drawByStampSheet(
            DrawByStampSheetRequest request
    ) {
        final AsyncResult<DrawByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        drawByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "lottery")
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
     * 現在有効な抽選設定のマスターデータをエクスポートします<br>
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
     * 現在有効な抽選設定のマスターデータをエクスポートします<br>
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

    class GetCurrentLotteryMasterTask extends Gs2RestSessionTask<GetCurrentLotteryMasterResult> {
        private GetCurrentLotteryMasterRequest request;

        public GetCurrentLotteryMasterTask(
            GetCurrentLotteryMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentLotteryMasterResult>> userCallback,
            Class<GetCurrentLotteryMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
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
     * 現在有効な抽選設定を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getCurrentLotteryMasterAsync(
            GetCurrentLotteryMasterRequest request,
            AsyncAction<AsyncResult<GetCurrentLotteryMasterResult>> callback
    ) {
        GetCurrentLotteryMasterTask task = new GetCurrentLotteryMasterTask(request, callback, GetCurrentLotteryMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な抽選設定を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetCurrentLotteryMasterResult getCurrentLotteryMaster(
            GetCurrentLotteryMasterRequest request
    ) {
        final AsyncResult<GetCurrentLotteryMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCurrentLotteryMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentLotteryMasterTask extends Gs2RestSessionTask<UpdateCurrentLotteryMasterResult> {
        private UpdateCurrentLotteryMasterRequest request;

        public UpdateCurrentLotteryMasterTask(
            UpdateCurrentLotteryMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentLotteryMasterResult>> userCallback,
            Class<UpdateCurrentLotteryMasterResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
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
     * 現在有効な抽選設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentLotteryMasterAsync(
            UpdateCurrentLotteryMasterRequest request,
            AsyncAction<AsyncResult<UpdateCurrentLotteryMasterResult>> callback
    ) {
        UpdateCurrentLotteryMasterTask task = new UpdateCurrentLotteryMasterTask(request, callback, UpdateCurrentLotteryMasterResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な抽選設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentLotteryMasterResult updateCurrentLotteryMaster(
            UpdateCurrentLotteryMasterRequest request
    ) {
        final AsyncResult<UpdateCurrentLotteryMasterResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentLotteryMasterAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateCurrentLotteryMasterFromGitHubTask extends Gs2RestSessionTask<UpdateCurrentLotteryMasterFromGitHubResult> {
        private UpdateCurrentLotteryMasterFromGitHubRequest request;

        public UpdateCurrentLotteryMasterFromGitHubTask(
            UpdateCurrentLotteryMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentLotteryMasterFromGitHubResult>> userCallback,
            Class<UpdateCurrentLotteryMasterFromGitHubResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "lottery")
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
     * 現在有効な抽選設定を更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateCurrentLotteryMasterFromGitHubAsync(
            UpdateCurrentLotteryMasterFromGitHubRequest request,
            AsyncAction<AsyncResult<UpdateCurrentLotteryMasterFromGitHubResult>> callback
    ) {
        UpdateCurrentLotteryMasterFromGitHubTask task = new UpdateCurrentLotteryMasterFromGitHubTask(request, callback, UpdateCurrentLotteryMasterFromGitHubResult.class);
        session.execute(task);
    }

    /**
     * 現在有効な抽選設定を更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateCurrentLotteryMasterFromGitHubResult updateCurrentLotteryMasterFromGitHub(
            UpdateCurrentLotteryMasterFromGitHubRequest request
    ) {
        final AsyncResult<UpdateCurrentLotteryMasterFromGitHubResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateCurrentLotteryMasterFromGitHubAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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