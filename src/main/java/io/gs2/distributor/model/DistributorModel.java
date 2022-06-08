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

package io.gs2.distributor.model;

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
public class DistributorModel implements IModel, Serializable, Comparable<DistributorModel> {
	private String distributorModelId;
	private String name;
	private String metadata;
	private String inboxNamespaceId;
	private List<String> whiteListTargetIds;
	public String getDistributorModelId() {
		return distributorModelId;
	}
	public void setDistributorModelId(String distributorModelId) {
		this.distributorModelId = distributorModelId;
	}
	public DistributorModel withDistributorModelId(String distributorModelId) {
		this.distributorModelId = distributorModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DistributorModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public DistributorModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getInboxNamespaceId() {
		return inboxNamespaceId;
	}
	public void setInboxNamespaceId(String inboxNamespaceId) {
		this.inboxNamespaceId = inboxNamespaceId;
	}
	public DistributorModel withInboxNamespaceId(String inboxNamespaceId) {
		this.inboxNamespaceId = inboxNamespaceId;
		return this;
	}
	public List<String> getWhiteListTargetIds() {
		return whiteListTargetIds;
	}
	public void setWhiteListTargetIds(List<String> whiteListTargetIds) {
		this.whiteListTargetIds = whiteListTargetIds;
	}
	public DistributorModel withWhiteListTargetIds(List<String> whiteListTargetIds) {
		this.whiteListTargetIds = whiteListTargetIds;
		return this;
	}

    public static DistributorModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DistributorModel()
            .withDistributorModelId(data.get("distributorModelId") == null || data.get("distributorModelId").isNull() ? null : data.get("distributorModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withInboxNamespaceId(data.get("inboxNamespaceId") == null || data.get("inboxNamespaceId").isNull() ? null : data.get("inboxNamespaceId").asText())
            .withWhiteListTargetIds(data.get("whiteListTargetIds") == null || data.get("whiteListTargetIds").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("whiteListTargetIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("distributorModelId", getDistributorModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("inboxNamespaceId", getInboxNamespaceId());
                put("whiteListTargetIds", getWhiteListTargetIds() == null ? new ArrayList<String>() :
                    getWhiteListTargetIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(DistributorModel o) {
		return distributorModelId.compareTo(o.distributorModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.distributorModelId == null) ? 0 : this.distributorModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.inboxNamespaceId == null) ? 0 : this.inboxNamespaceId.hashCode());
        result = prime * result + ((this.whiteListTargetIds == null) ? 0 : this.whiteListTargetIds.hashCode());
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
		DistributorModel other = (DistributorModel) o;
		if (distributorModelId == null) {
			return other.distributorModelId == null;
		} else if (!distributorModelId.equals(other.distributorModelId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (inboxNamespaceId == null) {
			return other.inboxNamespaceId == null;
		} else if (!inboxNamespaceId.equals(other.inboxNamespaceId)) {
			return false;
		}
		if (whiteListTargetIds == null) {
			return other.whiteListTargetIds == null;
		} else if (!whiteListTargetIds.equals(other.whiteListTargetIds)) {
			return false;
		}
		return true;
	}
}