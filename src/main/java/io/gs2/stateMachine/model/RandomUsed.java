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

package io.gs2.stateMachine.model;

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
public class RandomUsed implements IModel, Serializable {
	private Long category;
	private Long used;
	public Long getCategory() {
		return category;
	}
	public void setCategory(Long category) {
		this.category = category;
	}
	public RandomUsed withCategory(Long category) {
		this.category = category;
		return this;
	}
	public Long getUsed() {
		return used;
	}
	public void setUsed(Long used) {
		this.used = used;
	}
	public RandomUsed withUsed(Long used) {
		this.used = used;
		return this;
	}

    public static RandomUsed fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new RandomUsed()
            .withCategory(data.get("category") == null || data.get("category").isNull() ? null : data.get("category").longValue())
            .withUsed(data.get("used") == null || data.get("used").isNull() ? null : data.get("used").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("category", getCategory());
                put("used", getUsed());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.category == null) ? 0 : this.category.hashCode());
        result = prime * result + ((this.used == null) ? 0 : this.used.hashCode());
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
		RandomUsed other = (RandomUsed) o;
		if (category == null) {
			return other.category == null;
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (used == null) {
			return other.used == null;
		} else if (!used.equals(other.used)) {
			return false;
		}
		return true;
	}
}