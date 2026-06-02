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
import io.gs2.log.model.AggregationConfig;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class QueryTimeseriesRequest extends Gs2BasicRequest<QueryTimeseriesRequest> {
    private String namespaceName;
    private Long begin;
    private Long end;
    private String query;
    private List<String> groupBy;
    private AggregationConfig aggregation;
    private Integer interval;
    private Integer seriesLimit;
    private String pageToken;
    private Integer limit;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public QueryTimeseriesRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public Long getBegin() {
		return begin;
	}
	public void setBegin(Long begin) {
		this.begin = begin;
	}
	public QueryTimeseriesRequest withBegin(Long begin) {
		this.begin = begin;
		return this;
	}
	public Long getEnd() {
		return end;
	}
	public void setEnd(Long end) {
		this.end = end;
	}
	public QueryTimeseriesRequest withEnd(Long end) {
		this.end = end;
		return this;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public QueryTimeseriesRequest withQuery(String query) {
		this.query = query;
		return this;
	}
	public List<String> getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(List<String> groupBy) {
		this.groupBy = groupBy;
	}
	public QueryTimeseriesRequest withGroupBy(List<String> groupBy) {
		this.groupBy = groupBy;
		return this;
	}
	public AggregationConfig getAggregation() {
		return aggregation;
	}
	public void setAggregation(AggregationConfig aggregation) {
		this.aggregation = aggregation;
	}
	public QueryTimeseriesRequest withAggregation(AggregationConfig aggregation) {
		this.aggregation = aggregation;
		return this;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	public QueryTimeseriesRequest withInterval(Integer interval) {
		this.interval = interval;
		return this;
	}
	public Integer getSeriesLimit() {
		return seriesLimit;
	}
	public void setSeriesLimit(Integer seriesLimit) {
		this.seriesLimit = seriesLimit;
	}
	public QueryTimeseriesRequest withSeriesLimit(Integer seriesLimit) {
		this.seriesLimit = seriesLimit;
		return this;
	}
	public String getPageToken() {
		return pageToken;
	}
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}
	public QueryTimeseriesRequest withPageToken(String pageToken) {
		this.pageToken = pageToken;
		return this;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public QueryTimeseriesRequest withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

    public static QueryTimeseriesRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new QueryTimeseriesRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withBegin(data.get("begin") == null || data.get("begin").isNull() ? null : data.get("begin").longValue())
            .withEnd(data.get("end") == null || data.get("end").isNull() ? null : data.get("end").longValue())
            .withQuery(data.get("query") == null || data.get("query").isNull() ? null : data.get("query").asText())
            .withGroupBy(data.get("groupBy") == null || data.get("groupBy").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("groupBy").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withAggregation(data.get("aggregation") == null || data.get("aggregation").isNull() ? null : AggregationConfig.fromJson(data.get("aggregation")))
            .withInterval(data.get("interval") == null || data.get("interval").isNull() ? null : data.get("interval").intValue())
            .withSeriesLimit(data.get("seriesLimit") == null || data.get("seriesLimit").isNull() ? null : data.get("seriesLimit").intValue())
            .withPageToken(data.get("pageToken") == null || data.get("pageToken").isNull() ? null : data.get("pageToken").asText())
            .withLimit(data.get("limit") == null || data.get("limit").isNull() ? null : data.get("limit").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("begin", getBegin());
                put("end", getEnd());
                put("query", getQuery());
                put("groupBy", getGroupBy() == null ? null :
                    getGroupBy().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("aggregation", getAggregation() != null ? getAggregation().toJson() : null);
                put("interval", getInterval());
                put("seriesLimit", getSeriesLimit());
                put("pageToken", getPageToken());
                put("limit", getLimit());
            }}
        );
    }
}