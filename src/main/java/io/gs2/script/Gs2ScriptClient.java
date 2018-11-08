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

package io.gs2.script;

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
import io.gs2.script.control.*;

/**
 * GS2 Script API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2ScriptClient extends AbstractGs2Client<Gs2ScriptClient> {

	public static String ENDPOINT = "script";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2ScriptClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2ScriptClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2ScriptClient(IGs2Credential credential, String region) {
		super(credential, region);
	}


	/**
	 * スクリプトを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateScriptResult createScript(CreateScriptRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName())
				.put("script", request.getScript());
        if(request.getDescription() != null) body.put("description", request.getDescription());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/script",
				credential,
				ENDPOINT,
				CreateScriptRequest.Constant.MODULE,
				CreateScriptRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateScriptResult.class);

	}


	/**
	 * スクリプトを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteScript(DeleteScriptRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/script/" + (request.getScriptName() == null || request.getScriptName().equals("") ? "null" : request.getScriptName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteScriptRequest.Constant.MODULE,
				DeleteScriptRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * スクリプトの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeScriptResult describeScript(DescribeScriptRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/script";

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
				DescribeScriptRequest.Constant.MODULE,
				DescribeScriptRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeScriptResult.class);

	}


	/**
	 * スクリプトを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetScriptResult getScript(GetScriptRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/script/" + (request.getScriptName() == null || request.getScriptName().equals("") ? "null" : request.getScriptName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetScriptRequest.Constant.MODULE,
				GetScriptRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetScriptResult.class);

	}


	/**
	 * スクリプトを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateScriptResult updateScript(UpdateScriptRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getScript() != null) body.put("script", request.getScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/script/" + (request.getScriptName() == null || request.getScriptName().equals("") ? "null" : request.getScriptName()) + "",
				credential,
				ENDPOINT,
				UpdateScriptRequest.Constant.MODULE,
				UpdateScriptRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateScriptResult.class);

	}


}