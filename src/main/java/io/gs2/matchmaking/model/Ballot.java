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
public class Ballot implements IModel, Serializable {
	private String userId;
	private String ratingName;
	private String gatheringName;
	private Integer numberOfPlayer;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Ballot withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getRatingName() {
		return ratingName;
	}

	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}

	public Ballot withRatingName(String ratingName) {
		this.ratingName = ratingName;
		return this;
	}

	public String getGatheringName() {
		return gatheringName;
	}

	public void setGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
	}

	public Ballot withGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
		return this;
	}

	public Integer getNumberOfPlayer() {
		return numberOfPlayer;
	}

	public void setNumberOfPlayer(Integer numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
	}

	public Ballot withNumberOfPlayer(Integer numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
		return this;
	}

    public static Ballot fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Ballot()
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withRatingName(data.get("ratingName") == null || data.get("ratingName").isNull() ? null : data.get("ratingName").asText())
            .withGatheringName(data.get("gatheringName") == null || data.get("gatheringName").isNull() ? null : data.get("gatheringName").asText())
            .withNumberOfPlayer(data.get("numberOfPlayer") == null || data.get("numberOfPlayer").isNull() ? null : data.get("numberOfPlayer").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("userId", getUserId());
                put("ratingName", getRatingName());
                put("gatheringName", getGatheringName());
                put("numberOfPlayer", getNumberOfPlayer());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.ratingName == null) ? 0 : this.ratingName.hashCode());
        result = prime * result + ((this.gatheringName == null) ? 0 : this.gatheringName.hashCode());
        result = prime * result + ((this.numberOfPlayer == null) ? 0 : this.numberOfPlayer.hashCode());
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
		Ballot other = (Ballot) o;
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (ratingName == null) {
			return other.ratingName == null;
		} else if (!ratingName.equals(other.ratingName)) {
			return false;
		}
		if (gatheringName == null) {
			return other.gatheringName == null;
		} else if (!gatheringName.equals(other.gatheringName)) {
			return false;
		}
		if (numberOfPlayer == null) {
			return other.numberOfPlayer == null;
		} else if (!numberOfPlayer.equals(other.numberOfPlayer)) {
			return false;
		}
		return true;
	}
}