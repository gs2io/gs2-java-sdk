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

package io.gs2.showcase.model;

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
public class DisplayItemMaster implements IModel, Serializable {
	private String displayItemId;
	private String type;
	private String salesItemName;
	private String salesItemGroupName;
	private String salesPeriodEventId;
	public String getDisplayItemId() {
		return displayItemId;
	}
	public void setDisplayItemId(String displayItemId) {
		this.displayItemId = displayItemId;
	}
	public DisplayItemMaster withDisplayItemId(String displayItemId) {
		this.displayItemId = displayItemId;
		return this;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public DisplayItemMaster withType(String type) {
		this.type = type;
		return this;
	}
	public String getSalesItemName() {
		return salesItemName;
	}
	public void setSalesItemName(String salesItemName) {
		this.salesItemName = salesItemName;
	}
	public DisplayItemMaster withSalesItemName(String salesItemName) {
		this.salesItemName = salesItemName;
		return this;
	}
	public String getSalesItemGroupName() {
		return salesItemGroupName;
	}
	public void setSalesItemGroupName(String salesItemGroupName) {
		this.salesItemGroupName = salesItemGroupName;
	}
	public DisplayItemMaster withSalesItemGroupName(String salesItemGroupName) {
		this.salesItemGroupName = salesItemGroupName;
		return this;
	}
	public String getSalesPeriodEventId() {
		return salesPeriodEventId;
	}
	public void setSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
	}
	public DisplayItemMaster withSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
		return this;
	}

    public static DisplayItemMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DisplayItemMaster()
            .withDisplayItemId(data.get("displayItemId") == null || data.get("displayItemId").isNull() ? null : data.get("displayItemId").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withSalesItemName(data.get("salesItemName") == null || data.get("salesItemName").isNull() ? null : data.get("salesItemName").asText())
            .withSalesItemGroupName(data.get("salesItemGroupName") == null || data.get("salesItemGroupName").isNull() ? null : data.get("salesItemGroupName").asText())
            .withSalesPeriodEventId(data.get("salesPeriodEventId") == null || data.get("salesPeriodEventId").isNull() ? null : data.get("salesPeriodEventId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("displayItemId", getDisplayItemId());
                put("type", getType());
                put("salesItemName", getSalesItemName());
                put("salesItemGroupName", getSalesItemGroupName());
                put("salesPeriodEventId", getSalesPeriodEventId());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.displayItemId == null) ? 0 : this.displayItemId.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.salesItemName == null) ? 0 : this.salesItemName.hashCode());
        result = prime * result + ((this.salesItemGroupName == null) ? 0 : this.salesItemGroupName.hashCode());
        result = prime * result + ((this.salesPeriodEventId == null) ? 0 : this.salesPeriodEventId.hashCode());
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
		DisplayItemMaster other = (DisplayItemMaster) o;
		if (displayItemId == null) {
			return other.displayItemId == null;
		} else if (!displayItemId.equals(other.displayItemId)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (salesItemName == null) {
			return other.salesItemName == null;
		} else if (!salesItemName.equals(other.salesItemName)) {
			return false;
		}
		if (salesItemGroupName == null) {
			return other.salesItemGroupName == null;
		} else if (!salesItemGroupName.equals(other.salesItemGroupName)) {
			return false;
		}
		if (salesPeriodEventId == null) {
			return other.salesPeriodEventId == null;
		} else if (!salesPeriodEventId.equals(other.salesPeriodEventId)) {
			return false;
		}
		return true;
	}
}