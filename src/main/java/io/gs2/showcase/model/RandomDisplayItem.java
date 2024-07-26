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

package io.gs2.showcase.model;

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
public class RandomDisplayItem implements IModel, Serializable {
	private String showcaseName;
	private String name;
	private String metadata;
	private List<VerifyAction> verifyActions;
	private List<ConsumeAction> consumeActions;
	private List<AcquireAction> acquireActions;
	private Integer currentPurchaseCount;
	private Integer maximumPurchaseCount;
	public String getShowcaseName() {
		return showcaseName;
	}
	public void setShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
	}
	public RandomDisplayItem withShowcaseName(String showcaseName) {
		this.showcaseName = showcaseName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RandomDisplayItem withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public RandomDisplayItem withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<VerifyAction> getVerifyActions() {
		return verifyActions;
	}
	public void setVerifyActions(List<VerifyAction> verifyActions) {
		this.verifyActions = verifyActions;
	}
	public RandomDisplayItem withVerifyActions(List<VerifyAction> verifyActions) {
		this.verifyActions = verifyActions;
		return this;
	}
	public List<ConsumeAction> getConsumeActions() {
		return consumeActions;
	}
	public void setConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
	}
	public RandomDisplayItem withConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
		return this;
	}
	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}
	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}
	public RandomDisplayItem withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}
	public Integer getCurrentPurchaseCount() {
		return currentPurchaseCount;
	}
	public void setCurrentPurchaseCount(Integer currentPurchaseCount) {
		this.currentPurchaseCount = currentPurchaseCount;
	}
	public RandomDisplayItem withCurrentPurchaseCount(Integer currentPurchaseCount) {
		this.currentPurchaseCount = currentPurchaseCount;
		return this;
	}
	public Integer getMaximumPurchaseCount() {
		return maximumPurchaseCount;
	}
	public void setMaximumPurchaseCount(Integer maximumPurchaseCount) {
		this.maximumPurchaseCount = maximumPurchaseCount;
	}
	public RandomDisplayItem withMaximumPurchaseCount(Integer maximumPurchaseCount) {
		this.maximumPurchaseCount = maximumPurchaseCount;
		return this;
	}

    public static RandomDisplayItem fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RandomDisplayItem()
            .withShowcaseName(data.get("showcaseName") == null || data.get("showcaseName").isNull() ? null : data.get("showcaseName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withVerifyActions(data.get("verifyActions") == null || data.get("verifyActions").isNull() ? new ArrayList<VerifyAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("verifyActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return VerifyAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withConsumeActions(data.get("consumeActions") == null || data.get("consumeActions").isNull() ? new ArrayList<ConsumeAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("consumeActions").elements(), Spliterator.NONNULL), false).map(item -> {
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
            .withCurrentPurchaseCount(data.get("currentPurchaseCount") == null || data.get("currentPurchaseCount").isNull() ? null : data.get("currentPurchaseCount").intValue())
            .withMaximumPurchaseCount(data.get("maximumPurchaseCount") == null || data.get("maximumPurchaseCount").isNull() ? null : data.get("maximumPurchaseCount").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("showcaseName", getShowcaseName());
                put("name", getName());
                put("metadata", getMetadata());
                put("verifyActions", getVerifyActions() == null ? new ArrayList<VerifyAction>() :
                    getVerifyActions().stream().map(item -> {
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
                put("acquireActions", getAcquireActions() == null ? new ArrayList<AcquireAction>() :
                    getAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("currentPurchaseCount", getCurrentPurchaseCount());
                put("maximumPurchaseCount", getMaximumPurchaseCount());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.showcaseName == null) ? 0 : this.showcaseName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.verifyActions == null) ? 0 : this.verifyActions.hashCode());
        result = prime * result + ((this.consumeActions == null) ? 0 : this.consumeActions.hashCode());
        result = prime * result + ((this.acquireActions == null) ? 0 : this.acquireActions.hashCode());
        result = prime * result + ((this.currentPurchaseCount == null) ? 0 : this.currentPurchaseCount.hashCode());
        result = prime * result + ((this.maximumPurchaseCount == null) ? 0 : this.maximumPurchaseCount.hashCode());
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
		RandomDisplayItem other = (RandomDisplayItem) o;
		if (showcaseName == null) {
			return other.showcaseName == null;
		} else if (!showcaseName.equals(other.showcaseName)) {
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
		if (verifyActions == null) {
			return other.verifyActions == null;
		} else if (!verifyActions.equals(other.verifyActions)) {
			return false;
		}
		if (consumeActions == null) {
			return other.consumeActions == null;
		} else if (!consumeActions.equals(other.consumeActions)) {
			return false;
		}
		if (acquireActions == null) {
			return other.acquireActions == null;
		} else if (!acquireActions.equals(other.acquireActions)) {
			return false;
		}
		if (currentPurchaseCount == null) {
			return other.currentPurchaseCount == null;
		} else if (!currentPurchaseCount.equals(other.currentPurchaseCount)) {
			return false;
		}
		if (maximumPurchaseCount == null) {
			return other.maximumPurchaseCount == null;
		} else if (!maximumPurchaseCount.equals(other.maximumPurchaseCount)) {
			return false;
		}
		return true;
	}
}