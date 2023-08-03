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
import io.gs2.enchant.model.BalanceParameterValue;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SetBalanceParameterStatusByUserIdRequest extends Gs2BasicRequest<SetBalanceParameterStatusByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String parameterName;
    private String propertyId;
    private List<BalanceParameterValue> parameterValues;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public SetBalanceParameterStatusByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SetBalanceParameterStatusByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public SetBalanceParameterStatusByUserIdRequest withParameterName(String parameterName) {
		this.parameterName = parameterName;
		return this;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public SetBalanceParameterStatusByUserIdRequest withPropertyId(String propertyId) {
		this.propertyId = propertyId;
		return this;
	}
	public List<BalanceParameterValue> getParameterValues() {
		return parameterValues;
	}
	public void setParameterValues(List<BalanceParameterValue> parameterValues) {
		this.parameterValues = parameterValues;
	}
	public SetBalanceParameterStatusByUserIdRequest withParameterValues(List<BalanceParameterValue> parameterValues) {
		this.parameterValues = parameterValues;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public SetBalanceParameterStatusByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static SetBalanceParameterStatusByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetBalanceParameterStatusByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withParameterName(data.get("parameterName") == null || data.get("parameterName").isNull() ? null : data.get("parameterName").asText())
            .withPropertyId(data.get("propertyId") == null || data.get("propertyId").isNull() ? null : data.get("propertyId").asText())
            .withParameterValues(data.get("parameterValues") == null || data.get("parameterValues").isNull() ? new ArrayList<BalanceParameterValue>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("parameterValues").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return BalanceParameterValue.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("parameterName", getParameterName());
                put("propertyId", getPropertyId());
                put("parameterValues", getParameterValues() == null ? new ArrayList<BalanceParameterValue>() :
                    getParameterValues().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}