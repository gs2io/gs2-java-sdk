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
public class DeleteReferenceOfRequest extends Gs2BasicRequest<DeleteReferenceOfRequest> {
    private String namespaceName;
    private String inventoryName;
    private String accessToken;
    private String itemName;
    private String itemSetName;
    private String referenceOf;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public DeleteReferenceOfRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getInventoryName() {
		return inventoryName;
	}
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	public DeleteReferenceOfRequest withInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public DeleteReferenceOfRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public DeleteReferenceOfRequest withItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}
	public String getItemSetName() {
		return itemSetName;
	}
	public void setItemSetName(String itemSetName) {
		this.itemSetName = itemSetName;
	}
	public DeleteReferenceOfRequest withItemSetName(String itemSetName) {
		this.itemSetName = itemSetName;
		return this;
	}
	public String getReferenceOf() {
		return referenceOf;
	}
	public void setReferenceOf(String referenceOf) {
		this.referenceOf = referenceOf;
	}
	public DeleteReferenceOfRequest withReferenceOf(String referenceOf) {
		this.referenceOf = referenceOf;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public DeleteReferenceOfRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static DeleteReferenceOfRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DeleteReferenceOfRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withInventoryName(data.get("inventoryName") == null || data.get("inventoryName").isNull() ? null : data.get("inventoryName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withItemName(data.get("itemName") == null || data.get("itemName").isNull() ? null : data.get("itemName").asText())
            .withItemSetName(data.get("itemSetName") == null || data.get("itemSetName").isNull() ? null : data.get("itemSetName").asText())
            .withReferenceOf(data.get("referenceOf") == null || data.get("referenceOf").isNull() ? null : data.get("referenceOf").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("inventoryName", getInventoryName());
                put("accessToken", getAccessToken());
                put("itemName", getItemName());
                put("itemSetName", getItemSetName());
                put("referenceOf", getReferenceOf());
            }}
        );
    }
}