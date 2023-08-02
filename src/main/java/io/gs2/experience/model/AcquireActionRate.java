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

package io.gs2.experience.model;

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
public class AcquireActionRate implements IModel, Serializable {
	private String name;
	private List<Double> rates;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AcquireActionRate withName(String name) {
		this.name = name;
		return this;
	}
	public List<Double> getRates() {
		return rates;
	}
	public void setRates(List<Double> rates) {
		this.rates = rates;
	}
	public AcquireActionRate withRates(List<Double> rates) {
		this.rates = rates;
		return this;
	}

    public static AcquireActionRate fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AcquireActionRate()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withRates(data.get("rates") == null || data.get("rates").isNull() ? new ArrayList<Double>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("rates").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.doubleValue();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("rates", getRates() == null ? new ArrayList<Double>() :
                    getRates().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.rates == null) ? 0 : this.rates.hashCode());
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
		AcquireActionRate other = (AcquireActionRate) o;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (rates == null) {
			return other.rates == null;
		} else if (!rates.equals(other.rates)) {
			return false;
		}
		return true;
	}
}