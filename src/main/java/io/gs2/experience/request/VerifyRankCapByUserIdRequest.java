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

package io.gs2.experience.request;

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
public class VerifyRankCapByUserIdRequest extends Gs2BasicRequest<VerifyRankCapByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String experienceName;
    private String verifyType;
    private String propertyId;
    private Long rankCapValue;
    private Boolean multiplyValueSpecifyingQuantity;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public VerifyRankCapByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public VerifyRankCapByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getExperienceName() {
		return experienceName;
	}
	public void setExperienceName(String experienceName) {
		this.experienceName = experienceName;
	}
	public VerifyRankCapByUserIdRequest withExperienceName(String experienceName) {
		this.experienceName = experienceName;
		return this;
	}
	public String getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	public VerifyRankCapByUserIdRequest withVerifyType(String verifyType) {
		this.verifyType = verifyType;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public VerifyRankCapByUserIdRequest withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public Long getRankCapValue() {
		return rankCapValue;
	}
	public void setRankCapValue(Long rankCapValue) {
		this.rankCapValue = rankCapValue;
	}
	public VerifyRankCapByUserIdRequest withRankCapValue(Long rankCapValue) {
		this.rankCapValue = rankCapValue;
		return this;
	}
	public Boolean getMultiplyValueSpecifyingQuantity() {
		return multiplyValueSpecifyingQuantity;
	}
	public void setMultiplyValueSpecifyingQuantity(Boolean multiplyValueSpecifyingQuantity) {
		this.multiplyValueSpecifyingQuantity = multiplyValueSpecifyingQuantity;
	}
	public VerifyRankCapByUserIdRequest withMultiplyValueSpecifyingQuantity(Boolean multiplyValueSpecifyingQuantity) {
		this.multiplyValueSpecifyingQuantity = multiplyValueSpecifyingQuantity;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public VerifyRankCapByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static VerifyRankCapByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VerifyRankCapByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withExperienceName(data.get("experienceName") == null || data.get("experienceName").isNull() ? null : data.get("experienceName").asText())
            .withVerifyType(data.get("verifyType") == null || data.get("verifyType").isNull() ? null : data.get("verifyType").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withRankCapValue(data.get("rankCapValue") == null || data.get("rankCapValue").isNull() ? null : data.get("rankCapValue").longValue())
            .withMultiplyValueSpecifyingQuantity(data.get("multiplyValueSpecifyingQuantity") == null || data.get("multiplyValueSpecifyingQuantity").isNull() ? null : data.get("multiplyValueSpecifyingQuantity").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("experienceName", getExperienceName());
                put("verifyType", getVerifyType());
                put("propertyId", getPropertyId());
                put("rankCapValue", getRankCapValue());
                put("multiplyValueSpecifyingQuantity", getMultiplyValueSpecifyingQuantity());
            }}
        );
    }
}