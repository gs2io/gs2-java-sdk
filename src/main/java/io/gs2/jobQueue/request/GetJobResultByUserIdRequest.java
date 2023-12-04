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
public class GetJobResultByUserIdRequest extends Gs2BasicRequest<GetJobResultByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String jobName;
    private Integer tryNumber;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public GetJobResultByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public GetJobResultByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public GetJobResultByUserIdRequest withJobName(String jobName) {
		this.jobName = jobName;
		return this;
	}
	public Integer getTryNumber() {
		return tryNumber;
	}
	public void setTryNumber(Integer tryNumber) {
		this.tryNumber = tryNumber;
	}
	public GetJobResultByUserIdRequest withTryNumber(Integer tryNumber) {
		this.tryNumber = tryNumber;
		return this;
	}

    public static GetJobResultByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetJobResultByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withJobName(data.get("jobName") == null || data.get("jobName").isNull() ? null : data.get("jobName").asText())
            .withTryNumber(data.get("tryNumber") == null || data.get("tryNumber").isNull() ? null : data.get("tryNumber").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("jobName", getJobName());
                put("tryNumber", getTryNumber());
            }}
        );
    }
}