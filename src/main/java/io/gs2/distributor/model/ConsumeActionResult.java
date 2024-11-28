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
public class ConsumeActionResult implements IModel, Serializable {
	private String action;
	private String consumeRequest;
	private Integer statusCode;
	private String consumeResult;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public ConsumeActionResult withAction(String action) {
		this.action = action;
		return this;
	}
	public String getConsumeRequest() {
		return consumeRequest;
	}
	public void setConsumeRequest(String consumeRequest) {
		this.consumeRequest = consumeRequest;
	}
	public ConsumeActionResult withConsumeRequest(String consumeRequest) {
		this.consumeRequest = consumeRequest;
		return this;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public ConsumeActionResult withStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	public String getConsumeResult() {
		return consumeResult;
	}
	public void setConsumeResult(String consumeResult) {
		this.consumeResult = consumeResult;
	}
	public ConsumeActionResult withConsumeResult(String consumeResult) {
		this.consumeResult = consumeResult;
		return this;
	}

    public static ConsumeActionResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ConsumeActionResult()
            .withAction(data.get("action") == null || data.get("action").isNull() ? null : data.get("action").asText())
            .withConsumeRequest(data.get("consumeRequest") == null || data.get("consumeRequest").isNull() ? null : data.get("consumeRequest").asText())
            .withStatusCode(data.get("statusCode") == null || data.get("statusCode").isNull() ? null : data.get("statusCode").intValue())
            .withConsumeResult(data.get("consumeResult") == null || data.get("consumeResult").isNull() ? null : data.get("consumeResult").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("action", getAction());
                put("consumeRequest", getConsumeRequest());
                put("statusCode", getStatusCode());
                put("consumeResult", getConsumeResult());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.action == null) ? 0 : this.action.hashCode());
        result = prime * result + ((this.consumeRequest == null) ? 0 : this.consumeRequest.hashCode());
        result = prime * result + ((this.statusCode == null) ? 0 : this.statusCode.hashCode());
        result = prime * result + ((this.consumeResult == null) ? 0 : this.consumeResult.hashCode());
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
		ConsumeActionResult other = (ConsumeActionResult) o;
		if (action == null) {
			return other.action == null;
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (consumeRequest == null) {
			return other.consumeRequest == null;
		} else if (!consumeRequest.equals(other.consumeRequest)) {
			return false;
		}
		if (statusCode == null) {
			return other.statusCode == null;
		} else if (!statusCode.equals(other.statusCode)) {
			return false;
		}
		if (consumeResult == null) {
			return other.consumeResult == null;
		} else if (!consumeResult.equals(other.consumeResult)) {
			return false;
		}
		return true;
	}
}