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

package io.gs2.money.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * ウォレット
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Wallet implements IModel, Serializable, Comparable<Wallet> {
	/** ウォレット */
	protected String walletId;

	/**
	 * ウォレットを取得
	 *
	 * @return ウォレット
	 */
	public String getWalletId() {
		return walletId;
	}

	/**
	 * ウォレットを設定
	 *
	 * @param walletId ウォレット
	 */
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	/**
	 * ウォレットを設定
	 *
	 * @param walletId ウォレット
	 * @return this
	 */
	public Wallet withWalletId(String walletId) {
		this.walletId = walletId;
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
	public Wallet withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** スロット番号 */
	protected Integer slot;

	/**
	 * スロット番号を取得
	 *
	 * @return スロット番号
	 */
	public Integer getSlot() {
		return slot;
	}

	/**
	 * スロット番号を設定
	 *
	 * @param slot スロット番号
	 */
	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	/**
	 * スロット番号を設定
	 *
	 * @param slot スロット番号
	 * @return this
	 */
	public Wallet withSlot(Integer slot) {
		this.slot = slot;
		return this;
	}
	/** 有償課金通貨所持量 */
	protected Integer paid;

	/**
	 * 有償課金通貨所持量を取得
	 *
	 * @return 有償課金通貨所持量
	 */
	public Integer getPaid() {
		return paid;
	}

	/**
	 * 有償課金通貨所持量を設定
	 *
	 * @param paid 有償課金通貨所持量
	 */
	public void setPaid(Integer paid) {
		this.paid = paid;
	}

	/**
	 * 有償課金通貨所持量を設定
	 *
	 * @param paid 有償課金通貨所持量
	 * @return this
	 */
	public Wallet withPaid(Integer paid) {
		this.paid = paid;
		return this;
	}
	/** 無償課金通貨所持量 */
	protected Integer free;

	/**
	 * 無償課金通貨所持量を取得
	 *
	 * @return 無償課金通貨所持量
	 */
	public Integer getFree() {
		return free;
	}

	/**
	 * 無償課金通貨所持量を設定
	 *
	 * @param free 無償課金通貨所持量
	 */
	public void setFree(Integer free) {
		this.free = free;
	}

	/**
	 * 無償課金通貨所持量を設定
	 *
	 * @param free 無償課金通貨所持量
	 * @return this
	 */
	public Wallet withFree(Integer free) {
		this.free = free;
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
	public Wallet withCreatedAt(Long createdAt) {
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
	public Wallet withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("walletId", this.getWalletId())
            .put("userId", this.getUserId())
            .put("slot", this.getSlot())
            .put("paid", this.getPaid())
            .put("free", this.getFree())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Wallet o) {
		return walletId.compareTo(o.walletId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.walletId == null) ? 0 : this.walletId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.slot == null) ? 0 : this.slot.hashCode());
        result = prime * result + ((this.paid == null) ? 0 : this.paid.hashCode());
        result = prime * result + ((this.free == null) ? 0 : this.free.hashCode());
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
		Wallet other = (Wallet) o;
		if (walletId == null) {
			return other.walletId == null;
		} else if (!walletId.equals(other.walletId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (slot == null) {
			return other.slot == null;
		} else if (!slot.equals(other.slot)) {
			return false;
		}
		if (paid == null) {
			return other.paid == null;
		} else if (!paid.equals(other.paid)) {
			return false;
		}
		if (free == null) {
			return other.free == null;
		} else if (!free.equals(other.free)) {
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