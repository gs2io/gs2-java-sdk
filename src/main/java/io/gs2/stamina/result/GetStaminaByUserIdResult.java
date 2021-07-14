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

package io.gs2.stamina.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.stamina.model.*;
import io.gs2.stamina.model.Stamina;
import io.gs2.stamina.model.MaxStaminaTable;
import io.gs2.stamina.model.RecoverIntervalTable;
import io.gs2.stamina.model.RecoverValueTable;
import io.gs2.stamina.model.StaminaModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetStaminaByUserIdResult implements IResult, Serializable {
    private Stamina item;
    private StaminaModel staminaModel;

	public Stamina getItem() {
		return item;
	}

	public void setItem(Stamina item) {
		this.item = item;
	}

	public GetStaminaByUserIdResult withItem(Stamina item) {
		this.item = item;
		return this;
	}

	public StaminaModel getStaminaModel() {
		return staminaModel;
	}

	public void setStaminaModel(StaminaModel staminaModel) {
		this.staminaModel = staminaModel;
	}

	public GetStaminaByUserIdResult withStaminaModel(StaminaModel staminaModel) {
		this.staminaModel = staminaModel;
		return this;
	}

    public static GetStaminaByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetStaminaByUserIdResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Stamina.fromJson(data.get("item")))
            .withStaminaModel(data.get("staminaModel") == null || data.get("staminaModel").isNull() ? null : StaminaModel.fromJson(data.get("staminaModel")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("staminaModel", getStaminaModel() != null ? getStaminaModel().toJson() : null);
            }}
        );
    }
}