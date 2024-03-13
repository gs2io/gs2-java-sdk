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

package io.gs2.exchange.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.exchange.model.TransactionSetting;
import io.gs2.exchange.model.ScriptSetting;
import io.gs2.exchange.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private Boolean enableAwaitExchange;
    private Boolean enableDirectExchange;
    private TransactionSetting transactionSetting;
    private ScriptSetting exchangeScript;
    private ScriptSetting incrementalExchangeScript;
    private LogSetting logSetting;
    private String queueNamespaceId;
    private String keyId;
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
	public Boolean getEnableAwaitExchange() {
		return enableAwaitExchange;
	}
	public void setEnableAwaitExchange(Boolean enableAwaitExchange) {
		this.enableAwaitExchange = enableAwaitExchange;
	}
	public CreateNamespaceRequest withEnableAwaitExchange(Boolean enableAwaitExchange) {
		this.enableAwaitExchange = enableAwaitExchange;
		return this;
	}
	public Boolean getEnableDirectExchange() {
		return enableDirectExchange;
	}
	public void setEnableDirectExchange(Boolean enableDirectExchange) {
		this.enableDirectExchange = enableDirectExchange;
	}
	public CreateNamespaceRequest withEnableDirectExchange(Boolean enableDirectExchange) {
		this.enableDirectExchange = enableDirectExchange;
		return this;
	}
	public TransactionSetting getTransactionSetting() {
		return transactionSetting;
	}
	public void setTransactionSetting(TransactionSetting transactionSetting) {
		this.transactionSetting = transactionSetting;
	}
	public CreateNamespaceRequest withTransactionSetting(TransactionSetting transactionSetting) {
		this.transactionSetting = transactionSetting;
		return this;
	}
	public ScriptSetting getExchangeScript() {
		return exchangeScript;
	}
	public void setExchangeScript(ScriptSetting exchangeScript) {
		this.exchangeScript = exchangeScript;
	}
	public CreateNamespaceRequest withExchangeScript(ScriptSetting exchangeScript) {
		this.exchangeScript = exchangeScript;
		return this;
	}
	public ScriptSetting getIncrementalExchangeScript() {
		return incrementalExchangeScript;
	}
	public void setIncrementalExchangeScript(ScriptSetting incrementalExchangeScript) {
		this.incrementalExchangeScript = incrementalExchangeScript;
	}
	public CreateNamespaceRequest withIncrementalExchangeScript(ScriptSetting incrementalExchangeScript) {
		this.incrementalExchangeScript = incrementalExchangeScript;
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
    @Deprecated
	public String getQueueNamespaceId() {
		return queueNamespaceId;
	}
    @Deprecated
	public void setQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
	}
    @Deprecated
	public CreateNamespaceRequest withQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
		return this;
	}
    @Deprecated
	public String getKeyId() {
		return keyId;
	}
    @Deprecated
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
    @Deprecated
	public CreateNamespaceRequest withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}

    public static CreateNamespaceRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateNamespaceRequest()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withEnableAwaitExchange(data.get("enableAwaitExchange") == null || data.get("enableAwaitExchange").isNull() ? null : data.get("enableAwaitExchange").booleanValue())
            .withEnableDirectExchange(data.get("enableDirectExchange") == null || data.get("enableDirectExchange").isNull() ? null : data.get("enableDirectExchange").booleanValue())
            .withTransactionSetting(data.get("transactionSetting") == null || data.get("transactionSetting").isNull() ? null : TransactionSetting.fromJson(data.get("transactionSetting")))
            .withExchangeScript(data.get("exchangeScript") == null || data.get("exchangeScript").isNull() ? null : ScriptSetting.fromJson(data.get("exchangeScript")))
            .withIncrementalExchangeScript(data.get("incrementalExchangeScript") == null || data.get("incrementalExchangeScript").isNull() ? null : ScriptSetting.fromJson(data.get("incrementalExchangeScript")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")))
            .withQueueNamespaceId(data.get("queueNamespaceId") == null || data.get("queueNamespaceId").isNull() ? null : data.get("queueNamespaceId").asText())
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("enableAwaitExchange", getEnableAwaitExchange());
                put("enableDirectExchange", getEnableDirectExchange());
                put("transactionSetting", getTransactionSetting() != null ? getTransactionSetting().toJson() : null);
                put("exchangeScript", getExchangeScript() != null ? getExchangeScript().toJson() : null);
                put("incrementalExchangeScript", getIncrementalExchangeScript() != null ? getIncrementalExchangeScript().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
                put("queueNamespaceId", getQueueNamespaceId());
                put("keyId", getKeyId());
            }}
        );
    }
}