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

package io.gs2.money2.model;

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
public class UnusedBalance implements IModel, Serializable, Comparable<UnusedBalance> {
	private String unusedBalanceId;
	private String currency;
	private Double balance;
	private Long updatedAt;
	private Long revision;
	public String getUnusedBalanceId() {
		return unusedBalanceId;
	}
	public void setUnusedBalanceId(String unusedBalanceId) {
		this.unusedBalanceId = unusedBalanceId;
	}
	public UnusedBalance withUnusedBalanceId(String unusedBalanceId) {
		this.unusedBalanceId = unusedBalanceId;
		return this;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public UnusedBalance withCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public UnusedBalance withBalance(Double balance) {
		this.balance = balance;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public UnusedBalance withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public UnusedBalance withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static UnusedBalance fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UnusedBalance()
            .withUnusedBalanceId(data.get("unusedBalanceId") == null || data.get("unusedBalanceId").isNull() ? null : data.get("unusedBalanceId").asText())
            .withCurrency(data.get("currency") == null || data.get("currency").isNull() ? null : data.get("currency").asText())
            .withBalance(data.get("balance") == null || data.get("balance").isNull() ? null : data.get("balance").doubleValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("unusedBalanceId", getUnusedBalanceId());
                put("currency", getCurrency());
                put("balance", getBalance());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(UnusedBalance o) {
		return unusedBalanceId.compareTo(o.unusedBalanceId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.unusedBalanceId == null) ? 0 : this.unusedBalanceId.hashCode());
        result = prime * result + ((this.currency == null) ? 0 : this.currency.hashCode());
        result = prime * result + ((this.balance == null) ? 0 : this.balance.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		UnusedBalance other = (UnusedBalance) o;
		if (unusedBalanceId == null) {
			return other.unusedBalanceId == null;
		} else if (!unusedBalanceId.equals(other.unusedBalanceId)) {
			return false;
		}
		if (currency == null) {
			return other.currency == null;
		} else if (!currency.equals(other.currency)) {
			return false;
		}
		if (balance == null) {
			return other.balance == null;
		} else if (!balance.equals(other.balance)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
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