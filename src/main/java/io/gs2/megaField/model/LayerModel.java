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

package io.gs2.megaField.model;

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
public class LayerModel implements IModel, Serializable, Comparable<LayerModel> {
	private String layerModelId;
	private String areaModelName;
	private String name;
	private String metadata;
	public String getLayerModelId() {
		return layerModelId;
	}
	public void setLayerModelId(String layerModelId) {
		this.layerModelId = layerModelId;
	}
	public LayerModel withLayerModelId(String layerModelId) {
		this.layerModelId = layerModelId;
		return this;
	}
	public String getAreaModelName() {
		return areaModelName;
	}
	public void setAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
	}
	public LayerModel withAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LayerModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public LayerModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

    public static LayerModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new LayerModel()
            .withLayerModelId(data.get("layerModelId") == null || data.get("layerModelId").isNull() ? null : data.get("layerModelId").asText())
            .withAreaModelName(data.get("areaModelName") == null || data.get("areaModelName").isNull() ? null : data.get("areaModelName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("layerModelId", getLayerModelId());
                put("areaModelName", getAreaModelName());
                put("name", getName());
                put("metadata", getMetadata());
            }}
        );
    }

	@Override
	public int compareTo(LayerModel o) {
		return layerModelId.compareTo(o.layerModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.layerModelId == null) ? 0 : this.layerModelId.hashCode());
        result = prime * result + ((this.areaModelName == null) ? 0 : this.areaModelName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
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
		LayerModel other = (LayerModel) o;
		if (layerModelId == null) {
			return other.layerModelId == null;
		} else if (!layerModelId.equals(other.layerModelId)) {
			return false;
		}
		if (areaModelName == null) {
			return other.areaModelName == null;
		} else if (!areaModelName.equals(other.areaModelName)) {
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
		return true;
	}
}