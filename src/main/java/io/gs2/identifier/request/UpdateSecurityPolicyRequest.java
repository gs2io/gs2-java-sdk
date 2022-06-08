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
public class UpdateSecurityPolicyRequest extends Gs2BasicRequest<UpdateSecurityPolicyRequest> {
    private String securityPolicyName;
    private String description;
    private String policy;
	public String getSecurityPolicyName() {
		return securityPolicyName;
	}
	public void setSecurityPolicyName(String securityPolicyName) {
		this.securityPolicyName = securityPolicyName;
	}
	public UpdateSecurityPolicyRequest withSecurityPolicyName(String securityPolicyName) {
		this.securityPolicyName = securityPolicyName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateSecurityPolicyRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	public UpdateSecurityPolicyRequest withPolicy(String policy) {
		this.policy = policy;
		return this;
	}

    public static UpdateSecurityPolicyRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateSecurityPolicyRequest()
            .withSecurityPolicyName(data.get("securityPolicyName") == null || data.get("securityPolicyName").isNull() ? null : data.get("securityPolicyName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withPolicy(data.get("policy") == null || data.get("policy").isNull() ? null : data.get("policy").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("securityPolicyName", getSecurityPolicyName());
                put("description", getDescription());
                put("policy", getPolicy());
            }}
        );
    }
}