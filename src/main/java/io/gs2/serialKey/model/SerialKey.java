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

package io.gs2.serialKey.model;

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
public class SerialKey implements IModel, Serializable, Comparable<SerialKey> {
	private String serialKeyId;
	private String campaignModelName;
	private String code;
	private String metadata;
	private String status;
	private String usedUserId;
	private Long createdAt;
	private Long usedAt;
	private Long updatedAt;
	public String getSerialKeyId() {
		return serialKeyId;
	}
	public void setSerialKeyId(String serialKeyId) {
		this.serialKeyId = serialKeyId;
	}
	public SerialKey withSerialKeyId(String serialKeyId) {
		this.serialKeyId = serialKeyId;
		return this;
	}
	public String getCampaignModelName() {
		return campaignModelName;
	}
	public void setCampaignModelName(String campaignModelName) {
		this.campaignModelName = campaignModelName;
	}
	public SerialKey withCampaignModelName(String campaignModelName) {
		this.campaignModelName = campaignModelName;
		return this;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public SerialKey withCode(String code) {
		this.code = code;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public SerialKey withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public SerialKey withStatus(String status) {
		this.status = status;
		return this;
	}
	public String getUsedUserId() {
		return usedUserId;
	}
	public void setUsedUserId(String usedUserId) {
		this.usedUserId = usedUserId;
	}
	public SerialKey withUsedUserId(String usedUserId) {
		this.usedUserId = usedUserId;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public SerialKey withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUsedAt() {
		return usedAt;
	}
	public void setUsedAt(Long usedAt) {
		this.usedAt = usedAt;
	}
	public SerialKey withUsedAt(Long usedAt) {
		this.usedAt = usedAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public SerialKey withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static SerialKey fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SerialKey()
            .withSerialKeyId(data.get("serialKeyId") == null || data.get("serialKeyId").isNull() ? null : data.get("serialKeyId").asText())
            .withCampaignModelName(data.get("campaignModelName") == null || data.get("campaignModelName").isNull() ? null : data.get("campaignModelName").asText())
            .withCode(data.get("code") == null || data.get("code").isNull() ? null : data.get("code").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : data.get("status").asText())
            .withUsedUserId(data.get("usedUserId") == null || data.get("usedUserId").isNull() ? null : data.get("usedUserId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUsedAt(data.get("usedAt") == null || data.get("usedAt").isNull() ? null : data.get("usedAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("serialKeyId", getSerialKeyId());
                put("campaignModelName", getCampaignModelName());
                put("code", getCode());
                put("metadata", getMetadata());
                put("status", getStatus());
                put("usedUserId", getUsedUserId());
                put("createdAt", getCreatedAt());
                put("usedAt", getUsedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(SerialKey o) {
		return serialKeyId.compareTo(o.serialKeyId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.serialKeyId == null) ? 0 : this.serialKeyId.hashCode());
        result = prime * result + ((this.campaignModelName == null) ? 0 : this.campaignModelName.hashCode());
        result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.usedUserId == null) ? 0 : this.usedUserId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.usedAt == null) ? 0 : this.usedAt.hashCode());
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
		SerialKey other = (SerialKey) o;
		if (serialKeyId == null) {
			return other.serialKeyId == null;
		} else if (!serialKeyId.equals(other.serialKeyId)) {
			return false;
		}
		if (campaignModelName == null) {
			return other.campaignModelName == null;
		} else if (!campaignModelName.equals(other.campaignModelName)) {
			return false;
		}
		if (code == null) {
			return other.code == null;
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (usedUserId == null) {
			return other.usedUserId == null;
		} else if (!usedUserId.equals(other.usedUserId)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (usedAt == null) {
			return other.usedAt == null;
		} else if (!usedAt.equals(other.usedAt)) {
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