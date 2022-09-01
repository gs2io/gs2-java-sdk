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
public class ActionRequest extends Gs2BasicRequest<ActionRequest> {
    private String namespaceName;
    private String accessToken;
    private String areaModelName;
    private String layerModelName;
    private MyPosition position;
    private List<Scope> scopes;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public ActionRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public ActionRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public String getAreaModelName() {
		return areaModelName;
	}
	public void setAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
	}
	public ActionRequest withAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
		return this;
	}
	public String getLayerModelName() {
		return layerModelName;
	}
	public void setLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
	}
	public ActionRequest withLayerModelName(String layerModelName) {
		this.layerModelName = layerModelName;
		return this;
	}
	public MyPosition getPosition() {
		return position;
	}
	public void setPosition(MyPosition position) {
		this.position = position;
	}
	public ActionRequest withPosition(MyPosition position) {
		this.position = position;
		return this;
	}
	public List<Scope> getScopes() {
		return scopes;
	}
	public void setScopes(List<Scope> scopes) {
		this.scopes = scopes;
	}
	public ActionRequest withScopes(List<Scope> scopes) {
		this.scopes = scopes;
		return this;
	}

    public static ActionRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ActionRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withAreaModelName(data.get("areaModelName") == null || data.get("areaModelName").isNull() ? null : data.get("areaModelName").asText())
            .withLayerModelName(data.get("layerModelName") == null || data.get("layerModelName").isNull() ? null : data.get("layerModelName").asText())
            .withPosition(data.get("position") == null || data.get("position").isNull() ? null : MyPosition.fromJson(data.get("position")))
            .withScopes(data.get("scopes") == null || data.get("scopes").isNull() ? new ArrayList<Scope>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("scopes").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Scope.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("areaModelName", getAreaModelName());
                put("layerModelName", getLayerModelName());
                put("position", getPosition() != null ? getPosition().toJson() : null);
                put("scopes", getScopes() == null ? new ArrayList<Scope>() :
                    getScopes().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}