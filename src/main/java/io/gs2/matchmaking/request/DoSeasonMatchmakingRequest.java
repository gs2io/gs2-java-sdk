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
public class DoSeasonMatchmakingRequest extends Gs2BasicRequest<DoSeasonMatchmakingRequest> {
    private String namespaceName;
    private String seasonName;
    private String accessToken;
    private String matchmakingContextToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public DoSeasonMatchmakingRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	public DoSeasonMatchmakingRequest withSeasonName(String seasonName) {
		this.seasonName = seasonName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public DoSeasonMatchmakingRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getMatchmakingContextToken() {
		return matchmakingContextToken;
	}
	public void setMatchmakingContextToken(String matchmakingContextToken) {
		this.matchmakingContextToken = matchmakingContextToken;
	}
	public DoSeasonMatchmakingRequest withMatchmakingContextToken(String matchmakingContextToken) {
		this.matchmakingContextToken = matchmakingContextToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public DoSeasonMatchmakingRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static DoSeasonMatchmakingRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DoSeasonMatchmakingRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withSeasonName(data.get("seasonName") == null || data.get("seasonName").isNull() ? null : data.get("seasonName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withMatchmakingContextToken(data.get("matchmakingContextToken") == null || data.get("matchmakingContextToken").isNull() ? null : data.get("matchmakingContextToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("seasonName", getSeasonName());
                put("accessToken", getAccessToken());
                put("matchmakingContextToken", getMatchmakingContextToken());
            }}
        );
    }
}