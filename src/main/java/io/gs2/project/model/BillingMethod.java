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
public class BillingMethod implements IModel, Serializable, Comparable<BillingMethod> {
	private String billingMethodId;
	private String accountName;
	private String name;
	private String description;
	private String methodType;
	private String cardSignatureName;
	private String cardBrand;
	private String cardLast4;
	private String partnerId;
	private Long createdAt;
	private Long updatedAt;

	public String getBillingMethodId() {
		return billingMethodId;
	}

	public void setBillingMethodId(String billingMethodId) {
		this.billingMethodId = billingMethodId;
	}

	public BillingMethod withBillingMethodId(String billingMethodId) {
		this.billingMethodId = billingMethodId;
		return this;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BillingMethod withAccountName(String accountName) {
		this.accountName = accountName;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BillingMethod withName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BillingMethod withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public BillingMethod withMethodType(String methodType) {
		this.methodType = methodType;
		return this;
	}

	public String getCardSignatureName() {
		return cardSignatureName;
	}

	public void setCardSignatureName(String cardSignatureName) {
		this.cardSignatureName = cardSignatureName;
	}

	public BillingMethod withCardSignatureName(String cardSignatureName) {
		this.cardSignatureName = cardSignatureName;
		return this;
	}

	public String getCardBrand() {
		return cardBrand;
	}

	public void setCardBrand(String cardBrand) {
		this.cardBrand = cardBrand;
	}

	public BillingMethod withCardBrand(String cardBrand) {
		this.cardBrand = cardBrand;
		return this;
	}

	public String getCardLast4() {
		return cardLast4;
	}

	public void setCardLast4(String cardLast4) {
		this.cardLast4 = cardLast4;
	}

	public BillingMethod withCardLast4(String cardLast4) {
		this.cardLast4 = cardLast4;
		return this;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public BillingMethod withPartnerId(String partnerId) {
		this.partnerId = partnerId;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public BillingMethod withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public BillingMethod withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static BillingMethod fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BillingMethod()
            .withBillingMethodId(data.get("billingMethodId") == null || data.get("billingMethodId").isNull() ? null : data.get("billingMethodId").asText())
            .withAccountName(data.get("accountName") == null || data.get("accountName").isNull() ? null : data.get("accountName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMethodType(data.get("methodType") == null || data.get("methodType").isNull() ? null : data.get("methodType").asText())
            .withCardSignatureName(data.get("cardSignatureName") == null || data.get("cardSignatureName").isNull() ? null : data.get("cardSignatureName").asText())
            .withCardBrand(data.get("cardBrand") == null || data.get("cardBrand").isNull() ? null : data.get("cardBrand").asText())
            .withCardLast4(data.get("cardLast4") == null || data.get("cardLast4").isNull() ? null : data.get("cardLast4").asText())
            .withPartnerId(data.get("partnerId") == null || data.get("partnerId").isNull() ? null : data.get("partnerId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("billingMethodId", getBillingMethodId());
                put("accountName", getAccountName());
                put("name", getName());
                put("description", getDescription());
                put("methodType", getMethodType());
                put("cardSignatureName", getCardSignatureName());
                put("cardBrand", getCardBrand());
                put("cardLast4", getCardLast4());
                put("partnerId", getPartnerId());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(BillingMethod o) {
		return billingMethodId.compareTo(o.billingMethodId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.billingMethodId == null) ? 0 : this.billingMethodId.hashCode());
        result = prime * result + ((this.accountName == null) ? 0 : this.accountName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.methodType == null) ? 0 : this.methodType.hashCode());
        result = prime * result + ((this.cardSignatureName == null) ? 0 : this.cardSignatureName.hashCode());
        result = prime * result + ((this.cardBrand == null) ? 0 : this.cardBrand.hashCode());
        result = prime * result + ((this.cardLast4 == null) ? 0 : this.cardLast4.hashCode());
        result = prime * result + ((this.partnerId == null) ? 0 : this.partnerId.hashCode());
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
		BillingMethod other = (BillingMethod) o;
		if (billingMethodId == null) {
			return other.billingMethodId == null;
		} else if (!billingMethodId.equals(other.billingMethodId)) {
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
		if (methodType == null) {
			return other.methodType == null;
		} else if (!methodType.equals(other.methodType)) {
			return false;
		}
		if (cardSignatureName == null) {
			return other.cardSignatureName == null;
		} else if (!cardSignatureName.equals(other.cardSignatureName)) {
			return false;
		}
		if (cardBrand == null) {
			return other.cardBrand == null;
		} else if (!cardBrand.equals(other.cardBrand)) {
			return false;
		}
		if (cardLast4 == null) {
			return other.cardLast4 == null;
		} else if (!cardLast4.equals(other.cardLast4)) {
			return false;
		}
		if (partnerId == null) {
			return other.partnerId == null;
		} else if (!partnerId.equals(other.partnerId)) {
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