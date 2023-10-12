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
public class VerifyInventoryCurrentMaxCapacityRequest extends Gs2BasicRequest<VerifyInventoryCurrentMaxCapacityRequest> {
    private String namespaceName;
    private String accessToken;
    private String inventoryName;
    private String verifyType;
    private Integer currentInventoryMaxCapacity;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public VerifyInventoryCurrentMaxCapacityRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public VerifyInventoryCurrentMaxCapacityRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getInventoryName() {
		return inventoryName;
	}
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	public VerifyInventoryCurrentMaxCapacityRequest withInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
		return this;
	}
	public String getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	public VerifyInventoryCurrentMaxCapacityRequest withVerifyType(String verifyType) {
		this.verifyType = verifyType;
		return this;
	}
	public Integer getCurrentInventoryMaxCapacity() {
		return currentInventoryMaxCapacity;
	}
	public void setCurrentInventoryMaxCapacity(Integer currentInventoryMaxCapacity) {
		this.currentInventoryMaxCapacity = currentInventoryMaxCapacity;
	}
	public VerifyInventoryCurrentMaxCapacityRequest withCurrentInventoryMaxCapacity(Integer currentInventoryMaxCapacity) {
		this.currentInventoryMaxCapacity = currentInventoryMaxCapacity;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public VerifyInventoryCurrentMaxCapacityRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static VerifyInventoryCurrentMaxCapacityRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VerifyInventoryCurrentMaxCapacityRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withInventoryName(data.get("inventoryName") == null || data.get("inventoryName").isNull() ? null : data.get("inventoryName").asText())
            .withVerifyType(data.get("verifyType") == null || data.get("verifyType").isNull() ? null : data.get("verifyType").asText())
            .withCurrentInventoryMaxCapacity(data.get("currentInventoryMaxCapacity") == null || data.get("currentInventoryMaxCapacity").isNull() ? null : data.get("currentInventoryMaxCapacity").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("inventoryName", getInventoryName());
                put("verifyType", getVerifyType());
                put("currentInventoryMaxCapacity", getCurrentInventoryMaxCapacity());
            }}
        );
    }
}