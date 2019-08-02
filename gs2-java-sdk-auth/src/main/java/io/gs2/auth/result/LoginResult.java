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

package io.gs2.auth.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.model.*;
import io.gs2.auth.model.*;

/**
 * 指定したユーザIDでGS2にログインし、アクセストークンを取得します のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginResult implements IResult, Serializable {
	/** アクセストークン */
	private String token;
	/** ユーザーID */
	private String userId;
	/** 有効期限 */
	private Long expire;

	/**
	 * アクセストークンを取得
	 *
	 * @return 指定したユーザIDでGS2にログインし、アクセストークンを取得します
	 */
	public String getToken() {
		return token;
	}

	/**
	 * アクセストークンを設定
	 *
	 * @param token 指定したユーザIDでGS2にログインし、アクセストークンを取得します
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * ユーザーIDを取得
	 *
	 * @return 指定したユーザIDでGS2にログインし、アクセストークンを取得します
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId 指定したユーザIDでGS2にログインし、アクセストークンを取得します
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 有効期限を取得
	 *
	 * @return 指定したユーザIDでGS2にログインし、アクセストークンを取得します
	 */
	public Long getExpire() {
		return expire;
	}

	/**
	 * 有効期限を設定
	 *
	 * @param expire 指定したユーザIDでGS2にログインし、アクセストークンを取得します
	 */
	public void setExpire(Long expire) {
		this.expire = expire;
	}
}