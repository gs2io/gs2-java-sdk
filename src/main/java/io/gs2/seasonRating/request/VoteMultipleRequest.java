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

package io.gs2.seasonRating.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.seasonRating.model.SignedBallot;
import io.gs2.seasonRating.model.GameResult;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class VoteMultipleRequest extends Gs2BasicRequest<VoteMultipleRequest> {
    private String namespaceName;
    private List<SignedBallot> signedBallots;
    private List<GameResult> gameResults;
    private String keyId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public VoteMultipleRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public List<SignedBallot> getSignedBallots() {
		return signedBallots;
	}
	public void setSignedBallots(List<SignedBallot> signedBallots) {
		this.signedBallots = signedBallots;
	}
	public VoteMultipleRequest withSignedBallots(List<SignedBallot> signedBallots) {
		this.signedBallots = signedBallots;
		return this;
	}
	public List<GameResult> getGameResults() {
		return gameResults;
	}
	public void setGameResults(List<GameResult> gameResults) {
		this.gameResults = gameResults;
	}
	public VoteMultipleRequest withGameResults(List<GameResult> gameResults) {
		this.gameResults = gameResults;
		return this;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public VoteMultipleRequest withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}

    public static VoteMultipleRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VoteMultipleRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withSignedBallots(data.get("signedBallots") == null || data.get("signedBallots").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("signedBallots").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return SignedBallot.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withGameResults(data.get("gameResults") == null || data.get("gameResults").isNull() ? null :
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
                put("signedBallots", getSignedBallots() == null ? null :
                    getSignedBallots().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("gameResults", getGameResults() == null ? null :
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