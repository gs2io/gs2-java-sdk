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

package io.gs2.chat.request;

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
public class UpdateCurrentModelMasterRequest extends Gs2BasicRequest<UpdateCurrentModelMasterRequest> {
    private String namespaceName;
    private String mode;
    private String settings;
    private String uploadToken;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateCurrentModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public UpdateCurrentModelMasterRequest withMode(String mode) {
		this.mode = mode;
		return this;
	}
	public String getSettings() {
		return settings;
	}
	public void setSettings(String settings) {
		this.settings = settings;
	}
	public UpdateCurrentModelMasterRequest withSettings(String settings) {
		this.settings = settings;
		return this;
	}
	public String getUploadToken() {
		return uploadToken;
	}
	public void setUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
	}
	public UpdateCurrentModelMasterRequest withUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
		return this;
	}

    public static UpdateCurrentModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateCurrentModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withMode(data.get("mode") == null || data.get("mode").isNull() ? null : data.get("mode").asText())
            .withSettings(data.get("settings") == null || data.get("settings").isNull() ? null : data.get("settings").asText())
            .withUploadToken(data.get("uploadToken") == null || data.get("uploadToken").isNull() ? null : data.get("uploadToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("mode", getMode());
                put("settings", getSettings());
                put("uploadToken", getUploadToken());
            }}
        );
    }
}