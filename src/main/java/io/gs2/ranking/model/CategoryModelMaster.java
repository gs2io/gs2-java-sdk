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

package io.gs2.ranking.model;

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
public class CategoryModelMaster implements IModel, Serializable, Comparable<CategoryModelMaster> {
	private String categoryModelId;
	private String name;
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
	private String entryPeriodEventId;
	private String accessPeriodEventId;
	private String generation;
	private Long createdAt;
	private Long updatedAt;
	public String getCategoryModelId() {
		return categoryModelId;
	}
	public void setCategoryModelId(String categoryModelId) {
		this.categoryModelId = categoryModelId;
	}
	public CategoryModelMaster withCategoryModelId(String categoryModelId) {
		this.categoryModelId = categoryModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CategoryModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CategoryModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CategoryModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getMinimumValue() {
		return minimumValue;
	}
	public void setMinimumValue(Long minimumValue) {
		this.minimumValue = minimumValue;
	}
	public CategoryModelMaster withMinimumValue(Long minimumValue) {
		this.minimumValue = minimumValue;
		return this;
	}
	public Long getMaximumValue() {
		return maximumValue;
	}
	public void setMaximumValue(Long maximumValue) {
		this.maximumValue = maximumValue;
	}
	public CategoryModelMaster withMaximumValue(Long maximumValue) {
		this.maximumValue = maximumValue;
		return this;
	}
	public String getOrderDirection() {
		return orderDirection;
	}
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	public CategoryModelMaster withOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
		return this;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public CategoryModelMaster withScope(String scope) {
		this.scope = scope;
		return this;
	}
	public Boolean getUniqueByUserId() {
		return uniqueByUserId;
	}
	public void setUniqueByUserId(Boolean uniqueByUserId) {
		this.uniqueByUserId = uniqueByUserId;
	}
	public CategoryModelMaster withUniqueByUserId(Boolean uniqueByUserId) {
		this.uniqueByUserId = uniqueByUserId;
		return this;
	}
	public Boolean getSum() {
		return sum;
	}
	public void setSum(Boolean sum) {
		this.sum = sum;
	}
	public CategoryModelMaster withSum(Boolean sum) {
		this.sum = sum;
		return this;
	}
	public Integer getCalculateFixedTimingHour() {
		return calculateFixedTimingHour;
	}
	public void setCalculateFixedTimingHour(Integer calculateFixedTimingHour) {
		this.calculateFixedTimingHour = calculateFixedTimingHour;
	}
	public CategoryModelMaster withCalculateFixedTimingHour(Integer calculateFixedTimingHour) {
		this.calculateFixedTimingHour = calculateFixedTimingHour;
		return this;
	}
	public Integer getCalculateFixedTimingMinute() {
		return calculateFixedTimingMinute;
	}
	public void setCalculateFixedTimingMinute(Integer calculateFixedTimingMinute) {
		this.calculateFixedTimingMinute = calculateFixedTimingMinute;
	}
	public CategoryModelMaster withCalculateFixedTimingMinute(Integer calculateFixedTimingMinute) {
		this.calculateFixedTimingMinute = calculateFixedTimingMinute;
		return this;
	}
	public Integer getCalculateIntervalMinutes() {
		return calculateIntervalMinutes;
	}
	public void setCalculateIntervalMinutes(Integer calculateIntervalMinutes) {
		this.calculateIntervalMinutes = calculateIntervalMinutes;
	}
	public CategoryModelMaster withCalculateIntervalMinutes(Integer calculateIntervalMinutes) {
		this.calculateIntervalMinutes = calculateIntervalMinutes;
		return this;
	}
	public String getEntryPeriodEventId() {
		return entryPeriodEventId;
	}
	public void setEntryPeriodEventId(String entryPeriodEventId) {
		this.entryPeriodEventId = entryPeriodEventId;
	}
	public CategoryModelMaster withEntryPeriodEventId(String entryPeriodEventId) {
		this.entryPeriodEventId = entryPeriodEventId;
		return this;
	}
	public String getAccessPeriodEventId() {
		return accessPeriodEventId;
	}
	public void setAccessPeriodEventId(String accessPeriodEventId) {
		this.accessPeriodEventId = accessPeriodEventId;
	}
	public CategoryModelMaster withAccessPeriodEventId(String accessPeriodEventId) {
		this.accessPeriodEventId = accessPeriodEventId;
		return this;
	}
	public String getGeneration() {
		return generation;
	}
	public void setGeneration(String generation) {
		this.generation = generation;
	}
	public CategoryModelMaster withGeneration(String generation) {
		this.generation = generation;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public CategoryModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public CategoryModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static CategoryModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CategoryModelMaster()
            .withCategoryModelId(data.get("categoryModelId") == null || data.get("categoryModelId").isNull() ? null : data.get("categoryModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
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
            .withEntryPeriodEventId(data.get("entryPeriodEventId") == null || data.get("entryPeriodEventId").isNull() ? null : data.get("entryPeriodEventId").asText())
            .withAccessPeriodEventId(data.get("accessPeriodEventId") == null || data.get("accessPeriodEventId").isNull() ? null : data.get("accessPeriodEventId").asText())
            .withGeneration(data.get("generation") == null || data.get("generation").isNull() ? null : data.get("generation").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("categoryModelId", getCategoryModelId());
                put("name", getName());
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
                put("entryPeriodEventId", getEntryPeriodEventId());
                put("accessPeriodEventId", getAccessPeriodEventId());
                put("generation", getGeneration());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(CategoryModelMaster o) {
		return categoryModelId.compareTo(o.categoryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.categoryModelId == null) ? 0 : this.categoryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.minimumValue == null) ? 0 : this.minimumValue.hashCode());
        result = prime * result + ((this.maximumValue == null) ? 0 : this.maximumValue.hashCode());
        result = prime * result + ((this.orderDirection == null) ? 0 : this.orderDirection.hashCode());
        result = prime * result + ((this.scope == null) ? 0 : this.scope.hashCode());
        result = prime * result + ((this.uniqueByUserId == null) ? 0 : this.uniqueByUserId.hashCode());
        result = prime * result + ((this.sum == null) ? 0 : this.sum.hashCode());
        result = prime * result + ((this.calculateFixedTimingHour == null) ? 0 : this.calculateFixedTimingHour.hashCode());
        result = prime * result + ((this.calculateFixedTimingMinute == null) ? 0 : this.calculateFixedTimingMinute.hashCode());
        result = prime * result + ((this.calculateIntervalMinutes == null) ? 0 : this.calculateIntervalMinutes.hashCode());
        result = prime * result + ((this.entryPeriodEventId == null) ? 0 : this.entryPeriodEventId.hashCode());
        result = prime * result + ((this.accessPeriodEventId == null) ? 0 : this.accessPeriodEventId.hashCode());
        result = prime * result + ((this.generation == null) ? 0 : this.generation.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		CategoryModelMaster other = (CategoryModelMaster) o;
		if (categoryModelId == null) {
			return other.categoryModelId == null;
		} else if (!categoryModelId.equals(other.categoryModelId)) {
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
		if (minimumValue == null) {
			return other.minimumValue == null;
		} else if (!minimumValue.equals(other.minimumValue)) {
			return false;
		}
		if (maximumValue == null) {
			return other.maximumValue == null;
		} else if (!maximumValue.equals(other.maximumValue)) {
			return false;
		}
		if (orderDirection == null) {
			return other.orderDirection == null;
		} else if (!orderDirection.equals(other.orderDirection)) {
			return false;
		}
		if (scope == null) {
			return other.scope == null;
		} else if (!scope.equals(other.scope)) {
			return false;
		}
		if (uniqueByUserId == null) {
			return other.uniqueByUserId == null;
		} else if (!uniqueByUserId.equals(other.uniqueByUserId)) {
			return false;
		}
		if (sum == null) {
			return other.sum == null;
		} else if (!sum.equals(other.sum)) {
			return false;
		}
		if (calculateFixedTimingHour == null) {
			return other.calculateFixedTimingHour == null;
		} else if (!calculateFixedTimingHour.equals(other.calculateFixedTimingHour)) {
			return false;
		}
		if (calculateFixedTimingMinute == null) {
			return other.calculateFixedTimingMinute == null;
		} else if (!calculateFixedTimingMinute.equals(other.calculateFixedTimingMinute)) {
			return false;
		}
		if (calculateIntervalMinutes == null) {
			return other.calculateIntervalMinutes == null;
		} else if (!calculateIntervalMinutes.equals(other.calculateIntervalMinutes)) {
			return false;
		}
		if (entryPeriodEventId == null) {
			return other.entryPeriodEventId == null;
		} else if (!entryPeriodEventId.equals(other.entryPeriodEventId)) {
			return false;
		}
		if (accessPeriodEventId == null) {
			return other.accessPeriodEventId == null;
		} else if (!accessPeriodEventId.equals(other.accessPeriodEventId)) {
			return false;
		}
		if (generation == null) {
			return other.generation == null;
		} else if (!generation.equals(other.generation)) {
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
		return true;
	}
}