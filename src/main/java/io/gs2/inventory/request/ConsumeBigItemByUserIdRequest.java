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
public class ConsumeBigItemByUserIdRequest extends Gs2BasicRequest<ConsumeBigItemByUserIdRequest> {
    private String namespaceName;
    private String inventoryName;
    private String userId;
    private String itemName;
    private String consumeCount;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public ConsumeBigItemByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getInventoryName() {
		return inventoryName;
	}
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	public ConsumeBigItemByUserIdRequest withInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ConsumeBigItemByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public ConsumeBigItemByUserIdRequest withItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}
	public String getConsumeCount() {
		return consumeCount;
	}
	public void setConsumeCount(String consumeCount) {
		this.consumeCount = consumeCount;
	}
	public ConsumeBigItemByUserIdRequest withConsumeCount(String consumeCount) {
		this.consumeCount = consumeCount;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public ConsumeBigItemByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public ConsumeBigItemByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static ConsumeBigItemByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ConsumeBigItemByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withInventoryName(data.get("inventoryName") == null || data.get("inventoryName").isNull() ? null : data.get("inventoryName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withItemName(data.get("itemName") == null || data.get("itemName").isNull() ? null : data.get("itemName").asText())
            .withConsumeCount(data.get("consumeCount") == null || data.get("consumeCount").isNull() ? null : data.get("consumeCount").asText())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("inventoryName", getInventoryName());
                put("userId", getUserId());
                put("itemName", getItemName());
                put("consumeCount", getConsumeCount());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}