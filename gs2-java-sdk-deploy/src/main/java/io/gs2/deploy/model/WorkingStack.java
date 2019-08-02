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

package io.gs2.deploy.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * 実行中のスタック
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class WorkingStack implements IModel, Serializable, Comparable<WorkingStack> {
	/** 実行中のスタック */
	protected String stackId;

	/**
	 * 実行中のスタックを取得
	 *
	 * @return 実行中のスタック
	 */
	public String getStackId() {
		return stackId;
	}

	/**
	 * 実行中のスタックを設定
	 *
	 * @param stackId 実行中のスタック
	 */
	public void setStackId(String stackId) {
		this.stackId = stackId;
	}

	/**
	 * 実行中のスタックを設定
	 *
	 * @param stackId 実行中のスタック
	 * @return this
	 */
	public WorkingStack withStackId(String stackId) {
		this.stackId = stackId;
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
	public WorkingStack withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** 実行中のスタック名 */
	protected String name;

	/**
	 * 実行中のスタック名を取得
	 *
	 * @return 実行中のスタック名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 実行中のスタック名を設定
	 *
	 * @param name 実行中のスタック名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 実行中のスタック名を設定
	 *
	 * @param name 実行中のスタック名
	 * @return this
	 */
	public WorkingStack withName(String name) {
		this.name = name;
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
	public WorkingStack withCreatedAt(Long createdAt) {
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
	public WorkingStack withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("stackId", this.getStackId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(WorkingStack o) {
		return stackId.compareTo(o.stackId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.stackId == null) ? 0 : this.stackId.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
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
		WorkingStack other = (WorkingStack) o;
		if (stackId == null) {
			return other.stackId == null;
		} else if (!stackId.equals(other.stackId)) {
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