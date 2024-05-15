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
public class DeleteRequestRequest extends Gs2BasicRequest<DeleteRequestRequest> {
    private String namespaceName;
    private String accessToken;
    private String guildModelName;
    private String targetGuildName;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public DeleteRequestRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public DeleteRequestRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getGuildModelName() {
		return guildModelName;
	}
	public void setGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
	}
	public DeleteRequestRequest withGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
		return this;
	}
	public String getTargetGuildName() {
		return targetGuildName;
	}
	public void setTargetGuildName(String targetGuildName) {
		this.targetGuildName = targetGuildName;
	}
	public DeleteRequestRequest withTargetGuildName(String targetGuildName) {
		this.targetGuildName = targetGuildName;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public DeleteRequestRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static DeleteRequestRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DeleteRequestRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withGuildModelName(data.get("guildModelName") == null || data.get("guildModelName").isNull() ? null : data.get("guildModelName").asText())
            .withTargetGuildName(data.get("targetGuildName") == null || data.get("targetGuildName").isNull() ? null : data.get("targetGuildName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("guildModelName", getGuildModelName());
                put("targetGuildName", getTargetGuildName());
            }}
        );
    }
}