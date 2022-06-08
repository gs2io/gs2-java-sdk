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

package io.gs2.account.model;

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
public class TakeOver implements IModel, Serializable, Comparable<TakeOver> {
	private String takeOverId;
	private String userId;
	private Integer type;
	private String userIdentifier;
	private String password;
	private Long createdAt;
	public String getTakeOverId() {
		return takeOverId;
	}
	public void setTakeOverId(String takeOverId) {
		this.takeOverId = takeOverId;
	}
	public TakeOver withTakeOverId(String takeOverId) {
		this.takeOverId = takeOverId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public TakeOver withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public TakeOver withType(Integer type) {
		this.type = type;
		return this;
	}
	public String getUserIdentifier() {
		return userIdentifier;
	}
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}
	public TakeOver withUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public TakeOver withPassword(String password) {
		this.password = password;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public TakeOver withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static TakeOver fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new TakeOver()
            .withTakeOverId(data.get("takeOverId") == null || data.get("takeOverId").isNull() ? null : data.get("takeOverId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").intValue())
            .withUserIdentifier(data.get("userIdentifier") == null || data.get("userIdentifier").isNull() ? null : data.get("userIdentifier").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("takeOverId", getTakeOverId());
                put("userId", getUserId());
                put("type", getType());
                put("userIdentifier", getUserIdentifier());
                put("password", getPassword());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(TakeOver o) {
		return takeOverId.compareTo(o.takeOverId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.takeOverId == null) ? 0 : this.takeOverId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.userIdentifier == null) ? 0 : this.userIdentifier.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
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
		TakeOver other = (TakeOver) o;
		if (takeOverId == null) {
			return other.takeOverId == null;
		} else if (!takeOverId.equals(other.takeOverId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (userIdentifier == null) {
			return other.userIdentifier == null;
		} else if (!userIdentifier.equals(other.userIdentifier)) {
			return false;
		}
		if (password == null) {
			return other.password == null;
		} else if (!password.equals(other.password)) {
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