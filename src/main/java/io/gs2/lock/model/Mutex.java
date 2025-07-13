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

package io.gs2.lock.model;

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
public class Mutex implements IModel, Serializable, Comparable<Mutex> {
	private String mutexId;
	private String userId;
	private String propertyId;
	private String transactionId;
	private Long createdAt;
	private Long ttlAt;
	private Long revision;
	public String getMutexId() {
		return mutexId;
	}
	public void setMutexId(String mutexId) {
		this.mutexId = mutexId;
	}
	public Mutex withMutexId(String mutexId) {
		this.mutexId = mutexId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Mutex withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public Mutex withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Mutex withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Mutex withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getTtlAt() {
		return ttlAt;
	}
	public void setTtlAt(Long ttlAt) {
		this.ttlAt = ttlAt;
	}
	public Mutex withTtlAt(Long ttlAt) {
		this.ttlAt = ttlAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Mutex withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Mutex fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Mutex()
            .withMutexId(data.get("mutexId") == null || data.get("mutexId").isNull() ? null : data.get("mutexId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withTtlAt(data.get("ttlAt") == null || data.get("ttlAt").isNull() ? null : data.get("ttlAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("mutexId", getMutexId());
                put("userId", getUserId());
                put("propertyId", getPropertyId());
                put("transactionId", getTransactionId());
                put("createdAt", getCreatedAt());
                put("ttlAt", getTtlAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Mutex o) {
		return mutexId.compareTo(o.mutexId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.mutexId == null) ? 0 : this.mutexId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.propertyId == null) ? 0 : this.propertyId.hashCode());
        result = prime * result + ((this.transactionId == null) ? 0 : this.transactionId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.ttlAt == null) ? 0 : this.ttlAt.hashCode());
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
		Mutex other = (Mutex) o;
		if (mutexId == null) {
			return other.mutexId == null;
		} else if (!mutexId.equals(other.mutexId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (propertyId == null) {
			return other.propertyId == null;
		} else if (!propertyId.equals(other.propertyId)) {
			return false;
		}
		if (transactionId == null) {
			return other.transactionId == null;
		} else if (!transactionId.equals(other.transactionId)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (ttlAt == null) {
			return other.ttlAt == null;
		} else if (!ttlAt.equals(other.ttlAt)) {
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