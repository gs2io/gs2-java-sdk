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
public class DecreaseMaxValueByUserIdRequest extends Gs2BasicRequest<DecreaseMaxValueByUserIdRequest> {
    private String namespaceName;
    private String staminaName;
    private String userId;
    private Integer decreaseValue;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public DecreaseMaxValueByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getStaminaName() {
		return staminaName;
	}
	public void setStaminaName(String staminaName) {
		this.staminaName = staminaName;
	}
	public DecreaseMaxValueByUserIdRequest withStaminaName(String staminaName) {
		this.staminaName = staminaName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public DecreaseMaxValueByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getDecreaseValue() {
		return decreaseValue;
	}
	public void setDecreaseValue(Integer decreaseValue) {
		this.decreaseValue = decreaseValue;
	}
	public DecreaseMaxValueByUserIdRequest withDecreaseValue(Integer decreaseValue) {
		this.decreaseValue = decreaseValue;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public DecreaseMaxValueByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public DecreaseMaxValueByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static DecreaseMaxValueByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DecreaseMaxValueByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withStaminaName(data.get("staminaName") == null || data.get("staminaName").isNull() ? null : data.get("staminaName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withDecreaseValue(data.get("decreaseValue") == null || data.get("decreaseValue").isNull() ? null : data.get("decreaseValue").intValue())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("staminaName", getStaminaName());
                put("userId", getUserId());
                put("decreaseValue", getDecreaseValue());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}