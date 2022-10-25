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
public class Ranking implements IModel, Serializable {
	private Long rank;
	private Long index;
	private String categoryName;
	private String userId;
	private Long score;
	private String metadata;
	private Long createdAt;
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public Ranking withRank(Long rank) {
		this.rank = rank;
		return this;
	}
	public Long getIndex() {
		return index;
	}
	public void setIndex(Long index) {
		this.index = index;
	}
	public Ranking withIndex(Long index) {
		this.index = index;
		return this;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Ranking withCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Ranking withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Ranking withScore(Long score) {
		this.score = score;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public Ranking withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Ranking withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static Ranking fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Ranking()
            .withRank(data.get("rank") == null || data.get("rank").isNull() ? null : data.get("rank").longValue())
            .withIndex(data.get("index") == null || data.get("index").isNull() ? null : data.get("index").longValue())
            .withCategoryName(data.get("categoryName") == null || data.get("categoryName").isNull() ? null : data.get("categoryName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withScore(data.get("score") == null || data.get("score").isNull() ? null : data.get("score").longValue())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("rank", getRank());
                put("index", getIndex());
                put("categoryName", getCategoryName());
                put("userId", getUserId());
                put("score", getScore());
                put("metadata", getMetadata());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.rank == null) ? 0 : this.rank.hashCode());
        result = prime * result + ((this.index == null) ? 0 : this.index.hashCode());
        result = prime * result + ((this.categoryName == null) ? 0 : this.categoryName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
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
		Ranking other = (Ranking) o;
		if (rank == null) {
			return other.rank == null;
		} else if (!rank.equals(other.rank)) {
			return false;
		}
		if (index == null) {
			return other.index == null;
		} else if (!index.equals(other.index)) {
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