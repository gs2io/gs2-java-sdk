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

package io.gs2.grade.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.grade.model.DefaultGradeModel;
import io.gs2.grade.model.GradeEntryModel;
import io.gs2.grade.model.AcquireActionRate;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateGradeModelMasterRequest extends Gs2BasicRequest<UpdateGradeModelMasterRequest> {
    private String namespaceName;
    private String gradeName;
    private String description;
    private String metadata;
    private List<DefaultGradeModel> defaultGrades;
    private String experienceModelId;
    private List<GradeEntryModel> gradeEntries;
    private List<AcquireActionRate> acquireActionRates;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateGradeModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public UpdateGradeModelMasterRequest withGradeName(String gradeName) {
		this.gradeName = gradeName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateGradeModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateGradeModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<DefaultGradeModel> getDefaultGrades() {
		return defaultGrades;
	}
	public void setDefaultGrades(List<DefaultGradeModel> defaultGrades) {
		this.defaultGrades = defaultGrades;
	}
	public UpdateGradeModelMasterRequest withDefaultGrades(List<DefaultGradeModel> defaultGrades) {
		this.defaultGrades = defaultGrades;
		return this;
	}
	public String getExperienceModelId() {
		return experienceModelId;
	}
	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}
	public UpdateGradeModelMasterRequest withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}
	public List<GradeEntryModel> getGradeEntries() {
		return gradeEntries;
	}
	public void setGradeEntries(List<GradeEntryModel> gradeEntries) {
		this.gradeEntries = gradeEntries;
	}
	public UpdateGradeModelMasterRequest withGradeEntries(List<GradeEntryModel> gradeEntries) {
		this.gradeEntries = gradeEntries;
		return this;
	}
	public List<AcquireActionRate> getAcquireActionRates() {
		return acquireActionRates;
	}
	public void setAcquireActionRates(List<AcquireActionRate> acquireActionRates) {
		this.acquireActionRates = acquireActionRates;
	}
	public UpdateGradeModelMasterRequest withAcquireActionRates(List<AcquireActionRate> acquireActionRates) {
		this.acquireActionRates = acquireActionRates;
		return this;
	}

    public static UpdateGradeModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateGradeModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withGradeName(data.get("gradeName") == null || data.get("gradeName").isNull() ? null : data.get("gradeName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
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
                put("namespaceName", getNamespaceName());
                put("gradeName", getGradeName());
                put("description", getDescription());
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
}