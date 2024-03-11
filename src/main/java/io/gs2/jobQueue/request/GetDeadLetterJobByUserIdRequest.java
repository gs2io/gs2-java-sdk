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

package io.gs2.jobQueue.request;

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
public class GetDeadLetterJobByUserIdRequest extends Gs2BasicRequest<GetDeadLetterJobByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String deadLetterJobName;
    private String timeOffsetToken;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetDeadLetterJobByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public GetDeadLetterJobByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getDeadLetterJobName() {
		return deadLetterJobName;
	}
	public void setDeadLetterJobName(String deadLetterJobName) {
		this.deadLetterJobName = deadLetterJobName;
	}
	public GetDeadLetterJobByUserIdRequest withDeadLetterJobName(String deadLetterJobName) {
		this.deadLetterJobName = deadLetterJobName;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public GetDeadLetterJobByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

    public static GetDeadLetterJobByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetDeadLetterJobByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withDeadLetterJobName(data.get("deadLetterJobName") == null || data.get("deadLetterJobName").isNull() ? null : data.get("deadLetterJobName").asText())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("deadLetterJobName", getDeadLetterJobName());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}