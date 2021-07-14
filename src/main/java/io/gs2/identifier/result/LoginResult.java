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

package io.gs2.identifier.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.identifier.model.*;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginResult implements IResult, Serializable {
    private String accessToken;
    private String tokenType;
    private Integer expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public LoginResult withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public LoginResult withTokenType(String tokenType) {
		this.tokenType = tokenType;
		return this;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public LoginResult withExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}

    public static LoginResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new LoginResult()
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withTokenType(data.get("tokenType") == null || data.get("tokenType").isNull() ? null : data.get("tokenType").asText())
            .withExpiresIn(data.get("expiresIn") == null || data.get("expiresIn").isNull() ? null : data.get("expiresIn").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("accessToken", getAccessToken());
                put("tokenType", getTokenType());
                put("expiresIn", getExpiresIn());
            }}
        );
    }
}