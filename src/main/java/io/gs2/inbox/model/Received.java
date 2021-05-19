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

package io.gs2.inbox.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 受信済みグローバルメッセージ名
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Received implements IModel, Serializable, Comparable<Received> {
	/** 受信済みグローバルメッセージ名 */
	protected String receivedId;

	/**
	 * 受信済みグローバルメッセージ名を取得
	 *
	 * @return 受信済みグローバルメッセージ名
	 */
	public String getReceivedId() {
		return receivedId;
	}

	/**
	 * 受信済みグローバルメッセージ名を設定
	 *
	 * @param receivedId 受信済みグローバルメッセージ名
	 */
	public void setReceivedId(String receivedId) {
		this.receivedId = receivedId;
	}

	/**
	 * 受信済みグローバルメッセージ名を設定
	 *
	 * @param receivedId 受信済みグローバルメッセージ名
	 * @return this
	 */
	public Received withReceivedId(String receivedId) {
		this.receivedId = receivedId;
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
	public Received withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 受信したグローバルメッセージ名 */
	protected List<String> receivedGlobalMessageNames;

	/**
	 * 受信したグローバルメッセージ名を取得
	 *
	 * @return 受信したグローバルメッセージ名
	 */
	public List<String> getReceivedGlobalMessageNames() {
		return receivedGlobalMessageNames;
	}

	/**
	 * 受信したグローバルメッセージ名を設定
	 *
	 * @param receivedGlobalMessageNames 受信したグローバルメッセージ名
	 */
	public void setReceivedGlobalMessageNames(List<String> receivedGlobalMessageNames) {
		this.receivedGlobalMessageNames = receivedGlobalMessageNames;
	}

	/**
	 * 受信したグローバルメッセージ名を設定
	 *
	 * @param receivedGlobalMessageNames 受信したグローバルメッセージ名
	 * @return this
	 */
	public Received withReceivedGlobalMessageNames(List<String> receivedGlobalMessageNames) {
		this.receivedGlobalMessageNames = receivedGlobalMessageNames;
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
	public Received withCreatedAt(Long createdAt) {
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
	public Received withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> receivedGlobalMessageNames = new ArrayList<>();
        if(this.receivedGlobalMessageNames != null) {
            for(String item : this.receivedGlobalMessageNames) {
                receivedGlobalMessageNames.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("receivedId", this.getReceivedId())
            .put("userId", this.getUserId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("receivedGlobalMessageNames", JsonNodeFactory.instance.arrayNode().addAll(receivedGlobalMessageNames));
        return body_;
    }
	@Override
	public int compareTo(Received o) {
		return receivedId.compareTo(o.receivedId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.receivedId == null) ? 0 : this.receivedId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.receivedGlobalMessageNames == null) ? 0 : this.receivedGlobalMessageNames.hashCode());
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
		Received other = (Received) o;
		if (receivedId == null) {
			return other.receivedId == null;
		} else if (!receivedId.equals(other.receivedId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (receivedGlobalMessageNames == null) {
			return other.receivedGlobalMessageNames == null;
		} else if (!receivedGlobalMessageNames.equals(other.receivedGlobalMessageNames)) {
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