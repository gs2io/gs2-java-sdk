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
public class ConsumeItemSetRequest extends Gs2BasicRequest<ConsumeItemSetRequest> {
    private String namespaceName;
    private String inventoryName;
    private String accessToken;
    private String itemName;
    private Long consumeCount;
    private String itemSetName;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public ConsumeItemSetRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getInventoryName() {
		return inventoryName;
	}
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	public ConsumeItemSetRequest withInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public ConsumeItemSetRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public ConsumeItemSetRequest withItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}
	public Long getConsumeCount() {
		return consumeCount;
	}
	public void setConsumeCount(Long consumeCount) {
		this.consumeCount = consumeCount;
	}
	public ConsumeItemSetRequest withConsumeCount(Long consumeCount) {
		this.consumeCount = consumeCount;
		return this;
	}
	public String getItemSetName() {
		return itemSetName;
	}
	public void setItemSetName(String itemSetName) {
		this.itemSetName = itemSetName;
	}
	public ConsumeItemSetRequest withItemSetName(String itemSetName) {
		this.itemSetName = itemSetName;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public ConsumeItemSetRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static ConsumeItemSetRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ConsumeItemSetRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withInventoryName(data.get("inventoryName") == null || data.get("inventoryName").isNull() ? null : data.get("inventoryName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withItemName(data.get("itemName") == null || data.get("itemName").isNull() ? null : data.get("itemName").asText())
            .withConsumeCount(data.get("consumeCount") == null || data.get("consumeCount").isNull() ? null : data.get("consumeCount").longValue())
            .withItemSetName(data.get("itemSetName") == null || data.get("itemSetName").isNull() ? null : data.get("itemSetName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("inventoryName", getInventoryName());
                put("accessToken", getAccessToken());
                put("itemName", getItemName());
                put("consumeCount", getConsumeCount());
                put("itemSetName", getItemSetName());
            }}
        );
    }
}