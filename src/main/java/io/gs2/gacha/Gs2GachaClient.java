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

package io.gs2.gacha;

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
import io.gs2.gacha.control.*;

/**
 * GS2 Gacha API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2GachaClient extends AbstractGs2Client<Gs2GachaClient> {

	public static String ENDPOINT = "gacha";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2GachaClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2GachaClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2GachaClient(IGs2Credential credential, String region) {
		super(credential, region);

	}


	/**
	 * 公開されているガチャマスタを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetCurrentGachaMasterResult getCurrentGachaMaster(GetCurrentGachaMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/gacha/master";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetCurrentGachaMasterRequest.Constant.MODULE,
				GetCurrentGachaMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetCurrentGachaMasterResult.class);

	}


	/**
	 * 公開するガチャマスタを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateCurrentGachaMasterResult updateCurrentGachaMaster(UpdateCurrentGachaMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("settings", request.getSettings());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/gacha/master",
				credential,
				ENDPOINT,
				UpdateCurrentGachaMasterRequest.Constant.MODULE,
				UpdateCurrentGachaMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, UpdateCurrentGachaMasterResult.class);

	}


	/**
	 * ガチャを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateGachaMasterResult createGachaMaster(CreateGachaMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());
        if(request.getMeta() != null) body.put("meta", request.getMeta());
        if(request.getPrizeJobQueueName() != null) body.put("prizeJobQueueName", request.getPrizeJobQueueName());
        if(request.getPrizeJobQueueScriptName() != null) body.put("prizeJobQueueScriptName", request.getPrizeJobQueueScriptName());
        if(request.getPrizeTableNames() != null) {
            List<JsonNode> node = new ArrayList<>();
            for(String item : request.getPrizeTableNames()) {
                node.add(JsonNodeFactory.instance.textNode(item));
            }
            body.set("prizeTableNames", JsonNodeFactory.instance.arrayNode().addAll(node));
		}

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/gacha",
				credential,
				ENDPOINT,
				CreateGachaMasterRequest.Constant.MODULE,
				CreateGachaMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateGachaMasterResult.class);

	}


	/**
	 * ガチャを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteGachaMaster(DeleteGachaMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/gacha/" + (request.getGachaName() == null || request.getGachaName().equals("") ? "null" : request.getGachaName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteGachaMasterRequest.Constant.MODULE,
				DeleteGachaMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ガチャの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeGachaMasterResult describeGachaMaster(DescribeGachaMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/gacha";

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
				DescribeGachaMasterRequest.Constant.MODULE,
				DescribeGachaMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeGachaMasterResult.class);

	}


	/**
	 * ガチャを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetGachaMasterResult getGachaMaster(GetGachaMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/gacha/" + (request.getGachaName() == null || request.getGachaName().equals("") ? "null" : request.getGachaName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetGachaMasterRequest.Constant.MODULE,
				GetGachaMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetGachaMasterResult.class);

	}


	/**
	 * ガチャを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateGachaMasterResult updateGachaMaster(UpdateGachaMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getMeta() != null) body.put("meta", request.getMeta());
        if(request.getPrizeJobQueueName() != null) body.put("prizeJobQueueName", request.getPrizeJobQueueName());
        if(request.getPrizeJobQueueScriptName() != null) body.put("prizeJobQueueScriptName", request.getPrizeJobQueueScriptName());
        if(request.getPrizeTableNames() != null) {
            List<JsonNode> node = new ArrayList<>();
            for(String item : request.getPrizeTableNames()) {
                node.add(JsonNodeFactory.instance.textNode(item));
            }
            body.set("prizeTableNames", JsonNodeFactory.instance.arrayNode().addAll(node));
		}
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/gacha/" + (request.getGachaName() == null || request.getGachaName().equals("") ? "null" : request.getGachaName()) + "",
				credential,
				ENDPOINT,
				UpdateGachaMasterRequest.Constant.MODULE,
				UpdateGachaMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateGachaMasterResult.class);

	}


	/**
	 * ガチャプールを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateGachaPoolResult createGachaPool(CreateGachaPoolRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getPublicDrawRate() != null) body.put("publicDrawRate", request.getPublicDrawRate());
        if(request.getAllowAccessGachaInfo() != null) body.put("allowAccessGachaInfo", request.getAllowAccessGachaInfo());
        if(request.getRestrict() != null) body.put("restrict", request.getRestrict());
        if(request.getDrawPrizeTriggerScript() != null) body.put("drawPrizeTriggerScript", request.getDrawPrizeTriggerScript());
        if(request.getDrawPrizeDoneTriggerScript() != null) body.put("drawPrizeDoneTriggerScript", request.getDrawPrizeDoneTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/gachaPool",
				credential,
				ENDPOINT,
				CreateGachaPoolRequest.Constant.MODULE,
				CreateGachaPoolRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateGachaPoolResult.class);

	}


	/**
	 * ガチャプールを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteGachaPool(DeleteGachaPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteGachaPoolRequest.Constant.MODULE,
				DeleteGachaPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ガチャプールの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeGachaPoolResult describeGachaPool(DescribeGachaPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool";

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
				DescribeGachaPoolRequest.Constant.MODULE,
				DescribeGachaPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeGachaPoolResult.class);

	}


	/**
	 * ガチャプールを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetGachaPoolResult getGachaPool(GetGachaPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetGachaPoolRequest.Constant.MODULE,
				GetGachaPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetGachaPoolResult.class);

	}


	/**
	 * ガチャプールの状態を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetGachaPoolStatusResult getGachaPoolStatus(GetGachaPoolStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/status";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetGachaPoolStatusRequest.Constant.MODULE,
				GetGachaPoolStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetGachaPoolStatusResult.class);

	}


	/**
	 * ガチャプールを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateGachaPoolResult updateGachaPool(UpdateGachaPoolRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getPublicDrawRate() != null) body.put("publicDrawRate", request.getPublicDrawRate());
        if(request.getAllowAccessGachaInfo() != null) body.put("allowAccessGachaInfo", request.getAllowAccessGachaInfo());
        if(request.getRestrict() != null) body.put("restrict", request.getRestrict());
        if(request.getDrawPrizeTriggerScript() != null) body.put("drawPrizeTriggerScript", request.getDrawPrizeTriggerScript());
        if(request.getDrawPrizeDoneTriggerScript() != null) body.put("drawPrizeDoneTriggerScript", request.getDrawPrizeDoneTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "",
				credential,
				ENDPOINT,
				UpdateGachaPoolRequest.Constant.MODULE,
				UpdateGachaPoolRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateGachaPoolResult.class);

	}


	/**
	 * 公開されているガチャ一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeGachaResult describeGacha(DescribeGachaRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/gacha";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeGachaRequest.Constant.MODULE,
				DescribeGachaRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeGachaResult.class);

	}


	/**
	 * リソース毎のガチャの排出確率を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DetailProbabilityResult detailProbability(DetailProbabilityRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/gacha/" + (request.getGachaName() == null || request.getGachaName().equals("") ? "null" : request.getGachaName()) + "/probability/" + (request.getDrawTime() == null || request.getDrawTime().equals("") ? "null" : request.getDrawTime()) + "/detail";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DetailProbabilityRequest.Constant.MODULE,
				DetailProbabilityRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DetailProbabilityResult.class);

	}


	/**
	 * ガチャを実行します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DoGachaResult doGacha(DoGachaRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getContext() != null) body.put("context", request.getContext());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/gacha/" + (request.getGachaName() == null || request.getGachaName().equals("") ? "null" : request.getGachaName()) + "",
				credential,
				ENDPOINT,
				DoGachaRequest.Constant.MODULE,
				DoGachaRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, DoGachaResult.class);

	}


	/**
	 * スタンプシートを使用してガチャを実行します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DoGachaByStampSheetResult doGachaByStampSheet(DoGachaByStampSheetRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("sheet", request.getSheet())
				.put("keyName", request.getKeyName());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/gacha",
				credential,
				ENDPOINT,
				DoGachaByStampSheetRequest.Constant.MODULE,
				DoGachaByStampSheetRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, DoGachaByStampSheetResult.class);

	}


	/**
	 * 公開されているガチャを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetGachaResult getGacha(GetGachaRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/gacha/" + (request.getGachaName() == null || request.getGachaName().equals("") ? "null" : request.getGachaName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetGachaRequest.Constant.MODULE,
				GetGachaRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetGachaResult.class);

	}


	/**
	 * レアリティ毎のガチャの排出確率を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetProbabilityResult getProbability(GetProbabilityRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/gacha/" + (request.getGachaName() == null || request.getGachaName().equals("") ? "null" : request.getGachaName()) + "/probability/" + (request.getDrawTime() == null || request.getDrawTime().equals("") ? "null" : request.getDrawTime()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetProbabilityRequest.Constant.MODULE,
				GetProbabilityRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetProbabilityResult.class);

	}


	/**
	 * ガチャマスターデータをエクスポートする<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ExportMasterResult exportMaster(ExportMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master";



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
	 * 景品を新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreatePrizeMasterResult createPrizeMaster(CreatePrizeMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("resourceId", request.getResourceId())
				.put("weight", request.getWeight());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable/" + (request.getPrizeTableName() == null || request.getPrizeTableName().equals("") ? "null" : request.getPrizeTableName()) + "/rarity/" + (request.getRarityName() == null || request.getRarityName().equals("") ? "null" : request.getRarityName()) + "/prize",
				credential,
				ENDPOINT,
				CreatePrizeMasterRequest.Constant.MODULE,
				CreatePrizeMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreatePrizeMasterResult.class);

	}


	/**
	 * 景品を削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deletePrizeMaster(DeletePrizeMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable/" + (request.getPrizeTableName() == null || request.getPrizeTableName().equals("") ? "null" : request.getPrizeTableName()) + "/rarity/" + (request.getRarityName() == null || request.getRarityName().equals("") ? "null" : request.getRarityName()) + "/prize/" + (request.getResourceId() == null || request.getResourceId().equals("") ? "null" : request.getResourceId()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeletePrizeMasterRequest.Constant.MODULE,
				DeletePrizeMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 景品の一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribePrizeMasterResult describePrizeMaster(DescribePrizeMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable/" + (request.getPrizeTableName() == null || request.getPrizeTableName().equals("") ? "null" : request.getPrizeTableName()) + "/rarity/" + (request.getRarityName() == null || request.getRarityName().equals("") ? "null" : request.getRarityName()) + "/prize";

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
				DescribePrizeMasterRequest.Constant.MODULE,
				DescribePrizeMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribePrizeMasterResult.class);

	}


	/**
	 * 景品を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetPrizeMasterResult getPrizeMaster(GetPrizeMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable/" + (request.getPrizeTableName() == null || request.getPrizeTableName().equals("") ? "null" : request.getPrizeTableName()) + "/rarity/" + (request.getRarityName() == null || request.getRarityName().equals("") ? "null" : request.getRarityName()) + "/prize/" + (request.getResourceId() == null || request.getResourceId().equals("") ? "null" : request.getResourceId()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetPrizeMasterRequest.Constant.MODULE,
				GetPrizeMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetPrizeMasterResult.class);

	}


	/**
	 * 景品を更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdatePrizeMasterResult updatePrizeMaster(UpdatePrizeMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("weight", request.getWeight());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable/" + (request.getPrizeTableName() == null || request.getPrizeTableName().equals("") ? "null" : request.getPrizeTableName()) + "/rarity/" + (request.getRarityName() == null || request.getRarityName().equals("") ? "null" : request.getRarityName()) + "/prize/" + (request.getResourceId() == null || request.getResourceId().equals("") ? "null" : request.getResourceId()) + "",
				credential,
				ENDPOINT,
				UpdatePrizeMasterRequest.Constant.MODULE,
				UpdatePrizeMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdatePrizeMasterResult.class);

	}


	/**
	 * 排出確率テーブルを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreatePrizeTableMasterResult createPrizeTableMaster(CreatePrizeTableMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable",
				credential,
				ENDPOINT,
				CreatePrizeTableMasterRequest.Constant.MODULE,
				CreatePrizeTableMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreatePrizeTableMasterResult.class);

	}


	/**
	 * 排出確率テーブルを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deletePrizeTableMaster(DeletePrizeTableMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable/" + (request.getPrizeTableName() == null || request.getPrizeTableName().equals("") ? "null" : request.getPrizeTableName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeletePrizeTableMasterRequest.Constant.MODULE,
				DeletePrizeTableMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 排出確率テーブルの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribePrizeTableMasterResult describePrizeTableMaster(DescribePrizeTableMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable";

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
				DescribePrizeTableMasterRequest.Constant.MODULE,
				DescribePrizeTableMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribePrizeTableMasterResult.class);

	}


	/**
	 * 排出確率テーブルを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetPrizeTableMasterResult getPrizeTableMaster(GetPrizeTableMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable/" + (request.getPrizeTableName() == null || request.getPrizeTableName().equals("") ? "null" : request.getPrizeTableName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetPrizeTableMasterRequest.Constant.MODULE,
				GetPrizeTableMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetPrizeTableMasterResult.class);

	}


	/**
	 * 景品レアリティを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateRarityMasterResult createRarityMaster(CreateRarityMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("weight", request.getWeight());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable/" + (request.getPrizeTableName() == null || request.getPrizeTableName().equals("") ? "null" : request.getPrizeTableName()) + "/rarity",
				credential,
				ENDPOINT,
				CreateRarityMasterRequest.Constant.MODULE,
				CreateRarityMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateRarityMasterResult.class);

	}


	/**
	 * 景品レアリティを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteRarityMaster(DeleteRarityMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable/" + (request.getPrizeTableName() == null || request.getPrizeTableName().equals("") ? "null" : request.getPrizeTableName()) + "/rarity/" + (request.getRarityName() == null || request.getRarityName().equals("") ? "null" : request.getRarityName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteRarityMasterRequest.Constant.MODULE,
				DeleteRarityMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 景品レアリティの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeRarityMasterResult describeRarityMaster(DescribeRarityMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable/" + (request.getPrizeTableName() == null || request.getPrizeTableName().equals("") ? "null" : request.getPrizeTableName()) + "/rarity";

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
				DescribeRarityMasterRequest.Constant.MODULE,
				DescribeRarityMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeRarityMasterResult.class);

	}


	/**
	 * 景品レアリティを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetRarityMasterResult getRarityMaster(GetRarityMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable/" + (request.getPrizeTableName() == null || request.getPrizeTableName().equals("") ? "null" : request.getPrizeTableName()) + "/rarity/" + (request.getRarityName() == null || request.getRarityName().equals("") ? "null" : request.getRarityName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetRarityMasterRequest.Constant.MODULE,
				GetRarityMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetRarityMasterResult.class);

	}


	/**
	 * 景品レアリティを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateRarityMasterResult updateRarityMaster(UpdateRarityMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("weight", request.getWeight());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/gachaPool/" + (request.getGachaPoolName() == null || request.getGachaPoolName().equals("") ? "null" : request.getGachaPoolName()) + "/master/prizeTable/" + (request.getPrizeTableName() == null || request.getPrizeTableName().equals("") ? "null" : request.getPrizeTableName()) + "/rarity/" + (request.getRarityName() == null || request.getRarityName().equals("") ? "null" : request.getRarityName()) + "",
				credential,
				ENDPOINT,
				UpdateRarityMasterRequest.Constant.MODULE,
				UpdateRarityMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateRarityMasterResult.class);

	}


}