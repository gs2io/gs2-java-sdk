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

package io.gs2.project;

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
import io.gs2.project.request.*;
import io.gs2.project.result.*;
import io.gs2.project.model.*;

/**
 * GS2 Project API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2ProjectRestClient extends AbstractGs2Client<Gs2ProjectRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2ProjectRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
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
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getEmail() != null) {
                json.put("email", this.request.getEmail());
            }
            if (this.request.getFullName() != null) {
                json.put("fullName", this.request.getFullName());
            }
            if (this.request.getCompanyName() != null) {
                json.put("companyName", this.request.getCompanyName());
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
     * アカウントを新規作成<br>
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
     * アカウントを新規作成<br>
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

    class VerifyTask extends Gs2RestSessionTask<VerifyResult> {
        private VerifyRequest request;

        public VerifyTask(
            VerifyRequest request,
            AsyncAction<AsyncResult<VerifyResult>> userCallback,
            Class<VerifyResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/verify";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getVerifyToken() != null) {
                json.put("verifyToken", this.request.getVerifyToken());
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
     * GS2アカウントを有効化します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void verifyAsync(
            VerifyRequest request,
            AsyncAction<AsyncResult<VerifyResult>> callback
    ) {
        VerifyTask task = new VerifyTask(request, callback, VerifyResult.class);
        session.execute(task);
    }

    /**
     * GS2アカウントを有効化します<br>
     *
     * @param request リクエストパラメータ
     */
    public VerifyResult verify(
            VerifyRequest request
    ) {
        final AsyncResult<VerifyResult>[] resultAsyncResult = new AsyncResult[]{null};
        verifyAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class SignInTask extends Gs2RestSessionTask<SignInResult> {
        private SignInRequest request;

        public SignInTask(
            SignInRequest request,
            AsyncAction<AsyncResult<SignInResult>> userCallback,
            Class<SignInResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/signIn";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getEmail() != null) {
                json.put("email", this.request.getEmail());
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
     * サインインします<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void signInAsync(
            SignInRequest request,
            AsyncAction<AsyncResult<SignInResult>> callback
    ) {
        SignInTask task = new SignInTask(request, callback, SignInResult.class);
        session.execute(task);
    }

    /**
     * サインインします<br>
     *
     * @param request リクエストパラメータ
     */
    public SignInResult signIn(
            SignInRequest request
    ) {
        final AsyncResult<SignInResult>[] resultAsyncResult = new AsyncResult[]{null};
        signInAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class IssueAccountTokenTask extends Gs2RestSessionTask<IssueAccountTokenResult> {
        private IssueAccountTokenRequest request;

        public IssueAccountTokenTask(
            IssueAccountTokenRequest request,
            AsyncAction<AsyncResult<IssueAccountTokenResult>> userCallback,
            Class<IssueAccountTokenResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/accountToken";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getAccountName() != null) {
                json.put("accountName", this.request.getAccountName());
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
     * 指定したアカウント名のアカウントトークンを発行<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void issueAccountTokenAsync(
            IssueAccountTokenRequest request,
            AsyncAction<AsyncResult<IssueAccountTokenResult>> callback
    ) {
        IssueAccountTokenTask task = new IssueAccountTokenTask(request, callback, IssueAccountTokenResult.class);
        session.execute(task);
    }

    /**
     * 指定したアカウント名のアカウントトークンを発行<br>
     *
     * @param request リクエストパラメータ
     */
    public IssueAccountTokenResult issueAccountToken(
            IssueAccountTokenRequest request
    ) {
        final AsyncResult<IssueAccountTokenResult>[] resultAsyncResult = new AsyncResult[]{null};
        issueAccountTokenAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class ForgetTask extends Gs2RestSessionTask<ForgetResult> {
        private ForgetRequest request;

        public ForgetTask(
            ForgetRequest request,
            AsyncAction<AsyncResult<ForgetResult>> userCallback,
            Class<ForgetResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/forget";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getEmail() != null) {
                json.put("email", this.request.getEmail());
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
     * パスワード再発行トークンを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void forgetAsync(
            ForgetRequest request,
            AsyncAction<AsyncResult<ForgetResult>> callback
    ) {
        ForgetTask task = new ForgetTask(request, callback, ForgetResult.class);
        session.execute(task);
    }

    /**
     * パスワード再発行トークンを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public ForgetResult forget(
            ForgetRequest request
    ) {
        final AsyncResult<ForgetResult>[] resultAsyncResult = new AsyncResult[]{null};
        forgetAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class IssuePasswordTask extends Gs2RestSessionTask<IssuePasswordResult> {
        private IssuePasswordRequest request;

        public IssuePasswordTask(
            IssuePasswordRequest request,
            AsyncAction<AsyncResult<IssuePasswordResult>> userCallback,
            Class<IssuePasswordResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/password/issue";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getIssuePasswordToken() != null) {
                json.put("issuePasswordToken", this.request.getIssuePasswordToken());
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
     * パスワードを再発行<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void issuePasswordAsync(
            IssuePasswordRequest request,
            AsyncAction<AsyncResult<IssuePasswordResult>> callback
    ) {
        IssuePasswordTask task = new IssuePasswordTask(request, callback, IssuePasswordResult.class);
        session.execute(task);
    }

    /**
     * パスワードを再発行<br>
     *
     * @param request リクエストパラメータ
     */
    public IssuePasswordResult issuePassword(
            IssuePasswordRequest request
    ) {
        final AsyncResult<IssuePasswordResult>[] resultAsyncResult = new AsyncResult[]{null};
        issuePasswordAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateAccountTask extends Gs2RestSessionTask<UpdateAccountResult> {
        private UpdateAccountRequest request;

        public UpdateAccountTask(
            UpdateAccountRequest request,
            AsyncAction<AsyncResult<UpdateAccountResult>> userCallback,
            Class<UpdateAccountResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getEmail() != null) {
                json.put("email", this.request.getEmail());
            }
            if (this.request.getFullName() != null) {
                json.put("fullName", this.request.getFullName());
            }
            if (this.request.getCompanyName() != null) {
                json.put("companyName", this.request.getCompanyName());
            }
            if (this.request.getPassword() != null) {
                json.put("password", this.request.getPassword());
            }
            if (this.request.getAccountToken() != null) {
                json.put("accountToken", this.request.getAccountToken());
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
     * GS2アカウントを更新します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateAccountAsync(
            UpdateAccountRequest request,
            AsyncAction<AsyncResult<UpdateAccountResult>> callback
    ) {
        UpdateAccountTask task = new UpdateAccountTask(request, callback, UpdateAccountResult.class);
        session.execute(task);
    }

    /**
     * GS2アカウントを更新します<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateAccountResult updateAccount(
            UpdateAccountRequest request
    ) {
        final AsyncResult<UpdateAccountResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateAccountAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account";

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
     * GS2アカウントを削除します<br>
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
     * GS2アカウントを削除します<br>
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

    class DescribeProjectsTask extends Gs2RestSessionTask<DescribeProjectsResult> {
        private DescribeProjectsRequest request;

        public DescribeProjectsTask(
            DescribeProjectsRequest request,
            AsyncAction<AsyncResult<DescribeProjectsResult>> userCallback,
            Class<DescribeProjectsResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project";

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getAccountToken() != null) {
                queryStrings.add("accountToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getAccountToken()))));
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
     * プロジェクトの一覧を取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void describeProjectsAsync(
            DescribeProjectsRequest request,
            AsyncAction<AsyncResult<DescribeProjectsResult>> callback
    ) {
        DescribeProjectsTask task = new DescribeProjectsTask(request, callback, DescribeProjectsResult.class);
        session.execute(task);
    }

    /**
     * プロジェクトの一覧を取得<br>
     *
     * @param request リクエストパラメータ
     */
    public DescribeProjectsResult describeProjects(
            DescribeProjectsRequest request
    ) {
        final AsyncResult<DescribeProjectsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeProjectsAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class CreateProjectTask extends Gs2RestSessionTask<CreateProjectResult> {
        private CreateProjectRequest request;

        public CreateProjectTask(
            CreateProjectRequest request,
            AsyncAction<AsyncResult<CreateProjectResult>> userCallback,
            Class<CreateProjectResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getAccountToken() != null) {
                json.put("accountToken", this.request.getAccountToken());
            }
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
     * プロジェクトを新規作成<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void createProjectAsync(
            CreateProjectRequest request,
            AsyncAction<AsyncResult<CreateProjectResult>> callback
    ) {
        CreateProjectTask task = new CreateProjectTask(request, callback, CreateProjectResult.class);
        session.execute(task);
    }

    /**
     * プロジェクトを新規作成<br>
     *
     * @param request リクエストパラメータ
     */
    public CreateProjectResult createProject(
            CreateProjectRequest request
    ) {
        final AsyncResult<CreateProjectResult>[] resultAsyncResult = new AsyncResult[]{null};
        createProjectAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetProjectTask extends Gs2RestSessionTask<GetProjectResult> {
        private GetProjectRequest request;

        public GetProjectTask(
            GetProjectRequest request,
            AsyncAction<AsyncResult<GetProjectResult>> userCallback,
            Class<GetProjectResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/{projectName}";

            url = url.replace("{projectName}", this.request.getProjectName() == null|| this.request.getProjectName().length() == 0 ? "null" : String.valueOf(this.request.getProjectName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getAccountToken() != null) {
                queryStrings.add("accountToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getAccountToken()))));
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
     * プロジェクトを取得<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getProjectAsync(
            GetProjectRequest request,
            AsyncAction<AsyncResult<GetProjectResult>> callback
    ) {
        GetProjectTask task = new GetProjectTask(request, callback, GetProjectResult.class);
        session.execute(task);
    }

    /**
     * プロジェクトを取得<br>
     *
     * @param request リクエストパラメータ
     */
    public GetProjectResult getProject(
            GetProjectRequest request
    ) {
        final AsyncResult<GetProjectResult>[] resultAsyncResult = new AsyncResult[]{null};
        getProjectAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class GetProjectTokenTask extends Gs2RestSessionTask<GetProjectTokenResult> {
        private GetProjectTokenRequest request;

        public GetProjectTokenTask(
            GetProjectTokenRequest request,
            AsyncAction<AsyncResult<GetProjectTokenResult>> userCallback,
            Class<GetProjectTokenResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/project/{projectName}/projectToken";

            url = url.replace("{projectName}", this.request.getProjectName() == null|| this.request.getProjectName().length() == 0 ? "null" : String.valueOf(this.request.getProjectName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getAccountToken() != null) {
                json.put("accountToken", this.request.getAccountToken());
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
     * プロジェクトトークンを発行します<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void getProjectTokenAsync(
            GetProjectTokenRequest request,
            AsyncAction<AsyncResult<GetProjectTokenResult>> callback
    ) {
        GetProjectTokenTask task = new GetProjectTokenTask(request, callback, GetProjectTokenResult.class);
        session.execute(task);
    }

    /**
     * プロジェクトトークンを発行します<br>
     *
     * @param request リクエストパラメータ
     */
    public GetProjectTokenResult getProjectToken(
            GetProjectTokenRequest request
    ) {
        final AsyncResult<GetProjectTokenResult>[] resultAsyncResult = new AsyncResult[]{null};
        getProjectTokenAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class UpdateProjectTask extends Gs2RestSessionTask<UpdateProjectResult> {
        private UpdateProjectRequest request;

        public UpdateProjectTask(
            UpdateProjectRequest request,
            AsyncAction<AsyncResult<UpdateProjectResult>> userCallback,
            Class<UpdateProjectResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/{projectName}";

            url = url.replace("{projectName}", this.request.getProjectName() == null|| this.request.getProjectName().length() == 0 ? "null" : String.valueOf(this.request.getProjectName()));

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getAccountToken() != null) {
                json.put("accountToken", this.request.getAccountToken());
            }
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
     * プロジェクトを更新<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void updateProjectAsync(
            UpdateProjectRequest request,
            AsyncAction<AsyncResult<UpdateProjectResult>> callback
    ) {
        UpdateProjectTask task = new UpdateProjectTask(request, callback, UpdateProjectResult.class);
        session.execute(task);
    }

    /**
     * プロジェクトを更新<br>
     *
     * @param request リクエストパラメータ
     */
    public UpdateProjectResult updateProject(
            UpdateProjectRequest request
    ) {
        final AsyncResult<UpdateProjectResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateProjectAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class DeleteProjectTask extends Gs2RestSessionTask<DeleteProjectResult> {
        private DeleteProjectRequest request;

        public DeleteProjectTask(
            DeleteProjectRequest request,
            AsyncAction<AsyncResult<DeleteProjectResult>> userCallback,
            Class<DeleteProjectResult> clazz
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback,
                    clazz
            );
            this.request = request;
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/{projectName}";

            url = url.replace("{projectName}", this.request.getProjectName() == null|| this.request.getProjectName().length() == 0 ? "null" : String.valueOf(this.request.getProjectName()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getAccountToken() != null) {
                queryStrings.add("accountToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getAccountToken()))));
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
     * プロジェクトを削除<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void deleteProjectAsync(
            DeleteProjectRequest request,
            AsyncAction<AsyncResult<DeleteProjectResult>> callback
    ) {
        DeleteProjectTask task = new DeleteProjectTask(request, callback, DeleteProjectResult.class);
        session.execute(task);
    }

    /**
     * プロジェクトを削除<br>
     *
     * @param request リクエストパラメータ
     */
    public DeleteProjectResult deleteProject(
            DeleteProjectRequest request
    ) {
        final AsyncResult<DeleteProjectResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteProjectAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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