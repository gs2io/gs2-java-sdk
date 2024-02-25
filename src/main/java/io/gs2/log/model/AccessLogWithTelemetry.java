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
public class AccessLogWithTelemetry implements IModel, Serializable {
	private Long timestamp;
	private String sourceRequestId;
	private String requestId;
	private Long duration;
	private String service;
	private String method;
	private String userId;
	private String request;
	private String result;
	private String status;
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public AccessLogWithTelemetry withTimestamp(Long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	public String getSourceRequestId() {
		return sourceRequestId;
	}
	public void setSourceRequestId(String sourceRequestId) {
		this.sourceRequestId = sourceRequestId;
	}
	public AccessLogWithTelemetry withSourceRequestId(String sourceRequestId) {
		this.sourceRequestId = sourceRequestId;
		return this;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public AccessLogWithTelemetry withRequestId(String requestId) {
		this.requestId = requestId;
		return this;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public AccessLogWithTelemetry withDuration(Long duration) {
		this.duration = duration;
		return this;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public AccessLogWithTelemetry withService(String service) {
		this.service = service;
		return this;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public AccessLogWithTelemetry withMethod(String method) {
		this.method = method;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public AccessLogWithTelemetry withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public AccessLogWithTelemetry withRequest(String request) {
		this.request = request;
		return this;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public AccessLogWithTelemetry withResult(String result) {
		this.result = result;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AccessLogWithTelemetry withStatus(String status) {
		this.status = status;
		return this;
	}

    public static AccessLogWithTelemetry fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AccessLogWithTelemetry()
            .withTimestamp(data.get("timestamp") == null || data.get("timestamp").isNull() ? null : data.get("timestamp").longValue())
            .withSourceRequestId(data.get("sourceRequestId") == null || data.get("sourceRequestId").isNull() ? null : data.get("sourceRequestId").asText())
            .withRequestId(data.get("requestId") == null || data.get("requestId").isNull() ? null : data.get("requestId").asText())
            .withDuration(data.get("duration") == null || data.get("duration").isNull() ? null : data.get("duration").longValue())
            .withService(data.get("service") == null || data.get("service").isNull() ? null : data.get("service").asText())
            .withMethod(data.get("method") == null || data.get("method").isNull() ? null : data.get("method").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withRequest(data.get("request") == null || data.get("request").isNull() ? null : data.get("request").asText())
            .withResult(data.get("result") == null || data.get("result").isNull() ? null : data.get("result").asText())
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : data.get("status").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("timestamp", getTimestamp());
                put("sourceRequestId", getSourceRequestId());
                put("requestId", getRequestId());
                put("duration", getDuration());
                put("service", getService());
                put("method", getMethod());
                put("userId", getUserId());
                put("request", getRequest());
                put("result", getResult());
                put("status", getStatus());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
        result = prime * result + ((this.sourceRequestId == null) ? 0 : this.sourceRequestId.hashCode());
        result = prime * result + ((this.requestId == null) ? 0 : this.requestId.hashCode());
        result = prime * result + ((this.duration == null) ? 0 : this.duration.hashCode());
        result = prime * result + ((this.service == null) ? 0 : this.service.hashCode());
        result = prime * result + ((this.method == null) ? 0 : this.method.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.request == null) ? 0 : this.request.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
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
		AccessLogWithTelemetry other = (AccessLogWithTelemetry) o;
		if (timestamp == null) {
			return other.timestamp == null;
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		if (sourceRequestId == null) {
			return other.sourceRequestId == null;
		} else if (!sourceRequestId.equals(other.sourceRequestId)) {
			return false;
		}
		if (requestId == null) {
			return other.requestId == null;
		} else if (!requestId.equals(other.requestId)) {
			return false;
		}
		if (duration == null) {
			return other.duration == null;
		} else if (!duration.equals(other.duration)) {
			return false;
		}
		if (service == null) {
			return other.service == null;
		} else if (!service.equals(other.service)) {
			return false;
		}
		if (method == null) {
			return other.method == null;
		} else if (!method.equals(other.method)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (request == null) {
			return other.request == null;
		} else if (!request.equals(other.request)) {
			return false;
		}
		if (result == null) {
			return other.result == null;
		} else if (!result.equals(other.result)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}
}