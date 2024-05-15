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
public class GuildModelMaster implements IModel, Serializable, Comparable<GuildModelMaster> {
	private String guildModelId;
	private String name;
	private String description;
	private String metadata;
	private Integer defaultMaximumMemberCount;
	private Integer maximumMemberCount;
	private List<RoleModel> roles;
	private String guildMasterRole;
	private String guildMemberDefaultRole;
	private Integer rejoinCoolTimeMinutes;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getGuildModelId() {
		return guildModelId;
	}
	public void setGuildModelId(String guildModelId) {
		this.guildModelId = guildModelId;
	}
	public GuildModelMaster withGuildModelId(String guildModelId) {
		this.guildModelId = guildModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GuildModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public GuildModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public GuildModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getDefaultMaximumMemberCount() {
		return defaultMaximumMemberCount;
	}
	public void setDefaultMaximumMemberCount(Integer defaultMaximumMemberCount) {
		this.defaultMaximumMemberCount = defaultMaximumMemberCount;
	}
	public GuildModelMaster withDefaultMaximumMemberCount(Integer defaultMaximumMemberCount) {
		this.defaultMaximumMemberCount = defaultMaximumMemberCount;
		return this;
	}
	public Integer getMaximumMemberCount() {
		return maximumMemberCount;
	}
	public void setMaximumMemberCount(Integer maximumMemberCount) {
		this.maximumMemberCount = maximumMemberCount;
	}
	public GuildModelMaster withMaximumMemberCount(Integer maximumMemberCount) {
		this.maximumMemberCount = maximumMemberCount;
		return this;
	}
	public List<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}
	public GuildModelMaster withRoles(List<RoleModel> roles) {
		this.roles = roles;
		return this;
	}
	public String getGuildMasterRole() {
		return guildMasterRole;
	}
	public void setGuildMasterRole(String guildMasterRole) {
		this.guildMasterRole = guildMasterRole;
	}
	public GuildModelMaster withGuildMasterRole(String guildMasterRole) {
		this.guildMasterRole = guildMasterRole;
		return this;
	}
	public String getGuildMemberDefaultRole() {
		return guildMemberDefaultRole;
	}
	public void setGuildMemberDefaultRole(String guildMemberDefaultRole) {
		this.guildMemberDefaultRole = guildMemberDefaultRole;
	}
	public GuildModelMaster withGuildMemberDefaultRole(String guildMemberDefaultRole) {
		this.guildMemberDefaultRole = guildMemberDefaultRole;
		return this;
	}
	public Integer getRejoinCoolTimeMinutes() {
		return rejoinCoolTimeMinutes;
	}
	public void setRejoinCoolTimeMinutes(Integer rejoinCoolTimeMinutes) {
		this.rejoinCoolTimeMinutes = rejoinCoolTimeMinutes;
	}
	public GuildModelMaster withRejoinCoolTimeMinutes(Integer rejoinCoolTimeMinutes) {
		this.rejoinCoolTimeMinutes = rejoinCoolTimeMinutes;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public GuildModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public GuildModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public GuildModelMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static GuildModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GuildModelMaster()
            .withGuildModelId(data.get("guildModelId") == null || data.get("guildModelId").isNull() ? null : data.get("guildModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withDefaultMaximumMemberCount(data.get("defaultMaximumMemberCount") == null || data.get("defaultMaximumMemberCount").isNull() ? null : data.get("defaultMaximumMemberCount").intValue())
            .withMaximumMemberCount(data.get("maximumMemberCount") == null || data.get("maximumMemberCount").isNull() ? null : data.get("maximumMemberCount").intValue())
            .withRoles(data.get("roles") == null || data.get("roles").isNull() ? new ArrayList<RoleModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("roles").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RoleModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withGuildMasterRole(data.get("guildMasterRole") == null || data.get("guildMasterRole").isNull() ? null : data.get("guildMasterRole").asText())
            .withGuildMemberDefaultRole(data.get("guildMemberDefaultRole") == null || data.get("guildMemberDefaultRole").isNull() ? null : data.get("guildMemberDefaultRole").asText())
            .withRejoinCoolTimeMinutes(data.get("rejoinCoolTimeMinutes") == null || data.get("rejoinCoolTimeMinutes").isNull() ? null : data.get("rejoinCoolTimeMinutes").intValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("guildModelId", getGuildModelId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("defaultMaximumMemberCount", getDefaultMaximumMemberCount());
                put("maximumMemberCount", getMaximumMemberCount());
                put("roles", getRoles() == null ? new ArrayList<RoleModel>() :
                    getRoles().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("guildMasterRole", getGuildMasterRole());
                put("guildMemberDefaultRole", getGuildMemberDefaultRole());
                put("rejoinCoolTimeMinutes", getRejoinCoolTimeMinutes());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(GuildModelMaster o) {
		return guildModelId.compareTo(o.guildModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.guildModelId == null) ? 0 : this.guildModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.defaultMaximumMemberCount == null) ? 0 : this.defaultMaximumMemberCount.hashCode());
        result = prime * result + ((this.maximumMemberCount == null) ? 0 : this.maximumMemberCount.hashCode());
        result = prime * result + ((this.roles == null) ? 0 : this.roles.hashCode());
        result = prime * result + ((this.guildMasterRole == null) ? 0 : this.guildMasterRole.hashCode());
        result = prime * result + ((this.guildMemberDefaultRole == null) ? 0 : this.guildMemberDefaultRole.hashCode());
        result = prime * result + ((this.rejoinCoolTimeMinutes == null) ? 0 : this.rejoinCoolTimeMinutes.hashCode());
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
		GuildModelMaster other = (GuildModelMaster) o;
		if (guildModelId == null) {
			return other.guildModelId == null;
		} else if (!guildModelId.equals(other.guildModelId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (defaultMaximumMemberCount == null) {
			return other.defaultMaximumMemberCount == null;
		} else if (!defaultMaximumMemberCount.equals(other.defaultMaximumMemberCount)) {
			return false;
		}
		if (maximumMemberCount == null) {
			return other.maximumMemberCount == null;
		} else if (!maximumMemberCount.equals(other.maximumMemberCount)) {
			return false;
		}
		if (roles == null) {
			return other.roles == null;
		} else if (!roles.equals(other.roles)) {
			return false;
		}
		if (guildMasterRole == null) {
			return other.guildMasterRole == null;
		} else if (!guildMasterRole.equals(other.guildMasterRole)) {
			return false;
		}
		if (guildMemberDefaultRole == null) {
			return other.guildMemberDefaultRole == null;
		} else if (!guildMemberDefaultRole.equals(other.guildMemberDefaultRole)) {
			return false;
		}
		if (rejoinCoolTimeMinutes == null) {
			return other.rejoinCoolTimeMinutes == null;
		} else if (!rejoinCoolTimeMinutes.equals(other.rejoinCoolTimeMinutes)) {
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