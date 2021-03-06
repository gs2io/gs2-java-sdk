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

package io.gs2.quest.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.quest.model.ScriptSetting;
import io.gs2.quest.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private ScriptSetting startQuestScript;
    private ScriptSetting completeQuestScript;
    private ScriptSetting failedQuestScript;
    private String queueNamespaceId;
    private String keyId;
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

	public ScriptSetting getStartQuestScript() {
		return startQuestScript;
	}

	public void setStartQuestScript(ScriptSetting startQuestScript) {
		this.startQuestScript = startQuestScript;
	}

	public CreateNamespaceRequest withStartQuestScript(ScriptSetting startQuestScript) {
		this.startQuestScript = startQuestScript;
		return this;
	}

	public ScriptSetting getCompleteQuestScript() {
		return completeQuestScript;
	}

	public void setCompleteQuestScript(ScriptSetting completeQuestScript) {
		this.completeQuestScript = completeQuestScript;
	}

	public CreateNamespaceRequest withCompleteQuestScript(ScriptSetting completeQuestScript) {
		this.completeQuestScript = completeQuestScript;
		return this;
	}

	public ScriptSetting getFailedQuestScript() {
		return failedQuestScript;
	}

	public void setFailedQuestScript(ScriptSetting failedQuestScript) {
		this.failedQuestScript = failedQuestScript;
	}

	public CreateNamespaceRequest withFailedQuestScript(ScriptSetting failedQuestScript) {
		this.failedQuestScript = failedQuestScript;
		return this;
	}

	public String getQueueNamespaceId() {
		return queueNamespaceId;
	}

	public void setQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
	}

	public CreateNamespaceRequest withQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
		return this;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public CreateNamespaceRequest withKeyId(String keyId) {
		this.keyId = keyId;
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
            .withStartQuestScript(data.get("startQuestScript") == null || data.get("startQuestScript").isNull() ? null : ScriptSetting.fromJson(data.get("startQuestScript")))
            .withCompleteQuestScript(data.get("completeQuestScript") == null || data.get("completeQuestScript").isNull() ? null : ScriptSetting.fromJson(data.get("completeQuestScript")))
            .withFailedQuestScript(data.get("failedQuestScript") == null || data.get("failedQuestScript").isNull() ? null : ScriptSetting.fromJson(data.get("failedQuestScript")))
            .withQueueNamespaceId(data.get("queueNamespaceId") == null || data.get("queueNamespaceId").isNull() ? null : data.get("queueNamespaceId").asText())
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText())
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("startQuestScript", getStartQuestScript() != null ? getStartQuestScript().toJson() : null);
                put("completeQuestScript", getCompleteQuestScript() != null ? getCompleteQuestScript().toJson() : null);
                put("failedQuestScript", getFailedQuestScript() != null ? getFailedQuestScript().toJson() : null);
                put("queueNamespaceId", getQueueNamespaceId());
                put("keyId", getKeyId());
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}