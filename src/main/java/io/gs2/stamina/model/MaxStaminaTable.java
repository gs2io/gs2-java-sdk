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
public class MaxStaminaTable implements IModel, Serializable, Comparable<MaxStaminaTable> {
	private String maxStaminaTableId;
	private String name;
	private String metadata;
	private String experienceModelId;
	private List<Integer> values;

	public String getMaxStaminaTableId() {
		return maxStaminaTableId;
	}

	public void setMaxStaminaTableId(String maxStaminaTableId) {
		this.maxStaminaTableId = maxStaminaTableId;
	}

	public MaxStaminaTable withMaxStaminaTableId(String maxStaminaTableId) {
		this.maxStaminaTableId = maxStaminaTableId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MaxStaminaTable withName(String name) {
		this.name = name;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public MaxStaminaTable withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public String getExperienceModelId() {
		return experienceModelId;
	}

	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}

	public MaxStaminaTable withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}

	public List<Integer> getValues() {
		return values;
	}

	public void setValues(List<Integer> values) {
		this.values = values;
	}

	public MaxStaminaTable withValues(List<Integer> values) {
		this.values = values;
		return this;
	}

    public static MaxStaminaTable fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new MaxStaminaTable()
            .withMaxStaminaTableId(data.get("maxStaminaTableId") == null || data.get("maxStaminaTableId").isNull() ? null : data.get("maxStaminaTableId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withExperienceModelId(data.get("experienceModelId") == null || data.get("experienceModelId").isNull() ? null : data.get("experienceModelId").asText())
            .withValues(data.get("values") == null || data.get("values").isNull() ? new ArrayList<Integer>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("values").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.intValue();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("maxStaminaTableId", getMaxStaminaTableId());
                put("name", getName());
                put("metadata", getMetadata());
                put("experienceModelId", getExperienceModelId());
                put("values", getValues() == null ? new ArrayList<Integer>() :
                    getValues().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(MaxStaminaTable o) {
		return maxStaminaTableId.compareTo(o.maxStaminaTableId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.maxStaminaTableId == null) ? 0 : this.maxStaminaTableId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.experienceModelId == null) ? 0 : this.experienceModelId.hashCode());
        result = prime * result + ((this.values == null) ? 0 : this.values.hashCode());
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
		MaxStaminaTable other = (MaxStaminaTable) o;
		if (maxStaminaTableId == null) {
			return other.maxStaminaTableId == null;
		} else if (!maxStaminaTableId.equals(other.maxStaminaTableId)) {
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
		if (experienceModelId == null) {
			return other.experienceModelId == null;
		} else if (!experienceModelId.equals(other.experienceModelId)) {
			return false;
		}
		if (values == null) {
			return other.values == null;
		} else if (!values.equals(other.values)) {
			return false;
		}
		return true;
	}
}