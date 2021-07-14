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
public class UpdateProjectRequest extends Gs2BasicRequest<UpdateProjectRequest> {
    private String accountToken;
    private String projectName;
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

	public UpdateProjectRequest withAccountToken(String accountToken) {
		this.accountToken = accountToken;
		return this;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public UpdateProjectRequest withProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UpdateProjectRequest withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public UpdateProjectRequest withPlan(String plan) {
		this.plan = plan;
		return this;
	}

	public String getBillingMethodName() {
		return billingMethodName;
	}

	public void setBillingMethodName(String billingMethodName) {
		this.billingMethodName = billingMethodName;
	}

	public UpdateProjectRequest withBillingMethodName(String billingMethodName) {
		this.billingMethodName = billingMethodName;
		return this;
	}

	public String getEnableEventBridge() {
		return enableEventBridge;
	}

	public void setEnableEventBridge(String enableEventBridge) {
		this.enableEventBridge = enableEventBridge;
	}

	public UpdateProjectRequest withEnableEventBridge(String enableEventBridge) {
		this.enableEventBridge = enableEventBridge;
		return this;
	}

	public String getEventBridgeAwsAccountId() {
		return eventBridgeAwsAccountId;
	}

	public void setEventBridgeAwsAccountId(String eventBridgeAwsAccountId) {
		this.eventBridgeAwsAccountId = eventBridgeAwsAccountId;
	}

	public UpdateProjectRequest withEventBridgeAwsAccountId(String eventBridgeAwsAccountId) {
		this.eventBridgeAwsAccountId = eventBridgeAwsAccountId;
		return this;
	}

	public String getEventBridgeAwsRegion() {
		return eventBridgeAwsRegion;
	}

	public void setEventBridgeAwsRegion(String eventBridgeAwsRegion) {
		this.eventBridgeAwsRegion = eventBridgeAwsRegion;
	}

	public UpdateProjectRequest withEventBridgeAwsRegion(String eventBridgeAwsRegion) {
		this.eventBridgeAwsRegion = eventBridgeAwsRegion;
		return this;
	}

    public static UpdateProjectRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateProjectRequest()
            .withAccountToken(data.get("accountToken") == null || data.get("accountToken").isNull() ? null : data.get("accountToken").asText())
            .withProjectName(data.get("projectName") == null || data.get("projectName").isNull() ? null : data.get("projectName").asText())
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
                put("projectName", getProjectName());
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