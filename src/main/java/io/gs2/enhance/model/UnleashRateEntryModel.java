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

package io.gs2.enhance.model;

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
public class UnleashRateEntryModel implements IModel, Serializable {
	private Long gradeValue;
	private String type;
	private Integer needCount;
	private List<UnleashRecipe> recipes;
	public Long getGradeValue() {
		return gradeValue;
	}
	public void setGradeValue(Long gradeValue) {
		this.gradeValue = gradeValue;
	}
	public UnleashRateEntryModel withGradeValue(Long gradeValue) {
		this.gradeValue = gradeValue;
		return this;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UnleashRateEntryModel withType(String type) {
		this.type = type;
		return this;
	}
	public Integer getNeedCount() {
		return needCount;
	}
	public void setNeedCount(Integer needCount) {
		this.needCount = needCount;
	}
	public UnleashRateEntryModel withNeedCount(Integer needCount) {
		this.needCount = needCount;
		return this;
	}
	public List<UnleashRecipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(List<UnleashRecipe> recipes) {
		this.recipes = recipes;
	}
	public UnleashRateEntryModel withRecipes(List<UnleashRecipe> recipes) {
		this.recipes = recipes;
		return this;
	}

    public static UnleashRateEntryModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UnleashRateEntryModel()
            .withGradeValue(data.get("gradeValue") == null || data.get("gradeValue").isNull() ? null : data.get("gradeValue").longValue())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withNeedCount(data.get("needCount") == null || data.get("needCount").isNull() ? null : data.get("needCount").intValue())
            .withRecipes(data.get("recipes") == null || data.get("recipes").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("recipes").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return UnleashRecipe.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("gradeValue", getGradeValue());
                put("type", getType());
                put("needCount", getNeedCount());
                put("recipes", getRecipes() == null ? null :
                    getRecipes().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.gradeValue == null) ? 0 : this.gradeValue.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.needCount == null) ? 0 : this.needCount.hashCode());
        result = prime * result + ((this.recipes == null) ? 0 : this.recipes.hashCode());
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
		UnleashRateEntryModel other = (UnleashRateEntryModel) o;
		if (gradeValue == null) {
			return other.gradeValue == null;
		} else if (!gradeValue.equals(other.gradeValue)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (needCount == null) {
			return other.needCount == null;
		} else if (!needCount.equals(other.needCount)) {
			return false;
		}
		if (recipes == null) {
			return other.recipes == null;
		} else if (!recipes.equals(other.recipes)) {
			return false;
		}
		return true;
	}
}