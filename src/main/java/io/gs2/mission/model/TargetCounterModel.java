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
public class TargetCounterModel implements IModel, Serializable {
	private String counterName;
	private String resetType;
	private Long value;
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public TargetCounterModel withCounterName(String counterName) {
		this.counterName = counterName;
		return this;
	}
	public String getResetType() {
		return resetType;
	}
	public void setResetType(String resetType) {
		this.resetType = resetType;
	}
	public TargetCounterModel withResetType(String resetType) {
		this.resetType = resetType;
		return this;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public TargetCounterModel withValue(Long value) {
		this.value = value;
		return this;
	}

    public static TargetCounterModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new TargetCounterModel()
            .withCounterName(data.get("counterName") == null || data.get("counterName").isNull() ? null : data.get("counterName").asText())
            .withResetType(data.get("resetType") == null || data.get("resetType").isNull() ? null : data.get("resetType").asText())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("counterName", getCounterName());
                put("resetType", getResetType());
                put("value", getValue());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.counterName == null) ? 0 : this.counterName.hashCode());
        result = prime * result + ((this.resetType == null) ? 0 : this.resetType.hashCode());
        result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
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
		TargetCounterModel other = (TargetCounterModel) o;
		if (counterName == null) {
			return other.counterName == null;
		} else if (!counterName.equals(other.counterName)) {
			return false;
		}
		if (resetType == null) {
			return other.resetType == null;
		} else if (!resetType.equals(other.resetType)) {
			return false;
		}
		if (value == null) {
			return other.value == null;
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}