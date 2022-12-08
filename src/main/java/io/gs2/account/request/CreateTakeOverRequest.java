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
public class CreateTakeOverRequest extends Gs2BasicRequest<CreateTakeOverRequest> {
    private String namespaceName;
    private String accessToken;
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
	public CreateTakeOverRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public CreateTakeOverRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public CreateTakeOverRequest withType(Integer type) {
		this.type = type;
		return this;
	}
	public String getUserIdentifier() {
		return userIdentifier;
	}
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}
	public CreateTakeOverRequest withUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CreateTakeOverRequest withPassword(String password) {
		this.password = password;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public CreateTakeOverRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static CreateTakeOverRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateTakeOverRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").intValue())
            .withUserIdentifier(data.get("userIdentifier") == null || data.get("userIdentifier").isNull() ? null : data.get("userIdentifier").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("type", getType());
                put("userIdentifier", getUserIdentifier());
                put("password", getPassword());
            }}
        );
    }
}