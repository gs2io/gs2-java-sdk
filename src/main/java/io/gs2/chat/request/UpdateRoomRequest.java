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
public class UpdateRoomRequest extends Gs2BasicRequest<UpdateRoomRequest> {
    private String namespaceName;
    private String roomName;
    private String metadata;
    private String password;
    private List<String> whiteListUserIds;
    private String accessToken;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public UpdateRoomRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public UpdateRoomRequest withRoomName(String roomName) {
		this.roomName = roomName;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public UpdateRoomRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UpdateRoomRequest withPassword(String password) {
		this.password = password;
		return this;
	}

	public List<String> getWhiteListUserIds() {
		return whiteListUserIds;
	}

	public void setWhiteListUserIds(List<String> whiteListUserIds) {
		this.whiteListUserIds = whiteListUserIds;
	}

	public UpdateRoomRequest withWhiteListUserIds(List<String> whiteListUserIds) {
		this.whiteListUserIds = whiteListUserIds;
		return this;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public UpdateRoomRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

    public static UpdateRoomRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateRoomRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRoomName(data.get("roomName") == null || data.get("roomName").isNull() ? null : data.get("roomName").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText())
            .withWhiteListUserIds(data.get("whiteListUserIds") == null || data.get("whiteListUserIds").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("whiteListUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("roomName", getRoomName());
                put("metadata", getMetadata());
                put("password", getPassword());
                put("whiteListUserIds", getWhiteListUserIds() == null ? new ArrayList<String>() :
                    getWhiteListUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("accessToken", getAccessToken());
            }}
        );
    }
}