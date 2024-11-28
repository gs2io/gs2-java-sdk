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

package io.gs2.core.model;

import java.util.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AcquireActionResult implements IModel, Serializable {
	private String action;
	private String acquireRequest;
	private Integer statusCode;
	private String acquireResult;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public AcquireActionResult withAction(String action) {
		this.action = action;
		return this;
	}
	public String getAcquireRequest() {
		return acquireRequest;
	}
	public void setAcquireRequest(String acquireRequest) {
		this.acquireRequest = acquireRequest;
	}
	public AcquireActionResult withAcquireRequest(String acquireRequest) {
		this.acquireRequest = acquireRequest;
		return this;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public AcquireActionResult withStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	public String getAcquireResult() {
		return acquireResult;
	}
	public void setAcquireResult(String acquireResult) {
		this.acquireResult = acquireResult;
	}
	public AcquireActionResult withAcquireResult(String acquireResult) {
		this.acquireResult = acquireResult;
		return this;
	}

    public static AcquireActionResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AcquireActionResult()
            .withAction(data.get("action") == null || data.get("action").isNull() ? null : data.get("action").asText())
            .withAcquireRequest(data.get("acquireRequest") == null || data.get("acquireRequest").isNull() ? null : data.get("acquireRequest").asText())
            .withStatusCode(data.get("statusCode") == null || data.get("statusCode").isNull() ? null : data.get("statusCode").intValue())
            .withAcquireResult(data.get("acquireResult") == null || data.get("acquireResult").isNull() ? null : data.get("acquireResult").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("action", getAction());
                put("acquireRequest", getAcquireRequest());
                put("statusCode", getStatusCode());
                put("acquireResult", getAcquireResult());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.action == null) ? 0 : this.action.hashCode());
        result = prime * result + ((this.acquireRequest == null) ? 0 : this.acquireRequest.hashCode());
        result = prime * result + ((this.statusCode == null) ? 0 : this.statusCode.hashCode());
        result = prime * result + ((this.acquireResult == null) ? 0 : this.acquireResult.hashCode());
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
		AcquireActionResult other = (AcquireActionResult) o;
		if (action == null) {
			return other.action == null;
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (acquireRequest == null) {
			return other.acquireRequest == null;
		} else if (!acquireRequest.equals(other.acquireRequest)) {
			return false;
		}
		if (statusCode == null) {
			return other.statusCode == null;
		} else if (!statusCode.equals(other.statusCode)) {
			return false;
		}
		if (acquireResult == null) {
			return other.acquireResult == null;
		} else if (!acquireResult.equals(other.acquireResult)) {
			return false;
		}
		return true;
	}
}