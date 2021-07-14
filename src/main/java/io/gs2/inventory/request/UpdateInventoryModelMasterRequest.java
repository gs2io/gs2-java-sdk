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
public class UpdateInventoryModelMasterRequest extends Gs2BasicRequest<UpdateInventoryModelMasterRequest> {
    private String namespaceName;
    private String inventoryName;
    private String description;
    private String metadata;
    private Integer initialCapacity;
    private Integer maxCapacity;
    private Boolean protectReferencedItem;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public UpdateInventoryModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public UpdateInventoryModelMasterRequest withInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UpdateInventoryModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public UpdateInventoryModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public Integer getInitialCapacity() {
		return initialCapacity;
	}

	public void setInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
	}

	public UpdateInventoryModelMasterRequest withInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
		return this;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public UpdateInventoryModelMasterRequest withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}

	public Boolean getProtectReferencedItem() {
		return protectReferencedItem;
	}

	public void setProtectReferencedItem(Boolean protectReferencedItem) {
		this.protectReferencedItem = protectReferencedItem;
	}

	public UpdateInventoryModelMasterRequest withProtectReferencedItem(Boolean protectReferencedItem) {
		this.protectReferencedItem = protectReferencedItem;
		return this;
	}

    public static UpdateInventoryModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateInventoryModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withInventoryName(data.get("inventoryName") == null || data.get("inventoryName").isNull() ? null : data.get("inventoryName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withInitialCapacity(data.get("initialCapacity") == null || data.get("initialCapacity").isNull() ? null : data.get("initialCapacity").intValue())
            .withMaxCapacity(data.get("maxCapacity") == null || data.get("maxCapacity").isNull() ? null : data.get("maxCapacity").intValue())
            .withProtectReferencedItem(data.get("protectReferencedItem") == null || data.get("protectReferencedItem").isNull() ? null : data.get("protectReferencedItem").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("inventoryName", getInventoryName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("initialCapacity", getInitialCapacity());
                put("maxCapacity", getMaxCapacity());
                put("protectReferencedItem", getProtectReferencedItem());
            }}
        );
    }
}