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

package io.gs2.guild.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.guild.model.NotificationSetting;
import io.gs2.guild.model.ScriptSetting;
import io.gs2.guild.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private NotificationSetting changeNotification;
    private NotificationSetting joinNotification;
    private NotificationSetting leaveNotification;
    private NotificationSetting changeMemberNotification;
    private NotificationSetting receiveRequestNotification;
    private NotificationSetting removeRequestNotification;
    private ScriptSetting createGuildScript;
    private ScriptSetting updateGuildScript;
    private ScriptSetting joinGuildScript;
    private ScriptSetting receiveJoinRequestScript;
    private ScriptSetting leaveGuildScript;
    private ScriptSetting changeRoleScript;
    private ScriptSetting deleteGuildScript;
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
	public NotificationSetting getChangeNotification() {
		return changeNotification;
	}
	public void setChangeNotification(NotificationSetting changeNotification) {
		this.changeNotification = changeNotification;
	}
	public CreateNamespaceRequest withChangeNotification(NotificationSetting changeNotification) {
		this.changeNotification = changeNotification;
		return this;
	}
	public NotificationSetting getJoinNotification() {
		return joinNotification;
	}
	public void setJoinNotification(NotificationSetting joinNotification) {
		this.joinNotification = joinNotification;
	}
	public CreateNamespaceRequest withJoinNotification(NotificationSetting joinNotification) {
		this.joinNotification = joinNotification;
		return this;
	}
	public NotificationSetting getLeaveNotification() {
		return leaveNotification;
	}
	public void setLeaveNotification(NotificationSetting leaveNotification) {
		this.leaveNotification = leaveNotification;
	}
	public CreateNamespaceRequest withLeaveNotification(NotificationSetting leaveNotification) {
		this.leaveNotification = leaveNotification;
		return this;
	}
	public NotificationSetting getChangeMemberNotification() {
		return changeMemberNotification;
	}
	public void setChangeMemberNotification(NotificationSetting changeMemberNotification) {
		this.changeMemberNotification = changeMemberNotification;
	}
	public CreateNamespaceRequest withChangeMemberNotification(NotificationSetting changeMemberNotification) {
		this.changeMemberNotification = changeMemberNotification;
		return this;
	}
	public NotificationSetting getReceiveRequestNotification() {
		return receiveRequestNotification;
	}
	public void setReceiveRequestNotification(NotificationSetting receiveRequestNotification) {
		this.receiveRequestNotification = receiveRequestNotification;
	}
	public CreateNamespaceRequest withReceiveRequestNotification(NotificationSetting receiveRequestNotification) {
		this.receiveRequestNotification = receiveRequestNotification;
		return this;
	}
	public NotificationSetting getRemoveRequestNotification() {
		return removeRequestNotification;
	}
	public void setRemoveRequestNotification(NotificationSetting removeRequestNotification) {
		this.removeRequestNotification = removeRequestNotification;
	}
	public CreateNamespaceRequest withRemoveRequestNotification(NotificationSetting removeRequestNotification) {
		this.removeRequestNotification = removeRequestNotification;
		return this;
	}
	public ScriptSetting getCreateGuildScript() {
		return createGuildScript;
	}
	public void setCreateGuildScript(ScriptSetting createGuildScript) {
		this.createGuildScript = createGuildScript;
	}
	public CreateNamespaceRequest withCreateGuildScript(ScriptSetting createGuildScript) {
		this.createGuildScript = createGuildScript;
		return this;
	}
	public ScriptSetting getUpdateGuildScript() {
		return updateGuildScript;
	}
	public void setUpdateGuildScript(ScriptSetting updateGuildScript) {
		this.updateGuildScript = updateGuildScript;
	}
	public CreateNamespaceRequest withUpdateGuildScript(ScriptSetting updateGuildScript) {
		this.updateGuildScript = updateGuildScript;
		return this;
	}
	public ScriptSetting getJoinGuildScript() {
		return joinGuildScript;
	}
	public void setJoinGuildScript(ScriptSetting joinGuildScript) {
		this.joinGuildScript = joinGuildScript;
	}
	public CreateNamespaceRequest withJoinGuildScript(ScriptSetting joinGuildScript) {
		this.joinGuildScript = joinGuildScript;
		return this;
	}
	public ScriptSetting getReceiveJoinRequestScript() {
		return receiveJoinRequestScript;
	}
	public void setReceiveJoinRequestScript(ScriptSetting receiveJoinRequestScript) {
		this.receiveJoinRequestScript = receiveJoinRequestScript;
	}
	public CreateNamespaceRequest withReceiveJoinRequestScript(ScriptSetting receiveJoinRequestScript) {
		this.receiveJoinRequestScript = receiveJoinRequestScript;
		return this;
	}
	public ScriptSetting getLeaveGuildScript() {
		return leaveGuildScript;
	}
	public void setLeaveGuildScript(ScriptSetting leaveGuildScript) {
		this.leaveGuildScript = leaveGuildScript;
	}
	public CreateNamespaceRequest withLeaveGuildScript(ScriptSetting leaveGuildScript) {
		this.leaveGuildScript = leaveGuildScript;
		return this;
	}
	public ScriptSetting getChangeRoleScript() {
		return changeRoleScript;
	}
	public void setChangeRoleScript(ScriptSetting changeRoleScript) {
		this.changeRoleScript = changeRoleScript;
	}
	public CreateNamespaceRequest withChangeRoleScript(ScriptSetting changeRoleScript) {
		this.changeRoleScript = changeRoleScript;
		return this;
	}
	public ScriptSetting getDeleteGuildScript() {
		return deleteGuildScript;
	}
	public void setDeleteGuildScript(ScriptSetting deleteGuildScript) {
		this.deleteGuildScript = deleteGuildScript;
	}
	public CreateNamespaceRequest withDeleteGuildScript(ScriptSetting deleteGuildScript) {
		this.deleteGuildScript = deleteGuildScript;
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
            .withChangeNotification(data.get("changeNotification") == null || data.get("changeNotification").isNull() ? null : NotificationSetting.fromJson(data.get("changeNotification")))
            .withJoinNotification(data.get("joinNotification") == null || data.get("joinNotification").isNull() ? null : NotificationSetting.fromJson(data.get("joinNotification")))
            .withLeaveNotification(data.get("leaveNotification") == null || data.get("leaveNotification").isNull() ? null : NotificationSetting.fromJson(data.get("leaveNotification")))
            .withChangeMemberNotification(data.get("changeMemberNotification") == null || data.get("changeMemberNotification").isNull() ? null : NotificationSetting.fromJson(data.get("changeMemberNotification")))
            .withReceiveRequestNotification(data.get("receiveRequestNotification") == null || data.get("receiveRequestNotification").isNull() ? null : NotificationSetting.fromJson(data.get("receiveRequestNotification")))
            .withRemoveRequestNotification(data.get("removeRequestNotification") == null || data.get("removeRequestNotification").isNull() ? null : NotificationSetting.fromJson(data.get("removeRequestNotification")))
            .withCreateGuildScript(data.get("createGuildScript") == null || data.get("createGuildScript").isNull() ? null : ScriptSetting.fromJson(data.get("createGuildScript")))
            .withUpdateGuildScript(data.get("updateGuildScript") == null || data.get("updateGuildScript").isNull() ? null : ScriptSetting.fromJson(data.get("updateGuildScript")))
            .withJoinGuildScript(data.get("joinGuildScript") == null || data.get("joinGuildScript").isNull() ? null : ScriptSetting.fromJson(data.get("joinGuildScript")))
            .withReceiveJoinRequestScript(data.get("receiveJoinRequestScript") == null || data.get("receiveJoinRequestScript").isNull() ? null : ScriptSetting.fromJson(data.get("receiveJoinRequestScript")))
            .withLeaveGuildScript(data.get("leaveGuildScript") == null || data.get("leaveGuildScript").isNull() ? null : ScriptSetting.fromJson(data.get("leaveGuildScript")))
            .withChangeRoleScript(data.get("changeRoleScript") == null || data.get("changeRoleScript").isNull() ? null : ScriptSetting.fromJson(data.get("changeRoleScript")))
            .withDeleteGuildScript(data.get("deleteGuildScript") == null || data.get("deleteGuildScript").isNull() ? null : ScriptSetting.fromJson(data.get("deleteGuildScript")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("changeNotification", getChangeNotification() != null ? getChangeNotification().toJson() : null);
                put("joinNotification", getJoinNotification() != null ? getJoinNotification().toJson() : null);
                put("leaveNotification", getLeaveNotification() != null ? getLeaveNotification().toJson() : null);
                put("changeMemberNotification", getChangeMemberNotification() != null ? getChangeMemberNotification().toJson() : null);
                put("receiveRequestNotification", getReceiveRequestNotification() != null ? getReceiveRequestNotification().toJson() : null);
                put("removeRequestNotification", getRemoveRequestNotification() != null ? getRemoveRequestNotification().toJson() : null);
                put("createGuildScript", getCreateGuildScript() != null ? getCreateGuildScript().toJson() : null);
                put("updateGuildScript", getUpdateGuildScript() != null ? getUpdateGuildScript().toJson() : null);
                put("joinGuildScript", getJoinGuildScript() != null ? getJoinGuildScript().toJson() : null);
                put("receiveJoinRequestScript", getReceiveJoinRequestScript() != null ? getReceiveJoinRequestScript().toJson() : null);
                put("leaveGuildScript", getLeaveGuildScript() != null ? getLeaveGuildScript().toJson() : null);
                put("changeRoleScript", getChangeRoleScript() != null ? getChangeRoleScript().toJson() : null);
                put("deleteGuildScript", getDeleteGuildScript() != null ? getDeleteGuildScript().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}