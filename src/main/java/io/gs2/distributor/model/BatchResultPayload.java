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
public class BatchResultPayload implements IModel, Serializable {
	private String requestId;
	private Integer statusCode;
	private String resultPayload;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public BatchResultPayload withRequestId(String requestId) {
		this.requestId = requestId;
		return this;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public BatchResultPayload withStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	public String getResultPayload() {
		return resultPayload;
	}
	public void setResultPayload(String resultPayload) {
		this.resultPayload = resultPayload;
	}
	public BatchResultPayload withResultPayload(String resultPayload) {
		this.resultPayload = resultPayload;
		return this;
	}

    public static BatchResultPayload fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BatchResultPayload()
            .withRequestId(data.get("requestId") == null || data.get("requestId").isNull() ? null : data.get("requestId").asText())
            .withStatusCode(data.get("statusCode") == null || data.get("statusCode").isNull() ? null : data.get("statusCode").intValue())
            .withResultPayload(data.get("resultPayload") == null || data.get("resultPayload").isNull() ? null : data.get("resultPayload").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("requestId", getRequestId());
                put("statusCode", getStatusCode());
                put("resultPayload", getResultPayload());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.requestId == null) ? 0 : this.requestId.hashCode());
        result = prime * result + ((this.statusCode == null) ? 0 : this.statusCode.hashCode());
        result = prime * result + ((this.resultPayload == null) ? 0 : this.resultPayload.hashCode());
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
		BatchResultPayload other = (BatchResultPayload) o;
		if (requestId == null) {
			return other.requestId == null;
		} else if (!requestId.equals(other.requestId)) {
			return false;
		}
		if (statusCode == null) {
			return other.statusCode == null;
		} else if (!statusCode.equals(other.statusCode)) {
			return false;
		}
		if (resultPayload == null) {
			return other.resultPayload == null;
		} else if (!resultPayload.equals(other.resultPayload)) {
			return false;
		}
		return true;
	}
}