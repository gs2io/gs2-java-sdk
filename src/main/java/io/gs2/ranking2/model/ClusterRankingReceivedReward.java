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
public class ClusterRankingReceivedReward implements IModel, Serializable, Comparable<ClusterRankingReceivedReward> {
	private String clusterRankingReceivedRewardId;
	private String rankingName;
	private String clusterName;
	private Long season;
	private String userId;
	private Long receivedAt;
	private Long revision;
	public String getClusterRankingReceivedRewardId() {
		return clusterRankingReceivedRewardId;
	}
	public void setClusterRankingReceivedRewardId(String clusterRankingReceivedRewardId) {
		this.clusterRankingReceivedRewardId = clusterRankingReceivedRewardId;
	}
	public ClusterRankingReceivedReward withClusterRankingReceivedRewardId(String clusterRankingReceivedRewardId) {
		this.clusterRankingReceivedRewardId = clusterRankingReceivedRewardId;
		return this;
	}
	public String getRankingName() {
		return rankingName;
	}
	public void setRankingName(String rankingName) {
		this.rankingName = rankingName;
	}
	public ClusterRankingReceivedReward withRankingName(String rankingName) {
		this.rankingName = rankingName;
		return this;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public ClusterRankingReceivedReward withClusterName(String clusterName) {
		this.clusterName = clusterName;
		return this;
	}
	public Long getSeason() {
		return season;
	}
	public void setSeason(Long season) {
		this.season = season;
	}
	public ClusterRankingReceivedReward withSeason(Long season) {
		this.season = season;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ClusterRankingReceivedReward withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Long getReceivedAt() {
		return receivedAt;
	}
	public void setReceivedAt(Long receivedAt) {
		this.receivedAt = receivedAt;
	}
	public ClusterRankingReceivedReward withReceivedAt(Long receivedAt) {
		this.receivedAt = receivedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public ClusterRankingReceivedReward withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static ClusterRankingReceivedReward fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ClusterRankingReceivedReward()
            .withClusterRankingReceivedRewardId(data.get("clusterRankingReceivedRewardId") == null || data.get("clusterRankingReceivedRewardId").isNull() ? null : data.get("clusterRankingReceivedRewardId").asText())
            .withRankingName(data.get("rankingName") == null || data.get("rankingName").isNull() ? null : data.get("rankingName").asText())
            .withClusterName(data.get("clusterName") == null || data.get("clusterName").isNull() ? null : data.get("clusterName").asText())
            .withSeason(data.get("season") == null || data.get("season").isNull() ? null : data.get("season").longValue())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withReceivedAt(data.get("receivedAt") == null || data.get("receivedAt").isNull() ? null : data.get("receivedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("clusterRankingReceivedRewardId", getClusterRankingReceivedRewardId());
                put("rankingName", getRankingName());
                put("clusterName", getClusterName());
                put("season", getSeason());
                put("userId", getUserId());
                put("receivedAt", getReceivedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(ClusterRankingReceivedReward o) {
		return clusterRankingReceivedRewardId.compareTo(o.clusterRankingReceivedRewardId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.clusterRankingReceivedRewardId == null) ? 0 : this.clusterRankingReceivedRewardId.hashCode());
        result = prime * result + ((this.rankingName == null) ? 0 : this.rankingName.hashCode());
        result = prime * result + ((this.clusterName == null) ? 0 : this.clusterName.hashCode());
        result = prime * result + ((this.season == null) ? 0 : this.season.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.receivedAt == null) ? 0 : this.receivedAt.hashCode());
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
		ClusterRankingReceivedReward other = (ClusterRankingReceivedReward) o;
		if (clusterRankingReceivedRewardId == null) {
			return other.clusterRankingReceivedRewardId == null;
		} else if (!clusterRankingReceivedRewardId.equals(other.clusterRankingReceivedRewardId)) {
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
		if (receivedAt == null) {
			return other.receivedAt == null;
		} else if (!receivedAt.equals(other.receivedAt)) {
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