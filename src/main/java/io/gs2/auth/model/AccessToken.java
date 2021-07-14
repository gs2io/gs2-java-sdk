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

package io.gs2.auth.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AccessToken implements IModel, Serializable, Comparable<AccessToken> {
	private String token;
	private String userId;
	private Long expire;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AccessToken withToken(String token) {
		this.token = token;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public AccessToken withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Long getExpire() {
		return expire;
	}

	public void setExpire(Long expire) {
		this.expire = expire;
	}

	public AccessToken withExpire(Long expire) {
		this.expire = expire;
		return this;
	}

    public static AccessToken fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AccessToken()
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

	@Override
	public int compareTo(AccessToken o) {
		return token.compareTo(o.token);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.token == null) ? 0 : this.token.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.expire == null) ? 0 : this.expire.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		AccessToken other = (AccessToken) o;
		if (token == null) {
			return other.token == null;
		} else if (!token.equals(other.token)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (expire == null) {
			return other.expire == null;
		} else if (!expire.equals(other.expire)) {
			return false;
		}
		return true;
	}
}