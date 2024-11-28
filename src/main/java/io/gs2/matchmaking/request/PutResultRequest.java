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
public class PutResultRequest extends Gs2BasicRequest<PutResultRequest> {
    private String namespaceName;
    private String ratingName;
    private List<GameResult> gameResults;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public PutResultRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRatingName() {
		return ratingName;
	}
	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}
	public PutResultRequest withRatingName(String ratingName) {
		this.ratingName = ratingName;
		return this;
	}
	public List<GameResult> getGameResults() {
		return gameResults;
	}
	public void setGameResults(List<GameResult> gameResults) {
		this.gameResults = gameResults;
	}
	public PutResultRequest withGameResults(List<GameResult> gameResults) {
		this.gameResults = gameResults;
		return this;
	}

    public static PutResultRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PutResultRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRatingName(data.get("ratingName") == null || data.get("ratingName").isNull() ? null : data.get("ratingName").asText())
            .withGameResults(data.get("gameResults") == null || data.get("gameResults").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("gameResults").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return GameResult.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("ratingName", getRatingName());
                put("gameResults", getGameResults() == null ? null :
                    getGameResults().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}