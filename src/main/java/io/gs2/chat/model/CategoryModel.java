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

package io.gs2.chat.model;

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
public class CategoryModel implements IModel, Serializable, Comparable<CategoryModel> {
	private String categoryModelId;
	private Integer category;
	private String rejectAccessTokenPost;
	public String getCategoryModelId() {
		return categoryModelId;
	}
	public void setCategoryModelId(String categoryModelId) {
		this.categoryModelId = categoryModelId;
	}
	public CategoryModel withCategoryModelId(String categoryModelId) {
		this.categoryModelId = categoryModelId;
		return this;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public CategoryModel withCategory(Integer category) {
		this.category = category;
		return this;
	}
	public String getRejectAccessTokenPost() {
		return rejectAccessTokenPost;
	}
	public void setRejectAccessTokenPost(String rejectAccessTokenPost) {
		this.rejectAccessTokenPost = rejectAccessTokenPost;
	}
	public CategoryModel withRejectAccessTokenPost(String rejectAccessTokenPost) {
		this.rejectAccessTokenPost = rejectAccessTokenPost;
		return this;
	}

    public static CategoryModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CategoryModel()
            .withCategoryModelId(data.get("categoryModelId") == null || data.get("categoryModelId").isNull() ? null : data.get("categoryModelId").asText())
            .withCategory(data.get("category") == null || data.get("category").isNull() ? null : data.get("category").intValue())
            .withRejectAccessTokenPost(data.get("rejectAccessTokenPost") == null || data.get("rejectAccessTokenPost").isNull() ? null : data.get("rejectAccessTokenPost").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("categoryModelId", getCategoryModelId());
                put("category", getCategory());
                put("rejectAccessTokenPost", getRejectAccessTokenPost());
            }}
        );
    }

	@Override
	public int compareTo(CategoryModel o) {
		return categoryModelId.compareTo(o.categoryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.categoryModelId == null) ? 0 : this.categoryModelId.hashCode());
        result = prime * result + ((this.category == null) ? 0 : this.category.hashCode());
        result = prime * result + ((this.rejectAccessTokenPost == null) ? 0 : this.rejectAccessTokenPost.hashCode());
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
		CategoryModel other = (CategoryModel) o;
		if (categoryModelId == null) {
			return other.categoryModelId == null;
		} else if (!categoryModelId.equals(other.categoryModelId)) {
			return false;
		}
		if (category == null) {
			return other.category == null;
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (rejectAccessTokenPost == null) {
			return other.rejectAccessTokenPost == null;
		} else if (!rejectAccessTokenPost.equals(other.rejectAccessTokenPost)) {
			return false;
		}
		return true;
	}
}