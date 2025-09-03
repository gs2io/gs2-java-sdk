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
import io.gs2.jobQueue.model.TransactionSetting;
import io.gs2.jobQueue.model.NotificationSetting;
import io.gs2.jobQueue.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private TransactionSetting transactionSetting;
    private Boolean enableAutoRun;
    private NotificationSetting pushNotification;
    private NotificationSetting runNotification;
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
	public Boolean getEnableAutoRun() {
		return enableAutoRun;
	}
	public void setEnableAutoRun(Boolean enableAutoRun) {
		this.enableAutoRun = enableAutoRun;
	}
	public CreateNamespaceRequest withEnableAutoRun(Boolean enableAutoRun) {
		this.enableAutoRun = enableAutoRun;
		return this;
	}
	public NotificationSetting getPushNotification() {
		return pushNotification;
	}
	public void setPushNotification(NotificationSetting pushNotification) {
		this.pushNotification = pushNotification;
	}
	public CreateNamespaceRequest withPushNotification(NotificationSetting pushNotification) {
		this.pushNotification = pushNotification;
		return this;
	}
	public NotificationSetting getRunNotification() {
		return runNotification;
	}
	public void setRunNotification(NotificationSetting runNotification) {
		this.runNotification = runNotification;
	}
	public CreateNamespaceRequest withRunNotification(NotificationSetting runNotification) {
		this.runNotification = runNotification;
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
            .withEnableAutoRun(data.get("enableAutoRun") == null || data.get("enableAutoRun").isNull() ? null : data.get("enableAutoRun").booleanValue())
            .withPushNotification(data.get("pushNotification") == null || data.get("pushNotification").isNull() ? null : NotificationSetting.fromJson(data.get("pushNotification")))
            .withRunNotification(data.get("runNotification") == null || data.get("runNotification").isNull() ? null : NotificationSetting.fromJson(data.get("runNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("transactionSetting", getTransactionSetting() != null ? getTransactionSetting().toJson() : null);
                put("enableAutoRun", getEnableAutoRun());
                put("pushNotification", getPushNotification() != null ? getPushNotification().toJson() : null);
                put("runNotification", getRunNotification() != null ? getRunNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}