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

package io.gs2.inbox.request;

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
public class DescribeNamespacesRequest extends Gs2BasicRequest<DescribeNamespacesRequest> {
    private String namePrefix;
    private String pageToken;
    private Integer limit;
	public String getNamePrefix() {
		return namePrefix;
	}
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}
	public DescribeNamespacesRequest withNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
		return this;
	}
	public String getPageToken() {
		return pageToken;
	}
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}
	public DescribeNamespacesRequest withPageToken(String pageToken) {
		this.pageToken = pageToken;
		return this;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public DescribeNamespacesRequest withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

    public static DescribeNamespacesRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DescribeNamespacesRequest()
            .withNamePrefix(data.get("namePrefix") == null || data.get("namePrefix").isNull() ? null : data.get("namePrefix").asText())
            .withPageToken(data.get("pageToken") == null || data.get("pageToken").isNull() ? null : data.get("pageToken").asText())
            .withLimit(data.get("limit") == null || data.get("limit").isNull() ? null : data.get("limit").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namePrefix", getNamePrefix());
                put("pageToken", getPageToken());
                put("limit", getLimit());
            }}
        );
    }
}