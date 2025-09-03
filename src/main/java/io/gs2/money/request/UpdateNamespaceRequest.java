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
import io.gs2.money.model.TransactionSetting;
import io.gs2.money.model.ScriptSetting;
import io.gs2.money.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {
    private String namespaceName;
    private String description;
    private TransactionSetting transactionSetting;
    private String priority;
    private String appleKey;
    private String googleKey;
    private Boolean enableFakeReceipt;
    private ScriptSetting createWalletScript;
    private ScriptSetting depositScript;
    private ScriptSetting withdrawScript;
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
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public UpdateNamespaceRequest withPriority(String priority) {
		this.priority = priority;
		return this;
	}
	public String getAppleKey() {
		return appleKey;
	}
	public void setAppleKey(String appleKey) {
		this.appleKey = appleKey;
	}
	public UpdateNamespaceRequest withAppleKey(String appleKey) {
		this.appleKey = appleKey;
		return this;
	}
	public String getGoogleKey() {
		return googleKey;
	}
	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}
	public UpdateNamespaceRequest withGoogleKey(String googleKey) {
		this.googleKey = googleKey;
		return this;
	}
	public Boolean getEnableFakeReceipt() {
		return enableFakeReceipt;
	}
	public void setEnableFakeReceipt(Boolean enableFakeReceipt) {
		this.enableFakeReceipt = enableFakeReceipt;
	}
	public UpdateNamespaceRequest withEnableFakeReceipt(Boolean enableFakeReceipt) {
		this.enableFakeReceipt = enableFakeReceipt;
		return this;
	}
	public ScriptSetting getCreateWalletScript() {
		return createWalletScript;
	}
	public void setCreateWalletScript(ScriptSetting createWalletScript) {
		this.createWalletScript = createWalletScript;
	}
	public UpdateNamespaceRequest withCreateWalletScript(ScriptSetting createWalletScript) {
		this.createWalletScript = createWalletScript;
		return this;
	}
	public ScriptSetting getDepositScript() {
		return depositScript;
	}
	public void setDepositScript(ScriptSetting depositScript) {
		this.depositScript = depositScript;
	}
	public UpdateNamespaceRequest withDepositScript(ScriptSetting depositScript) {
		this.depositScript = depositScript;
		return this;
	}
	public ScriptSetting getWithdrawScript() {
		return withdrawScript;
	}
	public void setWithdrawScript(ScriptSetting withdrawScript) {
		this.withdrawScript = withdrawScript;
	}
	public UpdateNamespaceRequest withWithdrawScript(ScriptSetting withdrawScript) {
		this.withdrawScript = withdrawScript;
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
            .withPriority(data.get("priority") == null || data.get("priority").isNull() ? null : data.get("priority").asText())
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
                put("namespaceName", getNamespaceName());
                put("description", getDescription());
                put("transactionSetting", getTransactionSetting() != null ? getTransactionSetting().toJson() : null);
                put("priority", getPriority());
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