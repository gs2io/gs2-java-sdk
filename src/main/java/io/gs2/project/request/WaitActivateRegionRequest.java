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
public class WaitActivateRegionRequest extends Gs2BasicRequest<WaitActivateRegionRequest> {
    private String projectName;
    private String regionName;
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public WaitActivateRegionRequest withProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public WaitActivateRegionRequest withRegionName(String regionName) {
		this.regionName = regionName;
		return this;
	}

    public static WaitActivateRegionRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new WaitActivateRegionRequest()
            .withProjectName(data.get("projectName") == null || data.get("projectName").isNull() ? null : data.get("projectName").asText())
            .withRegionName(data.get("regionName") == null || data.get("regionName").isNull() ? null : data.get("regionName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("projectName", getProjectName());
                put("regionName", getRegionName());
            }}
        );
    }
}