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
public class RandomShowcaseBuyRequest extends Gs2BasicRequest<RandomShowcaseBuyRequest> {
    private String namespaceName;
    private String showcaseName;
    private String displayItemName;
    private String accessToken;
    private Integer quantity;
    private List<Config> config;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public RandomShowcaseBuyRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getShowcaseName() {
		return showcaseName;
	}
	public void setShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
	}
	public RandomShowcaseBuyRequest withShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
		return this;
	}
	public String getDisplayItemName() {
		return displayItemName;
	}
	public void setDisplayItemName(String displayItemName) {
		this.displayItemName = displayItemName;
	}
	public RandomShowcaseBuyRequest withDisplayItemName(String displayItemName) {
		this.displayItemName = displayItemName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public RandomShowcaseBuyRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public RandomShowcaseBuyRequest withQuantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}
	public List<Config> getConfig() {
		return config;
	}
	public void setConfig(List<Config> config) {
		this.config = config;
	}
	public RandomShowcaseBuyRequest withConfig(List<Config> config) {
		this.config = config;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public RandomShowcaseBuyRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static RandomShowcaseBuyRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RandomShowcaseBuyRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withShowcaseName(data.get("showcaseName") == null || data.get("showcaseName").isNull() ? null : data.get("showcaseName").asText())
            .withDisplayItemName(data.get("displayItemName") == null || data.get("displayItemName").isNull() ? null : data.get("displayItemName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withQuantity(data.get("quantity") == null || data.get("quantity").isNull() ? null : data.get("quantity").intValue())
            .withConfig(data.get("config") == null || data.get("config").isNull() ? null :
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
                put("displayItemName", getDisplayItemName());
                put("accessToken", getAccessToken());
                put("quantity", getQuantity());
                put("config", getConfig() == null ? null :
                    getConfig().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}