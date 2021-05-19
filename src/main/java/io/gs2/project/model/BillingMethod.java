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
 * 支払い方法
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BillingMethod implements IModel, Serializable, Comparable<BillingMethod> {
	/** 支払い方法 */
	protected String billingMethodId;

	/**
	 * 支払い方法を取得
	 *
	 * @return 支払い方法
	 */
	public String getBillingMethodId() {
		return billingMethodId;
	}

	/**
	 * 支払い方法を設定
	 *
	 * @param billingMethodId 支払い方法
	 */
	public void setBillingMethodId(String billingMethodId) {
		this.billingMethodId = billingMethodId;
	}

	/**
	 * 支払い方法を設定
	 *
	 * @param billingMethodId 支払い方法
	 * @return this
	 */
	public BillingMethod withBillingMethodId(String billingMethodId) {
		this.billingMethodId = billingMethodId;
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
	public BillingMethod withAccountName(String accountName) {
		this.accountName = accountName;
		return this;
	}
	/** 名前 */
	protected String name;

	/**
	 * 名前を取得
	 *
	 * @return 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名前を設定
	 *
	 * @param name 名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 名前を設定
	 *
	 * @param name 名前
	 * @return this
	 */
	public BillingMethod withName(String name) {
		this.name = name;
		return this;
	}
	/** 名前 */
	protected String description;

	/**
	 * 名前を取得
	 *
	 * @return 名前
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 名前を設定
	 *
	 * @param description 名前
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 名前を設定
	 *
	 * @param description 名前
	 * @return this
	 */
	public BillingMethod withDescription(String description) {
		this.description = description;
		return this;
	}
	/** 支払い方法 */
	protected String methodType;

	/**
	 * 支払い方法を取得
	 *
	 * @return 支払い方法
	 */
	public String getMethodType() {
		return methodType;
	}

	/**
	 * 支払い方法を設定
	 *
	 * @param methodType 支払い方法
	 */
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	/**
	 * 支払い方法を設定
	 *
	 * @param methodType 支払い方法
	 * @return this
	 */
	public BillingMethod withMethodType(String methodType) {
		this.methodType = methodType;
		return this;
	}
	/** クレジットカードカスタマーID */
	protected String cardCustomerId;

	/**
	 * クレジットカードカスタマーIDを取得
	 *
	 * @return クレジットカードカスタマーID
	 */
	public String getCardCustomerId() {
		return cardCustomerId;
	}

	/**
	 * クレジットカードカスタマーIDを設定
	 *
	 * @param cardCustomerId クレジットカードカスタマーID
	 */
	public void setCardCustomerId(String cardCustomerId) {
		this.cardCustomerId = cardCustomerId;
	}

	/**
	 * クレジットカードカスタマーIDを設定
	 *
	 * @param cardCustomerId クレジットカードカスタマーID
	 * @return this
	 */
	public BillingMethod withCardCustomerId(String cardCustomerId) {
		this.cardCustomerId = cardCustomerId;
		return this;
	}
	/** カード署名 */
	protected String cardSignatureName;

	/**
	 * カード署名を取得
	 *
	 * @return カード署名
	 */
	public String getCardSignatureName() {
		return cardSignatureName;
	}

	/**
	 * カード署名を設定
	 *
	 * @param cardSignatureName カード署名
	 */
	public void setCardSignatureName(String cardSignatureName) {
		this.cardSignatureName = cardSignatureName;
	}

	/**
	 * カード署名を設定
	 *
	 * @param cardSignatureName カード署名
	 * @return this
	 */
	public BillingMethod withCardSignatureName(String cardSignatureName) {
		this.cardSignatureName = cardSignatureName;
		return this;
	}
	/** カードブランド */
	protected String cardBrand;

	/**
	 * カードブランドを取得
	 *
	 * @return カードブランド
	 */
	public String getCardBrand() {
		return cardBrand;
	}

	/**
	 * カードブランドを設定
	 *
	 * @param cardBrand カードブランド
	 */
	public void setCardBrand(String cardBrand) {
		this.cardBrand = cardBrand;
	}

	/**
	 * カードブランドを設定
	 *
	 * @param cardBrand カードブランド
	 * @return this
	 */
	public BillingMethod withCardBrand(String cardBrand) {
		this.cardBrand = cardBrand;
		return this;
	}
	/** 末尾4桁 */
	protected String cardLast4;

	/**
	 * 末尾4桁を取得
	 *
	 * @return 末尾4桁
	 */
	public String getCardLast4() {
		return cardLast4;
	}

	/**
	 * 末尾4桁を設定
	 *
	 * @param cardLast4 末尾4桁
	 */
	public void setCardLast4(String cardLast4) {
		this.cardLast4 = cardLast4;
	}

	/**
	 * 末尾4桁を設定
	 *
	 * @param cardLast4 末尾4桁
	 * @return this
	 */
	public BillingMethod withCardLast4(String cardLast4) {
		this.cardLast4 = cardLast4;
		return this;
	}
	/** パートナーID */
	protected String partnerId;

	/**
	 * パートナーIDを取得
	 *
	 * @return パートナーID
	 */
	public String getPartnerId() {
		return partnerId;
	}

	/**
	 * パートナーIDを設定
	 *
	 * @param partnerId パートナーID
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * パートナーIDを設定
	 *
	 * @param partnerId パートナーID
	 * @return this
	 */
	public BillingMethod withPartnerId(String partnerId) {
		this.partnerId = partnerId;
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
	public BillingMethod withCreatedAt(Long createdAt) {
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
	public BillingMethod withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("billingMethodId", this.getBillingMethodId())
            .put("accountName", this.getAccountName())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("methodType", this.getMethodType())
            .put("cardCustomerId", this.getCardCustomerId())
            .put("cardSignatureName", this.getCardSignatureName())
            .put("cardBrand", this.getCardBrand())
            .put("cardLast4", this.getCardLast4())
            .put("partnerId", this.getPartnerId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(BillingMethod o) {
		return billingMethodId.compareTo(o.billingMethodId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.billingMethodId == null) ? 0 : this.billingMethodId.hashCode());
        result = prime * result + ((this.accountName == null) ? 0 : this.accountName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.methodType == null) ? 0 : this.methodType.hashCode());
        result = prime * result + ((this.cardCustomerId == null) ? 0 : this.cardCustomerId.hashCode());
        result = prime * result + ((this.cardSignatureName == null) ? 0 : this.cardSignatureName.hashCode());
        result = prime * result + ((this.cardBrand == null) ? 0 : this.cardBrand.hashCode());
        result = prime * result + ((this.cardLast4 == null) ? 0 : this.cardLast4.hashCode());
        result = prime * result + ((this.partnerId == null) ? 0 : this.partnerId.hashCode());
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
		BillingMethod other = (BillingMethod) o;
		if (billingMethodId == null) {
			return other.billingMethodId == null;
		} else if (!billingMethodId.equals(other.billingMethodId)) {
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
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (methodType == null) {
			return other.methodType == null;
		} else if (!methodType.equals(other.methodType)) {
			return false;
		}
		if (cardCustomerId == null) {
			return other.cardCustomerId == null;
		} else if (!cardCustomerId.equals(other.cardCustomerId)) {
			return false;
		}
		if (cardSignatureName == null) {
			return other.cardSignatureName == null;
		} else if (!cardSignatureName.equals(other.cardSignatureName)) {
			return false;
		}
		if (cardBrand == null) {
			return other.cardBrand == null;
		} else if (!cardBrand.equals(other.cardBrand)) {
			return false;
		}
		if (cardLast4 == null) {
			return other.cardLast4 == null;
		} else if (!cardLast4.equals(other.cardLast4)) {
			return false;
		}
		if (partnerId == null) {
			return other.partnerId == null;
		} else if (!partnerId.equals(other.partnerId)) {
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