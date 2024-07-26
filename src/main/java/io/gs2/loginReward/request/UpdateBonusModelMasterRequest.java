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

package io.gs2.loginReward.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.loginReward.model.AcquireAction;
import io.gs2.loginReward.model.Reward;
import io.gs2.loginReward.model.VerifyAction;
import io.gs2.loginReward.model.ConsumeAction;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateBonusModelMasterRequest extends Gs2BasicRequest<UpdateBonusModelMasterRequest> {
    private String namespaceName;
    private String bonusModelName;
    private String description;
    private String metadata;
    private String mode;
    private String periodEventId;
    private Integer resetHour;
    private String repeat;
    private List<Reward> rewards;
    private String missedReceiveRelief;
    private List<VerifyAction> missedReceiveReliefVerifyActions;
    private List<ConsumeAction> missedReceiveReliefConsumeActions;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateBonusModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getBonusModelName() {
		return bonusModelName;
	}
	public void setBonusModelName(String bonusModelName) {
		this.bonusModelName = bonusModelName;
	}
	public UpdateBonusModelMasterRequest withBonusModelName(String bonusModelName) {
		this.bonusModelName = bonusModelName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateBonusModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateBonusModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public UpdateBonusModelMasterRequest withMode(String mode) {
		this.mode = mode;
		return this;
	}
	public String getPeriodEventId() {
		return periodEventId;
	}
	public void setPeriodEventId(String periodEventId) {
		this.periodEventId = periodEventId;
	}
	public UpdateBonusModelMasterRequest withPeriodEventId(String periodEventId) {
		this.periodEventId = periodEventId;
		return this;
	}
	public Integer getResetHour() {
		return resetHour;
	}
	public void setResetHour(Integer resetHour) {
		this.resetHour = resetHour;
	}
	public UpdateBonusModelMasterRequest withResetHour(Integer resetHour) {
		this.resetHour = resetHour;
		return this;
	}
	public String getRepeat() {
		return repeat;
	}
	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}
	public UpdateBonusModelMasterRequest withRepeat(String repeat) {
		this.repeat = repeat;
		return this;
	}
	public List<Reward> getRewards() {
		return rewards;
	}
	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}
	public UpdateBonusModelMasterRequest withRewards(List<Reward> rewards) {
		this.rewards = rewards;
		return this;
	}
	public String getMissedReceiveRelief() {
		return missedReceiveRelief;
	}
	public void setMissedReceiveRelief(String missedReceiveRelief) {
		this.missedReceiveRelief = missedReceiveRelief;
	}
	public UpdateBonusModelMasterRequest withMissedReceiveRelief(String missedReceiveRelief) {
		this.missedReceiveRelief = missedReceiveRelief;
		return this;
	}
	public List<VerifyAction> getMissedReceiveReliefVerifyActions() {
		return missedReceiveReliefVerifyActions;
	}
	public void setMissedReceiveReliefVerifyActions(List<VerifyAction> missedReceiveReliefVerifyActions) {
		this.missedReceiveReliefVerifyActions = missedReceiveReliefVerifyActions;
	}
	public UpdateBonusModelMasterRequest withMissedReceiveReliefVerifyActions(List<VerifyAction> missedReceiveReliefVerifyActions) {
		this.missedReceiveReliefVerifyActions = missedReceiveReliefVerifyActions;
		return this;
	}
	public List<ConsumeAction> getMissedReceiveReliefConsumeActions() {
		return missedReceiveReliefConsumeActions;
	}
	public void setMissedReceiveReliefConsumeActions(List<ConsumeAction> missedReceiveReliefConsumeActions) {
		this.missedReceiveReliefConsumeActions = missedReceiveReliefConsumeActions;
	}
	public UpdateBonusModelMasterRequest withMissedReceiveReliefConsumeActions(List<ConsumeAction> missedReceiveReliefConsumeActions) {
		this.missedReceiveReliefConsumeActions = missedReceiveReliefConsumeActions;
		return this;
	}

    public static UpdateBonusModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateBonusModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withBonusModelName(data.get("bonusModelName") == null || data.get("bonusModelName").isNull() ? null : data.get("bonusModelName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
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
                put("namespaceName", getNamespaceName());
                put("bonusModelName", getBonusModelName());
                put("description", getDescription());
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
}