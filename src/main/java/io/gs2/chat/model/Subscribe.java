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

package io.gs2.chat.model;

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
	private String userId;
	private String roomName;
	private List<NotificationType> notificationTypes;
	private Long createdAt;
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
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Subscribe withRoomName(String roomName) {
		this.roomName = roomName;
		return this;
	}
	public List<NotificationType> getNotificationTypes() {
		return notificationTypes;
	}
	public void setNotificationTypes(List<NotificationType> notificationTypes) {
		this.notificationTypes = notificationTypes;
	}
	public Subscribe withNotificationTypes(List<NotificationType> notificationTypes) {
		this.notificationTypes = notificationTypes;
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
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withRoomName(data.get("roomName") == null || data.get("roomName").isNull() ? null : data.get("roomName").asText())
            .withNotificationTypes(data.get("notificationTypes") == null || data.get("notificationTypes").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("notificationTypes").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return NotificationType.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("subscribeId", getSubscribeId());
                put("userId", getUserId());
                put("roomName", getRoomName());
                put("notificationTypes", getNotificationTypes() == null ? null :
                    getNotificationTypes().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
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
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.roomName == null) ? 0 : this.roomName.hashCode());
        result = prime * result + ((this.notificationTypes == null) ? 0 : this.notificationTypes.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (roomName == null) {
			return other.roomName == null;
		} else if (!roomName.equals(other.roomName)) {
			return false;
		}
		if (notificationTypes == null) {
			return other.notificationTypes == null;
		} else if (!notificationTypes.equals(other.notificationTypes)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
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