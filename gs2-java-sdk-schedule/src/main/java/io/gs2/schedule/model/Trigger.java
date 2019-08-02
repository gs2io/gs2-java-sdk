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

package io.gs2.schedule.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * トリガー
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Trigger implements IModel, Serializable, Comparable<Trigger> {
	/** トリガー */
	protected String triggerId;

	/**
	 * トリガーを取得
	 *
	 * @return トリガー
	 */
	public String getTriggerId() {
		return triggerId;
	}

	/**
	 * トリガーを設定
	 *
	 * @param triggerId トリガー
	 */
	public void setTriggerId(String triggerId) {
		this.triggerId = triggerId;
	}

	/**
	 * トリガーを設定
	 *
	 * @param triggerId トリガー
	 * @return this
	 */
	public Trigger withTriggerId(String triggerId) {
		this.triggerId = triggerId;
		return this;
	}
	/** トリガーの名前 */
	protected String name;

	/**
	 * トリガーの名前を取得
	 *
	 * @return トリガーの名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * トリガーの名前を設定
	 *
	 * @param name トリガーの名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * トリガーの名前を設定
	 *
	 * @param name トリガーの名前
	 * @return this
	 */
	public Trigger withName(String name) {
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
	public Trigger withUserId(String userId) {
		this.userId = userId;
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
	public Trigger withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** トリガーの有効期限 */
	protected Long expiresAt;

	/**
	 * トリガーの有効期限を取得
	 *
	 * @return トリガーの有効期限
	 */
	public Long getExpiresAt() {
		return expiresAt;
	}

	/**
	 * トリガーの有効期限を設定
	 *
	 * @param expiresAt トリガーの有効期限
	 */
	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}

	/**
	 * トリガーの有効期限を設定
	 *
	 * @param expiresAt トリガーの有効期限
	 * @return this
	 */
	public Trigger withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("triggerId", this.getTriggerId())
            .put("name", this.getName())
            .put("userId", this.getUserId())
            .put("createdAt", this.getCreatedAt())
            .put("expiresAt", this.getExpiresAt());
        return body_;
    }
	@Override
	public int compareTo(Trigger o) {
		return triggerId.compareTo(o.triggerId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.triggerId == null) ? 0 : this.triggerId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.expiresAt == null) ? 0 : this.expiresAt.hashCode());
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
		Trigger other = (Trigger) o;
		if (triggerId == null) {
			return other.triggerId == null;
		} else if (!triggerId.equals(other.triggerId)) {
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
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (expiresAt == null) {
			return other.expiresAt == null;
		} else if (!expiresAt.equals(other.expiresAt)) {
			return false;
		}
		return true;
	}
}