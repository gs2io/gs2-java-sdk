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
public class NearUserIdsFromSystemRequest extends Gs2BasicRequest<NearUserIdsFromSystemRequest> {
    private String namespaceName;
    private String areaModelName;
    private String layerModelName;
    private Position point;
    private Double r;
    private Integer limit;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public NearUserIdsFromSystemRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAreaModelName() {
		return areaModelName;
	}
	public void setAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
	}
	public NearUserIdsFromSystemRequest withAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
		return this;
	}
	public String getLayerModelName() {
		return layerModelName;
	}
	public void setLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
	}
	public NearUserIdsFromSystemRequest withLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
		return this;
	}
	public Position getPoint() {
		return point;
	}
	public void setPoint(Position point) {
		this.point = point;
	}
	public NearUserIdsFromSystemRequest withPoint(Position point) {
		this.point = point;
		return this;
	}
	public Double getR() {
		return r;
	}
	public void setR(Double r) {
		this.r = r;
	}
	public NearUserIdsFromSystemRequest withR(Double r) {
		this.r = r;
		return this;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public NearUserIdsFromSystemRequest withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public NearUserIdsFromSystemRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static NearUserIdsFromSystemRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new NearUserIdsFromSystemRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
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
                put("areaModelName", getAreaModelName());
                put("layerModelName", getLayerModelName());
                put("point", getPoint() != null ? getPoint().toJson() : null);
                put("r", getR());
                put("limit", getLimit());
            }}
        );
    }
}