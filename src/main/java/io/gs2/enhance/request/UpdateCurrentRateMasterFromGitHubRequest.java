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

package io.gs2.enhance.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.enhance.model.GitHubCheckoutSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateCurrentRateMasterFromGitHubRequest extends Gs2BasicRequest<UpdateCurrentRateMasterFromGitHubRequest> {
    private String namespaceName;
    private GitHubCheckoutSetting checkoutSetting;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public UpdateCurrentRateMasterFromGitHubRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public GitHubCheckoutSetting getCheckoutSetting() {
		return checkoutSetting;
	}

	public void setCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
		this.checkoutSetting = checkoutSetting;
	}

	public UpdateCurrentRateMasterFromGitHubRequest withCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
		this.checkoutSetting = checkoutSetting;
		return this;
	}

    public static UpdateCurrentRateMasterFromGitHubRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateCurrentRateMasterFromGitHubRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withCheckoutSetting(data.get("checkoutSetting") == null || data.get("checkoutSetting").isNull() ? null : GitHubCheckoutSetting.fromJson(data.get("checkoutSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("checkoutSetting", getCheckoutSetting() != null ? getCheckoutSetting().toJson() : null);
            }}
        );
    }
}