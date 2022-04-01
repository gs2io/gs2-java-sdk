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
public class Receipt implements IModel, Serializable, Comparable<Receipt> {
	private String receiptId;
	private String transactionId;
	private String purchaseToken;
	private String userId;
	private String type;
	private Integer slot;
	private Float price;
	private Integer paid;
	private Integer free;
	private Integer total;
	private String contentsId;
	private Long createdAt;

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public Receipt withReceiptId(String receiptId) {
		this.receiptId = receiptId;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Receipt withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public String getPurchaseToken() {
		return purchaseToken;
	}

	public void setPurchaseToken(String purchaseToken) {
		this.purchaseToken = purchaseToken;
	}

	public Receipt withPurchaseToken(String purchaseToken) {
		this.purchaseToken = purchaseToken;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Receipt withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Receipt withType(String type) {
		this.type = type;
		return this;
	}

	public Integer getSlot() {
		return slot;
	}

	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	public Receipt withSlot(Integer slot) {
		this.slot = slot;
		return this;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Receipt withPrice(Float price) {
		this.price = price;
		return this;
	}

	public Integer getPaid() {
		return paid;
	}

	public void setPaid(Integer paid) {
		this.paid = paid;
	}

	public Receipt withPaid(Integer paid) {
		this.paid = paid;
		return this;
	}

	public Integer getFree() {
		return free;
	}

	public void setFree(Integer free) {
		this.free = free;
	}

	public Receipt withFree(Integer free) {
		this.free = free;
		return this;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Receipt withTotal(Integer total) {
		this.total = total;
		return this;
	}

	public String getContentsId() {
		return contentsId;
	}

	public void setContentsId(String contentsId) {
		this.contentsId = contentsId;
	}

	public Receipt withContentsId(String contentsId) {
		this.contentsId = contentsId;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Receipt withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static Receipt fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Receipt()
            .withReceiptId(data.get("receiptId") == null || data.get("receiptId").isNull() ? null : data.get("receiptId").asText())
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withPurchaseToken(data.get("purchaseToken") == null || data.get("purchaseToken").isNull() ? null : data.get("purchaseToken").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withSlot(data.get("slot") == null || data.get("slot").isNull() ? null : data.get("slot").intValue())
            .withPrice(data.get("price") == null || data.get("price").isNull() ? null : data.get("price").floatValue())
            .withPaid(data.get("paid") == null || data.get("paid").isNull() ? null : data.get("paid").intValue())
            .withFree(data.get("free") == null || data.get("free").isNull() ? null : data.get("free").intValue())
            .withTotal(data.get("total") == null || data.get("total").isNull() ? null : data.get("total").intValue())
            .withContentsId(data.get("contentsId") == null || data.get("contentsId").isNull() ? null : data.get("contentsId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("receiptId", getReceiptId());
                put("transactionId", getTransactionId());
                put("purchaseToken", getPurchaseToken());
                put("userId", getUserId());
                put("type", getType());
                put("slot", getSlot());
                put("price", getPrice());
                put("paid", getPaid());
                put("free", getFree());
                put("total", getTotal());
                put("contentsId", getContentsId());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Receipt o) {
		return receiptId.compareTo(o.receiptId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.receiptId == null) ? 0 : this.receiptId.hashCode());
        result = prime * result + ((this.transactionId == null) ? 0 : this.transactionId.hashCode());
        result = prime * result + ((this.purchaseToken == null) ? 0 : this.purchaseToken.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.slot == null) ? 0 : this.slot.hashCode());
        result = prime * result + ((this.price == null) ? 0 : this.price.hashCode());
        result = prime * result + ((this.paid == null) ? 0 : this.paid.hashCode());
        result = prime * result + ((this.free == null) ? 0 : this.free.hashCode());
        result = prime * result + ((this.total == null) ? 0 : this.total.hashCode());
        result = prime * result + ((this.contentsId == null) ? 0 : this.contentsId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		Receipt other = (Receipt) o;
		if (receiptId == null) {
			return other.receiptId == null;
		} else if (!receiptId.equals(other.receiptId)) {
			return false;
		}
		if (transactionId == null) {
			return other.transactionId == null;
		} else if (!transactionId.equals(other.transactionId)) {
			return false;
		}
		if (purchaseToken == null) {
			return other.purchaseToken == null;
		} else if (!purchaseToken.equals(other.purchaseToken)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (slot == null) {
			return other.slot == null;
		} else if (!slot.equals(other.slot)) {
			return false;
		}
		if (price == null) {
			return other.price == null;
		} else if (!price.equals(other.price)) {
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
		if (total == null) {
			return other.total == null;
		} else if (!total.equals(other.total)) {
			return false;
		}
		if (contentsId == null) {
			return other.contentsId == null;
		} else if (!contentsId.equals(other.contentsId)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		return true;
	}
}