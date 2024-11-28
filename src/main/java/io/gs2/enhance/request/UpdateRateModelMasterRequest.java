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
import io.gs2.enhance.model.BonusRate;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateRateModelMasterRequest extends Gs2BasicRequest<UpdateRateModelMasterRequest> {
    private String namespaceName;
    private String rateName;
    private String description;
    private String metadata;
    private String targetInventoryModelId;
    private String acquireExperienceSuffix;
    private String materialInventoryModelId;
    private List<String> acquireExperienceHierarchy;
    private String experienceModelId;
    private List<BonusRate> bonusRates;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateRateModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRateName() {
		return rateName;
	}
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	public UpdateRateModelMasterRequest withRateName(String rateName) {
		this.rateName = rateName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateRateModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateRateModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getTargetInventoryModelId() {
		return targetInventoryModelId;
	}
	public void setTargetInventoryModelId(String targetInventoryModelId) {
		this.targetInventoryModelId = targetInventoryModelId;
	}
	public UpdateRateModelMasterRequest withTargetInventoryModelId(String targetInventoryModelId) {
		this.targetInventoryModelId = targetInventoryModelId;
		return this;
	}
	public String getAcquireExperienceSuffix() {
		return acquireExperienceSuffix;
	}
	public void setAcquireExperienceSuffix(String acquireExperienceSuffix) {
		this.acquireExperienceSuffix = acquireExperienceSuffix;
	}
	public UpdateRateModelMasterRequest withAcquireExperienceSuffix(String acquireExperienceSuffix) {
		this.acquireExperienceSuffix = acquireExperienceSuffix;
		return this;
	}
	public String getMaterialInventoryModelId() {
		return materialInventoryModelId;
	}
	public void setMaterialInventoryModelId(String materialInventoryModelId) {
		this.materialInventoryModelId = materialInventoryModelId;
	}
	public UpdateRateModelMasterRequest withMaterialInventoryModelId(String materialInventoryModelId) {
		this.materialInventoryModelId = materialInventoryModelId;
		return this;
	}
	public List<String> getAcquireExperienceHierarchy() {
		return acquireExperienceHierarchy;
	}
	public void setAcquireExperienceHierarchy(List<String> acquireExperienceHierarchy) {
		this.acquireExperienceHierarchy = acquireExperienceHierarchy;
	}
	public UpdateRateModelMasterRequest withAcquireExperienceHierarchy(List<String> acquireExperienceHierarchy) {
		this.acquireExperienceHierarchy = acquireExperienceHierarchy;
		return this;
	}
	public String getExperienceModelId() {
		return experienceModelId;
	}
	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}
	public UpdateRateModelMasterRequest withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}
	public List<BonusRate> getBonusRates() {
		return bonusRates;
	}
	public void setBonusRates(List<BonusRate> bonusRates) {
		this.bonusRates = bonusRates;
	}
	public UpdateRateModelMasterRequest withBonusRates(List<BonusRate> bonusRates) {
		this.bonusRates = bonusRates;
		return this;
	}

    public static UpdateRateModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateRateModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRateName(data.get("rateName") == null || data.get("rateName").isNull() ? null : data.get("rateName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTargetInventoryModelId(data.get("targetInventoryModelId") == null || data.get("targetInventoryModelId").isNull() ? null : data.get("targetInventoryModelId").asText())
            .withAcquireExperienceSuffix(data.get("acquireExperienceSuffix") == null || data.get("acquireExperienceSuffix").isNull() ? null : data.get("acquireExperienceSuffix").asText())
            .withMaterialInventoryModelId(data.get("materialInventoryModelId") == null || data.get("materialInventoryModelId").isNull() ? null : data.get("materialInventoryModelId").asText())
            .withAcquireExperienceHierarchy(data.get("acquireExperienceHierarchy") == null || data.get("acquireExperienceHierarchy").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireExperienceHierarchy").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withExperienceModelId(data.get("experienceModelId") == null || data.get("experienceModelId").isNull() ? null : data.get("experienceModelId").asText())
            .withBonusRates(data.get("bonusRates") == null || data.get("bonusRates").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("bonusRates").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return BonusRate.fromJson(item);
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
                put("acquireExperienceSuffix", getAcquireExperienceSuffix());
                put("materialInventoryModelId", getMaterialInventoryModelId());
                put("acquireExperienceHierarchy", getAcquireExperienceHierarchy() == null ? null :
                    getAcquireExperienceHierarchy().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("experienceModelId", getExperienceModelId());
                put("bonusRates", getBonusRates() == null ? null :
                    getBonusRates().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}