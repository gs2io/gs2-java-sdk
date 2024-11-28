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
public class DepositEvent implements IModel, Serializable {
	private Integer slot;
	private List<DepositTransaction> depositTransactions;
	private WalletSummary status;
	public Integer getSlot() {
		return slot;
	}
	public void setSlot(Integer slot) {
		this.slot = slot;
	}
	public DepositEvent withSlot(Integer slot) {
		this.slot = slot;
		return this;
	}
	public List<DepositTransaction> getDepositTransactions() {
		return depositTransactions;
	}
	public void setDepositTransactions(List<DepositTransaction> depositTransactions) {
		this.depositTransactions = depositTransactions;
	}
	public DepositEvent withDepositTransactions(List<DepositTransaction> depositTransactions) {
		this.depositTransactions = depositTransactions;
		return this;
	}
	public WalletSummary getStatus() {
		return status;
	}
	public void setStatus(WalletSummary status) {
		this.status = status;
	}
	public DepositEvent withStatus(WalletSummary status) {
		this.status = status;
		return this;
	}

    public static DepositEvent fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DepositEvent()
            .withSlot(data.get("slot") == null || data.get("slot").isNull() ? null : data.get("slot").intValue())
            .withDepositTransactions(data.get("depositTransactions") == null || data.get("depositTransactions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("depositTransactions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return DepositTransaction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : WalletSummary.fromJson(data.get("status")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("slot", getSlot());
                put("depositTransactions", getDepositTransactions() == null ? null :
                    getDepositTransactions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("status", getStatus() != null ? getStatus().toJson() : null);
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.slot == null) ? 0 : this.slot.hashCode());
        result = prime * result + ((this.depositTransactions == null) ? 0 : this.depositTransactions.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
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
		DepositEvent other = (DepositEvent) o;
		if (slot == null) {
			return other.slot == null;
		} else if (!slot.equals(other.slot)) {
			return false;
		}
		if (depositTransactions == null) {
			return other.depositTransactions == null;
		} else if (!depositTransactions.equals(other.depositTransactions)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}
}