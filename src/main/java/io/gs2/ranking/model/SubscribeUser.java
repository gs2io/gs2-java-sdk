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
 * 購読対象
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SubscribeUser implements IModel, Serializable {
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
	public SubscribeUser withCategoryName(String categoryName) {
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
	public SubscribeUser withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 購読されるユーザID */
	protected String targetUserId;

	/**
	 * 購読されるユーザIDを取得
	 *
	 * @return 購読されるユーザID
	 */
	public String getTargetUserId() {
		return targetUserId;
	}

	/**
	 * 購読されるユーザIDを設定
	 *
	 * @param targetUserId 購読されるユーザID
	 */
	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	/**
	 * 購読されるユーザIDを設定
	 *
	 * @param targetUserId 購読されるユーザID
	 * @return this
	 */
	public SubscribeUser withTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("categoryName", this.getCategoryName())
            .put("userId", this.getUserId())
            .put("targetUserId", this.getTargetUserId());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.categoryName == null) ? 0 : this.categoryName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.targetUserId == null) ? 0 : this.targetUserId.hashCode());
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
		SubscribeUser other = (SubscribeUser) o;
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
		if (targetUserId == null) {
			return other.targetUserId == null;
		} else if (!targetUserId.equals(other.targetUserId)) {
			return false;
		}
		return true;
	}
}