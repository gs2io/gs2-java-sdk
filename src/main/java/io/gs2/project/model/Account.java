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
public class Account implements IModel, Serializable, Comparable<Account> {
	private String accountId;
	private String name;
	private String email;
	private String fullName;
	private String companyName;
	private String enableTwoFactorAuthentication;
	private TwoFactorAuthenticationSetting twoFactorAuthenticationSetting;
	private String status;
	private Long createdAt;
	private Long updatedAt;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Account withAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Account withName(String name) {
		this.name = name;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Account withEmail(String email) {
		this.email = email;
		return this;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Account withFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Account withCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}
	public String getEnableTwoFactorAuthentication() {
		return enableTwoFactorAuthentication;
	}
	public void setEnableTwoFactorAuthentication(String enableTwoFactorAuthentication) {
		this.enableTwoFactorAuthentication = enableTwoFactorAuthentication;
	}
	public Account withEnableTwoFactorAuthentication(String enableTwoFactorAuthentication) {
		this.enableTwoFactorAuthentication = enableTwoFactorAuthentication;
		return this;
	}
	public TwoFactorAuthenticationSetting getTwoFactorAuthenticationSetting() {
		return twoFactorAuthenticationSetting;
	}
	public void setTwoFactorAuthenticationSetting(TwoFactorAuthenticationSetting twoFactorAuthenticationSetting) {
		this.twoFactorAuthenticationSetting = twoFactorAuthenticationSetting;
	}
	public Account withTwoFactorAuthenticationSetting(TwoFactorAuthenticationSetting twoFactorAuthenticationSetting) {
		this.twoFactorAuthenticationSetting = twoFactorAuthenticationSetting;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Account withStatus(String status) {
		this.status = status;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Account withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Account withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Account fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Account()
            .withAccountId(data.get("accountId") == null || data.get("accountId").isNull() ? null : data.get("accountId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withEmail(data.get("email") == null || data.get("email").isNull() ? null : data.get("email").asText())
            .withFullName(data.get("fullName") == null || data.get("fullName").isNull() ? null : data.get("fullName").asText())
            .withCompanyName(data.get("companyName") == null || data.get("companyName").isNull() ? null : data.get("companyName").asText())
            .withEnableTwoFactorAuthentication(data.get("enableTwoFactorAuthentication") == null || data.get("enableTwoFactorAuthentication").isNull() ? null : data.get("enableTwoFactorAuthentication").asText())
            .withTwoFactorAuthenticationSetting(data.get("twoFactorAuthenticationSetting") == null || data.get("twoFactorAuthenticationSetting").isNull() ? null : TwoFactorAuthenticationSetting.fromJson(data.get("twoFactorAuthenticationSetting")))
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : data.get("status").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("accountId", getAccountId());
                put("name", getName());
                put("email", getEmail());
                put("fullName", getFullName());
                put("companyName", getCompanyName());
                put("enableTwoFactorAuthentication", getEnableTwoFactorAuthentication());
                put("twoFactorAuthenticationSetting", getTwoFactorAuthenticationSetting() != null ? getTwoFactorAuthenticationSetting().toJson() : null);
                put("status", getStatus());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Account o) {
		return accountId.compareTo(o.accountId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.accountId == null) ? 0 : this.accountId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.fullName == null) ? 0 : this.fullName.hashCode());
        result = prime * result + ((this.companyName == null) ? 0 : this.companyName.hashCode());
        result = prime * result + ((this.enableTwoFactorAuthentication == null) ? 0 : this.enableTwoFactorAuthentication.hashCode());
        result = prime * result + ((this.twoFactorAuthenticationSetting == null) ? 0 : this.twoFactorAuthenticationSetting.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
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
		Account other = (Account) o;
		if (accountId == null) {
			return other.accountId == null;
		} else if (!accountId.equals(other.accountId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (email == null) {
			return other.email == null;
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (fullName == null) {
			return other.fullName == null;
		} else if (!fullName.equals(other.fullName)) {
			return false;
		}
		if (companyName == null) {
			return other.companyName == null;
		} else if (!companyName.equals(other.companyName)) {
			return false;
		}
		if (enableTwoFactorAuthentication == null) {
			return other.enableTwoFactorAuthentication == null;
		} else if (!enableTwoFactorAuthentication.equals(other.enableTwoFactorAuthentication)) {
			return false;
		}
		if (twoFactorAuthenticationSetting == null) {
			return other.twoFactorAuthenticationSetting == null;
		} else if (!twoFactorAuthenticationSetting.equals(other.twoFactorAuthenticationSetting)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
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