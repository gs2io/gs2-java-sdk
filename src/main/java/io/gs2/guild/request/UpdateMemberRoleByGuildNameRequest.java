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

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateMemberRoleByGuildNameRequest extends Gs2BasicRequest<UpdateMemberRoleByGuildNameRequest> {
    private String namespaceName;
    private String guildModelName;
    private String guildName;
    private String targetUserId;
    private String roleName;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateMemberRoleByGuildNameRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getGuildModelName() {
		return guildModelName;
	}
	public void setGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
	}
	public UpdateMemberRoleByGuildNameRequest withGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
		return this;
	}
	public String getGuildName() {
		return guildName;
	}
	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}
	public UpdateMemberRoleByGuildNameRequest withGuildName(String guildName) {
		this.guildName = guildName;
		return this;
	}
	public String getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}
	public UpdateMemberRoleByGuildNameRequest withTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
		return this;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public UpdateMemberRoleByGuildNameRequest withRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public UpdateMemberRoleByGuildNameRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static UpdateMemberRoleByGuildNameRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateMemberRoleByGuildNameRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withGuildModelName(data.get("guildModelName") == null || data.get("guildModelName").isNull() ? null : data.get("guildModelName").asText())
            .withGuildName(data.get("guildName") == null || data.get("guildName").isNull() ? null : data.get("guildName").asText())
            .withTargetUserId(data.get("targetUserId") == null || data.get("targetUserId").isNull() ? null : data.get("targetUserId").asText())
            .withRoleName(data.get("roleName") == null || data.get("roleName").isNull() ? null : data.get("roleName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("guildModelName", getGuildModelName());
                put("guildName", getGuildName());
                put("targetUserId", getTargetUserId());
                put("roleName", getRoleName());
            }}
        );
    }
}