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
 * None
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class QuestModel implements IModel, Serializable, Comparable<QuestModel> {
	/** クエストモデル */
	protected String questModelId;

	/**
	 * クエストモデルを取得
	 *
	 * @return クエストモデル
	 */
	public String getQuestModelId() {
		return questModelId;
	}

	/**
	 * クエストモデルを設定
	 *
	 * @param questModelId クエストモデル
	 */
	public void setQuestModelId(String questModelId) {
		this.questModelId = questModelId;
	}

	/**
	 * クエストモデルを設定
	 *
	 * @param questModelId クエストモデル
	 * @return this
	 */
	public QuestModel withQuestModelId(String questModelId) {
		this.questModelId = questModelId;
		return this;
	}
	/** クエストモデル名 */
	protected String name;

	/**
	 * クエストモデル名を取得
	 *
	 * @return クエストモデル名
	 */
	public String getName() {
		return name;
	}

	/**
	 * クエストモデル名を設定
	 *
	 * @param name クエストモデル名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * クエストモデル名を設定
	 *
	 * @param name クエストモデル名
	 * @return this
	 */
	public QuestModel withName(String name) {
		this.name = name;
		return this;
	}
	/** クエストモデルのメタデータ */
	protected String metadata;

	/**
	 * クエストモデルのメタデータを取得
	 *
	 * @return クエストモデルのメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * クエストモデルのメタデータを設定
	 *
	 * @param metadata クエストモデルのメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * クエストモデルのメタデータを設定
	 *
	 * @param metadata クエストモデルのメタデータ
	 * @return this
	 */
	public QuestModel withMetadata(String metadata) {
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
	public QuestModel withContents(List<Contents> contents) {
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
	public QuestModel withChallengePeriodEventId(String challengePeriodEventId) {
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
	public QuestModel withConsumeActions(List<ConsumeAction> consumeActions) {
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
	public QuestModel withFailedAcquireActions(List<AcquireAction> failedAcquireActions) {
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
	public QuestModel withPremiseQuestNames(List<String> premiseQuestNames) {
		this.premiseQuestNames = premiseQuestNames;
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
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("challengePeriodEventId", this.getChallengePeriodEventId());
        body_.set("contents", JsonNodeFactory.instance.arrayNode().addAll(contents));
        body_.set("consumeActions", JsonNodeFactory.instance.arrayNode().addAll(consumeActions));
        body_.set("failedAcquireActions", JsonNodeFactory.instance.arrayNode().addAll(failedAcquireActions));
        body_.set("premiseQuestNames", JsonNodeFactory.instance.arrayNode().addAll(premiseQuestNames));
        return body_;
    }
	@Override
	public int compareTo(QuestModel o) {
		return questModelId.compareTo(o.questModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.questModelId == null) ? 0 : this.questModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.contents == null) ? 0 : this.contents.hashCode());
        result = prime * result + ((this.challengePeriodEventId == null) ? 0 : this.challengePeriodEventId.hashCode());
        result = prime * result + ((this.consumeActions == null) ? 0 : this.consumeActions.hashCode());
        result = prime * result + ((this.failedAcquireActions == null) ? 0 : this.failedAcquireActions.hashCode());
        result = prime * result + ((this.premiseQuestNames == null) ? 0 : this.premiseQuestNames.hashCode());
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
		QuestModel other = (QuestModel) o;
		if (questModelId == null) {
			return other.questModelId == null;
		} else if (!questModelId.equals(other.questModelId)) {
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
		return true;
	}
}