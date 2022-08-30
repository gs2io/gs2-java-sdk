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

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NearUserIdsRequest extends Gs2BasicRequest<NearUserIdsRequest> {
    private String namespaceName;
    private String accessToken;
    private String areaModelName;
    private String layerModelName;
    private Position point;
    private Double r;
    private Integer limit;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public NearUserIdsRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public NearUserIdsRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getAreaModelName() {
		return areaModelName;
	}
	public void setAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
	}
	public NearUserIdsRequest withAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
		return this;
	}
	public String getLayerModelName() {
		return layerModelName;
	}
	public void setLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
	}
	public NearUserIdsRequest withLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
		return this;
	}
	public Position getPoint() {
		return point;
	}
	public void setPoint(Position point) {
		this.point = point;
	}
	public NearUserIdsRequest withPoint(Position point) {
		this.point = point;
		return this;
	}
	public Double getR() {
		return r;
	}
	public void setR(Double r) {
		this.r = r;
	}
	public NearUserIdsRequest withR(Double r) {
		this.r = r;
		return this;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public NearUserIdsRequest withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

    public static NearUserIdsRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new NearUserIdsRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withAreaModelName(data.get("areaModelName") == null || data.get("areaModelName").isNull() ? null : data.get("areaModelName").asText())
            .withLayerModelName(data.get("layerModelName") == null || data.get("layerModelName").isNull() ? null : data.get("layerModelName").asText())
            .withPoint(data.get("point") == null || data.get("point").isNull() ? null : Position.fromJson(data.get("point")))
            .withR(data.get("r") == null || data.get("r").isNull() ? null : data.get("r").doubleValue())
            .withLimit(data.get("limit") == null || data.get("limit").isNull() ? null : data.get("limit").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("areaModelName", getAreaModelName());
                put("layerModelName", getLayerModelName());
                put("point", getPoint() != null ? getPoint().toJson() : null);
                put("r", getR());
                put("limit", getLimit());
            }}
        );
    }
}