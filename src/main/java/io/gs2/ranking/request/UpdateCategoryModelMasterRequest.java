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

package io.gs2.ranking.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.ranking.model.Scope;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateCategoryModelMasterRequest extends Gs2BasicRequest<UpdateCategoryModelMasterRequest> {
    private String namespaceName;
    private String categoryName;
    private String description;
    private String metadata;
    private Long minimumValue;
    private Long maximumValue;
    private String orderDirection;
    private String scope;
    private Boolean uniqueByUserId;
    private Boolean sum;
    private Integer calculateFixedTimingHour;
    private Integer calculateFixedTimingMinute;
    private Integer calculateIntervalMinutes;
    private List<Scope> additionalScopes;
    private String entryPeriodEventId;
    private String accessPeriodEventId;
    private List<String> ignoreUserIds;
    private String generation;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateCategoryModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public UpdateCategoryModelMasterRequest withCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateCategoryModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateCategoryModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getMinimumValue() {
		return minimumValue;
	}
	public void setMinimumValue(Long minimumValue) {
		this.minimumValue = minimumValue;
	}
	public UpdateCategoryModelMasterRequest withMinimumValue(Long minimumValue) {
		this.minimumValue = minimumValue;
		return this;
	}
	public Long getMaximumValue() {
		return maximumValue;
	}
	public void setMaximumValue(Long maximumValue) {
		this.maximumValue = maximumValue;
	}
	public UpdateCategoryModelMasterRequest withMaximumValue(Long maximumValue) {
		this.maximumValue = maximumValue;
		return this;
	}
	public String getOrderDirection() {
		return orderDirection;
	}
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	public UpdateCategoryModelMasterRequest withOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
		return this;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public UpdateCategoryModelMasterRequest withScope(String scope) {
		this.scope = scope;
		return this;
	}
	public Boolean getUniqueByUserId() {
		return uniqueByUserId;
	}
	public void setUniqueByUserId(Boolean uniqueByUserId) {
		this.uniqueByUserId = uniqueByUserId;
	}
	public UpdateCategoryModelMasterRequest withUniqueByUserId(Boolean uniqueByUserId) {
		this.uniqueByUserId = uniqueByUserId;
		return this;
	}
	public Boolean getSum() {
		return sum;
	}
	public void setSum(Boolean sum) {
		this.sum = sum;
	}
	public UpdateCategoryModelMasterRequest withSum(Boolean sum) {
		this.sum = sum;
		return this;
	}
	public Integer getCalculateFixedTimingHour() {
		return calculateFixedTimingHour;
	}
	public void setCalculateFixedTimingHour(Integer calculateFixedTimingHour) {
		this.calculateFixedTimingHour = calculateFixedTimingHour;
	}
	public UpdateCategoryModelMasterRequest withCalculateFixedTimingHour(Integer calculateFixedTimingHour) {
		this.calculateFixedTimingHour = calculateFixedTimingHour;
		return this;
	}
	public Integer getCalculateFixedTimingMinute() {
		return calculateFixedTimingMinute;
	}
	public void setCalculateFixedTimingMinute(Integer calculateFixedTimingMinute) {
		this.calculateFixedTimingMinute = calculateFixedTimingMinute;
	}
	public UpdateCategoryModelMasterRequest withCalculateFixedTimingMinute(Integer calculateFixedTimingMinute) {
		this.calculateFixedTimingMinute = calculateFixedTimingMinute;
		return this;
	}
	public Integer getCalculateIntervalMinutes() {
		return calculateIntervalMinutes;
	}
	public void setCalculateIntervalMinutes(Integer calculateIntervalMinutes) {
		this.calculateIntervalMinutes = calculateIntervalMinutes;
	}
	public UpdateCategoryModelMasterRequest withCalculateIntervalMinutes(Integer calculateIntervalMinutes) {
		this.calculateIntervalMinutes = calculateIntervalMinutes;
		return this;
	}
	public List<Scope> getAdditionalScopes() {
		return additionalScopes;
	}
	public void setAdditionalScopes(List<Scope> additionalScopes) {
		this.additionalScopes = additionalScopes;
	}
	public UpdateCategoryModelMasterRequest withAdditionalScopes(List<Scope> additionalScopes) {
		this.additionalScopes = additionalScopes;
		return this;
	}
	public String getEntryPeriodEventId() {
		return entryPeriodEventId;
	}
	public void setEntryPeriodEventId(String entryPeriodEventId) {
		this.entryPeriodEventId = entryPeriodEventId;
	}
	public UpdateCategoryModelMasterRequest withEntryPeriodEventId(String entryPeriodEventId) {
		this.entryPeriodEventId = entryPeriodEventId;
		return this;
	}
	public String getAccessPeriodEventId() {
		return accessPeriodEventId;
	}
	public void setAccessPeriodEventId(String accessPeriodEventId) {
		this.accessPeriodEventId = accessPeriodEventId;
	}
	public UpdateCategoryModelMasterRequest withAccessPeriodEventId(String accessPeriodEventId) {
		this.accessPeriodEventId = accessPeriodEventId;
		return this;
	}
	public List<String> getIgnoreUserIds() {
		return ignoreUserIds;
	}
	public void setIgnoreUserIds(List<String> ignoreUserIds) {
		this.ignoreUserIds = ignoreUserIds;
	}
	public UpdateCategoryModelMasterRequest withIgnoreUserIds(List<String> ignoreUserIds) {
		this.ignoreUserIds = ignoreUserIds;
		return this;
	}
	public String getGeneration() {
		return generation;
	}
	public void setGeneration(String generation) {
		this.generation = generation;
	}
	public UpdateCategoryModelMasterRequest withGeneration(String generation) {
		this.generation = generation;
		return this;
	}

    public static UpdateCategoryModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateCategoryModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withCategoryName(data.get("categoryName") == null || data.get("categoryName").isNull() ? null : data.get("categoryName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withMinimumValue(data.get("minimumValue") == null || data.get("minimumValue").isNull() ? null : data.get("minimumValue").longValue())
            .withMaximumValue(data.get("maximumValue") == null || data.get("maximumValue").isNull() ? null : data.get("maximumValue").longValue())
            .withOrderDirection(data.get("orderDirection") == null || data.get("orderDirection").isNull() ? null : data.get("orderDirection").asText())
            .withScope(data.get("scope") == null || data.get("scope").isNull() ? null : data.get("scope").asText())
            .withUniqueByUserId(data.get("uniqueByUserId") == null || data.get("uniqueByUserId").isNull() ? null : data.get("uniqueByUserId").booleanValue())
            .withSum(data.get("sum") == null || data.get("sum").isNull() ? null : data.get("sum").booleanValue())
            .withCalculateFixedTimingHour(data.get("calculateFixedTimingHour") == null || data.get("calculateFixedTimingHour").isNull() ? null : data.get("calculateFixedTimingHour").intValue())
            .withCalculateFixedTimingMinute(data.get("calculateFixedTimingMinute") == null || data.get("calculateFixedTimingMinute").isNull() ? null : data.get("calculateFixedTimingMinute").intValue())
            .withCalculateIntervalMinutes(data.get("calculateIntervalMinutes") == null || data.get("calculateIntervalMinutes").isNull() ? null : data.get("calculateIntervalMinutes").intValue())
            .withAdditionalScopes(data.get("additionalScopes") == null || data.get("additionalScopes").isNull() ? new ArrayList<Scope>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("additionalScopes").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Scope.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withEntryPeriodEventId(data.get("entryPeriodEventId") == null || data.get("entryPeriodEventId").isNull() ? null : data.get("entryPeriodEventId").asText())
            .withAccessPeriodEventId(data.get("accessPeriodEventId") == null || data.get("accessPeriodEventId").isNull() ? null : data.get("accessPeriodEventId").asText())
            .withIgnoreUserIds(data.get("ignoreUserIds") == null || data.get("ignoreUserIds").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("ignoreUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withGeneration(data.get("generation") == null || data.get("generation").isNull() ? null : data.get("generation").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("categoryName", getCategoryName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("minimumValue", getMinimumValue());
                put("maximumValue", getMaximumValue());
                put("orderDirection", getOrderDirection());
                put("scope", getScope());
                put("uniqueByUserId", getUniqueByUserId());
                put("sum", getSum());
                put("calculateFixedTimingHour", getCalculateFixedTimingHour());
                put("calculateFixedTimingMinute", getCalculateFixedTimingMinute());
                put("calculateIntervalMinutes", getCalculateIntervalMinutes());
                put("additionalScopes", getAdditionalScopes() == null ? new ArrayList<Scope>() :
                    getAdditionalScopes().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("entryPeriodEventId", getEntryPeriodEventId());
                put("accessPeriodEventId", getAccessPeriodEventId());
                put("ignoreUserIds", getIgnoreUserIds() == null ? new ArrayList<String>() :
                    getIgnoreUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("generation", getGeneration());
            }}
        );
    }
}