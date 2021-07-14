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
public class VersionModelMaster implements IModel, Serializable, Comparable<VersionModelMaster> {
	private String versionModelId;
	private String name;
	private String description;
	private String metadata;
	private Version warningVersion;
	private Version errorVersion;
	private String scope;
	private Version currentVersion;
	private Boolean needSignature;
	private String signatureKeyId;
	private Long createdAt;
	private Long updatedAt;

	public String getVersionModelId() {
		return versionModelId;
	}

	public void setVersionModelId(String versionModelId) {
		this.versionModelId = versionModelId;
	}

	public VersionModelMaster withVersionModelId(String versionModelId) {
		this.versionModelId = versionModelId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VersionModelMaster withName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public VersionModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public VersionModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public Version getWarningVersion() {
		return warningVersion;
	}

	public void setWarningVersion(Version warningVersion) {
		this.warningVersion = warningVersion;
	}

	public VersionModelMaster withWarningVersion(Version warningVersion) {
		this.warningVersion = warningVersion;
		return this;
	}

	public Version getErrorVersion() {
		return errorVersion;
	}

	public void setErrorVersion(Version errorVersion) {
		this.errorVersion = errorVersion;
	}

	public VersionModelMaster withErrorVersion(Version errorVersion) {
		this.errorVersion = errorVersion;
		return this;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public VersionModelMaster withScope(String scope) {
		this.scope = scope;
		return this;
	}

	public Version getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
	}

	public VersionModelMaster withCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
		return this;
	}

	public Boolean getNeedSignature() {
		return needSignature;
	}

	public void setNeedSignature(Boolean needSignature) {
		this.needSignature = needSignature;
	}

	public VersionModelMaster withNeedSignature(Boolean needSignature) {
		this.needSignature = needSignature;
		return this;
	}

	public String getSignatureKeyId() {
		return signatureKeyId;
	}

	public void setSignatureKeyId(String signatureKeyId) {
		this.signatureKeyId = signatureKeyId;
	}

	public VersionModelMaster withSignatureKeyId(String signatureKeyId) {
		this.signatureKeyId = signatureKeyId;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public VersionModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public VersionModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static VersionModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VersionModelMaster()
            .withVersionModelId(data.get("versionModelId") == null || data.get("versionModelId").isNull() ? null : data.get("versionModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withWarningVersion(data.get("warningVersion") == null || data.get("warningVersion").isNull() ? null : Version.fromJson(data.get("warningVersion")))
            .withErrorVersion(data.get("errorVersion") == null || data.get("errorVersion").isNull() ? null : Version.fromJson(data.get("errorVersion")))
            .withScope(data.get("scope") == null || data.get("scope").isNull() ? null : data.get("scope").asText())
            .withCurrentVersion(data.get("currentVersion") == null || data.get("currentVersion").isNull() ? null : Version.fromJson(data.get("currentVersion")))
            .withNeedSignature(data.get("needSignature") == null || data.get("needSignature").isNull() ? null : data.get("needSignature").booleanValue())
            .withSignatureKeyId(data.get("signatureKeyId") == null || data.get("signatureKeyId").isNull() ? null : data.get("signatureKeyId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("versionModelId", getVersionModelId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("warningVersion", getWarningVersion() != null ? getWarningVersion().toJson() : null);
                put("errorVersion", getErrorVersion() != null ? getErrorVersion().toJson() : null);
                put("scope", getScope());
                put("currentVersion", getCurrentVersion() != null ? getCurrentVersion().toJson() : null);
                put("needSignature", getNeedSignature());
                put("signatureKeyId", getSignatureKeyId());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(VersionModelMaster o) {
		return versionModelId.compareTo(o.versionModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.versionModelId == null) ? 0 : this.versionModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.warningVersion == null) ? 0 : this.warningVersion.hashCode());
        result = prime * result + ((this.errorVersion == null) ? 0 : this.errorVersion.hashCode());
        result = prime * result + ((this.scope == null) ? 0 : this.scope.hashCode());
        result = prime * result + ((this.currentVersion == null) ? 0 : this.currentVersion.hashCode());
        result = prime * result + ((this.needSignature == null) ? 0 : this.needSignature.hashCode());
        result = prime * result + ((this.signatureKeyId == null) ? 0 : this.signatureKeyId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		VersionModelMaster other = (VersionModelMaster) o;
		if (versionModelId == null) {
			return other.versionModelId == null;
		} else if (!versionModelId.equals(other.versionModelId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (warningVersion == null) {
			return other.warningVersion == null;
		} else if (!warningVersion.equals(other.warningVersion)) {
			return false;
		}
		if (errorVersion == null) {
			return other.errorVersion == null;
		} else if (!errorVersion.equals(other.errorVersion)) {
			return false;
		}
		if (scope == null) {
			return other.scope == null;
		} else if (!scope.equals(other.scope)) {
			return false;
		}
		if (currentVersion == null) {
			return other.currentVersion == null;
		} else if (!currentVersion.equals(other.currentVersion)) {
			return false;
		}
		if (needSignature == null) {
			return other.needSignature == null;
		} else if (!needSignature.equals(other.needSignature)) {
			return false;
		}
		if (signatureKeyId == null) {
			return other.signatureKeyId == null;
		} else if (!signatureKeyId.equals(other.signatureKeyId)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}