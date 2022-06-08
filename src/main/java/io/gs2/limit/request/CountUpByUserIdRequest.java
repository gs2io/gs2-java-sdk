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

package io.gs2.limit.request;

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
public class CountUpByUserIdRequest extends Gs2BasicRequest<CountUpByUserIdRequest> {
    private String namespaceName;
    private String limitName;
    private String counterName;
    private String userId;
    private Integer countUpValue;
    private Integer maxValue;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CountUpByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getLimitName() {
		return limitName;
	}
	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}
	public CountUpByUserIdRequest withLimitName(String limitName) {
		this.limitName = limitName;
		return this;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public CountUpByUserIdRequest withCounterName(String counterName) {
		this.counterName = counterName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public CountUpByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getCountUpValue() {
		return countUpValue;
	}
	public void setCountUpValue(Integer countUpValue) {
		this.countUpValue = countUpValue;
	}
	public CountUpByUserIdRequest withCountUpValue(Integer countUpValue) {
		this.countUpValue = countUpValue;
		return this;
	}
	public Integer getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}
	public CountUpByUserIdRequest withMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public CountUpByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static CountUpByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CountUpByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withLimitName(data.get("limitName") == null || data.get("limitName").isNull() ? null : data.get("limitName").asText())
            .withCounterName(data.get("counterName") == null || data.get("counterName").isNull() ? null : data.get("counterName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withCountUpValue(data.get("countUpValue") == null || data.get("countUpValue").isNull() ? null : data.get("countUpValue").intValue())
            .withMaxValue(data.get("maxValue") == null || data.get("maxValue").isNull() ? null : data.get("maxValue").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("limitName", getLimitName());
                put("counterName", getCounterName());
                put("userId", getUserId());
                put("countUpValue", getCountUpValue());
                put("maxValue", getMaxValue());
            }}
        );
    }
}