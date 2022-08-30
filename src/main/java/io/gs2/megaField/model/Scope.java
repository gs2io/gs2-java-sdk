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

package io.gs2.megaField.model;

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
public class Scope implements IModel, Serializable {
	private Double r;
	private Integer limit;
	public Double getR() {
		return r;
	}
	public void setR(Double r) {
		this.r = r;
	}
	public Scope withR(Double r) {
		this.r = r;
		return this;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Scope withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

    public static Scope fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Scope()
            .withR(data.get("r") == null || data.get("r").isNull() ? null : data.get("r").doubleValue())
            .withLimit(data.get("limit") == null || data.get("limit").isNull() ? null : data.get("limit").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("r", getR());
                put("limit", getLimit());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.r == null) ? 0 : this.r.hashCode());
        result = prime * result + ((this.limit == null) ? 0 : this.limit.hashCode());
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
		Scope other = (Scope) o;
		if (r == null) {
			return other.r == null;
		} else if (!r.equals(other.r)) {
			return false;
		}
		if (limit == null) {
			return other.limit == null;
		} else if (!limit.equals(other.limit)) {
			return false;
		}
		return true;
	}
}