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
public class Resource implements IModel, Serializable, Comparable<Resource> {
	private String resourceId;
	private String type;
	private String name;
	private String request;
	private String response;
	private String rollbackContext;
	private String rollbackRequest;
	private List<String> rollbackAfter;
	private List<OutputField> outputFields;
	private String workId;
	private Long createdAt;
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public Resource withResourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Resource withType(String type) {
		this.type = type;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Resource withName(String name) {
		this.name = name;
		return this;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public Resource withRequest(String request) {
		this.request = request;
		return this;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Resource withResponse(String response) {
		this.response = response;
		return this;
	}
	public String getRollbackContext() {
		return rollbackContext;
	}
	public void setRollbackContext(String rollbackContext) {
		this.rollbackContext = rollbackContext;
	}
	public Resource withRollbackContext(String rollbackContext) {
		this.rollbackContext = rollbackContext;
		return this;
	}
	public String getRollbackRequest() {
		return rollbackRequest;
	}
	public void setRollbackRequest(String rollbackRequest) {
		this.rollbackRequest = rollbackRequest;
	}
	public Resource withRollbackRequest(String rollbackRequest) {
		this.rollbackRequest = rollbackRequest;
		return this;
	}
	public List<String> getRollbackAfter() {
		return rollbackAfter;
	}
	public void setRollbackAfter(List<String> rollbackAfter) {
		this.rollbackAfter = rollbackAfter;
	}
	public Resource withRollbackAfter(List<String> rollbackAfter) {
		this.rollbackAfter = rollbackAfter;
		return this;
	}
	public List<OutputField> getOutputFields() {
		return outputFields;
	}
	public void setOutputFields(List<OutputField> outputFields) {
		this.outputFields = outputFields;
	}
	public Resource withOutputFields(List<OutputField> outputFields) {
		this.outputFields = outputFields;
		return this;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public Resource withWorkId(String workId) {
		this.workId = workId;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Resource withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static Resource fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Resource()
            .withResourceId(data.get("resourceId") == null || data.get("resourceId").isNull() ? null : data.get("resourceId").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withRequest(data.get("request") == null || data.get("request").isNull() ? null : data.get("request").asText())
            .withResponse(data.get("response") == null || data.get("response").isNull() ? null : data.get("response").asText())
            .withRollbackContext(data.get("rollbackContext") == null || data.get("rollbackContext").isNull() ? null : data.get("rollbackContext").asText())
            .withRollbackRequest(data.get("rollbackRequest") == null || data.get("rollbackRequest").isNull() ? null : data.get("rollbackRequest").asText())
            .withRollbackAfter(data.get("rollbackAfter") == null || data.get("rollbackAfter").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("rollbackAfter").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withOutputFields(data.get("outputFields") == null || data.get("outputFields").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("outputFields").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return OutputField.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withWorkId(data.get("workId") == null || data.get("workId").isNull() ? null : data.get("workId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("resourceId", getResourceId());
                put("type", getType());
                put("name", getName());
                put("request", getRequest());
                put("response", getResponse());
                put("rollbackContext", getRollbackContext());
                put("rollbackRequest", getRollbackRequest());
                put("rollbackAfter", getRollbackAfter() == null ? null :
                    getRollbackAfter().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("outputFields", getOutputFields() == null ? null :
                    getOutputFields().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("workId", getWorkId());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Resource o) {
		return resourceId.compareTo(o.resourceId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.resourceId == null) ? 0 : this.resourceId.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.request == null) ? 0 : this.request.hashCode());
        result = prime * result + ((this.response == null) ? 0 : this.response.hashCode());
        result = prime * result + ((this.rollbackContext == null) ? 0 : this.rollbackContext.hashCode());
        result = prime * result + ((this.rollbackRequest == null) ? 0 : this.rollbackRequest.hashCode());
        result = prime * result + ((this.rollbackAfter == null) ? 0 : this.rollbackAfter.hashCode());
        result = prime * result + ((this.outputFields == null) ? 0 : this.outputFields.hashCode());
        result = prime * result + ((this.workId == null) ? 0 : this.workId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		Resource other = (Resource) o;
		if (resourceId == null) {
			return other.resourceId == null;
		} else if (!resourceId.equals(other.resourceId)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (request == null) {
			return other.request == null;
		} else if (!request.equals(other.request)) {
			return false;
		}
		if (response == null) {
			return other.response == null;
		} else if (!response.equals(other.response)) {
			return false;
		}
		if (rollbackContext == null) {
			return other.rollbackContext == null;
		} else if (!rollbackContext.equals(other.rollbackContext)) {
			return false;
		}
		if (rollbackRequest == null) {
			return other.rollbackRequest == null;
		} else if (!rollbackRequest.equals(other.rollbackRequest)) {
			return false;
		}
		if (rollbackAfter == null) {
			return other.rollbackAfter == null;
		} else if (!rollbackAfter.equals(other.rollbackAfter)) {
			return false;
		}
		if (outputFields == null) {
			return other.outputFields == null;
		} else if (!outputFields.equals(other.outputFields)) {
			return false;
		}
		if (workId == null) {
			return other.workId == null;
		} else if (!workId.equals(other.workId)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		return true;
	}
}