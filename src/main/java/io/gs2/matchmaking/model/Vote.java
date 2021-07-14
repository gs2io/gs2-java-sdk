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
public class Vote implements IModel, Serializable, Comparable<Vote> {
	private String voteId;
	private String ratingName;
	private String gatheringName;
	private List<WrittenBallot> writtenBallots;
	private Long createdAt;
	private Long updatedAt;

	public String getVoteId() {
		return voteId;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	public Vote withVoteId(String voteId) {
		this.voteId = voteId;
		return this;
	}

	public String getRatingName() {
		return ratingName;
	}

	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}

	public Vote withRatingName(String ratingName) {
		this.ratingName = ratingName;
		return this;
	}

	public String getGatheringName() {
		return gatheringName;
	}

	public void setGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
	}

	public Vote withGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
		return this;
	}

	public List<WrittenBallot> getWrittenBallots() {
		return writtenBallots;
	}

	public void setWrittenBallots(List<WrittenBallot> writtenBallots) {
		this.writtenBallots = writtenBallots;
	}

	public Vote withWrittenBallots(List<WrittenBallot> writtenBallots) {
		this.writtenBallots = writtenBallots;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Vote withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Vote withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Vote fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Vote()
            .withVoteId(data.get("voteId") == null || data.get("voteId").isNull() ? null : data.get("voteId").asText())
            .withRatingName(data.get("ratingName") == null || data.get("ratingName").isNull() ? null : data.get("ratingName").asText())
            .withGatheringName(data.get("gatheringName") == null || data.get("gatheringName").isNull() ? null : data.get("gatheringName").asText())
            .withWrittenBallots(data.get("writtenBallots") == null || data.get("writtenBallots").isNull() ? new ArrayList<WrittenBallot>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("writtenBallots").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return WrittenBallot.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("voteId", getVoteId());
                put("ratingName", getRatingName());
                put("gatheringName", getGatheringName());
                put("writtenBallots", getWrittenBallots() == null ? new ArrayList<WrittenBallot>() :
                    getWrittenBallots().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Vote o) {
		return voteId.compareTo(o.voteId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.voteId == null) ? 0 : this.voteId.hashCode());
        result = prime * result + ((this.ratingName == null) ? 0 : this.ratingName.hashCode());
        result = prime * result + ((this.gatheringName == null) ? 0 : this.gatheringName.hashCode());
        result = prime * result + ((this.writtenBallots == null) ? 0 : this.writtenBallots.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		Vote other = (Vote) o;
		if (voteId == null) {
			return other.voteId == null;
		} else if (!voteId.equals(other.voteId)) {
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
		if (writtenBallots == null) {
			return other.writtenBallots == null;
		} else if (!writtenBallots.equals(other.writtenBallots)) {
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
		return true;
	}
}