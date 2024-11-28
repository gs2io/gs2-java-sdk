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

package io.gs2.distributor.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.distributor.model.NotificationSetting;
import io.gs2.distributor.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private String assumeUserId;
    private NotificationSetting autoRunStampSheetNotification;
    private NotificationSetting autoRunTransactionNotification;
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
	public String getAssumeUserId() {
		return assumeUserId;
	}
	public void setAssumeUserId(String assumeUserId) {
		this.assumeUserId = assumeUserId;
	}
	public CreateNamespaceRequest withAssumeUserId(String assumeUserId) {
		this.assumeUserId = assumeUserId;
		return this;
	}
	public NotificationSetting getAutoRunStampSheetNotification() {
		return autoRunStampSheetNotification;
	}
	public void setAutoRunStampSheetNotification(NotificationSetting autoRunStampSheetNotification) {
		this.autoRunStampSheetNotification = autoRunStampSheetNotification;
	}
	public CreateNamespaceRequest withAutoRunStampSheetNotification(NotificationSetting autoRunStampSheetNotification) {
		this.autoRunStampSheetNotification = autoRunStampSheetNotification;
		return this;
	}
	public NotificationSetting getAutoRunTransactionNotification() {
		return autoRunTransactionNotification;
	}
	public void setAutoRunTransactionNotification(NotificationSetting autoRunTransactionNotification) {
		this.autoRunTransactionNotification = autoRunTransactionNotification;
	}
	public CreateNamespaceRequest withAutoRunTransactionNotification(NotificationSetting autoRunTransactionNotification) {
		this.autoRunTransactionNotification = autoRunTransactionNotification;
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
            .withAssumeUserId(data.get("assumeUserId") == null || data.get("assumeUserId").isNull() ? null : data.get("assumeUserId").asText())
            .withAutoRunStampSheetNotification(data.get("autoRunStampSheetNotification") == null || data.get("autoRunStampSheetNotification").isNull() ? null : NotificationSetting.fromJson(data.get("autoRunStampSheetNotification")))
            .withAutoRunTransactionNotification(data.get("autoRunTransactionNotification") == null || data.get("autoRunTransactionNotification").isNull() ? null : NotificationSetting.fromJson(data.get("autoRunTransactionNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("assumeUserId", getAssumeUserId());
                put("autoRunStampSheetNotification", getAutoRunStampSheetNotification() != null ? getAutoRunStampSheetNotification().toJson() : null);
                put("autoRunTransactionNotification", getAutoRunTransactionNotification() != null ? getAutoRunTransactionNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}