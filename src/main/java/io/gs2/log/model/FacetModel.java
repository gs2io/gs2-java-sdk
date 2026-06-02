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
public class FacetModel implements IModel, Serializable, Comparable<FacetModel> {
	private String facetModelId;
	private String field;
	private String type;
	private String displayName;
	private Integer order;
	public String getFacetModelId() {
		return facetModelId;
	}
	public void setFacetModelId(String facetModelId) {
		this.facetModelId = facetModelId;
	}
	public FacetModel withFacetModelId(String facetModelId) {
		this.facetModelId = facetModelId;
		return this;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public FacetModel withField(String field) {
		this.field = field;
		return this;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public FacetModel withType(String type) {
		this.type = type;
		return this;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public FacetModel withDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public FacetModel withOrder(Integer order) {
		this.order = order;
		return this;
	}

    public static FacetModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new FacetModel()
            .withFacetModelId(data.get("facetModelId") == null || data.get("facetModelId").isNull() ? null : data.get("facetModelId").asText())
            .withField(data.get("field") == null || data.get("field").isNull() ? null : data.get("field").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withDisplayName(data.get("displayName") == null || data.get("displayName").isNull() ? null : data.get("displayName").asText())
            .withOrder(data.get("order") == null || data.get("order").isNull() ? null : data.get("order").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("facetModelId", getFacetModelId());
                put("field", getField());
                put("type", getType());
                put("displayName", getDisplayName());
                put("order", getOrder());
            }}
        );
    }

	@Override
	public int compareTo(FacetModel o) {
		return facetModelId.compareTo(o.facetModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.facetModelId == null) ? 0 : this.facetModelId.hashCode());
        result = prime * result + ((this.field == null) ? 0 : this.field.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.displayName == null) ? 0 : this.displayName.hashCode());
        result = prime * result + ((this.order == null) ? 0 : this.order.hashCode());
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
		FacetModel other = (FacetModel) o;
		if (facetModelId == null) {
			return other.facetModelId == null;
		} else if (!facetModelId.equals(other.facetModelId)) {
			return false;
		}
		if (field == null) {
			return other.field == null;
		} else if (!field.equals(other.field)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (displayName == null) {
			return other.displayName == null;
		} else if (!displayName.equals(other.displayName)) {
			return false;
		}
		if (order == null) {
			return other.order == null;
		} else if (!order.equals(other.order)) {
			return false;
		}
		return true;
	}
}