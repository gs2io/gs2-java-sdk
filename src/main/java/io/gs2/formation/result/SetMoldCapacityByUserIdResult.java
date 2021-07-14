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
import io.gs2.formation.model.Mold;
import io.gs2.formation.model.SlotModel;
import io.gs2.formation.model.FormModel;
import io.gs2.formation.model.MoldModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SetMoldCapacityByUserIdResult implements IResult, Serializable {
    private Mold item;
    private MoldModel moldModel;

	public Mold getItem() {
		return item;
	}

	public void setItem(Mold item) {
		this.item = item;
	}

	public SetMoldCapacityByUserIdResult withItem(Mold item) {
		this.item = item;
		return this;
	}

	public MoldModel getMoldModel() {
		return moldModel;
	}

	public void setMoldModel(MoldModel moldModel) {
		this.moldModel = moldModel;
	}

	public SetMoldCapacityByUserIdResult withMoldModel(MoldModel moldModel) {
		this.moldModel = moldModel;
		return this;
	}

    public static SetMoldCapacityByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetMoldCapacityByUserIdResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Mold.fromJson(data.get("item")))
            .withMoldModel(data.get("moldModel") == null || data.get("moldModel").isNull() ? null : MoldModel.fromJson(data.get("moldModel")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("moldModel", getMoldModel() != null ? getMoldModel().toJson() : null);
            }}
        );
    }
}