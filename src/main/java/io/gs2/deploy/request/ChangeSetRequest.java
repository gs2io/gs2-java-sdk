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

package io.gs2.deploy.request;

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
public class ChangeSetRequest extends Gs2BasicRequest<ChangeSetRequest> {
    private String stackName;
    private String mode;
    private String template;
    private String uploadToken;
	public String getStackName() {
		return stackName;
	}
	public void setStackName(String stackName) {
		this.stackName = stackName;
	}
	public ChangeSetRequest withStackName(String stackName) {
		this.stackName = stackName;
		return this;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public ChangeSetRequest withMode(String mode) {
		this.mode = mode;
		return this;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public ChangeSetRequest withTemplate(String template) {
		this.template = template;
		return this;
	}
	public String getUploadToken() {
		return uploadToken;
	}
	public void setUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
	}
	public ChangeSetRequest withUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
		return this;
	}

    public static ChangeSetRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ChangeSetRequest()
            .withStackName(data.get("stackName") == null || data.get("stackName").isNull() ? null : data.get("stackName").asText())
            .withMode(data.get("mode") == null || data.get("mode").isNull() ? null : data.get("mode").asText())
            .withTemplate(data.get("template") == null || data.get("template").isNull() ? null : data.get("template").asText())
            .withUploadToken(data.get("uploadToken") == null || data.get("uploadToken").isNull() ? null : data.get("uploadToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("stackName", getStackName());
                put("mode", getMode());
                put("template", getTemplate());
                put("uploadToken", getUploadToken());
            }}
        );
    }
}