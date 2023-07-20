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

package io.gs2.enchant.model;

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
public class RarityParameterValue implements IModel, Serializable {
	private String name;
	private String resourceName;
	private Long resourceValue;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RarityParameterValue withName(String name) {
		this.name = name;
		return this;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public RarityParameterValue withResourceName(String resourceName) {
		this.resourceName = resourceName;
		return this;
	}
	public Long getResourceValue() {
		return resourceValue;
	}
	public void setResourceValue(Long resourceValue) {
		this.resourceValue = resourceValue;
	}
	public RarityParameterValue withResourceValue(Long resourceValue) {
		this.resourceValue = resourceValue;
		return this;
	}

    public static RarityParameterValue fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RarityParameterValue()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withResourceName(data.get("resourceName") == null || data.get("resourceName").isNull() ? null : data.get("resourceName").asText())
            .withResourceValue(data.get("resourceValue") == null || data.get("resourceValue").isNull() ? null : data.get("resourceValue").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("resourceName", getResourceName());
                put("resourceValue", getResourceValue());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.resourceName == null) ? 0 : this.resourceName.hashCode());
        result = prime * result + ((this.resourceValue == null) ? 0 : this.resourceValue.hashCode());
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
		RarityParameterValue other = (RarityParameterValue) o;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (resourceName == null) {
			return other.resourceName == null;
		} else if (!resourceName.equals(other.resourceName)) {
			return false;
		}
		if (resourceValue == null) {
			return other.resourceValue == null;
		} else if (!resourceValue.equals(other.resourceValue)) {
			return false;
		}
		return true;
	}
}