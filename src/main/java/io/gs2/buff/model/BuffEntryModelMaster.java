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

package io.gs2.buff.model;

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
public class BuffEntryModelMaster implements IModel, Serializable, Comparable<BuffEntryModelMaster> {
	private String buffEntryModelId;
	private String name;
	private String description;
	private String metadata;
	private String expression;
	private String targetType;
	private BuffTargetModel targetModel;
	private BuffTargetAction targetAction;
	private Integer priority;
	private String applyPeriodScheduleEventId;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getBuffEntryModelId() {
		return buffEntryModelId;
	}
	public void setBuffEntryModelId(String buffEntryModelId) {
		this.buffEntryModelId = buffEntryModelId;
	}
	public BuffEntryModelMaster withBuffEntryModelId(String buffEntryModelId) {
		this.buffEntryModelId = buffEntryModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BuffEntryModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BuffEntryModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public BuffEntryModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public BuffEntryModelMaster withExpression(String expression) {
		this.expression = expression;
		return this;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public BuffEntryModelMaster withTargetType(String targetType) {
		this.targetType = targetType;
		return this;
	}
	public BuffTargetModel getTargetModel() {
		return targetModel;
	}
	public void setTargetModel(BuffTargetModel targetModel) {
		this.targetModel = targetModel;
	}
	public BuffEntryModelMaster withTargetModel(BuffTargetModel targetModel) {
		this.targetModel = targetModel;
		return this;
	}
	public BuffTargetAction getTargetAction() {
		return targetAction;
	}
	public void setTargetAction(BuffTargetAction targetAction) {
		this.targetAction = targetAction;
	}
	public BuffEntryModelMaster withTargetAction(BuffTargetAction targetAction) {
		this.targetAction = targetAction;
		return this;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public BuffEntryModelMaster withPriority(Integer priority) {
		this.priority = priority;
		return this;
	}
	public String getApplyPeriodScheduleEventId() {
		return applyPeriodScheduleEventId;
	}
	public void setApplyPeriodScheduleEventId(String applyPeriodScheduleEventId) {
		this.applyPeriodScheduleEventId = applyPeriodScheduleEventId;
	}
	public BuffEntryModelMaster withApplyPeriodScheduleEventId(String applyPeriodScheduleEventId) {
		this.applyPeriodScheduleEventId = applyPeriodScheduleEventId;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public BuffEntryModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public BuffEntryModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public BuffEntryModelMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static BuffEntryModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BuffEntryModelMaster()
            .withBuffEntryModelId(data.get("buffEntryModelId") == null || data.get("buffEntryModelId").isNull() ? null : data.get("buffEntryModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withExpression(data.get("expression") == null || data.get("expression").isNull() ? null : data.get("expression").asText())
            .withTargetType(data.get("targetType") == null || data.get("targetType").isNull() ? null : data.get("targetType").asText())
            .withTargetModel(data.get("targetModel") == null || data.get("targetModel").isNull() ? null : BuffTargetModel.fromJson(data.get("targetModel")))
            .withTargetAction(data.get("targetAction") == null || data.get("targetAction").isNull() ? null : BuffTargetAction.fromJson(data.get("targetAction")))
            .withPriority(data.get("priority") == null || data.get("priority").isNull() ? null : data.get("priority").intValue())
            .withApplyPeriodScheduleEventId(data.get("applyPeriodScheduleEventId") == null || data.get("applyPeriodScheduleEventId").isNull() ? null : data.get("applyPeriodScheduleEventId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("buffEntryModelId", getBuffEntryModelId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("expression", getExpression());
                put("targetType", getTargetType());
                put("targetModel", getTargetModel() != null ? getTargetModel().toJson() : null);
                put("targetAction", getTargetAction() != null ? getTargetAction().toJson() : null);
                put("priority", getPriority());
                put("applyPeriodScheduleEventId", getApplyPeriodScheduleEventId());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(BuffEntryModelMaster o) {
		return buffEntryModelId.compareTo(o.buffEntryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.buffEntryModelId == null) ? 0 : this.buffEntryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.expression == null) ? 0 : this.expression.hashCode());
        result = prime * result + ((this.targetType == null) ? 0 : this.targetType.hashCode());
        result = prime * result + ((this.targetModel == null) ? 0 : this.targetModel.hashCode());
        result = prime * result + ((this.targetAction == null) ? 0 : this.targetAction.hashCode());
        result = prime * result + ((this.priority == null) ? 0 : this.priority.hashCode());
        result = prime * result + ((this.applyPeriodScheduleEventId == null) ? 0 : this.applyPeriodScheduleEventId.hashCode());
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
		BuffEntryModelMaster other = (BuffEntryModelMaster) o;
		if (buffEntryModelId == null) {
			return other.buffEntryModelId == null;
		} else if (!buffEntryModelId.equals(other.buffEntryModelId)) {
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
		if (expression == null) {
			return other.expression == null;
		} else if (!expression.equals(other.expression)) {
			return false;
		}
		if (targetType == null) {
			return other.targetType == null;
		} else if (!targetType.equals(other.targetType)) {
			return false;
		}
		if (targetModel == null) {
			return other.targetModel == null;
		} else if (!targetModel.equals(other.targetModel)) {
			return false;
		}
		if (targetAction == null) {
			return other.targetAction == null;
		} else if (!targetAction.equals(other.targetAction)) {
			return false;
		}
		if (priority == null) {
			return other.priority == null;
		} else if (!priority.equals(other.priority)) {
			return false;
		}
		if (applyPeriodScheduleEventId == null) {
			return other.applyPeriodScheduleEventId == null;
		} else if (!applyPeriodScheduleEventId.equals(other.applyPeriodScheduleEventId)) {
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