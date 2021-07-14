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
public class Inventory implements IModel, Serializable, Comparable<Inventory> {
	private String inventoryId;
	private String inventoryName;
	private String userId;
	private Integer currentInventoryCapacityUsage;
	private Integer currentInventoryMaxCapacity;
	private Long createdAt;
	private Long updatedAt;

	public String getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Inventory withInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
		return this;
	}

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public Inventory withInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Inventory withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Integer getCurrentInventoryCapacityUsage() {
		return currentInventoryCapacityUsage;
	}

	public void setCurrentInventoryCapacityUsage(Integer currentInventoryCapacityUsage) {
		this.currentInventoryCapacityUsage = currentInventoryCapacityUsage;
	}

	public Inventory withCurrentInventoryCapacityUsage(Integer currentInventoryCapacityUsage) {
		this.currentInventoryCapacityUsage = currentInventoryCapacityUsage;
		return this;
	}

	public Integer getCurrentInventoryMaxCapacity() {
		return currentInventoryMaxCapacity;
	}

	public void setCurrentInventoryMaxCapacity(Integer currentInventoryMaxCapacity) {
		this.currentInventoryMaxCapacity = currentInventoryMaxCapacity;
	}

	public Inventory withCurrentInventoryMaxCapacity(Integer currentInventoryMaxCapacity) {
		this.currentInventoryMaxCapacity = currentInventoryMaxCapacity;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Inventory withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Inventory withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Inventory fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Inventory()
            .withInventoryId(data.get("inventoryId") == null || data.get("inventoryId").isNull() ? null : data.get("inventoryId").asText())
            .withInventoryName(data.get("inventoryName") == null || data.get("inventoryName").isNull() ? null : data.get("inventoryName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withCurrentInventoryCapacityUsage(data.get("currentInventoryCapacityUsage") == null || data.get("currentInventoryCapacityUsage").isNull() ? null : data.get("currentInventoryCapacityUsage").intValue())
            .withCurrentInventoryMaxCapacity(data.get("currentInventoryMaxCapacity") == null || data.get("currentInventoryMaxCapacity").isNull() ? null : data.get("currentInventoryMaxCapacity").intValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("inventoryId", getInventoryId());
                put("inventoryName", getInventoryName());
                put("userId", getUserId());
                put("currentInventoryCapacityUsage", getCurrentInventoryCapacityUsage());
                put("currentInventoryMaxCapacity", getCurrentInventoryMaxCapacity());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Inventory o) {
		return inventoryId.compareTo(o.inventoryId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.inventoryId == null) ? 0 : this.inventoryId.hashCode());
        result = prime * result + ((this.inventoryName == null) ? 0 : this.inventoryName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.currentInventoryCapacityUsage == null) ? 0 : this.currentInventoryCapacityUsage.hashCode());
        result = prime * result + ((this.currentInventoryMaxCapacity == null) ? 0 : this.currentInventoryMaxCapacity.hashCode());
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
		Inventory other = (Inventory) o;
		if (inventoryId == null) {
			return other.inventoryId == null;
		} else if (!inventoryId.equals(other.inventoryId)) {
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
		if (currentInventoryCapacityUsage == null) {
			return other.currentInventoryCapacityUsage == null;
		} else if (!currentInventoryCapacityUsage.equals(other.currentInventoryCapacityUsage)) {
			return false;
		}
		if (currentInventoryMaxCapacity == null) {
			return other.currentInventoryMaxCapacity == null;
		} else if (!currentInventoryMaxCapacity.equals(other.currentInventoryMaxCapacity)) {
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