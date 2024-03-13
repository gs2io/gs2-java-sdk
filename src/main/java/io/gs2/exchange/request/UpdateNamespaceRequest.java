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
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {
    private String namespaceName;
    private String description;
    private Boolean enableAwaitExchange;
    private Boolean enableDirectExchange;
    private TransactionSetting transactionSetting;
    private ScriptSetting exchangeScript;
    private ScriptSetting incrementalExchangeScript;
    private LogSetting logSetting;
    private String queueNamespaceId;
    private String keyId;
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
	public Boolean getEnableAwaitExchange() {
		return enableAwaitExchange;
	}
	public void setEnableAwaitExchange(Boolean enableAwaitExchange) {
		this.enableAwaitExchange = enableAwaitExchange;
	}
	public UpdateNamespaceRequest withEnableAwaitExchange(Boolean enableAwaitExchange) {
		this.enableAwaitExchange = enableAwaitExchange;
		return this;
	}
	public Boolean getEnableDirectExchange() {
		return enableDirectExchange;
	}
	public void setEnableDirectExchange(Boolean enableDirectExchange) {
		this.enableDirectExchange = enableDirectExchange;
	}
	public UpdateNamespaceRequest withEnableDirectExchange(Boolean enableDirectExchange) {
		this.enableDirectExchange = enableDirectExchange;
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
	public ScriptSetting getExchangeScript() {
		return exchangeScript;
	}
	public void setExchangeScript(ScriptSetting exchangeScript) {
		this.exchangeScript = exchangeScript;
	}
	public UpdateNamespaceRequest withExchangeScript(ScriptSetting exchangeScript) {
		this.exchangeScript = exchangeScript;
		return this;
	}
	public ScriptSetting getIncrementalExchangeScript() {
		return incrementalExchangeScript;
	}
	public void setIncrementalExchangeScript(ScriptSetting incrementalExchangeScript) {
		this.incrementalExchangeScript = incrementalExchangeScript;
	}
	public UpdateNamespaceRequest withIncrementalExchangeScript(ScriptSetting incrementalExchangeScript) {
		this.incrementalExchangeScript = incrementalExchangeScript;
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
    @Deprecated
	public String getQueueNamespaceId() {
		return queueNamespaceId;
	}
    @Deprecated
	public void setQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
	}
    @Deprecated
	public UpdateNamespaceRequest withQueueNamespaceId(String queueNamespaceId) {
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
	public UpdateNamespaceRequest withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}

    public static UpdateNamespaceRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateNamespaceRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
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
                put("namespaceName", getNamespaceName());
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