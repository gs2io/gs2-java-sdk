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
public class VerifyActionResult implements IModel, Serializable {
	private String action;
	private String verifyRequest;
	private Integer statusCode;
	private String verifyResult;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public VerifyActionResult withAction(String action) {
		this.action = action;
		return this;
	}
	public String getVerifyRequest() {
		return verifyRequest;
	}
	public void setVerifyRequest(String verifyRequest) {
		this.verifyRequest = verifyRequest;
	}
	public VerifyActionResult withVerifyRequest(String verifyRequest) {
		this.verifyRequest = verifyRequest;
		return this;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public VerifyActionResult withStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	public String getVerifyResult() {
		return verifyResult;
	}
	public void setVerifyResult(String verifyResult) {
		this.verifyResult = verifyResult;
	}
	public VerifyActionResult withVerifyResult(String verifyResult) {
		this.verifyResult = verifyResult;
		return this;
	}

    public static VerifyActionResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VerifyActionResult()
            .withAction(data.get("action") == null || data.get("action").isNull() ? null : data.get("action").asText())
            .withVerifyRequest(data.get("verifyRequest") == null || data.get("verifyRequest").isNull() ? null : data.get("verifyRequest").asText())
            .withStatusCode(data.get("statusCode") == null || data.get("statusCode").isNull() ? null : data.get("statusCode").intValue())
            .withVerifyResult(data.get("verifyResult") == null || data.get("verifyResult").isNull() ? null : data.get("verifyResult").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("action", getAction());
                put("verifyRequest", getVerifyRequest());
                put("statusCode", getStatusCode());
                put("verifyResult", getVerifyResult());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.action == null) ? 0 : this.action.hashCode());
        result = prime * result + ((this.verifyRequest == null) ? 0 : this.verifyRequest.hashCode());
        result = prime * result + ((this.statusCode == null) ? 0 : this.statusCode.hashCode());
        result = prime * result + ((this.verifyResult == null) ? 0 : this.verifyResult.hashCode());
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
		VerifyActionResult other = (VerifyActionResult) o;
		if (action == null) {
			return other.action == null;
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (verifyRequest == null) {
			return other.verifyRequest == null;
		} else if (!verifyRequest.equals(other.verifyRequest)) {
			return false;
		}
		if (statusCode == null) {
			return other.statusCode == null;
		} else if (!statusCode.equals(other.statusCode)) {
			return false;
		}
		if (verifyResult == null) {
			return other.verifyResult == null;
		} else if (!verifyResult.equals(other.verifyResult)) {
			return false;
		}
		return true;
	}
}