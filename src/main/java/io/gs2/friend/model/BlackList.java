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

package io.gs2.friend.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * ブラックリスト
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BlackList implements IModel, Serializable, Comparable<BlackList> {
	/** ブラックリスト */
	protected String blackListId;

	/**
	 * ブラックリストを取得
	 *
	 * @return ブラックリスト
	 */
	public String getBlackListId() {
		return blackListId;
	}

	/**
	 * ブラックリストを設定
	 *
	 * @param blackListId ブラックリスト
	 */
	public void setBlackListId(String blackListId) {
		this.blackListId = blackListId;
	}

	/**
	 * ブラックリストを設定
	 *
	 * @param blackListId ブラックリスト
	 * @return this
	 */
	public BlackList withBlackListId(String blackListId) {
		this.blackListId = blackListId;
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
	public BlackList withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** ブラックリストのユーザーIDリスト */
	protected List<String> targetUserIds;

	/**
	 * ブラックリストのユーザーIDリストを取得
	 *
	 * @return ブラックリストのユーザーIDリスト
	 */
	public List<String> getTargetUserIds() {
		return targetUserIds;
	}

	/**
	 * ブラックリストのユーザーIDリストを設定
	 *
	 * @param targetUserIds ブラックリストのユーザーIDリスト
	 */
	public void setTargetUserIds(List<String> targetUserIds) {
		this.targetUserIds = targetUserIds;
	}

	/**
	 * ブラックリストのユーザーIDリストを設定
	 *
	 * @param targetUserIds ブラックリストのユーザーIDリスト
	 * @return this
	 */
	public BlackList withTargetUserIds(List<String> targetUserIds) {
		this.targetUserIds = targetUserIds;
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
	public BlackList withCreatedAt(Long createdAt) {
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
	public BlackList withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> targetUserIds = new ArrayList<>();
        if(this.targetUserIds != null) {
            for(String item : this.targetUserIds) {
                targetUserIds.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("blackListId", this.getBlackListId())
            .put("userId", this.getUserId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("targetUserIds", JsonNodeFactory.instance.arrayNode().addAll(targetUserIds));
        return body_;
    }
	@Override
	public int compareTo(BlackList o) {
		return blackListId.compareTo(o.blackListId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.blackListId == null) ? 0 : this.blackListId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.targetUserIds == null) ? 0 : this.targetUserIds.hashCode());
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
		BlackList other = (BlackList) o;
		if (blackListId == null) {
			return other.blackListId == null;
		} else if (!blackListId.equals(other.blackListId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (targetUserIds == null) {
			return other.targetUserIds == null;
		} else if (!targetUserIds.equals(other.targetUserIds)) {
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