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

package io.gs2.mission.request;

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
public class VerifyCounterValueRequest extends Gs2BasicRequest<VerifyCounterValueRequest> {
    private String namespaceName;
    private String accessToken;
    private String counterName;
    private String verifyType;
    private String resetType;
    private Long value;
    private Boolean multiplyValueSpecifyingQuantity;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public VerifyCounterValueRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public VerifyCounterValueRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public VerifyCounterValueRequest withCounterName(String counterName) {
		this.counterName = counterName;
		return this;
	}
	public String getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	public VerifyCounterValueRequest withVerifyType(String verifyType) {
		this.verifyType = verifyType;
		return this;
	}
	public String getResetType() {
		return resetType;
	}
	public void setResetType(String resetType) {
		this.resetType = resetType;
	}
	public VerifyCounterValueRequest withResetType(String resetType) {
		this.resetType = resetType;
		return this;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public VerifyCounterValueRequest withValue(Long value) {
		this.value = value;
		return this;
	}
	public Boolean getMultiplyValueSpecifyingQuantity() {
		return multiplyValueSpecifyingQuantity;
	}
	public void setMultiplyValueSpecifyingQuantity(Boolean multiplyValueSpecifyingQuantity) {
		this.multiplyValueSpecifyingQuantity = multiplyValueSpecifyingQuantity;
	}
	public VerifyCounterValueRequest withMultiplyValueSpecifyingQuantity(Boolean multiplyValueSpecifyingQuantity) {
		this.multiplyValueSpecifyingQuantity = multiplyValueSpecifyingQuantity;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public VerifyCounterValueRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static VerifyCounterValueRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VerifyCounterValueRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withCounterName(data.get("counterName") == null || data.get("counterName").isNull() ? null : data.get("counterName").asText())
            .withVerifyType(data.get("verifyType") == null || data.get("verifyType").isNull() ? null : data.get("verifyType").asText())
            .withResetType(data.get("resetType") == null || data.get("resetType").isNull() ? null : data.get("resetType").asText())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").longValue())
            .withMultiplyValueSpecifyingQuantity(data.get("multiplyValueSpecifyingQuantity") == null || data.get("multiplyValueSpecifyingQuantity").isNull() ? null : data.get("multiplyValueSpecifyingQuantity").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("counterName", getCounterName());
                put("verifyType", getVerifyType());
                put("resetType", getResetType());
                put("value", getValue());
                put("multiplyValueSpecifyingQuantity", getMultiplyValueSpecifyingQuantity());
            }}
        );
    }
}