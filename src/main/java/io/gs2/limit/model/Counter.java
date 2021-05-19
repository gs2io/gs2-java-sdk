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

package io.gs2.limit.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * カウンター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Counter implements IModel, Serializable, Comparable<Counter> {
	/** カウンター */
	protected String counterId;

	/**
	 * カウンターを取得
	 *
	 * @return カウンター
	 */
	public String getCounterId() {
		return counterId;
	}

	/**
	 * カウンターを設定
	 *
	 * @param counterId カウンター
	 */
	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}

	/**
	 * カウンターを設定
	 *
	 * @param counterId カウンター
	 * @return this
	 */
	public Counter withCounterId(String counterId) {
		this.counterId = counterId;
		return this;
	}
	/** 回数制限の種類の名前 */
	protected String limitName;

	/**
	 * 回数制限の種類の名前を取得
	 *
	 * @return 回数制限の種類の名前
	 */
	public String getLimitName() {
		return limitName;
	}

	/**
	 * 回数制限の種類の名前を設定
	 *
	 * @param limitName 回数制限の種類の名前
	 */
	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}

	/**
	 * 回数制限の種類の名前を設定
	 *
	 * @param limitName 回数制限の種類の名前
	 * @return this
	 */
	public Counter withLimitName(String limitName) {
		this.limitName = limitName;
		return this;
	}
	/** カウンターの名前 */
	protected String name;

	/**
	 * カウンターの名前を取得
	 *
	 * @return カウンターの名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * カウンターの名前を設定
	 *
	 * @param name カウンターの名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * カウンターの名前を設定
	 *
	 * @param name カウンターの名前
	 * @return this
	 */
	public Counter withName(String name) {
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
	public Counter withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** カウント値 */
	protected Integer count;

	/**
	 * カウント値を取得
	 *
	 * @return カウント値
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * カウント値を設定
	 *
	 * @param count カウント値
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * カウント値を設定
	 *
	 * @param count カウント値
	 * @return this
	 */
	public Counter withCount(Integer count) {
		this.count = count;
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
	public Counter withCreatedAt(Long createdAt) {
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
	public Counter withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("counterId", this.getCounterId())
            .put("limitName", this.getLimitName())
            .put("name", this.getName())
            .put("userId", this.getUserId())
            .put("count", this.getCount())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Counter o) {
		return counterId.compareTo(o.counterId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.counterId == null) ? 0 : this.counterId.hashCode());
        result = prime * result + ((this.limitName == null) ? 0 : this.limitName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
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
		Counter other = (Counter) o;
		if (counterId == null) {
			return other.counterId == null;
		} else if (!counterId.equals(other.counterId)) {
			return false;
		}
		if (limitName == null) {
			return other.limitName == null;
		} else if (!limitName.equals(other.limitName)) {
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
		if (count == null) {
			return other.count == null;
		} else if (!count.equals(other.count)) {
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