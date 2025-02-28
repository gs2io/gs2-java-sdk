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
public class GooglePlaySetting implements IModel, Serializable {
	private String packageName;
	private String publicKey;
	private String credentialsJSON;
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public GooglePlaySetting withPackageName(String packageName) {
		this.packageName = packageName;
		return this;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public GooglePlaySetting withPublicKey(String publicKey) {
		this.publicKey = publicKey;
		return this;
	}
	public String getCredentialsJSON() {
		return credentialsJSON;
	}
	public void setCredentialsJSON(String credentialsJSON) {
		this.credentialsJSON = credentialsJSON;
	}
	public GooglePlaySetting withCredentialsJSON(String credentialsJSON) {
		this.credentialsJSON = credentialsJSON;
		return this;
	}

    public static GooglePlaySetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GooglePlaySetting()
            .withPackageName(data.get("packageName") == null || data.get("packageName").isNull() ? null : data.get("packageName").asText())
            .withPublicKey(data.get("publicKey") == null || data.get("publicKey").isNull() ? null : data.get("publicKey").asText())
            .withCredentialsJSON(data.get("credentialsJSON") == null || data.get("credentialsJSON").isNull() ? null : data.get("credentialsJSON").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("packageName", getPackageName());
                put("publicKey", getPublicKey());
                put("credentialsJSON", getCredentialsJSON());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.packageName == null) ? 0 : this.packageName.hashCode());
        result = prime * result + ((this.publicKey == null) ? 0 : this.publicKey.hashCode());
        result = prime * result + ((this.credentialsJSON == null) ? 0 : this.credentialsJSON.hashCode());
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
		GooglePlaySetting other = (GooglePlaySetting) o;
		if (packageName == null) {
			return other.packageName == null;
		} else if (!packageName.equals(other.packageName)) {
			return false;
		}
		if (publicKey == null) {
			return other.publicKey == null;
		} else if (!publicKey.equals(other.publicKey)) {
			return false;
		}
		if (credentialsJSON == null) {
			return other.credentialsJSON == null;
		} else if (!credentialsJSON.equals(other.credentialsJSON)) {
			return false;
		}
		return true;
	}
}