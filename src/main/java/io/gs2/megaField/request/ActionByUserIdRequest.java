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
import io.gs2.megaField.model.MyPosition;
import io.gs2.megaField.model.Scope;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ActionByUserIdRequest extends Gs2BasicRequest<ActionByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String areaModelName;
    private String layerModelName;
    private MyPosition position;
    private Scope scope;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public ActionByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ActionByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getAreaModelName() {
		return areaModelName;
	}
	public void setAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
	}
	public ActionByUserIdRequest withAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
		return this;
	}
	public String getLayerModelName() {
		return layerModelName;
	}
	public void setLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
	}
	public ActionByUserIdRequest withLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
		return this;
	}
	public MyPosition getPosition() {
		return position;
	}
	public void setPosition(MyPosition position) {
		this.position = position;
	}
	public ActionByUserIdRequest withPosition(MyPosition position) {
		this.position = position;
		return this;
	}
	public Scope getScope() {
		return scope;
	}
	public void setScope(Scope scope) {
		this.scope = scope;
	}
	public ActionByUserIdRequest withScope(Scope scope) {
		this.scope = scope;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public ActionByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static ActionByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ActionByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withAreaModelName(data.get("areaModelName") == null || data.get("areaModelName").isNull() ? null : data.get("areaModelName").asText())
            .withLayerModelName(data.get("layerModelName") == null || data.get("layerModelName").isNull() ? null : data.get("layerModelName").asText())
            .withPosition(data.get("position") == null || data.get("position").isNull() ? null : MyPosition.fromJson(data.get("position")))
            .withScope(data.get("scope") == null || data.get("scope").isNull() ? null : Scope.fromJson(data.get("scope")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("areaModelName", getAreaModelName());
                put("layerModelName", getLayerModelName());
                put("position", getPosition() != null ? getPosition().toJson() : null);
                put("scope", getScope() != null ? getScope().toJson() : null);
            }}
        );
    }
}