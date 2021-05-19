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
package io.gs2.core.model;

import org.apache.http.client.methods.HttpUriRequest;

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
	/** プロジェクトトークン */
	String projectToken;
	
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

	/**
	 * プロジェクトトークンを取得。
	 *
	 * @return プロジェクトトークン
	 */
	public String getProjectToken() {
		return projectToken;
	}


	/**
	 * プロジェクトトークンを設定。
	 * @param projectToken プロジェクトトークン
	 */
	public void setProjectToken(String projectToken) {
		this.projectToken = projectToken;
	}

	@Override
	public void authorized(HttpUriRequest request) {
		request.setHeader("X-GS2-CLIENT-ID", getClientId());
		if(getProjectToken() != null) {
			request.setHeader("Authorization", "Bearer " + getProjectToken());
		}
	}
}
