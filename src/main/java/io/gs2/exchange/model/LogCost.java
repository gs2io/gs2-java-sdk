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

package io.gs2.exchange.model;

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
public class LogCost implements IModel, Serializable {
	private Double base;
	private List<Double> adds;
	private List<Double> subs;
	public Double getBase() {
		return base;
	}
	public void setBase(Double base) {
		this.base = base;
	}
	public LogCost withBase(Double base) {
		this.base = base;
		return this;
	}
	public List<Double> getAdds() {
		return adds;
	}
	public void setAdds(List<Double> adds) {
		this.adds = adds;
	}
	public LogCost withAdds(List<Double> adds) {
		this.adds = adds;
		return this;
	}
	public List<Double> getSubs() {
		return subs;
	}
	public void setSubs(List<Double> subs) {
		this.subs = subs;
	}
	public LogCost withSubs(List<Double> subs) {
		this.subs = subs;
		return this;
	}

    public static LogCost fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new LogCost()
            .withBase(data.get("base") == null || data.get("base").isNull() ? null : data.get("base").doubleValue())
            .withAdds(data.get("adds") == null || data.get("adds").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("adds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.doubleValue();
                }
            ).collect(Collectors.toList()))
            .withSubs(data.get("subs") == null || data.get("subs").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("subs").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.doubleValue();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("base", getBase());
                put("adds", getAdds() == null ? null :
                    getAdds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("subs", getSubs() == null ? null :
                    getSubs().stream().map(item -> {
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
        result = prime * result + ((this.base == null) ? 0 : this.base.hashCode());
        result = prime * result + ((this.adds == null) ? 0 : this.adds.hashCode());
        result = prime * result + ((this.subs == null) ? 0 : this.subs.hashCode());
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
		LogCost other = (LogCost) o;
		if (base == null) {
			return other.base == null;
		} else if (!base.equals(other.base)) {
			return false;
		}
		if (adds == null) {
			return other.adds == null;
		} else if (!adds.equals(other.adds)) {
			return false;
		}
		if (subs == null) {
			return other.subs == null;
		} else if (!subs.equals(other.subs)) {
			return false;
		}
		return true;
	}
}