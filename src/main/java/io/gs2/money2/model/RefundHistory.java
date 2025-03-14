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
public class RefundHistory implements IModel, Serializable, Comparable<RefundHistory> {
	private String refundHistoryId;
	private String transactionId;
	private Integer year;
	private Integer month;
	private Integer day;
	private String userId;
	private RefundEvent detail;
	private Long createdAt;
	public String getRefundHistoryId() {
		return refundHistoryId;
	}
	public void setRefundHistoryId(String refundHistoryId) {
		this.refundHistoryId = refundHistoryId;
	}
	public RefundHistory withRefundHistoryId(String refundHistoryId) {
		this.refundHistoryId = refundHistoryId;
		return this;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public RefundHistory withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public RefundHistory withYear(Integer year) {
		this.year = year;
		return this;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public RefundHistory withMonth(Integer month) {
		this.month = month;
		return this;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public RefundHistory withDay(Integer day) {
		this.day = day;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public RefundHistory withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public RefundEvent getDetail() {
		return detail;
	}
	public void setDetail(RefundEvent detail) {
		this.detail = detail;
	}
	public RefundHistory withDetail(RefundEvent detail) {
		this.detail = detail;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public RefundHistory withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static RefundHistory fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RefundHistory()
            .withRefundHistoryId(data.get("refundHistoryId") == null || data.get("refundHistoryId").isNull() ? null : data.get("refundHistoryId").asText())
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withYear(data.get("year") == null || data.get("year").isNull() ? null : data.get("year").intValue())
            .withMonth(data.get("month") == null || data.get("month").isNull() ? null : data.get("month").intValue())
            .withDay(data.get("day") == null || data.get("day").isNull() ? null : data.get("day").intValue())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withDetail(data.get("detail") == null || data.get("detail").isNull() ? null : RefundEvent.fromJson(data.get("detail")))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("refundHistoryId", getRefundHistoryId());
                put("transactionId", getTransactionId());
                put("year", getYear());
                put("month", getMonth());
                put("day", getDay());
                put("userId", getUserId());
                put("detail", getDetail() != null ? getDetail().toJson() : null);
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(RefundHistory o) {
		return refundHistoryId.compareTo(o.refundHistoryId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.refundHistoryId == null) ? 0 : this.refundHistoryId.hashCode());
        result = prime * result + ((this.transactionId == null) ? 0 : this.transactionId.hashCode());
        result = prime * result + ((this.year == null) ? 0 : this.year.hashCode());
        result = prime * result + ((this.month == null) ? 0 : this.month.hashCode());
        result = prime * result + ((this.day == null) ? 0 : this.day.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.detail == null) ? 0 : this.detail.hashCode());
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
		RefundHistory other = (RefundHistory) o;
		if (refundHistoryId == null) {
			return other.refundHistoryId == null;
		} else if (!refundHistoryId.equals(other.refundHistoryId)) {
			return false;
		}
		if (transactionId == null) {
			return other.transactionId == null;
		} else if (!transactionId.equals(other.transactionId)) {
			return false;
		}
		if (year == null) {
			return other.year == null;
		} else if (!year.equals(other.year)) {
			return false;
		}
		if (month == null) {
			return other.month == null;
		} else if (!month.equals(other.month)) {
			return false;
		}
		if (day == null) {
			return other.day == null;
		} else if (!day.equals(other.day)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (detail == null) {
			return other.detail == null;
		} else if (!detail.equals(other.detail)) {
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