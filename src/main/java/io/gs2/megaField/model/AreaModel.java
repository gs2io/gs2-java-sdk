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
public class AreaModel implements IModel, Serializable, Comparable<AreaModel> {
	private String areaModelId;
	private String name;
	private String metadata;
	private List<LayerModel> layerModels;
	public String getAreaModelId() {
		return areaModelId;
	}
	public void setAreaModelId(String areaModelId) {
		this.areaModelId = areaModelId;
	}
	public AreaModel withAreaModelId(String areaModelId) {
		this.areaModelId = areaModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AreaModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public AreaModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<LayerModel> getLayerModels() {
		return layerModels;
	}
	public void setLayerModels(List<LayerModel> layerModels) {
		this.layerModels = layerModels;
	}
	public AreaModel withLayerModels(List<LayerModel> layerModels) {
		this.layerModels = layerModels;
		return this;
	}

    public static AreaModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AreaModel()
            .withAreaModelId(data.get("areaModelId") == null || data.get("areaModelId").isNull() ? null : data.get("areaModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withLayerModels(data.get("layerModels") == null || data.get("layerModels").isNull() ? new ArrayList<LayerModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("layerModels").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return LayerModel.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("areaModelId", getAreaModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("layerModels", getLayerModels() == null ? new ArrayList<LayerModel>() :
                    getLayerModels().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(AreaModel o) {
		return areaModelId.compareTo(o.areaModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.areaModelId == null) ? 0 : this.areaModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.layerModels == null) ? 0 : this.layerModels.hashCode());
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
		AreaModel other = (AreaModel) o;
		if (areaModelId == null) {
			return other.areaModelId == null;
		} else if (!areaModelId.equals(other.areaModelId)) {
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
		if (layerModels == null) {
			return other.layerModels == null;
		} else if (!layerModels.equals(other.layerModels)) {
			return false;
		}
		return true;
	}
}