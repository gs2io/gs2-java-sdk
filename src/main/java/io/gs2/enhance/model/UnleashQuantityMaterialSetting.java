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

package io.gs2.enhance.model;

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
public class UnleashQuantityMaterialSetting implements IModel, Serializable {
	private String matchType;
	private String materialInventoryModelId;
	private String itemModelId;
	private Integer count;
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	public UnleashQuantityMaterialSetting withMatchType(String matchType) {
		this.matchType = matchType;
		return this;
	}
	public String getMaterialInventoryModelId() {
		return materialInventoryModelId;
	}
	public void setMaterialInventoryModelId(String materialInventoryModelId) {
		this.materialInventoryModelId = materialInventoryModelId;
	}
	public UnleashQuantityMaterialSetting withMaterialInventoryModelId(String materialInventoryModelId) {
		this.materialInventoryModelId = materialInventoryModelId;
		return this;
	}
	public String getItemModelId() {
		return itemModelId;
	}
	public void setItemModelId(String itemModelId) {
		this.itemModelId = itemModelId;
	}
	public UnleashQuantityMaterialSetting withItemModelId(String itemModelId) {
		this.itemModelId = itemModelId;
		return this;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public UnleashQuantityMaterialSetting withCount(Integer count) {
		this.count = count;
		return this;
	}

    public static UnleashQuantityMaterialSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UnleashQuantityMaterialSetting()
            .withMatchType(data.get("matchType") == null || data.get("matchType").isNull() ? null : data.get("matchType").asText())
            .withMaterialInventoryModelId(data.get("materialInventoryModelId") == null || data.get("materialInventoryModelId").isNull() ? null : data.get("materialInventoryModelId").asText())
            .withItemModelId(data.get("itemModelId") == null || data.get("itemModelId").isNull() ? null : data.get("itemModelId").asText())
            .withCount(data.get("count") == null || data.get("count").isNull() ? null : data.get("count").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("matchType", getMatchType());
                put("materialInventoryModelId", getMaterialInventoryModelId());
                put("itemModelId", getItemModelId());
                put("count", getCount());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.matchType == null) ? 0 : this.matchType.hashCode());
        result = prime * result + ((this.materialInventoryModelId == null) ? 0 : this.materialInventoryModelId.hashCode());
        result = prime * result + ((this.itemModelId == null) ? 0 : this.itemModelId.hashCode());
        result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
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
		UnleashQuantityMaterialSetting other = (UnleashQuantityMaterialSetting) o;
		if (matchType == null) {
			return other.matchType == null;
		} else if (!matchType.equals(other.matchType)) {
			return false;
		}
		if (materialInventoryModelId == null) {
			return other.materialInventoryModelId == null;
		} else if (!materialInventoryModelId.equals(other.materialInventoryModelId)) {
			return false;
		}
		if (itemModelId == null) {
			return other.itemModelId == null;
		} else if (!itemModelId.equals(other.itemModelId)) {
			return false;
		}
		if (count == null) {
			return other.count == null;
		} else if (!count.equals(other.count)) {
			return false;
		}
		return true;
	}
}