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

package io.gs2.jobQueue.model;

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
public class Job implements IModel, Serializable, Comparable<Job> {
	private String jobId;
	private String name;
	private String userId;
	private String scriptId;
	private String args;
	private Integer currentRetryCount;
	private Integer maxTryCount;
	private Long createdAt;
	private Long updatedAt;
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public Job withJobId(String jobId) {
		this.jobId = jobId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Job withName(String name) {
		this.name = name;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Job withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getScriptId() {
		return scriptId;
	}
	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}
	public Job withScriptId(String scriptId) {
		this.scriptId = scriptId;
		return this;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	public Job withArgs(String args) {
		this.args = args;
		return this;
	}
	public Integer getCurrentRetryCount() {
		return currentRetryCount;
	}
	public void setCurrentRetryCount(Integer currentRetryCount) {
		this.currentRetryCount = currentRetryCount;
	}
	public Job withCurrentRetryCount(Integer currentRetryCount) {
		this.currentRetryCount = currentRetryCount;
		return this;
	}
	public Integer getMaxTryCount() {
		return maxTryCount;
	}
	public void setMaxTryCount(Integer maxTryCount) {
		this.maxTryCount = maxTryCount;
	}
	public Job withMaxTryCount(Integer maxTryCount) {
		this.maxTryCount = maxTryCount;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Job withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Job withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Job fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Job()
            .withJobId(data.get("jobId") == null || data.get("jobId").isNull() ? null : data.get("jobId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withScriptId(data.get("scriptId") == null || data.get("scriptId").isNull() ? null : data.get("scriptId").asText())
            .withArgs(data.get("args") == null || data.get("args").isNull() ? null : data.get("args").asText())
            .withCurrentRetryCount(data.get("currentRetryCount") == null || data.get("currentRetryCount").isNull() ? null : data.get("currentRetryCount").intValue())
            .withMaxTryCount(data.get("maxTryCount") == null || data.get("maxTryCount").isNull() ? null : data.get("maxTryCount").intValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("jobId", getJobId());
                put("name", getName());
                put("userId", getUserId());
                put("scriptId", getScriptId());
                put("args", getArgs());
                put("currentRetryCount", getCurrentRetryCount());
                put("maxTryCount", getMaxTryCount());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Job o) {
		return jobId.compareTo(o.jobId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.jobId == null) ? 0 : this.jobId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.scriptId == null) ? 0 : this.scriptId.hashCode());
        result = prime * result + ((this.args == null) ? 0 : this.args.hashCode());
        result = prime * result + ((this.currentRetryCount == null) ? 0 : this.currentRetryCount.hashCode());
        result = prime * result + ((this.maxTryCount == null) ? 0 : this.maxTryCount.hashCode());
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
		Job other = (Job) o;
		if (jobId == null) {
			return other.jobId == null;
		} else if (!jobId.equals(other.jobId)) {
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
		if (scriptId == null) {
			return other.scriptId == null;
		} else if (!scriptId.equals(other.scriptId)) {
			return false;
		}
		if (args == null) {
			return other.args == null;
		} else if (!args.equals(other.args)) {
			return false;
		}
		if (currentRetryCount == null) {
			return other.currentRetryCount == null;
		} else if (!currentRetryCount.equals(other.currentRetryCount)) {
			return false;
		}
		if (maxTryCount == null) {
			return other.maxTryCount == null;
		} else if (!maxTryCount.equals(other.maxTryCount)) {
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