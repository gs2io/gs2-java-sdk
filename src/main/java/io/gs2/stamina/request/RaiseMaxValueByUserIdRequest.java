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
public class RaiseMaxValueByUserIdRequest extends Gs2BasicRequest<RaiseMaxValueByUserIdRequest> {
    private String namespaceName;
    private String staminaName;
    private String userId;
    private Integer raiseValue;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public RaiseMaxValueByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getStaminaName() {
		return staminaName;
	}
	public void setStaminaName(String staminaName) {
		this.staminaName = staminaName;
	}
	public RaiseMaxValueByUserIdRequest withStaminaName(String staminaName) {
		this.staminaName = staminaName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public RaiseMaxValueByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getRaiseValue() {
		return raiseValue;
	}
	public void setRaiseValue(Integer raiseValue) {
		this.raiseValue = raiseValue;
	}
	public RaiseMaxValueByUserIdRequest withRaiseValue(Integer raiseValue) {
		this.raiseValue = raiseValue;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public RaiseMaxValueByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public RaiseMaxValueByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static RaiseMaxValueByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RaiseMaxValueByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withStaminaName(data.get("staminaName") == null || data.get("staminaName").isNull() ? null : data.get("staminaName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withRaiseValue(data.get("raiseValue") == null || data.get("raiseValue").isNull() ? null : data.get("raiseValue").intValue())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("staminaName", getStaminaName());
                put("userId", getUserId());
                put("raiseValue", getRaiseValue());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}