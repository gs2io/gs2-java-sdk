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

package io.gs2.inventory.model;

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
public class AcquireCount implements IModel, Serializable {
	private String itemName;
	private Long count;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public AcquireCount withItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public AcquireCount withCount(Long count) {
		this.count = count;
		return this;
	}

    public static AcquireCount fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AcquireCount()
            .withItemName(data.get("itemName") == null || data.get("itemName").isNull() ? null : data.get("itemName").asText())
            .withCount(data.get("count") == null || data.get("count").isNull() ? null : data.get("count").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("itemName", getItemName());
                put("count", getCount());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.itemName == null) ? 0 : this.itemName.hashCode());
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
		AcquireCount other = (AcquireCount) o;
		if (itemName == null) {
			return other.itemName == null;
		} else if (!itemName.equals(other.itemName)) {
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