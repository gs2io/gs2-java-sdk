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
import io.gs2.exchange.model.Config;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class IncrementalExchangeRequest extends Gs2BasicRequest<IncrementalExchangeRequest> {
    private String namespaceName;
    private String rateName;
    private String accessToken;
    private Integer count;
    private List<Config> config;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public IncrementalExchangeRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRateName() {
		return rateName;
	}
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	public IncrementalExchangeRequest withRateName(String rateName) {
		this.rateName = rateName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public IncrementalExchangeRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public IncrementalExchangeRequest withCount(Integer count) {
		this.count = count;
		return this;
	}
	public List<Config> getConfig() {
		return config;
	}
	public void setConfig(List<Config> config) {
		this.config = config;
	}
	public IncrementalExchangeRequest withConfig(List<Config> config) {
		this.config = config;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public IncrementalExchangeRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static IncrementalExchangeRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new IncrementalExchangeRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRateName(data.get("rateName") == null || data.get("rateName").isNull() ? null : data.get("rateName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withCount(data.get("count") == null || data.get("count").isNull() ? null : data.get("count").intValue())
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
                put("rateName", getRateName());
                put("accessToken", getAccessToken());
                put("count", getCount());
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