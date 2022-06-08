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

package io.gs2.deploy.model;

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
public class Event implements IModel, Serializable, Comparable<Event> {
	private String eventId;
	private String name;
	private String resourceName;
	private String type;
	private String message;
	private Long eventAt;
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public Event withEventId(String eventId) {
		this.eventId = eventId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Event withName(String name) {
		this.name = name;
		return this;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public Event withResourceName(String resourceName) {
		this.resourceName = resourceName;
		return this;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Event withType(String type) {
		this.type = type;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Event withMessage(String message) {
		this.message = message;
		return this;
	}
	public Long getEventAt() {
		return eventAt;
	}
	public void setEventAt(Long eventAt) {
		this.eventAt = eventAt;
	}
	public Event withEventAt(Long eventAt) {
		this.eventAt = eventAt;
		return this;
	}

    public static Event fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Event()
            .withEventId(data.get("eventId") == null || data.get("eventId").isNull() ? null : data.get("eventId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withResourceName(data.get("resourceName") == null || data.get("resourceName").isNull() ? null : data.get("resourceName").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withMessage(data.get("message") == null || data.get("message").isNull() ? null : data.get("message").asText())
            .withEventAt(data.get("eventAt") == null || data.get("eventAt").isNull() ? null : data.get("eventAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("eventId", getEventId());
                put("name", getName());
                put("resourceName", getResourceName());
                put("type", getType());
                put("message", getMessage());
                put("eventAt", getEventAt());
            }}
        );
    }

	@Override
	public int compareTo(Event o) {
		return eventId.compareTo(o.eventId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.eventId == null) ? 0 : this.eventId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.resourceName == null) ? 0 : this.resourceName.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.message == null) ? 0 : this.message.hashCode());
        result = prime * result + ((this.eventAt == null) ? 0 : this.eventAt.hashCode());
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
		if (eventId == null) {
			return other.eventId == null;
		} else if (!eventId.equals(other.eventId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (resourceName == null) {
			return other.resourceName == null;
		} else if (!resourceName.equals(other.resourceName)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (message == null) {
			return other.message == null;
		} else if (!message.equals(other.message)) {
			return false;
		}
		if (eventAt == null) {
			return other.eventAt == null;
		} else if (!eventAt.equals(other.eventAt)) {
			return false;
		}
		return true;
	}
}