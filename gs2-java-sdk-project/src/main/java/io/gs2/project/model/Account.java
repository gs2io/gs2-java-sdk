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

package io.gs2.project.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * GS2アカウント
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Account implements IModel, Serializable, Comparable<Account> {
	/** GS2アカウント */
	protected String accountId;

	/**
	 * GS2アカウントを取得
	 *
	 * @return GS2アカウント
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * GS2アカウントを設定
	 *
	 * @param accountId GS2アカウント
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * GS2アカウントを設定
	 *
	 * @param accountId GS2アカウント
	 * @return this
	 */
	public Account withAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}
	/** None */
	protected String ownerId;

	/**
	 * Noneを取得
	 *
	 * @return None
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * Noneを設定
	 *
	 * @param ownerId None
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * Noneを設定
	 *
	 * @param ownerId None
	 * @return this
	 */
	public Account withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** GS2アカウントの名前 */
	protected String name;

	/**
	 * GS2アカウントの名前を取得
	 *
	 * @return GS2アカウントの名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * GS2アカウントの名前を設定
	 *
	 * @param name GS2アカウントの名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * GS2アカウントの名前を設定
	 *
	 * @param name GS2アカウントの名前
	 * @return this
	 */
	public Account withName(String name) {
		this.name = name;
		return this;
	}
	/** メールアドレス */
	protected String email;

	/**
	 * メールアドレスを取得
	 *
	 * @return メールアドレス
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * メールアドレスを設定
	 *
	 * @param email メールアドレス
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * メールアドレスを設定
	 *
	 * @param email メールアドレス
	 * @return this
	 */
	public Account withEmail(String email) {
		this.email = email;
		return this;
	}
	/** フルネーム */
	protected String fullName;

	/**
	 * フルネームを取得
	 *
	 * @return フルネーム
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * フルネームを設定
	 *
	 * @param fullName フルネーム
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * フルネームを設定
	 *
	 * @param fullName フルネーム
	 * @return this
	 */
	public Account withFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}
	/** 会社名 */
	protected String companyName;

	/**
	 * 会社名を取得
	 *
	 * @return 会社名
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 会社名を設定
	 *
	 * @param companyName 会社名
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 会社名を設定
	 *
	 * @param companyName 会社名
	 * @return this
	 */
	public Account withCompanyName(String companyName) {
		this.companyName = companyName;
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
	/** ステータス */
	protected String status;

	/**
	 * ステータスを取得
	 *
	 * @return ステータス
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * ステータスを設定
	 *
	 * @param status ステータス
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * ステータスを設定
	 *
	 * @param status ステータス
	 * @return this
	 */
	public Account withStatus(String status) {
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
	public Account withCreatedAt(Long createdAt) {
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
	public Account withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("accountId", this.getAccountId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("email", this.getEmail())
            .put("fullName", this.getFullName())
            .put("companyName", this.getCompanyName())
            .put("password", this.getPassword())
            .put("status", this.getStatus())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
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
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.fullName == null) ? 0 : this.fullName.hashCode());
        result = prime * result + ((this.companyName == null) ? 0 : this.companyName.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
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
		Account other = (Account) o;
		if (accountId == null) {
			return other.accountId == null;
		} else if (!accountId.equals(other.accountId)) {
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
		if (email == null) {
			return other.email == null;
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (fullName == null) {
			return other.fullName == null;
		} else if (!fullName.equals(other.fullName)) {
			return false;
		}
		if (companyName == null) {
			return other.companyName == null;
		} else if (!companyName.equals(other.companyName)) {
			return false;
		}
		if (password == null) {
			return other.password == null;
		} else if (!password.equals(other.password)) {
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