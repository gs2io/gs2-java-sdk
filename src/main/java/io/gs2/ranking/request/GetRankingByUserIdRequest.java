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
public class GetRankingByUserIdRequest extends Gs2BasicRequest<GetRankingByUserIdRequest> {
    private String namespaceName;
    private String categoryName;
    private String userId;
    private String scorerUserId;
    private String uniqueId;
    private String additionalScopeName;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetRankingByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public GetRankingByUserIdRequest withCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public GetRankingByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getScorerUserId() {
		return scorerUserId;
	}
	public void setScorerUserId(String scorerUserId) {
		this.scorerUserId = scorerUserId;
	}
	public GetRankingByUserIdRequest withScorerUserId(String scorerUserId) {
		this.scorerUserId = scorerUserId;
		return this;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public GetRankingByUserIdRequest withUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
		return this;
	}
	public String getAdditionalScopeName() {
		return additionalScopeName;
	}
	public void setAdditionalScopeName(String additionalScopeName) {
		this.additionalScopeName = additionalScopeName;
	}
	public GetRankingByUserIdRequest withAdditionalScopeName(String additionalScopeName) {
		this.additionalScopeName = additionalScopeName;
		return this;
	}

    public static GetRankingByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetRankingByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withCategoryName(data.get("categoryName") == null || data.get("categoryName").isNull() ? null : data.get("categoryName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withScorerUserId(data.get("scorerUserId") == null || data.get("scorerUserId").isNull() ? null : data.get("scorerUserId").asText())
            .withUniqueId(data.get("uniqueId") == null || data.get("uniqueId").isNull() ? null : data.get("uniqueId").asText())
            .withAdditionalScopeName(data.get("additionalScopeName") == null || data.get("additionalScopeName").isNull() ? null : data.get("additionalScopeName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("categoryName", getCategoryName());
                put("userId", getUserId());
                put("scorerUserId", getScorerUserId());
                put("uniqueId", getUniqueId());
                put("additionalScopeName", getAdditionalScopeName());
            }}
        );
    }
}