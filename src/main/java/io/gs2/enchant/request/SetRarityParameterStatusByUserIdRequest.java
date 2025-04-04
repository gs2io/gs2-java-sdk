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
import io.gs2.enchant.model.RarityParameterValue;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SetRarityParameterStatusByUserIdRequest extends Gs2BasicRequest<SetRarityParameterStatusByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String parameterName;
    private String propertyId;
    private List<RarityParameterValue> parameterValues;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public SetRarityParameterStatusByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SetRarityParameterStatusByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public SetRarityParameterStatusByUserIdRequest withParameterName(String parameterName) {
		this.parameterName = parameterName;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public SetRarityParameterStatusByUserIdRequest withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public List<RarityParameterValue> getParameterValues() {
		return parameterValues;
	}
	public void setParameterValues(List<RarityParameterValue> parameterValues) {
		this.parameterValues = parameterValues;
	}
	public SetRarityParameterStatusByUserIdRequest withParameterValues(List<RarityParameterValue> parameterValues) {
		this.parameterValues = parameterValues;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public SetRarityParameterStatusByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public SetRarityParameterStatusByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static SetRarityParameterStatusByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetRarityParameterStatusByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withParameterName(data.get("parameterName") == null || data.get("parameterName").isNull() ? null : data.get("parameterName").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withParameterValues(data.get("parameterValues") == null || data.get("parameterValues").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("parameterValues").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RarityParameterValue.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("parameterName", getParameterName());
                put("propertyId", getPropertyId());
                put("parameterValues", getParameterValues() == null ? null :
                    getParameterValues().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}