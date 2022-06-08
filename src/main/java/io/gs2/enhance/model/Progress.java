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

package io.gs2.enhance.model;

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
public class Progress implements IModel, Serializable, Comparable<Progress> {
	private String progressId;
	private String userId;
	private String rateName;
	private String name;
	private String propertyId;
	private Integer experienceValue;
	private Float rate;
	private Long createdAt;
	private Long updatedAt;
	public String getProgressId() {
		return progressId;
	}
	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}
	public Progress withProgressId(String progressId) {
		this.progressId = progressId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Progress withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getRateName() {
		return rateName;
	}
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	public Progress withRateName(String rateName) {
		this.rateName = rateName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Progress withName(String name) {
		this.name = name;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public Progress withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public Integer getExperienceValue() {
		return experienceValue;
	}
	public void setExperienceValue(Integer experienceValue) {
		this.experienceValue = experienceValue;
	}
	public Progress withExperienceValue(Integer experienceValue) {
		this.experienceValue = experienceValue;
		return this;
	}
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
	public Progress withRate(Float rate) {
		this.rate = rate;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Progress withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Progress withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Progress fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Progress()
            .withProgressId(data.get("progressId") == null || data.get("progressId").isNull() ? null : data.get("progressId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withRateName(data.get("rateName") == null || data.get("rateName").isNull() ? null : data.get("rateName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withExperienceValue(data.get("experienceValue") == null || data.get("experienceValue").isNull() ? null : data.get("experienceValue").intValue())
            .withRate(data.get("rate") == null || data.get("rate").isNull() ? null : data.get("rate").floatValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("progressId", getProgressId());
                put("userId", getUserId());
                put("rateName", getRateName());
                put("name", getName());
                put("propertyId", getPropertyId());
                put("experienceValue", getExperienceValue());
                put("rate", getRate());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Progress o) {
		return progressId.compareTo(o.progressId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.progressId == null) ? 0 : this.progressId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.rateName == null) ? 0 : this.rateName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.propertyId == null) ? 0 : this.propertyId.hashCode());
        result = prime * result + ((this.experienceValue == null) ? 0 : this.experienceValue.hashCode());
        result = prime * result + ((this.rate == null) ? 0 : this.rate.hashCode());
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
		Progress other = (Progress) o;
		if (progressId == null) {
			return other.progressId == null;
		} else if (!progressId.equals(other.progressId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (rateName == null) {
			return other.rateName == null;
		} else if (!rateName.equals(other.rateName)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
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
		if (rate == null) {
			return other.rate == null;
		} else if (!rate.equals(other.rate)) {
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