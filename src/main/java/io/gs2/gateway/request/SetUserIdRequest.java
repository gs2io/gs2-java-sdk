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

package io.gs2.gateway.request;

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
public class SetUserIdRequest extends Gs2BasicRequest<SetUserIdRequest> {
    private String namespaceName;
    private String accessToken;
    private Boolean allowConcurrentAccess;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public SetUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public SetUserIdRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	public Boolean getAllowConcurrentAccess() {
		return allowConcurrentAccess;
	}

	public void setAllowConcurrentAccess(Boolean allowConcurrentAccess) {
		this.allowConcurrentAccess = allowConcurrentAccess;
	}

	public SetUserIdRequest withAllowConcurrentAccess(Boolean allowConcurrentAccess) {
		this.allowConcurrentAccess = allowConcurrentAccess;
		return this;
	}

    public static SetUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withAllowConcurrentAccess(data.get("allowConcurrentAccess") == null || data.get("allowConcurrentAccess").isNull() ? null : data.get("allowConcurrentAccess").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("allowConcurrentAccess", getAllowConcurrentAccess());
            }}
        );
    }
}