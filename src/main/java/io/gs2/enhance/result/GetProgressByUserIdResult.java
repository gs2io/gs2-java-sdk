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

package io.gs2.enhance.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.enhance.model.*;
import io.gs2.enhance.model.Progress;
import io.gs2.enhance.model.BonusRate;
import io.gs2.enhance.model.RateModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetProgressByUserIdResult implements IResult, Serializable {
    private Progress item;
    private RateModel rateModel;

	public Progress getItem() {
		return item;
	}

	public void setItem(Progress item) {
		this.item = item;
	}

	public GetProgressByUserIdResult withItem(Progress item) {
		this.item = item;
		return this;
	}

	public RateModel getRateModel() {
		return rateModel;
	}

	public void setRateModel(RateModel rateModel) {
		this.rateModel = rateModel;
	}

	public GetProgressByUserIdResult withRateModel(RateModel rateModel) {
		this.rateModel = rateModel;
		return this;
	}

    public static GetProgressByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetProgressByUserIdResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Progress.fromJson(data.get("item")))
            .withRateModel(data.get("rateModel") == null || data.get("rateModel").isNull() ? null : RateModel.fromJson(data.get("rateModel")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("rateModel", getRateModel() != null ? getRateModel().toJson() : null);
            }}
        );
    }
}