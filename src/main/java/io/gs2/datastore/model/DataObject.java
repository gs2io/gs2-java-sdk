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

package io.gs2.datastore.model;

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
public class DataObject implements IModel, Serializable, Comparable<DataObject> {
	private String dataObjectId;
	private String name;
	private String userId;
	private String scope;
	private List<String> allowUserIds;
	private String status;
	private String generation;
	private String previousGeneration;
	private Long createdAt;
	private Long updatedAt;
	public String getDataObjectId() {
		return dataObjectId;
	}
	public void setDataObjectId(String dataObjectId) {
		this.dataObjectId = dataObjectId;
	}
	public DataObject withDataObjectId(String dataObjectId) {
		this.dataObjectId = dataObjectId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DataObject withName(String name) {
		this.name = name;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public DataObject withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public DataObject withScope(String scope) {
		this.scope = scope;
		return this;
	}
	public List<String> getAllowUserIds() {
		return allowUserIds;
	}
	public void setAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
	}
	public DataObject withAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public DataObject withStatus(String status) {
		this.status = status;
		return this;
	}
	public String getGeneration() {
		return generation;
	}
	public void setGeneration(String generation) {
		this.generation = generation;
	}
	public DataObject withGeneration(String generation) {
		this.generation = generation;
		return this;
	}
	public String getPreviousGeneration() {
		return previousGeneration;
	}
	public void setPreviousGeneration(String previousGeneration) {
		this.previousGeneration = previousGeneration;
	}
	public DataObject withPreviousGeneration(String previousGeneration) {
		this.previousGeneration = previousGeneration;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public DataObject withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public DataObject withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static DataObject fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DataObject()
            .withDataObjectId(data.get("dataObjectId") == null || data.get("dataObjectId").isNull() ? null : data.get("dataObjectId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withScope(data.get("scope") == null || data.get("scope").isNull() ? null : data.get("scope").asText())
            .withAllowUserIds(data.get("allowUserIds") == null || data.get("allowUserIds").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("allowUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : data.get("status").asText())
            .withGeneration(data.get("generation") == null || data.get("generation").isNull() ? null : data.get("generation").asText())
            .withPreviousGeneration(data.get("previousGeneration") == null || data.get("previousGeneration").isNull() ? null : data.get("previousGeneration").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("dataObjectId", getDataObjectId());
                put("name", getName());
                put("userId", getUserId());
                put("scope", getScope());
                put("allowUserIds", getAllowUserIds() == null ? new ArrayList<String>() :
                    getAllowUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("status", getStatus());
                put("generation", getGeneration());
                put("previousGeneration", getPreviousGeneration());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(DataObject o) {
		return dataObjectId.compareTo(o.dataObjectId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.dataObjectId == null) ? 0 : this.dataObjectId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.scope == null) ? 0 : this.scope.hashCode());
        result = prime * result + ((this.allowUserIds == null) ? 0 : this.allowUserIds.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.generation == null) ? 0 : this.generation.hashCode());
        result = prime * result + ((this.previousGeneration == null) ? 0 : this.previousGeneration.hashCode());
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
		DataObject other = (DataObject) o;
		if (dataObjectId == null) {
			return other.dataObjectId == null;
		} else if (!dataObjectId.equals(other.dataObjectId)) {
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
		if (scope == null) {
			return other.scope == null;
		} else if (!scope.equals(other.scope)) {
			return false;
		}
		if (allowUserIds == null) {
			return other.allowUserIds == null;
		} else if (!allowUserIds.equals(other.allowUserIds)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (generation == null) {
			return other.generation == null;
		} else if (!generation.equals(other.generation)) {
			return false;
		}
		if (previousGeneration == null) {
			return other.previousGeneration == null;
		} else if (!previousGeneration.equals(other.previousGeneration)) {
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