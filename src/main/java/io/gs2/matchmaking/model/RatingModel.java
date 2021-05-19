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
 * レーティングモデル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RatingModel implements IModel, Serializable, Comparable<RatingModel> {
	/** レーティングモデル */
	protected String ratingModelId;

	/**
	 * レーティングモデルを取得
	 *
	 * @return レーティングモデル
	 */
	public String getRatingModelId() {
		return ratingModelId;
	}

	/**
	 * レーティングモデルを設定
	 *
	 * @param ratingModelId レーティングモデル
	 */
	public void setRatingModelId(String ratingModelId) {
		this.ratingModelId = ratingModelId;
	}

	/**
	 * レーティングモデルを設定
	 *
	 * @param ratingModelId レーティングモデル
	 * @return this
	 */
	public RatingModel withRatingModelId(String ratingModelId) {
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
	public RatingModel withName(String name) {
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
	public RatingModel withMetadata(String metadata) {
		this.metadata = metadata;
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
	public RatingModel withVolatility(Integer volatility) {
		this.volatility = volatility;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("ratingModelId", this.getRatingModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("volatility", this.getVolatility());
        return body_;
    }
	@Override
	public int compareTo(RatingModel o) {
		return ratingModelId.compareTo(o.ratingModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.ratingModelId == null) ? 0 : this.ratingModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.volatility == null) ? 0 : this.volatility.hashCode());
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
		RatingModel other = (RatingModel) o;
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
		if (volatility == null) {
			return other.volatility == null;
		} else if (!volatility.equals(other.volatility)) {
			return false;
		}
		return true;
	}
}