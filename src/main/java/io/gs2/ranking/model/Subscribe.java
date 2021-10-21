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
public class Subscribe implements IModel, Serializable, Comparable<Subscribe> {
	private String subscribeId;
	private String categoryName;
	private String userId;
	private List<String> targetUserIds;
	private List<String> subscribedUserIds;
	private Long createdAt;

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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Subscribe withCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public List<String> getSubscribedUserIds() {
		return subscribedUserIds;
	}

	public void setSubscribedUserIds(List<String> subscribedUserIds) {
		this.subscribedUserIds = subscribedUserIds;
	}

	public Subscribe withSubscribedUserIds(List<String> subscribedUserIds) {
		this.subscribedUserIds = subscribedUserIds;
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

    public static Subscribe fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Subscribe()
            .withSubscribeId(data.get("subscribeId") == null || data.get("subscribeId").isNull() ? null : data.get("subscribeId").asText())
            .withCategoryName(data.get("categoryName") == null || data.get("categoryName").isNull() ? null : data.get("categoryName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withTargetUserIds(data.get("targetUserIds") == null || data.get("targetUserIds").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("targetUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withSubscribedUserIds(data.get("subscribedUserIds") == null || data.get("subscribedUserIds").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("subscribedUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("subscribeId", getSubscribeId());
                put("categoryName", getCategoryName());
                put("userId", getUserId());
                put("targetUserIds", getTargetUserIds() == null ? new ArrayList<String>() :
                    getTargetUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("subscribedUserIds", getSubscribedUserIds() == null ? new ArrayList<String>() :
                    getSubscribedUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
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
        result = prime * result + ((this.categoryName == null) ? 0 : this.categoryName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.targetUserIds == null) ? 0 : this.targetUserIds.hashCode());
        result = prime * result + ((this.subscribedUserIds == null) ? 0 : this.subscribedUserIds.hashCode());
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
		Subscribe other = (Subscribe) o;
		if (subscribeId == null) {
			return other.subscribeId == null;
		} else if (!subscribeId.equals(other.subscribeId)) {
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
		if (targetUserIds == null) {
			return other.targetUserIds == null;
		} else if (!targetUserIds.equals(other.targetUserIds)) {
			return false;
		}
		if (subscribedUserIds == null) {
			return other.subscribedUserIds == null;
		} else if (!subscribedUserIds.equals(other.subscribedUserIds)) {
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