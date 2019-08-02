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

package io.gs2.account;

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
import io.gs2.account.request.*;
import io.gs2.account.result.*;
import io.gs2.account.model.*;

/**
 * GS2 Account API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2AccountRestClient extends AbstractGs2Client<Gs2AccountRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2AccountRestClient(Gs2RestSession gs2RestSession) {
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
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
            if (this.request.getChangePasswordIfTakeOver() != null) {
                json.put("changePasswordIfTakeOver", this.request.getChangePasswordIfTakeOver());
            }
            if (this.request.getCreateAccountTriggerScriptId() != null) {
                json.put("createAccountTriggerScriptId", this.request.getCreateAccountTriggerScriptId());
            }
            if (this.request.getCreateAccountDoneTriggerScriptId() != null) {
                json.put("createAccountDoneTriggerScriptId", this.request.getCreateAccountDoneTriggerScriptId());
            }
            if (this.request.getCreateAccountDoneTriggerQueueNamespaceId() != null) {
                json.put("createAccountDoneTriggerQueueNamespaceId", this.request.getCreateAccountDoneTriggerQueueNamespaceId());
            }
            if (this.request.getAuthenticationTriggerScriptId() != null) {
                json.put("authenticationTriggerScriptId", this.request.getAuthenticationTriggerScriptId());
            }
            if (this.request.getAuthenticationDoneTriggerScriptId() != null) {
                json.put("authenticationDoneTriggerScriptId", this.request.getAuthenticationDoneTriggerScriptId());
            }
            if (this.request.getAuthenticationDoneTriggerQueueNamespaceId() != null) {
                json.put("authenticationDoneTriggerQueueNamespaceId", this.request.getAuthenticationDoneTriggerQueueNamespaceId());
            }
            if (this.request.getCreateTakeOverTriggerScriptId() != null) {
                json.put("createTakeOverTriggerScriptId", this.request.getCreateTakeOverTriggerScriptId());
            }
            if (this.request.getCreateTakeOverDoneTriggerScriptId() != null) {
                json.put("createTakeOverDoneTriggerScriptId", this.request.getCreateTakeOverDoneTriggerScriptId());
            }
            if (this.request.getCreateTakeOverDoneTriggerQueueNamespaceId() != null) {
                json.put("createTakeOverDoneTriggerQueueNamespaceId", this.request.getCreateTakeOverDoneTriggerQueueNamespaceId());
            }
            if (this.request.getDoTakeOverTriggerScriptId() != null) {
                json.put("doTakeOverTriggerScriptId", this.request.getDoTakeOverTriggerScriptId());
            }
            if (this.request.getDoTakeOverDoneTriggerScriptId() != null) {
                json.put("doTakeOverDoneTriggerScriptId", this.request.getDoTakeOverDoneTriggerScriptId());
            }
            if (this.request.getDoTakeOverDoneTriggerQueueNamespaceId() != null) {
                json.put("doTakeOverDoneTriggerQueueNamespaceId", this.request.getDoTakeOverDoneTriggerQueueNamespaceId());
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
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
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getChangePasswordIfTakeOver() != null) {
                json.put("changePasswordIfTakeOver", this.request.getChangePasswordIfTakeOver());
            }
            if (this.request.getCreateAccountTriggerScriptId() != null) {
                json.put("createAccountTriggerScriptId", this.request.getCreateAccountTriggerScriptId());
            }
            if (this.request.getCreateAccountDoneTriggerScriptId() != null) {
                json.put("createAccountDoneTriggerScriptId", this.request.getCreateAccountDoneTriggerScriptId());
            }
            if (this.request.getCreateAccountDoneTriggerQueueNamespaceId() != null) {
                json.put("createAccountDoneTriggerQueueNamespaceId", this.request.getCreateAccountDoneTriggerQueueNamespaceId());
            }
            if (this.request.getAuthenticationTriggerScriptId() != null) {
                json.put("authenticationTriggerScriptId", this.request.getAuthenticationTriggerScriptId());
            }
            if (this.request.getAuthenticationDoneTriggerScriptId() != null) {
                json.put("authenticationDoneTriggerScriptId", this.request.getAuthenticationDoneTriggerScriptId());
            }
            if (this.request.getAuthenticationDoneTriggerQueueNamespaceId() != null) {
                json.put("authenticationDoneTriggerQueueNamespaceId", this.request.getAuthenticationDoneTriggerQueueNamespaceId());
            }
            if (this.request.getCreateTakeOverTriggerScriptId() != null) {
                json.put("createTakeOverTriggerScriptId", this.request.getCreateTakeOverTriggerScriptId());
            }
            if (this.request.getCreateTakeOverDoneTriggerScriptId() != null) {
                json.put("createTakeOverDoneTriggerScriptId", this.request.getCreateTakeOverDoneTriggerScriptId());
            }
            if (this.request.getCreateTakeOverDoneTriggerQueueNamespaceId() != null) {
                json.put("createTakeOverDoneTriggerQueueNamespaceId", this.request.getCreateTakeOverDoneTriggerQueueNamespaceId());
            }
            if (this.request.getDoTakeOverTriggerScriptId() != null) {
                json.put("doTakeOverTriggerScriptId", this.request.getDoTakeOverTriggerScriptId());
            }
            if (this.request.getDoTakeOverDoneTriggerScriptId() != null) {
                json.put("doTakeOverDoneTriggerScriptId", this.request.getDoTakeOverDoneTriggerScriptId());
            }
            if (this.request.getDoTakeOverDoneTriggerQueueNamespaceId() != null) {
                json.put("doTakeOverDoneTriggerQueueNamespaceId", this.request.getDoTakeOverDoneTriggerQueueNamespaceId());
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
                .replace("{service}", "account")
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

    class DescribeAccountsTask extends Gs2RestSessionTask<DescribeAccountsResult> {
        private DescribeAccountsRequest request;

        public DescribeAccountsTask(
            DescribeAccountsRequest request,
            AsyncAction<AsyncResult<DescribeAccountsResult>> userCallback,
            Class<DescribeAccountsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account";

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
     * ゲームプレイヤーアカウントの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeAccountsAsync(
            DescribeAccountsRequest request,
            AsyncAction<AsyncResult<DescribeAccountsResult>> callback
    ) {
        DescribeAccountsTask task = new DescribeAccountsTask(request, callback, DescribeAccountsResult.class);
        session.execute(task);
    }

    /**
     * ゲームプレイヤーアカウントの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeAccountsResult describeAccounts(
            DescribeAccountsRequest request
    ) {
        final AsyncResult<DescribeAccountsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeAccountsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateAccountTask extends Gs2RestSessionTask<CreateAccountResult> {
        private CreateAccountRequest request;

        public CreateAccountTask(
            CreateAccountRequest request,
            AsyncAction<AsyncResult<CreateAccountResult>> userCallback,
            Class<CreateAccountResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account";

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

            builder
                .build()
                .send();
        }
    }

    /**
     * ゲームプレイヤーアカウントを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createAccountAsync(
            CreateAccountRequest request,
            AsyncAction<AsyncResult<CreateAccountResult>> callback
    ) {
        CreateAccountTask task = new CreateAccountTask(request, callback, CreateAccountResult.class);
        session.execute(task);
    }

    /**
     * ゲームプレイヤーアカウントを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateAccountResult createAccount(
            CreateAccountRequest request
    ) {
        final AsyncResult<CreateAccountResult>[] resultAsyncResult = new AsyncResult[]{null};
        createAccountAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetAccountTask extends Gs2RestSessionTask<GetAccountResult> {
        private GetAccountRequest request;

        public GetAccountTask(
            GetAccountRequest request,
            AsyncAction<AsyncResult<GetAccountResult>> userCallback,
            Class<GetAccountResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}";

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
     * ゲームプレイヤーアカウントを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getAccountAsync(
            GetAccountRequest request,
            AsyncAction<AsyncResult<GetAccountResult>> callback
    ) {
        GetAccountTask task = new GetAccountTask(request, callback, GetAccountResult.class);
        session.execute(task);
    }

    /**
     * ゲームプレイヤーアカウントを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetAccountResult getAccount(
            GetAccountRequest request
    ) {
        final AsyncResult<GetAccountResult>[] resultAsyncResult = new AsyncResult[]{null};
        getAccountAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteAccountTask extends Gs2RestSessionTask<DeleteAccountResult> {
        private DeleteAccountRequest request;

        public DeleteAccountTask(
            DeleteAccountRequest request,
            AsyncAction<AsyncResult<DeleteAccountResult>> userCallback,
            Class<DeleteAccountResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}";

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
     * ゲームプレイヤーアカウントを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteAccountAsync(
            DeleteAccountRequest request,
            AsyncAction<AsyncResult<DeleteAccountResult>> callback
    ) {
        DeleteAccountTask task = new DeleteAccountTask(request, callback, DeleteAccountResult.class);
        session.execute(task);
    }

    /**
     * ゲームプレイヤーアカウントを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteAccountResult deleteAccount(
            DeleteAccountRequest request
    ) {
        final AsyncResult<DeleteAccountResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteAccountAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AuthenticationTask extends Gs2RestSessionTask<AuthenticationResult> {
        private AuthenticationRequest request;

        public AuthenticationTask(
            AuthenticationRequest request,
            AsyncAction<AsyncResult<AuthenticationResult>> userCallback,
            Class<AuthenticationResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getPassword() != null) {
                json.put("password", this.request.getPassword());
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
     * ゲームプレイヤーアカウントを認証<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void authenticationAsync(
            AuthenticationRequest request,
            AsyncAction<AsyncResult<AuthenticationResult>> callback
    ) {
        AuthenticationTask task = new AuthenticationTask(request, callback, AuthenticationResult.class);
        session.execute(task);
    }

    /**
     * ゲームプレイヤーアカウントを認証<br>
     *
     * @param request リクエストパラメータ
     */
    public AuthenticationResult authentication(
            AuthenticationRequest request
    ) {
        final AsyncResult<AuthenticationResult>[] resultAsyncResult = new AsyncResult[]{null};
        authenticationAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeTakeOversTask extends Gs2RestSessionTask<DescribeTakeOversResult> {
        private DescribeTakeOversRequest request;

        public DescribeTakeOversTask(
            DescribeTakeOversRequest request,
            AsyncAction<AsyncResult<DescribeTakeOversResult>> userCallback,
            Class<DescribeTakeOversResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/takeover";

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
     * 引き継ぎ設定の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeTakeOversAsync(
            DescribeTakeOversRequest request,
            AsyncAction<AsyncResult<DescribeTakeOversResult>> callback
    ) {
        DescribeTakeOversTask task = new DescribeTakeOversTask(request, callback, DescribeTakeOversResult.class);
        session.execute(task);
    }

    /**
     * 引き継ぎ設定の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeTakeOversResult describeTakeOvers(
            DescribeTakeOversRequest request
    ) {
        final AsyncResult<DescribeTakeOversResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeTakeOversAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeTakeOversByUserIdTask extends Gs2RestSessionTask<DescribeTakeOversByUserIdResult> {
        private DescribeTakeOversByUserIdRequest request;

        public DescribeTakeOversByUserIdTask(
            DescribeTakeOversByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeTakeOversByUserIdResult>> userCallback,
            Class<DescribeTakeOversByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/takeover";

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
     * ユーザーIDを指定して引き継ぎ設定の一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeTakeOversByUserIdAsync(
            DescribeTakeOversByUserIdRequest request,
            AsyncAction<AsyncResult<DescribeTakeOversByUserIdResult>> callback
    ) {
        DescribeTakeOversByUserIdTask task = new DescribeTakeOversByUserIdTask(request, callback, DescribeTakeOversByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定して引き継ぎ設定の一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeTakeOversByUserIdResult describeTakeOversByUserId(
            DescribeTakeOversByUserIdRequest request
    ) {
        final AsyncResult<DescribeTakeOversByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeTakeOversByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateTakeOverTask extends Gs2RestSessionTask<CreateTakeOverResult> {
        private CreateTakeOverRequest request;

        public CreateTakeOverTask(
            CreateTakeOverRequest request,
            AsyncAction<AsyncResult<CreateTakeOverResult>> userCallback,
            Class<CreateTakeOverResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/takeover";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getType() != null) {
                json.put("type", this.request.getType());
            }
            if (this.request.getUserIdentifier() != null) {
                json.put("userIdentifier", this.request.getUserIdentifier());
            }
            if (this.request.getPassword() != null) {
                json.put("password", this.request.getPassword());
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
     * 引き継ぎ設定を新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createTakeOverAsync(
            CreateTakeOverRequest request,
            AsyncAction<AsyncResult<CreateTakeOverResult>> callback
    ) {
        CreateTakeOverTask task = new CreateTakeOverTask(request, callback, CreateTakeOverResult.class);
        session.execute(task);
    }

    /**
     * 引き継ぎ設定を新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateTakeOverResult createTakeOver(
            CreateTakeOverRequest request
    ) {
        final AsyncResult<CreateTakeOverResult>[] resultAsyncResult = new AsyncResult[]{null};
        createTakeOverAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateTakeOverByUserIdTask extends Gs2RestSessionTask<CreateTakeOverByUserIdResult> {
        private CreateTakeOverByUserIdRequest request;

        public CreateTakeOverByUserIdTask(
            CreateTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<CreateTakeOverByUserIdResult>> userCallback,
            Class<CreateTakeOverByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/takeover";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getType() != null) {
                json.put("type", this.request.getType());
            }
            if (this.request.getUserIdentifier() != null) {
                json.put("userIdentifier", this.request.getUserIdentifier());
            }
            if (this.request.getPassword() != null) {
                json.put("password", this.request.getPassword());
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
     * ユーザーIDを指定して引き継ぎ設定を新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createTakeOverByUserIdAsync(
            CreateTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<CreateTakeOverByUserIdResult>> callback
    ) {
        CreateTakeOverByUserIdTask task = new CreateTakeOverByUserIdTask(request, callback, CreateTakeOverByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定して引き継ぎ設定を新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateTakeOverByUserIdResult createTakeOverByUserId(
            CreateTakeOverByUserIdRequest request
    ) {
        final AsyncResult<CreateTakeOverByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        createTakeOverByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetTakeOverTask extends Gs2RestSessionTask<GetTakeOverResult> {
        private GetTakeOverRequest request;

        public GetTakeOverTask(
            GetTakeOverRequest request,
            AsyncAction<AsyncResult<GetTakeOverResult>> userCallback,
            Class<GetTakeOverResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/takeover/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null ? "null" : String.valueOf(this.request.getType()));

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
     * 引き継ぎ設定を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getTakeOverAsync(
            GetTakeOverRequest request,
            AsyncAction<AsyncResult<GetTakeOverResult>> callback
    ) {
        GetTakeOverTask task = new GetTakeOverTask(request, callback, GetTakeOverResult.class);
        session.execute(task);
    }

    /**
     * 引き継ぎ設定を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetTakeOverResult getTakeOver(
            GetTakeOverRequest request
    ) {
        final AsyncResult<GetTakeOverResult>[] resultAsyncResult = new AsyncResult[]{null};
        getTakeOverAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetTakeOverByUserIdTask extends Gs2RestSessionTask<GetTakeOverByUserIdResult> {
        private GetTakeOverByUserIdRequest request;

        public GetTakeOverByUserIdTask(
            GetTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<GetTakeOverByUserIdResult>> userCallback,
            Class<GetTakeOverByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/takeover/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{type}", this.request.getType() == null ? "null" : String.valueOf(this.request.getType()));

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
     * ユーザーIDを指定して引き継ぎ設定を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getTakeOverByUserIdAsync(
            GetTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<GetTakeOverByUserIdResult>> callback
    ) {
        GetTakeOverByUserIdTask task = new GetTakeOverByUserIdTask(request, callback, GetTakeOverByUserIdResult.class);
        session.execute(task);
    }

    /**
     * ユーザーIDを指定して引き継ぎ設定を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetTakeOverByUserIdResult getTakeOverByUserId(
            GetTakeOverByUserIdRequest request
    ) {
        final AsyncResult<GetTakeOverByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        getTakeOverByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateTakeOverTask extends Gs2RestSessionTask<UpdateTakeOverResult> {
        private UpdateTakeOverRequest request;

        public UpdateTakeOverTask(
            UpdateTakeOverRequest request,
            AsyncAction<AsyncResult<UpdateTakeOverResult>> userCallback,
            Class<UpdateTakeOverResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/takeover/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null ? "null" : String.valueOf(this.request.getType()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getOldPassword() != null) {
                json.put("oldPassword", this.request.getOldPassword());
            }
            if (this.request.getPassword() != null) {
                json.put("password", this.request.getPassword());
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
     * 引き継ぎ設定を更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateTakeOverAsync(
            UpdateTakeOverRequest request,
            AsyncAction<AsyncResult<UpdateTakeOverResult>> callback
    ) {
        UpdateTakeOverTask task = new UpdateTakeOverTask(request, callback, UpdateTakeOverResult.class);
        session.execute(task);
    }

    /**
     * 引き継ぎ設定を更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateTakeOverResult updateTakeOver(
            UpdateTakeOverRequest request
    ) {
        final AsyncResult<UpdateTakeOverResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateTakeOverAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateTakeOverByUserIdTask extends Gs2RestSessionTask<UpdateTakeOverByUserIdResult> {
        private UpdateTakeOverByUserIdRequest request;

        public UpdateTakeOverByUserIdTask(
            UpdateTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateTakeOverByUserIdResult>> userCallback,
            Class<UpdateTakeOverByUserIdResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/{userId}/takeover/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{userId}", this.request.getUserId() == null|| this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));
            url = url.replace("{type}", this.request.getType() == null ? "null" : String.valueOf(this.request.getType()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getOldPassword() != null) {
                json.put("oldPassword", this.request.getOldPassword());
            }
            if (this.request.getPassword() != null) {
                json.put("password", this.request.getPassword());
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
     * 引き継ぎ設定を更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateTakeOverByUserIdAsync(
            UpdateTakeOverByUserIdRequest request,
            AsyncAction<AsyncResult<UpdateTakeOverByUserIdResult>> callback
    ) {
        UpdateTakeOverByUserIdTask task = new UpdateTakeOverByUserIdTask(request, callback, UpdateTakeOverByUserIdResult.class);
        session.execute(task);
    }

    /**
     * 引き継ぎ設定を更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateTakeOverByUserIdResult updateTakeOverByUserId(
            UpdateTakeOverByUserIdRequest request
    ) {
        final AsyncResult<UpdateTakeOverByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateTakeOverByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteTakeOverTask extends Gs2RestSessionTask<DeleteTakeOverResult> {
        private DeleteTakeOverRequest request;

        public DeleteTakeOverTask(
            DeleteTakeOverRequest request,
            AsyncAction<AsyncResult<DeleteTakeOverResult>> userCallback,
            Class<DeleteTakeOverResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/account/me/takeover/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null ? "null" : String.valueOf(this.request.getType()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getUserIdentifier() != null) {
                queryStrings.add("userIdentifier=" + EncodingUtil.urlEncode((String.valueOf(this.request.getUserIdentifier()))));
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
     * 引き継ぎ設定を削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteTakeOverAsync(
            DeleteTakeOverRequest request,
            AsyncAction<AsyncResult<DeleteTakeOverResult>> callback
    ) {
        DeleteTakeOverTask task = new DeleteTakeOverTask(request, callback, DeleteTakeOverResult.class);
        session.execute(task);
    }

    /**
     * 引き継ぎ設定を削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteTakeOverResult deleteTakeOver(
            DeleteTakeOverRequest request
    ) {
        final AsyncResult<DeleteTakeOverResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteTakeOverAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteTakeOverByUserIdentifierTask extends Gs2RestSessionTask<DeleteTakeOverByUserIdentifierResult> {
        private DeleteTakeOverByUserIdentifierRequest request;

        public DeleteTakeOverByUserIdentifierTask(
            DeleteTakeOverByUserIdentifierRequest request,
            AsyncAction<AsyncResult<DeleteTakeOverByUserIdentifierResult>> userCallback,
            Class<DeleteTakeOverByUserIdentifierResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/takeover/type/{type}/userIdentifier/{userIdentifier}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null ? "null" : String.valueOf(this.request.getType()));
            url = url.replace("{userIdentifier}", this.request.getUserIdentifier() == null|| this.request.getUserIdentifier().length() == 0 ? "null" : String.valueOf(this.request.getUserIdentifier()));

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
     * 引き継ぎ設定を削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteTakeOverByUserIdentifierAsync(
            DeleteTakeOverByUserIdentifierRequest request,
            AsyncAction<AsyncResult<DeleteTakeOverByUserIdentifierResult>> callback
    ) {
        DeleteTakeOverByUserIdentifierTask task = new DeleteTakeOverByUserIdentifierTask(request, callback, DeleteTakeOverByUserIdentifierResult.class);
        session.execute(task);
    }

    /**
     * 引き継ぎ設定を削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteTakeOverByUserIdentifierResult deleteTakeOverByUserIdentifier(
            DeleteTakeOverByUserIdentifierRequest request
    ) {
        final AsyncResult<DeleteTakeOverByUserIdentifierResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteTakeOverByUserIdentifierAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DoTakeOverTask extends Gs2RestSessionTask<DoTakeOverResult> {
        private DoTakeOverRequest request;

        public DoTakeOverTask(
            DoTakeOverRequest request,
            AsyncAction<AsyncResult<DoTakeOverResult>> userCallback,
            Class<DoTakeOverResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "account")
                .replace("{region}", session.getRegion().getName())
                + "/{namespaceName}/takeover/type/{type}";

            url = url.replace("{namespaceName}", this.request.getNamespaceName() == null|| this.request.getNamespaceName().length() == 0 ? "null" : String.valueOf(this.request.getNamespaceName()));
            url = url.replace("{type}", this.request.getType() == null ? "null" : String.valueOf(this.request.getType()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getUserIdentifier() != null) {
                json.put("userIdentifier", this.request.getUserIdentifier());
            }
            if (this.request.getPassword() != null) {
                json.put("password", this.request.getPassword());
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
     * 引き継ぎ設定を更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void doTakeOverAsync(
            DoTakeOverRequest request,
            AsyncAction<AsyncResult<DoTakeOverResult>> callback
    ) {
        DoTakeOverTask task = new DoTakeOverTask(request, callback, DoTakeOverResult.class);
        session.execute(task);
    }

    /**
     * 引き継ぎ設定を更新<br>
     *
     * @param request リクエストパラメータ
     */
    public DoTakeOverResult doTakeOver(
            DoTakeOverRequest request
    ) {
        final AsyncResult<DoTakeOverResult>[] resultAsyncResult = new AsyncResult[]{null};
        doTakeOverAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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