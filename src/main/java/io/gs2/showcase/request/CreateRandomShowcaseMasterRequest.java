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

package io.gs2.showcase.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.showcase.model.VerifyAction;
import io.gs2.showcase.model.ConsumeAction;
import io.gs2.showcase.model.AcquireAction;
import io.gs2.showcase.model.RandomDisplayItemModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateRandomShowcaseMasterRequest extends Gs2BasicRequest<CreateRandomShowcaseMasterRequest> {
    private String namespaceName;
    private String name;
    private String description;
    private String metadata;
    private Integer maximumNumberOfChoice;
    private List<RandomDisplayItemModel> displayItems;
    private Long baseTimestamp;
    private Integer resetIntervalHours;
    private String salesPeriodEventId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateRandomShowcaseMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateRandomShowcaseMasterRequest withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateRandomShowcaseMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public CreateRandomShowcaseMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getMaximumNumberOfChoice() {
		return maximumNumberOfChoice;
	}
	public void setMaximumNumberOfChoice(Integer maximumNumberOfChoice) {
		this.maximumNumberOfChoice = maximumNumberOfChoice;
	}
	public CreateRandomShowcaseMasterRequest withMaximumNumberOfChoice(Integer maximumNumberOfChoice) {
		this.maximumNumberOfChoice = maximumNumberOfChoice;
		return this;
	}
	public List<RandomDisplayItemModel> getDisplayItems() {
		return displayItems;
	}
	public void setDisplayItems(List<RandomDisplayItemModel> displayItems) {
		this.displayItems = displayItems;
	}
	public CreateRandomShowcaseMasterRequest withDisplayItems(List<RandomDisplayItemModel> displayItems) {
		this.displayItems = displayItems;
		return this;
	}
	public Long getBaseTimestamp() {
		return baseTimestamp;
	}
	public void setBaseTimestamp(Long baseTimestamp) {
		this.baseTimestamp = baseTimestamp;
	}
	public CreateRandomShowcaseMasterRequest withBaseTimestamp(Long baseTimestamp) {
		this.baseTimestamp = baseTimestamp;
		return this;
	}
	public Integer getResetIntervalHours() {
		return resetIntervalHours;
	}
	public void setResetIntervalHours(Integer resetIntervalHours) {
		this.resetIntervalHours = resetIntervalHours;
	}
	public CreateRandomShowcaseMasterRequest withResetIntervalHours(Integer resetIntervalHours) {
		this.resetIntervalHours = resetIntervalHours;
		return this;
	}
	public String getSalesPeriodEventId() {
		return salesPeriodEventId;
	}
	public void setSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
	}
	public CreateRandomShowcaseMasterRequest withSalesPeriodEventId(String salesPeriodEventId) {
		this.salesPeriodEventId = salesPeriodEventId;
		return this;
	}

    public static CreateRandomShowcaseMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateRandomShowcaseMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withMaximumNumberOfChoice(data.get("maximumNumberOfChoice") == null || data.get("maximumNumberOfChoice").isNull() ? null : data.get("maximumNumberOfChoice").intValue())
            .withDisplayItems(data.get("displayItems") == null || data.get("displayItems").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("displayItems").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RandomDisplayItemModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withBaseTimestamp(data.get("baseTimestamp") == null || data.get("baseTimestamp").isNull() ? null : data.get("baseTimestamp").longValue())
            .withResetIntervalHours(data.get("resetIntervalHours") == null || data.get("resetIntervalHours").isNull() ? null : data.get("resetIntervalHours").intValue())
            .withSalesPeriodEventId(data.get("salesPeriodEventId") == null || data.get("salesPeriodEventId").isNull() ? null : data.get("salesPeriodEventId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("maximumNumberOfChoice", getMaximumNumberOfChoice());
                put("displayItems", getDisplayItems() == null ? null :
                    getDisplayItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("baseTimestamp", getBaseTimestamp());
                put("resetIntervalHours", getResetIntervalHours());
                put("salesPeriodEventId", getSalesPeriodEventId());
            }}
        );
    }
}