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

package io.gs2.mission.model;

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
	private ScriptSetting missionCompleteScript;
	private ScriptSetting counterIncrementScript;
	private ScriptSetting receiveRewardsScript;
	private NotificationSetting completeNotification;
	private LogSetting logSetting;
	private Long createdAt;
	private Long updatedAt;
	private String queueNamespaceId;
	private String keyId;
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
	public ScriptSetting getMissionCompleteScript() {
		return missionCompleteScript;
	}
	public void setMissionCompleteScript(ScriptSetting missionCompleteScript) {
		this.missionCompleteScript = missionCompleteScript;
	}
	public Namespace withMissionCompleteScript(ScriptSetting missionCompleteScript) {
		this.missionCompleteScript = missionCompleteScript;
		return this;
	}
	public ScriptSetting getCounterIncrementScript() {
		return counterIncrementScript;
	}
	public void setCounterIncrementScript(ScriptSetting counterIncrementScript) {
		this.counterIncrementScript = counterIncrementScript;
	}
	public Namespace withCounterIncrementScript(ScriptSetting counterIncrementScript) {
		this.counterIncrementScript = counterIncrementScript;
		return this;
	}
	public ScriptSetting getReceiveRewardsScript() {
		return receiveRewardsScript;
	}
	public void setReceiveRewardsScript(ScriptSetting receiveRewardsScript) {
		this.receiveRewardsScript = receiveRewardsScript;
	}
	public Namespace withReceiveRewardsScript(ScriptSetting receiveRewardsScript) {
		this.receiveRewardsScript = receiveRewardsScript;
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
    @Deprecated
	public String getQueueNamespaceId() {
		return queueNamespaceId;
	}
    @Deprecated
	public void setQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
	}
    @Deprecated
	public Namespace withQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
		return this;
	}
    @Deprecated
	public String getKeyId() {
		return keyId;
	}
    @Deprecated
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
    @Deprecated
	public Namespace withKeyId(String keyId) {
		this.keyId = keyId;
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
            .withMissionCompleteScript(data.get("missionCompleteScript") == null || data.get("missionCompleteScript").isNull() ? null : ScriptSetting.fromJson(data.get("missionCompleteScript")))
            .withCounterIncrementScript(data.get("counterIncrementScript") == null || data.get("counterIncrementScript").isNull() ? null : ScriptSetting.fromJson(data.get("counterIncrementScript")))
            .withReceiveRewardsScript(data.get("receiveRewardsScript") == null || data.get("receiveRewardsScript").isNull() ? null : ScriptSetting.fromJson(data.get("receiveRewardsScript")))
            .withCompleteNotification(data.get("completeNotification") == null || data.get("completeNotification").isNull() ? null : NotificationSetting.fromJson(data.get("completeNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withQueueNamespaceId(data.get("queueNamespaceId") == null || data.get("queueNamespaceId").isNull() ? null : data.get("queueNamespaceId").asText())
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceId", getNamespaceId());
                put("name", getName());
                put("description", getDescription());
                put("transactionSetting", getTransactionSetting() != null ? getTransactionSetting().toJson() : null);
                put("missionCompleteScript", getMissionCompleteScript() != null ? getMissionCompleteScript().toJson() : null);
                put("counterIncrementScript", getCounterIncrementScript() != null ? getCounterIncrementScript().toJson() : null);
                put("receiveRewardsScript", getReceiveRewardsScript() != null ? getReceiveRewardsScript().toJson() : null);
                put("completeNotification", getCompleteNotification() != null ? getCompleteNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("queueNamespaceId", getQueueNamespaceId());
                put("keyId", getKeyId());
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
        result = prime * result + ((this.missionCompleteScript == null) ? 0 : this.missionCompleteScript.hashCode());
        result = prime * result + ((this.counterIncrementScript == null) ? 0 : this.counterIncrementScript.hashCode());
        result = prime * result + ((this.receiveRewardsScript == null) ? 0 : this.receiveRewardsScript.hashCode());
        result = prime * result + ((this.completeNotification == null) ? 0 : this.completeNotification.hashCode());
        result = prime * result + ((this.logSetting == null) ? 0 : this.logSetting.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.queueNamespaceId == null) ? 0 : this.queueNamespaceId.hashCode());
        result = prime * result + ((this.keyId == null) ? 0 : this.keyId.hashCode());
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
		if (missionCompleteScript == null) {
			return other.missionCompleteScript == null;
		} else if (!missionCompleteScript.equals(other.missionCompleteScript)) {
			return false;
		}
		if (counterIncrementScript == null) {
			return other.counterIncrementScript == null;
		} else if (!counterIncrementScript.equals(other.counterIncrementScript)) {
			return false;
		}
		if (receiveRewardsScript == null) {
			return other.receiveRewardsScript == null;
		} else if (!receiveRewardsScript.equals(other.receiveRewardsScript)) {
			return false;
		}
		if (completeNotification == null) {
			return other.completeNotification == null;
		} else if (!completeNotification.equals(other.completeNotification)) {
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
		if (queueNamespaceId == null) {
			return other.queueNamespaceId == null;
		} else if (!queueNamespaceId.equals(other.queueNamespaceId)) {
			return false;
		}
		if (keyId == null) {
			return other.keyId == null;
		} else if (!keyId.equals(other.keyId)) {
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