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
import io.gs2.version.model.ScheduleVersion;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateVersionModelMasterRequest extends Gs2BasicRequest<UpdateVersionModelMasterRequest> {
    private String namespaceName;
    private String versionName;
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
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateVersionModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public UpdateVersionModelMasterRequest withVersionName(String versionName) {
		this.versionName = versionName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateVersionModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateVersionModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public UpdateVersionModelMasterRequest withScope(String scope) {
		this.scope = scope;
		return this;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UpdateVersionModelMasterRequest withType(String type) {
		this.type = type;
		return this;
	}
	public Version getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
	}
	public UpdateVersionModelMasterRequest withCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
		return this;
	}
	public Version getWarningVersion() {
		return warningVersion;
	}
	public void setWarningVersion(Version warningVersion) {
		this.warningVersion = warningVersion;
	}
	public UpdateVersionModelMasterRequest withWarningVersion(Version warningVersion) {
		this.warningVersion = warningVersion;
		return this;
	}
	public Version getErrorVersion() {
		return errorVersion;
	}
	public void setErrorVersion(Version errorVersion) {
		this.errorVersion = errorVersion;
	}
	public UpdateVersionModelMasterRequest withErrorVersion(Version errorVersion) {
		this.errorVersion = errorVersion;
		return this;
	}
	public List<ScheduleVersion> getScheduleVersions() {
		return scheduleVersions;
	}
	public void setScheduleVersions(List<ScheduleVersion> scheduleVersions) {
		this.scheduleVersions = scheduleVersions;
	}
	public UpdateVersionModelMasterRequest withScheduleVersions(List<ScheduleVersion> scheduleVersions) {
		this.scheduleVersions = scheduleVersions;
		return this;
	}
	public Boolean getNeedSignature() {
		return needSignature;
	}
	public void setNeedSignature(Boolean needSignature) {
		this.needSignature = needSignature;
	}
	public UpdateVersionModelMasterRequest withNeedSignature(Boolean needSignature) {
		this.needSignature = needSignature;
		return this;
	}
	public String getSignatureKeyId() {
		return signatureKeyId;
	}
	public void setSignatureKeyId(String signatureKeyId) {
		this.signatureKeyId = signatureKeyId;
	}
	public UpdateVersionModelMasterRequest withSignatureKeyId(String signatureKeyId) {
		this.signatureKeyId = signatureKeyId;
		return this;
	}

    public static UpdateVersionModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateVersionModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withVersionName(data.get("versionName") == null || data.get("versionName").isNull() ? null : data.get("versionName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withScope(data.get("scope") == null || data.get("scope").isNull() ? null : data.get("scope").asText())
            .withType(data.get("type") == null || data.get("type").isNull() ? null : data.get("type").asText())
            .withCurrentVersion(data.get("currentVersion") == null || data.get("currentVersion").isNull() ? null : Version.fromJson(data.get("currentVersion")))
            .withWarningVersion(data.get("warningVersion") == null || data.get("warningVersion").isNull() ? null : Version.fromJson(data.get("warningVersion")))
            .withErrorVersion(data.get("errorVersion") == null || data.get("errorVersion").isNull() ? null : Version.fromJson(data.get("errorVersion")))
            .withScheduleVersions(data.get("scheduleVersions") == null || data.get("scheduleVersions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("scheduleVersions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ScheduleVersion.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withNeedSignature(data.get("needSignature") == null || data.get("needSignature").isNull() ? null : data.get("needSignature").booleanValue())
            .withSignatureKeyId(data.get("signatureKeyId") == null || data.get("signatureKeyId").isNull() ? null : data.get("signatureKeyId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("versionName", getVersionName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("scope", getScope());
                put("type", getType());
                put("currentVersion", getCurrentVersion() != null ? getCurrentVersion().toJson() : null);
                put("warningVersion", getWarningVersion() != null ? getWarningVersion().toJson() : null);
                put("errorVersion", getErrorVersion() != null ? getErrorVersion().toJson() : null);
                put("scheduleVersions", getScheduleVersions() == null ? null :
                    getScheduleVersions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("needSignature", getNeedSignature());
                put("signatureKeyId", getSignatureKeyId());
            }}
        );
    }
}