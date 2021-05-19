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
import io.gs2.auth.request.*;
import io.gs2.auth.result.*;
import io.gs2.auth.model.*;

/**
 * GS2 Auth API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2AuthRestClient extends AbstractGs2Client<Gs2AuthRestClient> {

	/**
	 * コンストラクタ。
	 *
	 * @param gs2RestSession セッション
	 */
	public Gs2AuthRestClient(Gs2RestSession gs2RestSession) {
		super(gs2RestSession);
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
                .replace("{service}", "auth")
                .replace("{region}", session.getRegion().getName())
                + "/login";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getUserId() != null) {
                json.put("userId", this.request.getUserId());
            }
            if (this.request.getTimeOffset() != null) {
                json.put("timeOffset", this.request.getTimeOffset());
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
     * 指定したユーザIDでGS2にログインし、アクセストークンを取得します<br>
     *   本APIは信頼出来るゲームサーバーから呼び出されることを想定しています。<br>
     *   ユーザIDの値の検証処理が存在しないため、クライアントから呼び出すのは不適切です。<br>
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
     * 指定したユーザIDでGS2にログインし、アクセストークンを取得します<br>
     *   本APIは信頼出来るゲームサーバーから呼び出されることを想定しています。<br>
     *   ユーザIDの値の検証処理が存在しないため、クライアントから呼び出すのは不適切です。<br>
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

    class LoginBySignatureTask extends Gs2RestSessionTask<LoginBySignatureResult> {
        private LoginBySignatureRequest request;

        public LoginBySignatureTask(
            LoginBySignatureRequest request,
            AsyncAction<AsyncResult<LoginBySignatureResult>> userCallback,
            Class<LoginBySignatureResult> clazz
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
                .replace("{service}", "auth")
                .replace("{region}", session.getRegion().getName())
                + "/login/signed";

            ObjectMapper mapper = new ObjectMapper();
            JSONObject json = new JSONObject();
            if (this.request.getUserId() != null) {
                json.put("userId", this.request.getUserId());
            }
            if (this.request.getKeyId() != null) {
                json.put("keyId", this.request.getKeyId());
            }
            if (this.request.getBody() != null) {
                json.put("body", this.request.getBody());
            }
            if (this.request.getSignature() != null) {
                json.put("signature", this.request.getSignature());
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
     * 指定したユーザIDでGS2にログインし、アクセストークンを取得します<br>
     *   ユーザIDの署名検証を実施することで、本APIはクライアントから呼び出しても安全です。<br>
     *
     * @param callback コールバック
     * @param request リクエストパラメータ
     */
    public void loginBySignatureAsync(
            LoginBySignatureRequest request,
            AsyncAction<AsyncResult<LoginBySignatureResult>> callback
    ) {
        LoginBySignatureTask task = new LoginBySignatureTask(request, callback, LoginBySignatureResult.class);
        session.execute(task);
    }

    /**
     * 指定したユーザIDでGS2にログインし、アクセストークンを取得します<br>
     *   ユーザIDの署名検証を実施することで、本APIはクライアントから呼び出しても安全です。<br>
     *
     * @param request リクエストパラメータ
     */
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
}