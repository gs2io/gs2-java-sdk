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
public class Cumulative implements IModel, Serializable, Comparable<Cumulative> {
	private String cumulativeId;
	private String resourceGrn;
	private String name;
	private Long value;
	private Long updatedAt;

	public String getCumulativeId() {
		return cumulativeId;
	}

	public void setCumulativeId(String cumulativeId) {
		this.cumulativeId = cumulativeId;
	}

	public Cumulative withCumulativeId(String cumulativeId) {
		this.cumulativeId = cumulativeId;
		return this;
	}

	public String getResourceGrn() {
		return resourceGrn;
	}

	public void setResourceGrn(String resourceGrn) {
		this.resourceGrn = resourceGrn;
	}

	public Cumulative withResourceGrn(String resourceGrn) {
		this.resourceGrn = resourceGrn;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cumulative withName(String name) {
		this.name = name;
		return this;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Cumulative withValue(Long value) {
		this.value = value;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Cumulative withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Cumulative fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Cumulative()
            .withCumulativeId(data.get("cumulativeId") == null || data.get("cumulativeId").isNull() ? null : data.get("cumulativeId").asText())
            .withResourceGrn(data.get("resourceGrn") == null || data.get("resourceGrn").isNull() ? null : data.get("resourceGrn").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("cumulativeId", getCumulativeId());
                put("resourceGrn", getResourceGrn());
                put("name", getName());
                put("value", getValue());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Cumulative o) {
		return cumulativeId.compareTo(o.cumulativeId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.cumulativeId == null) ? 0 : this.cumulativeId.hashCode());
        result = prime * result + ((this.resourceGrn == null) ? 0 : this.resourceGrn.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		Cumulative other = (Cumulative) o;
		if (cumulativeId == null) {
			return other.cumulativeId == null;
		} else if (!cumulativeId.equals(other.cumulativeId)) {
			return false;
		}
		if (resourceGrn == null) {
			return other.resourceGrn == null;
		} else if (!resourceGrn.equals(other.resourceGrn)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (value == null) {
			return other.value == null;
		} else if (!value.equals(other.value)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}