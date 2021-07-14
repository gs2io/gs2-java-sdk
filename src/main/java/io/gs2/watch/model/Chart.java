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

package io.gs2.watch.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Chart implements IModel, Serializable, Comparable<Chart> {
	private String chartId;
	private String embedId;
	private String html;

	public String getChartId() {
		return chartId;
	}

	public void setChartId(String chartId) {
		this.chartId = chartId;
	}

	public Chart withChartId(String chartId) {
		this.chartId = chartId;
		return this;
	}

	public String getEmbedId() {
		return embedId;
	}

	public void setEmbedId(String embedId) {
		this.embedId = embedId;
	}

	public Chart withEmbedId(String embedId) {
		this.embedId = embedId;
		return this;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Chart withHtml(String html) {
		this.html = html;
		return this;
	}

    public static Chart fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Chart()
            .withChartId(data.get("chartId") == null || data.get("chartId").isNull() ? null : data.get("chartId").asText())
            .withEmbedId(data.get("embedId") == null || data.get("embedId").isNull() ? null : data.get("embedId").asText())
            .withHtml(data.get("html") == null || data.get("html").isNull() ? null : data.get("html").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("chartId", getChartId());
                put("embedId", getEmbedId());
                put("html", getHtml());
            }}
        );
    }

	@Override
	public int compareTo(Chart o) {
		return chartId.compareTo(o.chartId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.chartId == null) ? 0 : this.chartId.hashCode());
        result = prime * result + ((this.embedId == null) ? 0 : this.embedId.hashCode());
        result = prime * result + ((this.html == null) ? 0 : this.html.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Chart other = (Chart) o;
		if (chartId == null) {
			return other.chartId == null;
		} else if (!chartId.equals(other.chartId)) {
			return false;
		}
		if (embedId == null) {
			return other.embedId == null;
		} else if (!embedId.equals(other.embedId)) {
			return false;
		}
		if (html == null) {
			return other.html == null;
		} else if (!html.equals(other.html)) {
			return false;
		}
		return true;
	}
}