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
public class Wallet implements IModel, Serializable, Comparable<Wallet> {
	private String walletId;
	private String userId;
	private Integer slot;
	private WalletSummary summary;
	private List<DepositTransaction> depositTransactions;
	private Boolean sharedFreeCurrency;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getWalletId() {
		return walletId;
	}
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	public Wallet withWalletId(String walletId) {
		this.walletId = walletId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Wallet withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getSlot() {
		return slot;
	}
	public void setSlot(Integer slot) {
		this.slot = slot;
	}
	public Wallet withSlot(Integer slot) {
		this.slot = slot;
		return this;
	}
	public WalletSummary getSummary() {
		return summary;
	}
	public void setSummary(WalletSummary summary) {
		this.summary = summary;
	}
	public Wallet withSummary(WalletSummary summary) {
		this.summary = summary;
		return this;
	}
	public List<DepositTransaction> getDepositTransactions() {
		return depositTransactions;
	}
	public void setDepositTransactions(List<DepositTransaction> depositTransactions) {
		this.depositTransactions = depositTransactions;
	}
	public Wallet withDepositTransactions(List<DepositTransaction> depositTransactions) {
		this.depositTransactions = depositTransactions;
		return this;
	}
	public Boolean getSharedFreeCurrency() {
		return sharedFreeCurrency;
	}
	public void setSharedFreeCurrency(Boolean sharedFreeCurrency) {
		this.sharedFreeCurrency = sharedFreeCurrency;
	}
	public Wallet withSharedFreeCurrency(Boolean sharedFreeCurrency) {
		this.sharedFreeCurrency = sharedFreeCurrency;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Wallet withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Wallet withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Wallet withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Wallet fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Wallet()
            .withWalletId(data.get("walletId") == null || data.get("walletId").isNull() ? null : data.get("walletId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withSlot(data.get("slot") == null || data.get("slot").isNull() ? null : data.get("slot").intValue())
            .withSummary(data.get("summary") == null || data.get("summary").isNull() ? null : WalletSummary.fromJson(data.get("summary")))
            .withDepositTransactions(data.get("depositTransactions") == null || data.get("depositTransactions").isNull() ? new ArrayList<DepositTransaction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("depositTransactions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return DepositTransaction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withSharedFreeCurrency(data.get("sharedFreeCurrency") == null || data.get("sharedFreeCurrency").isNull() ? null : data.get("sharedFreeCurrency").booleanValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("walletId", getWalletId());
                put("userId", getUserId());
                put("slot", getSlot());
                put("summary", getSummary() != null ? getSummary().toJson() : null);
                put("depositTransactions", getDepositTransactions() == null ? new ArrayList<DepositTransaction>() :
                    getDepositTransactions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("sharedFreeCurrency", getSharedFreeCurrency());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Wallet o) {
		return walletId.compareTo(o.walletId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.walletId == null) ? 0 : this.walletId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.slot == null) ? 0 : this.slot.hashCode());
        result = prime * result + ((this.summary == null) ? 0 : this.summary.hashCode());
        result = prime * result + ((this.depositTransactions == null) ? 0 : this.depositTransactions.hashCode());
        result = prime * result + ((this.sharedFreeCurrency == null) ? 0 : this.sharedFreeCurrency.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		Wallet other = (Wallet) o;
		if (walletId == null) {
			return other.walletId == null;
		} else if (!walletId.equals(other.walletId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (slot == null) {
			return other.slot == null;
		} else if (!slot.equals(other.slot)) {
			return false;
		}
		if (summary == null) {
			return other.summary == null;
		} else if (!summary.equals(other.summary)) {
			return false;
		}
		if (depositTransactions == null) {
			return other.depositTransactions == null;
		} else if (!depositTransactions.equals(other.depositTransactions)) {
			return false;
		}
		if (sharedFreeCurrency == null) {
			return other.sharedFreeCurrency == null;
		} else if (!sharedFreeCurrency.equals(other.sharedFreeCurrency)) {
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
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}