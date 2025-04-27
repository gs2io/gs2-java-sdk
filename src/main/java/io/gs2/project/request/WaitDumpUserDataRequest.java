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
public class WaitDumpUserDataRequest extends Gs2BasicRequest<WaitDumpUserDataRequest> {
    private String ownerId;
    private String transactionId;
    private String userId;
    private String microserviceName;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public WaitDumpUserDataRequest withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public WaitDumpUserDataRequest withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public WaitDumpUserDataRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getMicroserviceName() {
		return microserviceName;
	}
	public void setMicroserviceName(String microserviceName) {
		this.microserviceName = microserviceName;
	}
	public WaitDumpUserDataRequest withMicroserviceName(String microserviceName) {
		this.microserviceName = microserviceName;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public WaitDumpUserDataRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public WaitDumpUserDataRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static WaitDumpUserDataRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new WaitDumpUserDataRequest()
            .withOwnerId(data.get("ownerId") == null || data.get("ownerId").isNull() ? null : data.get("ownerId").asText())
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withMicroserviceName(data.get("microserviceName") == null || data.get("microserviceName").isNull() ? null : data.get("microserviceName").asText())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("ownerId", getOwnerId());
                put("transactionId", getTransactionId());
                put("userId", getUserId());
                put("microserviceName", getMicroserviceName());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}