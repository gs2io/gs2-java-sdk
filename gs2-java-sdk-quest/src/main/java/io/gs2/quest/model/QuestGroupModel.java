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

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * クエストグループ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class QuestGroupModel implements IModel, Serializable, Comparable<QuestGroupModel> {
	/** クエストグループ */
	protected String questGroupModelId;

	/**
	 * クエストグループを取得
	 *
	 * @return クエストグループ
	 */
	public String getQuestGroupModelId() {
		return questGroupModelId;
	}

	/**
	 * クエストグループを設定
	 *
	 * @param questGroupModelId クエストグループ
	 */
	public void setQuestGroupModelId(String questGroupModelId) {
		this.questGroupModelId = questGroupModelId;
	}

	/**
	 * クエストグループを設定
	 *
	 * @param questGroupModelId クエストグループ
	 * @return this
	 */
	public QuestGroupModel withQuestGroupModelId(String questGroupModelId) {
		this.questGroupModelId = questGroupModelId;
		return this;
	}
	/** クエストグループ名 */
	protected String name;

	/**
	 * クエストグループ名を取得
	 *
	 * @return クエストグループ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * クエストグループ名を設定
	 *
	 * @param name クエストグループ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * クエストグループ名を設定
	 *
	 * @param name クエストグループ名
	 * @return this
	 */
	public QuestGroupModel withName(String name) {
		this.name = name;
		return this;
	}
	/** クエストグループのメタデータ */
	protected String metadata;

	/**
	 * クエストグループのメタデータを取得
	 *
	 * @return クエストグループのメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * クエストグループのメタデータを設定
	 *
	 * @param metadata クエストグループのメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * クエストグループのメタデータを設定
	 *
	 * @param metadata クエストグループのメタデータ
	 * @return this
	 */
	public QuestGroupModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** グループに属するクエスト */
	protected List<QuestModel> quests;

	/**
	 * グループに属するクエストを取得
	 *
	 * @return グループに属するクエスト
	 */
	public List<QuestModel> getQuests() {
		return quests;
	}

	/**
	 * グループに属するクエストを設定
	 *
	 * @param quests グループに属するクエスト
	 */
	public void setQuests(List<QuestModel> quests) {
		this.quests = quests;
	}

	/**
	 * グループに属するクエストを設定
	 *
	 * @param quests グループに属するクエスト
	 * @return this
	 */
	public QuestGroupModel withQuests(List<QuestModel> quests) {
		this.quests = quests;
		return this;
	}
	/** 挑戦可能な期間を指定するイベントマスター のGRN */
	protected String challengePeriodEventId;

	/**
	 * 挑戦可能な期間を指定するイベントマスター のGRNを取得
	 *
	 * @return 挑戦可能な期間を指定するイベントマスター のGRN
	 */
	public String getChallengePeriodEventId() {
		return challengePeriodEventId;
	}

	/**
	 * 挑戦可能な期間を指定するイベントマスター のGRNを設定
	 *
	 * @param challengePeriodEventId 挑戦可能な期間を指定するイベントマスター のGRN
	 */
	public void setChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
	}

	/**
	 * 挑戦可能な期間を指定するイベントマスター のGRNを設定
	 *
	 * @param challengePeriodEventId 挑戦可能な期間を指定するイベントマスター のGRN
	 * @return this
	 */
	public QuestGroupModel withChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> quests = new ArrayList<>();
        if(this.quests != null) {
            for(QuestModel item : this.quests) {
                quests.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("questGroupModelId", this.getQuestGroupModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("challengePeriodEventId", this.getChallengePeriodEventId());
        body_.set("quests", JsonNodeFactory.instance.arrayNode().addAll(quests));
        return body_;
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