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
import io.gs2.account.control.*;

/**
 * GS2 Account API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2AccountClient extends AbstractGs2Client<Gs2AccountClient> {

	public static String ENDPOINT = "account";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2AccountClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2AccountClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2AccountClient(IGs2Credential credential, String region) {
		super(credential, region);
	}


	/**
	 * 認証処理を行います<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public AuthenticationResult authentication(AuthenticationRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("keyName", request.getKeyName())
				.put("password", request.getPassword());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "/account/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "",
				credential,
				ENDPOINT,
				AuthenticationRequest.Constant.MODULE,
				AuthenticationRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, AuthenticationResult.class);

	}


	/**
	 * アカウントを登録します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateAccountResult createAccount(CreateAccountRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "/account",
				credential,
				ENDPOINT,
				CreateAccountRequest.Constant.MODULE,
				CreateAccountRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateAccountResult.class);

	}


	/**
	 * アカウントを削除します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteAccount(DeleteAccountRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "/account/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteAccountRequest.Constant.MODULE,
				DeleteAccountRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * アカウントを取得します<br>
	 * <br>
	 * - 消費クオータ: 50件あたり5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeAccountResult describeAccount(DescribeAccountRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "/account";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeAccountRequest.Constant.MODULE,
				DescribeAccountRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeAccountResult.class);

	}


	/**
	 * ゲームを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateGameResult createGame(CreateGameRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("serviceClass", request.getServiceClass())
				.put("changePasswordIfTakeOver", request.getChangePasswordIfTakeOver());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getCreateAccountTriggerScript() != null) body.put("createAccountTriggerScript", request.getCreateAccountTriggerScript());
        if(request.getCreateAccountDoneTriggerScript() != null) body.put("createAccountDoneTriggerScript", request.getCreateAccountDoneTriggerScript());
        if(request.getAuthenticationTriggerScript() != null) body.put("authenticationTriggerScript", request.getAuthenticationTriggerScript());
        if(request.getAuthenticationDoneTriggerScript() != null) body.put("authenticationDoneTriggerScript", request.getAuthenticationDoneTriggerScript());
        if(request.getCreateTakeOverTriggerScript() != null) body.put("createTakeOverTriggerScript", request.getCreateTakeOverTriggerScript());
        if(request.getCreateTakeOverDoneTriggerScript() != null) body.put("createTakeOverDoneTriggerScript", request.getCreateTakeOverDoneTriggerScript());
        if(request.getDoTakeOverTriggerScript() != null) body.put("doTakeOverTriggerScript", request.getDoTakeOverTriggerScript());
        if(request.getDoTakeOverDoneTriggerScript() != null) body.put("doTakeOverDoneTriggerScript", request.getDoTakeOverDoneTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/game",
				credential,
				ENDPOINT,
				CreateGameRequest.Constant.MODULE,
				CreateGameRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateGameResult.class);

	}


	/**
	 * ゲームを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteGame(DeleteGameRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteGameRequest.Constant.MODULE,
				DeleteGameRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ゲームの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeGameResult describeGame(DescribeGameRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/game";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeGameRequest.Constant.MODULE,
				DescribeGameRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeGameResult.class);

	}


	/**
	 * サービスクラスの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeServiceClassResult describeServiceClass(DescribeServiceClassRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/game/serviceClass";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeServiceClassRequest.Constant.MODULE,
				DescribeServiceClassRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeServiceClassResult.class);

	}


	/**
	 * ゲームを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetGameResult getGame(GetGameRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetGameRequest.Constant.MODULE,
				GetGameRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetGameResult.class);

	}


	/**
	 * ゲームのステータスを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetGameStatusResult getGameStatus(GetGameStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "/status";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetGameStatusRequest.Constant.MODULE,
				GetGameStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetGameStatusResult.class);

	}


	/**
	 * ゲームを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateGameResult updateGame(UpdateGameRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("serviceClass", request.getServiceClass())
				.put("changePasswordIfTakeOver", request.getChangePasswordIfTakeOver());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getCreateAccountTriggerScript() != null) body.put("createAccountTriggerScript", request.getCreateAccountTriggerScript());
        if(request.getCreateAccountDoneTriggerScript() != null) body.put("createAccountDoneTriggerScript", request.getCreateAccountDoneTriggerScript());
        if(request.getAuthenticationTriggerScript() != null) body.put("authenticationTriggerScript", request.getAuthenticationTriggerScript());
        if(request.getAuthenticationDoneTriggerScript() != null) body.put("authenticationDoneTriggerScript", request.getAuthenticationDoneTriggerScript());
        if(request.getCreateTakeOverTriggerScript() != null) body.put("createTakeOverTriggerScript", request.getCreateTakeOverTriggerScript());
        if(request.getCreateTakeOverDoneTriggerScript() != null) body.put("createTakeOverDoneTriggerScript", request.getCreateTakeOverDoneTriggerScript());
        if(request.getDoTakeOverTriggerScript() != null) body.put("doTakeOverTriggerScript", request.getDoTakeOverTriggerScript());
        if(request.getDoTakeOverDoneTriggerScript() != null) body.put("doTakeOverDoneTriggerScript", request.getDoTakeOverDoneTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "",
				credential,
				ENDPOINT,
				UpdateGameRequest.Constant.MODULE,
				UpdateGameRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateGameResult.class);

	}


	/**
	 * 引き継ぎ情報を登録します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateTakeOverResult createTakeOver(CreateTakeOverRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("type", request.getType())
				.put("userIdentifier", request.getUserIdentifier())
				.put("password", request.getPassword());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "/takeover",
				credential,
				ENDPOINT,
				CreateTakeOverRequest.Constant.MODULE,
				CreateTakeOverRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, CreateTakeOverResult.class);

	}


	/**
	 * 引き継ぎ情報を削除します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteTakeOver(DeleteTakeOverRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "/takeover/" + (request.getType() == null || request.getType().equals("") ? "null" : request.getType()) + "/" + (request.getUserIdentifier() == null || request.getUserIdentifier().equals("") ? "null" : request.getUserIdentifier()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteTakeOverRequest.Constant.MODULE,
				DeleteTakeOverRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        delete.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		doRequest(delete, null);

	}


	/**
	 * 引き継ぎ情報を取得します<br>
	 * <br>
	 * - 消費クオータ: 50件あたり5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeTakeOverResult describeTakeOver(DescribeTakeOverRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "/takeover";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeTakeOverRequest.Constant.MODULE,
				DescribeTakeOverRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, DescribeTakeOverResult.class);

	}


	/**
	 * 引き継ぎを実行します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DoTakeOverResult doTakeOver(DoTakeOverRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("userIdentifier", request.getUserIdentifier())
				.put("password", request.getPassword());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "/takeover/" + (request.getType() == null || request.getType().equals("") ? "null" : request.getType()) + "",
				credential,
				ENDPOINT,
				DoTakeOverRequest.Constant.MODULE,
				DoTakeOverRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, DoTakeOverResult.class);

	}


	/**
	 * 引き継ぎ情報を取得します<br>
	 * <br>
	 * - 消費クオータ: 5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetTakeOverResult getTakeOver(GetTakeOverRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "/takeover/" + (request.getType() == null || request.getType().equals("") ? "null" : request.getType()) + "/" + (request.getUserIdentifier() == null || request.getUserIdentifier().equals("") ? "null" : request.getUserIdentifier()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetTakeOverRequest.Constant.MODULE,
				GetTakeOverRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, GetTakeOverResult.class);

	}


	/**
	 * 引き継ぎ情報を更新します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateTakeOverResult updateTakeOver(UpdateTakeOverRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("oldPassword", request.getOldPassword())
				.put("password", request.getPassword());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/game/" + (request.getGameName() == null || request.getGameName().equals("") ? "null" : request.getGameName()) + "/takeover/" + (request.getType() == null || request.getType().equals("") ? "null" : request.getType()) + "/" + (request.getUserIdentifier() == null || request.getUserIdentifier().equals("") ? "null" : request.getUserIdentifier()) + "",
				credential,
				ENDPOINT,
				UpdateTakeOverRequest.Constant.MODULE,
				UpdateTakeOverRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        put.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(put, UpdateTakeOverResult.class);

	}


}