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

package io.gs2.gateway.model;

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
public class SendNotificationEntry implements IModel, Serializable {
	private String userId;
	private String issuer;
	private String subject;
	private String payload;
	private Boolean enableTransferMobileNotification;
	private String sound;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SendNotificationEntry withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public SendNotificationEntry withIssuer(String issuer) {
		this.issuer = issuer;
		return this;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public SendNotificationEntry withSubject(String subject) {
		this.subject = subject;
		return this;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public SendNotificationEntry withPayload(String payload) {
		this.payload = payload;
		return this;
	}
	public Boolean getEnableTransferMobileNotification() {
		return enableTransferMobileNotification;
	}
	public void setEnableTransferMobileNotification(Boolean enableTransferMobileNotification) {
		this.enableTransferMobileNotification = enableTransferMobileNotification;
	}
	public SendNotificationEntry withEnableTransferMobileNotification(Boolean enableTransferMobileNotification) {
		this.enableTransferMobileNotification = enableTransferMobileNotification;
		return this;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public SendNotificationEntry withSound(String sound) {
		this.sound = sound;
		return this;
	}

    public static SendNotificationEntry fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SendNotificationEntry()
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withIssuer(data.get("issuer") == null || data.get("issuer").isNull() ? null : data.get("issuer").asText())
            .withSubject(data.get("subject") == null || data.get("subject").isNull() ? null : data.get("subject").asText())
            .withPayload(data.get("payload") == null || data.get("payload").isNull() ? null : data.get("payload").asText())
            .withEnableTransferMobileNotification(data.get("enableTransferMobileNotification") == null || data.get("enableTransferMobileNotification").isNull() ? null : data.get("enableTransferMobileNotification").booleanValue())
            .withSound(data.get("sound") == null || data.get("sound").isNull() ? null : data.get("sound").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("userId", getUserId());
                put("issuer", getIssuer());
                put("subject", getSubject());
                put("payload", getPayload());
                put("enableTransferMobileNotification", getEnableTransferMobileNotification());
                put("sound", getSound());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.issuer == null) ? 0 : this.issuer.hashCode());
        result = prime * result + ((this.subject == null) ? 0 : this.subject.hashCode());
        result = prime * result + ((this.payload == null) ? 0 : this.payload.hashCode());
        result = prime * result + ((this.enableTransferMobileNotification == null) ? 0 : this.enableTransferMobileNotification.hashCode());
        result = prime * result + ((this.sound == null) ? 0 : this.sound.hashCode());
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
		SendNotificationEntry other = (SendNotificationEntry) o;
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (issuer == null) {
			return other.issuer == null;
		} else if (!issuer.equals(other.issuer)) {
			return false;
		}
		if (subject == null) {
			return other.subject == null;
		} else if (!subject.equals(other.subject)) {
			return false;
		}
		if (payload == null) {
			return other.payload == null;
		} else if (!payload.equals(other.payload)) {
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
		return true;
	}
}