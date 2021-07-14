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
public class CounterScopeModel implements IModel, Serializable {
	private String resetType;
	private Integer resetDayOfMonth;
	private String resetDayOfWeek;
	private Integer resetHour;

	public String getResetType() {
		return resetType;
	}

	public void setResetType(String resetType) {
		this.resetType = resetType;
	}

	public CounterScopeModel withResetType(String resetType) {
		this.resetType = resetType;
		return this;
	}

	public Integer getResetDayOfMonth() {
		return resetDayOfMonth;
	}

	public void setResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
	}

	public CounterScopeModel withResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
		return this;
	}

	public String getResetDayOfWeek() {
		return resetDayOfWeek;
	}

	public void setResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
	}

	public CounterScopeModel withResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
		return this;
	}

	public Integer getResetHour() {
		return resetHour;
	}

	public void setResetHour(Integer resetHour) {
		this.resetHour = resetHour;
	}

	public CounterScopeModel withResetHour(Integer resetHour) {
		this.resetHour = resetHour;
		return this;
	}

    public static CounterScopeModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CounterScopeModel()
            .withResetType(data.get("resetType") == null || data.get("resetType").isNull() ? null : data.get("resetType").asText())
            .withResetDayOfMonth(data.get("resetDayOfMonth") == null || data.get("resetDayOfMonth").isNull() ? null : data.get("resetDayOfMonth").intValue())
            .withResetDayOfWeek(data.get("resetDayOfWeek") == null || data.get("resetDayOfWeek").isNull() ? null : data.get("resetDayOfWeek").asText())
            .withResetHour(data.get("resetHour") == null || data.get("resetHour").isNull() ? null : data.get("resetHour").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("resetType", getResetType());
                put("resetDayOfMonth", getResetDayOfMonth());
                put("resetDayOfWeek", getResetDayOfWeek());
                put("resetHour", getResetHour());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.resetType == null) ? 0 : this.resetType.hashCode());
        result = prime * result + ((this.resetDayOfMonth == null) ? 0 : this.resetDayOfMonth.hashCode());
        result = prime * result + ((this.resetDayOfWeek == null) ? 0 : this.resetDayOfWeek.hashCode());
        result = prime * result + ((this.resetHour == null) ? 0 : this.resetHour.hashCode());
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
		CounterScopeModel other = (CounterScopeModel) o;
		if (resetType == null) {
			return other.resetType == null;
		} else if (!resetType.equals(other.resetType)) {
			return false;
		}
		if (resetDayOfMonth == null) {
			return other.resetDayOfMonth == null;
		} else if (!resetDayOfMonth.equals(other.resetDayOfMonth)) {
			return false;
		}
		if (resetDayOfWeek == null) {
			return other.resetDayOfWeek == null;
		} else if (!resetDayOfWeek.equals(other.resetDayOfWeek)) {
			return false;
		}
		if (resetHour == null) {
			return other.resetHour == null;
		} else if (!resetHour.equals(other.resetHour)) {
			return false;
		}
		return true;
	}
}