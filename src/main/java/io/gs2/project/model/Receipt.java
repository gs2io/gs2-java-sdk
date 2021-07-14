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

package io.gs2.project.model;

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
	private String accountName;
	private String name;
	private Long date;
	private String amount;
	private String pdfUrl;
	private Long createdAt;
	private Long updatedAt;

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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Receipt withAccountName(String accountName) {
		this.accountName = accountName;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Receipt withName(String name) {
		this.name = name;
		return this;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Receipt withDate(Long date) {
		this.date = date;
		return this;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Receipt withAmount(String amount) {
		this.amount = amount;
		return this;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public Receipt withPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
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

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Receipt withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Receipt fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Receipt()
            .withReceiptId(data.get("receiptId") == null || data.get("receiptId").isNull() ? null : data.get("receiptId").asText())
            .withAccountName(data.get("accountName") == null || data.get("accountName").isNull() ? null : data.get("accountName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDate(data.get("date") == null || data.get("date").isNull() ? null : data.get("date").longValue())
            .withAmount(data.get("amount") == null || data.get("amount").isNull() ? null : data.get("amount").asText())
            .withPdfUrl(data.get("pdfUrl") == null || data.get("pdfUrl").isNull() ? null : data.get("pdfUrl").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("receiptId", getReceiptId());
                put("accountName", getAccountName());
                put("name", getName());
                put("date", getDate());
                put("amount", getAmount());
                put("pdfUrl", getPdfUrl());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
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
        result = prime * result + ((this.accountName == null) ? 0 : this.accountName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.date == null) ? 0 : this.date.hashCode());
        result = prime * result + ((this.amount == null) ? 0 : this.amount.hashCode());
        result = prime * result + ((this.pdfUrl == null) ? 0 : this.pdfUrl.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		if (accountName == null) {
			return other.accountName == null;
		} else if (!accountName.equals(other.accountName)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (date == null) {
			return other.date == null;
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (amount == null) {
			return other.amount == null;
		} else if (!amount.equals(other.amount)) {
			return false;
		}
		if (pdfUrl == null) {
			return other.pdfUrl == null;
		} else if (!pdfUrl.equals(other.pdfUrl)) {
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
		return true;
	}
}