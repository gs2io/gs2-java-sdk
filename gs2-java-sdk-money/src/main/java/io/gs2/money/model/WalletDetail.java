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
 * ウォレットの詳細
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class WalletDetail implements IModel, Serializable, Comparable<WalletDetail> {
	/** ウォレットの詳細 */
	protected String walletDetailId;

	/**
	 * ウォレットの詳細を取得
	 *
	 * @return ウォレットの詳細
	 */
	public String getWalletDetailId() {
		return walletDetailId;
	}

	/**
	 * ウォレットの詳細を設定
	 *
	 * @param walletDetailId ウォレットの詳細
	 */
	public void setWalletDetailId(String walletDetailId) {
		this.walletDetailId = walletDetailId;
	}

	/**
	 * ウォレットの詳細を設定
	 *
	 * @param walletDetailId ウォレットの詳細
	 * @return this
	 */
	public WalletDetail withWalletDetailId(String walletDetailId) {
		this.walletDetailId = walletDetailId;
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
	public WalletDetail withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** None */
	protected Integer slot;

	/**
	 * Noneを取得
	 *
	 * @return None
	 */
	public Integer getSlot() {
		return slot;
	}

	/**
	 * Noneを設定
	 *
	 * @param slot None
	 */
	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	/**
	 * Noneを設定
	 *
	 * @param slot None
	 * @return this
	 */
	public WalletDetail withSlot(Integer slot) {
		this.slot = slot;
		return this;
	}
	/** 単価 */
	protected Float price;

	/**
	 * 単価を取得
	 *
	 * @return 単価
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * 単価を設定
	 *
	 * @param price 単価
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * 単価を設定
	 *
	 * @param price 単価
	 * @return this
	 */
	public WalletDetail withPrice(Float price) {
		this.price = price;
		return this;
	}
	/** 所持量 */
	protected Integer count;

	/**
	 * 所持量を取得
	 *
	 * @return 所持量
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 所持量を設定
	 *
	 * @param count 所持量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 所持量を設定
	 *
	 * @param count 所持量
	 * @return this
	 */
	public WalletDetail withCount(Integer count) {
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
	public WalletDetail withCreatedAt(Long createdAt) {
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
	public WalletDetail withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("walletDetailId", this.getWalletDetailId())
            .put("userId", this.getUserId())
            .put("slot", this.getSlot())
            .put("price", this.getPrice())
            .put("count", this.getCount())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(WalletDetail o) {
		return walletDetailId.compareTo(o.walletDetailId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.walletDetailId == null) ? 0 : this.walletDetailId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.slot == null) ? 0 : this.slot.hashCode());
        result = prime * result + ((this.price == null) ? 0 : this.price.hashCode());
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
		WalletDetail other = (WalletDetail) o;
		if (walletDetailId == null) {
			return other.walletDetailId == null;
		} else if (!walletDetailId.equals(other.walletDetailId)) {
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
		if (price == null) {
			return other.price == null;
		} else if (!price.equals(other.price)) {
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