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

package io.gs2.exchange.model;

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
	private List<ConsumeAction> consumeActions;
	private String timingType;
	private Integer lockTime;
	private Boolean enableSkip;
	private List<ConsumeAction> skipConsumeActions;
	private List<AcquireAction> acquireActions;
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
	public List<ConsumeAction> getConsumeActions() {
		return consumeActions;
	}
	public void setConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
	}
	public RateModelMaster withConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
		return this;
	}
	public String getTimingType() {
		return timingType;
	}
	public void setTimingType(String timingType) {
		this.timingType = timingType;
	}
	public RateModelMaster withTimingType(String timingType) {
		this.timingType = timingType;
		return this;
	}
	public Integer getLockTime() {
		return lockTime;
	}
	public void setLockTime(Integer lockTime) {
		this.lockTime = lockTime;
	}
	public RateModelMaster withLockTime(Integer lockTime) {
		this.lockTime = lockTime;
		return this;
	}
	public Boolean getEnableSkip() {
		return enableSkip;
	}
	public void setEnableSkip(Boolean enableSkip) {
		this.enableSkip = enableSkip;
	}
	public RateModelMaster withEnableSkip(Boolean enableSkip) {
		this.enableSkip = enableSkip;
		return this;
	}
	public List<ConsumeAction> getSkipConsumeActions() {
		return skipConsumeActions;
	}
	public void setSkipConsumeActions(List<ConsumeAction> skipConsumeActions) {
		this.skipConsumeActions = skipConsumeActions;
	}
	public RateModelMaster withSkipConsumeActions(List<ConsumeAction> skipConsumeActions) {
		this.skipConsumeActions = skipConsumeActions;
		return this;
	}
	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}
	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}
	public RateModelMaster withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
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
            .withConsumeActions(data.get("consumeActions") == null || data.get("consumeActions").isNull() ? new ArrayList<ConsumeAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("consumeActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
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
                put("consumeActions", getConsumeActions() == null ? new ArrayList<ConsumeAction>() :
                    getConsumeActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
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
        result = prime * result + ((this.consumeActions == null) ? 0 : this.consumeActions.hashCode());
        result = prime * result + ((this.timingType == null) ? 0 : this.timingType.hashCode());
        result = prime * result + ((this.lockTime == null) ? 0 : this.lockTime.hashCode());
        result = prime * result + ((this.enableSkip == null) ? 0 : this.enableSkip.hashCode());
        result = prime * result + ((this.skipConsumeActions == null) ? 0 : this.skipConsumeActions.hashCode());
        result = prime * result + ((this.acquireActions == null) ? 0 : this.acquireActions.hashCode());
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
		if (consumeActions == null) {
			return other.consumeActions == null;
		} else if (!consumeActions.equals(other.consumeActions)) {
			return false;
		}
		if (timingType == null) {
			return other.timingType == null;
		} else if (!timingType.equals(other.timingType)) {
			return false;
		}
		if (lockTime == null) {
			return other.lockTime == null;
		} else if (!lockTime.equals(other.lockTime)) {
			return false;
		}
		if (enableSkip == null) {
			return other.enableSkip == null;
		} else if (!enableSkip.equals(other.enableSkip)) {
			return false;
		}
		if (skipConsumeActions == null) {
			return other.skipConsumeActions == null;
		} else if (!skipConsumeActions.equals(other.skipConsumeActions)) {
			return false;
		}
		if (acquireActions == null) {
			return other.acquireActions == null;
		} else if (!acquireActions.equals(other.acquireActions)) {
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