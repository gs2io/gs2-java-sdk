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

package io.gs2.ranking.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 購読
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Subscribe implements IModel, Serializable, Comparable<Subscribe> {
	/** 購読 */
	protected String subscribeId;

	/**
	 * 購読を取得
	 *
	 * @return 購読
	 */
	public String getSubscribeId() {
		return subscribeId;
	}

	/**
	 * 購読を設定
	 *
	 * @param subscribeId 購読
	 */
	public void setSubscribeId(String subscribeId) {
		this.subscribeId = subscribeId;
	}

	/**
	 * 購読を設定
	 *
	 * @param subscribeId 購読
	 * @return this
	 */
	public Subscribe withSubscribeId(String subscribeId) {
		this.subscribeId = subscribeId;
		return this;
	}
	/** カテゴリ名 */
	protected String categoryName;

	/**
	 * カテゴリ名を取得
	 *
	 * @return カテゴリ名
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * カテゴリ名を設定
	 *
	 * @param categoryName カテゴリ名
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * カテゴリ名を設定
	 *
	 * @param categoryName カテゴリ名
	 * @return this
	 */
	public Subscribe withCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}
	/** 購読するユーザID */
	protected String userId;

	/**
	 * 購読するユーザIDを取得
	 *
	 * @return 購読するユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 購読するユーザIDを設定
	 *
	 * @param userId 購読するユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 購読するユーザIDを設定
	 *
	 * @param userId 購読するユーザID
	 * @return this
	 */
	public Subscribe withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 購読されるユーザIDリスト */
	protected List<String> targetUserIds;

	/**
	 * 購読されるユーザIDリストを取得
	 *
	 * @return 購読されるユーザIDリスト
	 */
	public List<String> getTargetUserIds() {
		return targetUserIds;
	}

	/**
	 * 購読されるユーザIDリストを設定
	 *
	 * @param targetUserIds 購読されるユーザIDリスト
	 */
	public void setTargetUserIds(List<String> targetUserIds) {
		this.targetUserIds = targetUserIds;
	}

	/**
	 * 購読されるユーザIDリストを設定
	 *
	 * @param targetUserIds 購読されるユーザIDリスト
	 * @return this
	 */
	public Subscribe withTargetUserIds(List<String> targetUserIds) {
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
	public Subscribe withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
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
            .put("subscribeId", this.getSubscribeId())
            .put("categoryName", this.getCategoryName())
            .put("userId", this.getUserId())
            .put("createdAt", this.getCreatedAt());
        body_.set("targetUserIds", JsonNodeFactory.instance.arrayNode().addAll(targetUserIds));
        return body_;
    }
	@Override
	public int compareTo(Subscribe o) {
		return subscribeId.compareTo(o.subscribeId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.subscribeId == null) ? 0 : this.subscribeId.hashCode());
        result = prime * result + ((this.categoryName == null) ? 0 : this.categoryName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.targetUserIds == null) ? 0 : this.targetUserIds.hashCode());
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
		Subscribe other = (Subscribe) o;
		if (subscribeId == null) {
			return other.subscribeId == null;
		} else if (!subscribeId.equals(other.subscribeId)) {
			return false;
		}
		if (categoryName == null) {
			return other.categoryName == null;
		} else if (!categoryName.equals(other.categoryName)) {
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
		return true;
	}
}