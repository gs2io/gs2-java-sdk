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

package io.gs2.loginReward.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.loginReward.model.*;
import io.gs2.loginReward.model.ReceiveStatus;
import io.gs2.loginReward.model.AcquireAction;
import io.gs2.loginReward.model.Reward;
import io.gs2.loginReward.model.ConsumeAction;
import io.gs2.loginReward.model.BonusModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetReceiveStatusByUserIdResult implements IResult, Serializable {
    private ReceiveStatus item;
    private BonusModel bonusModel;

	public ReceiveStatus getItem() {
		return item;
	}

	public void setItem(ReceiveStatus item) {
		this.item = item;
	}

	public GetReceiveStatusByUserIdResult withItem(ReceiveStatus item) {
		this.item = item;
		return this;
	}

	public BonusModel getBonusModel() {
		return bonusModel;
	}

	public void setBonusModel(BonusModel bonusModel) {
		this.bonusModel = bonusModel;
	}

	public GetReceiveStatusByUserIdResult withBonusModel(BonusModel bonusModel) {
		this.bonusModel = bonusModel;
		return this;
	}

    public static GetReceiveStatusByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetReceiveStatusByUserIdResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : ReceiveStatus.fromJson(data.get("item")))
            .withBonusModel(data.get("bonusModel") == null || data.get("bonusModel").isNull() ? null : BonusModel.fromJson(data.get("bonusModel")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("bonusModel", getBonusModel() != null ? getBonusModel().toJson() : null);
            }}
        );
    }
}