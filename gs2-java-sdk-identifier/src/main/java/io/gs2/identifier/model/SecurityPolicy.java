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
 * セキュリティポリシー
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SecurityPolicy implements IModel, Serializable, Comparable<SecurityPolicy> {
	/** セキュリティポリシー */
	protected String securityPolicyId;

	/**
	 * セキュリティポリシーを取得
	 *
	 * @return セキュリティポリシー
	 */
	public String getSecurityPolicyId() {
		return securityPolicyId;
	}

	/**
	 * セキュリティポリシーを設定
	 *
	 * @param securityPolicyId セキュリティポリシー
	 */
	public void setSecurityPolicyId(String securityPolicyId) {
		this.securityPolicyId = securityPolicyId;
	}

	/**
	 * セキュリティポリシーを設定
	 *
	 * @param securityPolicyId セキュリティポリシー
	 * @return this
	 */
	public SecurityPolicy withSecurityPolicyId(String securityPolicyId) {
		this.securityPolicyId = securityPolicyId;
		return this;
	}
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
	public SecurityPolicy withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** セキュリティポリシー名 */
	protected String name;

	/**
	 * セキュリティポリシー名を取得
	 *
	 * @return セキュリティポリシー名
	 */
	public String getName() {
		return name;
	}

	/**
	 * セキュリティポリシー名を設定
	 *
	 * @param name セキュリティポリシー名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * セキュリティポリシー名を設定
	 *
	 * @param name セキュリティポリシー名
	 * @return this
	 */
	public SecurityPolicy withName(String name) {
		this.name = name;
		return this;
	}
	/** セキュリティポリシーの説明 */
	protected String description;

	/**
	 * セキュリティポリシーの説明を取得
	 *
	 * @return セキュリティポリシーの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * セキュリティポリシーの説明を設定
	 *
	 * @param description セキュリティポリシーの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * セキュリティポリシーの説明を設定
	 *
	 * @param description セキュリティポリシーの説明
	 * @return this
	 */
	public SecurityPolicy withDescription(String description) {
		this.description = description;
		return this;
	}
	/** ポリシードキュメント */
	protected String policy;

	/**
	 * ポリシードキュメントを取得
	 *
	 * @return ポリシードキュメント
	 */
	public String getPolicy() {
		return policy;
	}

	/**
	 * ポリシードキュメントを設定
	 *
	 * @param policy ポリシードキュメント
	 */
	public void setPolicy(String policy) {
		this.policy = policy;
	}

	/**
	 * ポリシードキュメントを設定
	 *
	 * @param policy ポリシードキュメント
	 * @return this
	 */
	public SecurityPolicy withPolicy(String policy) {
		this.policy = policy;
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
	public SecurityPolicy withCreatedAt(Long createdAt) {
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
	public SecurityPolicy withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("securityPolicyId", this.getSecurityPolicyId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("policy", this.getPolicy())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(SecurityPolicy o) {
		return securityPolicyId.compareTo(o.securityPolicyId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.securityPolicyId == null) ? 0 : this.securityPolicyId.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.policy == null) ? 0 : this.policy.hashCode());
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
		SecurityPolicy other = (SecurityPolicy) o;
		if (securityPolicyId == null) {
			return other.securityPolicyId == null;
		} else if (!securityPolicyId.equals(other.securityPolicyId)) {
			return false;
		}
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
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
		if (policy == null) {
			return other.policy == null;
		} else if (!policy.equals(other.policy)) {
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