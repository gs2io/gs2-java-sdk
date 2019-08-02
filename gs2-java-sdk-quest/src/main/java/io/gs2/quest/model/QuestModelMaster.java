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
 * クエストモデルマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class QuestModelMaster implements IModel, Serializable, Comparable<QuestModelMaster> {
	/** クエストモデルマスター */
	protected String questModelId;

	/**
	 * クエストモデルマスターを取得
	 *
	 * @return クエストモデルマスター
	 */
	public String getQuestModelId() {
		return questModelId;
	}

	/**
	 * クエストモデルマスターを設定
	 *
	 * @param questModelId クエストモデルマスター
	 */
	public void setQuestModelId(String questModelId) {
		this.questModelId = questModelId;
	}

	/**
	 * クエストモデルマスターを設定
	 *
	 * @param questModelId クエストモデルマスター
	 * @return this
	 */
	public QuestModelMaster withQuestModelId(String questModelId) {
		this.questModelId = questModelId;
		return this;
	}
	/** クエストグループモデル名 */
	protected String questGroupName;

	/**
	 * クエストグループモデル名を取得
	 *
	 * @return クエストグループモデル名
	 */
	public String getQuestGroupName() {
		return questGroupName;
	}

	/**
	 * クエストグループモデル名を設定
	 *
	 * @param questGroupName クエストグループモデル名
	 */
	public void setQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
	}

	/**
	 * クエストグループモデル名を設定
	 *
	 * @param questGroupName クエストグループモデル名
	 * @return this
	 */
	public QuestModelMaster withQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
		return this;
	}
	/** クエスト名 */
	protected String name;

	/**
	 * クエスト名を取得
	 *
	 * @return クエスト名
	 */
	public String getName() {
		return name;
	}

	/**
	 * クエスト名を設定
	 *
	 * @param name クエスト名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * クエスト名を設定
	 *
	 * @param name クエスト名
	 * @return this
	 */
	public QuestModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** クエストモデルの説明 */
	protected String description;

	/**
	 * クエストモデルの説明を取得
	 *
	 * @return クエストモデルの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * クエストモデルの説明を設定
	 *
	 * @param description クエストモデルの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * クエストモデルの説明を設定
	 *
	 * @param description クエストモデルの説明
	 * @return this
	 */
	public QuestModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** クエストのメタデータ */
	protected String metadata;

	/**
	 * クエストのメタデータを取得
	 *
	 * @return クエストのメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * クエストのメタデータを設定
	 *
	 * @param metadata クエストのメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * クエストのメタデータを設定
	 *
	 * @param metadata クエストのメタデータ
	 * @return this
	 */
	public QuestModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** クエストの内容 */
	protected List<Contents> contents;

	/**
	 * クエストの内容を取得
	 *
	 * @return クエストの内容
	 */
	public List<Contents> getContents() {
		return contents;
	}

	/**
	 * クエストの内容を設定
	 *
	 * @param contents クエストの内容
	 */
	public void setContents(List<Contents> contents) {
		this.contents = contents;
	}

	/**
	 * クエストの内容を設定
	 *
	 * @param contents クエストの内容
	 * @return this
	 */
	public QuestModelMaster withContents(List<Contents> contents) {
		this.contents = contents;
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
	public QuestModelMaster withChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
		return this;
	}
	/** クエストの参加料 */
	protected List<ConsumeAction> consumeActions;

	/**
	 * クエストの参加料を取得
	 *
	 * @return クエストの参加料
	 */
	public List<ConsumeAction> getConsumeActions() {
		return consumeActions;
	}

	/**
	 * クエストの参加料を設定
	 *
	 * @param consumeActions クエストの参加料
	 */
	public void setConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
	}

	/**
	 * クエストの参加料を設定
	 *
	 * @param consumeActions クエストの参加料
	 * @return this
	 */
	public QuestModelMaster withConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
		return this;
	}
	/** クエスト失敗時の報酬 */
	protected List<AcquireAction> failedAcquireActions;

	/**
	 * クエスト失敗時の報酬を取得
	 *
	 * @return クエスト失敗時の報酬
	 */
	public List<AcquireAction> getFailedAcquireActions() {
		return failedAcquireActions;
	}

	/**
	 * クエスト失敗時の報酬を設定
	 *
	 * @param failedAcquireActions クエスト失敗時の報酬
	 */
	public void setFailedAcquireActions(List<AcquireAction> failedAcquireActions) {
		this.failedAcquireActions = failedAcquireActions;
	}

	/**
	 * クエスト失敗時の報酬を設定
	 *
	 * @param failedAcquireActions クエスト失敗時の報酬
	 * @return this
	 */
	public QuestModelMaster withFailedAcquireActions(List<AcquireAction> failedAcquireActions) {
		this.failedAcquireActions = failedAcquireActions;
		return this;
	}
	/** クエストに挑戦するためにクリアしておく必要のあるクエスト名 */
	protected List<String> premiseQuestNames;

	/**
	 * クエストに挑戦するためにクリアしておく必要のあるクエスト名を取得
	 *
	 * @return クエストに挑戦するためにクリアしておく必要のあるクエスト名
	 */
	public List<String> getPremiseQuestNames() {
		return premiseQuestNames;
	}

	/**
	 * クエストに挑戦するためにクリアしておく必要のあるクエスト名を設定
	 *
	 * @param premiseQuestNames クエストに挑戦するためにクリアしておく必要のあるクエスト名
	 */
	public void setPremiseQuestNames(List<String> premiseQuestNames) {
		this.premiseQuestNames = premiseQuestNames;
	}

	/**
	 * クエストに挑戦するためにクリアしておく必要のあるクエスト名を設定
	 *
	 * @param premiseQuestNames クエストに挑戦するためにクリアしておく必要のあるクエスト名
	 * @return this
	 */
	public QuestModelMaster withPremiseQuestNames(List<String> premiseQuestNames) {
		this.premiseQuestNames = premiseQuestNames;
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
	public QuestModelMaster withCreatedAt(Long createdAt) {
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
	public QuestModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> contents = new ArrayList<>();
        if(this.contents != null) {
            for(Contents item : this.contents) {
                contents.add(item.toJson());
            }
        }
        List<JsonNode> consumeActions = new ArrayList<>();
        if(this.consumeActions != null) {
            for(ConsumeAction item : this.consumeActions) {
                consumeActions.add(item.toJson());
            }
        }
        List<JsonNode> failedAcquireActions = new ArrayList<>();
        if(this.failedAcquireActions != null) {
            for(AcquireAction item : this.failedAcquireActions) {
                failedAcquireActions.add(item.toJson());
            }
        }
        List<JsonNode> premiseQuestNames = new ArrayList<>();
        if(this.premiseQuestNames != null) {
            for(String item : this.premiseQuestNames) {
                premiseQuestNames.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("questModelId", this.getQuestModelId())
            .put("questGroupName", this.getQuestGroupName())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("metadata", this.getMetadata())
            .put("challengePeriodEventId", this.getChallengePeriodEventId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("contents", JsonNodeFactory.instance.arrayNode().addAll(contents));
        body_.set("consumeActions", JsonNodeFactory.instance.arrayNode().addAll(consumeActions));
        body_.set("failedAcquireActions", JsonNodeFactory.instance.arrayNode().addAll(failedAcquireActions));
        body_.set("premiseQuestNames", JsonNodeFactory.instance.arrayNode().addAll(premiseQuestNames));
        return body_;
    }
	@Override
	public int compareTo(QuestModelMaster o) {
		return questModelId.compareTo(o.questModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.questModelId == null) ? 0 : this.questModelId.hashCode());
        result = prime * result + ((this.questGroupName == null) ? 0 : this.questGroupName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.contents == null) ? 0 : this.contents.hashCode());
        result = prime * result + ((this.challengePeriodEventId == null) ? 0 : this.challengePeriodEventId.hashCode());
        result = prime * result + ((this.consumeActions == null) ? 0 : this.consumeActions.hashCode());
        result = prime * result + ((this.failedAcquireActions == null) ? 0 : this.failedAcquireActions.hashCode());
        result = prime * result + ((this.premiseQuestNames == null) ? 0 : this.premiseQuestNames.hashCode());
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
		QuestModelMaster other = (QuestModelMaster) o;
		if (questModelId == null) {
			return other.questModelId == null;
		} else if (!questModelId.equals(other.questModelId)) {
			return false;
		}
		if (questGroupName == null) {
			return other.questGroupName == null;
		} else if (!questGroupName.equals(other.questGroupName)) {
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
		if (contents == null) {
			return other.contents == null;
		} else if (!contents.equals(other.contents)) {
			return false;
		}
		if (challengePeriodEventId == null) {
			return other.challengePeriodEventId == null;
		} else if (!challengePeriodEventId.equals(other.challengePeriodEventId)) {
			return false;
		}
		if (consumeActions == null) {
			return other.consumeActions == null;
		} else if (!consumeActions.equals(other.consumeActions)) {
			return false;
		}
		if (failedAcquireActions == null) {
			return other.failedAcquireActions == null;
		} else if (!failedAcquireActions.equals(other.failedAcquireActions)) {
			return false;
		}
		if (premiseQuestNames == null) {
			return other.premiseQuestNames == null;
		} else if (!premiseQuestNames.equals(other.premiseQuestNames)) {
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