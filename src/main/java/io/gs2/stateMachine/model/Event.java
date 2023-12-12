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
public class Event implements IModel, Serializable {
	private String eventType;
	private ChangeStateEvent changeStateEvent;
	private EmitEvent emitEvent;
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public Event withEventType(String eventType) {
		this.eventType = eventType;
		return this;
	}
	public ChangeStateEvent getChangeStateEvent() {
		return changeStateEvent;
	}
	public void setChangeStateEvent(ChangeStateEvent changeStateEvent) {
		this.changeStateEvent = changeStateEvent;
	}
	public Event withChangeStateEvent(ChangeStateEvent changeStateEvent) {
		this.changeStateEvent = changeStateEvent;
		return this;
	}
	public EmitEvent getEmitEvent() {
		return emitEvent;
	}
	public void setEmitEvent(EmitEvent emitEvent) {
		this.emitEvent = emitEvent;
	}
	public Event withEmitEvent(EmitEvent emitEvent) {
		this.emitEvent = emitEvent;
		return this;
	}

    public static Event fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Event()
            .withEventType(data.get("eventType") == null || data.get("eventType").isNull() ? null : data.get("eventType").asText())
            .withChangeStateEvent(data.get("changeStateEvent") == null || data.get("changeStateEvent").isNull() ? null : ChangeStateEvent.fromJson(data.get("changeStateEvent")))
            .withEmitEvent(data.get("emitEvent") == null || data.get("emitEvent").isNull() ? null : EmitEvent.fromJson(data.get("emitEvent")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("eventType", getEventType());
                put("changeStateEvent", getChangeStateEvent() != null ? getChangeStateEvent().toJson() : null);
                put("emitEvent", getEmitEvent() != null ? getEmitEvent().toJson() : null);
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.eventType == null) ? 0 : this.eventType.hashCode());
        result = prime * result + ((this.changeStateEvent == null) ? 0 : this.changeStateEvent.hashCode());
        result = prime * result + ((this.emitEvent == null) ? 0 : this.emitEvent.hashCode());
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
		Event other = (Event) o;
		if (eventType == null) {
			return other.eventType == null;
		} else if (!eventType.equals(other.eventType)) {
			return false;
		}
		if (changeStateEvent == null) {
			return other.changeStateEvent == null;
		} else if (!changeStateEvent.equals(other.changeStateEvent)) {
			return false;
		}
		if (emitEvent == null) {
			return other.emitEvent == null;
		} else if (!emitEvent.equals(other.emitEvent)) {
			return false;
		}
		return true;
	}
}