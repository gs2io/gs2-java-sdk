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

package io.gs2.notification;

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
import io.gs2.notification.control.*;

/**
 * GS2 Notification API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2NotificationClient extends AbstractGs2Client<Gs2NotificationClient> {

	public static String ENDPOINT = "notification";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2NotificationClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2NotificationClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2NotificationClient(IGs2Credential credential, String region) {
		super(credential, region);
	}


	/**
	 * 通知を新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateNotificationResult createNotification(CreateNotificationRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());
        if(request.getDescription() != null) body.put("description", request.getDescription());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/notification",
				credential,
				ENDPOINT,
				CreateNotificationRequest.Constant.MODULE,
				CreateNotificationRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateNotificationResult.class);

	}


	/**
	 * 通知を削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteNotification(DeleteNotificationRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/notification/" + (request.getNotificationName() == null || request.getNotificationName().equals("") ? "null" : request.getNotificationName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteNotificationRequest.Constant.MODULE,
				DeleteNotificationRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 通知の一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeNotificationResult describeNotification(DescribeNotificationRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/notification";

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
				DescribeNotificationRequest.Constant.MODULE,
				DescribeNotificationRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeNotificationResult.class);

	}


	/**
	 * 通知を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetNotificationResult getNotification(GetNotificationRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/notification/" + (request.getNotificationName() == null || request.getNotificationName().equals("") ? "null" : request.getNotificationName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetNotificationRequest.Constant.MODULE,
				GetNotificationRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetNotificationResult.class);

	}


	/**
	 * 通知を更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateNotificationResult updateNotification(UpdateNotificationRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getDescription() != null) body.put("description", request.getDescription());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/notification/" + (request.getNotificationName() == null || request.getNotificationName().equals("") ? "null" : request.getNotificationName()) + "",
				credential,
				ENDPOINT,
				UpdateNotificationRequest.Constant.MODULE,
				UpdateNotificationRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateNotificationResult.class);

	}


	/**
	 * 購読を作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateSubscribeResult createSubscribe(CreateSubscribeRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("type", request.getType())
				.put("endpoint", request.getEndpoint());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/notification/" + (request.getNotificationName() == null || request.getNotificationName().equals("") ? "null" : request.getNotificationName()) + "/subscribe",
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
	 * 購読を削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteSubscribe(DeleteSubscribeRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/notification/" + (request.getNotificationName() == null || request.getNotificationName().equals("") ? "null" : request.getNotificationName()) + "/subscribe/" + (request.getSubscribeId() == null || request.getSubscribeId().equals("") ? "null" : request.getSubscribeId()) + "";



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
	 * 購読の一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeSubscribeResult describeSubscribe(DescribeSubscribeRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/notification/" + (request.getNotificationName() == null || request.getNotificationName().equals("") ? "null" : request.getNotificationName()) + "/subscribe";

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
				DescribeSubscribeRequest.Constant.MODULE,
				DescribeSubscribeRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeSubscribeResult.class);

	}


	/**
	 * 購読を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetSubscribeResult getSubscribe(GetSubscribeRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/notification/" + (request.getNotificationName() == null || request.getNotificationName().equals("") ? "null" : request.getNotificationName()) + "/subscribe/" + (request.getSubscribeId() == null || request.getSubscribeId().equals("") ? "null" : request.getSubscribeId()) + "";



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