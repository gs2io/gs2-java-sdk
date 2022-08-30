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

package io.gs2.megaField.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.megaField.model.Position;
import io.gs2.megaField.model.Vector;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PutPositionRequest extends Gs2BasicRequest<PutPositionRequest> {
    private String namespaceName;
    private String accessToken;
    private String areaModelName;
    private String layerModelName;
    private Position position;
    private Vector vector;
    private Float r;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public PutPositionRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public PutPositionRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getAreaModelName() {
		return areaModelName;
	}
	public void setAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
	}
	public PutPositionRequest withAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
		return this;
	}
	public String getLayerModelName() {
		return layerModelName;
	}
	public void setLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
	}
	public PutPositionRequest withLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
		return this;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public PutPositionRequest withPosition(Position position) {
		this.position = position;
		return this;
	}
	public Vector getVector() {
		return vector;
	}
	public void setVector(Vector vector) {
		this.vector = vector;
	}
	public PutPositionRequest withVector(Vector vector) {
		this.vector = vector;
		return this;
	}
	public Float getR() {
		return r;
	}
	public void setR(Float r) {
		this.r = r;
	}
	public PutPositionRequest withR(Float r) {
		this.r = r;
		return this;
	}

    public static PutPositionRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PutPositionRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withAreaModelName(data.get("areaModelName") == null || data.get("areaModelName").isNull() ? null : data.get("areaModelName").asText())
            .withLayerModelName(data.get("layerModelName") == null || data.get("layerModelName").isNull() ? null : data.get("layerModelName").asText())
            .withPosition(data.get("position") == null || data.get("position").isNull() ? null : Position.fromJson(data.get("position")))
            .withVector(data.get("vector") == null || data.get("vector").isNull() ? null : Vector.fromJson(data.get("vector")))
            .withR(data.get("r") == null || data.get("r").isNull() ? null : data.get("r").floatValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("areaModelName", getAreaModelName());
                put("layerModelName", getLayerModelName());
                put("position", getPosition() != null ? getPosition().toJson() : null);
                put("vector", getVector() != null ? getVector().toJson() : null);
                put("r", getR());
            }}
        );
    }
}