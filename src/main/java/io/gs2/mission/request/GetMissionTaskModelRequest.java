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

package io.gs2.mission.request;

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
public class GetMissionTaskModelRequest extends Gs2BasicRequest<GetMissionTaskModelRequest> {
    private String namespaceName;
    private String missionGroupName;
    private String missionTaskName;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public GetMissionTaskModelRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getMissionGroupName() {
		return missionGroupName;
	}

	public void setMissionGroupName(String missionGroupName) {
		this.missionGroupName = missionGroupName;
	}

	public GetMissionTaskModelRequest withMissionGroupName(String missionGroupName) {
		this.missionGroupName = missionGroupName;
		return this;
	}

	public String getMissionTaskName() {
		return missionTaskName;
	}

	public void setMissionTaskName(String missionTaskName) {
		this.missionTaskName = missionTaskName;
	}

	public GetMissionTaskModelRequest withMissionTaskName(String missionTaskName) {
		this.missionTaskName = missionTaskName;
		return this;
	}

    public static GetMissionTaskModelRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetMissionTaskModelRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withMissionGroupName(data.get("missionGroupName") == null || data.get("missionGroupName").isNull() ? null : data.get("missionGroupName").asText())
            .withMissionTaskName(data.get("missionTaskName") == null || data.get("missionTaskName").isNull() ? null : data.get("missionTaskName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("missionGroupName", getMissionGroupName());
                put("missionTaskName", getMissionTaskName());
            }}
        );
    }
}