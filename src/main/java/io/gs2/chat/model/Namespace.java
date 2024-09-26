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

package io.gs2.chat.model;

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
	private Boolean allowCreateRoom;
	private Integer messageLifeTimeDays;
	private ScriptSetting postMessageScript;
	private ScriptSetting createRoomScript;
	private ScriptSetting deleteRoomScript;
	private ScriptSetting subscribeRoomScript;
	private ScriptSetting unsubscribeRoomScript;
	private NotificationSetting postNotification;
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
	public Boolean getAllowCreateRoom() {
		return allowCreateRoom;
	}
	public void setAllowCreateRoom(Boolean allowCreateRoom) {
		this.allowCreateRoom = allowCreateRoom;
	}
	public Namespace withAllowCreateRoom(Boolean allowCreateRoom) {
		this.allowCreateRoom = allowCreateRoom;
		return this;
	}
	public Integer getMessageLifeTimeDays() {
		return messageLifeTimeDays;
	}
	public void setMessageLifeTimeDays(Integer messageLifeTimeDays) {
		this.messageLifeTimeDays = messageLifeTimeDays;
	}
	public Namespace withMessageLifeTimeDays(Integer messageLifeTimeDays) {
		this.messageLifeTimeDays = messageLifeTimeDays;
		return this;
	}
	public ScriptSetting getPostMessageScript() {
		return postMessageScript;
	}
	public void setPostMessageScript(ScriptSetting postMessageScript) {
		this.postMessageScript = postMessageScript;
	}
	public Namespace withPostMessageScript(ScriptSetting postMessageScript) {
		this.postMessageScript = postMessageScript;
		return this;
	}
	public ScriptSetting getCreateRoomScript() {
		return createRoomScript;
	}
	public void setCreateRoomScript(ScriptSetting createRoomScript) {
		this.createRoomScript = createRoomScript;
	}
	public Namespace withCreateRoomScript(ScriptSetting createRoomScript) {
		this.createRoomScript = createRoomScript;
		return this;
	}
	public ScriptSetting getDeleteRoomScript() {
		return deleteRoomScript;
	}
	public void setDeleteRoomScript(ScriptSetting deleteRoomScript) {
		this.deleteRoomScript = deleteRoomScript;
	}
	public Namespace withDeleteRoomScript(ScriptSetting deleteRoomScript) {
		this.deleteRoomScript = deleteRoomScript;
		return this;
	}
	public ScriptSetting getSubscribeRoomScript() {
		return subscribeRoomScript;
	}
	public void setSubscribeRoomScript(ScriptSetting subscribeRoomScript) {
		this.subscribeRoomScript = subscribeRoomScript;
	}
	public Namespace withSubscribeRoomScript(ScriptSetting subscribeRoomScript) {
		this.subscribeRoomScript = subscribeRoomScript;
		return this;
	}
	public ScriptSetting getUnsubscribeRoomScript() {
		return unsubscribeRoomScript;
	}
	public void setUnsubscribeRoomScript(ScriptSetting unsubscribeRoomScript) {
		this.unsubscribeRoomScript = unsubscribeRoomScript;
	}
	public Namespace withUnsubscribeRoomScript(ScriptSetting unsubscribeRoomScript) {
		this.unsubscribeRoomScript = unsubscribeRoomScript;
		return this;
	}
	public NotificationSetting getPostNotification() {
		return postNotification;
	}
	public void setPostNotification(NotificationSetting postNotification) {
		this.postNotification = postNotification;
	}
	public Namespace withPostNotification(NotificationSetting postNotification) {
		this.postNotification = postNotification;
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
            .withAllowCreateRoom(data.get("allowCreateRoom") == null || data.get("allowCreateRoom").isNull() ? null : data.get("allowCreateRoom").booleanValue())
            .withMessageLifeTimeDays(data.get("messageLifeTimeDays") == null || data.get("messageLifeTimeDays").isNull() ? null : data.get("messageLifeTimeDays").intValue())
            .withPostMessageScript(data.get("postMessageScript") == null || data.get("postMessageScript").isNull() ? null : ScriptSetting.fromJson(data.get("postMessageScript")))
            .withCreateRoomScript(data.get("createRoomScript") == null || data.get("createRoomScript").isNull() ? null : ScriptSetting.fromJson(data.get("createRoomScript")))
            .withDeleteRoomScript(data.get("deleteRoomScript") == null || data.get("deleteRoomScript").isNull() ? null : ScriptSetting.fromJson(data.get("deleteRoomScript")))
            .withSubscribeRoomScript(data.get("subscribeRoomScript") == null || data.get("subscribeRoomScript").isNull() ? null : ScriptSetting.fromJson(data.get("subscribeRoomScript")))
            .withUnsubscribeRoomScript(data.get("unsubscribeRoomScript") == null || data.get("unsubscribeRoomScript").isNull() ? null : ScriptSetting.fromJson(data.get("unsubscribeRoomScript")))
            .withPostNotification(data.get("postNotification") == null || data.get("postNotification").isNull() ? null : NotificationSetting.fromJson(data.get("postNotification")))
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
                put("allowCreateRoom", getAllowCreateRoom());
                put("messageLifeTimeDays", getMessageLifeTimeDays());
                put("postMessageScript", getPostMessageScript() != null ? getPostMessageScript().toJson() : null);
                put("createRoomScript", getCreateRoomScript() != null ? getCreateRoomScript().toJson() : null);
                put("deleteRoomScript", getDeleteRoomScript() != null ? getDeleteRoomScript().toJson() : null);
                put("subscribeRoomScript", getSubscribeRoomScript() != null ? getSubscribeRoomScript().toJson() : null);
                put("unsubscribeRoomScript", getUnsubscribeRoomScript() != null ? getUnsubscribeRoomScript().toJson() : null);
                put("postNotification", getPostNotification() != null ? getPostNotification().toJson() : null);
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
        result = prime * result + ((this.allowCreateRoom == null) ? 0 : this.allowCreateRoom.hashCode());
        result = prime * result + ((this.messageLifeTimeDays == null) ? 0 : this.messageLifeTimeDays.hashCode());
        result = prime * result + ((this.postMessageScript == null) ? 0 : this.postMessageScript.hashCode());
        result = prime * result + ((this.createRoomScript == null) ? 0 : this.createRoomScript.hashCode());
        result = prime * result + ((this.deleteRoomScript == null) ? 0 : this.deleteRoomScript.hashCode());
        result = prime * result + ((this.subscribeRoomScript == null) ? 0 : this.subscribeRoomScript.hashCode());
        result = prime * result + ((this.unsubscribeRoomScript == null) ? 0 : this.unsubscribeRoomScript.hashCode());
        result = prime * result + ((this.postNotification == null) ? 0 : this.postNotification.hashCode());
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
		if (allowCreateRoom == null) {
			return other.allowCreateRoom == null;
		} else if (!allowCreateRoom.equals(other.allowCreateRoom)) {
			return false;
		}
		if (messageLifeTimeDays == null) {
			return other.messageLifeTimeDays == null;
		} else if (!messageLifeTimeDays.equals(other.messageLifeTimeDays)) {
			return false;
		}
		if (postMessageScript == null) {
			return other.postMessageScript == null;
		} else if (!postMessageScript.equals(other.postMessageScript)) {
			return false;
		}
		if (createRoomScript == null) {
			return other.createRoomScript == null;
		} else if (!createRoomScript.equals(other.createRoomScript)) {
			return false;
		}
		if (deleteRoomScript == null) {
			return other.deleteRoomScript == null;
		} else if (!deleteRoomScript.equals(other.deleteRoomScript)) {
			return false;
		}
		if (subscribeRoomScript == null) {
			return other.subscribeRoomScript == null;
		} else if (!subscribeRoomScript.equals(other.subscribeRoomScript)) {
			return false;
		}
		if (unsubscribeRoomScript == null) {
			return other.unsubscribeRoomScript == null;
		} else if (!unsubscribeRoomScript.equals(other.unsubscribeRoomScript)) {
			return false;
		}
		if (postNotification == null) {
			return other.postNotification == null;
		} else if (!postNotification.equals(other.postNotification)) {
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