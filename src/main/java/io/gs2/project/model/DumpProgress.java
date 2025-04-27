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
public class DumpProgress implements IModel, Serializable, Comparable<DumpProgress> {
	private String dumpProgressId;
	private String transactionId;
	private String userId;
	private Integer dumped;
	private Integer microserviceCount;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getDumpProgressId() {
		return dumpProgressId;
	}
	public void setDumpProgressId(String dumpProgressId) {
		this.dumpProgressId = dumpProgressId;
	}
	public DumpProgress withDumpProgressId(String dumpProgressId) {
		this.dumpProgressId = dumpProgressId;
		return this;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public DumpProgress withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public DumpProgress withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getDumped() {
		return dumped;
	}
	public void setDumped(Integer dumped) {
		this.dumped = dumped;
	}
	public DumpProgress withDumped(Integer dumped) {
		this.dumped = dumped;
		return this;
	}
	public Integer getMicroserviceCount() {
		return microserviceCount;
	}
	public void setMicroserviceCount(Integer microserviceCount) {
		this.microserviceCount = microserviceCount;
	}
	public DumpProgress withMicroserviceCount(Integer microserviceCount) {
		this.microserviceCount = microserviceCount;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public DumpProgress withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public DumpProgress withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public DumpProgress withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static DumpProgress fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DumpProgress()
            .withDumpProgressId(data.get("dumpProgressId") == null || data.get("dumpProgressId").isNull() ? null : data.get("dumpProgressId").asText())
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withDumped(data.get("dumped") == null || data.get("dumped").isNull() ? null : data.get("dumped").intValue())
            .withMicroserviceCount(data.get("microserviceCount") == null || data.get("microserviceCount").isNull() ? null : data.get("microserviceCount").intValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("dumpProgressId", getDumpProgressId());
                put("transactionId", getTransactionId());
                put("userId", getUserId());
                put("dumped", getDumped());
                put("microserviceCount", getMicroserviceCount());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(DumpProgress o) {
		return dumpProgressId.compareTo(o.dumpProgressId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.dumpProgressId == null) ? 0 : this.dumpProgressId.hashCode());
        result = prime * result + ((this.transactionId == null) ? 0 : this.transactionId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.dumped == null) ? 0 : this.dumped.hashCode());
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
		DumpProgress other = (DumpProgress) o;
		if (dumpProgressId == null) {
			return other.dumpProgressId == null;
		} else if (!dumpProgressId.equals(other.dumpProgressId)) {
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
		if (dumped == null) {
			return other.dumped == null;
		} else if (!dumped.equals(other.dumped)) {
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