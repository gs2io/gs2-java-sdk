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

package io.gs2.exchange.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.exchange.model.ConsumeAction;
import io.gs2.exchange.model.AcquireAction;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateIncrementalRateModelMasterRequest extends Gs2BasicRequest<UpdateIncrementalRateModelMasterRequest> {
    private String namespaceName;
    private String rateName;
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
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateIncrementalRateModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRateName() {
		return rateName;
	}
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	public UpdateIncrementalRateModelMasterRequest withRateName(String rateName) {
		this.rateName = rateName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateIncrementalRateModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateIncrementalRateModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public ConsumeAction getConsumeAction() {
		return consumeAction;
	}
	public void setConsumeAction(ConsumeAction consumeAction) {
		this.consumeAction = consumeAction;
	}
	public UpdateIncrementalRateModelMasterRequest withConsumeAction(ConsumeAction consumeAction) {
		this.consumeAction = consumeAction;
		return this;
	}
	public String getCalculateType() {
		return calculateType;
	}
	public void setCalculateType(String calculateType) {
		this.calculateType = calculateType;
	}
	public UpdateIncrementalRateModelMasterRequest withCalculateType(String calculateType) {
		this.calculateType = calculateType;
		return this;
	}
	public Long getBaseValue() {
		return baseValue;
	}
	public void setBaseValue(Long baseValue) {
		this.baseValue = baseValue;
	}
	public UpdateIncrementalRateModelMasterRequest withBaseValue(Long baseValue) {
		this.baseValue = baseValue;
		return this;
	}
	public Long getCoefficientValue() {
		return coefficientValue;
	}
	public void setCoefficientValue(Long coefficientValue) {
		this.coefficientValue = coefficientValue;
	}
	public UpdateIncrementalRateModelMasterRequest withCoefficientValue(Long coefficientValue) {
		this.coefficientValue = coefficientValue;
		return this;
	}
	public String getCalculateScriptId() {
		return calculateScriptId;
	}
	public void setCalculateScriptId(String calculateScriptId) {
		this.calculateScriptId = calculateScriptId;
	}
	public UpdateIncrementalRateModelMasterRequest withCalculateScriptId(String calculateScriptId) {
		this.calculateScriptId = calculateScriptId;
		return this;
	}
	public String getExchangeCountId() {
		return exchangeCountId;
	}
	public void setExchangeCountId(String exchangeCountId) {
		this.exchangeCountId = exchangeCountId;
	}
	public UpdateIncrementalRateModelMasterRequest withExchangeCountId(String exchangeCountId) {
		this.exchangeCountId = exchangeCountId;
		return this;
	}
	public Integer getMaximumExchangeCount() {
		return maximumExchangeCount;
	}
	public void setMaximumExchangeCount(Integer maximumExchangeCount) {
		this.maximumExchangeCount = maximumExchangeCount;
	}
	public UpdateIncrementalRateModelMasterRequest withMaximumExchangeCount(Integer maximumExchangeCount) {
		this.maximumExchangeCount = maximumExchangeCount;
		return this;
	}
	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}
	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}
	public UpdateIncrementalRateModelMasterRequest withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}

    public static UpdateIncrementalRateModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateIncrementalRateModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRateName(data.get("rateName") == null || data.get("rateName").isNull() ? null : data.get("rateName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withConsumeAction(data.get("consumeAction") == null || data.get("consumeAction").isNull() ? null : ConsumeAction.fromJson(data.get("consumeAction")))
            .withCalculateType(data.get("calculateType") == null || data.get("calculateType").isNull() ? null : data.get("calculateType").asText())
            .withBaseValue(data.get("baseValue") == null || data.get("baseValue").isNull() ? null : data.get("baseValue").longValue())
            .withCoefficientValue(data.get("coefficientValue") == null || data.get("coefficientValue").isNull() ? null : data.get("coefficientValue").longValue())
            .withCalculateScriptId(data.get("calculateScriptId") == null || data.get("calculateScriptId").isNull() ? null : data.get("calculateScriptId").asText())
            .withExchangeCountId(data.get("exchangeCountId") == null || data.get("exchangeCountId").isNull() ? null : data.get("exchangeCountId").asText())
            .withMaximumExchangeCount(data.get("maximumExchangeCount") == null || data.get("maximumExchangeCount").isNull() ? null : data.get("maximumExchangeCount").intValue())
            .withAcquireActions(data.get("acquireActions") == null || data.get("acquireActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("rateName", getRateName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("consumeAction", getConsumeAction() != null ? getConsumeAction().toJson() : null);
                put("calculateType", getCalculateType());
                put("baseValue", getBaseValue());
                put("coefficientValue", getCoefficientValue());
                put("calculateScriptId", getCalculateScriptId());
                put("exchangeCountId", getExchangeCountId());
                put("maximumExchangeCount", getMaximumExchangeCount());
                put("acquireActions", getAcquireActions() == null ? null :
                    getAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}