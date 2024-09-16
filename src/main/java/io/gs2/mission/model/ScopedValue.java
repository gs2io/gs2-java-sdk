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

package io.gs2.mission.model;

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
public class ScopedValue implements IModel, Serializable {
	private String scopeType;
	private String resetType;
	private String conditionName;
	private Long value;
	private Long nextResetAt;
	private Long updatedAt;
	public String getScopeType() {
		return scopeType;
	}
	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}
	public ScopedValue withScopeType(String scopeType) {
		this.scopeType = scopeType;
		return this;
	}
	public String getResetType() {
		return resetType;
	}
	public void setResetType(String resetType) {
		this.resetType = resetType;
	}
	public ScopedValue withResetType(String resetType) {
		this.resetType = resetType;
		return this;
	}
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	public ScopedValue withConditionName(String conditionName) {
		this.conditionName = conditionName;
		return this;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public ScopedValue withValue(Long value) {
		this.value = value;
		return this;
	}
	public Long getNextResetAt() {
		return nextResetAt;
	}
	public void setNextResetAt(Long nextResetAt) {
		this.nextResetAt = nextResetAt;
	}
	public ScopedValue withNextResetAt(Long nextResetAt) {
		this.nextResetAt = nextResetAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public ScopedValue withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static ScopedValue fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ScopedValue()
            .withScopeType(data.get("scopeType") == null || data.get("scopeType").isNull() ? null : data.get("scopeType").asText())
            .withResetType(data.get("resetType") == null || data.get("resetType").isNull() ? null : data.get("resetType").asText())
            .withConditionName(data.get("conditionName") == null || data.get("conditionName").isNull() ? null : data.get("conditionName").asText())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").longValue())
            .withNextResetAt(data.get("nextResetAt") == null || data.get("nextResetAt").isNull() ? null : data.get("nextResetAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("scopeType", getScopeType());
                put("resetType", getResetType());
                put("conditionName", getConditionName());
                put("value", getValue());
                put("nextResetAt", getNextResetAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.scopeType == null) ? 0 : this.scopeType.hashCode());
        result = prime * result + ((this.resetType == null) ? 0 : this.resetType.hashCode());
        result = prime * result + ((this.conditionName == null) ? 0 : this.conditionName.hashCode());
        result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
        result = prime * result + ((this.nextResetAt == null) ? 0 : this.nextResetAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		ScopedValue other = (ScopedValue) o;
		if (scopeType == null) {
			return other.scopeType == null;
		} else if (!scopeType.equals(other.scopeType)) {
			return false;
		}
		if (resetType == null) {
			return other.resetType == null;
		} else if (!resetType.equals(other.resetType)) {
			return false;
		}
		if (conditionName == null) {
			return other.conditionName == null;
		} else if (!conditionName.equals(other.conditionName)) {
			return false;
		}
		if (value == null) {
			return other.value == null;
		} else if (!value.equals(other.value)) {
			return false;
		}
		if (nextResetAt == null) {
			return other.nextResetAt == null;
		} else if (!nextResetAt.equals(other.nextResetAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}