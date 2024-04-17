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
public class UpdateBuffEntryModelMasterRequest extends Gs2BasicRequest<UpdateBuffEntryModelMasterRequest> {
    private String namespaceName;
    private String buffEntryName;
    private String description;
    private String metadata;
    private String targetType;
    private BuffTargetModel targetModel;
    private BuffTargetAction targetAction;
    private String expression;
    private Integer priority;
    private String applyPeriodScheduleEventId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateBuffEntryModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getBuffEntryName() {
		return buffEntryName;
	}
	public void setBuffEntryName(String buffEntryName) {
		this.buffEntryName = buffEntryName;
	}
	public UpdateBuffEntryModelMasterRequest withBuffEntryName(String buffEntryName) {
		this.buffEntryName = buffEntryName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateBuffEntryModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateBuffEntryModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getTargetType() {
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	public UpdateBuffEntryModelMasterRequest withTargetType(String targetType) {
		this.targetType = targetType;
		return this;
	}
	public BuffTargetModel getTargetModel() {
		return targetModel;
	}
	public void setTargetModel(BuffTargetModel targetModel) {
		this.targetModel = targetModel;
	}
	public UpdateBuffEntryModelMasterRequest withTargetModel(BuffTargetModel targetModel) {
		this.targetModel = targetModel;
		return this;
	}
	public BuffTargetAction getTargetAction() {
		return targetAction;
	}
	public void setTargetAction(BuffTargetAction targetAction) {
		this.targetAction = targetAction;
	}
	public UpdateBuffEntryModelMasterRequest withTargetAction(BuffTargetAction targetAction) {
		this.targetAction = targetAction;
		return this;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public UpdateBuffEntryModelMasterRequest withExpression(String expression) {
		this.expression = expression;
		return this;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public UpdateBuffEntryModelMasterRequest withPriority(Integer priority) {
		this.priority = priority;
		return this;
	}
	public String getApplyPeriodScheduleEventId() {
		return applyPeriodScheduleEventId;
	}
	public void setApplyPeriodScheduleEventId(String applyPeriodScheduleEventId) {
		this.applyPeriodScheduleEventId = applyPeriodScheduleEventId;
	}
	public UpdateBuffEntryModelMasterRequest withApplyPeriodScheduleEventId(String applyPeriodScheduleEventId) {
		this.applyPeriodScheduleEventId = applyPeriodScheduleEventId;
		return this;
	}

    public static UpdateBuffEntryModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateBuffEntryModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withBuffEntryName(data.get("buffEntryName") == null || data.get("buffEntryName").isNull() ? null : data.get("buffEntryName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
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
                put("namespaceName", getNamespaceName());
                put("buffEntryName", getBuffEntryName());
                put("description", getDescription());
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
}