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
public class QueryMetricsTimeseriesRequest extends Gs2BasicRequest<QueryMetricsTimeseriesRequest> {
    private String namespaceName;
    private Long begin;
    private Long end;
    private String query;
    private List<String> groupBy;
    private List<AggregationConfig> aggregations;
    private Integer interval;
    private Integer seriesLimit;
    private String orderKey;
    private String orderBy;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public QueryMetricsTimeseriesRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public Long getBegin() {
		return begin;
	}
	public void setBegin(Long begin) {
		this.begin = begin;
	}
	public QueryMetricsTimeseriesRequest withBegin(Long begin) {
		this.begin = begin;
		return this;
	}
	public Long getEnd() {
		return end;
	}
	public void setEnd(Long end) {
		this.end = end;
	}
	public QueryMetricsTimeseriesRequest withEnd(Long end) {
		this.end = end;
		return this;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public QueryMetricsTimeseriesRequest withQuery(String query) {
		this.query = query;
		return this;
	}
	public List<String> getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(List<String> groupBy) {
		this.groupBy = groupBy;
	}
	public QueryMetricsTimeseriesRequest withGroupBy(List<String> groupBy) {
		this.groupBy = groupBy;
		return this;
	}
	public List<AggregationConfig> getAggregations() {
		return aggregations;
	}
	public void setAggregations(List<AggregationConfig> aggregations) {
		this.aggregations = aggregations;
	}
	public QueryMetricsTimeseriesRequest withAggregations(List<AggregationConfig> aggregations) {
		this.aggregations = aggregations;
		return this;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	public QueryMetricsTimeseriesRequest withInterval(Integer interval) {
		this.interval = interval;
		return this;
	}
	public Integer getSeriesLimit() {
		return seriesLimit;
	}
	public void setSeriesLimit(Integer seriesLimit) {
		this.seriesLimit = seriesLimit;
	}
	public QueryMetricsTimeseriesRequest withSeriesLimit(Integer seriesLimit) {
		this.seriesLimit = seriesLimit;
		return this;
	}
	public String getOrderKey() {
		return orderKey;
	}
	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}
	public QueryMetricsTimeseriesRequest withOrderKey(String orderKey) {
		this.orderKey = orderKey;
		return this;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public QueryMetricsTimeseriesRequest withOrderBy(String orderBy) {
		this.orderBy = orderBy;
		return this;
	}

    public static QueryMetricsTimeseriesRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new QueryMetricsTimeseriesRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withBegin(data.get("begin") == null || data.get("begin").isNull() ? null : data.get("begin").longValue())
            .withEnd(data.get("end") == null || data.get("end").isNull() ? null : data.get("end").longValue())
            .withQuery(data.get("query") == null || data.get("query").isNull() ? null : data.get("query").asText())
            .withGroupBy(data.get("groupBy") == null || data.get("groupBy").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("groupBy").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withAggregations(data.get("aggregations") == null || data.get("aggregations").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("aggregations").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AggregationConfig.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withInterval(data.get("interval") == null || data.get("interval").isNull() ? null : data.get("interval").intValue())
            .withSeriesLimit(data.get("seriesLimit") == null || data.get("seriesLimit").isNull() ? null : data.get("seriesLimit").intValue())
            .withOrderKey(data.get("orderKey") == null || data.get("orderKey").isNull() ? null : data.get("orderKey").asText())
            .withOrderBy(data.get("orderBy") == null || data.get("orderBy").isNull() ? null : data.get("orderBy").asText());
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
                put("aggregations", getAggregations() == null ? null :
                    getAggregations().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("interval", getInterval());
                put("seriesLimit", getSeriesLimit());
                put("orderKey", getOrderKey());
                put("orderBy", getOrderBy());
            }}
        );
    }
}