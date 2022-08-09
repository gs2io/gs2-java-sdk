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

package io.gs2.log.model;

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
public class ExecuteStampTaskLog implements IModel, Serializable {
	private Long timestamp;
	private String taskId;
	private String service;
	private String method;
	private String userId;
	private String action;
	private String args;
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public ExecuteStampTaskLog withTimestamp(Long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public ExecuteStampTaskLog withTaskId(String taskId) {
		this.taskId = taskId;
		return this;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public ExecuteStampTaskLog withService(String service) {
		this.service = service;
		return this;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public ExecuteStampTaskLog withMethod(String method) {
		this.method = method;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ExecuteStampTaskLog withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public ExecuteStampTaskLog withAction(String action) {
		this.action = action;
		return this;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	public ExecuteStampTaskLog withArgs(String args) {
		this.args = args;
		return this;
	}

    public static ExecuteStampTaskLog fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ExecuteStampTaskLog()
            .withTimestamp(data.get("timestamp") == null || data.get("timestamp").isNull() ? null : data.get("timestamp").longValue())
            .withTaskId(data.get("taskId") == null || data.get("taskId").isNull() ? null : data.get("taskId").asText())
            .withService(data.get("service") == null || data.get("service").isNull() ? null : data.get("service").asText())
            .withMethod(data.get("method") == null || data.get("method").isNull() ? null : data.get("method").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withAction(data.get("action") == null || data.get("action").isNull() ? null : data.get("action").asText())
            .withArgs(data.get("args") == null || data.get("args").isNull() ? null : data.get("args").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("timestamp", getTimestamp());
                put("taskId", getTaskId());
                put("service", getService());
                put("method", getMethod());
                put("userId", getUserId());
                put("action", getAction());
                put("args", getArgs());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        result = prime * result + ((this.taskId == null) ? 0 : this.taskId.hashCode());
        result = prime * result + ((this.service == null) ? 0 : this.service.hashCode());
        result = prime * result + ((this.method == null) ? 0 : this.method.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.action == null) ? 0 : this.action.hashCode());
        result = prime * result + ((this.args == null) ? 0 : this.args.hashCode());
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
		ExecuteStampTaskLog other = (ExecuteStampTaskLog) o;
		if (timestamp == null) {
			return other.timestamp == null;
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		if (taskId == null) {
			return other.taskId == null;
		} else if (!taskId.equals(other.taskId)) {
			return false;
		}
		if (service == null) {
			return other.service == null;
		} else if (!service.equals(other.service)) {
			return false;
		}
		if (method == null) {
			return other.method == null;
		} else if (!method.equals(other.method)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (action == null) {
			return other.action == null;
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (args == null) {
			return other.args == null;
		} else if (!args.equals(other.args)) {
			return false;
		}
		return true;
	}
}