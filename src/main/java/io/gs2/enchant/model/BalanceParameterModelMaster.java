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
public class BalanceParameterModelMaster implements IModel, Serializable, Comparable<BalanceParameterModelMaster> {
	private String balanceParameterModelId;
	private String name;
	private String description;
	private String metadata;
	private Long totalValue;
	private String initialValueStrategy;
	private List<BalanceParameterValueModel> parameters;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getBalanceParameterModelId() {
		return balanceParameterModelId;
	}
	public void setBalanceParameterModelId(String balanceParameterModelId) {
		this.balanceParameterModelId = balanceParameterModelId;
	}
	public BalanceParameterModelMaster withBalanceParameterModelId(String balanceParameterModelId) {
		this.balanceParameterModelId = balanceParameterModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BalanceParameterModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BalanceParameterModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public BalanceParameterModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(Long totalValue) {
		this.totalValue = totalValue;
	}
	public BalanceParameterModelMaster withTotalValue(Long totalValue) {
		this.totalValue = totalValue;
		return this;
	}
	public String getInitialValueStrategy() {
		return initialValueStrategy;
	}
	public void setInitialValueStrategy(String initialValueStrategy) {
		this.initialValueStrategy = initialValueStrategy;
	}
	public BalanceParameterModelMaster withInitialValueStrategy(String initialValueStrategy) {
		this.initialValueStrategy = initialValueStrategy;
		return this;
	}
	public List<BalanceParameterValueModel> getParameters() {
		return parameters;
	}
	public void setParameters(List<BalanceParameterValueModel> parameters) {
		this.parameters = parameters;
	}
	public BalanceParameterModelMaster withParameters(List<BalanceParameterValueModel> parameters) {
		this.parameters = parameters;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public BalanceParameterModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public BalanceParameterModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public BalanceParameterModelMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static BalanceParameterModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BalanceParameterModelMaster()
            .withBalanceParameterModelId(data.get("balanceParameterModelId") == null || data.get("balanceParameterModelId").isNull() ? null : data.get("balanceParameterModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTotalValue(data.get("totalValue") == null || data.get("totalValue").isNull() ? null : data.get("totalValue").longValue())
            .withInitialValueStrategy(data.get("initialValueStrategy") == null || data.get("initialValueStrategy").isNull() ? null : data.get("initialValueStrategy").asText())
            .withParameters(data.get("parameters") == null || data.get("parameters").isNull() ? new ArrayList<BalanceParameterValueModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("parameters").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return BalanceParameterValueModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("balanceParameterModelId", getBalanceParameterModelId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("totalValue", getTotalValue());
                put("initialValueStrategy", getInitialValueStrategy());
                put("parameters", getParameters() == null ? new ArrayList<BalanceParameterValueModel>() :
                    getParameters().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(BalanceParameterModelMaster o) {
		return balanceParameterModelId.compareTo(o.balanceParameterModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.balanceParameterModelId == null) ? 0 : this.balanceParameterModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.totalValue == null) ? 0 : this.totalValue.hashCode());
        result = prime * result + ((this.initialValueStrategy == null) ? 0 : this.initialValueStrategy.hashCode());
        result = prime * result + ((this.parameters == null) ? 0 : this.parameters.hashCode());
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
		BalanceParameterModelMaster other = (BalanceParameterModelMaster) o;
		if (balanceParameterModelId == null) {
			return other.balanceParameterModelId == null;
		} else if (!balanceParameterModelId.equals(other.balanceParameterModelId)) {
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
		if (totalValue == null) {
			return other.totalValue == null;
		} else if (!totalValue.equals(other.totalValue)) {
			return false;
		}
		if (initialValueStrategy == null) {
			return other.initialValueStrategy == null;
		} else if (!initialValueStrategy.equals(other.initialValueStrategy)) {
			return false;
		}
		if (parameters == null) {
			return other.parameters == null;
		} else if (!parameters.equals(other.parameters)) {
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