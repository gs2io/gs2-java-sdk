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

package io.gs2.stamina.model;

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
public class StaminaModel implements IModel, Serializable, Comparable<StaminaModel> {
	private String staminaModelId;
	private String name;
	private String metadata;
	private Integer recoverIntervalMinutes;
	private Integer recoverValue;
	private Integer initialCapacity;
	private Boolean isOverflow;
	private Integer maxCapacity;
	private MaxStaminaTable maxStaminaTable;
	private RecoverIntervalTable recoverIntervalTable;
	private RecoverValueTable recoverValueTable;
	public String getStaminaModelId() {
		return staminaModelId;
	}
	public void setStaminaModelId(String staminaModelId) {
		this.staminaModelId = staminaModelId;
	}
	public StaminaModel withStaminaModelId(String staminaModelId) {
		this.staminaModelId = staminaModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StaminaModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public StaminaModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getRecoverIntervalMinutes() {
		return recoverIntervalMinutes;
	}
	public void setRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
	}
	public StaminaModel withRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
		return this;
	}
	public Integer getRecoverValue() {
		return recoverValue;
	}
	public void setRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
	}
	public StaminaModel withRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
		return this;
	}
	public Integer getInitialCapacity() {
		return initialCapacity;
	}
	public void setInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
	}
	public StaminaModel withInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
		return this;
	}
	public Boolean getIsOverflow() {
		return isOverflow;
	}
	public void setIsOverflow(Boolean isOverflow) {
		this.isOverflow = isOverflow;
	}
	public StaminaModel withIsOverflow(Boolean isOverflow) {
		this.isOverflow = isOverflow;
		return this;
	}
	public Integer getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public StaminaModel withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}
	public MaxStaminaTable getMaxStaminaTable() {
		return maxStaminaTable;
	}
	public void setMaxStaminaTable(MaxStaminaTable maxStaminaTable) {
		this.maxStaminaTable = maxStaminaTable;
	}
	public StaminaModel withMaxStaminaTable(MaxStaminaTable maxStaminaTable) {
		this.maxStaminaTable = maxStaminaTable;
		return this;
	}
	public RecoverIntervalTable getRecoverIntervalTable() {
		return recoverIntervalTable;
	}
	public void setRecoverIntervalTable(RecoverIntervalTable recoverIntervalTable) {
		this.recoverIntervalTable = recoverIntervalTable;
	}
	public StaminaModel withRecoverIntervalTable(RecoverIntervalTable recoverIntervalTable) {
		this.recoverIntervalTable = recoverIntervalTable;
		return this;
	}
	public RecoverValueTable getRecoverValueTable() {
		return recoverValueTable;
	}
	public void setRecoverValueTable(RecoverValueTable recoverValueTable) {
		this.recoverValueTable = recoverValueTable;
	}
	public StaminaModel withRecoverValueTable(RecoverValueTable recoverValueTable) {
		this.recoverValueTable = recoverValueTable;
		return this;
	}

    public static StaminaModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new StaminaModel()
            .withStaminaModelId(data.get("staminaModelId") == null || data.get("staminaModelId").isNull() ? null : data.get("staminaModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withRecoverIntervalMinutes(data.get("recoverIntervalMinutes") == null || data.get("recoverIntervalMinutes").isNull() ? null : data.get("recoverIntervalMinutes").intValue())
            .withRecoverValue(data.get("recoverValue") == null || data.get("recoverValue").isNull() ? null : data.get("recoverValue").intValue())
            .withInitialCapacity(data.get("initialCapacity") == null || data.get("initialCapacity").isNull() ? null : data.get("initialCapacity").intValue())
            .withIsOverflow(data.get("isOverflow") == null || data.get("isOverflow").isNull() ? null : data.get("isOverflow").booleanValue())
            .withMaxCapacity(data.get("maxCapacity") == null || data.get("maxCapacity").isNull() ? null : data.get("maxCapacity").intValue())
            .withMaxStaminaTable(data.get("maxStaminaTable") == null || data.get("maxStaminaTable").isNull() ? null : MaxStaminaTable.fromJson(data.get("maxStaminaTable")))
            .withRecoverIntervalTable(data.get("recoverIntervalTable") == null || data.get("recoverIntervalTable").isNull() ? null : RecoverIntervalTable.fromJson(data.get("recoverIntervalTable")))
            .withRecoverValueTable(data.get("recoverValueTable") == null || data.get("recoverValueTable").isNull() ? null : RecoverValueTable.fromJson(data.get("recoverValueTable")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("staminaModelId", getStaminaModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("recoverIntervalMinutes", getRecoverIntervalMinutes());
                put("recoverValue", getRecoverValue());
                put("initialCapacity", getInitialCapacity());
                put("isOverflow", getIsOverflow());
                put("maxCapacity", getMaxCapacity());
                put("maxStaminaTable", getMaxStaminaTable() != null ? getMaxStaminaTable().toJson() : null);
                put("recoverIntervalTable", getRecoverIntervalTable() != null ? getRecoverIntervalTable().toJson() : null);
                put("recoverValueTable", getRecoverValueTable() != null ? getRecoverValueTable().toJson() : null);
            }}
        );
    }

	@Override
	public int compareTo(StaminaModel o) {
		return staminaModelId.compareTo(o.staminaModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.staminaModelId == null) ? 0 : this.staminaModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.recoverIntervalMinutes == null) ? 0 : this.recoverIntervalMinutes.hashCode());
        result = prime * result + ((this.recoverValue == null) ? 0 : this.recoverValue.hashCode());
        result = prime * result + ((this.initialCapacity == null) ? 0 : this.initialCapacity.hashCode());
        result = prime * result + ((this.isOverflow == null) ? 0 : this.isOverflow.hashCode());
        result = prime * result + ((this.maxCapacity == null) ? 0 : this.maxCapacity.hashCode());
        result = prime * result + ((this.maxStaminaTable == null) ? 0 : this.maxStaminaTable.hashCode());
        result = prime * result + ((this.recoverIntervalTable == null) ? 0 : this.recoverIntervalTable.hashCode());
        result = prime * result + ((this.recoverValueTable == null) ? 0 : this.recoverValueTable.hashCode());
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
		StaminaModel other = (StaminaModel) o;
		if (staminaModelId == null) {
			return other.staminaModelId == null;
		} else if (!staminaModelId.equals(other.staminaModelId)) {
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
		if (recoverIntervalMinutes == null) {
			return other.recoverIntervalMinutes == null;
		} else if (!recoverIntervalMinutes.equals(other.recoverIntervalMinutes)) {
			return false;
		}
		if (recoverValue == null) {
			return other.recoverValue == null;
		} else if (!recoverValue.equals(other.recoverValue)) {
			return false;
		}
		if (initialCapacity == null) {
			return other.initialCapacity == null;
		} else if (!initialCapacity.equals(other.initialCapacity)) {
			return false;
		}
		if (isOverflow == null) {
			return other.isOverflow == null;
		} else if (!isOverflow.equals(other.isOverflow)) {
			return false;
		}
		if (maxCapacity == null) {
			return other.maxCapacity == null;
		} else if (!maxCapacity.equals(other.maxCapacity)) {
			return false;
		}
		if (maxStaminaTable == null) {
			return other.maxStaminaTable == null;
		} else if (!maxStaminaTable.equals(other.maxStaminaTable)) {
			return false;
		}
		if (recoverIntervalTable == null) {
			return other.recoverIntervalTable == null;
		} else if (!recoverIntervalTable.equals(other.recoverIntervalTable)) {
			return false;
		}
		if (recoverValueTable == null) {
			return other.recoverValueTable == null;
		} else if (!recoverValueTable.equals(other.recoverValueTable)) {
			return false;
		}
		return true;
	}
}