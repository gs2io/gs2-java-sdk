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
public class ScheduleVersion implements IModel, Serializable {
	private Version currentVersion;
	private Version warningVersion;
	private Version errorVersion;
	private String scheduleEventId;
	public Version getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
	}
	public ScheduleVersion withCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
		return this;
	}
	public Version getWarningVersion() {
		return warningVersion;
	}
	public void setWarningVersion(Version warningVersion) {
		this.warningVersion = warningVersion;
	}
	public ScheduleVersion withWarningVersion(Version warningVersion) {
		this.warningVersion = warningVersion;
		return this;
	}
	public Version getErrorVersion() {
		return errorVersion;
	}
	public void setErrorVersion(Version errorVersion) {
		this.errorVersion = errorVersion;
	}
	public ScheduleVersion withErrorVersion(Version errorVersion) {
		this.errorVersion = errorVersion;
		return this;
	}
	public String getScheduleEventId() {
		return scheduleEventId;
	}
	public void setScheduleEventId(String scheduleEventId) {
		this.scheduleEventId = scheduleEventId;
	}
	public ScheduleVersion withScheduleEventId(String scheduleEventId) {
		this.scheduleEventId = scheduleEventId;
		return this;
	}

    public static ScheduleVersion fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ScheduleVersion()
            .withCurrentVersion(data.get("currentVersion") == null || data.get("currentVersion").isNull() ? null : Version.fromJson(data.get("currentVersion")))
            .withWarningVersion(data.get("warningVersion") == null || data.get("warningVersion").isNull() ? null : Version.fromJson(data.get("warningVersion")))
            .withErrorVersion(data.get("errorVersion") == null || data.get("errorVersion").isNull() ? null : Version.fromJson(data.get("errorVersion")))
            .withScheduleEventId(data.get("scheduleEventId") == null || data.get("scheduleEventId").isNull() ? null : data.get("scheduleEventId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("currentVersion", getCurrentVersion() != null ? getCurrentVersion().toJson() : null);
                put("warningVersion", getWarningVersion() != null ? getWarningVersion().toJson() : null);
                put("errorVersion", getErrorVersion() != null ? getErrorVersion().toJson() : null);
                put("scheduleEventId", getScheduleEventId());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.currentVersion == null) ? 0 : this.currentVersion.hashCode());
        result = prime * result + ((this.warningVersion == null) ? 0 : this.warningVersion.hashCode());
        result = prime * result + ((this.errorVersion == null) ? 0 : this.errorVersion.hashCode());
        result = prime * result + ((this.scheduleEventId == null) ? 0 : this.scheduleEventId.hashCode());
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
		ScheduleVersion other = (ScheduleVersion) o;
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
		if (scheduleEventId == null) {
			return other.scheduleEventId == null;
		} else if (!scheduleEventId.equals(other.scheduleEventId)) {
			return false;
		}
		return true;
	}
}