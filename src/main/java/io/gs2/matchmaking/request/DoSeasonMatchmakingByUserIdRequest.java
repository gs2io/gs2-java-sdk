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

package io.gs2.matchmaking.request;

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
public class DoSeasonMatchmakingByUserIdRequest extends Gs2BasicRequest<DoSeasonMatchmakingByUserIdRequest> {
    private String namespaceName;
    private String seasonName;
    private String userId;
    private String matchmakingContextToken;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public DoSeasonMatchmakingByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	public DoSeasonMatchmakingByUserIdRequest withSeasonName(String seasonName) {
		this.seasonName = seasonName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public DoSeasonMatchmakingByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getMatchmakingContextToken() {
		return matchmakingContextToken;
	}
	public void setMatchmakingContextToken(String matchmakingContextToken) {
		this.matchmakingContextToken = matchmakingContextToken;
	}
	public DoSeasonMatchmakingByUserIdRequest withMatchmakingContextToken(String matchmakingContextToken) {
		this.matchmakingContextToken = matchmakingContextToken;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public DoSeasonMatchmakingByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public DoSeasonMatchmakingByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static DoSeasonMatchmakingByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DoSeasonMatchmakingByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withSeasonName(data.get("seasonName") == null || data.get("seasonName").isNull() ? null : data.get("seasonName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withMatchmakingContextToken(data.get("matchmakingContextToken") == null || data.get("matchmakingContextToken").isNull() ? null : data.get("matchmakingContextToken").asText())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("seasonName", getSeasonName());
                put("userId", getUserId());
                put("matchmakingContextToken", getMatchmakingContextToken());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}