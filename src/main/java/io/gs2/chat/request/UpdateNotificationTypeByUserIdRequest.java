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

package io.gs2.chat.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.chat.model.NotificationType;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateNotificationTypeByUserIdRequest extends Gs2BasicRequest<UpdateNotificationTypeByUserIdRequest> {
    private String namespaceName;
    private String roomName;
    private String userId;
    private List<NotificationType> notificationTypes;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateNotificationTypeByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public UpdateNotificationTypeByUserIdRequest withRoomName(String roomName) {
		this.roomName = roomName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public UpdateNotificationTypeByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public List<NotificationType> getNotificationTypes() {
		return notificationTypes;
	}
	public void setNotificationTypes(List<NotificationType> notificationTypes) {
		this.notificationTypes = notificationTypes;
	}
	public UpdateNotificationTypeByUserIdRequest withNotificationTypes(List<NotificationType> notificationTypes) {
		this.notificationTypes = notificationTypes;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public UpdateNotificationTypeByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public UpdateNotificationTypeByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static UpdateNotificationTypeByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateNotificationTypeByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRoomName(data.get("roomName") == null || data.get("roomName").isNull() ? null : data.get("roomName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withNotificationTypes(data.get("notificationTypes") == null || data.get("notificationTypes").isNull() ? new ArrayList<NotificationType>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("notificationTypes").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return NotificationType.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("roomName", getRoomName());
                put("userId", getUserId());
                put("notificationTypes", getNotificationTypes() == null ? new ArrayList<NotificationType>() :
                    getNotificationTypes().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}