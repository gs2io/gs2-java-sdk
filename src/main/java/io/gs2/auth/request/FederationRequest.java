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

package io.gs2.auth.request;

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
public class FederationRequest extends Gs2BasicRequest<FederationRequest> {
    private String originalUserId;
    private String userId;
    private String policyDocument;
    private Integer timeOffset;
    private String timeOffsetToken;
	public String getOriginalUserId() {
		return originalUserId;
	}
	public void setOriginalUserId(String originalUserId) {
		this.originalUserId = originalUserId;
	}
	public FederationRequest withOriginalUserId(String originalUserId) {
		this.originalUserId = originalUserId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public FederationRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getPolicyDocument() {
		return policyDocument;
	}
	public void setPolicyDocument(String policyDocument) {
		this.policyDocument = policyDocument;
	}
	public FederationRequest withPolicyDocument(String policyDocument) {
		this.policyDocument = policyDocument;
		return this;
	}
	public Integer getTimeOffset() {
		return timeOffset;
	}
	public void setTimeOffset(Integer timeOffset) {
		this.timeOffset = timeOffset;
	}
	public FederationRequest withTimeOffset(Integer timeOffset) {
		this.timeOffset = timeOffset;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public FederationRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

    public static FederationRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new FederationRequest()
            .withOriginalUserId(data.get("originalUserId") == null || data.get("originalUserId").isNull() ? null : data.get("originalUserId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withPolicyDocument(data.get("policyDocument") == null || data.get("policyDocument").isNull() ? null : data.get("policyDocument").asText())
            .withTimeOffset(data.get("timeOffset") == null || data.get("timeOffset").isNull() ? null : data.get("timeOffset").intValue())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("originalUserId", getOriginalUserId());
                put("userId", getUserId());
                put("policyDocument", getPolicyDocument());
                put("timeOffset", getTimeOffset());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}