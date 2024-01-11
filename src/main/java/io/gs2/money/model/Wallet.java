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

package io.gs2.money.model;

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
	private Integer paid;
	private Integer free;
	private List<WalletDetail> detail;
	private Boolean shareFree;
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
	public Integer getPaid() {
		return paid;
	}
	public void setPaid(Integer paid) {
		this.paid = paid;
	}
	public Wallet withPaid(Integer paid) {
		this.paid = paid;
		return this;
	}
	public Integer getFree() {
		return free;
	}
	public void setFree(Integer free) {
		this.free = free;
	}
	public Wallet withFree(Integer free) {
		this.free = free;
		return this;
	}
	public List<WalletDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<WalletDetail> detail) {
		this.detail = detail;
	}
	public Wallet withDetail(List<WalletDetail> detail) {
		this.detail = detail;
		return this;
	}
	public Boolean getShareFree() {
		return shareFree;
	}
	public void setShareFree(Boolean shareFree) {
		this.shareFree = shareFree;
	}
	public Wallet withShareFree(Boolean shareFree) {
		this.shareFree = shareFree;
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
            .withPaid(data.get("paid") == null || data.get("paid").isNull() ? null : data.get("paid").intValue())
            .withFree(data.get("free") == null || data.get("free").isNull() ? null : data.get("free").intValue())
            .withDetail(data.get("detail") == null || data.get("detail").isNull() ? new ArrayList<WalletDetail>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("detail").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return WalletDetail.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withShareFree(data.get("shareFree") == null || data.get("shareFree").isNull() ? null : data.get("shareFree").booleanValue())
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
                put("paid", getPaid());
                put("free", getFree());
                put("detail", getDetail() == null ? new ArrayList<WalletDetail>() :
                    getDetail().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("shareFree", getShareFree());
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
        result = prime * result + ((this.paid == null) ? 0 : this.paid.hashCode());
        result = prime * result + ((this.free == null) ? 0 : this.free.hashCode());
        result = prime * result + ((this.detail == null) ? 0 : this.detail.hashCode());
        result = prime * result + ((this.shareFree == null) ? 0 : this.shareFree.hashCode());
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
		if (paid == null) {
			return other.paid == null;
		} else if (!paid.equals(other.paid)) {
			return false;
		}
		if (free == null) {
			return other.free == null;
		} else if (!free.equals(other.free)) {
			return false;
		}
		if (detail == null) {
			return other.detail == null;
		} else if (!detail.equals(other.detail)) {
			return false;
		}
		if (shareFree == null) {
			return other.shareFree == null;
		} else if (!shareFree.equals(other.shareFree)) {
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