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

package io.gs2.datastore.model;

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
public class DataObjectHistory implements IModel, Serializable, Comparable<DataObjectHistory> {
	private String dataObjectHistoryId;
	private String dataObjectName;
	private String generation;
	private Long contentLength;
	private Long createdAt;

	public String getDataObjectHistoryId() {
		return dataObjectHistoryId;
	}

	public void setDataObjectHistoryId(String dataObjectHistoryId) {
		this.dataObjectHistoryId = dataObjectHistoryId;
	}

	public DataObjectHistory withDataObjectHistoryId(String dataObjectHistoryId) {
		this.dataObjectHistoryId = dataObjectHistoryId;
		return this;
	}

	public String getDataObjectName() {
		return dataObjectName;
	}

	public void setDataObjectName(String dataObjectName) {
		this.dataObjectName = dataObjectName;
	}

	public DataObjectHistory withDataObjectName(String dataObjectName) {
		this.dataObjectName = dataObjectName;
		return this;
	}

	public String getGeneration() {
		return generation;
	}

	public void setGeneration(String generation) {
		this.generation = generation;
	}

	public DataObjectHistory withGeneration(String generation) {
		this.generation = generation;
		return this;
	}

	public Long getContentLength() {
		return contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	public DataObjectHistory withContentLength(Long contentLength) {
		this.contentLength = contentLength;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public DataObjectHistory withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public static DataObjectHistory fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DataObjectHistory()
            .withDataObjectHistoryId(data.get("dataObjectHistoryId") == null || data.get("dataObjectHistoryId").isNull() ? null : data.get("dataObjectHistoryId").asText())
            .withDataObjectName(data.get("dataObjectName") == null || data.get("dataObjectName").isNull() ? null : data.get("dataObjectName").asText())
            .withGeneration(data.get("generation") == null || data.get("generation").isNull() ? null : data.get("generation").asText())
            .withContentLength(data.get("contentLength") == null || data.get("contentLength").isNull() ? null : data.get("contentLength").longValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("dataObjectHistoryId", getDataObjectHistoryId());
                put("dataObjectName", getDataObjectName());
                put("generation", getGeneration());
                put("contentLength", getContentLength());
                put("createdAt", getCreatedAt());
            }}
        );
    }

	@Override
	public int compareTo(DataObjectHistory o) {
		return dataObjectHistoryId.compareTo(o.dataObjectHistoryId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.dataObjectHistoryId == null) ? 0 : this.dataObjectHistoryId.hashCode());
        result = prime * result + ((this.dataObjectName == null) ? 0 : this.dataObjectName.hashCode());
        result = prime * result + ((this.generation == null) ? 0 : this.generation.hashCode());
        result = prime * result + ((this.contentLength == null) ? 0 : this.contentLength.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		DataObjectHistory other = (DataObjectHistory) o;
		if (dataObjectHistoryId == null) {
			return other.dataObjectHistoryId == null;
		} else if (!dataObjectHistoryId.equals(other.dataObjectHistoryId)) {
			return false;
		}
		if (dataObjectName == null) {
			return other.dataObjectName == null;
		} else if (!dataObjectName.equals(other.dataObjectName)) {
			return false;
		}
		if (generation == null) {
			return other.generation == null;
		} else if (!generation.equals(other.generation)) {
			return false;
		}
		if (contentLength == null) {
			return other.contentLength == null;
		} else if (!contentLength.equals(other.contentLength)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		return true;
	}
}