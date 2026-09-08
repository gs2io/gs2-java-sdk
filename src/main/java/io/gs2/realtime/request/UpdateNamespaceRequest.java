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

package io.gs2.realtime.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.realtime.model.TransactionSetting;
import io.gs2.realtime.model.TransactionSettingV2;
import io.gs2.realtime.model.NotificationSetting;
import io.gs2.realtime.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {
    private String namespaceName;
    private String description;
    private TransactionSetting transactionSetting;
    private TransactionSettingV2 transactionSettingV2;
    private String serverType;
    private String serverSpec;
    private NotificationSetting createNotification;
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
    @Deprecated
	public TransactionSetting getTransactionSetting() {
		return transactionSetting;
	}
    @Deprecated
	public void setTransactionSetting(TransactionSetting transactionSetting) {
		this.transactionSetting = transactionSetting;
	}
    @Deprecated
	public UpdateNamespaceRequest withTransactionSetting(TransactionSetting transactionSetting) {
		this.transactionSetting = transactionSetting;
		return this;
	}
	public TransactionSettingV2 getTransactionSettingV2() {
		return transactionSettingV2;
	}
	public void setTransactionSettingV2(TransactionSettingV2 transactionSettingV2) {
		this.transactionSettingV2 = transactionSettingV2;
	}
	public UpdateNamespaceRequest withTransactionSettingV2(TransactionSettingV2 transactionSettingV2) {
		this.transactionSettingV2 = transactionSettingV2;
		return this;
	}
	public String getServerType() {
		return serverType;
	}
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	public UpdateNamespaceRequest withServerType(String serverType) {
		this.serverType = serverType;
		return this;
	}
	public String getServerSpec() {
		return serverSpec;
	}
	public void setServerSpec(String serverSpec) {
		this.serverSpec = serverSpec;
	}
	public UpdateNamespaceRequest withServerSpec(String serverSpec) {
		this.serverSpec = serverSpec;
		return this;
	}
	public NotificationSetting getCreateNotification() {
		return createNotification;
	}
	public void setCreateNotification(NotificationSetting createNotification) {
		this.createNotification = createNotification;
	}
	public UpdateNamespaceRequest withCreateNotification(NotificationSetting createNotification) {
		this.createNotification = createNotification;
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
            .withTransactionSetting(data.get("transactionSetting") == null || data.get("transactionSetting").isNull() ? null : TransactionSetting.fromJson(data.get("transactionSetting")))
            .withTransactionSettingV2(data.get("transactionSettingV2") == null || data.get("transactionSettingV2").isNull() ? null : TransactionSettingV2.fromJson(data.get("transactionSettingV2")))
            .withServerType(data.get("serverType") == null || data.get("serverType").isNull() ? null : data.get("serverType").asText())
            .withServerSpec(data.get("serverSpec") == null || data.get("serverSpec").isNull() ? null : data.get("serverSpec").asText())
            .withCreateNotification(data.get("createNotification") == null || data.get("createNotification").isNull() ? null : NotificationSetting.fromJson(data.get("createNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("description", getDescription());
                put("transactionSetting", getTransactionSetting() != null ? getTransactionSetting().toJson() : null);
                put("transactionSettingV2", getTransactionSettingV2() != null ? getTransactionSettingV2().toJson() : null);
                put("serverType", getServerType());
                put("serverSpec", getServerSpec());
                put("createNotification", getCreateNotification() != null ? getCreateNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}