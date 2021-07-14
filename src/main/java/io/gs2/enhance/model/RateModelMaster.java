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

package io.gs2.enhance.model;

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
public class RateModelMaster implements IModel, Serializable, Comparable<RateModelMaster> {
	private String rateModelId;
	private String name;
	private String description;
	private String metadata;
	private String targetInventoryModelId;
	private String acquireExperienceSuffix;
	private String materialInventoryModelId;
	private List<String> acquireExperienceHierarchy;
	private String experienceModelId;
	private List<BonusRate> bonusRates;
	private Long createdAt;
	private Long updatedAt;

	public String getRateModelId() {
		return rateModelId;
	}

	public void setRateModelId(String rateModelId) {
		this.rateModelId = rateModelId;
	}

	public RateModelMaster withRateModelId(String rateModelId) {
		this.rateModelId = rateModelId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RateModelMaster withName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RateModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public RateModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public String getTargetInventoryModelId() {
		return targetInventoryModelId;
	}

	public void setTargetInventoryModelId(String targetInventoryModelId) {
		this.targetInventoryModelId = targetInventoryModelId;
	}

	public RateModelMaster withTargetInventoryModelId(String targetInventoryModelId) {
		this.targetInventoryModelId = targetInventoryModelId;
		return this;
	}

	public String getAcquireExperienceSuffix() {
		return acquireExperienceSuffix;
	}

	public void setAcquireExperienceSuffix(String acquireExperienceSuffix) {
		this.acquireExperienceSuffix = acquireExperienceSuffix;
	}

	public RateModelMaster withAcquireExperienceSuffix(String acquireExperienceSuffix) {
		this.acquireExperienceSuffix = acquireExperienceSuffix;
		return this;
	}

	public String getMaterialInventoryModelId() {
		return materialInventoryModelId;
	}

	public void setMaterialInventoryModelId(String materialInventoryModelId) {
		this.materialInventoryModelId = materialInventoryModelId;
	}

	public RateModelMaster withMaterialInventoryModelId(String materialInventoryModelId) {
		this.materialInventoryModelId = materialInventoryModelId;
		return this;
	}

	public List<String> getAcquireExperienceHierarchy() {
		return acquireExperienceHierarchy;
	}

	public void setAcquireExperienceHierarchy(List<String> acquireExperienceHierarchy) {
		this.acquireExperienceHierarchy = acquireExperienceHierarchy;
	}

	public RateModelMaster withAcquireExperienceHierarchy(List<String> acquireExperienceHierarchy) {
		this.acquireExperienceHierarchy = acquireExperienceHierarchy;
		return this;
	}

	public String getExperienceModelId() {
		return experienceModelId;
	}

	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}

	public RateModelMaster withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}

	public List<BonusRate> getBonusRates() {
		return bonusRates;
	}

	public void setBonusRates(List<BonusRate> bonusRates) {
		this.bonusRates = bonusRates;
	}

	public RateModelMaster withBonusRates(List<BonusRate> bonusRates) {
		this.bonusRates = bonusRates;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public RateModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public RateModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static RateModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RateModelMaster()
            .withRateModelId(data.get("rateModelId") == null || data.get("rateModelId").isNull() ? null : data.get("rateModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTargetInventoryModelId(data.get("targetInventoryModelId") == null || data.get("targetInventoryModelId").isNull() ? null : data.get("targetInventoryModelId").asText())
            .withAcquireExperienceSuffix(data.get("acquireExperienceSuffix") == null || data.get("acquireExperienceSuffix").isNull() ? null : data.get("acquireExperienceSuffix").asText())
            .withMaterialInventoryModelId(data.get("materialInventoryModelId") == null || data.get("materialInventoryModelId").isNull() ? null : data.get("materialInventoryModelId").asText())
            .withAcquireExperienceHierarchy(data.get("acquireExperienceHierarchy") == null || data.get("acquireExperienceHierarchy").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireExperienceHierarchy").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withExperienceModelId(data.get("experienceModelId") == null || data.get("experienceModelId").isNull() ? null : data.get("experienceModelId").asText())
            .withBonusRates(data.get("bonusRates") == null || data.get("bonusRates").isNull() ? new ArrayList<BonusRate>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("bonusRates").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return BonusRate.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("rateModelId", getRateModelId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("targetInventoryModelId", getTargetInventoryModelId());
                put("acquireExperienceSuffix", getAcquireExperienceSuffix());
                put("materialInventoryModelId", getMaterialInventoryModelId());
                put("acquireExperienceHierarchy", getAcquireExperienceHierarchy() == null ? new ArrayList<String>() :
                    getAcquireExperienceHierarchy().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("experienceModelId", getExperienceModelId());
                put("bonusRates", getBonusRates() == null ? new ArrayList<BonusRate>() :
                    getBonusRates().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(RateModelMaster o) {
		return rateModelId.compareTo(o.rateModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.rateModelId == null) ? 0 : this.rateModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.targetInventoryModelId == null) ? 0 : this.targetInventoryModelId.hashCode());
        result = prime * result + ((this.acquireExperienceSuffix == null) ? 0 : this.acquireExperienceSuffix.hashCode());
        result = prime * result + ((this.materialInventoryModelId == null) ? 0 : this.materialInventoryModelId.hashCode());
        result = prime * result + ((this.acquireExperienceHierarchy == null) ? 0 : this.acquireExperienceHierarchy.hashCode());
        result = prime * result + ((this.experienceModelId == null) ? 0 : this.experienceModelId.hashCode());
        result = prime * result + ((this.bonusRates == null) ? 0 : this.bonusRates.hashCode());
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
		RateModelMaster other = (RateModelMaster) o;
		if (rateModelId == null) {
			return other.rateModelId == null;
		} else if (!rateModelId.equals(other.rateModelId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
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
		if (targetInventoryModelId == null) {
			return other.targetInventoryModelId == null;
		} else if (!targetInventoryModelId.equals(other.targetInventoryModelId)) {
			return false;
		}
		if (acquireExperienceSuffix == null) {
			return other.acquireExperienceSuffix == null;
		} else if (!acquireExperienceSuffix.equals(other.acquireExperienceSuffix)) {
			return false;
		}
		if (materialInventoryModelId == null) {
			return other.materialInventoryModelId == null;
		} else if (!materialInventoryModelId.equals(other.materialInventoryModelId)) {
			return false;
		}
		if (acquireExperienceHierarchy == null) {
			return other.acquireExperienceHierarchy == null;
		} else if (!acquireExperienceHierarchy.equals(other.acquireExperienceHierarchy)) {
			return false;
		}
		if (experienceModelId == null) {
			return other.experienceModelId == null;
		} else if (!experienceModelId.equals(other.experienceModelId)) {
			return false;
		}
		if (bonusRates == null) {
			return other.bonusRates == null;
		} else if (!bonusRates.equals(other.bonusRates)) {
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