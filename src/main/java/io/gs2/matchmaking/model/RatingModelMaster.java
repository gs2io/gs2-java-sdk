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
 * レーティングモデルマスター
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RatingModelMaster implements IModel, Serializable, Comparable<RatingModelMaster> {
	/** レーティングモデルマスター */
	protected String ratingModelId;

	/**
	 * レーティングモデルマスターを取得
	 *
	 * @return レーティングモデルマスター
	 */
	public String getRatingModelId() {
		return ratingModelId;
	}

	/**
	 * レーティングモデルマスターを設定
	 *
	 * @param ratingModelId レーティングモデルマスター
	 */
	public void setRatingModelId(String ratingModelId) {
		this.ratingModelId = ratingModelId;
	}

	/**
	 * レーティングモデルマスターを設定
	 *
	 * @param ratingModelId レーティングモデルマスター
	 * @return this
	 */
	public RatingModelMaster withRatingModelId(String ratingModelId) {
		this.ratingModelId = ratingModelId;
		return this;
	}
	/** レーティングの種類名 */
	protected String name;

	/**
	 * レーティングの種類名を取得
	 *
	 * @return レーティングの種類名
	 */
	public String getName() {
		return name;
	}

	/**
	 * レーティングの種類名を設定
	 *
	 * @param name レーティングの種類名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * レーティングの種類名を設定
	 *
	 * @param name レーティングの種類名
	 * @return this
	 */
	public RatingModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	/** レーティングの種類のメタデータ */
	protected String metadata;

	/**
	 * レーティングの種類のメタデータを取得
	 *
	 * @return レーティングの種類のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * レーティングの種類のメタデータを設定
	 *
	 * @param metadata レーティングの種類のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * レーティングの種類のメタデータを設定
	 *
	 * @param metadata レーティングの種類のメタデータ
	 * @return this
	 */
	public RatingModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** レーティングモデルマスターの説明 */
	protected String description;

	/**
	 * レーティングモデルマスターの説明を取得
	 *
	 * @return レーティングモデルマスターの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * レーティングモデルマスターの説明を設定
	 *
	 * @param description レーティングモデルマスターの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * レーティングモデルマスターの説明を設定
	 *
	 * @param description レーティングモデルマスターの説明
	 * @return this
	 */
	public RatingModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	/** レート値の変動の大きさ */
	protected Integer volatility;

	/**
	 * レート値の変動の大きさを取得
	 *
	 * @return レート値の変動の大きさ
	 */
	public Integer getVolatility() {
		return volatility;
	}

	/**
	 * レート値の変動の大きさを設定
	 *
	 * @param volatility レート値の変動の大きさ
	 */
	public void setVolatility(Integer volatility) {
		this.volatility = volatility;
	}

	/**
	 * レート値の変動の大きさを設定
	 *
	 * @param volatility レート値の変動の大きさ
	 * @return this
	 */
	public RatingModelMaster withVolatility(Integer volatility) {
		this.volatility = volatility;
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
	public RatingModelMaster withCreatedAt(Long createdAt) {
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
	public RatingModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("ratingModelId", this.getRatingModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("description", this.getDescription())
            .put("volatility", this.getVolatility())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
    }
	@Override
	public int compareTo(RatingModelMaster o) {
		return ratingModelId.compareTo(o.ratingModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.ratingModelId == null) ? 0 : this.ratingModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.volatility == null) ? 0 : this.volatility.hashCode());
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
		RatingModelMaster other = (RatingModelMaster) o;
		if (ratingModelId == null) {
			return other.ratingModelId == null;
		} else if (!ratingModelId.equals(other.ratingModelId)) {
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
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (volatility == null) {
			return other.volatility == null;
		} else if (!volatility.equals(other.volatility)) {
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