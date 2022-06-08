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

package io.gs2.friend.model;

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
	private ScriptSetting followScript;
	private ScriptSetting unfollowScript;
	private ScriptSetting sendRequestScript;
	private ScriptSetting cancelRequestScript;
	private ScriptSetting acceptRequestScript;
	private ScriptSetting rejectRequestScript;
	private ScriptSetting deleteFriendScript;
	private ScriptSetting updateProfileScript;
	private NotificationSetting followNotification;
	private NotificationSetting receiveRequestNotification;
	private NotificationSetting acceptRequestNotification;
	private LogSetting logSetting;
	private Long createdAt;
	private Long updatedAt;
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
	public ScriptSetting getFollowScript() {
		return followScript;
	}
	public void setFollowScript(ScriptSetting followScript) {
		this.followScript = followScript;
	}
	public Namespace withFollowScript(ScriptSetting followScript) {
		this.followScript = followScript;
		return this;
	}
	public ScriptSetting getUnfollowScript() {
		return unfollowScript;
	}
	public void setUnfollowScript(ScriptSetting unfollowScript) {
		this.unfollowScript = unfollowScript;
	}
	public Namespace withUnfollowScript(ScriptSetting unfollowScript) {
		this.unfollowScript = unfollowScript;
		return this;
	}
	public ScriptSetting getSendRequestScript() {
		return sendRequestScript;
	}
	public void setSendRequestScript(ScriptSetting sendRequestScript) {
		this.sendRequestScript = sendRequestScript;
	}
	public Namespace withSendRequestScript(ScriptSetting sendRequestScript) {
		this.sendRequestScript = sendRequestScript;
		return this;
	}
	public ScriptSetting getCancelRequestScript() {
		return cancelRequestScript;
	}
	public void setCancelRequestScript(ScriptSetting cancelRequestScript) {
		this.cancelRequestScript = cancelRequestScript;
	}
	public Namespace withCancelRequestScript(ScriptSetting cancelRequestScript) {
		this.cancelRequestScript = cancelRequestScript;
		return this;
	}
	public ScriptSetting getAcceptRequestScript() {
		return acceptRequestScript;
	}
	public void setAcceptRequestScript(ScriptSetting acceptRequestScript) {
		this.acceptRequestScript = acceptRequestScript;
	}
	public Namespace withAcceptRequestScript(ScriptSetting acceptRequestScript) {
		this.acceptRequestScript = acceptRequestScript;
		return this;
	}
	public ScriptSetting getRejectRequestScript() {
		return rejectRequestScript;
	}
	public void setRejectRequestScript(ScriptSetting rejectRequestScript) {
		this.rejectRequestScript = rejectRequestScript;
	}
	public Namespace withRejectRequestScript(ScriptSetting rejectRequestScript) {
		this.rejectRequestScript = rejectRequestScript;
		return this;
	}
	public ScriptSetting getDeleteFriendScript() {
		return deleteFriendScript;
	}
	public void setDeleteFriendScript(ScriptSetting deleteFriendScript) {
		this.deleteFriendScript = deleteFriendScript;
	}
	public Namespace withDeleteFriendScript(ScriptSetting deleteFriendScript) {
		this.deleteFriendScript = deleteFriendScript;
		return this;
	}
	public ScriptSetting getUpdateProfileScript() {
		return updateProfileScript;
	}
	public void setUpdateProfileScript(ScriptSetting updateProfileScript) {
		this.updateProfileScript = updateProfileScript;
	}
	public Namespace withUpdateProfileScript(ScriptSetting updateProfileScript) {
		this.updateProfileScript = updateProfileScript;
		return this;
	}
	public NotificationSetting getFollowNotification() {
		return followNotification;
	}
	public void setFollowNotification(NotificationSetting followNotification) {
		this.followNotification = followNotification;
	}
	public Namespace withFollowNotification(NotificationSetting followNotification) {
		this.followNotification = followNotification;
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
	public NotificationSetting getAcceptRequestNotification() {
		return acceptRequestNotification;
	}
	public void setAcceptRequestNotification(NotificationSetting acceptRequestNotification) {
		this.acceptRequestNotification = acceptRequestNotification;
	}
	public Namespace withAcceptRequestNotification(NotificationSetting acceptRequestNotification) {
		this.acceptRequestNotification = acceptRequestNotification;
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

    public static Namespace fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Namespace()
            .withNamespaceId(data.get("namespaceId") == null || data.get("namespaceId").isNull() ? null : data.get("namespaceId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withFollowScript(data.get("followScript") == null || data.get("followScript").isNull() ? null : ScriptSetting.fromJson(data.get("followScript")))
            .withUnfollowScript(data.get("unfollowScript") == null || data.get("unfollowScript").isNull() ? null : ScriptSetting.fromJson(data.get("unfollowScript")))
            .withSendRequestScript(data.get("sendRequestScript") == null || data.get("sendRequestScript").isNull() ? null : ScriptSetting.fromJson(data.get("sendRequestScript")))
            .withCancelRequestScript(data.get("cancelRequestScript") == null || data.get("cancelRequestScript").isNull() ? null : ScriptSetting.fromJson(data.get("cancelRequestScript")))
            .withAcceptRequestScript(data.get("acceptRequestScript") == null || data.get("acceptRequestScript").isNull() ? null : ScriptSetting.fromJson(data.get("acceptRequestScript")))
            .withRejectRequestScript(data.get("rejectRequestScript") == null || data.get("rejectRequestScript").isNull() ? null : ScriptSetting.fromJson(data.get("rejectRequestScript")))
            .withDeleteFriendScript(data.get("deleteFriendScript") == null || data.get("deleteFriendScript").isNull() ? null : ScriptSetting.fromJson(data.get("deleteFriendScript")))
            .withUpdateProfileScript(data.get("updateProfileScript") == null || data.get("updateProfileScript").isNull() ? null : ScriptSetting.fromJson(data.get("updateProfileScript")))
            .withFollowNotification(data.get("followNotification") == null || data.get("followNotification").isNull() ? null : NotificationSetting.fromJson(data.get("followNotification")))
            .withReceiveRequestNotification(data.get("receiveRequestNotification") == null || data.get("receiveRequestNotification").isNull() ? null : NotificationSetting.fromJson(data.get("receiveRequestNotification")))
            .withAcceptRequestNotification(data.get("acceptRequestNotification") == null || data.get("acceptRequestNotification").isNull() ? null : NotificationSetting.fromJson(data.get("acceptRequestNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceId", getNamespaceId());
                put("name", getName());
                put("description", getDescription());
                put("followScript", getFollowScript() != null ? getFollowScript().toJson() : null);
                put("unfollowScript", getUnfollowScript() != null ? getUnfollowScript().toJson() : null);
                put("sendRequestScript", getSendRequestScript() != null ? getSendRequestScript().toJson() : null);
                put("cancelRequestScript", getCancelRequestScript() != null ? getCancelRequestScript().toJson() : null);
                put("acceptRequestScript", getAcceptRequestScript() != null ? getAcceptRequestScript().toJson() : null);
                put("rejectRequestScript", getRejectRequestScript() != null ? getRejectRequestScript().toJson() : null);
                put("deleteFriendScript", getDeleteFriendScript() != null ? getDeleteFriendScript().toJson() : null);
                put("updateProfileScript", getUpdateProfileScript() != null ? getUpdateProfileScript().toJson() : null);
                put("followNotification", getFollowNotification() != null ? getFollowNotification().toJson() : null);
                put("receiveRequestNotification", getReceiveRequestNotification() != null ? getReceiveRequestNotification().toJson() : null);
                put("acceptRequestNotification", getAcceptRequestNotification() != null ? getAcceptRequestNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
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
        result = prime * result + ((this.followScript == null) ? 0 : this.followScript.hashCode());
        result = prime * result + ((this.unfollowScript == null) ? 0 : this.unfollowScript.hashCode());
        result = prime * result + ((this.sendRequestScript == null) ? 0 : this.sendRequestScript.hashCode());
        result = prime * result + ((this.cancelRequestScript == null) ? 0 : this.cancelRequestScript.hashCode());
        result = prime * result + ((this.acceptRequestScript == null) ? 0 : this.acceptRequestScript.hashCode());
        result = prime * result + ((this.rejectRequestScript == null) ? 0 : this.rejectRequestScript.hashCode());
        result = prime * result + ((this.deleteFriendScript == null) ? 0 : this.deleteFriendScript.hashCode());
        result = prime * result + ((this.updateProfileScript == null) ? 0 : this.updateProfileScript.hashCode());
        result = prime * result + ((this.followNotification == null) ? 0 : this.followNotification.hashCode());
        result = prime * result + ((this.receiveRequestNotification == null) ? 0 : this.receiveRequestNotification.hashCode());
        result = prime * result + ((this.acceptRequestNotification == null) ? 0 : this.acceptRequestNotification.hashCode());
        result = prime * result + ((this.logSetting == null) ? 0 : this.logSetting.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		if (followScript == null) {
			return other.followScript == null;
		} else if (!followScript.equals(other.followScript)) {
			return false;
		}
		if (unfollowScript == null) {
			return other.unfollowScript == null;
		} else if (!unfollowScript.equals(other.unfollowScript)) {
			return false;
		}
		if (sendRequestScript == null) {
			return other.sendRequestScript == null;
		} else if (!sendRequestScript.equals(other.sendRequestScript)) {
			return false;
		}
		if (cancelRequestScript == null) {
			return other.cancelRequestScript == null;
		} else if (!cancelRequestScript.equals(other.cancelRequestScript)) {
			return false;
		}
		if (acceptRequestScript == null) {
			return other.acceptRequestScript == null;
		} else if (!acceptRequestScript.equals(other.acceptRequestScript)) {
			return false;
		}
		if (rejectRequestScript == null) {
			return other.rejectRequestScript == null;
		} else if (!rejectRequestScript.equals(other.rejectRequestScript)) {
			return false;
		}
		if (deleteFriendScript == null) {
			return other.deleteFriendScript == null;
		} else if (!deleteFriendScript.equals(other.deleteFriendScript)) {
			return false;
		}
		if (updateProfileScript == null) {
			return other.updateProfileScript == null;
		} else if (!updateProfileScript.equals(other.updateProfileScript)) {
			return false;
		}
		if (followNotification == null) {
			return other.followNotification == null;
		} else if (!followNotification.equals(other.followNotification)) {
			return false;
		}
		if (receiveRequestNotification == null) {
			return other.receiveRequestNotification == null;
		} else if (!receiveRequestNotification.equals(other.receiveRequestNotification)) {
			return false;
		}
		if (acceptRequestNotification == null) {
			return other.acceptRequestNotification == null;
		} else if (!acceptRequestNotification.equals(other.acceptRequestNotification)) {
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
		return true;
	}
}