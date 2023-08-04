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
public class IncrementalRateModel implements IModel, Serializable, Comparable<IncrementalRateModel> {
	private String incrementalRateModelId;
	private String name;
	private String metadata;
	private ConsumeAction consumeAction;
	private String calculateType;
	private Long baseValue;
	private Long coefficientValue;
	private String calculateScriptId;
	private String exchangeCountId;
	private Integer maximumExchangeCount;
	private List<AcquireAction> acquireActions;
	public String getIncrementalRateModelId() {
		return incrementalRateModelId;
	}
	public void setIncrementalRateModelId(String incrementalRateModelId) {
		this.incrementalRateModelId = incrementalRateModelId;
	}
	public IncrementalRateModel withIncrementalRateModelId(String incrementalRateModelId) {
		this.incrementalRateModelId = incrementalRateModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IncrementalRateModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public IncrementalRateModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public ConsumeAction getConsumeAction() {
		return consumeAction;
	}
	public void setConsumeAction(ConsumeAction consumeAction) {
		this.consumeAction = consumeAction;
	}
	public IncrementalRateModel withConsumeAction(ConsumeAction consumeAction) {
		this.consumeAction = consumeAction;
		return this;
	}
	public String getCalculateType() {
		return calculateType;
	}
	public void setCalculateType(String calculateType) {
		this.calculateType = calculateType;
	}
	public IncrementalRateModel withCalculateType(String calculateType) {
		this.calculateType = calculateType;
		return this;
	}
	public Long getBaseValue() {
		return baseValue;
	}
	public void setBaseValue(Long baseValue) {
		this.baseValue = baseValue;
	}
	public IncrementalRateModel withBaseValue(Long baseValue) {
		this.baseValue = baseValue;
		return this;
	}
	public Long getCoefficientValue() {
		return coefficientValue;
	}
	public void setCoefficientValue(Long coefficientValue) {
		this.coefficientValue = coefficientValue;
	}
	public IncrementalRateModel withCoefficientValue(Long coefficientValue) {
		this.coefficientValue = coefficientValue;
		return this;
	}
	public String getCalculateScriptId() {
		return calculateScriptId;
	}
	public void setCalculateScriptId(String calculateScriptId) {
		this.calculateScriptId = calculateScriptId;
	}
	public IncrementalRateModel withCalculateScriptId(String calculateScriptId) {
		this.calculateScriptId = calculateScriptId;
		return this;
	}
	public String getExchangeCountId() {
		return exchangeCountId;
	}
	public void setExchangeCountId(String exchangeCountId) {
		this.exchangeCountId = exchangeCountId;
	}
	public IncrementalRateModel withExchangeCountId(String exchangeCountId) {
		this.exchangeCountId = exchangeCountId;
		return this;
	}
	public Integer getMaximumExchangeCount() {
		return maximumExchangeCount;
	}
	public void setMaximumExchangeCount(Integer maximumExchangeCount) {
		this.maximumExchangeCount = maximumExchangeCount;
	}
	public IncrementalRateModel withMaximumExchangeCount(Integer maximumExchangeCount) {
		this.maximumExchangeCount = maximumExchangeCount;
		return this;
	}
	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}
	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}
	public IncrementalRateModel withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}

    public static IncrementalRateModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new IncrementalRateModel()
            .withIncrementalRateModelId(data.get("incrementalRateModelId") == null || data.get("incrementalRateModelId").isNull() ? null : data.get("incrementalRateModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
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
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("incrementalRateModelId", getIncrementalRateModelId());
                put("name", getName());
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
            }}
        );
    }

	@Override
	public int compareTo(IncrementalRateModel o) {
		return incrementalRateModelId.compareTo(o.incrementalRateModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.incrementalRateModelId == null) ? 0 : this.incrementalRateModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.consumeAction == null) ? 0 : this.consumeAction.hashCode());
        result = prime * result + ((this.calculateType == null) ? 0 : this.calculateType.hashCode());
        result = prime * result + ((this.baseValue == null) ? 0 : this.baseValue.hashCode());
        result = prime * result + ((this.coefficientValue == null) ? 0 : this.coefficientValue.hashCode());
        result = prime * result + ((this.calculateScriptId == null) ? 0 : this.calculateScriptId.hashCode());
        result = prime * result + ((this.exchangeCountId == null) ? 0 : this.exchangeCountId.hashCode());
        result = prime * result + ((this.maximumExchangeCount == null) ? 0 : this.maximumExchangeCount.hashCode());
        result = prime * result + ((this.acquireActions == null) ? 0 : this.acquireActions.hashCode());
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
		IncrementalRateModel other = (IncrementalRateModel) o;
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
		return true;
	}
}