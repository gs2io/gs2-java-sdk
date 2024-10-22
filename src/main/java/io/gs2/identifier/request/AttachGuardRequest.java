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

package io.gs2.identifier.request;

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
public class AttachGuardRequest extends Gs2BasicRequest<AttachGuardRequest> {
    private String userName;
    private String clientId;
    private String guardNamespaceId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public AttachGuardRequest withUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public AttachGuardRequest withClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}
	public String getGuardNamespaceId() {
		return guardNamespaceId;
	}
	public void setGuardNamespaceId(String guardNamespaceId) {
		this.guardNamespaceId = guardNamespaceId;
	}
	public AttachGuardRequest withGuardNamespaceId(String guardNamespaceId) {
		this.guardNamespaceId = guardNamespaceId;
		return this;
	}

    public static AttachGuardRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AttachGuardRequest()
            .withUserName(data.get("userName") == null || data.get("userName").isNull() ? null : data.get("userName").asText())
            .withClientId(data.get("clientId") == null || data.get("clientId").isNull() ? null : data.get("clientId").asText())
            .withGuardNamespaceId(data.get("guardNamespaceId") == null || data.get("guardNamespaceId").isNull() ? null : data.get("guardNamespaceId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("userName", getUserName());
                put("clientId", getClientId());
                put("guardNamespaceId", getGuardNamespaceId());
            }}
        );
    }
}