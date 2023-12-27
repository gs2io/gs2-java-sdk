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

package io.gs2.grade.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.grade.model.*;
import io.gs2.grade.model.Status;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ApplyRankCapResult implements IResult, Serializable {
    private Status item;
    private String experienceNamespaceName;
    private io.gs2.experience.model.Status experienceStatus;

	public Status getItem() {
		return item;
	}

	public void setItem(Status item) {
		this.item = item;
	}

	public ApplyRankCapResult withItem(Status item) {
		this.item = item;
		return this;
	}

	public String getExperienceNamespaceName() {
		return experienceNamespaceName;
	}

	public void setExperienceNamespaceName(String experienceNamespaceName) {
		this.experienceNamespaceName = experienceNamespaceName;
	}

	public ApplyRankCapResult withExperienceNamespaceName(String experienceNamespaceName) {
		this.experienceNamespaceName = experienceNamespaceName;
		return this;
	}

	public io.gs2.experience.model.Status getExperienceStatus() {
		return experienceStatus;
	}

	public void setExperienceStatus(io.gs2.experience.model.Status experienceStatus) {
		this.experienceStatus = experienceStatus;
	}

	public ApplyRankCapResult withExperienceStatus(io.gs2.experience.model.Status experienceStatus) {
		this.experienceStatus = experienceStatus;
		return this;
	}

    public static ApplyRankCapResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ApplyRankCapResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Status.fromJson(data.get("item")))
            .withExperienceNamespaceName(data.get("experienceNamespaceName") == null || data.get("experienceNamespaceName").isNull() ? null : data.get("experienceNamespaceName").asText())
            .withExperienceStatus(data.get("experienceStatus") == null || data.get("experienceStatus").isNull() ? null : io.gs2.experience.model.Status.fromJson(data.get("experienceStatus")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("experienceNamespaceName", getExperienceNamespaceName());
                put("experienceStatus", getExperienceStatus() != null ? getExperienceStatus().toJson() : null);
            }}
        );
    }
}