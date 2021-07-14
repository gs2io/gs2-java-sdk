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

package io.gs2.inbox.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.inbox.model.ScriptSetting;
import io.gs2.inbox.model.NotificationSetting;
import io.gs2.inbox.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private Boolean isAutomaticDeletingEnabled;
    private ScriptSetting receiveMessageScript;
    private ScriptSetting readMessageScript;
    private ScriptSetting deleteMessageScript;
    private String queueNamespaceId;
    private String keyId;
    private NotificationSetting receiveNotification;
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

	public Boolean getIsAutomaticDeletingEnabled() {
		return isAutomaticDeletingEnabled;
	}

	public void setIsAutomaticDeletingEnabled(Boolean isAutomaticDeletingEnabled) {
		this.isAutomaticDeletingEnabled = isAutomaticDeletingEnabled;
	}

	public CreateNamespaceRequest withIsAutomaticDeletingEnabled(Boolean isAutomaticDeletingEnabled) {
		this.isAutomaticDeletingEnabled = isAutomaticDeletingEnabled;
		return this;
	}

	public ScriptSetting getReceiveMessageScript() {
		return receiveMessageScript;
	}

	public void setReceiveMessageScript(ScriptSetting receiveMessageScript) {
		this.receiveMessageScript = receiveMessageScript;
	}

	public CreateNamespaceRequest withReceiveMessageScript(ScriptSetting receiveMessageScript) {
		this.receiveMessageScript = receiveMessageScript;
		return this;
	}

	public ScriptSetting getReadMessageScript() {
		return readMessageScript;
	}

	public void setReadMessageScript(ScriptSetting readMessageScript) {
		this.readMessageScript = readMessageScript;
	}

	public CreateNamespaceRequest withReadMessageScript(ScriptSetting readMessageScript) {
		this.readMessageScript = readMessageScript;
		return this;
	}

	public ScriptSetting getDeleteMessageScript() {
		return deleteMessageScript;
	}

	public void setDeleteMessageScript(ScriptSetting deleteMessageScript) {
		this.deleteMessageScript = deleteMessageScript;
	}

	public CreateNamespaceRequest withDeleteMessageScript(ScriptSetting deleteMessageScript) {
		this.deleteMessageScript = deleteMessageScript;
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

	public NotificationSetting getReceiveNotification() {
		return receiveNotification;
	}

	public void setReceiveNotification(NotificationSetting receiveNotification) {
		this.receiveNotification = receiveNotification;
	}

	public CreateNamespaceRequest withReceiveNotification(NotificationSetting receiveNotification) {
		this.receiveNotification = receiveNotification;
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
            .withIsAutomaticDeletingEnabled(data.get("isAutomaticDeletingEnabled") == null || data.get("isAutomaticDeletingEnabled").isNull() ? null : data.get("isAutomaticDeletingEnabled").booleanValue())
            .withReceiveMessageScript(data.get("receiveMessageScript") == null || data.get("receiveMessageScript").isNull() ? null : ScriptSetting.fromJson(data.get("receiveMessageScript")))
            .withReadMessageScript(data.get("readMessageScript") == null || data.get("readMessageScript").isNull() ? null : ScriptSetting.fromJson(data.get("readMessageScript")))
            .withDeleteMessageScript(data.get("deleteMessageScript") == null || data.get("deleteMessageScript").isNull() ? null : ScriptSetting.fromJson(data.get("deleteMessageScript")))
            .withQueueNamespaceId(data.get("queueNamespaceId") == null || data.get("queueNamespaceId").isNull() ? null : data.get("queueNamespaceId").asText())
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText())
            .withReceiveNotification(data.get("receiveNotification") == null || data.get("receiveNotification").isNull() ? null : NotificationSetting.fromJson(data.get("receiveNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("isAutomaticDeletingEnabled", getIsAutomaticDeletingEnabled());
                put("receiveMessageScript", getReceiveMessageScript() != null ? getReceiveMessageScript().toJson() : null);
                put("readMessageScript", getReadMessageScript() != null ? getReadMessageScript().toJson() : null);
                put("deleteMessageScript", getDeleteMessageScript() != null ? getDeleteMessageScript().toJson() : null);
                put("queueNamespaceId", getQueueNamespaceId());
                put("keyId", getKeyId());
                put("receiveNotification", getReceiveNotification() != null ? getReceiveNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}