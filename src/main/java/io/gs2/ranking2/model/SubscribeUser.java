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

package io.gs2.ranking2.model;

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
public class SubscribeUser implements IModel, Serializable {
	private String rankingName;
	private String userId;
	private String targetUserId;
	public String getRankingName() {
		return rankingName;
	}
	public void setRankingName(String rankingName) {
		this.rankingName = rankingName;
	}
	public SubscribeUser withRankingName(String rankingName) {
		this.rankingName = rankingName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SubscribeUser withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}
	public SubscribeUser withTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
		return this;
	}

    public static SubscribeUser fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SubscribeUser()
            .withRankingName(data.get("rankingName") == null || data.get("rankingName").isNull() ? null : data.get("rankingName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withTargetUserId(data.get("targetUserId") == null || data.get("targetUserId").isNull() ? null : data.get("targetUserId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("rankingName", getRankingName());
                put("userId", getUserId());
                put("targetUserId", getTargetUserId());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.rankingName == null) ? 0 : this.rankingName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.targetUserId == null) ? 0 : this.targetUserId.hashCode());
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
		SubscribeUser other = (SubscribeUser) o;
		if (rankingName == null) {
			return other.rankingName == null;
		} else if (!rankingName.equals(other.rankingName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (targetUserId == null) {
			return other.targetUserId == null;
		} else if (!targetUserId.equals(other.targetUserId)) {
			return false;
		}
		return true;
	}
}