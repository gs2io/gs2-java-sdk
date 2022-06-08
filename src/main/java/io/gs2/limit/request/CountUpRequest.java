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
public class CountUpRequest extends Gs2BasicRequest<CountUpRequest> {
    private String namespaceName;
    private String limitName;
    private String counterName;
    private String accessToken;
    private Integer countUpValue;
    private Integer maxValue;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CountUpRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getLimitName() {
		return limitName;
	}
	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}
	public CountUpRequest withLimitName(String limitName) {
		this.limitName = limitName;
		return this;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public CountUpRequest withCounterName(String counterName) {
		this.counterName = counterName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public CountUpRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public Integer getCountUpValue() {
		return countUpValue;
	}
	public void setCountUpValue(Integer countUpValue) {
		this.countUpValue = countUpValue;
	}
	public CountUpRequest withCountUpValue(Integer countUpValue) {
		this.countUpValue = countUpValue;
		return this;
	}
	public Integer getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}
	public CountUpRequest withMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
		return this;
	}

    public static CountUpRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CountUpRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withLimitName(data.get("limitName") == null || data.get("limitName").isNull() ? null : data.get("limitName").asText())
            .withCounterName(data.get("counterName") == null || data.get("counterName").isNull() ? null : data.get("counterName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withCountUpValue(data.get("countUpValue") == null || data.get("countUpValue").isNull() ? null : data.get("countUpValue").intValue())
            .withMaxValue(data.get("maxValue") == null || data.get("maxValue").isNull() ? null : data.get("maxValue").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("limitName", getLimitName());
                put("counterName", getCounterName());
                put("accessToken", getAccessToken());
                put("countUpValue", getCountUpValue());
                put("maxValue", getMaxValue());
            }}
        );
    }
}