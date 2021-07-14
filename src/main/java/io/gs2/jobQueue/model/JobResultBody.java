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

package io.gs2.jobQueue.model;

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
public class JobResultBody implements IModel, Serializable {
	private Integer tryNumber;
	private Integer statusCode;
	private String result;
	private Long tryAt;

	public Integer getTryNumber() {
		return tryNumber;
	}

	public void setTryNumber(Integer tryNumber) {
		this.tryNumber = tryNumber;
	}

	public JobResultBody withTryNumber(Integer tryNumber) {
		this.tryNumber = tryNumber;
		return this;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public JobResultBody withStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public JobResultBody withResult(String result) {
		this.result = result;
		return this;
	}

	public Long getTryAt() {
		return tryAt;
	}

	public void setTryAt(Long tryAt) {
		this.tryAt = tryAt;
	}

	public JobResultBody withTryAt(Long tryAt) {
		this.tryAt = tryAt;
		return this;
	}

    public static JobResultBody fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new JobResultBody()
            .withTryNumber(data.get("tryNumber") == null || data.get("tryNumber").isNull() ? null : data.get("tryNumber").intValue())
            .withStatusCode(data.get("statusCode") == null || data.get("statusCode").isNull() ? null : data.get("statusCode").intValue())
            .withResult(data.get("result") == null || data.get("result").isNull() ? null : data.get("result").asText())
            .withTryAt(data.get("tryAt") == null || data.get("tryAt").isNull() ? null : data.get("tryAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("tryNumber", getTryNumber());
                put("statusCode", getStatusCode());
                put("result", getResult());
                put("tryAt", getTryAt());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.tryNumber == null) ? 0 : this.tryNumber.hashCode());
        result = prime * result + ((this.statusCode == null) ? 0 : this.statusCode.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
        result = prime * result + ((this.tryAt == null) ? 0 : this.tryAt.hashCode());
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
		JobResultBody other = (JobResultBody) o;
		if (tryNumber == null) {
			return other.tryNumber == null;
		} else if (!tryNumber.equals(other.tryNumber)) {
			return false;
		}
		if (statusCode == null) {
			return other.statusCode == null;
		} else if (!statusCode.equals(other.statusCode)) {
			return false;
		}
		if (result == null) {
			return other.result == null;
		} else if (!result.equals(other.result)) {
			return false;
		}
		if (tryAt == null) {
			return other.tryAt == null;
		} else if (!tryAt.equals(other.tryAt)) {
			return false;
		}
		return true;
	}
}