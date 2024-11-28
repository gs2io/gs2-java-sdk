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
public class ItemSet implements IModel, Serializable, Comparable<ItemSet> {
	private String itemSetId;
	private String name;
	private String inventoryName;
	private String userId;
	private String itemName;
	private Long count;
	private List<String> referenceOf;
	private Integer sortValue;
	private Long expiresAt;
	private Long createdAt;
	private Long updatedAt;
	public String getItemSetId() {
		return itemSetId;
	}
	public void setItemSetId(String itemSetId) {
		this.itemSetId = itemSetId;
	}
	public ItemSet withItemSetId(String itemSetId) {
		this.itemSetId = itemSetId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ItemSet withName(String name) {
		this.name = name;
		return this;
	}
	public String getInventoryName() {
		return inventoryName;
	}
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	public ItemSet withInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ItemSet withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public ItemSet withItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public ItemSet withCount(Long count) {
		this.count = count;
		return this;
	}
	public List<String> getReferenceOf() {
		return referenceOf;
	}
	public void setReferenceOf(List<String> referenceOf) {
		this.referenceOf = referenceOf;
	}
	public ItemSet withReferenceOf(List<String> referenceOf) {
		this.referenceOf = referenceOf;
		return this;
	}
	public Integer getSortValue() {
		return sortValue;
	}
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}
	public ItemSet withSortValue(Integer sortValue) {
		this.sortValue = sortValue;
		return this;
	}
	public Long getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}
	public ItemSet withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public ItemSet withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public ItemSet withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static ItemSet fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ItemSet()
            .withItemSetId(data.get("itemSetId") == null || data.get("itemSetId").isNull() ? null : data.get("itemSetId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withInventoryName(data.get("inventoryName") == null || data.get("inventoryName").isNull() ? null : data.get("inventoryName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withItemName(data.get("itemName") == null || data.get("itemName").isNull() ? null : data.get("itemName").asText())
            .withCount(data.get("count") == null || data.get("count").isNull() ? null : data.get("count").longValue())
            .withReferenceOf(data.get("referenceOf") == null || data.get("referenceOf").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("referenceOf").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withSortValue(data.get("sortValue") == null || data.get("sortValue").isNull() ? null : data.get("sortValue").intValue())
            .withExpiresAt(data.get("expiresAt") == null || data.get("expiresAt").isNull() ? null : data.get("expiresAt").longValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("itemSetId", getItemSetId());
                put("name", getName());
                put("inventoryName", getInventoryName());
                put("userId", getUserId());
                put("itemName", getItemName());
                put("count", getCount());
                put("referenceOf", getReferenceOf() == null ? null :
                    getReferenceOf().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("sortValue", getSortValue());
                put("expiresAt", getExpiresAt());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(ItemSet o) {
		return itemSetId.compareTo(o.itemSetId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.itemSetId == null) ? 0 : this.itemSetId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.inventoryName == null) ? 0 : this.inventoryName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.itemName == null) ? 0 : this.itemName.hashCode());
        result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
        result = prime * result + ((this.referenceOf == null) ? 0 : this.referenceOf.hashCode());
        result = prime * result + ((this.sortValue == null) ? 0 : this.sortValue.hashCode());
        result = prime * result + ((this.expiresAt == null) ? 0 : this.expiresAt.hashCode());
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
		ItemSet other = (ItemSet) o;
		if (itemSetId == null) {
			return other.itemSetId == null;
		} else if (!itemSetId.equals(other.itemSetId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (inventoryName == null) {
			return other.inventoryName == null;
		} else if (!inventoryName.equals(other.inventoryName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (itemName == null) {
			return other.itemName == null;
		} else if (!itemName.equals(other.itemName)) {
			return false;
		}
		if (count == null) {
			return other.count == null;
		} else if (!count.equals(other.count)) {
			return false;
		}
		if (referenceOf == null) {
			return other.referenceOf == null;
		} else if (!referenceOf.equals(other.referenceOf)) {
			return false;
		}
		if (sortValue == null) {
			return other.sortValue == null;
		} else if (!sortValue.equals(other.sortValue)) {
			return false;
		}
		if (expiresAt == null) {
			return other.expiresAt == null;
		} else if (!expiresAt.equals(other.expiresAt)) {
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