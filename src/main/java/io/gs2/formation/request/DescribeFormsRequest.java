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

package io.gs2.formation.request;

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
public class DescribeFormsRequest extends Gs2BasicRequest<DescribeFormsRequest> {
    private String namespaceName;
    private String moldName;
    private String accessToken;
    private String pageToken;
    private Integer limit;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public DescribeFormsRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getMoldName() {
		return moldName;
	}
	public void setMoldName(String moldName) {
		this.moldName = moldName;
	}
	public DescribeFormsRequest withMoldName(String moldName) {
		this.moldName = moldName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public DescribeFormsRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getPageToken() {
		return pageToken;
	}
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}
	public DescribeFormsRequest withPageToken(String pageToken) {
		this.pageToken = pageToken;
		return this;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public DescribeFormsRequest withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

    public static DescribeFormsRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DescribeFormsRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withMoldName(data.get("moldName") == null || data.get("moldName").isNull() ? null : data.get("moldName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withPageToken(data.get("pageToken") == null || data.get("pageToken").isNull() ? null : data.get("pageToken").asText())
            .withLimit(data.get("limit") == null || data.get("limit").isNull() ? null : data.get("limit").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("moldName", getMoldName());
                put("accessToken", getAccessToken());
                put("pageToken", getPageToken());
                put("limit", getLimit());
            }}
        );
    }
}