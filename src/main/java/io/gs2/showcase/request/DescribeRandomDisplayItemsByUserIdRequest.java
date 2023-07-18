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
public class DescribeRandomDisplayItemsByUserIdRequest extends Gs2BasicRequest<DescribeRandomDisplayItemsByUserIdRequest> {
    private String namespaceName;
    private String showcaseName;
    private String userId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public DescribeRandomDisplayItemsByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getShowcaseName() {
		return showcaseName;
	}
	public void setShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
	}
	public DescribeRandomDisplayItemsByUserIdRequest withShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public DescribeRandomDisplayItemsByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

    public static DescribeRandomDisplayItemsByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DescribeRandomDisplayItemsByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withShowcaseName(data.get("showcaseName") == null || data.get("showcaseName").isNull() ? null : data.get("showcaseName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("showcaseName", getShowcaseName());
                put("userId", getUserId());
            }}
        );
    }
}