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
	private String scopeType;
	private String resetType;
	private Integer resetDayOfMonth;
	private String resetDayOfWeek;
	private Integer resetHour;
	private String conditionName;
	private VerifyAction condition;
	private Long anchorTimestamp;
	private Integer days;
	public String getScopeType() {
		return scopeType;
	}
	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}
	public CounterScopeModel withScopeType(String scopeType) {
		this.scopeType = scopeType;
		return this;
	}
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
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	public CounterScopeModel withConditionName(String conditionName) {
		this.conditionName = conditionName;
		return this;
	}
	public VerifyAction getCondition() {
		return condition;
	}
	public void setCondition(VerifyAction condition) {
		this.condition = condition;
	}
	public CounterScopeModel withCondition(VerifyAction condition) {
		this.condition = condition;
		return this;
	}
	public Long getAnchorTimestamp() {
		return anchorTimestamp;
	}
	public void setAnchorTimestamp(Long anchorTimestamp) {
		this.anchorTimestamp = anchorTimestamp;
	}
	public CounterScopeModel withAnchorTimestamp(Long anchorTimestamp) {
		this.anchorTimestamp = anchorTimestamp;
		return this;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public CounterScopeModel withDays(Integer days) {
		this.days = days;
		return this;
	}

    public static CounterScopeModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CounterScopeModel()
            .withScopeType(data.get("scopeType") == null || data.get("scopeType").isNull() ? null : data.get("scopeType").asText())
            .withResetType(data.get("resetType") == null || data.get("resetType").isNull() ? null : data.get("resetType").asText())
            .withResetDayOfMonth(data.get("resetDayOfMonth") == null || data.get("resetDayOfMonth").isNull() ? null : data.get("resetDayOfMonth").intValue())
            .withResetDayOfWeek(data.get("resetDayOfWeek") == null || data.get("resetDayOfWeek").isNull() ? null : data.get("resetDayOfWeek").asText())
            .withResetHour(data.get("resetHour") == null || data.get("resetHour").isNull() ? null : data.get("resetHour").intValue())
            .withConditionName(data.get("conditionName") == null || data.get("conditionName").isNull() ? null : data.get("conditionName").asText())
            .withCondition(data.get("condition") == null || data.get("condition").isNull() ? null : VerifyAction.fromJson(data.get("condition")))
            .withAnchorTimestamp(data.get("anchorTimestamp") == null || data.get("anchorTimestamp").isNull() ? null : data.get("anchorTimestamp").longValue())
            .withDays(data.get("days") == null || data.get("days").isNull() ? null : data.get("days").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("scopeType", getScopeType());
                put("resetType", getResetType());
                put("resetDayOfMonth", getResetDayOfMonth());
                put("resetDayOfWeek", getResetDayOfWeek());
                put("resetHour", getResetHour());
                put("conditionName", getConditionName());
                put("condition", getCondition() != null ? getCondition().toJson() : null);
                put("anchorTimestamp", getAnchorTimestamp());
                put("days", getDays());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.scopeType == null) ? 0 : this.scopeType.hashCode());
        result = prime * result + ((this.resetType == null) ? 0 : this.resetType.hashCode());
        result = prime * result + ((this.resetDayOfMonth == null) ? 0 : this.resetDayOfMonth.hashCode());
        result = prime * result + ((this.resetDayOfWeek == null) ? 0 : this.resetDayOfWeek.hashCode());
        result = prime * result + ((this.resetHour == null) ? 0 : this.resetHour.hashCode());
        result = prime * result + ((this.conditionName == null) ? 0 : this.conditionName.hashCode());
        result = prime * result + ((this.condition == null) ? 0 : this.condition.hashCode());
        result = prime * result + ((this.anchorTimestamp == null) ? 0 : this.anchorTimestamp.hashCode());
        result = prime * result + ((this.days == null) ? 0 : this.days.hashCode());
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
		if (scopeType == null) {
			return other.scopeType == null;
		} else if (!scopeType.equals(other.scopeType)) {
			return false;
		}
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
		if (conditionName == null) {
			return other.conditionName == null;
		} else if (!conditionName.equals(other.conditionName)) {
			return false;
		}
		if (condition == null) {
			return other.condition == null;
		} else if (!condition.equals(other.condition)) {
			return false;
		}
		if (anchorTimestamp == null) {
			return other.anchorTimestamp == null;
		} else if (!anchorTimestamp.equals(other.anchorTimestamp)) {
			return false;
		}
		if (days == null) {
			return other.days == null;
		} else if (!days.equals(other.days)) {
			return false;
		}
		return true;
	}
}