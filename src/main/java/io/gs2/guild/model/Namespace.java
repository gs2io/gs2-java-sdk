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

package io.gs2.guild.model;

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
	public NotificationSetting getChangeNotification() {
		return changeNotification;
	}
	public void setChangeNotification(NotificationSetting changeNotification) {
		this.changeNotification = changeNotification;
	}
	public Namespace withChangeNotification(NotificationSetting changeNotification) {
		this.changeNotification = changeNotification;
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
	public NotificationSetting getChangeMemberNotification() {
		return changeMemberNotification;
	}
	public void setChangeMemberNotification(NotificationSetting changeMemberNotification) {
		this.changeMemberNotification = changeMemberNotification;
	}
	public Namespace withChangeMemberNotification(NotificationSetting changeMemberNotification) {
		this.changeMemberNotification = changeMemberNotification;
		return this;
	}
	public NotificationSetting getReceiveRequestNotification() {
		return receiveRequestNotification;
	}
	public void setReceiveRequestNotification(NotificationSetting receiveRequestNotification) {
		this.receiveRequestNotification = receiveRequestNotification;
	}
	public Namespace withReceiveRequestNotification(NotificationSetting receiveRequestNotification) {
		this.receiveRequestNotification = receiveRequestNotification;
		return this;
	}
	public NotificationSetting getRemoveRequestNotification() {
		return removeRequestNotification;
	}
	public void setRemoveRequestNotification(NotificationSetting removeRequestNotification) {
		this.removeRequestNotification = removeRequestNotification;
	}
	public Namespace withRemoveRequestNotification(NotificationSetting removeRequestNotification) {
		this.removeRequestNotification = removeRequestNotification;
		return this;
	}
	public ScriptSetting getCreateGuildScript() {
		return createGuildScript;
	}
	public void setCreateGuildScript(ScriptSetting createGuildScript) {
		this.createGuildScript = createGuildScript;
	}
	public Namespace withCreateGuildScript(ScriptSetting createGuildScript) {
		this.createGuildScript = createGuildScript;
		return this;
	}
	public ScriptSetting getUpdateGuildScript() {
		return updateGuildScript;
	}
	public void setUpdateGuildScript(ScriptSetting updateGuildScript) {
		this.updateGuildScript = updateGuildScript;
	}
	public Namespace withUpdateGuildScript(ScriptSetting updateGuildScript) {
		this.updateGuildScript = updateGuildScript;
		return this;
	}
	public ScriptSetting getJoinGuildScript() {
		return joinGuildScript;
	}
	public void setJoinGuildScript(ScriptSetting joinGuildScript) {
		this.joinGuildScript = joinGuildScript;
	}
	public Namespace withJoinGuildScript(ScriptSetting joinGuildScript) {
		this.joinGuildScript = joinGuildScript;
		return this;
	}
	public ScriptSetting getReceiveJoinRequestScript() {
		return receiveJoinRequestScript;
	}
	public void setReceiveJoinRequestScript(ScriptSetting receiveJoinRequestScript) {
		this.receiveJoinRequestScript = receiveJoinRequestScript;
	}
	public Namespace withReceiveJoinRequestScript(ScriptSetting receiveJoinRequestScript) {
		this.receiveJoinRequestScript = receiveJoinRequestScript;
		return this;
	}
	public ScriptSetting getLeaveGuildScript() {
		return leaveGuildScript;
	}
	public void setLeaveGuildScript(ScriptSetting leaveGuildScript) {
		this.leaveGuildScript = leaveGuildScript;
	}
	public Namespace withLeaveGuildScript(ScriptSetting leaveGuildScript) {
		this.leaveGuildScript = leaveGuildScript;
		return this;
	}
	public ScriptSetting getChangeRoleScript() {
		return changeRoleScript;
	}
	public void setChangeRoleScript(ScriptSetting changeRoleScript) {
		this.changeRoleScript = changeRoleScript;
	}
	public Namespace withChangeRoleScript(ScriptSetting changeRoleScript) {
		this.changeRoleScript = changeRoleScript;
		return this;
	}
	public ScriptSetting getDeleteGuildScript() {
		return deleteGuildScript;
	}
	public void setDeleteGuildScript(ScriptSetting deleteGuildScript) {
		this.deleteGuildScript = deleteGuildScript;
	}
	public Namespace withDeleteGuildScript(ScriptSetting deleteGuildScript) {
		this.deleteGuildScript = deleteGuildScript;
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
        result = prime * result + ((this.changeNotification == null) ? 0 : this.changeNotification.hashCode());
        result = prime * result + ((this.joinNotification == null) ? 0 : this.joinNotification.hashCode());
        result = prime * result + ((this.leaveNotification == null) ? 0 : this.leaveNotification.hashCode());
        result = prime * result + ((this.changeMemberNotification == null) ? 0 : this.changeMemberNotification.hashCode());
        result = prime * result + ((this.receiveRequestNotification == null) ? 0 : this.receiveRequestNotification.hashCode());
        result = prime * result + ((this.removeRequestNotification == null) ? 0 : this.removeRequestNotification.hashCode());
        result = prime * result + ((this.createGuildScript == null) ? 0 : this.createGuildScript.hashCode());
        result = prime * result + ((this.updateGuildScript == null) ? 0 : this.updateGuildScript.hashCode());
        result = prime * result + ((this.joinGuildScript == null) ? 0 : this.joinGuildScript.hashCode());
        result = prime * result + ((this.receiveJoinRequestScript == null) ? 0 : this.receiveJoinRequestScript.hashCode());
        result = prime * result + ((this.leaveGuildScript == null) ? 0 : this.leaveGuildScript.hashCode());
        result = prime * result + ((this.changeRoleScript == null) ? 0 : this.changeRoleScript.hashCode());
        result = prime * result + ((this.deleteGuildScript == null) ? 0 : this.deleteGuildScript.hashCode());
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
		if (changeNotification == null) {
			return other.changeNotification == null;
		} else if (!changeNotification.equals(other.changeNotification)) {
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
		if (changeMemberNotification == null) {
			return other.changeMemberNotification == null;
		} else if (!changeMemberNotification.equals(other.changeMemberNotification)) {
			return false;
		}
		if (receiveRequestNotification == null) {
			return other.receiveRequestNotification == null;
		} else if (!receiveRequestNotification.equals(other.receiveRequestNotification)) {
			return false;
		}
		if (removeRequestNotification == null) {
			return other.removeRequestNotification == null;
		} else if (!removeRequestNotification.equals(other.removeRequestNotification)) {
			return false;
		}
		if (createGuildScript == null) {
			return other.createGuildScript == null;
		} else if (!createGuildScript.equals(other.createGuildScript)) {
			return false;
		}
		if (updateGuildScript == null) {
			return other.updateGuildScript == null;
		} else if (!updateGuildScript.equals(other.updateGuildScript)) {
			return false;
		}
		if (joinGuildScript == null) {
			return other.joinGuildScript == null;
		} else if (!joinGuildScript.equals(other.joinGuildScript)) {
			return false;
		}
		if (receiveJoinRequestScript == null) {
			return other.receiveJoinRequestScript == null;
		} else if (!receiveJoinRequestScript.equals(other.receiveJoinRequestScript)) {
			return false;
		}
		if (leaveGuildScript == null) {
			return other.leaveGuildScript == null;
		} else if (!leaveGuildScript.equals(other.leaveGuildScript)) {
			return false;
		}
		if (changeRoleScript == null) {
			return other.changeRoleScript == null;
		} else if (!changeRoleScript.equals(other.changeRoleScript)) {
			return false;
		}
		if (deleteGuildScript == null) {
			return other.deleteGuildScript == null;
		} else if (!deleteGuildScript.equals(other.deleteGuildScript)) {
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