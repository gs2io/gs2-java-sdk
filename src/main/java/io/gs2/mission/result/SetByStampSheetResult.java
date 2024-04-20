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
public class SetByStampSheetResult implements IResult, Serializable {
    private Counter item;
    private Counter old;
    private List<Complete> changedCompletes;

	public Counter getItem() {
		return item;
	}

	public void setItem(Counter item) {
		this.item = item;
	}

	public SetByStampSheetResult withItem(Counter item) {
		this.item = item;
		return this;
	}

	public Counter getOld() {
		return old;
	}

	public void setOld(Counter old) {
		this.old = old;
	}

	public SetByStampSheetResult withOld(Counter old) {
		this.old = old;
		return this;
	}

	public List<Complete> getChangedCompletes() {
		return changedCompletes;
	}

	public void setChangedCompletes(List<Complete> changedCompletes) {
		this.changedCompletes = changedCompletes;
	}

	public SetByStampSheetResult withChangedCompletes(List<Complete> changedCompletes) {
		this.changedCompletes = changedCompletes;
		return this;
	}

    public static SetByStampSheetResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetByStampSheetResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Counter.fromJson(data.get("item")))
            .withOld(data.get("old") == null || data.get("old").isNull() ? null : Counter.fromJson(data.get("old")))
            .withChangedCompletes(data.get("changedCompletes") == null || data.get("changedCompletes").isNull() ? new ArrayList<Complete>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("changedCompletes").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Complete.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("old", getOld() != null ? getOld().toJson() : null);
                put("changedCompletes", getChangedCompletes() == null ? new ArrayList<Complete>() :
                    getChangedCompletes().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}