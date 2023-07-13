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

package io.gs2.loginReward.request;

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
public class MarkReceivedByUserIdRequest extends Gs2BasicRequest<MarkReceivedByUserIdRequest> {
    private String namespaceName;
    private String bonusModelName;
    private String userId;
    private Integer stepNumber;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public MarkReceivedByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getBonusModelName() {
		return bonusModelName;
	}
	public void setBonusModelName(String bonusModelName) {
		this.bonusModelName = bonusModelName;
	}
	public MarkReceivedByUserIdRequest withBonusModelName(String bonusModelName) {
		this.bonusModelName = bonusModelName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public MarkReceivedByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getStepNumber() {
		return stepNumber;
	}
	public void setStepNumber(Integer stepNumber) {
		this.stepNumber = stepNumber;
	}
	public MarkReceivedByUserIdRequest withStepNumber(Integer stepNumber) {
		this.stepNumber = stepNumber;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public MarkReceivedByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static MarkReceivedByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new MarkReceivedByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withBonusModelName(data.get("bonusModelName") == null || data.get("bonusModelName").isNull() ? null : data.get("bonusModelName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withStepNumber(data.get("stepNumber") == null || data.get("stepNumber").isNull() ? null : data.get("stepNumber").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("bonusModelName", getBonusModelName());
                put("userId", getUserId());
                put("stepNumber", getStepNumber());
            }}
        );
    }
}