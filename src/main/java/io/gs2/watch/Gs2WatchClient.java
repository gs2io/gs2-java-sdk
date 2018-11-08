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

package io.gs2.watch;

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
import io.gs2.watch.control.*;

/**
 * GS2 Watch API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2WatchClient extends AbstractGs2Client<Gs2WatchClient> {

	public static String ENDPOINT = "watch";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2WatchClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2WatchClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2WatchClient(IGs2Credential credential, String region) {
		super(credential, region);

	}


	/**
	 * アラームを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateAlarmResult createAlarm(CreateAlarmRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("service", request.getService())
				.put("operation", request.getOperation())
				.put("expression", request.getExpression())
				.put("threshold", request.getThreshold())
				.put("notificationId", request.getNotificationId());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getServiceId() != null) body.put("serviceId", request.getServiceId());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/alarm",
				credential,
				ENDPOINT,
				CreateAlarmRequest.Constant.MODULE,
				CreateAlarmRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateAlarmResult.class);

	}


	/**
	 * アラームを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteAlarm(DeleteAlarmRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/alarm/" + (request.getAlarmName() == null || request.getAlarmName().equals("") ? "null" : request.getAlarmName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteAlarmRequest.Constant.MODULE,
				DeleteAlarmRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * アラームの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeAlarmResult describeAlarm(DescribeAlarmRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/alarm";

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
				DescribeAlarmRequest.Constant.MODULE,
				DescribeAlarmRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeAlarmResult.class);

	}


	/**
	 * アラームイベントの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeAlarmEventResult describeAlarmEvent(DescribeAlarmEventRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/alarm/" + (request.getAlarmName() == null || request.getAlarmName().equals("") ? "null" : request.getAlarmName()) + "/event";

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
				DescribeAlarmEventRequest.Constant.MODULE,
				DescribeAlarmEventRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeAlarmEventResult.class);

	}


	/**
	 * アラームを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetAlarmResult getAlarm(GetAlarmRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/alarm/" + (request.getAlarmName() == null || request.getAlarmName().equals("") ? "null" : request.getAlarmName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetAlarmRequest.Constant.MODULE,
				GetAlarmRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetAlarmResult.class);

	}


	/**
	 * アラームを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateAlarmResult updateAlarm(UpdateAlarmRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("expression", request.getExpression())
				.put("threshold", request.getThreshold())
				.put("notificationId", request.getNotificationId());
        if(request.getDescription() != null) body.put("description", request.getDescription());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/alarm/" + (request.getAlarmName() == null || request.getAlarmName().equals("") ? "null" : request.getAlarmName()) + "",
				credential,
				ENDPOINT,
				UpdateAlarmRequest.Constant.MODULE,
				UpdateAlarmRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateAlarmResult.class);

	}


	/**
	 * 操作の一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeOperationResult describeOperation(DescribeOperationRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/service/" + (request.getService() == null || request.getService().equals("") ? "null" : request.getService()) + "/operation";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeOperationRequest.Constant.MODULE,
				DescribeOperationRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeOperationResult.class);

	}


	/**
	 * サービスの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeServiceResult describeService(DescribeServiceRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/service";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeServiceRequest.Constant.MODULE,
				DescribeServiceRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeServiceResult.class);

	}


	/**
	 * メトリックを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetMetricResult getMetric(GetMetricRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/metric/" + (request.getServiceId() == null || request.getServiceId().equals("") ? "null" : request.getServiceId()) + "/" + (request.getOperation() == null || request.getOperation().equals("") ? "null" : request.getOperation()) + "";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getBegin() != null) queryString.add(new BasicNameValuePair("begin", String.valueOf(request.getBegin())));
        if(request.getEnd() != null) queryString.add(new BasicNameValuePair("end", String.valueOf(request.getEnd())));
        if(request.getAllowLongTerm() != null) queryString.add(new BasicNameValuePair("allowLongTerm", String.valueOf(request.getAllowLongTerm())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetMetricRequest.Constant.MODULE,
				GetMetricRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetMetricResult.class);

	}


}