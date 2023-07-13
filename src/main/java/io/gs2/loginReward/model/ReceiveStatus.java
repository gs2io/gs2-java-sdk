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

package io.gs2.loginReward.model;

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
public class ReceiveStatus implements IModel, Serializable, Comparable<ReceiveStatus> {
	private String receiveStatusId;
	private String bonusModelName;
	private String userId;
	private List<Boolean> receivedSteps;
	private Long lastReceivedAt;
	private Long createdAt;
	private Long updatedAt;
	public String getReceiveStatusId() {
		return receiveStatusId;
	}
	public void setReceiveStatusId(String receiveStatusId) {
		this.receiveStatusId = receiveStatusId;
	}
	public ReceiveStatus withReceiveStatusId(String receiveStatusId) {
		this.receiveStatusId = receiveStatusId;
		return this;
	}
	public String getBonusModelName() {
		return bonusModelName;
	}
	public void setBonusModelName(String bonusModelName) {
		this.bonusModelName = bonusModelName;
	}
	public ReceiveStatus withBonusModelName(String bonusModelName) {
		this.bonusModelName = bonusModelName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ReceiveStatus withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public List<Boolean> getReceivedSteps() {
		return receivedSteps;
	}
	public void setReceivedSteps(List<Boolean> receivedSteps) {
		this.receivedSteps = receivedSteps;
	}
	public ReceiveStatus withReceivedSteps(List<Boolean> receivedSteps) {
		this.receivedSteps = receivedSteps;
		return this;
	}
	public Long getLastReceivedAt() {
		return lastReceivedAt;
	}
	public void setLastReceivedAt(Long lastReceivedAt) {
		this.lastReceivedAt = lastReceivedAt;
	}
	public ReceiveStatus withLastReceivedAt(Long lastReceivedAt) {
		this.lastReceivedAt = lastReceivedAt;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public ReceiveStatus withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public ReceiveStatus withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static ReceiveStatus fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ReceiveStatus()
            .withReceiveStatusId(data.get("receiveStatusId") == null || data.get("receiveStatusId").isNull() ? null : data.get("receiveStatusId").asText())
            .withBonusModelName(data.get("bonusModelName") == null || data.get("bonusModelName").isNull() ? null : data.get("bonusModelName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withReceivedSteps(data.get("receivedSteps") == null || data.get("receivedSteps").isNull() ? new ArrayList<Boolean>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("receivedSteps").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.booleanValue();
                }
            ).collect(Collectors.toList()))
            .withLastReceivedAt(data.get("lastReceivedAt") == null || data.get("lastReceivedAt").isNull() ? null : data.get("lastReceivedAt").longValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("receiveStatusId", getReceiveStatusId());
                put("bonusModelName", getBonusModelName());
                put("userId", getUserId());
                put("receivedSteps", getReceivedSteps() == null ? new ArrayList<Boolean>() :
                    getReceivedSteps().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("lastReceivedAt", getLastReceivedAt());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(ReceiveStatus o) {
		return receiveStatusId.compareTo(o.receiveStatusId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.receiveStatusId == null) ? 0 : this.receiveStatusId.hashCode());
        result = prime * result + ((this.bonusModelName == null) ? 0 : this.bonusModelName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.receivedSteps == null) ? 0 : this.receivedSteps.hashCode());
        result = prime * result + ((this.lastReceivedAt == null) ? 0 : this.lastReceivedAt.hashCode());
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
		ReceiveStatus other = (ReceiveStatus) o;
		if (receiveStatusId == null) {
			return other.receiveStatusId == null;
		} else if (!receiveStatusId.equals(other.receiveStatusId)) {
			return false;
		}
		if (bonusModelName == null) {
			return other.bonusModelName == null;
		} else if (!bonusModelName.equals(other.bonusModelName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (receivedSteps == null) {
			return other.receivedSteps == null;
		} else if (!receivedSteps.equals(other.receivedSteps)) {
			return false;
		}
		if (lastReceivedAt == null) {
			return other.lastReceivedAt == null;
		} else if (!lastReceivedAt.equals(other.lastReceivedAt)) {
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