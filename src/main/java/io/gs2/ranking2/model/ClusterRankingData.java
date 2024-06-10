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
public class ClusterRankingData implements IModel, Serializable, Comparable<ClusterRankingData> {
	private String clusterRankingDataId;
	private String rankingName;
	private String clusterName;
	private Long season;
	private String userId;
	private Integer index;
	private Integer rank;
	private Long score;
	private String metadata;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getClusterRankingDataId() {
		return clusterRankingDataId;
	}
	public void setClusterRankingDataId(String clusterRankingDataId) {
		this.clusterRankingDataId = clusterRankingDataId;
	}
	public ClusterRankingData withClusterRankingDataId(String clusterRankingDataId) {
		this.clusterRankingDataId = clusterRankingDataId;
		return this;
	}
	public String getRankingName() {
		return rankingName;
	}
	public void setRankingName(String rankingName) {
		this.rankingName = rankingName;
	}
	public ClusterRankingData withRankingName(String rankingName) {
		this.rankingName = rankingName;
		return this;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public ClusterRankingData withClusterName(String clusterName) {
		this.clusterName = clusterName;
		return this;
	}
	public Long getSeason() {
		return season;
	}
	public void setSeason(Long season) {
		this.season = season;
	}
	public ClusterRankingData withSeason(Long season) {
		this.season = season;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ClusterRankingData withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public ClusterRankingData withIndex(Integer index) {
		this.index = index;
		return this;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public ClusterRankingData withRank(Integer rank) {
		this.rank = rank;
		return this;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public ClusterRankingData withScore(Long score) {
		this.score = score;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public ClusterRankingData withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public ClusterRankingData withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public ClusterRankingData withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public ClusterRankingData withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static ClusterRankingData fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ClusterRankingData()
            .withClusterRankingDataId(data.get("clusterRankingDataId") == null || data.get("clusterRankingDataId").isNull() ? null : data.get("clusterRankingDataId").asText())
            .withRankingName(data.get("rankingName") == null || data.get("rankingName").isNull() ? null : data.get("rankingName").asText())
            .withClusterName(data.get("clusterName") == null || data.get("clusterName").isNull() ? null : data.get("clusterName").asText())
            .withSeason(data.get("season") == null || data.get("season").isNull() ? null : data.get("season").longValue())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withIndex(data.get("index") == null || data.get("index").isNull() ? null : data.get("index").intValue())
            .withRank(data.get("rank") == null || data.get("rank").isNull() ? null : data.get("rank").intValue())
            .withScore(data.get("score") == null || data.get("score").isNull() ? null : data.get("score").longValue())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("clusterRankingDataId", getClusterRankingDataId());
                put("rankingName", getRankingName());
                put("clusterName", getClusterName());
                put("season", getSeason());
                put("userId", getUserId());
                put("index", getIndex());
                put("rank", getRank());
                put("score", getScore());
                put("metadata", getMetadata());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(ClusterRankingData o) {
		return clusterRankingDataId.compareTo(o.clusterRankingDataId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.clusterRankingDataId == null) ? 0 : this.clusterRankingDataId.hashCode());
        result = prime * result + ((this.rankingName == null) ? 0 : this.rankingName.hashCode());
        result = prime * result + ((this.clusterName == null) ? 0 : this.clusterName.hashCode());
        result = prime * result + ((this.season == null) ? 0 : this.season.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.index == null) ? 0 : this.index.hashCode());
        result = prime * result + ((this.rank == null) ? 0 : this.rank.hashCode());
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
		ClusterRankingData other = (ClusterRankingData) o;
		if (clusterRankingDataId == null) {
			return other.clusterRankingDataId == null;
		} else if (!clusterRankingDataId.equals(other.clusterRankingDataId)) {
			return false;
		}
		if (rankingName == null) {
			return other.rankingName == null;
		} else if (!rankingName.equals(other.rankingName)) {
			return false;
		}
		if (clusterName == null) {
			return other.clusterName == null;
		} else if (!clusterName.equals(other.clusterName)) {
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