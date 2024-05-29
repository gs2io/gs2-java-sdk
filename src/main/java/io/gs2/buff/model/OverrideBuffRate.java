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

package io.gs2.buff.model;

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
public class OverrideBuffRate implements IModel, Serializable {
	private String name;
	private Float rate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public OverrideBuffRate withName(String name) {
		this.name = name;
		return this;
	}
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
	public OverrideBuffRate withRate(Float rate) {
		this.rate = rate;
		return this;
	}

    public static OverrideBuffRate fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new OverrideBuffRate()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withRate(data.get("rate") == null || data.get("rate").isNull() ? null : data.get("rate").floatValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("rate", getRate());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.rate == null) ? 0 : this.rate.hashCode());
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
		OverrideBuffRate other = (OverrideBuffRate) o;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (rate == null) {
			return other.rate == null;
		} else if (!rate.equals(other.rate)) {
			return false;
		}
		return true;
	}
}