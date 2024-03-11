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

package io.gs2.enchant.request;

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
public class VerifyRarityParameterStatusByUserIdRequest extends Gs2BasicRequest<VerifyRarityParameterStatusByUserIdRequest> {
    private String namespaceName;
    private String parameterName;
    private String userId;
    private String propertyId;
    private String verifyType;
    private String parameterValueName;
    private Integer parameterCount;
    private Boolean multiplyValueSpecifyingQuantity;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public VerifyRarityParameterStatusByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public VerifyRarityParameterStatusByUserIdRequest withParameterName(String parameterName) {
		this.parameterName = parameterName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public VerifyRarityParameterStatusByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public VerifyRarityParameterStatusByUserIdRequest withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public String getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	public VerifyRarityParameterStatusByUserIdRequest withVerifyType(String verifyType) {
		this.verifyType = verifyType;
		return this;
	}
	public String getParameterValueName() {
		return parameterValueName;
	}
	public void setParameterValueName(String parameterValueName) {
		this.parameterValueName = parameterValueName;
	}
	public VerifyRarityParameterStatusByUserIdRequest withParameterValueName(String parameterValueName) {
		this.parameterValueName = parameterValueName;
		return this;
	}
	public Integer getParameterCount() {
		return parameterCount;
	}
	public void setParameterCount(Integer parameterCount) {
		this.parameterCount = parameterCount;
	}
	public VerifyRarityParameterStatusByUserIdRequest withParameterCount(Integer parameterCount) {
		this.parameterCount = parameterCount;
		return this;
	}
	public Boolean getMultiplyValueSpecifyingQuantity() {
		return multiplyValueSpecifyingQuantity;
	}
	public void setMultiplyValueSpecifyingQuantity(Boolean multiplyValueSpecifyingQuantity) {
		this.multiplyValueSpecifyingQuantity = multiplyValueSpecifyingQuantity;
	}
	public VerifyRarityParameterStatusByUserIdRequest withMultiplyValueSpecifyingQuantity(Boolean multiplyValueSpecifyingQuantity) {
		this.multiplyValueSpecifyingQuantity = multiplyValueSpecifyingQuantity;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public VerifyRarityParameterStatusByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public VerifyRarityParameterStatusByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static VerifyRarityParameterStatusByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VerifyRarityParameterStatusByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withParameterName(data.get("parameterName") == null || data.get("parameterName").isNull() ? null : data.get("parameterName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withVerifyType(data.get("verifyType") == null || data.get("verifyType").isNull() ? null : data.get("verifyType").asText())
            .withParameterValueName(data.get("parameterValueName") == null || data.get("parameterValueName").isNull() ? null : data.get("parameterValueName").asText())
            .withParameterCount(data.get("parameterCount") == null || data.get("parameterCount").isNull() ? null : data.get("parameterCount").intValue())
            .withMultiplyValueSpecifyingQuantity(data.get("multiplyValueSpecifyingQuantity") == null || data.get("multiplyValueSpecifyingQuantity").isNull() ? null : data.get("multiplyValueSpecifyingQuantity").booleanValue())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("parameterName", getParameterName());
                put("userId", getUserId());
                put("propertyId", getPropertyId());
                put("verifyType", getVerifyType());
                put("parameterValueName", getParameterValueName());
                put("parameterCount", getParameterCount());
                put("multiplyValueSpecifyingQuantity", getMultiplyValueSpecifyingQuantity());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}