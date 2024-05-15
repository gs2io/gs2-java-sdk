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

package io.gs2.guild.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.guild.model.*;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AssumeByUserIdResult implements IResult, Serializable {
    private String token;
    private String userId;
    private Long expire;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AssumeByUserIdResult withToken(String token) {
		this.token = token;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public AssumeByUserIdResult withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Long getExpire() {
		return expire;
	}

	public void setExpire(Long expire) {
		this.expire = expire;
	}

	public AssumeByUserIdResult withExpire(Long expire) {
		this.expire = expire;
		return this;
	}

    public static AssumeByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AssumeByUserIdResult()
            .withToken(data.get("token") == null || data.get("token").isNull() ? null : data.get("token").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withExpire(data.get("expire") == null || data.get("expire").isNull() ? null : data.get("expire").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("token", getToken());
                put("userId", getUserId());
                put("expire", getExpire());
            }}
        );
    }
}