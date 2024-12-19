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

package io.gs2.distributor.model;

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
public class BatchRequestPayload implements IModel, Serializable {
	private String requestId;
	private String service;
	private String methodName;
	private String parameter;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public BatchRequestPayload withRequestId(String requestId) {
		this.requestId = requestId;
		return this;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public BatchRequestPayload withService(String service) {
		this.service = service;
		return this;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public BatchRequestPayload withMethodName(String methodName) {
		this.methodName = methodName;
		return this;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public BatchRequestPayload withParameter(String parameter) {
		this.parameter = parameter;
		return this;
	}

    public static BatchRequestPayload fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BatchRequestPayload()
            .withRequestId(data.get("requestId") == null || data.get("requestId").isNull() ? null : data.get("requestId").asText())
            .withService(data.get("service") == null || data.get("service").isNull() ? null : data.get("service").asText())
            .withMethodName(data.get("methodName") == null || data.get("methodName").isNull() ? null : data.get("methodName").asText())
            .withParameter(data.get("parameter") == null || data.get("parameter").isNull() ? null : data.get("parameter").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("requestId", getRequestId());
                put("service", getService());
                put("methodName", getMethodName());
                put("parameter", getParameter());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.requestId == null) ? 0 : this.requestId.hashCode());
        result = prime * result + ((this.service == null) ? 0 : this.service.hashCode());
        result = prime * result + ((this.methodName == null) ? 0 : this.methodName.hashCode());
        result = prime * result + ((this.parameter == null) ? 0 : this.parameter.hashCode());
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
		BatchRequestPayload other = (BatchRequestPayload) o;
		if (requestId == null) {
			return other.requestId == null;
		} else if (!requestId.equals(other.requestId)) {
			return false;
		}
		if (service == null) {
			return other.service == null;
		} else if (!service.equals(other.service)) {
			return false;
		}
		if (methodName == null) {
			return other.methodName == null;
		} else if (!methodName.equals(other.methodName)) {
			return false;
		}
		if (parameter == null) {
			return other.parameter == null;
		} else if (!parameter.equals(other.parameter)) {
			return false;
		}
		return true;
	}
}