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
public class MissionGroupModelMaster implements IModel, Serializable, Comparable<MissionGroupModelMaster> {
	private String missionGroupId;
	private String name;
	private String metadata;
	private String description;
	private String resetType;
	private Integer resetDayOfMonth;
	private String resetDayOfWeek;
	private Integer resetHour;
	private Long anchorTimestamp;
	private Integer days;
	private String completeNotificationNamespaceId;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getMissionGroupId() {
		return missionGroupId;
	}
	public void setMissionGroupId(String missionGroupId) {
		this.missionGroupId = missionGroupId;
	}
	public MissionGroupModelMaster withMissionGroupId(String missionGroupId) {
		this.missionGroupId = missionGroupId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MissionGroupModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public MissionGroupModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MissionGroupModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getResetType() {
		return resetType;
	}
	public void setResetType(String resetType) {
		this.resetType = resetType;
	}
	public MissionGroupModelMaster withResetType(String resetType) {
		this.resetType = resetType;
		return this;
	}
	public Integer getResetDayOfMonth() {
		return resetDayOfMonth;
	}
	public void setResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
	}
	public MissionGroupModelMaster withResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
		return this;
	}
	public String getResetDayOfWeek() {
		return resetDayOfWeek;
	}
	public void setResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
	}
	public MissionGroupModelMaster withResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
		return this;
	}
	public Integer getResetHour() {
		return resetHour;
	}
	public void setResetHour(Integer resetHour) {
		this.resetHour = resetHour;
	}
	public MissionGroupModelMaster withResetHour(Integer resetHour) {
		this.resetHour = resetHour;
		return this;
	}
	public Long getAnchorTimestamp() {
		return anchorTimestamp;
	}
	public void setAnchorTimestamp(Long anchorTimestamp) {
		this.anchorTimestamp = anchorTimestamp;
	}
	public MissionGroupModelMaster withAnchorTimestamp(Long anchorTimestamp) {
		this.anchorTimestamp = anchorTimestamp;
		return this;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public MissionGroupModelMaster withDays(Integer days) {
		this.days = days;
		return this;
	}
	public String getCompleteNotificationNamespaceId() {
		return completeNotificationNamespaceId;
	}
	public void setCompleteNotificationNamespaceId(String completeNotificationNamespaceId) {
		this.completeNotificationNamespaceId = completeNotificationNamespaceId;
	}
	public MissionGroupModelMaster withCompleteNotificationNamespaceId(String completeNotificationNamespaceId) {
		this.completeNotificationNamespaceId = completeNotificationNamespaceId;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public MissionGroupModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public MissionGroupModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public MissionGroupModelMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static MissionGroupModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new MissionGroupModelMaster()
            .withMissionGroupId(data.get("missionGroupId") == null || data.get("missionGroupId").isNull() ? null : data.get("missionGroupId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withResetType(data.get("resetType") == null || data.get("resetType").isNull() ? null : data.get("resetType").asText())
            .withResetDayOfMonth(data.get("resetDayOfMonth") == null || data.get("resetDayOfMonth").isNull() ? null : data.get("resetDayOfMonth").intValue())
            .withResetDayOfWeek(data.get("resetDayOfWeek") == null || data.get("resetDayOfWeek").isNull() ? null : data.get("resetDayOfWeek").asText())
            .withResetHour(data.get("resetHour") == null || data.get("resetHour").isNull() ? null : data.get("resetHour").intValue())
            .withAnchorTimestamp(data.get("anchorTimestamp") == null || data.get("anchorTimestamp").isNull() ? null : data.get("anchorTimestamp").longValue())
            .withDays(data.get("days") == null || data.get("days").isNull() ? null : data.get("days").intValue())
            .withCompleteNotificationNamespaceId(data.get("completeNotificationNamespaceId") == null || data.get("completeNotificationNamespaceId").isNull() ? null : data.get("completeNotificationNamespaceId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("missionGroupId", getMissionGroupId());
                put("name", getName());
                put("metadata", getMetadata());
                put("description", getDescription());
                put("resetType", getResetType());
                put("resetDayOfMonth", getResetDayOfMonth());
                put("resetDayOfWeek", getResetDayOfWeek());
                put("resetHour", getResetHour());
                put("anchorTimestamp", getAnchorTimestamp());
                put("days", getDays());
                put("completeNotificationNamespaceId", getCompleteNotificationNamespaceId());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(MissionGroupModelMaster o) {
		return missionGroupId.compareTo(o.missionGroupId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.missionGroupId == null) ? 0 : this.missionGroupId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.resetType == null) ? 0 : this.resetType.hashCode());
        result = prime * result + ((this.resetDayOfMonth == null) ? 0 : this.resetDayOfMonth.hashCode());
        result = prime * result + ((this.resetDayOfWeek == null) ? 0 : this.resetDayOfWeek.hashCode());
        result = prime * result + ((this.resetHour == null) ? 0 : this.resetHour.hashCode());
        result = prime * result + ((this.anchorTimestamp == null) ? 0 : this.anchorTimestamp.hashCode());
        result = prime * result + ((this.days == null) ? 0 : this.days.hashCode());
        result = prime * result + ((this.completeNotificationNamespaceId == null) ? 0 : this.completeNotificationNamespaceId.hashCode());
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
		MissionGroupModelMaster other = (MissionGroupModelMaster) o;
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
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
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
		if (anchorTimestamp == null) {
			return other.anchorTimestamp == null;
		} else if (!anchorTimestamp.equals(other.anchorTimestamp)) {
			return false;
		}
		if (days == null) {
			return other.days == null;
		} else if (!days.equals(other.days)) {
			return false;
		}
		if (completeNotificationNamespaceId == null) {
			return other.completeNotificationNamespaceId == null;
		} else if (!completeNotificationNamespaceId.equals(other.completeNotificationNamespaceId)) {
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