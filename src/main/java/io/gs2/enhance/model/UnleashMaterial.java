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

package io.gs2.enhance.model;

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
public class UnleashMaterial implements IModel, Serializable {
	private String name;
	private String materialType;
	private UnleashIndividualMaterialSetting individualSetting;
	private UnleashQuantityMaterialSetting quantitySetting;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UnleashMaterial withName(String name) {
		this.name = name;
		return this;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public UnleashMaterial withMaterialType(String materialType) {
		this.materialType = materialType;
		return this;
	}
	public UnleashIndividualMaterialSetting getIndividualSetting() {
		return individualSetting;
	}
	public void setIndividualSetting(UnleashIndividualMaterialSetting individualSetting) {
		this.individualSetting = individualSetting;
	}
	public UnleashMaterial withIndividualSetting(UnleashIndividualMaterialSetting individualSetting) {
		this.individualSetting = individualSetting;
		return this;
	}
	public UnleashQuantityMaterialSetting getQuantitySetting() {
		return quantitySetting;
	}
	public void setQuantitySetting(UnleashQuantityMaterialSetting quantitySetting) {
		this.quantitySetting = quantitySetting;
	}
	public UnleashMaterial withQuantitySetting(UnleashQuantityMaterialSetting quantitySetting) {
		this.quantitySetting = quantitySetting;
		return this;
	}

    public static UnleashMaterial fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UnleashMaterial()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMaterialType(data.get("materialType") == null || data.get("materialType").isNull() ? null : data.get("materialType").asText())
            .withIndividualSetting(data.get("individualSetting") == null || data.get("individualSetting").isNull() ? null : UnleashIndividualMaterialSetting.fromJson(data.get("individualSetting")))
            .withQuantitySetting(data.get("quantitySetting") == null || data.get("quantitySetting").isNull() ? null : UnleashQuantityMaterialSetting.fromJson(data.get("quantitySetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("materialType", getMaterialType());
                put("individualSetting", getIndividualSetting() != null ? getIndividualSetting().toJson() : null);
                put("quantitySetting", getQuantitySetting() != null ? getQuantitySetting().toJson() : null);
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.materialType == null) ? 0 : this.materialType.hashCode());
        result = prime * result + ((this.individualSetting == null) ? 0 : this.individualSetting.hashCode());
        result = prime * result + ((this.quantitySetting == null) ? 0 : this.quantitySetting.hashCode());
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
		UnleashMaterial other = (UnleashMaterial) o;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (materialType == null) {
			return other.materialType == null;
		} else if (!materialType.equals(other.materialType)) {
			return false;
		}
		if (individualSetting == null) {
			return other.individualSetting == null;
		} else if (!individualSetting.equals(other.individualSetting)) {
			return false;
		}
		if (quantitySetting == null) {
			return other.quantitySetting == null;
		} else if (!quantitySetting.equals(other.quantitySetting)) {
			return false;
		}
		return true;
	}
}