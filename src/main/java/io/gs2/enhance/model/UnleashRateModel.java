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

package io.gs2.enhance.model;

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
public class UnleashRateModel implements IModel, Serializable, Comparable<UnleashRateModel> {
	private String unleashRateModelId;
	private String name;
	private String description;
	private String metadata;
	private String targetInventoryModelId;
	private String gradeModelId;
	private List<UnleashRateEntryModel> gradeEntries;
	public String getUnleashRateModelId() {
		return unleashRateModelId;
	}
	public void setUnleashRateModelId(String unleashRateModelId) {
		this.unleashRateModelId = unleashRateModelId;
	}
	public UnleashRateModel withUnleashRateModelId(String unleashRateModelId) {
		this.unleashRateModelId = unleashRateModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UnleashRateModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UnleashRateModel withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UnleashRateModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getTargetInventoryModelId() {
		return targetInventoryModelId;
	}
	public void setTargetInventoryModelId(String targetInventoryModelId) {
		this.targetInventoryModelId = targetInventoryModelId;
	}
	public UnleashRateModel withTargetInventoryModelId(String targetInventoryModelId) {
		this.targetInventoryModelId = targetInventoryModelId;
		return this;
	}
	public String getGradeModelId() {
		return gradeModelId;
	}
	public void setGradeModelId(String gradeModelId) {
		this.gradeModelId = gradeModelId;
	}
	public UnleashRateModel withGradeModelId(String gradeModelId) {
		this.gradeModelId = gradeModelId;
		return this;
	}
	public List<UnleashRateEntryModel> getGradeEntries() {
		return gradeEntries;
	}
	public void setGradeEntries(List<UnleashRateEntryModel> gradeEntries) {
		this.gradeEntries = gradeEntries;
	}
	public UnleashRateModel withGradeEntries(List<UnleashRateEntryModel> gradeEntries) {
		this.gradeEntries = gradeEntries;
		return this;
	}

    public static UnleashRateModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UnleashRateModel()
            .withUnleashRateModelId(data.get("unleashRateModelId") == null || data.get("unleashRateModelId").isNull() ? null : data.get("unleashRateModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTargetInventoryModelId(data.get("targetInventoryModelId") == null || data.get("targetInventoryModelId").isNull() ? null : data.get("targetInventoryModelId").asText())
            .withGradeModelId(data.get("gradeModelId") == null || data.get("gradeModelId").isNull() ? null : data.get("gradeModelId").asText())
            .withGradeEntries(data.get("gradeEntries") == null || data.get("gradeEntries").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("gradeEntries").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return UnleashRateEntryModel.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("unleashRateModelId", getUnleashRateModelId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("targetInventoryModelId", getTargetInventoryModelId());
                put("gradeModelId", getGradeModelId());
                put("gradeEntries", getGradeEntries() == null ? null :
                    getGradeEntries().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(UnleashRateModel o) {
		return unleashRateModelId.compareTo(o.unleashRateModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.unleashRateModelId == null) ? 0 : this.unleashRateModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.targetInventoryModelId == null) ? 0 : this.targetInventoryModelId.hashCode());
        result = prime * result + ((this.gradeModelId == null) ? 0 : this.gradeModelId.hashCode());
        result = prime * result + ((this.gradeEntries == null) ? 0 : this.gradeEntries.hashCode());
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
		UnleashRateModel other = (UnleashRateModel) o;
		if (unleashRateModelId == null) {
			return other.unleashRateModelId == null;
		} else if (!unleashRateModelId.equals(other.unleashRateModelId)) {
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
		if (targetInventoryModelId == null) {
			return other.targetInventoryModelId == null;
		} else if (!targetInventoryModelId.equals(other.targetInventoryModelId)) {
			return false;
		}
		if (gradeModelId == null) {
			return other.gradeModelId == null;
		} else if (!gradeModelId.equals(other.gradeModelId)) {
			return false;
		}
		if (gradeEntries == null) {
			return other.gradeEntries == null;
		} else if (!gradeEntries.equals(other.gradeEntries)) {
			return false;
		}
		return true;
	}
}