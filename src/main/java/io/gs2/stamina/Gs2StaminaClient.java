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

package io.gs2.stamina;

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
import io.gs2.stamina.control.*;

/**
 * GS2 Stamina API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2StaminaClient extends AbstractGs2Client<Gs2StaminaClient> {

	public static String ENDPOINT = "stamina";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2StaminaClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2StaminaClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2StaminaClient(IGs2Credential credential, String region) {
		super(credential, region);
	}


	/**
	 * スタミナプールを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateStaminaPoolResult createStaminaPool(CreateStaminaPoolRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("serviceClass", request.getServiceClass())
				.put("increaseInterval", request.getIncreaseInterval());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getConsumeStaminaTriggerScript() != null) body.put("consumeStaminaTriggerScript", request.getConsumeStaminaTriggerScript());
        if(request.getConsumeStaminaDoneTriggerScript() != null) body.put("consumeStaminaDoneTriggerScript", request.getConsumeStaminaDoneTriggerScript());
        if(request.getAddStaminaTriggerScript() != null) body.put("addStaminaTriggerScript", request.getAddStaminaTriggerScript());
        if(request.getAddStaminaDoneTriggerScript() != null) body.put("addStaminaDoneTriggerScript", request.getAddStaminaDoneTriggerScript());
        if(request.getGetMaxStaminaTriggerScript() != null) body.put("getMaxStaminaTriggerScript", request.getGetMaxStaminaTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/staminaPool",
				credential,
				ENDPOINT,
				CreateStaminaPoolRequest.Constant.MODULE,
				CreateStaminaPoolRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateStaminaPoolResult.class);

	}


	/**
	 * スタミナプールを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteStaminaPool(DeleteStaminaPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/staminaPool/" + (request.getStaminaPoolName() == null || request.getStaminaPoolName().equals("") ? "null" : request.getStaminaPoolName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteStaminaPoolRequest.Constant.MODULE,
				DeleteStaminaPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * サービスクラスの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeServiceClassResult describeServiceClass(DescribeServiceClassRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/staminaPool/serviceClass";



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
	 * スタミナプールの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeStaminaPoolResult describeStaminaPool(DescribeStaminaPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/staminaPool";

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
				DescribeStaminaPoolRequest.Constant.MODULE,
				DescribeStaminaPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeStaminaPoolResult.class);

	}


	/**
	 * スタミナプールを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetStaminaPoolResult getStaminaPool(GetStaminaPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/staminaPool/" + (request.getStaminaPoolName() == null || request.getStaminaPoolName().equals("") ? "null" : request.getStaminaPoolName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetStaminaPoolRequest.Constant.MODULE,
				GetStaminaPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetStaminaPoolResult.class);

	}


	/**
	 * スタミナプールの状態を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetStaminaPoolStatusResult getStaminaPoolStatus(GetStaminaPoolStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/staminaPool/" + (request.getStaminaPoolName() == null || request.getStaminaPoolName().equals("") ? "null" : request.getStaminaPoolName()) + "/status";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetStaminaPoolStatusRequest.Constant.MODULE,
				GetStaminaPoolStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetStaminaPoolStatusResult.class);

	}


	/**
	 * スタミナプールを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateStaminaPoolResult updateStaminaPool(UpdateStaminaPoolRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("serviceClass", request.getServiceClass())
				.put("increaseInterval", request.getIncreaseInterval());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getConsumeStaminaTriggerScript() != null) body.put("consumeStaminaTriggerScript", request.getConsumeStaminaTriggerScript());
        if(request.getConsumeStaminaDoneTriggerScript() != null) body.put("consumeStaminaDoneTriggerScript", request.getConsumeStaminaDoneTriggerScript());
        if(request.getAddStaminaTriggerScript() != null) body.put("addStaminaTriggerScript", request.getAddStaminaTriggerScript());
        if(request.getAddStaminaDoneTriggerScript() != null) body.put("addStaminaDoneTriggerScript", request.getAddStaminaDoneTriggerScript());
        if(request.getGetMaxStaminaTriggerScript() != null) body.put("getMaxStaminaTriggerScript", request.getGetMaxStaminaTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/staminaPool/" + (request.getStaminaPoolName() == null || request.getStaminaPoolName().equals("") ? "null" : request.getStaminaPoolName()) + "",
				credential,
				ENDPOINT,
				UpdateStaminaPoolRequest.Constant.MODULE,
				UpdateStaminaPoolRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateStaminaPoolResult.class);

	}


	/**
	 * スタミナを増減します<br>
	 * <br>
	 * - 消費クオータ: 5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ChangeStaminaResult changeStamina(ChangeStaminaRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("variation", request.getVariation())
				.put("maxValue", request.getMaxValue());
        if(request.getOverflow() != null) body.put("overflow", request.getOverflow());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/staminaPool/" + (request.getStaminaPoolName() == null || request.getStaminaPoolName().equals("") ? "null" : request.getStaminaPoolName()) + "/stamina",
				credential,
				ENDPOINT,
				ChangeStaminaRequest.Constant.MODULE,
				ChangeStaminaRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, ChangeStaminaResult.class);

	}


	/**
	 * スタンプシートを使用してスタミナを増減します<br>
	 * <br>
	 * - 消費クオータ: 5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ChangeStaminaByStampSheetResult changeStaminaByStampSheet(ChangeStaminaByStampSheetRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("sheet", request.getSheet())
				.put("keyName", request.getKeyName());
        if(request.getMaxValue() != null) body.put("maxValue", request.getMaxValue());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/stamina",
				credential,
				ENDPOINT,
				ChangeStaminaByStampSheetRequest.Constant.MODULE,
				ChangeStaminaByStampSheetRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, ChangeStaminaByStampSheetResult.class);

	}


	/**
	 * スタミナを消費します。<br>
	 * このエンドポイントは回復に使用できません。<br>
	 * ポリシーで消費と回復を分けて管理したい場合に使用してください。<br>
	 * <br>
	 * - 消費クオータ: 5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ConsumeStaminaResult consumeStamina(ConsumeStaminaRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("variation", request.getVariation())
				.put("maxValue", request.getMaxValue());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/staminaPool/" + (request.getStaminaPoolName() == null || request.getStaminaPoolName().equals("") ? "null" : request.getStaminaPoolName()) + "/stamina/consume",
				credential,
				ENDPOINT,
				ConsumeStaminaRequest.Constant.MODULE,
				ConsumeStaminaRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, ConsumeStaminaResult.class);

	}


	/**
	 * スタンプタスクを使用してスタミナを消費します。<br>
	 * <br>
	 * - 消費クオータ: 5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ConsumeStaminaByStampTaskResult consumeStaminaByStampTask(ConsumeStaminaByStampTaskRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("task", request.getTask())
				.put("keyName", request.getKeyName())
				.put("transactionId", request.getTransactionId())
				.put("maxValue", request.getMaxValue());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/stamina/consume",
				credential,
				ENDPOINT,
				ConsumeStaminaByStampTaskRequest.Constant.MODULE,
				ConsumeStaminaByStampTaskRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, ConsumeStaminaByStampTaskResult.class);

	}


	/**
	 * 現在のスタミナ値を取得します<br>
	 * <br>
	 * - 消費クオータ: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetStaminaResult getStamina(GetStaminaRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/staminaPool/" + (request.getStaminaPoolName() == null || request.getStaminaPoolName().equals("") ? "null" : request.getStaminaPoolName()) + "/stamina";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getMaxValue() != null) queryString.add(new BasicNameValuePair("maxValue", String.valueOf(request.getMaxValue())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetStaminaRequest.Constant.MODULE,
				GetStaminaRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, GetStaminaResult.class);

	}


}