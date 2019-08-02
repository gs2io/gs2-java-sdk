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

package io.gs2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * プロジェクトトークン を取得します のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginResult implements Serializable {
	/** プロジェクトトークン */
	private String access_token;

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	/**
	 * プロジェクトトークンを取得
	 *
	 * @return プロジェクトトークン を取得します
	 */
	public String getAccessToken() {
		return access_token;
	}

	/**
	 * プロジェクトトークンを設定
	 *
	 * @param accessToken プロジェクトトークン を取得します
	 */
	public void setAccessToken(String accessToken) {
		this.access_token = accessToken;
	}
}