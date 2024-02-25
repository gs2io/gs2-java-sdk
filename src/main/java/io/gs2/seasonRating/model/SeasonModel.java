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

package io.gs2.seasonRating.model;

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
public class SeasonModel implements IModel, Serializable, Comparable<SeasonModel> {
	private String seasonModelId;
	private String name;
	private String metadata;
	private List<TierModel> tiers;
	private String experienceModelId;
	public String getSeasonModelId() {
		return seasonModelId;
	}
	public void setSeasonModelId(String seasonModelId) {
		this.seasonModelId = seasonModelId;
	}
	public SeasonModel withSeasonModelId(String seasonModelId) {
		this.seasonModelId = seasonModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SeasonModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public SeasonModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<TierModel> getTiers() {
		return tiers;
	}
	public void setTiers(List<TierModel> tiers) {
		this.tiers = tiers;
	}
	public SeasonModel withTiers(List<TierModel> tiers) {
		this.tiers = tiers;
		return this;
	}
	public String getExperienceModelId() {
		return experienceModelId;
	}
	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}
	public SeasonModel withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}

    public static SeasonModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SeasonModel()
            .withSeasonModelId(data.get("seasonModelId") == null || data.get("seasonModelId").isNull() ? null : data.get("seasonModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTiers(data.get("tiers") == null || data.get("tiers").isNull() ? new ArrayList<TierModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("tiers").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return TierModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withExperienceModelId(data.get("experienceModelId") == null || data.get("experienceModelId").isNull() ? null : data.get("experienceModelId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("seasonModelId", getSeasonModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("tiers", getTiers() == null ? new ArrayList<TierModel>() :
                    getTiers().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("experienceModelId", getExperienceModelId());
            }}
        );
    }

	@Override
	public int compareTo(SeasonModel o) {
		return seasonModelId.compareTo(o.seasonModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.seasonModelId == null) ? 0 : this.seasonModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.tiers == null) ? 0 : this.tiers.hashCode());
        result = prime * result + ((this.experienceModelId == null) ? 0 : this.experienceModelId.hashCode());
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
		SeasonModel other = (SeasonModel) o;
		if (seasonModelId == null) {
			return other.seasonModelId == null;
		} else if (!seasonModelId.equals(other.seasonModelId)) {
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
		if (tiers == null) {
			return other.tiers == null;
		} else if (!tiers.equals(other.tiers)) {
			return false;
		}
		if (experienceModelId == null) {
			return other.experienceModelId == null;
		} else if (!experienceModelId.equals(other.experienceModelId)) {
			return false;
		}
		return true;
	}
}