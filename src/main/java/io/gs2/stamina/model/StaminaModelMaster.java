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
public class StaminaModelMaster implements IModel, Serializable, Comparable<StaminaModelMaster> {
	private String staminaModelId;
	private String name;
	private String metadata;
	private String description;
	private Integer recoverIntervalMinutes;
	private Integer recoverValue;
	private Integer initialCapacity;
	private Boolean isOverflow;
	private Integer maxCapacity;
	private String maxStaminaTableName;
	private String recoverIntervalTableName;
	private String recoverValueTableName;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getStaminaModelId() {
		return staminaModelId;
	}
	public void setStaminaModelId(String staminaModelId) {
		this.staminaModelId = staminaModelId;
	}
	public StaminaModelMaster withStaminaModelId(String staminaModelId) {
		this.staminaModelId = staminaModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StaminaModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public StaminaModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StaminaModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public Integer getRecoverIntervalMinutes() {
		return recoverIntervalMinutes;
	}
	public void setRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
	}
	public StaminaModelMaster withRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
		return this;
	}
	public Integer getRecoverValue() {
		return recoverValue;
	}
	public void setRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
	}
	public StaminaModelMaster withRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
		return this;
	}
	public Integer getInitialCapacity() {
		return initialCapacity;
	}
	public void setInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
	}
	public StaminaModelMaster withInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
		return this;
	}
	public Boolean getIsOverflow() {
		return isOverflow;
	}
	public void setIsOverflow(Boolean isOverflow) {
		this.isOverflow = isOverflow;
	}
	public StaminaModelMaster withIsOverflow(Boolean isOverflow) {
		this.isOverflow = isOverflow;
		return this;
	}
	public Integer getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public StaminaModelMaster withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}
	public String getMaxStaminaTableName() {
		return maxStaminaTableName;
	}
	public void setMaxStaminaTableName(String maxStaminaTableName) {
		this.maxStaminaTableName = maxStaminaTableName;
	}
	public StaminaModelMaster withMaxStaminaTableName(String maxStaminaTableName) {
		this.maxStaminaTableName = maxStaminaTableName;
		return this;
	}
	public String getRecoverIntervalTableName() {
		return recoverIntervalTableName;
	}
	public void setRecoverIntervalTableName(String recoverIntervalTableName) {
		this.recoverIntervalTableName = recoverIntervalTableName;
	}
	public StaminaModelMaster withRecoverIntervalTableName(String recoverIntervalTableName) {
		this.recoverIntervalTableName = recoverIntervalTableName;
		return this;
	}
	public String getRecoverValueTableName() {
		return recoverValueTableName;
	}
	public void setRecoverValueTableName(String recoverValueTableName) {
		this.recoverValueTableName = recoverValueTableName;
	}
	public StaminaModelMaster withRecoverValueTableName(String recoverValueTableName) {
		this.recoverValueTableName = recoverValueTableName;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public StaminaModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public StaminaModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public StaminaModelMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static StaminaModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new StaminaModelMaster()
            .withStaminaModelId(data.get("staminaModelId") == null || data.get("staminaModelId").isNull() ? null : data.get("staminaModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withRecoverIntervalMinutes(data.get("recoverIntervalMinutes") == null || data.get("recoverIntervalMinutes").isNull() ? null : data.get("recoverIntervalMinutes").intValue())
            .withRecoverValue(data.get("recoverValue") == null || data.get("recoverValue").isNull() ? null : data.get("recoverValue").intValue())
            .withInitialCapacity(data.get("initialCapacity") == null || data.get("initialCapacity").isNull() ? null : data.get("initialCapacity").intValue())
            .withIsOverflow(data.get("isOverflow") == null || data.get("isOverflow").isNull() ? null : data.get("isOverflow").booleanValue())
            .withMaxCapacity(data.get("maxCapacity") == null || data.get("maxCapacity").isNull() ? null : data.get("maxCapacity").intValue())
            .withMaxStaminaTableName(data.get("maxStaminaTableName") == null || data.get("maxStaminaTableName").isNull() ? null : data.get("maxStaminaTableName").asText())
            .withRecoverIntervalTableName(data.get("recoverIntervalTableName") == null || data.get("recoverIntervalTableName").isNull() ? null : data.get("recoverIntervalTableName").asText())
            .withRecoverValueTableName(data.get("recoverValueTableName") == null || data.get("recoverValueTableName").isNull() ? null : data.get("recoverValueTableName").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("staminaModelId", getStaminaModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("description", getDescription());
                put("recoverIntervalMinutes", getRecoverIntervalMinutes());
                put("recoverValue", getRecoverValue());
                put("initialCapacity", getInitialCapacity());
                put("isOverflow", getIsOverflow());
                put("maxCapacity", getMaxCapacity());
                put("maxStaminaTableName", getMaxStaminaTableName());
                put("recoverIntervalTableName", getRecoverIntervalTableName());
                put("recoverValueTableName", getRecoverValueTableName());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(StaminaModelMaster o) {
		return staminaModelId.compareTo(o.staminaModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.staminaModelId == null) ? 0 : this.staminaModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.recoverIntervalMinutes == null) ? 0 : this.recoverIntervalMinutes.hashCode());
        result = prime * result + ((this.recoverValue == null) ? 0 : this.recoverValue.hashCode());
        result = prime * result + ((this.initialCapacity == null) ? 0 : this.initialCapacity.hashCode());
        result = prime * result + ((this.isOverflow == null) ? 0 : this.isOverflow.hashCode());
        result = prime * result + ((this.maxCapacity == null) ? 0 : this.maxCapacity.hashCode());
        result = prime * result + ((this.maxStaminaTableName == null) ? 0 : this.maxStaminaTableName.hashCode());
        result = prime * result + ((this.recoverIntervalTableName == null) ? 0 : this.recoverIntervalTableName.hashCode());
        result = prime * result + ((this.recoverValueTableName == null) ? 0 : this.recoverValueTableName.hashCode());
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
		StaminaModelMaster other = (StaminaModelMaster) o;
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
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
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
		if (maxStaminaTableName == null) {
			return other.maxStaminaTableName == null;
		} else if (!maxStaminaTableName.equals(other.maxStaminaTableName)) {
			return false;
		}
		if (recoverIntervalTableName == null) {
			return other.recoverIntervalTableName == null;
		} else if (!recoverIntervalTableName.equals(other.recoverIntervalTableName)) {
			return false;
		}
		if (recoverValueTableName == null) {
			return other.recoverValueTableName == null;
		} else if (!recoverValueTableName.equals(other.recoverValueTableName)) {
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