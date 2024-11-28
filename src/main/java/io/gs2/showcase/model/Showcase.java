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
public class Showcase implements IModel, Serializable, Comparable<Showcase> {
	private String showcaseId;
	private String name;
	private String metadata;
	private String salesPeriodEventId;
	private List<DisplayItem> displayItems;
	public String getShowcaseId() {
		return showcaseId;
	}
	public void setShowcaseId(String showcaseId) {
		this.showcaseId = showcaseId;
	}
	public Showcase withShowcaseId(String showcaseId) {
		this.showcaseId = showcaseId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Showcase withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public Showcase withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getSalesPeriodEventId() {
		return salesPeriodEventId;
	}
	public void setSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
	}
	public Showcase withSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
		return this;
	}
	public List<DisplayItem> getDisplayItems() {
		return displayItems;
	}
	public void setDisplayItems(List<DisplayItem> displayItems) {
		this.displayItems = displayItems;
	}
	public Showcase withDisplayItems(List<DisplayItem> displayItems) {
		this.displayItems = displayItems;
		return this;
	}

    public static Showcase fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Showcase()
            .withShowcaseId(data.get("showcaseId") == null || data.get("showcaseId").isNull() ? null : data.get("showcaseId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withSalesPeriodEventId(data.get("salesPeriodEventId") == null || data.get("salesPeriodEventId").isNull() ? null : data.get("salesPeriodEventId").asText())
            .withDisplayItems(data.get("displayItems") == null || data.get("displayItems").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("displayItems").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return DisplayItem.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("showcaseId", getShowcaseId());
                put("name", getName());
                put("metadata", getMetadata());
                put("salesPeriodEventId", getSalesPeriodEventId());
                put("displayItems", getDisplayItems() == null ? null :
                    getDisplayItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(Showcase o) {
		return showcaseId.compareTo(o.showcaseId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.showcaseId == null) ? 0 : this.showcaseId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.salesPeriodEventId == null) ? 0 : this.salesPeriodEventId.hashCode());
        result = prime * result + ((this.displayItems == null) ? 0 : this.displayItems.hashCode());
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
		Showcase other = (Showcase) o;
		if (showcaseId == null) {
			return other.showcaseId == null;
		} else if (!showcaseId.equals(other.showcaseId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (salesPeriodEventId == null) {
			return other.salesPeriodEventId == null;
		} else if (!salesPeriodEventId.equals(other.salesPeriodEventId)) {
			return false;
		}
		if (displayItems == null) {
			return other.displayItems == null;
		} else if (!displayItems.equals(other.displayItems)) {
			return false;
		}
		return true;
	}
}