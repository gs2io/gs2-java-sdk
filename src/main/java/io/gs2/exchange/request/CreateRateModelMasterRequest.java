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

package io.gs2.exchange.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.exchange.model.ConsumeAction;
import io.gs2.exchange.model.AcquireAction;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateRateModelMasterRequest extends Gs2BasicRequest<CreateRateModelMasterRequest> {
    private String namespaceName;
    private String name;
    private String description;
    private String metadata;
    private String timingType;
    private Integer lockTime;
    private Boolean enableSkip;
    private List<ConsumeAction> skipConsumeActions;
    private List<AcquireAction> acquireActions;
    private List<ConsumeAction> consumeActions;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public CreateRateModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CreateRateModelMasterRequest withName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CreateRateModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public CreateRateModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public String getTimingType() {
		return timingType;
	}

	public void setTimingType(String timingType) {
		this.timingType = timingType;
	}

	public CreateRateModelMasterRequest withTimingType(String timingType) {
		this.timingType = timingType;
		return this;
	}

	public Integer getLockTime() {
		return lockTime;
	}

	public void setLockTime(Integer lockTime) {
		this.lockTime = lockTime;
	}

	public CreateRateModelMasterRequest withLockTime(Integer lockTime) {
		this.lockTime = lockTime;
		return this;
	}

	public Boolean getEnableSkip() {
		return enableSkip;
	}

	public void setEnableSkip(Boolean enableSkip) {
		this.enableSkip = enableSkip;
	}

	public CreateRateModelMasterRequest withEnableSkip(Boolean enableSkip) {
		this.enableSkip = enableSkip;
		return this;
	}

	public List<ConsumeAction> getSkipConsumeActions() {
		return skipConsumeActions;
	}

	public void setSkipConsumeActions(List<ConsumeAction> skipConsumeActions) {
		this.skipConsumeActions = skipConsumeActions;
	}

	public CreateRateModelMasterRequest withSkipConsumeActions(List<ConsumeAction> skipConsumeActions) {
		this.skipConsumeActions = skipConsumeActions;
		return this;
	}

	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}

	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}

	public CreateRateModelMasterRequest withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}

	public List<ConsumeAction> getConsumeActions() {
		return consumeActions;
	}

	public void setConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
	}

	public CreateRateModelMasterRequest withConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
		return this;
	}

    public static CreateRateModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateRateModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTimingType(data.get("timingType") == null || data.get("timingType").isNull() ? null : data.get("timingType").asText())
            .withLockTime(data.get("lockTime") == null || data.get("lockTime").isNull() ? null : data.get("lockTime").intValue())
            .withEnableSkip(data.get("enableSkip") == null || data.get("enableSkip").isNull() ? null : data.get("enableSkip").booleanValue())
            .withSkipConsumeActions(data.get("skipConsumeActions") == null || data.get("skipConsumeActions").isNull() ? new ArrayList<ConsumeAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("skipConsumeActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withAcquireActions(data.get("acquireActions") == null || data.get("acquireActions").isNull() ? new ArrayList<AcquireAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withConsumeActions(data.get("consumeActions") == null || data.get("consumeActions").isNull() ? new ArrayList<ConsumeAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("consumeActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("timingType", getTimingType());
                put("lockTime", getLockTime());
                put("enableSkip", getEnableSkip());
                put("skipConsumeActions", getSkipConsumeActions() == null ? new ArrayList<ConsumeAction>() :
                    getSkipConsumeActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("acquireActions", getAcquireActions() == null ? new ArrayList<AcquireAction>() :
                    getAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("consumeActions", getConsumeActions() == null ? new ArrayList<ConsumeAction>() :
                    getConsumeActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}