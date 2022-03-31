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
public class StatsEvent implements IModel, Serializable {
	private String grn;
	private String service;
	private String method;
	private String metric;
	private Boolean cumulative;
	private Double value;
	private List<String> tags;
	private Long callAt;

	public String getGrn() {
		return grn;
	}

	public void setGrn(String grn) {
		this.grn = grn;
	}

	public StatsEvent withGrn(String grn) {
		this.grn = grn;
		return this;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public StatsEvent withService(String service) {
		this.service = service;
		return this;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public StatsEvent withMethod(String method) {
		this.method = method;
		return this;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	public StatsEvent withMetric(String metric) {
		this.metric = metric;
		return this;
	}

	public Boolean getCumulative() {
		return cumulative;
	}

	public void setCumulative(Boolean cumulative) {
		this.cumulative = cumulative;
	}

	public StatsEvent withCumulative(Boolean cumulative) {
		this.cumulative = cumulative;
		return this;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public StatsEvent withValue(Double value) {
		this.value = value;
		return this;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public StatsEvent withTags(List<String> tags) {
		this.tags = tags;
		return this;
	}

	public Long getCallAt() {
		return callAt;
	}

	public void setCallAt(Long callAt) {
		this.callAt = callAt;
	}

	public StatsEvent withCallAt(Long callAt) {
		this.callAt = callAt;
		return this;
	}

    public static StatsEvent fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new StatsEvent()
            .withGrn(data.get("grn") == null || data.get("grn").isNull() ? null : data.get("grn").asText())
            .withService(data.get("service") == null || data.get("service").isNull() ? null : data.get("service").asText())
            .withMethod(data.get("method") == null || data.get("method").isNull() ? null : data.get("method").asText())
            .withMetric(data.get("metric") == null || data.get("metric").isNull() ? null : data.get("metric").asText())
            .withCumulative(data.get("cumulative") == null || data.get("cumulative").isNull() ? null : data.get("cumulative").booleanValue())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").doubleValue())
            .withTags(data.get("tags") == null || data.get("tags").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("tags").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withCallAt(data.get("callAt") == null || data.get("callAt").isNull() ? null : data.get("callAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("grn", getGrn());
                put("service", getService());
                put("method", getMethod());
                put("metric", getMetric());
                put("cumulative", getCumulative());
                put("value", getValue());
                put("tags", getTags() == null ? new ArrayList<String>() :
                    getTags().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("callAt", getCallAt());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.grn == null) ? 0 : this.grn.hashCode());
        result = prime * result + ((this.service == null) ? 0 : this.service.hashCode());
        result = prime * result + ((this.method == null) ? 0 : this.method.hashCode());
        result = prime * result + ((this.metric == null) ? 0 : this.metric.hashCode());
        result = prime * result + ((this.cumulative == null) ? 0 : this.cumulative.hashCode());
        result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
        result = prime * result + ((this.tags == null) ? 0 : this.tags.hashCode());
        result = prime * result + ((this.callAt == null) ? 0 : this.callAt.hashCode());
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
		StatsEvent other = (StatsEvent) o;
		if (grn == null) {
			return other.grn == null;
		} else if (!grn.equals(other.grn)) {
			return false;
		}
		if (service == null) {
			return other.service == null;
		} else if (!service.equals(other.service)) {
			return false;
		}
		if (method == null) {
			return other.method == null;
		} else if (!method.equals(other.method)) {
			return false;
		}
		if (metric == null) {
			return other.metric == null;
		} else if (!metric.equals(other.metric)) {
			return false;
		}
		if (cumulative == null) {
			return other.cumulative == null;
		} else if (!cumulative.equals(other.cumulative)) {
			return false;
		}
		if (value == null) {
			return other.value == null;
		} else if (!value.equals(other.value)) {
			return false;
		}
		if (tags == null) {
			return other.tags == null;
		} else if (!tags.equals(other.tags)) {
			return false;
		}
		if (callAt == null) {
			return other.callAt == null;
		} else if (!callAt.equals(other.callAt)) {
			return false;
		}
		return true;
	}
}