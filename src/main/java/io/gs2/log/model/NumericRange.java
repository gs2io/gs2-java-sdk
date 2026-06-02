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

package io.gs2.log.model;

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
public class NumericRange implements IModel, Serializable {
	private Double min;
	private Double max;
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public NumericRange withMin(Double min) {
		this.min = min;
		return this;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public NumericRange withMax(Double max) {
		this.max = max;
		return this;
	}

    public static NumericRange fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new NumericRange()
            .withMin(data.get("min") == null || data.get("min").isNull() ? null : data.get("min").doubleValue())
            .withMax(data.get("max") == null || data.get("max").isNull() ? null : data.get("max").doubleValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("min", getMin());
                put("max", getMax());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.min == null) ? 0 : this.min.hashCode());
        result = prime * result + ((this.max == null) ? 0 : this.max.hashCode());
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
		NumericRange other = (NumericRange) o;
		if (min == null) {
			return other.min == null;
		} else if (!min.equals(other.min)) {
			return false;
		}
		if (max == null) {
			return other.max == null;
		} else if (!max.equals(other.max)) {
			return false;
		}
		return true;
	}
}