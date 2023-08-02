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

package io.gs2.experience.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.experience.model.AcquireActionRate;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateExperienceModelMasterRequest extends Gs2BasicRequest<CreateExperienceModelMasterRequest> {
    private String namespaceName;
    private String name;
    private String description;
    private String metadata;
    private Long defaultExperience;
    private Long defaultRankCap;
    private Long maxRankCap;
    private String rankThresholdName;
    private List<AcquireActionRate> acquireActionRates;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateExperienceModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateExperienceModelMasterRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateExperienceModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CreateExperienceModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Long getDefaultExperience() {
		return defaultExperience;
	}
	public void setDefaultExperience(Long defaultExperience) {
		this.defaultExperience = defaultExperience;
	}
	public CreateExperienceModelMasterRequest withDefaultExperience(Long defaultExperience) {
		this.defaultExperience = defaultExperience;
		return this;
	}
	public Long getDefaultRankCap() {
		return defaultRankCap;
	}
	public void setDefaultRankCap(Long defaultRankCap) {
		this.defaultRankCap = defaultRankCap;
	}
	public CreateExperienceModelMasterRequest withDefaultRankCap(Long defaultRankCap) {
		this.defaultRankCap = defaultRankCap;
		return this;
	}
	public Long getMaxRankCap() {
		return maxRankCap;
	}
	public void setMaxRankCap(Long maxRankCap) {
		this.maxRankCap = maxRankCap;
	}
	public CreateExperienceModelMasterRequest withMaxRankCap(Long maxRankCap) {
		this.maxRankCap = maxRankCap;
		return this;
	}
	public String getRankThresholdName() {
		return rankThresholdName;
	}
	public void setRankThresholdName(String rankThresholdName) {
		this.rankThresholdName = rankThresholdName;
	}
	public CreateExperienceModelMasterRequest withRankThresholdName(String rankThresholdName) {
		this.rankThresholdName = rankThresholdName;
		return this;
	}
	public List<AcquireActionRate> getAcquireActionRates() {
		return acquireActionRates;
	}
	public void setAcquireActionRates(List<AcquireActionRate> acquireActionRates) {
		this.acquireActionRates = acquireActionRates;
	}
	public CreateExperienceModelMasterRequest withAcquireActionRates(List<AcquireActionRate> acquireActionRates) {
		this.acquireActionRates = acquireActionRates;
		return this;
	}

    public static CreateExperienceModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateExperienceModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withDefaultExperience(data.get("defaultExperience") == null || data.get("defaultExperience").isNull() ? null : data.get("defaultExperience").longValue())
            .withDefaultRankCap(data.get("defaultRankCap") == null || data.get("defaultRankCap").isNull() ? null : data.get("defaultRankCap").longValue())
            .withMaxRankCap(data.get("maxRankCap") == null || data.get("maxRankCap").isNull() ? null : data.get("maxRankCap").longValue())
            .withRankThresholdName(data.get("rankThresholdName") == null || data.get("rankThresholdName").isNull() ? null : data.get("rankThresholdName").asText())
            .withAcquireActionRates(data.get("acquireActionRates") == null || data.get("acquireActionRates").isNull() ? new ArrayList<AcquireActionRate>() :
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
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("defaultExperience", getDefaultExperience());
                put("defaultRankCap", getDefaultRankCap());
                put("maxRankCap", getMaxRankCap());
                put("rankThresholdName", getRankThresholdName());
                put("acquireActionRates", getAcquireActionRates() == null ? new ArrayList<AcquireActionRate>() :
                    getAcquireActionRates().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}