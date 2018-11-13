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

import org.apache.http.client.methods.HttpUriRequest;

/**
 * ワンタイムトークンによる認証。
 * 
 * @author Game Server Services, Inc.
 *
 */
public class OnetimeTokenGs2Credential implements IGs2Credential {
	
	/** ワンタイムトークン */
	String token;
	
	/**
	 * コンストラクタ。
	 * 
	 * @param token ワンタイムトークン
	 */
	public OnetimeTokenGs2Credential(String token) {
		if(token == null) {
			throw new IllegalArgumentException("invalid credential");
		}
		this.token = token;
	}

	/**
	 * ワンタイムトークンを取得。
	 * 
	 * @return ワンタイムトークン
	 */
	public String getToken() {
		return token;
	}

	@Override
	public void authorized(HttpUriRequest request, String service, String module, String function, Long timestamp) {
		request.setHeader("X-GS2-REQUEST-TIMESTAMP", String.valueOf(timestamp));
		request.setHeader("X-GS2-ONETIME-TOKEN", token);
	}
}
