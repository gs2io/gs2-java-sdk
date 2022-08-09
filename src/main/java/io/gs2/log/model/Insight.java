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
public class Insight implements IModel, Serializable, Comparable<Insight> {
	private String insightId;
	private String name;
	private String taskId;
	private String host;
	private String password;
	private String status;
	private Long createdAt;
	public String getInsightId() {
		return insightId;
	}
	public void setInsightId(String insightId) {
		this.insightId = insightId;
	}
	public Insight withInsightId(String insightId) {
		this.insightId = insightId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Insight withName(String name) {
		this.name = name;
		return this;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public Insight withTaskId(String taskId) {
		this.taskId = taskId;
		return this;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Insight withHost(String host) {
		this.host = host;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Insight withPassword(String password) {
		this.password = password;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Insight withStatus(String status) {
		this.status = status;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Insight withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static Insight fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Insight()
            .withInsightId(data.get("insightId") == null || data.get("insightId").isNull() ? null : data.get("insightId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withTaskId(data.get("taskId") == null || data.get("taskId").isNull() ? null : data.get("taskId").asText())
            .withHost(data.get("host") == null || data.get("host").isNull() ? null : data.get("host").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText())
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : data.get("status").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("insightId", getInsightId());
                put("name", getName());
                put("taskId", getTaskId());
                put("host", getHost());
                put("password", getPassword());
                put("status", getStatus());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Insight o) {
		return insightId.compareTo(o.insightId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.insightId == null) ? 0 : this.insightId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.taskId == null) ? 0 : this.taskId.hashCode());
        result = prime * result + ((this.host == null) ? 0 : this.host.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		Insight other = (Insight) o;
		if (insightId == null) {
			return other.insightId == null;
		} else if (!insightId.equals(other.insightId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (taskId == null) {
			return other.taskId == null;
		} else if (!taskId.equals(other.taskId)) {
			return false;
		}
		if (host == null) {
			return other.host == null;
		} else if (!host.equals(other.host)) {
			return false;
		}
		if (password == null) {
			return other.password == null;
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		return true;
	}
}