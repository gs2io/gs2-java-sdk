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
import io.gs2.matchmaking.model.GameResult;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class VoteRequest extends Gs2BasicRequest<VoteRequest> {
    private String namespaceName;
    private String ballotBody;
    private String ballotSignature;
    private List<GameResult> gameResults;
    private String keyId;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public VoteRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getBallotBody() {
		return ballotBody;
	}

	public void setBallotBody(String ballotBody) {
		this.ballotBody = ballotBody;
	}

	public VoteRequest withBallotBody(String ballotBody) {
		this.ballotBody = ballotBody;
		return this;
	}

	public String getBallotSignature() {
		return ballotSignature;
	}

	public void setBallotSignature(String ballotSignature) {
		this.ballotSignature = ballotSignature;
	}

	public VoteRequest withBallotSignature(String ballotSignature) {
		this.ballotSignature = ballotSignature;
		return this;
	}

	public List<GameResult> getGameResults() {
		return gameResults;
	}

	public void setGameResults(List<GameResult> gameResults) {
		this.gameResults = gameResults;
	}

	public VoteRequest withGameResults(List<GameResult> gameResults) {
		this.gameResults = gameResults;
		return this;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public VoteRequest withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}

    public static VoteRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VoteRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withBallotBody(data.get("ballotBody") == null || data.get("ballotBody").isNull() ? null : data.get("ballotBody").asText())
            .withBallotSignature(data.get("ballotSignature") == null || data.get("ballotSignature").isNull() ? null : data.get("ballotSignature").asText())
            .withGameResults(data.get("gameResults") == null || data.get("gameResults").isNull() ? new ArrayList<GameResult>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("gameResults").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return GameResult.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("ballotBody", getBallotBody());
                put("ballotSignature", getBallotSignature());
                put("gameResults", getGameResults() == null ? new ArrayList<GameResult>() :
                    getGameResults().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("keyId", getKeyId());
            }}
        );
    }
}