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
import io.gs2.chat.model.TransactionSetting;
import io.gs2.chat.model.ScriptSetting;
import io.gs2.chat.model.NotificationSetting;
import io.gs2.chat.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private TransactionSetting transactionSetting;
    private Boolean allowCreateRoom;
    private Integer messageLifeTimeDays;
    private ScriptSetting postMessageScript;
    private ScriptSetting createRoomScript;
    private ScriptSetting deleteRoomScript;
    private ScriptSetting subscribeRoomScript;
    private ScriptSetting unsubscribeRoomScript;
    private NotificationSetting postNotification;
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
	public Boolean getAllowCreateRoom() {
		return allowCreateRoom;
	}
	public void setAllowCreateRoom(Boolean allowCreateRoom) {
		this.allowCreateRoom = allowCreateRoom;
	}
	public CreateNamespaceRequest withAllowCreateRoom(Boolean allowCreateRoom) {
		this.allowCreateRoom = allowCreateRoom;
		return this;
	}
	public Integer getMessageLifeTimeDays() {
		return messageLifeTimeDays;
	}
	public void setMessageLifeTimeDays(Integer messageLifeTimeDays) {
		this.messageLifeTimeDays = messageLifeTimeDays;
	}
	public CreateNamespaceRequest withMessageLifeTimeDays(Integer messageLifeTimeDays) {
		this.messageLifeTimeDays = messageLifeTimeDays;
		return this;
	}
	public ScriptSetting getPostMessageScript() {
		return postMessageScript;
	}
	public void setPostMessageScript(ScriptSetting postMessageScript) {
		this.postMessageScript = postMessageScript;
	}
	public CreateNamespaceRequest withPostMessageScript(ScriptSetting postMessageScript) {
		this.postMessageScript = postMessageScript;
		return this;
	}
	public ScriptSetting getCreateRoomScript() {
		return createRoomScript;
	}
	public void setCreateRoomScript(ScriptSetting createRoomScript) {
		this.createRoomScript = createRoomScript;
	}
	public CreateNamespaceRequest withCreateRoomScript(ScriptSetting createRoomScript) {
		this.createRoomScript = createRoomScript;
		return this;
	}
	public ScriptSetting getDeleteRoomScript() {
		return deleteRoomScript;
	}
	public void setDeleteRoomScript(ScriptSetting deleteRoomScript) {
		this.deleteRoomScript = deleteRoomScript;
	}
	public CreateNamespaceRequest withDeleteRoomScript(ScriptSetting deleteRoomScript) {
		this.deleteRoomScript = deleteRoomScript;
		return this;
	}
	public ScriptSetting getSubscribeRoomScript() {
		return subscribeRoomScript;
	}
	public void setSubscribeRoomScript(ScriptSetting subscribeRoomScript) {
		this.subscribeRoomScript = subscribeRoomScript;
	}
	public CreateNamespaceRequest withSubscribeRoomScript(ScriptSetting subscribeRoomScript) {
		this.subscribeRoomScript = subscribeRoomScript;
		return this;
	}
	public ScriptSetting getUnsubscribeRoomScript() {
		return unsubscribeRoomScript;
	}
	public void setUnsubscribeRoomScript(ScriptSetting unsubscribeRoomScript) {
		this.unsubscribeRoomScript = unsubscribeRoomScript;
	}
	public CreateNamespaceRequest withUnsubscribeRoomScript(ScriptSetting unsubscribeRoomScript) {
		this.unsubscribeRoomScript = unsubscribeRoomScript;
		return this;
	}
	public NotificationSetting getPostNotification() {
		return postNotification;
	}
	public void setPostNotification(NotificationSetting postNotification) {
		this.postNotification = postNotification;
	}
	public CreateNamespaceRequest withPostNotification(NotificationSetting postNotification) {
		this.postNotification = postNotification;
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
            .withAllowCreateRoom(data.get("allowCreateRoom") == null || data.get("allowCreateRoom").isNull() ? null : data.get("allowCreateRoom").booleanValue())
            .withMessageLifeTimeDays(data.get("messageLifeTimeDays") == null || data.get("messageLifeTimeDays").isNull() ? null : data.get("messageLifeTimeDays").intValue())
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
                put("name", getName());
                put("description", getDescription());
                put("transactionSetting", getTransactionSetting() != null ? getTransactionSetting().toJson() : null);
                put("allowCreateRoom", getAllowCreateRoom());
                put("messageLifeTimeDays", getMessageLifeTimeDays());
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