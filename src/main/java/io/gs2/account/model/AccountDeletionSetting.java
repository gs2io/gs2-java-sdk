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

package io.gs2.account.model;

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
public class AccountDeletionSetting implements IModel, Serializable {
	private Integer gracePeriodMinutes;
	private String loginDuringGracePeriod;
	private String cleanUserDataScope;
	public Integer getGracePeriodMinutes() {
		return gracePeriodMinutes;
	}
	public void setGracePeriodMinutes(Integer gracePeriodMinutes) {
		this.gracePeriodMinutes = gracePeriodMinutes;
	}
	public AccountDeletionSetting withGracePeriodMinutes(Integer gracePeriodMinutes) {
		this.gracePeriodMinutes = gracePeriodMinutes;
		return this;
	}
	public String getLoginDuringGracePeriod() {
		return loginDuringGracePeriod;
	}
	public void setLoginDuringGracePeriod(String loginDuringGracePeriod) {
		this.loginDuringGracePeriod = loginDuringGracePeriod;
	}
	public AccountDeletionSetting withLoginDuringGracePeriod(String loginDuringGracePeriod) {
		this.loginDuringGracePeriod = loginDuringGracePeriod;
		return this;
	}
	public String getCleanUserDataScope() {
		return cleanUserDataScope;
	}
	public void setCleanUserDataScope(String cleanUserDataScope) {
		this.cleanUserDataScope = cleanUserDataScope;
	}
	public AccountDeletionSetting withCleanUserDataScope(String cleanUserDataScope) {
		this.cleanUserDataScope = cleanUserDataScope;
		return this;
	}

    public static AccountDeletionSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AccountDeletionSetting()
            .withGracePeriodMinutes(data.get("gracePeriodMinutes") == null || data.get("gracePeriodMinutes").isNull() ? null : data.get("gracePeriodMinutes").intValue())
            .withLoginDuringGracePeriod(data.get("loginDuringGracePeriod") == null || data.get("loginDuringGracePeriod").isNull() ? null : data.get("loginDuringGracePeriod").asText())
            .withCleanUserDataScope(data.get("cleanUserDataScope") == null || data.get("cleanUserDataScope").isNull() ? null : data.get("cleanUserDataScope").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("gracePeriodMinutes", getGracePeriodMinutes());
                put("loginDuringGracePeriod", getLoginDuringGracePeriod());
                put("cleanUserDataScope", getCleanUserDataScope());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.gracePeriodMinutes == null) ? 0 : this.gracePeriodMinutes.hashCode());
        result = prime * result + ((this.loginDuringGracePeriod == null) ? 0 : this.loginDuringGracePeriod.hashCode());
        result = prime * result + ((this.cleanUserDataScope == null) ? 0 : this.cleanUserDataScope.hashCode());
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
		AccountDeletionSetting other = (AccountDeletionSetting) o;
		if (gracePeriodMinutes == null) {
			return other.gracePeriodMinutes == null;
		} else if (!gracePeriodMinutes.equals(other.gracePeriodMinutes)) {
			return false;
		}
		if (loginDuringGracePeriod == null) {
			return other.loginDuringGracePeriod == null;
		} else if (!loginDuringGracePeriod.equals(other.loginDuringGracePeriod)) {
			return false;
		}
		if (cleanUserDataScope == null) {
			return other.cleanUserDataScope == null;
		} else if (!cleanUserDataScope.equals(other.cleanUserDataScope)) {
			return false;
		}
		return true;
	}
}