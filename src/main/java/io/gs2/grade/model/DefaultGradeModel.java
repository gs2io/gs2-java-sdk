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

package io.gs2.grade.model;

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
public class DefaultGradeModel implements IModel, Serializable {
	private String propertyIdRegex;
	private Long defaultGradeValue;
	public String getPropertyIdRegex() {
		return propertyIdRegex;
	}
	public void setPropertyIdRegex(String propertyIdRegex) {
		this.propertyIdRegex = propertyIdRegex;
	}
	public DefaultGradeModel withPropertyIdRegex(String propertyIdRegex) {
		this.propertyIdRegex = propertyIdRegex;
		return this;
	}
	public Long getDefaultGradeValue() {
		return defaultGradeValue;
	}
	public void setDefaultGradeValue(Long defaultGradeValue) {
		this.defaultGradeValue = defaultGradeValue;
	}
	public DefaultGradeModel withDefaultGradeValue(Long defaultGradeValue) {
		this.defaultGradeValue = defaultGradeValue;
		return this;
	}

    public static DefaultGradeModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DefaultGradeModel()
            .withPropertyIdRegex(data.get("propertyIdRegex") == null || data.get("propertyIdRegex").isNull() ? null : data.get("propertyIdRegex").asText())
            .withDefaultGradeValue(data.get("defaultGradeValue") == null || data.get("defaultGradeValue").isNull() ? null : data.get("defaultGradeValue").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("propertyIdRegex", getPropertyIdRegex());
                put("defaultGradeValue", getDefaultGradeValue());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.propertyIdRegex == null) ? 0 : this.propertyIdRegex.hashCode());
        result = prime * result + ((this.defaultGradeValue == null) ? 0 : this.defaultGradeValue.hashCode());
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
		DefaultGradeModel other = (DefaultGradeModel) o;
		if (propertyIdRegex == null) {
			return other.propertyIdRegex == null;
		} else if (!propertyIdRegex.equals(other.propertyIdRegex)) {
			return false;
		}
		if (defaultGradeValue == null) {
			return other.defaultGradeValue == null;
		} else if (!defaultGradeValue.equals(other.defaultGradeValue)) {
			return false;
		}
		return true;
	}
}