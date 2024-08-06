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
import io.gs2.log.model.InGameLogTag;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class QueryInGameLogRequest extends Gs2BasicRequest<QueryInGameLogRequest> {
    private String namespaceName;
    private String userId;
    private List<InGameLogTag> tags;
    private Long begin;
    private Long end;
    private Boolean longTerm;
    private String pageToken;
    private Integer limit;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public QueryInGameLogRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public QueryInGameLogRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public List<InGameLogTag> getTags() {
		return tags;
	}
	public void setTags(List<InGameLogTag> tags) {
		this.tags = tags;
	}
	public QueryInGameLogRequest withTags(List<InGameLogTag> tags) {
		this.tags = tags;
		return this;
	}
	public Long getBegin() {
		return begin;
	}
	public void setBegin(Long begin) {
		this.begin = begin;
	}
	public QueryInGameLogRequest withBegin(Long begin) {
		this.begin = begin;
		return this;
	}
	public Long getEnd() {
		return end;
	}
	public void setEnd(Long end) {
		this.end = end;
	}
	public QueryInGameLogRequest withEnd(Long end) {
		this.end = end;
		return this;
	}
	public Boolean getLongTerm() {
		return longTerm;
	}
	public void setLongTerm(Boolean longTerm) {
		this.longTerm = longTerm;
	}
	public QueryInGameLogRequest withLongTerm(Boolean longTerm) {
		this.longTerm = longTerm;
		return this;
	}
	public String getPageToken() {
		return pageToken;
	}
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}
	public QueryInGameLogRequest withPageToken(String pageToken) {
		this.pageToken = pageToken;
		return this;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public QueryInGameLogRequest withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public QueryInGameLogRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public QueryInGameLogRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static QueryInGameLogRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new QueryInGameLogRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withTags(data.get("tags") == null || data.get("tags").isNull() ? new ArrayList<InGameLogTag>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("tags").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return InGameLogTag.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withBegin(data.get("begin") == null || data.get("begin").isNull() ? null : data.get("begin").longValue())
            .withEnd(data.get("end") == null || data.get("end").isNull() ? null : data.get("end").longValue())
            .withLongTerm(data.get("longTerm") == null || data.get("longTerm").isNull() ? null : data.get("longTerm").booleanValue())
            .withPageToken(data.get("pageToken") == null || data.get("pageToken").isNull() ? null : data.get("pageToken").asText())
            .withLimit(data.get("limit") == null || data.get("limit").isNull() ? null : data.get("limit").intValue())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("tags", getTags() == null ? new ArrayList<InGameLogTag>() :
                    getTags().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("begin", getBegin());
                put("end", getEnd());
                put("longTerm", getLongTerm());
                put("pageToken", getPageToken());
                put("limit", getLimit());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}