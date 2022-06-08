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
public class EntryModel implements IModel, Serializable, Comparable<EntryModel> {
	private String entryModelId;
	private String name;
	private String metadata;
	public String getEntryModelId() {
		return entryModelId;
	}
	public void setEntryModelId(String entryModelId) {
		this.entryModelId = entryModelId;
	}
	public EntryModel withEntryModelId(String entryModelId) {
		this.entryModelId = entryModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EntryModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public EntryModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

    public static EntryModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new EntryModel()
            .withEntryModelId(data.get("entryModelId") == null || data.get("entryModelId").isNull() ? null : data.get("entryModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("entryModelId", getEntryModelId());
                put("name", getName());
                put("metadata", getMetadata());
            }}
        );
    }

	@Override
	public int compareTo(EntryModel o) {
		return entryModelId.compareTo(o.entryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.entryModelId == null) ? 0 : this.entryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
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
		EntryModel other = (EntryModel) o;
		if (entryModelId == null) {
			return other.entryModelId == null;
		} else if (!entryModelId.equals(other.entryModelId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		return true;
	}
}