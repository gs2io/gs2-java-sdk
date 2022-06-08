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
public class MoldModel implements IModel, Serializable, Comparable<MoldModel> {
	private String moldModelId;
	private String name;
	private String metadata;
	private Integer initialMaxCapacity;
	private Integer maxCapacity;
	private FormModel formModel;
	public String getMoldModelId() {
		return moldModelId;
	}
	public void setMoldModelId(String moldModelId) {
		this.moldModelId = moldModelId;
	}
	public MoldModel withMoldModelId(String moldModelId) {
		this.moldModelId = moldModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MoldModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public MoldModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public Integer getInitialMaxCapacity() {
		return initialMaxCapacity;
	}
	public void setInitialMaxCapacity(Integer initialMaxCapacity) {
		this.initialMaxCapacity = initialMaxCapacity;
	}
	public MoldModel withInitialMaxCapacity(Integer initialMaxCapacity) {
		this.initialMaxCapacity = initialMaxCapacity;
		return this;
	}
	public Integer getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public MoldModel withMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}
	public FormModel getFormModel() {
		return formModel;
	}
	public void setFormModel(FormModel formModel) {
		this.formModel = formModel;
	}
	public MoldModel withFormModel(FormModel formModel) {
		this.formModel = formModel;
		return this;
	}

    public static MoldModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new MoldModel()
            .withMoldModelId(data.get("moldModelId") == null || data.get("moldModelId").isNull() ? null : data.get("moldModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withInitialMaxCapacity(data.get("initialMaxCapacity") == null || data.get("initialMaxCapacity").isNull() ? null : data.get("initialMaxCapacity").intValue())
            .withMaxCapacity(data.get("maxCapacity") == null || data.get("maxCapacity").isNull() ? null : data.get("maxCapacity").intValue())
            .withFormModel(data.get("formModel") == null || data.get("formModel").isNull() ? null : FormModel.fromJson(data.get("formModel")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("moldModelId", getMoldModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("initialMaxCapacity", getInitialMaxCapacity());
                put("maxCapacity", getMaxCapacity());
                put("formModel", getFormModel() != null ? getFormModel().toJson() : null);
            }}
        );
    }

	@Override
	public int compareTo(MoldModel o) {
		return moldModelId.compareTo(o.moldModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.moldModelId == null) ? 0 : this.moldModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.initialMaxCapacity == null) ? 0 : this.initialMaxCapacity.hashCode());
        result = prime * result + ((this.maxCapacity == null) ? 0 : this.maxCapacity.hashCode());
        result = prime * result + ((this.formModel == null) ? 0 : this.formModel.hashCode());
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
		MoldModel other = (MoldModel) o;
		if (moldModelId == null) {
			return other.moldModelId == null;
		} else if (!moldModelId.equals(other.moldModelId)) {
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
		if (initialMaxCapacity == null) {
			return other.initialMaxCapacity == null;
		} else if (!initialMaxCapacity.equals(other.initialMaxCapacity)) {
			return false;
		}
		if (maxCapacity == null) {
			return other.maxCapacity == null;
		} else if (!maxCapacity.equals(other.maxCapacity)) {
			return false;
		}
		if (formModel == null) {
			return other.formModel == null;
		} else if (!formModel.equals(other.formModel)) {
			return false;
		}
		return true;
	}
}