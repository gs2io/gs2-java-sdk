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

package io.gs2.watch.request;

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
public class GetChartRequest extends Gs2BasicRequest<GetChartRequest> {
    private String metrics;
    private String grn;
    private List<String> queries;
    private String by;
    private String timeframe;
    private String size;
    private String format;
    private String aggregator;
    private String style;
    private String title;

	public String getMetrics() {
		return metrics;
	}

	public void setMetrics(String metrics) {
		this.metrics = metrics;
	}

	public GetChartRequest withMetrics(String metrics) {
		this.metrics = metrics;
		return this;
	}

	public String getGrn() {
		return grn;
	}

	public void setGrn(String grn) {
		this.grn = grn;
	}

	public GetChartRequest withGrn(String grn) {
		this.grn = grn;
		return this;
	}

	public List<String> getQueries() {
		return queries;
	}

	public void setQueries(List<String> queries) {
		this.queries = queries;
	}

	public GetChartRequest withQueries(List<String> queries) {
		this.queries = queries;
		return this;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public GetChartRequest withBy(String by) {
		this.by = by;
		return this;
	}

	public String getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(String timeframe) {
		this.timeframe = timeframe;
	}

	public GetChartRequest withTimeframe(String timeframe) {
		this.timeframe = timeframe;
		return this;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public GetChartRequest withSize(String size) {
		this.size = size;
		return this;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public GetChartRequest withFormat(String format) {
		this.format = format;
		return this;
	}

	public String getAggregator() {
		return aggregator;
	}

	public void setAggregator(String aggregator) {
		this.aggregator = aggregator;
	}

	public GetChartRequest withAggregator(String aggregator) {
		this.aggregator = aggregator;
		return this;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public GetChartRequest withStyle(String style) {
		this.style = style;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public GetChartRequest withTitle(String title) {
		this.title = title;
		return this;
	}

    public static GetChartRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetChartRequest()
            .withMetrics(data.get("metrics") == null || data.get("metrics").isNull() ? null : data.get("metrics").asText())
            .withGrn(data.get("grn") == null || data.get("grn").isNull() ? null : data.get("grn").asText())
            .withQueries(data.get("queries") == null || data.get("queries").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("queries").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withBy(data.get("by") == null || data.get("by").isNull() ? null : data.get("by").asText())
            .withTimeframe(data.get("timeframe") == null || data.get("timeframe").isNull() ? null : data.get("timeframe").asText())
            .withSize(data.get("size") == null || data.get("size").isNull() ? null : data.get("size").asText())
            .withFormat(data.get("format") == null || data.get("format").isNull() ? null : data.get("format").asText())
            .withAggregator(data.get("aggregator") == null || data.get("aggregator").isNull() ? null : data.get("aggregator").asText())
            .withStyle(data.get("style") == null || data.get("style").isNull() ? null : data.get("style").asText())
            .withTitle(data.get("title") == null || data.get("title").isNull() ? null : data.get("title").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("metrics", getMetrics());
                put("grn", getGrn());
                put("queries", getQueries() == null ? new ArrayList<String>() :
                    getQueries().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("by", getBy());
                put("timeframe", getTimeframe());
                put("size", getSize());
                put("format", getFormat());
                put("aggregator", getAggregator());
                put("style", getStyle());
                put("title", getTitle());
            }}
        );
    }
}