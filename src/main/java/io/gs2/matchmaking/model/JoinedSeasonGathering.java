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
public class JoinedSeasonGathering implements IModel, Serializable, Comparable<JoinedSeasonGathering> {
	private String joinedSeasonGatheringId;
	private String userId;
	private String seasonName;
	private Long season;
	private Long tier;
	private String seasonGatheringName;
	private Long createdAt;
	public String getJoinedSeasonGatheringId() {
		return joinedSeasonGatheringId;
	}
	public void setJoinedSeasonGatheringId(String joinedSeasonGatheringId) {
		this.joinedSeasonGatheringId = joinedSeasonGatheringId;
	}
	public JoinedSeasonGathering withJoinedSeasonGatheringId(String joinedSeasonGatheringId) {
		this.joinedSeasonGatheringId = joinedSeasonGatheringId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public JoinedSeasonGathering withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	public JoinedSeasonGathering withSeasonName(String seasonName) {
		this.seasonName = seasonName;
		return this;
	}
	public Long getSeason() {
		return season;
	}
	public void setSeason(Long season) {
		this.season = season;
	}
	public JoinedSeasonGathering withSeason(Long season) {
		this.season = season;
		return this;
	}
	public Long getTier() {
		return tier;
	}
	public void setTier(Long tier) {
		this.tier = tier;
	}
	public JoinedSeasonGathering withTier(Long tier) {
		this.tier = tier;
		return this;
	}
	public String getSeasonGatheringName() {
		return seasonGatheringName;
	}
	public void setSeasonGatheringName(String seasonGatheringName) {
		this.seasonGatheringName = seasonGatheringName;
	}
	public JoinedSeasonGathering withSeasonGatheringName(String seasonGatheringName) {
		this.seasonGatheringName = seasonGatheringName;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public JoinedSeasonGathering withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static JoinedSeasonGathering fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new JoinedSeasonGathering()
            .withJoinedSeasonGatheringId(data.get("joinedSeasonGatheringId") == null || data.get("joinedSeasonGatheringId").isNull() ? null : data.get("joinedSeasonGatheringId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withSeasonName(data.get("seasonName") == null || data.get("seasonName").isNull() ? null : data.get("seasonName").asText())
            .withSeason(data.get("season") == null || data.get("season").isNull() ? null : data.get("season").longValue())
            .withTier(data.get("tier") == null || data.get("tier").isNull() ? null : data.get("tier").longValue())
            .withSeasonGatheringName(data.get("seasonGatheringName") == null || data.get("seasonGatheringName").isNull() ? null : data.get("seasonGatheringName").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("joinedSeasonGatheringId", getJoinedSeasonGatheringId());
                put("userId", getUserId());
                put("seasonName", getSeasonName());
                put("season", getSeason());
                put("tier", getTier());
                put("seasonGatheringName", getSeasonGatheringName());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(JoinedSeasonGathering o) {
		return joinedSeasonGatheringId.compareTo(o.joinedSeasonGatheringId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.joinedSeasonGatheringId == null) ? 0 : this.joinedSeasonGatheringId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.seasonName == null) ? 0 : this.seasonName.hashCode());
        result = prime * result + ((this.season == null) ? 0 : this.season.hashCode());
        result = prime * result + ((this.tier == null) ? 0 : this.tier.hashCode());
        result = prime * result + ((this.seasonGatheringName == null) ? 0 : this.seasonGatheringName.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		JoinedSeasonGathering other = (JoinedSeasonGathering) o;
		if (joinedSeasonGatheringId == null) {
			return other.joinedSeasonGatheringId == null;
		} else if (!joinedSeasonGatheringId.equals(other.joinedSeasonGatheringId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
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
		if (seasonGatheringName == null) {
			return other.seasonGatheringName == null;
		} else if (!seasonGatheringName.equals(other.seasonGatheringName)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		return true;
	}
}