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

package io.gs2.lottery.request;

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
public class PredictionRequest extends Gs2BasicRequest<PredictionRequest> {
    private String namespaceName;
    private String lotteryName;
    private String userId;
    private Long randomSeed;
    private Integer count;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public PredictionRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getLotteryName() {
		return lotteryName;
	}
	public void setLotteryName(String lotteryName) {
		this.lotteryName = lotteryName;
	}
	public PredictionRequest withLotteryName(String lotteryName) {
		this.lotteryName = lotteryName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public PredictionRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Long getRandomSeed() {
		return randomSeed;
	}
	public void setRandomSeed(Long randomSeed) {
		this.randomSeed = randomSeed;
	}
	public PredictionRequest withRandomSeed(Long randomSeed) {
		this.randomSeed = randomSeed;
		return this;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public PredictionRequest withCount(Integer count) {
		this.count = count;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public PredictionRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static PredictionRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PredictionRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withLotteryName(data.get("lotteryName") == null || data.get("lotteryName").isNull() ? null : data.get("lotteryName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withRandomSeed(data.get("randomSeed") == null || data.get("randomSeed").isNull() ? null : data.get("randomSeed").longValue())
            .withCount(data.get("count") == null || data.get("count").isNull() ? null : data.get("count").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("lotteryName", getLotteryName());
                put("userId", getUserId());
                put("randomSeed", getRandomSeed());
                put("count", getCount());
            }}
        );
    }
}