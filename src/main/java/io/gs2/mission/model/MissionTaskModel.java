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

package io.gs2.mission.model;

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
public class MissionTaskModel implements IModel, Serializable, Comparable<MissionTaskModel> {
	private String missionTaskId;
	private String name;
	private String metadata;
	private String counterName;
	private Long targetValue;
	private List<AcquireAction> completeAcquireActions;
	private String challengePeriodEventId;
	private String premiseMissionTaskName;

	public String getMissionTaskId() {
		return missionTaskId;
	}

	public void setMissionTaskId(String missionTaskId) {
		this.missionTaskId = missionTaskId;
	}

	public MissionTaskModel withMissionTaskId(String missionTaskId) {
		this.missionTaskId = missionTaskId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MissionTaskModel withName(String name) {
		this.name = name;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public MissionTaskModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public MissionTaskModel withCounterName(String counterName) {
		this.counterName = counterName;
		return this;
	}

	public Long getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(Long targetValue) {
		this.targetValue = targetValue;
	}

	public MissionTaskModel withTargetValue(Long targetValue) {
		this.targetValue = targetValue;
		return this;
	}

	public List<AcquireAction> getCompleteAcquireActions() {
		return completeAcquireActions;
	}

	public void setCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
		this.completeAcquireActions = completeAcquireActions;
	}

	public MissionTaskModel withCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
		this.completeAcquireActions = completeAcquireActions;
		return this;
	}

	public String getChallengePeriodEventId() {
		return challengePeriodEventId;
	}

	public void setChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
	}

	public MissionTaskModel withChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
		return this;
	}

	public String getPremiseMissionTaskName() {
		return premiseMissionTaskName;
	}

	public void setPremiseMissionTaskName(String premiseMissionTaskName) {
		this.premiseMissionTaskName = premiseMissionTaskName;
	}

	public MissionTaskModel withPremiseMissionTaskName(String premiseMissionTaskName) {
		this.premiseMissionTaskName = premiseMissionTaskName;
		return this;
	}

    public static MissionTaskModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new MissionTaskModel()
            .withMissionTaskId(data.get("missionTaskId") == null || data.get("missionTaskId").isNull() ? null : data.get("missionTaskId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withCounterName(data.get("counterName") == null || data.get("counterName").isNull() ? null : data.get("counterName").asText())
            .withTargetValue(data.get("targetValue") == null || data.get("targetValue").isNull() ? null : data.get("targetValue").longValue())
            .withCompleteAcquireActions(data.get("completeAcquireActions") == null || data.get("completeAcquireActions").isNull() ? new ArrayList<AcquireAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("completeAcquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withChallengePeriodEventId(data.get("challengePeriodEventId") == null || data.get("challengePeriodEventId").isNull() ? null : data.get("challengePeriodEventId").asText())
            .withPremiseMissionTaskName(data.get("premiseMissionTaskName") == null || data.get("premiseMissionTaskName").isNull() ? null : data.get("premiseMissionTaskName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("missionTaskId", getMissionTaskId());
                put("name", getName());
                put("metadata", getMetadata());
                put("counterName", getCounterName());
                put("targetValue", getTargetValue());
                put("completeAcquireActions", getCompleteAcquireActions() == null ? new ArrayList<AcquireAction>() :
                    getCompleteAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("challengePeriodEventId", getChallengePeriodEventId());
                put("premiseMissionTaskName", getPremiseMissionTaskName());
            }}
        );
    }

	@Override
	public int compareTo(MissionTaskModel o) {
		return missionTaskId.compareTo(o.missionTaskId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.missionTaskId == null) ? 0 : this.missionTaskId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.counterName == null) ? 0 : this.counterName.hashCode());
        result = prime * result + ((this.targetValue == null) ? 0 : this.targetValue.hashCode());
        result = prime * result + ((this.completeAcquireActions == null) ? 0 : this.completeAcquireActions.hashCode());
        result = prime * result + ((this.challengePeriodEventId == null) ? 0 : this.challengePeriodEventId.hashCode());
        result = prime * result + ((this.premiseMissionTaskName == null) ? 0 : this.premiseMissionTaskName.hashCode());
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
		MissionTaskModel other = (MissionTaskModel) o;
		if (missionTaskId == null) {
			return other.missionTaskId == null;
		} else if (!missionTaskId.equals(other.missionTaskId)) {
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
		if (counterName == null) {
			return other.counterName == null;
		} else if (!counterName.equals(other.counterName)) {
			return false;
		}
		if (targetValue == null) {
			return other.targetValue == null;
		} else if (!targetValue.equals(other.targetValue)) {
			return false;
		}
		if (completeAcquireActions == null) {
			return other.completeAcquireActions == null;
		} else if (!completeAcquireActions.equals(other.completeAcquireActions)) {
			return false;
		}
		if (challengePeriodEventId == null) {
			return other.challengePeriodEventId == null;
		} else if (!challengePeriodEventId.equals(other.challengePeriodEventId)) {
			return false;
		}
		if (premiseMissionTaskName == null) {
			return other.premiseMissionTaskName == null;
		} else if (!premiseMissionTaskName.equals(other.premiseMissionTaskName)) {
			return false;
		}
		return true;
	}
}