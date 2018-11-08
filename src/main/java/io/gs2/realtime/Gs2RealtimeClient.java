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

package io.gs2.realtime;

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
import io.gs2.realtime.control.*;

/**
 * GS2 Realtime API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2RealtimeClient extends AbstractGs2Client<Gs2RealtimeClient> {

	public static String ENDPOINT = "realtime";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2RealtimeClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2RealtimeClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2RealtimeClient(IGs2Credential credential, String region) {
		super(credential, region);

	}


	/**
	 * ギャザリングプールを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateGatheringPoolResult createGatheringPool(CreateGatheringPoolRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());
        if(request.getDescription() != null) body.put("description", request.getDescription());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/gatheringPool",
				credential,
				ENDPOINT,
				CreateGatheringPoolRequest.Constant.MODULE,
				CreateGatheringPoolRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateGatheringPoolResult.class);

	}


	/**
	 * ギャザリングプールを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteGatheringPool(DeleteGatheringPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gatheringPool/" + (request.getGatheringPoolName() == null || request.getGatheringPoolName().equals("") ? "null" : request.getGatheringPoolName()) + "";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteGatheringPoolRequest.Constant.MODULE,
				DeleteGatheringPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ギャザリングプールの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeGatheringPoolResult describeGatheringPool(DescribeGatheringPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gatheringPool";

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
				DescribeGatheringPoolRequest.Constant.MODULE,
				DescribeGatheringPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeGatheringPoolResult.class);

	}


	/**
	 * ギャザリングプールを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetGatheringPoolResult getGatheringPool(GetGatheringPoolRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gatheringPool/" + (request.getGatheringPoolName() == null || request.getGatheringPoolName().equals("") ? "null" : request.getGatheringPoolName()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetGatheringPoolRequest.Constant.MODULE,
				GetGatheringPoolRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetGatheringPoolResult.class);

	}


	/**
	 * ギャザリングプールを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateGatheringPoolResult updateGatheringPool(UpdateGatheringPoolRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getDescription() != null) body.put("description", request.getDescription());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/gatheringPool/" + (request.getGatheringPoolName() == null || request.getGatheringPoolName().equals("") ? "null" : request.getGatheringPoolName()) + "",
				credential,
				ENDPOINT,
				UpdateGatheringPoolRequest.Constant.MODULE,
				UpdateGatheringPoolRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateGatheringPoolResult.class);

	}


	/**
	 * ギャザリングを作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateGatheringResult createGathering(CreateGatheringRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getName() != null) body.put("name", request.getName());
        if(request.getUserIds() != null) {
            List<JsonNode> node = new ArrayList<>();
            for(String item : request.getUserIds()) {
                node.add(JsonNodeFactory.instance.textNode(item));
            }
            body.set("userIds", JsonNodeFactory.instance.arrayNode().addAll(node));
		}

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/gatheringPool/" + (request.getGatheringPoolName() == null || request.getGatheringPoolName().equals("") ? "null" : request.getGatheringPoolName()) + "/gathering",
				credential,
				ENDPOINT,
				CreateGatheringRequest.Constant.MODULE,
				CreateGatheringRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateGatheringResult.class);

	}


	/**
	 * ギャザリングを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteGathering(DeleteGatheringRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gatheringPool/" + (request.getGatheringPoolName() == null || request.getGatheringPoolName().equals("") ? "null" : request.getGatheringPoolName()) + "/gathering/" + (request.getGatheringName() == null || request.getGatheringName().equals("") ? "null" : request.getGatheringName()) + "";


		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteGatheringRequest.Constant.MODULE,
				DeleteGatheringRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ギャザリングの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeGatheringResult describeGathering(DescribeGatheringRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gatheringPool/" + (request.getGatheringPoolName() == null || request.getGatheringPoolName().equals("") ? "null" : request.getGatheringPoolName()) + "/gathering";

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
				DescribeGatheringRequest.Constant.MODULE,
				DescribeGatheringRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeGatheringResult.class);

	}


	/**
	 * ギャザリングを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetGatheringResult getGathering(GetGatheringRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/gatheringPool/" + (request.getGatheringPoolName() == null || request.getGatheringPoolName().equals("") ? "null" : request.getGatheringPoolName()) + "/gathering/" + (request.getGatheringName() == null || request.getGatheringName().equals("") ? "null" : request.getGatheringName()) + "";


		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetGatheringRequest.Constant.MODULE,
				GetGatheringRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetGatheringResult.class);

	}


}