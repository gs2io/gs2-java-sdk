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

package io.gs2.project.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Project implements IModel, Serializable, Comparable<Project> {
	private String projectId;
	private String accountName;
	private String name;
	private String description;
	private String plan;
	private List<Gs2Region> regions;
	private String billingMethodName;
	private String enableEventBridge;
	private String currency;
	private String eventBridgeAwsAccountId;
	private String eventBridgeAwsRegion;
	private Long createdAt;
	private Long updatedAt;
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Project withProjectId(String projectId) {
		this.projectId = projectId;
		return this;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Project withAccountName(String accountName) {
		this.accountName = accountName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Project withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Project withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public Project withPlan(String plan) {
		this.plan = plan;
		return this;
	}
	public List<Gs2Region> getRegions() {
		return regions;
	}
	public void setRegions(List<Gs2Region> regions) {
		this.regions = regions;
	}
	public Project withRegions(List<Gs2Region> regions) {
		this.regions = regions;
		return this;
	}
	public String getBillingMethodName() {
		return billingMethodName;
	}
	public void setBillingMethodName(String billingMethodName) {
		this.billingMethodName = billingMethodName;
	}
	public Project withBillingMethodName(String billingMethodName) {
		this.billingMethodName = billingMethodName;
		return this;
	}
	public String getEnableEventBridge() {
		return enableEventBridge;
	}
	public void setEnableEventBridge(String enableEventBridge) {
		this.enableEventBridge = enableEventBridge;
	}
	public Project withEnableEventBridge(String enableEventBridge) {
		this.enableEventBridge = enableEventBridge;
		return this;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Project withCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	public String getEventBridgeAwsAccountId() {
		return eventBridgeAwsAccountId;
	}
	public void setEventBridgeAwsAccountId(String eventBridgeAwsAccountId) {
		this.eventBridgeAwsAccountId = eventBridgeAwsAccountId;
	}
	public Project withEventBridgeAwsAccountId(String eventBridgeAwsAccountId) {
		this.eventBridgeAwsAccountId = eventBridgeAwsAccountId;
		return this;
	}
	public String getEventBridgeAwsRegion() {
		return eventBridgeAwsRegion;
	}
	public void setEventBridgeAwsRegion(String eventBridgeAwsRegion) {
		this.eventBridgeAwsRegion = eventBridgeAwsRegion;
	}
	public Project withEventBridgeAwsRegion(String eventBridgeAwsRegion) {
		this.eventBridgeAwsRegion = eventBridgeAwsRegion;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Project withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Project withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Project fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Project()
            .withProjectId(data.get("projectId") == null || data.get("projectId").isNull() ? null : data.get("projectId").asText())
            .withAccountName(data.get("accountName") == null || data.get("accountName").isNull() ? null : data.get("accountName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withPlan(data.get("plan") == null || data.get("plan").isNull() ? null : data.get("plan").asText())
            .withRegions(data.get("regions") == null || data.get("regions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("regions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Gs2Region.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withBillingMethodName(data.get("billingMethodName") == null || data.get("billingMethodName").isNull() ? null : data.get("billingMethodName").asText())
            .withEnableEventBridge(data.get("enableEventBridge") == null || data.get("enableEventBridge").isNull() ? null : data.get("enableEventBridge").asText())
            .withCurrency(data.get("currency") == null || data.get("currency").isNull() ? null : data.get("currency").asText())
            .withEventBridgeAwsAccountId(data.get("eventBridgeAwsAccountId") == null || data.get("eventBridgeAwsAccountId").isNull() ? null : data.get("eventBridgeAwsAccountId").asText())
            .withEventBridgeAwsRegion(data.get("eventBridgeAwsRegion") == null || data.get("eventBridgeAwsRegion").isNull() ? null : data.get("eventBridgeAwsRegion").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("projectId", getProjectId());
                put("accountName", getAccountName());
                put("name", getName());
                put("description", getDescription());
                put("plan", getPlan());
                put("regions", getRegions() == null ? null :
                    getRegions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("billingMethodName", getBillingMethodName());
                put("enableEventBridge", getEnableEventBridge());
                put("currency", getCurrency());
                put("eventBridgeAwsAccountId", getEventBridgeAwsAccountId());
                put("eventBridgeAwsRegion", getEventBridgeAwsRegion());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Project o) {
		return projectId.compareTo(o.projectId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.projectId == null) ? 0 : this.projectId.hashCode());
        result = prime * result + ((this.accountName == null) ? 0 : this.accountName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.plan == null) ? 0 : this.plan.hashCode());
        result = prime * result + ((this.regions == null) ? 0 : this.regions.hashCode());
        result = prime * result + ((this.billingMethodName == null) ? 0 : this.billingMethodName.hashCode());
        result = prime * result + ((this.enableEventBridge == null) ? 0 : this.enableEventBridge.hashCode());
        result = prime * result + ((this.currency == null) ? 0 : this.currency.hashCode());
        result = prime * result + ((this.eventBridgeAwsAccountId == null) ? 0 : this.eventBridgeAwsAccountId.hashCode());
        result = prime * result + ((this.eventBridgeAwsRegion == null) ? 0 : this.eventBridgeAwsRegion.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Project other = (Project) o;
		if (projectId == null) {
			return other.projectId == null;
		} else if (!projectId.equals(other.projectId)) {
			return false;
		}
		if (accountName == null) {
			return other.accountName == null;
		} else if (!accountName.equals(other.accountName)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (plan == null) {
			return other.plan == null;
		} else if (!plan.equals(other.plan)) {
			return false;
		}
		if (regions == null) {
			return other.regions == null;
		} else if (!regions.equals(other.regions)) {
			return false;
		}
		if (billingMethodName == null) {
			return other.billingMethodName == null;
		} else if (!billingMethodName.equals(other.billingMethodName)) {
			return false;
		}
		if (enableEventBridge == null) {
			return other.enableEventBridge == null;
		} else if (!enableEventBridge.equals(other.enableEventBridge)) {
			return false;
		}
		if (currency == null) {
			return other.currency == null;
		} else if (!currency.equals(other.currency)) {
			return false;
		}
		if (eventBridgeAwsAccountId == null) {
			return other.eventBridgeAwsAccountId == null;
		} else if (!eventBridgeAwsAccountId.equals(other.eventBridgeAwsAccountId)) {
			return false;
		}
		if (eventBridgeAwsRegion == null) {
			return other.eventBridgeAwsRegion == null;
		} else if (!eventBridgeAwsRegion.equals(other.eventBridgeAwsRegion)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}