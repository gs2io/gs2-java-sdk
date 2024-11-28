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
public class Subscribe implements IModel, Serializable, Comparable<Subscribe> {
	private String subscribeId;
	private String rankingName;
	private String userId;
	private List<String> targetUserIds;
	private List<String> fromUserIds;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getSubscribeId() {
		return subscribeId;
	}
	public void setSubscribeId(String subscribeId) {
		this.subscribeId = subscribeId;
	}
	public Subscribe withSubscribeId(String subscribeId) {
		this.subscribeId = subscribeId;
		return this;
	}
	public String getRankingName() {
		return rankingName;
	}
	public void setRankingName(String rankingName) {
		this.rankingName = rankingName;
	}
	public Subscribe withRankingName(String rankingName) {
		this.rankingName = rankingName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Subscribe withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public List<String> getTargetUserIds() {
		return targetUserIds;
	}
	public void setTargetUserIds(List<String> targetUserIds) {
		this.targetUserIds = targetUserIds;
	}
	public Subscribe withTargetUserIds(List<String> targetUserIds) {
		this.targetUserIds = targetUserIds;
		return this;
	}
	public List<String> getFromUserIds() {
		return fromUserIds;
	}
	public void setFromUserIds(List<String> fromUserIds) {
		this.fromUserIds = fromUserIds;
	}
	public Subscribe withFromUserIds(List<String> fromUserIds) {
		this.fromUserIds = fromUserIds;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Subscribe withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Subscribe withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Subscribe withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Subscribe fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Subscribe()
            .withSubscribeId(data.get("subscribeId") == null || data.get("subscribeId").isNull() ? null : data.get("subscribeId").asText())
            .withRankingName(data.get("rankingName") == null || data.get("rankingName").isNull() ? null : data.get("rankingName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withTargetUserIds(data.get("targetUserIds") == null || data.get("targetUserIds").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("targetUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withFromUserIds(data.get("fromUserIds") == null || data.get("fromUserIds").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("fromUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("subscribeId", getSubscribeId());
                put("rankingName", getRankingName());
                put("userId", getUserId());
                put("targetUserIds", getTargetUserIds() == null ? null :
                    getTargetUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("fromUserIds", getFromUserIds() == null ? null :
                    getFromUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Subscribe o) {
		return subscribeId.compareTo(o.subscribeId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.subscribeId == null) ? 0 : this.subscribeId.hashCode());
        result = prime * result + ((this.rankingName == null) ? 0 : this.rankingName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.targetUserIds == null) ? 0 : this.targetUserIds.hashCode());
        result = prime * result + ((this.fromUserIds == null) ? 0 : this.fromUserIds.hashCode());
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
		Subscribe other = (Subscribe) o;
		if (subscribeId == null) {
			return other.subscribeId == null;
		} else if (!subscribeId.equals(other.subscribeId)) {
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
		if (targetUserIds == null) {
			return other.targetUserIds == null;
		} else if (!targetUserIds.equals(other.targetUserIds)) {
			return false;
		}
		if (fromUserIds == null) {
			return other.fromUserIds == null;
		} else if (!fromUserIds.equals(other.fromUserIds)) {
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