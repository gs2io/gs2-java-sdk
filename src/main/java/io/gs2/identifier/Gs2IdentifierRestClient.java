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

package io.gs2.identifier;

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
import io.gs2.identifier.request.*;
import io.gs2.identifier.result.*;
import io.gs2.identifier.model.*;

/**
 * GS2 Identifier API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2IdentifierRestClient extends AbstractGs2Client<Gs2IdentifierRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2IdentifierRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class DescribeUsersTask extends Gs2RestSessionTask<DescribeUsersResult> {
        private DescribeUsersRequest request;

        public DescribeUsersTask(
            DescribeUsersRequest request,
            AsyncAction<AsyncResult<DescribeUsersResult>> userCallback,
            Class<DescribeUsersResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user";

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
     * ユーザの一覧を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeUsersAsync(
            DescribeUsersRequest request,
            AsyncAction<AsyncResult<DescribeUsersResult>> callback
    ) {
        DescribeUsersTask task = new DescribeUsersTask(request, callback, DescribeUsersResult.class);
        session.execute(task);
    }

    /**
     * ユーザの一覧を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeUsersResult describeUsers(
            DescribeUsersRequest request
    ) {
        final AsyncResult<DescribeUsersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeUsersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateUserTask extends Gs2RestSessionTask<CreateUserResult> {
        private CreateUserRequest request;

        public CreateUserTask(
            CreateUserRequest request,
            AsyncAction<AsyncResult<CreateUserResult>> userCallback,
            Class<CreateUserResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user";

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
     * ユーザを新規作成します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createUserAsync(
            CreateUserRequest request,
            AsyncAction<AsyncResult<CreateUserResult>> callback
    ) {
        CreateUserTask task = new CreateUserTask(request, callback, CreateUserResult.class);
        session.execute(task);
    }

    /**
     * ユーザを新規作成します<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateUserResult createUser(
            CreateUserRequest request
    ) {
        final AsyncResult<CreateUserResult>[] resultAsyncResult = new AsyncResult[]{null};
        createUserAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateUserTask extends Gs2RestSessionTask<UpdateUserResult> {
        private UpdateUserRequest request;

        public UpdateUserTask(
            UpdateUserRequest request,
            AsyncAction<AsyncResult<UpdateUserResult>> userCallback,
            Class<UpdateUserResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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
     * ユーザを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateUserAsync(
            UpdateUserRequest request,
            AsyncAction<AsyncResult<UpdateUserResult>> callback
    ) {
        UpdateUserTask task = new UpdateUserTask(request, callback, UpdateUserResult.class);
        session.execute(task);
    }

    /**
     * ユーザを更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateUserResult updateUser(
            UpdateUserRequest request
    ) {
        final AsyncResult<UpdateUserResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateUserAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetUserTask extends Gs2RestSessionTask<GetUserResult> {
        private GetUserRequest request;

        public GetUserTask(
            GetUserRequest request,
            AsyncAction<AsyncResult<GetUserResult>> userCallback,
            Class<GetUserResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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
     * ユーザを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getUserAsync(
            GetUserRequest request,
            AsyncAction<AsyncResult<GetUserResult>> callback
    ) {
        GetUserTask task = new GetUserTask(request, callback, GetUserResult.class);
        session.execute(task);
    }

    /**
     * ユーザを取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetUserResult getUser(
            GetUserRequest request
    ) {
        final AsyncResult<GetUserResult>[] resultAsyncResult = new AsyncResult[]{null};
        getUserAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteUserTask extends Gs2RestSessionTask<DeleteUserResult> {
        private DeleteUserRequest request;

        public DeleteUserTask(
            DeleteUserRequest request,
            AsyncAction<AsyncResult<DeleteUserResult>> userCallback,
            Class<DeleteUserResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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
     * ユーザを削除します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteUserAsync(
            DeleteUserRequest request,
            AsyncAction<AsyncResult<DeleteUserResult>> callback
    ) {
        DeleteUserTask task = new DeleteUserTask(request, callback, DeleteUserResult.class);
        session.execute(task);
    }

    /**
     * ユーザを削除します<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteUserResult deleteUser(
            DeleteUserRequest request
    ) {
        final AsyncResult<DeleteUserResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteUserAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeSecurityPoliciesTask extends Gs2RestSessionTask<DescribeSecurityPoliciesResult> {
        private DescribeSecurityPoliciesRequest request;

        public DescribeSecurityPoliciesTask(
            DescribeSecurityPoliciesRequest request,
            AsyncAction<AsyncResult<DescribeSecurityPoliciesResult>> userCallback,
            Class<DescribeSecurityPoliciesResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/securityPolicy";

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
     * セキュリティポリシーの一覧を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeSecurityPoliciesAsync(
            DescribeSecurityPoliciesRequest request,
            AsyncAction<AsyncResult<DescribeSecurityPoliciesResult>> callback
    ) {
        DescribeSecurityPoliciesTask task = new DescribeSecurityPoliciesTask(request, callback, DescribeSecurityPoliciesResult.class);
        session.execute(task);
    }

    /**
     * セキュリティポリシーの一覧を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeSecurityPoliciesResult describeSecurityPolicies(
            DescribeSecurityPoliciesRequest request
    ) {
        final AsyncResult<DescribeSecurityPoliciesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeSecurityPoliciesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeCommonSecurityPoliciesTask extends Gs2RestSessionTask<DescribeCommonSecurityPoliciesResult> {
        private DescribeCommonSecurityPoliciesRequest request;

        public DescribeCommonSecurityPoliciesTask(
            DescribeCommonSecurityPoliciesRequest request,
            AsyncAction<AsyncResult<DescribeCommonSecurityPoliciesResult>> userCallback,
            Class<DescribeCommonSecurityPoliciesResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/securityPolicy/common";

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
     * オーナーIDを指定してセキュリティポリシーの一覧を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeCommonSecurityPoliciesAsync(
            DescribeCommonSecurityPoliciesRequest request,
            AsyncAction<AsyncResult<DescribeCommonSecurityPoliciesResult>> callback
    ) {
        DescribeCommonSecurityPoliciesTask task = new DescribeCommonSecurityPoliciesTask(request, callback, DescribeCommonSecurityPoliciesResult.class);
        session.execute(task);
    }

    /**
     * オーナーIDを指定してセキュリティポリシーの一覧を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeCommonSecurityPoliciesResult describeCommonSecurityPolicies(
            DescribeCommonSecurityPoliciesRequest request
    ) {
        final AsyncResult<DescribeCommonSecurityPoliciesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCommonSecurityPoliciesAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateSecurityPolicyTask extends Gs2RestSessionTask<CreateSecurityPolicyResult> {
        private CreateSecurityPolicyRequest request;

        public CreateSecurityPolicyTask(
            CreateSecurityPolicyRequest request,
            AsyncAction<AsyncResult<CreateSecurityPolicyResult>> userCallback,
            Class<CreateSecurityPolicyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/securityPolicy";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getName() != null) {
                json.put("name", this.request.getName());
            }
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getPolicy() != null) {
                json.put("policy", this.request.getPolicy());
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
     * セキュリティポリシーを新規作成します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createSecurityPolicyAsync(
            CreateSecurityPolicyRequest request,
            AsyncAction<AsyncResult<CreateSecurityPolicyResult>> callback
    ) {
        CreateSecurityPolicyTask task = new CreateSecurityPolicyTask(request, callback, CreateSecurityPolicyResult.class);
        session.execute(task);
    }

    /**
     * セキュリティポリシーを新規作成します<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateSecurityPolicyResult createSecurityPolicy(
            CreateSecurityPolicyRequest request
    ) {
        final AsyncResult<CreateSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        createSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateSecurityPolicyTask extends Gs2RestSessionTask<UpdateSecurityPolicyResult> {
        private UpdateSecurityPolicyRequest request;

        public UpdateSecurityPolicyTask(
            UpdateSecurityPolicyRequest request,
            AsyncAction<AsyncResult<UpdateSecurityPolicyResult>> userCallback,
            Class<UpdateSecurityPolicyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/securityPolicy/{securityPolicyName}";

            url = url.replace("{securityPolicyName}", this.request.getSecurityPolicyName() == null|| this.request.getSecurityPolicyName().length() == 0 ? "null" : String.valueOf(this.request.getSecurityPolicyName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getDescription() != null) {
                json.put("description", this.request.getDescription());
            }
            if (this.request.getPolicy() != null) {
                json.put("policy", this.request.getPolicy());
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
     * セキュリティポリシーを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateSecurityPolicyAsync(
            UpdateSecurityPolicyRequest request,
            AsyncAction<AsyncResult<UpdateSecurityPolicyResult>> callback
    ) {
        UpdateSecurityPolicyTask task = new UpdateSecurityPolicyTask(request, callback, UpdateSecurityPolicyResult.class);
        session.execute(task);
    }

    /**
     * セキュリティポリシーを更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateSecurityPolicyResult updateSecurityPolicy(
            UpdateSecurityPolicyRequest request
    ) {
        final AsyncResult<UpdateSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetSecurityPolicyTask extends Gs2RestSessionTask<GetSecurityPolicyResult> {
        private GetSecurityPolicyRequest request;

        public GetSecurityPolicyTask(
            GetSecurityPolicyRequest request,
            AsyncAction<AsyncResult<GetSecurityPolicyResult>> userCallback,
            Class<GetSecurityPolicyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/securityPolicy/{securityPolicyName}";

            url = url.replace("{securityPolicyName}", this.request.getSecurityPolicyName() == null|| this.request.getSecurityPolicyName().length() == 0 ? "null" : String.valueOf(this.request.getSecurityPolicyName()));

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
     * セキュリティポリシーを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getSecurityPolicyAsync(
            GetSecurityPolicyRequest request,
            AsyncAction<AsyncResult<GetSecurityPolicyResult>> callback
    ) {
        GetSecurityPolicyTask task = new GetSecurityPolicyTask(request, callback, GetSecurityPolicyResult.class);
        session.execute(task);
    }

    /**
     * セキュリティポリシーを取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetSecurityPolicyResult getSecurityPolicy(
            GetSecurityPolicyRequest request
    ) {
        final AsyncResult<GetSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        getSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteSecurityPolicyTask extends Gs2RestSessionTask<DeleteSecurityPolicyResult> {
        private DeleteSecurityPolicyRequest request;

        public DeleteSecurityPolicyTask(
            DeleteSecurityPolicyRequest request,
            AsyncAction<AsyncResult<DeleteSecurityPolicyResult>> userCallback,
            Class<DeleteSecurityPolicyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/securityPolicy/{securityPolicyName}";

            url = url.replace("{securityPolicyName}", this.request.getSecurityPolicyName() == null|| this.request.getSecurityPolicyName().length() == 0 ? "null" : String.valueOf(this.request.getSecurityPolicyName()));

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
     * セキュリティポリシーを削除します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteSecurityPolicyAsync(
            DeleteSecurityPolicyRequest request,
            AsyncAction<AsyncResult<DeleteSecurityPolicyResult>> callback
    ) {
        DeleteSecurityPolicyTask task = new DeleteSecurityPolicyTask(request, callback, DeleteSecurityPolicyResult.class);
        session.execute(task);
    }

    /**
     * セキュリティポリシーを削除します<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteSecurityPolicyResult deleteSecurityPolicy(
            DeleteSecurityPolicyRequest request
    ) {
        final AsyncResult<DeleteSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribeIdentifiersTask extends Gs2RestSessionTask<DescribeIdentifiersResult> {
        private DescribeIdentifiersRequest request;

        public DescribeIdentifiersTask(
            DescribeIdentifiersRequest request,
            AsyncAction<AsyncResult<DescribeIdentifiersResult>> userCallback,
            Class<DescribeIdentifiersResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/identifier";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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
     * クレデンシャルの一覧を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeIdentifiersAsync(
            DescribeIdentifiersRequest request,
            AsyncAction<AsyncResult<DescribeIdentifiersResult>> callback
    ) {
        DescribeIdentifiersTask task = new DescribeIdentifiersTask(request, callback, DescribeIdentifiersResult.class);
        session.execute(task);
    }

    /**
     * クレデンシャルの一覧を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeIdentifiersResult describeIdentifiers(
            DescribeIdentifiersRequest request
    ) {
        final AsyncResult<DescribeIdentifiersResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeIdentifiersAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateIdentifierTask extends Gs2RestSessionTask<CreateIdentifierResult> {
        private CreateIdentifierRequest request;

        public CreateIdentifierTask(
            CreateIdentifierRequest request,
            AsyncAction<AsyncResult<CreateIdentifierResult>> userCallback,
            Class<CreateIdentifierResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/identifier";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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
     * クレデンシャルを新規作成します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createIdentifierAsync(
            CreateIdentifierRequest request,
            AsyncAction<AsyncResult<CreateIdentifierResult>> callback
    ) {
        CreateIdentifierTask task = new CreateIdentifierTask(request, callback, CreateIdentifierResult.class);
        session.execute(task);
    }

    /**
     * クレデンシャルを新規作成します<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateIdentifierResult createIdentifier(
            CreateIdentifierRequest request
    ) {
        final AsyncResult<CreateIdentifierResult>[] resultAsyncResult = new AsyncResult[]{null};
        createIdentifierAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetIdentifierTask extends Gs2RestSessionTask<GetIdentifierResult> {
        private GetIdentifierRequest request;

        public GetIdentifierTask(
            GetIdentifierRequest request,
            AsyncAction<AsyncResult<GetIdentifierResult>> userCallback,
            Class<GetIdentifierResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/identifier/{clientId}";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));
            url = url.replace("{clientId}", this.request.getClientId() == null|| this.request.getClientId().length() == 0 ? "null" : String.valueOf(this.request.getClientId()));

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
     * クレデンシャルを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getIdentifierAsync(
            GetIdentifierRequest request,
            AsyncAction<AsyncResult<GetIdentifierResult>> callback
    ) {
        GetIdentifierTask task = new GetIdentifierTask(request, callback, GetIdentifierResult.class);
        session.execute(task);
    }

    /**
     * クレデンシャルを取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetIdentifierResult getIdentifier(
            GetIdentifierRequest request
    ) {
        final AsyncResult<GetIdentifierResult>[] resultAsyncResult = new AsyncResult[]{null};
        getIdentifierAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteIdentifierTask extends Gs2RestSessionTask<DeleteIdentifierResult> {
        private DeleteIdentifierRequest request;

        public DeleteIdentifierTask(
            DeleteIdentifierRequest request,
            AsyncAction<AsyncResult<DeleteIdentifierResult>> userCallback,
            Class<DeleteIdentifierResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/identifier/{clientId}";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));
            url = url.replace("{clientId}", this.request.getClientId() == null|| this.request.getClientId().length() == 0 ? "null" : String.valueOf(this.request.getClientId()));

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
     * クレデンシャルを削除します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteIdentifierAsync(
            DeleteIdentifierRequest request,
            AsyncAction<AsyncResult<DeleteIdentifierResult>> callback
    ) {
        DeleteIdentifierTask task = new DeleteIdentifierTask(request, callback, DeleteIdentifierResult.class);
        session.execute(task);
    }

    /**
     * クレデンシャルを削除します<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteIdentifierResult deleteIdentifier(
            DeleteIdentifierRequest request
    ) {
        final AsyncResult<DeleteIdentifierResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteIdentifierAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DescribePasswordsTask extends Gs2RestSessionTask<DescribePasswordsResult> {
        private DescribePasswordsRequest request;

        public DescribePasswordsTask(
            DescribePasswordsRequest request,
            AsyncAction<AsyncResult<DescribePasswordsResult>> userCallback,
            Class<DescribePasswordsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/password";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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
     * パスワードの一覧を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describePasswordsAsync(
            DescribePasswordsRequest request,
            AsyncAction<AsyncResult<DescribePasswordsResult>> callback
    ) {
        DescribePasswordsTask task = new DescribePasswordsTask(request, callback, DescribePasswordsResult.class);
        session.execute(task);
    }

    /**
     * パスワードの一覧を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribePasswordsResult describePasswords(
            DescribePasswordsRequest request
    ) {
        final AsyncResult<DescribePasswordsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describePasswordsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreatePasswordTask extends Gs2RestSessionTask<CreatePasswordResult> {
        private CreatePasswordRequest request;

        public CreatePasswordTask(
            CreatePasswordRequest request,
            AsyncAction<AsyncResult<CreatePasswordResult>> userCallback,
            Class<CreatePasswordResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/password";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
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
     * パスワードを新規作成します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createPasswordAsync(
            CreatePasswordRequest request,
            AsyncAction<AsyncResult<CreatePasswordResult>> callback
    ) {
        CreatePasswordTask task = new CreatePasswordTask(request, callback, CreatePasswordResult.class);
        session.execute(task);
    }

    /**
     * パスワードを新規作成します<br>
     *
     * @param request リクエストパラメータ
     */
    public CreatePasswordResult createPassword(
            CreatePasswordRequest request
    ) {
        final AsyncResult<CreatePasswordResult>[] resultAsyncResult = new AsyncResult[]{null};
        createPasswordAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetPasswordTask extends Gs2RestSessionTask<GetPasswordResult> {
        private GetPasswordRequest request;

        public GetPasswordTask(
            GetPasswordRequest request,
            AsyncAction<AsyncResult<GetPasswordResult>> userCallback,
            Class<GetPasswordResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/password/entity";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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
     * パスワードを取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getPasswordAsync(
            GetPasswordRequest request,
            AsyncAction<AsyncResult<GetPasswordResult>> callback
    ) {
        GetPasswordTask task = new GetPasswordTask(request, callback, GetPasswordResult.class);
        session.execute(task);
    }

    /**
     * パスワードを取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetPasswordResult getPassword(
            GetPasswordRequest request
    ) {
        final AsyncResult<GetPasswordResult>[] resultAsyncResult = new AsyncResult[]{null};
        getPasswordAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeletePasswordTask extends Gs2RestSessionTask<DeletePasswordResult> {
        private DeletePasswordRequest request;

        public DeletePasswordTask(
            DeletePasswordRequest request,
            AsyncAction<AsyncResult<DeletePasswordResult>> userCallback,
            Class<DeletePasswordResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/password/entity";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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
     * パスワードを削除します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deletePasswordAsync(
            DeletePasswordRequest request,
            AsyncAction<AsyncResult<DeletePasswordResult>> callback
    ) {
        DeletePasswordTask task = new DeletePasswordTask(request, callback, DeletePasswordResult.class);
        session.execute(task);
    }

    /**
     * パスワードを削除します<br>
     *
     * @param request リクエストパラメータ
     */
    public DeletePasswordResult deletePassword(
            DeletePasswordRequest request
    ) {
        final AsyncResult<DeletePasswordResult>[] resultAsyncResult = new AsyncResult[]{null};
        deletePasswordAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetHasSecurityPolicyTask extends Gs2RestSessionTask<GetHasSecurityPolicyResult> {
        private GetHasSecurityPolicyRequest request;

        public GetHasSecurityPolicyTask(
            GetHasSecurityPolicyRequest request,
            AsyncAction<AsyncResult<GetHasSecurityPolicyResult>> userCallback,
            Class<GetHasSecurityPolicyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/securityPolicy";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

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
     * 割り当てられたセキュリティポリシーの一覧を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getHasSecurityPolicyAsync(
            GetHasSecurityPolicyRequest request,
            AsyncAction<AsyncResult<GetHasSecurityPolicyResult>> callback
    ) {
        GetHasSecurityPolicyTask task = new GetHasSecurityPolicyTask(request, callback, GetHasSecurityPolicyResult.class);
        session.execute(task);
    }

    /**
     * 割り当てられたセキュリティポリシーの一覧を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetHasSecurityPolicyResult getHasSecurityPolicy(
            GetHasSecurityPolicyRequest request
    ) {
        final AsyncResult<GetHasSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        getHasSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class AttachSecurityPolicyTask extends Gs2RestSessionTask<AttachSecurityPolicyResult> {
        private AttachSecurityPolicyRequest request;

        public AttachSecurityPolicyTask(
            AttachSecurityPolicyRequest request,
            AsyncAction<AsyncResult<AttachSecurityPolicyResult>> userCallback,
            Class<AttachSecurityPolicyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/securityPolicy";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getSecurityPolicyId() != null) {
                json.put("securityPolicyId", this.request.getSecurityPolicyId());
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
     * 割り当てられたセキュリティポリシーを新しくユーザーに割り当てます<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void attachSecurityPolicyAsync(
            AttachSecurityPolicyRequest request,
            AsyncAction<AsyncResult<AttachSecurityPolicyResult>> callback
    ) {
        AttachSecurityPolicyTask task = new AttachSecurityPolicyTask(request, callback, AttachSecurityPolicyResult.class);
        session.execute(task);
    }

    /**
     * 割り当てられたセキュリティポリシーを新しくユーザーに割り当てます<br>
     *
     * @param request リクエストパラメータ
     */
    public AttachSecurityPolicyResult attachSecurityPolicy(
            AttachSecurityPolicyRequest request
    ) {
        final AsyncResult<AttachSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        attachSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DetachSecurityPolicyTask extends Gs2RestSessionTask<DetachSecurityPolicyResult> {
        private DetachSecurityPolicyRequest request;

        public DetachSecurityPolicyTask(
            DetachSecurityPolicyRequest request,
            AsyncAction<AsyncResult<DetachSecurityPolicyResult>> userCallback,
            Class<DetachSecurityPolicyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/user/{userName}/securityPolicy/{securityPolicyId}";

            url = url.replace("{userName}", this.request.getUserName() == null|| this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));
            url = url.replace("{securityPolicyId}", this.request.getSecurityPolicyId() == null|| this.request.getSecurityPolicyId().length() == 0 ? "null" : String.valueOf(this.request.getSecurityPolicyId()));

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
     * 割り当てられたセキュリティポリシーをユーザーから外します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void detachSecurityPolicyAsync(
            DetachSecurityPolicyRequest request,
            AsyncAction<AsyncResult<DetachSecurityPolicyResult>> callback
    ) {
        DetachSecurityPolicyTask task = new DetachSecurityPolicyTask(request, callback, DetachSecurityPolicyResult.class);
        session.execute(task);
    }

    /**
     * 割り当てられたセキュリティポリシーをユーザーから外します<br>
     *
     * @param request リクエストパラメータ
     */
    public DetachSecurityPolicyResult detachSecurityPolicy(
            DetachSecurityPolicyRequest request
    ) {
        final AsyncResult<DetachSecurityPolicyResult>[] resultAsyncResult = new AsyncResult[]{null};
        detachSecurityPolicyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class LoginTask extends Gs2RestSessionTask<LoginResult> {
        private LoginRequest request;

        public LoginTask(
            LoginRequest request,
            AsyncAction<AsyncResult<LoginResult>> userCallback,
            Class<LoginResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/projectToken/login";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getClientId() != null) {
                json.put("clientId", this.request.getClientId());
            }
            if (this.request.getClientSecret() != null) {
                json.put("clientSecret", this.request.getClientSecret());
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
     * プロジェクトトークン を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void loginAsync(
            LoginRequest request,
            AsyncAction<AsyncResult<LoginResult>> callback
    ) {
        LoginTask task = new LoginTask(request, callback, LoginResult.class);
        session.execute(task);
    }

    /**
     * プロジェクトトークン を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public LoginResult login(
            LoginRequest request
    ) {
        final AsyncResult<LoginResult>[] resultAsyncResult = new AsyncResult[]{null};
        loginAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class LoginByUserTask extends Gs2RestSessionTask<LoginByUserResult> {
        private LoginByUserRequest request;

        public LoginByUserTask(
            LoginByUserRequest request,
            AsyncAction<AsyncResult<LoginByUserResult>> userCallback,
            Class<LoginByUserResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "identifier")
                .replace("{region}", session.getRegion().getName())
                + "/projectToken/login/user";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getUserName() != null) {
                json.put("userName", this.request.getUserName());
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
     * プロジェクトトークン を取得します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void loginByUserAsync(
            LoginByUserRequest request,
            AsyncAction<AsyncResult<LoginByUserResult>> callback
    ) {
        LoginByUserTask task = new LoginByUserTask(request, callback, LoginByUserResult.class);
        session.execute(task);
    }

    /**
     * プロジェクトトークン を取得します<br>
     *
     * @param request リクエストパラメータ
     */
    public LoginByUserResult loginByUser(
            LoginByUserRequest request
    ) {
        final AsyncResult<LoginByUserResult>[] resultAsyncResult = new AsyncResult[]{null};
        loginByUserAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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