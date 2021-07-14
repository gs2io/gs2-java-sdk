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
public class UpdateBillingMethodRequest extends Gs2BasicRequest<UpdateBillingMethodRequest> {
    private String accountToken;
    private String billingMethodName;
    private String description;

	public String getAccountToken() {
		return accountToken;
	}

	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}

	public UpdateBillingMethodRequest withAccountToken(String accountToken) {
		this.accountToken = accountToken;
		return this;
	}

	public String getBillingMethodName() {
		return billingMethodName;
	}

	public void setBillingMethodName(String billingMethodName) {
		this.billingMethodName = billingMethodName;
	}

	public UpdateBillingMethodRequest withBillingMethodName(String billingMethodName) {
		this.billingMethodName = billingMethodName;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UpdateBillingMethodRequest withDescription(String description) {
		this.description = description;
		return this;
	}

    public static UpdateBillingMethodRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateBillingMethodRequest()
            .withAccountToken(data.get("accountToken") == null || data.get("accountToken").isNull() ? null : data.get("accountToken").asText())
            .withBillingMethodName(data.get("billingMethodName") == null || data.get("billingMethodName").isNull() ? null : data.get("billingMethodName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("accountToken", getAccountToken());
                put("billingMethodName", getBillingMethodName());
                put("description", getDescription());
            }}
        );
    }
}