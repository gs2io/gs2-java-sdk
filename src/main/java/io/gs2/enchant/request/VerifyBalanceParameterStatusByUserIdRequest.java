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
public class VerifyBalanceParameterStatusByUserIdRequest extends Gs2BasicRequest<VerifyBalanceParameterStatusByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String parameterName;
    private String propertyId;
    private String parameterValueName;
    private String verifyType;
    private Long parameterValue;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public VerifyBalanceParameterStatusByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public VerifyBalanceParameterStatusByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public VerifyBalanceParameterStatusByUserIdRequest withParameterName(String parameterName) {
		this.parameterName = parameterName;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public VerifyBalanceParameterStatusByUserIdRequest withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public String getParameterValueName() {
		return parameterValueName;
	}
	public void setParameterValueName(String parameterValueName) {
		this.parameterValueName = parameterValueName;
	}
	public VerifyBalanceParameterStatusByUserIdRequest withParameterValueName(String parameterValueName) {
		this.parameterValueName = parameterValueName;
		return this;
	}
	public String getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	public VerifyBalanceParameterStatusByUserIdRequest withVerifyType(String verifyType) {
		this.verifyType = verifyType;
		return this;
	}
	public Long getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(Long parameterValue) {
		this.parameterValue = parameterValue;
	}
	public VerifyBalanceParameterStatusByUserIdRequest withParameterValue(Long parameterValue) {
		this.parameterValue = parameterValue;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public VerifyBalanceParameterStatusByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public VerifyBalanceParameterStatusByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static VerifyBalanceParameterStatusByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VerifyBalanceParameterStatusByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withParameterName(data.get("parameterName") == null || data.get("parameterName").isNull() ? null : data.get("parameterName").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withParameterValueName(data.get("parameterValueName") == null || data.get("parameterValueName").isNull() ? null : data.get("parameterValueName").asText())
            .withVerifyType(data.get("verifyType") == null || data.get("verifyType").isNull() ? null : data.get("verifyType").asText())
            .withParameterValue(data.get("parameterValue") == null || data.get("parameterValue").isNull() ? null : data.get("parameterValue").longValue())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("parameterName", getParameterName());
                put("propertyId", getPropertyId());
                put("parameterValueName", getParameterValueName());
                put("verifyType", getVerifyType());
                put("parameterValue", getParameterValue());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}