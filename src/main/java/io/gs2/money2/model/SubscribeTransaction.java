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

package io.gs2.money2.model;

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
public class SubscribeTransaction implements IModel, Serializable, Comparable<SubscribeTransaction> {
	private String subscribeTransactionId;
	private String transactionId;
	private String store;
	private String userId;
	private String status;
	private String statusDetail;
	private Long expiresAt;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getSubscribeTransactionId() {
		return subscribeTransactionId;
	}
	public void setSubscribeTransactionId(String subscribeTransactionId) {
		this.subscribeTransactionId = subscribeTransactionId;
	}
	public SubscribeTransaction withSubscribeTransactionId(String subscribeTransactionId) {
		this.subscribeTransactionId = subscribeTransactionId;
		return this;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public SubscribeTransaction withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public SubscribeTransaction withStore(String store) {
		this.store = store;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SubscribeTransaction withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public SubscribeTransaction withStatus(String status) {
		this.status = status;
		return this;
	}
	public String getStatusDetail() {
		return statusDetail;
	}
	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}
	public SubscribeTransaction withStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
		return this;
	}
	public Long getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}
	public SubscribeTransaction withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public SubscribeTransaction withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public SubscribeTransaction withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public SubscribeTransaction withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static SubscribeTransaction fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SubscribeTransaction()
            .withSubscribeTransactionId(data.get("subscribeTransactionId") == null || data.get("subscribeTransactionId").isNull() ? null : data.get("subscribeTransactionId").asText())
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withStore(data.get("store") == null || data.get("store").isNull() ? null : data.get("store").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : data.get("status").asText())
            .withStatusDetail(data.get("statusDetail") == null || data.get("statusDetail").isNull() ? null : data.get("statusDetail").asText())
            .withExpiresAt(data.get("expiresAt") == null || data.get("expiresAt").isNull() ? null : data.get("expiresAt").longValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("subscribeTransactionId", getSubscribeTransactionId());
                put("transactionId", getTransactionId());
                put("store", getStore());
                put("userId", getUserId());
                put("status", getStatus());
                put("statusDetail", getStatusDetail());
                put("expiresAt", getExpiresAt());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(SubscribeTransaction o) {
		return subscribeTransactionId.compareTo(o.subscribeTransactionId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.subscribeTransactionId == null) ? 0 : this.subscribeTransactionId.hashCode());
        result = prime * result + ((this.transactionId == null) ? 0 : this.transactionId.hashCode());
        result = prime * result + ((this.store == null) ? 0 : this.store.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.statusDetail == null) ? 0 : this.statusDetail.hashCode());
        result = prime * result + ((this.expiresAt == null) ? 0 : this.expiresAt.hashCode());
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
		SubscribeTransaction other = (SubscribeTransaction) o;
		if (subscribeTransactionId == null) {
			return other.subscribeTransactionId == null;
		} else if (!subscribeTransactionId.equals(other.subscribeTransactionId)) {
			return false;
		}
		if (transactionId == null) {
			return other.transactionId == null;
		} else if (!transactionId.equals(other.transactionId)) {
			return false;
		}
		if (store == null) {
			return other.store == null;
		} else if (!store.equals(other.store)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (statusDetail == null) {
			return other.statusDetail == null;
		} else if (!statusDetail.equals(other.statusDetail)) {
			return false;
		}
		if (expiresAt == null) {
			return other.expiresAt == null;
		} else if (!expiresAt.equals(other.expiresAt)) {
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