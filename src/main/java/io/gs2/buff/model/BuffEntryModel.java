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
public class BuffEntryModel implements IModel, Serializable, Comparable<BuffEntryModel> {
	private String buffEntryModelId;
	private String name;
	private String metadata;
	private String targetType;
	private BuffTargetModel targetModel;
	private BuffTargetAction targetAction;
	private String expression;
	private Integer priority;
	private String applyPeriodScheduleEventId;
	public String getBuffEntryModelId() {
		return buffEntryModelId;
	}
	public void setBuffEntryModelId(String buffEntryModelId) {
		this.buffEntryModelId = buffEntryModelId;
	}
	public BuffEntryModel withBuffEntryModelId(String buffEntryModelId) {
		this.buffEntryModelId = buffEntryModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BuffEntryModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public BuffEntryModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public BuffEntryModel withTargetType(String targetType) {
		this.targetType = targetType;
		return this;
	}
	public BuffTargetModel getTargetModel() {
		return targetModel;
	}
	public void setTargetModel(BuffTargetModel targetModel) {
		this.targetModel = targetModel;
	}
	public BuffEntryModel withTargetModel(BuffTargetModel targetModel) {
		this.targetModel = targetModel;
		return this;
	}
	public BuffTargetAction getTargetAction() {
		return targetAction;
	}
	public void setTargetAction(BuffTargetAction targetAction) {
		this.targetAction = targetAction;
	}
	public BuffEntryModel withTargetAction(BuffTargetAction targetAction) {
		this.targetAction = targetAction;
		return this;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public BuffEntryModel withExpression(String expression) {
		this.expression = expression;
		return this;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public BuffEntryModel withPriority(Integer priority) {
		this.priority = priority;
		return this;
	}
	public String getApplyPeriodScheduleEventId() {
		return applyPeriodScheduleEventId;
	}
	public void setApplyPeriodScheduleEventId(String applyPeriodScheduleEventId) {
		this.applyPeriodScheduleEventId = applyPeriodScheduleEventId;
	}
	public BuffEntryModel withApplyPeriodScheduleEventId(String applyPeriodScheduleEventId) {
		this.applyPeriodScheduleEventId = applyPeriodScheduleEventId;
		return this;
	}

    public static BuffEntryModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BuffEntryModel()
            .withBuffEntryModelId(data.get("buffEntryModelId") == null || data.get("buffEntryModelId").isNull() ? null : data.get("buffEntryModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTargetType(data.get("targetType") == null || data.get("targetType").isNull() ? null : data.get("targetType").asText())
            .withTargetModel(data.get("targetModel") == null || data.get("targetModel").isNull() ? null : BuffTargetModel.fromJson(data.get("targetModel")))
            .withTargetAction(data.get("targetAction") == null || data.get("targetAction").isNull() ? null : BuffTargetAction.fromJson(data.get("targetAction")))
            .withExpression(data.get("expression") == null || data.get("expression").isNull() ? null : data.get("expression").asText())
            .withPriority(data.get("priority") == null || data.get("priority").isNull() ? null : data.get("priority").intValue())
            .withApplyPeriodScheduleEventId(data.get("applyPeriodScheduleEventId") == null || data.get("applyPeriodScheduleEventId").isNull() ? null : data.get("applyPeriodScheduleEventId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("buffEntryModelId", getBuffEntryModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("targetType", getTargetType());
                put("targetModel", getTargetModel() != null ? getTargetModel().toJson() : null);
                put("targetAction", getTargetAction() != null ? getTargetAction().toJson() : null);
                put("expression", getExpression());
                put("priority", getPriority());
                put("applyPeriodScheduleEventId", getApplyPeriodScheduleEventId());
            }}
        );
    }

	@Override
	public int compareTo(BuffEntryModel o) {
		return buffEntryModelId.compareTo(o.buffEntryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.buffEntryModelId == null) ? 0 : this.buffEntryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.targetType == null) ? 0 : this.targetType.hashCode());
        result = prime * result + ((this.targetModel == null) ? 0 : this.targetModel.hashCode());
        result = prime * result + ((this.targetAction == null) ? 0 : this.targetAction.hashCode());
        result = prime * result + ((this.expression == null) ? 0 : this.expression.hashCode());
        result = prime * result + ((this.priority == null) ? 0 : this.priority.hashCode());
        result = prime * result + ((this.applyPeriodScheduleEventId == null) ? 0 : this.applyPeriodScheduleEventId.hashCode());
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
		BuffEntryModel other = (BuffEntryModel) o;
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
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
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
		if (expression == null) {
			return other.expression == null;
		} else if (!expression.equals(other.expression)) {
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
		return true;
	}
}