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

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateRoomFromBackendRequest extends Gs2BasicRequest<UpdateRoomFromBackendRequest> {
    private String namespaceName;
    private String roomName;
    private String metadata;
    private String password;
    private List<String> whiteListUserIds;
    private String userId;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateRoomFromBackendRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public UpdateRoomFromBackendRequest withRoomName(String roomName) {
		this.roomName = roomName;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateRoomFromBackendRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UpdateRoomFromBackendRequest withPassword(String password) {
		this.password = password;
		return this;
	}
	public List<String> getWhiteListUserIds() {
		return whiteListUserIds;
	}
	public void setWhiteListUserIds(List<String> whiteListUserIds) {
		this.whiteListUserIds = whiteListUserIds;
	}
	public UpdateRoomFromBackendRequest withWhiteListUserIds(List<String> whiteListUserIds) {
		this.whiteListUserIds = whiteListUserIds;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public UpdateRoomFromBackendRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public UpdateRoomFromBackendRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public UpdateRoomFromBackendRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static UpdateRoomFromBackendRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateRoomFromBackendRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRoomName(data.get("roomName") == null || data.get("roomName").isNull() ? null : data.get("roomName").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText())
            .withWhiteListUserIds(data.get("whiteListUserIds") == null || data.get("whiteListUserIds").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("whiteListUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("roomName", getRoomName());
                put("metadata", getMetadata());
                put("password", getPassword());
                put("whiteListUserIds", getWhiteListUserIds() == null ? null :
                    getWhiteListUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("userId", getUserId());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}