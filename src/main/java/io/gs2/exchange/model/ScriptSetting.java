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

package io.gs2.exchange.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ScriptSetting implements IModel, Serializable {
	private String triggerScriptId;
	private String doneTriggerTargetType;
	private String doneTriggerScriptId;
	private String doneTriggerQueueNamespaceId;

	public String getTriggerScriptId() {
		return triggerScriptId;
	}

	public void setTriggerScriptId(String triggerScriptId) {
		this.triggerScriptId = triggerScriptId;
	}

	public ScriptSetting withTriggerScriptId(String triggerScriptId) {
		this.triggerScriptId = triggerScriptId;
		return this;
	}

	public String getDoneTriggerTargetType() {
		return doneTriggerTargetType;
	}

	public void setDoneTriggerTargetType(String doneTriggerTargetType) {
		this.doneTriggerTargetType = doneTriggerTargetType;
	}

	public ScriptSetting withDoneTriggerTargetType(String doneTriggerTargetType) {
		this.doneTriggerTargetType = doneTriggerTargetType;
		return this;
	}

	public String getDoneTriggerScriptId() {
		return doneTriggerScriptId;
	}

	public void setDoneTriggerScriptId(String doneTriggerScriptId) {
		this.doneTriggerScriptId = doneTriggerScriptId;
	}

	public ScriptSetting withDoneTriggerScriptId(String doneTriggerScriptId) {
		this.doneTriggerScriptId = doneTriggerScriptId;
		return this;
	}

	public String getDoneTriggerQueueNamespaceId() {
		return doneTriggerQueueNamespaceId;
	}

	public void setDoneTriggerQueueNamespaceId(String doneTriggerQueueNamespaceId) {
		this.doneTriggerQueueNamespaceId = doneTriggerQueueNamespaceId;
	}

	public ScriptSetting withDoneTriggerQueueNamespaceId(String doneTriggerQueueNamespaceId) {
		this.doneTriggerQueueNamespaceId = doneTriggerQueueNamespaceId;
		return this;
	}

    public static ScriptSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ScriptSetting()
            .withTriggerScriptId(data.get("triggerScriptId") == null || data.get("triggerScriptId").isNull() ? null : data.get("triggerScriptId").asText())
            .withDoneTriggerTargetType(data.get("doneTriggerTargetType") == null || data.get("doneTriggerTargetType").isNull() ? null : data.get("doneTriggerTargetType").asText())
            .withDoneTriggerScriptId(data.get("doneTriggerScriptId") == null || data.get("doneTriggerScriptId").isNull() ? null : data.get("doneTriggerScriptId").asText())
            .withDoneTriggerQueueNamespaceId(data.get("doneTriggerQueueNamespaceId") == null || data.get("doneTriggerQueueNamespaceId").isNull() ? null : data.get("doneTriggerQueueNamespaceId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("triggerScriptId", getTriggerScriptId());
                put("doneTriggerTargetType", getDoneTriggerTargetType());
                put("doneTriggerScriptId", getDoneTriggerScriptId());
                put("doneTriggerQueueNamespaceId", getDoneTriggerQueueNamespaceId());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.triggerScriptId == null) ? 0 : this.triggerScriptId.hashCode());
        result = prime * result + ((this.doneTriggerTargetType == null) ? 0 : this.doneTriggerTargetType.hashCode());
        result = prime * result + ((this.doneTriggerScriptId == null) ? 0 : this.doneTriggerScriptId.hashCode());
        result = prime * result + ((this.doneTriggerQueueNamespaceId == null) ? 0 : this.doneTriggerQueueNamespaceId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		ScriptSetting other = (ScriptSetting) o;
		if (triggerScriptId == null) {
			return other.triggerScriptId == null;
		} else if (!triggerScriptId.equals(other.triggerScriptId)) {
			return false;
		}
		if (doneTriggerTargetType == null) {
			return other.doneTriggerTargetType == null;
		} else if (!doneTriggerTargetType.equals(other.doneTriggerTargetType)) {
			return false;
		}
		if (doneTriggerScriptId == null) {
			return other.doneTriggerScriptId == null;
		} else if (!doneTriggerScriptId.equals(other.doneTriggerScriptId)) {
			return false;
		}
		if (doneTriggerQueueNamespaceId == null) {
			return other.doneTriggerQueueNamespaceId == null;
		} else if (!doneTriggerQueueNamespaceId.equals(other.doneTriggerQueueNamespaceId)) {
			return false;
		}
		return true;
	}
}