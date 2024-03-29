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

package io.gs2.inventory.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.inventory.model.*;
import io.gs2.inventory.model.SimpleItemModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetSimpleItemModelResult implements IResult, Serializable {
    private SimpleItemModel item;

	public SimpleItemModel getItem() {
		return item;
	}

	public void setItem(SimpleItemModel item) {
		this.item = item;
	}

	public GetSimpleItemModelResult withItem(SimpleItemModel item) {
		this.item = item;
		return this;
	}

    public static GetSimpleItemModelResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetSimpleItemModelResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : SimpleItemModel.fromJson(data.get("item")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
            }}
        );
    }
}