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

package io.gs2.lottery.model;

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
public class Prize implements IModel, Serializable {
	private String prizeId;
	private String type;
	private List<AcquireAction> acquireActions;
	private String prizeTableName;
	private Integer weight;

	public String getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}

	public Prize withPrizeId(String prizeId) {
		this.prizeId = prizeId;
		return this;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Prize withType(String type) {
		this.type = type;
		return this;
	}

	public List<AcquireAction> getAcquireActions() {
		return acquireActions;
	}

	public void setAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
	}

	public Prize withAcquireActions(List<AcquireAction> acquireActions) {
		this.acquireActions = acquireActions;
		return this;
	}

	public String getPrizeTableName() {
		return prizeTableName;
	}

	public void setPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
	}

	public Prize withPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
		return this;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Prize withWeight(Integer weight) {
		this.weight = weight;
		return this;
	}

    public static Prize fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Prize()
            .withPrizeId(data.get("prizeId") == null || data.get("prizeId").isNull() ? null : data.get("prizeId").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withAcquireActions(data.get("acquireActions") == null || data.get("acquireActions").isNull() ? new ArrayList<AcquireAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withPrizeTableName(data.get("prizeTableName") == null || data.get("prizeTableName").isNull() ? null : data.get("prizeTableName").asText())
            .withWeight(data.get("weight") == null || data.get("weight").isNull() ? null : data.get("weight").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("prizeId", getPrizeId());
                put("type", getType());
                put("acquireActions", getAcquireActions() == null ? new ArrayList<AcquireAction>() :
                    getAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("prizeTableName", getPrizeTableName());
                put("weight", getWeight());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.prizeId == null) ? 0 : this.prizeId.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.acquireActions == null) ? 0 : this.acquireActions.hashCode());
        result = prime * result + ((this.prizeTableName == null) ? 0 : this.prizeTableName.hashCode());
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
		Prize other = (Prize) o;
		if (prizeId == null) {
			return other.prizeId == null;
		} else if (!prizeId.equals(other.prizeId)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (acquireActions == null) {
			return other.acquireActions == null;
		} else if (!acquireActions.equals(other.acquireActions)) {
			return false;
		}
		if (prizeTableName == null) {
			return other.prizeTableName == null;
		} else if (!prizeTableName.equals(other.prizeTableName)) {
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