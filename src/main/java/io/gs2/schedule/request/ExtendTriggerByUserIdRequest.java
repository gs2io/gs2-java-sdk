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
public class ExtendTriggerByUserIdRequest extends Gs2BasicRequest<ExtendTriggerByUserIdRequest> {
    private String namespaceName;
    private String triggerName;
    private String userId;
    private Integer extendSeconds;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public ExtendTriggerByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public ExtendTriggerByUserIdRequest withTriggerName(String triggerName) {
		this.triggerName = triggerName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ExtendTriggerByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getExtendSeconds() {
		return extendSeconds;
	}
	public void setExtendSeconds(Integer extendSeconds) {
		this.extendSeconds = extendSeconds;
	}
	public ExtendTriggerByUserIdRequest withExtendSeconds(Integer extendSeconds) {
		this.extendSeconds = extendSeconds;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public ExtendTriggerByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public ExtendTriggerByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static ExtendTriggerByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ExtendTriggerByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withTriggerName(data.get("triggerName") == null || data.get("triggerName").isNull() ? null : data.get("triggerName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withExtendSeconds(data.get("extendSeconds") == null || data.get("extendSeconds").isNull() ? null : data.get("extendSeconds").intValue())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("triggerName", getTriggerName());
                put("userId", getUserId());
                put("extendSeconds", getExtendSeconds());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}