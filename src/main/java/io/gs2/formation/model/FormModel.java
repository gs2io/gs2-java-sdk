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

package io.gs2.formation.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class FormModel implements IModel, Serializable, Comparable<FormModel> {
	private String formModelId;
	private String name;
	private String metadata;
	private List<SlotModel> slots;

	public String getFormModelId() {
		return formModelId;
	}

	public void setFormModelId(String formModelId) {
		this.formModelId = formModelId;
	}

	public FormModel withFormModelId(String formModelId) {
		this.formModelId = formModelId;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FormModel withName(String name) {
		this.name = name;
		return this;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public FormModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}

	public List<SlotModel> getSlots() {
		return slots;
	}

	public void setSlots(List<SlotModel> slots) {
		this.slots = slots;
	}

	public FormModel withSlots(List<SlotModel> slots) {
		this.slots = slots;
		return this;
	}

    public static FormModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new FormModel()
            .withFormModelId(data.get("formModelId") == null || data.get("formModelId").isNull() ? null : data.get("formModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
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
                put("formModelId", getFormModelId());
                put("name", getName());
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

	@Override
	public int compareTo(FormModel o) {
		return formModelId.compareTo(o.formModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.formModelId == null) ? 0 : this.formModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.slots == null) ? 0 : this.slots.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		FormModel other = (FormModel) o;
		if (formModelId == null) {
			return other.formModelId == null;
		} else if (!formModelId.equals(other.formModelId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (slots == null) {
			return other.slots == null;
		} else if (!slots.equals(other.slots)) {
			return false;
		}
		return true;
	}
}