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

package io.gs2.buff.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.buff.model.BuffTargetGrn;
import io.gs2.buff.model.BuffTargetModel;
import io.gs2.buff.model.BuffTargetAction;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateBuffEntryModelMasterRequest extends Gs2BasicRequest<CreateBuffEntryModelMasterRequest> {
    private String namespaceName;
    private String name;
    private String description;
    private String metadata;
    private String expression;
    private String targetType;
    private BuffTargetModel targetModel;
    private BuffTargetAction targetAction;
    private Integer priority;
    private String applyPeriodScheduleEventId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateBuffEntryModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateBuffEntryModelMasterRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateBuffEntryModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CreateBuffEntryModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public CreateBuffEntryModelMasterRequest withExpression(String expression) {
		this.expression = expression;
		return this;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public CreateBuffEntryModelMasterRequest withTargetType(String targetType) {
		this.targetType = targetType;
		return this;
	}
	public BuffTargetModel getTargetModel() {
		return targetModel;
	}
	public void setTargetModel(BuffTargetModel targetModel) {
		this.targetModel = targetModel;
	}
	public CreateBuffEntryModelMasterRequest withTargetModel(BuffTargetModel targetModel) {
		this.targetModel = targetModel;
		return this;
	}
	public BuffTargetAction getTargetAction() {
		return targetAction;
	}
	public void setTargetAction(BuffTargetAction targetAction) {
		this.targetAction = targetAction;
	}
	public CreateBuffEntryModelMasterRequest withTargetAction(BuffTargetAction targetAction) {
		this.targetAction = targetAction;
		return this;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public CreateBuffEntryModelMasterRequest withPriority(Integer priority) {
		this.priority = priority;
		return this;
	}
	public String getApplyPeriodScheduleEventId() {
		return applyPeriodScheduleEventId;
	}
	public void setApplyPeriodScheduleEventId(String applyPeriodScheduleEventId) {
		this.applyPeriodScheduleEventId = applyPeriodScheduleEventId;
	}
	public CreateBuffEntryModelMasterRequest withApplyPeriodScheduleEventId(String applyPeriodScheduleEventId) {
		this.applyPeriodScheduleEventId = applyPeriodScheduleEventId;
		return this;
	}

    public static CreateBuffEntryModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateBuffEntryModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withExpression(data.get("expression") == null || data.get("expression").isNull() ? null : data.get("expression").asText())
            .withTargetType(data.get("targetType") == null || data.get("targetType").isNull() ? null : data.get("targetType").asText())
            .withTargetModel(data.get("targetModel") == null || data.get("targetModel").isNull() ? null : BuffTargetModel.fromJson(data.get("targetModel")))
            .withTargetAction(data.get("targetAction") == null || data.get("targetAction").isNull() ? null : BuffTargetAction.fromJson(data.get("targetAction")))
            .withPriority(data.get("priority") == null || data.get("priority").isNull() ? null : data.get("priority").intValue())
            .withApplyPeriodScheduleEventId(data.get("applyPeriodScheduleEventId") == null || data.get("applyPeriodScheduleEventId").isNull() ? null : data.get("applyPeriodScheduleEventId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("expression", getExpression());
                put("targetType", getTargetType());
                put("targetModel", getTargetModel() != null ? getTargetModel().toJson() : null);
                put("targetAction", getTargetAction() != null ? getTargetAction().toJson() : null);
                put("priority", getPriority());
                put("applyPeriodScheduleEventId", getApplyPeriodScheduleEventId());
            }}
        );
    }
}