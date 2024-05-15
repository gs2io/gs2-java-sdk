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
public class JoinedGuild implements IModel, Serializable, Comparable<JoinedGuild> {
	private String joinedGuildId;
	private String guildModelName;
	private String guildName;
	private String userId;
	private Long createdAt;
	public String getJoinedGuildId() {
		return joinedGuildId;
	}
	public void setJoinedGuildId(String joinedGuildId) {
		this.joinedGuildId = joinedGuildId;
	}
	public JoinedGuild withJoinedGuildId(String joinedGuildId) {
		this.joinedGuildId = joinedGuildId;
		return this;
	}
	public String getGuildModelName() {
		return guildModelName;
	}
	public void setGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
	}
	public JoinedGuild withGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
		return this;
	}
	public String getGuildName() {
		return guildName;
	}
	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}
	public JoinedGuild withGuildName(String guildName) {
		this.guildName = guildName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public JoinedGuild withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public JoinedGuild withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static JoinedGuild fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new JoinedGuild()
            .withJoinedGuildId(data.get("joinedGuildId") == null || data.get("joinedGuildId").isNull() ? null : data.get("joinedGuildId").asText())
            .withGuildModelName(data.get("guildModelName") == null || data.get("guildModelName").isNull() ? null : data.get("guildModelName").asText())
            .withGuildName(data.get("guildName") == null || data.get("guildName").isNull() ? null : data.get("guildName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("joinedGuildId", getJoinedGuildId());
                put("guildModelName", getGuildModelName());
                put("guildName", getGuildName());
                put("userId", getUserId());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(JoinedGuild o) {
		return joinedGuildId.compareTo(o.joinedGuildId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.joinedGuildId == null) ? 0 : this.joinedGuildId.hashCode());
        result = prime * result + ((this.guildModelName == null) ? 0 : this.guildModelName.hashCode());
        result = prime * result + ((this.guildName == null) ? 0 : this.guildName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		JoinedGuild other = (JoinedGuild) o;
		if (joinedGuildId == null) {
			return other.joinedGuildId == null;
		} else if (!joinedGuildId.equals(other.joinedGuildId)) {
			return false;
		}
		if (guildModelName == null) {
			return other.guildModelName == null;
		} else if (!guildModelName.equals(other.guildModelName)) {
			return false;
		}
		if (guildName == null) {
			return other.guildName == null;
		} else if (!guildName.equals(other.guildName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		return true;
	}
}