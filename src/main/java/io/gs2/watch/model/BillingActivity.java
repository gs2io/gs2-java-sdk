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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BillingActivity implements IModel, Serializable, Comparable<BillingActivity> {
	private String billingActivityId;
	private Integer year;
	private Integer month;
	private String service;
	private String activityType;
	private Long value;

	public String getBillingActivityId() {
		return billingActivityId;
	}

	public void setBillingActivityId(String billingActivityId) {
		this.billingActivityId = billingActivityId;
	}

	public BillingActivity withBillingActivityId(String billingActivityId) {
		this.billingActivityId = billingActivityId;
		return this;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public BillingActivity withYear(Integer year) {
		this.year = year;
		return this;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public BillingActivity withMonth(Integer month) {
		this.month = month;
		return this;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public BillingActivity withService(String service) {
		this.service = service;
		return this;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public BillingActivity withActivityType(String activityType) {
		this.activityType = activityType;
		return this;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public BillingActivity withValue(Long value) {
		this.value = value;
		return this;
	}

    public static BillingActivity fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BillingActivity()
            .withBillingActivityId(data.get("billingActivityId") == null || data.get("billingActivityId").isNull() ? null : data.get("billingActivityId").asText())
            .withYear(data.get("year") == null || data.get("year").isNull() ? null : data.get("year").intValue())
            .withMonth(data.get("month") == null || data.get("month").isNull() ? null : data.get("month").intValue())
            .withService(data.get("service") == null || data.get("service").isNull() ? null : data.get("service").asText())
            .withActivityType(data.get("activityType") == null || data.get("activityType").isNull() ? null : data.get("activityType").asText())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("billingActivityId", getBillingActivityId());
                put("year", getYear());
                put("month", getMonth());
                put("service", getService());
                put("activityType", getActivityType());
                put("value", getValue());
            }}
        );
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