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

package io.gs2.enchant.model;

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
public class RarityParameterCountModel implements IModel, Serializable {
	private Integer count;
	private Integer weight;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public RarityParameterCountModel withCount(Integer count) {
		this.count = count;
		return this;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public RarityParameterCountModel withWeight(Integer weight) {
		this.weight = weight;
		return this;
	}

    public static RarityParameterCountModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RarityParameterCountModel()
            .withCount(data.get("count") == null || data.get("count").isNull() ? null : data.get("count").intValue())
            .withWeight(data.get("weight") == null || data.get("weight").isNull() ? null : data.get("weight").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("count", getCount());
                put("weight", getWeight());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
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
		RarityParameterCountModel other = (RarityParameterCountModel) o;
		if (count == null) {
			return other.count == null;
		} else if (!count.equals(other.count)) {
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