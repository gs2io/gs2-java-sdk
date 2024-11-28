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

package io.gs2.guild.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.guild.model.RoleModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateGuildModelMasterRequest extends Gs2BasicRequest<CreateGuildModelMasterRequest> {
    private String namespaceName;
    private String name;
    private String description;
    private String metadata;
    private Integer defaultMaximumMemberCount;
    private Integer maximumMemberCount;
    private Integer inactivityPeriodDays;
    private List<RoleModel> roles;
    private String guildMasterRole;
    private String guildMemberDefaultRole;
    private Integer rejoinCoolTimeMinutes;
    private Integer maxConcurrentJoinGuilds;
    private Integer maxConcurrentGuildMasterCount;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateGuildModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateGuildModelMasterRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateGuildModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CreateGuildModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getDefaultMaximumMemberCount() {
		return defaultMaximumMemberCount;
	}
	public void setDefaultMaximumMemberCount(Integer defaultMaximumMemberCount) {
		this.defaultMaximumMemberCount = defaultMaximumMemberCount;
	}
	public CreateGuildModelMasterRequest withDefaultMaximumMemberCount(Integer defaultMaximumMemberCount) {
		this.defaultMaximumMemberCount = defaultMaximumMemberCount;
		return this;
	}
	public Integer getMaximumMemberCount() {
		return maximumMemberCount;
	}
	public void setMaximumMemberCount(Integer maximumMemberCount) {
		this.maximumMemberCount = maximumMemberCount;
	}
	public CreateGuildModelMasterRequest withMaximumMemberCount(Integer maximumMemberCount) {
		this.maximumMemberCount = maximumMemberCount;
		return this;
	}
	public Integer getInactivityPeriodDays() {
		return inactivityPeriodDays;
	}
	public void setInactivityPeriodDays(Integer inactivityPeriodDays) {
		this.inactivityPeriodDays = inactivityPeriodDays;
	}
	public CreateGuildModelMasterRequest withInactivityPeriodDays(Integer inactivityPeriodDays) {
		this.inactivityPeriodDays = inactivityPeriodDays;
		return this;
	}
	public List<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}
	public CreateGuildModelMasterRequest withRoles(List<RoleModel> roles) {
		this.roles = roles;
		return this;
	}
	public String getGuildMasterRole() {
		return guildMasterRole;
	}
	public void setGuildMasterRole(String guildMasterRole) {
		this.guildMasterRole = guildMasterRole;
	}
	public CreateGuildModelMasterRequest withGuildMasterRole(String guildMasterRole) {
		this.guildMasterRole = guildMasterRole;
		return this;
	}
	public String getGuildMemberDefaultRole() {
		return guildMemberDefaultRole;
	}
	public void setGuildMemberDefaultRole(String guildMemberDefaultRole) {
		this.guildMemberDefaultRole = guildMemberDefaultRole;
	}
	public CreateGuildModelMasterRequest withGuildMemberDefaultRole(String guildMemberDefaultRole) {
		this.guildMemberDefaultRole = guildMemberDefaultRole;
		return this;
	}
	public Integer getRejoinCoolTimeMinutes() {
		return rejoinCoolTimeMinutes;
	}
	public void setRejoinCoolTimeMinutes(Integer rejoinCoolTimeMinutes) {
		this.rejoinCoolTimeMinutes = rejoinCoolTimeMinutes;
	}
	public CreateGuildModelMasterRequest withRejoinCoolTimeMinutes(Integer rejoinCoolTimeMinutes) {
		this.rejoinCoolTimeMinutes = rejoinCoolTimeMinutes;
		return this;
	}
	public Integer getMaxConcurrentJoinGuilds() {
		return maxConcurrentJoinGuilds;
	}
	public void setMaxConcurrentJoinGuilds(Integer maxConcurrentJoinGuilds) {
		this.maxConcurrentJoinGuilds = maxConcurrentJoinGuilds;
	}
	public CreateGuildModelMasterRequest withMaxConcurrentJoinGuilds(Integer maxConcurrentJoinGuilds) {
		this.maxConcurrentJoinGuilds = maxConcurrentJoinGuilds;
		return this;
	}
	public Integer getMaxConcurrentGuildMasterCount() {
		return maxConcurrentGuildMasterCount;
	}
	public void setMaxConcurrentGuildMasterCount(Integer maxConcurrentGuildMasterCount) {
		this.maxConcurrentGuildMasterCount = maxConcurrentGuildMasterCount;
	}
	public CreateGuildModelMasterRequest withMaxConcurrentGuildMasterCount(Integer maxConcurrentGuildMasterCount) {
		this.maxConcurrentGuildMasterCount = maxConcurrentGuildMasterCount;
		return this;
	}

    public static CreateGuildModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateGuildModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withDefaultMaximumMemberCount(data.get("defaultMaximumMemberCount") == null || data.get("defaultMaximumMemberCount").isNull() ? null : data.get("defaultMaximumMemberCount").intValue())
            .withMaximumMemberCount(data.get("maximumMemberCount") == null || data.get("maximumMemberCount").isNull() ? null : data.get("maximumMemberCount").intValue())
            .withInactivityPeriodDays(data.get("inactivityPeriodDays") == null || data.get("inactivityPeriodDays").isNull() ? null : data.get("inactivityPeriodDays").intValue())
            .withRoles(data.get("roles") == null || data.get("roles").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("roles").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RoleModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withGuildMasterRole(data.get("guildMasterRole") == null || data.get("guildMasterRole").isNull() ? null : data.get("guildMasterRole").asText())
            .withGuildMemberDefaultRole(data.get("guildMemberDefaultRole") == null || data.get("guildMemberDefaultRole").isNull() ? null : data.get("guildMemberDefaultRole").asText())
            .withRejoinCoolTimeMinutes(data.get("rejoinCoolTimeMinutes") == null || data.get("rejoinCoolTimeMinutes").isNull() ? null : data.get("rejoinCoolTimeMinutes").intValue())
            .withMaxConcurrentJoinGuilds(data.get("maxConcurrentJoinGuilds") == null || data.get("maxConcurrentJoinGuilds").isNull() ? null : data.get("maxConcurrentJoinGuilds").intValue())
            .withMaxConcurrentGuildMasterCount(data.get("maxConcurrentGuildMasterCount") == null || data.get("maxConcurrentGuildMasterCount").isNull() ? null : data.get("maxConcurrentGuildMasterCount").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("defaultMaximumMemberCount", getDefaultMaximumMemberCount());
                put("maximumMemberCount", getMaximumMemberCount());
                put("inactivityPeriodDays", getInactivityPeriodDays());
                put("roles", getRoles() == null ? null :
                    getRoles().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("guildMasterRole", getGuildMasterRole());
                put("guildMemberDefaultRole", getGuildMemberDefaultRole());
                put("rejoinCoolTimeMinutes", getRejoinCoolTimeMinutes());
                put("maxConcurrentJoinGuilds", getMaxConcurrentJoinGuilds());
                put("maxConcurrentGuildMasterCount", getMaxConcurrentGuildMasterCount());
            }}
        );
    }
}