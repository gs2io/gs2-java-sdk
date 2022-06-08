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

package io.gs2.jobQueue.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.jobQueue.model.NotificationSetting;
import io.gs2.jobQueue.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {
    private String namespaceName;
    private String description;
    private Boolean enableAutoRun;
    private NotificationSetting pushNotification;
    private NotificationSetting runNotification;
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
	public Boolean getEnableAutoRun() {
		return enableAutoRun;
	}
	public void setEnableAutoRun(Boolean enableAutoRun) {
		this.enableAutoRun = enableAutoRun;
	}
	public UpdateNamespaceRequest withEnableAutoRun(Boolean enableAutoRun) {
		this.enableAutoRun = enableAutoRun;
		return this;
	}
	public NotificationSetting getPushNotification() {
		return pushNotification;
	}
	public void setPushNotification(NotificationSetting pushNotification) {
		this.pushNotification = pushNotification;
	}
	public UpdateNamespaceRequest withPushNotification(NotificationSetting pushNotification) {
		this.pushNotification = pushNotification;
		return this;
	}
	public NotificationSetting getRunNotification() {
		return runNotification;
	}
	public void setRunNotification(NotificationSetting runNotification) {
		this.runNotification = runNotification;
	}
	public UpdateNamespaceRequest withRunNotification(NotificationSetting runNotification) {
		this.runNotification = runNotification;
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
            .withEnableAutoRun(data.get("enableAutoRun") == null || data.get("enableAutoRun").isNull() ? null : data.get("enableAutoRun").booleanValue())
            .withPushNotification(data.get("pushNotification") == null || data.get("pushNotification").isNull() ? null : NotificationSetting.fromJson(data.get("pushNotification")))
            .withRunNotification(data.get("runNotification") == null || data.get("runNotification").isNull() ? null : NotificationSetting.fromJson(data.get("runNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("description", getDescription());
                put("enableAutoRun", getEnableAutoRun());
                put("pushNotification", getPushNotification() != null ? getPushNotification().toJson() : null);
                put("runNotification", getRunNotification() != null ? getRunNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}