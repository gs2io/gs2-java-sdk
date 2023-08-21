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
public class RarityParameterStatus implements IModel, Serializable, Comparable<RarityParameterStatus> {
	private String rarityParameterStatusId;
	private String userId;
	private String parameterName;
	private String propertyId;
	private List<RarityParameterValue> parameterValues;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getRarityParameterStatusId() {
		return rarityParameterStatusId;
	}
	public void setRarityParameterStatusId(String rarityParameterStatusId) {
		this.rarityParameterStatusId = rarityParameterStatusId;
	}
	public RarityParameterStatus withRarityParameterStatusId(String rarityParameterStatusId) {
		this.rarityParameterStatusId = rarityParameterStatusId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public RarityParameterStatus withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public RarityParameterStatus withParameterName(String parameterName) {
		this.parameterName = parameterName;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public RarityParameterStatus withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public List<RarityParameterValue> getParameterValues() {
		return parameterValues;
	}
	public void setParameterValues(List<RarityParameterValue> parameterValues) {
		this.parameterValues = parameterValues;
	}
	public RarityParameterStatus withParameterValues(List<RarityParameterValue> parameterValues) {
		this.parameterValues = parameterValues;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public RarityParameterStatus withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public RarityParameterStatus withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public RarityParameterStatus withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static RarityParameterStatus fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RarityParameterStatus()
            .withRarityParameterStatusId(data.get("rarityParameterStatusId") == null || data.get("rarityParameterStatusId").isNull() ? null : data.get("rarityParameterStatusId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withParameterName(data.get("parameterName") == null || data.get("parameterName").isNull() ? null : data.get("parameterName").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withParameterValues(data.get("parameterValues") == null || data.get("parameterValues").isNull() ? new ArrayList<RarityParameterValue>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("parameterValues").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RarityParameterValue.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("rarityParameterStatusId", getRarityParameterStatusId());
                put("userId", getUserId());
                put("parameterName", getParameterName());
                put("propertyId", getPropertyId());
                put("parameterValues", getParameterValues() == null ? new ArrayList<RarityParameterValue>() :
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
	public int compareTo(RarityParameterStatus o) {
		return rarityParameterStatusId.compareTo(o.rarityParameterStatusId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.rarityParameterStatusId == null) ? 0 : this.rarityParameterStatusId.hashCode());
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
		RarityParameterStatus other = (RarityParameterStatus) o;
		if (rarityParameterStatusId == null) {
			return other.rarityParameterStatusId == null;
		} else if (!rarityParameterStatusId.equals(other.rarityParameterStatusId)) {
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