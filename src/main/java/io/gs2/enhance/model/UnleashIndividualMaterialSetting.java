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

package io.gs2.enhance.model;

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
public class UnleashIndividualMaterialSetting implements IModel, Serializable {
	private String matchType;
	private String gradeCondition;
	private Long gradeValue;
	private Integer count;
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	public UnleashIndividualMaterialSetting withMatchType(String matchType) {
		this.matchType = matchType;
		return this;
	}
	public String getGradeCondition() {
		return gradeCondition;
	}
	public void setGradeCondition(String gradeCondition) {
		this.gradeCondition = gradeCondition;
	}
	public UnleashIndividualMaterialSetting withGradeCondition(String gradeCondition) {
		this.gradeCondition = gradeCondition;
		return this;
	}
	public Long getGradeValue() {
		return gradeValue;
	}
	public void setGradeValue(Long gradeValue) {
		this.gradeValue = gradeValue;
	}
	public UnleashIndividualMaterialSetting withGradeValue(Long gradeValue) {
		this.gradeValue = gradeValue;
		return this;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public UnleashIndividualMaterialSetting withCount(Integer count) {
		this.count = count;
		return this;
	}

    public static UnleashIndividualMaterialSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UnleashIndividualMaterialSetting()
            .withMatchType(data.get("matchType") == null || data.get("matchType").isNull() ? null : data.get("matchType").asText())
            .withGradeCondition(data.get("gradeCondition") == null || data.get("gradeCondition").isNull() ? null : data.get("gradeCondition").asText())
            .withGradeValue(data.get("gradeValue") == null || data.get("gradeValue").isNull() ? null : data.get("gradeValue").longValue())
            .withCount(data.get("count") == null || data.get("count").isNull() ? null : data.get("count").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("matchType", getMatchType());
                put("gradeCondition", getGradeCondition());
                put("gradeValue", getGradeValue());
                put("count", getCount());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.matchType == null) ? 0 : this.matchType.hashCode());
        result = prime * result + ((this.gradeCondition == null) ? 0 : this.gradeCondition.hashCode());
        result = prime * result + ((this.gradeValue == null) ? 0 : this.gradeValue.hashCode());
        result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
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
		UnleashIndividualMaterialSetting other = (UnleashIndividualMaterialSetting) o;
		if (matchType == null) {
			return other.matchType == null;
		} else if (!matchType.equals(other.matchType)) {
			return false;
		}
		if (gradeCondition == null) {
			return other.gradeCondition == null;
		} else if (!gradeCondition.equals(other.gradeCondition)) {
			return false;
		}
		if (gradeValue == null) {
			return other.gradeValue == null;
		} else if (!gradeValue.equals(other.gradeValue)) {
			return false;
		}
		if (count == null) {
			return other.count == null;
		} else if (!count.equals(other.count)) {
			return false;
		}
		return true;
	}
}