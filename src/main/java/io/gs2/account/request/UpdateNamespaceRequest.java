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
import io.gs2.account.model.TransactionSetting;
import io.gs2.account.model.ScriptSetting;
import io.gs2.account.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {
    private String namespaceName;
    private String description;
    private TransactionSetting transactionSetting;
    private Boolean changePasswordIfTakeOver;
    private ScriptSetting createAccountScript;
    private ScriptSetting authenticationScript;
    private ScriptSetting createTakeOverScript;
    private ScriptSetting doTakeOverScript;
    private ScriptSetting banScript;
    private ScriptSetting unBanScript;
    private LogSetting logSetting;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateNamespaceRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateNamespaceRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public TransactionSetting getTransactionSetting() {
		return transactionSetting;
	}
	public void setTransactionSetting(TransactionSetting transactionSetting) {
		this.transactionSetting = transactionSetting;
	}
	public UpdateNamespaceRequest withTransactionSetting(TransactionSetting transactionSetting) {
		this.transactionSetting = transactionSetting;
		return this;
	}
	public Boolean getChangePasswordIfTakeOver() {
		return changePasswordIfTakeOver;
	}
	public void setChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
		this.changePasswordIfTakeOver = changePasswordIfTakeOver;
	}
	public UpdateNamespaceRequest withChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
		this.changePasswordIfTakeOver = changePasswordIfTakeOver;
		return this;
	}
	public ScriptSetting getCreateAccountScript() {
		return createAccountScript;
	}
	public void setCreateAccountScript(ScriptSetting createAccountScript) {
		this.createAccountScript = createAccountScript;
	}
	public UpdateNamespaceRequest withCreateAccountScript(ScriptSetting createAccountScript) {
		this.createAccountScript = createAccountScript;
		return this;
	}
	public ScriptSetting getAuthenticationScript() {
		return authenticationScript;
	}
	public void setAuthenticationScript(ScriptSetting authenticationScript) {
		this.authenticationScript = authenticationScript;
	}
	public UpdateNamespaceRequest withAuthenticationScript(ScriptSetting authenticationScript) {
		this.authenticationScript = authenticationScript;
		return this;
	}
	public ScriptSetting getCreateTakeOverScript() {
		return createTakeOverScript;
	}
	public void setCreateTakeOverScript(ScriptSetting createTakeOverScript) {
		this.createTakeOverScript = createTakeOverScript;
	}
	public UpdateNamespaceRequest withCreateTakeOverScript(ScriptSetting createTakeOverScript) {
		this.createTakeOverScript = createTakeOverScript;
		return this;
	}
	public ScriptSetting getDoTakeOverScript() {
		return doTakeOverScript;
	}
	public void setDoTakeOverScript(ScriptSetting doTakeOverScript) {
		this.doTakeOverScript = doTakeOverScript;
	}
	public UpdateNamespaceRequest withDoTakeOverScript(ScriptSetting doTakeOverScript) {
		this.doTakeOverScript = doTakeOverScript;
		return this;
	}
	public ScriptSetting getBanScript() {
		return banScript;
	}
	public void setBanScript(ScriptSetting banScript) {
		this.banScript = banScript;
	}
	public UpdateNamespaceRequest withBanScript(ScriptSetting banScript) {
		this.banScript = banScript;
		return this;
	}
	public ScriptSetting getUnBanScript() {
		return unBanScript;
	}
	public void setUnBanScript(ScriptSetting unBanScript) {
		this.unBanScript = unBanScript;
	}
	public UpdateNamespaceRequest withUnBanScript(ScriptSetting unBanScript) {
		this.unBanScript = unBanScript;
		return this;
	}
	public LogSetting getLogSetting() {
		return logSetting;
	}
	public void setLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
	}
	public UpdateNamespaceRequest withLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
		return this;
	}

    public static UpdateNamespaceRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateNamespaceRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withTransactionSetting(data.get("transactionSetting") == null || data.get("transactionSetting").isNull() ? null : TransactionSetting.fromJson(data.get("transactionSetting")))
            .withChangePasswordIfTakeOver(data.get("changePasswordIfTakeOver") == null || data.get("changePasswordIfTakeOver").isNull() ? null : data.get("changePasswordIfTakeOver").booleanValue())
            .withCreateAccountScript(data.get("createAccountScript") == null || data.get("createAccountScript").isNull() ? null : ScriptSetting.fromJson(data.get("createAccountScript")))
            .withAuthenticationScript(data.get("authenticationScript") == null || data.get("authenticationScript").isNull() ? null : ScriptSetting.fromJson(data.get("authenticationScript")))
            .withCreateTakeOverScript(data.get("createTakeOverScript") == null || data.get("createTakeOverScript").isNull() ? null : ScriptSetting.fromJson(data.get("createTakeOverScript")))
            .withDoTakeOverScript(data.get("doTakeOverScript") == null || data.get("doTakeOverScript").isNull() ? null : ScriptSetting.fromJson(data.get("doTakeOverScript")))
            .withBanScript(data.get("banScript") == null || data.get("banScript").isNull() ? null : ScriptSetting.fromJson(data.get("banScript")))
            .withUnBanScript(data.get("unBanScript") == null || data.get("unBanScript").isNull() ? null : ScriptSetting.fromJson(data.get("unBanScript")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("description", getDescription());
                put("transactionSetting", getTransactionSetting() != null ? getTransactionSetting().toJson() : null);
                put("changePasswordIfTakeOver", getChangePasswordIfTakeOver());
                put("createAccountScript", getCreateAccountScript() != null ? getCreateAccountScript().toJson() : null);
                put("authenticationScript", getAuthenticationScript() != null ? getAuthenticationScript().toJson() : null);
                put("createTakeOverScript", getCreateTakeOverScript() != null ? getCreateTakeOverScript().toJson() : null);
                put("doTakeOverScript", getDoTakeOverScript() != null ? getDoTakeOverScript().toJson() : null);
                put("banScript", getBanScript() != null ? getBanScript().toJson() : null);
                put("unBanScript", getUnBanScript() != null ? getUnBanScript().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}