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
public class SendMemberRequest implements IModel, Serializable, Comparable<SendMemberRequest> {
	private String userId;
	private String targetGuildName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SendMemberRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getTargetGuildName() {
		return targetGuildName;
	}
	public void setTargetGuildName(String targetGuildName) {
		this.targetGuildName = targetGuildName;
	}
	public SendMemberRequest withTargetGuildName(String targetGuildName) {
		this.targetGuildName = targetGuildName;
		return this;
	}

    public static SendMemberRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SendMemberRequest()
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withTargetGuildName(data.get("targetGuildName") == null || data.get("targetGuildName").isNull() ? null : data.get("targetGuildName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("userId", getUserId());
                put("targetGuildName", getTargetGuildName());
            }}
        );
    }

	@Override
	public int compareTo(SendMemberRequest o) {
		return userId.compareTo(o.userId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.targetGuildName == null) ? 0 : this.targetGuildName.hashCode());
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
		SendMemberRequest other = (SendMemberRequest) o;
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (targetGuildName == null) {
			return other.targetGuildName == null;
		} else if (!targetGuildName.equals(other.targetGuildName)) {
			return false;
		}
		return true;
	}
}