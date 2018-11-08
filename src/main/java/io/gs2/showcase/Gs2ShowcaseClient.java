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

package io.gs2.showcase;

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
import io.gs2.showcase.control.*;

/**
 * GS2 Showcase API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2ShowcaseClient extends AbstractGs2Client<Gs2ShowcaseClient> {

	public static String ENDPOINT = "showcase";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2ShowcaseClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2ShowcaseClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2ShowcaseClient(IGs2Credential credential, String region) {
		super(credential, region);

	}


	/**
	 * 公開されているショーケースマスタを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetCurrentShowcaseMasterResult getCurrentShowcaseMaster(GetCurrentShowcaseMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/item/master";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetCurrentShowcaseMasterRequest.Constant.MODULE,
				GetCurrentShowcaseMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetCurrentShowcaseMasterResult.class);

	}


	/**
	 * 公開するショーケースマスタを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateCurrentShowcaseMasterResult updateCurrentShowcaseMaster(UpdateCurrentShowcaseMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("settings", request.getSettings());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/item/master",
				credential,
				ENDPOINT,
				UpdateCurrentShowcaseMasterRequest.Constant.MODULE,
				UpdateCurrentShowcaseMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, UpdateCurrentShowcaseMasterResult.class);

	}


	/**
	 * 商品グループを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateItemGroupMasterResult createItemGroupMaster(CreateItemGroupMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());
        if(request.getItemNames() != null) {
            List<JsonNode> node = new ArrayList<>();
            for(String item : request.getItemNames()) {
                node.add(JsonNodeFactory.instance.textNode(item));
            }
            body.set("itemNames", JsonNodeFactory.instance.arrayNode().addAll(node));
		}

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/itemGroup",
				credential,
				ENDPOINT,
				CreateItemGroupMasterRequest.Constant.MODULE,
				CreateItemGroupMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateItemGroupMasterResult.class);

	}


	/**
	 * 商品グループを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteItemGroupMaster(DeleteItemGroupMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/itemGroup/" + (request.getItemGroupName() == null || request.getItemGroupName().equals("") ? "null" : request.getItemGroupName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteItemGroupMasterRequest.Constant.MODULE,
				DeleteItemGroupMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 商品グループの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeItemGroupMasterResult describeItemGroupMaster(DescribeItemGroupMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/itemGroup";

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
				DescribeItemGroupMasterRequest.Constant.MODULE,
				DescribeItemGroupMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeItemGroupMasterResult.class);

	}


	/**
	 * 商品グループを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetItemGroupMasterResult getItemGroupMaster(GetItemGroupMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/itemGroup/" + (request.getItemGroupName() == null || request.getItemGroupName().equals("") ? "null" : request.getItemGroupName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetItemGroupMasterRequest.Constant.MODULE,
				GetItemGroupMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetItemGroupMasterResult.class);

	}


	/**
	 * 商品グループを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateItemGroupMasterResult updateItemGroupMaster(UpdateItemGroupMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getItemNames() != null) {
            List<JsonNode> node = new ArrayList<>();
            for(String item : request.getItemNames()) {
                node.add(JsonNodeFactory.instance.textNode(item));
            }
            body.set("itemNames", JsonNodeFactory.instance.arrayNode().addAll(node));
		}
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/itemGroup/" + (request.getItemGroupName() == null || request.getItemGroupName().equals("") ? "null" : request.getItemGroupName()) + "",
				credential,
				ENDPOINT,
				UpdateItemGroupMasterRequest.Constant.MODULE,
				UpdateItemGroupMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateItemGroupMasterResult.class);

	}


	/**
	 * 商品を新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateItemMasterResult createItemMaster(CreateItemMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("currencyType", request.getCurrencyType())
				.put("price", request.getPrice())
				.put("itemType", request.getItemType());
        if(request.getMeta() != null) body.put("meta", request.getMeta());
        if(request.getCurrencyMoneyName() != null) body.put("currencyMoneyName", request.getCurrencyMoneyName());
        if(request.getCurrencyGoldName() != null) body.put("currencyGoldName", request.getCurrencyGoldName());
        if(request.getCurrencyGoldPoolName() != null) body.put("currencyGoldPoolName", request.getCurrencyGoldPoolName());
        if(request.getCurrencyConsumableItemItemPoolName() != null) body.put("currencyConsumableItemItemPoolName", request.getCurrencyConsumableItemItemPoolName());
        if(request.getCurrencyConsumableItemItemName() != null) body.put("currencyConsumableItemItemName", request.getCurrencyConsumableItemItemName());
        if(request.getCurrencyOption() != null) body.put("currencyOption", request.getCurrencyOption());
        if(request.getItemMoneyName() != null) body.put("itemMoneyName", request.getItemMoneyName());
        if(request.getItemGoldPoolName() != null) body.put("itemGoldPoolName", request.getItemGoldPoolName());
        if(request.getItemGoldName() != null) body.put("itemGoldName", request.getItemGoldName());
        if(request.getItemStaminaStaminaPoolName() != null) body.put("itemStaminaStaminaPoolName", request.getItemStaminaStaminaPoolName());
        if(request.getItemConsumableItemItemPoolName() != null) body.put("itemConsumableItemItemPoolName", request.getItemConsumableItemItemPoolName());
        if(request.getItemConsumableItemItemName() != null) body.put("itemConsumableItemItemName", request.getItemConsumableItemItemName());
        if(request.getItemGachaGachaPoolName() != null) body.put("itemGachaGachaPoolName", request.getItemGachaGachaPoolName());
        if(request.getItemGachaGachaName() != null) body.put("itemGachaGachaName", request.getItemGachaGachaName());
        if(request.getItemAmount() != null) body.put("itemAmount", request.getItemAmount());
        if(request.getItemOption() != null) body.put("itemOption", request.getItemOption());
        if(request.getOpenConditionType() != null) body.put("openConditionType", request.getOpenConditionType());
        if(request.getOpenConditionLimitName() != null) body.put("openConditionLimitName", request.getOpenConditionLimitName());
        if(request.getOpenConditionLimitCounterName() != null) body.put("openConditionLimitCounterName", request.getOpenConditionLimitCounterName());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/item",
				credential,
				ENDPOINT,
				CreateItemMasterRequest.Constant.MODULE,
				CreateItemMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateItemMasterResult.class);

	}


	/**
	 * 商品を削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteItemMaster(DeleteItemMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/item/" + (request.getItemName() == null || request.getItemName().equals("") ? "null" : request.getItemName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteItemMasterRequest.Constant.MODULE,
				DeleteItemMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 商品の一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeItemMasterResult describeItemMaster(DescribeItemMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/item";

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
				DescribeItemMasterRequest.Constant.MODULE,
				DescribeItemMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeItemMasterResult.class);

	}


	/**
	 * 商品を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetItemMasterResult getItemMaster(GetItemMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/item/" + (request.getItemName() == null || request.getItemName().equals("") ? "null" : request.getItemName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetItemMasterRequest.Constant.MODULE,
				GetItemMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetItemMasterResult.class);

	}


	/**
	 * 商品を更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateItemMasterResult updateItemMaster(UpdateItemMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("currencyType", request.getCurrencyType())
				.put("price", request.getPrice())
				.put("itemType", request.getItemType());
        if(request.getMeta() != null) body.put("meta", request.getMeta());
        if(request.getCurrencyMoneyName() != null) body.put("currencyMoneyName", request.getCurrencyMoneyName());
        if(request.getCurrencyGoldName() != null) body.put("currencyGoldName", request.getCurrencyGoldName());
        if(request.getCurrencyGoldPoolName() != null) body.put("currencyGoldPoolName", request.getCurrencyGoldPoolName());
        if(request.getCurrencyConsumableItemItemPoolName() != null) body.put("currencyConsumableItemItemPoolName", request.getCurrencyConsumableItemItemPoolName());
        if(request.getCurrencyConsumableItemItemName() != null) body.put("currencyConsumableItemItemName", request.getCurrencyConsumableItemItemName());
        if(request.getCurrencyOption() != null) body.put("currencyOption", request.getCurrencyOption());
        if(request.getItemMoneyName() != null) body.put("itemMoneyName", request.getItemMoneyName());
        if(request.getItemGoldPoolName() != null) body.put("itemGoldPoolName", request.getItemGoldPoolName());
        if(request.getItemGoldName() != null) body.put("itemGoldName", request.getItemGoldName());
        if(request.getItemStaminaStaminaPoolName() != null) body.put("itemStaminaStaminaPoolName", request.getItemStaminaStaminaPoolName());
        if(request.getItemConsumableItemItemPoolName() != null) body.put("itemConsumableItemItemPoolName", request.getItemConsumableItemItemPoolName());
        if(request.getItemConsumableItemItemName() != null) body.put("itemConsumableItemItemName", request.getItemConsumableItemItemName());
        if(request.getItemGachaGachaPoolName() != null) body.put("itemGachaGachaPoolName", request.getItemGachaGachaPoolName());
        if(request.getItemGachaGachaName() != null) body.put("itemGachaGachaName", request.getItemGachaGachaName());
        if(request.getItemAmount() != null) body.put("itemAmount", request.getItemAmount());
        if(request.getItemOption() != null) body.put("itemOption", request.getItemOption());
        if(request.getOpenConditionType() != null) body.put("openConditionType", request.getOpenConditionType());
        if(request.getOpenConditionLimitName() != null) body.put("openConditionLimitName", request.getOpenConditionLimitName());
        if(request.getOpenConditionLimitCounterName() != null) body.put("openConditionLimitCounterName", request.getOpenConditionLimitCounterName());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/item/" + (request.getItemName() == null || request.getItemName().equals("") ? "null" : request.getItemName()) + "",
				credential,
				ENDPOINT,
				UpdateItemMasterRequest.Constant.MODULE,
				UpdateItemMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateItemMasterResult.class);

	}


	/**
	 * 購入処理を実行完了する為に必要となるスタンプシートを取得します。<br>
	 * スタンプシートの詳細は GS2 ドキュメントを参照してください。<br>
	 * <br>
	 * このAPIによって払い出されるスタンプシートが要求するタスクは以下のアクションの可能性があります。<br>
	 * <br>
	 * Gs2Money:VerifyByStampTask<br>
	 * Gs2Money:ConsumeWalletByStampTask<br>
	 * Gs2Gold:WithdrawFromWalletByStampTask<br>
	 * Gs2Stamina:ConsumeStaminaByStampTask<br>
	 * Gs2ConsumableItem:ConsumeInventoryByStampTask<br>
	 * Gs2Limit:UpCounterByStampTask<br>
	 * <br>
	 * このAPIによって払い出されるスタンプシートの完了は以下のアクションの可能性があります。<br>
	 * <br>
	 * Gs2Money:ChargeWalletByStampSheet<br>
	 * Gs2Gold:DepositIntoWalletByStampSheet<br>
	 * Gs2Stamina:ChangeStaminaByStampSheet<br>
	 * Gs2ConsumableItem:AcquisitionInventoryByStampSheet<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public BuyItemResult buyItem(BuyItemRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("keyName", request.getKeyName());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/item/" + (request.getShowcaseItemId() == null || request.getShowcaseItemId().equals("") ? "null" : request.getShowcaseItemId()) + "",
				credential,
				ENDPOINT,
				BuyItemRequest.Constant.MODULE,
				BuyItemRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, BuyItemResult.class);

	}


	/**
	 * 陳列されている商品一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeItemResult describeItem(DescribeItemRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/item";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeItemRequest.Constant.MODULE,
				DescribeItemRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, DescribeItemResult.class);

	}


	/**
	 * 陳列されている商品を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetItemResult getItem(GetItemRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/item/" + (request.getShowcaseItemId() == null || request.getShowcaseItemId().equals("") ? "null" : request.getShowcaseItemId()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetItemRequest.Constant.MODULE,
				GetItemRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, GetItemResult.class);

	}


	/**
	 * ショーケースマスターデータをエクスポートする<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ExportMasterResult exportMaster(ExportMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master";



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
	 * 陳列商品を新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateShowcaseItemMasterResult createShowcaseItemMaster(CreateShowcaseItemMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("category", request.getCategory())
				.put("priority", request.getPriority());
        if(request.getItemName() != null) body.put("itemName", request.getItemName());
        if(request.getItemGroupName() != null) body.put("itemGroupName", request.getItemGroupName());
        if(request.getReleaseConditionType() != null) body.put("releaseConditionType", request.getReleaseConditionType());
        if(request.getReleaseConditionScheduleName() != null) body.put("releaseConditionScheduleName", request.getReleaseConditionScheduleName());
        if(request.getReleaseConditionScheduleEventName() != null) body.put("releaseConditionScheduleEventName", request.getReleaseConditionScheduleEventName());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/showcaseItem",
				credential,
				ENDPOINT,
				CreateShowcaseItemMasterRequest.Constant.MODULE,
				CreateShowcaseItemMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateShowcaseItemMasterResult.class);

	}


	/**
	 * 陳列商品を削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteShowcaseItemMaster(DeleteShowcaseItemMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/showcaseItem/" + (request.getCategory() == null || request.getCategory().equals("") ? "null" : request.getCategory()) + "/" + (request.getResourceId() == null || request.getResourceId().equals("") ? "null" : request.getResourceId()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteShowcaseItemMasterRequest.Constant.MODULE,
				DeleteShowcaseItemMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 陳列商品の一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeShowcaseItemMasterResult describeShowcaseItemMaster(DescribeShowcaseItemMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/showcaseItem";

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
				DescribeShowcaseItemMasterRequest.Constant.MODULE,
				DescribeShowcaseItemMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeShowcaseItemMasterResult.class);

	}


	/**
	 * 陳列商品を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetShowcaseItemMasterResult getShowcaseItemMaster(GetShowcaseItemMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/showcaseItem/" + (request.getCategory() == null || request.getCategory().equals("") ? "null" : request.getCategory()) + "/" + (request.getResourceId() == null || request.getResourceId().equals("") ? "null" : request.getResourceId()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetShowcaseItemMasterRequest.Constant.MODULE,
				GetShowcaseItemMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetShowcaseItemMasterResult.class);

	}


	/**
	 * 陳列商品を更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateShowcaseItemMasterResult updateShowcaseItemMaster(UpdateShowcaseItemMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("releaseConditionType", request.getReleaseConditionType())
				.put("priority", request.getPriority());
        if(request.getReleaseConditionScheduleName() != null) body.put("releaseConditionScheduleName", request.getReleaseConditionScheduleName());
        if(request.getReleaseConditionScheduleEventName() != null) body.put("releaseConditionScheduleEventName", request.getReleaseConditionScheduleEventName());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/master/showcaseItem/" + (request.getCategory() == null || request.getCategory().equals("") ? "null" : request.getCategory()) + "/" + (request.getResourceId() == null || request.getResourceId().equals("") ? "null" : request.getResourceId()) + "",
				credential,
				ENDPOINT,
				UpdateShowcaseItemMasterRequest.Constant.MODULE,
				UpdateShowcaseItemMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateShowcaseItemMasterResult.class);

	}


	/**
	 * ショーケースを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateShowcaseResult createShowcase(CreateShowcaseRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getReleaseConditionTriggerScript() != null) body.put("releaseConditionTriggerScript", request.getReleaseConditionTriggerScript());
        if(request.getBuyTriggerScript() != null) body.put("buyTriggerScript", request.getBuyTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/showcase",
				credential,
				ENDPOINT,
				CreateShowcaseRequest.Constant.MODULE,
				CreateShowcaseRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateShowcaseResult.class);

	}


	/**
	 * ショーケースを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteShowcase(DeleteShowcaseRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteShowcaseRequest.Constant.MODULE,
				DeleteShowcaseRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ショーケースの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeShowcaseResult describeShowcase(DescribeShowcaseRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase";

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
				DescribeShowcaseRequest.Constant.MODULE,
				DescribeShowcaseRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeShowcaseResult.class);

	}


	/**
	 * ショーケースを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetShowcaseResult getShowcase(GetShowcaseRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetShowcaseRequest.Constant.MODULE,
				GetShowcaseRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetShowcaseResult.class);

	}


	/**
	 * ショーケースの状態を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetShowcaseStatusResult getShowcaseStatus(GetShowcaseStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "/status";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetShowcaseStatusRequest.Constant.MODULE,
				GetShowcaseStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetShowcaseStatusResult.class);

	}


	/**
	 * ショーケースを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateShowcaseResult updateShowcase(UpdateShowcaseRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getReleaseConditionTriggerScript() != null) body.put("releaseConditionTriggerScript", request.getReleaseConditionTriggerScript());
        if(request.getBuyTriggerScript() != null) body.put("buyTriggerScript", request.getBuyTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/showcase/" + (request.getShowcaseName() == null || request.getShowcaseName().equals("") ? "null" : request.getShowcaseName()) + "",
				credential,
				ENDPOINT,
				UpdateShowcaseRequest.Constant.MODULE,
				UpdateShowcaseRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateShowcaseResult.class);

	}


}