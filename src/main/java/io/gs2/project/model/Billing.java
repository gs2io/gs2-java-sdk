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
 * 利用状況
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Billing implements IModel, Serializable, Comparable<Billing> {
	/** 利用状況 */
	protected String billingId;

	/**
	 * 利用状況を取得
	 *
	 * @return 利用状況
	 */
	public String getBillingId() {
		return billingId;
	}

	/**
	 * 利用状況を設定
	 *
	 * @param billingId 利用状況
	 */
	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}

	/**
	 * 利用状況を設定
	 *
	 * @param billingId 利用状況
	 * @return this
	 */
	public Billing withBillingId(String billingId) {
		this.billingId = billingId;
		return this;
	}
	/** プロジェクト名 */
	protected String projectName;

	/**
	 * プロジェクト名を取得
	 *
	 * @return プロジェクト名
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * プロジェクト名を設定
	 *
	 * @param projectName プロジェクト名
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * プロジェクト名を設定
	 *
	 * @param projectName プロジェクト名
	 * @return this
	 */
	public Billing withProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}
	/** イベントの発生年 */
	protected Integer year;

	/**
	 * イベントの発生年を取得
	 *
	 * @return イベントの発生年
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * イベントの発生年を設定
	 *
	 * @param year イベントの発生年
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * イベントの発生年を設定
	 *
	 * @param year イベントの発生年
	 * @return this
	 */
	public Billing withYear(Integer year) {
		this.year = year;
		return this;
	}
	/** イベントの発生月 */
	protected Integer month;

	/**
	 * イベントの発生月を取得
	 *
	 * @return イベントの発生月
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * イベントの発生月を設定
	 *
	 * @param month イベントの発生月
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * イベントの発生月を設定
	 *
	 * @param month イベントの発生月
	 * @return this
	 */
	public Billing withMonth(Integer month) {
		this.month = month;
		return this;
	}
	/** リージョン */
	protected String region;

	/**
	 * リージョンを取得
	 *
	 * @return リージョン
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * リージョンを設定
	 *
	 * @param region リージョン
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * リージョンを設定
	 *
	 * @param region リージョン
	 * @return this
	 */
	public Billing withRegion(String region) {
		this.region = region;
		return this;
	}
	/** サービスの種類 */
	protected String service;

	/**
	 * サービスの種類を取得
	 *
	 * @return サービスの種類
	 */
	public String getService() {
		return service;
	}

	/**
	 * サービスの種類を設定
	 *
	 * @param service サービスの種類
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * サービスの種類を設定
	 *
	 * @param service サービスの種類
	 * @return this
	 */
	public Billing withService(String service) {
		this.service = service;
		return this;
	}
	/** イベントの種類 */
	protected String activityType;

	/**
	 * イベントの種類を取得
	 *
	 * @return イベントの種類
	 */
	public String getActivityType() {
		return activityType;
	}

	/**
	 * イベントの種類を設定
	 *
	 * @param activityType イベントの種類
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	/**
	 * イベントの種類を設定
	 *
	 * @param activityType イベントの種類
	 * @return this
	 */
	public Billing withActivityType(String activityType) {
		this.activityType = activityType;
		return this;
	}
	/** 回数 */
	protected Long unit;

	/**
	 * 回数を取得
	 *
	 * @return 回数
	 */
	public Long getUnit() {
		return unit;
	}

	/**
	 * 回数を設定
	 *
	 * @param unit 回数
	 */
	public void setUnit(Long unit) {
		this.unit = unit;
	}

	/**
	 * 回数を設定
	 *
	 * @param unit 回数
	 * @return this
	 */
	public Billing withUnit(Long unit) {
		this.unit = unit;
		return this;
	}
	/** 単位 */
	protected String unitName;

	/**
	 * 単位を取得
	 *
	 * @return 単位
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位を設定
	 *
	 * @param unitName 単位
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 単位を設定
	 *
	 * @param unitName 単位
	 * @return this
	 */
	public Billing withUnitName(String unitName) {
		this.unitName = unitName;
		return this;
	}
	/** 料金 */
	protected Long price;

	/**
	 * 料金を取得
	 *
	 * @return 料金
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * 料金を設定
	 *
	 * @param price 料金
	 */
	public void setPrice(Long price) {
		this.price = price;
	}

	/**
	 * 料金を設定
	 *
	 * @param price 料金
	 * @return this
	 */
	public Billing withPrice(Long price) {
		this.price = price;
		return this;
	}
	/** 通貨 */
	protected String currency;

	/**
	 * 通貨を取得
	 *
	 * @return 通貨
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * 通貨を設定
	 *
	 * @param currency 通貨
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * 通貨を設定
	 *
	 * @param currency 通貨
	 * @return this
	 */
	public Billing withCurrency(String currency) {
		this.currency = currency;
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
	public Billing withCreatedAt(Long createdAt) {
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
	public Billing withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("billingId", this.getBillingId())
            .put("projectName", this.getProjectName())
            .put("year", this.getYear())
            .put("month", this.getMonth())
            .put("region", this.getRegion())
            .put("service", this.getService())
            .put("activityType", this.getActivityType())
            .put("unit", this.getUnit())
            .put("unitName", this.getUnitName())
            .put("price", this.getPrice())
            .put("currency", this.getCurrency())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Billing o) {
		return billingId.compareTo(o.billingId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.billingId == null) ? 0 : this.billingId.hashCode());
        result = prime * result + ((this.projectName == null) ? 0 : this.projectName.hashCode());
        result = prime * result + ((this.year == null) ? 0 : this.year.hashCode());
        result = prime * result + ((this.month == null) ? 0 : this.month.hashCode());
        result = prime * result + ((this.region == null) ? 0 : this.region.hashCode());
        result = prime * result + ((this.service == null) ? 0 : this.service.hashCode());
        result = prime * result + ((this.activityType == null) ? 0 : this.activityType.hashCode());
        result = prime * result + ((this.unit == null) ? 0 : this.unit.hashCode());
        result = prime * result + ((this.unitName == null) ? 0 : this.unitName.hashCode());
        result = prime * result + ((this.price == null) ? 0 : this.price.hashCode());
        result = prime * result + ((this.currency == null) ? 0 : this.currency.hashCode());
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
		Billing other = (Billing) o;
		if (billingId == null) {
			return other.billingId == null;
		} else if (!billingId.equals(other.billingId)) {
			return false;
		}
		if (projectName == null) {
			return other.projectName == null;
		} else if (!projectName.equals(other.projectName)) {
			return false;
		}
		if (year == null) {
			return other.year == null;
		} else if (!year.equals(other.year)) {
			return false;
		}
		if (month == null) {
			return other.month == null;
		} else if (!month.equals(other.month)) {
			return false;
		}
		if (region == null) {
			return other.region == null;
		} else if (!region.equals(other.region)) {
			return false;
		}
		if (service == null) {
			return other.service == null;
		} else if (!service.equals(other.service)) {
			return false;
		}
		if (activityType == null) {
			return other.activityType == null;
		} else if (!activityType.equals(other.activityType)) {
			return false;
		}
		if (unit == null) {
			return other.unit == null;
		} else if (!unit.equals(other.unit)) {
			return false;
		}
		if (unitName == null) {
			return other.unitName == null;
		} else if (!unitName.equals(other.unitName)) {
			return false;
		}
		if (price == null) {
			return other.price == null;
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (currency == null) {
			return other.currency == null;
		} else if (!currency.equals(other.currency)) {
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