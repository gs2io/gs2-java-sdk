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
public class ExperienceModelMaster implements IModel, Serializable, Comparable<ExperienceModelMaster> {
	private String experienceModelId;
	private String name;
	private String description;
	private String metadata;
	private Long defaultExperience;
	private Long defaultRankCap;
	private Long maxRankCap;
	private String rankThresholdName;
	private Long createdAt;
	private Long updatedAt;

	public String getExperienceModelId() {
		return experienceModelId;
	}

	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}

	public ExperienceModelMaster withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExperienceModelMaster withName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ExperienceModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public ExperienceModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public Long getDefaultExperience() {
		return defaultExperience;
	}

	public void setDefaultExperience(Long defaultExperience) {
		this.defaultExperience = defaultExperience;
	}

	public ExperienceModelMaster withDefaultExperience(Long defaultExperience) {
		this.defaultExperience = defaultExperience;
		return this;
	}

	public Long getDefaultRankCap() {
		return defaultRankCap;
	}

	public void setDefaultRankCap(Long defaultRankCap) {
		this.defaultRankCap = defaultRankCap;
	}

	public ExperienceModelMaster withDefaultRankCap(Long defaultRankCap) {
		this.defaultRankCap = defaultRankCap;
		return this;
	}

	public Long getMaxRankCap() {
		return maxRankCap;
	}

	public void setMaxRankCap(Long maxRankCap) {
		this.maxRankCap = maxRankCap;
	}

	public ExperienceModelMaster withMaxRankCap(Long maxRankCap) {
		this.maxRankCap = maxRankCap;
		return this;
	}

	public String getRankThresholdName() {
		return rankThresholdName;
	}

	public void setRankThresholdName(String rankThresholdName) {
		this.rankThresholdName = rankThresholdName;
	}

	public ExperienceModelMaster withRankThresholdName(String rankThresholdName) {
		this.rankThresholdName = rankThresholdName;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public ExperienceModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ExperienceModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static ExperienceModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ExperienceModelMaster()
            .withExperienceModelId(data.get("experienceModelId") == null || data.get("experienceModelId").isNull() ? null : data.get("experienceModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withDefaultExperience(data.get("defaultExperience") == null || data.get("defaultExperience").isNull() ? null : data.get("defaultExperience").longValue())
            .withDefaultRankCap(data.get("defaultRankCap") == null || data.get("defaultRankCap").isNull() ? null : data.get("defaultRankCap").longValue())
            .withMaxRankCap(data.get("maxRankCap") == null || data.get("maxRankCap").isNull() ? null : data.get("maxRankCap").longValue())
            .withRankThresholdName(data.get("rankThresholdName") == null || data.get("rankThresholdName").isNull() ? null : data.get("rankThresholdName").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("experienceModelId", getExperienceModelId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("defaultExperience", getDefaultExperience());
                put("defaultRankCap", getDefaultRankCap());
                put("maxRankCap", getMaxRankCap());
                put("rankThresholdName", getRankThresholdName());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(ExperienceModelMaster o) {
		return experienceModelId.compareTo(o.experienceModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.experienceModelId == null) ? 0 : this.experienceModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.defaultExperience == null) ? 0 : this.defaultExperience.hashCode());
        result = prime * result + ((this.defaultRankCap == null) ? 0 : this.defaultRankCap.hashCode());
        result = prime * result + ((this.maxRankCap == null) ? 0 : this.maxRankCap.hashCode());
        result = prime * result + ((this.rankThresholdName == null) ? 0 : this.rankThresholdName.hashCode());
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
		ExperienceModelMaster other = (ExperienceModelMaster) o;
		if (experienceModelId == null) {
			return other.experienceModelId == null;
		} else if (!experienceModelId.equals(other.experienceModelId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (defaultExperience == null) {
			return other.defaultExperience == null;
		} else if (!defaultExperience.equals(other.defaultExperience)) {
			return false;
		}
		if (defaultRankCap == null) {
			return other.defaultRankCap == null;
		} else if (!defaultRankCap.equals(other.defaultRankCap)) {
			return false;
		}
		if (maxRankCap == null) {
			return other.maxRankCap == null;
		} else if (!maxRankCap.equals(other.maxRankCap)) {
			return false;
		}
		if (rankThresholdName == null) {
			return other.rankThresholdName == null;
		} else if (!rankThresholdName.equals(other.rankThresholdName)) {
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