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
import io.gs2.quest.model.Reward;
import io.gs2.quest.model.Progress;
import io.gs2.quest.model.AcquireAction;
import io.gs2.quest.model.Contents;
import io.gs2.quest.model.ConsumeAction;
import io.gs2.quest.model.QuestModel;
import io.gs2.quest.model.QuestGroupModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetProgressByUserIdResult implements IResult, Serializable {
    private Progress item;
    private QuestGroupModel questGroup;
    private QuestModel quest;

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

	public QuestGroupModel getQuestGroup() {
		return questGroup;
	}

	public void setQuestGroup(QuestGroupModel questGroup) {
		this.questGroup = questGroup;
	}

	public GetProgressByUserIdResult withQuestGroup(QuestGroupModel questGroup) {
		this.questGroup = questGroup;
		return this;
	}

	public QuestModel getQuest() {
		return quest;
	}

	public void setQuest(QuestModel quest) {
		this.quest = quest;
	}

	public GetProgressByUserIdResult withQuest(QuestModel quest) {
		this.quest = quest;
		return this;
	}

    public static GetProgressByUserIdResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetProgressByUserIdResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Progress.fromJson(data.get("item")))
            .withQuestGroup(data.get("questGroup") == null || data.get("questGroup").isNull() ? null : QuestGroupModel.fromJson(data.get("questGroup")))
            .withQuest(data.get("quest") == null || data.get("quest").isNull() ? null : QuestModel.fromJson(data.get("quest")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("questGroup", getQuestGroup() != null ? getQuestGroup().toJson() : null);
                put("quest", getQuest() != null ? getQuest().toJson() : null);
            }}
        );
    }
}