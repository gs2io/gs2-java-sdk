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

package io.gs2.stateMachine.model;

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
public class Variable implements IModel, Serializable {
	private String stateMachineName;
	private String value;
	public String getStateMachineName() {
		return stateMachineName;
	}
	public void setStateMachineName(String stateMachineName) {
		this.stateMachineName = stateMachineName;
	}
	public Variable withStateMachineName(String stateMachineName) {
		this.stateMachineName = stateMachineName;
		return this;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Variable withValue(String value) {
		this.value = value;
		return this;
	}

    public static Variable fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Variable()
            .withStateMachineName(data.get("stateMachineName") == null || data.get("stateMachineName").isNull() ? null : data.get("stateMachineName").asText())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("stateMachineName", getStateMachineName());
                put("value", getValue());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.stateMachineName == null) ? 0 : this.stateMachineName.hashCode());
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
		Variable other = (Variable) o;
		if (stateMachineName == null) {
			return other.stateMachineName == null;
		} else if (!stateMachineName.equals(other.stateMachineName)) {
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