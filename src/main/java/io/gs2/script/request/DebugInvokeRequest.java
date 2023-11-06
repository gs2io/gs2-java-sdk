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

package io.gs2.script.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.script.model.RandomUsed;
import io.gs2.script.model.RandomStatus;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DebugInvokeRequest extends Gs2BasicRequest<DebugInvokeRequest> {
    private String script;
    private String args;
    private RandomStatus randomStatus;
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public DebugInvokeRequest withScript(String script) {
		this.script = script;
		return this;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	public DebugInvokeRequest withArgs(String args) {
		this.args = args;
		return this;
	}
	public RandomStatus getRandomStatus() {
		return randomStatus;
	}
	public void setRandomStatus(RandomStatus randomStatus) {
		this.randomStatus = randomStatus;
	}
	public DebugInvokeRequest withRandomStatus(RandomStatus randomStatus) {
		this.randomStatus = randomStatus;
		return this;
	}

    public static DebugInvokeRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DebugInvokeRequest()
            .withScript(data.get("script") == null || data.get("script").isNull() ? null : data.get("script").asText())
            .withArgs(data.get("args") == null || data.get("args").isNull() ? null : data.get("args").asText())
            .withRandomStatus(data.get("randomStatus") == null || data.get("randomStatus").isNull() ? null : RandomStatus.fromJson(data.get("randomStatus")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("script", getScript());
                put("args", getArgs());
                put("randomStatus", getRandomStatus() != null ? getRandomStatus().toJson() : null);
            }}
        );
    }
}