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

package io.gs2.enchant.model;

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
public class BalanceParameterStatus implements IModel, Serializable, Comparable<BalanceParameterStatus> {
	private String balanceParameterStatusId;
	private String userId;
	private String parameterName;
	private String propertyId;
	private List<BalanceParameterValue> parameterValues;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getBalanceParameterStatusId() {
		return balanceParameterStatusId;
	}
	public void setBalanceParameterStatusId(String balanceParameterStatusId) {
		this.balanceParameterStatusId = balanceParameterStatusId;
	}
	public BalanceParameterStatus withBalanceParameterStatusId(String balanceParameterStatusId) {
		this.balanceParameterStatusId = balanceParameterStatusId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BalanceParameterStatus withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public BalanceParameterStatus withParameterName(String parameterName) {
		this.parameterName = parameterName;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public BalanceParameterStatus withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public List<BalanceParameterValue> getParameterValues() {
		return parameterValues;
	}
	public void setParameterValues(List<BalanceParameterValue> parameterValues) {
		this.parameterValues = parameterValues;
	}
	public BalanceParameterStatus withParameterValues(List<BalanceParameterValue> parameterValues) {
		this.parameterValues = parameterValues;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public BalanceParameterStatus withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public BalanceParameterStatus withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public BalanceParameterStatus withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static BalanceParameterStatus fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BalanceParameterStatus()
            .withBalanceParameterStatusId(data.get("balanceParameterStatusId") == null || data.get("balanceParameterStatusId").isNull() ? null : data.get("balanceParameterStatusId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withParameterName(data.get("parameterName") == null || data.get("parameterName").isNull() ? null : data.get("parameterName").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withParameterValues(data.get("parameterValues") == null || data.get("parameterValues").isNull() ? new ArrayList<BalanceParameterValue>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("parameterValues").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return BalanceParameterValue.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("balanceParameterStatusId", getBalanceParameterStatusId());
                put("userId", getUserId());
                put("parameterName", getParameterName());
                put("propertyId", getPropertyId());
                put("parameterValues", getParameterValues() == null ? new ArrayList<BalanceParameterValue>() :
                    getParameterValues().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(BalanceParameterStatus o) {
		return balanceParameterStatusId.compareTo(o.balanceParameterStatusId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.balanceParameterStatusId == null) ? 0 : this.balanceParameterStatusId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.parameterName == null) ? 0 : this.parameterName.hashCode());
        result = prime * result + ((this.propertyId == null) ? 0 : this.propertyId.hashCode());
        result = prime * result + ((this.parameterValues == null) ? 0 : this.parameterValues.hashCode());
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
		BalanceParameterStatus other = (BalanceParameterStatus) o;
		if (balanceParameterStatusId == null) {
			return other.balanceParameterStatusId == null;
		} else if (!balanceParameterStatusId.equals(other.balanceParameterStatusId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (parameterName == null) {
			return other.parameterName == null;
		} else if (!parameterName.equals(other.parameterName)) {
			return false;
		}
		if (propertyId == null) {
			return other.propertyId == null;
		} else if (!propertyId.equals(other.propertyId)) {
			return false;
		}
		if (parameterValues == null) {
			return other.parameterValues == null;
		} else if (!parameterValues.equals(other.parameterValues)) {
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