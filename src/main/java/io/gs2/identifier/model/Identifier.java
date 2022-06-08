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

package io.gs2.identifier.model;

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
public class Identifier implements IModel, Serializable, Comparable<Identifier> {
	private String clientId;
	private String userName;
	private String clientSecret;
	private Long createdAt;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public Identifier withClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Identifier withUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public Identifier withClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Identifier withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static Identifier fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Identifier()
            .withClientId(data.get("clientId") == null || data.get("clientId").isNull() ? null : data.get("clientId").asText())
            .withUserName(data.get("userName") == null || data.get("userName").isNull() ? null : data.get("userName").asText())
            .withClientSecret(data.get("clientSecret") == null || data.get("clientSecret").isNull() ? null : data.get("clientSecret").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("clientId", getClientId());
                put("userName", getUserName());
                put("clientSecret", getClientSecret());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Identifier o) {
		return clientId.compareTo(o.clientId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.clientId == null) ? 0 : this.clientId.hashCode());
        result = prime * result + ((this.userName == null) ? 0 : this.userName.hashCode());
        result = prime * result + ((this.clientSecret == null) ? 0 : this.clientSecret.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		Identifier other = (Identifier) o;
		if (clientId == null) {
			return other.clientId == null;
		} else if (!clientId.equals(other.clientId)) {
			return false;
		}
		if (userName == null) {
			return other.userName == null;
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		if (clientSecret == null) {
			return other.clientSecret == null;
		} else if (!clientSecret.equals(other.clientSecret)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		return true;
	}
}