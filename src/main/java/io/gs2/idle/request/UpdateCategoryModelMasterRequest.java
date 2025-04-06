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

package io.gs2.idle.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.idle.model.AcquireAction;
import io.gs2.idle.model.AcquireActionList;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateCategoryModelMasterRequest extends Gs2BasicRequest<UpdateCategoryModelMasterRequest> {
    private String namespaceName;
    private String categoryName;
    private String description;
    private String metadata;
    private Integer rewardIntervalMinutes;
    private Integer defaultMaximumIdleMinutes;
    private String rewardResetMode;
    private List<AcquireActionList> acquireActions;
    private String idlePeriodScheduleId;
    private String receivePeriodScheduleId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateCategoryModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public UpdateCategoryModelMasterRequest withCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateCategoryModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateCategoryModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getRewardIntervalMinutes() {
		return rewardIntervalMinutes;
	}
	public void setRewardIntervalMinutes(Integer rewardIntervalMinutes) {
		this.rewardIntervalMinutes = rewardIntervalMinutes;
	}
	public UpdateCategoryModelMasterRequest withRewardIntervalMinutes(Integer rewardIntervalMinutes) {
		this.rewardIntervalMinutes = rewardIntervalMinutes;
		return this;
	}
	public Integer getDefaultMaximumIdleMinutes() {
		return defaultMaximumIdleMinutes;
	}
	public void setDefaultMaximumIdleMinutes(Integer defaultMaximumIdleMinutes) {
		this.defaultMaximumIdleMinutes = defaultMaximumIdleMinutes;
	}
	public UpdateCategoryModelMasterRequest withDefaultMaximumIdleMinutes(Integer defaultMaximumIdleMinutes) {
		this.defaultMaximumIdleMinutes = defaultMaximumIdleMinutes;
		return this;
	}
	public String getRewardResetMode() {
		return rewardResetMode;
	}
	public void setRewardResetMode(String rewardResetMode) {
		this.rewardResetMode = rewardResetMode;
	}
	public UpdateCategoryModelMasterRequest withRewardResetMode(String rewardResetMode) {
		this.rewardResetMode = rewardResetMode;
		return this;
	}
	public List<AcquireActionList> getAcquireActions() {
		return acquireActions;
	}
	public void setAcquireActions(List<AcquireActionList> acquireActions) {
		this.acquireActions = acquireActions;
	}
	public UpdateCategoryModelMasterRequest withAcquireActions(List<AcquireActionList> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}
	public String getIdlePeriodScheduleId() {
		return idlePeriodScheduleId;
	}
	public void setIdlePeriodScheduleId(String idlePeriodScheduleId) {
		this.idlePeriodScheduleId = idlePeriodScheduleId;
	}
	public UpdateCategoryModelMasterRequest withIdlePeriodScheduleId(String idlePeriodScheduleId) {
		this.idlePeriodScheduleId = idlePeriodScheduleId;
		return this;
	}
	public String getReceivePeriodScheduleId() {
		return receivePeriodScheduleId;
	}
	public void setReceivePeriodScheduleId(String receivePeriodScheduleId) {
		this.receivePeriodScheduleId = receivePeriodScheduleId;
	}
	public UpdateCategoryModelMasterRequest withReceivePeriodScheduleId(String receivePeriodScheduleId) {
		this.receivePeriodScheduleId = receivePeriodScheduleId;
		return this;
	}

    public static UpdateCategoryModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateCategoryModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withCategoryName(data.get("categoryName") == null || data.get("categoryName").isNull() ? null : data.get("categoryName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withRewardIntervalMinutes(data.get("rewardIntervalMinutes") == null || data.get("rewardIntervalMinutes").isNull() ? null : data.get("rewardIntervalMinutes").intValue())
            .withDefaultMaximumIdleMinutes(data.get("defaultMaximumIdleMinutes") == null || data.get("defaultMaximumIdleMinutes").isNull() ? null : data.get("defaultMaximumIdleMinutes").intValue())
            .withRewardResetMode(data.get("rewardResetMode") == null || data.get("rewardResetMode").isNull() ? null : data.get("rewardResetMode").asText())
            .withAcquireActions(data.get("acquireActions") == null || data.get("acquireActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireActionList.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withIdlePeriodScheduleId(data.get("idlePeriodScheduleId") == null || data.get("idlePeriodScheduleId").isNull() ? null : data.get("idlePeriodScheduleId").asText())
            .withReceivePeriodScheduleId(data.get("receivePeriodScheduleId") == null || data.get("receivePeriodScheduleId").isNull() ? null : data.get("receivePeriodScheduleId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("categoryName", getCategoryName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("rewardIntervalMinutes", getRewardIntervalMinutes());
                put("defaultMaximumIdleMinutes", getDefaultMaximumIdleMinutes());
                put("rewardResetMode", getRewardResetMode());
                put("acquireActions", getAcquireActions() == null ? null :
                    getAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("idlePeriodScheduleId", getIdlePeriodScheduleId());
                put("receivePeriodScheduleId", getReceivePeriodScheduleId());
            }}
        );
    }
}