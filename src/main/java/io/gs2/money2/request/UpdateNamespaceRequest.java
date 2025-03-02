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

package io.gs2.money2.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.money2.model.AppleAppStoreSetting;
import io.gs2.money2.model.GooglePlaySetting;
import io.gs2.money2.model.FakeSetting;
import io.gs2.money2.model.PlatformSetting;
import io.gs2.money2.model.ScriptSetting;
import io.gs2.money2.model.NotificationSetting;
import io.gs2.money2.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {
    private String namespaceName;
    private String currencyUsagePriority;
    private String description;
    private PlatformSetting platformSetting;
    private ScriptSetting depositBalanceScript;
    private ScriptSetting withdrawBalanceScript;
    private String subscribeScript;
    private String renewScript;
    private String unsubscribeScript;
    private ScriptSetting takeOverScript;
    private NotificationSetting changeSubscriptionStatusNotification;
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
	public String getCurrencyUsagePriority() {
		return currencyUsagePriority;
	}
	public void setCurrencyUsagePriority(String currencyUsagePriority) {
		this.currencyUsagePriority = currencyUsagePriority;
	}
	public UpdateNamespaceRequest withCurrencyUsagePriority(String currencyUsagePriority) {
		this.currencyUsagePriority = currencyUsagePriority;
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
	public PlatformSetting getPlatformSetting() {
		return platformSetting;
	}
	public void setPlatformSetting(PlatformSetting platformSetting) {
		this.platformSetting = platformSetting;
	}
	public UpdateNamespaceRequest withPlatformSetting(PlatformSetting platformSetting) {
		this.platformSetting = platformSetting;
		return this;
	}
	public ScriptSetting getDepositBalanceScript() {
		return depositBalanceScript;
	}
	public void setDepositBalanceScript(ScriptSetting depositBalanceScript) {
		this.depositBalanceScript = depositBalanceScript;
	}
	public UpdateNamespaceRequest withDepositBalanceScript(ScriptSetting depositBalanceScript) {
		this.depositBalanceScript = depositBalanceScript;
		return this;
	}
	public ScriptSetting getWithdrawBalanceScript() {
		return withdrawBalanceScript;
	}
	public void setWithdrawBalanceScript(ScriptSetting withdrawBalanceScript) {
		this.withdrawBalanceScript = withdrawBalanceScript;
	}
	public UpdateNamespaceRequest withWithdrawBalanceScript(ScriptSetting withdrawBalanceScript) {
		this.withdrawBalanceScript = withdrawBalanceScript;
		return this;
	}
	public String getSubscribeScript() {
		return subscribeScript;
	}
	public void setSubscribeScript(String subscribeScript) {
		this.subscribeScript = subscribeScript;
	}
	public UpdateNamespaceRequest withSubscribeScript(String subscribeScript) {
		this.subscribeScript = subscribeScript;
		return this;
	}
	public String getRenewScript() {
		return renewScript;
	}
	public void setRenewScript(String renewScript) {
		this.renewScript = renewScript;
	}
	public UpdateNamespaceRequest withRenewScript(String renewScript) {
		this.renewScript = renewScript;
		return this;
	}
	public String getUnsubscribeScript() {
		return unsubscribeScript;
	}
	public void setUnsubscribeScript(String unsubscribeScript) {
		this.unsubscribeScript = unsubscribeScript;
	}
	public UpdateNamespaceRequest withUnsubscribeScript(String unsubscribeScript) {
		this.unsubscribeScript = unsubscribeScript;
		return this;
	}
	public ScriptSetting getTakeOverScript() {
		return takeOverScript;
	}
	public void setTakeOverScript(ScriptSetting takeOverScript) {
		this.takeOverScript = takeOverScript;
	}
	public UpdateNamespaceRequest withTakeOverScript(ScriptSetting takeOverScript) {
		this.takeOverScript = takeOverScript;
		return this;
	}
	public NotificationSetting getChangeSubscriptionStatusNotification() {
		return changeSubscriptionStatusNotification;
	}
	public void setChangeSubscriptionStatusNotification(NotificationSetting changeSubscriptionStatusNotification) {
		this.changeSubscriptionStatusNotification = changeSubscriptionStatusNotification;
	}
	public UpdateNamespaceRequest withChangeSubscriptionStatusNotification(NotificationSetting changeSubscriptionStatusNotification) {
		this.changeSubscriptionStatusNotification = changeSubscriptionStatusNotification;
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
            .withCurrencyUsagePriority(data.get("currencyUsagePriority") == null || data.get("currencyUsagePriority").isNull() ? null : data.get("currencyUsagePriority").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withPlatformSetting(data.get("platformSetting") == null || data.get("platformSetting").isNull() ? null : PlatformSetting.fromJson(data.get("platformSetting")))
            .withDepositBalanceScript(data.get("depositBalanceScript") == null || data.get("depositBalanceScript").isNull() ? null : ScriptSetting.fromJson(data.get("depositBalanceScript")))
            .withWithdrawBalanceScript(data.get("withdrawBalanceScript") == null || data.get("withdrawBalanceScript").isNull() ? null : ScriptSetting.fromJson(data.get("withdrawBalanceScript")))
            .withSubscribeScript(data.get("subscribeScript") == null || data.get("subscribeScript").isNull() ? null : data.get("subscribeScript").asText())
            .withRenewScript(data.get("renewScript") == null || data.get("renewScript").isNull() ? null : data.get("renewScript").asText())
            .withUnsubscribeScript(data.get("unsubscribeScript") == null || data.get("unsubscribeScript").isNull() ? null : data.get("unsubscribeScript").asText())
            .withTakeOverScript(data.get("takeOverScript") == null || data.get("takeOverScript").isNull() ? null : ScriptSetting.fromJson(data.get("takeOverScript")))
            .withChangeSubscriptionStatusNotification(data.get("changeSubscriptionStatusNotification") == null || data.get("changeSubscriptionStatusNotification").isNull() ? null : NotificationSetting.fromJson(data.get("changeSubscriptionStatusNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("currencyUsagePriority", getCurrencyUsagePriority());
                put("description", getDescription());
                put("platformSetting", getPlatformSetting() != null ? getPlatformSetting().toJson() : null);
                put("depositBalanceScript", getDepositBalanceScript() != null ? getDepositBalanceScript().toJson() : null);
                put("withdrawBalanceScript", getWithdrawBalanceScript() != null ? getWithdrawBalanceScript().toJson() : null);
                put("subscribeScript", getSubscribeScript());
                put("renewScript", getRenewScript());
                put("unsubscribeScript", getUnsubscribeScript());
                put("takeOverScript", getTakeOverScript() != null ? getTakeOverScript().toJson() : null);
                put("changeSubscriptionStatusNotification", getChangeSubscriptionStatusNotification() != null ? getChangeSubscriptionStatusNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}