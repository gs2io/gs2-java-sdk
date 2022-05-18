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
public class CreateTakeOverByUserIdRequest extends Gs2BasicRequest<CreateTakeOverByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private Integer type;
    private String userIdentifier;
    private String password;
    private String duplicationAvoider;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public CreateTakeOverByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public CreateTakeOverByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public CreateTakeOverByUserIdRequest withType(Integer type) {
		this.type = type;
		return this;
	}

	public String getUserIdentifier() {
		return userIdentifier;
	}

	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	public CreateTakeOverByUserIdRequest withUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CreateTakeOverByUserIdRequest withPassword(String password) {
		this.password = password;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public CreateTakeOverByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static CreateTakeOverByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateTakeOverByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").intValue())
            .withUserIdentifier(data.get("userIdentifier") == null || data.get("userIdentifier").isNull() ? null : data.get("userIdentifier").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("type", getType());
                put("userIdentifier", getUserIdentifier());
                put("password", getPassword());
            }}
        );
    }
}