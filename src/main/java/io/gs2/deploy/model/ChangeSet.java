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

package io.gs2.deploy.model;

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
public class ChangeSet implements IModel, Serializable {
	private String resourceName;
	private String resourceType;
	private String operation;
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public ChangeSet withResourceName(String resourceName) {
		this.resourceName = resourceName;
		return this;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public ChangeSet withResourceType(String resourceType) {
		this.resourceType = resourceType;
		return this;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public ChangeSet withOperation(String operation) {
		this.operation = operation;
		return this;
	}

    public static ChangeSet fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ChangeSet()
            .withResourceName(data.get("resourceName") == null || data.get("resourceName").isNull() ? null : data.get("resourceName").asText())
            .withResourceType(data.get("resourceType") == null || data.get("resourceType").isNull() ? null : data.get("resourceType").asText())
            .withOperation(data.get("operation") == null || data.get("operation").isNull() ? null : data.get("operation").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("resourceName", getResourceName());
                put("resourceType", getResourceType());
                put("operation", getOperation());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.resourceName == null) ? 0 : this.resourceName.hashCode());
        result = prime * result + ((this.resourceType == null) ? 0 : this.resourceType.hashCode());
        result = prime * result + ((this.operation == null) ? 0 : this.operation.hashCode());
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
		ChangeSet other = (ChangeSet) o;
		if (resourceName == null) {
			return other.resourceName == null;
		} else if (!resourceName.equals(other.resourceName)) {
			return false;
		}
		if (resourceType == null) {
			return other.resourceType == null;
		} else if (!resourceType.equals(other.resourceType)) {
			return false;
		}
		if (operation == null) {
			return other.operation == null;
		} else if (!operation.equals(other.operation)) {
			return false;
		}
		return true;
	}
}