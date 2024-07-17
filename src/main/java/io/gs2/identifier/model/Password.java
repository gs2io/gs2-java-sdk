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

package io.gs2.identifier.model;

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
public class Password implements IModel, Serializable, Comparable<Password> {
	private String passwordId;
	private String userId;
	private String userName;
	private String enableTwoFactorAuthentication;
	private TwoFactorAuthenticationSetting twoFactorAuthenticationSetting;
	private Long createdAt;
	private Long revision;
	public String getPasswordId() {
		return passwordId;
	}
	public void setPasswordId(String passwordId) {
		this.passwordId = passwordId;
	}
	public Password withPasswordId(String passwordId) {
		this.passwordId = passwordId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Password withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Password withUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getEnableTwoFactorAuthentication() {
		return enableTwoFactorAuthentication;
	}
	public void setEnableTwoFactorAuthentication(String enableTwoFactorAuthentication) {
		this.enableTwoFactorAuthentication = enableTwoFactorAuthentication;
	}
	public Password withEnableTwoFactorAuthentication(String enableTwoFactorAuthentication) {
		this.enableTwoFactorAuthentication = enableTwoFactorAuthentication;
		return this;
	}
	public TwoFactorAuthenticationSetting getTwoFactorAuthenticationSetting() {
		return twoFactorAuthenticationSetting;
	}
	public void setTwoFactorAuthenticationSetting(TwoFactorAuthenticationSetting twoFactorAuthenticationSetting) {
		this.twoFactorAuthenticationSetting = twoFactorAuthenticationSetting;
	}
	public Password withTwoFactorAuthenticationSetting(TwoFactorAuthenticationSetting twoFactorAuthenticationSetting) {
		this.twoFactorAuthenticationSetting = twoFactorAuthenticationSetting;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Password withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Password withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Password fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Password()
            .withPasswordId(data.get("passwordId") == null || data.get("passwordId").isNull() ? null : data.get("passwordId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withUserName(data.get("userName") == null || data.get("userName").isNull() ? null : data.get("userName").asText())
            .withEnableTwoFactorAuthentication(data.get("enableTwoFactorAuthentication") == null || data.get("enableTwoFactorAuthentication").isNull() ? null : data.get("enableTwoFactorAuthentication").asText())
            .withTwoFactorAuthenticationSetting(data.get("twoFactorAuthenticationSetting") == null || data.get("twoFactorAuthenticationSetting").isNull() ? null : TwoFactorAuthenticationSetting.fromJson(data.get("twoFactorAuthenticationSetting")))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("passwordId", getPasswordId());
                put("userId", getUserId());
                put("userName", getUserName());
                put("enableTwoFactorAuthentication", getEnableTwoFactorAuthentication());
                put("twoFactorAuthenticationSetting", getTwoFactorAuthenticationSetting() != null ? getTwoFactorAuthenticationSetting().toJson() : null);
                put("createdAt", getCreatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Password o) {
		return passwordId.compareTo(o.passwordId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.passwordId == null) ? 0 : this.passwordId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.userName == null) ? 0 : this.userName.hashCode());
        result = prime * result + ((this.enableTwoFactorAuthentication == null) ? 0 : this.enableTwoFactorAuthentication.hashCode());
        result = prime * result + ((this.twoFactorAuthenticationSetting == null) ? 0 : this.twoFactorAuthenticationSetting.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.revision == null) ? 0 : this.revision.hashCode());
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
		Password other = (Password) o;
		if (passwordId == null) {
			return other.passwordId == null;
		} else if (!passwordId.equals(other.passwordId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (userName == null) {
			return other.userName == null;
		} else if (!userName.equals(other.userName)) {
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
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}