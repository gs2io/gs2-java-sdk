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
public class WalletSummary implements IModel, Serializable {
	private Integer paid;
	private Integer free;
	private Integer total;
	public Integer getPaid() {
		return paid;
	}
	public void setPaid(Integer paid) {
		this.paid = paid;
	}
	public WalletSummary withPaid(Integer paid) {
		this.paid = paid;
		return this;
	}
	public Integer getFree() {
		return free;
	}
	public void setFree(Integer free) {
		this.free = free;
	}
	public WalletSummary withFree(Integer free) {
		this.free = free;
		return this;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public WalletSummary withTotal(Integer total) {
		this.total = total;
		return this;
	}

    public static WalletSummary fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new WalletSummary()
            .withPaid(data.get("paid") == null || data.get("paid").isNull() ? null : data.get("paid").intValue())
            .withFree(data.get("free") == null || data.get("free").isNull() ? null : data.get("free").intValue())
            .withTotal(data.get("total") == null || data.get("total").isNull() ? null : data.get("total").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("paid", getPaid());
                put("free", getFree());
                put("total", getTotal());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.paid == null) ? 0 : this.paid.hashCode());
        result = prime * result + ((this.free == null) ? 0 : this.free.hashCode());
        result = prime * result + ((this.total == null) ? 0 : this.total.hashCode());
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
		WalletSummary other = (WalletSummary) o;
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
		return true;
	}
}