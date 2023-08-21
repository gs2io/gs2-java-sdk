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

package io.gs2.idle.model;

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
public class CategoryModelMaster implements IModel, Serializable, Comparable<CategoryModelMaster> {
	private String categoryModelId;
	private String name;
	private String description;
	private String metadata;
	private Integer rewardIntervalMinutes;
	private Integer defaultMaximumIdleMinutes;
	private List<AcquireActionList> acquireActions;
	private String idlePeriodScheduleId;
	private String receivePeriodScheduleId;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getCategoryModelId() {
		return categoryModelId;
	}
	public void setCategoryModelId(String categoryModelId) {
		this.categoryModelId = categoryModelId;
	}
	public CategoryModelMaster withCategoryModelId(String categoryModelId) {
		this.categoryModelId = categoryModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CategoryModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CategoryModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CategoryModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getRewardIntervalMinutes() {
		return rewardIntervalMinutes;
	}
	public void setRewardIntervalMinutes(Integer rewardIntervalMinutes) {
		this.rewardIntervalMinutes = rewardIntervalMinutes;
	}
	public CategoryModelMaster withRewardIntervalMinutes(Integer rewardIntervalMinutes) {
		this.rewardIntervalMinutes = rewardIntervalMinutes;
		return this;
	}
	public Integer getDefaultMaximumIdleMinutes() {
		return defaultMaximumIdleMinutes;
	}
	public void setDefaultMaximumIdleMinutes(Integer defaultMaximumIdleMinutes) {
		this.defaultMaximumIdleMinutes = defaultMaximumIdleMinutes;
	}
	public CategoryModelMaster withDefaultMaximumIdleMinutes(Integer defaultMaximumIdleMinutes) {
		this.defaultMaximumIdleMinutes = defaultMaximumIdleMinutes;
		return this;
	}
	public List<AcquireActionList> getAcquireActions() {
		return acquireActions;
	}
	public void setAcquireActions(List<AcquireActionList> acquireActions) {
		this.acquireActions = acquireActions;
	}
	public CategoryModelMaster withAcquireActions(List<AcquireActionList> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}
	public String getIdlePeriodScheduleId() {
		return idlePeriodScheduleId;
	}
	public void setIdlePeriodScheduleId(String idlePeriodScheduleId) {
		this.idlePeriodScheduleId = idlePeriodScheduleId;
	}
	public CategoryModelMaster withIdlePeriodScheduleId(String idlePeriodScheduleId) {
		this.idlePeriodScheduleId = idlePeriodScheduleId;
		return this;
	}
	public String getReceivePeriodScheduleId() {
		return receivePeriodScheduleId;
	}
	public void setReceivePeriodScheduleId(String receivePeriodScheduleId) {
		this.receivePeriodScheduleId = receivePeriodScheduleId;
	}
	public CategoryModelMaster withReceivePeriodScheduleId(String receivePeriodScheduleId) {
		this.receivePeriodScheduleId = receivePeriodScheduleId;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public CategoryModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public CategoryModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public CategoryModelMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static CategoryModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CategoryModelMaster()
            .withCategoryModelId(data.get("categoryModelId") == null || data.get("categoryModelId").isNull() ? null : data.get("categoryModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withRewardIntervalMinutes(data.get("rewardIntervalMinutes") == null || data.get("rewardIntervalMinutes").isNull() ? null : data.get("rewardIntervalMinutes").intValue())
            .withDefaultMaximumIdleMinutes(data.get("defaultMaximumIdleMinutes") == null || data.get("defaultMaximumIdleMinutes").isNull() ? null : data.get("defaultMaximumIdleMinutes").intValue())
            .withAcquireActions(data.get("acquireActions") == null || data.get("acquireActions").isNull() ? new ArrayList<AcquireActionList>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireActionList.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withIdlePeriodScheduleId(data.get("idlePeriodScheduleId") == null || data.get("idlePeriodScheduleId").isNull() ? null : data.get("idlePeriodScheduleId").asText())
            .withReceivePeriodScheduleId(data.get("receivePeriodScheduleId") == null || data.get("receivePeriodScheduleId").isNull() ? null : data.get("receivePeriodScheduleId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("categoryModelId", getCategoryModelId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("rewardIntervalMinutes", getRewardIntervalMinutes());
                put("defaultMaximumIdleMinutes", getDefaultMaximumIdleMinutes());
                put("acquireActions", getAcquireActions() == null ? new ArrayList<AcquireActionList>() :
                    getAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("idlePeriodScheduleId", getIdlePeriodScheduleId());
                put("receivePeriodScheduleId", getReceivePeriodScheduleId());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(CategoryModelMaster o) {
		return categoryModelId.compareTo(o.categoryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.categoryModelId == null) ? 0 : this.categoryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.rewardIntervalMinutes == null) ? 0 : this.rewardIntervalMinutes.hashCode());
        result = prime * result + ((this.defaultMaximumIdleMinutes == null) ? 0 : this.defaultMaximumIdleMinutes.hashCode());
        result = prime * result + ((this.acquireActions == null) ? 0 : this.acquireActions.hashCode());
        result = prime * result + ((this.idlePeriodScheduleId == null) ? 0 : this.idlePeriodScheduleId.hashCode());
        result = prime * result + ((this.receivePeriodScheduleId == null) ? 0 : this.receivePeriodScheduleId.hashCode());
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
		CategoryModelMaster other = (CategoryModelMaster) o;
		if (categoryModelId == null) {
			return other.categoryModelId == null;
		} else if (!categoryModelId.equals(other.categoryModelId)) {
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
		if (rewardIntervalMinutes == null) {
			return other.rewardIntervalMinutes == null;
		} else if (!rewardIntervalMinutes.equals(other.rewardIntervalMinutes)) {
			return false;
		}
		if (defaultMaximumIdleMinutes == null) {
			return other.defaultMaximumIdleMinutes == null;
		} else if (!defaultMaximumIdleMinutes.equals(other.defaultMaximumIdleMinutes)) {
			return false;
		}
		if (acquireActions == null) {
			return other.acquireActions == null;
		} else if (!acquireActions.equals(other.acquireActions)) {
			return false;
		}
		if (idlePeriodScheduleId == null) {
			return other.idlePeriodScheduleId == null;
		} else if (!idlePeriodScheduleId.equals(other.idlePeriodScheduleId)) {
			return false;
		}
		if (receivePeriodScheduleId == null) {
			return other.receivePeriodScheduleId == null;
		} else if (!receivePeriodScheduleId.equals(other.receivePeriodScheduleId)) {
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