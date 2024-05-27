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

package io.gs2.ranking.model;

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
public class FixedTiming implements IModel, Serializable {
	private Integer hour;
	private Integer minute;
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public FixedTiming withHour(Integer hour) {
		this.hour = hour;
		return this;
	}
	public Integer getMinute() {
		return minute;
	}
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	public FixedTiming withMinute(Integer minute) {
		this.minute = minute;
		return this;
	}

    public static FixedTiming fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new FixedTiming()
            .withHour(data.get("hour") == null || data.get("hour").isNull() ? null : data.get("hour").intValue())
            .withMinute(data.get("minute") == null || data.get("minute").isNull() ? null : data.get("minute").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("hour", getHour());
                put("minute", getMinute());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.hour == null) ? 0 : this.hour.hashCode());
        result = prime * result + ((this.minute == null) ? 0 : this.minute.hashCode());
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
		FixedTiming other = (FixedTiming) o;
		if (hour == null) {
			return other.hour == null;
		} else if (!hour.equals(other.hour)) {
			return false;
		}
		if (minute == null) {
			return other.minute == null;
		} else if (!minute.equals(other.minute)) {
			return false;
		}
		return true;
	}
}