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
public class RandomDisplayItemModel implements IModel, Serializable {
	private String name;
	private String metadata;
	private List<VerifyAction> verifyActions;
	private List<ConsumeAction> consumeActions;
	private List<AcquireAction> acquireActions;
	private Integer stock;
	private Integer weight;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RandomDisplayItemModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public RandomDisplayItemModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<VerifyAction> getVerifyActions() {
		return verifyActions;
	}
	public void setVerifyActions(List<VerifyAction> verifyActions) {
		this.verifyActions = verifyActions;
	}
	public RandomDisplayItemModel withVerifyActions(List<VerifyAction> verifyActions) {
		this.verifyActions = verifyActions;
		return this;
	}
	public List<ConsumeAction> getConsumeActions() {
		return consumeActions;
	}
	public void setConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
	}
	public RandomDisplayItemModel withConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
		return this;
	}
	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}
	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}
	public RandomDisplayItemModel withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public RandomDisplayItemModel withStock(Integer stock) {
		this.stock = stock;
		return this;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public RandomDisplayItemModel withWeight(Integer weight) {
		this.weight = weight;
		return this;
	}

    public static RandomDisplayItemModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RandomDisplayItemModel()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withVerifyActions(data.get("verifyActions") == null || data.get("verifyActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("verifyActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return VerifyAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withConsumeActions(data.get("consumeActions") == null || data.get("consumeActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("consumeActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withAcquireActions(data.get("acquireActions") == null || data.get("acquireActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withStock(data.get("stock") == null || data.get("stock").isNull() ? null : data.get("stock").intValue())
            .withWeight(data.get("weight") == null || data.get("weight").isNull() ? null : data.get("weight").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("metadata", getMetadata());
                put("verifyActions", getVerifyActions() == null ? null :
                    getVerifyActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("consumeActions", getConsumeActions() == null ? null :
                    getConsumeActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("acquireActions", getAcquireActions() == null ? null :
                    getAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("stock", getStock());
                put("weight", getWeight());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.verifyActions == null) ? 0 : this.verifyActions.hashCode());
        result = prime * result + ((this.consumeActions == null) ? 0 : this.consumeActions.hashCode());
        result = prime * result + ((this.acquireActions == null) ? 0 : this.acquireActions.hashCode());
        result = prime * result + ((this.stock == null) ? 0 : this.stock.hashCode());
        result = prime * result + ((this.weight == null) ? 0 : this.weight.hashCode());
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
		RandomDisplayItemModel other = (RandomDisplayItemModel) o;
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
		if (stock == null) {
			return other.stock == null;
		} else if (!stock.equals(other.stock)) {
			return false;
		}
		if (weight == null) {
			return other.weight == null;
		} else if (!weight.equals(other.weight)) {
			return false;
		}
		return true;
	}
}