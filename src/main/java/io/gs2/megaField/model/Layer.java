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
public class Layer implements IModel, Serializable, Comparable<Layer> {
	private String layerId;
	private String areaModelName;
	private String layerModelName;
	private Integer numberOfMinEntries;
	private Integer numberOfMaxEntries;
	private Long createdAt;
	public String getLayerId() {
		return layerId;
	}
	public void setLayerId(String layerId) {
		this.layerId = layerId;
	}
	public Layer withLayerId(String layerId) {
		this.layerId = layerId;
		return this;
	}
	public String getAreaModelName() {
		return areaModelName;
	}
	public void setAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
	}
	public Layer withAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
		return this;
	}
	public String getLayerModelName() {
		return layerModelName;
	}
	public void setLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
	}
	public Layer withLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
		return this;
	}
	public Integer getNumberOfMinEntries() {
		return numberOfMinEntries;
	}
	public void setNumberOfMinEntries(Integer numberOfMinEntries) {
		this.numberOfMinEntries = numberOfMinEntries;
	}
	public Layer withNumberOfMinEntries(Integer numberOfMinEntries) {
		this.numberOfMinEntries = numberOfMinEntries;
		return this;
	}
	public Integer getNumberOfMaxEntries() {
		return numberOfMaxEntries;
	}
	public void setNumberOfMaxEntries(Integer numberOfMaxEntries) {
		this.numberOfMaxEntries = numberOfMaxEntries;
	}
	public Layer withNumberOfMaxEntries(Integer numberOfMaxEntries) {
		this.numberOfMaxEntries = numberOfMaxEntries;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Layer withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static Layer fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Layer()
            .withLayerId(data.get("layerId") == null || data.get("layerId").isNull() ? null : data.get("layerId").asText())
            .withAreaModelName(data.get("areaModelName") == null || data.get("areaModelName").isNull() ? null : data.get("areaModelName").asText())
            .withLayerModelName(data.get("layerModelName") == null || data.get("layerModelName").isNull() ? null : data.get("layerModelName").asText())
            .withNumberOfMinEntries(data.get("numberOfMinEntries") == null || data.get("numberOfMinEntries").isNull() ? null : data.get("numberOfMinEntries").intValue())
            .withNumberOfMaxEntries(data.get("numberOfMaxEntries") == null || data.get("numberOfMaxEntries").isNull() ? null : data.get("numberOfMaxEntries").intValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("layerId", getLayerId());
                put("areaModelName", getAreaModelName());
                put("layerModelName", getLayerModelName());
                put("numberOfMinEntries", getNumberOfMinEntries());
                put("numberOfMaxEntries", getNumberOfMaxEntries());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Layer o) {
		return layerId.compareTo(o.layerId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.layerId == null) ? 0 : this.layerId.hashCode());
        result = prime * result + ((this.areaModelName == null) ? 0 : this.areaModelName.hashCode());
        result = prime * result + ((this.layerModelName == null) ? 0 : this.layerModelName.hashCode());
        result = prime * result + ((this.numberOfMinEntries == null) ? 0 : this.numberOfMinEntries.hashCode());
        result = prime * result + ((this.numberOfMaxEntries == null) ? 0 : this.numberOfMaxEntries.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		Layer other = (Layer) o;
		if (layerId == null) {
			return other.layerId == null;
		} else if (!layerId.equals(other.layerId)) {
			return false;
		}
		if (areaModelName == null) {
			return other.areaModelName == null;
		} else if (!areaModelName.equals(other.areaModelName)) {
			return false;
		}
		if (layerModelName == null) {
			return other.layerModelName == null;
		} else if (!layerModelName.equals(other.layerModelName)) {
			return false;
		}
		if (numberOfMinEntries == null) {
			return other.numberOfMinEntries == null;
		} else if (!numberOfMinEntries.equals(other.numberOfMinEntries)) {
			return false;
		}
		if (numberOfMaxEntries == null) {
			return other.numberOfMaxEntries == null;
		} else if (!numberOfMaxEntries.equals(other.numberOfMaxEntries)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		return true;
	}
}