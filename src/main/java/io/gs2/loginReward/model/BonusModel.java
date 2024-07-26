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

package io.gs2.loginReward.model;

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
public class BonusModel implements IModel, Serializable, Comparable<BonusModel> {
	private String bonusModelId;
	private String name;
	private String metadata;
	private String mode;
	private String periodEventId;
	private Integer resetHour;
	private String repeat;
	private List<Reward> rewards;
	private String missedReceiveRelief;
	private List<VerifyAction> missedReceiveReliefVerifyActions;
	private List<ConsumeAction> missedReceiveReliefConsumeActions;
	public String getBonusModelId() {
		return bonusModelId;
	}
	public void setBonusModelId(String bonusModelId) {
		this.bonusModelId = bonusModelId;
	}
	public BonusModel withBonusModelId(String bonusModelId) {
		this.bonusModelId = bonusModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BonusModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public BonusModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public BonusModel withMode(String mode) {
		this.mode = mode;
		return this;
	}
	public String getPeriodEventId() {
		return periodEventId;
	}
	public void setPeriodEventId(String periodEventId) {
		this.periodEventId = periodEventId;
	}
	public BonusModel withPeriodEventId(String periodEventId) {
		this.periodEventId = periodEventId;
		return this;
	}
	public Integer getResetHour() {
		return resetHour;
	}
	public void setResetHour(Integer resetHour) {
		this.resetHour = resetHour;
	}
	public BonusModel withResetHour(Integer resetHour) {
		this.resetHour = resetHour;
		return this;
	}
	public String getRepeat() {
		return repeat;
	}
	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}
	public BonusModel withRepeat(String repeat) {
		this.repeat = repeat;
		return this;
	}
	public List<Reward> getRewards() {
		return rewards;
	}
	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}
	public BonusModel withRewards(List<Reward> rewards) {
		this.rewards = rewards;
		return this;
	}
	public String getMissedReceiveRelief() {
		return missedReceiveRelief;
	}
	public void setMissedReceiveRelief(String missedReceiveRelief) {
		this.missedReceiveRelief = missedReceiveRelief;
	}
	public BonusModel withMissedReceiveRelief(String missedReceiveRelief) {
		this.missedReceiveRelief = missedReceiveRelief;
		return this;
	}
	public List<VerifyAction> getMissedReceiveReliefVerifyActions() {
		return missedReceiveReliefVerifyActions;
	}
	public void setMissedReceiveReliefVerifyActions(List<VerifyAction> missedReceiveReliefVerifyActions) {
		this.missedReceiveReliefVerifyActions = missedReceiveReliefVerifyActions;
	}
	public BonusModel withMissedReceiveReliefVerifyActions(List<VerifyAction> missedReceiveReliefVerifyActions) {
		this.missedReceiveReliefVerifyActions = missedReceiveReliefVerifyActions;
		return this;
	}
	public List<ConsumeAction> getMissedReceiveReliefConsumeActions() {
		return missedReceiveReliefConsumeActions;
	}
	public void setMissedReceiveReliefConsumeActions(List<ConsumeAction> missedReceiveReliefConsumeActions) {
		this.missedReceiveReliefConsumeActions = missedReceiveReliefConsumeActions;
	}
	public BonusModel withMissedReceiveReliefConsumeActions(List<ConsumeAction> missedReceiveReliefConsumeActions) {
		this.missedReceiveReliefConsumeActions = missedReceiveReliefConsumeActions;
		return this;
	}

    public static BonusModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BonusModel()
            .withBonusModelId(data.get("bonusModelId") == null || data.get("bonusModelId").isNull() ? null : data.get("bonusModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withMode(data.get("mode") == null || data.get("mode").isNull() ? null : data.get("mode").asText())
            .withPeriodEventId(data.get("periodEventId") == null || data.get("periodEventId").isNull() ? null : data.get("periodEventId").asText())
            .withResetHour(data.get("resetHour") == null || data.get("resetHour").isNull() ? null : data.get("resetHour").intValue())
            .withRepeat(data.get("repeat") == null || data.get("repeat").isNull() ? null : data.get("repeat").asText())
            .withRewards(data.get("rewards") == null || data.get("rewards").isNull() ? new ArrayList<Reward>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("rewards").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Reward.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withMissedReceiveRelief(data.get("missedReceiveRelief") == null || data.get("missedReceiveRelief").isNull() ? null : data.get("missedReceiveRelief").asText())
            .withMissedReceiveReliefVerifyActions(data.get("missedReceiveReliefVerifyActions") == null || data.get("missedReceiveReliefVerifyActions").isNull() ? new ArrayList<VerifyAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("missedReceiveReliefVerifyActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return VerifyAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withMissedReceiveReliefConsumeActions(data.get("missedReceiveReliefConsumeActions") == null || data.get("missedReceiveReliefConsumeActions").isNull() ? new ArrayList<ConsumeAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("missedReceiveReliefConsumeActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("bonusModelId", getBonusModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("mode", getMode());
                put("periodEventId", getPeriodEventId());
                put("resetHour", getResetHour());
                put("repeat", getRepeat());
                put("rewards", getRewards() == null ? new ArrayList<Reward>() :
                    getRewards().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("missedReceiveRelief", getMissedReceiveRelief());
                put("missedReceiveReliefVerifyActions", getMissedReceiveReliefVerifyActions() == null ? new ArrayList<VerifyAction>() :
                    getMissedReceiveReliefVerifyActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("missedReceiveReliefConsumeActions", getMissedReceiveReliefConsumeActions() == null ? new ArrayList<ConsumeAction>() :
                    getMissedReceiveReliefConsumeActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(BonusModel o) {
		return bonusModelId.compareTo(o.bonusModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.bonusModelId == null) ? 0 : this.bonusModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.mode == null) ? 0 : this.mode.hashCode());
        result = prime * result + ((this.periodEventId == null) ? 0 : this.periodEventId.hashCode());
        result = prime * result + ((this.resetHour == null) ? 0 : this.resetHour.hashCode());
        result = prime * result + ((this.repeat == null) ? 0 : this.repeat.hashCode());
        result = prime * result + ((this.rewards == null) ? 0 : this.rewards.hashCode());
        result = prime * result + ((this.missedReceiveRelief == null) ? 0 : this.missedReceiveRelief.hashCode());
        result = prime * result + ((this.missedReceiveReliefVerifyActions == null) ? 0 : this.missedReceiveReliefVerifyActions.hashCode());
        result = prime * result + ((this.missedReceiveReliefConsumeActions == null) ? 0 : this.missedReceiveReliefConsumeActions.hashCode());
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
		BonusModel other = (BonusModel) o;
		if (bonusModelId == null) {
			return other.bonusModelId == null;
		} else if (!bonusModelId.equals(other.bonusModelId)) {
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
		if (mode == null) {
			return other.mode == null;
		} else if (!mode.equals(other.mode)) {
			return false;
		}
		if (periodEventId == null) {
			return other.periodEventId == null;
		} else if (!periodEventId.equals(other.periodEventId)) {
			return false;
		}
		if (resetHour == null) {
			return other.resetHour == null;
		} else if (!resetHour.equals(other.resetHour)) {
			return false;
		}
		if (repeat == null) {
			return other.repeat == null;
		} else if (!repeat.equals(other.repeat)) {
			return false;
		}
		if (rewards == null) {
			return other.rewards == null;
		} else if (!rewards.equals(other.rewards)) {
			return false;
		}
		if (missedReceiveRelief == null) {
			return other.missedReceiveRelief == null;
		} else if (!missedReceiveRelief.equals(other.missedReceiveRelief)) {
			return false;
		}
		if (missedReceiveReliefVerifyActions == null) {
			return other.missedReceiveReliefVerifyActions == null;
		} else if (!missedReceiveReliefVerifyActions.equals(other.missedReceiveReliefVerifyActions)) {
			return false;
		}
		if (missedReceiveReliefConsumeActions == null) {
			return other.missedReceiveReliefConsumeActions == null;
		} else if (!missedReceiveReliefConsumeActions.equals(other.missedReceiveReliefConsumeActions)) {
			return false;
		}
		return true;
	}
}