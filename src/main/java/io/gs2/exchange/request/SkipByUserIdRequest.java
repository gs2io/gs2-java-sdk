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

package io.gs2.exchange.request;

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
public class SkipByUserIdRequest extends Gs2BasicRequest<SkipByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String awaitName;
    private String skipType;
    private Integer minutes;
    private Float rate;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public SkipByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SkipByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getAwaitName() {
		return awaitName;
	}
	public void setAwaitName(String awaitName) {
		this.awaitName = awaitName;
	}
	public SkipByUserIdRequest withAwaitName(String awaitName) {
		this.awaitName = awaitName;
		return this;
	}
	public String getSkipType() {
		return skipType;
	}
	public void setSkipType(String skipType) {
		this.skipType = skipType;
	}
	public SkipByUserIdRequest withSkipType(String skipType) {
		this.skipType = skipType;
		return this;
	}
	public Integer getMinutes() {
		return minutes;
	}
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	public SkipByUserIdRequest withMinutes(Integer minutes) {
		this.minutes = minutes;
		return this;
	}
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
	public SkipByUserIdRequest withRate(Float rate) {
		this.rate = rate;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public SkipByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public SkipByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static SkipByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SkipByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withAwaitName(data.get("awaitName") == null || data.get("awaitName").isNull() ? null : data.get("awaitName").asText())
            .withSkipType(data.get("skipType") == null || data.get("skipType").isNull() ? null : data.get("skipType").asText())
            .withMinutes(data.get("minutes") == null || data.get("minutes").isNull() ? null : data.get("minutes").intValue())
            .withRate(data.get("rate") == null || data.get("rate").isNull() ? null : data.get("rate").floatValue())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("awaitName", getAwaitName());
                put("skipType", getSkipType());
                put("minutes", getMinutes());
                put("rate", getRate());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}