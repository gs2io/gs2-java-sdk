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
public class EmitEvent implements IModel, Serializable {
	private String event;
	private String parameters;
	private Long timestamp;
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public EmitEvent withEvent(String event) {
		this.event = event;
		return this;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	public EmitEvent withParameters(String parameters) {
		this.parameters = parameters;
		return this;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public EmitEvent withTimestamp(Long timestamp) {
		this.timestamp = timestamp;
		return this;
	}

    public static EmitEvent fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new EmitEvent()
            .withEvent(data.get("event") == null || data.get("event").isNull() ? null : data.get("event").asText())
            .withParameters(data.get("parameters") == null || data.get("parameters").isNull() ? null : data.get("parameters").asText())
            .withTimestamp(data.get("timestamp") == null || data.get("timestamp").isNull() ? null : data.get("timestamp").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("event", getEvent());
                put("parameters", getParameters());
                put("timestamp", getTimestamp());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.event == null) ? 0 : this.event.hashCode());
        result = prime * result + ((this.parameters == null) ? 0 : this.parameters.hashCode());
        result = prime * result + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
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
		EmitEvent other = (EmitEvent) o;
		if (event == null) {
			return other.event == null;
		} else if (!event.equals(other.event)) {
			return false;
		}
		if (parameters == null) {
			return other.parameters == null;
		} else if (!parameters.equals(other.parameters)) {
			return false;
		}
		if (timestamp == null) {
			return other.timestamp == null;
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		return true;
	}
}