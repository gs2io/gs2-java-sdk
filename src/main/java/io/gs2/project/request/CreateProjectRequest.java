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
public class CreateProjectRequest extends Gs2BasicRequest<CreateProjectRequest> {
    private String accountToken;
    private String name;
    private String description;
    private String plan;
    private String billingMethodName;
    private String enableEventBridge;
    private String eventBridgeAwsAccountId;
    private String eventBridgeAwsRegion;

	public String getAccountToken() {
		return accountToken;
	}

	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}

	public CreateProjectRequest withAccountToken(String accountToken) {
		this.accountToken = accountToken;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CreateProjectRequest withName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CreateProjectRequest withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public CreateProjectRequest withPlan(String plan) {
		this.plan = plan;
		return this;
	}

	public String getBillingMethodName() {
		return billingMethodName;
	}

	public void setBillingMethodName(String billingMethodName) {
		this.billingMethodName = billingMethodName;
	}

	public CreateProjectRequest withBillingMethodName(String billingMethodName) {
		this.billingMethodName = billingMethodName;
		return this;
	}

	public String getEnableEventBridge() {
		return enableEventBridge;
	}

	public void setEnableEventBridge(String enableEventBridge) {
		this.enableEventBridge = enableEventBridge;
	}

	public CreateProjectRequest withEnableEventBridge(String enableEventBridge) {
		this.enableEventBridge = enableEventBridge;
		return this;
	}

	public String getEventBridgeAwsAccountId() {
		return eventBridgeAwsAccountId;
	}

	public void setEventBridgeAwsAccountId(String eventBridgeAwsAccountId) {
		this.eventBridgeAwsAccountId = eventBridgeAwsAccountId;
	}

	public CreateProjectRequest withEventBridgeAwsAccountId(String eventBridgeAwsAccountId) {
		this.eventBridgeAwsAccountId = eventBridgeAwsAccountId;
		return this;
	}

	public String getEventBridgeAwsRegion() {
		return eventBridgeAwsRegion;
	}

	public void setEventBridgeAwsRegion(String eventBridgeAwsRegion) {
		this.eventBridgeAwsRegion = eventBridgeAwsRegion;
	}

	public CreateProjectRequest withEventBridgeAwsRegion(String eventBridgeAwsRegion) {
		this.eventBridgeAwsRegion = eventBridgeAwsRegion;
		return this;
	}

    public static CreateProjectRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateProjectRequest()
            .withAccountToken(data.get("accountToken") == null || data.get("accountToken").isNull() ? null : data.get("accountToken").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withPlan(data.get("plan") == null || data.get("plan").isNull() ? null : data.get("plan").asText())
            .withBillingMethodName(data.get("billingMethodName") == null || data.get("billingMethodName").isNull() ? null : data.get("billingMethodName").asText())
            .withEnableEventBridge(data.get("enableEventBridge") == null || data.get("enableEventBridge").isNull() ? null : data.get("enableEventBridge").asText())
            .withEventBridgeAwsAccountId(data.get("eventBridgeAwsAccountId") == null || data.get("eventBridgeAwsAccountId").isNull() ? null : data.get("eventBridgeAwsAccountId").asText())
            .withEventBridgeAwsRegion(data.get("eventBridgeAwsRegion") == null || data.get("eventBridgeAwsRegion").isNull() ? null : data.get("eventBridgeAwsRegion").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("accountToken", getAccountToken());
                put("name", getName());
                put("description", getDescription());
                put("plan", getPlan());
                put("billingMethodName", getBillingMethodName());
                put("enableEventBridge", getEnableEventBridge());
                put("eventBridgeAwsAccountId", getEventBridgeAwsAccountId());
                put("eventBridgeAwsRegion", getEventBridgeAwsRegion());
            }}
        );
    }
}