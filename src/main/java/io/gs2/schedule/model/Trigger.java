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

package io.gs2.schedule.model;

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
public class Trigger implements IModel, Serializable, Comparable<Trigger> {
	private String triggerId;
	private String name;
	private String userId;
	private Long triggeredAt;
	private Long expiresAt;
	private Long createdAt;
	private Long revision;
	public String getTriggerId() {
		return triggerId;
	}
	public void setTriggerId(String triggerId) {
		this.triggerId = triggerId;
	}
	public Trigger withTriggerId(String triggerId) {
		this.triggerId = triggerId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Trigger withName(String name) {
		this.name = name;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Trigger withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Long getTriggeredAt() {
		return triggeredAt;
	}
	public void setTriggeredAt(Long triggeredAt) {
		this.triggeredAt = triggeredAt;
	}
	public Trigger withTriggeredAt(Long triggeredAt) {
		this.triggeredAt = triggeredAt;
		return this;
	}
	public Long getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}
	public Trigger withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Trigger withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Trigger withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Trigger fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Trigger()
            .withTriggerId(data.get("triggerId") == null || data.get("triggerId").isNull() ? null : data.get("triggerId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withTriggeredAt(data.get("triggeredAt") == null || data.get("triggeredAt").isNull() ? null : data.get("triggeredAt").longValue())
            .withExpiresAt(data.get("expiresAt") == null || data.get("expiresAt").isNull() ? null : data.get("expiresAt").longValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("triggerId", getTriggerId());
                put("name", getName());
                put("userId", getUserId());
                put("triggeredAt", getTriggeredAt());
                put("expiresAt", getExpiresAt());
                put("createdAt", getCreatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Trigger o) {
		return triggerId.compareTo(o.triggerId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.triggerId == null) ? 0 : this.triggerId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.triggeredAt == null) ? 0 : this.triggeredAt.hashCode());
        result = prime * result + ((this.expiresAt == null) ? 0 : this.expiresAt.hashCode());
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
		Trigger other = (Trigger) o;
		if (triggerId == null) {
			return other.triggerId == null;
		} else if (!triggerId.equals(other.triggerId)) {
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
		if (triggeredAt == null) {
			return other.triggeredAt == null;
		} else if (!triggeredAt.equals(other.triggeredAt)) {
			return false;
		}
		if (expiresAt == null) {
			return other.expiresAt == null;
		} else if (!expiresAt.equals(other.expiresAt)) {
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