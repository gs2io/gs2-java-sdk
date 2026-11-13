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

package io.gs2.account.model;

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
public class TakeOverTypeModel implements IModel, Serializable, Comparable<TakeOverTypeModel> {
	private String takeOverTypeModelId;
	private Integer type;
	private String metadata;
	private String takeOverMethod;
	private OpenIdConnectSetting openIdConnectSetting;
	private IssuedCodeSetting issuedCodeSetting;
	public String getTakeOverTypeModelId() {
		return takeOverTypeModelId;
	}
	public void setTakeOverTypeModelId(String takeOverTypeModelId) {
		this.takeOverTypeModelId = takeOverTypeModelId;
	}
	public TakeOverTypeModel withTakeOverTypeModelId(String takeOverTypeModelId) {
		this.takeOverTypeModelId = takeOverTypeModelId;
		return this;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public TakeOverTypeModel withType(Integer type) {
		this.type = type;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public TakeOverTypeModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getTakeOverMethod() {
		return takeOverMethod;
	}
	public void setTakeOverMethod(String takeOverMethod) {
		this.takeOverMethod = takeOverMethod;
	}
	public TakeOverTypeModel withTakeOverMethod(String takeOverMethod) {
		this.takeOverMethod = takeOverMethod;
		return this;
	}
	public OpenIdConnectSetting getOpenIdConnectSetting() {
		return openIdConnectSetting;
	}
	public void setOpenIdConnectSetting(OpenIdConnectSetting openIdConnectSetting) {
		this.openIdConnectSetting = openIdConnectSetting;
	}
	public TakeOverTypeModel withOpenIdConnectSetting(OpenIdConnectSetting openIdConnectSetting) {
		this.openIdConnectSetting = openIdConnectSetting;
		return this;
	}
	public IssuedCodeSetting getIssuedCodeSetting() {
		return issuedCodeSetting;
	}
	public void setIssuedCodeSetting(IssuedCodeSetting issuedCodeSetting) {
		this.issuedCodeSetting = issuedCodeSetting;
	}
	public TakeOverTypeModel withIssuedCodeSetting(IssuedCodeSetting issuedCodeSetting) {
		this.issuedCodeSetting = issuedCodeSetting;
		return this;
	}

    public static TakeOverTypeModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new TakeOverTypeModel()
            .withTakeOverTypeModelId(data.get("takeOverTypeModelId") == null || data.get("takeOverTypeModelId").isNull() ? null : data.get("takeOverTypeModelId").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").intValue())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTakeOverMethod(data.get("takeOverMethod") == null || data.get("takeOverMethod").isNull() ? null : data.get("takeOverMethod").asText())
            .withOpenIdConnectSetting(data.get("openIdConnectSetting") == null || data.get("openIdConnectSetting").isNull() ? null : OpenIdConnectSetting.fromJson(data.get("openIdConnectSetting")))
            .withIssuedCodeSetting(data.get("issuedCodeSetting") == null || data.get("issuedCodeSetting").isNull() ? null : IssuedCodeSetting.fromJson(data.get("issuedCodeSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("takeOverTypeModelId", getTakeOverTypeModelId());
                put("type", getType());
                put("metadata", getMetadata());
                put("takeOverMethod", getTakeOverMethod());
                put("openIdConnectSetting", getOpenIdConnectSetting() != null ? getOpenIdConnectSetting().toJson() : null);
                put("issuedCodeSetting", getIssuedCodeSetting() != null ? getIssuedCodeSetting().toJson() : null);
            }}
        );
    }

	@Override
	public int compareTo(TakeOverTypeModel o) {
		return takeOverTypeModelId.compareTo(o.takeOverTypeModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.takeOverTypeModelId == null) ? 0 : this.takeOverTypeModelId.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.takeOverMethod == null) ? 0 : this.takeOverMethod.hashCode());
        result = prime * result + ((this.openIdConnectSetting == null) ? 0 : this.openIdConnectSetting.hashCode());
        result = prime * result + ((this.issuedCodeSetting == null) ? 0 : this.issuedCodeSetting.hashCode());
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
		TakeOverTypeModel other = (TakeOverTypeModel) o;
		if (takeOverTypeModelId == null) {
			return other.takeOverTypeModelId == null;
		} else if (!takeOverTypeModelId.equals(other.takeOverTypeModelId)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (takeOverMethod == null) {
			return other.takeOverMethod == null;
		} else if (!takeOverMethod.equals(other.takeOverMethod)) {
			return false;
		}
		if (openIdConnectSetting == null) {
			return other.openIdConnectSetting == null;
		} else if (!openIdConnectSetting.equals(other.openIdConnectSetting)) {
			return false;
		}
		if (issuedCodeSetting == null) {
			return other.issuedCodeSetting == null;
		} else if (!issuedCodeSetting.equals(other.issuedCodeSetting)) {
			return false;
		}
		return true;
	}
}