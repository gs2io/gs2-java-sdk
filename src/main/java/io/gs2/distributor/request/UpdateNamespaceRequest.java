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
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {
    private String namespaceName;
    private String description;
    private String assumeUserId;
    private NotificationSetting autoRunStampSheetNotification;
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

	public String getAssumeUserId() {
		return assumeUserId;
	}

	public void setAssumeUserId(String assumeUserId) {
		this.assumeUserId = assumeUserId;
	}

	public UpdateNamespaceRequest withAssumeUserId(String assumeUserId) {
		this.assumeUserId = assumeUserId;
		return this;
	}

	public NotificationSetting getAutoRunStampSheetNotification() {
		return autoRunStampSheetNotification;
	}

	public void setAutoRunStampSheetNotification(NotificationSetting autoRunStampSheetNotification) {
		this.autoRunStampSheetNotification = autoRunStampSheetNotification;
	}

	public UpdateNamespaceRequest withAutoRunStampSheetNotification(NotificationSetting autoRunStampSheetNotification) {
		this.autoRunStampSheetNotification = autoRunStampSheetNotification;
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
            .withAssumeUserId(data.get("assumeUserId") == null || data.get("assumeUserId").isNull() ? null : data.get("assumeUserId").asText())
            .withAutoRunStampSheetNotification(data.get("autoRunStampSheetNotification") == null || data.get("autoRunStampSheetNotification").isNull() ? null : NotificationSetting.fromJson(data.get("autoRunStampSheetNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("description", getDescription());
                put("assumeUserId", getAssumeUserId());
                put("autoRunStampSheetNotification", getAutoRunStampSheetNotification() != null ? getAutoRunStampSheetNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}