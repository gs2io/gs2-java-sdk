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

package io.gs2.key.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * 暗号鍵
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Key implements IModel, Serializable, Comparable<Key> {
	/** 暗号鍵 */
	protected String keyId;

	/**
	 * 暗号鍵を取得
	 *
	 * @return 暗号鍵
	 */
	public String getKeyId() {
		return keyId;
	}

	/**
	 * 暗号鍵を設定
	 *
	 * @param keyId 暗号鍵
	 */
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	/**
	 * 暗号鍵を設定
	 *
	 * @param keyId 暗号鍵
	 * @return this
	 */
	public Key withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}
	/** 暗号鍵名 */
	protected String name;

	/**
	 * 暗号鍵名を取得
	 *
	 * @return 暗号鍵名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 暗号鍵名を設定
	 *
	 * @param name 暗号鍵名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 暗号鍵名を設定
	 *
	 * @param name 暗号鍵名
	 * @return this
	 */
	public Key withName(String name) {
		this.name = name;
		return this;
	}
	/** 説明文 */
	protected String description;

	/**
	 * 説明文を取得
	 *
	 * @return 説明文
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 * @return this
	 */
	public Key withDescription(String description) {
		this.description = description;
		return this;
	}
	/** 暗号鍵 */
	protected String secret;

	/**
	 * 暗号鍵を取得
	 *
	 * @return 暗号鍵
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * 暗号鍵を設定
	 *
	 * @param secret 暗号鍵
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * 暗号鍵を設定
	 *
	 * @param secret 暗号鍵
	 * @return this
	 */
	public Key withSecret(String secret) {
		this.secret = secret;
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
	public Key withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** 最終更新日時 */
	protected Long updatedAt;

	/**
	 * 最終更新日時を取得
	 *
	 * @return 最終更新日時
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 * @return this
	 */
	public Key withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("keyId", this.getKeyId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("secret", this.getSecret())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Key o) {
		return keyId.compareTo(o.keyId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.keyId == null) ? 0 : this.keyId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.secret == null) ? 0 : this.secret.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		Key other = (Key) o;
		if (keyId == null) {
			return other.keyId == null;
		} else if (!keyId.equals(other.keyId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (secret == null) {
			return other.secret == null;
		} else if (!secret.equals(other.secret)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}