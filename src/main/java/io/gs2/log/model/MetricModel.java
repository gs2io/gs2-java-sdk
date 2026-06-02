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

package io.gs2.log.model;

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
public class MetricModel implements IModel, Serializable, Comparable<MetricModel> {
	private String name;
	private String type;
	private List<String> labels;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MetricModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public MetricModel withType(String type) {
		this.type = type;
		return this;
	}
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public MetricModel withLabels(List<String> labels) {
		this.labels = labels;
		return this;
	}

    public static MetricModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new MetricModel()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withLabels(data.get("labels") == null || data.get("labels").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("labels").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("type", getType());
                put("labels", getLabels() == null ? null :
                    getLabels().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(MetricModel o) {
		return name.compareTo(o.name);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.labels == null) ? 0 : this.labels.hashCode());
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
		MetricModel other = (MetricModel) o;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (labels == null) {
			return other.labels == null;
		} else if (!labels.equals(other.labels)) {
			return false;
		}
		return true;
	}
}