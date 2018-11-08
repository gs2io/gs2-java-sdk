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

package io.gs2.key;

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
import io.gs2.key.control.*;

/**
 * GS2 Key API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2KeyClient extends AbstractGs2Client<Gs2KeyClient> {

	public static String ENDPOINT = "key";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2KeyClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2KeyClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2KeyClient(IGs2Credential credential, String region) {
		super(credential, region);
	}


	/**
	 * 暗号鍵を新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateKeyResult createKey(CreateKeyRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/key",
				credential,
				ENDPOINT,
				CreateKeyRequest.Constant.MODULE,
				CreateKeyRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateKeyResult.class);

	}


	/**
	 * 復号化処理を実行します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DecryptResult decrypt(DecryptRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("data", request.getData());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/key/" + (request.getKeyName() == null || request.getKeyName().equals("") ? "null" : request.getKeyName()) + "/decrypt",
				credential,
				ENDPOINT,
				DecryptRequest.Constant.MODULE,
				DecryptRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, DecryptResult.class);

	}


	/**
	 * 暗号鍵を削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteKey(DeleteKeyRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/key/" + (request.getKeyName() == null || request.getKeyName().equals("") ? "null" : request.getKeyName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteKeyRequest.Constant.MODULE,
				DeleteKeyRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 暗号鍵の一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeKeyResult describeKey(DescribeKeyRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/key";

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
				DescribeKeyRequest.Constant.MODULE,
				DescribeKeyRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeKeyResult.class);

	}


	/**
	 * 暗号化処理を実行します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public EncryptResult encrypt(EncryptRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("data", request.getData());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/key/" + (request.getKeyName() == null || request.getKeyName().equals("") ? "null" : request.getKeyName()) + "/encrypt",
				credential,
				ENDPOINT,
				EncryptRequest.Constant.MODULE,
				EncryptRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, EncryptResult.class);

	}


	/**
	 * 暗号鍵を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetKeyResult getKey(GetKeyRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/key/" + (request.getKeyName() == null || request.getKeyName().equals("") ? "null" : request.getKeyName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetKeyRequest.Constant.MODULE,
				GetKeyRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetKeyResult.class);

	}


}