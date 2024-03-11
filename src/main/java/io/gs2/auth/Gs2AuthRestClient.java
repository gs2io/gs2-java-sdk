
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

package io.gs2.auth;

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
import io.gs2.auth.request.*;
import io.gs2.auth.result.*;
import io.gs2.auth.model.*;public class Gs2AuthRestClient extends AbstractGs2Client<Gs2AuthRestClient> {

	public Gs2AuthRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
	}

    class LoginTask extends Gs2RestSessionTask<LoginResult> {
        private LoginRequest request;

        public LoginTask(
            LoginRequest request,
            AsyncAction<AsyncResult<LoginResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public LoginResult parse(JsonNode data) {
            return LoginResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "auth")
                .replace("{region}", session.getRegion().getName())
                + "/login";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userId", request.getUserId());
                    put("timeOffset", request.getTimeOffset());
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void loginAsync(
            LoginRequest request,
            AsyncAction<AsyncResult<LoginResult>> callback
    ) {
        LoginTask task = new LoginTask(request, callback);
        session.execute(task);
    }

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

    class LoginBySignatureTask extends Gs2RestSessionTask<LoginBySignatureResult> {
        private LoginBySignatureRequest request;

        public LoginBySignatureTask(
            LoginBySignatureRequest request,
            AsyncAction<AsyncResult<LoginBySignatureResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public LoginBySignatureResult parse(JsonNode data) {
            return LoginBySignatureResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "auth")
                .replace("{region}", session.getRegion().getName())
                + "/login/signed";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("keyId", request.getKeyId());
                    put("body", request.getBody());
                    put("signature", request.getSignature());
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

    public void loginBySignatureAsync(
            LoginBySignatureRequest request,
            AsyncAction<AsyncResult<LoginBySignatureResult>> callback
    ) {
        LoginBySignatureTask task = new LoginBySignatureTask(request, callback);
        session.execute(task);
    }

    public LoginBySignatureResult loginBySignature(
            LoginBySignatureRequest request
    ) {
        final AsyncResult<LoginBySignatureResult>[] resultAsyncResult = new AsyncResult[]{null};
        loginBySignatureAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        if(resultAsyncResult[0].getError() != null) {
            throw resultAsyncResult[0].getError();
        }

        return resultAsyncResult[0].getResult();
    }

    class IssueTimeOffsetTokenByUserIdTask extends Gs2RestSessionTask<IssueTimeOffsetTokenByUserIdResult> {
        private IssueTimeOffsetTokenByUserIdRequest request;

        public IssueTimeOffsetTokenByUserIdTask(
            IssueTimeOffsetTokenByUserIdRequest request,
            AsyncAction<AsyncResult<IssueTimeOffsetTokenByUserIdResult>> userCallback
        ) {
            super(
                    (Gs2RestSession) session,
                    userCallback
            );
            this.request = request;
        }

        @Override
        public IssueTimeOffsetTokenByUserIdResult parse(JsonNode data) {
            return IssueTimeOffsetTokenByUserIdResult.fromJson(data);
        }

        @Override
        protected void executeImpl() {

            String url = Gs2RestSession.EndpointHost
                .replace("{service}", "auth")
                .replace("{region}", session.getRegion().getName())
                + "/timeoffset/token";

            builder.setBody(new ObjectMapper().valueToTree(
                new HashMap<String, Object>() {{
                    put("userId", request.getUserId());
                    put("timeOffset", request.getTimeOffset());
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
            if (this.request.getTimeOffsetToken() != null) {
                builder.setHeader("X-GS2-TIME-OFFSET-TOKEN", this.request.getTimeOffsetToken());
            }

            builder
                .build()
                .send();
        }
    }

    public void issueTimeOffsetTokenByUserIdAsync(
            IssueTimeOffsetTokenByUserIdRequest request,
            AsyncAction<AsyncResult<IssueTimeOffsetTokenByUserIdResult>> callback
    ) {
        IssueTimeOffsetTokenByUserIdTask task = new IssueTimeOffsetTokenByUserIdTask(request, callback);
        session.execute(task);
    }

    public IssueTimeOffsetTokenByUserIdResult issueTimeOffsetTokenByUserId(
            IssueTimeOffsetTokenByUserIdRequest request
    ) {
        final AsyncResult<IssueTimeOffsetTokenByUserIdResult>[] resultAsyncResult = new AsyncResult[]{null};
        issueTimeOffsetTokenByUserIdAsync(
                request,
                result -> resultAsyncResult[0] = result
        );
        while (resultAsyncResult[0] == null) {
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