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
public class UpdateStackRequest extends Gs2BasicRequest<UpdateStackRequest> {
    private String stackName;
    private String description;
    private String template;
	public String getStackName() {
		return stackName;
	}
	public void setStackName(String stackName) {
		this.stackName = stackName;
	}
	public UpdateStackRequest withStackName(String stackName) {
		this.stackName = stackName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateStackRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public UpdateStackRequest withTemplate(String template) {
		this.template = template;
		return this;
	}

    public static UpdateStackRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateStackRequest()
            .withStackName(data.get("stackName") == null || data.get("stackName").isNull() ? null : data.get("stackName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withTemplate(data.get("template") == null || data.get("template").isNull() ? null : data.get("template").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("stackName", getStackName());
                put("description", getDescription());
                put("template", getTemplate());
            }}
        );
    }
}