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
public class UnleashRecipe implements IModel, Serializable {
	private String name;
	private String metadata;
	private List<String> targetGroupKeys;
	private List<UnleashMaterial> materials;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UnleashRecipe withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UnleashRecipe withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<String> getTargetGroupKeys() {
		return targetGroupKeys;
	}
	public void setTargetGroupKeys(List<String> targetGroupKeys) {
		this.targetGroupKeys = targetGroupKeys;
	}
	public UnleashRecipe withTargetGroupKeys(List<String> targetGroupKeys) {
		this.targetGroupKeys = targetGroupKeys;
		return this;
	}
	public List<UnleashMaterial> getMaterials() {
		return materials;
	}
	public void setMaterials(List<UnleashMaterial> materials) {
		this.materials = materials;
	}
	public UnleashRecipe withMaterials(List<UnleashMaterial> materials) {
		this.materials = materials;
		return this;
	}

    public static UnleashRecipe fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UnleashRecipe()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTargetGroupKeys(data.get("targetGroupKeys") == null || data.get("targetGroupKeys").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("targetGroupKeys").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withMaterials(data.get("materials") == null || data.get("materials").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("materials").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return UnleashMaterial.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("metadata", getMetadata());
                put("targetGroupKeys", getTargetGroupKeys() == null ? null :
                    getTargetGroupKeys().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("materials", getMaterials() == null ? null :
                    getMaterials().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.targetGroupKeys == null) ? 0 : this.targetGroupKeys.hashCode());
        result = prime * result + ((this.materials == null) ? 0 : this.materials.hashCode());
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
		UnleashRecipe other = (UnleashRecipe) o;
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
		if (targetGroupKeys == null) {
			return other.targetGroupKeys == null;
		} else if (!targetGroupKeys.equals(other.targetGroupKeys)) {
			return false;
		}
		if (materials == null) {
			return other.materials == null;
		} else if (!materials.equals(other.materials)) {
			return false;
		}
		return true;
	}
}