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

package io.gs2.project.request;

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
public class GetProjectTokenRequest extends Gs2BasicRequest<GetProjectTokenRequest> {
    private String projectName;
    private String accountToken;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public GetProjectTokenRequest withProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

	public String getAccountToken() {
		return accountToken;
	}

	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}

	public GetProjectTokenRequest withAccountToken(String accountToken) {
		this.accountToken = accountToken;
		return this;
	}

    public static GetProjectTokenRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetProjectTokenRequest()
            .withProjectName(data.get("projectName") == null || data.get("projectName").isNull() ? null : data.get("projectName").asText())
            .withAccountToken(data.get("accountToken") == null || data.get("accountToken").isNull() ? null : data.get("accountToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("projectName", getProjectName());
                put("accountToken", getAccountToken());
            }}
        );
    }
}