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
public class GradeModel implements IModel, Serializable, Comparable<GradeModel> {
	private String gradeModelId;
	private String name;
	private String metadata;
	private List<DefaultGradeModel> defaultGrades;
	private String experienceModelId;
	private List<GradeEntryModel> gradeEntries;
	private List<AcquireActionRate> acquireActionRates;
	public String getGradeModelId() {
		return gradeModelId;
	}
	public void setGradeModelId(String gradeModelId) {
		this.gradeModelId = gradeModelId;
	}
	public GradeModel withGradeModelId(String gradeModelId) {
		this.gradeModelId = gradeModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GradeModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public GradeModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<DefaultGradeModel> getDefaultGrades() {
		return defaultGrades;
	}
	public void setDefaultGrades(List<DefaultGradeModel> defaultGrades) {
		this.defaultGrades = defaultGrades;
	}
	public GradeModel withDefaultGrades(List<DefaultGradeModel> defaultGrades) {
		this.defaultGrades = defaultGrades;
		return this;
	}
	public String getExperienceModelId() {
		return experienceModelId;
	}
	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}
	public GradeModel withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}
	public List<GradeEntryModel> getGradeEntries() {
		return gradeEntries;
	}
	public void setGradeEntries(List<GradeEntryModel> gradeEntries) {
		this.gradeEntries = gradeEntries;
	}
	public GradeModel withGradeEntries(List<GradeEntryModel> gradeEntries) {
		this.gradeEntries = gradeEntries;
		return this;
	}
	public List<AcquireActionRate> getAcquireActionRates() {
		return acquireActionRates;
	}
	public void setAcquireActionRates(List<AcquireActionRate> acquireActionRates) {
		this.acquireActionRates = acquireActionRates;
	}
	public GradeModel withAcquireActionRates(List<AcquireActionRate> acquireActionRates) {
		this.acquireActionRates = acquireActionRates;
		return this;
	}

    public static GradeModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GradeModel()
            .withGradeModelId(data.get("gradeModelId") == null || data.get("gradeModelId").isNull() ? null : data.get("gradeModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withDefaultGrades(data.get("defaultGrades") == null || data.get("defaultGrades").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("defaultGrades").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return DefaultGradeModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withExperienceModelId(data.get("experienceModelId") == null || data.get("experienceModelId").isNull() ? null : data.get("experienceModelId").asText())
            .withGradeEntries(data.get("gradeEntries") == null || data.get("gradeEntries").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("gradeEntries").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return GradeEntryModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withAcquireActionRates(data.get("acquireActionRates") == null || data.get("acquireActionRates").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireActionRates").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireActionRate.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("gradeModelId", getGradeModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("defaultGrades", getDefaultGrades() == null ? null :
                    getDefaultGrades().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("experienceModelId", getExperienceModelId());
                put("gradeEntries", getGradeEntries() == null ? null :
                    getGradeEntries().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("acquireActionRates", getAcquireActionRates() == null ? null :
                    getAcquireActionRates().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(GradeModel o) {
		return gradeModelId.compareTo(o.gradeModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.gradeModelId == null) ? 0 : this.gradeModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.defaultGrades == null) ? 0 : this.defaultGrades.hashCode());
        result = prime * result + ((this.experienceModelId == null) ? 0 : this.experienceModelId.hashCode());
        result = prime * result + ((this.gradeEntries == null) ? 0 : this.gradeEntries.hashCode());
        result = prime * result + ((this.acquireActionRates == null) ? 0 : this.acquireActionRates.hashCode());
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
		GradeModel other = (GradeModel) o;
		if (gradeModelId == null) {
			return other.gradeModelId == null;
		} else if (!gradeModelId.equals(other.gradeModelId)) {
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
		if (defaultGrades == null) {
			return other.defaultGrades == null;
		} else if (!defaultGrades.equals(other.defaultGrades)) {
			return false;
		}
		if (experienceModelId == null) {
			return other.experienceModelId == null;
		} else if (!experienceModelId.equals(other.experienceModelId)) {
			return false;
		}
		if (gradeEntries == null) {
			return other.gradeEntries == null;
		} else if (!gradeEntries.equals(other.gradeEntries)) {
			return false;
		}
		if (acquireActionRates == null) {
			return other.acquireActionRates == null;
		} else if (!acquireActionRates.equals(other.acquireActionRates)) {
			return false;
		}
		return true;
	}
}