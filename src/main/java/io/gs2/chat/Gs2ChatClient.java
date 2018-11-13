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

package io.gs2.chat;

import java.util.ArrayList;
import java.util.List;

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
import com.fasterxml.jackson.databind.JsonNode;

import io.gs2.AbstractGs2Client;
import io.gs2.Gs2Constant;
import io.gs2.model.IGs2Credential;
import io.gs2.chat.control.*;

/**
 * GS2 Chat API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2ChatClient extends AbstractGs2Client<Gs2ChatClient> {

	public static String ENDPOINT = "chat";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2ChatClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2ChatClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2ChatClient(IGs2Credential credential, String region) {
		super(credential, region);

	}


	/**
	 * ロビーを新規作成します<br>
	 * <br>
	 * GS2-Chat の使用を開始するには、まずはロビーを作成します。<br>
	 * ロビーはチャットルームの集合体のような存在です。<br>
	 * <br>
	 * ロビーへの設定項目として購読しているルームに発言があったときの通知方式を指定できます。<br>
	 * http/https を設定した場合は、新しい発言があるたびに指定されたURLに通知を出します。<br>
	 * 通知は以下のフォーマットで通知されます。<br>
	 * {<br>
	 *   "_gs2_service": "GS2-Chat#Receive",<br>
	 *   "notificationUserIds": [<br>
	 *     通知先ユーザID<br>
	 *   ],<br>
	 *   "roomId": 発言されたルームID,<br>
	 *   "userId": 発言したユーザのユーザID,<br>
	 *   "message": {<br>
	 *     "text": メッセージテキスト,<br>
	 *     "meta": メタデータ,<br>
	 *   }<br>
	 * }<br>
	 * GS2-InGamePushNotification を指定した場合は、新しい発言があるたびにプッシュ通知を出します。<br>
	 * 通知は以下のフォーマットで通知されます。<br>
	 * {<br>
	 *   "subject": メッセージテキスト,<br>
	 *   "body": {<br>
	 *     "_gs2_service": "GS2-Chat#Receive",<br>
	 *     "roomId": 発言されたルームID,<br>
	 *     "userId": 発言したユーザのユーザID,<br>
	 *     "message": {<br>
	 *       "text": メッセージテキスト,<br>
	 *       "meta": メタデータ,<br>
	 *     }<br>
	 *   }<br>
	 * }<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateLobbyResult createLobby(CreateLobbyRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("serviceClass", request.getServiceClass())
				.put("notificationType", request.getNotificationType());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getNotificationUrl() != null) body.put("notificationUrl", request.getNotificationUrl());
        if(request.getNotificationGameName() != null) body.put("notificationGameName", request.getNotificationGameName());
        if(request.getLogging() != null) body.put("logging", request.getLogging());
        if(request.getLoggingDate() != null) body.put("loggingDate", request.getLoggingDate());
        if(request.getCreateRoomTriggerScript() != null) body.put("createRoomTriggerScript", request.getCreateRoomTriggerScript());
        if(request.getCreateRoomDoneTriggerScript() != null) body.put("createRoomDoneTriggerScript", request.getCreateRoomDoneTriggerScript());
        if(request.getDeleteRoomTriggerScript() != null) body.put("deleteRoomTriggerScript", request.getDeleteRoomTriggerScript());
        if(request.getDeleteRoomDoneTriggerScript() != null) body.put("deleteRoomDoneTriggerScript", request.getDeleteRoomDoneTriggerScript());
        if(request.getCreateSubscribeTriggerScript() != null) body.put("createSubscribeTriggerScript", request.getCreateSubscribeTriggerScript());
        if(request.getCreateSubscribeDoneTriggerScript() != null) body.put("createSubscribeDoneTriggerScript", request.getCreateSubscribeDoneTriggerScript());
        if(request.getDeleteSubscribeTriggerScript() != null) body.put("deleteSubscribeTriggerScript", request.getDeleteSubscribeTriggerScript());
        if(request.getDeleteSubscribeDoneTriggerScript() != null) body.put("deleteSubscribeDoneTriggerScript", request.getDeleteSubscribeDoneTriggerScript());
        if(request.getSendMessageTriggerScript() != null) body.put("sendMessageTriggerScript", request.getSendMessageTriggerScript());
        if(request.getSendMessageDoneTriggerScript() != null) body.put("sendMessageDoneTriggerScript", request.getSendMessageDoneTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/lobby",
				credential,
				ENDPOINT,
				CreateLobbyRequest.Constant.MODULE,
				CreateLobbyRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateLobbyResult.class);

	}


	/**
	 * ロビーを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteLobby(DeleteLobbyRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteLobbyRequest.Constant.MODULE,
				DeleteLobbyRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ロビーの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeLobbyResult describeLobby(DescribeLobbyRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby";

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
				DescribeLobbyRequest.Constant.MODULE,
				DescribeLobbyRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeLobbyResult.class);

	}


	/**
	 * サービスクラスの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeServiceClassResult describeServiceClass(DescribeServiceClassRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/serviceClass";


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
	 * ロビーを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetLobbyResult getLobby(GetLobbyRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetLobbyRequest.Constant.MODULE,
				GetLobbyRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetLobbyResult.class);

	}


	/**
	 * ロビーの状態を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetLobbyStatusResult getLobbyStatus(GetLobbyStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/status";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetLobbyStatusRequest.Constant.MODULE,
				GetLobbyStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetLobbyStatusResult.class);

	}


	/**
	 * ロビーを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateLobbyResult updateLobby(UpdateLobbyRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("serviceClass", request.getServiceClass())
				.put("notificationType", request.getNotificationType())
				.put("logging", request.getLogging());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getNotificationUrl() != null) body.put("notificationUrl", request.getNotificationUrl());
        if(request.getNotificationGameName() != null) body.put("notificationGameName", request.getNotificationGameName());
        if(request.getLoggingDate() != null) body.put("loggingDate", request.getLoggingDate());
        if(request.getCreateRoomTriggerScript() != null) body.put("createRoomTriggerScript", request.getCreateRoomTriggerScript());
        if(request.getCreateRoomDoneTriggerScript() != null) body.put("createRoomDoneTriggerScript", request.getCreateRoomDoneTriggerScript());
        if(request.getDeleteRoomTriggerScript() != null) body.put("deleteRoomTriggerScript", request.getDeleteRoomTriggerScript());
        if(request.getDeleteRoomDoneTriggerScript() != null) body.put("deleteRoomDoneTriggerScript", request.getDeleteRoomDoneTriggerScript());
        if(request.getCreateSubscribeTriggerScript() != null) body.put("createSubscribeTriggerScript", request.getCreateSubscribeTriggerScript());
        if(request.getCreateSubscribeDoneTriggerScript() != null) body.put("createSubscribeDoneTriggerScript", request.getCreateSubscribeDoneTriggerScript());
        if(request.getDeleteSubscribeTriggerScript() != null) body.put("deleteSubscribeTriggerScript", request.getDeleteSubscribeTriggerScript());
        if(request.getDeleteSubscribeDoneTriggerScript() != null) body.put("deleteSubscribeDoneTriggerScript", request.getDeleteSubscribeDoneTriggerScript());
        if(request.getSendMessageTriggerScript() != null) body.put("sendMessageTriggerScript", request.getSendMessageTriggerScript());
        if(request.getSendMessageDoneTriggerScript() != null) body.put("sendMessageDoneTriggerScript", request.getSendMessageDoneTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "",
				credential,
				ENDPOINT,
				UpdateLobbyRequest.Constant.MODULE,
				UpdateLobbyRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateLobbyResult.class);

	}


	/**
	 * メッセージ検索時にスキャンするログサイズの予測値を取得します。<br>
	 * <br>
	 * 長期にわたる検索を行う場合、事前におおよそのログスキャン容量を把握したいと思うはずです。<br>
	 * そのような場合にはこのAPIを使用することで、事前にログスキャン容量を把握することが出来ます。<br>
	 * <br>
	 * ただし、ここで得られる値はあくまで予測値であり、実際に実行した際の値とは異なる場合があります。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CheckEstimateScanByteByAllRoomResult checkEstimateScanByteByAllRoom(CheckEstimateScanByteByAllRoomRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/log/estimate";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getUserId() != null) queryString.add(new BasicNameValuePair("userId", String.valueOf(request.getUserId())));
        if(request.getMessage() != null) queryString.add(new BasicNameValuePair("message", String.valueOf(request.getMessage())));
        if(request.getMeta() != null) queryString.add(new BasicNameValuePair("meta", String.valueOf(request.getMeta())));
        if(request.getBegin() != null) queryString.add(new BasicNameValuePair("begin", String.valueOf(request.getBegin())));
        if(request.getEnd() != null) queryString.add(new BasicNameValuePair("end", String.valueOf(request.getEnd())));
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				CheckEstimateScanByteByAllRoomRequest.Constant.MODULE,
				CheckEstimateScanByteByAllRoomRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, CheckEstimateScanByteByAllRoomResult.class);

	}


	/**
	 * メッセージ検索時にスキャンするログサイズの予測値を取得します。<br>
	 * <br>
	 * 長期にわたる検索を行う場合、事前におおよそのログスキャン容量を把握したいと思うはずです。<br>
	 * そのような場合にはこのAPIを使用することで、事前にログスキャン容量を把握することが出来ます。<br>
	 * <br>
	 * ただし、ここで得られる値はあくまで予測値であり、実際に実行した際の値とは異なる場合があります。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CheckEstimateScanByteByRoomResult checkEstimateScanByteByRoom(CheckEstimateScanByteByRoomRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "/log/estimate";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getUserId() != null) queryString.add(new BasicNameValuePair("userId", String.valueOf(request.getUserId())));
        if(request.getMessage() != null) queryString.add(new BasicNameValuePair("message", String.valueOf(request.getMessage())));
        if(request.getMeta() != null) queryString.add(new BasicNameValuePair("meta", String.valueOf(request.getMeta())));
        if(request.getBegin() != null) queryString.add(new BasicNameValuePair("begin", String.valueOf(request.getBegin())));
        if(request.getEnd() != null) queryString.add(new BasicNameValuePair("end", String.valueOf(request.getEnd())));
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				CheckEstimateScanByteByRoomRequest.Constant.MODULE,
				CheckEstimateScanByteByRoomRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, CheckEstimateScanByteByRoomResult.class);

	}


	/**
	 * メッセージログを検索します。<br>
	 * <br>
	 * メッセージログの検索には以下のパラメータを使用できます。<br>
	 * * ユーザID<br>
	 * * メッセージテキスト<br>
	 * * メッセージメタデータ<br>
	 * いずれも部分一致で検索できます。<br>
	 * たとえば、メッセージメタデータに JSON フォーマットを使用している場合は JSON 文字列に対する部分一致検索が適用できます。<br>
	 * 一方で、BLOB データを Base64 かけたようなデータの場合は検索対象とするのは困難です。<br>
	 * <br>
	 * メッセージログ検索にかかる費用は、検索時にログデータを何バイトスキャンしたかで決定されます。<br>
	 * そのため、発言者が滞在していたルームが特定できている場合は、本APIではなく『Gs2Chat:SearchLogByRoom』を使用する方が費用を節約できます。<br>
	 * また、検索範囲を時間で指定できますが、ログデータは1日単位(UTC)で分割して保存されており、スキャン時には1日分全てがスキャン対象となります。<br>
	 * つまり、特定の日付の5分間のログを検索するクエリを実行した場合、該当する1日分のログデータがスキャン対象となり、<br>
	 * さらにその5分間が日付をまたぐような場合は2日分のログデータがスキャン対象となります。<br>
	 * <br>
	 * 検索結果が指定した取得最大件数以上の結果を持つ場合、実行後一定期間内であればページトークンを使用した続きのデータ取得が可能です。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public SearchLogByAllRoomResult searchLogByAllRoom(SearchLogByAllRoomRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/log";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getUserId() != null) queryString.add(new BasicNameValuePair("userId", String.valueOf(request.getUserId())));
        if(request.getMessage() != null) queryString.add(new BasicNameValuePair("message", String.valueOf(request.getMessage())));
        if(request.getMeta() != null) queryString.add(new BasicNameValuePair("meta", String.valueOf(request.getMeta())));
        if(request.getBegin() != null) queryString.add(new BasicNameValuePair("begin", String.valueOf(request.getBegin())));
        if(request.getEnd() != null) queryString.add(new BasicNameValuePair("end", String.valueOf(request.getEnd())));
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));
        if(request.getUseCache() != null) queryString.add(new BasicNameValuePair("useCache", String.valueOf(request.getUseCache())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				SearchLogByAllRoomRequest.Constant.MODULE,
				SearchLogByAllRoomRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, SearchLogByAllRoomResult.class);

	}


	/**
	 * メッセージログを検索します。<br>
	 * <br>
	 * メッセージログの検索には以下のパラメータを使用できます。<br>
	 * * ユーザID<br>
	 * * メッセージテキスト<br>
	 * * メッセージメタデータ<br>
	 * いずれも部分一致で検索できます。<br>
	 * たとえば、メッセージメタデータに JSON フォーマットを使用している場合は JSON 文字列に対する部分一致検索が適用できます。<br>
	 * 一方で、BLOB データを Base64 かけたようなデータの場合は検索対象とするのは困難です。<br>
	 * <br>
	 * メッセージログ検索にかかる費用は、検索時にログデータを何バイトスキャンしたかで決定されます。<br>
	 * 検索範囲を時間で指定できますが、ログデータは1日単位(UTC)で分割して保存されており、スキャン時には1日分全てがスキャン対象となります。<br>
	 * つまり、特定の日付の5分間のログを検索するクエリを実行した場合、該当する1日分のログデータがスキャン対象となり、<br>
	 * さらにその5分間が日付をまたぐような場合は2日分のログデータがスキャン対象となります。<br>
	 * <br>
	 * 検索結果が指定した取得最大件数以上の結果を持つ場合、実行後一定期間内であればページトークンを使用した続きのデータ取得が可能です。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public SearchLogByRoomResult searchLogByRoom(SearchLogByRoomRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "/log";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getUserId() != null) queryString.add(new BasicNameValuePair("userId", String.valueOf(request.getUserId())));
        if(request.getMessage() != null) queryString.add(new BasicNameValuePair("message", String.valueOf(request.getMessage())));
        if(request.getMeta() != null) queryString.add(new BasicNameValuePair("meta", String.valueOf(request.getMeta())));
        if(request.getBegin() != null) queryString.add(new BasicNameValuePair("begin", String.valueOf(request.getBegin())));
        if(request.getEnd() != null) queryString.add(new BasicNameValuePair("end", String.valueOf(request.getEnd())));
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));
        if(request.getUseCache() != null) queryString.add(new BasicNameValuePair("useCache", String.valueOf(request.getUseCache())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				SearchLogByRoomRequest.Constant.MODULE,
				SearchLogByRoomRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, SearchLogByRoomResult.class);

	}


	/**
	 * メッセージの一覧を取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeMessageResult describeMessage(DescribeMessageRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "/message";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPassword() != null) queryString.add(new BasicNameValuePair("password", String.valueOf(request.getPassword())));
        if(request.getStartAt() != null) queryString.add(new BasicNameValuePair("startAt", String.valueOf(request.getStartAt())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeMessageRequest.Constant.MODULE,
				DescribeMessageRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, DescribeMessageResult.class);

	}


	/**
	 * メッセージの一覧を取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeMessageNoAuthResult describeMessageNoAuth(DescribeMessageNoAuthRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "/message/force";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getStartAt() != null) queryString.add(new BasicNameValuePair("startAt", String.valueOf(request.getStartAt())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeMessageNoAuthRequest.Constant.MODULE,
				DescribeMessageNoAuthRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeMessageNoAuthResult.class);

	}


	/**
	 * メッセージを送信します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public SendMessageResult sendMessage(SendMessageRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("message", request.getMessage());
        if(request.getMeta() != null) body.put("meta", request.getMeta());
        if(request.getPassword() != null) body.put("password", request.getPassword());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "/message",
				credential,
				ENDPOINT,
				SendMessageRequest.Constant.MODULE,
				SendMessageRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, SendMessageResult.class);

	}


	/**
	 * メッセージを送信します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public SendMessageNoAuthResult sendMessageNoAuth(SendMessageNoAuthRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("userId", request.getUserId())
				.put("message", request.getMessage());
        if(request.getMeta() != null) body.put("meta", request.getMeta());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "/message/force",
				credential,
				ENDPOINT,
				SendMessageNoAuthRequest.Constant.MODULE,
				SendMessageNoAuthRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, SendMessageNoAuthResult.class);

	}


	/**
	 * ルームを作成します<br>
	 * <br>
	 * ルームには参加可能なユーザIDリストを設定することが出来ます。<br>
	 * ここで指定されたユーザID以外のユーザがメッセージ情報を取得したり、メッセージを書き込もうとしても失敗するようになります。<br>
	 * 何も指定しなければ、誰でも読み書きの出来る部屋になります。<br>
	 * ルームを作成する際に参加するユーザが確定している場合に使用するといいでしょう。<br>
	 * <br>
	 * ルームにはパスワードを設定することが出来ます。<br>
	 * パスワードが設定されたルームのメッセージ情報を取得したり、メッセージを書き込もうとするさいにパスワードを指定する必要があります。<br>
	 * パスワードが一致しない場合は失敗します。<br>
	 * 何も指定しなければ、メッセージの読み書きにパスワードを要求しません。<br>
	 * ルームを作成する際には参加するユーザが確定できないけれど、アクセスを制限したい場合に使用するといいでしょう。<br>
	 * <br>
	 * ルームIDには任意の値を指定することが出来ます。<br>
	 * たとえば、マッチメイキングを実行し構築されたギャザリングのユーザ向けにチャットルームを提供したい場合、<br>
	 * ギャザリングIDをキーとしてルームを作成することで、クライアントがチャットにアクセスする際にIDの特定が容易になります。<br>
	 * ルームIDを省略するとUUIDv4に基づいて自動的に採番されます。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateRoomResult createRoom(CreateRoomRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getRoomId() != null) body.put("roomId", request.getRoomId());
        if(request.getPassword() != null) body.put("password", request.getPassword());
        if(request.getAllowUserIds() != null) {
            List<JsonNode> node = new ArrayList<>();
            for(String item : request.getAllowUserIds()) {
                node.add(JsonNodeFactory.instance.textNode(item));
            }
            body.set("allowUserIds", JsonNodeFactory.instance.arrayNode().addAll(node));
		}

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room",
				credential,
				ENDPOINT,
				CreateRoomRequest.Constant.MODULE,
				CreateRoomRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateRoomResult.class);

	}


	/**
	 * ルームを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteRoom(DeleteRoomRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteRoomRequest.Constant.MODULE,
				DeleteRoomRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ルームの一覧を取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeRoomResult describeRoom(DescribeRoomRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room";

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
				DescribeRoomRequest.Constant.MODULE,
				DescribeRoomRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeRoomResult.class);

	}


	/**
	 * ルームを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetRoomResult getRoom(GetRoomRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetRoomRequest.Constant.MODULE,
				GetRoomRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetRoomResult.class);

	}


	/**
	 * ルームを購読します。<br>
	 * <br>
	 * ルームを購読すると、ルームに対する新着メッセージを受信したときに通知を受けることが出来ます。<br>
	 * 通知方式はロビーの設定に依存します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateMySubscribeResult createMySubscribe(CreateMySubscribeRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getEnableOfflineTransfer() != null) body.put("enableOfflineTransfer", request.getEnableOfflineTransfer());
        if(request.getOfflineTransferSound() != null) body.put("offlineTransferSound", request.getOfflineTransferSound());
        if(request.getPassword() != null) body.put("password", request.getPassword());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "/subscribe",
				credential,
				ENDPOINT,
				CreateMySubscribeRequest.Constant.MODULE,
				CreateMySubscribeRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, CreateMySubscribeResult.class);

	}


	/**
	 * ルームを購読します。<br>
	 * <br>
	 * ルームを購読すると、ルームに対する新着メッセージを受信したときに通知を受けることが出来ます。<br>
	 * 通知方式はロビーの設定に依存します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateSubscribeResult createSubscribe(CreateSubscribeRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getEnableOfflineTransfer() != null) body.put("enableOfflineTransfer", request.getEnableOfflineTransfer());
        if(request.getOfflineTransferSound() != null) body.put("offlineTransferSound", request.getOfflineTransferSound());
        if(request.getPassword() != null) body.put("password", request.getPassword());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/subscribe",
				credential,
				ENDPOINT,
				CreateSubscribeRequest.Constant.MODULE,
				CreateSubscribeRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateSubscribeResult.class);

	}


	/**
	 * 購読を解除する。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteMySubscribe(DeleteMySubscribeRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "/user/self/subscribe";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteMySubscribeRequest.Constant.MODULE,
				DeleteMySubscribeRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        delete.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		doRequest(delete, null);

	}


	/**
	 * 購読を解除する。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteSubscribe(DeleteSubscribeRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/subscribe";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteSubscribeRequest.Constant.MODULE,
				DeleteSubscribeRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ユーザが購読しているルームの一覧を取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeMySubscribeResult describeMySubscribe(DescribeMySubscribeRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/user/subscribe";

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
				DescribeMySubscribeRequest.Constant.MODULE,
				DescribeMySubscribeRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, DescribeMySubscribeResult.class);

	}


	/**
	 * ルームを購読しているユーザの一覧を取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeSubscribeByRoomIdResult describeSubscribeByRoomId(DescribeSubscribeByRoomIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "/subscribe";

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
				DescribeSubscribeByRoomIdRequest.Constant.MODULE,
				DescribeSubscribeByRoomIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeSubscribeByRoomIdResult.class);

	}


	/**
	 * ユーザが購読しているルームの一覧を取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeSubscribeByUserIdResult describeSubscribeByUserId(DescribeSubscribeByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/subscribe";

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
				DescribeSubscribeByUserIdRequest.Constant.MODULE,
				DescribeSubscribeByUserIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeSubscribeByUserIdResult.class);

	}


	/**
	 * 購読情報を取得する。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetMySubscribeResult getMySubscribe(GetMySubscribeRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "/user/self/subscribe";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetMySubscribeRequest.Constant.MODULE,
				GetMySubscribeRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, GetMySubscribeResult.class);

	}


	/**
	 * 購読情報を取得する。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetSubscribeResult getSubscribe(GetSubscribeRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/lobby/" + (request.getLobbyName() == null || request.getLobbyName().equals("") ? "null" : request.getLobbyName()) + "/room/" + (request.getRoomId() == null || request.getRoomId().equals("") ? "null" : request.getRoomId()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/subscribe";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetSubscribeRequest.Constant.MODULE,
				GetSubscribeRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetSubscribeResult.class);

	}


}