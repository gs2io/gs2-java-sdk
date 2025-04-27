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
public class CreateBillingMethodRequest extends Gs2BasicRequest<CreateBillingMethodRequest> {
    private String accountToken;
    private String description;
    private String methodType;
    private String cardCustomerId;
    private String partnerId;
	public String getAccountToken() {
		return accountToken;
	}
	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}
	public CreateBillingMethodRequest withAccountToken(String accountToken) {
		this.accountToken = accountToken;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateBillingMethodRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	public CreateBillingMethodRequest withMethodType(String methodType) {
		this.methodType = methodType;
		return this;
	}
	public String getCardCustomerId() {
		return cardCustomerId;
	}
	public void setCardCustomerId(String cardCustomerId) {
		this.cardCustomerId = cardCustomerId;
	}
	public CreateBillingMethodRequest withCardCustomerId(String cardCustomerId) {
		this.cardCustomerId = cardCustomerId;
		return this;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public CreateBillingMethodRequest withPartnerId(String partnerId) {
		this.partnerId = partnerId;
		return this;
	}

    public static CreateBillingMethodRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateBillingMethodRequest()
            .withAccountToken(data.get("accountToken") == null || data.get("accountToken").isNull() ? null : data.get("accountToken").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMethodType(data.get("methodType") == null || data.get("methodType").isNull() ? null : data.get("methodType").asText())
            .withCardCustomerId(data.get("cardCustomerId") == null || data.get("cardCustomerId").isNull() ? null : data.get("cardCustomerId").asText())
            .withPartnerId(data.get("partnerId") == null || data.get("partnerId").isNull() ? null : data.get("partnerId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("accountToken", getAccountToken());
                put("description", getDescription());
                put("methodType", getMethodType());
                put("cardCustomerId", getCardCustomerId());
                put("partnerId", getPartnerId());
            }}
        );
    }
}