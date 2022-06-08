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

package io.gs2.key.request;

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
public class GetGitHubApiKeyRequest extends Gs2BasicRequest<GetGitHubApiKeyRequest> {
    private String namespaceName;
    private String apiKeyName;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetGitHubApiKeyRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getApiKeyName() {
		return apiKeyName;
	}
	public void setApiKeyName(String apiKeyName) {
		this.apiKeyName = apiKeyName;
	}
	public GetGitHubApiKeyRequest withApiKeyName(String apiKeyName) {
		this.apiKeyName = apiKeyName;
		return this;
	}

    public static GetGitHubApiKeyRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetGitHubApiKeyRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withApiKeyName(data.get("apiKeyName") == null || data.get("apiKeyName").isNull() ? null : data.get("apiKeyName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("apiKeyName", getApiKeyName());
            }}
        );
    }
}