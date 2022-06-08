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

package io.gs2.experience.model;

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
public class Status implements IModel, Serializable, Comparable<Status> {
	private String statusId;
	private String experienceName;
	private String userId;
	private String propertyId;
	private Long experienceValue;
	private Long rankValue;
	private Long rankCapValue;
	private Long nextRankUpExperienceValue;
	private Long createdAt;
	private Long updatedAt;
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public Status withStatusId(String statusId) {
		this.statusId = statusId;
		return this;
	}
	public String getExperienceName() {
		return experienceName;
	}
	public void setExperienceName(String experienceName) {
		this.experienceName = experienceName;
	}
	public Status withExperienceName(String experienceName) {
		this.experienceName = experienceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Status withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public Status withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public Long getExperienceValue() {
		return experienceValue;
	}
	public void setExperienceValue(Long experienceValue) {
		this.experienceValue = experienceValue;
	}
	public Status withExperienceValue(Long experienceValue) {
		this.experienceValue = experienceValue;
		return this;
	}
	public Long getRankValue() {
		return rankValue;
	}
	public void setRankValue(Long rankValue) {
		this.rankValue = rankValue;
	}
	public Status withRankValue(Long rankValue) {
		this.rankValue = rankValue;
		return this;
	}
	public Long getRankCapValue() {
		return rankCapValue;
	}
	public void setRankCapValue(Long rankCapValue) {
		this.rankCapValue = rankCapValue;
	}
	public Status withRankCapValue(Long rankCapValue) {
		this.rankCapValue = rankCapValue;
		return this;
	}
	public Long getNextRankUpExperienceValue() {
		return nextRankUpExperienceValue;
	}
	public void setNextRankUpExperienceValue(Long nextRankUpExperienceValue) {
		this.nextRankUpExperienceValue = nextRankUpExperienceValue;
	}
	public Status withNextRankUpExperienceValue(Long nextRankUpExperienceValue) {
		this.nextRankUpExperienceValue = nextRankUpExperienceValue;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Status withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Status withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Status fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Status()
            .withStatusId(data.get("statusId") == null || data.get("statusId").isNull() ? null : data.get("statusId").asText())
            .withExperienceName(data.get("experienceName") == null || data.get("experienceName").isNull() ? null : data.get("experienceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withExperienceValue(data.get("experienceValue") == null || data.get("experienceValue").isNull() ? null : data.get("experienceValue").longValue())
            .withRankValue(data.get("rankValue") == null || data.get("rankValue").isNull() ? null : data.get("rankValue").longValue())
            .withRankCapValue(data.get("rankCapValue") == null || data.get("rankCapValue").isNull() ? null : data.get("rankCapValue").longValue())
            .withNextRankUpExperienceValue(data.get("nextRankUpExperienceValue") == null || data.get("nextRankUpExperienceValue").isNull() ? null : data.get("nextRankUpExperienceValue").longValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("statusId", getStatusId());
                put("experienceName", getExperienceName());
                put("userId", getUserId());
                put("propertyId", getPropertyId());
                put("experienceValue", getExperienceValue());
                put("rankValue", getRankValue());
                put("rankCapValue", getRankCapValue());
                put("nextRankUpExperienceValue", getNextRankUpExperienceValue());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Status o) {
		return statusId.compareTo(o.statusId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.statusId == null) ? 0 : this.statusId.hashCode());
        result = prime * result + ((this.experienceName == null) ? 0 : this.experienceName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.propertyId == null) ? 0 : this.propertyId.hashCode());
        result = prime * result + ((this.experienceValue == null) ? 0 : this.experienceValue.hashCode());
        result = prime * result + ((this.rankValue == null) ? 0 : this.rankValue.hashCode());
        result = prime * result + ((this.rankCapValue == null) ? 0 : this.rankCapValue.hashCode());
        result = prime * result + ((this.nextRankUpExperienceValue == null) ? 0 : this.nextRankUpExperienceValue.hashCode());
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
		Status other = (Status) o;
		if (statusId == null) {
			return other.statusId == null;
		} else if (!statusId.equals(other.statusId)) {
			return false;
		}
		if (experienceName == null) {
			return other.experienceName == null;
		} else if (!experienceName.equals(other.experienceName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (propertyId == null) {
			return other.propertyId == null;
		} else if (!propertyId.equals(other.propertyId)) {
			return false;
		}
		if (experienceValue == null) {
			return other.experienceValue == null;
		} else if (!experienceValue.equals(other.experienceValue)) {
			return false;
		}
		if (rankValue == null) {
			return other.rankValue == null;
		} else if (!rankValue.equals(other.rankValue)) {
			return false;
		}
		if (rankCapValue == null) {
			return other.rankCapValue == null;
		} else if (!rankCapValue.equals(other.rankCapValue)) {
			return false;
		}
		if (nextRankUpExperienceValue == null) {
			return other.nextRankUpExperienceValue == null;
		} else if (!nextRankUpExperienceValue.equals(other.nextRankUpExperienceValue)) {
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