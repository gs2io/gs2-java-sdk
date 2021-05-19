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

package io.gs2.ranking.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * スコア
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Score implements IModel, Serializable, Comparable<Score> {
	/** スコア */
	protected String scoreId;

	/**
	 * スコアを取得
	 *
	 * @return スコア
	 */
	public String getScoreId() {
		return scoreId;
	}

	/**
	 * スコアを設定
	 *
	 * @param scoreId スコア
	 */
	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}

	/**
	 * スコアを設定
	 *
	 * @param scoreId スコア
	 * @return this
	 */
	public Score withScoreId(String scoreId) {
		this.scoreId = scoreId;
		return this;
	}
	/** カテゴリ名 */
	protected String categoryName;

	/**
	 * カテゴリ名を取得
	 *
	 * @return カテゴリ名
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * カテゴリ名を設定
	 *
	 * @param categoryName カテゴリ名
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * カテゴリ名を設定
	 *
	 * @param categoryName カテゴリ名
	 * @return this
	 */
	public Score withCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}
	/** ユーザID */
	protected String userId;

	/**
	 * ユーザIDを取得
	 *
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 * @return this
	 */
	public Score withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** スコアのユニークID */
	protected String uniqueId;

	/**
	 * スコアのユニークIDを取得
	 *
	 * @return スコアのユニークID
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * スコアのユニークIDを設定
	 *
	 * @param uniqueId スコアのユニークID
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	/**
	 * スコアのユニークIDを設定
	 *
	 * @param uniqueId スコアのユニークID
	 * @return this
	 */
	public Score withUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
		return this;
	}
	/** スコアを獲得したユーザID */
	protected String scorerUserId;

	/**
	 * スコアを獲得したユーザIDを取得
	 *
	 * @return スコアを獲得したユーザID
	 */
	public String getScorerUserId() {
		return scorerUserId;
	}

	/**
	 * スコアを獲得したユーザIDを設定
	 *
	 * @param scorerUserId スコアを獲得したユーザID
	 */
	public void setScorerUserId(String scorerUserId) {
		this.scorerUserId = scorerUserId;
	}

	/**
	 * スコアを獲得したユーザIDを設定
	 *
	 * @param scorerUserId スコアを獲得したユーザID
	 * @return this
	 */
	public Score withScorerUserId(String scorerUserId) {
		this.scorerUserId = scorerUserId;
		return this;
	}
	/** スコア */
	protected Long score;

	/**
	 * スコアを取得
	 *
	 * @return スコア
	 */
	public Long getScore() {
		return score;
	}

	/**
	 * スコアを設定
	 *
	 * @param score スコア
	 */
	public void setScore(Long score) {
		this.score = score;
	}

	/**
	 * スコアを設定
	 *
	 * @param score スコア
	 * @return this
	 */
	public Score withScore(Long score) {
		this.score = score;
		return this;
	}
	/** メタデータ */
	protected String metadata;

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * メタデータを設定
	 *
	 * @param metadata メタデータ
	 * @return this
	 */
	public Score withMetadata(String metadata) {
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
	public Score withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("scoreId", this.getScoreId())
            .put("categoryName", this.getCategoryName())
            .put("userId", this.getUserId())
            .put("uniqueId", this.getUniqueId())
            .put("scorerUserId", this.getScorerUserId())
            .put("score", this.getScore())
            .put("metadata", this.getMetadata())
            .put("createdAt", this.getCreatedAt());
        return body_;
    }
	@Override
	public int compareTo(Score o) {
		return scoreId.compareTo(o.scoreId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.scoreId == null) ? 0 : this.scoreId.hashCode());
        result = prime * result + ((this.categoryName == null) ? 0 : this.categoryName.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.uniqueId == null) ? 0 : this.uniqueId.hashCode());
        result = prime * result + ((this.scorerUserId == null) ? 0 : this.scorerUserId.hashCode());
        result = prime * result + ((this.score == null) ? 0 : this.score.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
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
		Score other = (Score) o;
		if (scoreId == null) {
			return other.scoreId == null;
		} else if (!scoreId.equals(other.scoreId)) {
			return false;
		}
		if (categoryName == null) {
			return other.categoryName == null;
		} else if (!categoryName.equals(other.categoryName)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (uniqueId == null) {
			return other.uniqueId == null;
		} else if (!uniqueId.equals(other.uniqueId)) {
			return false;
		}
		if (scorerUserId == null) {
			return other.scorerUserId == null;
		} else if (!scorerUserId.equals(other.scorerUserId)) {
			return false;
		}
		if (score == null) {
			return other.score == null;
		} else if (!score.equals(other.score)) {
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
		return true;
	}
}