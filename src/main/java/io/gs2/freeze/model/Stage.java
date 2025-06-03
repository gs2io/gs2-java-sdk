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

package io.gs2.freeze.model;

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
public class Stage implements IModel, Serializable, Comparable<Stage> {
	private String stageId;
	private String name;
	private String sourceStageName;
	private Integer sortNumber;
	private String status;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getStageId() {
		return stageId;
	}
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}
	public Stage withStageId(String stageId) {
		this.stageId = stageId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Stage withName(String name) {
		this.name = name;
		return this;
	}
	public String getSourceStageName() {
		return sourceStageName;
	}
	public void setSourceStageName(String sourceStageName) {
		this.sourceStageName = sourceStageName;
	}
	public Stage withSourceStageName(String sourceStageName) {
		this.sourceStageName = sourceStageName;
		return this;
	}
	public Integer getSortNumber() {
		return sortNumber;
	}
	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}
	public Stage withSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Stage withStatus(String status) {
		this.status = status;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Stage withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Stage withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Stage withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Stage fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Stage()
            .withStageId(data.get("stageId") == null || data.get("stageId").isNull() ? null : data.get("stageId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withSourceStageName(data.get("sourceStageName") == null || data.get("sourceStageName").isNull() ? null : data.get("sourceStageName").asText())
            .withSortNumber(data.get("sortNumber") == null || data.get("sortNumber").isNull() ? null : data.get("sortNumber").intValue())
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : data.get("status").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("stageId", getStageId());
                put("name", getName());
                put("sourceStageName", getSourceStageName());
                put("sortNumber", getSortNumber());
                put("status", getStatus());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Stage o) {
		return stageId.compareTo(o.stageId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.stageId == null) ? 0 : this.stageId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.sourceStageName == null) ? 0 : this.sourceStageName.hashCode());
        result = prime * result + ((this.sortNumber == null) ? 0 : this.sortNumber.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
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
		Stage other = (Stage) o;
		if (stageId == null) {
			return other.stageId == null;
		} else if (!stageId.equals(other.stageId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (sourceStageName == null) {
			return other.sourceStageName == null;
		} else if (!sourceStageName.equals(other.sourceStageName)) {
			return false;
		}
		if (sortNumber == null) {
			return other.sortNumber == null;
		} else if (!sortNumber.equals(other.sortNumber)) {
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