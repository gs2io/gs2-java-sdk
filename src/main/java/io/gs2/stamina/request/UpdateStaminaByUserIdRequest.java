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
public class UpdateStaminaByUserIdRequest extends Gs2BasicRequest<UpdateStaminaByUserIdRequest> {
    private String namespaceName;
    private String staminaName;
    private String userId;
    private Integer value;
    private Integer maxValue;
    private Integer recoverIntervalMinutes;
    private Integer recoverValue;
    private String duplicationAvoider;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public UpdateStaminaByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getStaminaName() {
		return staminaName;
	}

	public void setStaminaName(String staminaName) {
		this.staminaName = staminaName;
	}

	public UpdateStaminaByUserIdRequest withStaminaName(String staminaName) {
		this.staminaName = staminaName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UpdateStaminaByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public UpdateStaminaByUserIdRequest withValue(Integer value) {
		this.value = value;
		return this;
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	public UpdateStaminaByUserIdRequest withMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
		return this;
	}

	public Integer getRecoverIntervalMinutes() {
		return recoverIntervalMinutes;
	}

	public void setRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
	}

	public UpdateStaminaByUserIdRequest withRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
		return this;
	}

	public Integer getRecoverValue() {
		return recoverValue;
	}

	public void setRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
	}

	public UpdateStaminaByUserIdRequest withRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public UpdateStaminaByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static UpdateStaminaByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateStaminaByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withStaminaName(data.get("staminaName") == null || data.get("staminaName").isNull() ? null : data.get("staminaName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").intValue())
            .withMaxValue(data.get("maxValue") == null || data.get("maxValue").isNull() ? null : data.get("maxValue").intValue())
            .withRecoverIntervalMinutes(data.get("recoverIntervalMinutes") == null || data.get("recoverIntervalMinutes").isNull() ? null : data.get("recoverIntervalMinutes").intValue())
            .withRecoverValue(data.get("recoverValue") == null || data.get("recoverValue").isNull() ? null : data.get("recoverValue").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("staminaName", getStaminaName());
                put("userId", getUserId());
                put("value", getValue());
                put("maxValue", getMaxValue());
                put("recoverIntervalMinutes", getRecoverIntervalMinutes());
                put("recoverValue", getRecoverValue());
            }}
        );
    }
}