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
import io.gs2.core.model.IModel;

/**
 * クエストグループマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class QuestGroupModelMaster implements IModel, Serializable, Comparable<QuestGroupModelMaster> {
	/** クエストグループマスター */
	protected String questGroupModelId;

	/**
	 * クエストグループマスターを取得
	 *
	 * @return クエストグループマスター
	 */
	public String getQuestGroupModelId() {
		return questGroupModelId;
	}

	/**
	 * クエストグループマスターを設定
	 *
	 * @param questGroupModelId クエストグループマスター
	 */
	public void setQuestGroupModelId(String questGroupModelId) {
		this.questGroupModelId = questGroupModelId;
	}

	/**
	 * クエストグループマスターを設定
	 *
	 * @param questGroupModelId クエストグループマスター
	 * @return this
	 */
	public QuestGroupModelMaster withQuestGroupModelId(String questGroupModelId) {
		this.questGroupModelId = questGroupModelId;
		return this;
	}
	/** クエストグループモデル名 */
	protected String name;

	/**
	 * クエストグループモデル名を取得
	 *
	 * @return クエストグループモデル名
	 */
	public String getName() {
		return name;
	}

	/**
	 * クエストグループモデル名を設定
	 *
	 * @param name クエストグループモデル名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * クエストグループモデル名を設定
	 *
	 * @param name クエストグループモデル名
	 * @return this
	 */
	public QuestGroupModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** クエストグループマスターの説明 */
	protected String description;

	/**
	 * クエストグループマスターの説明を取得
	 *
	 * @return クエストグループマスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * クエストグループマスターの説明を設定
	 *
	 * @param description クエストグループマスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * クエストグループマスターの説明を設定
	 *
	 * @param description クエストグループマスターの説明
	 * @return this
	 */
	public QuestGroupModelMaster withDescription(String description) {
		this.description = description;
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
	public QuestGroupModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
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
	public QuestGroupModelMaster withChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
		return this;
	}
	/** 作成日時 */
	protected Long createdAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 * @return this
	 */
	public QuestGroupModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** 最終更新日時 */
	protected Long updatedAt;

	/**
	 * 最終更新日時を取得
	 *
	 * @return 最終更新日時
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 * @return this
	 */
	public QuestGroupModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("questGroupModelId", this.getQuestGroupModelId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("challengePeriodEventId", this.getChallengePeriodEventId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(QuestGroupModelMaster o) {
		return questGroupModelId.compareTo(o.questGroupModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.questGroupModelId == null) ? 0 : this.questGroupModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.challengePeriodEventId == null) ? 0 : this.challengePeriodEventId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		QuestGroupModelMaster other = (QuestGroupModelMaster) o;
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
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (challengePeriodEventId == null) {
			return other.challengePeriodEventId == null;
		} else if (!challengePeriodEventId.equals(other.challengePeriodEventId)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}