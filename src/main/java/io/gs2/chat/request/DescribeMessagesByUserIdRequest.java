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
public class DescribeMessagesByUserIdRequest extends Gs2BasicRequest<DescribeMessagesByUserIdRequest> {
    private String namespaceName;
    private String roomName;
    private String password;
    private String userId;
    private Long startAt;
    private Integer limit;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public DescribeMessagesByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public DescribeMessagesByUserIdRequest withRoomName(String roomName) {
		this.roomName = roomName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DescribeMessagesByUserIdRequest withPassword(String password) {
		this.password = password;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public DescribeMessagesByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Long getStartAt() {
		return startAt;
	}

	public void setStartAt(Long startAt) {
		this.startAt = startAt;
	}

	public DescribeMessagesByUserIdRequest withStartAt(Long startAt) {
		this.startAt = startAt;
		return this;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public DescribeMessagesByUserIdRequest withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

    public static DescribeMessagesByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DescribeMessagesByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRoomName(data.get("roomName") == null || data.get("roomName").isNull() ? null : data.get("roomName").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withStartAt(data.get("startAt") == null || data.get("startAt").isNull() ? null : data.get("startAt").longValue())
            .withLimit(data.get("limit") == null || data.get("limit").isNull() ? null : data.get("limit").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("roomName", getRoomName());
                put("password", getPassword());
                put("userId", getUserId());
                put("startAt", getStartAt());
                put("limit", getLimit());
            }}
        );
    }
}