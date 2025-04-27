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
public class ImportProgress implements IModel, Serializable, Comparable<ImportProgress> {
	private String importProgressId;
	private String transactionId;
	private String userId;
	private Integer imported;
	private Integer microserviceCount;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getImportProgressId() {
		return importProgressId;
	}
	public void setImportProgressId(String importProgressId) {
		this.importProgressId = importProgressId;
	}
	public ImportProgress withImportProgressId(String importProgressId) {
		this.importProgressId = importProgressId;
		return this;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public ImportProgress withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ImportProgress withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getImported() {
		return imported;
	}
	public void setImported(Integer imported) {
		this.imported = imported;
	}
	public ImportProgress withImported(Integer imported) {
		this.imported = imported;
		return this;
	}
	public Integer getMicroserviceCount() {
		return microserviceCount;
	}
	public void setMicroserviceCount(Integer microserviceCount) {
		this.microserviceCount = microserviceCount;
	}
	public ImportProgress withMicroserviceCount(Integer microserviceCount) {
		this.microserviceCount = microserviceCount;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public ImportProgress withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public ImportProgress withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public ImportProgress withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static ImportProgress fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ImportProgress()
            .withImportProgressId(data.get("importProgressId") == null || data.get("importProgressId").isNull() ? null : data.get("importProgressId").asText())
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withImported(data.get("imported") == null || data.get("imported").isNull() ? null : data.get("imported").intValue())
            .withMicroserviceCount(data.get("microserviceCount") == null || data.get("microserviceCount").isNull() ? null : data.get("microserviceCount").intValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("importProgressId", getImportProgressId());
                put("transactionId", getTransactionId());
                put("userId", getUserId());
                put("imported", getImported());
                put("microserviceCount", getMicroserviceCount());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(ImportProgress o) {
		return importProgressId.compareTo(o.importProgressId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.importProgressId == null) ? 0 : this.importProgressId.hashCode());
        result = prime * result + ((this.transactionId == null) ? 0 : this.transactionId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.imported == null) ? 0 : this.imported.hashCode());
        result = prime * result + ((this.microserviceCount == null) ? 0 : this.microserviceCount.hashCode());
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
		ImportProgress other = (ImportProgress) o;
		if (importProgressId == null) {
			return other.importProgressId == null;
		} else if (!importProgressId.equals(other.importProgressId)) {
			return false;
		}
		if (transactionId == null) {
			return other.transactionId == null;
		} else if (!transactionId.equals(other.transactionId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (imported == null) {
			return other.imported == null;
		} else if (!imported.equals(other.imported)) {
			return false;
		}
		if (microserviceCount == null) {
			return other.microserviceCount == null;
		} else if (!microserviceCount.equals(other.microserviceCount)) {
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