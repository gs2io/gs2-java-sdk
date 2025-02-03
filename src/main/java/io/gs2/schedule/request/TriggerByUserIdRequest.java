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
public class TriggerByUserIdRequest extends Gs2BasicRequest<TriggerByUserIdRequest> {
    private String namespaceName;
    private String triggerName;
    private String userId;
    private String triggerStrategy;
    private Integer ttl;
    private String eventId;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public TriggerByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public TriggerByUserIdRequest withTriggerName(String triggerName) {
		this.triggerName = triggerName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public TriggerByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getTriggerStrategy() {
		return triggerStrategy;
	}
	public void setTriggerStrategy(String triggerStrategy) {
		this.triggerStrategy = triggerStrategy;
	}
	public TriggerByUserIdRequest withTriggerStrategy(String triggerStrategy) {
		this.triggerStrategy = triggerStrategy;
		return this;
	}
	public Integer getTtl() {
		return ttl;
	}
	public void setTtl(Integer ttl) {
		this.ttl = ttl;
	}
	public TriggerByUserIdRequest withTtl(Integer ttl) {
		this.ttl = ttl;
		return this;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public TriggerByUserIdRequest withEventId(String eventId) {
		this.eventId = eventId;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public TriggerByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public TriggerByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static TriggerByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new TriggerByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withTriggerName(data.get("triggerName") == null || data.get("triggerName").isNull() ? null : data.get("triggerName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withTriggerStrategy(data.get("triggerStrategy") == null || data.get("triggerStrategy").isNull() ? null : data.get("triggerStrategy").asText())
            .withTtl(data.get("ttl") == null || data.get("ttl").isNull() ? null : data.get("ttl").intValue())
            .withEventId(data.get("eventId") == null || data.get("eventId").isNull() ? null : data.get("eventId").asText())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("triggerName", getTriggerName());
                put("userId", getUserId());
                put("triggerStrategy", getTriggerStrategy());
                put("ttl", getTtl());
                put("eventId", getEventId());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}