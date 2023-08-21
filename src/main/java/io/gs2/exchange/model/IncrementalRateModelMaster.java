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

package io.gs2.exchange.model;

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
public class IncrementalRateModelMaster implements IModel, Serializable, Comparable<IncrementalRateModelMaster> {
	private String incrementalRateModelId;
	private String name;
	private String description;
	private String metadata;
	private ConsumeAction consumeAction;
	private String calculateType;
	private Long baseValue;
	private Long coefficientValue;
	private String calculateScriptId;
	private String exchangeCountId;
	private Integer maximumExchangeCount;
	private List<AcquireAction> acquireActions;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getIncrementalRateModelId() {
		return incrementalRateModelId;
	}
	public void setIncrementalRateModelId(String incrementalRateModelId) {
		this.incrementalRateModelId = incrementalRateModelId;
	}
	public IncrementalRateModelMaster withIncrementalRateModelId(String incrementalRateModelId) {
		this.incrementalRateModelId = incrementalRateModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IncrementalRateModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public IncrementalRateModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public IncrementalRateModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public ConsumeAction getConsumeAction() {
		return consumeAction;
	}
	public void setConsumeAction(ConsumeAction consumeAction) {
		this.consumeAction = consumeAction;
	}
	public IncrementalRateModelMaster withConsumeAction(ConsumeAction consumeAction) {
		this.consumeAction = consumeAction;
		return this;
	}
	public String getCalculateType() {
		return calculateType;
	}
	public void setCalculateType(String calculateType) {
		this.calculateType = calculateType;
	}
	public IncrementalRateModelMaster withCalculateType(String calculateType) {
		this.calculateType = calculateType;
		return this;
	}
	public Long getBaseValue() {
		return baseValue;
	}
	public void setBaseValue(Long baseValue) {
		this.baseValue = baseValue;
	}
	public IncrementalRateModelMaster withBaseValue(Long baseValue) {
		this.baseValue = baseValue;
		return this;
	}
	public Long getCoefficientValue() {
		return coefficientValue;
	}
	public void setCoefficientValue(Long coefficientValue) {
		this.coefficientValue = coefficientValue;
	}
	public IncrementalRateModelMaster withCoefficientValue(Long coefficientValue) {
		this.coefficientValue = coefficientValue;
		return this;
	}
	public String getCalculateScriptId() {
		return calculateScriptId;
	}
	public void setCalculateScriptId(String calculateScriptId) {
		this.calculateScriptId = calculateScriptId;
	}
	public IncrementalRateModelMaster withCalculateScriptId(String calculateScriptId) {
		this.calculateScriptId = calculateScriptId;
		return this;
	}
	public String getExchangeCountId() {
		return exchangeCountId;
	}
	public void setExchangeCountId(String exchangeCountId) {
		this.exchangeCountId = exchangeCountId;
	}
	public IncrementalRateModelMaster withExchangeCountId(String exchangeCountId) {
		this.exchangeCountId = exchangeCountId;
		return this;
	}
	public Integer getMaximumExchangeCount() {
		return maximumExchangeCount;
	}
	public void setMaximumExchangeCount(Integer maximumExchangeCount) {
		this.maximumExchangeCount = maximumExchangeCount;
	}
	public IncrementalRateModelMaster withMaximumExchangeCount(Integer maximumExchangeCount) {
		this.maximumExchangeCount = maximumExchangeCount;
		return this;
	}
	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}
	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}
	public IncrementalRateModelMaster withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public IncrementalRateModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public IncrementalRateModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public IncrementalRateModelMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static IncrementalRateModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new IncrementalRateModelMaster()
            .withIncrementalRateModelId(data.get("incrementalRateModelId") == null || data.get("incrementalRateModelId").isNull() ? null : data.get("incrementalRateModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withConsumeAction(data.get("consumeAction") == null || data.get("consumeAction").isNull() ? null : ConsumeAction.fromJson(data.get("consumeAction")))
            .withCalculateType(data.get("calculateType") == null || data.get("calculateType").isNull() ? null : data.get("calculateType").asText())
            .withBaseValue(data.get("baseValue") == null || data.get("baseValue").isNull() ? null : data.get("baseValue").longValue())
            .withCoefficientValue(data.get("coefficientValue") == null || data.get("coefficientValue").isNull() ? null : data.get("coefficientValue").longValue())
            .withCalculateScriptId(data.get("calculateScriptId") == null || data.get("calculateScriptId").isNull() ? null : data.get("calculateScriptId").asText())
            .withExchangeCountId(data.get("exchangeCountId") == null || data.get("exchangeCountId").isNull() ? null : data.get("exchangeCountId").asText())
            .withMaximumExchangeCount(data.get("maximumExchangeCount") == null || data.get("maximumExchangeCount").isNull() ? null : data.get("maximumExchangeCount").intValue())
            .withAcquireActions(data.get("acquireActions") == null || data.get("acquireActions").isNull() ? new ArrayList<AcquireAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("incrementalRateModelId", getIncrementalRateModelId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("consumeAction", getConsumeAction() != null ? getConsumeAction().toJson() : null);
                put("calculateType", getCalculateType());
                put("baseValue", getBaseValue());
                put("coefficientValue", getCoefficientValue());
                put("calculateScriptId", getCalculateScriptId());
                put("exchangeCountId", getExchangeCountId());
                put("maximumExchangeCount", getMaximumExchangeCount());
                put("acquireActions", getAcquireActions() == null ? new ArrayList<AcquireAction>() :
                    getAcquireActions().stream().map(item -> {
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
	public int compareTo(IncrementalRateModelMaster o) {
		return incrementalRateModelId.compareTo(o.incrementalRateModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.incrementalRateModelId == null) ? 0 : this.incrementalRateModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.consumeAction == null) ? 0 : this.consumeAction.hashCode());
        result = prime * result + ((this.calculateType == null) ? 0 : this.calculateType.hashCode());
        result = prime * result + ((this.baseValue == null) ? 0 : this.baseValue.hashCode());
        result = prime * result + ((this.coefficientValue == null) ? 0 : this.coefficientValue.hashCode());
        result = prime * result + ((this.calculateScriptId == null) ? 0 : this.calculateScriptId.hashCode());
        result = prime * result + ((this.exchangeCountId == null) ? 0 : this.exchangeCountId.hashCode());
        result = prime * result + ((this.maximumExchangeCount == null) ? 0 : this.maximumExchangeCount.hashCode());
        result = prime * result + ((this.acquireActions == null) ? 0 : this.acquireActions.hashCode());
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
		IncrementalRateModelMaster other = (IncrementalRateModelMaster) o;
		if (incrementalRateModelId == null) {
			return other.incrementalRateModelId == null;
		} else if (!incrementalRateModelId.equals(other.incrementalRateModelId)) {
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
		if (consumeAction == null) {
			return other.consumeAction == null;
		} else if (!consumeAction.equals(other.consumeAction)) {
			return false;
		}
		if (calculateType == null) {
			return other.calculateType == null;
		} else if (!calculateType.equals(other.calculateType)) {
			return false;
		}
		if (baseValue == null) {
			return other.baseValue == null;
		} else if (!baseValue.equals(other.baseValue)) {
			return false;
		}
		if (coefficientValue == null) {
			return other.coefficientValue == null;
		} else if (!coefficientValue.equals(other.coefficientValue)) {
			return false;
		}
		if (calculateScriptId == null) {
			return other.calculateScriptId == null;
		} else if (!calculateScriptId.equals(other.calculateScriptId)) {
			return false;
		}
		if (exchangeCountId == null) {
			return other.exchangeCountId == null;
		} else if (!exchangeCountId.equals(other.exchangeCountId)) {
			return false;
		}
		if (maximumExchangeCount == null) {
			return other.maximumExchangeCount == null;
		} else if (!maximumExchangeCount.equals(other.maximumExchangeCount)) {
			return false;
		}
		if (acquireActions == null) {
			return other.acquireActions == null;
		} else if (!acquireActions.equals(other.acquireActions)) {
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