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
public class MyPosition implements IModel, Serializable {
	private Position position;
	private Vector vector;
	private Float r;
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public MyPosition withPosition(Position position) {
		this.position = position;
		return this;
	}
	public Vector getVector() {
		return vector;
	}
	public void setVector(Vector vector) {
		this.vector = vector;
	}
	public MyPosition withVector(Vector vector) {
		this.vector = vector;
		return this;
	}
	public Float getR() {
		return r;
	}
	public void setR(Float r) {
		this.r = r;
	}
	public MyPosition withR(Float r) {
		this.r = r;
		return this;
	}

    public static MyPosition fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new MyPosition()
            .withPosition(data.get("position") == null || data.get("position").isNull() ? null : Position.fromJson(data.get("position")))
            .withVector(data.get("vector") == null || data.get("vector").isNull() ? null : Vector.fromJson(data.get("vector")))
            .withR(data.get("r") == null || data.get("r").isNull() ? null : data.get("r").floatValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("position", getPosition() != null ? getPosition().toJson() : null);
                put("vector", getVector() != null ? getVector().toJson() : null);
                put("r", getR());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.position == null) ? 0 : this.position.hashCode());
        result = prime * result + ((this.vector == null) ? 0 : this.vector.hashCode());
        result = prime * result + ((this.r == null) ? 0 : this.r.hashCode());
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
		MyPosition other = (MyPosition) o;
		if (position == null) {
			return other.position == null;
		} else if (!position.equals(other.position)) {
			return false;
		}
		if (vector == null) {
			return other.vector == null;
		} else if (!vector.equals(other.vector)) {
			return false;
		}
		if (r == null) {
			return other.r == null;
		} else if (!r.equals(other.r)) {
			return false;
		}
		return true;
	}
}