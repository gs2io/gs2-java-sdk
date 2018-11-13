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

package io.gs2.gold;

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
import io.gs2.gold.control.*;

/**
 * GS2 Gold API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2GoldClient extends AbstractGs2Client<Gs2GoldClient> {

	public static String ENDPOINT = "gold";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2GoldClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2GoldClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2GoldClient(IGs2Credential credential, String region) {
		super(credential, region);

	}


	/**
	 * 公開されているゴールドマスタを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetCurrentGoldMasterResult getCurrentGoldMaster(GetCurrentGoldMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/gold/master";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetCurrentGoldMasterRequest.Constant.MODULE,
				GetCurrentGoldMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetCurrentGoldMasterResult.class);

	}


	/**
	 * 公開するゴールドマスタを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateCurrentGoldMasterResult updateCurrentGoldMaster(UpdateCurrentGoldMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("settings", request.getSettings());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/gold/master",
				credential,
				ENDPOINT,
				UpdateCurrentGoldMasterRequest.Constant.MODULE,
				UpdateCurrentGoldMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, UpdateCurrentGoldMasterResult.class);

	}


	/**
	 * ゴールドマスターを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateGoldMasterResult createGoldMaster(CreateGoldMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());
        if(request.getMeta() != null) body.put("meta", request.getMeta());
        if(request.getBalanceMax() != null) body.put("balanceMax", request.getBalanceMax());
        if(request.getResetType() != null) body.put("resetType", request.getResetType());
        if(request.getResetDayOfMonth() != null) body.put("resetDayOfMonth", request.getResetDayOfMonth());
        if(request.getResetDayOfWeek() != null) body.put("resetDayOfWeek", request.getResetDayOfWeek());
        if(request.getResetHour() != null) body.put("resetHour", request.getResetHour());
        if(request.getLatestGainMax() != null) body.put("latestGainMax", request.getLatestGainMax());
        if(request.getCreateWalletTriggerScript() != null) body.put("createWalletTriggerScript", request.getCreateWalletTriggerScript());
        if(request.getCreateWalletDoneTriggerScript() != null) body.put("createWalletDoneTriggerScript", request.getCreateWalletDoneTriggerScript());
        if(request.getDepositIntoWalletTriggerScript() != null) body.put("depositIntoWalletTriggerScript", request.getDepositIntoWalletTriggerScript());
        if(request.getDepositIntoWalletDoneTriggerScript() != null) body.put("depositIntoWalletDoneTriggerScript", request.getDepositIntoWalletDoneTriggerScript());
        if(request.getWithdrawFromWalletTriggerScript() != null) body.put("withdrawFromWalletTriggerScript", request.getWithdrawFromWalletTriggerScript());
        if(request.getWithdrawFromWalletDoneTriggerScript() != null) body.put("withdrawFromWalletDoneTriggerScript", request.getWithdrawFromWalletDoneTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/master/gold",
				credential,
				ENDPOINT,
				CreateGoldMasterRequest.Constant.MODULE,
				CreateGoldMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateGoldMasterResult.class);

	}


	/**
	 * ゴールドマスターを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteGoldMaster(DeleteGoldMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/master/gold/" + (request.getGoldName() == null || request.getGoldName().equals("") ? "null" : request.getGoldName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteGoldMasterRequest.Constant.MODULE,
				DeleteGoldMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ゴールドマスターの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeGoldMasterResult describeGoldMaster(DescribeGoldMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/master/gold";

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
				DescribeGoldMasterRequest.Constant.MODULE,
				DescribeGoldMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeGoldMasterResult.class);

	}


	/**
	 * ゴールドマスターを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetGoldMasterResult getGoldMaster(GetGoldMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/master/gold/" + (request.getGoldName() == null || request.getGoldName().equals("") ? "null" : request.getGoldName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetGoldMasterRequest.Constant.MODULE,
				GetGoldMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetGoldMasterResult.class);

	}


	/**
	 * ゴールドマスターを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateGoldMasterResult updateGoldMaster(UpdateGoldMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getMeta() != null) body.put("meta", request.getMeta());
        if(request.getBalanceMax() != null) body.put("balanceMax", request.getBalanceMax());
        if(request.getResetType() != null) body.put("resetType", request.getResetType());
        if(request.getResetDayOfMonth() != null) body.put("resetDayOfMonth", request.getResetDayOfMonth());
        if(request.getResetDayOfWeek() != null) body.put("resetDayOfWeek", request.getResetDayOfWeek());
        if(request.getResetHour() != null) body.put("resetHour", request.getResetHour());
        if(request.getLatestGainMax() != null) body.put("latestGainMax", request.getLatestGainMax());
        if(request.getCreateWalletTriggerScript() != null) body.put("createWalletTriggerScript", request.getCreateWalletTriggerScript());
        if(request.getCreateWalletDoneTriggerScript() != null) body.put("createWalletDoneTriggerScript", request.getCreateWalletDoneTriggerScript());
        if(request.getDepositIntoWalletTriggerScript() != null) body.put("depositIntoWalletTriggerScript", request.getDepositIntoWalletTriggerScript());
        if(request.getDepositIntoWalletDoneTriggerScript() != null) body.put("depositIntoWalletDoneTriggerScript", request.getDepositIntoWalletDoneTriggerScript());
        if(request.getWithdrawFromWalletTriggerScript() != null) body.put("withdrawFromWalletTriggerScript", request.getWithdrawFromWalletTriggerScript());
        if(request.getWithdrawFromWalletDoneTriggerScript() != null) body.put("withdrawFromWalletDoneTriggerScript", request.getWithdrawFromWalletDoneTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/master/gold/" + (request.getGoldName() == null || request.getGoldName().equals("") ? "null" : request.getGoldName()) + "",
				credential,
				ENDPOINT,
				UpdateGoldMasterRequest.Constant.MODULE,
				UpdateGoldMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateGoldMasterResult.class);

	}


	/**
	 * ゴールドプールを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateGoldPoolResult createGoldPool(CreateGoldPoolRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("serviceClass", request.getServiceClass());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getCreateWalletTriggerScript() != null) body.put("createWalletTriggerScript", request.getCreateWalletTriggerScript());
        if(request.getCreateWalletDoneTriggerScript() != null) body.put("createWalletDoneTriggerScript", request.getCreateWalletDoneTriggerScript());
        if(request.getDepositIntoWalletTriggerScript() != null) body.put("depositIntoWalletTriggerScript", request.getDepositIntoWalletTriggerScript());
        if(request.getDepositIntoWalletDoneTriggerScript() != null) body.put("depositIntoWalletDoneTriggerScript", request.getDepositIntoWalletDoneTriggerScript());
        if(request.getWithdrawFromWalletTriggerScript() != null) body.put("withdrawFromWalletTriggerScript", request.getWithdrawFromWalletTriggerScript());
        if(request.getWithdrawFromWalletDoneTriggerScript() != null) body.put("withdrawFromWalletDoneTriggerScript", request.getWithdrawFromWalletDoneTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/goldPool",
				credential,
				ENDPOINT,
				CreateGoldPoolRequest.Constant.MODULE,
				CreateGoldPoolRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateGoldPoolResult.class);

	}


	/**
	 * ゴールドプールを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteGoldPool(DeleteGoldPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteGoldPoolRequest.Constant.MODULE,
				DeleteGoldPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ゴールドプールの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeGoldPoolResult describeGoldPool(DescribeGoldPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool";

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
				DescribeGoldPoolRequest.Constant.MODULE,
				DescribeGoldPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeGoldPoolResult.class);

	}


	/**
	 * サービスクラスの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeServiceClassResult describeServiceClass(DescribeServiceClassRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/serviceClass";



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
	 * ゴールドプールを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetGoldPoolResult getGoldPool(GetGoldPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetGoldPoolRequest.Constant.MODULE,
				GetGoldPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetGoldPoolResult.class);

	}


	/**
	 * ゴールドプールの状態を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetGoldPoolStatusResult getGoldPoolStatus(GetGoldPoolStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/status";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetGoldPoolStatusRequest.Constant.MODULE,
				GetGoldPoolStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetGoldPoolStatusResult.class);

	}


	/**
	 * ゴールドプールを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateGoldPoolResult updateGoldPool(UpdateGoldPoolRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("serviceClass", request.getServiceClass());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getCreateWalletTriggerScript() != null) body.put("createWalletTriggerScript", request.getCreateWalletTriggerScript());
        if(request.getCreateWalletDoneTriggerScript() != null) body.put("createWalletDoneTriggerScript", request.getCreateWalletDoneTriggerScript());
        if(request.getDepositIntoWalletTriggerScript() != null) body.put("depositIntoWalletTriggerScript", request.getDepositIntoWalletTriggerScript());
        if(request.getDepositIntoWalletDoneTriggerScript() != null) body.put("depositIntoWalletDoneTriggerScript", request.getDepositIntoWalletDoneTriggerScript());
        if(request.getWithdrawFromWalletTriggerScript() != null) body.put("withdrawFromWalletTriggerScript", request.getWithdrawFromWalletTriggerScript());
        if(request.getWithdrawFromWalletDoneTriggerScript() != null) body.put("withdrawFromWalletDoneTriggerScript", request.getWithdrawFromWalletDoneTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "",
				credential,
				ENDPOINT,
				UpdateGoldPoolRequest.Constant.MODULE,
				UpdateGoldPoolRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateGoldPoolResult.class);

	}


	/**
	 * ゴールドマスターデータをエクスポートする<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ExportMasterResult exportMaster(ExportMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/master";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				ExportMasterRequest.Constant.MODULE,
				ExportMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, ExportMasterResult.class);

	}


	/**
	 * ウォレットの設定を取得します<br>
	 * <br>
	 * - 消費クオータ: 2<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetWalletSettingsResult getWalletSettings(GetWalletSettingsRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/gold/" + (request.getGoldName() == null || request.getGoldName().equals("") ? "null" : request.getGoldName()) + "/wallet/settings";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetWalletSettingsRequest.Constant.MODULE,
				GetWalletSettingsRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetWalletSettingsResult.class);

	}


	/**
	 * ウォレットを削除します<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteWalletByUserId(DeleteWalletByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/gold/" + (request.getGoldName() == null || request.getGoldName().equals("") ? "null" : request.getGoldName()) + "/wallet/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteWalletByUserIdRequest.Constant.MODULE,
				DeleteWalletByUserIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ウォレットの残高を加算します<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DepositIntoWalletResult depositIntoWallet(DepositIntoWalletRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("value", request.getValue());
        if(request.getContext() != null) body.put("context", request.getContext());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/gold/" + (request.getGoldName() == null || request.getGoldName().equals("") ? "null" : request.getGoldName()) + "/wallet/user/self/action/deposit",
				credential,
				ENDPOINT,
				DepositIntoWalletRequest.Constant.MODULE,
				DepositIntoWalletRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, DepositIntoWalletResult.class);

	}


	/**
	 * スタンプタスクを使用してウォレットの残高を加算します<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DepositIntoWalletByStampSheetResult depositIntoWalletByStampSheet(DepositIntoWalletByStampSheetRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("sheet", request.getSheet())
				.put("keyName", request.getKeyName());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/wallet/action/deposit",
				credential,
				ENDPOINT,
				DepositIntoWalletByStampSheetRequest.Constant.MODULE,
				DepositIntoWalletByStampSheetRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, DepositIntoWalletByStampSheetResult.class);

	}


	/**
	 * ウォレットの残高を加算します<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DepositIntoWalletByUserIdResult depositIntoWalletByUserId(DepositIntoWalletByUserIdRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("value", request.getValue());
        if(request.getContext() != null) body.put("context", request.getContext());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/gold/" + (request.getGoldName() == null || request.getGoldName().equals("") ? "null" : request.getGoldName()) + "/wallet/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/action/deposit",
				credential,
				ENDPOINT,
				DepositIntoWalletByUserIdRequest.Constant.MODULE,
				DepositIntoWalletByUserIdRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, DepositIntoWalletByUserIdResult.class);

	}


	/**
	 * ウォレットの一覧を取得します<br>
	 * <br>
	 * - 消費クオータ: 30件あたり10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeWalletResult describeWallet(DescribeWalletRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/gold";

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
				DescribeWalletRequest.Constant.MODULE,
				DescribeWalletRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, DescribeWalletResult.class);

	}


	/**
	 * ウォレットの一覧を取得します<br>
	 * <br>
	 * - 消費クオータ: 30件あたり10<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeWalletByUserIdResult describeWalletByUserId(DescribeWalletByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/gold/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "";

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
				DescribeWalletByUserIdRequest.Constant.MODULE,
				DescribeWalletByUserIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeWalletByUserIdResult.class);

	}


	/**
	 * ウォレットを取得します<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetWalletResult getWallet(GetWalletRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/gold/" + (request.getGoldName() == null || request.getGoldName().equals("") ? "null" : request.getGoldName()) + "/wallet/user/self";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetWalletRequest.Constant.MODULE,
				GetWalletRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, GetWalletResult.class);

	}


	/**
	 * ウォレットを取得します<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetWalletByUserIdResult getWalletByUserId(GetWalletByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/gold/" + (request.getGoldName() == null || request.getGoldName().equals("") ? "null" : request.getGoldName()) + "/wallet/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetWalletByUserIdRequest.Constant.MODULE,
				GetWalletByUserIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetWalletByUserIdResult.class);

	}


	/**
	 * ウォレットの残高を加算します<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public SetLatestGainResult setLatestGain(SetLatestGainRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("value", request.getValue());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/gold/" + (request.getGoldName() == null || request.getGoldName().equals("") ? "null" : request.getGoldName()) + "/wallet/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/action/set",
				credential,
				ENDPOINT,
				SetLatestGainRequest.Constant.MODULE,
				SetLatestGainRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, SetLatestGainResult.class);

	}


	/**
	 * ウォレットの残高を減算します<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public WithdrawFromWalletResult withdrawFromWallet(WithdrawFromWalletRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("value", request.getValue());
        if(request.getContext() != null) body.put("context", request.getContext());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/gold/" + (request.getGoldName() == null || request.getGoldName().equals("") ? "null" : request.getGoldName()) + "/wallet/user/self/action/withdraw",
				credential,
				ENDPOINT,
				WithdrawFromWalletRequest.Constant.MODULE,
				WithdrawFromWalletRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, WithdrawFromWalletResult.class);

	}


	/**
	 * スタンプタスクを使用してウォレットの減算を加算します<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public WithdrawFromWalletByStampTaskResult withdrawFromWalletByStampTask(WithdrawFromWalletByStampTaskRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("task", request.getTask())
				.put("keyName", request.getKeyName())
				.put("transactionId", request.getTransactionId());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/wallet/action/withdraw",
				credential,
				ENDPOINT,
				WithdrawFromWalletByStampTaskRequest.Constant.MODULE,
				WithdrawFromWalletByStampTaskRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, WithdrawFromWalletByStampTaskResult.class);

	}


	/**
	 * ウォレットの残高を減算します<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public WithdrawFromWalletByUserIdResult withdrawFromWalletByUserId(WithdrawFromWalletByUserIdRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("value", request.getValue());
        if(request.getContext() != null) body.put("context", request.getContext());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/goldPool/" + (request.getGoldPoolName() == null || request.getGoldPoolName().equals("") ? "null" : request.getGoldPoolName()) + "/gold/" + (request.getGoldName() == null || request.getGoldName().equals("") ? "null" : request.getGoldName()) + "/wallet/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/action/withdraw",
				credential,
				ENDPOINT,
				WithdrawFromWalletByUserIdRequest.Constant.MODULE,
				WithdrawFromWalletByUserIdRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, WithdrawFromWalletByUserIdResult.class);

	}


}