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
public class GetBallotRequest extends Gs2BasicRequest<GetBallotRequest> {
    private String namespaceName;
    private String ratingName;
    private String gatheringName;
    private String accessToken;
    private Integer numberOfPlayer;
    private String keyId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetBallotRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRatingName() {
		return ratingName;
	}
	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}
	public GetBallotRequest withRatingName(String ratingName) {
		this.ratingName = ratingName;
		return this;
	}
	public String getGatheringName() {
		return gatheringName;
	}
	public void setGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
	}
	public GetBallotRequest withGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public GetBallotRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public Integer getNumberOfPlayer() {
		return numberOfPlayer;
	}
	public void setNumberOfPlayer(Integer numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
	}
	public GetBallotRequest withNumberOfPlayer(Integer numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
		return this;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public GetBallotRequest withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}

    public static GetBallotRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetBallotRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRatingName(data.get("ratingName") == null || data.get("ratingName").isNull() ? null : data.get("ratingName").asText())
            .withGatheringName(data.get("gatheringName") == null || data.get("gatheringName").isNull() ? null : data.get("gatheringName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withNumberOfPlayer(data.get("numberOfPlayer") == null || data.get("numberOfPlayer").isNull() ? null : data.get("numberOfPlayer").intValue())
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("ratingName", getRatingName());
                put("gatheringName", getGatheringName());
                put("accessToken", getAccessToken());
                put("numberOfPlayer", getNumberOfPlayer());
                put("keyId", getKeyId());
            }}
        );
    }
}