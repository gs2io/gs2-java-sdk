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
public class CreateRateModelMasterRequest extends Gs2BasicRequest<CreateRateModelMasterRequest> {
    private String namespaceName;
    private String name;
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

	public CreateRateModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CreateRateModelMasterRequest withName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CreateRateModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public CreateRateModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public String getTargetInventoryModelId() {
		return targetInventoryModelId;
	}

	public void setTargetInventoryModelId(String targetInventoryModelId) {
		this.targetInventoryModelId = targetInventoryModelId;
	}

	public CreateRateModelMasterRequest withTargetInventoryModelId(String targetInventoryModelId) {
		this.targetInventoryModelId = targetInventoryModelId;
		return this;
	}

	public String getAcquireExperienceSuffix() {
		return acquireExperienceSuffix;
	}

	public void setAcquireExperienceSuffix(String acquireExperienceSuffix) {
		this.acquireExperienceSuffix = acquireExperienceSuffix;
	}

	public CreateRateModelMasterRequest withAcquireExperienceSuffix(String acquireExperienceSuffix) {
		this.acquireExperienceSuffix = acquireExperienceSuffix;
		return this;
	}

	public String getMaterialInventoryModelId() {
		return materialInventoryModelId;
	}

	public void setMaterialInventoryModelId(String materialInventoryModelId) {
		this.materialInventoryModelId = materialInventoryModelId;
	}

	public CreateRateModelMasterRequest withMaterialInventoryModelId(String materialInventoryModelId) {
		this.materialInventoryModelId = materialInventoryModelId;
		return this;
	}

	public List<String> getAcquireExperienceHierarchy() {
		return acquireExperienceHierarchy;
	}

	public void setAcquireExperienceHierarchy(List<String> acquireExperienceHierarchy) {
		this.acquireExperienceHierarchy = acquireExperienceHierarchy;
	}

	public CreateRateModelMasterRequest withAcquireExperienceHierarchy(List<String> acquireExperienceHierarchy) {
		this.acquireExperienceHierarchy = acquireExperienceHierarchy;
		return this;
	}

	public String getExperienceModelId() {
		return experienceModelId;
	}

	public void setExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
	}

	public CreateRateModelMasterRequest withExperienceModelId(String experienceModelId) {
		this.experienceModelId = experienceModelId;
		return this;
	}

	public List<BonusRate> getBonusRates() {
		return bonusRates;
	}

	public void setBonusRates(List<BonusRate> bonusRates) {
		this.bonusRates = bonusRates;
	}

	public CreateRateModelMasterRequest withBonusRates(List<BonusRate> bonusRates) {
		this.bonusRates = bonusRates;
		return this;
	}

    public static CreateRateModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateRateModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withTargetInventoryModelId(data.get("targetInventoryModelId") == null || data.get("targetInventoryModelId").isNull() ? null : data.get("targetInventoryModelId").asText())
            .withAcquireExperienceSuffix(data.get("acquireExperienceSuffix") == null || data.get("acquireExperienceSuffix").isNull() ? null : data.get("acquireExperienceSuffix").asText())
            .withMaterialInventoryModelId(data.get("materialInventoryModelId") == null || data.get("materialInventoryModelId").isNull() ? null : data.get("materialInventoryModelId").asText())
            .withAcquireExperienceHierarchy(data.get("acquireExperienceHierarchy") == null || data.get("acquireExperienceHierarchy").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("acquireExperienceHierarchy").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withExperienceModelId(data.get("experienceModelId") == null || data.get("experienceModelId").isNull() ? null : data.get("experienceModelId").asText())
            .withBonusRates(data.get("bonusRates") == null || data.get("bonusRates").isNull() ? new ArrayList<BonusRate>() :
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
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("targetInventoryModelId", getTargetInventoryModelId());
                put("acquireExperienceSuffix", getAcquireExperienceSuffix());
                put("materialInventoryModelId", getMaterialInventoryModelId());
                put("acquireExperienceHierarchy", getAcquireExperienceHierarchy() == null ? new ArrayList<String>() :
                    getAcquireExperienceHierarchy().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("experienceModelId", getExperienceModelId());
                put("bonusRates", getBonusRates() == null ? new ArrayList<BonusRate>() :
                    getBonusRates().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}