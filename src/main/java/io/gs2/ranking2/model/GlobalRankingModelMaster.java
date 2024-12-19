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
public class GlobalRankingModelMaster implements IModel, Serializable, Comparable<GlobalRankingModelMaster> {
	private String globalRankingModelId;
	private String name;
	private String description;
	private String metadata;
	private Long minimumValue;
	private Long maximumValue;
	private Boolean sum;
	private String orderDirection;
	private String entryPeriodEventId;
	private List<RankingReward> rankingRewards;
	private String accessPeriodEventId;
	private String rewardCalculationIndex;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getGlobalRankingModelId() {
		return globalRankingModelId;
	}
	public void setGlobalRankingModelId(String globalRankingModelId) {
		this.globalRankingModelId = globalRankingModelId;
	}
	public GlobalRankingModelMaster withGlobalRankingModelId(String globalRankingModelId) {
		this.globalRankingModelId = globalRankingModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GlobalRankingModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public GlobalRankingModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public GlobalRankingModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getMinimumValue() {
		return minimumValue;
	}
	public void setMinimumValue(Long minimumValue) {
		this.minimumValue = minimumValue;
	}
	public GlobalRankingModelMaster withMinimumValue(Long minimumValue) {
		this.minimumValue = minimumValue;
		return this;
	}
	public Long getMaximumValue() {
		return maximumValue;
	}
	public void setMaximumValue(Long maximumValue) {
		this.maximumValue = maximumValue;
	}
	public GlobalRankingModelMaster withMaximumValue(Long maximumValue) {
		this.maximumValue = maximumValue;
		return this;
	}
	public Boolean getSum() {
		return sum;
	}
	public void setSum(Boolean sum) {
		this.sum = sum;
	}
	public GlobalRankingModelMaster withSum(Boolean sum) {
		this.sum = sum;
		return this;
	}
	public String getOrderDirection() {
		return orderDirection;
	}
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	public GlobalRankingModelMaster withOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
		return this;
	}
	public String getEntryPeriodEventId() {
		return entryPeriodEventId;
	}
	public void setEntryPeriodEventId(String entryPeriodEventId) {
		this.entryPeriodEventId = entryPeriodEventId;
	}
	public GlobalRankingModelMaster withEntryPeriodEventId(String entryPeriodEventId) {
		this.entryPeriodEventId = entryPeriodEventId;
		return this;
	}
	public List<RankingReward> getRankingRewards() {
		return rankingRewards;
	}
	public void setRankingRewards(List<RankingReward> rankingRewards) {
		this.rankingRewards = rankingRewards;
	}
	public GlobalRankingModelMaster withRankingRewards(List<RankingReward> rankingRewards) {
		this.rankingRewards = rankingRewards;
		return this;
	}
	public String getAccessPeriodEventId() {
		return accessPeriodEventId;
	}
	public void setAccessPeriodEventId(String accessPeriodEventId) {
		this.accessPeriodEventId = accessPeriodEventId;
	}
	public GlobalRankingModelMaster withAccessPeriodEventId(String accessPeriodEventId) {
		this.accessPeriodEventId = accessPeriodEventId;
		return this;
	}
	public String getRewardCalculationIndex() {
		return rewardCalculationIndex;
	}
	public void setRewardCalculationIndex(String rewardCalculationIndex) {
		this.rewardCalculationIndex = rewardCalculationIndex;
	}
	public GlobalRankingModelMaster withRewardCalculationIndex(String rewardCalculationIndex) {
		this.rewardCalculationIndex = rewardCalculationIndex;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public GlobalRankingModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public GlobalRankingModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public GlobalRankingModelMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static GlobalRankingModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GlobalRankingModelMaster()
            .withGlobalRankingModelId(data.get("globalRankingModelId") == null || data.get("globalRankingModelId").isNull() ? null : data.get("globalRankingModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
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
            .withRewardCalculationIndex(data.get("rewardCalculationIndex") == null || data.get("rewardCalculationIndex").isNull() ? null : data.get("rewardCalculationIndex").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("globalRankingModelId", getGlobalRankingModelId());
                put("name", getName());
                put("description", getDescription());
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
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(GlobalRankingModelMaster o) {
		return globalRankingModelId.compareTo(o.globalRankingModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.globalRankingModelId == null) ? 0 : this.globalRankingModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.minimumValue == null) ? 0 : this.minimumValue.hashCode());
        result = prime * result + ((this.maximumValue == null) ? 0 : this.maximumValue.hashCode());
        result = prime * result + ((this.sum == null) ? 0 : this.sum.hashCode());
        result = prime * result + ((this.orderDirection == null) ? 0 : this.orderDirection.hashCode());
        result = prime * result + ((this.entryPeriodEventId == null) ? 0 : this.entryPeriodEventId.hashCode());
        result = prime * result + ((this.rankingRewards == null) ? 0 : this.rankingRewards.hashCode());
        result = prime * result + ((this.accessPeriodEventId == null) ? 0 : this.accessPeriodEventId.hashCode());
        result = prime * result + ((this.rewardCalculationIndex == null) ? 0 : this.rewardCalculationIndex.hashCode());
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
		GlobalRankingModelMaster other = (GlobalRankingModelMaster) o;
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