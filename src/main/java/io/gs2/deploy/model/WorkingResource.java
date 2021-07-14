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
public class WorkingResource implements IModel, Serializable, Comparable<WorkingResource> {
	private String resourceId;
	private String context;
	private String type;
	private String name;
	private String request;
	private List<String> after;
	private String rollbackContext;
	private String rollbackRequest;
	private List<String> rollbackAfter;
	private List<OutputField> outputFields;
	private String workId;
	private Long createdAt;
	private Long updatedAt;

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public WorkingResource withResourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public WorkingResource withContext(String context) {
		this.context = context;
		return this;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public WorkingResource withType(String type) {
		this.type = type;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorkingResource withName(String name) {
		this.name = name;
		return this;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public WorkingResource withRequest(String request) {
		this.request = request;
		return this;
	}

	public List<String> getAfter() {
		return after;
	}

	public void setAfter(List<String> after) {
		this.after = after;
	}

	public WorkingResource withAfter(List<String> after) {
		this.after = after;
		return this;
	}

	public String getRollbackContext() {
		return rollbackContext;
	}

	public void setRollbackContext(String rollbackContext) {
		this.rollbackContext = rollbackContext;
	}

	public WorkingResource withRollbackContext(String rollbackContext) {
		this.rollbackContext = rollbackContext;
		return this;
	}

	public String getRollbackRequest() {
		return rollbackRequest;
	}

	public void setRollbackRequest(String rollbackRequest) {
		this.rollbackRequest = rollbackRequest;
	}

	public WorkingResource withRollbackRequest(String rollbackRequest) {
		this.rollbackRequest = rollbackRequest;
		return this;
	}

	public List<String> getRollbackAfter() {
		return rollbackAfter;
	}

	public void setRollbackAfter(List<String> rollbackAfter) {
		this.rollbackAfter = rollbackAfter;
	}

	public WorkingResource withRollbackAfter(List<String> rollbackAfter) {
		this.rollbackAfter = rollbackAfter;
		return this;
	}

	public List<OutputField> getOutputFields() {
		return outputFields;
	}

	public void setOutputFields(List<OutputField> outputFields) {
		this.outputFields = outputFields;
	}

	public WorkingResource withOutputFields(List<OutputField> outputFields) {
		this.outputFields = outputFields;
		return this;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public WorkingResource withWorkId(String workId) {
		this.workId = workId;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public WorkingResource withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public WorkingResource withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static WorkingResource fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new WorkingResource()
            .withResourceId(data.get("resourceId") == null || data.get("resourceId").isNull() ? null : data.get("resourceId").asText())
            .withContext(data.get("context") == null || data.get("context").isNull() ? null : data.get("context").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withRequest(data.get("request") == null || data.get("request").isNull() ? null : data.get("request").asText())
            .withAfter(data.get("after") == null || data.get("after").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("after").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withRollbackContext(data.get("rollbackContext") == null || data.get("rollbackContext").isNull() ? null : data.get("rollbackContext").asText())
            .withRollbackRequest(data.get("rollbackRequest") == null || data.get("rollbackRequest").isNull() ? null : data.get("rollbackRequest").asText())
            .withRollbackAfter(data.get("rollbackAfter") == null || data.get("rollbackAfter").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("rollbackAfter").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withOutputFields(data.get("outputFields") == null || data.get("outputFields").isNull() ? new ArrayList<OutputField>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("outputFields").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return OutputField.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withWorkId(data.get("workId") == null || data.get("workId").isNull() ? null : data.get("workId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("resourceId", getResourceId());
                put("context", getContext());
                put("type", getType());
                put("name", getName());
                put("request", getRequest());
                put("after", getAfter() == null ? new ArrayList<String>() :
                    getAfter().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("rollbackContext", getRollbackContext());
                put("rollbackRequest", getRollbackRequest());
                put("rollbackAfter", getRollbackAfter() == null ? new ArrayList<String>() :
                    getRollbackAfter().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("outputFields", getOutputFields() == null ? new ArrayList<OutputField>() :
                    getOutputFields().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("workId", getWorkId());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(WorkingResource o) {
		return resourceId.compareTo(o.resourceId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.resourceId == null) ? 0 : this.resourceId.hashCode());
        result = prime * result + ((this.context == null) ? 0 : this.context.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.request == null) ? 0 : this.request.hashCode());
        result = prime * result + ((this.after == null) ? 0 : this.after.hashCode());
        result = prime * result + ((this.rollbackContext == null) ? 0 : this.rollbackContext.hashCode());
        result = prime * result + ((this.rollbackRequest == null) ? 0 : this.rollbackRequest.hashCode());
        result = prime * result + ((this.rollbackAfter == null) ? 0 : this.rollbackAfter.hashCode());
        result = prime * result + ((this.outputFields == null) ? 0 : this.outputFields.hashCode());
        result = prime * result + ((this.workId == null) ? 0 : this.workId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		WorkingResource other = (WorkingResource) o;
		if (resourceId == null) {
			return other.resourceId == null;
		} else if (!resourceId.equals(other.resourceId)) {
			return false;
		}
		if (context == null) {
			return other.context == null;
		} else if (!context.equals(other.context)) {
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
		if (after == null) {
			return other.after == null;
		} else if (!after.equals(other.after)) {
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
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}