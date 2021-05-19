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

package io.gs2.auth.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * アクセストークン
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AccessToken implements IModel, Serializable, Comparable<AccessToken> {
	/** オーナーID */
	protected String ownerId;

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 * @return this
	 */
	public AccessToken withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** アクセストークン */
	protected String token;

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
	 * アクセストークンを設定
	 *
	 * @param token アクセストークン
	 * @return this
	 */
	public AccessToken withToken(String token) {
		this.token = token;
		return this;
	}
	/** ユーザーID */
	protected String userId;

	/**
	 * ユーザーIDを取得
	 *
	 * @return ユーザーID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 * @return this
	 */
	public AccessToken withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 有効期限 */
	protected Long expire;

	/**
	 * 有効期限を取得
	 *
	 * @return 有効期限
	 */
	public Long getExpire() {
		return expire;
	}

	/**
	 * 有効期限を設定
	 *
	 * @param expire 有効期限
	 */
	public void setExpire(Long expire) {
		this.expire = expire;
	}

	/**
	 * 有効期限を設定
	 *
	 * @param expire 有効期限
	 * @return this
	 */
	public AccessToken withExpire(Long expire) {
		this.expire = expire;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("ownerId", this.getOwnerId())
            .put("token", this.getToken())
            .put("userId", this.getUserId())
            .put("expire", this.getExpire());
        return body_;
    }
	@Override
	public int compareTo(AccessToken o) {
		return token.compareTo(o.token);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.token == null) ? 0 : this.token.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.expire == null) ? 0 : this.expire.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		AccessToken other = (AccessToken) o;
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
			return false;
		}
		if (token == null) {
			return other.token == null;
		} else if (!token.equals(other.token)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (expire == null) {
			return other.expire == null;
		} else if (!expire.equals(other.expire)) {
			return false;
		}
		return true;
	}
}