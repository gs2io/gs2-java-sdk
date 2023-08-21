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
public class ShowcaseMaster implements IModel, Serializable, Comparable<ShowcaseMaster> {
	private String showcaseId;
	private String name;
	private String description;
	private String metadata;
	private String salesPeriodEventId;
	private List<DisplayItemMaster> displayItems;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getShowcaseId() {
		return showcaseId;
	}
	public void setShowcaseId(String showcaseId) {
		this.showcaseId = showcaseId;
	}
	public ShowcaseMaster withShowcaseId(String showcaseId) {
		this.showcaseId = showcaseId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ShowcaseMaster withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ShowcaseMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public ShowcaseMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getSalesPeriodEventId() {
		return salesPeriodEventId;
	}
	public void setSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
	}
	public ShowcaseMaster withSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
		return this;
	}
	public List<DisplayItemMaster> getDisplayItems() {
		return displayItems;
	}
	public void setDisplayItems(List<DisplayItemMaster> displayItems) {
		this.displayItems = displayItems;
	}
	public ShowcaseMaster withDisplayItems(List<DisplayItemMaster> displayItems) {
		this.displayItems = displayItems;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public ShowcaseMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public ShowcaseMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public ShowcaseMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static ShowcaseMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ShowcaseMaster()
            .withShowcaseId(data.get("showcaseId") == null || data.get("showcaseId").isNull() ? null : data.get("showcaseId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withSalesPeriodEventId(data.get("salesPeriodEventId") == null || data.get("salesPeriodEventId").isNull() ? null : data.get("salesPeriodEventId").asText())
            .withDisplayItems(data.get("displayItems") == null || data.get("displayItems").isNull() ? new ArrayList<DisplayItemMaster>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("displayItems").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return DisplayItemMaster.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("showcaseId", getShowcaseId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("salesPeriodEventId", getSalesPeriodEventId());
                put("displayItems", getDisplayItems() == null ? new ArrayList<DisplayItemMaster>() :
                    getDisplayItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(ShowcaseMaster o) {
		return showcaseId.compareTo(o.showcaseId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.showcaseId == null) ? 0 : this.showcaseId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.salesPeriodEventId == null) ? 0 : this.salesPeriodEventId.hashCode());
        result = prime * result + ((this.displayItems == null) ? 0 : this.displayItems.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.revision == null) ? 0 : this.revision.hashCode());
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
		ShowcaseMaster other = (ShowcaseMaster) o;
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
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
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
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}