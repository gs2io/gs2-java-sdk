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
import io.gs2.account.model.ScriptSetting;
import io.gs2.account.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private Boolean changePasswordIfTakeOver;
    private Boolean differentUserIdForLoginAndDataRetention;
    private ScriptSetting createAccountScript;
    private ScriptSetting authenticationScript;
    private ScriptSetting createTakeOverScript;
    private ScriptSetting doTakeOverScript;
    private LogSetting logSetting;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateNamespaceRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateNamespaceRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public Boolean getChangePasswordIfTakeOver() {
		return changePasswordIfTakeOver;
	}
	public void setChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
		this.changePasswordIfTakeOver = changePasswordIfTakeOver;
	}
	public CreateNamespaceRequest withChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
		this.changePasswordIfTakeOver = changePasswordIfTakeOver;
		return this;
	}
	public Boolean getDifferentUserIdForLoginAndDataRetention() {
		return differentUserIdForLoginAndDataRetention;
	}
	public void setDifferentUserIdForLoginAndDataRetention(Boolean differentUserIdForLoginAndDataRetention) {
		this.differentUserIdForLoginAndDataRetention = differentUserIdForLoginAndDataRetention;
	}
	public CreateNamespaceRequest withDifferentUserIdForLoginAndDataRetention(Boolean differentUserIdForLoginAndDataRetention) {
		this.differentUserIdForLoginAndDataRetention = differentUserIdForLoginAndDataRetention;
		return this;
	}
	public ScriptSetting getCreateAccountScript() {
		return createAccountScript;
	}
	public void setCreateAccountScript(ScriptSetting createAccountScript) {
		this.createAccountScript = createAccountScript;
	}
	public CreateNamespaceRequest withCreateAccountScript(ScriptSetting createAccountScript) {
		this.createAccountScript = createAccountScript;
		return this;
	}
	public ScriptSetting getAuthenticationScript() {
		return authenticationScript;
	}
	public void setAuthenticationScript(ScriptSetting authenticationScript) {
		this.authenticationScript = authenticationScript;
	}
	public CreateNamespaceRequest withAuthenticationScript(ScriptSetting authenticationScript) {
		this.authenticationScript = authenticationScript;
		return this;
	}
	public ScriptSetting getCreateTakeOverScript() {
		return createTakeOverScript;
	}
	public void setCreateTakeOverScript(ScriptSetting createTakeOverScript) {
		this.createTakeOverScript = createTakeOverScript;
	}
	public CreateNamespaceRequest withCreateTakeOverScript(ScriptSetting createTakeOverScript) {
		this.createTakeOverScript = createTakeOverScript;
		return this;
	}
	public ScriptSetting getDoTakeOverScript() {
		return doTakeOverScript;
	}
	public void setDoTakeOverScript(ScriptSetting doTakeOverScript) {
		this.doTakeOverScript = doTakeOverScript;
	}
	public CreateNamespaceRequest withDoTakeOverScript(ScriptSetting doTakeOverScript) {
		this.doTakeOverScript = doTakeOverScript;
		return this;
	}
	public LogSetting getLogSetting() {
		return logSetting;
	}
	public void setLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
	}
	public CreateNamespaceRequest withLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
		return this;
	}

    public static CreateNamespaceRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateNamespaceRequest()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withChangePasswordIfTakeOver(data.get("changePasswordIfTakeOver") == null || data.get("changePasswordIfTakeOver").isNull() ? null : data.get("changePasswordIfTakeOver").booleanValue())
            .withDifferentUserIdForLoginAndDataRetention(data.get("differentUserIdForLoginAndDataRetention") == null || data.get("differentUserIdForLoginAndDataRetention").isNull() ? null : data.get("differentUserIdForLoginAndDataRetention").booleanValue())
            .withCreateAccountScript(data.get("createAccountScript") == null || data.get("createAccountScript").isNull() ? null : ScriptSetting.fromJson(data.get("createAccountScript")))
            .withAuthenticationScript(data.get("authenticationScript") == null || data.get("authenticationScript").isNull() ? null : ScriptSetting.fromJson(data.get("authenticationScript")))
            .withCreateTakeOverScript(data.get("createTakeOverScript") == null || data.get("createTakeOverScript").isNull() ? null : ScriptSetting.fromJson(data.get("createTakeOverScript")))
            .withDoTakeOverScript(data.get("doTakeOverScript") == null || data.get("doTakeOverScript").isNull() ? null : ScriptSetting.fromJson(data.get("doTakeOverScript")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("changePasswordIfTakeOver", getChangePasswordIfTakeOver());
                put("differentUserIdForLoginAndDataRetention", getDifferentUserIdForLoginAndDataRetention());
                put("createAccountScript", getCreateAccountScript() != null ? getCreateAccountScript().toJson() : null);
                put("authenticationScript", getAuthenticationScript() != null ? getAuthenticationScript().toJson() : null);
                put("createTakeOverScript", getCreateTakeOverScript() != null ? getCreateTakeOverScript().toJson() : null);
                put("doTakeOverScript", getDoTakeOverScript() != null ? getDoTakeOverScript().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}