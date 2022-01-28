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
public class Complete implements IModel, Serializable, Comparable<Complete> {
	private String completeId;
	private String userId;
	private String missionGroupName;
	private List<String> completedMissionTaskNames;
	private List<String> receivedMissionTaskNames;
	private Long nextResetAt;
	private Long createdAt;
	private Long updatedAt;

	public String getCompleteId() {
		return completeId;
	}

	public void setCompleteId(String completeId) {
		this.completeId = completeId;
	}

	public Complete withCompleteId(String completeId) {
		this.completeId = completeId;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Complete withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getMissionGroupName() {
		return missionGroupName;
	}

	public void setMissionGroupName(String missionGroupName) {
		this.missionGroupName = missionGroupName;
	}

	public Complete withMissionGroupName(String missionGroupName) {
		this.missionGroupName = missionGroupName;
		return this;
	}

	public List<String> getCompletedMissionTaskNames() {
		return completedMissionTaskNames;
	}

	public void setCompletedMissionTaskNames(List<String> completedMissionTaskNames) {
		this.completedMissionTaskNames = completedMissionTaskNames;
	}

	public Complete withCompletedMissionTaskNames(List<String> completedMissionTaskNames) {
		this.completedMissionTaskNames = completedMissionTaskNames;
		return this;
	}

	public List<String> getReceivedMissionTaskNames() {
		return receivedMissionTaskNames;
	}

	public void setReceivedMissionTaskNames(List<String> receivedMissionTaskNames) {
		this.receivedMissionTaskNames = receivedMissionTaskNames;
	}

	public Complete withReceivedMissionTaskNames(List<String> receivedMissionTaskNames) {
		this.receivedMissionTaskNames = receivedMissionTaskNames;
		return this;
	}

	public Long getNextResetAt() {
		return nextResetAt;
	}

	public void setNextResetAt(Long nextResetAt) {
		this.nextResetAt = nextResetAt;
	}

	public Complete withNextResetAt(Long nextResetAt) {
		this.nextResetAt = nextResetAt;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Complete withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Complete withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Complete fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Complete()
            .withCompleteId(data.get("completeId") == null || data.get("completeId").isNull() ? null : data.get("completeId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withMissionGroupName(data.get("missionGroupName") == null || data.get("missionGroupName").isNull() ? null : data.get("missionGroupName").asText())
            .withCompletedMissionTaskNames(data.get("completedMissionTaskNames") == null || data.get("completedMissionTaskNames").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("completedMissionTaskNames").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withReceivedMissionTaskNames(data.get("receivedMissionTaskNames") == null || data.get("receivedMissionTaskNames").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("receivedMissionTaskNames").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withNextResetAt(data.get("nextResetAt") == null || data.get("nextResetAt").isNull() ? null : data.get("nextResetAt").longValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("completeId", getCompleteId());
                put("userId", getUserId());
                put("missionGroupName", getMissionGroupName());
                put("completedMissionTaskNames", getCompletedMissionTaskNames() == null ? new ArrayList<String>() :
                    getCompletedMissionTaskNames().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("receivedMissionTaskNames", getReceivedMissionTaskNames() == null ? new ArrayList<String>() :
                    getReceivedMissionTaskNames().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("nextResetAt", getNextResetAt());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Complete o) {
		return completeId.compareTo(o.completeId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.completeId == null) ? 0 : this.completeId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.missionGroupName == null) ? 0 : this.missionGroupName.hashCode());
        result = prime * result + ((this.completedMissionTaskNames == null) ? 0 : this.completedMissionTaskNames.hashCode());
        result = prime * result + ((this.receivedMissionTaskNames == null) ? 0 : this.receivedMissionTaskNames.hashCode());
        result = prime * result + ((this.nextResetAt == null) ? 0 : this.nextResetAt.hashCode());
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
		Complete other = (Complete) o;
		if (completeId == null) {
			return other.completeId == null;
		} else if (!completeId.equals(other.completeId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (missionGroupName == null) {
			return other.missionGroupName == null;
		} else if (!missionGroupName.equals(other.missionGroupName)) {
			return false;
		}
		if (completedMissionTaskNames == null) {
			return other.completedMissionTaskNames == null;
		} else if (!completedMissionTaskNames.equals(other.completedMissionTaskNames)) {
			return false;
		}
		if (receivedMissionTaskNames == null) {
			return other.receivedMissionTaskNames == null;
		} else if (!receivedMissionTaskNames.equals(other.receivedMissionTaskNames)) {
			return false;
		}
		if (nextResetAt == null) {
			return other.nextResetAt == null;
		} else if (!nextResetAt.equals(other.nextResetAt)) {
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