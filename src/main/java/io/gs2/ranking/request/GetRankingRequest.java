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

package io.gs2.ranking.request;

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
public class GetRankingRequest extends Gs2BasicRequest<GetRankingRequest> {
    private String namespaceName;
    private String categoryName;
    private String accessToken;
    private String scorerUserId;
    private String uniqueId;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public GetRankingRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public GetRankingRequest withCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public GetRankingRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	public String getScorerUserId() {
		return scorerUserId;
	}

	public void setScorerUserId(String scorerUserId) {
		this.scorerUserId = scorerUserId;
	}

	public GetRankingRequest withScorerUserId(String scorerUserId) {
		this.scorerUserId = scorerUserId;
		return this;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public GetRankingRequest withUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
		return this;
	}

    public static GetRankingRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetRankingRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withCategoryName(data.get("categoryName") == null || data.get("categoryName").isNull() ? null : data.get("categoryName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withScorerUserId(data.get("scorerUserId") == null || data.get("scorerUserId").isNull() ? null : data.get("scorerUserId").asText())
            .withUniqueId(data.get("uniqueId") == null || data.get("uniqueId").isNull() ? null : data.get("uniqueId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("categoryName", getCategoryName());
                put("accessToken", getAccessToken());
                put("scorerUserId", getScorerUserId());
                put("uniqueId", getUniqueId());
            }}
        );
    }
}