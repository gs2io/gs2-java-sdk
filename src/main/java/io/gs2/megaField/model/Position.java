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
public class Position implements IModel, Serializable {
	private Float x;
	private Float y;
	private Float z;
	public Float getX() {
		return x;
	}
	public void setX(Float x) {
		this.x = x;
	}
	public Position withX(Float x) {
		this.x = x;
		return this;
	}
	public Float getY() {
		return y;
	}
	public void setY(Float y) {
		this.y = y;
	}
	public Position withY(Float y) {
		this.y = y;
		return this;
	}
	public Float getZ() {
		return z;
	}
	public void setZ(Float z) {
		this.z = z;
	}
	public Position withZ(Float z) {
		this.z = z;
		return this;
	}

    public static Position fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Position()
            .withX(data.get("x") == null || data.get("x").isNull() ? null : data.get("x").floatValue())
            .withY(data.get("y") == null || data.get("y").isNull() ? null : data.get("y").floatValue())
            .withZ(data.get("z") == null || data.get("z").isNull() ? null : data.get("z").floatValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("x", getX());
                put("y", getY());
                put("z", getZ());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.x == null) ? 0 : this.x.hashCode());
        result = prime * result + ((this.y == null) ? 0 : this.y.hashCode());
        result = prime * result + ((this.z == null) ? 0 : this.z.hashCode());
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
		Position other = (Position) o;
		if (x == null) {
			return other.x == null;
		} else if (!x.equals(other.x)) {
			return false;
		}
		if (y == null) {
			return other.y == null;
		} else if (!y.equals(other.y)) {
			return false;
		}
		if (z == null) {
			return other.z == null;
		} else if (!z.equals(other.z)) {
			return false;
		}
		return true;
	}
}