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

package io.gs2.matchmaking.model;

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
public class Gathering implements IModel, Serializable, Comparable<Gathering> {
	private String gatheringId;
	private String name;
	private List<AttributeRange> attributeRanges;
	private List<CapacityOfRole> capacityOfRoles;
	private List<String> allowUserIds;
	private String metadata;
	private Long expiresAt;
	private Long createdAt;
	private Long updatedAt;

	public String getGatheringId() {
		return gatheringId;
	}

	public void setGatheringId(String gatheringId) {
		this.gatheringId = gatheringId;
	}

	public Gathering withGatheringId(String gatheringId) {
		this.gatheringId = gatheringId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gathering withName(String name) {
		this.name = name;
		return this;
	}

	public List<AttributeRange> getAttributeRanges() {
		return attributeRanges;
	}

	public void setAttributeRanges(List<AttributeRange> attributeRanges) {
		this.attributeRanges = attributeRanges;
	}

	public Gathering withAttributeRanges(List<AttributeRange> attributeRanges) {
		this.attributeRanges = attributeRanges;
		return this;
	}

	public List<CapacityOfRole> getCapacityOfRoles() {
		return capacityOfRoles;
	}

	public void setCapacityOfRoles(List<CapacityOfRole> capacityOfRoles) {
		this.capacityOfRoles = capacityOfRoles;
	}

	public Gathering withCapacityOfRoles(List<CapacityOfRole> capacityOfRoles) {
		this.capacityOfRoles = capacityOfRoles;
		return this;
	}

	public List<String> getAllowUserIds() {
		return allowUserIds;
	}

	public void setAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
	}

	public Gathering withAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public Gathering withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public Long getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}

	public Gathering withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Gathering withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Gathering withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Gathering fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Gathering()
            .withGatheringId(data.get("gatheringId") == null || data.get("gatheringId").isNull() ? null : data.get("gatheringId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withAttributeRanges(data.get("attributeRanges") == null || data.get("attributeRanges").isNull() ? new ArrayList<AttributeRange>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("attributeRanges").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AttributeRange.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCapacityOfRoles(data.get("capacityOfRoles") == null || data.get("capacityOfRoles").isNull() ? new ArrayList<CapacityOfRole>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("capacityOfRoles").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return CapacityOfRole.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withAllowUserIds(data.get("allowUserIds") == null || data.get("allowUserIds").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("allowUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withExpiresAt(data.get("expiresAt") == null || data.get("expiresAt").isNull() ? null : data.get("expiresAt").longValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("gatheringId", getGatheringId());
                put("name", getName());
                put("attributeRanges", getAttributeRanges() == null ? new ArrayList<AttributeRange>() :
                    getAttributeRanges().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("capacityOfRoles", getCapacityOfRoles() == null ? new ArrayList<CapacityOfRole>() :
                    getCapacityOfRoles().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("allowUserIds", getAllowUserIds() == null ? new ArrayList<String>() :
                    getAllowUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("metadata", getMetadata());
                put("expiresAt", getExpiresAt());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Gathering o) {
		return gatheringId.compareTo(o.gatheringId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.gatheringId == null) ? 0 : this.gatheringId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.attributeRanges == null) ? 0 : this.attributeRanges.hashCode());
        result = prime * result + ((this.capacityOfRoles == null) ? 0 : this.capacityOfRoles.hashCode());
        result = prime * result + ((this.allowUserIds == null) ? 0 : this.allowUserIds.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.expiresAt == null) ? 0 : this.expiresAt.hashCode());
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
		Gathering other = (Gathering) o;
		if (gatheringId == null) {
			return other.gatheringId == null;
		} else if (!gatheringId.equals(other.gatheringId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (attributeRanges == null) {
			return other.attributeRanges == null;
		} else if (!attributeRanges.equals(other.attributeRanges)) {
			return false;
		}
		if (capacityOfRoles == null) {
			return other.capacityOfRoles == null;
		} else if (!capacityOfRoles.equals(other.capacityOfRoles)) {
			return false;
		}
		if (allowUserIds == null) {
			return other.allowUserIds == null;
		} else if (!allowUserIds.equals(other.allowUserIds)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (expiresAt == null) {
			return other.expiresAt == null;
		} else if (!expiresAt.equals(other.expiresAt)) {
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