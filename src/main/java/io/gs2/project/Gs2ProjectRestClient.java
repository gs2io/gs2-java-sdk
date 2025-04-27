
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
import java.util.concurrent.atomic.AtomicReference;
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
import io.gs2.project.model.*;

public class Gs2ProjectRestClient extends AbstractGs2Client<Gs2ProjectRestClient> {

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
                    put("otp", request.getOtp());
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

    class EnableMfaTask extends Gs2RestSessionTask<EnableMfaResult> {
        private EnableMfaRequest request;

        public EnableMfaTask(
            EnableMfaRequest request,
            AsyncAction<AsyncResult<EnableMfaResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public EnableMfaResult parse(JsonNode data) {
            return EnableMfaResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/mfa";

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

    public void enableMfaAsync(
            EnableMfaRequest request,
            AsyncAction<AsyncResult<EnableMfaResult>> callback
    ) {
        EnableMfaTask task = new EnableMfaTask(request, callback);
        session.execute(task);
    }

    public EnableMfaResult enableMfa(
            EnableMfaRequest request
    ) {
        final AsyncResult<EnableMfaResult>[] resultAsyncResult = new AsyncResult[]{null};
        enableMfaAsync(
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

    class ChallengeMfaTask extends Gs2RestSessionTask<ChallengeMfaResult> {
        private ChallengeMfaRequest request;

        public ChallengeMfaTask(
            ChallengeMfaRequest request,
            AsyncAction<AsyncResult<ChallengeMfaResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ChallengeMfaResult parse(JsonNode data) {
            return ChallengeMfaResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/mfa/challenge";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("accountToken", request.getAccountToken());
                    put("passcode", request.getPasscode());
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

    public void challengeMfaAsync(
            ChallengeMfaRequest request,
            AsyncAction<AsyncResult<ChallengeMfaResult>> callback
    ) {
        ChallengeMfaTask task = new ChallengeMfaTask(request, callback);
        session.execute(task);
    }

    public ChallengeMfaResult challengeMfa(
            ChallengeMfaRequest request
    ) {
        final AsyncResult<ChallengeMfaResult>[] resultAsyncResult = new AsyncResult[]{null};
        challengeMfaAsync(
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

    class DisableMfaTask extends Gs2RestSessionTask<DisableMfaResult> {
        private DisableMfaRequest request;

        public DisableMfaTask(
            DisableMfaRequest request,
            AsyncAction<AsyncResult<DisableMfaResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DisableMfaResult parse(JsonNode data) {
            return DisableMfaResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/mfa";

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

    public void disableMfaAsync(
            DisableMfaRequest request,
            AsyncAction<AsyncResult<DisableMfaResult>> callback
    ) {
        DisableMfaTask task = new DisableMfaTask(request, callback);
        session.execute(task);
    }

    public DisableMfaResult disableMfa(
            DisableMfaRequest request
    ) {
        final AsyncResult<DisableMfaResult>[] resultAsyncResult = new AsyncResult[]{null};
        disableMfaAsync(
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
                    put("currency", request.getCurrency());
                    put("activateRegionName", request.getActivateRegionName());
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
                    put("otp", request.getOtp());
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

    class ActivateRegionTask extends Gs2RestSessionTask<ActivateRegionResult> {
        private ActivateRegionRequest request;

        public ActivateRegionTask(
            ActivateRegionRequest request,
            AsyncAction<AsyncResult<ActivateRegionResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ActivateRegionResult parse(JsonNode data) {
            return ActivateRegionResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/{projectName}/region/{regionName}/activate";

            url = url.replace("{projectName}", this.request.getProjectName() == null || this.request.getProjectName().length() == 0 ? "null" : String.valueOf(this.request.getProjectName()));
            url = url.replace("{regionName}", this.request.getRegionName() == null || this.request.getRegionName().length() == 0 ? "null" : String.valueOf(this.request.getRegionName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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

    public void activateRegionAsync(
            ActivateRegionRequest request,
            AsyncAction<AsyncResult<ActivateRegionResult>> callback
    ) {
        ActivateRegionTask task = new ActivateRegionTask(request, callback);
        session.execute(task);
    }

    public ActivateRegionResult activateRegion(
            ActivateRegionRequest request
    ) {
        final AsyncResult<ActivateRegionResult>[] resultAsyncResult = new AsyncResult[]{null};
        activateRegionAsync(
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

    class WaitActivateRegionTask extends Gs2RestSessionTask<WaitActivateRegionResult> {
        private WaitActivateRegionRequest request;

        public WaitActivateRegionTask(
            WaitActivateRegionRequest request,
            AsyncAction<AsyncResult<WaitActivateRegionResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public WaitActivateRegionResult parse(JsonNode data) {
            return WaitActivateRegionResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/{projectName}/region/{regionName}/activate/wait";

            url = url.replace("{projectName}", this.request.getProjectName() == null || this.request.getProjectName().length() == 0 ? "null" : String.valueOf(this.request.getProjectName()));
            url = url.replace("{regionName}", this.request.getRegionName() == null || this.request.getRegionName().length() == 0 ? "null" : String.valueOf(this.request.getRegionName()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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

    public void waitActivateRegionAsync(
            WaitActivateRegionRequest request,
            AsyncAction<AsyncResult<WaitActivateRegionResult>> callback
    ) {
        WaitActivateRegionTask task = new WaitActivateRegionTask(request, callback);
        session.execute(task);
    }

    public WaitActivateRegionResult waitActivateRegion(
            WaitActivateRegionRequest request
    ) {
        final AsyncResult<WaitActivateRegionResult>[] resultAsyncResult = new AsyncResult[]{null};
        waitActivateRegionAsync(
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

    class DescribeDumpProgressesTask extends Gs2RestSessionTask<DescribeDumpProgressesResult> {
        private DescribeDumpProgressesRequest request;

        public DescribeDumpProgressesTask(
            DescribeDumpProgressesRequest request,
            AsyncAction<AsyncResult<DescribeDumpProgressesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeDumpProgressesResult parse(JsonNode data) {
            return DescribeDumpProgressesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/dump/progress";

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

    public void describeDumpProgressesAsync(
            DescribeDumpProgressesRequest request,
            AsyncAction<AsyncResult<DescribeDumpProgressesResult>> callback
    ) {
        DescribeDumpProgressesTask task = new DescribeDumpProgressesTask(request, callback);
        session.execute(task);
    }

    public DescribeDumpProgressesResult describeDumpProgresses(
            DescribeDumpProgressesRequest request
    ) {
        final AsyncResult<DescribeDumpProgressesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeDumpProgressesAsync(
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

    class GetDumpProgressTask extends Gs2RestSessionTask<GetDumpProgressResult> {
        private GetDumpProgressRequest request;

        public GetDumpProgressTask(
            GetDumpProgressRequest request,
            AsyncAction<AsyncResult<GetDumpProgressResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetDumpProgressResult parse(JsonNode data) {
            return GetDumpProgressResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/dump/progress/{transactionId}";

            url = url.replace("{transactionId}", this.request.getTransactionId() == null || this.request.getTransactionId().length() == 0 ? "null" : String.valueOf(this.request.getTransactionId()));

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

    public void getDumpProgressAsync(
            GetDumpProgressRequest request,
            AsyncAction<AsyncResult<GetDumpProgressResult>> callback
    ) {
        GetDumpProgressTask task = new GetDumpProgressTask(request, callback);
        session.execute(task);
    }

    public GetDumpProgressResult getDumpProgress(
            GetDumpProgressRequest request
    ) {
        final AsyncResult<GetDumpProgressResult>[] resultAsyncResult = new AsyncResult[]{null};
        getDumpProgressAsync(
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

    class WaitDumpUserDataTask extends Gs2RestSessionTask<WaitDumpUserDataResult> {
        private WaitDumpUserDataRequest request;

        public WaitDumpUserDataTask(
            WaitDumpUserDataRequest request,
            AsyncAction<AsyncResult<WaitDumpUserDataResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public WaitDumpUserDataResult parse(JsonNode data) {
            return WaitDumpUserDataResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/system/{ownerId}/project/dump/progress/{transactionId}/wait";

            url = url.replace("{ownerId}", this.request.getOwnerId() == null || this.request.getOwnerId().length() == 0 ? "null" : String.valueOf(this.request.getOwnerId()));
            url = url.replace("{transactionId}", this.request.getTransactionId() == null || this.request.getTransactionId().length() == 0 ? "null" : String.valueOf(this.request.getTransactionId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userId", request.getUserId());
                    put("microserviceName", request.getMicroserviceName());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void waitDumpUserDataAsync(
            WaitDumpUserDataRequest request,
            AsyncAction<AsyncResult<WaitDumpUserDataResult>> callback
    ) {
        WaitDumpUserDataTask task = new WaitDumpUserDataTask(request, callback);
        session.execute(task);
    }

    public WaitDumpUserDataResult waitDumpUserData(
            WaitDumpUserDataRequest request
    ) {
        final AsyncResult<WaitDumpUserDataResult>[] resultAsyncResult = new AsyncResult[]{null};
        waitDumpUserDataAsync(
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

    class ArchiveDumpUserDataTask extends Gs2RestSessionTask<ArchiveDumpUserDataResult> {
        private ArchiveDumpUserDataRequest request;

        public ArchiveDumpUserDataTask(
            ArchiveDumpUserDataRequest request,
            AsyncAction<AsyncResult<ArchiveDumpUserDataResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ArchiveDumpUserDataResult parse(JsonNode data) {
            return ArchiveDumpUserDataResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/system/{ownerId}/project/dump/progress/{transactionId}/archive";

            url = url.replace("{ownerId}", this.request.getOwnerId() == null || this.request.getOwnerId().length() == 0 ? "null" : String.valueOf(this.request.getOwnerId()));
            url = url.replace("{transactionId}", this.request.getTransactionId() == null || this.request.getTransactionId().length() == 0 ? "null" : String.valueOf(this.request.getTransactionId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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

    public void archiveDumpUserDataAsync(
            ArchiveDumpUserDataRequest request,
            AsyncAction<AsyncResult<ArchiveDumpUserDataResult>> callback
    ) {
        ArchiveDumpUserDataTask task = new ArchiveDumpUserDataTask(request, callback);
        session.execute(task);
    }

    public ArchiveDumpUserDataResult archiveDumpUserData(
            ArchiveDumpUserDataRequest request
    ) {
        final AsyncResult<ArchiveDumpUserDataResult>[] resultAsyncResult = new AsyncResult[]{null};
        archiveDumpUserDataAsync(
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

    class DumpUserDataTask extends Gs2RestSessionTask<DumpUserDataResult> {
        private DumpUserDataRequest request;

        public DumpUserDataTask(
            DumpUserDataRequest request,
            AsyncAction<AsyncResult<DumpUserDataResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DumpUserDataResult parse(JsonNode data) {
            return DumpUserDataResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/dump/{userId}";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void dumpUserDataAsync(
            DumpUserDataRequest request,
            AsyncAction<AsyncResult<DumpUserDataResult>> callback
    ) {
        DumpUserDataTask task = new DumpUserDataTask(request, callback);
        session.execute(task);
    }

    public DumpUserDataResult dumpUserData(
            DumpUserDataRequest request
    ) {
        final AsyncResult<DumpUserDataResult>[] resultAsyncResult = new AsyncResult[]{null};
        dumpUserDataAsync(
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

    class GetDumpUserDataTask extends Gs2RestSessionTask<GetDumpUserDataResult> {
        private GetDumpUserDataRequest request;

        public GetDumpUserDataTask(
            GetDumpUserDataRequest request,
            AsyncAction<AsyncResult<GetDumpUserDataResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetDumpUserDataResult parse(JsonNode data) {
            return GetDumpUserDataResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/dump/{transactionId}";

            url = url.replace("{transactionId}", this.request.getTransactionId() == null || this.request.getTransactionId().length() == 0 ? "null" : String.valueOf(this.request.getTransactionId()));

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

    public void getDumpUserDataAsync(
            GetDumpUserDataRequest request,
            AsyncAction<AsyncResult<GetDumpUserDataResult>> callback
    ) {
        GetDumpUserDataTask task = new GetDumpUserDataTask(request, callback);
        session.execute(task);
    }

    public GetDumpUserDataResult getDumpUserData(
            GetDumpUserDataRequest request
    ) {
        final AsyncResult<GetDumpUserDataResult>[] resultAsyncResult = new AsyncResult[]{null};
        getDumpUserDataAsync(
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

    class DescribeCleanProgressesTask extends Gs2RestSessionTask<DescribeCleanProgressesResult> {
        private DescribeCleanProgressesRequest request;

        public DescribeCleanProgressesTask(
            DescribeCleanProgressesRequest request,
            AsyncAction<AsyncResult<DescribeCleanProgressesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeCleanProgressesResult parse(JsonNode data) {
            return DescribeCleanProgressesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/clean/progress";

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

    public void describeCleanProgressesAsync(
            DescribeCleanProgressesRequest request,
            AsyncAction<AsyncResult<DescribeCleanProgressesResult>> callback
    ) {
        DescribeCleanProgressesTask task = new DescribeCleanProgressesTask(request, callback);
        session.execute(task);
    }

    public DescribeCleanProgressesResult describeCleanProgresses(
            DescribeCleanProgressesRequest request
    ) {
        final AsyncResult<DescribeCleanProgressesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeCleanProgressesAsync(
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

    class GetCleanProgressTask extends Gs2RestSessionTask<GetCleanProgressResult> {
        private GetCleanProgressRequest request;

        public GetCleanProgressTask(
            GetCleanProgressRequest request,
            AsyncAction<AsyncResult<GetCleanProgressResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetCleanProgressResult parse(JsonNode data) {
            return GetCleanProgressResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/clean/progress/{transactionId}";

            url = url.replace("{transactionId}", this.request.getTransactionId() == null || this.request.getTransactionId().length() == 0 ? "null" : String.valueOf(this.request.getTransactionId()));

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

    public void getCleanProgressAsync(
            GetCleanProgressRequest request,
            AsyncAction<AsyncResult<GetCleanProgressResult>> callback
    ) {
        GetCleanProgressTask task = new GetCleanProgressTask(request, callback);
        session.execute(task);
    }

    public GetCleanProgressResult getCleanProgress(
            GetCleanProgressRequest request
    ) {
        final AsyncResult<GetCleanProgressResult>[] resultAsyncResult = new AsyncResult[]{null};
        getCleanProgressAsync(
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

    class WaitCleanUserDataTask extends Gs2RestSessionTask<WaitCleanUserDataResult> {
        private WaitCleanUserDataRequest request;

        public WaitCleanUserDataTask(
            WaitCleanUserDataRequest request,
            AsyncAction<AsyncResult<WaitCleanUserDataResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public WaitCleanUserDataResult parse(JsonNode data) {
            return WaitCleanUserDataResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/clean/progress/{transactionId}/wait";

            url = url.replace("{transactionId}", this.request.getTransactionId() == null || this.request.getTransactionId().length() == 0 ? "null" : String.valueOf(this.request.getTransactionId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userId", request.getUserId());
                    put("microserviceName", request.getMicroserviceName());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void waitCleanUserDataAsync(
            WaitCleanUserDataRequest request,
            AsyncAction<AsyncResult<WaitCleanUserDataResult>> callback
    ) {
        WaitCleanUserDataTask task = new WaitCleanUserDataTask(request, callback);
        session.execute(task);
    }

    public WaitCleanUserDataResult waitCleanUserData(
            WaitCleanUserDataRequest request
    ) {
        final AsyncResult<WaitCleanUserDataResult>[] resultAsyncResult = new AsyncResult[]{null};
        waitCleanUserDataAsync(
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

    class CleanUserDataTask extends Gs2RestSessionTask<CleanUserDataResult> {
        private CleanUserDataRequest request;

        public CleanUserDataTask(
            CleanUserDataRequest request,
            AsyncAction<AsyncResult<CleanUserDataResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public CleanUserDataResult parse(JsonNode data) {
            return CleanUserDataResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/clean/{userId}";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void cleanUserDataAsync(
            CleanUserDataRequest request,
            AsyncAction<AsyncResult<CleanUserDataResult>> callback
    ) {
        CleanUserDataTask task = new CleanUserDataTask(request, callback);
        session.execute(task);
    }

    public CleanUserDataResult cleanUserData(
            CleanUserDataRequest request
    ) {
        final AsyncResult<CleanUserDataResult>[] resultAsyncResult = new AsyncResult[]{null};
        cleanUserDataAsync(
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

    class DescribeImportProgressesTask extends Gs2RestSessionTask<DescribeImportProgressesResult> {
        private DescribeImportProgressesRequest request;

        public DescribeImportProgressesTask(
            DescribeImportProgressesRequest request,
            AsyncAction<AsyncResult<DescribeImportProgressesResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeImportProgressesResult parse(JsonNode data) {
            return DescribeImportProgressesResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/import/progress";

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

    public void describeImportProgressesAsync(
            DescribeImportProgressesRequest request,
            AsyncAction<AsyncResult<DescribeImportProgressesResult>> callback
    ) {
        DescribeImportProgressesTask task = new DescribeImportProgressesTask(request, callback);
        session.execute(task);
    }

    public DescribeImportProgressesResult describeImportProgresses(
            DescribeImportProgressesRequest request
    ) {
        final AsyncResult<DescribeImportProgressesResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeImportProgressesAsync(
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

    class GetImportProgressTask extends Gs2RestSessionTask<GetImportProgressResult> {
        private GetImportProgressRequest request;

        public GetImportProgressTask(
            GetImportProgressRequest request,
            AsyncAction<AsyncResult<GetImportProgressResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetImportProgressResult parse(JsonNode data) {
            return GetImportProgressResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/import/progress/{transactionId}";

            url = url.replace("{transactionId}", this.request.getTransactionId() == null || this.request.getTransactionId().length() == 0 ? "null" : String.valueOf(this.request.getTransactionId()));

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

    public void getImportProgressAsync(
            GetImportProgressRequest request,
            AsyncAction<AsyncResult<GetImportProgressResult>> callback
    ) {
        GetImportProgressTask task = new GetImportProgressTask(request, callback);
        session.execute(task);
    }

    public GetImportProgressResult getImportProgress(
            GetImportProgressRequest request
    ) {
        final AsyncResult<GetImportProgressResult>[] resultAsyncResult = new AsyncResult[]{null};
        getImportProgressAsync(
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

    class WaitImportUserDataTask extends Gs2RestSessionTask<WaitImportUserDataResult> {
        private WaitImportUserDataRequest request;

        public WaitImportUserDataTask(
            WaitImportUserDataRequest request,
            AsyncAction<AsyncResult<WaitImportUserDataResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public WaitImportUserDataResult parse(JsonNode data) {
            return WaitImportUserDataResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/import/progress/{transactionId}/wait";

            url = url.replace("{transactionId}", this.request.getTransactionId() == null || this.request.getTransactionId().length() == 0 ? "null" : String.valueOf(this.request.getTransactionId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userId", request.getUserId());
                    put("microserviceName", request.getMicroserviceName());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void waitImportUserDataAsync(
            WaitImportUserDataRequest request,
            AsyncAction<AsyncResult<WaitImportUserDataResult>> callback
    ) {
        WaitImportUserDataTask task = new WaitImportUserDataTask(request, callback);
        session.execute(task);
    }

    public WaitImportUserDataResult waitImportUserData(
            WaitImportUserDataRequest request
    ) {
        final AsyncResult<WaitImportUserDataResult>[] resultAsyncResult = new AsyncResult[]{null};
        waitImportUserDataAsync(
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

    class PrepareImportUserDataTask extends Gs2RestSessionTask<PrepareImportUserDataResult> {
        private PrepareImportUserDataRequest request;

        public PrepareImportUserDataTask(
            PrepareImportUserDataRequest request,
            AsyncAction<AsyncResult<PrepareImportUserDataResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public PrepareImportUserDataResult parse(JsonNode data) {
            return PrepareImportUserDataResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/import/{userId}/prepare";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void prepareImportUserDataAsync(
            PrepareImportUserDataRequest request,
            AsyncAction<AsyncResult<PrepareImportUserDataResult>> callback
    ) {
        PrepareImportUserDataTask task = new PrepareImportUserDataTask(request, callback);
        session.execute(task);
    }

    public PrepareImportUserDataResult prepareImportUserData(
            PrepareImportUserDataRequest request
    ) {
        final AsyncResult<PrepareImportUserDataResult>[] resultAsyncResult = new AsyncResult[]{null};
        prepareImportUserDataAsync(
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

    class ImportUserDataTask extends Gs2RestSessionTask<ImportUserDataResult> {
        private ImportUserDataRequest request;

        public ImportUserDataTask(
            ImportUserDataRequest request,
            AsyncAction<AsyncResult<ImportUserDataResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public ImportUserDataResult parse(JsonNode data) {
            return ImportUserDataResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/import/{userId}";

            url = url.replace("{userId}", this.request.getUserId() == null || this.request.getUserId().length() == 0 ? "null" : String.valueOf(this.request.getUserId()));

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("uploadToken", request.getUploadToken());
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
            if (this.request.getDuplicationAvoider() != null) {
                builder.setHeader("X-GS2-DUPLICATION-AVOIDER", this.request.getDuplicationAvoider());
            }
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void importUserDataAsync(
            ImportUserDataRequest request,
            AsyncAction<AsyncResult<ImportUserDataResult>> callback
    ) {
        ImportUserDataTask task = new ImportUserDataTask(request, callback);
        session.execute(task);
    }

    public ImportUserDataResult importUserData(
            ImportUserDataRequest request
    ) {
        final AsyncResult<ImportUserDataResult>[] resultAsyncResult = new AsyncResult[]{null};
        importUserDataAsync(
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

    class DescribeImportErrorLogsTask extends Gs2RestSessionTask<DescribeImportErrorLogsResult> {
        private DescribeImportErrorLogsRequest request;

        public DescribeImportErrorLogsTask(
            DescribeImportErrorLogsRequest request,
            AsyncAction<AsyncResult<DescribeImportErrorLogsResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public DescribeImportErrorLogsResult parse(JsonNode data) {
            return DescribeImportErrorLogsResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/import/progress/{transactionId}/log";

            url = url.replace("{transactionId}", this.request.getTransactionId() == null || this.request.getTransactionId().length() == 0 ? "null" : String.valueOf(this.request.getTransactionId()));

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

    public void describeImportErrorLogsAsync(
            DescribeImportErrorLogsRequest request,
            AsyncAction<AsyncResult<DescribeImportErrorLogsResult>> callback
    ) {
        DescribeImportErrorLogsTask task = new DescribeImportErrorLogsTask(request, callback);
        session.execute(task);
    }

    public DescribeImportErrorLogsResult describeImportErrorLogs(
            DescribeImportErrorLogsRequest request
    ) {
        final AsyncResult<DescribeImportErrorLogsResult>[] resultAsyncResult = new AsyncResult[]{null};
        describeImportErrorLogsAsync(
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

    class GetImportErrorLogTask extends Gs2RestSessionTask<GetImportErrorLogResult> {
        private GetImportErrorLogRequest request;

        public GetImportErrorLogTask(
            GetImportErrorLogRequest request,
            AsyncAction<AsyncResult<GetImportErrorLogResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public GetImportErrorLogResult parse(JsonNode data) {
            return GetImportErrorLogResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "project")
                .replace("{region}", session.getRegion().getName())
                + "/account/me/project/import/progress/{transactionId}/log/{errorLogName}";

            url = url.replace("{transactionId}", this.request.getTransactionId() == null || this.request.getTransactionId().length() == 0 ? "null" : String.valueOf(this.request.getTransactionId()));
            url = url.replace("{errorLogName}", this.request.getErrorLogName() == null || this.request.getErrorLogName().length() == 0 ? "null" : String.valueOf(this.request.getErrorLogName()));

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

    public void getImportErrorLogAsync(
            GetImportErrorLogRequest request,
            AsyncAction<AsyncResult<GetImportErrorLogResult>> callback
    ) {
        GetImportErrorLogTask task = new GetImportErrorLogTask(request, callback);
        session.execute(task);
    }

    public GetImportErrorLogResult getImportErrorLog(
            GetImportErrorLogRequest request
    ) {
        final AsyncResult<GetImportErrorLogResult>[] resultAsyncResult = new AsyncResult[]{null};
        getImportErrorLogAsync(
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