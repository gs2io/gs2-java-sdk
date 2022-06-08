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
public class DeadLetterJob implements IModel, Serializable, Comparable<DeadLetterJob> {
	private String deadLetterJobId;
	private String name;
	private String userId;
	private String scriptId;
	private String args;
	private List<JobResultBody> result;
	private Long createdAt;
	private Long updatedAt;
	public String getDeadLetterJobId() {
		return deadLetterJobId;
	}
	public void setDeadLetterJobId(String deadLetterJobId) {
		this.deadLetterJobId = deadLetterJobId;
	}
	public DeadLetterJob withDeadLetterJobId(String deadLetterJobId) {
		this.deadLetterJobId = deadLetterJobId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DeadLetterJob withName(String name) {
		this.name = name;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public DeadLetterJob withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getScriptId() {
		return scriptId;
	}
	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}
	public DeadLetterJob withScriptId(String scriptId) {
		this.scriptId = scriptId;
		return this;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	public DeadLetterJob withArgs(String args) {
		this.args = args;
		return this;
	}
	public List<JobResultBody> getResult() {
		return result;
	}
	public void setResult(List<JobResultBody> result) {
		this.result = result;
	}
	public DeadLetterJob withResult(List<JobResultBody> result) {
		this.result = result;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public DeadLetterJob withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public DeadLetterJob withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static DeadLetterJob fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DeadLetterJob()
            .withDeadLetterJobId(data.get("deadLetterJobId") == null || data.get("deadLetterJobId").isNull() ? null : data.get("deadLetterJobId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withScriptId(data.get("scriptId") == null || data.get("scriptId").isNull() ? null : data.get("scriptId").asText())
            .withArgs(data.get("args") == null || data.get("args").isNull() ? null : data.get("args").asText())
            .withResult(data.get("result") == null || data.get("result").isNull() ? new ArrayList<JobResultBody>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("result").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return JobResultBody.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("deadLetterJobId", getDeadLetterJobId());
                put("name", getName());
                put("userId", getUserId());
                put("scriptId", getScriptId());
                put("args", getArgs());
                put("result", getResult() == null ? new ArrayList<JobResultBody>() :
                    getResult().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(DeadLetterJob o) {
		return deadLetterJobId.compareTo(o.deadLetterJobId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.deadLetterJobId == null) ? 0 : this.deadLetterJobId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.scriptId == null) ? 0 : this.scriptId.hashCode());
        result = prime * result + ((this.args == null) ? 0 : this.args.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
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
		DeadLetterJob other = (DeadLetterJob) o;
		if (deadLetterJobId == null) {
			return other.deadLetterJobId == null;
		} else if (!deadLetterJobId.equals(other.deadLetterJobId)) {
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
		if (result == null) {
			return other.result == null;
		} else if (!result.equals(other.result)) {
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