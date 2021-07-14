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
public class RatingModel implements IModel, Serializable, Comparable<RatingModel> {
	private String ratingModelId;
	private String name;
	private String metadata;
	private Integer volatility;

	public String getRatingModelId() {
		return ratingModelId;
	}

	public void setRatingModelId(String ratingModelId) {
		this.ratingModelId = ratingModelId;
	}

	public RatingModel withRatingModelId(String ratingModelId) {
		this.ratingModelId = ratingModelId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RatingModel withName(String name) {
		this.name = name;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public RatingModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public Integer getVolatility() {
		return volatility;
	}

	public void setVolatility(Integer volatility) {
		this.volatility = volatility;
	}

	public RatingModel withVolatility(Integer volatility) {
		this.volatility = volatility;
		return this;
	}

    public static RatingModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RatingModel()
            .withRatingModelId(data.get("ratingModelId") == null || data.get("ratingModelId").isNull() ? null : data.get("ratingModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withVolatility(data.get("volatility") == null || data.get("volatility").isNull() ? null : data.get("volatility").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("ratingModelId", getRatingModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("volatility", getVolatility());
            }}
        );
    }

	@Override
	public int compareTo(RatingModel o) {
		return ratingModelId.compareTo(o.ratingModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.ratingModelId == null) ? 0 : this.ratingModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.volatility == null) ? 0 : this.volatility.hashCode());
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
		RatingModel other = (RatingModel) o;
		if (ratingModelId == null) {
			return other.ratingModelId == null;
		} else if (!ratingModelId.equals(other.ratingModelId)) {
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
		if (volatility == null) {
			return other.volatility == null;
		} else if (!volatility.equals(other.volatility)) {
			return false;
		}
		return true;
	}
}