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

package io.gs2.idle.model;

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
	private String categoryName;
	private String userId;
	private Long randomSeed;
	private Integer idleMinutes;
	private Long nextRewardsAt;
	private Integer maximumIdleMinutes;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Status withCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public Long getRandomSeed() {
		return randomSeed;
	}
	public void setRandomSeed(Long randomSeed) {
		this.randomSeed = randomSeed;
	}
	public Status withRandomSeed(Long randomSeed) {
		this.randomSeed = randomSeed;
		return this;
	}
	public Integer getIdleMinutes() {
		return idleMinutes;
	}
	public void setIdleMinutes(Integer idleMinutes) {
		this.idleMinutes = idleMinutes;
	}
	public Status withIdleMinutes(Integer idleMinutes) {
		this.idleMinutes = idleMinutes;
		return this;
	}
	public Long getNextRewardsAt() {
		return nextRewardsAt;
	}
	public void setNextRewardsAt(Long nextRewardsAt) {
		this.nextRewardsAt = nextRewardsAt;
	}
	public Status withNextRewardsAt(Long nextRewardsAt) {
		this.nextRewardsAt = nextRewardsAt;
		return this;
	}
	public Integer getMaximumIdleMinutes() {
		return maximumIdleMinutes;
	}
	public void setMaximumIdleMinutes(Integer maximumIdleMinutes) {
		this.maximumIdleMinutes = maximumIdleMinutes;
	}
	public Status withMaximumIdleMinutes(Integer maximumIdleMinutes) {
		this.maximumIdleMinutes = maximumIdleMinutes;
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
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Status withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Status fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Status()
            .withStatusId(data.get("statusId") == null || data.get("statusId").isNull() ? null : data.get("statusId").asText())
            .withCategoryName(data.get("categoryName") == null || data.get("categoryName").isNull() ? null : data.get("categoryName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withRandomSeed(data.get("randomSeed") == null || data.get("randomSeed").isNull() ? null : data.get("randomSeed").longValue())
            .withIdleMinutes(data.get("idleMinutes") == null || data.get("idleMinutes").isNull() ? null : data.get("idleMinutes").intValue())
            .withNextRewardsAt(data.get("nextRewardsAt") == null || data.get("nextRewardsAt").isNull() ? null : data.get("nextRewardsAt").longValue())
            .withMaximumIdleMinutes(data.get("maximumIdleMinutes") == null || data.get("maximumIdleMinutes").isNull() ? null : data.get("maximumIdleMinutes").intValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("statusId", getStatusId());
                put("categoryName", getCategoryName());
                put("userId", getUserId());
                put("randomSeed", getRandomSeed());
                put("idleMinutes", getIdleMinutes());
                put("nextRewardsAt", getNextRewardsAt());
                put("maximumIdleMinutes", getMaximumIdleMinutes());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
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
        result = prime * result + ((this.categoryName == null) ? 0 : this.categoryName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.randomSeed == null) ? 0 : this.randomSeed.hashCode());
        result = prime * result + ((this.idleMinutes == null) ? 0 : this.idleMinutes.hashCode());
        result = prime * result + ((this.nextRewardsAt == null) ? 0 : this.nextRewardsAt.hashCode());
        result = prime * result + ((this.maximumIdleMinutes == null) ? 0 : this.maximumIdleMinutes.hashCode());
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
		Status other = (Status) o;
		if (statusId == null) {
			return other.statusId == null;
		} else if (!statusId.equals(other.statusId)) {
			return false;
		}
		if (categoryName == null) {
			return other.categoryName == null;
		} else if (!categoryName.equals(other.categoryName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (randomSeed == null) {
			return other.randomSeed == null;
		} else if (!randomSeed.equals(other.randomSeed)) {
			return false;
		}
		if (idleMinutes == null) {
			return other.idleMinutes == null;
		} else if (!idleMinutes.equals(other.idleMinutes)) {
			return false;
		}
		if (nextRewardsAt == null) {
			return other.nextRewardsAt == null;
		} else if (!nextRewardsAt.equals(other.nextRewardsAt)) {
			return false;
		}
		if (maximumIdleMinutes == null) {
			return other.maximumIdleMinutes == null;
		} else if (!maximumIdleMinutes.equals(other.maximumIdleMinutes)) {
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