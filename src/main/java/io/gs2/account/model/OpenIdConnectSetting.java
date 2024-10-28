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
public class OpenIdConnectSetting implements IModel, Serializable {
	private String configurationPath;
	private String clientId;
	private String clientSecret;
	private String appleTeamId;
	private String appleKeyId;
	private String applePrivateKeyPem;
	private String doneEndpointUrl;
	public String getConfigurationPath() {
		return configurationPath;
	}
	public void setConfigurationPath(String configurationPath) {
		this.configurationPath = configurationPath;
	}
	public OpenIdConnectSetting withConfigurationPath(String configurationPath) {
		this.configurationPath = configurationPath;
		return this;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public OpenIdConnectSetting withClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public OpenIdConnectSetting withClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
		return this;
	}
	public String getAppleTeamId() {
		return appleTeamId;
	}
	public void setAppleTeamId(String appleTeamId) {
		this.appleTeamId = appleTeamId;
	}
	public OpenIdConnectSetting withAppleTeamId(String appleTeamId) {
		this.appleTeamId = appleTeamId;
		return this;
	}
	public String getAppleKeyId() {
		return appleKeyId;
	}
	public void setAppleKeyId(String appleKeyId) {
		this.appleKeyId = appleKeyId;
	}
	public OpenIdConnectSetting withAppleKeyId(String appleKeyId) {
		this.appleKeyId = appleKeyId;
		return this;
	}
	public String getApplePrivateKeyPem() {
		return applePrivateKeyPem;
	}
	public void setApplePrivateKeyPem(String applePrivateKeyPem) {
		this.applePrivateKeyPem = applePrivateKeyPem;
	}
	public OpenIdConnectSetting withApplePrivateKeyPem(String applePrivateKeyPem) {
		this.applePrivateKeyPem = applePrivateKeyPem;
		return this;
	}
	public String getDoneEndpointUrl() {
		return doneEndpointUrl;
	}
	public void setDoneEndpointUrl(String doneEndpointUrl) {
		this.doneEndpointUrl = doneEndpointUrl;
	}
	public OpenIdConnectSetting withDoneEndpointUrl(String doneEndpointUrl) {
		this.doneEndpointUrl = doneEndpointUrl;
		return this;
	}

    public static OpenIdConnectSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new OpenIdConnectSetting()
            .withConfigurationPath(data.get("configurationPath") == null || data.get("configurationPath").isNull() ? null : data.get("configurationPath").asText())
            .withClientId(data.get("clientId") == null || data.get("clientId").isNull() ? null : data.get("clientId").asText())
            .withClientSecret(data.get("clientSecret") == null || data.get("clientSecret").isNull() ? null : data.get("clientSecret").asText())
            .withAppleTeamId(data.get("appleTeamId") == null || data.get("appleTeamId").isNull() ? null : data.get("appleTeamId").asText())
            .withAppleKeyId(data.get("appleKeyId") == null || data.get("appleKeyId").isNull() ? null : data.get("appleKeyId").asText())
            .withApplePrivateKeyPem(data.get("applePrivateKeyPem") == null || data.get("applePrivateKeyPem").isNull() ? null : data.get("applePrivateKeyPem").asText())
            .withDoneEndpointUrl(data.get("doneEndpointUrl") == null || data.get("doneEndpointUrl").isNull() ? null : data.get("doneEndpointUrl").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("configurationPath", getConfigurationPath());
                put("clientId", getClientId());
                put("clientSecret", getClientSecret());
                put("appleTeamId", getAppleTeamId());
                put("appleKeyId", getAppleKeyId());
                put("applePrivateKeyPem", getApplePrivateKeyPem());
                put("doneEndpointUrl", getDoneEndpointUrl());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.configurationPath == null) ? 0 : this.configurationPath.hashCode());
        result = prime * result + ((this.clientId == null) ? 0 : this.clientId.hashCode());
        result = prime * result + ((this.clientSecret == null) ? 0 : this.clientSecret.hashCode());
        result = prime * result + ((this.appleTeamId == null) ? 0 : this.appleTeamId.hashCode());
        result = prime * result + ((this.appleKeyId == null) ? 0 : this.appleKeyId.hashCode());
        result = prime * result + ((this.applePrivateKeyPem == null) ? 0 : this.applePrivateKeyPem.hashCode());
        result = prime * result + ((this.doneEndpointUrl == null) ? 0 : this.doneEndpointUrl.hashCode());
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
		OpenIdConnectSetting other = (OpenIdConnectSetting) o;
		if (configurationPath == null) {
			return other.configurationPath == null;
		} else if (!configurationPath.equals(other.configurationPath)) {
			return false;
		}
		if (clientId == null) {
			return other.clientId == null;
		} else if (!clientId.equals(other.clientId)) {
			return false;
		}
		if (clientSecret == null) {
			return other.clientSecret == null;
		} else if (!clientSecret.equals(other.clientSecret)) {
			return false;
		}
		if (appleTeamId == null) {
			return other.appleTeamId == null;
		} else if (!appleTeamId.equals(other.appleTeamId)) {
			return false;
		}
		if (appleKeyId == null) {
			return other.appleKeyId == null;
		} else if (!appleKeyId.equals(other.appleKeyId)) {
			return false;
		}
		if (applePrivateKeyPem == null) {
			return other.applePrivateKeyPem == null;
		} else if (!applePrivateKeyPem.equals(other.applePrivateKeyPem)) {
			return false;
		}
		if (doneEndpointUrl == null) {
			return other.doneEndpointUrl == null;
		} else if (!doneEndpointUrl.equals(other.doneEndpointUrl)) {
			return false;
		}
		return true;
	}
}