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

package io.gs2.showcase.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.showcase.model.*;
import io.gs2.showcase.model.VerifyAction;
import io.gs2.showcase.model.ConsumeAction;
import io.gs2.showcase.model.AcquireAction;
import io.gs2.showcase.model.RandomDisplayItemModel;
import io.gs2.showcase.model.RandomShowcaseMaster;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateRandomShowcaseMasterResult implements IResult, Serializable {
    private RandomShowcaseMaster item;

	public RandomShowcaseMaster getItem() {
		return item;
	}

	public void setItem(RandomShowcaseMaster item) {
		this.item = item;
	}

	public CreateRandomShowcaseMasterResult withItem(RandomShowcaseMaster item) {
		this.item = item;
		return this;
	}

    public static CreateRandomShowcaseMasterResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateRandomShowcaseMasterResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : RandomShowcaseMaster.fromJson(data.get("item")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
            }}
        );
    }
}