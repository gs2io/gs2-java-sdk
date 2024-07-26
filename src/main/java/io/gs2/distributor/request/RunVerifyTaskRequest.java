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

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RunVerifyTaskRequest extends Gs2BasicRequest<RunVerifyTaskRequest> {
    private String namespaceName;
    private String verifyTask;
    private String keyId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public RunVerifyTaskRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getVerifyTask() {
		return verifyTask;
	}
	public void setVerifyTask(String verifyTask) {
		this.verifyTask = verifyTask;
	}
	public RunVerifyTaskRequest withVerifyTask(String verifyTask) {
		this.verifyTask = verifyTask;
		return this;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public RunVerifyTaskRequest withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}

    public static RunVerifyTaskRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RunVerifyTaskRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withVerifyTask(data.get("verifyTask") == null || data.get("verifyTask").isNull() ? null : data.get("verifyTask").asText())
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("verifyTask", getVerifyTask());
                put("keyId", getKeyId());
            }}
        );
    }
}