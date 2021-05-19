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
 * クエスト挑戦
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Progress implements IModel, Serializable, Comparable<Progress> {
	/** クエスト挑戦 */
	protected String progressId;

	/**
	 * クエスト挑戦を取得
	 *
	 * @return クエスト挑戦
	 */
	public String getProgressId() {
		return progressId;
	}

	/**
	 * クエスト挑戦を設定
	 *
	 * @param progressId クエスト挑戦
	 */
	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	/**
	 * クエスト挑戦を設定
	 *
	 * @param progressId クエスト挑戦
	 * @return this
	 */
	public Progress withProgressId(String progressId) {
		this.progressId = progressId;
		return this;
	}
	/** ユーザーID */
	protected String userId;

	/**
	 * ユーザーIDを取得
	 *
	 * @return ユーザーID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 * @return this
	 */
	public Progress withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** トランザクションID */
	protected String transactionId;

	/**
	 * トランザクションIDを取得
	 *
	 * @return トランザクションID
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * トランザクションIDを設定
	 *
	 * @param transactionId トランザクションID
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * トランザクションIDを設定
	 *
	 * @param transactionId トランザクションID
	 * @return this
	 */
	public Progress withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}
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
	public Progress withQuestModelId(String questModelId) {
		this.questModelId = questModelId;
		return this;
	}
	/** 乱数シード */
	protected Long randomSeed;

	/**
	 * 乱数シードを取得
	 *
	 * @return 乱数シード
	 */
	public Long getRandomSeed() {
		return randomSeed;
	}

	/**
	 * 乱数シードを設定
	 *
	 * @param randomSeed 乱数シード
	 */
	public void setRandomSeed(Long randomSeed) {
		this.randomSeed = randomSeed;
	}

	/**
	 * 乱数シードを設定
	 *
	 * @param randomSeed 乱数シード
	 * @return this
	 */
	public Progress withRandomSeed(Long randomSeed) {
		this.randomSeed = randomSeed;
		return this;
	}
	/** クエストで得られる報酬の上限 */
	protected List<Reward> rewards;

	/**
	 * クエストで得られる報酬の上限を取得
	 *
	 * @return クエストで得られる報酬の上限
	 */
	public List<Reward> getRewards() {
		return rewards;
	}

	/**
	 * クエストで得られる報酬の上限を設定
	 *
	 * @param rewards クエストで得られる報酬の上限
	 */
	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}

	/**
	 * クエストで得られる報酬の上限を設定
	 *
	 * @param rewards クエストで得られる報酬の上限
	 * @return this
	 */
	public Progress withRewards(List<Reward> rewards) {
		this.rewards = rewards;
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
	public Progress withMetadata(String metadata) {
		this.metadata = metadata;
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
	public Progress withCreatedAt(Long createdAt) {
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
	public Progress withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> rewards = new ArrayList<>();
        if(this.rewards != null) {
            for(Reward item : this.rewards) {
                rewards.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("progressId", this.getProgressId())
            .put("userId", this.getUserId())
            .put("transactionId", this.getTransactionId())
            .put("questModelId", this.getQuestModelId())
            .put("randomSeed", this.getRandomSeed())
            .put("metadata", this.getMetadata())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("rewards", JsonNodeFactory.instance.arrayNode().addAll(rewards));
        return body_;
    }
	@Override
	public int compareTo(Progress o) {
		return progressId.compareTo(o.progressId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.progressId == null) ? 0 : this.progressId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.transactionId == null) ? 0 : this.transactionId.hashCode());
        result = prime * result + ((this.questModelId == null) ? 0 : this.questModelId.hashCode());
        result = prime * result + ((this.randomSeed == null) ? 0 : this.randomSeed.hashCode());
        result = prime * result + ((this.rewards == null) ? 0 : this.rewards.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
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
		Progress other = (Progress) o;
		if (progressId == null) {
			return other.progressId == null;
		} else if (!progressId.equals(other.progressId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (transactionId == null) {
			return other.transactionId == null;
		} else if (!transactionId.equals(other.transactionId)) {
			return false;
		}
		if (questModelId == null) {
			return other.questModelId == null;
		} else if (!questModelId.equals(other.questModelId)) {
			return false;
		}
		if (randomSeed == null) {
			return other.randomSeed == null;
		} else if (!randomSeed.equals(other.randomSeed)) {
			return false;
		}
		if (rewards == null) {
			return other.rewards == null;
		} else if (!rewards.equals(other.rewards)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
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