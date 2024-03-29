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

package io.gs2.formation.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.formation.model.TransactionSetting;
import io.gs2.formation.model.ScriptSetting;
import io.gs2.formation.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private TransactionSetting transactionSetting;
    private ScriptSetting updateMoldScript;
    private ScriptSetting updateFormScript;
    private ScriptSetting updatePropertyFormScript;
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
	public ScriptSetting getUpdateMoldScript() {
		return updateMoldScript;
	}
	public void setUpdateMoldScript(ScriptSetting updateMoldScript) {
		this.updateMoldScript = updateMoldScript;
	}
	public CreateNamespaceRequest withUpdateMoldScript(ScriptSetting updateMoldScript) {
		this.updateMoldScript = updateMoldScript;
		return this;
	}
	public ScriptSetting getUpdateFormScript() {
		return updateFormScript;
	}
	public void setUpdateFormScript(ScriptSetting updateFormScript) {
		this.updateFormScript = updateFormScript;
	}
	public CreateNamespaceRequest withUpdateFormScript(ScriptSetting updateFormScript) {
		this.updateFormScript = updateFormScript;
		return this;
	}
	public ScriptSetting getUpdatePropertyFormScript() {
		return updatePropertyFormScript;
	}
	public void setUpdatePropertyFormScript(ScriptSetting updatePropertyFormScript) {
		this.updatePropertyFormScript = updatePropertyFormScript;
	}
	public CreateNamespaceRequest withUpdatePropertyFormScript(ScriptSetting updatePropertyFormScript) {
		this.updatePropertyFormScript = updatePropertyFormScript;
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
            .withTransactionSetting(data.get("transactionSetting") == null || data.get("transactionSetting").isNull() ? null : TransactionSetting.fromJson(data.get("transactionSetting")))
            .withUpdateMoldScript(data.get("updateMoldScript") == null || data.get("updateMoldScript").isNull() ? null : ScriptSetting.fromJson(data.get("updateMoldScript")))
            .withUpdateFormScript(data.get("updateFormScript") == null || data.get("updateFormScript").isNull() ? null : ScriptSetting.fromJson(data.get("updateFormScript")))
            .withUpdatePropertyFormScript(data.get("updatePropertyFormScript") == null || data.get("updatePropertyFormScript").isNull() ? null : ScriptSetting.fromJson(data.get("updatePropertyFormScript")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("transactionSetting", getTransactionSetting() != null ? getTransactionSetting().toJson() : null);
                put("updateMoldScript", getUpdateMoldScript() != null ? getUpdateMoldScript().toJson() : null);
                put("updateFormScript", getUpdateFormScript() != null ? getUpdateFormScript().toJson() : null);
                put("updatePropertyFormScript", getUpdatePropertyFormScript() != null ? getUpdatePropertyFormScript().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}