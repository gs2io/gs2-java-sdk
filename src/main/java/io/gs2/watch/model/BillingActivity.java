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

package io.gs2.watch.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 請求にまつわるアクティビティ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BillingActivity implements IModel, Serializable, Comparable<BillingActivity> {
	/** 請求にまつわるアクティビティ */
	protected String billingActivityId;

	/**
	 * 請求にまつわるアクティビティを取得
	 *
	 * @return 請求にまつわるアクティビティ
	 */
	public String getBillingActivityId() {
		return billingActivityId;
	}

	/**
	 * 請求にまつわるアクティビティを設定
	 *
	 * @param billingActivityId 請求にまつわるアクティビティ
	 */
	public void setBillingActivityId(String billingActivityId) {
		this.billingActivityId = billingActivityId;
	}

	/**
	 * 請求にまつわるアクティビティを設定
	 *
	 * @param billingActivityId 請求にまつわるアクティビティ
	 * @return this
	 */
	public BillingActivity withBillingActivityId(String billingActivityId) {
		this.billingActivityId = billingActivityId;
		return this;
	}
	/** オーナーID */
	protected String ownerId;

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 * @return this
	 */
	public BillingActivity withOwnerId(String ownerId) {
		this.ownerId = ownerId;
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
	public BillingActivity withYear(Integer year) {
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
	public BillingActivity withMonth(Integer month) {
		this.month = month;
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
	public BillingActivity withService(String service) {
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
	public BillingActivity withActivityType(String activityType) {
		this.activityType = activityType;
		return this;
	}
	/** イベントの値 */
	protected Long value;

	/**
	 * イベントの値を取得
	 *
	 * @return イベントの値
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * イベントの値を設定
	 *
	 * @param value イベントの値
	 */
	public void setValue(Long value) {
		this.value = value;
	}

	/**
	 * イベントの値を設定
	 *
	 * @param value イベントの値
	 * @return this
	 */
	public BillingActivity withValue(Long value) {
		this.value = value;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("billingActivityId", this.getBillingActivityId())
            .put("ownerId", this.getOwnerId())
            .put("year", this.getYear())
            .put("month", this.getMonth())
            .put("service", this.getService())
            .put("activityType", this.getActivityType())
            .put("value", this.getValue());
        return body_;
    }
	@Override
	public int compareTo(BillingActivity o) {
		return billingActivityId.compareTo(o.billingActivityId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.billingActivityId == null) ? 0 : this.billingActivityId.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.year == null) ? 0 : this.year.hashCode());
        result = prime * result + ((this.month == null) ? 0 : this.month.hashCode());
        result = prime * result + ((this.service == null) ? 0 : this.service.hashCode());
        result = prime * result + ((this.activityType == null) ? 0 : this.activityType.hashCode());
        result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
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
		BillingActivity other = (BillingActivity) o;
		if (billingActivityId == null) {
			return other.billingActivityId == null;
		} else if (!billingActivityId.equals(other.billingActivityId)) {
			return false;
		}
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
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
		if (value == null) {
			return other.value == null;
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}