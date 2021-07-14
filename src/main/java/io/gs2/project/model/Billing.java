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
public class Billing implements IModel, Serializable, Comparable<Billing> {
	private String billingId;
	private String projectName;
	private Integer year;
	private Integer month;
	private String region;
	private String service;
	private String activityType;
	private Long unit;
	private String unitName;
	private Long price;
	private String currency;
	private Long createdAt;
	private Long updatedAt;

	public String getBillingId() {
		return billingId;
	}

	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}

	public Billing withBillingId(String billingId) {
		this.billingId = billingId;
		return this;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Billing withProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Billing withYear(Integer year) {
		this.year = year;
		return this;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Billing withMonth(Integer month) {
		this.month = month;
		return this;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Billing withRegion(String region) {
		this.region = region;
		return this;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Billing withService(String service) {
		this.service = service;
		return this;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public Billing withActivityType(String activityType) {
		this.activityType = activityType;
		return this;
	}

	public Long getUnit() {
		return unit;
	}

	public void setUnit(Long unit) {
		this.unit = unit;
	}

	public Billing withUnit(Long unit) {
		this.unit = unit;
		return this;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Billing withUnitName(String unitName) {
		this.unitName = unitName;
		return this;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Billing withPrice(Long price) {
		this.price = price;
		return this;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Billing withCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Billing withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Billing withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Billing fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Billing()
            .withBillingId(data.get("billingId") == null || data.get("billingId").isNull() ? null : data.get("billingId").asText())
            .withProjectName(data.get("projectName") == null || data.get("projectName").isNull() ? null : data.get("projectName").asText())
            .withYear(data.get("year") == null || data.get("year").isNull() ? null : data.get("year").intValue())
            .withMonth(data.get("month") == null || data.get("month").isNull() ? null : data.get("month").intValue())
            .withRegion(data.get("region") == null || data.get("region").isNull() ? null : data.get("region").asText())
            .withService(data.get("service") == null || data.get("service").isNull() ? null : data.get("service").asText())
            .withActivityType(data.get("activityType") == null || data.get("activityType").isNull() ? null : data.get("activityType").asText())
            .withUnit(data.get("unit") == null || data.get("unit").isNull() ? null : data.get("unit").longValue())
            .withUnitName(data.get("unitName") == null || data.get("unitName").isNull() ? null : data.get("unitName").asText())
            .withPrice(data.get("price") == null || data.get("price").isNull() ? null : data.get("price").longValue())
            .withCurrency(data.get("currency") == null || data.get("currency").isNull() ? null : data.get("currency").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("billingId", getBillingId());
                put("projectName", getProjectName());
                put("year", getYear());
                put("month", getMonth());
                put("region", getRegion());
                put("service", getService());
                put("activityType", getActivityType());
                put("unit", getUnit());
                put("unitName", getUnitName());
                put("price", getPrice());
                put("currency", getCurrency());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
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