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

package io.gs2.matchmaking;

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
import io.gs2.matchmaking.control.*;

/**
 * GS2 Matchmaking API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2MatchmakingClient extends AbstractGs2Client<Gs2MatchmakingClient> {

	public static String ENDPOINT = "matchmaking";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2MatchmakingClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2MatchmakingClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2MatchmakingClient(IGs2Credential credential, String region) {
		super(credential, region);
	}


	/**
	 * ギャザリングの参加者一覧を取得します<br>
	 * <br>
	 * マッチメイキングが成立すると 404 Not Found 応答が返るようになります。<br>
	 * 404応答を返すようになる直前にコールバックエンドポイントに確定した参加者一覧情報が渡されるため、<br>
	 * コールバックを受け取ってからは本APIを呼び出さないように実装するべきです。<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public AnybodyDescribeJoinedUserResult anybodyDescribeJoinedUser(AnybodyDescribeJoinedUserRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/anybody/" + (request.getGatheringId() == null || request.getGatheringId().equals("") ? "null" : request.getGatheringId()) + "/player";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				AnybodyDescribeJoinedUserRequest.Constant.MODULE,
				AnybodyDescribeJoinedUserRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, AnybodyDescribeJoinedUserResult.class);

	}


	/**
	 * Anybodyマッチメイキングを開始します<br>
	 * <br>
	 * すでに存在するギャザリングに参加するか、新しいギャザリングを新規作成します。<br>
	 * 戻り値で参加したギャザリングIDが返りますので、そのIDを利用して後続のAPIを利用できます。<br>
	 * <br>
	 * ギャザリングが成立した場合、マッチメイキングに設定したコールバックエンドポイントにギャザリング<br>
	 * とそのギャザリングの参加者一覧が返されます。<br>
	 * コールバック後にギャザリング情報はマッチメイキングサーバから削除され、後続のAPIも利用できなくなります。<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public AnybodyDoMatchmakingResult anybodyDoMatchmaking(AnybodyDoMatchmakingRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/anybody",
				credential,
				ENDPOINT,
				AnybodyDoMatchmakingRequest.Constant.MODULE,
				AnybodyDoMatchmakingRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, AnybodyDoMatchmakingResult.class);

	}


	/**
	 * ギャザリングから離脱します<br>
	 * <br>
	 * 本APIは確実に成功することを保証していません。<br>
	 * 例えば、離脱する直前にギャザリングが成立した場合は 404 Not Found を応答します。<br>
	 * <br>
	 * 404応答を受け取った場合はすでにギャザリングが成立していないかを確認する必要があります。<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void anybodyLeaveGathering(AnybodyLeaveGatheringRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/anybody/" + (request.getGatheringId() == null || request.getGatheringId().equals("") ? "null" : request.getGatheringId()) + "/player";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				AnybodyLeaveGatheringRequest.Constant.MODULE,
				AnybodyLeaveGatheringRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        delete.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		doRequest(delete, null);

	}


	/**
	 * ギャザリングの参加者一覧を取得します<br>
	 * <br>
	 * マッチメイキングが成立すると 404 Not Found 応答が返るようになります。<br>
	 * 404応答を返すようになる直前にコールバックエンドポイントに確定した参加者一覧情報が渡されるため、<br>
	 * コールバックを受け取ってからは本APIを呼び出さないように実装するべきです。<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CustomAutoDescribeJoinedUserResult customAutoDescribeJoinedUser(CustomAutoDescribeJoinedUserRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/customauto/" + (request.getGatheringId() == null || request.getGatheringId().equals("") ? "null" : request.getGatheringId()) + "/player";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				CustomAutoDescribeJoinedUserRequest.Constant.MODULE,
				CustomAutoDescribeJoinedUserRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, CustomAutoDescribeJoinedUserResult.class);

	}


	/**
	 * カスタムオートマッチメイキングを開始します<br>
	 * <br>
	 * カスタムオートマッチメイキングは最大5つの属性値を指定してギャザリングを作成するか、<br>
	 * すでに存在するギャザリングの最大5つの属性値で、すべての属性値が指定した範囲内に収まっているギャザリングに参加します。<br>
	 * <br>
	 * ギャザリングへの参加が成功した場合は done に true が返ります。<br>
	 * done に true が返った場合は、同時に item に参加したギャザリングの情報が格納されています。<br>
	 * <br>
	 * done に false が返った場合は、一定時間内に存在するギャザリングの検索が完了しなかった場合に返ります。<br>
	 * この場合、searchContext に検索を継続するための情報が返ります。<br>
	 * 引き続き検索を続けるために、再度APIを呼び出す際に引数に指定することで検索を再開することができます。<br>
	 * <br>
	 * すべてのギャザリングを検索したが、条件にマッチするものがなかった場合、新しいギャザリングを作成し done に true が返ります。<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CustomAutoDoMatchmakingResult customAutoDoMatchmaking(CustomAutoDoMatchmakingRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getAttribute1() != null) body.put("attribute1", request.getAttribute1());
        if(request.getAttribute2() != null) body.put("attribute2", request.getAttribute2());
        if(request.getAttribute3() != null) body.put("attribute3", request.getAttribute3());
        if(request.getAttribute4() != null) body.put("attribute4", request.getAttribute4());
        if(request.getAttribute5() != null) body.put("attribute5", request.getAttribute5());
        if(request.getSearchAttribute1Min() != null) body.put("searchAttribute1Min", request.getSearchAttribute1Min());
        if(request.getSearchAttribute2Min() != null) body.put("searchAttribute2Min", request.getSearchAttribute2Min());
        if(request.getSearchAttribute3Min() != null) body.put("searchAttribute3Min", request.getSearchAttribute3Min());
        if(request.getSearchAttribute4Min() != null) body.put("searchAttribute4Min", request.getSearchAttribute4Min());
        if(request.getSearchAttribute5Min() != null) body.put("searchAttribute5Min", request.getSearchAttribute5Min());
        if(request.getSearchAttribute1Max() != null) body.put("searchAttribute1Max", request.getSearchAttribute1Max());
        if(request.getSearchAttribute2Max() != null) body.put("searchAttribute2Max", request.getSearchAttribute2Max());
        if(request.getSearchAttribute3Max() != null) body.put("searchAttribute3Max", request.getSearchAttribute3Max());
        if(request.getSearchAttribute4Max() != null) body.put("searchAttribute4Max", request.getSearchAttribute4Max());
        if(request.getSearchAttribute5Max() != null) body.put("searchAttribute5Max", request.getSearchAttribute5Max());
        if(request.getSearchContext() != null) body.put("searchContext", request.getSearchContext());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/customauto",
				credential,
				ENDPOINT,
				CustomAutoDoMatchmakingRequest.Constant.MODULE,
				CustomAutoDoMatchmakingRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, CustomAutoDoMatchmakingResult.class);

	}


	/**
	 * ギャザリングから離脱します<br>
	 * <br>
	 * 本APIは確実に成功することを保証していません。<br>
	 * 例えば、離脱する直前にギャザリングが成立した場合は 404 Not Found を応答します。<br>
	 * <br>
	 * 404応答を受け取った場合はすでにギャザリングが成立していないかを確認する必要があります。<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void customAutoLeaveGathering(CustomAutoLeaveGatheringRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/customauto/" + (request.getGatheringId() == null || request.getGatheringId().equals("") ? "null" : request.getGatheringId()) + "/player";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				CustomAutoLeaveGatheringRequest.Constant.MODULE,
				CustomAutoLeaveGatheringRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        delete.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		doRequest(delete, null);

	}


	/**
	 * マッチメイキングを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateMatchmakingResult createMatchmaking(CreateMatchmakingRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("serviceClass", request.getServiceClass())
				.put("type", request.getType())
				.put("maxPlayer", request.getMaxPlayer());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getGatheringPoolName() != null) body.put("gatheringPoolName", request.getGatheringPoolName());
        if(request.getCallback() != null) body.put("callback", request.getCallback());
        if(request.getNotificationGameName() != null) body.put("notificationGameName", request.getNotificationGameName());
        if(request.getCreateGatheringTriggerScript() != null) body.put("createGatheringTriggerScript", request.getCreateGatheringTriggerScript());
        if(request.getCreateGatheringDoneTriggerScript() != null) body.put("createGatheringDoneTriggerScript", request.getCreateGatheringDoneTriggerScript());
        if(request.getJoinGatheringTriggerScript() != null) body.put("joinGatheringTriggerScript", request.getJoinGatheringTriggerScript());
        if(request.getJoinGatheringDoneTriggerScript() != null) body.put("joinGatheringDoneTriggerScript", request.getJoinGatheringDoneTriggerScript());
        if(request.getLeaveGatheringTriggerScript() != null) body.put("leaveGatheringTriggerScript", request.getLeaveGatheringTriggerScript());
        if(request.getLeaveGatheringDoneTriggerScript() != null) body.put("leaveGatheringDoneTriggerScript", request.getLeaveGatheringDoneTriggerScript());
        if(request.getBreakupGatheringTriggerScript() != null) body.put("breakupGatheringTriggerScript", request.getBreakupGatheringTriggerScript());
        if(request.getMatchmakingCompleteTriggerScript() != null) body.put("matchmakingCompleteTriggerScript", request.getMatchmakingCompleteTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/matchmaking",
				credential,
				ENDPOINT,
				CreateMatchmakingRequest.Constant.MODULE,
				CreateMatchmakingRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateMatchmakingResult.class);

	}


	/**
	 * マッチメイキングを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteMatchmaking(DeleteMatchmakingRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteMatchmakingRequest.Constant.MODULE,
				DeleteMatchmakingRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * マッチメイキングの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeMatchmakingResult describeMatchmaking(DescribeMatchmakingRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking";

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
				DescribeMatchmakingRequest.Constant.MODULE,
				DescribeMatchmakingRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeMatchmakingResult.class);

	}


	/**
	 * サービスクラスの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeServiceClassResult describeServiceClass(DescribeServiceClassRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/serviceClass";



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
	 * マッチメイキングを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetMatchmakingResult getMatchmaking(GetMatchmakingRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetMatchmakingRequest.Constant.MODULE,
				GetMatchmakingRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetMatchmakingResult.class);

	}


	/**
	 * マッチメイキングのステータスを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetMatchmakingStatusResult getMatchmakingStatus(GetMatchmakingStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/status";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetMatchmakingStatusRequest.Constant.MODULE,
				GetMatchmakingStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetMatchmakingStatusResult.class);

	}


	/**
	 * マッチメイキングを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateMatchmakingResult updateMatchmaking(UpdateMatchmakingRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("serviceClass", request.getServiceClass());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getGatheringPoolName() != null) body.put("gatheringPoolName", request.getGatheringPoolName());
        if(request.getCallback() != null) body.put("callback", request.getCallback());
        if(request.getNotificationGameName() != null) body.put("notificationGameName", request.getNotificationGameName());
        if(request.getCreateGatheringTriggerScript() != null) body.put("createGatheringTriggerScript", request.getCreateGatheringTriggerScript());
        if(request.getCreateGatheringDoneTriggerScript() != null) body.put("createGatheringDoneTriggerScript", request.getCreateGatheringDoneTriggerScript());
        if(request.getJoinGatheringTriggerScript() != null) body.put("joinGatheringTriggerScript", request.getJoinGatheringTriggerScript());
        if(request.getJoinGatheringDoneTriggerScript() != null) body.put("joinGatheringDoneTriggerScript", request.getJoinGatheringDoneTriggerScript());
        if(request.getLeaveGatheringTriggerScript() != null) body.put("leaveGatheringTriggerScript", request.getLeaveGatheringTriggerScript());
        if(request.getLeaveGatheringDoneTriggerScript() != null) body.put("leaveGatheringDoneTriggerScript", request.getLeaveGatheringDoneTriggerScript());
        if(request.getBreakupGatheringTriggerScript() != null) body.put("breakupGatheringTriggerScript", request.getBreakupGatheringTriggerScript());
        if(request.getMatchmakingCompleteTriggerScript() != null) body.put("matchmakingCompleteTriggerScript", request.getMatchmakingCompleteTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "",
				credential,
				ENDPOINT,
				UpdateMatchmakingRequest.Constant.MODULE,
				UpdateMatchmakingRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateMatchmakingResult.class);

	}


	/**
	 * ギャザリングを解散します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void passcodeBreakupGathering(PasscodeBreakupGatheringRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/passcode/" + (request.getGatheringId() == null || request.getGatheringId().equals("") ? "null" : request.getGatheringId()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				PasscodeBreakupGatheringRequest.Constant.MODULE,
				PasscodeBreakupGatheringRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        delete.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		doRequest(delete, null);

	}


	/**
	 * パスコード付きギャザリングを新規作成します<br>
	 * <br>
	 * パスコードは8桁の数字で構成されたものが自動的に発行されます。<br>
	 * パスコードの解像度的に秒間100を超える勢いでギャザリングを作成する必要がある場合は<br>
	 * オートマッチメイキングと組み合わせる必要があります。<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public PasscodeCreateGatheringResult passcodeCreateGathering(PasscodeCreateGatheringRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/passcode",
				credential,
				ENDPOINT,
				PasscodeCreateGatheringRequest.Constant.MODULE,
				PasscodeCreateGatheringRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, PasscodeCreateGatheringResult.class);

	}


	/**
	 * ギャザリングの参加者一覧を取得します<br>
	 * <br>
	 * マッチメイキングが成立すると 404 Not Found 応答が返るようになります。<br>
	 * 404応答を返すようになる直前にコールバックエンドポイントに確定した参加者一覧情報が渡されるため、<br>
	 * コールバックを受け取ってからは本APIを呼び出さないように実装するべきです。<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public PasscodeDescribeJoinedUserResult passcodeDescribeJoinedUser(PasscodeDescribeJoinedUserRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/passcode/" + (request.getGatheringId() == null || request.getGatheringId().equals("") ? "null" : request.getGatheringId()) + "/player";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				PasscodeDescribeJoinedUserRequest.Constant.MODULE,
				PasscodeDescribeJoinedUserRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, PasscodeDescribeJoinedUserResult.class);

	}


	/**
	 * マッチメイキングを早期終了します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void passcodeEarlyCompleteGathering(PasscodeEarlyCompleteGatheringRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/passcode/" + (request.getGatheringId() == null || request.getGatheringId().equals("") ? "null" : request.getGatheringId()) + "/complete",
				credential,
				ENDPOINT,
				PasscodeEarlyCompleteGatheringRequest.Constant.MODULE,
				PasscodeEarlyCompleteGatheringRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		doRequest(post, null);

	}


	/**
	 * パスコード付きギャザリングに参加します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public PasscodeJoinGatheringResult passcodeJoinGathering(PasscodeJoinGatheringRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/passcode/join/" + (request.getPasscode() == null || request.getPasscode().equals("") ? "null" : request.getPasscode()) + "",
				credential,
				ENDPOINT,
				PasscodeJoinGatheringRequest.Constant.MODULE,
				PasscodeJoinGatheringRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, PasscodeJoinGatheringResult.class);

	}


	/**
	 * ギャザリングから離脱します<br>
	 * <br>
	 * 本APIは確実に成功することを保証していません。<br>
	 * 例えば、離脱する直前にギャザリングが成立した場合は 404 Not Found を応答します。<br>
	 * <br>
	 * 404応答を受け取った場合はすでにギャザリングが成立していないかを確認する必要があります。<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void passcodeLeaveGathering(PasscodeLeaveGatheringRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/passcode/" + (request.getGatheringId() == null || request.getGatheringId().equals("") ? "null" : request.getGatheringId()) + "/player";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				PasscodeLeaveGatheringRequest.Constant.MODULE,
				PasscodeLeaveGatheringRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        delete.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		doRequest(delete, null);

	}


	/**
	 * ギャザリングを解散します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void roomBreakupGathering(RoomBreakupGatheringRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/room/" + (request.getGatheringId() == null || request.getGatheringId().equals("") ? "null" : request.getGatheringId()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				RoomBreakupGatheringRequest.Constant.MODULE,
				RoomBreakupGatheringRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        delete.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		doRequest(delete, null);

	}


	/**
	 * ギャザリングを新規作成します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public RoomCreateGatheringResult roomCreateGathering(RoomCreateGatheringRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getMeta() != null) body.put("meta", request.getMeta());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/room",
				credential,
				ENDPOINT,
				RoomCreateGatheringRequest.Constant.MODULE,
				RoomCreateGatheringRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, RoomCreateGatheringResult.class);

	}


	/**
	 * ギャザリング一覧を取得します<br>
	 * <br>
	 * - 消費クオータ: 20件あたり3クオータ<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public RoomDescribeGatheringResult roomDescribeGathering(RoomDescribeGatheringRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/room";

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
				RoomDescribeGatheringRequest.Constant.MODULE,
				RoomDescribeGatheringRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, RoomDescribeGatheringResult.class);

	}


	/**
	 * ギャザリングの参加者一覧を取得します<br>
	 * <br>
	 * マッチメイキングが成立すると 404 Not Found 応答が返るようになります。<br>
	 * 404応答を返すようになる直前にコールバックエンドポイントに確定した参加者一覧情報が渡されるため、<br>
	 * コールバックを受け取ってからは本APIを呼び出さないように実装するべきです。<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public RoomDescribeJoinedUserResult roomDescribeJoinedUser(RoomDescribeJoinedUserRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/room/" + (request.getGatheringId() == null || request.getGatheringId().equals("") ? "null" : request.getGatheringId()) + "/player";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				RoomDescribeJoinedUserRequest.Constant.MODULE,
				RoomDescribeJoinedUserRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, RoomDescribeJoinedUserResult.class);

	}


	/**
	 * マッチメイキングを早期終了します<br>
	 * <br>
	 *     - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void roomEarlyCompleteGathering(RoomEarlyCompleteGatheringRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/room/" + (request.getGatheringId() == null || request.getGatheringId().equals("") ? "null" : request.getGatheringId()) + "/complete",
				credential,
				ENDPOINT,
				RoomEarlyCompleteGatheringRequest.Constant.MODULE,
				RoomEarlyCompleteGatheringRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		doRequest(post, null);

	}


	/**
	 * ギャザリングに参加します<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public RoomJoinGatheringResult roomJoinGathering(RoomJoinGatheringRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/room/" + (request.getGatheringId() == null || request.getGatheringId().equals("") ? "null" : request.getGatheringId()) + "",
				credential,
				ENDPOINT,
				RoomJoinGatheringRequest.Constant.MODULE,
				RoomJoinGatheringRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, RoomJoinGatheringResult.class);

	}


	/**
	 * ギャザリングから離脱します<br>
	 * <br>
	 * 本APIは確実に成功することを保証していません。<br>
	 * 例えば、離脱する直前にギャザリングが成立した場合は 404 Not Found を応答します。<br>
	 * <br>
	 * 404応答を受け取った場合はすでにギャザリングが成立していないかを確認する必要があります。<br>
	 * <br>
	 * - 消費クオータ: 10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void roomLeaveGathering(RoomLeaveGatheringRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/matchmaking/" + (request.getMatchmakingName() == null || request.getMatchmakingName().equals("") ? "null" : request.getMatchmakingName()) + "/room/" + (request.getGatheringId() == null || request.getGatheringId().equals("") ? "null" : request.getGatheringId()) + "/player";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				RoomLeaveGatheringRequest.Constant.MODULE,
				RoomLeaveGatheringRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        delete.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		doRequest(delete, null);

	}


}