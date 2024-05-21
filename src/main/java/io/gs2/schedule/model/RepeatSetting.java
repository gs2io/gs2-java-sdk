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
public class RepeatSetting implements IModel, Serializable {
	private String repeatType;
	private Integer beginDayOfMonth;
	private Integer endDayOfMonth;
	private String beginDayOfWeek;
	private String endDayOfWeek;
	private Integer beginHour;
	private Integer endHour;
	public String getRepeatType() {
		return repeatType;
	}
	public void setRepeatType(String repeatType) {
		this.repeatType = repeatType;
	}
	public RepeatSetting withRepeatType(String repeatType) {
		this.repeatType = repeatType;
		return this;
	}
	public Integer getBeginDayOfMonth() {
		return beginDayOfMonth;
	}
	public void setBeginDayOfMonth(Integer beginDayOfMonth) {
		this.beginDayOfMonth = beginDayOfMonth;
	}
	public RepeatSetting withBeginDayOfMonth(Integer beginDayOfMonth) {
		this.beginDayOfMonth = beginDayOfMonth;
		return this;
	}
	public Integer getEndDayOfMonth() {
		return endDayOfMonth;
	}
	public void setEndDayOfMonth(Integer endDayOfMonth) {
		this.endDayOfMonth = endDayOfMonth;
	}
	public RepeatSetting withEndDayOfMonth(Integer endDayOfMonth) {
		this.endDayOfMonth = endDayOfMonth;
		return this;
	}
	public String getBeginDayOfWeek() {
		return beginDayOfWeek;
	}
	public void setBeginDayOfWeek(String beginDayOfWeek) {
		this.beginDayOfWeek = beginDayOfWeek;
	}
	public RepeatSetting withBeginDayOfWeek(String beginDayOfWeek) {
		this.beginDayOfWeek = beginDayOfWeek;
		return this;
	}
	public String getEndDayOfWeek() {
		return endDayOfWeek;
	}
	public void setEndDayOfWeek(String endDayOfWeek) {
		this.endDayOfWeek = endDayOfWeek;
	}
	public RepeatSetting withEndDayOfWeek(String endDayOfWeek) {
		this.endDayOfWeek = endDayOfWeek;
		return this;
	}
	public Integer getBeginHour() {
		return beginHour;
	}
	public void setBeginHour(Integer beginHour) {
		this.beginHour = beginHour;
	}
	public RepeatSetting withBeginHour(Integer beginHour) {
		this.beginHour = beginHour;
		return this;
	}
	public Integer getEndHour() {
		return endHour;
	}
	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}
	public RepeatSetting withEndHour(Integer endHour) {
		this.endHour = endHour;
		return this;
	}

    public static RepeatSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RepeatSetting()
            .withRepeatType(data.get("repeatType") == null || data.get("repeatType").isNull() ? null : data.get("repeatType").asText())
            .withBeginDayOfMonth(data.get("beginDayOfMonth") == null || data.get("beginDayOfMonth").isNull() ? null : data.get("beginDayOfMonth").intValue())
            .withEndDayOfMonth(data.get("endDayOfMonth") == null || data.get("endDayOfMonth").isNull() ? null : data.get("endDayOfMonth").intValue())
            .withBeginDayOfWeek(data.get("beginDayOfWeek") == null || data.get("beginDayOfWeek").isNull() ? null : data.get("beginDayOfWeek").asText())
            .withEndDayOfWeek(data.get("endDayOfWeek") == null || data.get("endDayOfWeek").isNull() ? null : data.get("endDayOfWeek").asText())
            .withBeginHour(data.get("beginHour") == null || data.get("beginHour").isNull() ? null : data.get("beginHour").intValue())
            .withEndHour(data.get("endHour") == null || data.get("endHour").isNull() ? null : data.get("endHour").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("repeatType", getRepeatType());
                put("beginDayOfMonth", getBeginDayOfMonth());
                put("endDayOfMonth", getEndDayOfMonth());
                put("beginDayOfWeek", getBeginDayOfWeek());
                put("endDayOfWeek", getEndDayOfWeek());
                put("beginHour", getBeginHour());
                put("endHour", getEndHour());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.repeatType == null) ? 0 : this.repeatType.hashCode());
        result = prime * result + ((this.beginDayOfMonth == null) ? 0 : this.beginDayOfMonth.hashCode());
        result = prime * result + ((this.endDayOfMonth == null) ? 0 : this.endDayOfMonth.hashCode());
        result = prime * result + ((this.beginDayOfWeek == null) ? 0 : this.beginDayOfWeek.hashCode());
        result = prime * result + ((this.endDayOfWeek == null) ? 0 : this.endDayOfWeek.hashCode());
        result = prime * result + ((this.beginHour == null) ? 0 : this.beginHour.hashCode());
        result = prime * result + ((this.endHour == null) ? 0 : this.endHour.hashCode());
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
		RepeatSetting other = (RepeatSetting) o;
		if (repeatType == null) {
			return other.repeatType == null;
		} else if (!repeatType.equals(other.repeatType)) {
			return false;
		}
		if (beginDayOfMonth == null) {
			return other.beginDayOfMonth == null;
		} else if (!beginDayOfMonth.equals(other.beginDayOfMonth)) {
			return false;
		}
		if (endDayOfMonth == null) {
			return other.endDayOfMonth == null;
		} else if (!endDayOfMonth.equals(other.endDayOfMonth)) {
			return false;
		}
		if (beginDayOfWeek == null) {
			return other.beginDayOfWeek == null;
		} else if (!beginDayOfWeek.equals(other.beginDayOfWeek)) {
			return false;
		}
		if (endDayOfWeek == null) {
			return other.endDayOfWeek == null;
		} else if (!endDayOfWeek.equals(other.endDayOfWeek)) {
			return false;
		}
		if (beginHour == null) {
			return other.beginHour == null;
		} else if (!beginHour.equals(other.beginHour)) {
			return false;
		}
		if (endHour == null) {
			return other.endHour == null;
		} else if (!endHour.equals(other.endHour)) {
			return false;
		}
		return true;
	}
}