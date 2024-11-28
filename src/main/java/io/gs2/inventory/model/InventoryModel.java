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
public class InventoryModel implements IModel, Serializable, Comparable<InventoryModel> {
	private String inventoryModelId;
	private String name;
	private String metadata;
	private Integer initialCapacity;
	private Integer maxCapacity;
	private Boolean protectReferencedItem;
	private List<ItemModel> itemModels;
	public String getInventoryModelId() {
		return inventoryModelId;
	}
	public void setInventoryModelId(String inventoryModelId) {
		this.inventoryModelId = inventoryModelId;
	}
	public InventoryModel withInventoryModelId(String inventoryModelId) {
		this.inventoryModelId = inventoryModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InventoryModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public InventoryModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getInitialCapacity() {
		return initialCapacity;
	}
	public void setInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
	}
	public InventoryModel withInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
		return this;
	}
	public Integer getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public InventoryModel withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}
	public Boolean getProtectReferencedItem() {
		return protectReferencedItem;
	}
	public void setProtectReferencedItem(Boolean protectReferencedItem) {
		this.protectReferencedItem = protectReferencedItem;
	}
	public InventoryModel withProtectReferencedItem(Boolean protectReferencedItem) {
		this.protectReferencedItem = protectReferencedItem;
		return this;
	}
	public List<ItemModel> getItemModels() {
		return itemModels;
	}
	public void setItemModels(List<ItemModel> itemModels) {
		this.itemModels = itemModels;
	}
	public InventoryModel withItemModels(List<ItemModel> itemModels) {
		this.itemModels = itemModels;
		return this;
	}

    public static InventoryModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new InventoryModel()
            .withInventoryModelId(data.get("inventoryModelId") == null || data.get("inventoryModelId").isNull() ? null : data.get("inventoryModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withInitialCapacity(data.get("initialCapacity") == null || data.get("initialCapacity").isNull() ? null : data.get("initialCapacity").intValue())
            .withMaxCapacity(data.get("maxCapacity") == null || data.get("maxCapacity").isNull() ? null : data.get("maxCapacity").intValue())
            .withProtectReferencedItem(data.get("protectReferencedItem") == null || data.get("protectReferencedItem").isNull() ? null : data.get("protectReferencedItem").booleanValue())
            .withItemModels(data.get("itemModels") == null || data.get("itemModels").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("itemModels").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ItemModel.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("inventoryModelId", getInventoryModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("initialCapacity", getInitialCapacity());
                put("maxCapacity", getMaxCapacity());
                put("protectReferencedItem", getProtectReferencedItem());
                put("itemModels", getItemModels() == null ? null :
                    getItemModels().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(InventoryModel o) {
		return inventoryModelId.compareTo(o.inventoryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.inventoryModelId == null) ? 0 : this.inventoryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.initialCapacity == null) ? 0 : this.initialCapacity.hashCode());
        result = prime * result + ((this.maxCapacity == null) ? 0 : this.maxCapacity.hashCode());
        result = prime * result + ((this.protectReferencedItem == null) ? 0 : this.protectReferencedItem.hashCode());
        result = prime * result + ((this.itemModels == null) ? 0 : this.itemModels.hashCode());
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
		InventoryModel other = (InventoryModel) o;
		if (inventoryModelId == null) {
			return other.inventoryModelId == null;
		} else if (!inventoryModelId.equals(other.inventoryModelId)) {
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
		if (initialCapacity == null) {
			return other.initialCapacity == null;
		} else if (!initialCapacity.equals(other.initialCapacity)) {
			return false;
		}
		if (maxCapacity == null) {
			return other.maxCapacity == null;
		} else if (!maxCapacity.equals(other.maxCapacity)) {
			return false;
		}
		if (protectReferencedItem == null) {
			return other.protectReferencedItem == null;
		} else if (!protectReferencedItem.equals(other.protectReferencedItem)) {
			return false;
		}
		if (itemModels == null) {
			return other.itemModels == null;
		} else if (!itemModels.equals(other.itemModels)) {
			return false;
		}
		return true;
	}
}