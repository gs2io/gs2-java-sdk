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

package io.gs2.dictionary.model;

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
public class Entry implements IModel, Serializable, Comparable<Entry> {
	private String entryId;
	private String userId;
	private String name;
	private Long acquiredAt;
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public Entry withEntryId(String entryId) {
		this.entryId = entryId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Entry withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Entry withName(String name) {
		this.name = name;
		return this;
	}
	public Long getAcquiredAt() {
		return acquiredAt;
	}
	public void setAcquiredAt(Long acquiredAt) {
		this.acquiredAt = acquiredAt;
	}
	public Entry withAcquiredAt(Long acquiredAt) {
		this.acquiredAt = acquiredAt;
		return this;
	}

    public static Entry fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Entry()
            .withEntryId(data.get("entryId") == null || data.get("entryId").isNull() ? null : data.get("entryId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withAcquiredAt(data.get("acquiredAt") == null || data.get("acquiredAt").isNull() ? null : data.get("acquiredAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("entryId", getEntryId());
                put("userId", getUserId());
                put("name", getName());
                put("acquiredAt", getAcquiredAt());
            }}
        );
    }

	@Override
	public int compareTo(Entry o) {
		return entryId.compareTo(o.entryId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.entryId == null) ? 0 : this.entryId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.acquiredAt == null) ? 0 : this.acquiredAt.hashCode());
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
		Entry other = (Entry) o;
		if (entryId == null) {
			return other.entryId == null;
		} else if (!entryId.equals(other.entryId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (acquiredAt == null) {
			return other.acquiredAt == null;
		} else if (!acquiredAt.equals(other.acquiredAt)) {
			return false;
		}
		return true;
	}
}