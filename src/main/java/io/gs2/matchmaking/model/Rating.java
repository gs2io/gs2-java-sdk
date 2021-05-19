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

package io.gs2.matchmaking.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * レーティング
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Rating implements IModel, Serializable, Comparable<Rating> {
	/** レーティング */
	protected String ratingId;

	/**
	 * レーティングを取得
	 *
	 * @return レーティング
	 */
	public String getRatingId() {
		return ratingId;
	}

	/**
	 * レーティングを設定
	 *
	 * @param ratingId レーティング
	 */
	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}

	/**
	 * レーティングを設定
	 *
	 * @param ratingId レーティング
	 * @return this
	 */
	public Rating withRatingId(String ratingId) {
		this.ratingId = ratingId;
		return this;
	}
	/** レーティング名 */
	protected String name;

	/**
	 * レーティング名を取得
	 *
	 * @return レーティング名
	 */
	public String getName() {
		return name;
	}

	/**
	 * レーティング名を設定
	 *
	 * @param name レーティング名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * レーティング名を設定
	 *
	 * @param name レーティング名
	 * @return this
	 */
	public Rating withName(String name) {
		this.name = name;
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
	public Rating withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** None */
	protected Float rateValue;

	/**
	 * Noneを取得
	 *
	 * @return None
	 */
	public Float getRateValue() {
		return rateValue;
	}

	/**
	 * Noneを設定
	 *
	 * @param rateValue None
	 */
	public void setRateValue(Float rateValue) {
		this.rateValue = rateValue;
	}

	/**
	 * Noneを設定
	 *
	 * @param rateValue None
	 * @return this
	 */
	public Rating withRateValue(Float rateValue) {
		this.rateValue = rateValue;
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
	public Rating withCreatedAt(Long createdAt) {
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
	public Rating withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("ratingId", this.getRatingId())
            .put("name", this.getName())
            .put("userId", this.getUserId())
            .put("rateValue", this.getRateValue())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(Rating o) {
		return ratingId.compareTo(o.ratingId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.ratingId == null) ? 0 : this.ratingId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.rateValue == null) ? 0 : this.rateValue.hashCode());
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
		Rating other = (Rating) o;
		if (ratingId == null) {
			return other.ratingId == null;
		} else if (!ratingId.equals(other.ratingId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (rateValue == null) {
			return other.rateValue == null;
		} else if (!rateValue.equals(other.rateValue)) {
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