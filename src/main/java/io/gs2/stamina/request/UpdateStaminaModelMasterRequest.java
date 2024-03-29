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
public class UpdateStaminaModelMasterRequest extends Gs2BasicRequest<UpdateStaminaModelMasterRequest> {
    private String namespaceName;
    private String staminaName;
    private String description;
    private String metadata;
    private Integer recoverIntervalMinutes;
    private Integer recoverValue;
    private Integer initialCapacity;
    private Boolean isOverflow;
    private Integer maxCapacity;
    private String maxStaminaTableName;
    private String recoverIntervalTableName;
    private String recoverValueTableName;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateStaminaModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getStaminaName() {
		return staminaName;
	}
	public void setStaminaName(String staminaName) {
		this.staminaName = staminaName;
	}
	public UpdateStaminaModelMasterRequest withStaminaName(String staminaName) {
		this.staminaName = staminaName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateStaminaModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateStaminaModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getRecoverIntervalMinutes() {
		return recoverIntervalMinutes;
	}
	public void setRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
	}
	public UpdateStaminaModelMasterRequest withRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
		this.recoverIntervalMinutes = recoverIntervalMinutes;
		return this;
	}
	public Integer getRecoverValue() {
		return recoverValue;
	}
	public void setRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
	}
	public UpdateStaminaModelMasterRequest withRecoverValue(Integer recoverValue) {
		this.recoverValue = recoverValue;
		return this;
	}
	public Integer getInitialCapacity() {
		return initialCapacity;
	}
	public void setInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
	}
	public UpdateStaminaModelMasterRequest withInitialCapacity(Integer initialCapacity) {
		this.initialCapacity = initialCapacity;
		return this;
	}
	public Boolean getIsOverflow() {
		return isOverflow;
	}
	public void setIsOverflow(Boolean isOverflow) {
		this.isOverflow = isOverflow;
	}
	public UpdateStaminaModelMasterRequest withIsOverflow(Boolean isOverflow) {
		this.isOverflow = isOverflow;
		return this;
	}
	public Integer getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public UpdateStaminaModelMasterRequest withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}
	public String getMaxStaminaTableName() {
		return maxStaminaTableName;
	}
	public void setMaxStaminaTableName(String maxStaminaTableName) {
		this.maxStaminaTableName = maxStaminaTableName;
	}
	public UpdateStaminaModelMasterRequest withMaxStaminaTableName(String maxStaminaTableName) {
		this.maxStaminaTableName = maxStaminaTableName;
		return this;
	}
	public String getRecoverIntervalTableName() {
		return recoverIntervalTableName;
	}
	public void setRecoverIntervalTableName(String recoverIntervalTableName) {
		this.recoverIntervalTableName = recoverIntervalTableName;
	}
	public UpdateStaminaModelMasterRequest withRecoverIntervalTableName(String recoverIntervalTableName) {
		this.recoverIntervalTableName = recoverIntervalTableName;
		return this;
	}
	public String getRecoverValueTableName() {
		return recoverValueTableName;
	}
	public void setRecoverValueTableName(String recoverValueTableName) {
		this.recoverValueTableName = recoverValueTableName;
	}
	public UpdateStaminaModelMasterRequest withRecoverValueTableName(String recoverValueTableName) {
		this.recoverValueTableName = recoverValueTableName;
		return this;
	}

    public static UpdateStaminaModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateStaminaModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withStaminaName(data.get("staminaName") == null || data.get("staminaName").isNull() ? null : data.get("staminaName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withRecoverIntervalMinutes(data.get("recoverIntervalMinutes") == null || data.get("recoverIntervalMinutes").isNull() ? null : data.get("recoverIntervalMinutes").intValue())
            .withRecoverValue(data.get("recoverValue") == null || data.get("recoverValue").isNull() ? null : data.get("recoverValue").intValue())
            .withInitialCapacity(data.get("initialCapacity") == null || data.get("initialCapacity").isNull() ? null : data.get("initialCapacity").intValue())
            .withIsOverflow(data.get("isOverflow") == null || data.get("isOverflow").isNull() ? null : data.get("isOverflow").booleanValue())
            .withMaxCapacity(data.get("maxCapacity") == null || data.get("maxCapacity").isNull() ? null : data.get("maxCapacity").intValue())
            .withMaxStaminaTableName(data.get("maxStaminaTableName") == null || data.get("maxStaminaTableName").isNull() ? null : data.get("maxStaminaTableName").asText())
            .withRecoverIntervalTableName(data.get("recoverIntervalTableName") == null || data.get("recoverIntervalTableName").isNull() ? null : data.get("recoverIntervalTableName").asText())
            .withRecoverValueTableName(data.get("recoverValueTableName") == null || data.get("recoverValueTableName").isNull() ? null : data.get("recoverValueTableName").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("staminaName", getStaminaName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("recoverIntervalMinutes", getRecoverIntervalMinutes());
                put("recoverValue", getRecoverValue());
                put("initialCapacity", getInitialCapacity());
                put("isOverflow", getIsOverflow());
                put("maxCapacity", getMaxCapacity());
                put("maxStaminaTableName", getMaxStaminaTableName());
                put("recoverIntervalTableName", getRecoverIntervalTableName());
                put("recoverValueTableName", getRecoverValueTableName());
            }}
        );
    }
}