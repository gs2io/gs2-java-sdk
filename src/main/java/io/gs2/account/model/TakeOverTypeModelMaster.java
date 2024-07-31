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

package io.gs2.account.model;

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
public class TakeOverTypeModelMaster implements IModel, Serializable, Comparable<TakeOverTypeModelMaster> {
	private String takeOverTypeModelId;
	private Integer type;
	private String description;
	private String metadata;
	private OpenIdConnectSetting openIdConnectSetting;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getTakeOverTypeModelId() {
		return takeOverTypeModelId;
	}
	public void setTakeOverTypeModelId(String takeOverTypeModelId) {
		this.takeOverTypeModelId = takeOverTypeModelId;
	}
	public TakeOverTypeModelMaster withTakeOverTypeModelId(String takeOverTypeModelId) {
		this.takeOverTypeModelId = takeOverTypeModelId;
		return this;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public TakeOverTypeModelMaster withType(Integer type) {
		this.type = type;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TakeOverTypeModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public TakeOverTypeModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public OpenIdConnectSetting getOpenIdConnectSetting() {
		return openIdConnectSetting;
	}
	public void setOpenIdConnectSetting(OpenIdConnectSetting openIdConnectSetting) {
		this.openIdConnectSetting = openIdConnectSetting;
	}
	public TakeOverTypeModelMaster withOpenIdConnectSetting(OpenIdConnectSetting openIdConnectSetting) {
		this.openIdConnectSetting = openIdConnectSetting;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public TakeOverTypeModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public TakeOverTypeModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public TakeOverTypeModelMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static TakeOverTypeModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new TakeOverTypeModelMaster()
            .withTakeOverTypeModelId(data.get("takeOverTypeModelId") == null || data.get("takeOverTypeModelId").isNull() ? null : data.get("takeOverTypeModelId").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").intValue())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withOpenIdConnectSetting(data.get("openIdConnectSetting") == null || data.get("openIdConnectSetting").isNull() ? null : OpenIdConnectSetting.fromJson(data.get("openIdConnectSetting")))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("takeOverTypeModelId", getTakeOverTypeModelId());
                put("type", getType());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("openIdConnectSetting", getOpenIdConnectSetting() != null ? getOpenIdConnectSetting().toJson() : null);
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(TakeOverTypeModelMaster o) {
		return takeOverTypeModelId.compareTo(o.takeOverTypeModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.takeOverTypeModelId == null) ? 0 : this.takeOverTypeModelId.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.openIdConnectSetting == null) ? 0 : this.openIdConnectSetting.hashCode());
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
		TakeOverTypeModelMaster other = (TakeOverTypeModelMaster) o;
		if (takeOverTypeModelId == null) {
			return other.takeOverTypeModelId == null;
		} else if (!takeOverTypeModelId.equals(other.takeOverTypeModelId)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (openIdConnectSetting == null) {
			return other.openIdConnectSetting == null;
		} else if (!openIdConnectSetting.equals(other.openIdConnectSetting)) {
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