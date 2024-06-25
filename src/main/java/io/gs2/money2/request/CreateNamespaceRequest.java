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
import io.gs2.money2.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String currencyUsagePriority;
    private String description;
    private Boolean sharedFreeCurrency;
    private PlatformSetting platformSetting;
    private ScriptSetting changeBalanceScript;
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
	public String getCurrencyUsagePriority() {
		return currencyUsagePriority;
	}
	public void setCurrencyUsagePriority(String currencyUsagePriority) {
		this.currencyUsagePriority = currencyUsagePriority;
	}
	public CreateNamespaceRequest withCurrencyUsagePriority(String currencyUsagePriority) {
		this.currencyUsagePriority = currencyUsagePriority;
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
	public Boolean getSharedFreeCurrency() {
		return sharedFreeCurrency;
	}
	public void setSharedFreeCurrency(Boolean sharedFreeCurrency) {
		this.sharedFreeCurrency = sharedFreeCurrency;
	}
	public CreateNamespaceRequest withSharedFreeCurrency(Boolean sharedFreeCurrency) {
		this.sharedFreeCurrency = sharedFreeCurrency;
		return this;
	}
	public PlatformSetting getPlatformSetting() {
		return platformSetting;
	}
	public void setPlatformSetting(PlatformSetting platformSetting) {
		this.platformSetting = platformSetting;
	}
	public CreateNamespaceRequest withPlatformSetting(PlatformSetting platformSetting) {
		this.platformSetting = platformSetting;
		return this;
	}
	public ScriptSetting getChangeBalanceScript() {
		return changeBalanceScript;
	}
	public void setChangeBalanceScript(ScriptSetting changeBalanceScript) {
		this.changeBalanceScript = changeBalanceScript;
	}
	public CreateNamespaceRequest withChangeBalanceScript(ScriptSetting changeBalanceScript) {
		this.changeBalanceScript = changeBalanceScript;
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
            .withCurrencyUsagePriority(data.get("currencyUsagePriority") == null || data.get("currencyUsagePriority").isNull() ? null : data.get("currencyUsagePriority").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withSharedFreeCurrency(data.get("sharedFreeCurrency") == null || data.get("sharedFreeCurrency").isNull() ? null : data.get("sharedFreeCurrency").booleanValue())
            .withPlatformSetting(data.get("platformSetting") == null || data.get("platformSetting").isNull() ? null : PlatformSetting.fromJson(data.get("platformSetting")))
            .withChangeBalanceScript(data.get("changeBalanceScript") == null || data.get("changeBalanceScript").isNull() ? null : ScriptSetting.fromJson(data.get("changeBalanceScript")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("currencyUsagePriority", getCurrencyUsagePriority());
                put("description", getDescription());
                put("sharedFreeCurrency", getSharedFreeCurrency());
                put("platformSetting", getPlatformSetting() != null ? getPlatformSetting().toJson() : null);
                put("changeBalanceScript", getChangeBalanceScript() != null ? getChangeBalanceScript().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}