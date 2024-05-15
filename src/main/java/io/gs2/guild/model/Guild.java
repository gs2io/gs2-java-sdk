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
public class Guild implements IModel, Serializable, Comparable<Guild> {
	private String guildId;
	private String guildModelName;
	private String name;
	private String displayName;
	private Integer attribute1;
	private Integer attribute2;
	private Integer attribute3;
	private Integer attribute4;
	private Integer attribute5;
	private String joinPolicy;
	private List<RoleModel> customRoles;
	private String guildMemberDefaultRole;
	private Integer currentMaximumMemberCount;
	private List<Member> members;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getGuildId() {
		return guildId;
	}
	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}
	public Guild withGuildId(String guildId) {
		this.guildId = guildId;
		return this;
	}
	public String getGuildModelName() {
		return guildModelName;
	}
	public void setGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
	}
	public Guild withGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Guild withName(String name) {
		this.name = name;
		return this;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Guild withDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}
	public Integer getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(Integer attribute1) {
		this.attribute1 = attribute1;
	}
	public Guild withAttribute1(Integer attribute1) {
		this.attribute1 = attribute1;
		return this;
	}
	public Integer getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(Integer attribute2) {
		this.attribute2 = attribute2;
	}
	public Guild withAttribute2(Integer attribute2) {
		this.attribute2 = attribute2;
		return this;
	}
	public Integer getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(Integer attribute3) {
		this.attribute3 = attribute3;
	}
	public Guild withAttribute3(Integer attribute3) {
		this.attribute3 = attribute3;
		return this;
	}
	public Integer getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(Integer attribute4) {
		this.attribute4 = attribute4;
	}
	public Guild withAttribute4(Integer attribute4) {
		this.attribute4 = attribute4;
		return this;
	}
	public Integer getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(Integer attribute5) {
		this.attribute5 = attribute5;
	}
	public Guild withAttribute5(Integer attribute5) {
		this.attribute5 = attribute5;
		return this;
	}
	public String getJoinPolicy() {
		return joinPolicy;
	}
	public void setJoinPolicy(String joinPolicy) {
		this.joinPolicy = joinPolicy;
	}
	public Guild withJoinPolicy(String joinPolicy) {
		this.joinPolicy = joinPolicy;
		return this;
	}
	public List<RoleModel> getCustomRoles() {
		return customRoles;
	}
	public void setCustomRoles(List<RoleModel> customRoles) {
		this.customRoles = customRoles;
	}
	public Guild withCustomRoles(List<RoleModel> customRoles) {
		this.customRoles = customRoles;
		return this;
	}
	public String getGuildMemberDefaultRole() {
		return guildMemberDefaultRole;
	}
	public void setGuildMemberDefaultRole(String guildMemberDefaultRole) {
		this.guildMemberDefaultRole = guildMemberDefaultRole;
	}
	public Guild withGuildMemberDefaultRole(String guildMemberDefaultRole) {
		this.guildMemberDefaultRole = guildMemberDefaultRole;
		return this;
	}
	public Integer getCurrentMaximumMemberCount() {
		return currentMaximumMemberCount;
	}
	public void setCurrentMaximumMemberCount(Integer currentMaximumMemberCount) {
		this.currentMaximumMemberCount = currentMaximumMemberCount;
	}
	public Guild withCurrentMaximumMemberCount(Integer currentMaximumMemberCount) {
		this.currentMaximumMemberCount = currentMaximumMemberCount;
		return this;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	public Guild withMembers(List<Member> members) {
		this.members = members;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Guild withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Guild withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Guild withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Guild fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Guild()
            .withGuildId(data.get("guildId") == null || data.get("guildId").isNull() ? null : data.get("guildId").asText())
            .withGuildModelName(data.get("guildModelName") == null || data.get("guildModelName").isNull() ? null : data.get("guildModelName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDisplayName(data.get("displayName") == null || data.get("displayName").isNull() ? null : data.get("displayName").asText())
            .withAttribute1(data.get("attribute1") == null || data.get("attribute1").isNull() ? null : data.get("attribute1").intValue())
            .withAttribute2(data.get("attribute2") == null || data.get("attribute2").isNull() ? null : data.get("attribute2").intValue())
            .withAttribute3(data.get("attribute3") == null || data.get("attribute3").isNull() ? null : data.get("attribute3").intValue())
            .withAttribute4(data.get("attribute4") == null || data.get("attribute4").isNull() ? null : data.get("attribute4").intValue())
            .withAttribute5(data.get("attribute5") == null || data.get("attribute5").isNull() ? null : data.get("attribute5").intValue())
            .withJoinPolicy(data.get("joinPolicy") == null || data.get("joinPolicy").isNull() ? null : data.get("joinPolicy").asText())
            .withCustomRoles(data.get("customRoles") == null || data.get("customRoles").isNull() ? new ArrayList<RoleModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("customRoles").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RoleModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withGuildMemberDefaultRole(data.get("guildMemberDefaultRole") == null || data.get("guildMemberDefaultRole").isNull() ? null : data.get("guildMemberDefaultRole").asText())
            .withCurrentMaximumMemberCount(data.get("currentMaximumMemberCount") == null || data.get("currentMaximumMemberCount").isNull() ? null : data.get("currentMaximumMemberCount").intValue())
            .withMembers(data.get("members") == null || data.get("members").isNull() ? new ArrayList<Member>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("members").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Member.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("guildId", getGuildId());
                put("guildModelName", getGuildModelName());
                put("name", getName());
                put("displayName", getDisplayName());
                put("attribute1", getAttribute1());
                put("attribute2", getAttribute2());
                put("attribute3", getAttribute3());
                put("attribute4", getAttribute4());
                put("attribute5", getAttribute5());
                put("joinPolicy", getJoinPolicy());
                put("customRoles", getCustomRoles() == null ? new ArrayList<RoleModel>() :
                    getCustomRoles().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("guildMemberDefaultRole", getGuildMemberDefaultRole());
                put("currentMaximumMemberCount", getCurrentMaximumMemberCount());
                put("members", getMembers() == null ? new ArrayList<Member>() :
                    getMembers().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Guild o) {
		return guildId.compareTo(o.guildId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.guildId == null) ? 0 : this.guildId.hashCode());
        result = prime * result + ((this.guildModelName == null) ? 0 : this.guildModelName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.displayName == null) ? 0 : this.displayName.hashCode());
        result = prime * result + ((this.attribute1 == null) ? 0 : this.attribute1.hashCode());
        result = prime * result + ((this.attribute2 == null) ? 0 : this.attribute2.hashCode());
        result = prime * result + ((this.attribute3 == null) ? 0 : this.attribute3.hashCode());
        result = prime * result + ((this.attribute4 == null) ? 0 : this.attribute4.hashCode());
        result = prime * result + ((this.attribute5 == null) ? 0 : this.attribute5.hashCode());
        result = prime * result + ((this.joinPolicy == null) ? 0 : this.joinPolicy.hashCode());
        result = prime * result + ((this.customRoles == null) ? 0 : this.customRoles.hashCode());
        result = prime * result + ((this.guildMemberDefaultRole == null) ? 0 : this.guildMemberDefaultRole.hashCode());
        result = prime * result + ((this.currentMaximumMemberCount == null) ? 0 : this.currentMaximumMemberCount.hashCode());
        result = prime * result + ((this.members == null) ? 0 : this.members.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.revision == null) ? 0 : this.revision.hashCode());
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
		Guild other = (Guild) o;
		if (guildId == null) {
			return other.guildId == null;
		} else if (!guildId.equals(other.guildId)) {
			return false;
		}
		if (guildModelName == null) {
			return other.guildModelName == null;
		} else if (!guildModelName.equals(other.guildModelName)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (displayName == null) {
			return other.displayName == null;
		} else if (!displayName.equals(other.displayName)) {
			return false;
		}
		if (attribute1 == null) {
			return other.attribute1 == null;
		} else if (!attribute1.equals(other.attribute1)) {
			return false;
		}
		if (attribute2 == null) {
			return other.attribute2 == null;
		} else if (!attribute2.equals(other.attribute2)) {
			return false;
		}
		if (attribute3 == null) {
			return other.attribute3 == null;
		} else if (!attribute3.equals(other.attribute3)) {
			return false;
		}
		if (attribute4 == null) {
			return other.attribute4 == null;
		} else if (!attribute4.equals(other.attribute4)) {
			return false;
		}
		if (attribute5 == null) {
			return other.attribute5 == null;
		} else if (!attribute5.equals(other.attribute5)) {
			return false;
		}
		if (joinPolicy == null) {
			return other.joinPolicy == null;
		} else if (!joinPolicy.equals(other.joinPolicy)) {
			return false;
		}
		if (customRoles == null) {
			return other.customRoles == null;
		} else if (!customRoles.equals(other.customRoles)) {
			return false;
		}
		if (guildMemberDefaultRole == null) {
			return other.guildMemberDefaultRole == null;
		} else if (!guildMemberDefaultRole.equals(other.guildMemberDefaultRole)) {
			return false;
		}
		if (currentMaximumMemberCount == null) {
			return other.currentMaximumMemberCount == null;
		} else if (!currentMaximumMemberCount.equals(other.currentMaximumMemberCount)) {
			return false;
		}
		if (members == null) {
			return other.members == null;
		} else if (!members.equals(other.members)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}