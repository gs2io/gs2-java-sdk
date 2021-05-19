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

package io.gs2.formation.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 保存したフォーム
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Mold implements IModel, Serializable, Comparable<Mold> {
	/** 保存したフォーム */
	protected String moldId;

	/**
	 * 保存したフォームを取得
	 *
	 * @return 保存したフォーム
	 */
	public String getMoldId() {
		return moldId;
	}

	/**
	 * 保存したフォームを設定
	 *
	 * @param moldId 保存したフォーム
	 */
	public void setMoldId(String moldId) {
		this.moldId = moldId;
	}

	/**
	 * 保存したフォームを設定
	 *
	 * @param moldId 保存したフォーム
	 * @return this
	 */
	public Mold withMoldId(String moldId) {
		this.moldId = moldId;
		return this;
	}
	/** フォームの保存領域の名前 */
	protected String name;

	/**
	 * フォームの保存領域の名前を取得
	 *
	 * @return フォームの保存領域の名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * フォームの保存領域の名前を設定
	 *
	 * @param name フォームの保存領域の名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * フォームの保存領域の名前を設定
	 *
	 * @param name フォームの保存領域の名前
	 * @return this
	 */
	public Mold withName(String name) {
		this.name = name;
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
	public Mold withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 現在のキャパシティ */
	protected Integer capacity;

	/**
	 * 現在のキャパシティを取得
	 *
	 * @return 現在のキャパシティ
	 */
	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * 現在のキャパシティを設定
	 *
	 * @param capacity 現在のキャパシティ
	 */
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	/**
	 * 現在のキャパシティを設定
	 *
	 * @param capacity 現在のキャパシティ
	 * @return this
	 */
	public Mold withCapacity(Integer capacity) {
		this.capacity = capacity;
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
	public Mold withCreatedAt(Long createdAt) {
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
	public Mold withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("moldId", this.getMoldId())
            .put("name", this.getName())
            .put("userId", this.getUserId())
            .put("capacity", this.getCapacity())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Mold o) {
		return moldId.compareTo(o.moldId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.moldId == null) ? 0 : this.moldId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.capacity == null) ? 0 : this.capacity.hashCode());
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
		Mold other = (Mold) o;
		if (moldId == null) {
			return other.moldId == null;
		} else if (!moldId.equals(other.moldId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (capacity == null) {
			return other.capacity == null;
		} else if (!capacity.equals(other.capacity)) {
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