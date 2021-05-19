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
 * カテゴリ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CategoryModel implements IModel, Serializable, Comparable<CategoryModel> {
	/** カテゴリ */
	protected String categoryModelId;

	/**
	 * カテゴリを取得
	 *
	 * @return カテゴリ
	 */
	public String getCategoryModelId() {
		return categoryModelId;
	}

	/**
	 * カテゴリを設定
	 *
	 * @param categoryModelId カテゴリ
	 */
	public void setCategoryModelId(String categoryModelId) {
		this.categoryModelId = categoryModelId;
	}

	/**
	 * カテゴリを設定
	 *
	 * @param categoryModelId カテゴリ
	 * @return this
	 */
	public CategoryModel withCategoryModelId(String categoryModelId) {
		this.categoryModelId = categoryModelId;
		return this;
	}
	/** カテゴリ名 */
	protected String name;

	/**
	 * カテゴリ名を取得
	 *
	 * @return カテゴリ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * カテゴリ名を設定
	 *
	 * @param name カテゴリ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * カテゴリ名を設定
	 *
	 * @param name カテゴリ名
	 * @return this
	 */
	public CategoryModel withName(String name) {
		this.name = name;
		return this;
	}
	/** カテゴリのメタデータ */
	protected String metadata;

	/**
	 * カテゴリのメタデータを取得
	 *
	 * @return カテゴリのメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * カテゴリのメタデータを設定
	 *
	 * @param metadata カテゴリのメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * カテゴリのメタデータを設定
	 *
	 * @param metadata カテゴリのメタデータ
	 * @return this
	 */
	public CategoryModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** スコアの最小値 */
	protected Long minimumValue;

	/**
	 * スコアの最小値を取得
	 *
	 * @return スコアの最小値
	 */
	public Long getMinimumValue() {
		return minimumValue;
	}

	/**
	 * スコアの最小値を設定
	 *
	 * @param minimumValue スコアの最小値
	 */
	public void setMinimumValue(Long minimumValue) {
		this.minimumValue = minimumValue;
	}

	/**
	 * スコアの最小値を設定
	 *
	 * @param minimumValue スコアの最小値
	 * @return this
	 */
	public CategoryModel withMinimumValue(Long minimumValue) {
		this.minimumValue = minimumValue;
		return this;
	}
	/** スコアの最大値 */
	protected Long maximumValue;

	/**
	 * スコアの最大値を取得
	 *
	 * @return スコアの最大値
	 */
	public Long getMaximumValue() {
		return maximumValue;
	}

	/**
	 * スコアの最大値を設定
	 *
	 * @param maximumValue スコアの最大値
	 */
	public void setMaximumValue(Long maximumValue) {
		this.maximumValue = maximumValue;
	}

	/**
	 * スコアの最大値を設定
	 *
	 * @param maximumValue スコアの最大値
	 * @return this
	 */
	public CategoryModel withMaximumValue(Long maximumValue) {
		this.maximumValue = maximumValue;
		return this;
	}
	/** スコアのソート方向 */
	protected String orderDirection;

	/**
	 * スコアのソート方向を取得
	 *
	 * @return スコアのソート方向
	 */
	public String getOrderDirection() {
		return orderDirection;
	}

	/**
	 * スコアのソート方向を設定
	 *
	 * @param orderDirection スコアのソート方向
	 */
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	/**
	 * スコアのソート方向を設定
	 *
	 * @param orderDirection スコアのソート方向
	 * @return this
	 */
	public CategoryModel withOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
		return this;
	}
	/** ランキングの種類 */
	protected String scope;

	/**
	 * ランキングの種類を取得
	 *
	 * @return ランキングの種類
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * ランキングの種類を設定
	 *
	 * @param scope ランキングの種類
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * ランキングの種類を設定
	 *
	 * @param scope ランキングの種類
	 * @return this
	 */
	public CategoryModel withScope(String scope) {
		this.scope = scope;
		return this;
	}
	/** ユーザID毎にスコアを1つしか登録されないようにする */
	protected Boolean uniqueByUserId;

	/**
	 * ユーザID毎にスコアを1つしか登録されないようにするを取得
	 *
	 * @return ユーザID毎にスコアを1つしか登録されないようにする
	 */
	public Boolean getUniqueByUserId() {
		return uniqueByUserId;
	}

	/**
	 * ユーザID毎にスコアを1つしか登録されないようにするを設定
	 *
	 * @param uniqueByUserId ユーザID毎にスコアを1つしか登録されないようにする
	 */
	public void setUniqueByUserId(Boolean uniqueByUserId) {
		this.uniqueByUserId = uniqueByUserId;
	}

	/**
	 * ユーザID毎にスコアを1つしか登録されないようにするを設定
	 *
	 * @param uniqueByUserId ユーザID毎にスコアを1つしか登録されないようにする
	 * @return this
	 */
	public CategoryModel withUniqueByUserId(Boolean uniqueByUserId) {
		this.uniqueByUserId = uniqueByUserId;
		return this;
	}
	/** スコアの固定集計開始時刻(時) */
	protected Integer calculateFixedTimingHour;

	/**
	 * スコアの固定集計開始時刻(時)を取得
	 *
	 * @return スコアの固定集計開始時刻(時)
	 */
	public Integer getCalculateFixedTimingHour() {
		return calculateFixedTimingHour;
	}

	/**
	 * スコアの固定集計開始時刻(時)を設定
	 *
	 * @param calculateFixedTimingHour スコアの固定集計開始時刻(時)
	 */
	public void setCalculateFixedTimingHour(Integer calculateFixedTimingHour) {
		this.calculateFixedTimingHour = calculateFixedTimingHour;
	}

	/**
	 * スコアの固定集計開始時刻(時)を設定
	 *
	 * @param calculateFixedTimingHour スコアの固定集計開始時刻(時)
	 * @return this
	 */
	public CategoryModel withCalculateFixedTimingHour(Integer calculateFixedTimingHour) {
		this.calculateFixedTimingHour = calculateFixedTimingHour;
		return this;
	}
	/** スコアの固定集計開始時刻(分) */
	protected Integer calculateFixedTimingMinute;

	/**
	 * スコアの固定集計開始時刻(分)を取得
	 *
	 * @return スコアの固定集計開始時刻(分)
	 */
	public Integer getCalculateFixedTimingMinute() {
		return calculateFixedTimingMinute;
	}

	/**
	 * スコアの固定集計開始時刻(分)を設定
	 *
	 * @param calculateFixedTimingMinute スコアの固定集計開始時刻(分)
	 */
	public void setCalculateFixedTimingMinute(Integer calculateFixedTimingMinute) {
		this.calculateFixedTimingMinute = calculateFixedTimingMinute;
	}

	/**
	 * スコアの固定集計開始時刻(分)を設定
	 *
	 * @param calculateFixedTimingMinute スコアの固定集計開始時刻(分)
	 * @return this
	 */
	public CategoryModel withCalculateFixedTimingMinute(Integer calculateFixedTimingMinute) {
		this.calculateFixedTimingMinute = calculateFixedTimingMinute;
		return this;
	}
	/** スコアの集計間隔(分) */
	protected Integer calculateIntervalMinutes;

	/**
	 * スコアの集計間隔(分)を取得
	 *
	 * @return スコアの集計間隔(分)
	 */
	public Integer getCalculateIntervalMinutes() {
		return calculateIntervalMinutes;
	}

	/**
	 * スコアの集計間隔(分)を設定
	 *
	 * @param calculateIntervalMinutes スコアの集計間隔(分)
	 */
	public void setCalculateIntervalMinutes(Integer calculateIntervalMinutes) {
		this.calculateIntervalMinutes = calculateIntervalMinutes;
	}

	/**
	 * スコアの集計間隔(分)を設定
	 *
	 * @param calculateIntervalMinutes スコアの集計間隔(分)
	 * @return this
	 */
	public CategoryModel withCalculateIntervalMinutes(Integer calculateIntervalMinutes) {
		this.calculateIntervalMinutes = calculateIntervalMinutes;
		return this;
	}
	/** スコアの登録可能期間とするイベントマスター のGRN */
	protected String entryPeriodEventId;

	/**
	 * スコアの登録可能期間とするイベントマスター のGRNを取得
	 *
	 * @return スコアの登録可能期間とするイベントマスター のGRN
	 */
	public String getEntryPeriodEventId() {
		return entryPeriodEventId;
	}

	/**
	 * スコアの登録可能期間とするイベントマスター のGRNを設定
	 *
	 * @param entryPeriodEventId スコアの登録可能期間とするイベントマスター のGRN
	 */
	public void setEntryPeriodEventId(String entryPeriodEventId) {
		this.entryPeriodEventId = entryPeriodEventId;
	}

	/**
	 * スコアの登録可能期間とするイベントマスター のGRNを設定
	 *
	 * @param entryPeriodEventId スコアの登録可能期間とするイベントマスター のGRN
	 * @return this
	 */
	public CategoryModel withEntryPeriodEventId(String entryPeriodEventId) {
		this.entryPeriodEventId = entryPeriodEventId;
		return this;
	}
	/** アクセス可能期間とするイベントマスター のGRN */
	protected String accessPeriodEventId;

	/**
	 * アクセス可能期間とするイベントマスター のGRNを取得
	 *
	 * @return アクセス可能期間とするイベントマスター のGRN
	 */
	public String getAccessPeriodEventId() {
		return accessPeriodEventId;
	}

	/**
	 * アクセス可能期間とするイベントマスター のGRNを設定
	 *
	 * @param accessPeriodEventId アクセス可能期間とするイベントマスター のGRN
	 */
	public void setAccessPeriodEventId(String accessPeriodEventId) {
		this.accessPeriodEventId = accessPeriodEventId;
	}

	/**
	 * アクセス可能期間とするイベントマスター のGRNを設定
	 *
	 * @param accessPeriodEventId アクセス可能期間とするイベントマスター のGRN
	 * @return this
	 */
	public CategoryModel withAccessPeriodEventId(String accessPeriodEventId) {
		this.accessPeriodEventId = accessPeriodEventId;
		return this;
	}
	/** ランキングの世代 */
	protected String generation;

	/**
	 * ランキングの世代を取得
	 *
	 * @return ランキングの世代
	 */
	public String getGeneration() {
		return generation;
	}

	/**
	 * ランキングの世代を設定
	 *
	 * @param generation ランキングの世代
	 */
	public void setGeneration(String generation) {
		this.generation = generation;
	}

	/**
	 * ランキングの世代を設定
	 *
	 * @param generation ランキングの世代
	 * @return this
	 */
	public CategoryModel withGeneration(String generation) {
		this.generation = generation;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("categoryModelId", this.getCategoryModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("minimumValue", this.getMinimumValue())
            .put("maximumValue", this.getMaximumValue())
            .put("orderDirection", this.getOrderDirection())
            .put("scope", this.getScope())
            .put("uniqueByUserId", this.getUniqueByUserId())
            .put("calculateFixedTimingHour", this.getCalculateFixedTimingHour())
            .put("calculateFixedTimingMinute", this.getCalculateFixedTimingMinute())
            .put("calculateIntervalMinutes", this.getCalculateIntervalMinutes())
            .put("entryPeriodEventId", this.getEntryPeriodEventId())
            .put("accessPeriodEventId", this.getAccessPeriodEventId())
            .put("generation", this.getGeneration());
        return body_;
    }
	@Override
	public int compareTo(CategoryModel o) {
		return categoryModelId.compareTo(o.categoryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.categoryModelId == null) ? 0 : this.categoryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.minimumValue == null) ? 0 : this.minimumValue.hashCode());
        result = prime * result + ((this.maximumValue == null) ? 0 : this.maximumValue.hashCode());
        result = prime * result + ((this.orderDirection == null) ? 0 : this.orderDirection.hashCode());
        result = prime * result + ((this.scope == null) ? 0 : this.scope.hashCode());
        result = prime * result + ((this.uniqueByUserId == null) ? 0 : this.uniqueByUserId.hashCode());
        result = prime * result + ((this.calculateFixedTimingHour == null) ? 0 : this.calculateFixedTimingHour.hashCode());
        result = prime * result + ((this.calculateFixedTimingMinute == null) ? 0 : this.calculateFixedTimingMinute.hashCode());
        result = prime * result + ((this.calculateIntervalMinutes == null) ? 0 : this.calculateIntervalMinutes.hashCode());
        result = prime * result + ((this.entryPeriodEventId == null) ? 0 : this.entryPeriodEventId.hashCode());
        result = prime * result + ((this.accessPeriodEventId == null) ? 0 : this.accessPeriodEventId.hashCode());
        result = prime * result + ((this.generation == null) ? 0 : this.generation.hashCode());
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
		CategoryModel other = (CategoryModel) o;
		if (categoryModelId == null) {
			return other.categoryModelId == null;
		} else if (!categoryModelId.equals(other.categoryModelId)) {
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
		if (minimumValue == null) {
			return other.minimumValue == null;
		} else if (!minimumValue.equals(other.minimumValue)) {
			return false;
		}
		if (maximumValue == null) {
			return other.maximumValue == null;
		} else if (!maximumValue.equals(other.maximumValue)) {
			return false;
		}
		if (orderDirection == null) {
			return other.orderDirection == null;
		} else if (!orderDirection.equals(other.orderDirection)) {
			return false;
		}
		if (scope == null) {
			return other.scope == null;
		} else if (!scope.equals(other.scope)) {
			return false;
		}
		if (uniqueByUserId == null) {
			return other.uniqueByUserId == null;
		} else if (!uniqueByUserId.equals(other.uniqueByUserId)) {
			return false;
		}
		if (calculateFixedTimingHour == null) {
			return other.calculateFixedTimingHour == null;
		} else if (!calculateFixedTimingHour.equals(other.calculateFixedTimingHour)) {
			return false;
		}
		if (calculateFixedTimingMinute == null) {
			return other.calculateFixedTimingMinute == null;
		} else if (!calculateFixedTimingMinute.equals(other.calculateFixedTimingMinute)) {
			return false;
		}
		if (calculateIntervalMinutes == null) {
			return other.calculateIntervalMinutes == null;
		} else if (!calculateIntervalMinutes.equals(other.calculateIntervalMinutes)) {
			return false;
		}
		if (entryPeriodEventId == null) {
			return other.entryPeriodEventId == null;
		} else if (!entryPeriodEventId.equals(other.entryPeriodEventId)) {
			return false;
		}
		if (accessPeriodEventId == null) {
			return other.accessPeriodEventId == null;
		} else if (!accessPeriodEventId.equals(other.accessPeriodEventId)) {
			return false;
		}
		if (generation == null) {
			return other.generation == null;
		} else if (!generation.equals(other.generation)) {
			return false;
		}
		return true;
	}
}