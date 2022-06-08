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

package io.gs2.formation.request;

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
public class UpdateMoldModelMasterRequest extends Gs2BasicRequest<UpdateMoldModelMasterRequest> {
    private String namespaceName;
    private String moldName;
    private String description;
    private String metadata;
    private String formModelName;
    private Integer initialMaxCapacity;
    private Integer maxCapacity;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateMoldModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getMoldName() {
		return moldName;
	}
	public void setMoldName(String moldName) {
		this.moldName = moldName;
	}
	public UpdateMoldModelMasterRequest withMoldName(String moldName) {
		this.moldName = moldName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateMoldModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateMoldModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getFormModelName() {
		return formModelName;
	}
	public void setFormModelName(String formModelName) {
		this.formModelName = formModelName;
	}
	public UpdateMoldModelMasterRequest withFormModelName(String formModelName) {
		this.formModelName = formModelName;
		return this;
	}
	public Integer getInitialMaxCapacity() {
		return initialMaxCapacity;
	}
	public void setInitialMaxCapacity(Integer initialMaxCapacity) {
		this.initialMaxCapacity = initialMaxCapacity;
	}
	public UpdateMoldModelMasterRequest withInitialMaxCapacity(Integer initialMaxCapacity) {
		this.initialMaxCapacity = initialMaxCapacity;
		return this;
	}
	public Integer getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public UpdateMoldModelMasterRequest withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}

    public static UpdateMoldModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateMoldModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withMoldName(data.get("moldName") == null || data.get("moldName").isNull() ? null : data.get("moldName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withFormModelName(data.get("formModelName") == null || data.get("formModelName").isNull() ? null : data.get("formModelName").asText())
            .withInitialMaxCapacity(data.get("initialMaxCapacity") == null || data.get("initialMaxCapacity").isNull() ? null : data.get("initialMaxCapacity").intValue())
            .withMaxCapacity(data.get("maxCapacity") == null || data.get("maxCapacity").isNull() ? null : data.get("maxCapacity").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("moldName", getMoldName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("formModelName", getFormModelName());
                put("initialMaxCapacity", getInitialMaxCapacity());
                put("maxCapacity", getMaxCapacity());
            }}
        );
    }
}