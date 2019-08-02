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

package io.gs2.account.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * 引き継ぎ設定
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class TakeOver implements IModel, Serializable, Comparable<TakeOver> {
	/** 引き継ぎ設定 */
	protected String takeOverId;

	/**
	 * 引き継ぎ設定を取得
	 *
	 * @return 引き継ぎ設定
	 */
	public String getTakeOverId() {
		return takeOverId;
	}

	/**
	 * 引き継ぎ設定を設定
	 *
	 * @param takeOverId 引き継ぎ設定
	 */
	public void setTakeOverId(String takeOverId) {
		this.takeOverId = takeOverId;
	}

	/**
	 * 引き継ぎ設定を設定
	 *
	 * @param takeOverId 引き継ぎ設定
	 * @return this
	 */
	public TakeOver withTakeOverId(String takeOverId) {
		this.takeOverId = takeOverId;
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
	public TakeOver withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** スロット番号 */
	protected Integer type;

	/**
	 * スロット番号を取得
	 *
	 * @return スロット番号
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * スロット番号を設定
	 *
	 * @param type スロット番号
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * スロット番号を設定
	 *
	 * @param type スロット番号
	 * @return this
	 */
	public TakeOver withType(Integer type) {
		this.type = type;
		return this;
	}
	/** 引き継ぎ用ユーザーID */
	protected String userIdentifier;

	/**
	 * 引き継ぎ用ユーザーIDを取得
	 *
	 * @return 引き継ぎ用ユーザーID
	 */
	public String getUserIdentifier() {
		return userIdentifier;
	}

	/**
	 * 引き継ぎ用ユーザーIDを設定
	 *
	 * @param userIdentifier 引き継ぎ用ユーザーID
	 */
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	/**
	 * 引き継ぎ用ユーザーIDを設定
	 *
	 * @param userIdentifier 引き継ぎ用ユーザーID
	 * @return this
	 */
	public TakeOver withUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
		return this;
	}
	/** パスワード */
	protected String password;

	/**
	 * パスワードを取得
	 *
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードを設定
	 *
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * パスワードを設定
	 *
	 * @param password パスワード
	 * @return this
	 */
	public TakeOver withPassword(String password) {
		this.password = password;
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
	public TakeOver withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("takeOverId", this.getTakeOverId())
            .put("userId", this.getUserId())
            .put("type", this.getType())
            .put("userIdentifier", this.getUserIdentifier())
            .put("password", this.getPassword())
            .put("createdAt", this.getCreatedAt());
        return body_;
    }
	@Override
	public int compareTo(TakeOver o) {
		return takeOverId.compareTo(o.takeOverId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.takeOverId == null) ? 0 : this.takeOverId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.userIdentifier == null) ? 0 : this.userIdentifier.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
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
		TakeOver other = (TakeOver) o;
		if (takeOverId == null) {
			return other.takeOverId == null;
		} else if (!takeOverId.equals(other.takeOverId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (userIdentifier == null) {
			return other.userIdentifier == null;
		} else if (!userIdentifier.equals(other.userIdentifier)) {
			return false;
		}
		if (password == null) {
			return other.password == null;
		} else if (!password.equals(other.password)) {
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