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
	private String scope;
	private String type;
	private Version currentVersion;
	private Version warningVersion;
	private Version errorVersion;
	private List<ScheduleVersion> scheduleVersions;
	private Boolean needSignature;
	private String signatureKeyId;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public VersionModelMaster withType(String type) {
		this.type = type;
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
	public List<ScheduleVersion> getScheduleVersions() {
		return scheduleVersions;
	}
	public void setScheduleVersions(List<ScheduleVersion> scheduleVersions) {
		this.scheduleVersions = scheduleVersions;
	}
	public VersionModelMaster withScheduleVersions(List<ScheduleVersion> scheduleVersions) {
		this.scheduleVersions = scheduleVersions;
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
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public VersionModelMaster withRevision(Long revision) {
		this.revision = revision;
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
            .withScope(data.get("scope") == null || data.get("scope").isNull() ? null : data.get("scope").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withCurrentVersion(data.get("currentVersion") == null || data.get("currentVersion").isNull() ? null : Version.fromJson(data.get("currentVersion")))
            .withWarningVersion(data.get("warningVersion") == null || data.get("warningVersion").isNull() ? null : Version.fromJson(data.get("warningVersion")))
            .withErrorVersion(data.get("errorVersion") == null || data.get("errorVersion").isNull() ? null : Version.fromJson(data.get("errorVersion")))
            .withScheduleVersions(data.get("scheduleVersions") == null || data.get("scheduleVersions").isNull() ? new ArrayList<ScheduleVersion>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("scheduleVersions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ScheduleVersion.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withNeedSignature(data.get("needSignature") == null || data.get("needSignature").isNull() ? null : data.get("needSignature").booleanValue())
            .withSignatureKeyId(data.get("signatureKeyId") == null || data.get("signatureKeyId").isNull() ? null : data.get("signatureKeyId").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("versionModelId", getVersionModelId());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("scope", getScope());
                put("type", getType());
                put("currentVersion", getCurrentVersion() != null ? getCurrentVersion().toJson() : null);
                put("warningVersion", getWarningVersion() != null ? getWarningVersion().toJson() : null);
                put("errorVersion", getErrorVersion() != null ? getErrorVersion().toJson() : null);
                put("scheduleVersions", getScheduleVersions() == null ? new ArrayList<ScheduleVersion>() :
                    getScheduleVersions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("needSignature", getNeedSignature());
                put("signatureKeyId", getSignatureKeyId());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
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
        result = prime * result + ((this.scope == null) ? 0 : this.scope.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.currentVersion == null) ? 0 : this.currentVersion.hashCode());
        result = prime * result + ((this.warningVersion == null) ? 0 : this.warningVersion.hashCode());
        result = prime * result + ((this.errorVersion == null) ? 0 : this.errorVersion.hashCode());
        result = prime * result + ((this.scheduleVersions == null) ? 0 : this.scheduleVersions.hashCode());
        result = prime * result + ((this.needSignature == null) ? 0 : this.needSignature.hashCode());
        result = prime * result + ((this.signatureKeyId == null) ? 0 : this.signatureKeyId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.revision == null) ? 0 : this.revision.hashCode());
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
		if (scope == null) {
			return other.scope == null;
		} else if (!scope.equals(other.scope)) {
			return false;
		}
		if (type == null) {
			return other.type == null;
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (currentVersion == null) {
			return other.currentVersion == null;
		} else if (!currentVersion.equals(other.currentVersion)) {
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
		if (scheduleVersions == null) {
			return other.scheduleVersions == null;
		} else if (!scheduleVersions.equals(other.scheduleVersions)) {
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
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}