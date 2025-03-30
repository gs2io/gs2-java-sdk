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
public class DepositTransaction implements IModel, Serializable {
	private Double price;
	private String currency;
	private Integer count;
	private Long depositedAt;
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public DepositTransaction withPrice(Double price) {
		this.price = price;
		return this;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public DepositTransaction withCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public DepositTransaction withCount(Integer count) {
		this.count = count;
		return this;
	}
	public Long getDepositedAt() {
		return depositedAt;
	}
	public void setDepositedAt(Long depositedAt) {
		this.depositedAt = depositedAt;
	}
	public DepositTransaction withDepositedAt(Long depositedAt) {
		this.depositedAt = depositedAt;
		return this;
	}

    public static DepositTransaction fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DepositTransaction()
            .withPrice(data.get("price") == null || data.get("price").isNull() ? null : data.get("price").doubleValue())
            .withCurrency(data.get("currency") == null || data.get("currency").isNull() ? null : data.get("currency").asText())
            .withCount(data.get("count") == null || data.get("count").isNull() ? null : data.get("count").intValue())
            .withDepositedAt(data.get("depositedAt") == null || data.get("depositedAt").isNull() ? null : data.get("depositedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("price", getPrice());
                put("currency", getCurrency());
                put("count", getCount());
                put("depositedAt", getDepositedAt());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.price == null) ? 0 : this.price.hashCode());
        result = prime * result + ((this.currency == null) ? 0 : this.currency.hashCode());
        result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
        result = prime * result + ((this.depositedAt == null) ? 0 : this.depositedAt.hashCode());
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
		DepositTransaction other = (DepositTransaction) o;
		if (price == null) {
			return other.price == null;
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (currency == null) {
			return other.currency == null;
		} else if (!currency.equals(other.currency)) {
			return false;
		}
		if (count == null) {
			return other.count == null;
		} else if (!count.equals(other.count)) {
			return false;
		}
		if (depositedAt == null) {
			return other.depositedAt == null;
		} else if (!depositedAt.equals(other.depositedAt)) {
			return false;
		}
		return true;
	}
}