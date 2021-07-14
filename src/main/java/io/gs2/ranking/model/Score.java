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

package io.gs2.ranking.model;

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
public class Score implements IModel, Serializable, Comparable<Score> {
	private String scoreId;
	private String categoryName;
	private String userId;
	private String uniqueId;
	private String scorerUserId;
	private Long score;
	private String metadata;
	private Long createdAt;

	public String getScoreId() {
		return scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}

	public Score withScoreId(String scoreId) {
		this.scoreId = scoreId;
		return this;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Score withCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Score withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Score withUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
		return this;
	}

	public String getScorerUserId() {
		return scorerUserId;
	}

	public void setScorerUserId(String scorerUserId) {
		this.scorerUserId = scorerUserId;
	}

	public Score withScorerUserId(String scorerUserId) {
		this.scorerUserId = scorerUserId;
		return this;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Score withScore(Long score) {
		this.score = score;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public Score withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Score withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static Score fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Score()
            .withScoreId(data.get("scoreId") == null || data.get("scoreId").isNull() ? null : data.get("scoreId").asText())
            .withCategoryName(data.get("categoryName") == null || data.get("categoryName").isNull() ? null : data.get("categoryName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withUniqueId(data.get("uniqueId") == null || data.get("uniqueId").isNull() ? null : data.get("uniqueId").asText())
            .withScorerUserId(data.get("scorerUserId") == null || data.get("scorerUserId").isNull() ? null : data.get("scorerUserId").asText())
            .withScore(data.get("score") == null || data.get("score").isNull() ? null : data.get("score").longValue())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("scoreId", getScoreId());
                put("categoryName", getCategoryName());
                put("userId", getUserId());
                put("uniqueId", getUniqueId());
                put("scorerUserId", getScorerUserId());
                put("score", getScore());
                put("metadata", getMetadata());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Score o) {
		return scoreId.compareTo(o.scoreId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.scoreId == null) ? 0 : this.scoreId.hashCode());
        result = prime * result + ((this.categoryName == null) ? 0 : this.categoryName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.uniqueId == null) ? 0 : this.uniqueId.hashCode());
        result = prime * result + ((this.scorerUserId == null) ? 0 : this.scorerUserId.hashCode());
        result = prime * result + ((this.score == null) ? 0 : this.score.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
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
		Score other = (Score) o;
		if (scoreId == null) {
			return other.scoreId == null;
		} else if (!scoreId.equals(other.scoreId)) {
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
		if (uniqueId == null) {
			return other.uniqueId == null;
		} else if (!uniqueId.equals(other.uniqueId)) {
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
		return true;
	}
}