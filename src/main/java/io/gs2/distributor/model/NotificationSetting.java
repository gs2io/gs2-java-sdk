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

package io.gs2.distributor.model;

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
public class NotificationSetting implements IModel, Serializable {
	private String gatewayNamespaceId;
	private Boolean enableTransferMobileNotification;
	private String sound;
	private String enable;
	public String getGatewayNamespaceId() {
		return gatewayNamespaceId;
	}
	public void setGatewayNamespaceId(String gatewayNamespaceId) {
		this.gatewayNamespaceId = gatewayNamespaceId;
	}
	public NotificationSetting withGatewayNamespaceId(String gatewayNamespaceId) {
		this.gatewayNamespaceId = gatewayNamespaceId;
		return this;
	}
	public Boolean getEnableTransferMobileNotification() {
		return enableTransferMobileNotification;
	}
	public void setEnableTransferMobileNotification(Boolean enableTransferMobileNotification) {
		this.enableTransferMobileNotification = enableTransferMobileNotification;
	}
	public NotificationSetting withEnableTransferMobileNotification(Boolean enableTransferMobileNotification) {
		this.enableTransferMobileNotification = enableTransferMobileNotification;
		return this;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public NotificationSetting withSound(String sound) {
		this.sound = sound;
		return this;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public NotificationSetting withEnable(String enable) {
		this.enable = enable;
		return this;
	}

    public static NotificationSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new NotificationSetting()
            .withGatewayNamespaceId(data.get("gatewayNamespaceId") == null || data.get("gatewayNamespaceId").isNull() ? null : data.get("gatewayNamespaceId").asText())
            .withEnableTransferMobileNotification(data.get("enableTransferMobileNotification") == null || data.get("enableTransferMobileNotification").isNull() ? null : data.get("enableTransferMobileNotification").booleanValue())
            .withSound(data.get("sound") == null || data.get("sound").isNull() ? null : data.get("sound").asText())
            .withEnable(data.get("enable") == null || data.get("enable").isNull() ? null : data.get("enable").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("gatewayNamespaceId", getGatewayNamespaceId());
                put("enableTransferMobileNotification", getEnableTransferMobileNotification());
                put("sound", getSound());
                put("enable", getEnable());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.gatewayNamespaceId == null) ? 0 : this.gatewayNamespaceId.hashCode());
        result = prime * result + ((this.enableTransferMobileNotification == null) ? 0 : this.enableTransferMobileNotification.hashCode());
        result = prime * result + ((this.sound == null) ? 0 : this.sound.hashCode());
        result = prime * result + ((this.enable == null) ? 0 : this.enable.hashCode());
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
		NotificationSetting other = (NotificationSetting) o;
		if (gatewayNamespaceId == null) {
			return other.gatewayNamespaceId == null;
		} else if (!gatewayNamespaceId.equals(other.gatewayNamespaceId)) {
			return false;
		}
		if (enableTransferMobileNotification == null) {
			return other.enableTransferMobileNotification == null;
		} else if (!enableTransferMobileNotification.equals(other.enableTransferMobileNotification)) {
			return false;
		}
		if (sound == null) {
			return other.sound == null;
		} else if (!sound.equals(other.sound)) {
			return false;
		}
		if (enable == null) {
			return other.enable == null;
		} else if (!enable.equals(other.enable)) {
			return false;
		}
		return true;
	}
}