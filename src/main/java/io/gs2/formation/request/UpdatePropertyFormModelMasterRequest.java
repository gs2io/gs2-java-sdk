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
import io.gs2.formation.model.SlotModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdatePropertyFormModelMasterRequest extends Gs2BasicRequest<UpdatePropertyFormModelMasterRequest> {
    private String namespaceName;
    private String propertyFormModelName;
    private String description;
    private String metadata;
    private List<SlotModel> slots;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdatePropertyFormModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getPropertyFormModelName() {
		return propertyFormModelName;
	}
	public void setPropertyFormModelName(String propertyFormModelName) {
		this.propertyFormModelName = propertyFormModelName;
	}
	public UpdatePropertyFormModelMasterRequest withPropertyFormModelName(String propertyFormModelName) {
		this.propertyFormModelName = propertyFormModelName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdatePropertyFormModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdatePropertyFormModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<SlotModel> getSlots() {
		return slots;
	}
	public void setSlots(List<SlotModel> slots) {
		this.slots = slots;
	}
	public UpdatePropertyFormModelMasterRequest withSlots(List<SlotModel> slots) {
		this.slots = slots;
		return this;
	}

    public static UpdatePropertyFormModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdatePropertyFormModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withPropertyFormModelName(data.get("propertyFormModelName") == null || data.get("propertyFormModelName").isNull() ? null : data.get("propertyFormModelName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withSlots(data.get("slots") == null || data.get("slots").isNull() ? new ArrayList<SlotModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("slots").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return SlotModel.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("propertyFormModelName", getPropertyFormModelName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("slots", getSlots() == null ? new ArrayList<SlotModel>() :
                    getSlots().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}