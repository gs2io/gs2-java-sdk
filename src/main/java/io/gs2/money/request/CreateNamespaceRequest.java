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

package io.gs2.money.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.money.model.ScriptSetting;
import io.gs2.money.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private String priority;
    private Boolean shareFree;
    private String currency;
    private String appleKey;
    private String googleKey;
    private Boolean enableFakeReceipt;
    private ScriptSetting createWalletScript;
    private ScriptSetting depositScript;
    private ScriptSetting withdrawScript;
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

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public CreateNamespaceRequest withPriority(String priority) {
		this.priority = priority;
		return this;
	}

	public Boolean getShareFree() {
		return shareFree;
	}

	public void setShareFree(Boolean shareFree) {
		this.shareFree = shareFree;
	}

	public CreateNamespaceRequest withShareFree(Boolean shareFree) {
		this.shareFree = shareFree;
		return this;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public CreateNamespaceRequest withCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public String getAppleKey() {
		return appleKey;
	}

	public void setAppleKey(String appleKey) {
		this.appleKey = appleKey;
	}

	public CreateNamespaceRequest withAppleKey(String appleKey) {
		this.appleKey = appleKey;
		return this;
	}

	public String getGoogleKey() {
		return googleKey;
	}

	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}

	public CreateNamespaceRequest withGoogleKey(String googleKey) {
		this.googleKey = googleKey;
		return this;
	}

	public Boolean getEnableFakeReceipt() {
		return enableFakeReceipt;
	}

	public void setEnableFakeReceipt(Boolean enableFakeReceipt) {
		this.enableFakeReceipt = enableFakeReceipt;
	}

	public CreateNamespaceRequest withEnableFakeReceipt(Boolean enableFakeReceipt) {
		this.enableFakeReceipt = enableFakeReceipt;
		return this;
	}

	public ScriptSetting getCreateWalletScript() {
		return createWalletScript;
	}

	public void setCreateWalletScript(ScriptSetting createWalletScript) {
		this.createWalletScript = createWalletScript;
	}

	public CreateNamespaceRequest withCreateWalletScript(ScriptSetting createWalletScript) {
		this.createWalletScript = createWalletScript;
		return this;
	}

	public ScriptSetting getDepositScript() {
		return depositScript;
	}

	public void setDepositScript(ScriptSetting depositScript) {
		this.depositScript = depositScript;
	}

	public CreateNamespaceRequest withDepositScript(ScriptSetting depositScript) {
		this.depositScript = depositScript;
		return this;
	}

	public ScriptSetting getWithdrawScript() {
		return withdrawScript;
	}

	public void setWithdrawScript(ScriptSetting withdrawScript) {
		this.withdrawScript = withdrawScript;
	}

	public CreateNamespaceRequest withWithdrawScript(ScriptSetting withdrawScript) {
		this.withdrawScript = withdrawScript;
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
            .withPriority(data.get("priority") == null || data.get("priority").isNull() ? null : data.get("priority").asText())
            .withShareFree(data.get("shareFree") == null || data.get("shareFree").isNull() ? null : data.get("shareFree").booleanValue())
            .withCurrency(data.get("currency") == null || data.get("currency").isNull() ? null : data.get("currency").asText())
            .withAppleKey(data.get("appleKey") == null || data.get("appleKey").isNull() ? null : data.get("appleKey").asText())
            .withGoogleKey(data.get("googleKey") == null || data.get("googleKey").isNull() ? null : data.get("googleKey").asText())
            .withEnableFakeReceipt(data.get("enableFakeReceipt") == null || data.get("enableFakeReceipt").isNull() ? null : data.get("enableFakeReceipt").booleanValue())
            .withCreateWalletScript(data.get("createWalletScript") == null || data.get("createWalletScript").isNull() ? null : ScriptSetting.fromJson(data.get("createWalletScript")))
            .withDepositScript(data.get("depositScript") == null || data.get("depositScript").isNull() ? null : ScriptSetting.fromJson(data.get("depositScript")))
            .withWithdrawScript(data.get("withdrawScript") == null || data.get("withdrawScript").isNull() ? null : ScriptSetting.fromJson(data.get("withdrawScript")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("priority", getPriority());
                put("shareFree", getShareFree());
                put("currency", getCurrency());
                put("appleKey", getAppleKey());
                put("googleKey", getGoogleKey());
                put("enableFakeReceipt", getEnableFakeReceipt());
                put("createWalletScript", getCreateWalletScript() != null ? getCreateWalletScript().toJson() : null);
                put("depositScript", getDepositScript() != null ? getDepositScript().toJson() : null);
                put("withdrawScript", getWithdrawScript() != null ? getWithdrawScript().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}