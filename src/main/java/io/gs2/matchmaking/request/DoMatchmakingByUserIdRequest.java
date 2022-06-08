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
import io.gs2.matchmaking.model.Attribute;
import io.gs2.matchmaking.model.Player;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DoMatchmakingByUserIdRequest extends Gs2BasicRequest<DoMatchmakingByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private Player player;
    private String matchmakingContextToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public DoMatchmakingByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public DoMatchmakingByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public DoMatchmakingByUserIdRequest withPlayer(Player player) {
		this.player = player;
		return this;
	}
	public String getMatchmakingContextToken() {
		return matchmakingContextToken;
	}
	public void setMatchmakingContextToken(String matchmakingContextToken) {
		this.matchmakingContextToken = matchmakingContextToken;
	}
	public DoMatchmakingByUserIdRequest withMatchmakingContextToken(String matchmakingContextToken) {
		this.matchmakingContextToken = matchmakingContextToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public DoMatchmakingByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static DoMatchmakingByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DoMatchmakingByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withPlayer(data.get("player") == null || data.get("player").isNull() ? null : Player.fromJson(data.get("player")))
            .withMatchmakingContextToken(data.get("matchmakingContextToken") == null || data.get("matchmakingContextToken").isNull() ? null : data.get("matchmakingContextToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("player", getPlayer() != null ? getPlayer().toJson() : null);
                put("matchmakingContextToken", getMatchmakingContextToken());
            }}
        );
    }
}