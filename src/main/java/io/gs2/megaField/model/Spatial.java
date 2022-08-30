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
public class Spatial implements IModel, Serializable, Comparable<Spatial> {
	private String spatialId;
	private String userId;
	private String areaModelName;
	private String layerModelName;
	private Position position;
	private Vector vector;
	private Float r;
	private Long lastSyncAt;
	private Long createdAt;
	public String getSpatialId() {
		return spatialId;
	}
	public void setSpatialId(String spatialId) {
		this.spatialId = spatialId;
	}
	public Spatial withSpatialId(String spatialId) {
		this.spatialId = spatialId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Spatial withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getAreaModelName() {
		return areaModelName;
	}
	public void setAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
	}
	public Spatial withAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
		return this;
	}
	public String getLayerModelName() {
		return layerModelName;
	}
	public void setLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
	}
	public Spatial withLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
		return this;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Spatial withPosition(Position position) {
		this.position = position;
		return this;
	}
	public Vector getVector() {
		return vector;
	}
	public void setVector(Vector vector) {
		this.vector = vector;
	}
	public Spatial withVector(Vector vector) {
		this.vector = vector;
		return this;
	}
	public Float getR() {
		return r;
	}
	public void setR(Float r) {
		this.r = r;
	}
	public Spatial withR(Float r) {
		this.r = r;
		return this;
	}
	public Long getLastSyncAt() {
		return lastSyncAt;
	}
	public void setLastSyncAt(Long lastSyncAt) {
		this.lastSyncAt = lastSyncAt;
	}
	public Spatial withLastSyncAt(Long lastSyncAt) {
		this.lastSyncAt = lastSyncAt;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Spatial withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static Spatial fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Spatial()
            .withSpatialId(data.get("spatialId") == null || data.get("spatialId").isNull() ? null : data.get("spatialId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withAreaModelName(data.get("areaModelName") == null || data.get("areaModelName").isNull() ? null : data.get("areaModelName").asText())
            .withLayerModelName(data.get("layerModelName") == null || data.get("layerModelName").isNull() ? null : data.get("layerModelName").asText())
            .withPosition(data.get("position") == null || data.get("position").isNull() ? null : Position.fromJson(data.get("position")))
            .withVector(data.get("vector") == null || data.get("vector").isNull() ? null : Vector.fromJson(data.get("vector")))
            .withR(data.get("r") == null || data.get("r").isNull() ? null : data.get("r").floatValue())
            .withLastSyncAt(data.get("lastSyncAt") == null || data.get("lastSyncAt").isNull() ? null : data.get("lastSyncAt").longValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("spatialId", getSpatialId());
                put("userId", getUserId());
                put("areaModelName", getAreaModelName());
                put("layerModelName", getLayerModelName());
                put("position", getPosition() != null ? getPosition().toJson() : null);
                put("vector", getVector() != null ? getVector().toJson() : null);
                put("r", getR());
                put("lastSyncAt", getLastSyncAt());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Spatial o) {
		return spatialId.compareTo(o.spatialId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.spatialId == null) ? 0 : this.spatialId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.areaModelName == null) ? 0 : this.areaModelName.hashCode());
        result = prime * result + ((this.layerModelName == null) ? 0 : this.layerModelName.hashCode());
        result = prime * result + ((this.position == null) ? 0 : this.position.hashCode());
        result = prime * result + ((this.vector == null) ? 0 : this.vector.hashCode());
        result = prime * result + ((this.r == null) ? 0 : this.r.hashCode());
        result = prime * result + ((this.lastSyncAt == null) ? 0 : this.lastSyncAt.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		Spatial other = (Spatial) o;
		if (spatialId == null) {
			return other.spatialId == null;
		} else if (!spatialId.equals(other.spatialId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (areaModelName == null) {
			return other.areaModelName == null;
		} else if (!areaModelName.equals(other.areaModelName)) {
			return false;
		}
		if (layerModelName == null) {
			return other.layerModelName == null;
		} else if (!layerModelName.equals(other.layerModelName)) {
			return false;
		}
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
		if (lastSyncAt == null) {
			return other.lastSyncAt == null;
		} else if (!lastSyncAt.equals(other.lastSyncAt)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		return true;
	}
}