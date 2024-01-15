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

package io.gs2.inventory.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.inventory.model.ScriptSetting;
import io.gs2.inventory.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private ScriptSetting acquireScript;
    private ScriptSetting overflowScript;
    private ScriptSetting consumeScript;
    private ScriptSetting simpleItemAcquireScript;
    private ScriptSetting simpleItemConsumeScript;
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
	public ScriptSetting getAcquireScript() {
		return acquireScript;
	}
	public void setAcquireScript(ScriptSetting acquireScript) {
		this.acquireScript = acquireScript;
	}
	public CreateNamespaceRequest withAcquireScript(ScriptSetting acquireScript) {
		this.acquireScript = acquireScript;
		return this;
	}
	public ScriptSetting getOverflowScript() {
		return overflowScript;
	}
	public void setOverflowScript(ScriptSetting overflowScript) {
		this.overflowScript = overflowScript;
	}
	public CreateNamespaceRequest withOverflowScript(ScriptSetting overflowScript) {
		this.overflowScript = overflowScript;
		return this;
	}
	public ScriptSetting getConsumeScript() {
		return consumeScript;
	}
	public void setConsumeScript(ScriptSetting consumeScript) {
		this.consumeScript = consumeScript;
	}
	public CreateNamespaceRequest withConsumeScript(ScriptSetting consumeScript) {
		this.consumeScript = consumeScript;
		return this;
	}
	public ScriptSetting getSimpleItemAcquireScript() {
		return simpleItemAcquireScript;
	}
	public void setSimpleItemAcquireScript(ScriptSetting simpleItemAcquireScript) {
		this.simpleItemAcquireScript = simpleItemAcquireScript;
	}
	public CreateNamespaceRequest withSimpleItemAcquireScript(ScriptSetting simpleItemAcquireScript) {
		this.simpleItemAcquireScript = simpleItemAcquireScript;
		return this;
	}
	public ScriptSetting getSimpleItemConsumeScript() {
		return simpleItemConsumeScript;
	}
	public void setSimpleItemConsumeScript(ScriptSetting simpleItemConsumeScript) {
		this.simpleItemConsumeScript = simpleItemConsumeScript;
	}
	public CreateNamespaceRequest withSimpleItemConsumeScript(ScriptSetting simpleItemConsumeScript) {
		this.simpleItemConsumeScript = simpleItemConsumeScript;
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
            .withAcquireScript(data.get("acquireScript") == null || data.get("acquireScript").isNull() ? null : ScriptSetting.fromJson(data.get("acquireScript")))
            .withOverflowScript(data.get("overflowScript") == null || data.get("overflowScript").isNull() ? null : ScriptSetting.fromJson(data.get("overflowScript")))
            .withConsumeScript(data.get("consumeScript") == null || data.get("consumeScript").isNull() ? null : ScriptSetting.fromJson(data.get("consumeScript")))
            .withSimpleItemAcquireScript(data.get("simpleItemAcquireScript") == null || data.get("simpleItemAcquireScript").isNull() ? null : ScriptSetting.fromJson(data.get("simpleItemAcquireScript")))
            .withSimpleItemConsumeScript(data.get("simpleItemConsumeScript") == null || data.get("simpleItemConsumeScript").isNull() ? null : ScriptSetting.fromJson(data.get("simpleItemConsumeScript")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("acquireScript", getAcquireScript() != null ? getAcquireScript().toJson() : null);
                put("overflowScript", getOverflowScript() != null ? getOverflowScript().toJson() : null);
                put("consumeScript", getConsumeScript() != null ? getConsumeScript().toJson() : null);
                put("simpleItemAcquireScript", getSimpleItemAcquireScript() != null ? getSimpleItemAcquireScript().toJson() : null);
                put("simpleItemConsumeScript", getSimpleItemConsumeScript() != null ? getSimpleItemConsumeScript().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}