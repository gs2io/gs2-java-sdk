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

package io.gs2.grade.model;

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
public class GradeEntryModel implements IModel, Serializable {
	private String metadata;
	private Long rankCapValue;
	private String propertyIdRegex;
	private String gradeUpPropertyIdRegex;
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public GradeEntryModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getRankCapValue() {
		return rankCapValue;
	}
	public void setRankCapValue(Long rankCapValue) {
		this.rankCapValue = rankCapValue;
	}
	public GradeEntryModel withRankCapValue(Long rankCapValue) {
		this.rankCapValue = rankCapValue;
		return this;
	}
	public String getPropertyIdRegex() {
		return propertyIdRegex;
	}
	public void setPropertyIdRegex(String propertyIdRegex) {
		this.propertyIdRegex = propertyIdRegex;
	}
	public GradeEntryModel withPropertyIdRegex(String propertyIdRegex) {
		this.propertyIdRegex = propertyIdRegex;
		return this;
	}
	public String getGradeUpPropertyIdRegex() {
		return gradeUpPropertyIdRegex;
	}
	public void setGradeUpPropertyIdRegex(String gradeUpPropertyIdRegex) {
		this.gradeUpPropertyIdRegex = gradeUpPropertyIdRegex;
	}
	public GradeEntryModel withGradeUpPropertyIdRegex(String gradeUpPropertyIdRegex) {
		this.gradeUpPropertyIdRegex = gradeUpPropertyIdRegex;
		return this;
	}

    public static GradeEntryModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GradeEntryModel()
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withRankCapValue(data.get("rankCapValue") == null || data.get("rankCapValue").isNull() ? null : data.get("rankCapValue").longValue())
            .withPropertyIdRegex(data.get("propertyIdRegex") == null || data.get("propertyIdRegex").isNull() ? null : data.get("propertyIdRegex").asText())
            .withGradeUpPropertyIdRegex(data.get("gradeUpPropertyIdRegex") == null || data.get("gradeUpPropertyIdRegex").isNull() ? null : data.get("gradeUpPropertyIdRegex").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("metadata", getMetadata());
                put("rankCapValue", getRankCapValue());
                put("propertyIdRegex", getPropertyIdRegex());
                put("gradeUpPropertyIdRegex", getGradeUpPropertyIdRegex());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.rankCapValue == null) ? 0 : this.rankCapValue.hashCode());
        result = prime * result + ((this.propertyIdRegex == null) ? 0 : this.propertyIdRegex.hashCode());
        result = prime * result + ((this.gradeUpPropertyIdRegex == null) ? 0 : this.gradeUpPropertyIdRegex.hashCode());
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
		GradeEntryModel other = (GradeEntryModel) o;
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (rankCapValue == null) {
			return other.rankCapValue == null;
		} else if (!rankCapValue.equals(other.rankCapValue)) {
			return false;
		}
		if (propertyIdRegex == null) {
			return other.propertyIdRegex == null;
		} else if (!propertyIdRegex.equals(other.propertyIdRegex)) {
			return false;
		}
		if (gradeUpPropertyIdRegex == null) {
			return other.gradeUpPropertyIdRegex == null;
		} else if (!gradeUpPropertyIdRegex.equals(other.gradeUpPropertyIdRegex)) {
			return false;
		}
		return true;
	}
}