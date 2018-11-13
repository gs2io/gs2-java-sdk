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

package io.gs2.limit;

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
import io.gs2.limit.control.*;

/**
 * GS2 Limit API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2LimitClient extends AbstractGs2Client<Gs2LimitClient> {

	public static String ENDPOINT = "limit";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2LimitClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2LimitClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2LimitClient(IGs2Credential credential, String region) {
		super(credential, region);
	}


	/**
	 * カウンターマスターを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateCounterMasterResult createCounterMaster(CreateCounterMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("max", request.getMax())
				.put("resetType", request.getResetType());
        if(request.getResetDayOfMonth() != null) body.put("resetDayOfMonth", request.getResetDayOfMonth());
        if(request.getResetDayOfWeek() != null) body.put("resetDayOfWeek", request.getResetDayOfWeek());
        if(request.getResetHour() != null) body.put("resetHour", request.getResetHour());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/master/counter",
				credential,
				ENDPOINT,
				CreateCounterMasterRequest.Constant.MODULE,
				CreateCounterMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateCounterMasterResult.class);

	}


	/**
	 * カウンターマスターを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteCounterMaster(DeleteCounterMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/master/counter/" + (request.getCounterName() == null || request.getCounterName().equals("") ? "null" : request.getCounterName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteCounterMasterRequest.Constant.MODULE,
				DeleteCounterMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * カウンターマスターの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeCounterMasterResult describeCounterMaster(DescribeCounterMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/master/counter";

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
				DescribeCounterMasterRequest.Constant.MODULE,
				DescribeCounterMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeCounterMasterResult.class);

	}


	/**
	 * カウンターマスターを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetCounterMasterResult getCounterMaster(GetCounterMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/master/counter/" + (request.getCounterName() == null || request.getCounterName().equals("") ? "null" : request.getCounterName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetCounterMasterRequest.Constant.MODULE,
				GetCounterMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetCounterMasterResult.class);

	}


	/**
	 * カウンターマスターを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateCounterMasterResult updateCounterMaster(UpdateCounterMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("max", request.getMax())
				.put("resetType", request.getResetType());
        if(request.getResetDayOfMonth() != null) body.put("resetDayOfMonth", request.getResetDayOfMonth());
        if(request.getResetDayOfWeek() != null) body.put("resetDayOfWeek", request.getResetDayOfWeek());
        if(request.getResetHour() != null) body.put("resetHour", request.getResetHour());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/master/counter/" + (request.getCounterName() == null || request.getCounterName().equals("") ? "null" : request.getCounterName()) + "",
				credential,
				ENDPOINT,
				UpdateCounterMasterRequest.Constant.MODULE,
				UpdateCounterMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateCounterMasterResult.class);

	}


	/**
	 * カウンターを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteCounter(DeleteCounterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/counter/" + (request.getCounterName() == null || request.getCounterName().equals("") ? "null" : request.getCounterName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteCounterRequest.Constant.MODULE,
				DeleteCounterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * カウンターの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeCounterResult describeCounter(DescribeCounterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/counter";

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
				DescribeCounterRequest.Constant.MODULE,
				DescribeCounterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeCounterResult.class);

	}


	/**
	 * ユーザのカウンターの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeCounterByUserIdResult describeCounterByUserId(DescribeCounterByUserIdRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/counter";

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
				DescribeCounterByUserIdRequest.Constant.MODULE,
				DescribeCounterByUserIdRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeCounterByUserIdResult.class);

	}


	/**
	 * カウンターを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetCounterResult getCounter(GetCounterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/counter/" + (request.getCounterName() == null || request.getCounterName().equals("") ? "null" : request.getCounterName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetCounterRequest.Constant.MODULE,
				GetCounterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetCounterResult.class);

	}


	/**
	 * カウンターを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetMyCounterResult getMyCounter(GetMyCounterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/counter/" + (request.getCounterName() == null || request.getCounterName().equals("") ? "null" : request.getCounterName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetMyCounterRequest.Constant.MODULE,
				GetMyCounterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, GetMyCounterResult.class);

	}


	/**
	 * カウンターを進めます<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpCounterResult upCounter(UpCounterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/counter/" + (request.getCounterName() == null || request.getCounterName().equals("") ? "null" : request.getCounterName()) + "",
				credential,
				ENDPOINT,
				UpCounterRequest.Constant.MODULE,
				UpCounterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpCounterResult.class);

	}


	/**
	 * カウンターを進めます<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpCounterByStampTaskResult upCounterByStampTask(UpCounterByStampTaskRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("task", request.getTask())
				.put("keyName", request.getKeyName())
				.put("transactionId", request.getTransactionId());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/counter",
				credential,
				ENDPOINT,
				UpCounterByStampTaskRequest.Constant.MODULE,
				UpCounterByStampTaskRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        put.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(put, UpCounterByStampTaskResult.class);

	}


	/**
	 * カウンターを進めます<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpMyCounterResult upMyCounter(UpMyCounterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/counter/" + (request.getCounterName() == null || request.getCounterName().equals("") ? "null" : request.getCounterName()) + "",
				credential,
				ENDPOINT,
				UpMyCounterRequest.Constant.MODULE,
				UpMyCounterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        put.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(put, UpMyCounterResult.class);

	}


	/**
	 * 現在適用されている回数制限マスターを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetCurrentCounterMasterResult getCurrentCounterMaster(GetCurrentCounterMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/counter/master";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetCurrentCounterMasterRequest.Constant.MODULE,
				GetCurrentCounterMasterRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetCurrentCounterMasterResult.class);

	}


	/**
	 * 回数制限マスターをインポートします<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateCurrentCounterMasterResult updateCurrentCounterMaster(UpdateCurrentCounterMasterRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("settings", request.getSettings());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/counter/master",
				credential,
				ENDPOINT,
				UpdateCurrentCounterMasterRequest.Constant.MODULE,
				UpdateCurrentCounterMasterRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, UpdateCurrentCounterMasterResult.class);

	}


	/**
	 * 回数制限を新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateLimitResult createLimit(CreateLimitRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());
        if(request.getDescription() != null) body.put("description", request.getDescription());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/limit",
				credential,
				ENDPOINT,
				CreateLimitRequest.Constant.MODULE,
				CreateLimitRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateLimitResult.class);

	}


	/**
	 * 回数制限を削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteLimit(DeleteLimitRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteLimitRequest.Constant.MODULE,
				DeleteLimitRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 回数制限の一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeLimitResult describeLimit(DescribeLimitRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit";

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
				DescribeLimitRequest.Constant.MODULE,
				DescribeLimitRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeLimitResult.class);

	}


	/**
	 * 回数制限を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetLimitResult getLimit(GetLimitRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetLimitRequest.Constant.MODULE,
				GetLimitRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetLimitResult.class);

	}


	/**
	 * 回数制限の状態を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetLimitStatusResult getLimitStatus(GetLimitStatusRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/status";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetLimitStatusRequest.Constant.MODULE,
				GetLimitStatusRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetLimitStatusResult.class);

	}


	/**
	 * 回数制限を更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateLimitResult updateLimit(UpdateLimitRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getDescription() != null) body.put("description", request.getDescription());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "",
				credential,
				ENDPOINT,
				UpdateLimitRequest.Constant.MODULE,
				UpdateLimitRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateLimitResult.class);

	}


	/**
	 * カウンターマスターをエクスポートします<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public ExportMasterResult exportMaster(ExportMasterRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/limit/" + (request.getLimitName() == null || request.getLimitName().equals("") ? "null" : request.getLimitName()) + "/master";



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