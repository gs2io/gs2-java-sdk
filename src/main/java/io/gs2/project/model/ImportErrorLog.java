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

package io.gs2.project.model;

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
public class ImportErrorLog implements IModel, Serializable, Comparable<ImportErrorLog> {
	private String dumpProgressId;
	private String name;
	private String microserviceName;
	private String message;
	private Long createdAt;
	private Long revision;
	public String getDumpProgressId() {
		return dumpProgressId;
	}
	public void setDumpProgressId(String dumpProgressId) {
		this.dumpProgressId = dumpProgressId;
	}
	public ImportErrorLog withDumpProgressId(String dumpProgressId) {
		this.dumpProgressId = dumpProgressId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ImportErrorLog withName(String name) {
		this.name = name;
		return this;
	}
	public String getMicroserviceName() {
		return microserviceName;
	}
	public void setMicroserviceName(String microserviceName) {
		this.microserviceName = microserviceName;
	}
	public ImportErrorLog withMicroserviceName(String microserviceName) {
		this.microserviceName = microserviceName;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ImportErrorLog withMessage(String message) {
		this.message = message;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public ImportErrorLog withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public ImportErrorLog withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static ImportErrorLog fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ImportErrorLog()
            .withDumpProgressId(data.get("dumpProgressId") == null || data.get("dumpProgressId").isNull() ? null : data.get("dumpProgressId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMicroserviceName(data.get("microserviceName") == null || data.get("microserviceName").isNull() ? null : data.get("microserviceName").asText())
            .withMessage(data.get("message") == null || data.get("message").isNull() ? null : data.get("message").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("dumpProgressId", getDumpProgressId());
                put("name", getName());
                put("microserviceName", getMicroserviceName());
                put("message", getMessage());
                put("createdAt", getCreatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(ImportErrorLog o) {
		return dumpProgressId.compareTo(o.dumpProgressId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.dumpProgressId == null) ? 0 : this.dumpProgressId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.microserviceName == null) ? 0 : this.microserviceName.hashCode());
        result = prime * result + ((this.message == null) ? 0 : this.message.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		ImportErrorLog other = (ImportErrorLog) o;
		if (dumpProgressId == null) {
			return other.dumpProgressId == null;
		} else if (!dumpProgressId.equals(other.dumpProgressId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (microserviceName == null) {
			return other.microserviceName == null;
		} else if (!microserviceName.equals(other.microserviceName)) {
			return false;
		}
		if (message == null) {
			return other.message == null;
		} else if (!message.equals(other.message)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
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