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
 * ゲームプレイヤーアカウント
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Account implements IModel, Serializable, Comparable<Account> {
	/** ゲームプレイヤーアカウント */
	protected String accountId;

	/**
	 * ゲームプレイヤーアカウントを取得
	 *
	 * @return ゲームプレイヤーアカウント
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * ゲームプレイヤーアカウントを設定
	 *
	 * @param accountId ゲームプレイヤーアカウント
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * ゲームプレイヤーアカウントを設定
	 *
	 * @param accountId ゲームプレイヤーアカウント
	 * @return this
	 */
	public Account withAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}
	/** アカウントID */
	protected String userId;

	/**
	 * アカウントIDを取得
	 *
	 * @return アカウントID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * アカウントIDを設定
	 *
	 * @param userId アカウントID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * アカウントIDを設定
	 *
	 * @param userId アカウントID
	 * @return this
	 */
	public Account withUserId(String userId) {
		this.userId = userId;
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
	public Account withPassword(String password) {
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
	public Account withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("accountId", this.getAccountId())
            .put("userId", this.getUserId())
            .put("password", this.getPassword())
            .put("createdAt", this.getCreatedAt());
        return body_;
    }
	@Override
	public int compareTo(Account o) {
		return accountId.compareTo(o.accountId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.accountId == null) ? 0 : this.accountId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
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
		Account other = (Account) o;
		if (accountId == null) {
			return other.accountId == null;
		} else if (!accountId.equals(other.accountId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
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