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
public class DescribeMessagesRequest extends Gs2BasicRequest<DescribeMessagesRequest> {
    private String namespaceName;
    private String roomName;
    private String password;
    private Integer category;
    private String accessToken;
    private Long startAt;
    private Integer limit;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public DescribeMessagesRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public DescribeMessagesRequest withRoomName(String roomName) {
		this.roomName = roomName;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public DescribeMessagesRequest withPassword(String password) {
		this.password = password;
		return this;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public DescribeMessagesRequest withCategory(Integer category) {
		this.category = category;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public DescribeMessagesRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public Long getStartAt() {
		return startAt;
	}
	public void setStartAt(Long startAt) {
		this.startAt = startAt;
	}
	public DescribeMessagesRequest withStartAt(Long startAt) {
		this.startAt = startAt;
		return this;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public DescribeMessagesRequest withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

    public static DescribeMessagesRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DescribeMessagesRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRoomName(data.get("roomName") == null || data.get("roomName").isNull() ? null : data.get("roomName").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText())
            .withCategory(data.get("category") == null || data.get("category").isNull() ? null : data.get("category").intValue())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withStartAt(data.get("startAt") == null || data.get("startAt").isNull() ? null : data.get("startAt").longValue())
            .withLimit(data.get("limit") == null || data.get("limit").isNull() ? null : data.get("limit").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("roomName", getRoomName());
                put("password", getPassword());
                put("category", getCategory());
                put("accessToken", getAccessToken());
                put("startAt", getStartAt());
                put("limit", getLimit());
            }}
        );
    }
}