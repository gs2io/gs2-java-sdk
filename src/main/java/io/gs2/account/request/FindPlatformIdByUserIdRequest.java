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

package io.gs2.account.request;

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
public class FindPlatformIdByUserIdRequest extends Gs2BasicRequest<FindPlatformIdByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private Integer type;
    private String userIdentifier;
    private Boolean dontResolveDataOwner;
    private String timeOffsetToken;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public FindPlatformIdByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public FindPlatformIdByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public FindPlatformIdByUserIdRequest withType(Integer type) {
		this.type = type;
		return this;
	}
	public String getUserIdentifier() {
		return userIdentifier;
	}
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}
	public FindPlatformIdByUserIdRequest withUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
		return this;
	}
	public Boolean getDontResolveDataOwner() {
		return dontResolveDataOwner;
	}
	public void setDontResolveDataOwner(Boolean dontResolveDataOwner) {
		this.dontResolveDataOwner = dontResolveDataOwner;
	}
	public FindPlatformIdByUserIdRequest withDontResolveDataOwner(Boolean dontResolveDataOwner) {
		this.dontResolveDataOwner = dontResolveDataOwner;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public FindPlatformIdByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

    public static FindPlatformIdByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new FindPlatformIdByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").intValue())
            .withUserIdentifier(data.get("userIdentifier") == null || data.get("userIdentifier").isNull() ? null : data.get("userIdentifier").asText())
            .withDontResolveDataOwner(data.get("dontResolveDataOwner") == null || data.get("dontResolveDataOwner").isNull() ? null : data.get("dontResolveDataOwner").booleanValue())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("type", getType());
                put("userIdentifier", getUserIdentifier());
                put("dontResolveDataOwner", getDontResolveDataOwner());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}