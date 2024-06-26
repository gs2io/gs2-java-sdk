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
public class Event implements IModel, Serializable, Comparable<Event> {
	private String eventId;
	private String name;
	private String metadata;
	private String scheduleType;
	private Long absoluteBegin;
	private Long absoluteEnd;
	private String relativeTriggerName;
	private RepeatSetting repeatSetting;
	private String repeatType;
	private Integer repeatBeginDayOfMonth;
	private Integer repeatEndDayOfMonth;
	private String repeatBeginDayOfWeek;
	private String repeatEndDayOfWeek;
	private Integer repeatBeginHour;
	private Integer repeatEndHour;
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
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public Event withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	public Event withScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
		return this;
	}
	public Long getAbsoluteBegin() {
		return absoluteBegin;
	}
	public void setAbsoluteBegin(Long absoluteBegin) {
		this.absoluteBegin = absoluteBegin;
	}
	public Event withAbsoluteBegin(Long absoluteBegin) {
		this.absoluteBegin = absoluteBegin;
		return this;
	}
	public Long getAbsoluteEnd() {
		return absoluteEnd;
	}
	public void setAbsoluteEnd(Long absoluteEnd) {
		this.absoluteEnd = absoluteEnd;
	}
	public Event withAbsoluteEnd(Long absoluteEnd) {
		this.absoluteEnd = absoluteEnd;
		return this;
	}
	public String getRelativeTriggerName() {
		return relativeTriggerName;
	}
	public void setRelativeTriggerName(String relativeTriggerName) {
		this.relativeTriggerName = relativeTriggerName;
	}
	public Event withRelativeTriggerName(String relativeTriggerName) {
		this.relativeTriggerName = relativeTriggerName;
		return this;
	}
	public RepeatSetting getRepeatSetting() {
		return repeatSetting;
	}
	public void setRepeatSetting(RepeatSetting repeatSetting) {
		this.repeatSetting = repeatSetting;
	}
	public Event withRepeatSetting(RepeatSetting repeatSetting) {
		this.repeatSetting = repeatSetting;
		return this;
	}
    @Deprecated
	public String getRepeatType() {
		return repeatType;
	}
    @Deprecated
	public void setRepeatType(String repeatType) {
		this.repeatType = repeatType;
	}
    @Deprecated
	public Event withRepeatType(String repeatType) {
		this.repeatType = repeatType;
		return this;
	}
    @Deprecated
	public Integer getRepeatBeginDayOfMonth() {
		return repeatBeginDayOfMonth;
	}
    @Deprecated
	public void setRepeatBeginDayOfMonth(Integer repeatBeginDayOfMonth) {
		this.repeatBeginDayOfMonth = repeatBeginDayOfMonth;
	}
    @Deprecated
	public Event withRepeatBeginDayOfMonth(Integer repeatBeginDayOfMonth) {
		this.repeatBeginDayOfMonth = repeatBeginDayOfMonth;
		return this;
	}
    @Deprecated
	public Integer getRepeatEndDayOfMonth() {
		return repeatEndDayOfMonth;
	}
    @Deprecated
	public void setRepeatEndDayOfMonth(Integer repeatEndDayOfMonth) {
		this.repeatEndDayOfMonth = repeatEndDayOfMonth;
	}
    @Deprecated
	public Event withRepeatEndDayOfMonth(Integer repeatEndDayOfMonth) {
		this.repeatEndDayOfMonth = repeatEndDayOfMonth;
		return this;
	}
    @Deprecated
	public String getRepeatBeginDayOfWeek() {
		return repeatBeginDayOfWeek;
	}
    @Deprecated
	public void setRepeatBeginDayOfWeek(String repeatBeginDayOfWeek) {
		this.repeatBeginDayOfWeek = repeatBeginDayOfWeek;
	}
    @Deprecated
	public Event withRepeatBeginDayOfWeek(String repeatBeginDayOfWeek) {
		this.repeatBeginDayOfWeek = repeatBeginDayOfWeek;
		return this;
	}
    @Deprecated
	public String getRepeatEndDayOfWeek() {
		return repeatEndDayOfWeek;
	}
    @Deprecated
	public void setRepeatEndDayOfWeek(String repeatEndDayOfWeek) {
		this.repeatEndDayOfWeek = repeatEndDayOfWeek;
	}
    @Deprecated
	public Event withRepeatEndDayOfWeek(String repeatEndDayOfWeek) {
		this.repeatEndDayOfWeek = repeatEndDayOfWeek;
		return this;
	}
    @Deprecated
	public Integer getRepeatBeginHour() {
		return repeatBeginHour;
	}
    @Deprecated
	public void setRepeatBeginHour(Integer repeatBeginHour) {
		this.repeatBeginHour = repeatBeginHour;
	}
    @Deprecated
	public Event withRepeatBeginHour(Integer repeatBeginHour) {
		this.repeatBeginHour = repeatBeginHour;
		return this;
	}
    @Deprecated
	public Integer getRepeatEndHour() {
		return repeatEndHour;
	}
    @Deprecated
	public void setRepeatEndHour(Integer repeatEndHour) {
		this.repeatEndHour = repeatEndHour;
	}
    @Deprecated
	public Event withRepeatEndHour(Integer repeatEndHour) {
		this.repeatEndHour = repeatEndHour;
		return this;
	}

    public static Event fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Event()
            .withEventId(data.get("eventId") == null || data.get("eventId").isNull() ? null : data.get("eventId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withScheduleType(data.get("scheduleType") == null || data.get("scheduleType").isNull() ? null : data.get("scheduleType").asText())
            .withAbsoluteBegin(data.get("absoluteBegin") == null || data.get("absoluteBegin").isNull() ? null : data.get("absoluteBegin").longValue())
            .withAbsoluteEnd(data.get("absoluteEnd") == null || data.get("absoluteEnd").isNull() ? null : data.get("absoluteEnd").longValue())
            .withRelativeTriggerName(data.get("relativeTriggerName") == null || data.get("relativeTriggerName").isNull() ? null : data.get("relativeTriggerName").asText())
            .withRepeatSetting(data.get("repeatSetting") == null || data.get("repeatSetting").isNull() ? null : RepeatSetting.fromJson(data.get("repeatSetting")))
            .withRepeatType(data.get("repeatType") == null || data.get("repeatType").isNull() ? null : data.get("repeatType").asText())
            .withRepeatBeginDayOfMonth(data.get("repeatBeginDayOfMonth") == null || data.get("repeatBeginDayOfMonth").isNull() ? null : data.get("repeatBeginDayOfMonth").intValue())
            .withRepeatEndDayOfMonth(data.get("repeatEndDayOfMonth") == null || data.get("repeatEndDayOfMonth").isNull() ? null : data.get("repeatEndDayOfMonth").intValue())
            .withRepeatBeginDayOfWeek(data.get("repeatBeginDayOfWeek") == null || data.get("repeatBeginDayOfWeek").isNull() ? null : data.get("repeatBeginDayOfWeek").asText())
            .withRepeatEndDayOfWeek(data.get("repeatEndDayOfWeek") == null || data.get("repeatEndDayOfWeek").isNull() ? null : data.get("repeatEndDayOfWeek").asText())
            .withRepeatBeginHour(data.get("repeatBeginHour") == null || data.get("repeatBeginHour").isNull() ? null : data.get("repeatBeginHour").intValue())
            .withRepeatEndHour(data.get("repeatEndHour") == null || data.get("repeatEndHour").isNull() ? null : data.get("repeatEndHour").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("eventId", getEventId());
                put("name", getName());
                put("metadata", getMetadata());
                put("scheduleType", getScheduleType());
                put("absoluteBegin", getAbsoluteBegin());
                put("absoluteEnd", getAbsoluteEnd());
                put("relativeTriggerName", getRelativeTriggerName());
                put("repeatSetting", getRepeatSetting() != null ? getRepeatSetting().toJson() : null);
                put("repeatType", getRepeatType());
                put("repeatBeginDayOfMonth", getRepeatBeginDayOfMonth());
                put("repeatEndDayOfMonth", getRepeatEndDayOfMonth());
                put("repeatBeginDayOfWeek", getRepeatBeginDayOfWeek());
                put("repeatEndDayOfWeek", getRepeatEndDayOfWeek());
                put("repeatBeginHour", getRepeatBeginHour());
                put("repeatEndHour", getRepeatEndHour());
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
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.scheduleType == null) ? 0 : this.scheduleType.hashCode());
        result = prime * result + ((this.absoluteBegin == null) ? 0 : this.absoluteBegin.hashCode());
        result = prime * result + ((this.absoluteEnd == null) ? 0 : this.absoluteEnd.hashCode());
        result = prime * result + ((this.relativeTriggerName == null) ? 0 : this.relativeTriggerName.hashCode());
        result = prime * result + ((this.repeatSetting == null) ? 0 : this.repeatSetting.hashCode());
        result = prime * result + ((this.repeatType == null) ? 0 : this.repeatType.hashCode());
        result = prime * result + ((this.repeatBeginDayOfMonth == null) ? 0 : this.repeatBeginDayOfMonth.hashCode());
        result = prime * result + ((this.repeatEndDayOfMonth == null) ? 0 : this.repeatEndDayOfMonth.hashCode());
        result = prime * result + ((this.repeatBeginDayOfWeek == null) ? 0 : this.repeatBeginDayOfWeek.hashCode());
        result = prime * result + ((this.repeatEndDayOfWeek == null) ? 0 : this.repeatEndDayOfWeek.hashCode());
        result = prime * result + ((this.repeatBeginHour == null) ? 0 : this.repeatBeginHour.hashCode());
        result = prime * result + ((this.repeatEndHour == null) ? 0 : this.repeatEndHour.hashCode());
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
		if (relativeTriggerName == null) {
			return other.relativeTriggerName == null;
		} else if (!relativeTriggerName.equals(other.relativeTriggerName)) {
			return false;
		}
		if (repeatSetting == null) {
			return other.repeatSetting == null;
		} else if (!repeatSetting.equals(other.repeatSetting)) {
			return false;
		}
		if (repeatType == null) {
			return other.repeatType == null;
		} else if (!repeatType.equals(other.repeatType)) {
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
		return true;
	}
}