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

package io.gs2.gateway.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SendNotificationRequest extends Gs2BasicRequest<SendNotificationRequest> {
    private String namespaceName;
    private String userId;
    private String subject;
    private String payload;
    private Boolean enableTransferMobileNotification;
    private String sound;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public SendNotificationRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SendNotificationRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public SendNotificationRequest withSubject(String subject) {
		this.subject = subject;
		return this;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public SendNotificationRequest withPayload(String payload) {
		this.payload = payload;
		return this;
	}
	public Boolean getEnableTransferMobileNotification() {
		return enableTransferMobileNotification;
	}
	public void setEnableTransferMobileNotification(Boolean enableTransferMobileNotification) {
		this.enableTransferMobileNotification = enableTransferMobileNotification;
	}
	public SendNotificationRequest withEnableTransferMobileNotification(Boolean enableTransferMobileNotification) {
		this.enableTransferMobileNotification = enableTransferMobileNotification;
		return this;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public SendNotificationRequest withSound(String sound) {
		this.sound = sound;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public SendNotificationRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public SendNotificationRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static SendNotificationRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SendNotificationRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withSubject(data.get("subject") == null || data.get("subject").isNull() ? null : data.get("subject").asText())
            .withPayload(data.get("payload") == null || data.get("payload").isNull() ? null : data.get("payload").asText())
            .withEnableTransferMobileNotification(data.get("enableTransferMobileNotification") == null || data.get("enableTransferMobileNotification").isNull() ? null : data.get("enableTransferMobileNotification").booleanValue())
            .withSound(data.get("sound") == null || data.get("sound").isNull() ? null : data.get("sound").asText())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("subject", getSubject());
                put("payload", getPayload());
                put("enableTransferMobileNotification", getEnableTransferMobileNotification());
                put("sound", getSound());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}