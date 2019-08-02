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
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.Serializable;

/**
 * プロジェクトトークン
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Gs2ProjectToken implements Serializable {
	/** プロジェクトトークン */
	protected String token;

	/**
	 * プロジェクトトークンを取得
	 *
	 * @return プロジェクトトークン
	 */
	public String getToken() {
		return token;
	}

	/**
	 * プロジェクトトークンを設定
	 *
	 * @param token プロジェクトトークン
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * プロジェクトトークンを設定
	 *
	 * @param token プロジェクトトークン
	 * @return this
	 */
	public Gs2ProjectToken withToken(String token) {
		this.token = token;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("token", this.getToken());
        return body_;
    }
}