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
public class AcceptVersion implements IModel, Serializable, Comparable<AcceptVersion> {
	private String acceptVersionId;
	private String versionName;
	private String userId;
	private Version version;
	private Long createdAt;
	private Long updatedAt;

	public String getAcceptVersionId() {
		return acceptVersionId;
	}

	public void setAcceptVersionId(String acceptVersionId) {
		this.acceptVersionId = acceptVersionId;
	}

	public AcceptVersion withAcceptVersionId(String acceptVersionId) {
		this.acceptVersionId = acceptVersionId;
		return this;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public AcceptVersion withVersionName(String versionName) {
		this.versionName = versionName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public AcceptVersion withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public AcceptVersion withVersion(Version version) {
		this.version = version;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public AcceptVersion withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public AcceptVersion withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static AcceptVersion fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AcceptVersion()
            .withAcceptVersionId(data.get("acceptVersionId") == null || data.get("acceptVersionId").isNull() ? null : data.get("acceptVersionId").asText())
            .withVersionName(data.get("versionName") == null || data.get("versionName").isNull() ? null : data.get("versionName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withVersion(data.get("version") == null || data.get("version").isNull() ? null : Version.fromJson(data.get("version")))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("acceptVersionId", getAcceptVersionId());
                put("versionName", getVersionName());
                put("userId", getUserId());
                put("version", getVersion() != null ? getVersion().toJson() : null);
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(AcceptVersion o) {
		return acceptVersionId.compareTo(o.acceptVersionId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.acceptVersionId == null) ? 0 : this.acceptVersionId.hashCode());
        result = prime * result + ((this.versionName == null) ? 0 : this.versionName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
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
		AcceptVersion other = (AcceptVersion) o;
		if (acceptVersionId == null) {
			return other.acceptVersionId == null;
		} else if (!acceptVersionId.equals(other.acceptVersionId)) {
			return false;
		}
		if (versionName == null) {
			return other.versionName == null;
		} else if (!versionName.equals(other.versionName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (version == null) {
			return other.version == null;
		} else if (!version.equals(other.version)) {
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