
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import io.gs2.core.model.AsyncAction;
import io.gs2.core.model.AsyncResult;
import io.gs2.core.exception.*;
import io.gs2.core.net.*;
import io.gs2.core.util.EncodingUtil;

import io.gs2.core.AbstractGs2Client;
import io.gs2.project.request.*;
import io.gs2.project.result.*;
import io.gs2.project.model.*;public class Gs2ProjectRestClient extends AbstractGs2Client<Gs2ProjectRestClient> {

	public Gs2ProjectRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class CreateAccountTask extends Gs2RestSessionTask<CreateAccountResult> {
        private CreateAccountRequest request;

        public CreateAccountTask(
            CreateAccountRequest request,
            AsyncAction<AsyncResult<CreateAccountResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateAccountResult parse(JsonNode data) {
            return CreateAccountResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("email", request.getEmail());
                    put("fullName", request.getFullName());
                    put("companyName", request.getCompanyName());
                    put("password", request.getPassword());
                    put("lang", request.getLang());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

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

    public void createAccountAsync(
            CreateAccountRequest request,
            AsyncAction<AsyncResult<CreateAccountResult>> callback
    ) {
        CreateAccountTask task = new CreateAccountTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<VerifyResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public VerifyResult parse(JsonNode data) {
            return VerifyResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/verify";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("verifyToken", request.getVerifyToken());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

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

    public void verifyAsync(
            VerifyRequest request,
            AsyncAction<AsyncResult<VerifyResult>> callback
    ) {
        VerifyTask task = new VerifyTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<SignInResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public SignInResult parse(JsonNode data) {
            return SignInResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/signIn";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("email", request.getEmail());
                    put("password", request.getPassword());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

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

    public void signInAsync(
            SignInRequest request,
            AsyncAction<AsyncResult<SignInResult>> callback
    ) {
        SignInTask task = new SignInTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<IssueAccountTokenResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IssueAccountTokenResult parse(JsonNode data) {
            return IssueAccountTokenResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/accountToken";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("accountName", request.getAccountName());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

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

    public void issueAccountTokenAsync(
            IssueAccountTokenRequest request,
            AsyncAction<AsyncResult<IssueAccountTokenResult>> callback
    ) {
        IssueAccountTokenTask task = new IssueAccountTokenTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<ForgetResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ForgetResult parse(JsonNode data) {
            return ForgetResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/forget";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("email", request.getEmail());
                    put("lang", request.getLang());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

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

    public void forgetAsync(
            ForgetRequest request,
            AsyncAction<AsyncResult<ForgetResult>> callback
    ) {
        ForgetTask task = new ForgetTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<IssuePasswordResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IssuePasswordResult parse(JsonNode data) {
            return IssuePasswordResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/password/issue";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("issuePasswordToken", request.getIssuePasswordToken());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

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

    public void issuePasswordAsync(
            IssuePasswordRequest request,
            AsyncAction<AsyncResult<IssuePasswordResult>> callback
    ) {
        IssuePasswordTask task = new IssuePasswordTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<UpdateAccountResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateAccountResult parse(JsonNode data) {
            return UpdateAccountResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("email", request.getEmail());
                    put("fullName", request.getFullName());
                    put("companyName", request.getCompanyName());
                    put("password", request.getPassword());
                    put("accountToken", request.getAccountToken());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

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

    public void updateAccountAsync(
            UpdateAccountRequest request,
            AsyncAction<AsyncResult<UpdateAccountResult>> callback
    ) {
        UpdateAccountTask task = new UpdateAccountTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DeleteAccountResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteAccountResult parse(JsonNode data) {
            return DeleteAccountResult.fromJson(data);
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

    public void deleteAccountAsync(
            DeleteAccountRequest request,
            AsyncAction<AsyncResult<DeleteAccountResult>> callback
    ) {
        DeleteAccountTask task = new DeleteAccountTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DescribeProjectsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeProjectsResult parse(JsonNode data) {
            return DescribeProjectsResult.fromJson(data);
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

    public void describeProjectsAsync(
            DescribeProjectsRequest request,
            AsyncAction<AsyncResult<DescribeProjectsResult>> callback
    ) {
        DescribeProjectsTask task = new DescribeProjectsTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<CreateProjectResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateProjectResult parse(JsonNode data) {
            return CreateProjectResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("accountToken", request.getAccountToken());
                    put("name", request.getName());
                    put("description", request.getDescription());
                    put("plan", request.getPlan());
                    put("billingMethodName", request.getBillingMethodName());
                    put("enableEventBridge", request.getEnableEventBridge());
                    put("eventBridgeAwsAccountId", request.getEventBridgeAwsAccountId());
                    put("eventBridgeAwsRegion", request.getEventBridgeAwsRegion());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

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

    public void createProjectAsync(
            CreateProjectRequest request,
            AsyncAction<AsyncResult<CreateProjectResult>> callback
    ) {
        CreateProjectTask task = new CreateProjectTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetProjectResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetProjectResult parse(JsonNode data) {
            return GetProjectResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/{projectName}";

            url = url.replace("{projectName}", this.request.getProjectName() == null || this.request.getProjectName().length() == 0 ? "null" : String.valueOf(this.request.getProjectName()));

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

    public void getProjectAsync(
            GetProjectRequest request,
            AsyncAction<AsyncResult<GetProjectResult>> callback
    ) {
        GetProjectTask task = new GetProjectTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<GetProjectTokenResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetProjectTokenResult parse(JsonNode data) {
            return GetProjectTokenResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/project/{projectName}/projectToken";

            url = url.replace("{projectName}", this.request.getProjectName() == null || this.request.getProjectName().length() == 0 ? "null" : String.valueOf(this.request.getProjectName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("accountToken", request.getAccountToken());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

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

    public void getProjectTokenAsync(
            GetProjectTokenRequest request,
            AsyncAction<AsyncResult<GetProjectTokenResult>> callback
    ) {
        GetProjectTokenTask task = new GetProjectTokenTask(request, callback);
        session.execute(task);
    }

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

    class GetProjectTokenByIdentifierTask extends Gs2RestSessionTask<GetProjectTokenByIdentifierResult> {
        private GetProjectTokenByIdentifierRequest request;

        public GetProjectTokenByIdentifierTask(
            GetProjectTokenByIdentifierRequest request,
            AsyncAction<AsyncResult<GetProjectTokenByIdentifierResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetProjectTokenByIdentifierResult parse(JsonNode data) {
            return GetProjectTokenByIdentifierResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/{accountName}/project/{projectName}/user/{userName}/projectToken";

            url = url.replace("{accountName}", this.request.getAccountName() == null || this.request.getAccountName().length() == 0 ? "null" : String.valueOf(this.request.getAccountName()));
            url = url.replace("{projectName}", this.request.getProjectName() == null || this.request.getProjectName().length() == 0 ? "null" : String.valueOf(this.request.getProjectName()));
            url = url.replace("{userName}", this.request.getUserName() == null || this.request.getUserName().length() == 0 ? "null" : String.valueOf(this.request.getUserName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("password", request.getPassword());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

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

    public void getProjectTokenByIdentifierAsync(
            GetProjectTokenByIdentifierRequest request,
            AsyncAction<AsyncResult<GetProjectTokenByIdentifierResult>> callback
    ) {
        GetProjectTokenByIdentifierTask task = new GetProjectTokenByIdentifierTask(request, callback);
        session.execute(task);
    }

    public GetProjectTokenByIdentifierResult getProjectTokenByIdentifier(
            GetProjectTokenByIdentifierRequest request
    ) {
        final AsyncResult<GetProjectTokenByIdentifierResult>[] resultAsyncResult = new AsyncResult[]{null};
        getProjectTokenByIdentifierAsync(
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
            AsyncAction<AsyncResult<UpdateProjectResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateProjectResult parse(JsonNode data) {
            return UpdateProjectResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/{projectName}";

            url = url.replace("{projectName}", this.request.getProjectName() == null || this.request.getProjectName().length() == 0 ? "null" : String.valueOf(this.request.getProjectName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("accountToken", request.getAccountToken());
                    put("description", request.getDescription());
                    put("plan", request.getPlan());
                    put("billingMethodName", request.getBillingMethodName());
                    put("enableEventBridge", request.getEnableEventBridge());
                    put("eventBridgeAwsAccountId", request.getEventBridgeAwsAccountId());
                    put("eventBridgeAwsRegion", request.getEventBridgeAwsRegion());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

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

    public void updateProjectAsync(
            UpdateProjectRequest request,
            AsyncAction<AsyncResult<UpdateProjectResult>> callback
    ) {
        UpdateProjectTask task = new UpdateProjectTask(request, callback);
        session.execute(task);
    }

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
            AsyncAction<AsyncResult<DeleteProjectResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteProjectResult parse(JsonNode data) {
            return DeleteProjectResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/{projectName}";

            url = url.replace("{projectName}", this.request.getProjectName() == null || this.request.getProjectName().length() == 0 ? "null" : String.valueOf(this.request.getProjectName()));

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

    public void deleteProjectAsync(
            DeleteProjectRequest request,
            AsyncAction<AsyncResult<DeleteProjectResult>> callback
    ) {
        DeleteProjectTask task = new DeleteProjectTask(request, callback);
        session.execute(task);
    }

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

    class DescribeBillingMethodsTask extends Gs2RestSessionTask<DescribeBillingMethodsResult> {
        private DescribeBillingMethodsRequest request;

        public DescribeBillingMethodsTask(
            DescribeBillingMethodsRequest request,
            AsyncAction<AsyncResult<DescribeBillingMethodsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBillingMethodsResult parse(JsonNode data) {
            return DescribeBillingMethodsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/billingMethod";

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

    public void describeBillingMethodsAsync(
            DescribeBillingMethodsRequest request,
            AsyncAction<AsyncResult<DescribeBillingMethodsResult>> callback
    ) {
        DescribeBillingMethodsTask task = new DescribeBillingMethodsTask(request, callback);
        session.execute(task);
    }

    public DescribeBillingMethodsResult describeBillingMethods(
            DescribeBillingMethodsRequest request
    ) {
        final AsyncResult<DescribeBillingMethodsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBillingMethodsAsync(
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

    class CreateBillingMethodTask extends Gs2RestSessionTask<CreateBillingMethodResult> {
        private CreateBillingMethodRequest request;

        public CreateBillingMethodTask(
            CreateBillingMethodRequest request,
            AsyncAction<AsyncResult<CreateBillingMethodResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CreateBillingMethodResult parse(JsonNode data) {
            return CreateBillingMethodResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/billingMethod";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("accountToken", request.getAccountToken());
                    put("description", request.getDescription());
                    put("methodType", request.getMethodType());
                    put("cardCustomerId", request.getCardCustomerId());
                    put("partnerId", request.getPartnerId());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

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

    public void createBillingMethodAsync(
            CreateBillingMethodRequest request,
            AsyncAction<AsyncResult<CreateBillingMethodResult>> callback
    ) {
        CreateBillingMethodTask task = new CreateBillingMethodTask(request, callback);
        session.execute(task);
    }

    public CreateBillingMethodResult createBillingMethod(
            CreateBillingMethodRequest request
    ) {
        final AsyncResult<CreateBillingMethodResult>[] resultAsyncResult = new AsyncResult[]{null};
        createBillingMethodAsync(
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

    class GetBillingMethodTask extends Gs2RestSessionTask<GetBillingMethodResult> {
        private GetBillingMethodRequest request;

        public GetBillingMethodTask(
            GetBillingMethodRequest request,
            AsyncAction<AsyncResult<GetBillingMethodResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetBillingMethodResult parse(JsonNode data) {
            return GetBillingMethodResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/billingMethod/{billingMethodName}";

            url = url.replace("{billingMethodName}", this.request.getBillingMethodName() == null || this.request.getBillingMethodName().length() == 0 ? "null" : String.valueOf(this.request.getBillingMethodName()));

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

    public void getBillingMethodAsync(
            GetBillingMethodRequest request,
            AsyncAction<AsyncResult<GetBillingMethodResult>> callback
    ) {
        GetBillingMethodTask task = new GetBillingMethodTask(request, callback);
        session.execute(task);
    }

    public GetBillingMethodResult getBillingMethod(
            GetBillingMethodRequest request
    ) {
        final AsyncResult<GetBillingMethodResult>[] resultAsyncResult = new AsyncResult[]{null};
        getBillingMethodAsync(
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

    class UpdateBillingMethodTask extends Gs2RestSessionTask<UpdateBillingMethodResult> {
        private UpdateBillingMethodRequest request;

        public UpdateBillingMethodTask(
            UpdateBillingMethodRequest request,
            AsyncAction<AsyncResult<UpdateBillingMethodResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public UpdateBillingMethodResult parse(JsonNode data) {
            return UpdateBillingMethodResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/billingMethod/{billingMethodName}";

            url = url.replace("{billingMethodName}", this.request.getBillingMethodName() == null || this.request.getBillingMethodName().length() == 0 ? "null" : String.valueOf(this.request.getBillingMethodName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("accountToken", request.getAccountToken());
                    put("description", request.getDescription());
                    put("contextStack", request.getContextStack());
                }}
            ).toString().getBytes());

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

    public void updateBillingMethodAsync(
            UpdateBillingMethodRequest request,
            AsyncAction<AsyncResult<UpdateBillingMethodResult>> callback
    ) {
        UpdateBillingMethodTask task = new UpdateBillingMethodTask(request, callback);
        session.execute(task);
    }

    public UpdateBillingMethodResult updateBillingMethod(
            UpdateBillingMethodRequest request
    ) {
        final AsyncResult<UpdateBillingMethodResult>[] resultAsyncResult = new AsyncResult[]{null};
        updateBillingMethodAsync(
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

    class DeleteBillingMethodTask extends Gs2RestSessionTask<DeleteBillingMethodResult> {
        private DeleteBillingMethodRequest request;

        public DeleteBillingMethodTask(
            DeleteBillingMethodRequest request,
            AsyncAction<AsyncResult<DeleteBillingMethodResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DeleteBillingMethodResult parse(JsonNode data) {
            return DeleteBillingMethodResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/billingMethod/{billingMethodName}";

            url = url.replace("{billingMethodName}", this.request.getBillingMethodName() == null || this.request.getBillingMethodName().length() == 0 ? "null" : String.valueOf(this.request.getBillingMethodName()));

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

    public void deleteBillingMethodAsync(
            DeleteBillingMethodRequest request,
            AsyncAction<AsyncResult<DeleteBillingMethodResult>> callback
    ) {
        DeleteBillingMethodTask task = new DeleteBillingMethodTask(request, callback);
        session.execute(task);
    }

    public DeleteBillingMethodResult deleteBillingMethod(
            DeleteBillingMethodRequest request
    ) {
        final AsyncResult<DeleteBillingMethodResult>[] resultAsyncResult = new AsyncResult[]{null};
        deleteBillingMethodAsync(
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
            AsyncAction<AsyncResult<DescribeReceiptsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeReceiptsResult parse(JsonNode data) {
            return DescribeReceiptsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/receipt";

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

    public void describeReceiptsAsync(
            DescribeReceiptsRequest request,
            AsyncAction<AsyncResult<DescribeReceiptsResult>> callback
    ) {
        DescribeReceiptsTask task = new DescribeReceiptsTask(request, callback);
        session.execute(task);
    }

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

    class DescribeBillingsTask extends Gs2RestSessionTask<DescribeBillingsResult> {
        private DescribeBillingsRequest request;

        public DescribeBillingsTask(
            DescribeBillingsRequest request,
            AsyncAction<AsyncResult<DescribeBillingsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeBillingsResult parse(JsonNode data) {
            return DescribeBillingsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/billing/{projectName}/{year}/{month}";

            url = url.replace("{projectName}", this.request.getProjectName() == null || this.request.getProjectName().length() == 0 ? "null" : String.valueOf(this.request.getProjectName()));
            url = url.replace("{year}", this.request.getYear() == null  ? "null" : String.valueOf(this.request.getYear()));
            url = url.replace("{month}", this.request.getMonth() == null  ? "null" : String.valueOf(this.request.getMonth()));

            List<String> queryStrings = new ArrayList<> ();
            if (this.request.getContextStack() != null) {
                queryStrings.add("contextStack=" + EncodingUtil.urlEncode(this.request.getContextStack()));
            }
            if (this.request.getAccountToken() != null) {
                queryStrings.add("accountToken=" + EncodingUtil.urlEncode((String.valueOf(this.request.getAccountToken()))));
            }
            if (this.request.getRegion() != null) {
                queryStrings.add("region=" + EncodingUtil.urlEncode((String.valueOf(this.request.getRegion()))));
            }
            if (this.request.getService() != null) {
                queryStrings.add("service=" + EncodingUtil.urlEncode((String.valueOf(this.request.getService()))));
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

    public void describeBillingsAsync(
            DescribeBillingsRequest request,
            AsyncAction<AsyncResult<DescribeBillingsResult>> callback
    ) {
        DescribeBillingsTask task = new DescribeBillingsTask(request, callback);
        session.execute(task);
    }

    public DescribeBillingsResult describeBillings(
            DescribeBillingsRequest request
    ) {
        final AsyncResult<DescribeBillingsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeBillingsAsync(
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