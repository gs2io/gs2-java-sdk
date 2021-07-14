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

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class InvokeScriptRequest extends Gs2BasicRequest<InvokeScriptRequest> {
    private String scriptId;
    private String args;

	public String getScriptId() {
		return scriptId;
	}

	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}

	public InvokeScriptRequest withScriptId(String scriptId) {
		this.scriptId = scriptId;
		return this;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public InvokeScriptRequest withArgs(String args) {
		this.args = args;
		return this;
	}

    public static InvokeScriptRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new InvokeScriptRequest()
            .withScriptId(data.get("scriptId") == null || data.get("scriptId").isNull() ? null : data.get("scriptId").asText())
            .withArgs(data.get("args") == null || data.get("args").isNull() ? null : data.get("args").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("scriptId", getScriptId());
                put("args", getArgs());
            }}
        );
    }
}