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
public class StateMachineMaster implements IModel, Serializable, Comparable<StateMachineMaster> {
	private String stateMachineId;
	private String mainStateMachineName;
	private String payload;
	private Long version;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getStateMachineId() {
		return stateMachineId;
	}
	public void setStateMachineId(String stateMachineId) {
		this.stateMachineId = stateMachineId;
	}
	public StateMachineMaster withStateMachineId(String stateMachineId) {
		this.stateMachineId = stateMachineId;
		return this;
	}
	public String getMainStateMachineName() {
		return mainStateMachineName;
	}
	public void setMainStateMachineName(String mainStateMachineName) {
		this.mainStateMachineName = mainStateMachineName;
	}
	public StateMachineMaster withMainStateMachineName(String mainStateMachineName) {
		this.mainStateMachineName = mainStateMachineName;
		return this;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public StateMachineMaster withPayload(String payload) {
		this.payload = payload;
		return this;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public StateMachineMaster withVersion(Long version) {
		this.version = version;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public StateMachineMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public StateMachineMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public StateMachineMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static StateMachineMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new StateMachineMaster()
            .withStateMachineId(data.get("stateMachineId") == null || data.get("stateMachineId").isNull() ? null : data.get("stateMachineId").asText())
            .withMainStateMachineName(data.get("mainStateMachineName") == null || data.get("mainStateMachineName").isNull() ? null : data.get("mainStateMachineName").asText())
            .withPayload(data.get("payload") == null || data.get("payload").isNull() ? null : data.get("payload").asText())
            .withVersion(data.get("version") == null || data.get("version").isNull() ? null : data.get("version").longValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("stateMachineId", getStateMachineId());
                put("mainStateMachineName", getMainStateMachineName());
                put("payload", getPayload());
                put("version", getVersion());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(StateMachineMaster o) {
		return stateMachineId.compareTo(o.stateMachineId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.stateMachineId == null) ? 0 : this.stateMachineId.hashCode());
        result = prime * result + ((this.mainStateMachineName == null) ? 0 : this.mainStateMachineName.hashCode());
        result = prime * result + ((this.payload == null) ? 0 : this.payload.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
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
		StateMachineMaster other = (StateMachineMaster) o;
		if (stateMachineId == null) {
			return other.stateMachineId == null;
		} else if (!stateMachineId.equals(other.stateMachineId)) {
			return false;
		}
		if (mainStateMachineName == null) {
			return other.mainStateMachineName == null;
		} else if (!mainStateMachineName.equals(other.mainStateMachineName)) {
			return false;
		}
		if (payload == null) {
			return other.payload == null;
		} else if (!payload.equals(other.payload)) {
			return false;
		}
		if (version == null) {
			return other.version == null;
		} else if (!version.equals(other.version)) {
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