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
public class CreateIncrementalRateModelMasterRequest extends Gs2BasicRequest<CreateIncrementalRateModelMasterRequest> {
    private String namespaceName;
    private String name;
    private String description;
    private String metadata;
    private ConsumeAction consumeAction;
    private String calculateType;
    private Long baseValue;
    private Long coefficientValue;
    private String calculateScriptId;
    private String exchangeCountId;
    private List<AcquireAction> acquireActions;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateIncrementalRateModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateIncrementalRateModelMasterRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateIncrementalRateModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CreateIncrementalRateModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public ConsumeAction getConsumeAction() {
		return consumeAction;
	}
	public void setConsumeAction(ConsumeAction consumeAction) {
		this.consumeAction = consumeAction;
	}
	public CreateIncrementalRateModelMasterRequest withConsumeAction(ConsumeAction consumeAction) {
		this.consumeAction = consumeAction;
		return this;
	}
	public String getCalculateType() {
		return calculateType;
	}
	public void setCalculateType(String calculateType) {
		this.calculateType = calculateType;
	}
	public CreateIncrementalRateModelMasterRequest withCalculateType(String calculateType) {
		this.calculateType = calculateType;
		return this;
	}
	public Long getBaseValue() {
		return baseValue;
	}
	public void setBaseValue(Long baseValue) {
		this.baseValue = baseValue;
	}
	public CreateIncrementalRateModelMasterRequest withBaseValue(Long baseValue) {
		this.baseValue = baseValue;
		return this;
	}
	public Long getCoefficientValue() {
		return coefficientValue;
	}
	public void setCoefficientValue(Long coefficientValue) {
		this.coefficientValue = coefficientValue;
	}
	public CreateIncrementalRateModelMasterRequest withCoefficientValue(Long coefficientValue) {
		this.coefficientValue = coefficientValue;
		return this;
	}
	public String getCalculateScriptId() {
		return calculateScriptId;
	}
	public void setCalculateScriptId(String calculateScriptId) {
		this.calculateScriptId = calculateScriptId;
	}
	public CreateIncrementalRateModelMasterRequest withCalculateScriptId(String calculateScriptId) {
		this.calculateScriptId = calculateScriptId;
		return this;
	}
	public String getExchangeCountId() {
		return exchangeCountId;
	}
	public void setExchangeCountId(String exchangeCountId) {
		this.exchangeCountId = exchangeCountId;
	}
	public CreateIncrementalRateModelMasterRequest withExchangeCountId(String exchangeCountId) {
		this.exchangeCountId = exchangeCountId;
		return this;
	}
	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}
	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}
	public CreateIncrementalRateModelMasterRequest withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}

    public static CreateIncrementalRateModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateIncrementalRateModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withConsumeAction(data.get("consumeAction") == null || data.get("consumeAction").isNull() ? null : ConsumeAction.fromJson(data.get("consumeAction")))
            .withCalculateType(data.get("calculateType") == null || data.get("calculateType").isNull() ? null : data.get("calculateType").asText())
            .withBaseValue(data.get("baseValue") == null || data.get("baseValue").isNull() ? null : data.get("baseValue").longValue())
            .withCoefficientValue(data.get("coefficientValue") == null || data.get("coefficientValue").isNull() ? null : data.get("coefficientValue").longValue())
            .withCalculateScriptId(data.get("calculateScriptId") == null || data.get("calculateScriptId").isNull() ? null : data.get("calculateScriptId").asText())
            .withExchangeCountId(data.get("exchangeCountId") == null || data.get("exchangeCountId").isNull() ? null : data.get("exchangeCountId").asText())
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
                put("namespaceName", getNamespaceName());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("consumeAction", getConsumeAction() != null ? getConsumeAction().toJson() : null);
                put("calculateType", getCalculateType());
                put("baseValue", getBaseValue());
                put("coefficientValue", getCoefficientValue());
                put("calculateScriptId", getCalculateScriptId());
                put("exchangeCountId", getExchangeCountId());
                put("acquireActions", getAcquireActions() == null ? new ArrayList<AcquireAction>() :
                    getAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}