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

package io.gs2.project.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetImportErrorLogRequest extends Gs2BasicRequest<GetImportErrorLogRequest> {
    private String transactionId;
    private String errorLogName;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public GetImportErrorLogRequest withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}
	public String getErrorLogName() {
		return errorLogName;
	}
	public void setErrorLogName(String errorLogName) {
		this.errorLogName = errorLogName;
	}
	public GetImportErrorLogRequest withErrorLogName(String errorLogName) {
		this.errorLogName = errorLogName;
		return this;
	}

    public static GetImportErrorLogRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetImportErrorLogRequest()
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withErrorLogName(data.get("errorLogName") == null || data.get("errorLogName").isNull() ? null : data.get("errorLogName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("transactionId", getTransactionId());
                put("errorLogName", getErrorLogName());
            }}
        );
    }
}