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

package io.gs2.freeze.model;

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
public class Output implements IModel, Serializable, Comparable<Output> {
	private String outputId;
	private String name;
	private String text;
	private Long createdAt;
	private Long revision;
	public String getOutputId() {
		return outputId;
	}
	public void setOutputId(String outputId) {
		this.outputId = outputId;
	}
	public Output withOutputId(String outputId) {
		this.outputId = outputId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Output withName(String name) {
		this.name = name;
		return this;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Output withText(String text) {
		this.text = text;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Output withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Output withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Output fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Output()
            .withOutputId(data.get("outputId") == null || data.get("outputId").isNull() ? null : data.get("outputId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withText(data.get("text") == null || data.get("text").isNull() ? null : data.get("text").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("outputId", getOutputId());
                put("name", getName());
                put("text", getText());
                put("createdAt", getCreatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Output o) {
		return outputId.compareTo(o.outputId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.outputId == null) ? 0 : this.outputId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.text == null) ? 0 : this.text.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		Output other = (Output) o;
		if (outputId == null) {
			return other.outputId == null;
		} else if (!outputId.equals(other.outputId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (text == null) {
			return other.text == null;
		} else if (!text.equals(other.text)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
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