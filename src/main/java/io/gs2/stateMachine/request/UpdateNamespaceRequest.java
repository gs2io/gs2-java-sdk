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

package io.gs2.stateMachine.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.stateMachine.model.TransactionSetting;
import io.gs2.stateMachine.model.ScriptSetting;
import io.gs2.stateMachine.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {
    private String namespaceName;
    private String description;
    private String supportSpeculativeExecution;
    private TransactionSetting transactionSetting;
    private ScriptSetting startScript;
    private ScriptSetting passScript;
    private ScriptSetting errorScript;
    private Long lowestStateMachineVersion;
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
	public String getSupportSpeculativeExecution() {
		return supportSpeculativeExecution;
	}
	public void setSupportSpeculativeExecution(String supportSpeculativeExecution) {
		this.supportSpeculativeExecution = supportSpeculativeExecution;
	}
	public UpdateNamespaceRequest withSupportSpeculativeExecution(String supportSpeculativeExecution) {
		this.supportSpeculativeExecution = supportSpeculativeExecution;
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
	public ScriptSetting getStartScript() {
		return startScript;
	}
	public void setStartScript(ScriptSetting startScript) {
		this.startScript = startScript;
	}
	public UpdateNamespaceRequest withStartScript(ScriptSetting startScript) {
		this.startScript = startScript;
		return this;
	}
	public ScriptSetting getPassScript() {
		return passScript;
	}
	public void setPassScript(ScriptSetting passScript) {
		this.passScript = passScript;
	}
	public UpdateNamespaceRequest withPassScript(ScriptSetting passScript) {
		this.passScript = passScript;
		return this;
	}
	public ScriptSetting getErrorScript() {
		return errorScript;
	}
	public void setErrorScript(ScriptSetting errorScript) {
		this.errorScript = errorScript;
	}
	public UpdateNamespaceRequest withErrorScript(ScriptSetting errorScript) {
		this.errorScript = errorScript;
		return this;
	}
	public Long getLowestStateMachineVersion() {
		return lowestStateMachineVersion;
	}
	public void setLowestStateMachineVersion(Long lowestStateMachineVersion) {
		this.lowestStateMachineVersion = lowestStateMachineVersion;
	}
	public UpdateNamespaceRequest withLowestStateMachineVersion(Long lowestStateMachineVersion) {
		this.lowestStateMachineVersion = lowestStateMachineVersion;
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
            .withSupportSpeculativeExecution(data.get("supportSpeculativeExecution") == null || data.get("supportSpeculativeExecution").isNull() ? null : data.get("supportSpeculativeExecution").asText())
            .withTransactionSetting(data.get("transactionSetting") == null || data.get("transactionSetting").isNull() ? null : TransactionSetting.fromJson(data.get("transactionSetting")))
            .withStartScript(data.get("startScript") == null || data.get("startScript").isNull() ? null : ScriptSetting.fromJson(data.get("startScript")))
            .withPassScript(data.get("passScript") == null || data.get("passScript").isNull() ? null : ScriptSetting.fromJson(data.get("passScript")))
            .withErrorScript(data.get("errorScript") == null || data.get("errorScript").isNull() ? null : ScriptSetting.fromJson(data.get("errorScript")))
            .withLowestStateMachineVersion(data.get("lowestStateMachineVersion") == null || data.get("lowestStateMachineVersion").isNull() ? null : data.get("lowestStateMachineVersion").longValue())
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("description", getDescription());
                put("supportSpeculativeExecution", getSupportSpeculativeExecution());
                put("transactionSetting", getTransactionSetting() != null ? getTransactionSetting().toJson() : null);
                put("startScript", getStartScript() != null ? getStartScript().toJson() : null);
                put("passScript", getPassScript() != null ? getPassScript().toJson() : null);
                put("errorScript", getErrorScript() != null ? getErrorScript().toJson() : null);
                put("lowestStateMachineVersion", getLowestStateMachineVersion());
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}