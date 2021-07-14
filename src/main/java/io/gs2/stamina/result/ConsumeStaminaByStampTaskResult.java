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
public class ConsumeStaminaByStampTaskResult implements IResult, Serializable {
    private Stamina item;
    private StaminaModel staminaModel;
    private String newContextStack;

	public Stamina getItem() {
		return item;
	}

	public void setItem(Stamina item) {
		this.item = item;
	}

	public ConsumeStaminaByStampTaskResult withItem(Stamina item) {
		this.item = item;
		return this;
	}

	public StaminaModel getStaminaModel() {
		return staminaModel;
	}

	public void setStaminaModel(StaminaModel staminaModel) {
		this.staminaModel = staminaModel;
	}

	public ConsumeStaminaByStampTaskResult withStaminaModel(StaminaModel staminaModel) {
		this.staminaModel = staminaModel;
		return this;
	}

	public String getNewContextStack() {
		return newContextStack;
	}

	public void setNewContextStack(String newContextStack) {
		this.newContextStack = newContextStack;
	}

	public ConsumeStaminaByStampTaskResult withNewContextStack(String newContextStack) {
		this.newContextStack = newContextStack;
		return this;
	}

    public static ConsumeStaminaByStampTaskResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ConsumeStaminaByStampTaskResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Stamina.fromJson(data.get("item")))
            .withStaminaModel(data.get("staminaModel") == null || data.get("staminaModel").isNull() ? null : StaminaModel.fromJson(data.get("staminaModel")))
            .withNewContextStack(data.get("newContextStack") == null || data.get("newContextStack").isNull() ? null : data.get("newContextStack").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("staminaModel", getStaminaModel() != null ? getStaminaModel().toJson() : null);
                put("newContextStack", getNewContextStack());
            }}
        );
    }
}