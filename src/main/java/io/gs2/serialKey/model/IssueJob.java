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

package io.gs2.serialKey.model;

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
public class IssueJob implements IModel, Serializable, Comparable<IssueJob> {
	private String issueJobId;
	private String name;
	private String metadata;
	private Integer issuedCount;
	private Integer issueRequestCount;
	private String status;
	private Long createdAt;
	public String getIssueJobId() {
		return issueJobId;
	}
	public void setIssueJobId(String issueJobId) {
		this.issueJobId = issueJobId;
	}
	public IssueJob withIssueJobId(String issueJobId) {
		this.issueJobId = issueJobId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IssueJob withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public IssueJob withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getIssuedCount() {
		return issuedCount;
	}
	public void setIssuedCount(Integer issuedCount) {
		this.issuedCount = issuedCount;
	}
	public IssueJob withIssuedCount(Integer issuedCount) {
		this.issuedCount = issuedCount;
		return this;
	}
	public Integer getIssueRequestCount() {
		return issueRequestCount;
	}
	public void setIssueRequestCount(Integer issueRequestCount) {
		this.issueRequestCount = issueRequestCount;
	}
	public IssueJob withIssueRequestCount(Integer issueRequestCount) {
		this.issueRequestCount = issueRequestCount;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public IssueJob withStatus(String status) {
		this.status = status;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public IssueJob withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static IssueJob fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new IssueJob()
            .withIssueJobId(data.get("issueJobId") == null || data.get("issueJobId").isNull() ? null : data.get("issueJobId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withIssuedCount(data.get("issuedCount") == null || data.get("issuedCount").isNull() ? null : data.get("issuedCount").intValue())
            .withIssueRequestCount(data.get("issueRequestCount") == null || data.get("issueRequestCount").isNull() ? null : data.get("issueRequestCount").intValue())
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : data.get("status").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("issueJobId", getIssueJobId());
                put("name", getName());
                put("metadata", getMetadata());
                put("issuedCount", getIssuedCount());
                put("issueRequestCount", getIssueRequestCount());
                put("status", getStatus());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(IssueJob o) {
		return issueJobId.compareTo(o.issueJobId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.issueJobId == null) ? 0 : this.issueJobId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.issuedCount == null) ? 0 : this.issuedCount.hashCode());
        result = prime * result + ((this.issueRequestCount == null) ? 0 : this.issueRequestCount.hashCode());
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
		IssueJob other = (IssueJob) o;
		if (issueJobId == null) {
			return other.issueJobId == null;
		} else if (!issueJobId.equals(other.issueJobId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (issuedCount == null) {
			return other.issuedCount == null;
		} else if (!issuedCount.equals(other.issuedCount)) {
			return false;
		}
		if (issueRequestCount == null) {
			return other.issueRequestCount == null;
		} else if (!issueRequestCount.equals(other.issueRequestCount)) {
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