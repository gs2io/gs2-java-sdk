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

package io.gs2.money.request;

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
public class DescribeReceiptsRequest extends Gs2BasicRequest<DescribeReceiptsRequest> {
    private String namespaceName;
    private String userId;
    private Integer slot;
    private Long begin;
    private Long end;
    private String pageToken;
    private Integer limit;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public DescribeReceiptsRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public DescribeReceiptsRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Integer getSlot() {
		return slot;
	}

	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	public DescribeReceiptsRequest withSlot(Integer slot) {
		this.slot = slot;
		return this;
	}

	public Long getBegin() {
		return begin;
	}

	public void setBegin(Long begin) {
		this.begin = begin;
	}

	public DescribeReceiptsRequest withBegin(Long begin) {
		this.begin = begin;
		return this;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public DescribeReceiptsRequest withEnd(Long end) {
		this.end = end;
		return this;
	}

	public String getPageToken() {
		return pageToken;
	}

	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}

	public DescribeReceiptsRequest withPageToken(String pageToken) {
		this.pageToken = pageToken;
		return this;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public DescribeReceiptsRequest withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

    public static DescribeReceiptsRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DescribeReceiptsRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withSlot(data.get("slot") == null || data.get("slot").isNull() ? null : data.get("slot").intValue())
            .withBegin(data.get("begin") == null || data.get("begin").isNull() ? null : data.get("begin").longValue())
            .withEnd(data.get("end") == null || data.get("end").isNull() ? null : data.get("end").longValue())
            .withPageToken(data.get("pageToken") == null || data.get("pageToken").isNull() ? null : data.get("pageToken").asText())
            .withLimit(data.get("limit") == null || data.get("limit").isNull() ? null : data.get("limit").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("slot", getSlot());
                put("begin", getBegin());
                put("end", getEnd());
                put("pageToken", getPageToken());
                put("limit", getLimit());
            }}
        );
    }
}