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
 * スタック
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Stack implements IModel, Serializable, Comparable<Stack> {
	/** スタック */
	protected String stackId;

	/**
	 * スタックを取得
	 *
	 * @return スタック
	 */
	public String getStackId() {
		return stackId;
	}

	/**
	 * スタックを設定
	 *
	 * @param stackId スタック
	 */
	public void setStackId(String stackId) {
		this.stackId = stackId;
	}

	/**
	 * スタックを設定
	 *
	 * @param stackId スタック
	 * @return this
	 */
	public Stack withStackId(String stackId) {
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
	public Stack withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** スタック名 */
	protected String name;

	/**
	 * スタック名を取得
	 *
	 * @return スタック名
	 */
	public String getName() {
		return name;
	}

	/**
	 * スタック名を設定
	 *
	 * @param name スタック名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * スタック名を設定
	 *
	 * @param name スタック名
	 * @return this
	 */
	public Stack withName(String name) {
		this.name = name;
		return this;
	}
	/** スタックの説明 */
	protected String description;

	/**
	 * スタックの説明を取得
	 *
	 * @return スタックの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * スタックの説明を設定
	 *
	 * @param description スタックの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * スタックの説明を設定
	 *
	 * @param description スタックの説明
	 * @return this
	 */
	public Stack withDescription(String description) {
		this.description = description;
		return this;
	}
	/** テンプレートデータ */
	protected String template;

	/**
	 * テンプレートデータを取得
	 *
	 * @return テンプレートデータ
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * テンプレートデータを設定
	 *
	 * @param template テンプレートデータ
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * テンプレートデータを設定
	 *
	 * @param template テンプレートデータ
	 * @return this
	 */
	public Stack withTemplate(String template) {
		this.template = template;
		return this;
	}
	/** 実行状態 */
	protected String status;

	/**
	 * 実行状態を取得
	 *
	 * @return 実行状態
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 実行状態を設定
	 *
	 * @param status 実行状態
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 実行状態を設定
	 *
	 * @param status 実行状態
	 * @return this
	 */
	public Stack withStatus(String status) {
		this.status = status;
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
	public Stack withCreatedAt(Long createdAt) {
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
	public Stack withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("stackId", this.getStackId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("template", this.getTemplate())
            .put("status", this.getStatus())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Stack o) {
		return stackId.compareTo(o.stackId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.stackId == null) ? 0 : this.stackId.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.template == null) ? 0 : this.template.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
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
		Stack other = (Stack) o;
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
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (template == null) {
			return other.template == null;
		} else if (!template.equals(other.template)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
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