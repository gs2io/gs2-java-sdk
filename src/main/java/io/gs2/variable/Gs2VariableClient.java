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

package io.gs2.variable;

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
import io.gs2.variable.control.*;

/**
 * GS2 Variable API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2VariableClient extends AbstractGs2Client<Gs2VariableClient> {

	public static String ENDPOINT = "variable";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2VariableClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2VariableClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2VariableClient(IGs2Credential credential, String region) {
		super(credential, region);
	}


	/**
	 * 変数を削除する<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteMyVariable(DeleteMyVariableRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/variable/" + (request.getVariableName() == null || request.getVariableName().equals("") ? "null" : request.getVariableName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteMyVariableRequest.Constant.MODULE,
				DeleteMyVariableRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        delete.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		doRequest(delete, null);

	}


	/**
	 * 変数を削除する<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteVariable(DeleteVariableRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/variable/" + (request.getVariableName() == null || request.getVariableName().equals("") ? "null" : request.getVariableName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteVariableRequest.Constant.MODULE,
				DeleteVariableRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * 変数を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetMyVariableResult getMyVariable(GetMyVariableRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/variable/" + (request.getVariableName() == null || request.getVariableName().equals("") ? "null" : request.getVariableName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetMyVariableRequest.Constant.MODULE,
				GetMyVariableRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, GetMyVariableResult.class);

	}


	/**
	 * 変数を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetVariableResult getVariable(GetVariableRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/variable/" + (request.getVariableName() == null || request.getVariableName().equals("") ? "null" : request.getVariableName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetVariableRequest.Constant.MODULE,
				GetVariableRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetVariableResult.class);

	}


	/**
	 * 変数を格納する<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public SetMyVariableResult setMyVariable(SetMyVariableRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("value", request.getValue())
				.put("ttl", request.getTtl());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/variable/" + (request.getVariableName() == null || request.getVariableName().equals("") ? "null" : request.getVariableName()) + "",
				credential,
				ENDPOINT,
				SetMyVariableRequest.Constant.MODULE,
				SetMyVariableRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        put.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(put, SetMyVariableResult.class);

	}


	/**
	 * 変数を格納する<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public SetVariableResult setVariable(SetVariableRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("value", request.getValue())
				.put("ttl", request.getTtl());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/user/" + (request.getUserId() == null || request.getUserId().equals("") ? "null" : request.getUserId()) + "/variable/" + (request.getVariableName() == null || request.getVariableName().equals("") ? "null" : request.getVariableName()) + "",
				credential,
				ENDPOINT,
				SetVariableRequest.Constant.MODULE,
				SetVariableRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, SetVariableResult.class);

	}


}