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

package io.gs2.money2.model;

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
public class AppleAppStoreSetting implements IModel, Serializable {
	private String bundleId;
	private String sharedSecretKey;
	private String issuerId;
	private String keyId;
	private String privateKeyPem;
	public String getBundleId() {
		return bundleId;
	}
	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}
	public AppleAppStoreSetting withBundleId(String bundleId) {
		this.bundleId = bundleId;
		return this;
	}
	public String getSharedSecretKey() {
		return sharedSecretKey;
	}
	public void setSharedSecretKey(String sharedSecretKey) {
		this.sharedSecretKey = sharedSecretKey;
	}
	public AppleAppStoreSetting withSharedSecretKey(String sharedSecretKey) {
		this.sharedSecretKey = sharedSecretKey;
		return this;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public AppleAppStoreSetting withIssuerId(String issuerId) {
		this.issuerId = issuerId;
		return this;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public AppleAppStoreSetting withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}
	public String getPrivateKeyPem() {
		return privateKeyPem;
	}
	public void setPrivateKeyPem(String privateKeyPem) {
		this.privateKeyPem = privateKeyPem;
	}
	public AppleAppStoreSetting withPrivateKeyPem(String privateKeyPem) {
		this.privateKeyPem = privateKeyPem;
		return this;
	}

    public static AppleAppStoreSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AppleAppStoreSetting()
            .withBundleId(data.get("bundleId") == null || data.get("bundleId").isNull() ? null : data.get("bundleId").asText())
            .withSharedSecretKey(data.get("sharedSecretKey") == null || data.get("sharedSecretKey").isNull() ? null : data.get("sharedSecretKey").asText())
            .withIssuerId(data.get("issuerId") == null || data.get("issuerId").isNull() ? null : data.get("issuerId").asText())
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText())
            .withPrivateKeyPem(data.get("privateKeyPem") == null || data.get("privateKeyPem").isNull() ? null : data.get("privateKeyPem").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("bundleId", getBundleId());
                put("sharedSecretKey", getSharedSecretKey());
                put("issuerId", getIssuerId());
                put("keyId", getKeyId());
                put("privateKeyPem", getPrivateKeyPem());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.bundleId == null) ? 0 : this.bundleId.hashCode());
        result = prime * result + ((this.sharedSecretKey == null) ? 0 : this.sharedSecretKey.hashCode());
        result = prime * result + ((this.issuerId == null) ? 0 : this.issuerId.hashCode());
        result = prime * result + ((this.keyId == null) ? 0 : this.keyId.hashCode());
        result = prime * result + ((this.privateKeyPem == null) ? 0 : this.privateKeyPem.hashCode());
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
		AppleAppStoreSetting other = (AppleAppStoreSetting) o;
		if (bundleId == null) {
			return other.bundleId == null;
		} else if (!bundleId.equals(other.bundleId)) {
			return false;
		}
		if (sharedSecretKey == null) {
			return other.sharedSecretKey == null;
		} else if (!sharedSecretKey.equals(other.sharedSecretKey)) {
			return false;
		}
		if (issuerId == null) {
			return other.issuerId == null;
		} else if (!issuerId.equals(other.issuerId)) {
			return false;
		}
		if (keyId == null) {
			return other.keyId == null;
		} else if (!keyId.equals(other.keyId)) {
			return false;
		}
		if (privateKeyPem == null) {
			return other.privateKeyPem == null;
		} else if (!privateKeyPem.equals(other.privateKeyPem)) {
			return false;
		}
		return true;
	}
}