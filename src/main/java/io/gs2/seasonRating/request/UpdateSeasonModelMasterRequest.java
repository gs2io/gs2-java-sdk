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

package io.gs2.seasonRating.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.seasonRating.model.TierModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateSeasonModelMasterRequest extends Gs2BasicRequest<UpdateSeasonModelMasterRequest> {
    private String namespaceName;
    private String seasonName;
    private String description;
    private String metadata;
    private List<TierModel> tiers;
    private String experienceModelId;
    private String challengePeriodEventId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateSeasonModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	public UpdateSeasonModelMasterRequest withSeasonName(String seasonName) {
		this.seasonName = seasonName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateSeasonModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateSeasonModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<TierModel> getTiers() {
		return tiers;
	}
	public void setTiers(List<TierModel> tiers) {
		this.tiers = tiers;
	}
	public UpdateSeasonModelMasterRequest withTiers(List<TierModel> tiers) {
		this.tiers = tiers;
		return this;
	}
	public String getExperienceModelId() {
		return experienceModelId;
	}
	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}
	public UpdateSeasonModelMasterRequest withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}
	public String getChallengePeriodEventId() {
		return challengePeriodEventId;
	}
	public void setChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
	}
	public UpdateSeasonModelMasterRequest withChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
		return this;
	}

    public static UpdateSeasonModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateSeasonModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withSeasonName(data.get("seasonName") == null || data.get("seasonName").isNull() ? null : data.get("seasonName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTiers(data.get("tiers") == null || data.get("tiers").isNull() ? new ArrayList<TierModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("tiers").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return TierModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withExperienceModelId(data.get("experienceModelId") == null || data.get("experienceModelId").isNull() ? null : data.get("experienceModelId").asText())
            .withChallengePeriodEventId(data.get("challengePeriodEventId") == null || data.get("challengePeriodEventId").isNull() ? null : data.get("challengePeriodEventId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("seasonName", getSeasonName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("tiers", getTiers() == null ? new ArrayList<TierModel>() :
                    getTiers().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("experienceModelId", getExperienceModelId());
                put("challengePeriodEventId", getChallengePeriodEventId());
            }}
        );
    }
}