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

package io.gs2.matchmaking.model;

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
public class TimeSpan implements IModel, Serializable {
	private Integer days;
	private Integer hours;
	private Integer minutes;
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public TimeSpan withDays(Integer days) {
		this.days = days;
		return this;
	}
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public TimeSpan withHours(Integer hours) {
		this.hours = hours;
		return this;
	}
	public Integer getMinutes() {
		return minutes;
	}
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	public TimeSpan withMinutes(Integer minutes) {
		this.minutes = minutes;
		return this;
	}

    public static TimeSpan fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new TimeSpan()
            .withDays(data.get("days") == null || data.get("days").isNull() ? null : data.get("days").intValue())
            .withHours(data.get("hours") == null || data.get("hours").isNull() ? null : data.get("hours").intValue())
            .withMinutes(data.get("minutes") == null || data.get("minutes").isNull() ? null : data.get("minutes").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("days", getDays());
                put("hours", getHours());
                put("minutes", getMinutes());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.days == null) ? 0 : this.days.hashCode());
        result = prime * result + ((this.hours == null) ? 0 : this.hours.hashCode());
        result = prime * result + ((this.minutes == null) ? 0 : this.minutes.hashCode());
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
		TimeSpan other = (TimeSpan) o;
		if (days == null) {
			return other.days == null;
		} else if (!days.equals(other.days)) {
			return false;
		}
		if (hours == null) {
			return other.hours == null;
		} else if (!hours.equals(other.hours)) {
			return false;
		}
		if (minutes == null) {
			return other.minutes == null;
		} else if (!minutes.equals(other.minutes)) {
			return false;
		}
		return true;
	}
}