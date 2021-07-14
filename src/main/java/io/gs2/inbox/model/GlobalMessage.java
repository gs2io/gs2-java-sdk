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
public class GlobalMessage implements IModel, Serializable, Comparable<GlobalMessage> {
	private String globalMessageId;
	private String name;
	private String metadata;
	private List<AcquireAction> readAcquireActions;
	private TimeSpan expiresTimeSpan;
	private Long expiresAt;

	public String getGlobalMessageId() {
		return globalMessageId;
	}

	public void setGlobalMessageId(String globalMessageId) {
		this.globalMessageId = globalMessageId;
	}

	public GlobalMessage withGlobalMessageId(String globalMessageId) {
		this.globalMessageId = globalMessageId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GlobalMessage withName(String name) {
		this.name = name;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public GlobalMessage withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public List<AcquireAction> getReadAcquireActions() {
		return readAcquireActions;
	}

	public void setReadAcquireActions(List<AcquireAction> readAcquireActions) {
		this.readAcquireActions = readAcquireActions;
	}

	public GlobalMessage withReadAcquireActions(List<AcquireAction> readAcquireActions) {
		this.readAcquireActions = readAcquireActions;
		return this;
	}

	public TimeSpan getExpiresTimeSpan() {
		return expiresTimeSpan;
	}

	public void setExpiresTimeSpan(TimeSpan expiresTimeSpan) {
		this.expiresTimeSpan = expiresTimeSpan;
	}

	public GlobalMessage withExpiresTimeSpan(TimeSpan expiresTimeSpan) {
		this.expiresTimeSpan = expiresTimeSpan;
		return this;
	}

	public Long getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}

	public GlobalMessage withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}

    public static GlobalMessage fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GlobalMessage()
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
            .withExpiresAt(data.get("expiresAt") == null || data.get("expiresAt").isNull() ? null : data.get("expiresAt").longValue());
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
            }}
        );
    }

	@Override
	public int compareTo(GlobalMessage o) {
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
		GlobalMessage other = (GlobalMessage) o;
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
		return true;
	}
}