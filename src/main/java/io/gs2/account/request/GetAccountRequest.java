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

package io.gs2.account.request;

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
public class GetAccountRequest extends Gs2BasicRequest<GetAccountRequest> {
    private String namespaceName;
    private String userId;
    private Boolean includeLastAuthenticatedAt;
    private String timeOffsetToken;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetAccountRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public GetAccountRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Boolean getIncludeLastAuthenticatedAt() {
		return includeLastAuthenticatedAt;
	}
	public void setIncludeLastAuthenticatedAt(Boolean includeLastAuthenticatedAt) {
		this.includeLastAuthenticatedAt = includeLastAuthenticatedAt;
	}
	public GetAccountRequest withIncludeLastAuthenticatedAt(Boolean includeLastAuthenticatedAt) {
		this.includeLastAuthenticatedAt = includeLastAuthenticatedAt;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public GetAccountRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

    public static GetAccountRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetAccountRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withIncludeLastAuthenticatedAt(data.get("includeLastAuthenticatedAt") == null || data.get("includeLastAuthenticatedAt").isNull() ? null : data.get("includeLastAuthenticatedAt").booleanValue())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("includeLastAuthenticatedAt", getIncludeLastAuthenticatedAt());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}