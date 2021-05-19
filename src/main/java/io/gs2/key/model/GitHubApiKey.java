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
import io.gs2.core.model.IModel;

/**
 * GitHub のAPIキー
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GitHubApiKey implements IModel, Serializable, Comparable<GitHubApiKey> {
	/** GitHub のAPIキー */
	protected String apiKeyId;

	/**
	 * GitHub のAPIキーを取得
	 *
	 * @return GitHub のAPIキー
	 */
	public String getApiKeyId() {
		return apiKeyId;
	}

	/**
	 * GitHub のAPIキーを設定
	 *
	 * @param apiKeyId GitHub のAPIキー
	 */
	public void setApiKeyId(String apiKeyId) {
		this.apiKeyId = apiKeyId;
	}

	/**
	 * GitHub のAPIキーを設定
	 *
	 * @param apiKeyId GitHub のAPIキー
	 * @return this
	 */
	public GitHubApiKey withApiKeyId(String apiKeyId) {
		this.apiKeyId = apiKeyId;
		return this;
	}
	/** GitHub APIキー名 */
	protected String name;

	/**
	 * GitHub APIキー名を取得
	 *
	 * @return GitHub APIキー名
	 */
	public String getName() {
		return name;
	}

	/**
	 * GitHub APIキー名を設定
	 *
	 * @param name GitHub APIキー名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * GitHub APIキー名を設定
	 *
	 * @param name GitHub APIキー名
	 * @return this
	 */
	public GitHubApiKey withName(String name) {
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
	public GitHubApiKey withDescription(String description) {
		this.description = description;
		return this;
	}
	/** APIキー */
	protected String apiKey;

	/**
	 * APIキーを取得
	 *
	 * @return APIキー
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * APIキーを設定
	 *
	 * @param apiKey APIキー
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * APIキーを設定
	 *
	 * @param apiKey APIキー
	 * @return this
	 */
	public GitHubApiKey withApiKey(String apiKey) {
		this.apiKey = apiKey;
		return this;
	}
	/** APIキーの暗号化に使用する暗号鍵名 */
	protected String encryptionKeyName;

	/**
	 * APIキーの暗号化に使用する暗号鍵名を取得
	 *
	 * @return APIキーの暗号化に使用する暗号鍵名
	 */
	public String getEncryptionKeyName() {
		return encryptionKeyName;
	}

	/**
	 * APIキーの暗号化に使用する暗号鍵名を設定
	 *
	 * @param encryptionKeyName APIキーの暗号化に使用する暗号鍵名
	 */
	public void setEncryptionKeyName(String encryptionKeyName) {
		this.encryptionKeyName = encryptionKeyName;
	}

	/**
	 * APIキーの暗号化に使用する暗号鍵名を設定
	 *
	 * @param encryptionKeyName APIキーの暗号化に使用する暗号鍵名
	 * @return this
	 */
	public GitHubApiKey withEncryptionKeyName(String encryptionKeyName) {
		this.encryptionKeyName = encryptionKeyName;
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
	public GitHubApiKey withCreatedAt(Long createdAt) {
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
	public GitHubApiKey withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("apiKeyId", this.getApiKeyId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("apiKey", this.getApiKey())
            .put("encryptionKeyName", this.getEncryptionKeyName())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(GitHubApiKey o) {
		return apiKeyId.compareTo(o.apiKeyId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.apiKeyId == null) ? 0 : this.apiKeyId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.apiKey == null) ? 0 : this.apiKey.hashCode());
        result = prime * result + ((this.encryptionKeyName == null) ? 0 : this.encryptionKeyName.hashCode());
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
		GitHubApiKey other = (GitHubApiKey) o;
		if (apiKeyId == null) {
			return other.apiKeyId == null;
		} else if (!apiKeyId.equals(other.apiKeyId)) {
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
		if (apiKey == null) {
			return other.apiKey == null;
		} else if (!apiKey.equals(other.apiKey)) {
			return false;
		}
		if (encryptionKeyName == null) {
			return other.encryptionKeyName == null;
		} else if (!encryptionKeyName.equals(other.encryptionKeyName)) {
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