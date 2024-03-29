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

package io.gs2.experience.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.experience.model.TransactionSetting;
import io.gs2.experience.model.ScriptSetting;
import io.gs2.experience.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {
    private String namespaceName;
    private String description;
    private TransactionSetting transactionSetting;
    private String rankCapScriptId;
    private ScriptSetting changeExperienceScript;
    private ScriptSetting changeRankScript;
    private ScriptSetting changeRankCapScript;
    private String overflowExperienceScript;
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
	public String getRankCapScriptId() {
		return rankCapScriptId;
	}
	public void setRankCapScriptId(String rankCapScriptId) {
		this.rankCapScriptId = rankCapScriptId;
	}
	public UpdateNamespaceRequest withRankCapScriptId(String rankCapScriptId) {
		this.rankCapScriptId = rankCapScriptId;
		return this;
	}
	public ScriptSetting getChangeExperienceScript() {
		return changeExperienceScript;
	}
	public void setChangeExperienceScript(ScriptSetting changeExperienceScript) {
		this.changeExperienceScript = changeExperienceScript;
	}
	public UpdateNamespaceRequest withChangeExperienceScript(ScriptSetting changeExperienceScript) {
		this.changeExperienceScript = changeExperienceScript;
		return this;
	}
	public ScriptSetting getChangeRankScript() {
		return changeRankScript;
	}
	public void setChangeRankScript(ScriptSetting changeRankScript) {
		this.changeRankScript = changeRankScript;
	}
	public UpdateNamespaceRequest withChangeRankScript(ScriptSetting changeRankScript) {
		this.changeRankScript = changeRankScript;
		return this;
	}
	public ScriptSetting getChangeRankCapScript() {
		return changeRankCapScript;
	}
	public void setChangeRankCapScript(ScriptSetting changeRankCapScript) {
		this.changeRankCapScript = changeRankCapScript;
	}
	public UpdateNamespaceRequest withChangeRankCapScript(ScriptSetting changeRankCapScript) {
		this.changeRankCapScript = changeRankCapScript;
		return this;
	}
	public String getOverflowExperienceScript() {
		return overflowExperienceScript;
	}
	public void setOverflowExperienceScript(String overflowExperienceScript) {
		this.overflowExperienceScript = overflowExperienceScript;
	}
	public UpdateNamespaceRequest withOverflowExperienceScript(String overflowExperienceScript) {
		this.overflowExperienceScript = overflowExperienceScript;
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
            .withRankCapScriptId(data.get("rankCapScriptId") == null || data.get("rankCapScriptId").isNull() ? null : data.get("rankCapScriptId").asText())
            .withChangeExperienceScript(data.get("changeExperienceScript") == null || data.get("changeExperienceScript").isNull() ? null : ScriptSetting.fromJson(data.get("changeExperienceScript")))
            .withChangeRankScript(data.get("changeRankScript") == null || data.get("changeRankScript").isNull() ? null : ScriptSetting.fromJson(data.get("changeRankScript")))
            .withChangeRankCapScript(data.get("changeRankCapScript") == null || data.get("changeRankCapScript").isNull() ? null : ScriptSetting.fromJson(data.get("changeRankCapScript")))
            .withOverflowExperienceScript(data.get("overflowExperienceScript") == null || data.get("overflowExperienceScript").isNull() ? null : data.get("overflowExperienceScript").asText())
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("description", getDescription());
                put("transactionSetting", getTransactionSetting() != null ? getTransactionSetting().toJson() : null);
                put("rankCapScriptId", getRankCapScriptId());
                put("changeExperienceScript", getChangeExperienceScript() != null ? getChangeExperienceScript().toJson() : null);
                put("changeRankScript", getChangeRankScript() != null ? getChangeRankScript().toJson() : null);
                put("changeRankCapScript", getChangeRankCapScript() != null ? getChangeRankCapScript().toJson() : null);
                put("overflowExperienceScript", getOverflowExperienceScript());
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}