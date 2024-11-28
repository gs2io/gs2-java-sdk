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

package io.gs2.inbox.model;

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
public class Message implements IModel, Serializable, Comparable<Message> {
	private String messageId;
	private String name;
	private String userId;
	private String metadata;
	private Boolean isRead;
	private List<AcquireAction> readAcquireActions;
	private Long receivedAt;
	private Long readAt;
	private Long expiresAt;
	private Long revision;
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public Message withMessageId(String messageId) {
		this.messageId = messageId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Message withName(String name) {
		this.name = name;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Message withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public Message withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	public Message withIsRead(Boolean isRead) {
		this.isRead = isRead;
		return this;
	}
	public List<AcquireAction> getReadAcquireActions() {
		return readAcquireActions;
	}
	public void setReadAcquireActions(List<AcquireAction> readAcquireActions) {
		this.readAcquireActions = readAcquireActions;
	}
	public Message withReadAcquireActions(List<AcquireAction> readAcquireActions) {
		this.readAcquireActions = readAcquireActions;
		return this;
	}
	public Long getReceivedAt() {
		return receivedAt;
	}
	public void setReceivedAt(Long receivedAt) {
		this.receivedAt = receivedAt;
	}
	public Message withReceivedAt(Long receivedAt) {
		this.receivedAt = receivedAt;
		return this;
	}
	public Long getReadAt() {
		return readAt;
	}
	public void setReadAt(Long readAt) {
		this.readAt = readAt;
	}
	public Message withReadAt(Long readAt) {
		this.readAt = readAt;
		return this;
	}
	public Long getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}
	public Message withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Message withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Message fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Message()
            .withMessageId(data.get("messageId") == null || data.get("messageId").isNull() ? null : data.get("messageId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withIsRead(data.get("isRead") == null || data.get("isRead").isNull() ? null : data.get("isRead").booleanValue())
            .withReadAcquireActions(data.get("readAcquireActions") == null || data.get("readAcquireActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("readAcquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withReceivedAt(data.get("receivedAt") == null || data.get("receivedAt").isNull() ? null : data.get("receivedAt").longValue())
            .withReadAt(data.get("readAt") == null || data.get("readAt").isNull() ? null : data.get("readAt").longValue())
            .withExpiresAt(data.get("expiresAt") == null || data.get("expiresAt").isNull() ? null : data.get("expiresAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("messageId", getMessageId());
                put("name", getName());
                put("userId", getUserId());
                put("metadata", getMetadata());
                put("isRead", getIsRead());
                put("readAcquireActions", getReadAcquireActions() == null ? null :
                    getReadAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("receivedAt", getReceivedAt());
                put("readAt", getReadAt());
                put("expiresAt", getExpiresAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Message o) {
		return messageId.compareTo(o.messageId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.messageId == null) ? 0 : this.messageId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.isRead == null) ? 0 : this.isRead.hashCode());
        result = prime * result + ((this.readAcquireActions == null) ? 0 : this.readAcquireActions.hashCode());
        result = prime * result + ((this.receivedAt == null) ? 0 : this.receivedAt.hashCode());
        result = prime * result + ((this.readAt == null) ? 0 : this.readAt.hashCode());
        result = prime * result + ((this.expiresAt == null) ? 0 : this.expiresAt.hashCode());
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
		Message other = (Message) o;
		if (messageId == null) {
			return other.messageId == null;
		} else if (!messageId.equals(other.messageId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (isRead == null) {
			return other.isRead == null;
		} else if (!isRead.equals(other.isRead)) {
			return false;
		}
		if (readAcquireActions == null) {
			return other.readAcquireActions == null;
		} else if (!readAcquireActions.equals(other.readAcquireActions)) {
			return false;
		}
		if (receivedAt == null) {
			return other.receivedAt == null;
		} else if (!receivedAt.equals(other.receivedAt)) {
			return false;
		}
		if (readAt == null) {
			return other.readAt == null;
		} else if (!readAt.equals(other.readAt)) {
			return false;
		}
		if (expiresAt == null) {
			return other.expiresAt == null;
		} else if (!expiresAt.equals(other.expiresAt)) {
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