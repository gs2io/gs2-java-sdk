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

package io.gs2.matchmaking.model;

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
public class SeasonGathering implements IModel, Serializable, Comparable<SeasonGathering> {
	private String seasonGatheringId;
	private String seasonName;
	private Long season;
	private Long tier;
	private String name;
	private List<String> participants;
	private Long createdAt;
	private Long revision;
	public String getSeasonGatheringId() {
		return seasonGatheringId;
	}
	public void setSeasonGatheringId(String seasonGatheringId) {
		this.seasonGatheringId = seasonGatheringId;
	}
	public SeasonGathering withSeasonGatheringId(String seasonGatheringId) {
		this.seasonGatheringId = seasonGatheringId;
		return this;
	}
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	public SeasonGathering withSeasonName(String seasonName) {
		this.seasonName = seasonName;
		return this;
	}
	public Long getSeason() {
		return season;
	}
	public void setSeason(Long season) {
		this.season = season;
	}
	public SeasonGathering withSeason(Long season) {
		this.season = season;
		return this;
	}
	public Long getTier() {
		return tier;
	}
	public void setTier(Long tier) {
		this.tier = tier;
	}
	public SeasonGathering withTier(Long tier) {
		this.tier = tier;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SeasonGathering withName(String name) {
		this.name = name;
		return this;
	}
	public List<String> getParticipants() {
		return participants;
	}
	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}
	public SeasonGathering withParticipants(List<String> participants) {
		this.participants = participants;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public SeasonGathering withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public SeasonGathering withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static SeasonGathering fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SeasonGathering()
            .withSeasonGatheringId(data.get("seasonGatheringId") == null || data.get("seasonGatheringId").isNull() ? null : data.get("seasonGatheringId").asText())
            .withSeasonName(data.get("seasonName") == null || data.get("seasonName").isNull() ? null : data.get("seasonName").asText())
            .withSeason(data.get("season") == null || data.get("season").isNull() ? null : data.get("season").longValue())
            .withTier(data.get("tier") == null || data.get("tier").isNull() ? null : data.get("tier").longValue())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withParticipants(data.get("participants") == null || data.get("participants").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("participants").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("seasonGatheringId", getSeasonGatheringId());
                put("seasonName", getSeasonName());
                put("season", getSeason());
                put("tier", getTier());
                put("name", getName());
                put("participants", getParticipants() == null ? null :
                    getParticipants().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(SeasonGathering o) {
		return seasonGatheringId.compareTo(o.seasonGatheringId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.seasonGatheringId == null) ? 0 : this.seasonGatheringId.hashCode());
        result = prime * result + ((this.seasonName == null) ? 0 : this.seasonName.hashCode());
        result = prime * result + ((this.season == null) ? 0 : this.season.hashCode());
        result = prime * result + ((this.tier == null) ? 0 : this.tier.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.participants == null) ? 0 : this.participants.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		SeasonGathering other = (SeasonGathering) o;
		if (seasonGatheringId == null) {
			return other.seasonGatheringId == null;
		} else if (!seasonGatheringId.equals(other.seasonGatheringId)) {
			return false;
		}
		if (seasonName == null) {
			return other.seasonName == null;
		} else if (!seasonName.equals(other.seasonName)) {
			return false;
		}
		if (season == null) {
			return other.season == null;
		} else if (!season.equals(other.season)) {
			return false;
		}
		if (tier == null) {
			return other.tier == null;
		} else if (!tier.equals(other.tier)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (participants == null) {
			return other.participants == null;
		} else if (!participants.equals(other.participants)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
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