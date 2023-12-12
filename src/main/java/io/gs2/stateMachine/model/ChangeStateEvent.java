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
public class ChangeStateEvent implements IModel, Serializable {
	private String taskName;
	private String hash;
	private Long timestamp;
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public ChangeStateEvent withTaskName(String taskName) {
		this.taskName = taskName;
		return this;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public ChangeStateEvent withHash(String hash) {
		this.hash = hash;
		return this;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public ChangeStateEvent withTimestamp(Long timestamp) {
		this.timestamp = timestamp;
		return this;
	}

    public static ChangeStateEvent fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ChangeStateEvent()
            .withTaskName(data.get("taskName") == null || data.get("taskName").isNull() ? null : data.get("taskName").asText())
            .withHash(data.get("hash") == null || data.get("hash").isNull() ? null : data.get("hash").asText())
            .withTimestamp(data.get("timestamp") == null || data.get("timestamp").isNull() ? null : data.get("timestamp").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("taskName", getTaskName());
                put("hash", getHash());
                put("timestamp", getTimestamp());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.taskName == null) ? 0 : this.taskName.hashCode());
        result = prime * result + ((this.hash == null) ? 0 : this.hash.hashCode());
        result = prime * result + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
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
		ChangeStateEvent other = (ChangeStateEvent) o;
		if (taskName == null) {
			return other.taskName == null;
		} else if (!taskName.equals(other.taskName)) {
			return false;
		}
		if (hash == null) {
			return other.hash == null;
		} else if (!hash.equals(other.hash)) {
			return false;
		}
		if (timestamp == null) {
			return other.timestamp == null;
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		return true;
	}
}