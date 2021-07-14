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

package io.gs2.schedule.model;

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
public class EventMaster implements IModel, Serializable, Comparable<EventMaster> {
	private String eventId;
	private String name;
	private String description;
	private String metadata;
	private String scheduleType;
	private String repeatType;
	private Long absoluteBegin;
	private Long absoluteEnd;
	private Integer repeatBeginDayOfMonth;
	private Integer repeatEndDayOfMonth;
	private String repeatBeginDayOfWeek;
	private String repeatEndDayOfWeek;
	private Integer repeatBeginHour;
	private Integer repeatEndHour;
	private String relativeTriggerName;
	private Integer relativeDuration;
	private Long createdAt;
	private Long updatedAt;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public EventMaster withEventId(String eventId) {
		this.eventId = eventId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EventMaster withName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EventMaster withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public EventMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public EventMaster withScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
		return this;
	}

	public String getRepeatType() {
		return repeatType;
	}

	public void setRepeatType(String repeatType) {
		this.repeatType = repeatType;
	}

	public EventMaster withRepeatType(String repeatType) {
		this.repeatType = repeatType;
		return this;
	}

	public Long getAbsoluteBegin() {
		return absoluteBegin;
	}

	public void setAbsoluteBegin(Long absoluteBegin) {
		this.absoluteBegin = absoluteBegin;
	}

	public EventMaster withAbsoluteBegin(Long absoluteBegin) {
		this.absoluteBegin = absoluteBegin;
		return this;
	}

	public Long getAbsoluteEnd() {
		return absoluteEnd;
	}

	public void setAbsoluteEnd(Long absoluteEnd) {
		this.absoluteEnd = absoluteEnd;
	}

	public EventMaster withAbsoluteEnd(Long absoluteEnd) {
		this.absoluteEnd = absoluteEnd;
		return this;
	}

	public Integer getRepeatBeginDayOfMonth() {
		return repeatBeginDayOfMonth;
	}

	public void setRepeatBeginDayOfMonth(Integer repeatBeginDayOfMonth) {
		this.repeatBeginDayOfMonth = repeatBeginDayOfMonth;
	}

	public EventMaster withRepeatBeginDayOfMonth(Integer repeatBeginDayOfMonth) {
		this.repeatBeginDayOfMonth = repeatBeginDayOfMonth;
		return this;
	}

	public Integer getRepeatEndDayOfMonth() {
		return repeatEndDayOfMonth;
	}

	public void setRepeatEndDayOfMonth(Integer repeatEndDayOfMonth) {
		this.repeatEndDayOfMonth = repeatEndDayOfMonth;
	}

	public EventMaster withRepeatEndDayOfMonth(Integer repeatEndDayOfMonth) {
		this.repeatEndDayOfMonth = repeatEndDayOfMonth;
		return this;
	}

	public String getRepeatBeginDayOfWeek() {
		return repeatBeginDayOfWeek;
	}

	public void setRepeatBeginDayOfWeek(String repeatBeginDayOfWeek) {
		this.repeatBeginDayOfWeek = repeatBeginDayOfWeek;
	}

	public EventMaster withRepeatBeginDayOfWeek(String repeatBeginDayOfWeek) {
		this.repeatBeginDayOfWeek = repeatBeginDayOfWeek;
		return this;
	}

	public String getRepeatEndDayOfWeek() {
		return repeatEndDayOfWeek;
	}

	public void setRepeatEndDayOfWeek(String repeatEndDayOfWeek) {
		this.repeatEndDayOfWeek = repeatEndDayOfWeek;
	}

	public EventMaster withRepeatEndDayOfWeek(String repeatEndDayOfWeek) {
		this.repeatEndDayOfWeek = repeatEndDayOfWeek;
		return this;
	}

	public Integer getRepeatBeginHour() {
		return repeatBeginHour;
	}

	public void setRepeatBeginHour(Integer repeatBeginHour) {
		this.repeatBeginHour = repeatBeginHour;
	}

	public EventMaster withRepeatBeginHour(Integer repeatBeginHour) {
		this.repeatBeginHour = repeatBeginHour;
		return this;
	}

	public Integer getRepeatEndHour() {
		return repeatEndHour;
	}

	public void setRepeatEndHour(Integer repeatEndHour) {
		this.repeatEndHour = repeatEndHour;
	}

	public EventMaster withRepeatEndHour(Integer repeatEndHour) {
		this.repeatEndHour = repeatEndHour;
		return this;
	}

	public String getRelativeTriggerName() {
		return relativeTriggerName;
	}

	public void setRelativeTriggerName(String relativeTriggerName) {
		this.relativeTriggerName = relativeTriggerName;
	}

	public EventMaster withRelativeTriggerName(String relativeTriggerName) {
		this.relativeTriggerName = relativeTriggerName;
		return this;
	}

	public Integer getRelativeDuration() {
		return relativeDuration;
	}

	public void setRelativeDuration(Integer relativeDuration) {
		this.relativeDuration = relativeDuration;
	}

	public EventMaster withRelativeDuration(Integer relativeDuration) {
		this.relativeDuration = relativeDuration;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public EventMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public EventMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static EventMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new EventMaster()
            .withEventId(data.get("eventId") == null || data.get("eventId").isNull() ? null : data.get("eventId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withScheduleType(data.get("scheduleType") == null || data.get("scheduleType").isNull() ? null : data.get("scheduleType").asText())
            .withRepeatType(data.get("repeatType") == null || data.get("repeatType").isNull() ? null : data.get("repeatType").asText())
            .withAbsoluteBegin(data.get("absoluteBegin") == null || data.get("absoluteBegin").isNull() ? null : data.get("absoluteBegin").longValue())
            .withAbsoluteEnd(data.get("absoluteEnd") == null || data.get("absoluteEnd").isNull() ? null : data.get("absoluteEnd").longValue())
            .withRepeatBeginDayOfMonth(data.get("repeatBeginDayOfMonth") == null || data.get("repeatBeginDayOfMonth").isNull() ? null : data.get("repeatBeginDayOfMonth").intValue())
            .withRepeatEndDayOfMonth(data.get("repeatEndDayOfMonth") == null || data.get("repeatEndDayOfMonth").isNull() ? null : data.get("repeatEndDayOfMonth").intValue())
            .withRepeatBeginDayOfWeek(data.get("repeatBeginDayOfWeek") == null || data.get("repeatBeginDayOfWeek").isNull() ? null : data.get("repeatBeginDayOfWeek").asText())
            .withRepeatEndDayOfWeek(data.get("repeatEndDayOfWeek") == null || data.get("repeatEndDayOfWeek").isNull() ? null : data.get("repeatEndDayOfWeek").asText())
            .withRepeatBeginHour(data.get("repeatBeginHour") == null || data.get("repeatBeginHour").isNull() ? null : data.get("repeatBeginHour").intValue())
            .withRepeatEndHour(data.get("repeatEndHour") == null || data.get("repeatEndHour").isNull() ? null : data.get("repeatEndHour").intValue())
            .withRelativeTriggerName(data.get("relativeTriggerName") == null || data.get("relativeTriggerName").isNull() ? null : data.get("relativeTriggerName").asText())
            .withRelativeDuration(data.get("relativeDuration") == null || data.get("relativeDuration").isNull() ? null : data.get("relativeDuration").intValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("eventId", getEventId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("scheduleType", getScheduleType());
                put("repeatType", getRepeatType());
                put("absoluteBegin", getAbsoluteBegin());
                put("absoluteEnd", getAbsoluteEnd());
                put("repeatBeginDayOfMonth", getRepeatBeginDayOfMonth());
                put("repeatEndDayOfMonth", getRepeatEndDayOfMonth());
                put("repeatBeginDayOfWeek", getRepeatBeginDayOfWeek());
                put("repeatEndDayOfWeek", getRepeatEndDayOfWeek());
                put("repeatBeginHour", getRepeatBeginHour());
                put("repeatEndHour", getRepeatEndHour());
                put("relativeTriggerName", getRelativeTriggerName());
                put("relativeDuration", getRelativeDuration());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(EventMaster o) {
		return eventId.compareTo(o.eventId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.eventId == null) ? 0 : this.eventId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.scheduleType == null) ? 0 : this.scheduleType.hashCode());
        result = prime * result + ((this.repeatType == null) ? 0 : this.repeatType.hashCode());
        result = prime * result + ((this.absoluteBegin == null) ? 0 : this.absoluteBegin.hashCode());
        result = prime * result + ((this.absoluteEnd == null) ? 0 : this.absoluteEnd.hashCode());
        result = prime * result + ((this.repeatBeginDayOfMonth == null) ? 0 : this.repeatBeginDayOfMonth.hashCode());
        result = prime * result + ((this.repeatEndDayOfMonth == null) ? 0 : this.repeatEndDayOfMonth.hashCode());
        result = prime * result + ((this.repeatBeginDayOfWeek == null) ? 0 : this.repeatBeginDayOfWeek.hashCode());
        result = prime * result + ((this.repeatEndDayOfWeek == null) ? 0 : this.repeatEndDayOfWeek.hashCode());
        result = prime * result + ((this.repeatBeginHour == null) ? 0 : this.repeatBeginHour.hashCode());
        result = prime * result + ((this.repeatEndHour == null) ? 0 : this.repeatEndHour.hashCode());
        result = prime * result + ((this.relativeTriggerName == null) ? 0 : this.relativeTriggerName.hashCode());
        result = prime * result + ((this.relativeDuration == null) ? 0 : this.relativeDuration.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		EventMaster other = (EventMaster) o;
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
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (scheduleType == null) {
			return other.scheduleType == null;
		} else if (!scheduleType.equals(other.scheduleType)) {
			return false;
		}
		if (repeatType == null) {
			return other.repeatType == null;
		} else if (!repeatType.equals(other.repeatType)) {
			return false;
		}
		if (absoluteBegin == null) {
			return other.absoluteBegin == null;
		} else if (!absoluteBegin.equals(other.absoluteBegin)) {
			return false;
		}
		if (absoluteEnd == null) {
			return other.absoluteEnd == null;
		} else if (!absoluteEnd.equals(other.absoluteEnd)) {
			return false;
		}
		if (repeatBeginDayOfMonth == null) {
			return other.repeatBeginDayOfMonth == null;
		} else if (!repeatBeginDayOfMonth.equals(other.repeatBeginDayOfMonth)) {
			return false;
		}
		if (repeatEndDayOfMonth == null) {
			return other.repeatEndDayOfMonth == null;
		} else if (!repeatEndDayOfMonth.equals(other.repeatEndDayOfMonth)) {
			return false;
		}
		if (repeatBeginDayOfWeek == null) {
			return other.repeatBeginDayOfWeek == null;
		} else if (!repeatBeginDayOfWeek.equals(other.repeatBeginDayOfWeek)) {
			return false;
		}
		if (repeatEndDayOfWeek == null) {
			return other.repeatEndDayOfWeek == null;
		} else if (!repeatEndDayOfWeek.equals(other.repeatEndDayOfWeek)) {
			return false;
		}
		if (repeatBeginHour == null) {
			return other.repeatBeginHour == null;
		} else if (!repeatBeginHour.equals(other.repeatBeginHour)) {
			return false;
		}
		if (repeatEndHour == null) {
			return other.repeatEndHour == null;
		} else if (!repeatEndHour.equals(other.repeatEndHour)) {
			return false;
		}
		if (relativeTriggerName == null) {
			return other.relativeTriggerName == null;
		} else if (!relativeTriggerName.equals(other.relativeTriggerName)) {
			return false;
		}
		if (relativeDuration == null) {
			return other.relativeDuration == null;
		} else if (!relativeDuration.equals(other.relativeDuration)) {
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
		return true;
	}
}