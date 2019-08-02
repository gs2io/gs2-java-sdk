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

package io.gs2.identifier.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * クレデンシャル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Identifier implements IModel, Serializable, Comparable<Identifier> {
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
	public Identifier withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** クライアントID */
	protected String clientId;

	/**
	 * クライアントIDを取得
	 *
	 * @return クライアントID
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * クライアントIDを設定
	 *
	 * @param clientId クライアントID
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * クライアントIDを設定
	 *
	 * @param clientId クライアントID
	 * @return this
	 */
	public Identifier withClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}
	/** ユーザー名 */
	protected String userName;

	/**
	 * ユーザー名を取得
	 *
	 * @return ユーザー名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ユーザー名を設定
	 *
	 * @param userName ユーザー名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * ユーザー名を設定
	 *
	 * @param userName ユーザー名
	 * @return this
	 */
	public Identifier withUserName(String userName) {
		this.userName = userName;
		return this;
	}
	/** クライアントシークレット */
	protected String clientSecret;

	/**
	 * クライアントシークレットを取得
	 *
	 * @return クライアントシークレット
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * クライアントシークレットを設定
	 *
	 * @param clientSecret クライアントシークレット
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	/**
	 * クライアントシークレットを設定
	 *
	 * @param clientSecret クライアントシークレット
	 * @return this
	 */
	public Identifier withClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
		return this;
	}
	/** 作成日時 */
	protected Long createdAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 * @return this
	 */
	public Identifier withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("ownerId", this.getOwnerId())
            .put("clientId", this.getClientId())
            .put("userName", this.getUserName())
            .put("clientSecret", this.getClientSecret())
            .put("createdAt", this.getCreatedAt());
        return body_;
    }
	@Override
	public int compareTo(Identifier o) {
		return clientId.compareTo(o.clientId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.clientId == null) ? 0 : this.clientId.hashCode());
        result = prime * result + ((this.userName == null) ? 0 : this.userName.hashCode());
        result = prime * result + ((this.clientSecret == null) ? 0 : this.clientSecret.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		Identifier other = (Identifier) o;
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
			return false;
		}
		if (clientId == null) {
			return other.clientId == null;
		} else if (!clientId.equals(other.clientId)) {
			return false;
		}
		if (userName == null) {
			return other.userName == null;
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		if (clientSecret == null) {
			return other.clientSecret == null;
		} else if (!clientSecret.equals(other.clientSecret)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		return true;
	}
}