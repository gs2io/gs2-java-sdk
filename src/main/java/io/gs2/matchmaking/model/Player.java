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

package io.gs2.matchmaking.model;

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
public class Player implements IModel, Serializable {
	private String userId;
	private List<Attribute> attributes;
	private String roleName;
	private List<String> denyUserIds;
	private Long createdAt;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Player withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	public Player withAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
		return this;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Player withRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}
	public List<String> getDenyUserIds() {
		return denyUserIds;
	}
	public void setDenyUserIds(List<String> denyUserIds) {
		this.denyUserIds = denyUserIds;
	}
	public Player withDenyUserIds(List<String> denyUserIds) {
		this.denyUserIds = denyUserIds;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Player withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static Player fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Player()
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withAttributes(data.get("attributes") == null || data.get("attributes").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("attributes").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Attribute.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withRoleName(data.get("roleName") == null || data.get("roleName").isNull() ? null : data.get("roleName").asText())
            .withDenyUserIds(data.get("denyUserIds") == null || data.get("denyUserIds").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("denyUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("userId", getUserId());
                put("attributes", getAttributes() == null ? null :
                    getAttributes().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("roleName", getRoleName());
                put("denyUserIds", getDenyUserIds() == null ? null :
                    getDenyUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.attributes == null) ? 0 : this.attributes.hashCode());
        result = prime * result + ((this.roleName == null) ? 0 : this.roleName.hashCode());
        result = prime * result + ((this.denyUserIds == null) ? 0 : this.denyUserIds.hashCode());
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
		Player other = (Player) o;
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (attributes == null) {
			return other.attributes == null;
		} else if (!attributes.equals(other.attributes)) {
			return false;
		}
		if (roleName == null) {
			return other.roleName == null;
		} else if (!roleName.equals(other.roleName)) {
			return false;
		}
		if (denyUserIds == null) {
			return other.denyUserIds == null;
		} else if (!denyUserIds.equals(other.denyUserIds)) {
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