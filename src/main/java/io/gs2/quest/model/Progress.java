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

package io.gs2.quest.model;

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
public class Progress implements IModel, Serializable, Comparable<Progress> {
	private String progressId;
	private String userId;
	private String transactionId;
	private String questModelId;
	private Long randomSeed;
	private List<Reward> rewards;
	private String metadata;
	private Long createdAt;
	private Long updatedAt;

	public String getProgressId() {
		return progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	public Progress withProgressId(String progressId) {
		this.progressId = progressId;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Progress withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Progress withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public String getQuestModelId() {
		return questModelId;
	}

	public void setQuestModelId(String questModelId) {
		this.questModelId = questModelId;
	}

	public Progress withQuestModelId(String questModelId) {
		this.questModelId = questModelId;
		return this;
	}

	public Long getRandomSeed() {
		return randomSeed;
	}

	public void setRandomSeed(Long randomSeed) {
		this.randomSeed = randomSeed;
	}

	public Progress withRandomSeed(Long randomSeed) {
		this.randomSeed = randomSeed;
		return this;
	}

	public List<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}

	public Progress withRewards(List<Reward> rewards) {
		this.rewards = rewards;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public Progress withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Progress withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Progress withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Progress fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Progress()
            .withProgressId(data.get("progressId") == null || data.get("progressId").isNull() ? null : data.get("progressId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withQuestModelId(data.get("questModelId") == null || data.get("questModelId").isNull() ? null : data.get("questModelId").asText())
            .withRandomSeed(data.get("randomSeed") == null || data.get("randomSeed").isNull() ? null : data.get("randomSeed").longValue())
            .withRewards(data.get("rewards") == null || data.get("rewards").isNull() ? new ArrayList<Reward>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("rewards").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Reward.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("progressId", getProgressId());
                put("userId", getUserId());
                put("transactionId", getTransactionId());
                put("questModelId", getQuestModelId());
                put("randomSeed", getRandomSeed());
                put("rewards", getRewards() == null ? new ArrayList<Reward>() :
                    getRewards().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("metadata", getMetadata());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Progress o) {
		return progressId.compareTo(o.progressId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.progressId == null) ? 0 : this.progressId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.transactionId == null) ? 0 : this.transactionId.hashCode());
        result = prime * result + ((this.questModelId == null) ? 0 : this.questModelId.hashCode());
        result = prime * result + ((this.randomSeed == null) ? 0 : this.randomSeed.hashCode());
        result = prime * result + ((this.rewards == null) ? 0 : this.rewards.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
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
		Progress other = (Progress) o;
		if (progressId == null) {
			return other.progressId == null;
		} else if (!progressId.equals(other.progressId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (transactionId == null) {
			return other.transactionId == null;
		} else if (!transactionId.equals(other.transactionId)) {
			return false;
		}
		if (questModelId == null) {
			return other.questModelId == null;
		} else if (!questModelId.equals(other.questModelId)) {
			return false;
		}
		if (randomSeed == null) {
			return other.randomSeed == null;
		} else if (!randomSeed.equals(other.randomSeed)) {
			return false;
		}
		if (rewards == null) {
			return other.rewards == null;
		} else if (!rewards.equals(other.rewards)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
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