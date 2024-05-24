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

package io.gs2.mission.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.mission.model.TargetCounterModel;
import io.gs2.mission.model.ConsumeAction;
import io.gs2.mission.model.AcquireAction;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateMissionTaskModelMasterRequest extends Gs2BasicRequest<UpdateMissionTaskModelMasterRequest> {
    private String namespaceName;
    private String missionGroupName;
    private String missionTaskName;
    private String metadata;
    private String description;
    private String verifyCompleteType;
    private TargetCounterModel targetCounter;
    private List<ConsumeAction> verifyCompleteConsumeActions;
    private List<AcquireAction> completeAcquireActions;
    private String challengePeriodEventId;
    private String premiseMissionTaskName;
    private String counterName;
    private String targetResetType;
    private Long targetValue;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateMissionTaskModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getMissionGroupName() {
		return missionGroupName;
	}
	public void setMissionGroupName(String missionGroupName) {
		this.missionGroupName = missionGroupName;
	}
	public UpdateMissionTaskModelMasterRequest withMissionGroupName(String missionGroupName) {
		this.missionGroupName = missionGroupName;
		return this;
	}
	public String getMissionTaskName() {
		return missionTaskName;
	}
	public void setMissionTaskName(String missionTaskName) {
		this.missionTaskName = missionTaskName;
	}
	public UpdateMissionTaskModelMasterRequest withMissionTaskName(String missionTaskName) {
		this.missionTaskName = missionTaskName;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateMissionTaskModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateMissionTaskModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getVerifyCompleteType() {
		return verifyCompleteType;
	}
	public void setVerifyCompleteType(String verifyCompleteType) {
		this.verifyCompleteType = verifyCompleteType;
	}
	public UpdateMissionTaskModelMasterRequest withVerifyCompleteType(String verifyCompleteType) {
		this.verifyCompleteType = verifyCompleteType;
		return this;
	}
	public TargetCounterModel getTargetCounter() {
		return targetCounter;
	}
	public void setTargetCounter(TargetCounterModel targetCounter) {
		this.targetCounter = targetCounter;
	}
	public UpdateMissionTaskModelMasterRequest withTargetCounter(TargetCounterModel targetCounter) {
		this.targetCounter = targetCounter;
		return this;
	}
	public List<ConsumeAction> getVerifyCompleteConsumeActions() {
		return verifyCompleteConsumeActions;
	}
	public void setVerifyCompleteConsumeActions(List<ConsumeAction> verifyCompleteConsumeActions) {
		this.verifyCompleteConsumeActions = verifyCompleteConsumeActions;
	}
	public UpdateMissionTaskModelMasterRequest withVerifyCompleteConsumeActions(List<ConsumeAction> verifyCompleteConsumeActions) {
		this.verifyCompleteConsumeActions = verifyCompleteConsumeActions;
		return this;
	}
	public List<AcquireAction> getCompleteAcquireActions() {
		return completeAcquireActions;
	}
	public void setCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
		this.completeAcquireActions = completeAcquireActions;
	}
	public UpdateMissionTaskModelMasterRequest withCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
		this.completeAcquireActions = completeAcquireActions;
		return this;
	}
	public String getChallengePeriodEventId() {
		return challengePeriodEventId;
	}
	public void setChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
	}
	public UpdateMissionTaskModelMasterRequest withChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
		return this;
	}
	public String getPremiseMissionTaskName() {
		return premiseMissionTaskName;
	}
	public void setPremiseMissionTaskName(String premiseMissionTaskName) {
		this.premiseMissionTaskName = premiseMissionTaskName;
	}
	public UpdateMissionTaskModelMasterRequest withPremiseMissionTaskName(String premiseMissionTaskName) {
		this.premiseMissionTaskName = premiseMissionTaskName;
		return this;
	}
    @Deprecated
	public String getCounterName() {
		return counterName;
	}
    @Deprecated
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
    @Deprecated
	public UpdateMissionTaskModelMasterRequest withCounterName(String counterName) {
		this.counterName = counterName;
		return this;
	}
    @Deprecated
	public String getTargetResetType() {
		return targetResetType;
	}
    @Deprecated
	public void setTargetResetType(String targetResetType) {
		this.targetResetType = targetResetType;
	}
    @Deprecated
	public UpdateMissionTaskModelMasterRequest withTargetResetType(String targetResetType) {
		this.targetResetType = targetResetType;
		return this;
	}
    @Deprecated
	public Long getTargetValue() {
		return targetValue;
	}
    @Deprecated
	public void setTargetValue(Long targetValue) {
		this.targetValue = targetValue;
	}
    @Deprecated
	public UpdateMissionTaskModelMasterRequest withTargetValue(Long targetValue) {
		this.targetValue = targetValue;
		return this;
	}

    public static UpdateMissionTaskModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateMissionTaskModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withMissionGroupName(data.get("missionGroupName") == null || data.get("missionGroupName").isNull() ? null : data.get("missionGroupName").asText())
            .withMissionTaskName(data.get("missionTaskName") == null || data.get("missionTaskName").isNull() ? null : data.get("missionTaskName").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withVerifyCompleteType(data.get("verifyCompleteType") == null || data.get("verifyCompleteType").isNull() ? null : data.get("verifyCompleteType").asText())
            .withTargetCounter(data.get("targetCounter") == null || data.get("targetCounter").isNull() ? null : TargetCounterModel.fromJson(data.get("targetCounter")))
            .withVerifyCompleteConsumeActions(data.get("verifyCompleteConsumeActions") == null || data.get("verifyCompleteConsumeActions").isNull() ? new ArrayList<ConsumeAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("verifyCompleteConsumeActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCompleteAcquireActions(data.get("completeAcquireActions") == null || data.get("completeAcquireActions").isNull() ? new ArrayList<AcquireAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("completeAcquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withChallengePeriodEventId(data.get("challengePeriodEventId") == null || data.get("challengePeriodEventId").isNull() ? null : data.get("challengePeriodEventId").asText())
            .withPremiseMissionTaskName(data.get("premiseMissionTaskName") == null || data.get("premiseMissionTaskName").isNull() ? null : data.get("premiseMissionTaskName").asText())
            .withCounterName(data.get("counterName") == null || data.get("counterName").isNull() ? null : data.get("counterName").asText())
            .withTargetResetType(data.get("targetResetType") == null || data.get("targetResetType").isNull() ? null : data.get("targetResetType").asText())
            .withTargetValue(data.get("targetValue") == null || data.get("targetValue").isNull() ? null : data.get("targetValue").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("missionGroupName", getMissionGroupName());
                put("missionTaskName", getMissionTaskName());
                put("metadata", getMetadata());
                put("description", getDescription());
                put("verifyCompleteType", getVerifyCompleteType());
                put("targetCounter", getTargetCounter() != null ? getTargetCounter().toJson() : null);
                put("verifyCompleteConsumeActions", getVerifyCompleteConsumeActions() == null ? new ArrayList<ConsumeAction>() :
                    getVerifyCompleteConsumeActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("completeAcquireActions", getCompleteAcquireActions() == null ? new ArrayList<AcquireAction>() :
                    getCompleteAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("challengePeriodEventId", getChallengePeriodEventId());
                put("premiseMissionTaskName", getPremiseMissionTaskName());
                put("counterName", getCounterName());
                put("targetResetType", getTargetResetType());
                put("targetValue", getTargetValue());
            }}
        );
    }
}