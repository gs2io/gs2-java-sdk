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

package io.gs2.formation.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.formation.model.*;
import io.gs2.formation.model.Slot;
import io.gs2.formation.model.PropertyForm;
import io.gs2.formation.model.SlotModel;
import io.gs2.formation.model.PropertyFormModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SetPropertyFormResult implements IResult, Serializable {
    private PropertyForm item;
    private PropertyFormModel propertyFormModel;

	public PropertyForm getItem() {
		return item;
	}

	public void setItem(PropertyForm item) {
		this.item = item;
	}

	public SetPropertyFormResult withItem(PropertyForm item) {
		this.item = item;
		return this;
	}

	public PropertyFormModel getPropertyFormModel() {
		return propertyFormModel;
	}

	public void setPropertyFormModel(PropertyFormModel propertyFormModel) {
		this.propertyFormModel = propertyFormModel;
	}

	public SetPropertyFormResult withPropertyFormModel(PropertyFormModel propertyFormModel) {
		this.propertyFormModel = propertyFormModel;
		return this;
	}

    public static SetPropertyFormResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetPropertyFormResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : PropertyForm.fromJson(data.get("item")))
            .withPropertyFormModel(data.get("propertyFormModel") == null || data.get("propertyFormModel").isNull() ? null : PropertyFormModel.fromJson(data.get("propertyFormModel")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("propertyFormModel", getPropertyFormModel() != null ? getPropertyFormModel().toJson() : null);
            }}
        );
    }
}