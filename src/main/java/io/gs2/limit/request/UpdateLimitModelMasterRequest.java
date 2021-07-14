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

package io.gs2.limit.request;

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
public class UpdateLimitModelMasterRequest extends Gs2BasicRequest<UpdateLimitModelMasterRequest> {
    private String namespaceName;
    private String limitName;
    private String description;
    private String metadata;
    private String resetType;
    private Integer resetDayOfMonth;
    private String resetDayOfWeek;
    private Integer resetHour;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public UpdateLimitModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getLimitName() {
		return limitName;
	}

	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}

	public UpdateLimitModelMasterRequest withLimitName(String limitName) {
		this.limitName = limitName;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UpdateLimitModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public UpdateLimitModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public String getResetType() {
		return resetType;
	}

	public void setResetType(String resetType) {
		this.resetType = resetType;
	}

	public UpdateLimitModelMasterRequest withResetType(String resetType) {
		this.resetType = resetType;
		return this;
	}

	public Integer getResetDayOfMonth() {
		return resetDayOfMonth;
	}

	public void setResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
	}

	public UpdateLimitModelMasterRequest withResetDayOfMonth(Integer resetDayOfMonth) {
		this.resetDayOfMonth = resetDayOfMonth;
		return this;
	}

	public String getResetDayOfWeek() {
		return resetDayOfWeek;
	}

	public void setResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
	}

	public UpdateLimitModelMasterRequest withResetDayOfWeek(String resetDayOfWeek) {
		this.resetDayOfWeek = resetDayOfWeek;
		return this;
	}

	public Integer getResetHour() {
		return resetHour;
	}

	public void setResetHour(Integer resetHour) {
		this.resetHour = resetHour;
	}

	public UpdateLimitModelMasterRequest withResetHour(Integer resetHour) {
		this.resetHour = resetHour;
		return this;
	}

    public static UpdateLimitModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateLimitModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withLimitName(data.get("limitName") == null || data.get("limitName").isNull() ? null : data.get("limitName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withResetType(data.get("resetType") == null || data.get("resetType").isNull() ? null : data.get("resetType").asText())
            .withResetDayOfMonth(data.get("resetDayOfMonth") == null || data.get("resetDayOfMonth").isNull() ? null : data.get("resetDayOfMonth").intValue())
            .withResetDayOfWeek(data.get("resetDayOfWeek") == null || data.get("resetDayOfWeek").isNull() ? null : data.get("resetDayOfWeek").asText())
            .withResetHour(data.get("resetHour") == null || data.get("resetHour").isNull() ? null : data.get("resetHour").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("limitName", getLimitName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("resetType", getResetType());
                put("resetDayOfMonth", getResetDayOfMonth());
                put("resetDayOfWeek", getResetDayOfWeek());
                put("resetHour", getResetHour());
            }}
        );
    }
}