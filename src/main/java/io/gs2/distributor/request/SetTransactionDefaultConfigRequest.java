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

package io.gs2.distributor.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.distributor.model.Config;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SetTransactionDefaultConfigRequest extends Gs2BasicRequest<SetTransactionDefaultConfigRequest> {
    private String accessToken;
    private List<Config> config;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public SetTransactionDefaultConfigRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public List<Config> getConfig() {
		return config;
	}
	public void setConfig(List<Config> config) {
		this.config = config;
	}
	public SetTransactionDefaultConfigRequest withConfig(List<Config> config) {
		this.config = config;
		return this;
	}

    public static SetTransactionDefaultConfigRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetTransactionDefaultConfigRequest()
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
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
                put("accessToken", getAccessToken());
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