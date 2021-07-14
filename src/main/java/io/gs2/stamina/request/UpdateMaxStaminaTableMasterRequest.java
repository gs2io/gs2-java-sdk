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

package io.gs2.stamina.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateMaxStaminaTableMasterRequest extends Gs2BasicRequest<UpdateMaxStaminaTableMasterRequest> {
    private String namespaceName;
    private String maxStaminaTableName;
    private String description;
    private String metadata;
    private String experienceModelId;
    private List<Integer> values;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public UpdateMaxStaminaTableMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getMaxStaminaTableName() {
		return maxStaminaTableName;
	}

	public void setMaxStaminaTableName(String maxStaminaTableName) {
		this.maxStaminaTableName = maxStaminaTableName;
	}

	public UpdateMaxStaminaTableMasterRequest withMaxStaminaTableName(String maxStaminaTableName) {
		this.maxStaminaTableName = maxStaminaTableName;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UpdateMaxStaminaTableMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public UpdateMaxStaminaTableMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public String getExperienceModelId() {
		return experienceModelId;
	}

	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}

	public UpdateMaxStaminaTableMasterRequest withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}

	public List<Integer> getValues() {
		return values;
	}

	public void setValues(List<Integer> values) {
		this.values = values;
	}

	public UpdateMaxStaminaTableMasterRequest withValues(List<Integer> values) {
		this.values = values;
		return this;
	}

    public static UpdateMaxStaminaTableMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateMaxStaminaTableMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withMaxStaminaTableName(data.get("maxStaminaTableName") == null || data.get("maxStaminaTableName").isNull() ? null : data.get("maxStaminaTableName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withExperienceModelId(data.get("experienceModelId") == null || data.get("experienceModelId").isNull() ? null : data.get("experienceModelId").asText())
            .withValues(data.get("values") == null || data.get("values").isNull() ? new ArrayList<Integer>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("values").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.intValue();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("maxStaminaTableName", getMaxStaminaTableName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("experienceModelId", getExperienceModelId());
                put("values", getValues() == null ? new ArrayList<Integer>() :
                    getValues().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}