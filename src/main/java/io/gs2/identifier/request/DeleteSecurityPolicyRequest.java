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
public class DeleteSecurityPolicyRequest extends Gs2BasicRequest<DeleteSecurityPolicyRequest> {
    private String securityPolicyName;
	public String getSecurityPolicyName() {
		return securityPolicyName;
	}
	public void setSecurityPolicyName(String securityPolicyName) {
		this.securityPolicyName = securityPolicyName;
	}
	public DeleteSecurityPolicyRequest withSecurityPolicyName(String securityPolicyName) {
		this.securityPolicyName = securityPolicyName;
		return this;
	}

    public static DeleteSecurityPolicyRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DeleteSecurityPolicyRequest()
            .withSecurityPolicyName(data.get("securityPolicyName") == null || data.get("securityPolicyName").isNull() ? null : data.get("securityPolicyName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("securityPolicyName", getSecurityPolicyName());
            }}
        );
    }
}