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
public class DailyTransactionHistory implements IModel, Serializable, Comparable<DailyTransactionHistory> {
	private String dailyTransactionHistoryId;
	private Integer year;
	private Integer month;
	private Integer day;
	private String currency;
	private Double depositAmount;
	private Double withdrawAmount;
	private Long issueCount;
	private Long consumeCount;
	private Long updatedAt;
	private Long revision;
	public String getDailyTransactionHistoryId() {
		return dailyTransactionHistoryId;
	}
	public void setDailyTransactionHistoryId(String dailyTransactionHistoryId) {
		this.dailyTransactionHistoryId = dailyTransactionHistoryId;
	}
	public DailyTransactionHistory withDailyTransactionHistoryId(String dailyTransactionHistoryId) {
		this.dailyTransactionHistoryId = dailyTransactionHistoryId;
		return this;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public DailyTransactionHistory withYear(Integer year) {
		this.year = year;
		return this;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public DailyTransactionHistory withMonth(Integer month) {
		this.month = month;
		return this;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public DailyTransactionHistory withDay(Integer day) {
		this.day = day;
		return this;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public DailyTransactionHistory withCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	public Double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public DailyTransactionHistory withDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
		return this;
	}
	public Double getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(Double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	public DailyTransactionHistory withWithdrawAmount(Double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
		return this;
	}
	public Long getIssueCount() {
		return issueCount;
	}
	public void setIssueCount(Long issueCount) {
		this.issueCount = issueCount;
	}
	public DailyTransactionHistory withIssueCount(Long issueCount) {
		this.issueCount = issueCount;
		return this;
	}
	public Long getConsumeCount() {
		return consumeCount;
	}
	public void setConsumeCount(Long consumeCount) {
		this.consumeCount = consumeCount;
	}
	public DailyTransactionHistory withConsumeCount(Long consumeCount) {
		this.consumeCount = consumeCount;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public DailyTransactionHistory withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public DailyTransactionHistory withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static DailyTransactionHistory fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DailyTransactionHistory()
            .withDailyTransactionHistoryId(data.get("dailyTransactionHistoryId") == null || data.get("dailyTransactionHistoryId").isNull() ? null : data.get("dailyTransactionHistoryId").asText())
            .withYear(data.get("year") == null || data.get("year").isNull() ? null : data.get("year").intValue())
            .withMonth(data.get("month") == null || data.get("month").isNull() ? null : data.get("month").intValue())
            .withDay(data.get("day") == null || data.get("day").isNull() ? null : data.get("day").intValue())
            .withCurrency(data.get("currency") == null || data.get("currency").isNull() ? null : data.get("currency").asText())
            .withDepositAmount(data.get("depositAmount") == null || data.get("depositAmount").isNull() ? null : data.get("depositAmount").doubleValue())
            .withWithdrawAmount(data.get("withdrawAmount") == null || data.get("withdrawAmount").isNull() ? null : data.get("withdrawAmount").doubleValue())
            .withIssueCount(data.get("issueCount") == null || data.get("issueCount").isNull() ? null : data.get("issueCount").longValue())
            .withConsumeCount(data.get("consumeCount") == null || data.get("consumeCount").isNull() ? null : data.get("consumeCount").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("dailyTransactionHistoryId", getDailyTransactionHistoryId());
                put("year", getYear());
                put("month", getMonth());
                put("day", getDay());
                put("currency", getCurrency());
                put("depositAmount", getDepositAmount());
                put("withdrawAmount", getWithdrawAmount());
                put("issueCount", getIssueCount());
                put("consumeCount", getConsumeCount());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(DailyTransactionHistory o) {
		return dailyTransactionHistoryId.compareTo(o.dailyTransactionHistoryId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.dailyTransactionHistoryId == null) ? 0 : this.dailyTransactionHistoryId.hashCode());
        result = prime * result + ((this.year == null) ? 0 : this.year.hashCode());
        result = prime * result + ((this.month == null) ? 0 : this.month.hashCode());
        result = prime * result + ((this.day == null) ? 0 : this.day.hashCode());
        result = prime * result + ((this.currency == null) ? 0 : this.currency.hashCode());
        result = prime * result + ((this.depositAmount == null) ? 0 : this.depositAmount.hashCode());
        result = prime * result + ((this.withdrawAmount == null) ? 0 : this.withdrawAmount.hashCode());
        result = prime * result + ((this.issueCount == null) ? 0 : this.issueCount.hashCode());
        result = prime * result + ((this.consumeCount == null) ? 0 : this.consumeCount.hashCode());
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
		DailyTransactionHistory other = (DailyTransactionHistory) o;
		if (dailyTransactionHistoryId == null) {
			return other.dailyTransactionHistoryId == null;
		} else if (!dailyTransactionHistoryId.equals(other.dailyTransactionHistoryId)) {
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
		if (currency == null) {
			return other.currency == null;
		} else if (!currency.equals(other.currency)) {
			return false;
		}
		if (depositAmount == null) {
			return other.depositAmount == null;
		} else if (!depositAmount.equals(other.depositAmount)) {
			return false;
		}
		if (withdrawAmount == null) {
			return other.withdrawAmount == null;
		} else if (!withdrawAmount.equals(other.withdrawAmount)) {
			return false;
		}
		if (issueCount == null) {
			return other.issueCount == null;
		} else if (!issueCount.equals(other.issueCount)) {
			return false;
		}
		if (consumeCount == null) {
			return other.consumeCount == null;
		} else if (!consumeCount.equals(other.consumeCount)) {
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