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

package io.gs2.ranking.model;

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
public class CalculatedAt implements IModel, Serializable {
	private String categoryName;
	private Long calculatedAt;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public CalculatedAt withCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}

	public Long getCalculatedAt() {
		return calculatedAt;
	}

	public void setCalculatedAt(Long calculatedAt) {
		this.calculatedAt = calculatedAt;
	}

	public CalculatedAt withCalculatedAt(Long calculatedAt) {
		this.calculatedAt = calculatedAt;
		return this;
	}

    public static CalculatedAt fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CalculatedAt()
            .withCategoryName(data.get("categoryName") == null || data.get("categoryName").isNull() ? null : data.get("categoryName").asText())
            .withCalculatedAt(data.get("calculatedAt") == null || data.get("calculatedAt").isNull() ? null : data.get("calculatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("categoryName", getCategoryName());
                put("calculatedAt", getCalculatedAt());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.categoryName == null) ? 0 : this.categoryName.hashCode());
        result = prime * result + ((this.calculatedAt == null) ? 0 : this.calculatedAt.hashCode());
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
		CalculatedAt other = (CalculatedAt) o;
		if (categoryName == null) {
			return other.categoryName == null;
		} else if (!categoryName.equals(other.categoryName)) {
			return false;
		}
		if (calculatedAt == null) {
			return other.calculatedAt == null;
		} else if (!calculatedAt.equals(other.calculatedAt)) {
			return false;
		}
		return true;
	}
}