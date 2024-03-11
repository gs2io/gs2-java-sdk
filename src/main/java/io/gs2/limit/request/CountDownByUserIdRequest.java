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
public class CountDownByUserIdRequest extends Gs2BasicRequest<CountDownByUserIdRequest> {
    private String namespaceName;
    private String limitName;
    private String counterName;
    private String userId;
    private Integer countDownValue;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CountDownByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getLimitName() {
		return limitName;
	}
	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}
	public CountDownByUserIdRequest withLimitName(String limitName) {
		this.limitName = limitName;
		return this;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public CountDownByUserIdRequest withCounterName(String counterName) {
		this.counterName = counterName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public CountDownByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getCountDownValue() {
		return countDownValue;
	}
	public void setCountDownValue(Integer countDownValue) {
		this.countDownValue = countDownValue;
	}
	public CountDownByUserIdRequest withCountDownValue(Integer countDownValue) {
		this.countDownValue = countDownValue;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public CountDownByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public CountDownByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static CountDownByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CountDownByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withLimitName(data.get("limitName") == null || data.get("limitName").isNull() ? null : data.get("limitName").asText())
            .withCounterName(data.get("counterName") == null || data.get("counterName").isNull() ? null : data.get("counterName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withCountDownValue(data.get("countDownValue") == null || data.get("countDownValue").isNull() ? null : data.get("countDownValue").intValue())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("limitName", getLimitName());
                put("counterName", getCounterName());
                put("userId", getUserId());
                put("countDownValue", getCountDownValue());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}