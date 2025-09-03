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

package io.gs2.matchmaking.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Namespace implements IModel, Serializable, Comparable<Namespace> {
	private String namespaceId;
	private String name;
	private String description;
	private TransactionSetting transactionSetting;
	private Boolean enableRating;
	private String enableDisconnectDetection;
	private Integer disconnectDetectionTimeoutSeconds;
	private String createGatheringTriggerType;
	private String createGatheringTriggerRealtimeNamespaceId;
	private String createGatheringTriggerScriptId;
	private String completeMatchmakingTriggerType;
	private String completeMatchmakingTriggerRealtimeNamespaceId;
	private String completeMatchmakingTriggerScriptId;
	private String enableCollaborateSeasonRating;
	private String collaborateSeasonRatingNamespaceId;
	private Integer collaborateSeasonRatingTtl;
	private ScriptSetting changeRatingScript;
	private NotificationSetting joinNotification;
	private NotificationSetting leaveNotification;
	private NotificationSetting completeNotification;
	private NotificationSetting changeRatingNotification;
	private LogSetting logSetting;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getNamespaceId() {
		return namespaceId;
	}
	public void setNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
	}
	public Namespace withNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Namespace withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Namespace withDescription(String description) {
		this.description = description;
		return this;
	}
	public TransactionSetting getTransactionSetting() {
		return transactionSetting;
	}
	public void setTransactionSetting(TransactionSetting transactionSetting) {
		this.transactionSetting = transactionSetting;
	}
	public Namespace withTransactionSetting(TransactionSetting transactionSetting) {
		this.transactionSetting = transactionSetting;
		return this;
	}
	public Boolean getEnableRating() {
		return enableRating;
	}
	public void setEnableRating(Boolean enableRating) {
		this.enableRating = enableRating;
	}
	public Namespace withEnableRating(Boolean enableRating) {
		this.enableRating = enableRating;
		return this;
	}
	public String getEnableDisconnectDetection() {
		return enableDisconnectDetection;
	}
	public void setEnableDisconnectDetection(String enableDisconnectDetection) {
		this.enableDisconnectDetection = enableDisconnectDetection;
	}
	public Namespace withEnableDisconnectDetection(String enableDisconnectDetection) {
		this.enableDisconnectDetection = enableDisconnectDetection;
		return this;
	}
	public Integer getDisconnectDetectionTimeoutSeconds() {
		return disconnectDetectionTimeoutSeconds;
	}
	public void setDisconnectDetectionTimeoutSeconds(Integer disconnectDetectionTimeoutSeconds) {
		this.disconnectDetectionTimeoutSeconds = disconnectDetectionTimeoutSeconds;
	}
	public Namespace withDisconnectDetectionTimeoutSeconds(Integer disconnectDetectionTimeoutSeconds) {
		this.disconnectDetectionTimeoutSeconds = disconnectDetectionTimeoutSeconds;
		return this;
	}
	public String getCreateGatheringTriggerType() {
		return createGatheringTriggerType;
	}
	public void setCreateGatheringTriggerType(String createGatheringTriggerType) {
		this.createGatheringTriggerType = createGatheringTriggerType;
	}
	public Namespace withCreateGatheringTriggerType(String createGatheringTriggerType) {
		this.createGatheringTriggerType = createGatheringTriggerType;
		return this;
	}
	public String getCreateGatheringTriggerRealtimeNamespaceId() {
		return createGatheringTriggerRealtimeNamespaceId;
	}
	public void setCreateGatheringTriggerRealtimeNamespaceId(String createGatheringTriggerRealtimeNamespaceId) {
		this.createGatheringTriggerRealtimeNamespaceId = createGatheringTriggerRealtimeNamespaceId;
	}
	public Namespace withCreateGatheringTriggerRealtimeNamespaceId(String createGatheringTriggerRealtimeNamespaceId) {
		this.createGatheringTriggerRealtimeNamespaceId = createGatheringTriggerRealtimeNamespaceId;
		return this;
	}
	public String getCreateGatheringTriggerScriptId() {
		return createGatheringTriggerScriptId;
	}
	public void setCreateGatheringTriggerScriptId(String createGatheringTriggerScriptId) {
		this.createGatheringTriggerScriptId = createGatheringTriggerScriptId;
	}
	public Namespace withCreateGatheringTriggerScriptId(String createGatheringTriggerScriptId) {
		this.createGatheringTriggerScriptId = createGatheringTriggerScriptId;
		return this;
	}
	public String getCompleteMatchmakingTriggerType() {
		return completeMatchmakingTriggerType;
	}
	public void setCompleteMatchmakingTriggerType(String completeMatchmakingTriggerType) {
		this.completeMatchmakingTriggerType = completeMatchmakingTriggerType;
	}
	public Namespace withCompleteMatchmakingTriggerType(String completeMatchmakingTriggerType) {
		this.completeMatchmakingTriggerType = completeMatchmakingTriggerType;
		return this;
	}
	public String getCompleteMatchmakingTriggerRealtimeNamespaceId() {
		return completeMatchmakingTriggerRealtimeNamespaceId;
	}
	public void setCompleteMatchmakingTriggerRealtimeNamespaceId(String completeMatchmakingTriggerRealtimeNamespaceId) {
		this.completeMatchmakingTriggerRealtimeNamespaceId = completeMatchmakingTriggerRealtimeNamespaceId;
	}
	public Namespace withCompleteMatchmakingTriggerRealtimeNamespaceId(String completeMatchmakingTriggerRealtimeNamespaceId) {
		this.completeMatchmakingTriggerRealtimeNamespaceId = completeMatchmakingTriggerRealtimeNamespaceId;
		return this;
	}
	public String getCompleteMatchmakingTriggerScriptId() {
		return completeMatchmakingTriggerScriptId;
	}
	public void setCompleteMatchmakingTriggerScriptId(String completeMatchmakingTriggerScriptId) {
		this.completeMatchmakingTriggerScriptId = completeMatchmakingTriggerScriptId;
	}
	public Namespace withCompleteMatchmakingTriggerScriptId(String completeMatchmakingTriggerScriptId) {
		this.completeMatchmakingTriggerScriptId = completeMatchmakingTriggerScriptId;
		return this;
	}
	public String getEnableCollaborateSeasonRating() {
		return enableCollaborateSeasonRating;
	}
	public void setEnableCollaborateSeasonRating(String enableCollaborateSeasonRating) {
		this.enableCollaborateSeasonRating = enableCollaborateSeasonRating;
	}
	public Namespace withEnableCollaborateSeasonRating(String enableCollaborateSeasonRating) {
		this.enableCollaborateSeasonRating = enableCollaborateSeasonRating;
		return this;
	}
	public String getCollaborateSeasonRatingNamespaceId() {
		return collaborateSeasonRatingNamespaceId;
	}
	public void setCollaborateSeasonRatingNamespaceId(String collaborateSeasonRatingNamespaceId) {
		this.collaborateSeasonRatingNamespaceId = collaborateSeasonRatingNamespaceId;
	}
	public Namespace withCollaborateSeasonRatingNamespaceId(String collaborateSeasonRatingNamespaceId) {
		this.collaborateSeasonRatingNamespaceId = collaborateSeasonRatingNamespaceId;
		return this;
	}
	public Integer getCollaborateSeasonRatingTtl() {
		return collaborateSeasonRatingTtl;
	}
	public void setCollaborateSeasonRatingTtl(Integer collaborateSeasonRatingTtl) {
		this.collaborateSeasonRatingTtl = collaborateSeasonRatingTtl;
	}
	public Namespace withCollaborateSeasonRatingTtl(Integer collaborateSeasonRatingTtl) {
		this.collaborateSeasonRatingTtl = collaborateSeasonRatingTtl;
		return this;
	}
	public ScriptSetting getChangeRatingScript() {
		return changeRatingScript;
	}
	public void setChangeRatingScript(ScriptSetting changeRatingScript) {
		this.changeRatingScript = changeRatingScript;
	}
	public Namespace withChangeRatingScript(ScriptSetting changeRatingScript) {
		this.changeRatingScript = changeRatingScript;
		return this;
	}
	public NotificationSetting getJoinNotification() {
		return joinNotification;
	}
	public void setJoinNotification(NotificationSetting joinNotification) {
		this.joinNotification = joinNotification;
	}
	public Namespace withJoinNotification(NotificationSetting joinNotification) {
		this.joinNotification = joinNotification;
		return this;
	}
	public NotificationSetting getLeaveNotification() {
		return leaveNotification;
	}
	public void setLeaveNotification(NotificationSetting leaveNotification) {
		this.leaveNotification = leaveNotification;
	}
	public Namespace withLeaveNotification(NotificationSetting leaveNotification) {
		this.leaveNotification = leaveNotification;
		return this;
	}
	public NotificationSetting getCompleteNotification() {
		return completeNotification;
	}
	public void setCompleteNotification(NotificationSetting completeNotification) {
		this.completeNotification = completeNotification;
	}
	public Namespace withCompleteNotification(NotificationSetting completeNotification) {
		this.completeNotification = completeNotification;
		return this;
	}
	public NotificationSetting getChangeRatingNotification() {
		return changeRatingNotification;
	}
	public void setChangeRatingNotification(NotificationSetting changeRatingNotification) {
		this.changeRatingNotification = changeRatingNotification;
	}
	public Namespace withChangeRatingNotification(NotificationSetting changeRatingNotification) {
		this.changeRatingNotification = changeRatingNotification;
		return this;
	}
	public LogSetting getLogSetting() {
		return logSetting;
	}
	public void setLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
	}
	public Namespace withLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Namespace withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Namespace withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Namespace withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Namespace fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Namespace()
            .withNamespaceId(data.get("namespaceId") == null || data.get("namespaceId").isNull() ? null : data.get("namespaceId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withTransactionSetting(data.get("transactionSetting") == null || data.get("transactionSetting").isNull() ? null : TransactionSetting.fromJson(data.get("transactionSetting")))
            .withEnableRating(data.get("enableRating") == null || data.get("enableRating").isNull() ? null : data.get("enableRating").booleanValue())
            .withEnableDisconnectDetection(data.get("enableDisconnectDetection") == null || data.get("enableDisconnectDetection").isNull() ? null : data.get("enableDisconnectDetection").asText())
            .withDisconnectDetectionTimeoutSeconds(data.get("disconnectDetectionTimeoutSeconds") == null || data.get("disconnectDetectionTimeoutSeconds").isNull() ? null : data.get("disconnectDetectionTimeoutSeconds").intValue())
            .withCreateGatheringTriggerType(data.get("createGatheringTriggerType") == null || data.get("createGatheringTriggerType").isNull() ? null : data.get("createGatheringTriggerType").asText())
            .withCreateGatheringTriggerRealtimeNamespaceId(data.get("createGatheringTriggerRealtimeNamespaceId") == null || data.get("createGatheringTriggerRealtimeNamespaceId").isNull() ? null : data.get("createGatheringTriggerRealtimeNamespaceId").asText())
            .withCreateGatheringTriggerScriptId(data.get("createGatheringTriggerScriptId") == null || data.get("createGatheringTriggerScriptId").isNull() ? null : data.get("createGatheringTriggerScriptId").asText())
            .withCompleteMatchmakingTriggerType(data.get("completeMatchmakingTriggerType") == null || data.get("completeMatchmakingTriggerType").isNull() ? null : data.get("completeMatchmakingTriggerType").asText())
            .withCompleteMatchmakingTriggerRealtimeNamespaceId(data.get("completeMatchmakingTriggerRealtimeNamespaceId") == null || data.get("completeMatchmakingTriggerRealtimeNamespaceId").isNull() ? null : data.get("completeMatchmakingTriggerRealtimeNamespaceId").asText())
            .withCompleteMatchmakingTriggerScriptId(data.get("completeMatchmakingTriggerScriptId") == null || data.get("completeMatchmakingTriggerScriptId").isNull() ? null : data.get("completeMatchmakingTriggerScriptId").asText())
            .withEnableCollaborateSeasonRating(data.get("enableCollaborateSeasonRating") == null || data.get("enableCollaborateSeasonRating").isNull() ? null : data.get("enableCollaborateSeasonRating").asText())
            .withCollaborateSeasonRatingNamespaceId(data.get("collaborateSeasonRatingNamespaceId") == null || data.get("collaborateSeasonRatingNamespaceId").isNull() ? null : data.get("collaborateSeasonRatingNamespaceId").asText())
            .withCollaborateSeasonRatingTtl(data.get("collaborateSeasonRatingTtl") == null || data.get("collaborateSeasonRatingTtl").isNull() ? null : data.get("collaborateSeasonRatingTtl").intValue())
            .withChangeRatingScript(data.get("changeRatingScript") == null || data.get("changeRatingScript").isNull() ? null : ScriptSetting.fromJson(data.get("changeRatingScript")))
            .withJoinNotification(data.get("joinNotification") == null || data.get("joinNotification").isNull() ? null : NotificationSetting.fromJson(data.get("joinNotification")))
            .withLeaveNotification(data.get("leaveNotification") == null || data.get("leaveNotification").isNull() ? null : NotificationSetting.fromJson(data.get("leaveNotification")))
            .withCompleteNotification(data.get("completeNotification") == null || data.get("completeNotification").isNull() ? null : NotificationSetting.fromJson(data.get("completeNotification")))
            .withChangeRatingNotification(data.get("changeRatingNotification") == null || data.get("changeRatingNotification").isNull() ? null : NotificationSetting.fromJson(data.get("changeRatingNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceId", getNamespaceId());
                put("name", getName());
                put("description", getDescription());
                put("transactionSetting", getTransactionSetting() != null ? getTransactionSetting().toJson() : null);
                put("enableRating", getEnableRating());
                put("enableDisconnectDetection", getEnableDisconnectDetection());
                put("disconnectDetectionTimeoutSeconds", getDisconnectDetectionTimeoutSeconds());
                put("createGatheringTriggerType", getCreateGatheringTriggerType());
                put("createGatheringTriggerRealtimeNamespaceId", getCreateGatheringTriggerRealtimeNamespaceId());
                put("createGatheringTriggerScriptId", getCreateGatheringTriggerScriptId());
                put("completeMatchmakingTriggerType", getCompleteMatchmakingTriggerType());
                put("completeMatchmakingTriggerRealtimeNamespaceId", getCompleteMatchmakingTriggerRealtimeNamespaceId());
                put("completeMatchmakingTriggerScriptId", getCompleteMatchmakingTriggerScriptId());
                put("enableCollaborateSeasonRating", getEnableCollaborateSeasonRating());
                put("collaborateSeasonRatingNamespaceId", getCollaborateSeasonRatingNamespaceId());
                put("collaborateSeasonRatingTtl", getCollaborateSeasonRatingTtl());
                put("changeRatingScript", getChangeRatingScript() != null ? getChangeRatingScript().toJson() : null);
                put("joinNotification", getJoinNotification() != null ? getJoinNotification().toJson() : null);
                put("leaveNotification", getLeaveNotification() != null ? getLeaveNotification().toJson() : null);
                put("completeNotification", getCompleteNotification() != null ? getCompleteNotification().toJson() : null);
                put("changeRatingNotification", getChangeRatingNotification() != null ? getChangeRatingNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Namespace o) {
		return namespaceId.compareTo(o.namespaceId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.namespaceId == null) ? 0 : this.namespaceId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.transactionSetting == null) ? 0 : this.transactionSetting.hashCode());
        result = prime * result + ((this.enableRating == null) ? 0 : this.enableRating.hashCode());
        result = prime * result + ((this.enableDisconnectDetection == null) ? 0 : this.enableDisconnectDetection.hashCode());
        result = prime * result + ((this.disconnectDetectionTimeoutSeconds == null) ? 0 : this.disconnectDetectionTimeoutSeconds.hashCode());
        result = prime * result + ((this.createGatheringTriggerType == null) ? 0 : this.createGatheringTriggerType.hashCode());
        result = prime * result + ((this.createGatheringTriggerRealtimeNamespaceId == null) ? 0 : this.createGatheringTriggerRealtimeNamespaceId.hashCode());
        result = prime * result + ((this.createGatheringTriggerScriptId == null) ? 0 : this.createGatheringTriggerScriptId.hashCode());
        result = prime * result + ((this.completeMatchmakingTriggerType == null) ? 0 : this.completeMatchmakingTriggerType.hashCode());
        result = prime * result + ((this.completeMatchmakingTriggerRealtimeNamespaceId == null) ? 0 : this.completeMatchmakingTriggerRealtimeNamespaceId.hashCode());
        result = prime * result + ((this.completeMatchmakingTriggerScriptId == null) ? 0 : this.completeMatchmakingTriggerScriptId.hashCode());
        result = prime * result + ((this.enableCollaborateSeasonRating == null) ? 0 : this.enableCollaborateSeasonRating.hashCode());
        result = prime * result + ((this.collaborateSeasonRatingNamespaceId == null) ? 0 : this.collaborateSeasonRatingNamespaceId.hashCode());
        result = prime * result + ((this.collaborateSeasonRatingTtl == null) ? 0 : this.collaborateSeasonRatingTtl.hashCode());
        result = prime * result + ((this.changeRatingScript == null) ? 0 : this.changeRatingScript.hashCode());
        result = prime * result + ((this.joinNotification == null) ? 0 : this.joinNotification.hashCode());
        result = prime * result + ((this.leaveNotification == null) ? 0 : this.leaveNotification.hashCode());
        result = prime * result + ((this.completeNotification == null) ? 0 : this.completeNotification.hashCode());
        result = prime * result + ((this.changeRatingNotification == null) ? 0 : this.changeRatingNotification.hashCode());
        result = prime * result + ((this.logSetting == null) ? 0 : this.logSetting.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.revision == null) ? 0 : this.revision.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Namespace other = (Namespace) o;
		if (namespaceId == null) {
			return other.namespaceId == null;
		} else if (!namespaceId.equals(other.namespaceId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (transactionSetting == null) {
			return other.transactionSetting == null;
		} else if (!transactionSetting.equals(other.transactionSetting)) {
			return false;
		}
		if (enableRating == null) {
			return other.enableRating == null;
		} else if (!enableRating.equals(other.enableRating)) {
			return false;
		}
		if (enableDisconnectDetection == null) {
			return other.enableDisconnectDetection == null;
		} else if (!enableDisconnectDetection.equals(other.enableDisconnectDetection)) {
			return false;
		}
		if (disconnectDetectionTimeoutSeconds == null) {
			return other.disconnectDetectionTimeoutSeconds == null;
		} else if (!disconnectDetectionTimeoutSeconds.equals(other.disconnectDetectionTimeoutSeconds)) {
			return false;
		}
		if (createGatheringTriggerType == null) {
			return other.createGatheringTriggerType == null;
		} else if (!createGatheringTriggerType.equals(other.createGatheringTriggerType)) {
			return false;
		}
		if (createGatheringTriggerRealtimeNamespaceId == null) {
			return other.createGatheringTriggerRealtimeNamespaceId == null;
		} else if (!createGatheringTriggerRealtimeNamespaceId.equals(other.createGatheringTriggerRealtimeNamespaceId)) {
			return false;
		}
		if (createGatheringTriggerScriptId == null) {
			return other.createGatheringTriggerScriptId == null;
		} else if (!createGatheringTriggerScriptId.equals(other.createGatheringTriggerScriptId)) {
			return false;
		}
		if (completeMatchmakingTriggerType == null) {
			return other.completeMatchmakingTriggerType == null;
		} else if (!completeMatchmakingTriggerType.equals(other.completeMatchmakingTriggerType)) {
			return false;
		}
		if (completeMatchmakingTriggerRealtimeNamespaceId == null) {
			return other.completeMatchmakingTriggerRealtimeNamespaceId == null;
		} else if (!completeMatchmakingTriggerRealtimeNamespaceId.equals(other.completeMatchmakingTriggerRealtimeNamespaceId)) {
			return false;
		}
		if (completeMatchmakingTriggerScriptId == null) {
			return other.completeMatchmakingTriggerScriptId == null;
		} else if (!completeMatchmakingTriggerScriptId.equals(other.completeMatchmakingTriggerScriptId)) {
			return false;
		}
		if (enableCollaborateSeasonRating == null) {
			return other.enableCollaborateSeasonRating == null;
		} else if (!enableCollaborateSeasonRating.equals(other.enableCollaborateSeasonRating)) {
			return false;
		}
		if (collaborateSeasonRatingNamespaceId == null) {
			return other.collaborateSeasonRatingNamespaceId == null;
		} else if (!collaborateSeasonRatingNamespaceId.equals(other.collaborateSeasonRatingNamespaceId)) {
			return false;
		}
		if (collaborateSeasonRatingTtl == null) {
			return other.collaborateSeasonRatingTtl == null;
		} else if (!collaborateSeasonRatingTtl.equals(other.collaborateSeasonRatingTtl)) {
			return false;
		}
		if (changeRatingScript == null) {
			return other.changeRatingScript == null;
		} else if (!changeRatingScript.equals(other.changeRatingScript)) {
			return false;
		}
		if (joinNotification == null) {
			return other.joinNotification == null;
		} else if (!joinNotification.equals(other.joinNotification)) {
			return false;
		}
		if (leaveNotification == null) {
			return other.leaveNotification == null;
		} else if (!leaveNotification.equals(other.leaveNotification)) {
			return false;
		}
		if (completeNotification == null) {
			return other.completeNotification == null;
		} else if (!completeNotification.equals(other.completeNotification)) {
			return false;
		}
		if (changeRatingNotification == null) {
			return other.changeRatingNotification == null;
		} else if (!changeRatingNotification.equals(other.changeRatingNotification)) {
			return false;
		}
		if (logSetting == null) {
			return other.logSetting == null;
		} else if (!logSetting.equals(other.logSetting)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}