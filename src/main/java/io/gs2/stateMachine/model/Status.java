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

package io.gs2.stateMachine.model;

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
	private String name;
	private Long stateMachineVersion;
	private List<StackEntry> stacks;
	private List<Variable> variables;
	private String status;
	private String lastError;
	private Integer transitionCount;
	private Long createdAt;
	private Long updatedAt;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Status withName(String name) {
		this.name = name;
		return this;
	}
	public Long getStateMachineVersion() {
		return stateMachineVersion;
	}
	public void setStateMachineVersion(Long stateMachineVersion) {
		this.stateMachineVersion = stateMachineVersion;
	}
	public Status withStateMachineVersion(Long stateMachineVersion) {
		this.stateMachineVersion = stateMachineVersion;
		return this;
	}
	public List<StackEntry> getStacks() {
		return stacks;
	}
	public void setStacks(List<StackEntry> stacks) {
		this.stacks = stacks;
	}
	public Status withStacks(List<StackEntry> stacks) {
		this.stacks = stacks;
		return this;
	}
	public List<Variable> getVariables() {
		return variables;
	}
	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}
	public Status withVariables(List<Variable> variables) {
		this.variables = variables;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Status withStatus(String status) {
		this.status = status;
		return this;
	}
	public String getLastError() {
		return lastError;
	}
	public void setLastError(String lastError) {
		this.lastError = lastError;
	}
	public Status withLastError(String lastError) {
		this.lastError = lastError;
		return this;
	}
	public Integer getTransitionCount() {
		return transitionCount;
	}
	public void setTransitionCount(Integer transitionCount) {
		this.transitionCount = transitionCount;
	}
	public Status withTransitionCount(Integer transitionCount) {
		this.transitionCount = transitionCount;
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

    public static Status fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Status()
            .withStatusId(data.get("statusId") == null || data.get("statusId").isNull() ? null : data.get("statusId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withStateMachineVersion(data.get("stateMachineVersion") == null || data.get("stateMachineVersion").isNull() ? null : data.get("stateMachineVersion").longValue())
            .withStacks(data.get("stacks") == null || data.get("stacks").isNull() ? new ArrayList<StackEntry>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("stacks").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return StackEntry.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withVariables(data.get("variables") == null || data.get("variables").isNull() ? new ArrayList<Variable>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("variables").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Variable.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : data.get("status").asText())
            .withLastError(data.get("lastError") == null || data.get("lastError").isNull() ? null : data.get("lastError").asText())
            .withTransitionCount(data.get("transitionCount") == null || data.get("transitionCount").isNull() ? null : data.get("transitionCount").intValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("statusId", getStatusId());
                put("userId", getUserId());
                put("name", getName());
                put("stateMachineVersion", getStateMachineVersion());
                put("stacks", getStacks() == null ? new ArrayList<StackEntry>() :
                    getStacks().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("variables", getVariables() == null ? new ArrayList<Variable>() :
                    getVariables().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("status", getStatus());
                put("lastError", getLastError());
                put("transitionCount", getTransitionCount());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
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
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.stateMachineVersion == null) ? 0 : this.stateMachineVersion.hashCode());
        result = prime * result + ((this.stacks == null) ? 0 : this.stacks.hashCode());
        result = prime * result + ((this.variables == null) ? 0 : this.variables.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.lastError == null) ? 0 : this.lastError.hashCode());
        result = prime * result + ((this.transitionCount == null) ? 0 : this.transitionCount.hashCode());
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
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (stateMachineVersion == null) {
			return other.stateMachineVersion == null;
		} else if (!stateMachineVersion.equals(other.stateMachineVersion)) {
			return false;
		}
		if (stacks == null) {
			return other.stacks == null;
		} else if (!stacks.equals(other.stacks)) {
			return false;
		}
		if (variables == null) {
			return other.variables == null;
		} else if (!variables.equals(other.variables)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (lastError == null) {
			return other.lastError == null;
		} else if (!lastError.equals(other.lastError)) {
			return false;
		}
		if (transitionCount == null) {
			return other.transitionCount == null;
		} else if (!transitionCount.equals(other.transitionCount)) {
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