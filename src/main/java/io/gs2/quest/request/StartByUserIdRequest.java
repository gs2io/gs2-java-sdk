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

package io.gs2.quest.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.quest.model.Config;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class StartByUserIdRequest extends Gs2BasicRequest<StartByUserIdRequest> {
    private String namespaceName;
    private String questGroupName;
    private String questName;
    private String userId;
    private Boolean force;
    private List<Config> config;
    private String duplicationAvoider;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public StartByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getQuestGroupName() {
		return questGroupName;
	}

	public void setQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
	}

	public StartByUserIdRequest withQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
		return this;
	}

	public String getQuestName() {
		return questName;
	}

	public void setQuestName(String questName) {
		this.questName = questName;
	}

	public StartByUserIdRequest withQuestName(String questName) {
		this.questName = questName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public StartByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Boolean getForce() {
		return force;
	}

	public void setForce(Boolean force) {
		this.force = force;
	}

	public StartByUserIdRequest withForce(Boolean force) {
		this.force = force;
		return this;
	}

	public List<Config> getConfig() {
		return config;
	}

	public void setConfig(List<Config> config) {
		this.config = config;
	}

	public StartByUserIdRequest withConfig(List<Config> config) {
		this.config = config;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public StartByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static StartByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new StartByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withQuestGroupName(data.get("questGroupName") == null || data.get("questGroupName").isNull() ? null : data.get("questGroupName").asText())
            .withQuestName(data.get("questName") == null || data.get("questName").isNull() ? null : data.get("questName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withForce(data.get("force") == null || data.get("force").isNull() ? null : data.get("force").booleanValue())
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
                put("questGroupName", getQuestGroupName());
                put("questName", getQuestName());
                put("userId", getUserId());
                put("force", getForce());
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