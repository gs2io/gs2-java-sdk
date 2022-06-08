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
public class CreateGitHubApiKeyRequest extends Gs2BasicRequest<CreateGitHubApiKeyRequest> {
    private String namespaceName;
    private String name;
    private String description;
    private String apiKey;
    private String encryptionKeyName;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateGitHubApiKeyRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateGitHubApiKeyRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateGitHubApiKeyRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public CreateGitHubApiKeyRequest withApiKey(String apiKey) {
		this.apiKey = apiKey;
		return this;
	}
	public String getEncryptionKeyName() {
		return encryptionKeyName;
	}
	public void setEncryptionKeyName(String encryptionKeyName) {
		this.encryptionKeyName = encryptionKeyName;
	}
	public CreateGitHubApiKeyRequest withEncryptionKeyName(String encryptionKeyName) {
		this.encryptionKeyName = encryptionKeyName;
		return this;
	}

    public static CreateGitHubApiKeyRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateGitHubApiKeyRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withApiKey(data.get("apiKey") == null || data.get("apiKey").isNull() ? null : data.get("apiKey").asText())
            .withEncryptionKeyName(data.get("encryptionKeyName") == null || data.get("encryptionKeyName").isNull() ? null : data.get("encryptionKeyName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("name", getName());
                put("description", getDescription());
                put("apiKey", getApiKey());
                put("encryptionKeyName", getEncryptionKeyName());
            }}
        );
    }
}