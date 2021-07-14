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
public class MissionGroupModel implements IModel, Serializable, Comparable<MissionGroupModel> {
	private String missionGroupId;
	private String name;
	private String metadata;
	private List<MissionTaskModel> tasks;
	private String resetType;
	private Integer resetDayOfMonth;
	private String resetDayOfWeek;
	private Integer resetHour;
	private String completeNotificationNamespaceId;

	public String getMissionGroupId() {
		return missionGroupId;
	}

	public void setMissionGroupId(String missionGroupId) {
		this.missionGroupId = missionGroupId;
	}

	public MissionGroupModel withMissionGroupId(String missionGroupId) {
		this.missionGroupId = missionGroupId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MissionGroupModel withName(String name) {
		this.name = name;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public MissionGroupModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public List<MissionTaskModel> getTasks() {
		return tasks;
	}

	public void setTasks(List<MissionTaskModel> tasks) {
		this.tasks = tasks;
	}

	public MissionGroupModel withTasks(List<MissionTaskModel> tasks) {
		this.tasks = tasks;
		return this;
	}

	public String getResetType() {
		return resetType;
	}

	public void setResetType(String resetType) {
		this.resetType = resetType;
	}

	public MissionGroupModel withResetType(String resetType) {
		this.resetType = resetType;
		return this;
	}

	public Integer getResetDayOfMonth() {
		return resetDayOfMonth;
	}

	public void setResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
	}

	public MissionGroupModel withResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
		return this;
	}

	public String getResetDayOfWeek() {
		return resetDayOfWeek;
	}

	public void setResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
	}

	public MissionGroupModel withResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
		return this;
	}

	public Integer getResetHour() {
		return resetHour;
	}

	public void setResetHour(Integer resetHour) {
		this.resetHour = resetHour;
	}

	public MissionGroupModel withResetHour(Integer resetHour) {
		this.resetHour = resetHour;
		return this;
	}

	public String getCompleteNotificationNamespaceId() {
		return completeNotificationNamespaceId;
	}

	public void setCompleteNotificationNamespaceId(String completeNotificationNamespaceId) {
		this.completeNotificationNamespaceId = completeNotificationNamespaceId;
	}

	public MissionGroupModel withCompleteNotificationNamespaceId(String completeNotificationNamespaceId) {
		this.completeNotificationNamespaceId = completeNotificationNamespaceId;
		return this;
	}

    public static MissionGroupModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new MissionGroupModel()
            .withMissionGroupId(data.get("missionGroupId") == null || data.get("missionGroupId").isNull() ? null : data.get("missionGroupId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTasks(data.get("tasks") == null || data.get("tasks").isNull() ? new ArrayList<MissionTaskModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("tasks").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return MissionTaskModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withResetType(data.get("resetType") == null || data.get("resetType").isNull() ? null : data.get("resetType").asText())
            .withResetDayOfMonth(data.get("resetDayOfMonth") == null || data.get("resetDayOfMonth").isNull() ? null : data.get("resetDayOfMonth").intValue())
            .withResetDayOfWeek(data.get("resetDayOfWeek") == null || data.get("resetDayOfWeek").isNull() ? null : data.get("resetDayOfWeek").asText())
            .withResetHour(data.get("resetHour") == null || data.get("resetHour").isNull() ? null : data.get("resetHour").intValue())
            .withCompleteNotificationNamespaceId(data.get("completeNotificationNamespaceId") == null || data.get("completeNotificationNamespaceId").isNull() ? null : data.get("completeNotificationNamespaceId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("missionGroupId", getMissionGroupId());
                put("name", getName());
                put("metadata", getMetadata());
                put("tasks", getTasks() == null ? new ArrayList<MissionTaskModel>() :
                    getTasks().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("resetType", getResetType());
                put("resetDayOfMonth", getResetDayOfMonth());
                put("resetDayOfWeek", getResetDayOfWeek());
                put("resetHour", getResetHour());
                put("completeNotificationNamespaceId", getCompleteNotificationNamespaceId());
            }}
        );
    }

	@Override
	public int compareTo(MissionGroupModel o) {
		return missionGroupId.compareTo(o.missionGroupId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.missionGroupId == null) ? 0 : this.missionGroupId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.tasks == null) ? 0 : this.tasks.hashCode());
        result = prime * result + ((this.resetType == null) ? 0 : this.resetType.hashCode());
        result = prime * result + ((this.resetDayOfMonth == null) ? 0 : this.resetDayOfMonth.hashCode());
        result = prime * result + ((this.resetDayOfWeek == null) ? 0 : this.resetDayOfWeek.hashCode());
        result = prime * result + ((this.resetHour == null) ? 0 : this.resetHour.hashCode());
        result = prime * result + ((this.completeNotificationNamespaceId == null) ? 0 : this.completeNotificationNamespaceId.hashCode());
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
		MissionGroupModel other = (MissionGroupModel) o;
		if (missionGroupId == null) {
			return other.missionGroupId == null;
		} else if (!missionGroupId.equals(other.missionGroupId)) {
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
		if (tasks == null) {
			return other.tasks == null;
		} else if (!tasks.equals(other.tasks)) {
			return false;
		}
		if (resetType == null) {
			return other.resetType == null;
		} else if (!resetType.equals(other.resetType)) {
			return false;
		}
		if (resetDayOfMonth == null) {
			return other.resetDayOfMonth == null;
		} else if (!resetDayOfMonth.equals(other.resetDayOfMonth)) {
			return false;
		}
		if (resetDayOfWeek == null) {
			return other.resetDayOfWeek == null;
		} else if (!resetDayOfWeek.equals(other.resetDayOfWeek)) {
			return false;
		}
		if (resetHour == null) {
			return other.resetHour == null;
		} else if (!resetHour.equals(other.resetHour)) {
			return false;
		}
		if (completeNotificationNamespaceId == null) {
			return other.completeNotificationNamespaceId == null;
		} else if (!completeNotificationNamespaceId.equals(other.completeNotificationNamespaceId)) {
			return false;
		}
		return true;
	}
}