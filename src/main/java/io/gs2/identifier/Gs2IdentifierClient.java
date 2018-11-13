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

package io.gs2.identifier;

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
import io.gs2.identifier.control.*;

/**
 * GS2 Identifier API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2IdentifierClient extends AbstractGs2Client<Gs2IdentifierClient> {

	public static String ENDPOINT = "identifier";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2IdentifierClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2IdentifierClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2IdentifierClient(IGs2Credential credential, String region) {
		super(credential, region);
	}


	/**
	 * GSIを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateIdentifierResult createIdentifier(CreateIdentifierRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/user/" + (request.getUserName() == null || request.getUserName().equals("") ? "null" : request.getUserName()) + "/identifier",
				credential,
				ENDPOINT,
				CreateIdentifierRequest.Constant.MODULE,
				CreateIdentifierRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateIdentifierResult.class);

	}


	/**
	 * GSIを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteIdentifier(DeleteIdentifierRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/user/" + (request.getUserName() == null || request.getUserName().equals("") ? "null" : request.getUserName()) + "/identifier/" + (request.getIdentifierId() == null || request.getIdentifierId().equals("") ? "null" : request.getIdentifierId()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteIdentifierRequest.Constant.MODULE,
				DeleteIdentifierRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * GSIの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeIdentifierResult describeIdentifier(DescribeIdentifierRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/user/" + (request.getUserName() == null || request.getUserName().equals("") ? "null" : request.getUserName()) + "/identifier";

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
				DescribeIdentifierRequest.Constant.MODULE,
				DescribeIdentifierRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeIdentifierResult.class);

	}


	/**
	 * GSIを取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetIdentifierResult getIdentifier(GetIdentifierRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/user/" + (request.getUserName() == null || request.getUserName().equals("") ? "null" : request.getUserName()) + "/identifier/" + (request.getIdentifierId() == null || request.getIdentifierId().equals("") ? "null" : request.getIdentifierId()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetIdentifierRequest.Constant.MODULE,
				GetIdentifierRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetIdentifierResult.class);

	}


	/**
	 * セキュリティポリシーを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateSecurityPolicyResult createSecurityPolicy(CreateSecurityPolicyRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("policy", request.getPolicy());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/securityPolicy",
				credential,
				ENDPOINT,
				CreateSecurityPolicyRequest.Constant.MODULE,
				CreateSecurityPolicyRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateSecurityPolicyResult.class);

	}


	/**
	 * セキュリティポリシーを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteSecurityPolicy(DeleteSecurityPolicyRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/securityPolicy/" + (request.getSecurityPolicyName() == null || request.getSecurityPolicyName().equals("") ? "null" : request.getSecurityPolicyName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteSecurityPolicyRequest.Constant.MODULE,
				DeleteSecurityPolicyRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 共用セキュリティポリシーの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeCommonSecurityPolicyResult describeCommonSecurityPolicy(DescribeCommonSecurityPolicyRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/securityPolicy/common";

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
				DescribeCommonSecurityPolicyRequest.Constant.MODULE,
				DescribeCommonSecurityPolicyRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeCommonSecurityPolicyResult.class);

	}


	/**
	 * セキュリティポリシーの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeSecurityPolicyResult describeSecurityPolicy(DescribeSecurityPolicyRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/securityPolicy";

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
				DescribeSecurityPolicyRequest.Constant.MODULE,
				DescribeSecurityPolicyRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeSecurityPolicyResult.class);

	}


	/**
	 * セキュリティポリシーを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetSecurityPolicyResult getSecurityPolicy(GetSecurityPolicyRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/securityPolicy/" + (request.getSecurityPolicyName() == null || request.getSecurityPolicyName().equals("") ? "null" : request.getSecurityPolicyName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetSecurityPolicyRequest.Constant.MODULE,
				GetSecurityPolicyRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetSecurityPolicyResult.class);

	}


	/**
	 * セキュリティポリシーを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateSecurityPolicyResult updateSecurityPolicy(UpdateSecurityPolicyRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("policy", request.getPolicy());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/securityPolicy/" + (request.getSecurityPolicyName() == null || request.getSecurityPolicyName().equals("") ? "null" : request.getSecurityPolicyName()) + "",
				credential,
				ENDPOINT,
				UpdateSecurityPolicyRequest.Constant.MODULE,
				UpdateSecurityPolicyRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateSecurityPolicyResult.class);

	}


	/**
	 * ユーザにセキュリティポリシーを割り当てます<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void attachSecurityPolicy(AttachSecurityPolicyRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("securityPolicyId", request.getSecurityPolicyId());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/user/" + (request.getUserName() == null || request.getUserName().equals("") ? "null" : request.getUserName()) + "/securityPolicy",
				credential,
				ENDPOINT,
				AttachSecurityPolicyRequest.Constant.MODULE,
				AttachSecurityPolicyRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(put, null);

	}


	/**
	 * ユーザを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateUserResult createUser(CreateUserRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/user",
				credential,
				ENDPOINT,
				CreateUserRequest.Constant.MODULE,
				CreateUserRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateUserResult.class);

	}


	/**
	 * ユーザを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteUser(DeleteUserRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/user/" + (request.getUserName() == null || request.getUserName().equals("") ? "null" : request.getUserName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteUserRequest.Constant.MODULE,
				DeleteUserRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ユーザの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeUserResult describeUser(DescribeUserRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/user";

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
				DescribeUserRequest.Constant.MODULE,
				DescribeUserRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeUserResult.class);

	}


	/**
	 * ユーザに割り当てられたセキュリティポリシーを解除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void detachSecurityPolicy(DetachSecurityPolicyRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/user/" + (request.getUserName() == null || request.getUserName().equals("") ? "null" : request.getUserName()) + "/securityPolicy/" + (request.getSecurityPolicyId() == null || request.getSecurityPolicyId().equals("") ? "null" : request.getSecurityPolicyId()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DetachSecurityPolicyRequest.Constant.MODULE,
				DetachSecurityPolicyRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ユーザが保持しているセキュリティポリシー一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetHasSecurityPolicyResult getHasSecurityPolicy(GetHasSecurityPolicyRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/user/" + (request.getUserName() == null || request.getUserName().equals("") ? "null" : request.getUserName()) + "/securityPolicy";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetHasSecurityPolicyRequest.Constant.MODULE,
				GetHasSecurityPolicyRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetHasSecurityPolicyResult.class);

	}


	/**
	 * ユーザを取得します。<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetUserResult getUser(GetUserRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/user/" + (request.getUserName() == null || request.getUserName().equals("") ? "null" : request.getUserName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetUserRequest.Constant.MODULE,
				GetUserRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetUserResult.class);

	}


}