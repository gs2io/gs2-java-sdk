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
public class AcquireItemSetByUserIdRequest extends Gs2BasicRequest<AcquireItemSetByUserIdRequest> {
    private String namespaceName;
    private String inventoryName;
    private String itemName;
    private String userId;
    private Long acquireCount;
    private Long expiresAt;
    private Boolean createNewItemSet;
    private String itemSetName;
    private String duplicationAvoider;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public AcquireItemSetByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public AcquireItemSetByUserIdRequest withInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
		return this;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public AcquireItemSetByUserIdRequest withItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public AcquireItemSetByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Long getAcquireCount() {
		return acquireCount;
	}

	public void setAcquireCount(Long acquireCount) {
		this.acquireCount = acquireCount;
	}

	public AcquireItemSetByUserIdRequest withAcquireCount(Long acquireCount) {
		this.acquireCount = acquireCount;
		return this;
	}

	public Long getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}

	public AcquireItemSetByUserIdRequest withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}

	public Boolean getCreateNewItemSet() {
		return createNewItemSet;
	}

	public void setCreateNewItemSet(Boolean createNewItemSet) {
		this.createNewItemSet = createNewItemSet;
	}

	public AcquireItemSetByUserIdRequest withCreateNewItemSet(Boolean createNewItemSet) {
		this.createNewItemSet = createNewItemSet;
		return this;
	}

	public String getItemSetName() {
		return itemSetName;
	}

	public void setItemSetName(String itemSetName) {
		this.itemSetName = itemSetName;
	}

	public AcquireItemSetByUserIdRequest withItemSetName(String itemSetName) {
		this.itemSetName = itemSetName;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public AcquireItemSetByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static AcquireItemSetByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AcquireItemSetByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withInventoryName(data.get("inventoryName") == null || data.get("inventoryName").isNull() ? null : data.get("inventoryName").asText())
            .withItemName(data.get("itemName") == null || data.get("itemName").isNull() ? null : data.get("itemName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withAcquireCount(data.get("acquireCount") == null || data.get("acquireCount").isNull() ? null : data.get("acquireCount").longValue())
            .withExpiresAt(data.get("expiresAt") == null || data.get("expiresAt").isNull() ? null : data.get("expiresAt").longValue())
            .withCreateNewItemSet(data.get("createNewItemSet") == null || data.get("createNewItemSet").isNull() ? null : data.get("createNewItemSet").booleanValue())
            .withItemSetName(data.get("itemSetName") == null || data.get("itemSetName").isNull() ? null : data.get("itemSetName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("inventoryName", getInventoryName());
                put("itemName", getItemName());
                put("userId", getUserId());
                put("acquireCount", getAcquireCount());
                put("expiresAt", getExpiresAt());
                put("createNewItemSet", getCreateNewItemSet());
                put("itemSetName", getItemSetName());
            }}
        );
    }
}