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
public class CreateRoomFromBackendRequest extends Gs2BasicRequest<CreateRoomFromBackendRequest> {
    private String namespaceName;
    private String name;
    private String userId;
    private String metadata;
    private String password;
    private List<String> whiteListUserIds;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public CreateRoomFromBackendRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CreateRoomFromBackendRequest withName(String name) {
		this.name = name;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public CreateRoomFromBackendRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public CreateRoomFromBackendRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CreateRoomFromBackendRequest withPassword(String password) {
		this.password = password;
		return this;
	}

	public List<String> getWhiteListUserIds() {
		return whiteListUserIds;
	}

	public void setWhiteListUserIds(List<String> whiteListUserIds) {
		this.whiteListUserIds = whiteListUserIds;
	}

	public CreateRoomFromBackendRequest withWhiteListUserIds(List<String> whiteListUserIds) {
		this.whiteListUserIds = whiteListUserIds;
		return this;
	}

    public static CreateRoomFromBackendRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateRoomFromBackendRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText())
            .withWhiteListUserIds(data.get("whiteListUserIds") == null || data.get("whiteListUserIds").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("whiteListUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("name", getName());
                put("userId", getUserId());
                put("metadata", getMetadata());
                put("password", getPassword());
                put("whiteListUserIds", getWhiteListUserIds() == null ? new ArrayList<String>() :
                    getWhiteListUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}