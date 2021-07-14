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

package io.gs2.log.request;

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
public class CountExecuteStampTaskLogRequest extends Gs2BasicRequest<CountExecuteStampTaskLogRequest> {
    private String namespaceName;
    private String service;
    private String method;
    private String userId;
    private String action;
    private Long begin;
    private Long end;
    private Boolean longTerm;
    private String pageToken;
    private Integer limit;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public CountExecuteStampTaskLogRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public CountExecuteStampTaskLogRequest withService(String service) {
		this.service = service;
		return this;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public CountExecuteStampTaskLogRequest withMethod(String method) {
		this.method = method;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public CountExecuteStampTaskLogRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public CountExecuteStampTaskLogRequest withAction(String action) {
		this.action = action;
		return this;
	}

	public Long getBegin() {
		return begin;
	}

	public void setBegin(Long begin) {
		this.begin = begin;
	}

	public CountExecuteStampTaskLogRequest withBegin(Long begin) {
		this.begin = begin;
		return this;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public CountExecuteStampTaskLogRequest withEnd(Long end) {
		this.end = end;
		return this;
	}

	public Boolean getLongTerm() {
		return longTerm;
	}

	public void setLongTerm(Boolean longTerm) {
		this.longTerm = longTerm;
	}

	public CountExecuteStampTaskLogRequest withLongTerm(Boolean longTerm) {
		this.longTerm = longTerm;
		return this;
	}

	public String getPageToken() {
		return pageToken;
	}

	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}

	public CountExecuteStampTaskLogRequest withPageToken(String pageToken) {
		this.pageToken = pageToken;
		return this;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public CountExecuteStampTaskLogRequest withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

    public static CountExecuteStampTaskLogRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CountExecuteStampTaskLogRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withService(data.get("service") == null || data.get("service").isNull() ? null : data.get("service").asText())
            .withMethod(data.get("method") == null || data.get("method").isNull() ? null : data.get("method").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withAction(data.get("action") == null || data.get("action").isNull() ? null : data.get("action").asText())
            .withBegin(data.get("begin") == null || data.get("begin").isNull() ? null : data.get("begin").longValue())
            .withEnd(data.get("end") == null || data.get("end").isNull() ? null : data.get("end").longValue())
            .withLongTerm(data.get("longTerm") == null || data.get("longTerm").isNull() ? null : data.get("longTerm").booleanValue())
            .withPageToken(data.get("pageToken") == null || data.get("pageToken").isNull() ? null : data.get("pageToken").asText())
            .withLimit(data.get("limit") == null || data.get("limit").isNull() ? null : data.get("limit").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("service", getService());
                put("method", getMethod());
                put("userId", getUserId());
                put("action", getAction());
                put("begin", getBegin());
                put("end", getEnd());
                put("longTerm", getLongTerm());
                put("pageToken", getPageToken());
                put("limit", getLimit());
            }}
        );
    }
}