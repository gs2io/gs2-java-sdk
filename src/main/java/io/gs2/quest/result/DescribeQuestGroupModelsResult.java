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

package io.gs2.quest.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.quest.model.*;
import io.gs2.quest.model.AcquireAction;
import io.gs2.quest.model.Contents;
import io.gs2.quest.model.VerifyAction;
import io.gs2.quest.model.ConsumeAction;
import io.gs2.quest.model.QuestModel;
import io.gs2.quest.model.QuestGroupModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeQuestGroupModelsResult implements IResult, Serializable {
    private List<QuestGroupModel> items;

	public List<QuestGroupModel> getItems() {
		return items;
	}

	public void setItems(List<QuestGroupModel> items) {
		this.items = items;
	}

	public DescribeQuestGroupModelsResult withItems(List<QuestGroupModel> items) {
		this.items = items;
		return this;
	}

    public static DescribeQuestGroupModelsResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new DescribeQuestGroupModelsResult()
            .withItems(data.get("items") == null || data.get("items").isNull() ? new ArrayList<QuestGroupModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("items").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return QuestGroupModel.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("items", getItems() == null ? new ArrayList<QuestGroupModel>() :
                    getItems().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}