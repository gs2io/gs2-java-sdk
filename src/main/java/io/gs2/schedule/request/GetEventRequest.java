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

package io.gs2.schedule.request;

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
public class GetEventRequest extends Gs2BasicRequest<GetEventRequest> {
    private String namespaceName;
    private String eventName;
    private String accessToken;
    private Boolean isInSchedule;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetEventRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public GetEventRequest withEventName(String eventName) {
		this.eventName = eventName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public GetEventRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public Boolean getIsInSchedule() {
		return isInSchedule;
	}
	public void setIsInSchedule(Boolean isInSchedule) {
		this.isInSchedule = isInSchedule;
	}
	public GetEventRequest withIsInSchedule(Boolean isInSchedule) {
		this.isInSchedule = isInSchedule;
		return this;
	}

    public static GetEventRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetEventRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withEventName(data.get("eventName") == null || data.get("eventName").isNull() ? null : data.get("eventName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withIsInSchedule(data.get("isInSchedule") == null || data.get("isInSchedule").isNull() ? null : data.get("isInSchedule").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("eventName", getEventName());
                put("accessToken", getAccessToken());
                put("isInSchedule", getIsInSchedule());
            }}
        );
    }
}