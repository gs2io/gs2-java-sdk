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

package io.gs2.friend.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.friend.model.TransactionSetting;
import io.gs2.friend.model.ScriptSetting;
import io.gs2.friend.model.NotificationSetting;
import io.gs2.friend.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private TransactionSetting transactionSetting;
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
    private NotificationSetting cancelRequestNotification;
    private NotificationSetting acceptRequestNotification;
    private NotificationSetting rejectRequestNotification;
    private NotificationSetting deleteFriendNotification;
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
	public ScriptSetting getFollowScript() {
		return followScript;
	}
	public void setFollowScript(ScriptSetting followScript) {
		this.followScript = followScript;
	}
	public CreateNamespaceRequest withFollowScript(ScriptSetting followScript) {
		this.followScript = followScript;
		return this;
	}
	public ScriptSetting getUnfollowScript() {
		return unfollowScript;
	}
	public void setUnfollowScript(ScriptSetting unfollowScript) {
		this.unfollowScript = unfollowScript;
	}
	public CreateNamespaceRequest withUnfollowScript(ScriptSetting unfollowScript) {
		this.unfollowScript = unfollowScript;
		return this;
	}
	public ScriptSetting getSendRequestScript() {
		return sendRequestScript;
	}
	public void setSendRequestScript(ScriptSetting sendRequestScript) {
		this.sendRequestScript = sendRequestScript;
	}
	public CreateNamespaceRequest withSendRequestScript(ScriptSetting sendRequestScript) {
		this.sendRequestScript = sendRequestScript;
		return this;
	}
	public ScriptSetting getCancelRequestScript() {
		return cancelRequestScript;
	}
	public void setCancelRequestScript(ScriptSetting cancelRequestScript) {
		this.cancelRequestScript = cancelRequestScript;
	}
	public CreateNamespaceRequest withCancelRequestScript(ScriptSetting cancelRequestScript) {
		this.cancelRequestScript = cancelRequestScript;
		return this;
	}
	public ScriptSetting getAcceptRequestScript() {
		return acceptRequestScript;
	}
	public void setAcceptRequestScript(ScriptSetting acceptRequestScript) {
		this.acceptRequestScript = acceptRequestScript;
	}
	public CreateNamespaceRequest withAcceptRequestScript(ScriptSetting acceptRequestScript) {
		this.acceptRequestScript = acceptRequestScript;
		return this;
	}
	public ScriptSetting getRejectRequestScript() {
		return rejectRequestScript;
	}
	public void setRejectRequestScript(ScriptSetting rejectRequestScript) {
		this.rejectRequestScript = rejectRequestScript;
	}
	public CreateNamespaceRequest withRejectRequestScript(ScriptSetting rejectRequestScript) {
		this.rejectRequestScript = rejectRequestScript;
		return this;
	}
	public ScriptSetting getDeleteFriendScript() {
		return deleteFriendScript;
	}
	public void setDeleteFriendScript(ScriptSetting deleteFriendScript) {
		this.deleteFriendScript = deleteFriendScript;
	}
	public CreateNamespaceRequest withDeleteFriendScript(ScriptSetting deleteFriendScript) {
		this.deleteFriendScript = deleteFriendScript;
		return this;
	}
	public ScriptSetting getUpdateProfileScript() {
		return updateProfileScript;
	}
	public void setUpdateProfileScript(ScriptSetting updateProfileScript) {
		this.updateProfileScript = updateProfileScript;
	}
	public CreateNamespaceRequest withUpdateProfileScript(ScriptSetting updateProfileScript) {
		this.updateProfileScript = updateProfileScript;
		return this;
	}
	public NotificationSetting getFollowNotification() {
		return followNotification;
	}
	public void setFollowNotification(NotificationSetting followNotification) {
		this.followNotification = followNotification;
	}
	public CreateNamespaceRequest withFollowNotification(NotificationSetting followNotification) {
		this.followNotification = followNotification;
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
	public NotificationSetting getCancelRequestNotification() {
		return cancelRequestNotification;
	}
	public void setCancelRequestNotification(NotificationSetting cancelRequestNotification) {
		this.cancelRequestNotification = cancelRequestNotification;
	}
	public CreateNamespaceRequest withCancelRequestNotification(NotificationSetting cancelRequestNotification) {
		this.cancelRequestNotification = cancelRequestNotification;
		return this;
	}
	public NotificationSetting getAcceptRequestNotification() {
		return acceptRequestNotification;
	}
	public void setAcceptRequestNotification(NotificationSetting acceptRequestNotification) {
		this.acceptRequestNotification = acceptRequestNotification;
	}
	public CreateNamespaceRequest withAcceptRequestNotification(NotificationSetting acceptRequestNotification) {
		this.acceptRequestNotification = acceptRequestNotification;
		return this;
	}
	public NotificationSetting getRejectRequestNotification() {
		return rejectRequestNotification;
	}
	public void setRejectRequestNotification(NotificationSetting rejectRequestNotification) {
		this.rejectRequestNotification = rejectRequestNotification;
	}
	public CreateNamespaceRequest withRejectRequestNotification(NotificationSetting rejectRequestNotification) {
		this.rejectRequestNotification = rejectRequestNotification;
		return this;
	}
	public NotificationSetting getDeleteFriendNotification() {
		return deleteFriendNotification;
	}
	public void setDeleteFriendNotification(NotificationSetting deleteFriendNotification) {
		this.deleteFriendNotification = deleteFriendNotification;
	}
	public CreateNamespaceRequest withDeleteFriendNotification(NotificationSetting deleteFriendNotification) {
		this.deleteFriendNotification = deleteFriendNotification;
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
            .withCancelRequestNotification(data.get("cancelRequestNotification") == null || data.get("cancelRequestNotification").isNull() ? null : NotificationSetting.fromJson(data.get("cancelRequestNotification")))
            .withAcceptRequestNotification(data.get("acceptRequestNotification") == null || data.get("acceptRequestNotification").isNull() ? null : NotificationSetting.fromJson(data.get("acceptRequestNotification")))
            .withRejectRequestNotification(data.get("rejectRequestNotification") == null || data.get("rejectRequestNotification").isNull() ? null : NotificationSetting.fromJson(data.get("rejectRequestNotification")))
            .withDeleteFriendNotification(data.get("deleteFriendNotification") == null || data.get("deleteFriendNotification").isNull() ? null : NotificationSetting.fromJson(data.get("deleteFriendNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("transactionSetting", getTransactionSetting() != null ? getTransactionSetting().toJson() : null);
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
                put("cancelRequestNotification", getCancelRequestNotification() != null ? getCancelRequestNotification().toJson() : null);
                put("acceptRequestNotification", getAcceptRequestNotification() != null ? getAcceptRequestNotification().toJson() : null);
                put("rejectRequestNotification", getRejectRequestNotification() != null ? getRejectRequestNotification().toJson() : null);
                put("deleteFriendNotification", getDeleteFriendNotification() != null ? getDeleteFriendNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}