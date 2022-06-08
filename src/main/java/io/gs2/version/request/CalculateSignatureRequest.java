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

package io.gs2.version.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.version.model.Version;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CalculateSignatureRequest extends Gs2BasicRequest<CalculateSignatureRequest> {
    private String namespaceName;
    private String versionName;
    private Version version;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CalculateSignatureRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public CalculateSignatureRequest withVersionName(String versionName) {
		this.versionName = versionName;
		return this;
	}
	public Version getVersion() {
		return version;
	}
	public void setVersion(Version version) {
		this.version = version;
	}
	public CalculateSignatureRequest withVersion(Version version) {
		this.version = version;
		return this;
	}

    public static CalculateSignatureRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CalculateSignatureRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withVersionName(data.get("versionName") == null || data.get("versionName").isNull() ? null : data.get("versionName").asText())
            .withVersion(data.get("version") == null || data.get("version").isNull() ? null : Version.fromJson(data.get("version")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("versionName", getVersionName());
                put("version", getVersion() != null ? getVersion().toJson() : null);
            }}
        );
    }
}