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
public class GetRandomShowcaseSalesItemRequest extends Gs2BasicRequest<GetRandomShowcaseSalesItemRequest> {
    private String namespaceName;
    private String showcaseName;
    private String displayItemName;
    private String accessToken;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetRandomShowcaseSalesItemRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getShowcaseName() {
		return showcaseName;
	}
	public void setShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
	}
	public GetRandomShowcaseSalesItemRequest withShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
		return this;
	}
	public String getDisplayItemName() {
		return displayItemName;
	}
	public void setDisplayItemName(String displayItemName) {
		this.displayItemName = displayItemName;
	}
	public GetRandomShowcaseSalesItemRequest withDisplayItemName(String displayItemName) {
		this.displayItemName = displayItemName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public GetRandomShowcaseSalesItemRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

    public static GetRandomShowcaseSalesItemRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetRandomShowcaseSalesItemRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withShowcaseName(data.get("showcaseName") == null || data.get("showcaseName").isNull() ? null : data.get("showcaseName").asText())
            .withDisplayItemName(data.get("displayItemName") == null || data.get("displayItemName").isNull() ? null : data.get("displayItemName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("showcaseName", getShowcaseName());
                put("displayItemName", getDisplayItemName());
                put("accessToken", getAccessToken());
            }}
        );
    }
}