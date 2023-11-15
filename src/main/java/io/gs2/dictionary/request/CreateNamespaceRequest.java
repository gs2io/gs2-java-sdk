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

package io.gs2.dictionary.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.dictionary.model.ScriptSetting;
import io.gs2.dictionary.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private ScriptSetting entryScript;
    private String duplicateEntryScript;
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
	public ScriptSetting getEntryScript() {
		return entryScript;
	}
	public void setEntryScript(ScriptSetting entryScript) {
		this.entryScript = entryScript;
	}
	public CreateNamespaceRequest withEntryScript(ScriptSetting entryScript) {
		this.entryScript = entryScript;
		return this;
	}
	public String getDuplicateEntryScript() {
		return duplicateEntryScript;
	}
	public void setDuplicateEntryScript(String duplicateEntryScript) {
		this.duplicateEntryScript = duplicateEntryScript;
	}
	public CreateNamespaceRequest withDuplicateEntryScript(String duplicateEntryScript) {
		this.duplicateEntryScript = duplicateEntryScript;
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
            .withEntryScript(data.get("entryScript") == null || data.get("entryScript").isNull() ? null : ScriptSetting.fromJson(data.get("entryScript")))
            .withDuplicateEntryScript(data.get("duplicateEntryScript") == null || data.get("duplicateEntryScript").isNull() ? null : data.get("duplicateEntryScript").asText())
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("entryScript", getEntryScript() != null ? getEntryScript().toJson() : null);
                put("duplicateEntryScript", getDuplicateEntryScript());
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}