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

package io.gs2.chat.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.chat.model.ScriptSetting;
import io.gs2.chat.model.NotificationSetting;
import io.gs2.chat.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {
    private String namespaceName;
    private String description;
    private Boolean allowCreateRoom;
    private ScriptSetting postMessageScript;
    private ScriptSetting createRoomScript;
    private ScriptSetting deleteRoomScript;
    private ScriptSetting subscribeRoomScript;
    private ScriptSetting unsubscribeRoomScript;
    private NotificationSetting postNotification;
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
	public Boolean getAllowCreateRoom() {
		return allowCreateRoom;
	}
	public void setAllowCreateRoom(Boolean allowCreateRoom) {
		this.allowCreateRoom = allowCreateRoom;
	}
	public UpdateNamespaceRequest withAllowCreateRoom(Boolean allowCreateRoom) {
		this.allowCreateRoom = allowCreateRoom;
		return this;
	}
	public ScriptSetting getPostMessageScript() {
		return postMessageScript;
	}
	public void setPostMessageScript(ScriptSetting postMessageScript) {
		this.postMessageScript = postMessageScript;
	}
	public UpdateNamespaceRequest withPostMessageScript(ScriptSetting postMessageScript) {
		this.postMessageScript = postMessageScript;
		return this;
	}
	public ScriptSetting getCreateRoomScript() {
		return createRoomScript;
	}
	public void setCreateRoomScript(ScriptSetting createRoomScript) {
		this.createRoomScript = createRoomScript;
	}
	public UpdateNamespaceRequest withCreateRoomScript(ScriptSetting createRoomScript) {
		this.createRoomScript = createRoomScript;
		return this;
	}
	public ScriptSetting getDeleteRoomScript() {
		return deleteRoomScript;
	}
	public void setDeleteRoomScript(ScriptSetting deleteRoomScript) {
		this.deleteRoomScript = deleteRoomScript;
	}
	public UpdateNamespaceRequest withDeleteRoomScript(ScriptSetting deleteRoomScript) {
		this.deleteRoomScript = deleteRoomScript;
		return this;
	}
	public ScriptSetting getSubscribeRoomScript() {
		return subscribeRoomScript;
	}
	public void setSubscribeRoomScript(ScriptSetting subscribeRoomScript) {
		this.subscribeRoomScript = subscribeRoomScript;
	}
	public UpdateNamespaceRequest withSubscribeRoomScript(ScriptSetting subscribeRoomScript) {
		this.subscribeRoomScript = subscribeRoomScript;
		return this;
	}
	public ScriptSetting getUnsubscribeRoomScript() {
		return unsubscribeRoomScript;
	}
	public void setUnsubscribeRoomScript(ScriptSetting unsubscribeRoomScript) {
		this.unsubscribeRoomScript = unsubscribeRoomScript;
	}
	public UpdateNamespaceRequest withUnsubscribeRoomScript(ScriptSetting unsubscribeRoomScript) {
		this.unsubscribeRoomScript = unsubscribeRoomScript;
		return this;
	}
	public NotificationSetting getPostNotification() {
		return postNotification;
	}
	public void setPostNotification(NotificationSetting postNotification) {
		this.postNotification = postNotification;
	}
	public UpdateNamespaceRequest withPostNotification(NotificationSetting postNotification) {
		this.postNotification = postNotification;
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
            .withAllowCreateRoom(data.get("allowCreateRoom") == null || data.get("allowCreateRoom").isNull() ? null : data.get("allowCreateRoom").booleanValue())
            .withPostMessageScript(data.get("postMessageScript") == null || data.get("postMessageScript").isNull() ? null : ScriptSetting.fromJson(data.get("postMessageScript")))
            .withCreateRoomScript(data.get("createRoomScript") == null || data.get("createRoomScript").isNull() ? null : ScriptSetting.fromJson(data.get("createRoomScript")))
            .withDeleteRoomScript(data.get("deleteRoomScript") == null || data.get("deleteRoomScript").isNull() ? null : ScriptSetting.fromJson(data.get("deleteRoomScript")))
            .withSubscribeRoomScript(data.get("subscribeRoomScript") == null || data.get("subscribeRoomScript").isNull() ? null : ScriptSetting.fromJson(data.get("subscribeRoomScript")))
            .withUnsubscribeRoomScript(data.get("unsubscribeRoomScript") == null || data.get("unsubscribeRoomScript").isNull() ? null : ScriptSetting.fromJson(data.get("unsubscribeRoomScript")))
            .withPostNotification(data.get("postNotification") == null || data.get("postNotification").isNull() ? null : NotificationSetting.fromJson(data.get("postNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("description", getDescription());
                put("allowCreateRoom", getAllowCreateRoom());
                put("postMessageScript", getPostMessageScript() != null ? getPostMessageScript().toJson() : null);
                put("createRoomScript", getCreateRoomScript() != null ? getCreateRoomScript().toJson() : null);
                put("deleteRoomScript", getDeleteRoomScript() != null ? getDeleteRoomScript().toJson() : null);
                put("subscribeRoomScript", getSubscribeRoomScript() != null ? getSubscribeRoomScript().toJson() : null);
                put("unsubscribeRoomScript", getUnsubscribeRoomScript() != null ? getUnsubscribeRoomScript().toJson() : null);
                put("postNotification", getPostNotification() != null ? getPostNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}