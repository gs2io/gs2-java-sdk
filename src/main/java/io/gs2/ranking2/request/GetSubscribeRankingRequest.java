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

package io.gs2.ranking2.request;

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
public class GetSubscribeRankingRequest extends Gs2BasicRequest<GetSubscribeRankingRequest> {
    private String namespaceName;
    private String rankingName;
    private String accessToken;
    private Long season;
    private String scorerUserId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetSubscribeRankingRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRankingName() {
		return rankingName;
	}
	public void setRankingName(String rankingName) {
		this.rankingName = rankingName;
	}
	public GetSubscribeRankingRequest withRankingName(String rankingName) {
		this.rankingName = rankingName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public GetSubscribeRankingRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public Long getSeason() {
		return season;
	}
	public void setSeason(Long season) {
		this.season = season;
	}
	public GetSubscribeRankingRequest withSeason(Long season) {
		this.season = season;
		return this;
	}
	public String getScorerUserId() {
		return scorerUserId;
	}
	public void setScorerUserId(String scorerUserId) {
		this.scorerUserId = scorerUserId;
	}
	public GetSubscribeRankingRequest withScorerUserId(String scorerUserId) {
		this.scorerUserId = scorerUserId;
		return this;
	}

    public static GetSubscribeRankingRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetSubscribeRankingRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRankingName(data.get("rankingName") == null || data.get("rankingName").isNull() ? null : data.get("rankingName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withSeason(data.get("season") == null || data.get("season").isNull() ? null : data.get("season").longValue())
            .withScorerUserId(data.get("scorerUserId") == null || data.get("scorerUserId").isNull() ? null : data.get("scorerUserId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("rankingName", getRankingName());
                put("accessToken", getAccessToken());
                put("season", getSeason());
                put("scorerUserId", getScorerUserId());
            }}
        );
    }
}