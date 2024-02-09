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

package io.gs2.distributor.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.distributor.model.*;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RunStampSheetWithoutNamespaceResult implements IResult, Serializable {
    private Integer statusCode;
    private String result;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public RunStampSheetWithoutNamespaceResult withStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public RunStampSheetWithoutNamespaceResult withResult(String result) {
		this.result = result;
		return this;
	}

    public static RunStampSheetWithoutNamespaceResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RunStampSheetWithoutNamespaceResult()
            .withStatusCode(data.get("statusCode") == null || data.get("statusCode").isNull() ? null : data.get("statusCode").intValue())
            .withResult(data.get("result") == null || data.get("result").isNull() ? null : data.get("result").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("statusCode", getStatusCode());
                put("result", getResult());
            }}
        );
    }
}