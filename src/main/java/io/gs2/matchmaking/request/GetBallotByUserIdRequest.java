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
public class GetBallotByUserIdRequest extends Gs2BasicRequest<GetBallotByUserIdRequest> {
    private String namespaceName;
    private String ratingName;
    private String gatheringName;
    private String userId;
    private Integer numberOfPlayer;
    private String keyId;
    private String timeOffsetToken;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetBallotByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRatingName() {
		return ratingName;
	}
	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}
	public GetBallotByUserIdRequest withRatingName(String ratingName) {
		this.ratingName = ratingName;
		return this;
	}
	public String getGatheringName() {
		return gatheringName;
	}
	public void setGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
	}
	public GetBallotByUserIdRequest withGatheringName(String gatheringName) {
		this.gatheringName = gatheringName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public GetBallotByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getNumberOfPlayer() {
		return numberOfPlayer;
	}
	public void setNumberOfPlayer(Integer numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
	}
	public GetBallotByUserIdRequest withNumberOfPlayer(Integer numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
		return this;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public GetBallotByUserIdRequest withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public GetBallotByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

    public static GetBallotByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetBallotByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRatingName(data.get("ratingName") == null || data.get("ratingName").isNull() ? null : data.get("ratingName").asText())
            .withGatheringName(data.get("gatheringName") == null || data.get("gatheringName").isNull() ? null : data.get("gatheringName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withNumberOfPlayer(data.get("numberOfPlayer") == null || data.get("numberOfPlayer").isNull() ? null : data.get("numberOfPlayer").intValue())
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("ratingName", getRatingName());
                put("gatheringName", getGatheringName());
                put("userId", getUserId());
                put("numberOfPlayer", getNumberOfPlayer());
                put("keyId", getKeyId());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}