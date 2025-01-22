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
public class CreateGuildRequest extends Gs2BasicRequest<CreateGuildRequest> {
    private String namespaceName;
    private String accessToken;
    private String guildModelName;
    private String displayName;
    private Integer attribute1;
    private Integer attribute2;
    private Integer attribute3;
    private Integer attribute4;
    private Integer attribute5;
    private String metadata;
    private String memberMetadata;
    private String joinPolicy;
    private List<RoleModel> customRoles;
    private String guildMemberDefaultRole;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateGuildRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public CreateGuildRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getGuildModelName() {
		return guildModelName;
	}
	public void setGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
	}
	public CreateGuildRequest withGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
		return this;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public CreateGuildRequest withDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}
	public Integer getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(Integer attribute1) {
		this.attribute1 = attribute1;
	}
	public CreateGuildRequest withAttribute1(Integer attribute1) {
		this.attribute1 = attribute1;
		return this;
	}
	public Integer getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(Integer attribute2) {
		this.attribute2 = attribute2;
	}
	public CreateGuildRequest withAttribute2(Integer attribute2) {
		this.attribute2 = attribute2;
		return this;
	}
	public Integer getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(Integer attribute3) {
		this.attribute3 = attribute3;
	}
	public CreateGuildRequest withAttribute3(Integer attribute3) {
		this.attribute3 = attribute3;
		return this;
	}
	public Integer getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(Integer attribute4) {
		this.attribute4 = attribute4;
	}
	public CreateGuildRequest withAttribute4(Integer attribute4) {
		this.attribute4 = attribute4;
		return this;
	}
	public Integer getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(Integer attribute5) {
		this.attribute5 = attribute5;
	}
	public CreateGuildRequest withAttribute5(Integer attribute5) {
		this.attribute5 = attribute5;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CreateGuildRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getMemberMetadata() {
		return memberMetadata;
	}
	public void setMemberMetadata(String memberMetadata) {
		this.memberMetadata = memberMetadata;
	}
	public CreateGuildRequest withMemberMetadata(String memberMetadata) {
		this.memberMetadata = memberMetadata;
		return this;
	}
	public String getJoinPolicy() {
		return joinPolicy;
	}
	public void setJoinPolicy(String joinPolicy) {
		this.joinPolicy = joinPolicy;
	}
	public CreateGuildRequest withJoinPolicy(String joinPolicy) {
		this.joinPolicy = joinPolicy;
		return this;
	}
	public List<RoleModel> getCustomRoles() {
		return customRoles;
	}
	public void setCustomRoles(List<RoleModel> customRoles) {
		this.customRoles = customRoles;
	}
	public CreateGuildRequest withCustomRoles(List<RoleModel> customRoles) {
		this.customRoles = customRoles;
		return this;
	}
	public String getGuildMemberDefaultRole() {
		return guildMemberDefaultRole;
	}
	public void setGuildMemberDefaultRole(String guildMemberDefaultRole) {
		this.guildMemberDefaultRole = guildMemberDefaultRole;
	}
	public CreateGuildRequest withGuildMemberDefaultRole(String guildMemberDefaultRole) {
		this.guildMemberDefaultRole = guildMemberDefaultRole;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public CreateGuildRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static CreateGuildRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateGuildRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withGuildModelName(data.get("guildModelName") == null || data.get("guildModelName").isNull() ? null : data.get("guildModelName").asText())
            .withDisplayName(data.get("displayName") == null || data.get("displayName").isNull() ? null : data.get("displayName").asText())
            .withAttribute1(data.get("attribute1") == null || data.get("attribute1").isNull() ? null : data.get("attribute1").intValue())
            .withAttribute2(data.get("attribute2") == null || data.get("attribute2").isNull() ? null : data.get("attribute2").intValue())
            .withAttribute3(data.get("attribute3") == null || data.get("attribute3").isNull() ? null : data.get("attribute3").intValue())
            .withAttribute4(data.get("attribute4") == null || data.get("attribute4").isNull() ? null : data.get("attribute4").intValue())
            .withAttribute5(data.get("attribute5") == null || data.get("attribute5").isNull() ? null : data.get("attribute5").intValue())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withMemberMetadata(data.get("memberMetadata") == null || data.get("memberMetadata").isNull() ? null : data.get("memberMetadata").asText())
            .withJoinPolicy(data.get("joinPolicy") == null || data.get("joinPolicy").isNull() ? null : data.get("joinPolicy").asText())
            .withCustomRoles(data.get("customRoles") == null || data.get("customRoles").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("customRoles").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RoleModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withGuildMemberDefaultRole(data.get("guildMemberDefaultRole") == null || data.get("guildMemberDefaultRole").isNull() ? null : data.get("guildMemberDefaultRole").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("guildModelName", getGuildModelName());
                put("displayName", getDisplayName());
                put("attribute1", getAttribute1());
                put("attribute2", getAttribute2());
                put("attribute3", getAttribute3());
                put("attribute4", getAttribute4());
                put("attribute5", getAttribute5());
                put("metadata", getMetadata());
                put("memberMetadata", getMemberMetadata());
                put("joinPolicy", getJoinPolicy());
                put("customRoles", getCustomRoles() == null ? null :
                    getCustomRoles().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("guildMemberDefaultRole", getGuildMemberDefaultRole());
            }}
        );
    }
}