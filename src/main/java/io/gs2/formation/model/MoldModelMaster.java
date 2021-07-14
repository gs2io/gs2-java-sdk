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

package io.gs2.formation.model;

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
public class MoldModelMaster implements IModel, Serializable, Comparable<MoldModelMaster> {
	private String moldModelId;
	private String name;
	private String description;
	private String metadata;
	private Integer initialMaxCapacity;
	private Integer maxCapacity;
	private String formModelName;
	private Long createdAt;
	private Long updatedAt;

	public String getMoldModelId() {
		return moldModelId;
	}

	public void setMoldModelId(String moldModelId) {
		this.moldModelId = moldModelId;
	}

	public MoldModelMaster withMoldModelId(String moldModelId) {
		this.moldModelId = moldModelId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MoldModelMaster withName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MoldModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public MoldModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public Integer getInitialMaxCapacity() {
		return initialMaxCapacity;
	}

	public void setInitialMaxCapacity(Integer initialMaxCapacity) {
		this.initialMaxCapacity = initialMaxCapacity;
	}

	public MoldModelMaster withInitialMaxCapacity(Integer initialMaxCapacity) {
		this.initialMaxCapacity = initialMaxCapacity;
		return this;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public MoldModelMaster withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}

	public String getFormModelName() {
		return formModelName;
	}

	public void setFormModelName(String formModelName) {
		this.formModelName = formModelName;
	}

	public MoldModelMaster withFormModelName(String formModelName) {
		this.formModelName = formModelName;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public MoldModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public MoldModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static MoldModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new MoldModelMaster()
            .withMoldModelId(data.get("moldModelId") == null || data.get("moldModelId").isNull() ? null : data.get("moldModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withInitialMaxCapacity(data.get("initialMaxCapacity") == null || data.get("initialMaxCapacity").isNull() ? null : data.get("initialMaxCapacity").intValue())
            .withMaxCapacity(data.get("maxCapacity") == null || data.get("maxCapacity").isNull() ? null : data.get("maxCapacity").intValue())
            .withFormModelName(data.get("formModelName") == null || data.get("formModelName").isNull() ? null : data.get("formModelName").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("moldModelId", getMoldModelId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("initialMaxCapacity", getInitialMaxCapacity());
                put("maxCapacity", getMaxCapacity());
                put("formModelName", getFormModelName());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(MoldModelMaster o) {
		return moldModelId.compareTo(o.moldModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.moldModelId == null) ? 0 : this.moldModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.initialMaxCapacity == null) ? 0 : this.initialMaxCapacity.hashCode());
        result = prime * result + ((this.maxCapacity == null) ? 0 : this.maxCapacity.hashCode());
        result = prime * result + ((this.formModelName == null) ? 0 : this.formModelName.hashCode());
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
		MoldModelMaster other = (MoldModelMaster) o;
		if (moldModelId == null) {
			return other.moldModelId == null;
		} else if (!moldModelId.equals(other.moldModelId)) {
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
		if (initialMaxCapacity == null) {
			return other.initialMaxCapacity == null;
		} else if (!initialMaxCapacity.equals(other.initialMaxCapacity)) {
			return false;
		}
		if (maxCapacity == null) {
			return other.maxCapacity == null;
		} else if (!maxCapacity.equals(other.maxCapacity)) {
			return false;
		}
		if (formModelName == null) {
			return other.formModelName == null;
		} else if (!formModelName.equals(other.formModelName)) {
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