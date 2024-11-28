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
public class Received implements IModel, Serializable, Comparable<Received> {
	private String receivedId;
	private String userId;
	private List<String> receivedGlobalMessageNames;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getReceivedId() {
		return receivedId;
	}
	public void setReceivedId(String receivedId) {
		this.receivedId = receivedId;
	}
	public Received withReceivedId(String receivedId) {
		this.receivedId = receivedId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Received withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public List<String> getReceivedGlobalMessageNames() {
		return receivedGlobalMessageNames;
	}
	public void setReceivedGlobalMessageNames(List<String> receivedGlobalMessageNames) {
		this.receivedGlobalMessageNames = receivedGlobalMessageNames;
	}
	public Received withReceivedGlobalMessageNames(List<String> receivedGlobalMessageNames) {
		this.receivedGlobalMessageNames = receivedGlobalMessageNames;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Received withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Received withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Received withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Received fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Received()
            .withReceivedId(data.get("receivedId") == null || data.get("receivedId").isNull() ? null : data.get("receivedId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withReceivedGlobalMessageNames(data.get("receivedGlobalMessageNames") == null || data.get("receivedGlobalMessageNames").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("receivedGlobalMessageNames").elements(), Spliterator.NONNULL), false).map(item -> {
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
                put("receivedId", getReceivedId());
                put("userId", getUserId());
                put("receivedGlobalMessageNames", getReceivedGlobalMessageNames() == null ? null :
                    getReceivedGlobalMessageNames().stream().map(item -> {
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
	public int compareTo(Received o) {
		return receivedId.compareTo(o.receivedId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.receivedId == null) ? 0 : this.receivedId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.receivedGlobalMessageNames == null) ? 0 : this.receivedGlobalMessageNames.hashCode());
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
		Received other = (Received) o;
		if (receivedId == null) {
			return other.receivedId == null;
		} else if (!receivedId.equals(other.receivedId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (receivedGlobalMessageNames == null) {
			return other.receivedGlobalMessageNames == null;
		} else if (!receivedGlobalMessageNames.equals(other.receivedGlobalMessageNames)) {
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