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

package io.gs2.quest.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class QuestGroupModel implements IModel, Serializable, Comparable<QuestGroupModel> {
	private String questGroupModelId;
	private String name;
	private String metadata;
	private List<QuestModel> quests;
	private String challengePeriodEventId;
	public String getQuestGroupModelId() {
		return questGroupModelId;
	}
	public void setQuestGroupModelId(String questGroupModelId) {
		this.questGroupModelId = questGroupModelId;
	}
	public QuestGroupModel withQuestGroupModelId(String questGroupModelId) {
		this.questGroupModelId = questGroupModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public QuestGroupModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public QuestGroupModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<QuestModel> getQuests() {
		return quests;
	}
	public void setQuests(List<QuestModel> quests) {
		this.quests = quests;
	}
	public QuestGroupModel withQuests(List<QuestModel> quests) {
		this.quests = quests;
		return this;
	}
	public String getChallengePeriodEventId() {
		return challengePeriodEventId;
	}
	public void setChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
	}
	public QuestGroupModel withChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
		return this;
	}

    public static QuestGroupModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new QuestGroupModel()
            .withQuestGroupModelId(data.get("questGroupModelId") == null || data.get("questGroupModelId").isNull() ? null : data.get("questGroupModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withQuests(data.get("quests") == null || data.get("quests").isNull() ? new ArrayList<QuestModel>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("quests").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return QuestModel.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withChallengePeriodEventId(data.get("challengePeriodEventId") == null || data.get("challengePeriodEventId").isNull() ? null : data.get("challengePeriodEventId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("questGroupModelId", getQuestGroupModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("quests", getQuests() == null ? new ArrayList<QuestModel>() :
                    getQuests().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("challengePeriodEventId", getChallengePeriodEventId());
            }}
        );
    }

	@Override
	public int compareTo(QuestGroupModel o) {
		return questGroupModelId.compareTo(o.questGroupModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.questGroupModelId == null) ? 0 : this.questGroupModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.quests == null) ? 0 : this.quests.hashCode());
        result = prime * result + ((this.challengePeriodEventId == null) ? 0 : this.challengePeriodEventId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		QuestGroupModel other = (QuestGroupModel) o;
		if (questGroupModelId == null) {
			return other.questGroupModelId == null;
		} else if (!questGroupModelId.equals(other.questGroupModelId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (quests == null) {
			return other.quests == null;
		} else if (!quests.equals(other.quests)) {
			return false;
		}
		if (challengePeriodEventId == null) {
			return other.challengePeriodEventId == null;
		} else if (!challengePeriodEventId.equals(other.challengePeriodEventId)) {
			return false;
		}
		return true;
	}
}