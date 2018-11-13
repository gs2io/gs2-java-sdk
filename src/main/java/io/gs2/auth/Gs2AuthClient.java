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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.gs2.model.Region;
import io.gs2.util.EncodingUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.gs2.AbstractGs2Client;
import io.gs2.Gs2Constant;
import io.gs2.model.IGs2Credential;
import io.gs2.auth.control.*;

/**
 * GS2 Auth API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2AuthClient extends AbstractGs2Client<Gs2AuthClient> {

	public static String ENDPOINT = "auth";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2AuthClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2AuthClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2AuthClient(IGs2Credential credential, String region) {
		super(credential, region);
	}


	/**
	 * 実行回数制限付きワンタイムトークンを発行します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateOnceOnetimeTokenResult createOnceOnetimeToken(CreateOnceOnetimeTokenRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("scriptName", request.getScriptName());
        if(request.getGrant() != null) body.put("grant", request.getGrant());
        if(request.getArgs() != null) body.put("args", request.getArgs());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/onetime/once/token",
				credential,
				ENDPOINT,
				CreateOnceOnetimeTokenRequest.Constant.MODULE,
				CreateOnceOnetimeTokenRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateOnceOnetimeTokenResult.class);

	}


	/**
	 * 1回のみ実行を許可するワンタイムトークンを発行します<br>
	 * このトークンはスタミナの回復処理など、有効期間内だからといって何度も実行されたくない処理を1度だけ許可したい場合に発行します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateTimeOnetimeTokenResult createTimeOnetimeToken(CreateTimeOnetimeTokenRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("scriptName", request.getScriptName());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/onetime/time/token",
				credential,
				ENDPOINT,
				CreateTimeOnetimeTokenRequest.Constant.MODULE,
				CreateTimeOnetimeTokenRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateTimeOnetimeTokenResult.class);

	}


	/**
	 * ログイン処理を実行します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public LoginResult login(LoginRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("serviceId", request.getServiceId())
				.put("userId", request.getUserId());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/login",
				credential,
				ENDPOINT,
				LoginRequest.Constant.MODULE,
				LoginRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, LoginResult.class);

	}


	/**
	 * GS2-Accountの認証署名付きログイン処理を実行します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public LoginWithSignResult loginWithSign(LoginWithSignRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("serviceId", request.getServiceId())
				.put("userId", request.getUserId())
				.put("keyName", request.getKeyName())
				.put("sign", request.getSign());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/login/signed",
				credential,
				ENDPOINT,
				LoginWithSignRequest.Constant.MODULE,
				LoginWithSignRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, LoginWithSignResult.class);

	}


}