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

package io.gs2.identifier.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.identifier.model.*;

/**
 * プロジェクトトークン を取得します のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginResult implements IResult, Serializable {
	/** プロジェクトトークン */
	private String access_token;
	/** Bearer */
	private String token_type;
	/** 有効期間(秒) */
	private Integer expires_in;

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

	/**
	 * Bearerを取得
	 *
	 * @return プロジェクトトークン を取得します
	 */
	public String getTokenType() {
		return token_type;
	}

	/**
	 * Bearerを設定
	 *
	 * @param tokenType プロジェクトトークン を取得します
	 */
	public void setTokenType(String tokenType) {
		this.token_type = tokenType;
	}

	/**
	 * 有効期間(秒)を取得
	 *
	 * @return プロジェクトトークン を取得します
	 */
	public Integer getExpiresIn() {
		return expires_in;
	}

	/**
	 * 有効期間(秒)を設定
	 *
	 * @param expiresIn プロジェクトトークン を取得します
	 */
	public void setExpiresIn(Integer expiresIn) {
		this.expires_in = expiresIn;
	}
}