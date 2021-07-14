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
public class MissionTaskModelMaster implements IModel, Serializable, Comparable<MissionTaskModelMaster> {
	private String missionTaskId;
	private String name;
	private String metadata;
	private String description;
	private String counterName;
	private Long targetValue;
	private List<AcquireAction> completeAcquireActions;
	private String challengePeriodEventId;
	private String premiseMissionTaskName;
	private Long createdAt;
	private Long updatedAt;

	public String getMissionTaskId() {
		return missionTaskId;
	}

	public void setMissionTaskId(String missionTaskId) {
		this.missionTaskId = missionTaskId;
	}

	public MissionTaskModelMaster withMissionTaskId(String missionTaskId) {
		this.missionTaskId = missionTaskId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MissionTaskModelMaster withName(String name) {
		this.name = name;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public MissionTaskModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MissionTaskModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public MissionTaskModelMaster withCounterName(String counterName) {
		this.counterName = counterName;
		return this;
	}

	public Long getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(Long targetValue) {
		this.targetValue = targetValue;
	}

	public MissionTaskModelMaster withTargetValue(Long targetValue) {
		this.targetValue = targetValue;
		return this;
	}

	public List<AcquireAction> getCompleteAcquireActions() {
		return completeAcquireActions;
	}

	public void setCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
		this.completeAcquireActions = completeAcquireActions;
	}

	public MissionTaskModelMaster withCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
		this.completeAcquireActions = completeAcquireActions;
		return this;
	}

	public String getChallengePeriodEventId() {
		return challengePeriodEventId;
	}

	public void setChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
	}

	public MissionTaskModelMaster withChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
		return this;
	}

	public String getPremiseMissionTaskName() {
		return premiseMissionTaskName;
	}

	public void setPremiseMissionTaskName(String premiseMissionTaskName) {
		this.premiseMissionTaskName = premiseMissionTaskName;
	}

	public MissionTaskModelMaster withPremiseMissionTaskName(String premiseMissionTaskName) {
		this.premiseMissionTaskName = premiseMissionTaskName;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public MissionTaskModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public MissionTaskModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static MissionTaskModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new MissionTaskModelMaster()
            .withMissionTaskId(data.get("missionTaskId") == null || data.get("missionTaskId").isNull() ? null : data.get("missionTaskId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withCounterName(data.get("counterName") == null || data.get("counterName").isNull() ? null : data.get("counterName").asText())
            .withTargetValue(data.get("targetValue") == null || data.get("targetValue").isNull() ? null : data.get("targetValue").longValue())
            .withCompleteAcquireActions(data.get("completeAcquireActions") == null || data.get("completeAcquireActions").isNull() ? new ArrayList<AcquireAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("completeAcquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withChallengePeriodEventId(data.get("challengePeriodEventId") == null || data.get("challengePeriodEventId").isNull() ? null : data.get("challengePeriodEventId").asText())
            .withPremiseMissionTaskName(data.get("premiseMissionTaskName") == null || data.get("premiseMissionTaskName").isNull() ? null : data.get("premiseMissionTaskName").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("missionTaskId", getMissionTaskId());
                put("name", getName());
                put("metadata", getMetadata());
                put("description", getDescription());
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
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(MissionTaskModelMaster o) {
		return missionTaskId.compareTo(o.missionTaskId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.missionTaskId == null) ? 0 : this.missionTaskId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.counterName == null) ? 0 : this.counterName.hashCode());
        result = prime * result + ((this.targetValue == null) ? 0 : this.targetValue.hashCode());
        result = prime * result + ((this.completeAcquireActions == null) ? 0 : this.completeAcquireActions.hashCode());
        result = prime * result + ((this.challengePeriodEventId == null) ? 0 : this.challengePeriodEventId.hashCode());
        result = prime * result + ((this.premiseMissionTaskName == null) ? 0 : this.premiseMissionTaskName.hashCode());
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
		MissionTaskModelMaster other = (MissionTaskModelMaster) o;
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
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
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