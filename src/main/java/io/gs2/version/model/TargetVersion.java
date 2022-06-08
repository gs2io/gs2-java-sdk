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
public class TargetVersion implements IModel, Serializable {
	private String versionName;
	private Version version;
	private String body;
	private String signature;
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public TargetVersion withVersionName(String versionName) {
		this.versionName = versionName;
		return this;
	}
	public Version getVersion() {
		return version;
	}
	public void setVersion(Version version) {
		this.version = version;
	}
	public TargetVersion withVersion(Version version) {
		this.version = version;
		return this;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public TargetVersion withBody(String body) {
		this.body = body;
		return this;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public TargetVersion withSignature(String signature) {
		this.signature = signature;
		return this;
	}

    public static TargetVersion fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new TargetVersion()
            .withVersionName(data.get("versionName") == null || data.get("versionName").isNull() ? null : data.get("versionName").asText())
            .withVersion(data.get("version") == null || data.get("version").isNull() ? null : Version.fromJson(data.get("version")))
            .withBody(data.get("body") == null || data.get("body").isNull() ? null : data.get("body").asText())
            .withSignature(data.get("signature") == null || data.get("signature").isNull() ? null : data.get("signature").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("versionName", getVersionName());
                put("version", getVersion() != null ? getVersion().toJson() : null);
                put("body", getBody());
                put("signature", getSignature());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.versionName == null) ? 0 : this.versionName.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        result = prime * result + ((this.body == null) ? 0 : this.body.hashCode());
        result = prime * result + ((this.signature == null) ? 0 : this.signature.hashCode());
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
		TargetVersion other = (TargetVersion) o;
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
		if (body == null) {
			return other.body == null;
		} else if (!body.equals(other.body)) {
			return false;
		}
		if (signature == null) {
			return other.signature == null;
		} else if (!signature.equals(other.signature)) {
			return false;
		}
		return true;
	}
}