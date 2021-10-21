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

package io.gs2.friend.model;

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
public class SendBox implements IModel, Serializable, Comparable<SendBox> {
	private String sendBoxId;
	private String userId;
	private List<String> targetUserIds;
	private Long createdAt;
	private Long updatedAt;

	public String getSendBoxId() {
		return sendBoxId;
	}

	public void setSendBoxId(String sendBoxId) {
		this.sendBoxId = sendBoxId;
	}

	public SendBox withSendBoxId(String sendBoxId) {
		this.sendBoxId = sendBoxId;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public SendBox withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public List<String> getTargetUserIds() {
		return targetUserIds;
	}

	public void setTargetUserIds(List<String> targetUserIds) {
		this.targetUserIds = targetUserIds;
	}

	public SendBox withTargetUserIds(List<String> targetUserIds) {
		this.targetUserIds = targetUserIds;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public SendBox withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public SendBox withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static SendBox fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SendBox()
            .withSendBoxId(data.get("sendBoxId") == null || data.get("sendBoxId").isNull() ? null : data.get("sendBoxId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withTargetUserIds(data.get("targetUserIds") == null || data.get("targetUserIds").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("targetUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("sendBoxId", getSendBoxId());
                put("userId", getUserId());
                put("targetUserIds", getTargetUserIds() == null ? new ArrayList<String>() :
                    getTargetUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(SendBox o) {
		return sendBoxId.compareTo(o.sendBoxId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.sendBoxId == null) ? 0 : this.sendBoxId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.targetUserIds == null) ? 0 : this.targetUserIds.hashCode());
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
		SendBox other = (SendBox) o;
		if (sendBoxId == null) {
			return other.sendBoxId == null;
		} else if (!sendBoxId.equals(other.sendBoxId)) {
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