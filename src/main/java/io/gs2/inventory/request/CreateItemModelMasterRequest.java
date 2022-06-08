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

package io.gs2.inventory.request;

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
public class CreateItemModelMasterRequest extends Gs2BasicRequest<CreateItemModelMasterRequest> {
    private String namespaceName;
    private String inventoryName;
    private String name;
    private String description;
    private String metadata;
    private Long stackingLimit;
    private Boolean allowMultipleStacks;
    private Integer sortValue;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateItemModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getInventoryName() {
		return inventoryName;
	}
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	public CreateItemModelMasterRequest withInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateItemModelMasterRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateItemModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CreateItemModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getStackingLimit() {
		return stackingLimit;
	}
	public void setStackingLimit(Long stackingLimit) {
		this.stackingLimit = stackingLimit;
	}
	public CreateItemModelMasterRequest withStackingLimit(Long stackingLimit) {
		this.stackingLimit = stackingLimit;
		return this;
	}
	public Boolean getAllowMultipleStacks() {
		return allowMultipleStacks;
	}
	public void setAllowMultipleStacks(Boolean allowMultipleStacks) {
		this.allowMultipleStacks = allowMultipleStacks;
	}
	public CreateItemModelMasterRequest withAllowMultipleStacks(Boolean allowMultipleStacks) {
		this.allowMultipleStacks = allowMultipleStacks;
		return this;
	}
	public Integer getSortValue() {
		return sortValue;
	}
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}
	public CreateItemModelMasterRequest withSortValue(Integer sortValue) {
		this.sortValue = sortValue;
		return this;
	}

    public static CreateItemModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateItemModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withInventoryName(data.get("inventoryName") == null || data.get("inventoryName").isNull() ? null : data.get("inventoryName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withStackingLimit(data.get("stackingLimit") == null || data.get("stackingLimit").isNull() ? null : data.get("stackingLimit").longValue())
            .withAllowMultipleStacks(data.get("allowMultipleStacks") == null || data.get("allowMultipleStacks").isNull() ? null : data.get("allowMultipleStacks").booleanValue())
            .withSortValue(data.get("sortValue") == null || data.get("sortValue").isNull() ? null : data.get("sortValue").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("inventoryName", getInventoryName());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("stackingLimit", getStackingLimit());
                put("allowMultipleStacks", getAllowMultipleStacks());
                put("sortValue", getSortValue());
            }}
        );
    }
}