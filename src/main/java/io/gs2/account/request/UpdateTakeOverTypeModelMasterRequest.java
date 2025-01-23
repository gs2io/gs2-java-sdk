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

package io.gs2.account.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.account.model.ScopeValue;
import io.gs2.account.model.OpenIdConnectSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateTakeOverTypeModelMasterRequest extends Gs2BasicRequest<UpdateTakeOverTypeModelMasterRequest> {
    private String namespaceName;
    private Integer type;
    private String description;
    private String metadata;
    private OpenIdConnectSetting openIdConnectSetting;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateTakeOverTypeModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public UpdateTakeOverTypeModelMasterRequest withType(Integer type) {
		this.type = type;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateTakeOverTypeModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateTakeOverTypeModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public OpenIdConnectSetting getOpenIdConnectSetting() {
		return openIdConnectSetting;
	}
	public void setOpenIdConnectSetting(OpenIdConnectSetting openIdConnectSetting) {
		this.openIdConnectSetting = openIdConnectSetting;
	}
	public UpdateTakeOverTypeModelMasterRequest withOpenIdConnectSetting(OpenIdConnectSetting openIdConnectSetting) {
		this.openIdConnectSetting = openIdConnectSetting;
		return this;
	}

    public static UpdateTakeOverTypeModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateTakeOverTypeModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").intValue())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withOpenIdConnectSetting(data.get("openIdConnectSetting") == null || data.get("openIdConnectSetting").isNull() ? null : OpenIdConnectSetting.fromJson(data.get("openIdConnectSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("type", getType());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("openIdConnectSetting", getOpenIdConnectSetting() != null ? getOpenIdConnectSetting().toJson() : null);
            }}
        );
    }
}