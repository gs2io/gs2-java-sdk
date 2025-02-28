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
public class Event implements IModel, Serializable, Comparable<Event> {
	private String eventId;
	private String transactionId;
	private String userId;
	private String eventType;
	private VerifyReceiptEvent verifyReceiptEvent;
	private DepositEvent depositEvent;
	private WithdrawEvent withdrawEvent;
	private RefundEvent refundEvent;
	private Long createdAt;
	private Long revision;
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public Event withEventId(String eventId) {
		this.eventId = eventId;
		return this;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Event withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Event withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public Event withEventType(String eventType) {
		this.eventType = eventType;
		return this;
	}
	public VerifyReceiptEvent getVerifyReceiptEvent() {
		return verifyReceiptEvent;
	}
	public void setVerifyReceiptEvent(VerifyReceiptEvent verifyReceiptEvent) {
		this.verifyReceiptEvent = verifyReceiptEvent;
	}
	public Event withVerifyReceiptEvent(VerifyReceiptEvent verifyReceiptEvent) {
		this.verifyReceiptEvent = verifyReceiptEvent;
		return this;
	}
	public DepositEvent getDepositEvent() {
		return depositEvent;
	}
	public void setDepositEvent(DepositEvent depositEvent) {
		this.depositEvent = depositEvent;
	}
	public Event withDepositEvent(DepositEvent depositEvent) {
		this.depositEvent = depositEvent;
		return this;
	}
	public WithdrawEvent getWithdrawEvent() {
		return withdrawEvent;
	}
	public void setWithdrawEvent(WithdrawEvent withdrawEvent) {
		this.withdrawEvent = withdrawEvent;
	}
	public Event withWithdrawEvent(WithdrawEvent withdrawEvent) {
		this.withdrawEvent = withdrawEvent;
		return this;
	}
	public RefundEvent getRefundEvent() {
		return refundEvent;
	}
	public void setRefundEvent(RefundEvent refundEvent) {
		this.refundEvent = refundEvent;
	}
	public Event withRefundEvent(RefundEvent refundEvent) {
		this.refundEvent = refundEvent;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Event withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Event withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Event fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Event()
            .withEventId(data.get("eventId") == null || data.get("eventId").isNull() ? null : data.get("eventId").asText())
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withEventType(data.get("eventType") == null || data.get("eventType").isNull() ? null : data.get("eventType").asText())
            .withVerifyReceiptEvent(data.get("verifyReceiptEvent") == null || data.get("verifyReceiptEvent").isNull() ? null : VerifyReceiptEvent.fromJson(data.get("verifyReceiptEvent")))
            .withDepositEvent(data.get("depositEvent") == null || data.get("depositEvent").isNull() ? null : DepositEvent.fromJson(data.get("depositEvent")))
            .withWithdrawEvent(data.get("withdrawEvent") == null || data.get("withdrawEvent").isNull() ? null : WithdrawEvent.fromJson(data.get("withdrawEvent")))
            .withRefundEvent(data.get("refundEvent") == null || data.get("refundEvent").isNull() ? null : RefundEvent.fromJson(data.get("refundEvent")))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("eventId", getEventId());
                put("transactionId", getTransactionId());
                put("userId", getUserId());
                put("eventType", getEventType());
                put("verifyReceiptEvent", getVerifyReceiptEvent() != null ? getVerifyReceiptEvent().toJson() : null);
                put("depositEvent", getDepositEvent() != null ? getDepositEvent().toJson() : null);
                put("withdrawEvent", getWithdrawEvent() != null ? getWithdrawEvent().toJson() : null);
                put("refundEvent", getRefundEvent() != null ? getRefundEvent().toJson() : null);
                put("createdAt", getCreatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Event o) {
		return eventId.compareTo(o.eventId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.eventId == null) ? 0 : this.eventId.hashCode());
        result = prime * result + ((this.transactionId == null) ? 0 : this.transactionId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.eventType == null) ? 0 : this.eventType.hashCode());
        result = prime * result + ((this.verifyReceiptEvent == null) ? 0 : this.verifyReceiptEvent.hashCode());
        result = prime * result + ((this.depositEvent == null) ? 0 : this.depositEvent.hashCode());
        result = prime * result + ((this.withdrawEvent == null) ? 0 : this.withdrawEvent.hashCode());
        result = prime * result + ((this.refundEvent == null) ? 0 : this.refundEvent.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		Event other = (Event) o;
		if (eventId == null) {
			return other.eventId == null;
		} else if (!eventId.equals(other.eventId)) {
			return false;
		}
		if (transactionId == null) {
			return other.transactionId == null;
		} else if (!transactionId.equals(other.transactionId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (eventType == null) {
			return other.eventType == null;
		} else if (!eventType.equals(other.eventType)) {
			return false;
		}
		if (verifyReceiptEvent == null) {
			return other.verifyReceiptEvent == null;
		} else if (!verifyReceiptEvent.equals(other.verifyReceiptEvent)) {
			return false;
		}
		if (depositEvent == null) {
			return other.depositEvent == null;
		} else if (!depositEvent.equals(other.depositEvent)) {
			return false;
		}
		if (withdrawEvent == null) {
			return other.withdrawEvent == null;
		} else if (!withdrawEvent.equals(other.withdrawEvent)) {
			return false;
		}
		if (refundEvent == null) {
			return other.refundEvent == null;
		} else if (!refundEvent.equals(other.refundEvent)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
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