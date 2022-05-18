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
public class ConsumeStaminaByUserIdRequest extends Gs2BasicRequest<ConsumeStaminaByUserIdRequest> {
    private String namespaceName;
    private String staminaName;
    private String userId;
    private Integer consumeValue;
    private String duplicationAvoider;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public ConsumeStaminaByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getStaminaName() {
		return staminaName;
	}

	public void setStaminaName(String staminaName) {
		this.staminaName = staminaName;
	}

	public ConsumeStaminaByUserIdRequest withStaminaName(String staminaName) {
		this.staminaName = staminaName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ConsumeStaminaByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Integer getConsumeValue() {
		return consumeValue;
	}

	public void setConsumeValue(Integer consumeValue) {
		this.consumeValue = consumeValue;
	}

	public ConsumeStaminaByUserIdRequest withConsumeValue(Integer consumeValue) {
		this.consumeValue = consumeValue;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public ConsumeStaminaByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static ConsumeStaminaByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ConsumeStaminaByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withStaminaName(data.get("staminaName") == null || data.get("staminaName").isNull() ? null : data.get("staminaName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withConsumeValue(data.get("consumeValue") == null || data.get("consumeValue").isNull() ? null : data.get("consumeValue").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("staminaName", getStaminaName());
                put("userId", getUserId());
                put("consumeValue", getConsumeValue());
            }}
        );
    }
}