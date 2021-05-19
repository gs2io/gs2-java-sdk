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
 * ランキング
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Ranking implements IModel, Serializable {
	/** 順位 */
	protected Long rank;

	/**
	 * 順位を取得
	 *
	 * @return 順位
	 */
	public Long getRank() {
		return rank;
	}

	/**
	 * 順位を設定
	 *
	 * @param rank 順位
	 */
	public void setRank(Long rank) {
		this.rank = rank;
	}

	/**
	 * 順位を設定
	 *
	 * @param rank 順位
	 * @return this
	 */
	public Ranking withRank(Long rank) {
		this.rank = rank;
		return this;
	}
	/** 1位からのインデックス */
	protected Long index;

	/**
	 * 1位からのインデックスを取得
	 *
	 * @return 1位からのインデックス
	 */
	public Long getIndex() {
		return index;
	}

	/**
	 * 1位からのインデックスを設定
	 *
	 * @param index 1位からのインデックス
	 */
	public void setIndex(Long index) {
		this.index = index;
	}

	/**
	 * 1位からのインデックスを設定
	 *
	 * @param index 1位からのインデックス
	 * @return this
	 */
	public Ranking withIndex(Long index) {
		this.index = index;
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
	public Ranking withUserId(String userId) {
		this.userId = userId;
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
	public Ranking withScore(Long score) {
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
	public Ranking withMetadata(String metadata) {
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
	public Ranking withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("rank", this.getRank())
            .put("index", this.getIndex())
            .put("userId", this.getUserId())
            .put("score", this.getScore())
            .put("metadata", this.getMetadata())
            .put("createdAt", this.getCreatedAt());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.rank == null) ? 0 : this.rank.hashCode());
        result = prime * result + ((this.index == null) ? 0 : this.index.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
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
		Ranking other = (Ranking) o;
		if (rank == null) {
			return other.rank == null;
		} else if (!rank.equals(other.rank)) {
			return false;
		}
		if (index == null) {
			return other.index == null;
		} else if (!index.equals(other.index)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
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