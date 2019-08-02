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
 * 割り当てられたセキュリティポリシー
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AttachSecurityPolicy implements IModel, Serializable, Comparable<AttachSecurityPolicy> {
	/** ユーザ のGRN */
	protected String userId;

	/**
	 * ユーザ のGRNを取得
	 *
	 * @return ユーザ のGRN
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザ のGRNを設定
	 *
	 * @param userId ユーザ のGRN
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザ のGRNを設定
	 *
	 * @param userId ユーザ のGRN
	 * @return this
	 */
	public AttachSecurityPolicy withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** セキュリティポリシー のGRNのリスト */
	protected List<String> securityPolicyIds;

	/**
	 * セキュリティポリシー のGRNのリストを取得
	 *
	 * @return セキュリティポリシー のGRNのリスト
	 */
	public List<String> getSecurityPolicyIds() {
		return securityPolicyIds;
	}

	/**
	 * セキュリティポリシー のGRNのリストを設定
	 *
	 * @param securityPolicyIds セキュリティポリシー のGRNのリスト
	 */
	public void setSecurityPolicyIds(List<String> securityPolicyIds) {
		this.securityPolicyIds = securityPolicyIds;
	}

	/**
	 * セキュリティポリシー のGRNのリストを設定
	 *
	 * @param securityPolicyIds セキュリティポリシー のGRNのリスト
	 * @return this
	 */
	public AttachSecurityPolicy withSecurityPolicyIds(List<String> securityPolicyIds) {
		this.securityPolicyIds = securityPolicyIds;
		return this;
	}
	/** 作成日時 */
	protected Long attachedAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getAttachedAt() {
		return attachedAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param attachedAt 作成日時
	 */
	public void setAttachedAt(Long attachedAt) {
		this.attachedAt = attachedAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param attachedAt 作成日時
	 * @return this
	 */
	public AttachSecurityPolicy withAttachedAt(Long attachedAt) {
		this.attachedAt = attachedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> securityPolicyIds = new ArrayList<>();
        if(this.securityPolicyIds != null) {
            for(String item : this.securityPolicyIds) {
                securityPolicyIds.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("userId", this.getUserId())
            .put("attachedAt", this.getAttachedAt());
        body_.set("securityPolicyIds", JsonNodeFactory.instance.arrayNode().addAll(securityPolicyIds));
        return body_;
    }
	@Override
	public int compareTo(AttachSecurityPolicy o) {
		return userId.compareTo(o.userId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.securityPolicyIds == null) ? 0 : this.securityPolicyIds.hashCode());
        result = prime * result + ((this.attachedAt == null) ? 0 : this.attachedAt.hashCode());
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
		AttachSecurityPolicy other = (AttachSecurityPolicy) o;
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (securityPolicyIds == null) {
			return other.securityPolicyIds == null;
		} else if (!securityPolicyIds.equals(other.securityPolicyIds)) {
			return false;
		}
		if (attachedAt == null) {
			return other.attachedAt == null;
		} else if (!attachedAt.equals(other.attachedAt)) {
			return false;
		}
		return true;
	}
}