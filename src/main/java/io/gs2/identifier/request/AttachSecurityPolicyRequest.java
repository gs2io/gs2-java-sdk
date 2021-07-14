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
public class AttachSecurityPolicyRequest extends Gs2BasicRequest<AttachSecurityPolicyRequest> {
    private String userName;
    private String securityPolicyId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public AttachSecurityPolicyRequest withUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getSecurityPolicyId() {
		return securityPolicyId;
	}

	public void setSecurityPolicyId(String securityPolicyId) {
		this.securityPolicyId = securityPolicyId;
	}

	public AttachSecurityPolicyRequest withSecurityPolicyId(String securityPolicyId) {
		this.securityPolicyId = securityPolicyId;
		return this;
	}

    public static AttachSecurityPolicyRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AttachSecurityPolicyRequest()
            .withUserName(data.get("userName") == null || data.get("userName").isNull() ? null : data.get("userName").asText())
            .withSecurityPolicyId(data.get("securityPolicyId") == null || data.get("securityPolicyId").isNull() ? null : data.get("securityPolicyId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("userName", getUserName());
                put("securityPolicyId", getSecurityPolicyId());
            }}
        );
    }
}