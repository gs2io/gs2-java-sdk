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
public class SimpleInventoryModel implements IModel, Serializable, Comparable<SimpleInventoryModel> {
	private String inventoryModelId;
	private String name;
	private String metadata;
	private List<SimpleItemModel> simpleItemModels;
	public String getInventoryModelId() {
		return inventoryModelId;
	}
	public void setInventoryModelId(String inventoryModelId) {
		this.inventoryModelId = inventoryModelId;
	}
	public SimpleInventoryModel withInventoryModelId(String inventoryModelId) {
		this.inventoryModelId = inventoryModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SimpleInventoryModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public SimpleInventoryModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<SimpleItemModel> getSimpleItemModels() {
		return simpleItemModels;
	}
	public void setSimpleItemModels(List<SimpleItemModel> simpleItemModels) {
		this.simpleItemModels = simpleItemModels;
	}
	public SimpleInventoryModel withSimpleItemModels(List<SimpleItemModel> simpleItemModels) {
		this.simpleItemModels = simpleItemModels;
		return this;
	}

    public static SimpleInventoryModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SimpleInventoryModel()
            .withInventoryModelId(data.get("inventoryModelId") == null || data.get("inventoryModelId").isNull() ? null : data.get("inventoryModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withSimpleItemModels(data.get("simpleItemModels") == null || data.get("simpleItemModels").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("simpleItemModels").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return SimpleItemModel.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("inventoryModelId", getInventoryModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("simpleItemModels", getSimpleItemModels() == null ? null :
                    getSimpleItemModels().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(SimpleInventoryModel o) {
		return inventoryModelId.compareTo(o.inventoryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.inventoryModelId == null) ? 0 : this.inventoryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.simpleItemModels == null) ? 0 : this.simpleItemModels.hashCode());
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
		SimpleInventoryModel other = (SimpleInventoryModel) o;
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
		if (simpleItemModels == null) {
			return other.simpleItemModels == null;
		} else if (!simpleItemModels.equals(other.simpleItemModels)) {
			return false;
		}
		return true;
	}
}