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

package io.gs2.ranking2.model;

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
public class GlobalRankingModel implements IModel, Serializable, Comparable<GlobalRankingModel> {
	private String globalRankingModelId;
	private String name;
	private String metadata;
	private Long minimumValue;
	private Long maximumValue;
	private Boolean sum;
	private String orderDirection;
	private String entryPeriodEventId;
	private List<RankingReward> rankingRewards;
	private String accessPeriodEventId;
	private String rewardCalculationIndex;
	public String getGlobalRankingModelId() {
		return globalRankingModelId;
	}
	public void setGlobalRankingModelId(String globalRankingModelId) {
		this.globalRankingModelId = globalRankingModelId;
	}
	public GlobalRankingModel withGlobalRankingModelId(String globalRankingModelId) {
		this.globalRankingModelId = globalRankingModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GlobalRankingModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public GlobalRankingModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getMinimumValue() {
		return minimumValue;
	}
	public void setMinimumValue(Long minimumValue) {
		this.minimumValue = minimumValue;
	}
	public GlobalRankingModel withMinimumValue(Long minimumValue) {
		this.minimumValue = minimumValue;
		return this;
	}
	public Long getMaximumValue() {
		return maximumValue;
	}
	public void setMaximumValue(Long maximumValue) {
		this.maximumValue = maximumValue;
	}
	public GlobalRankingModel withMaximumValue(Long maximumValue) {
		this.maximumValue = maximumValue;
		return this;
	}
	public Boolean getSum() {
		return sum;
	}
	public void setSum(Boolean sum) {
		this.sum = sum;
	}
	public GlobalRankingModel withSum(Boolean sum) {
		this.sum = sum;
		return this;
	}
	public String getOrderDirection() {
		return orderDirection;
	}
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	public GlobalRankingModel withOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
		return this;
	}
	public String getEntryPeriodEventId() {
		return entryPeriodEventId;
	}
	public void setEntryPeriodEventId(String entryPeriodEventId) {
		this.entryPeriodEventId = entryPeriodEventId;
	}
	public GlobalRankingModel withEntryPeriodEventId(String entryPeriodEventId) {
		this.entryPeriodEventId = entryPeriodEventId;
		return this;
	}
	public List<RankingReward> getRankingRewards() {
		return rankingRewards;
	}
	public void setRankingRewards(List<RankingReward> rankingRewards) {
		this.rankingRewards = rankingRewards;
	}
	public GlobalRankingModel withRankingRewards(List<RankingReward> rankingRewards) {
		this.rankingRewards = rankingRewards;
		return this;
	}
	public String getAccessPeriodEventId() {
		return accessPeriodEventId;
	}
	public void setAccessPeriodEventId(String accessPeriodEventId) {
		this.accessPeriodEventId = accessPeriodEventId;
	}
	public GlobalRankingModel withAccessPeriodEventId(String accessPeriodEventId) {
		this.accessPeriodEventId = accessPeriodEventId;
		return this;
	}
	public String getRewardCalculationIndex() {
		return rewardCalculationIndex;
	}
	public void setRewardCalculationIndex(String rewardCalculationIndex) {
		this.rewardCalculationIndex = rewardCalculationIndex;
	}
	public GlobalRankingModel withRewardCalculationIndex(String rewardCalculationIndex) {
		this.rewardCalculationIndex = rewardCalculationIndex;
		return this;
	}

    public static GlobalRankingModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GlobalRankingModel()
            .withGlobalRankingModelId(data.get("globalRankingModelId") == null || data.get("globalRankingModelId").isNull() ? null : data.get("globalRankingModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withMinimumValue(data.get("minimumValue") == null || data.get("minimumValue").isNull() ? null : data.get("minimumValue").longValue())
            .withMaximumValue(data.get("maximumValue") == null || data.get("maximumValue").isNull() ? null : data.get("maximumValue").longValue())
            .withSum(data.get("sum") == null || data.get("sum").isNull() ? null : data.get("sum").booleanValue())
            .withOrderDirection(data.get("orderDirection") == null || data.get("orderDirection").isNull() ? null : data.get("orderDirection").asText())
            .withEntryPeriodEventId(data.get("entryPeriodEventId") == null || data.get("entryPeriodEventId").isNull() ? null : data.get("entryPeriodEventId").asText())
            .withRankingRewards(data.get("rankingRewards") == null || data.get("rankingRewards").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("rankingRewards").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RankingReward.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withAccessPeriodEventId(data.get("accessPeriodEventId") == null || data.get("accessPeriodEventId").isNull() ? null : data.get("accessPeriodEventId").asText())
            .withRewardCalculationIndex(data.get("rewardCalculationIndex") == null || data.get("rewardCalculationIndex").isNull() ? null : data.get("rewardCalculationIndex").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("globalRankingModelId", getGlobalRankingModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("minimumValue", getMinimumValue());
                put("maximumValue", getMaximumValue());
                put("sum", getSum());
                put("orderDirection", getOrderDirection());
                put("entryPeriodEventId", getEntryPeriodEventId());
                put("rankingRewards", getRankingRewards() == null ? null :
                    getRankingRewards().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("accessPeriodEventId", getAccessPeriodEventId());
                put("rewardCalculationIndex", getRewardCalculationIndex());
            }}
        );
    }

	@Override
	public int compareTo(GlobalRankingModel o) {
		return globalRankingModelId.compareTo(o.globalRankingModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.globalRankingModelId == null) ? 0 : this.globalRankingModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.minimumValue == null) ? 0 : this.minimumValue.hashCode());
        result = prime * result + ((this.maximumValue == null) ? 0 : this.maximumValue.hashCode());
        result = prime * result + ((this.sum == null) ? 0 : this.sum.hashCode());
        result = prime * result + ((this.orderDirection == null) ? 0 : this.orderDirection.hashCode());
        result = prime * result + ((this.entryPeriodEventId == null) ? 0 : this.entryPeriodEventId.hashCode());
        result = prime * result + ((this.rankingRewards == null) ? 0 : this.rankingRewards.hashCode());
        result = prime * result + ((this.accessPeriodEventId == null) ? 0 : this.accessPeriodEventId.hashCode());
        result = prime * result + ((this.rewardCalculationIndex == null) ? 0 : this.rewardCalculationIndex.hashCode());
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
		GlobalRankingModel other = (GlobalRankingModel) o;
		if (globalRankingModelId == null) {
			return other.globalRankingModelId == null;
		} else if (!globalRankingModelId.equals(other.globalRankingModelId)) {
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
		if (sum == null) {
			return other.sum == null;
		} else if (!sum.equals(other.sum)) {
			return false;
		}
		if (orderDirection == null) {
			return other.orderDirection == null;
		} else if (!orderDirection.equals(other.orderDirection)) {
			return false;
		}
		if (entryPeriodEventId == null) {
			return other.entryPeriodEventId == null;
		} else if (!entryPeriodEventId.equals(other.entryPeriodEventId)) {
			return false;
		}
		if (rankingRewards == null) {
			return other.rankingRewards == null;
		} else if (!rankingRewards.equals(other.rankingRewards)) {
			return false;
		}
		if (accessPeriodEventId == null) {
			return other.accessPeriodEventId == null;
		} else if (!accessPeriodEventId.equals(other.accessPeriodEventId)) {
			return false;
		}
		if (rewardCalculationIndex == null) {
			return other.rewardCalculationIndex == null;
		} else if (!rewardCalculationIndex.equals(other.rewardCalculationIndex)) {
			return false;
		}
		return true;
	}
}