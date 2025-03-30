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

package io.gs2.stamina.request;

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
public class VerifyStaminaValueRequest extends Gs2BasicRequest<VerifyStaminaValueRequest> {
    private String namespaceName;
    private String accessToken;
    private String staminaName;
    private String verifyType;
    private Integer value;
    private Boolean multiplyValueSpecifyingQuantity;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public VerifyStaminaValueRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public VerifyStaminaValueRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getStaminaName() {
		return staminaName;
	}
	public void setStaminaName(String staminaName) {
		this.staminaName = staminaName;
	}
	public VerifyStaminaValueRequest withStaminaName(String staminaName) {
		this.staminaName = staminaName;
		return this;
	}
	public String getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	public VerifyStaminaValueRequest withVerifyType(String verifyType) {
		this.verifyType = verifyType;
		return this;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public VerifyStaminaValueRequest withValue(Integer value) {
		this.value = value;
		return this;
	}
	public Boolean getMultiplyValueSpecifyingQuantity() {
		return multiplyValueSpecifyingQuantity;
	}
	public void setMultiplyValueSpecifyingQuantity(Boolean multiplyValueSpecifyingQuantity) {
		this.multiplyValueSpecifyingQuantity = multiplyValueSpecifyingQuantity;
	}
	public VerifyStaminaValueRequest withMultiplyValueSpecifyingQuantity(Boolean multiplyValueSpecifyingQuantity) {
		this.multiplyValueSpecifyingQuantity = multiplyValueSpecifyingQuantity;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public VerifyStaminaValueRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static VerifyStaminaValueRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VerifyStaminaValueRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withStaminaName(data.get("staminaName") == null || data.get("staminaName").isNull() ? null : data.get("staminaName").asText())
            .withVerifyType(data.get("verifyType") == null || data.get("verifyType").isNull() ? null : data.get("verifyType").asText())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").intValue())
            .withMultiplyValueSpecifyingQuantity(data.get("multiplyValueSpecifyingQuantity") == null || data.get("multiplyValueSpecifyingQuantity").isNull() ? null : data.get("multiplyValueSpecifyingQuantity").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("staminaName", getStaminaName());
                put("verifyType", getVerifyType());
                put("value", getValue());
                put("multiplyValueSpecifyingQuantity", getMultiplyValueSpecifyingQuantity());
            }}
        );
    }
}