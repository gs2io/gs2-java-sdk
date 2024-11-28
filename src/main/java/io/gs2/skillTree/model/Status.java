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

package io.gs2.skillTree.model;

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
public class Status implements IModel, Serializable, Comparable<Status> {
	private String statusId;
	private String userId;
	private String propertyId;
	private List<String> releasedNodeNames;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public Status withStatusId(String statusId) {
		this.statusId = statusId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Status withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public Status withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public List<String> getReleasedNodeNames() {
		return releasedNodeNames;
	}
	public void setReleasedNodeNames(List<String> releasedNodeNames) {
		this.releasedNodeNames = releasedNodeNames;
	}
	public Status withReleasedNodeNames(List<String> releasedNodeNames) {
		this.releasedNodeNames = releasedNodeNames;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Status withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Status withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Status withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Status fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Status()
            .withStatusId(data.get("statusId") == null || data.get("statusId").isNull() ? null : data.get("statusId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withReleasedNodeNames(data.get("releasedNodeNames") == null || data.get("releasedNodeNames").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("releasedNodeNames").elements(), Spliterator.NONNULL), false).map(item -> {
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
                put("statusId", getStatusId());
                put("userId", getUserId());
                put("propertyId", getPropertyId());
                put("releasedNodeNames", getReleasedNodeNames() == null ? null :
                    getReleasedNodeNames().stream().map(item -> {
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
	public int compareTo(Status o) {
		return statusId.compareTo(o.statusId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.statusId == null) ? 0 : this.statusId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.propertyId == null) ? 0 : this.propertyId.hashCode());
        result = prime * result + ((this.releasedNodeNames == null) ? 0 : this.releasedNodeNames.hashCode());
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
		Status other = (Status) o;
		if (statusId == null) {
			return other.statusId == null;
		} else if (!statusId.equals(other.statusId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (propertyId == null) {
			return other.propertyId == null;
		} else if (!propertyId.equals(other.propertyId)) {
			return false;
		}
		if (releasedNodeNames == null) {
			return other.releasedNodeNames == null;
		} else if (!releasedNodeNames.equals(other.releasedNodeNames)) {
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