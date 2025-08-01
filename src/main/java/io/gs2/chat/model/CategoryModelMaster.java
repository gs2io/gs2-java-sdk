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
public class CategoryModelMaster implements IModel, Serializable, Comparable<CategoryModelMaster> {
	private String categoryModelId;
	private Integer category;
	private String description;
	private String rejectAccessTokenPost;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getCategoryModelId() {
		return categoryModelId;
	}
	public void setCategoryModelId(String categoryModelId) {
		this.categoryModelId = categoryModelId;
	}
	public CategoryModelMaster withCategoryModelId(String categoryModelId) {
		this.categoryModelId = categoryModelId;
		return this;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public CategoryModelMaster withCategory(Integer category) {
		this.category = category;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CategoryModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getRejectAccessTokenPost() {
		return rejectAccessTokenPost;
	}
	public void setRejectAccessTokenPost(String rejectAccessTokenPost) {
		this.rejectAccessTokenPost = rejectAccessTokenPost;
	}
	public CategoryModelMaster withRejectAccessTokenPost(String rejectAccessTokenPost) {
		this.rejectAccessTokenPost = rejectAccessTokenPost;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public CategoryModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public CategoryModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public CategoryModelMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static CategoryModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CategoryModelMaster()
            .withCategoryModelId(data.get("categoryModelId") == null || data.get("categoryModelId").isNull() ? null : data.get("categoryModelId").asText())
            .withCategory(data.get("category") == null || data.get("category").isNull() ? null : data.get("category").intValue())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withRejectAccessTokenPost(data.get("rejectAccessTokenPost") == null || data.get("rejectAccessTokenPost").isNull() ? null : data.get("rejectAccessTokenPost").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("categoryModelId", getCategoryModelId());
                put("category", getCategory());
                put("description", getDescription());
                put("rejectAccessTokenPost", getRejectAccessTokenPost());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(CategoryModelMaster o) {
		return categoryModelId.compareTo(o.categoryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.categoryModelId == null) ? 0 : this.categoryModelId.hashCode());
        result = prime * result + ((this.category == null) ? 0 : this.category.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.rejectAccessTokenPost == null) ? 0 : this.rejectAccessTokenPost.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.revision == null) ? 0 : this.revision.hashCode());
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
		CategoryModelMaster other = (CategoryModelMaster) o;
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
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (rejectAccessTokenPost == null) {
			return other.rejectAccessTokenPost == null;
		} else if (!rejectAccessTokenPost.equals(other.rejectAccessTokenPost)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}