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

package io.gs2.idle.request;

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
public class SetMaximumIdleMinutesByUserIdRequest extends Gs2BasicRequest<SetMaximumIdleMinutesByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String categoryName;
    private Integer maximumIdleMinutes;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public SetMaximumIdleMinutesByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SetMaximumIdleMinutesByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public SetMaximumIdleMinutesByUserIdRequest withCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}
	public Integer getMaximumIdleMinutes() {
		return maximumIdleMinutes;
	}
	public void setMaximumIdleMinutes(Integer maximumIdleMinutes) {
		this.maximumIdleMinutes = maximumIdleMinutes;
	}
	public SetMaximumIdleMinutesByUserIdRequest withMaximumIdleMinutes(Integer maximumIdleMinutes) {
		this.maximumIdleMinutes = maximumIdleMinutes;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public SetMaximumIdleMinutesByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static SetMaximumIdleMinutesByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetMaximumIdleMinutesByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withCategoryName(data.get("categoryName") == null || data.get("categoryName").isNull() ? null : data.get("categoryName").asText())
            .withMaximumIdleMinutes(data.get("maximumIdleMinutes") == null || data.get("maximumIdleMinutes").isNull() ? null : data.get("maximumIdleMinutes").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("categoryName", getCategoryName());
                put("maximumIdleMinutes", getMaximumIdleMinutes());
            }}
        );
    }
}