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

package io.gs2.chat.request;

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
public class CreateCategoryModelMasterRequest extends Gs2BasicRequest<CreateCategoryModelMasterRequest> {
    private String namespaceName;
    private Integer category;
    private String description;
    private String rejectAccessTokenPost;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateCategoryModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public CreateCategoryModelMasterRequest withCategory(Integer category) {
		this.category = category;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateCategoryModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getRejectAccessTokenPost() {
		return rejectAccessTokenPost;
	}
	public void setRejectAccessTokenPost(String rejectAccessTokenPost) {
		this.rejectAccessTokenPost = rejectAccessTokenPost;
	}
	public CreateCategoryModelMasterRequest withRejectAccessTokenPost(String rejectAccessTokenPost) {
		this.rejectAccessTokenPost = rejectAccessTokenPost;
		return this;
	}

    public static CreateCategoryModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateCategoryModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withCategory(data.get("category") == null || data.get("category").isNull() ? null : data.get("category").intValue())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withRejectAccessTokenPost(data.get("rejectAccessTokenPost") == null || data.get("rejectAccessTokenPost").isNull() ? null : data.get("rejectAccessTokenPost").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("category", getCategory());
                put("description", getDescription());
                put("rejectAccessTokenPost", getRejectAccessTokenPost());
            }}
        );
    }
}