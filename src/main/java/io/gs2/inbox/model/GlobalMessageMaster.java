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
public class GlobalMessageMaster implements IModel, Serializable, Comparable<GlobalMessageMaster> {
	private String globalMessageId;
	private String name;
	private String metadata;
	private List<AcquireAction> readAcquireActions;
	private TimeSpan expiresTimeSpan;
	private Long expiresAt;
	private String messageReceptionPeriodEventId;
	private Long createdAt;
	private Long revision;
	public String getGlobalMessageId() {
		return globalMessageId;
	}
	public void setGlobalMessageId(String globalMessageId) {
		this.globalMessageId = globalMessageId;
	}
	public GlobalMessageMaster withGlobalMessageId(String globalMessageId) {
		this.globalMessageId = globalMessageId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GlobalMessageMaster withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public GlobalMessageMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<AcquireAction> getReadAcquireActions() {
		return readAcquireActions;
	}
	public void setReadAcquireActions(List<AcquireAction> readAcquireActions) {
		this.readAcquireActions = readAcquireActions;
	}
	public GlobalMessageMaster withReadAcquireActions(List<AcquireAction> readAcquireActions) {
		this.readAcquireActions = readAcquireActions;
		return this;
	}
	public TimeSpan getExpiresTimeSpan() {
		return expiresTimeSpan;
	}
	public void setExpiresTimeSpan(TimeSpan expiresTimeSpan) {
		this.expiresTimeSpan = expiresTimeSpan;
	}
	public GlobalMessageMaster withExpiresTimeSpan(TimeSpan expiresTimeSpan) {
		this.expiresTimeSpan = expiresTimeSpan;
		return this;
	}
    @Deprecated
	public Long getExpiresAt() {
		return expiresAt;
	}
    @Deprecated
	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}
    @Deprecated
	public GlobalMessageMaster withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}
	public String getMessageReceptionPeriodEventId() {
		return messageReceptionPeriodEventId;
	}
	public void setMessageReceptionPeriodEventId(String messageReceptionPeriodEventId) {
		this.messageReceptionPeriodEventId = messageReceptionPeriodEventId;
	}
	public GlobalMessageMaster withMessageReceptionPeriodEventId(String messageReceptionPeriodEventId) {
		this.messageReceptionPeriodEventId = messageReceptionPeriodEventId;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public GlobalMessageMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public GlobalMessageMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static GlobalMessageMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GlobalMessageMaster()
            .withGlobalMessageId(data.get("globalMessageId") == null || data.get("globalMessageId").isNull() ? null : data.get("globalMessageId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withReadAcquireActions(data.get("readAcquireActions") == null || data.get("readAcquireActions").isNull() ? new ArrayList<AcquireAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("readAcquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withExpiresTimeSpan(data.get("expiresTimeSpan") == null || data.get("expiresTimeSpan").isNull() ? null : TimeSpan.fromJson(data.get("expiresTimeSpan")))
            .withExpiresAt(data.get("expiresAt") == null || data.get("expiresAt").isNull() ? null : data.get("expiresAt").longValue())
            .withMessageReceptionPeriodEventId(data.get("messageReceptionPeriodEventId") == null || data.get("messageReceptionPeriodEventId").isNull() ? null : data.get("messageReceptionPeriodEventId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("globalMessageId", getGlobalMessageId());
                put("name", getName());
                put("metadata", getMetadata());
                put("readAcquireActions", getReadAcquireActions() == null ? new ArrayList<AcquireAction>() :
                    getReadAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("expiresTimeSpan", getExpiresTimeSpan() != null ? getExpiresTimeSpan().toJson() : null);
                put("expiresAt", getExpiresAt());
                put("messageReceptionPeriodEventId", getMessageReceptionPeriodEventId());
                put("createdAt", getCreatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(GlobalMessageMaster o) {
		return globalMessageId.compareTo(o.globalMessageId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.globalMessageId == null) ? 0 : this.globalMessageId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.readAcquireActions == null) ? 0 : this.readAcquireActions.hashCode());
        result = prime * result + ((this.expiresTimeSpan == null) ? 0 : this.expiresTimeSpan.hashCode());
        result = prime * result + ((this.expiresAt == null) ? 0 : this.expiresAt.hashCode());
        result = prime * result + ((this.messageReceptionPeriodEventId == null) ? 0 : this.messageReceptionPeriodEventId.hashCode());
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
		GlobalMessageMaster other = (GlobalMessageMaster) o;
		if (globalMessageId == null) {
			return other.globalMessageId == null;
		} else if (!globalMessageId.equals(other.globalMessageId)) {
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
		if (readAcquireActions == null) {
			return other.readAcquireActions == null;
		} else if (!readAcquireActions.equals(other.readAcquireActions)) {
			return false;
		}
		if (expiresTimeSpan == null) {
			return other.expiresTimeSpan == null;
		} else if (!expiresTimeSpan.equals(other.expiresTimeSpan)) {
			return false;
		}
		if (expiresAt == null) {
			return other.expiresAt == null;
		} else if (!expiresAt.equals(other.expiresAt)) {
			return false;
		}
		if (messageReceptionPeriodEventId == null) {
			return other.messageReceptionPeriodEventId == null;
		} else if (!messageReceptionPeriodEventId.equals(other.messageReceptionPeriodEventId)) {
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