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

package io.gs2.showcase.model;

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
public class RandomShowcase implements IModel, Serializable, Comparable<RandomShowcase> {
	private String randomShowcaseId;
	private String name;
	private String metadata;
	private Integer maximumNumberOfChoice;
	private List<RandomDisplayItemModel> displayItems;
	private Long baseTimestamp;
	private Integer resetIntervalHours;
	private String salesPeriodEventId;
	public String getRandomShowcaseId() {
		return randomShowcaseId;
	}
	public void setRandomShowcaseId(String randomShowcaseId) {
		this.randomShowcaseId = randomShowcaseId;
	}
	public RandomShowcase withRandomShowcaseId(String randomShowcaseId) {
		this.randomShowcaseId = randomShowcaseId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RandomShowcase withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public RandomShowcase withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getMaximumNumberOfChoice() {
		return maximumNumberOfChoice;
	}
	public void setMaximumNumberOfChoice(Integer maximumNumberOfChoice) {
		this.maximumNumberOfChoice = maximumNumberOfChoice;
	}
	public RandomShowcase withMaximumNumberOfChoice(Integer maximumNumberOfChoice) {
		this.maximumNumberOfChoice = maximumNumberOfChoice;
		return this;
	}
	public List<RandomDisplayItemModel> getDisplayItems() {
		return displayItems;
	}
	public void setDisplayItems(List<RandomDisplayItemModel> displayItems) {
		this.displayItems = displayItems;
	}
	public RandomShowcase withDisplayItems(List<RandomDisplayItemModel> displayItems) {
		this.displayItems = displayItems;
		return this;
	}
	public Long getBaseTimestamp() {
		return baseTimestamp;
	}
	public void setBaseTimestamp(Long baseTimestamp) {
		this.baseTimestamp = baseTimestamp;
	}
	public RandomShowcase withBaseTimestamp(Long baseTimestamp) {
		this.baseTimestamp = baseTimestamp;
		return this;
	}
	public Integer getResetIntervalHours() {
		return resetIntervalHours;
	}
	public void setResetIntervalHours(Integer resetIntervalHours) {
		this.resetIntervalHours = resetIntervalHours;
	}
	public RandomShowcase withResetIntervalHours(Integer resetIntervalHours) {
		this.resetIntervalHours = resetIntervalHours;
		return this;
	}
	public String getSalesPeriodEventId() {
		return salesPeriodEventId;
	}
	public void setSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
	}
	public RandomShowcase withSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
		return this;
	}

    public static RandomShowcase fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RandomShowcase()
            .withRandomShowcaseId(data.get("randomShowcaseId") == null || data.get("randomShowcaseId").isNull() ? null : data.get("randomShowcaseId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withMaximumNumberOfChoice(data.get("maximumNumberOfChoice") == null || data.get("maximumNumberOfChoice").isNull() ? null : data.get("maximumNumberOfChoice").intValue())
            .withDisplayItems(data.get("displayItems") == null || data.get("displayItems").isNull() ? new ArrayList<RandomDisplayItemModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("displayItems").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RandomDisplayItemModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withBaseTimestamp(data.get("baseTimestamp") == null || data.get("baseTimestamp").isNull() ? null : data.get("baseTimestamp").longValue())
            .withResetIntervalHours(data.get("resetIntervalHours") == null || data.get("resetIntervalHours").isNull() ? null : data.get("resetIntervalHours").intValue())
            .withSalesPeriodEventId(data.get("salesPeriodEventId") == null || data.get("salesPeriodEventId").isNull() ? null : data.get("salesPeriodEventId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("randomShowcaseId", getRandomShowcaseId());
                put("name", getName());
                put("metadata", getMetadata());
                put("maximumNumberOfChoice", getMaximumNumberOfChoice());
                put("displayItems", getDisplayItems() == null ? new ArrayList<RandomDisplayItemModel>() :
                    getDisplayItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("baseTimestamp", getBaseTimestamp());
                put("resetIntervalHours", getResetIntervalHours());
                put("salesPeriodEventId", getSalesPeriodEventId());
            }}
        );
    }

	@Override
	public int compareTo(RandomShowcase o) {
		return randomShowcaseId.compareTo(o.randomShowcaseId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.randomShowcaseId == null) ? 0 : this.randomShowcaseId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.maximumNumberOfChoice == null) ? 0 : this.maximumNumberOfChoice.hashCode());
        result = prime * result + ((this.displayItems == null) ? 0 : this.displayItems.hashCode());
        result = prime * result + ((this.baseTimestamp == null) ? 0 : this.baseTimestamp.hashCode());
        result = prime * result + ((this.resetIntervalHours == null) ? 0 : this.resetIntervalHours.hashCode());
        result = prime * result + ((this.salesPeriodEventId == null) ? 0 : this.salesPeriodEventId.hashCode());
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
		RandomShowcase other = (RandomShowcase) o;
		if (randomShowcaseId == null) {
			return other.randomShowcaseId == null;
		} else if (!randomShowcaseId.equals(other.randomShowcaseId)) {
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
		if (maximumNumberOfChoice == null) {
			return other.maximumNumberOfChoice == null;
		} else if (!maximumNumberOfChoice.equals(other.maximumNumberOfChoice)) {
			return false;
		}
		if (displayItems == null) {
			return other.displayItems == null;
		} else if (!displayItems.equals(other.displayItems)) {
			return false;
		}
		if (baseTimestamp == null) {
			return other.baseTimestamp == null;
		} else if (!baseTimestamp.equals(other.baseTimestamp)) {
			return false;
		}
		if (resetIntervalHours == null) {
			return other.resetIntervalHours == null;
		} else if (!resetIntervalHours.equals(other.resetIntervalHours)) {
			return false;
		}
		if (salesPeriodEventId == null) {
			return other.salesPeriodEventId == null;
		} else if (!salesPeriodEventId.equals(other.salesPeriodEventId)) {
			return false;
		}
		return true;
	}
}