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

package io.gs2.stateMachine.request;

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
public class UpdateStateMachineMasterRequest extends Gs2BasicRequest<UpdateStateMachineMasterRequest> {
    private String namespaceName;
    private String mainStateMachineName;
    private String payload;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateStateMachineMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getMainStateMachineName() {
		return mainStateMachineName;
	}
	public void setMainStateMachineName(String mainStateMachineName) {
		this.mainStateMachineName = mainStateMachineName;
	}
	public UpdateStateMachineMasterRequest withMainStateMachineName(String mainStateMachineName) {
		this.mainStateMachineName = mainStateMachineName;
		return this;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public UpdateStateMachineMasterRequest withPayload(String payload) {
		this.payload = payload;
		return this;
	}

    public static UpdateStateMachineMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateStateMachineMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withMainStateMachineName(data.get("mainStateMachineName") == null || data.get("mainStateMachineName").isNull() ? null : data.get("mainStateMachineName").asText())
            .withPayload(data.get("payload") == null || data.get("payload").isNull() ? null : data.get("payload").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("mainStateMachineName", getMainStateMachineName());
                put("payload", getPayload());
            }}
        );
    }
}