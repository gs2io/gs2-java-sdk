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
public class VerifyStaminaMaxValueByUserIdRequest extends Gs2BasicRequest<VerifyStaminaMaxValueByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String staminaName;
    private String verifyType;
    private Integer value;
    private Boolean multiplyValueSpecifyingQuantity;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public VerifyStaminaMaxValueByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public VerifyStaminaMaxValueByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getStaminaName() {
		return staminaName;
	}
	public void setStaminaName(String staminaName) {
		this.staminaName = staminaName;
	}
	public VerifyStaminaMaxValueByUserIdRequest withStaminaName(String staminaName) {
		this.staminaName = staminaName;
		return this;
	}
	public String getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	public VerifyStaminaMaxValueByUserIdRequest withVerifyType(String verifyType) {
		this.verifyType = verifyType;
		return this;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public VerifyStaminaMaxValueByUserIdRequest withValue(Integer value) {
		this.value = value;
		return this;
	}
	public Boolean getMultiplyValueSpecifyingQuantity() {
		return multiplyValueSpecifyingQuantity;
	}
	public void setMultiplyValueSpecifyingQuantity(Boolean multiplyValueSpecifyingQuantity) {
		this.multiplyValueSpecifyingQuantity = multiplyValueSpecifyingQuantity;
	}
	public VerifyStaminaMaxValueByUserIdRequest withMultiplyValueSpecifyingQuantity(Boolean multiplyValueSpecifyingQuantity) {
		this.multiplyValueSpecifyingQuantity = multiplyValueSpecifyingQuantity;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public VerifyStaminaMaxValueByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public VerifyStaminaMaxValueByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static VerifyStaminaMaxValueByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VerifyStaminaMaxValueByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withStaminaName(data.get("staminaName") == null || data.get("staminaName").isNull() ? null : data.get("staminaName").asText())
            .withVerifyType(data.get("verifyType") == null || data.get("verifyType").isNull() ? null : data.get("verifyType").asText())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").intValue())
            .withMultiplyValueSpecifyingQuantity(data.get("multiplyValueSpecifyingQuantity") == null || data.get("multiplyValueSpecifyingQuantity").isNull() ? null : data.get("multiplyValueSpecifyingQuantity").booleanValue())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("staminaName", getStaminaName());
                put("verifyType", getVerifyType());
                put("value", getValue());
                put("multiplyValueSpecifyingQuantity", getMultiplyValueSpecifyingQuantity());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}