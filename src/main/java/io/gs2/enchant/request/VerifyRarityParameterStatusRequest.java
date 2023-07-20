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
public class VerifyRarityParameterStatusRequest extends Gs2BasicRequest<VerifyRarityParameterStatusRequest> {
    private String namespaceName;
    private String parameterName;
    private String accessToken;
    private String propertyId;
    private String verifyType;
    private String parameterValueName;
    private Integer parameterCount;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public VerifyRarityParameterStatusRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public VerifyRarityParameterStatusRequest withParameterName(String parameterName) {
		this.parameterName = parameterName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public VerifyRarityParameterStatusRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public VerifyRarityParameterStatusRequest withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public String getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	public VerifyRarityParameterStatusRequest withVerifyType(String verifyType) {
		this.verifyType = verifyType;
		return this;
	}
	public String getParameterValueName() {
		return parameterValueName;
	}
	public void setParameterValueName(String parameterValueName) {
		this.parameterValueName = parameterValueName;
	}
	public VerifyRarityParameterStatusRequest withParameterValueName(String parameterValueName) {
		this.parameterValueName = parameterValueName;
		return this;
	}
	public Integer getParameterCount() {
		return parameterCount;
	}
	public void setParameterCount(Integer parameterCount) {
		this.parameterCount = parameterCount;
	}
	public VerifyRarityParameterStatusRequest withParameterCount(Integer parameterCount) {
		this.parameterCount = parameterCount;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public VerifyRarityParameterStatusRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static VerifyRarityParameterStatusRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VerifyRarityParameterStatusRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withParameterName(data.get("parameterName") == null || data.get("parameterName").isNull() ? null : data.get("parameterName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withVerifyType(data.get("verifyType") == null || data.get("verifyType").isNull() ? null : data.get("verifyType").asText())
            .withParameterValueName(data.get("parameterValueName") == null || data.get("parameterValueName").isNull() ? null : data.get("parameterValueName").asText())
            .withParameterCount(data.get("parameterCount") == null || data.get("parameterCount").isNull() ? null : data.get("parameterCount").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("parameterName", getParameterName());
                put("accessToken", getAccessToken());
                put("propertyId", getPropertyId());
                put("verifyType", getVerifyType());
                put("parameterValueName", getParameterValueName());
                put("parameterCount", getParameterCount());
            }}
        );
    }
}