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

package io.gs2.project.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.project.model.*;
import io.gs2.project.model.Project;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetProjectTokenByIdentifierResult implements IResult, Serializable {
    private Project item;
    private String ownerId;
    private String projectToken;

	public Project getItem() {
		return item;
	}

	public void setItem(Project item) {
		this.item = item;
	}

	public GetProjectTokenByIdentifierResult withItem(Project item) {
		this.item = item;
		return this;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public GetProjectTokenByIdentifierResult withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	public String getProjectToken() {
		return projectToken;
	}

	public void setProjectToken(String projectToken) {
		this.projectToken = projectToken;
	}

	public GetProjectTokenByIdentifierResult withProjectToken(String projectToken) {
		this.projectToken = projectToken;
		return this;
	}

    public static GetProjectTokenByIdentifierResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetProjectTokenByIdentifierResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Project.fromJson(data.get("item")))
            .withOwnerId(data.get("ownerId") == null || data.get("ownerId").isNull() ? null : data.get("ownerId").asText())
            .withProjectToken(data.get("projectToken") == null || data.get("projectToken").isNull() ? null : data.get("projectToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("ownerId", getOwnerId());
                put("projectToken", getProjectToken());
            }}
        );
    }
}