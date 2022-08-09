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

package io.gs2.log.request;

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
public class PutLogRequest extends Gs2BasicRequest<PutLogRequest> {
    private String loggingNamespaceId;
    private String logCategory;
    private String payload;
	public String getLoggingNamespaceId() {
		return loggingNamespaceId;
	}
	public void setLoggingNamespaceId(String loggingNamespaceId) {
		this.loggingNamespaceId = loggingNamespaceId;
	}
	public PutLogRequest withLoggingNamespaceId(String loggingNamespaceId) {
		this.loggingNamespaceId = loggingNamespaceId;
		return this;
	}
	public String getLogCategory() {
		return logCategory;
	}
	public void setLogCategory(String logCategory) {
		this.logCategory = logCategory;
	}
	public PutLogRequest withLogCategory(String logCategory) {
		this.logCategory = logCategory;
		return this;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public PutLogRequest withPayload(String payload) {
		this.payload = payload;
		return this;
	}

    public static PutLogRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PutLogRequest()
            .withLoggingNamespaceId(data.get("loggingNamespaceId") == null || data.get("loggingNamespaceId").isNull() ? null : data.get("loggingNamespaceId").asText())
            .withLogCategory(data.get("logCategory") == null || data.get("logCategory").isNull() ? null : data.get("logCategory").asText())
            .withPayload(data.get("payload") == null || data.get("payload").isNull() ? null : data.get("payload").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("loggingNamespaceId", getLoggingNamespaceId());
                put("logCategory", getLogCategory());
                put("payload", getPayload());
            }}
        );
    }
}