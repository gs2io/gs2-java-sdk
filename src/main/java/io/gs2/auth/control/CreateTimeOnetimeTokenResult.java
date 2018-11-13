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

package io.gs2.auth.control;

import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.auth.model.*;

/**
 * @author Game Server Services, Inc.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateTimeOnetimeTokenResult {

	/** アクセストークン */
	private String token;

	/** アクセストークンの有効期限 */
	private Integer expire;


	/**
	 * アクセストークンを取得
	 *
	 * @return アクセストークン
	 */
	public String getToken() {
		return token;
	}

	/**
	 * アクセストークンを設定
	 *
	 * @param token アクセストークン
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * アクセストークンの有効期限を取得
	 *
	 * @return アクセストークンの有効期限
	 */
	public Integer getExpire() {
		return expire;
	}

	/**
	 * アクセストークンの有効期限を設定
	 *
	 * @param expire アクセストークンの有効期限
	 */
	public void setExpire(Integer expire) {
		this.expire = expire;
	}

}