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

package io.gs2.money;

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
import io.gs2.money.request.*;
import io.gs2.money.result.*;
import io.gs2.money.model.*;

/**
 * GS2 Money API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2MoneyRestClient extends AbstractGs2Client<Gs2MoneyRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2MoneyRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "money")
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
     * ネームスペースの一覧を取得します<br>
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
     * ネームスペースの一覧を取得します<br>
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
                .replace("{service}", "money")
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
            if (this.request.getPriority() != null) {
                json.put("priority", this.request.getPriority());
            }
            if (this.request.getShareFree() != null) {
                json.put("shareFree", this.request.getShareFree());
            }
            if (this.request.getCurrency() != null) {
                json.put("currency", this.request.getCurrency());
            }
            if (this.request.getAppleKey() != null) {
                json.put("appleKey", this.request.getAppleKey());
            }
            if (this.request.getGoogleKey() != null) {
                json.put("googleKey", this.request.getGoogleKey());
            }
            if (this.request.getEnableFakeReceipt() != null) {
                json.put("enableFakeReceipt", this.request.getEnableFakeReceipt());
            }
            if (this.request.getCreateWalletTriggerScriptId() != null) {
                json.put("createWalletTriggerScriptId", this.request.getCreateWalletTriggerScriptId());
            }
            if (this.request.getCreateWalletDoneTriggerScriptId() != null) {
                json.put("createWalletDoneTriggerScriptId", this.request.getCreateWalletDoneTriggerScriptId());
            }
            if (this.request.getCreateWalletDoneTriggerNamespaceId() != null) {
                json.put("createWalletDoneTriggerNamespaceId", this.request.getCreateWalletDoneTriggerNamespaceId());
            }
            if (this.request.getDepositTriggerScriptId() != null) {
                json.put("depositTriggerScriptId", this.request.getDepositTriggerScriptId());
            }
            if (this.request.getDepositDoneTriggerScriptId() != null) {
                json.put("depositDoneTriggerScriptId", this.request.getDepositDoneTriggerScriptId());
            }
            if (this.request.getDepositDoneTriggerNamespaceId() != null) {
                json.put("depositDoneTriggerNamespaceId", this.request.getDepositDoneTriggerNamespaceId());
            }
            if (this.request.getWithdrawTriggerScriptId() != null) {
                json.put("withdrawTriggerScriptId", this.request.getWithdrawTriggerScriptId());
            }
            if (this.request.getWithdrawDoneTriggerScriptId() != null) {
                json.put("withdrawDoneTriggerScriptId", this.request.getWithdrawDoneTriggerScriptId());
            }
            if (this.request.getWithdrawDoneTriggerNamespaceId() != null) {
                json.put("withdrawDoneTriggerNamespaceId", this.request.getWithdrawDoneTriggerNamespaceId());
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
     * ネームスペースを新規作成します<br>
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
     * ネームスペースを新規作成します<br>
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
                .replace("{service}", "money")
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
     * ネームスペースの状態を取得します<br>
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
     * ネームスペースの状態を取得します<br>
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
                .replace("{service}", "money")
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
     * ネームスペースを取得します<br>
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
     * ネームスペースを取得します<br>
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
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getPriority() != null) {
                json.put("priority", this.request.getPriority());
            }
            if (this.request.getAppleKey() != null) {
                json.put("appleKey", this.request.getAppleKey());
            }
            if (this.request.getGoogleKey() != null) {
                json.put("googleKey", this.request.getGoogleKey());
            }
            if (this.request.getEnableFakeReceipt() != null) {
                json.put("enableFakeReceipt", this.request.getEnableFakeReceipt());
            }
            if (this.request.getCreateWalletTriggerScriptId() != null) {
                json.put("createWalletTriggerScriptId", this.request.getCreateWalletTriggerScriptId());
            }
            if (this.request.getCreateWalletDoneTriggerScriptId() != null) {
                json.put("createWalletDoneTriggerScriptId", this.request.getCreateWalletDoneTriggerScriptId());
            }
            if (this.request.getCreateWalletDoneTriggerNamespaceId() != null) {
                json.put("createWalletDoneTriggerNamespaceId", this.request.getCreateWalletDoneTriggerNamespaceId());
            }
            if (this.request.getDepositTriggerScriptId() != null) {
                json.put("depositTriggerScriptId", this.request.getDepositTriggerScriptId());
            }
            if (this.request.getDepositDoneTriggerScriptId() != null) {
                json.put("depositDoneTriggerScriptId", this.request.getDepositDoneTriggerScriptId());
            }
            if (this.request.getDepositDoneTriggerNamespaceId() != null) {
                json.put("depositDoneTriggerNamespaceId", this.request.getDepositDoneTriggerNamespaceId());
            }
            if (this.request.getWithdrawTriggerScriptId() != null) {
                json.put("withdrawTriggerScriptId", this.request.getWithdrawTriggerScriptId());
            }
            if (this.request.getWithdrawDoneTriggerScriptId() != null) {
                json.put("withdrawDoneTriggerScriptId", this.request.getWithdrawDoneTriggerScriptId());
            }
            if (this.request.getWithdrawDoneTriggerNamespaceId() != null) {
                json.put("withdrawDoneTriggerNamespaceId", this.request.getWithdrawDoneTriggerNamespaceId());
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
     * ネームスペースを更新します<br>
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
     * ネームスペースを更新します<br>
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
                .replace("{service}", "money")
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
     * ネームスペースを削除します<br>
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
     * ネームスペースを削除します<br>
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

    class DescribeWalletsTask extends Gs2RestSessionTask<DescribeWalletsResult> {
        private DescribeWalletsRequest request;

        public DescribeWalletsTask(
            DescribeWalletsRequest request,
            AsyncAction<AsyncResult<DescribeWalletsResult>> userCallback,
            Class<DescribeWalletsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/wallet";

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
     * ウォレット一覧を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeWalletsAsync(
            DescribeWalletsRequest request,
            AsyncAction<AsyncResult<DescribeWalletsResult>> callback
    ) {
        DescribeWalletsTask task = new DescribeWalletsTask(request, callback, DescribeWalletsResult.class);
        session.execute(task);
    }

    /**
     * ウォレット一覧を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeWalletsResult describeWallets(
            DescribeWalletsRequest request
    ) {
        final AsyncResult<DescribeWalletsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeWalletsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeWalletsByUserIdTask extends Gs2RestSessionTask<DescribeWalletsByUserIdResult> {
        private DescribeWalletsByUserIdRequest request;

        public DescribeWalletsByUserIdTask(
            DescribeWalletsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeWalletsByUserIdResult>> userCallback,
            Class<DescribeWalletsByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/wallet";

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
     * ウォレット一覧を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeWalletsByUserIdAsync(
            DescribeWalletsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeWalletsByUserIdResult>> callback
    ) {
        DescribeWalletsByUserIdTask task = new DescribeWalletsByUserIdTask(request, callback, DescribeWalletsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ウォレット一覧を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeWalletsByUserIdResult describeWalletsByUserId(
            DescribeWalletsByUserIdRequest request
    ) {
        final AsyncResult<DescribeWalletsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeWalletsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class QueryWalletsTask extends Gs2RestSessionTask<QueryWalletsResult> {
        private QueryWalletsRequest request;

        public QueryWalletsTask(
            QueryWalletsRequest request,
            AsyncAction<AsyncResult<QueryWalletsResult>> userCallback,
            Class<QueryWalletsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/wallet/query";

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
     * ウォレット一覧を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void queryWalletsAsync(
            QueryWalletsRequest request,
            AsyncAction<AsyncResult<QueryWalletsResult>> callback
    ) {
        QueryWalletsTask task = new QueryWalletsTask(request, callback, QueryWalletsResult.class);
        session.execute(task);
    }

    /**
     * ウォレット一覧を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public QueryWalletsResult queryWallets(
            QueryWalletsRequest request
    ) {
        final AsyncResult<QueryWalletsResult>[] resultAsyncResult = new AsyncResult[]{null};
        queryWalletsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetWalletTask extends Gs2RestSessionTask<GetWalletResult> {
        private GetWalletRequest request;

        public GetWalletTask(
            GetWalletRequest request,
            AsyncAction<AsyncResult<GetWalletResult>> userCallback,
            Class<GetWalletResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/wallet/{slot}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{slot}", this.request.getSlot() == null ? "null" : String.valueOf(this.request.getSlot()));

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
     * ウォレットを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getWalletAsync(
            GetWalletRequest request,
            AsyncAction<AsyncResult<GetWalletResult>> callback
    ) {
        GetWalletTask task = new GetWalletTask(request, callback, GetWalletResult.class);
        session.execute(task);
    }

    /**
     * ウォレットを取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetWalletResult getWallet(
            GetWalletRequest request
    ) {
        final AsyncResult<GetWalletResult>[] resultAsyncResult = new AsyncResult[]{null};
        getWalletAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetWalletByUserIdTask extends Gs2RestSessionTask<GetWalletByUserIdResult> {
        private GetWalletByUserIdRequest request;

        public GetWalletByUserIdTask(
            GetWalletByUserIdRequest request,
            AsyncAction<AsyncResult<GetWalletByUserIdResult>> userCallback,
            Class<GetWalletByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/wallet/{slot}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{slot}", this.request.getSlot() == null ? "null" : String.valueOf(this.request.getSlot()));

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
     * ユーザーIDを指定してウォレットを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getWalletByUserIdAsync(
            GetWalletByUserIdRequest request,
            AsyncAction<AsyncResult<GetWalletByUserIdResult>> callback
    ) {
        GetWalletByUserIdTask task = new GetWalletByUserIdTask(request, callback, GetWalletByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してウォレットを取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetWalletByUserIdResult getWalletByUserId(
            GetWalletByUserIdRequest request
    ) {
        final AsyncResult<GetWalletByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getWalletByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DepositByUserIdTask extends Gs2RestSessionTask<DepositByUserIdResult> {
        private DepositByUserIdRequest request;

        public DepositByUserIdTask(
            DepositByUserIdRequest request,
            AsyncAction<AsyncResult<DepositByUserIdResult>> userCallback,
            Class<DepositByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/wallet/{slot}/deposit";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{slot}", this.request.getSlot() == null ? "null" : String.valueOf(this.request.getSlot()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getPrice() != null) {
                json.put("price", this.request.getPrice());
            }
            if (this.request.getCount() != null) {
                json.put("count", this.request.getCount());
            }
            if (this.request.getTransactionId() != null) {
                json.put("transactionId", this.request.getTransactionId());
            }
            if (this.request.getContentsId() != null) {
                json.put("contentsId", this.request.getContentsId());
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
     * ユーザーIDを指定してウォレットに残高を加算します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void depositByUserIdAsync(
            DepositByUserIdRequest request,
            AsyncAction<AsyncResult<DepositByUserIdResult>> callback
    ) {
        DepositByUserIdTask task = new DepositByUserIdTask(request, callback, DepositByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してウォレットに残高を加算します<br>
     *
     * @param request リクエストパラメータ
     */
    public DepositByUserIdResult depositByUserId(
            DepositByUserIdRequest request
    ) {
        final AsyncResult<DepositByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        depositByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class WithdrawTask extends Gs2RestSessionTask<WithdrawResult> {
        private WithdrawRequest request;

        public WithdrawTask(
            WithdrawRequest request,
            AsyncAction<AsyncResult<WithdrawResult>> userCallback,
            Class<WithdrawResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/me/wallet/{slot}/withdraw";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{slot}", this.request.getSlot() == null ? "null" : String.valueOf(this.request.getSlot()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCount() != null) {
                json.put("count", this.request.getCount());
            }
            if (this.request.getPaidOnly() != null) {
                json.put("paidOnly", this.request.getPaidOnly());
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
     * ウォレットから残高を消費します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void withdrawAsync(
            WithdrawRequest request,
            AsyncAction<AsyncResult<WithdrawResult>> callback
    ) {
        WithdrawTask task = new WithdrawTask(request, callback, WithdrawResult.class);
        session.execute(task);
    }

    /**
     * ウォレットから残高を消費します<br>
     *
     * @param request リクエストパラメータ
     */
    public WithdrawResult withdraw(
            WithdrawRequest request
    ) {
        final AsyncResult<WithdrawResult>[] resultAsyncResult = new AsyncResult[]{null};
        withdrawAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class WithdrawByUserIdTask extends Gs2RestSessionTask<WithdrawByUserIdResult> {
        private WithdrawByUserIdRequest request;

        public WithdrawByUserIdTask(
            WithdrawByUserIdRequest request,
            AsyncAction<AsyncResult<WithdrawByUserIdResult>> userCallback,
            Class<WithdrawByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/wallet/{slot}/withdraw";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{slot}", this.request.getSlot() == null ? "null" : String.valueOf(this.request.getSlot()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getCount() != null) {
                json.put("count", this.request.getCount());
            }
            if (this.request.getPaidOnly() != null) {
                json.put("paidOnly", this.request.getPaidOnly());
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
     * ユーザーIDを指定してウォレットから残高を消費します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void withdrawByUserIdAsync(
            WithdrawByUserIdRequest request,
            AsyncAction<AsyncResult<WithdrawByUserIdResult>> callback
    ) {
        WithdrawByUserIdTask task = new WithdrawByUserIdTask(request, callback, WithdrawByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定してウォレットから残高を消費します<br>
     *
     * @param request リクエストパラメータ
     */
    public WithdrawByUserIdResult withdrawByUserId(
            WithdrawByUserIdRequest request
    ) {
        final AsyncResult<WithdrawByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        withdrawByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DepositByStampSheetTask extends Gs2RestSessionTask<DepositByStampSheetResult> {
        private DepositByStampSheetRequest request;

        public DepositByStampSheetTask(
            DepositByStampSheetRequest request,
            AsyncAction<AsyncResult<DepositByStampSheetResult>> userCallback,
            Class<DepositByStampSheetResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/deposit";

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
     * スタンプシートを使用してウォレットに残高を加算します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void depositByStampSheetAsync(
            DepositByStampSheetRequest request,
            AsyncAction<AsyncResult<DepositByStampSheetResult>> callback
    ) {
        DepositByStampSheetTask task = new DepositByStampSheetTask(request, callback, DepositByStampSheetResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートを使用してウォレットに残高を加算します<br>
     *
     * @param request リクエストパラメータ
     */
    public DepositByStampSheetResult depositByStampSheet(
            DepositByStampSheetRequest request
    ) {
        final AsyncResult<DepositByStampSheetResult>[] resultAsyncResult = new AsyncResult[]{null};
        depositByStampSheetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class WithdrawByStampTaskTask extends Gs2RestSessionTask<WithdrawByStampTaskResult> {
        private WithdrawByStampTaskRequest request;

        public WithdrawByStampTaskTask(
            WithdrawByStampTaskRequest request,
            AsyncAction<AsyncResult<WithdrawByStampTaskResult>> userCallback,
            Class<WithdrawByStampTaskResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/withdraw";

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
     * ウォレットから残高を消費します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void withdrawByStampTaskAsync(
            WithdrawByStampTaskRequest request,
            AsyncAction<AsyncResult<WithdrawByStampTaskResult>> callback
    ) {
        WithdrawByStampTaskTask task = new WithdrawByStampTaskTask(request, callback, WithdrawByStampTaskResult.class);
        session.execute(task);
    }

    /**
     * ウォレットから残高を消費します<br>
     *
     * @param request リクエストパラメータ
     */
    public WithdrawByStampTaskResult withdrawByStampTask(
            WithdrawByStampTaskRequest request
    ) {
        final AsyncResult<WithdrawByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        withdrawByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeWalletDetailsByUserIdTask extends Gs2RestSessionTask<DescribeWalletDetailsByUserIdResult> {
        private DescribeWalletDetailsByUserIdRequest request;

        public DescribeWalletDetailsByUserIdTask(
            DescribeWalletDetailsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeWalletDetailsByUserIdResult>> userCallback,
            Class<DescribeWalletDetailsByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/wallet/{slot}/detail";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{slot}", this.request.getSlot() == null ? "null" : String.valueOf(this.request.getSlot()));

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
     * ウォレットの詳細を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeWalletDetailsByUserIdAsync(
            DescribeWalletDetailsByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeWalletDetailsByUserIdResult>> callback
    ) {
        DescribeWalletDetailsByUserIdTask task = new DescribeWalletDetailsByUserIdTask(request, callback, DescribeWalletDetailsByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ウォレットの詳細を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeWalletDetailsByUserIdResult describeWalletDetailsByUserId(
            DescribeWalletDetailsByUserIdRequest request
    ) {
        final AsyncResult<DescribeWalletDetailsByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeWalletDetailsByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeReceiptsTask extends Gs2RestSessionTask<DescribeReceiptsResult> {
        private DescribeReceiptsRequest request;

        public DescribeReceiptsTask(
            DescribeReceiptsRequest request,
            AsyncAction<AsyncResult<DescribeReceiptsResult>> userCallback,
            Class<DescribeReceiptsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/receipt";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getUserId() != null) {
                queryStrings.add("userId=" + EncodingUtil.urlEncode((String.valueOf(this.request.getUserId()))));
            }
            if (this.request.getSlot() != null) {
                queryStrings.add("slot=" + String.valueOf(this.request.getSlot()));
            }
            if (this.request.getBegin() != null) {
                queryStrings.add("begin=" + String.valueOf(this.request.getBegin()));
            }
            if (this.request.getEnd() != null) {
                queryStrings.add("end=" + String.valueOf(this.request.getEnd()));
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
     * レシートの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeReceiptsAsync(
            DescribeReceiptsRequest request,
            AsyncAction<AsyncResult<DescribeReceiptsResult>> callback
    ) {
        DescribeReceiptsTask task = new DescribeReceiptsTask(request, callback, DescribeReceiptsResult.class);
        session.execute(task);
    }

    /**
     * レシートの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeReceiptsResult describeReceipts(
            DescribeReceiptsRequest request
    ) {
        final AsyncResult<DescribeReceiptsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeReceiptsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetByUserIdAndTransactionIdTask extends Gs2RestSessionTask<GetByUserIdAndTransactionIdResult> {
        private GetByUserIdAndTransactionIdRequest request;

        public GetByUserIdAndTransactionIdTask(
            GetByUserIdAndTransactionIdRequest request,
            AsyncAction<AsyncResult<GetByUserIdAndTransactionIdResult>> userCallback,
            Class<GetByUserIdAndTransactionIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/receipt/{transactionId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{transactionId}", this.request.getTransactionId() == null|| this.request.getTransactionId().length() == 0 ? "null" : String.valueOf(this.request.getTransactionId()));

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
     * レシートの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getByUserIdAndTransactionIdAsync(
            GetByUserIdAndTransactionIdRequest request,
            AsyncAction<AsyncResult<GetByUserIdAndTransactionIdResult>> callback
    ) {
        GetByUserIdAndTransactionIdTask task = new GetByUserIdAndTransactionIdTask(request, callback, GetByUserIdAndTransactionIdResult.class);
        session.execute(task);
    }

    /**
     * レシートの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetByUserIdAndTransactionIdResult getByUserIdAndTransactionId(
            GetByUserIdAndTransactionIdRequest request
    ) {
        final AsyncResult<GetByUserIdAndTransactionIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getByUserIdAndTransactionIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RecordReceiptTask extends Gs2RestSessionTask<RecordReceiptResult> {
        private RecordReceiptRequest request;

        public RecordReceiptTask(
            RecordReceiptRequest request,
            AsyncAction<AsyncResult<RecordReceiptResult>> userCallback,
            Class<RecordReceiptResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/user/{userId}/receipt";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getContentsId() != null) {
                json.put("contentsId", this.request.getContentsId());
            }
            if (this.request.getReceipt() != null) {
                json.put("receipt", this.request.getReceipt());
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
     * レシートを記録<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void recordReceiptAsync(
            RecordReceiptRequest request,
            AsyncAction<AsyncResult<RecordReceiptResult>> callback
    ) {
        RecordReceiptTask task = new RecordReceiptTask(request, callback, RecordReceiptResult.class);
        session.execute(task);
    }

    /**
     * レシートを記録<br>
     *
     * @param request リクエストパラメータ
     */
    public RecordReceiptResult recordReceipt(
            RecordReceiptRequest request
    ) {
        final AsyncResult<RecordReceiptResult>[] resultAsyncResult = new AsyncResult[]{null};
        recordReceiptAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class RecordReceiptByStampTaskTask extends Gs2RestSessionTask<RecordReceiptByStampTaskResult> {
        private RecordReceiptByStampTaskRequest request;

        public RecordReceiptByStampTaskTask(
            RecordReceiptByStampTaskRequest request,
            AsyncAction<AsyncResult<RecordReceiptByStampTaskResult>> userCallback,
            Class<RecordReceiptByStampTaskResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "money")
                .replace("{region}", session.getRegion().getName())
                + "/stamp/receipt/record";

            url = url.replace("{stampTask}", this.request.getStampTask() == null|| this.request.getStampTask().length() == 0 ? "null" : String.valueOf(this.request.getStampTask()));

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
     * スタンプシートを使用してレシートを記録<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void recordReceiptByStampTaskAsync(
            RecordReceiptByStampTaskRequest request,
            AsyncAction<AsyncResult<RecordReceiptByStampTaskResult>> callback
    ) {
        RecordReceiptByStampTaskTask task = new RecordReceiptByStampTaskTask(request, callback, RecordReceiptByStampTaskResult.class);
        session.execute(task);
    }

    /**
     * スタンプシートを使用してレシートを記録<br>
     *
     * @param request リクエストパラメータ
     */
    public RecordReceiptByStampTaskResult recordReceiptByStampTask(
            RecordReceiptByStampTaskRequest request
    ) {
        final AsyncResult<RecordReceiptByStampTaskResult>[] resultAsyncResult = new AsyncResult[]{null};
        recordReceiptByStampTaskAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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