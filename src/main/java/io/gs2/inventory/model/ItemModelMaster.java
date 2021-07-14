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

package io.gs2.inventory.model;

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
public class ItemModelMaster implements IModel, Serializable, Comparable<ItemModelMaster> {
	private String itemModelId;
	private String inventoryName;
	private String name;
	private String description;
	private String metadata;
	private Long stackingLimit;
	private Boolean allowMultipleStacks;
	private Integer sortValue;
	private Long createdAt;
	private Long updatedAt;

	public String getItemModelId() {
		return itemModelId;
	}

	public void setItemModelId(String itemModelId) {
		this.itemModelId = itemModelId;
	}

	public ItemModelMaster withItemModelId(String itemModelId) {
		this.itemModelId = itemModelId;
		return this;
	}

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public ItemModelMaster withInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemModelMaster withName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ItemModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public ItemModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public Long getStackingLimit() {
		return stackingLimit;
	}

	public void setStackingLimit(Long stackingLimit) {
		this.stackingLimit = stackingLimit;
	}

	public ItemModelMaster withStackingLimit(Long stackingLimit) {
		this.stackingLimit = stackingLimit;
		return this;
	}

	public Boolean getAllowMultipleStacks() {
		return allowMultipleStacks;
	}

	public void setAllowMultipleStacks(Boolean allowMultipleStacks) {
		this.allowMultipleStacks = allowMultipleStacks;
	}

	public ItemModelMaster withAllowMultipleStacks(Boolean allowMultipleStacks) {
		this.allowMultipleStacks = allowMultipleStacks;
		return this;
	}

	public Integer getSortValue() {
		return sortValue;
	}

	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}

	public ItemModelMaster withSortValue(Integer sortValue) {
		this.sortValue = sortValue;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public ItemModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ItemModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static ItemModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ItemModelMaster()
            .withItemModelId(data.get("itemModelId") == null || data.get("itemModelId").isNull() ? null : data.get("itemModelId").asText())
            .withInventoryName(data.get("inventoryName") == null || data.get("inventoryName").isNull() ? null : data.get("inventoryName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withStackingLimit(data.get("stackingLimit") == null || data.get("stackingLimit").isNull() ? null : data.get("stackingLimit").longValue())
            .withAllowMultipleStacks(data.get("allowMultipleStacks") == null || data.get("allowMultipleStacks").isNull() ? null : data.get("allowMultipleStacks").booleanValue())
            .withSortValue(data.get("sortValue") == null || data.get("sortValue").isNull() ? null : data.get("sortValue").intValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("itemModelId", getItemModelId());
                put("inventoryName", getInventoryName());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("stackingLimit", getStackingLimit());
                put("allowMultipleStacks", getAllowMultipleStacks());
                put("sortValue", getSortValue());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(ItemModelMaster o) {
		return itemModelId.compareTo(o.itemModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.itemModelId == null) ? 0 : this.itemModelId.hashCode());
        result = prime * result + ((this.inventoryName == null) ? 0 : this.inventoryName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.stackingLimit == null) ? 0 : this.stackingLimit.hashCode());
        result = prime * result + ((this.allowMultipleStacks == null) ? 0 : this.allowMultipleStacks.hashCode());
        result = prime * result + ((this.sortValue == null) ? 0 : this.sortValue.hashCode());
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
		ItemModelMaster other = (ItemModelMaster) o;
		if (itemModelId == null) {
			return other.itemModelId == null;
		} else if (!itemModelId.equals(other.itemModelId)) {
			return false;
		}
		if (inventoryName == null) {
			return other.inventoryName == null;
		} else if (!inventoryName.equals(other.inventoryName)) {
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
		if (stackingLimit == null) {
			return other.stackingLimit == null;
		} else if (!stackingLimit.equals(other.stackingLimit)) {
			return false;
		}
		if (allowMultipleStacks == null) {
			return other.allowMultipleStacks == null;
		} else if (!allowMultipleStacks.equals(other.allowMultipleStacks)) {
			return false;
		}
		if (sortValue == null) {
			return other.sortValue == null;
		} else if (!sortValue.equals(other.sortValue)) {
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