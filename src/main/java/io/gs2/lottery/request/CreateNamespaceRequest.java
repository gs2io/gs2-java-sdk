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
import io.gs2.lottery.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private String description;
    private String queueNamespaceId;
    private String keyId;
    private String lotteryTriggerScriptId;
    private String choicePrizeTableScriptId;
    private LogSetting logSetting;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CreateNamespaceRequest withName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CreateNamespaceRequest withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getQueueNamespaceId() {
		return queueNamespaceId;
	}

	public void setQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
	}

	public CreateNamespaceRequest withQueueNamespaceId(String queueNamespaceId) {
		this.queueNamespaceId = queueNamespaceId;
		return this;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public CreateNamespaceRequest withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}

	public String getLotteryTriggerScriptId() {
		return lotteryTriggerScriptId;
	}

	public void setLotteryTriggerScriptId(String lotteryTriggerScriptId) {
		this.lotteryTriggerScriptId = lotteryTriggerScriptId;
	}

	public CreateNamespaceRequest withLotteryTriggerScriptId(String lotteryTriggerScriptId) {
		this.lotteryTriggerScriptId = lotteryTriggerScriptId;
		return this;
	}

	public String getChoicePrizeTableScriptId() {
		return choicePrizeTableScriptId;
	}

	public void setChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
		this.choicePrizeTableScriptId = choicePrizeTableScriptId;
	}

	public CreateNamespaceRequest withChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
		this.choicePrizeTableScriptId = choicePrizeTableScriptId;
		return this;
	}

	public LogSetting getLogSetting() {
		return logSetting;
	}

	public void setLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
	}

	public CreateNamespaceRequest withLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
		return this;
	}

    public static CreateNamespaceRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateNamespaceRequest()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withQueueNamespaceId(data.get("queueNamespaceId") == null || data.get("queueNamespaceId").isNull() ? null : data.get("queueNamespaceId").asText())
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText())
            .withLotteryTriggerScriptId(data.get("lotteryTriggerScriptId") == null || data.get("lotteryTriggerScriptId").isNull() ? null : data.get("lotteryTriggerScriptId").asText())
            .withChoicePrizeTableScriptId(data.get("choicePrizeTableScriptId") == null || data.get("choicePrizeTableScriptId").isNull() ? null : data.get("choicePrizeTableScriptId").asText())
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("description", getDescription());
                put("queueNamespaceId", getQueueNamespaceId());
                put("keyId", getKeyId());
                put("lotteryTriggerScriptId", getLotteryTriggerScriptId());
                put("choicePrizeTableScriptId", getChoicePrizeTableScriptId());
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}