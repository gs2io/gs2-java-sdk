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

package io.gs2.deploy.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.deploy.model.GitHubCheckoutSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateStackFromGitHubRequest extends Gs2BasicRequest<CreateStackFromGitHubRequest> {
    private String name;
    private String description;
    private GitHubCheckoutSetting checkoutSetting;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CreateStackFromGitHubRequest withName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CreateStackFromGitHubRequest withDescription(String description) {
		this.description = description;
		return this;
	}

	public GitHubCheckoutSetting getCheckoutSetting() {
		return checkoutSetting;
	}

	public void setCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
		this.checkoutSetting = checkoutSetting;
	}

	public CreateStackFromGitHubRequest withCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
		this.checkoutSetting = checkoutSetting;
		return this;
	}

    public static CreateStackFromGitHubRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateStackFromGitHubRequest()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withCheckoutSetting(data.get("checkoutSetting") == null || data.get("checkoutSetting").isNull() ? null : GitHubCheckoutSetting.fromJson(data.get("checkoutSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("checkoutSetting", getCheckoutSetting() != null ? getCheckoutSetting().toJson() : null);
            }}
        );
    }
}