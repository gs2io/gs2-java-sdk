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

package io.gs2.enhance.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.enhance.model.UnleashRateEntryModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateUnleashRateModelMasterRequest extends Gs2BasicRequest<UpdateUnleashRateModelMasterRequest> {
    private String namespaceName;
    private String rateName;
    private String description;
    private String metadata;
    private String targetInventoryModelId;
    private String gradeModelId;
    private List<UnleashRateEntryModel> gradeEntries;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateUnleashRateModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRateName() {
		return rateName;
	}
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	public UpdateUnleashRateModelMasterRequest withRateName(String rateName) {
		this.rateName = rateName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateUnleashRateModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateUnleashRateModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getTargetInventoryModelId() {
		return targetInventoryModelId;
	}
	public void setTargetInventoryModelId(String targetInventoryModelId) {
		this.targetInventoryModelId = targetInventoryModelId;
	}
	public UpdateUnleashRateModelMasterRequest withTargetInventoryModelId(String targetInventoryModelId) {
		this.targetInventoryModelId = targetInventoryModelId;
		return this;
	}
	public String getGradeModelId() {
		return gradeModelId;
	}
	public void setGradeModelId(String gradeModelId) {
		this.gradeModelId = gradeModelId;
	}
	public UpdateUnleashRateModelMasterRequest withGradeModelId(String gradeModelId) {
		this.gradeModelId = gradeModelId;
		return this;
	}
	public List<UnleashRateEntryModel> getGradeEntries() {
		return gradeEntries;
	}
	public void setGradeEntries(List<UnleashRateEntryModel> gradeEntries) {
		this.gradeEntries = gradeEntries;
	}
	public UpdateUnleashRateModelMasterRequest withGradeEntries(List<UnleashRateEntryModel> gradeEntries) {
		this.gradeEntries = gradeEntries;
		return this;
	}

    public static UpdateUnleashRateModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateUnleashRateModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRateName(data.get("rateName") == null || data.get("rateName").isNull() ? null : data.get("rateName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTargetInventoryModelId(data.get("targetInventoryModelId") == null || data.get("targetInventoryModelId").isNull() ? null : data.get("targetInventoryModelId").asText())
            .withGradeModelId(data.get("gradeModelId") == null || data.get("gradeModelId").isNull() ? null : data.get("gradeModelId").asText())
            .withGradeEntries(data.get("gradeEntries") == null || data.get("gradeEntries").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("gradeEntries").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return UnleashRateEntryModel.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("rateName", getRateName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("targetInventoryModelId", getTargetInventoryModelId());
                put("gradeModelId", getGradeModelId());
                put("gradeEntries", getGradeEntries() == null ? null :
                    getGradeEntries().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}