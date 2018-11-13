/*
 * Copyright 2016 Game Server Services, Inc. or its affiliates. All Rights
 * Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package io.gs2.model;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpUriRequest;

import io.gs2.util.SignUtil;

/**
 * アクセスキーによる認証。
 * 
 * @author Game Server Services, Inc.
 *
 */
public class BasicGs2Credential implements IGs2Credential {
	
	/** クライアントID */
	String clientId;
	/** クライアントシークレット */
	String clientSecret;
	
	/**
	 * コンストラクタ。
	 * 
	 * @param clientId クライアントID
	 * @param clientSecret クライアントシークレット
	 */
	public BasicGs2Credential(String clientId, String clientSecret) {
		if(clientId == null || clientSecret == null) {
			throw new IllegalArgumentException("invalid credential");
		}
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	/**
	 * クライアントIDを取得。
	 * 
	 * @return クライアントID
	 */
	public String getClientId() {
		return clientId;
	}
	
	/**
	 * クライアントシークレットを取得。
	 * 
	 * @return クライアントシークレット
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	@Override
	public void authorized(HttpUriRequest request, String service, String module, String function, Long timestamp) {
		String sign = new Base64().encodeAsString(SignUtil.sign(getClientSecret(), module, function, timestamp));
		request.setHeader("X-GS2-CLIENT-ID", getClientId());
		request.setHeader("X-GS2-REQUEST-TIMESTAMP", String.valueOf(timestamp));
		request.setHeader("X-GS2-REQUEST-SIGN", sign);
	}
}
