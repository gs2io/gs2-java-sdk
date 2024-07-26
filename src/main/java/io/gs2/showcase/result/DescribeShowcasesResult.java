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
import io.gs2.showcase.model.SalesItem;
import io.gs2.showcase.model.SalesItemGroup;
import io.gs2.showcase.model.DisplayItem;
import io.gs2.showcase.model.Showcase;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeShowcasesResult implements IResult, Serializable {
    private List<Showcase> items;

	public List<Showcase> getItems() {
		return items;
	}

	public void setItems(List<Showcase> items) {
		this.items = items;
	}

	public DescribeShowcasesResult withItems(List<Showcase> items) {
		this.items = items;
		return this;
	}

    public static DescribeShowcasesResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DescribeShowcasesResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? new ArrayList<Showcase>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Showcase.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("items", getItems() == null ? new ArrayList<Showcase>() :
                    getItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}