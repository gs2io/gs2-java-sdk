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

package io.gs2.quest.request;

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
public class GetQuestModelRequest extends Gs2BasicRequest<GetQuestModelRequest> {
    private String namespaceName;
    private String questGroupName;
    private String questName;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public GetQuestModelRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getQuestGroupName() {
		return questGroupName;
	}

	public void setQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
	}

	public GetQuestModelRequest withQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
		return this;
	}

	public String getQuestName() {
		return questName;
	}

	public void setQuestName(String questName) {
		this.questName = questName;
	}

	public GetQuestModelRequest withQuestName(String questName) {
		this.questName = questName;
		return this;
	}

    public static GetQuestModelRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetQuestModelRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withQuestGroupName(data.get("questGroupName") == null || data.get("questGroupName").isNull() ? null : data.get("questGroupName").asText())
            .withQuestName(data.get("questName") == null || data.get("questName").isNull() ? null : data.get("questName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("questGroupName", getQuestGroupName());
                put("questName", getQuestName());
            }}
        );
    }
}