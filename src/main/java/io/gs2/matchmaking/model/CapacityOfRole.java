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
public class CapacityOfRole implements IModel, Serializable {
	private String roleName;
	private List<String> roleAliases;
	private Integer capacity;
	private List<Player> participants;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public CapacityOfRole withRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}
	public List<String> getRoleAliases() {
		return roleAliases;
	}
	public void setRoleAliases(List<String> roleAliases) {
		this.roleAliases = roleAliases;
	}
	public CapacityOfRole withRoleAliases(List<String> roleAliases) {
		this.roleAliases = roleAliases;
		return this;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public CapacityOfRole withCapacity(Integer capacity) {
		this.capacity = capacity;
		return this;
	}
	public List<Player> getParticipants() {
		return participants;
	}
	public void setParticipants(List<Player> participants) {
		this.participants = participants;
	}
	public CapacityOfRole withParticipants(List<Player> participants) {
		this.participants = participants;
		return this;
	}

    public static CapacityOfRole fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CapacityOfRole()
            .withRoleName(data.get("roleName") == null || data.get("roleName").isNull() ? null : data.get("roleName").asText())
            .withRoleAliases(data.get("roleAliases") == null || data.get("roleAliases").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("roleAliases").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withCapacity(data.get("capacity") == null || data.get("capacity").isNull() ? null : data.get("capacity").intValue())
            .withParticipants(data.get("participants") == null || data.get("participants").isNull() ? new ArrayList<Player>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("participants").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Player.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("roleName", getRoleName());
                put("roleAliases", getRoleAliases() == null ? new ArrayList<String>() :
                    getRoleAliases().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("capacity", getCapacity());
                put("participants", getParticipants() == null ? new ArrayList<Player>() :
                    getParticipants().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.roleName == null) ? 0 : this.roleName.hashCode());
        result = prime * result + ((this.roleAliases == null) ? 0 : this.roleAliases.hashCode());
        result = prime * result + ((this.capacity == null) ? 0 : this.capacity.hashCode());
        result = prime * result + ((this.participants == null) ? 0 : this.participants.hashCode());
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
		CapacityOfRole other = (CapacityOfRole) o;
		if (roleName == null) {
			return other.roleName == null;
		} else if (!roleName.equals(other.roleName)) {
			return false;
		}
		if (roleAliases == null) {
			return other.roleAliases == null;
		} else if (!roleAliases.equals(other.roleAliases)) {
			return false;
		}
		if (capacity == null) {
			return other.capacity == null;
		} else if (!capacity.equals(other.capacity)) {
			return false;
		}
		if (participants == null) {
			return other.participants == null;
		} else if (!participants.equals(other.participants)) {
			return false;
		}
		return true;
	}
}