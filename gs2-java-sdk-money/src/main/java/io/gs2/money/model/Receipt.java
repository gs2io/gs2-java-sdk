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
 * レシート
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Receipt implements IModel, Serializable, Comparable<Receipt> {
	/** レシート */
	protected String receiptId;

	/**
	 * レシートを取得
	 *
	 * @return レシート
	 */
	public String getReceiptId() {
		return receiptId;
	}

	/**
	 * レシートを設定
	 *
	 * @param receiptId レシート
	 */
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	/**
	 * レシートを設定
	 *
	 * @param receiptId レシート
	 * @return this
	 */
	public Receipt withReceiptId(String receiptId) {
		this.receiptId = receiptId;
		return this;
	}
	/** トランザクションID */
	protected String transactionId;

	/**
	 * トランザクションIDを取得
	 *
	 * @return トランザクションID
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * トランザクションIDを設定
	 *
	 * @param transactionId トランザクションID
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * トランザクションIDを設定
	 *
	 * @param transactionId トランザクションID
	 * @return this
	 */
	public Receipt withTransactionId(String transactionId) {
		this.transactionId = transactionId;
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
	public Receipt withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 種類 */
	protected String type;

	/**
	 * 種類を取得
	 *
	 * @return 種類
	 */
	public String getType() {
		return type;
	}

	/**
	 * 種類を設定
	 *
	 * @param type 種類
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 種類を設定
	 *
	 * @param type 種類
	 * @return this
	 */
	public Receipt withType(String type) {
		this.type = type;
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
	public Receipt withSlot(Integer slot) {
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
	public Receipt withPrice(Float price) {
		this.price = price;
		return this;
	}
	/** 有償課金通貨 */
	protected Integer paid;

	/**
	 * 有償課金通貨を取得
	 *
	 * @return 有償課金通貨
	 */
	public Integer getPaid() {
		return paid;
	}

	/**
	 * 有償課金通貨を設定
	 *
	 * @param paid 有償課金通貨
	 */
	public void setPaid(Integer paid) {
		this.paid = paid;
	}

	/**
	 * 有償課金通貨を設定
	 *
	 * @param paid 有償課金通貨
	 * @return this
	 */
	public Receipt withPaid(Integer paid) {
		this.paid = paid;
		return this;
	}
	/** 無償課金通貨 */
	protected Integer free;

	/**
	 * 無償課金通貨を取得
	 *
	 * @return 無償課金通貨
	 */
	public Integer getFree() {
		return free;
	}

	/**
	 * 無償課金通貨を設定
	 *
	 * @param free 無償課金通貨
	 */
	public void setFree(Integer free) {
		this.free = free;
	}

	/**
	 * 無償課金通貨を設定
	 *
	 * @param free 無償課金通貨
	 * @return this
	 */
	public Receipt withFree(Integer free) {
		this.free = free;
		return this;
	}
	/** 総数 */
	protected Integer total;

	/**
	 * 総数を取得
	 *
	 * @return 総数
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * 総数を設定
	 *
	 * @param total 総数
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * 総数を設定
	 *
	 * @param total 総数
	 * @return this
	 */
	public Receipt withTotal(Integer total) {
		this.total = total;
		return this;
	}
	/** ストアプラットフォームで販売されているコンテンツID */
	protected String contentsId;

	/**
	 * ストアプラットフォームで販売されているコンテンツIDを取得
	 *
	 * @return ストアプラットフォームで販売されているコンテンツID
	 */
	public String getContentsId() {
		return contentsId;
	}

	/**
	 * ストアプラットフォームで販売されているコンテンツIDを設定
	 *
	 * @param contentsId ストアプラットフォームで販売されているコンテンツID
	 */
	public void setContentsId(String contentsId) {
		this.contentsId = contentsId;
	}

	/**
	 * ストアプラットフォームで販売されているコンテンツIDを設定
	 *
	 * @param contentsId ストアプラットフォームで販売されているコンテンツID
	 * @return this
	 */
	public Receipt withContentsId(String contentsId) {
		this.contentsId = contentsId;
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
	public Receipt withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("receiptId", this.getReceiptId())
            .put("transactionId", this.getTransactionId())
            .put("userId", this.getUserId())
            .put("type", this.getType())
            .put("slot", this.getSlot())
            .put("price", this.getPrice())
            .put("paid", this.getPaid())
            .put("free", this.getFree())
            .put("total", this.getTotal())
            .put("contentsId", this.getContentsId())
            .put("createdAt", this.getCreatedAt());
        return body_;
    }
	@Override
	public int compareTo(Receipt o) {
		return receiptId.compareTo(o.receiptId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.receiptId == null) ? 0 : this.receiptId.hashCode());
        result = prime * result + ((this.transactionId == null) ? 0 : this.transactionId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.slot == null) ? 0 : this.slot.hashCode());
        result = prime * result + ((this.price == null) ? 0 : this.price.hashCode());
        result = prime * result + ((this.paid == null) ? 0 : this.paid.hashCode());
        result = prime * result + ((this.free == null) ? 0 : this.free.hashCode());
        result = prime * result + ((this.total == null) ? 0 : this.total.hashCode());
        result = prime * result + ((this.contentsId == null) ? 0 : this.contentsId.hashCode());
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
		Receipt other = (Receipt) o;
		if (receiptId == null) {
			return other.receiptId == null;
		} else if (!receiptId.equals(other.receiptId)) {
			return false;
		}
		if (transactionId == null) {
			return other.transactionId == null;
		} else if (!transactionId.equals(other.transactionId)) {
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
		if (total == null) {
			return other.total == null;
		} else if (!total.equals(other.total)) {
			return false;
		}
		if (contentsId == null) {
			return other.contentsId == null;
		} else if (!contentsId.equals(other.contentsId)) {
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