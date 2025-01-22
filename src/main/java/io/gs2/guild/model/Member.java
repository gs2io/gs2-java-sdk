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

package io.gs2.guild.model;

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
public class Member implements IModel, Serializable {
	private String userId;
	private String roleName;
	private String metadata;
	private Long joinedAt;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Member withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Member withRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public Member withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getJoinedAt() {
		return joinedAt;
	}
	public void setJoinedAt(Long joinedAt) {
		this.joinedAt = joinedAt;
	}
	public Member withJoinedAt(Long joinedAt) {
		this.joinedAt = joinedAt;
		return this;
	}

    public static Member fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Member()
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withRoleName(data.get("roleName") == null || data.get("roleName").isNull() ? null : data.get("roleName").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withJoinedAt(data.get("joinedAt") == null || data.get("joinedAt").isNull() ? null : data.get("joinedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("userId", getUserId());
                put("roleName", getRoleName());
                put("metadata", getMetadata());
                put("joinedAt", getJoinedAt());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.roleName == null) ? 0 : this.roleName.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.joinedAt == null) ? 0 : this.joinedAt.hashCode());
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
		Member other = (Member) o;
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (roleName == null) {
			return other.roleName == null;
		} else if (!roleName.equals(other.roleName)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (joinedAt == null) {
			return other.joinedAt == null;
		} else if (!joinedAt.equals(other.joinedAt)) {
			return false;
		}
		return true;
	}
}