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

package io.gs2.ranking2.model;

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
public class SubscribeRankingData implements IModel, Serializable, Comparable<SubscribeRankingData> {
	private String subscribeRankingDataId;
	private String rankingName;
	private Long season;
	private String userId;
	private Integer index;
	private Integer rank;
	private String scorerUserId;
	private Long score;
	private String metadata;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getSubscribeRankingDataId() {
		return subscribeRankingDataId;
	}
	public void setSubscribeRankingDataId(String subscribeRankingDataId) {
		this.subscribeRankingDataId = subscribeRankingDataId;
	}
	public SubscribeRankingData withSubscribeRankingDataId(String subscribeRankingDataId) {
		this.subscribeRankingDataId = subscribeRankingDataId;
		return this;
	}
	public String getRankingName() {
		return rankingName;
	}
	public void setRankingName(String rankingName) {
		this.rankingName = rankingName;
	}
	public SubscribeRankingData withRankingName(String rankingName) {
		this.rankingName = rankingName;
		return this;
	}
	public Long getSeason() {
		return season;
	}
	public void setSeason(Long season) {
		this.season = season;
	}
	public SubscribeRankingData withSeason(Long season) {
		this.season = season;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SubscribeRankingData withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public SubscribeRankingData withIndex(Integer index) {
		this.index = index;
		return this;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public SubscribeRankingData withRank(Integer rank) {
		this.rank = rank;
		return this;
	}
	public String getScorerUserId() {
		return scorerUserId;
	}
	public void setScorerUserId(String scorerUserId) {
		this.scorerUserId = scorerUserId;
	}
	public SubscribeRankingData withScorerUserId(String scorerUserId) {
		this.scorerUserId = scorerUserId;
		return this;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public SubscribeRankingData withScore(Long score) {
		this.score = score;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public SubscribeRankingData withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public SubscribeRankingData withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public SubscribeRankingData withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public SubscribeRankingData withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static SubscribeRankingData fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SubscribeRankingData()
            .withSubscribeRankingDataId(data.get("subscribeRankingDataId") == null || data.get("subscribeRankingDataId").isNull() ? null : data.get("subscribeRankingDataId").asText())
            .withRankingName(data.get("rankingName") == null || data.get("rankingName").isNull() ? null : data.get("rankingName").asText())
            .withSeason(data.get("season") == null || data.get("season").isNull() ? null : data.get("season").longValue())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withIndex(data.get("index") == null || data.get("index").isNull() ? null : data.get("index").intValue())
            .withRank(data.get("rank") == null || data.get("rank").isNull() ? null : data.get("rank").intValue())
            .withScorerUserId(data.get("scorerUserId") == null || data.get("scorerUserId").isNull() ? null : data.get("scorerUserId").asText())
            .withScore(data.get("score") == null || data.get("score").isNull() ? null : data.get("score").longValue())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("subscribeRankingDataId", getSubscribeRankingDataId());
                put("rankingName", getRankingName());
                put("season", getSeason());
                put("userId", getUserId());
                put("index", getIndex());
                put("rank", getRank());
                put("scorerUserId", getScorerUserId());
                put("score", getScore());
                put("metadata", getMetadata());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(SubscribeRankingData o) {
		return subscribeRankingDataId.compareTo(o.subscribeRankingDataId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.subscribeRankingDataId == null) ? 0 : this.subscribeRankingDataId.hashCode());
        result = prime * result + ((this.rankingName == null) ? 0 : this.rankingName.hashCode());
        result = prime * result + ((this.season == null) ? 0 : this.season.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.index == null) ? 0 : this.index.hashCode());
        result = prime * result + ((this.rank == null) ? 0 : this.rank.hashCode());
        result = prime * result + ((this.scorerUserId == null) ? 0 : this.scorerUserId.hashCode());
        result = prime * result + ((this.score == null) ? 0 : this.score.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
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
		SubscribeRankingData other = (SubscribeRankingData) o;
		if (subscribeRankingDataId == null) {
			return other.subscribeRankingDataId == null;
		} else if (!subscribeRankingDataId.equals(other.subscribeRankingDataId)) {
			return false;
		}
		if (rankingName == null) {
			return other.rankingName == null;
		} else if (!rankingName.equals(other.rankingName)) {
			return false;
		}
		if (season == null) {
			return other.season == null;
		} else if (!season.equals(other.season)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (index == null) {
			return other.index == null;
		} else if (!index.equals(other.index)) {
			return false;
		}
		if (rank == null) {
			return other.rank == null;
		} else if (!rank.equals(other.rank)) {
			return false;
		}
		if (scorerUserId == null) {
			return other.scorerUserId == null;
		} else if (!scorerUserId.equals(other.scorerUserId)) {
			return false;
		}
		if (score == null) {
			return other.score == null;
		} else if (!score.equals(other.score)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
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