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
public class SetMaximumCurrentMaximumMemberCountByGuildNameRequest extends Gs2BasicRequest<SetMaximumCurrentMaximumMemberCountByGuildNameRequest> {
    private String namespaceName;
    private String guildName;
    private String guildModelName;
    private Integer value;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public SetMaximumCurrentMaximumMemberCountByGuildNameRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getGuildName() {
		return guildName;
	}
	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}
	public SetMaximumCurrentMaximumMemberCountByGuildNameRequest withGuildName(String guildName) {
		this.guildName = guildName;
		return this;
	}
	public String getGuildModelName() {
		return guildModelName;
	}
	public void setGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
	}
	public SetMaximumCurrentMaximumMemberCountByGuildNameRequest withGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
		return this;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public SetMaximumCurrentMaximumMemberCountByGuildNameRequest withValue(Integer value) {
		this.value = value;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public SetMaximumCurrentMaximumMemberCountByGuildNameRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static SetMaximumCurrentMaximumMemberCountByGuildNameRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetMaximumCurrentMaximumMemberCountByGuildNameRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withGuildName(data.get("guildName") == null || data.get("guildName").isNull() ? null : data.get("guildName").asText())
            .withGuildModelName(data.get("guildModelName") == null || data.get("guildModelName").isNull() ? null : data.get("guildModelName").asText())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("guildName", getGuildName());
                put("guildModelName", getGuildModelName());
                put("value", getValue());
            }}
        );
    }
}