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

package io.gs2.stamina.model;

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
public class Stamina implements IModel, Serializable, Comparable<Stamina> {
	private String staminaId;
	private String staminaName;
	private String userId;
	private Integer value;
	private Integer maxValue;
	private Integer recoverIntervalMinutes;
	private Integer recoverValue;
	private Integer overflowValue;
	private Long nextRecoverAt;
	private Long lastRecoveredAt;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getStaminaId() {
		return staminaId;
	}
	public void setStaminaId(String staminaId) {
		this.staminaId = staminaId;
	}
	public Stamina withStaminaId(String staminaId) {
		this.staminaId = staminaId;
		return this;
	}
	public String getStaminaName() {
		return staminaName;
	}
	public void setStaminaName(String staminaName) {
		this.staminaName = staminaName;
	}
	public Stamina withStaminaName(String staminaName) {
		this.staminaName = staminaName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Stamina withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Stamina withValue(Integer value) {
		this.value = value;
		return this;
	}
	public Integer getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}
	public Stamina withMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
		return this;
	}
	public Integer getRecoverIntervalMinutes() {
		return recoverIntervalMinutes;
	}
	public void setRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
	}
	public Stamina withRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
		return this;
	}
	public Integer getRecoverValue() {
		return recoverValue;
	}
	public void setRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
	}
	public Stamina withRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
		return this;
	}
	public Integer getOverflowValue() {
		return overflowValue;
	}
	public void setOverflowValue(Integer overflowValue) {
		this.overflowValue = overflowValue;
	}
	public Stamina withOverflowValue(Integer overflowValue) {
		this.overflowValue = overflowValue;
		return this;
	}
	public Long getNextRecoverAt() {
		return nextRecoverAt;
	}
	public void setNextRecoverAt(Long nextRecoverAt) {
		this.nextRecoverAt = nextRecoverAt;
	}
	public Stamina withNextRecoverAt(Long nextRecoverAt) {
		this.nextRecoverAt = nextRecoverAt;
		return this;
	}
	public Long getLastRecoveredAt() {
		return lastRecoveredAt;
	}
	public void setLastRecoveredAt(Long lastRecoveredAt) {
		this.lastRecoveredAt = lastRecoveredAt;
	}
	public Stamina withLastRecoveredAt(Long lastRecoveredAt) {
		this.lastRecoveredAt = lastRecoveredAt;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Stamina withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Stamina withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Stamina withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Stamina fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Stamina()
            .withStaminaId(data.get("staminaId") == null || data.get("staminaId").isNull() ? null : data.get("staminaId").asText())
            .withStaminaName(data.get("staminaName") == null || data.get("staminaName").isNull() ? null : data.get("staminaName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withValue(data.get("value") == null || data.get("value").isNull() ? null : data.get("value").intValue())
            .withMaxValue(data.get("maxValue") == null || data.get("maxValue").isNull() ? null : data.get("maxValue").intValue())
            .withRecoverIntervalMinutes(data.get("recoverIntervalMinutes") == null || data.get("recoverIntervalMinutes").isNull() ? null : data.get("recoverIntervalMinutes").intValue())
            .withRecoverValue(data.get("recoverValue") == null || data.get("recoverValue").isNull() ? null : data.get("recoverValue").intValue())
            .withOverflowValue(data.get("overflowValue") == null || data.get("overflowValue").isNull() ? null : data.get("overflowValue").intValue())
            .withNextRecoverAt(data.get("nextRecoverAt") == null || data.get("nextRecoverAt").isNull() ? null : data.get("nextRecoverAt").longValue())
            .withLastRecoveredAt(data.get("lastRecoveredAt") == null || data.get("lastRecoveredAt").isNull() ? null : data.get("lastRecoveredAt").longValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("staminaId", getStaminaId());
                put("staminaName", getStaminaName());
                put("userId", getUserId());
                put("value", getValue());
                put("maxValue", getMaxValue());
                put("recoverIntervalMinutes", getRecoverIntervalMinutes());
                put("recoverValue", getRecoverValue());
                put("overflowValue", getOverflowValue());
                put("nextRecoverAt", getNextRecoverAt());
                put("lastRecoveredAt", getLastRecoveredAt());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Stamina o) {
		return staminaId.compareTo(o.staminaId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.staminaId == null) ? 0 : this.staminaId.hashCode());
        result = prime * result + ((this.staminaName == null) ? 0 : this.staminaName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
        result = prime * result + ((this.maxValue == null) ? 0 : this.maxValue.hashCode());
        result = prime * result + ((this.recoverIntervalMinutes == null) ? 0 : this.recoverIntervalMinutes.hashCode());
        result = prime * result + ((this.recoverValue == null) ? 0 : this.recoverValue.hashCode());
        result = prime * result + ((this.overflowValue == null) ? 0 : this.overflowValue.hashCode());
        result = prime * result + ((this.nextRecoverAt == null) ? 0 : this.nextRecoverAt.hashCode());
        result = prime * result + ((this.lastRecoveredAt == null) ? 0 : this.lastRecoveredAt.hashCode());
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
		Stamina other = (Stamina) o;
		if (staminaId == null) {
			return other.staminaId == null;
		} else if (!staminaId.equals(other.staminaId)) {
			return false;
		}
		if (staminaName == null) {
			return other.staminaName == null;
		} else if (!staminaName.equals(other.staminaName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (value == null) {
			return other.value == null;
		} else if (!value.equals(other.value)) {
			return false;
		}
		if (maxValue == null) {
			return other.maxValue == null;
		} else if (!maxValue.equals(other.maxValue)) {
			return false;
		}
		if (recoverIntervalMinutes == null) {
			return other.recoverIntervalMinutes == null;
		} else if (!recoverIntervalMinutes.equals(other.recoverIntervalMinutes)) {
			return false;
		}
		if (recoverValue == null) {
			return other.recoverValue == null;
		} else if (!recoverValue.equals(other.recoverValue)) {
			return false;
		}
		if (overflowValue == null) {
			return other.overflowValue == null;
		} else if (!overflowValue.equals(other.overflowValue)) {
			return false;
		}
		if (nextRecoverAt == null) {
			return other.nextRecoverAt == null;
		} else if (!nextRecoverAt.equals(other.nextRecoverAt)) {
			return false;
		}
		if (lastRecoveredAt == null) {
			return other.lastRecoveredAt == null;
		} else if (!lastRecoveredAt.equals(other.lastRecoveredAt)) {
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