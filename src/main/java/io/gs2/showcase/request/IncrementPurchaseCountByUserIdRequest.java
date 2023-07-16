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

package io.gs2.showcase.request;

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
public class IncrementPurchaseCountByUserIdRequest extends Gs2BasicRequest<IncrementPurchaseCountByUserIdRequest> {
    private String namespaceName;
    private String showcaseName;
    private String displayItemName;
    private String userId;
    private Integer count;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public IncrementPurchaseCountByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getShowcaseName() {
		return showcaseName;
	}
	public void setShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
	}
	public IncrementPurchaseCountByUserIdRequest withShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
		return this;
	}
	public String getDisplayItemName() {
		return displayItemName;
	}
	public void setDisplayItemName(String displayItemName) {
		this.displayItemName = displayItemName;
	}
	public IncrementPurchaseCountByUserIdRequest withDisplayItemName(String displayItemName) {
		this.displayItemName = displayItemName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public IncrementPurchaseCountByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public IncrementPurchaseCountByUserIdRequest withCount(Integer count) {
		this.count = count;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public IncrementPurchaseCountByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static IncrementPurchaseCountByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new IncrementPurchaseCountByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withShowcaseName(data.get("showcaseName") == null || data.get("showcaseName").isNull() ? null : data.get("showcaseName").asText())
            .withDisplayItemName(data.get("displayItemName") == null || data.get("displayItemName").isNull() ? null : data.get("displayItemName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withCount(data.get("count") == null || data.get("count").isNull() ? null : data.get("count").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("showcaseName", getShowcaseName());
                put("displayItemName", getDisplayItemName());
                put("userId", getUserId());
                put("count", getCount());
            }}
        );
    }
}