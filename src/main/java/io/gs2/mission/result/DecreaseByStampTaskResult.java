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

package io.gs2.mission.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.mission.model.*;
import io.gs2.mission.model.ScopedValue;
import io.gs2.mission.model.Counter;
import io.gs2.mission.model.Complete;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DecreaseByStampTaskResult implements IResult, Serializable {
    private Counter item;
    private List<Complete> changedCompletes;
    private String newContextStack;

	public Counter getItem() {
		return item;
	}

	public void setItem(Counter item) {
		this.item = item;
	}

	public DecreaseByStampTaskResult withItem(Counter item) {
		this.item = item;
		return this;
	}

	public List<Complete> getChangedCompletes() {
		return changedCompletes;
	}

	public void setChangedCompletes(List<Complete> changedCompletes) {
		this.changedCompletes = changedCompletes;
	}

	public DecreaseByStampTaskResult withChangedCompletes(List<Complete> changedCompletes) {
		this.changedCompletes = changedCompletes;
		return this;
	}

	public String getNewContextStack() {
		return newContextStack;
	}

	public void setNewContextStack(String newContextStack) {
		this.newContextStack = newContextStack;
	}

	public DecreaseByStampTaskResult withNewContextStack(String newContextStack) {
		this.newContextStack = newContextStack;
		return this;
	}

    public static DecreaseByStampTaskResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DecreaseByStampTaskResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Counter.fromJson(data.get("item")))
            .withChangedCompletes(data.get("changedCompletes") == null || data.get("changedCompletes").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("changedCompletes").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Complete.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withNewContextStack(data.get("newContextStack") == null || data.get("newContextStack").isNull() ? null : data.get("newContextStack").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("changedCompletes", getChangedCompletes() == null ? null :
                    getChangedCompletes().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("newContextStack", getNewContextStack());
            }}
        );
    }
}