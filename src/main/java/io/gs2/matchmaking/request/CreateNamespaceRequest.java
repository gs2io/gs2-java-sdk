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

package io.gs2.matchmaking.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.matchmaking.model.ScriptSetting;
import io.gs2.matchmaking.model.NotificationSetting;
import io.gs2.matchmaking.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private Boolean enableRating;
    private String createGatheringTriggerType;
    private String createGatheringTriggerRealtimeNamespaceId;
    private String createGatheringTriggerScriptId;
    private String completeMatchmakingTriggerType;
    private String completeMatchmakingTriggerRealtimeNamespaceId;
    private String completeMatchmakingTriggerScriptId;
    private ScriptSetting changeRatingScript;
    private NotificationSetting joinNotification;
    private NotificationSetting leaveNotification;
    private NotificationSetting completeNotification;
    private NotificationSetting changeRatingNotification;
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
	public Boolean getEnableRating() {
		return enableRating;
	}
	public void setEnableRating(Boolean enableRating) {
		this.enableRating = enableRating;
	}
	public CreateNamespaceRequest withEnableRating(Boolean enableRating) {
		this.enableRating = enableRating;
		return this;
	}
	public String getCreateGatheringTriggerType() {
		return createGatheringTriggerType;
	}
	public void setCreateGatheringTriggerType(String createGatheringTriggerType) {
		this.createGatheringTriggerType = createGatheringTriggerType;
	}
	public CreateNamespaceRequest withCreateGatheringTriggerType(String createGatheringTriggerType) {
		this.createGatheringTriggerType = createGatheringTriggerType;
		return this;
	}
	public String getCreateGatheringTriggerRealtimeNamespaceId() {
		return createGatheringTriggerRealtimeNamespaceId;
	}
	public void setCreateGatheringTriggerRealtimeNamespaceId(String createGatheringTriggerRealtimeNamespaceId) {
		this.createGatheringTriggerRealtimeNamespaceId = createGatheringTriggerRealtimeNamespaceId;
	}
	public CreateNamespaceRequest withCreateGatheringTriggerRealtimeNamespaceId(String createGatheringTriggerRealtimeNamespaceId) {
		this.createGatheringTriggerRealtimeNamespaceId = createGatheringTriggerRealtimeNamespaceId;
		return this;
	}
	public String getCreateGatheringTriggerScriptId() {
		return createGatheringTriggerScriptId;
	}
	public void setCreateGatheringTriggerScriptId(String createGatheringTriggerScriptId) {
		this.createGatheringTriggerScriptId = createGatheringTriggerScriptId;
	}
	public CreateNamespaceRequest withCreateGatheringTriggerScriptId(String createGatheringTriggerScriptId) {
		this.createGatheringTriggerScriptId = createGatheringTriggerScriptId;
		return this;
	}
	public String getCompleteMatchmakingTriggerType() {
		return completeMatchmakingTriggerType;
	}
	public void setCompleteMatchmakingTriggerType(String completeMatchmakingTriggerType) {
		this.completeMatchmakingTriggerType = completeMatchmakingTriggerType;
	}
	public CreateNamespaceRequest withCompleteMatchmakingTriggerType(String completeMatchmakingTriggerType) {
		this.completeMatchmakingTriggerType = completeMatchmakingTriggerType;
		return this;
	}
	public String getCompleteMatchmakingTriggerRealtimeNamespaceId() {
		return completeMatchmakingTriggerRealtimeNamespaceId;
	}
	public void setCompleteMatchmakingTriggerRealtimeNamespaceId(String completeMatchmakingTriggerRealtimeNamespaceId) {
		this.completeMatchmakingTriggerRealtimeNamespaceId = completeMatchmakingTriggerRealtimeNamespaceId;
	}
	public CreateNamespaceRequest withCompleteMatchmakingTriggerRealtimeNamespaceId(String completeMatchmakingTriggerRealtimeNamespaceId) {
		this.completeMatchmakingTriggerRealtimeNamespaceId = completeMatchmakingTriggerRealtimeNamespaceId;
		return this;
	}
	public String getCompleteMatchmakingTriggerScriptId() {
		return completeMatchmakingTriggerScriptId;
	}
	public void setCompleteMatchmakingTriggerScriptId(String completeMatchmakingTriggerScriptId) {
		this.completeMatchmakingTriggerScriptId = completeMatchmakingTriggerScriptId;
	}
	public CreateNamespaceRequest withCompleteMatchmakingTriggerScriptId(String completeMatchmakingTriggerScriptId) {
		this.completeMatchmakingTriggerScriptId = completeMatchmakingTriggerScriptId;
		return this;
	}
	public ScriptSetting getChangeRatingScript() {
		return changeRatingScript;
	}
	public void setChangeRatingScript(ScriptSetting changeRatingScript) {
		this.changeRatingScript = changeRatingScript;
	}
	public CreateNamespaceRequest withChangeRatingScript(ScriptSetting changeRatingScript) {
		this.changeRatingScript = changeRatingScript;
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
	public NotificationSetting getCompleteNotification() {
		return completeNotification;
	}
	public void setCompleteNotification(NotificationSetting completeNotification) {
		this.completeNotification = completeNotification;
	}
	public CreateNamespaceRequest withCompleteNotification(NotificationSetting completeNotification) {
		this.completeNotification = completeNotification;
		return this;
	}
	public NotificationSetting getChangeRatingNotification() {
		return changeRatingNotification;
	}
	public void setChangeRatingNotification(NotificationSetting changeRatingNotification) {
		this.changeRatingNotification = changeRatingNotification;
	}
	public CreateNamespaceRequest withChangeRatingNotification(NotificationSetting changeRatingNotification) {
		this.changeRatingNotification = changeRatingNotification;
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
            .withEnableRating(data.get("enableRating") == null || data.get("enableRating").isNull() ? null : data.get("enableRating").booleanValue())
            .withCreateGatheringTriggerType(data.get("createGatheringTriggerType") == null || data.get("createGatheringTriggerType").isNull() ? null : data.get("createGatheringTriggerType").asText())
            .withCreateGatheringTriggerRealtimeNamespaceId(data.get("createGatheringTriggerRealtimeNamespaceId") == null || data.get("createGatheringTriggerRealtimeNamespaceId").isNull() ? null : data.get("createGatheringTriggerRealtimeNamespaceId").asText())
            .withCreateGatheringTriggerScriptId(data.get("createGatheringTriggerScriptId") == null || data.get("createGatheringTriggerScriptId").isNull() ? null : data.get("createGatheringTriggerScriptId").asText())
            .withCompleteMatchmakingTriggerType(data.get("completeMatchmakingTriggerType") == null || data.get("completeMatchmakingTriggerType").isNull() ? null : data.get("completeMatchmakingTriggerType").asText())
            .withCompleteMatchmakingTriggerRealtimeNamespaceId(data.get("completeMatchmakingTriggerRealtimeNamespaceId") == null || data.get("completeMatchmakingTriggerRealtimeNamespaceId").isNull() ? null : data.get("completeMatchmakingTriggerRealtimeNamespaceId").asText())
            .withCompleteMatchmakingTriggerScriptId(data.get("completeMatchmakingTriggerScriptId") == null || data.get("completeMatchmakingTriggerScriptId").isNull() ? null : data.get("completeMatchmakingTriggerScriptId").asText())
            .withChangeRatingScript(data.get("changeRatingScript") == null || data.get("changeRatingScript").isNull() ? null : ScriptSetting.fromJson(data.get("changeRatingScript")))
            .withJoinNotification(data.get("joinNotification") == null || data.get("joinNotification").isNull() ? null : NotificationSetting.fromJson(data.get("joinNotification")))
            .withLeaveNotification(data.get("leaveNotification") == null || data.get("leaveNotification").isNull() ? null : NotificationSetting.fromJson(data.get("leaveNotification")))
            .withCompleteNotification(data.get("completeNotification") == null || data.get("completeNotification").isNull() ? null : NotificationSetting.fromJson(data.get("completeNotification")))
            .withChangeRatingNotification(data.get("changeRatingNotification") == null || data.get("changeRatingNotification").isNull() ? null : NotificationSetting.fromJson(data.get("changeRatingNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("enableRating", getEnableRating());
                put("createGatheringTriggerType", getCreateGatheringTriggerType());
                put("createGatheringTriggerRealtimeNamespaceId", getCreateGatheringTriggerRealtimeNamespaceId());
                put("createGatheringTriggerScriptId", getCreateGatheringTriggerScriptId());
                put("completeMatchmakingTriggerType", getCompleteMatchmakingTriggerType());
                put("completeMatchmakingTriggerRealtimeNamespaceId", getCompleteMatchmakingTriggerRealtimeNamespaceId());
                put("completeMatchmakingTriggerScriptId", getCompleteMatchmakingTriggerScriptId());
                put("changeRatingScript", getChangeRatingScript() != null ? getChangeRatingScript().toJson() : null);
                put("joinNotification", getJoinNotification() != null ? getJoinNotification().toJson() : null);
                put("leaveNotification", getLeaveNotification() != null ? getLeaveNotification().toJson() : null);
                put("completeNotification", getCompleteNotification() != null ? getCompleteNotification().toJson() : null);
                put("changeRatingNotification", getChangeRatingNotification() != null ? getChangeRatingNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}