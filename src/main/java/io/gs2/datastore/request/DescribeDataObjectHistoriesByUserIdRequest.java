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

package io.gs2.datastore.request;

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
public class DescribeDataObjectHistoriesByUserIdRequest extends Gs2BasicRequest<DescribeDataObjectHistoriesByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String dataObjectName;
    private String pageToken;
    private Integer limit;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public DescribeDataObjectHistoriesByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public DescribeDataObjectHistoriesByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getDataObjectName() {
		return dataObjectName;
	}

	public void setDataObjectName(String dataObjectName) {
		this.dataObjectName = dataObjectName;
	}

	public DescribeDataObjectHistoriesByUserIdRequest withDataObjectName(String dataObjectName) {
		this.dataObjectName = dataObjectName;
		return this;
	}

	public String getPageToken() {
		return pageToken;
	}

	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}

	public DescribeDataObjectHistoriesByUserIdRequest withPageToken(String pageToken) {
		this.pageToken = pageToken;
		return this;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public DescribeDataObjectHistoriesByUserIdRequest withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

    public static DescribeDataObjectHistoriesByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DescribeDataObjectHistoriesByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withDataObjectName(data.get("dataObjectName") == null || data.get("dataObjectName").isNull() ? null : data.get("dataObjectName").asText())
            .withPageToken(data.get("pageToken") == null || data.get("pageToken").isNull() ? null : data.get("pageToken").asText())
            .withLimit(data.get("limit") == null || data.get("limit").isNull() ? null : data.get("limit").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("dataObjectName", getDataObjectName());
                put("pageToken", getPageToken());
                put("limit", getLimit());
            }}
        );
    }
}