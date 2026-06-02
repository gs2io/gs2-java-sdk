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

package io.gs2.log.model;

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
public class AggregationConfig implements IModel, Serializable {
	private String type;
	private String field;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public AggregationConfig withType(String type) {
		this.type = type;
		return this;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public AggregationConfig withField(String field) {
		this.field = field;
		return this;
	}

    public static AggregationConfig fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AggregationConfig()
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withField(data.get("field") == null || data.get("field").isNull() ? null : data.get("field").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("type", getType());
                put("field", getField());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.field == null) ? 0 : this.field.hashCode());
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
		AggregationConfig other = (AggregationConfig) o;
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (field == null) {
			return other.field == null;
		} else if (!field.equals(other.field)) {
			return false;
		}
		return true;
	}
}