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

package io.gs2.consumableItem;

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
import io.gs2.consumableItem.control.*;

/**
 * GS2 ConsumableItem API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2ConsumableItemClient extends AbstractGs2Client<Gs2ConsumableItemClient> {

	public static String ENDPOINT = "consumable-item";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2ConsumableItemClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2ConsumableItemClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2ConsumableItemClient(IGs2Credential credential, String region) {
		super(credential, region);

	}


	/**
	 * 公開されているアイテムマスタを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetCurrentItemMasterResult getCurrentItemMaster(GetCurrentItemMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/item/master";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetCurrentItemMasterRequest.Constant.MODULE,
				GetCurrentItemMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetCurrentItemMasterResult.class);

	}


	/**
	 * 公開するアイテムマスタを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateCurrentItemMasterResult updateCurrentItemMaster(UpdateCurrentItemMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("settings", request.getSettings());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/item/master",
				credential,
				ENDPOINT,
				UpdateCurrentItemMasterRequest.Constant.MODULE,
				UpdateCurrentItemMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, UpdateCurrentItemMasterResult.class);

	}


	/**
	 * インベントリにアイテムを加えます<br>
	 * <br>
	 * 有効期限に 0 を設定すると有効期限無しになります。<br>
	 * <br>
	 * アイテムに所持数の上限が設定されている状態で、複数個付与することによって<br>
	 * 所持数の上限を超えてしまうケースでは一切付与せずエラー応答を返します。<br>
	 * <br>
	 * 例えば、所持数上限 10 のアイテムで、8個所持しているユーザに 3個 付与しようとすると1個も付与せずにエラーを返します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public AcquisitionItemResult acquisitionItem(AcquisitionItemRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("count", request.getCount());
        if(request.getExpireAt() != null) body.put("expireAt", request.getExpireAt());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/inventory/user/my/item/" + (request.getItemName() == null || request.getItemName().equals("") ? "null" : request.getItemName()) + "",
				credential,
				ENDPOINT,
				AcquisitionItemRequest.Constant.MODULE,
				AcquisitionItemRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, AcquisitionItemResult.class);

	}


	/**
	 * スタンプシートを使用してインベントリにアイテムを加えます<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public AcquisitionItemByStampSheetResult acquisitionItemByStampSheet(AcquisitionItemByStampSheetRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("sheet", request.getSheet())
				.put("keyName", request.getKeyName());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/inventory",
				credential,
				ENDPOINT,
				AcquisitionItemByStampSheetRequest.Constant.MODULE,
				AcquisitionItemByStampSheetRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, AcquisitionItemByStampSheetResult.class);

	}


	/**
	 * インベントリにアイテムを加えます<br>
	 * <br>
	 * 有効期限に 0 を設定すると有効期限無しになります。<br>
	 * <br>
	 * アイテムに所持数の上限が設定されている状態で、複数個付与することによって<br>
	 * 所持数の上限を超えてしまうケースでは一切付与せずエラー応答を返します。<br>
	 * <br>
	 * 例えば、所持数上限 10 のアイテムで、8個所持しているユーザに 3個 付与しようとすると<br>
	 * 1個も付与せずにエラーを返します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public AcquisitionItemByUserIdResult acquisitionItemByUserId(AcquisitionItemByUserIdRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("count", request.getCount());
        if(request.getExpireAt() != null) body.put("expireAt", request.getExpireAt());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/inventory/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/item/" + (request.getItemName() == null || request.getItemName().equals("") ? "null" : request.getItemName()) + "",
				credential,
				ENDPOINT,
				AcquisitionItemByUserIdRequest.Constant.MODULE,
				AcquisitionItemByUserIdRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, AcquisitionItemByUserIdResult.class);

	}


	/**
	 * インベントリのアイテムを消費します<br>
	 * <br>
	 * expireAt を指定しない場合は有効期限内の所有するアイテムの中から有効期限の近いアイテムから消費します。<br>
	 * ただし、この場合有効期限内の所有するアイテムの数量倍クォーターを消費します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ConsumeItemResult consumeItem(ConsumeItemRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("count", request.getCount());
        if(request.getExpireAt() != null) body.put("expireAt", request.getExpireAt());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/inventory/user/my/item/" + (request.getItemName() == null || request.getItemName().equals("") ? "null" : request.getItemName()) + "",
				credential,
				ENDPOINT,
				ConsumeItemRequest.Constant.MODULE,
				ConsumeItemRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        put.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(put, ConsumeItemResult.class);

	}


	/**
	 * スタンプタスクを使用してインベントリのアイテムを消費します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ConsumeItemByStampTaskResult consumeItemByStampTask(ConsumeItemByStampTaskRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("task", request.getTask())
				.put("keyName", request.getKeyName())
				.put("transactionId", request.getTransactionId());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/inventory",
				credential,
				ENDPOINT,
				ConsumeItemByStampTaskRequest.Constant.MODULE,
				ConsumeItemByStampTaskRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        put.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(put, ConsumeItemByStampTaskResult.class);

	}


	/**
	 * インベントリのアイテムを消費します<br>
	 * <br>
	 * expireAt を指定しない場合は有効期限内の所有するアイテムの中から有効期限の近いアイテムから消費します。<br>
	 * ただし、この場合クォーターを有効期限内の所有するアイテムの数量倍消費します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ConsumeItemByUserIdResult consumeItemByUserId(ConsumeItemByUserIdRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("count", request.getCount());
        if(request.getExpireAt() != null) body.put("expireAt", request.getExpireAt());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/inventory/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/item/" + (request.getItemName() == null || request.getItemName().equals("") ? "null" : request.getItemName()) + "",
				credential,
				ENDPOINT,
				ConsumeItemByUserIdRequest.Constant.MODULE,
				ConsumeItemByUserIdRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, ConsumeItemByUserIdResult.class);

	}


	/**
	 * インベントリからアイテムを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteInventoryByUserId(DeleteInventoryByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/inventory/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/item/" + (request.getItemName() == null || request.getItemName().equals("") ? "null" : request.getItemName()) + "/" + (request.getExpireAt() == null || request.getExpireAt().equals("") ? "null" : request.getExpireAt()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteInventoryByUserIdRequest.Constant.MODULE,
				DeleteInventoryByUserIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ユーザが所持しているインベントリの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeInventoryResult describeInventory(DescribeInventoryRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/inventory/user/my";

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
				DescribeInventoryRequest.Constant.MODULE,
				DescribeInventoryRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, DescribeInventoryResult.class);

	}


	/**
	 * ユーザが所持しているインベントリの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeInventoryByUserIdResult describeInventoryByUserId(DescribeInventoryByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/inventory/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "";

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
				DescribeInventoryByUserIdRequest.Constant.MODULE,
				DescribeInventoryByUserIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeInventoryByUserIdResult.class);

	}


	/**
	 * インベントリの内容を取得します<br>
	 * <br>
	 * expireAt を指定しない場合は有効期限内の所有するアイテムの数量を丸めて応答します。<br>
	 * 有効期限には0が設定されて応答されますので、有効期限が存在するかどうかを判別することもできなくなります。<br>
	 * <br>
	 * また、expireAt を指定しない場合は処理時間が expireAt を指定しない場合を指定する場合と比較して長くなります。<br>
	 * 全ての消費型アイテムが有効期限を持たないアイテムで構成される場合は、有効期限に0を設定すると有効期限の無いアイテムとして管理されますので、そちらを利用してください。<br>
	 * <br>
	 * expireAt を指定しない場合は有効期限内の所有するアイテムの数量倍クォーターを消費します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetInventoryResult getInventory(GetInventoryRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/inventory/user/my/item/" + (request.getItemName() == null || request.getItemName().equals("") ? "null" : request.getItemName()) + "";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getExpireAt() != null) queryString.add(new BasicNameValuePair("expireAt", String.valueOf(request.getExpireAt())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetInventoryRequest.Constant.MODULE,
				GetInventoryRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, GetInventoryResult.class);

	}


	/**
	 * インベントリの内容を取得します<br>
	 * <br>
	 * expireAt を指定しない場合は有効期限内の所有するアイテムの数量を丸めて応答します。<br>
	 * 有効期限には0が設定されて応答されますので、有効期限が存在するかどうかを判別することもできなくなります。<br>
	 * <br>
	 * また、expireAt を指定しない場合は処理時間が expireAt を指定しない場合を指定する場合と比較して長くなります。<br>
	 * 全ての消費型アイテムが有効期限を持たないアイテムで構成される場合は、有効期限に0を設定すると有効期限の無いアイテムとして管理されますので、そちらを利用してください。<br>
	 * <br>
	 * expireAt を指定しない場合は有効期限内の所有するアイテムの数量倍クォーターを消費します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetInventoryByUserIdResult getInventoryByUserId(GetInventoryByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/inventory/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/item/" + (request.getItemName() == null || request.getItemName().equals("") ? "null" : request.getItemName()) + "";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getExpireAt() != null) queryString.add(new BasicNameValuePair("expireAt", String.valueOf(request.getExpireAt())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetInventoryByUserIdRequest.Constant.MODULE,
				GetInventoryByUserIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetInventoryByUserIdResult.class);

	}


	/**
	 * 消費型アイテムを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateItemMasterResult createItemMaster(CreateItemMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());
        if(request.getMax() != null) body.put("max", request.getMax());
        if(request.getAcquisitionItemTriggerScript() != null) body.put("acquisitionItemTriggerScript", request.getAcquisitionItemTriggerScript());
        if(request.getAcquisitionItemDoneTriggerScript() != null) body.put("acquisitionItemDoneTriggerScript", request.getAcquisitionItemDoneTriggerScript());
        if(request.getConsumeItemTriggerScript() != null) body.put("consumeItemTriggerScript", request.getConsumeItemTriggerScript());
        if(request.getConsumeItemDoneTriggerScript() != null) body.put("consumeItemDoneTriggerScript", request.getConsumeItemDoneTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/master/item",
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
	 * 消費型アイテムを削除します<br>
	 * <br>
	 * 既にアイテムを所持しているユーザがいる場合、そのアイテムはインベントリから削除されることはありません。<br>
	 * 消費型アイテムを削除することで新しくアイテムを付与することはできなくなりますが、消費することは出来ます。<br>
	 * <br>
	 * これを防ぎたい場合はすべてのユーザのインベントリを走査して該当アイテムを削除する必要があります。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteItemMaster(DeleteItemMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/master/item/" + (request.getItemName() == null || request.getItemName().equals("") ? "null" : request.getItemName()) + "";



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
	 * 消費型アイテムの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeItemMasterResult describeItemMaster(DescribeItemMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/master/item";

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
	 * 消費型アイテムを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetItemMasterResult getItemMaster(GetItemMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/master/item/" + (request.getItemName() == null || request.getItemName().equals("") ? "null" : request.getItemName()) + "";



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
	 * 消費型アイテムを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateItemMasterResult updateItemMaster(UpdateItemMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("max", request.getMax());
        if(request.getAcquisitionItemTriggerScript() != null) body.put("acquisitionItemTriggerScript", request.getAcquisitionItemTriggerScript());
        if(request.getAcquisitionItemDoneTriggerScript() != null) body.put("acquisitionItemDoneTriggerScript", request.getAcquisitionItemDoneTriggerScript());
        if(request.getConsumeItemTriggerScript() != null) body.put("consumeItemTriggerScript", request.getConsumeItemTriggerScript());
        if(request.getConsumeItemDoneTriggerScript() != null) body.put("consumeItemDoneTriggerScript", request.getConsumeItemDoneTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/master/item/" + (request.getItemName() == null || request.getItemName().equals("") ? "null" : request.getItemName()) + "",
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
	 * 消費型アイテムプールを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateItemPoolResult createItemPool(CreateItemPoolRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("serviceClass", request.getServiceClass());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getAcquisitionItemTriggerScript() != null) body.put("acquisitionItemTriggerScript", request.getAcquisitionItemTriggerScript());
        if(request.getAcquisitionItemDoneTriggerScript() != null) body.put("acquisitionItemDoneTriggerScript", request.getAcquisitionItemDoneTriggerScript());
        if(request.getConsumeItemTriggerScript() != null) body.put("consumeItemTriggerScript", request.getConsumeItemTriggerScript());
        if(request.getConsumeItemDoneTriggerScript() != null) body.put("consumeItemDoneTriggerScript", request.getConsumeItemDoneTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/itemPool",
				credential,
				ENDPOINT,
				CreateItemPoolRequest.Constant.MODULE,
				CreateItemPoolRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateItemPoolResult.class);

	}


	/**
	 * 消費型アイテムプールを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteItemPool(DeleteItemPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteItemPoolRequest.Constant.MODULE,
				DeleteItemPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 消費型アイテムプールの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeItemPoolResult describeItemPool(DescribeItemPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool";

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
				DescribeItemPoolRequest.Constant.MODULE,
				DescribeItemPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeItemPoolResult.class);

	}


	/**
	 * サービスクラスの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeServiceClassResult describeServiceClass(DescribeServiceClassRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/serviceClass";



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
	 * 消費型アイテムプールを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetItemPoolResult getItemPool(GetItemPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetItemPoolRequest.Constant.MODULE,
				GetItemPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetItemPoolResult.class);

	}


	/**
	 * 消費型アイテムプールの状態を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetItemPoolStatusResult getItemPoolStatus(GetItemPoolStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/status";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetItemPoolStatusRequest.Constant.MODULE,
				GetItemPoolStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetItemPoolStatusResult.class);

	}


	/**
	 * 消費型アイテムプールを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateItemPoolResult updateItemPool(UpdateItemPoolRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("serviceClass", request.getServiceClass());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getAcquisitionItemTriggerScript() != null) body.put("acquisitionItemTriggerScript", request.getAcquisitionItemTriggerScript());
        if(request.getAcquisitionItemDoneTriggerScript() != null) body.put("acquisitionItemDoneTriggerScript", request.getAcquisitionItemDoneTriggerScript());
        if(request.getConsumeItemTriggerScript() != null) body.put("consumeItemTriggerScript", request.getConsumeItemTriggerScript());
        if(request.getConsumeItemDoneTriggerScript() != null) body.put("consumeItemDoneTriggerScript", request.getConsumeItemDoneTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "",
				credential,
				ENDPOINT,
				UpdateItemPoolRequest.Constant.MODULE,
				UpdateItemPoolRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateItemPoolResult.class);

	}


	/**
	 * アイテムマスターデータをエクスポートする<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ExportMasterResult exportMaster(ExportMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/itemPool/" + (request.getItemPoolName() == null || request.getItemPoolName().equals("") ? "null" : request.getItemPoolName()) + "/master";



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


}