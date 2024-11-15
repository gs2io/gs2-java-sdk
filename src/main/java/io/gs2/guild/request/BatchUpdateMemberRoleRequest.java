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
import io.gs2.guild.model.Member;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BatchUpdateMemberRoleRequest extends Gs2BasicRequest<BatchUpdateMemberRoleRequest> {
    private String namespaceName;
    private String guildModelName;
    private String accessToken;
    private List<Member> members;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public BatchUpdateMemberRoleRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getGuildModelName() {
		return guildModelName;
	}
	public void setGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
	}
	public BatchUpdateMemberRoleRequest withGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public BatchUpdateMemberRoleRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	public BatchUpdateMemberRoleRequest withMembers(List<Member> members) {
		this.members = members;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public BatchUpdateMemberRoleRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static BatchUpdateMemberRoleRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BatchUpdateMemberRoleRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withGuildModelName(data.get("guildModelName") == null || data.get("guildModelName").isNull() ? null : data.get("guildModelName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withMembers(data.get("members") == null || data.get("members").isNull() ? new ArrayList<Member>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("members").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Member.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("guildModelName", getGuildModelName());
                put("accessToken", getAccessToken());
                put("members", getMembers() == null ? new ArrayList<Member>() :
                    getMembers().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}