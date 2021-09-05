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
public class DisplayItem implements IModel, Serializable {
	private String displayItemId;
	private String type;
	private SalesItem salesItem;
	private SalesItemGroup salesItemGroup;
	private String salesPeriodEventId;

	public String getDisplayItemId() {
		return displayItemId;
	}

	public void setDisplayItemId(String displayItemId) {
		this.displayItemId = displayItemId;
	}

	public DisplayItem withDisplayItemId(String displayItemId) {
		this.displayItemId = displayItemId;
		return this;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DisplayItem withType(String type) {
		this.type = type;
		return this;
	}

	public SalesItem getSalesItem() {
		return salesItem;
	}

	public void setSalesItem(SalesItem salesItem) {
		this.salesItem = salesItem;
	}

	public DisplayItem withSalesItem(SalesItem salesItem) {
		this.salesItem = salesItem;
		return this;
	}

	public SalesItemGroup getSalesItemGroup() {
		return salesItemGroup;
	}

	public void setSalesItemGroup(SalesItemGroup salesItemGroup) {
		this.salesItemGroup = salesItemGroup;
	}

	public DisplayItem withSalesItemGroup(SalesItemGroup salesItemGroup) {
		this.salesItemGroup = salesItemGroup;
		return this;
	}

	public String getSalesPeriodEventId() {
		return salesPeriodEventId;
	}

	public void setSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
	}

	public DisplayItem withSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
		return this;
	}

    public static DisplayItem fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DisplayItem()
            .withDisplayItemId(data.get("displayItemId") == null || data.get("displayItemId").isNull() ? null : data.get("displayItemId").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withSalesItem(data.get("salesItem") == null || data.get("salesItem").isNull() ? null : SalesItem.fromJson(data.get("salesItem")))
            .withSalesItemGroup(data.get("salesItemGroup") == null || data.get("salesItemGroup").isNull() ? null : SalesItemGroup.fromJson(data.get("salesItemGroup")))
            .withSalesPeriodEventId(data.get("salesPeriodEventId") == null || data.get("salesPeriodEventId").isNull() ? null : data.get("salesPeriodEventId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("displayItemId", getDisplayItemId());
                put("type", getType());
                put("salesItem", getSalesItem() != null ? getSalesItem().toJson() : null);
                put("salesItemGroup", getSalesItemGroup() != null ? getSalesItemGroup().toJson() : null);
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
        result = prime * result + ((this.salesItem == null) ? 0 : this.salesItem.hashCode());
        result = prime * result + ((this.salesItemGroup == null) ? 0 : this.salesItemGroup.hashCode());
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
		DisplayItem other = (DisplayItem) o;
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
		if (salesItem == null) {
			return other.salesItem == null;
		} else if (!salesItem.equals(other.salesItem)) {
			return false;
		}
		if (salesItemGroup == null) {
			return other.salesItemGroup == null;
		} else if (!salesItemGroup.equals(other.salesItemGroup)) {
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