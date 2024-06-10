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
public class GlobalRankingReceivedReward implements IModel, Serializable, Comparable<GlobalRankingReceivedReward> {
	private String globalRankingReceivedRewardId;
	private String rankingName;
	private String userId;
	private Long season;
	private Long receivedAt;
	private Long revision;
	public String getGlobalRankingReceivedRewardId() {
		return globalRankingReceivedRewardId;
	}
	public void setGlobalRankingReceivedRewardId(String globalRankingReceivedRewardId) {
		this.globalRankingReceivedRewardId = globalRankingReceivedRewardId;
	}
	public GlobalRankingReceivedReward withGlobalRankingReceivedRewardId(String globalRankingReceivedRewardId) {
		this.globalRankingReceivedRewardId = globalRankingReceivedRewardId;
		return this;
	}
	public String getRankingName() {
		return rankingName;
	}
	public void setRankingName(String rankingName) {
		this.rankingName = rankingName;
	}
	public GlobalRankingReceivedReward withRankingName(String rankingName) {
		this.rankingName = rankingName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public GlobalRankingReceivedReward withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Long getSeason() {
		return season;
	}
	public void setSeason(Long season) {
		this.season = season;
	}
	public GlobalRankingReceivedReward withSeason(Long season) {
		this.season = season;
		return this;
	}
	public Long getReceivedAt() {
		return receivedAt;
	}
	public void setReceivedAt(Long receivedAt) {
		this.receivedAt = receivedAt;
	}
	public GlobalRankingReceivedReward withReceivedAt(Long receivedAt) {
		this.receivedAt = receivedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public GlobalRankingReceivedReward withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static GlobalRankingReceivedReward fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GlobalRankingReceivedReward()
            .withGlobalRankingReceivedRewardId(data.get("globalRankingReceivedRewardId") == null || data.get("globalRankingReceivedRewardId").isNull() ? null : data.get("globalRankingReceivedRewardId").asText())
            .withRankingName(data.get("rankingName") == null || data.get("rankingName").isNull() ? null : data.get("rankingName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withSeason(data.get("season") == null || data.get("season").isNull() ? null : data.get("season").longValue())
            .withReceivedAt(data.get("receivedAt") == null || data.get("receivedAt").isNull() ? null : data.get("receivedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("globalRankingReceivedRewardId", getGlobalRankingReceivedRewardId());
                put("rankingName", getRankingName());
                put("userId", getUserId());
                put("season", getSeason());
                put("receivedAt", getReceivedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(GlobalRankingReceivedReward o) {
		return globalRankingReceivedRewardId.compareTo(o.globalRankingReceivedRewardId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.globalRankingReceivedRewardId == null) ? 0 : this.globalRankingReceivedRewardId.hashCode());
        result = prime * result + ((this.rankingName == null) ? 0 : this.rankingName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.season == null) ? 0 : this.season.hashCode());
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
		GlobalRankingReceivedReward other = (GlobalRankingReceivedReward) o;
		if (globalRankingReceivedRewardId == null) {
			return other.globalRankingReceivedRewardId == null;
		} else if (!globalRankingReceivedRewardId.equals(other.globalRankingReceivedRewardId)) {
			return false;
		}
		if (rankingName == null) {
			return other.rankingName == null;
		} else if (!rankingName.equals(other.rankingName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (season == null) {
			return other.season == null;
		} else if (!season.equals(other.season)) {
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