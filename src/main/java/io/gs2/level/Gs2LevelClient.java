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

package io.gs2.level;

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
import io.gs2.level.control.*;

/**
 * GS2 Level API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2LevelClient extends AbstractGs2Client<Gs2LevelClient> {

	public static String ENDPOINT = "level";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2LevelClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2LevelClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2LevelClient(IGs2Credential credential, String region) {
		super(credential, region);

	}


	/**
	 * レベルマスタを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetCurrentLevelMasterResult getCurrentLevelMaster(GetCurrentLevelMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/status/master";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetCurrentLevelMasterRequest.Constant.MODULE,
				GetCurrentLevelMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetCurrentLevelMasterResult.class);

	}


	/**
	 * レベルマスタを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateCurrentLevelMasterResult updateCurrentLevelMaster(UpdateCurrentLevelMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("settings", request.getSettings());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/status/master",
				credential,
				ENDPOINT,
				UpdateCurrentLevelMasterRequest.Constant.MODULE,
				UpdateCurrentLevelMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, UpdateCurrentLevelMasterResult.class);

	}


	/**
	 * レベルテーブルを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateLevelTableMasterResult createLevelTableMaster(CreateLevelTableMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master/levelTable",
				credential,
				ENDPOINT,
				CreateLevelTableMasterRequest.Constant.MODULE,
				CreateLevelTableMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateLevelTableMasterResult.class);

	}


	/**
	 * レベルテーブルを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteLevelTableMaster(DeleteLevelTableMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master/levelTable/" + (request.getLevelTableName() == null || request.getLevelTableName().equals("") ? "null" : request.getLevelTableName()) + "";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteLevelTableMasterRequest.Constant.MODULE,
				DeleteLevelTableMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * レベルテーブルの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeLevelTableMasterResult describeLevelTableMaster(DescribeLevelTableMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master/levelTable";

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
				DescribeLevelTableMasterRequest.Constant.MODULE,
				DescribeLevelTableMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeLevelTableMasterResult.class);

	}


	/**
	 * レベルテーブルを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetLevelTableMasterResult getLevelTableMaster(GetLevelTableMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master/levelTable/" + (request.getLevelTableName() == null || request.getLevelTableName().equals("") ? "null" : request.getLevelTableName()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetLevelTableMasterRequest.Constant.MODULE,
				GetLevelTableMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetLevelTableMasterResult.class);

	}


	/**
	 * レベルアップ経験値閾値を新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateLevelThresholdMasterResult createLevelThresholdMaster(CreateLevelThresholdMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("threshold", request.getThreshold());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master/levelTable/" + (request.getLevelTableName() == null || request.getLevelTableName().equals("") ? "null" : request.getLevelTableName()) + "/threshold",
				credential,
				ENDPOINT,
				CreateLevelThresholdMasterRequest.Constant.MODULE,
				CreateLevelThresholdMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateLevelThresholdMasterResult.class);

	}


	/**
	 * レベルアップ経験値閾値を削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteLevelThresholdMaster(DeleteLevelThresholdMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master/levelTable/" + (request.getLevelTableName() == null || request.getLevelTableName().equals("") ? "null" : request.getLevelTableName()) + "/threshold/" + (request.getThreshold() == null || request.getThreshold().equals("") ? "null" : request.getThreshold()) + "";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteLevelThresholdMasterRequest.Constant.MODULE,
				DeleteLevelThresholdMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * レベルアップ経験値閾値の一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeLevelThresholdMasterResult describeLevelThresholdMaster(DescribeLevelThresholdMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master/levelTable/" + (request.getLevelTableName() == null || request.getLevelTableName().equals("") ? "null" : request.getLevelTableName()) + "/threshold";

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
				DescribeLevelThresholdMasterRequest.Constant.MODULE,
				DescribeLevelThresholdMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeLevelThresholdMasterResult.class);

	}


	/**
	 * レベルアップ経験値閾値を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetLevelThresholdMasterResult getLevelThresholdMaster(GetLevelThresholdMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master/levelTable/" + (request.getLevelTableName() == null || request.getLevelTableName().equals("") ? "null" : request.getLevelTableName()) + "/threshold/" + (request.getThreshold() == null || request.getThreshold().equals("") ? "null" : request.getThreshold()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetLevelThresholdMasterRequest.Constant.MODULE,
				GetLevelThresholdMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetLevelThresholdMasterResult.class);

	}


	/**
	 * レベルマスターデータをエクスポートする<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ExportMasterResult exportMaster(ExportMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master";


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
	 * リソースプールを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateResourcePoolResult createResourcePool(CreateResourcePoolRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("serviceClass", request.getServiceClass());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getLevelCapScript() != null) body.put("levelCapScript", request.getLevelCapScript());
        if(request.getChangeExperienceTriggerScript() != null) body.put("changeExperienceTriggerScript", request.getChangeExperienceTriggerScript());
        if(request.getChangeExperienceDoneTriggerScript() != null) body.put("changeExperienceDoneTriggerScript", request.getChangeExperienceDoneTriggerScript());
        if(request.getChangeLevelTriggerScript() != null) body.put("changeLevelTriggerScript", request.getChangeLevelTriggerScript());
        if(request.getChangeLevelDoneTriggerScript() != null) body.put("changeLevelDoneTriggerScript", request.getChangeLevelDoneTriggerScript());
        if(request.getChangeLevelCapTriggerScript() != null) body.put("changeLevelCapTriggerScript", request.getChangeLevelCapTriggerScript());
        if(request.getChangeLevelCapDoneTriggerScript() != null) body.put("changeLevelCapDoneTriggerScript", request.getChangeLevelCapDoneTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/resourcePool",
				credential,
				ENDPOINT,
				CreateResourcePoolRequest.Constant.MODULE,
				CreateResourcePoolRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateResourcePoolResult.class);

	}


	/**
	 * リソースプールを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteResourcePool(DeleteResourcePoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteResourcePoolRequest.Constant.MODULE,
				DeleteResourcePoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * リソースプールの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeResourcePoolResult describeResourcePool(DescribeResourcePoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool";

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
				DescribeResourcePoolRequest.Constant.MODULE,
				DescribeResourcePoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeResourcePoolResult.class);

	}


	/**
	 * リソースプールを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetResourcePoolResult getResourcePool(GetResourcePoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetResourcePoolRequest.Constant.MODULE,
				GetResourcePoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetResourcePoolResult.class);

	}


	/**
	 * リソースプールの状態を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetResourcePoolStatusResult getResourcePoolStatus(GetResourcePoolStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/status";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetResourcePoolStatusRequest.Constant.MODULE,
				GetResourcePoolStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetResourcePoolStatusResult.class);

	}


	/**
	 * リソースプールを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateResourcePoolResult updateResourcePool(UpdateResourcePoolRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("serviceClass", request.getServiceClass());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getLevelCapScript() != null) body.put("levelCapScript", request.getLevelCapScript());
        if(request.getChangeExperienceTriggerScript() != null) body.put("changeExperienceTriggerScript", request.getChangeExperienceTriggerScript());
        if(request.getChangeExperienceDoneTriggerScript() != null) body.put("changeExperienceDoneTriggerScript", request.getChangeExperienceDoneTriggerScript());
        if(request.getChangeLevelTriggerScript() != null) body.put("changeLevelTriggerScript", request.getChangeLevelTriggerScript());
        if(request.getChangeLevelDoneTriggerScript() != null) body.put("changeLevelDoneTriggerScript", request.getChangeLevelDoneTriggerScript());
        if(request.getChangeLevelCapTriggerScript() != null) body.put("changeLevelCapTriggerScript", request.getChangeLevelCapTriggerScript());
        if(request.getChangeLevelCapDoneTriggerScript() != null) body.put("changeLevelCapDoneTriggerScript", request.getChangeLevelCapDoneTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "",
				credential,
				ENDPOINT,
				UpdateResourcePoolRequest.Constant.MODULE,
				UpdateResourcePoolRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateResourcePoolResult.class);

	}


	/**
	 * リソースタイプを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateResourceTypeMasterResult createResourceTypeMaster(CreateResourceTypeMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("levelTableName", request.getLevelTableName())
				.put("defaultLevelCap", request.getDefaultLevelCap())
				.put("maxLevelCap", request.getMaxLevelCap());
        if(request.getMeta() != null) body.put("meta", request.getMeta());
        if(request.getDefaultExperience() != null) body.put("defaultExperience", request.getDefaultExperience());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master/resourceType",
				credential,
				ENDPOINT,
				CreateResourceTypeMasterRequest.Constant.MODULE,
				CreateResourceTypeMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateResourceTypeMasterResult.class);

	}


	/**
	 * リソースタイプを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteResourceTypeMaster(DeleteResourceTypeMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master/resourceType/" + (request.getResourceTypeName() == null || request.getResourceTypeName().equals("") ? "null" : request.getResourceTypeName()) + "";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteResourceTypeMasterRequest.Constant.MODULE,
				DeleteResourceTypeMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * リソースタイプの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeResourceTypeMasterResult describeResourceTypeMaster(DescribeResourceTypeMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master/resourceType";

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
				DescribeResourceTypeMasterRequest.Constant.MODULE,
				DescribeResourceTypeMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeResourceTypeMasterResult.class);

	}


	/**
	 * リソースタイプを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetResourceTypeMasterResult getResourceTypeMaster(GetResourceTypeMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master/resourceType/" + (request.getResourceTypeName() == null || request.getResourceTypeName().equals("") ? "null" : request.getResourceTypeName()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetResourceTypeMasterRequest.Constant.MODULE,
				GetResourceTypeMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetResourceTypeMasterResult.class);

	}


	/**
	 * リソースタイプを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateResourceTypeMasterResult updateResourceTypeMaster(UpdateResourceTypeMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("levelTableName", request.getLevelTableName())
				.put("defaultLevelCap", request.getDefaultLevelCap())
				.put("maxLevelCap", request.getMaxLevelCap());
        if(request.getMeta() != null) body.put("meta", request.getMeta());
        if(request.getDefaultExperience() != null) body.put("defaultExperience", request.getDefaultExperience());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/master/resourceType/" + (request.getResourceTypeName() == null || request.getResourceTypeName().equals("") ? "null" : request.getResourceTypeName()) + "",
				credential,
				ENDPOINT,
				UpdateResourceTypeMasterRequest.Constant.MODULE,
				UpdateResourceTypeMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateResourceTypeMasterResult.class);

	}


	/**
	 * スタンプシートを使用してレベルキャップを変更します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ChangeLevelCapByStampSheetResult changeLevelCapByStampSheet(ChangeLevelCapByStampSheetRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("sheet", request.getSheet())
				.put("keyName", request.getKeyName());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/status/levelCap",
				credential,
				ENDPOINT,
				ChangeLevelCapByStampSheetRequest.Constant.MODULE,
				ChangeLevelCapByStampSheetRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, ChangeLevelCapByStampSheetResult.class);

	}


	/**
	 * レベルキャップを変更します<br>
	 * <br>
	 * 消費クォーター: 5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ChangeLevelCapByUserIdResult changeLevelCapByUserId(ChangeLevelCapByUserIdRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("levelCap", request.getLevelCap());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/status/" + (request.getStatusId() == null || request.getStatusId().equals("") ? "null" : request.getStatusId()) + "/levelCap",
				credential,
				ENDPOINT,
				ChangeLevelCapByUserIdRequest.Constant.MODULE,
				ChangeLevelCapByUserIdRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, ChangeLevelCapByUserIdResult.class);

	}


	/**
	 * レベルキャップを変更します<br>
	 * <br>
	 * 消費クォーター: 5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ChangeLevelCapByUserIdAndResourceTypeResult changeLevelCapByUserIdAndResourceType(ChangeLevelCapByUserIdAndResourceTypeRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("levelCap", request.getLevelCap());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/status/resourceType/" + (request.getResourceTypeName() == null || request.getResourceTypeName().equals("") ? "null" : request.getResourceTypeName()) + "/" + (request.getResourceId() == null || request.getResourceId().equals("") ? "null" : request.getResourceId()) + "/levelCap",
				credential,
				ENDPOINT,
				ChangeLevelCapByUserIdAndResourceTypeRequest.Constant.MODULE,
				ChangeLevelCapByUserIdAndResourceTypeRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, ChangeLevelCapByUserIdAndResourceTypeResult.class);

	}


	/**
	 * ステータス一覧を取得します<br>
	 * <br>
	 * 本APIは statusIds に取得するリソースIDのリストを指定出来ます。<br>
	 * リソースIDリストを指定しない場合は全てのリソースのステータスを応答しますが、IDを指定する場合と比較して2倍のクォーターを消費します。<br>
	 * ステータスを取得する段階でリソースIDが明らかな場合はリソースIDのリストを指定することをお勧めします。<br>
	 * <br>
	 * 消費クォーター: 取得件数 × 3(リソースIDを省略した場合は2倍)<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeStatusResult describeStatus(DescribeStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/user/my/status";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getStatusIds() != null) queryString.add(new BasicNameValuePair("statusIds", toString(request.getStatusIds())));
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeStatusRequest.Constant.MODULE,
				DescribeStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, DescribeStatusResult.class);

	}


	/**
	 * ステータスを一覧を取得します<br>
	 * <br>
	 * 本APIは statusIds に取得するリソースIDのリストを指定出来ます。<br>
	 * リソースIDリストを指定しない場合は全てのリソースのステータスを応答しますが、IDを指定する場合と比較して2倍のクォーターを消費します。<br>
	 * ステータスを取得する段階でリソースIDが明らかな場合はリソースIDのリストを指定することをお勧めします。<br>
	 * <br>
	 * 消費クォーター: 取得件数 × 3(リソースIDを省略した場合は2倍)<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeStatusByUserIdResult describeStatusByUserId(DescribeStatusByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/status";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getStatusIds() != null) queryString.add(new BasicNameValuePair("statusIds", toString(request.getStatusIds())));
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));

		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeStatusByUserIdRequest.Constant.MODULE,
				DescribeStatusByUserIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeStatusByUserIdResult.class);

	}


	/**
	 * ステータスを取得します<br>
	 * <br>
	 * 消費クォーター: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetStatusResult getStatus(GetStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/user/my/status/" + (request.getStatusId() == null || request.getStatusId().equals("") ? "null" : request.getStatusId()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetStatusRequest.Constant.MODULE,
				GetStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, GetStatusResult.class);

	}


	/**
	 * ステータスを取得します<br>
	 * <br>
	 * 消費クォーター: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetStatusByResourceTypeResult getStatusByResourceType(GetStatusByResourceTypeRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/user/my/status/resourceType/" + (request.getResourceTypeName() == null || request.getResourceTypeName().equals("") ? "null" : request.getResourceTypeName()) + "/" + (request.getResourceId() == null || request.getResourceId().equals("") ? "null" : request.getResourceId()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetStatusByResourceTypeRequest.Constant.MODULE,
				GetStatusByResourceTypeRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, GetStatusByResourceTypeResult.class);

	}


	/**
	 * ステータスを取得します<br>
	 * <br>
	 * 消費クォーター: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetStatusByUserIdResult getStatusByUserId(GetStatusByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/status/" + (request.getStatusId() == null || request.getStatusId().equals("") ? "null" : request.getStatusId()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetStatusByUserIdRequest.Constant.MODULE,
				GetStatusByUserIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetStatusByUserIdResult.class);

	}


	/**
	 * ステータスを取得します<br>
	 * <br>
	 * 消費クォーター: 3<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetStatusByUserIdAndResourceTypeResult getStatusByUserIdAndResourceType(GetStatusByUserIdAndResourceTypeRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/status/resourceType/" + (request.getResourceTypeName() == null || request.getResourceTypeName().equals("") ? "null" : request.getResourceTypeName()) + "/" + (request.getResourceId() == null || request.getResourceId().equals("") ? "null" : request.getResourceId()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetStatusByUserIdAndResourceTypeRequest.Constant.MODULE,
				GetStatusByUserIdAndResourceTypeRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetStatusByUserIdAndResourceTypeResult.class);

	}


	/**
	 * スタンプシートを使用して経験値を加算します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public IncreaseExperienceByStampSheetResult increaseExperienceByStampSheet(IncreaseExperienceByStampSheetRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("sheet", request.getSheet())
				.put("keyName", request.getKeyName());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/status",
				credential,
				ENDPOINT,
				IncreaseExperienceByStampSheetRequest.Constant.MODULE,
				IncreaseExperienceByStampSheetRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, IncreaseExperienceByStampSheetResult.class);

	}


	/**
	 * 経験値を加算します<br>
	 * <br>
	 * 消費クォーター: 5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public IncreaseExperienceByUserIdResult increaseExperienceByUserId(IncreaseExperienceByUserIdRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("value", request.getValue());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/status/" + (request.getStatusId() == null || request.getStatusId().equals("") ? "null" : request.getStatusId()) + "",
				credential,
				ENDPOINT,
				IncreaseExperienceByUserIdRequest.Constant.MODULE,
				IncreaseExperienceByUserIdRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, IncreaseExperienceByUserIdResult.class);

	}


	/**
	 * 経験値を加算します<br>
	 * <br>
	 * 消費クォーター: 5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public IncreaseExperienceByUserIdAndResourceTypeResult increaseExperienceByUserIdAndResourceType(IncreaseExperienceByUserIdAndResourceTypeRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("value", request.getValue());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/status/resourceType/" + (request.getResourceTypeName() == null || request.getResourceTypeName().equals("") ? "null" : request.getResourceTypeName()) + "/" + (request.getResourceId() == null || request.getResourceId().equals("") ? "null" : request.getResourceId()) + "",
				credential,
				ENDPOINT,
				IncreaseExperienceByUserIdAndResourceTypeRequest.Constant.MODULE,
				IncreaseExperienceByUserIdAndResourceTypeRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, IncreaseExperienceByUserIdAndResourceTypeResult.class);

	}


	/**
	 * 経験値を設定します<br>
	 * <br>
	 * 消費クォーター: 5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public SetExperienceByUserIdResult setExperienceByUserId(SetExperienceByUserIdRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("experience", request.getExperience());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/status/" + (request.getStatusId() == null || request.getStatusId().equals("") ? "null" : request.getStatusId()) + "",
				credential,
				ENDPOINT,
				SetExperienceByUserIdRequest.Constant.MODULE,
				SetExperienceByUserIdRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, SetExperienceByUserIdResult.class);

	}


	/**
	 * 経験値を設定します<br>
	 * <br>
	 * 消費クォーター: 5<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public SetExperienceByUserIdAndResourceTypeResult setExperienceByUserIdAndResourceType(SetExperienceByUserIdAndResourceTypeRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("experience", request.getExperience());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/resourcePool/" + (request.getResourcePoolName() == null || request.getResourcePoolName().equals("") ? "null" : request.getResourcePoolName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/status/resourceType/" + (request.getResourceTypeName() == null || request.getResourceTypeName().equals("") ? "null" : request.getResourceTypeName()) + "/" + (request.getResourceId() == null || request.getResourceId().equals("") ? "null" : request.getResourceId()) + "",
				credential,
				ENDPOINT,
				SetExperienceByUserIdAndResourceTypeRequest.Constant.MODULE,
				SetExperienceByUserIdAndResourceTypeRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, SetExperienceByUserIdAndResourceTypeResult.class);

	}


}