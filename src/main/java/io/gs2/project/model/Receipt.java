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
import io.gs2.core.model.IModel;

/**
 * 領収書
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Receipt implements IModel, Serializable, Comparable<Receipt> {
	/** 領収書 */
	protected String receiptId;

	/**
	 * 領収書を取得
	 *
	 * @return 領収書
	 */
	public String getReceiptId() {
		return receiptId;
	}

	/**
	 * 領収書を設定
	 *
	 * @param receiptId 領収書
	 */
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	/**
	 * 領収書を設定
	 *
	 * @param receiptId 領収書
	 * @return this
	 */
	public Receipt withReceiptId(String receiptId) {
		this.receiptId = receiptId;
		return this;
	}
	/** GS2アカウントの名前 */
	protected String accountName;

	/**
	 * GS2アカウントの名前を取得
	 *
	 * @return GS2アカウントの名前
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * GS2アカウントの名前を設定
	 *
	 * @param accountName GS2アカウントの名前
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * GS2アカウントの名前を設定
	 *
	 * @param accountName GS2アカウントの名前
	 * @return this
	 */
	public Receipt withAccountName(String accountName) {
		this.accountName = accountName;
		return this;
	}
	/** 請求書名 */
	protected String name;

	/**
	 * 請求書名を取得
	 *
	 * @return 請求書名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 請求書名を設定
	 *
	 * @param name 請求書名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 請求書名を設定
	 *
	 * @param name 請求書名
	 * @return this
	 */
	public Receipt withName(String name) {
		this.name = name;
		return this;
	}
	/** 請求月 */
	protected Long date;

	/**
	 * 請求月を取得
	 *
	 * @return 請求月
	 */
	public Long getDate() {
		return date;
	}

	/**
	 * 請求月を設定
	 *
	 * @param date 請求月
	 */
	public void setDate(Long date) {
		this.date = date;
	}

	/**
	 * 請求月を設定
	 *
	 * @param date 請求月
	 * @return this
	 */
	public Receipt withDate(Long date) {
		this.date = date;
		return this;
	}
	/** 請求金額 */
	protected String amount;

	/**
	 * 請求金額を取得
	 *
	 * @return 請求金額
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * 請求金額を設定
	 *
	 * @param amount 請求金額
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * 請求金額を設定
	 *
	 * @param amount 請求金額
	 * @return this
	 */
	public Receipt withAmount(String amount) {
		this.amount = amount;
		return this;
	}
	/** PDF URL */
	protected String pdfUrl;

	/**
	 * PDF URLを取得
	 *
	 * @return PDF URL
	 */
	public String getPdfUrl() {
		return pdfUrl;
	}

	/**
	 * PDF URLを設定
	 *
	 * @param pdfUrl PDF URL
	 */
	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	/**
	 * PDF URLを設定
	 *
	 * @param pdfUrl PDF URL
	 * @return this
	 */
	public Receipt withPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
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
	public Receipt withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("receiptId", this.getReceiptId())
            .put("accountName", this.getAccountName())
            .put("name", this.getName())
            .put("date", this.getDate())
            .put("amount", this.getAmount())
            .put("pdfUrl", this.getPdfUrl())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
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
        result = prime * result + ((this.accountName == null) ? 0 : this.accountName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.date == null) ? 0 : this.date.hashCode());
        result = prime * result + ((this.amount == null) ? 0 : this.amount.hashCode());
        result = prime * result + ((this.pdfUrl == null) ? 0 : this.pdfUrl.hashCode());
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
		Receipt other = (Receipt) o;
		if (receiptId == null) {
			return other.receiptId == null;
		} else if (!receiptId.equals(other.receiptId)) {
			return false;
		}
		if (accountName == null) {
			return other.accountName == null;
		} else if (!accountName.equals(other.accountName)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (date == null) {
			return other.date == null;
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (amount == null) {
			return other.amount == null;
		} else if (!amount.equals(other.amount)) {
			return false;
		}
		if (pdfUrl == null) {
			return other.pdfUrl == null;
		} else if (!pdfUrl.equals(other.pdfUrl)) {
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