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
import io.gs2.showcase.model.Config;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BuyByUserIdRequest extends Gs2BasicRequest<BuyByUserIdRequest> {
    private String namespaceName;
    private String showcaseName;
    private String displayItemId;
    private String userId;
    private List<Config> config;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public BuyByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getShowcaseName() {
		return showcaseName;
	}

	public void setShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
	}

	public BuyByUserIdRequest withShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
		return this;
	}

	public String getDisplayItemId() {
		return displayItemId;
	}

	public void setDisplayItemId(String displayItemId) {
		this.displayItemId = displayItemId;
	}

	public BuyByUserIdRequest withDisplayItemId(String displayItemId) {
		this.displayItemId = displayItemId;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BuyByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public List<Config> getConfig() {
		return config;
	}

	public void setConfig(List<Config> config) {
		this.config = config;
	}

	public BuyByUserIdRequest withConfig(List<Config> config) {
		this.config = config;
		return this;
	}

    public static BuyByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BuyByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withShowcaseName(data.get("showcaseName") == null || data.get("showcaseName").isNull() ? null : data.get("showcaseName").asText())
            .withDisplayItemId(data.get("displayItemId") == null || data.get("displayItemId").isNull() ? null : data.get("displayItemId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withConfig(data.get("config") == null || data.get("config").isNull() ? new ArrayList<Config>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("config").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Config.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("showcaseName", getShowcaseName());
                put("displayItemId", getDisplayItemId());
                put("userId", getUserId());
                put("config", getConfig() == null ? new ArrayList<Config>() :
                    getConfig().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}