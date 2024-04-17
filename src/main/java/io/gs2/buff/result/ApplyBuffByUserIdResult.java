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

package io.gs2.buff.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.buff.model.*;
import io.gs2.buff.model.BuffTargetGrn;
import io.gs2.buff.model.BuffTargetModel;
import io.gs2.buff.model.BuffTargetAction;
import io.gs2.buff.model.BuffEntryModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ApplyBuffByUserIdResult implements IResult, Serializable {
    private List<BuffEntryModel> items;
    private String newContextStack;

	public List<BuffEntryModel> getItems() {
		return items;
	}

	public void setItems(List<BuffEntryModel> items) {
		this.items = items;
	}

	public ApplyBuffByUserIdResult withItems(List<BuffEntryModel> items) {
		this.items = items;
		return this;
	}

	public String getNewContextStack() {
		return newContextStack;
	}

	public void setNewContextStack(String newContextStack) {
		this.newContextStack = newContextStack;
	}

	public ApplyBuffByUserIdResult withNewContextStack(String newContextStack) {
		this.newContextStack = newContextStack;
		return this;
	}

    public static ApplyBuffByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ApplyBuffByUserIdResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? new ArrayList<BuffEntryModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return BuffEntryModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withNewContextStack(data.get("newContextStack") == null || data.get("newContextStack").isNull() ? null : data.get("newContextStack").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("items", getItems() == null ? new ArrayList<BuffEntryModel>() :
                    getItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("newContextStack", getNewContextStack());
            }}
        );
    }
}