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

package io.gs2.realtime.model;

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
public class Room implements IModel, Serializable, Comparable<Room> {
	private String roomId;
	private String name;
	private String ipAddress;
	private Integer port;
	private String encryptionKey;
	private List<String> notificationUserIds;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public Room withRoomId(String roomId) {
		this.roomId = roomId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Room withName(String name) {
		this.name = name;
		return this;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Room withIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
		return this;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Room withPort(Integer port) {
		this.port = port;
		return this;
	}
	public String getEncryptionKey() {
		return encryptionKey;
	}
	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
	}
	public Room withEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
		return this;
	}
	public List<String> getNotificationUserIds() {
		return notificationUserIds;
	}
	public void setNotificationUserIds(List<String> notificationUserIds) {
		this.notificationUserIds = notificationUserIds;
	}
	public Room withNotificationUserIds(List<String> notificationUserIds) {
		this.notificationUserIds = notificationUserIds;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Room withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Room withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Room withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Room fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Room()
            .withRoomId(data.get("roomId") == null || data.get("roomId").isNull() ? null : data.get("roomId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withIpAddress(data.get("ipAddress") == null || data.get("ipAddress").isNull() ? null : data.get("ipAddress").asText())
            .withPort(data.get("port") == null || data.get("port").isNull() ? null : data.get("port").intValue())
            .withEncryptionKey(data.get("encryptionKey") == null || data.get("encryptionKey").isNull() ? null : data.get("encryptionKey").asText())
            .withNotificationUserIds(data.get("notificationUserIds") == null || data.get("notificationUserIds").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("notificationUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
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
                put("roomId", getRoomId());
                put("name", getName());
                put("ipAddress", getIpAddress());
                put("port", getPort());
                put("encryptionKey", getEncryptionKey());
                put("notificationUserIds", getNotificationUserIds() == null ? new ArrayList<String>() :
                    getNotificationUserIds().stream().map(item -> {
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
	public int compareTo(Room o) {
		return roomId.compareTo(o.roomId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.roomId == null) ? 0 : this.roomId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.ipAddress == null) ? 0 : this.ipAddress.hashCode());
        result = prime * result + ((this.port == null) ? 0 : this.port.hashCode());
        result = prime * result + ((this.encryptionKey == null) ? 0 : this.encryptionKey.hashCode());
        result = prime * result + ((this.notificationUserIds == null) ? 0 : this.notificationUserIds.hashCode());
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
		Room other = (Room) o;
		if (roomId == null) {
			return other.roomId == null;
		} else if (!roomId.equals(other.roomId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (ipAddress == null) {
			return other.ipAddress == null;
		} else if (!ipAddress.equals(other.ipAddress)) {
			return false;
		}
		if (port == null) {
			return other.port == null;
		} else if (!port.equals(other.port)) {
			return false;
		}
		if (encryptionKey == null) {
			return other.encryptionKey == null;
		} else if (!encryptionKey.equals(other.encryptionKey)) {
			return false;
		}
		if (notificationUserIds == null) {
			return other.notificationUserIds == null;
		} else if (!notificationUserIds.equals(other.notificationUserIds)) {
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