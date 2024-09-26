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

package io.gs2.script.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.script.model.GitHubCheckoutSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateScriptFromGitHubRequest extends Gs2BasicRequest<UpdateScriptFromGitHubRequest> {
    private String namespaceName;
    private String scriptName;
    private String description;
    private GitHubCheckoutSetting checkoutSetting;
    private Boolean disableStringNumberToNumber;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateScriptFromGitHubRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getScriptName() {
		return scriptName;
	}
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}
	public UpdateScriptFromGitHubRequest withScriptName(String scriptName) {
		this.scriptName = scriptName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateScriptFromGitHubRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public GitHubCheckoutSetting getCheckoutSetting() {
		return checkoutSetting;
	}
	public void setCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
		this.checkoutSetting = checkoutSetting;
	}
	public UpdateScriptFromGitHubRequest withCheckoutSetting(GitHubCheckoutSetting checkoutSetting) {
		this.checkoutSetting = checkoutSetting;
		return this;
	}
	public Boolean getDisableStringNumberToNumber() {
		return disableStringNumberToNumber;
	}
	public void setDisableStringNumberToNumber(Boolean disableStringNumberToNumber) {
		this.disableStringNumberToNumber = disableStringNumberToNumber;
	}
	public UpdateScriptFromGitHubRequest withDisableStringNumberToNumber(Boolean disableStringNumberToNumber) {
		this.disableStringNumberToNumber = disableStringNumberToNumber;
		return this;
	}

    public static UpdateScriptFromGitHubRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateScriptFromGitHubRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withScriptName(data.get("scriptName") == null || data.get("scriptName").isNull() ? null : data.get("scriptName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withCheckoutSetting(data.get("checkoutSetting") == null || data.get("checkoutSetting").isNull() ? null : GitHubCheckoutSetting.fromJson(data.get("checkoutSetting")))
            .withDisableStringNumberToNumber(data.get("disableStringNumberToNumber") == null || data.get("disableStringNumberToNumber").isNull() ? null : data.get("disableStringNumberToNumber").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("scriptName", getScriptName());
                put("description", getDescription());
                put("checkoutSetting", getCheckoutSetting() != null ? getCheckoutSetting().toJson() : null);
                put("disableStringNumberToNumber", getDisableStringNumberToNumber());
            }}
        );
    }
}