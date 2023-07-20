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

package io.gs2.enchant.model;

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
public class RarityParameterModel implements IModel, Serializable, Comparable<RarityParameterModel> {
	private String rarityParameterModelId;
	private String name;
	private String metadata;
	private Integer maximumParameterCount;
	private List<RarityParameterCountModel> parameterCounts;
	private List<RarityParameterValueModel> parameters;
	public String getRarityParameterModelId() {
		return rarityParameterModelId;
	}
	public void setRarityParameterModelId(String rarityParameterModelId) {
		this.rarityParameterModelId = rarityParameterModelId;
	}
	public RarityParameterModel withRarityParameterModelId(String rarityParameterModelId) {
		this.rarityParameterModelId = rarityParameterModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RarityParameterModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public RarityParameterModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getMaximumParameterCount() {
		return maximumParameterCount;
	}
	public void setMaximumParameterCount(Integer maximumParameterCount) {
		this.maximumParameterCount = maximumParameterCount;
	}
	public RarityParameterModel withMaximumParameterCount(Integer maximumParameterCount) {
		this.maximumParameterCount = maximumParameterCount;
		return this;
	}
	public List<RarityParameterCountModel> getParameterCounts() {
		return parameterCounts;
	}
	public void setParameterCounts(List<RarityParameterCountModel> parameterCounts) {
		this.parameterCounts = parameterCounts;
	}
	public RarityParameterModel withParameterCounts(List<RarityParameterCountModel> parameterCounts) {
		this.parameterCounts = parameterCounts;
		return this;
	}
	public List<RarityParameterValueModel> getParameters() {
		return parameters;
	}
	public void setParameters(List<RarityParameterValueModel> parameters) {
		this.parameters = parameters;
	}
	public RarityParameterModel withParameters(List<RarityParameterValueModel> parameters) {
		this.parameters = parameters;
		return this;
	}

    public static RarityParameterModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RarityParameterModel()
            .withRarityParameterModelId(data.get("rarityParameterModelId") == null || data.get("rarityParameterModelId").isNull() ? null : data.get("rarityParameterModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withMaximumParameterCount(data.get("maximumParameterCount") == null || data.get("maximumParameterCount").isNull() ? null : data.get("maximumParameterCount").intValue())
            .withParameterCounts(data.get("parameterCounts") == null || data.get("parameterCounts").isNull() ? new ArrayList<RarityParameterCountModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("parameterCounts").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RarityParameterCountModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withParameters(data.get("parameters") == null || data.get("parameters").isNull() ? new ArrayList<RarityParameterValueModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("parameters").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RarityParameterValueModel.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("rarityParameterModelId", getRarityParameterModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("maximumParameterCount", getMaximumParameterCount());
                put("parameterCounts", getParameterCounts() == null ? new ArrayList<RarityParameterCountModel>() :
                    getParameterCounts().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("parameters", getParameters() == null ? new ArrayList<RarityParameterValueModel>() :
                    getParameters().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(RarityParameterModel o) {
		return rarityParameterModelId.compareTo(o.rarityParameterModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.rarityParameterModelId == null) ? 0 : this.rarityParameterModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.maximumParameterCount == null) ? 0 : this.maximumParameterCount.hashCode());
        result = prime * result + ((this.parameterCounts == null) ? 0 : this.parameterCounts.hashCode());
        result = prime * result + ((this.parameters == null) ? 0 : this.parameters.hashCode());
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
		RarityParameterModel other = (RarityParameterModel) o;
		if (rarityParameterModelId == null) {
			return other.rarityParameterModelId == null;
		} else if (!rarityParameterModelId.equals(other.rarityParameterModelId)) {
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
		if (maximumParameterCount == null) {
			return other.maximumParameterCount == null;
		} else if (!maximumParameterCount.equals(other.maximumParameterCount)) {
			return false;
		}
		if (parameterCounts == null) {
			return other.parameterCounts == null;
		} else if (!parameterCounts.equals(other.parameterCounts)) {
			return false;
		}
		if (parameters == null) {
			return other.parameters == null;
		} else if (!parameters.equals(other.parameters)) {
			return false;
		}
		return true;
	}
}