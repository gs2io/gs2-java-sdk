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

package io.gs2.exchange.request;

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
public class DeleteAwaitByUserIdRequest extends Gs2BasicRequest<DeleteAwaitByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String rateName;
    private String awaitName;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public DeleteAwaitByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public DeleteAwaitByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getRateName() {
		return rateName;
	}

	public void setRateName(String rateName) {
		this.rateName = rateName;
	}

	public DeleteAwaitByUserIdRequest withRateName(String rateName) {
		this.rateName = rateName;
		return this;
	}

	public String getAwaitName() {
		return awaitName;
	}

	public void setAwaitName(String awaitName) {
		this.awaitName = awaitName;
	}

	public DeleteAwaitByUserIdRequest withAwaitName(String awaitName) {
		this.awaitName = awaitName;
		return this;
	}

    public static DeleteAwaitByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DeleteAwaitByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withRateName(data.get("rateName") == null || data.get("rateName").isNull() ? null : data.get("rateName").asText())
            .withAwaitName(data.get("awaitName") == null || data.get("awaitName").isNull() ? null : data.get("awaitName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("rateName", getRateName());
                put("awaitName", getAwaitName());
            }}
        );
    }
}