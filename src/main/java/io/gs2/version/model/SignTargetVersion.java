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

package io.gs2.version.model;

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
public class SignTargetVersion implements IModel, Serializable {
	private String region;
	private String namespaceName;
	private String versionName;
	private Version version;
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public SignTargetVersion withRegion(String region) {
		this.region = region;
		return this;
	}
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public SignTargetVersion withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public SignTargetVersion withVersionName(String versionName) {
		this.versionName = versionName;
		return this;
	}
	public Version getVersion() {
		return version;
	}
	public void setVersion(Version version) {
		this.version = version;
	}
	public SignTargetVersion withVersion(Version version) {
		this.version = version;
		return this;
	}

    public static SignTargetVersion fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SignTargetVersion()
            .withRegion(data.get("region") == null || data.get("region").isNull() ? null : data.get("region").asText())
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withVersionName(data.get("versionName") == null || data.get("versionName").isNull() ? null : data.get("versionName").asText())
            .withVersion(data.get("version") == null || data.get("version").isNull() ? null : Version.fromJson(data.get("version")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("region", getRegion());
                put("namespaceName", getNamespaceName());
                put("versionName", getVersionName());
                put("version", getVersion() != null ? getVersion().toJson() : null);
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.region == null) ? 0 : this.region.hashCode());
        result = prime * result + ((this.namespaceName == null) ? 0 : this.namespaceName.hashCode());
        result = prime * result + ((this.versionName == null) ? 0 : this.versionName.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
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
		SignTargetVersion other = (SignTargetVersion) o;
		if (region == null) {
			return other.region == null;
		} else if (!region.equals(other.region)) {
			return false;
		}
		if (namespaceName == null) {
			return other.namespaceName == null;
		} else if (!namespaceName.equals(other.namespaceName)) {
			return false;
		}
		if (versionName == null) {
			return other.versionName == null;
		} else if (!versionName.equals(other.versionName)) {
			return false;
		}
		if (version == null) {
			return other.version == null;
		} else if (!version.equals(other.version)) {
			return false;
		}
		return true;
	}
}