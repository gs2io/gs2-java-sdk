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

package io.gs2.datastore.request;

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
public class PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest extends Gs2BasicRequest<PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest> {
    private String namespaceName;
    private String userId;
    private String dataObjectName;
    private String generation;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getDataObjectName() {
		return dataObjectName;
	}
	public void setDataObjectName(String dataObjectName) {
		this.dataObjectName = dataObjectName;
	}
	public PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest withDataObjectName(String dataObjectName) {
		this.dataObjectName = dataObjectName;
		return this;
	}
	public String getGeneration() {
		return generation;
	}
	public void setGeneration(String generation) {
		this.generation = generation;
	}
	public PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest withGeneration(String generation) {
		this.generation = generation;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PrepareDownloadByUserIdAndDataObjectNameAndGenerationRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withDataObjectName(data.get("dataObjectName") == null || data.get("dataObjectName").isNull() ? null : data.get("dataObjectName").asText())
            .withGeneration(data.get("generation") == null || data.get("generation").isNull() ? null : data.get("generation").asText())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("dataObjectName", getDataObjectName());
                put("generation", getGeneration());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}