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
public class IssuedCodeSetting implements IModel, Serializable {
	private Integer expiresDays;
	private Integer maxFailedAttempts;
	private Boolean deleteOnUse;
	public Integer getExpiresDays() {
		return expiresDays;
	}
	public void setExpiresDays(Integer expiresDays) {
		this.expiresDays = expiresDays;
	}
	public IssuedCodeSetting withExpiresDays(Integer expiresDays) {
		this.expiresDays = expiresDays;
		return this;
	}
	public Integer getMaxFailedAttempts() {
		return maxFailedAttempts;
	}
	public void setMaxFailedAttempts(Integer maxFailedAttempts) {
		this.maxFailedAttempts = maxFailedAttempts;
	}
	public IssuedCodeSetting withMaxFailedAttempts(Integer maxFailedAttempts) {
		this.maxFailedAttempts = maxFailedAttempts;
		return this;
	}
	public Boolean getDeleteOnUse() {
		return deleteOnUse;
	}
	public void setDeleteOnUse(Boolean deleteOnUse) {
		this.deleteOnUse = deleteOnUse;
	}
	public IssuedCodeSetting withDeleteOnUse(Boolean deleteOnUse) {
		this.deleteOnUse = deleteOnUse;
		return this;
	}

    public static IssuedCodeSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new IssuedCodeSetting()
            .withExpiresDays(data.get("expiresDays") == null || data.get("expiresDays").isNull() ? null : data.get("expiresDays").intValue())
            .withMaxFailedAttempts(data.get("maxFailedAttempts") == null || data.get("maxFailedAttempts").isNull() ? null : data.get("maxFailedAttempts").intValue())
            .withDeleteOnUse(data.get("deleteOnUse") == null || data.get("deleteOnUse").isNull() ? null : data.get("deleteOnUse").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("expiresDays", getExpiresDays());
                put("maxFailedAttempts", getMaxFailedAttempts());
                put("deleteOnUse", getDeleteOnUse());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.expiresDays == null) ? 0 : this.expiresDays.hashCode());
        result = prime * result + ((this.maxFailedAttempts == null) ? 0 : this.maxFailedAttempts.hashCode());
        result = prime * result + ((this.deleteOnUse == null) ? 0 : this.deleteOnUse.hashCode());
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
		IssuedCodeSetting other = (IssuedCodeSetting) o;
		if (expiresDays == null) {
			return other.expiresDays == null;
		} else if (!expiresDays.equals(other.expiresDays)) {
			return false;
		}
		if (maxFailedAttempts == null) {
			return other.maxFailedAttempts == null;
		} else if (!maxFailedAttempts.equals(other.maxFailedAttempts)) {
			return false;
		}
		if (deleteOnUse == null) {
			return other.deleteOnUse == null;
		} else if (!deleteOnUse.equals(other.deleteOnUse)) {
			return false;
		}
		return true;
	}
}