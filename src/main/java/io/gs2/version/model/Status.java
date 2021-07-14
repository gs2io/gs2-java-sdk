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
public class Status implements IModel, Serializable {
	private VersionModel versionModel;
	private Version currentVersion;

	public VersionModel getVersionModel() {
		return versionModel;
	}

	public void setVersionModel(VersionModel versionModel) {
		this.versionModel = versionModel;
	}

	public Status withVersionModel(VersionModel versionModel) {
		this.versionModel = versionModel;
		return this;
	}

	public Version getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
	}

	public Status withCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
		return this;
	}

    public static Status fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Status()
            .withVersionModel(data.get("versionModel") == null || data.get("versionModel").isNull() ? null : VersionModel.fromJson(data.get("versionModel")))
            .withCurrentVersion(data.get("currentVersion") == null || data.get("currentVersion").isNull() ? null : Version.fromJson(data.get("currentVersion")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("versionModel", getVersionModel() != null ? getVersionModel().toJson() : null);
                put("currentVersion", getCurrentVersion() != null ? getCurrentVersion().toJson() : null);
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.versionModel == null) ? 0 : this.versionModel.hashCode());
        result = prime * result + ((this.currentVersion == null) ? 0 : this.currentVersion.hashCode());
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
		Status other = (Status) o;
		if (versionModel == null) {
			return other.versionModel == null;
		} else if (!versionModel.equals(other.versionModel)) {
			return false;
		}
		if (currentVersion == null) {
			return other.currentVersion == null;
		} else if (!currentVersion.equals(other.currentVersion)) {
			return false;
		}
		return true;
	}
}