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

package io.gs2.ranking.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.ranking.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * カテゴリマスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateCategoryModelMasterRequest extends Gs2BasicRequest<UpdateCategoryModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return カテゴリマスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName カテゴリマスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** カテゴリモデル名 */
    private String categoryName;

    /**
     * カテゴリモデル名を取得
     *
     * @return カテゴリマスターを更新
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * カテゴリモデル名を設定
     *
     * @param categoryName カテゴリマスターを更新
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * カテゴリモデル名を設定
     *
     * @param categoryName カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withCategoryName(String categoryName) {
        setCategoryName(categoryName);
        return this;
    }

    /** カテゴリマスターの説明 */
    private String description;

    /**
     * カテゴリマスターの説明を取得
     *
     * @return カテゴリマスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * カテゴリマスターの説明を設定
     *
     * @param description カテゴリマスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * カテゴリマスターの説明を設定
     *
     * @param description カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** カテゴリマスターのメタデータ */
    private String metadata;

    /**
     * カテゴリマスターのメタデータを取得
     *
     * @return カテゴリマスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * カテゴリマスターのメタデータを設定
     *
     * @param metadata カテゴリマスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * カテゴリマスターのメタデータを設定
     *
     * @param metadata カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** スコアの最小値 */
    private Long minimumValue;

    /**
     * スコアの最小値を取得
     *
     * @return カテゴリマスターを更新
     */
    public Long getMinimumValue() {
        return minimumValue;
    }

    /**
     * スコアの最小値を設定
     *
     * @param minimumValue カテゴリマスターを更新
     */
    public void setMinimumValue(Long minimumValue) {
        this.minimumValue = minimumValue;
    }

    /**
     * スコアの最小値を設定
     *
     * @param minimumValue カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withMinimumValue(Long minimumValue) {
        setMinimumValue(minimumValue);
        return this;
    }

    /** スコアの最大値 */
    private Long maximumValue;

    /**
     * スコアの最大値を取得
     *
     * @return カテゴリマスターを更新
     */
    public Long getMaximumValue() {
        return maximumValue;
    }

    /**
     * スコアの最大値を設定
     *
     * @param maximumValue カテゴリマスターを更新
     */
    public void setMaximumValue(Long maximumValue) {
        this.maximumValue = maximumValue;
    }

    /**
     * スコアの最大値を設定
     *
     * @param maximumValue カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withMaximumValue(Long maximumValue) {
        setMaximumValue(maximumValue);
        return this;
    }

    /** スコアのソート方向 */
    private String orderDirection;

    /**
     * スコアのソート方向を取得
     *
     * @return カテゴリマスターを更新
     */
    public String getOrderDirection() {
        return orderDirection;
    }

    /**
     * スコアのソート方向を設定
     *
     * @param orderDirection カテゴリマスターを更新
     */
    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    /**
     * スコアのソート方向を設定
     *
     * @param orderDirection カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withOrderDirection(String orderDirection) {
        setOrderDirection(orderDirection);
        return this;
    }

    /** ランキングの種類 */
    private String scope;

    /**
     * ランキングの種類を取得
     *
     * @return カテゴリマスターを更新
     */
    public String getScope() {
        return scope;
    }

    /**
     * ランキングの種類を設定
     *
     * @param scope カテゴリマスターを更新
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * ランキングの種類を設定
     *
     * @param scope カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withScope(String scope) {
        setScope(scope);
        return this;
    }

    /** ユーザID毎にスコアを1つしか登録されないようにする */
    private Boolean uniqueByUserId;

    /**
     * ユーザID毎にスコアを1つしか登録されないようにするを取得
     *
     * @return カテゴリマスターを更新
     */
    public Boolean getUniqueByUserId() {
        return uniqueByUserId;
    }

    /**
     * ユーザID毎にスコアを1つしか登録されないようにするを設定
     *
     * @param uniqueByUserId カテゴリマスターを更新
     */
    public void setUniqueByUserId(Boolean uniqueByUserId) {
        this.uniqueByUserId = uniqueByUserId;
    }

    /**
     * ユーザID毎にスコアを1つしか登録されないようにするを設定
     *
     * @param uniqueByUserId カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withUniqueByUserId(Boolean uniqueByUserId) {
        setUniqueByUserId(uniqueByUserId);
        return this;
    }

    /** スコアの固定集計開始時刻(時) */
    private Integer calculateFixedTimingHour;

    /**
     * スコアの固定集計開始時刻(時)を取得
     *
     * @return カテゴリマスターを更新
     */
    public Integer getCalculateFixedTimingHour() {
        return calculateFixedTimingHour;
    }

    /**
     * スコアの固定集計開始時刻(時)を設定
     *
     * @param calculateFixedTimingHour カテゴリマスターを更新
     */
    public void setCalculateFixedTimingHour(Integer calculateFixedTimingHour) {
        this.calculateFixedTimingHour = calculateFixedTimingHour;
    }

    /**
     * スコアの固定集計開始時刻(時)を設定
     *
     * @param calculateFixedTimingHour カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withCalculateFixedTimingHour(Integer calculateFixedTimingHour) {
        setCalculateFixedTimingHour(calculateFixedTimingHour);
        return this;
    }

    /** スコアの固定集計開始時刻(分) */
    private Integer calculateFixedTimingMinute;

    /**
     * スコアの固定集計開始時刻(分)を取得
     *
     * @return カテゴリマスターを更新
     */
    public Integer getCalculateFixedTimingMinute() {
        return calculateFixedTimingMinute;
    }

    /**
     * スコアの固定集計開始時刻(分)を設定
     *
     * @param calculateFixedTimingMinute カテゴリマスターを更新
     */
    public void setCalculateFixedTimingMinute(Integer calculateFixedTimingMinute) {
        this.calculateFixedTimingMinute = calculateFixedTimingMinute;
    }

    /**
     * スコアの固定集計開始時刻(分)を設定
     *
     * @param calculateFixedTimingMinute カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withCalculateFixedTimingMinute(Integer calculateFixedTimingMinute) {
        setCalculateFixedTimingMinute(calculateFixedTimingMinute);
        return this;
    }

    /** スコアの集計間隔(分) */
    private Integer calculateIntervalMinutes;

    /**
     * スコアの集計間隔(分)を取得
     *
     * @return カテゴリマスターを更新
     */
    public Integer getCalculateIntervalMinutes() {
        return calculateIntervalMinutes;
    }

    /**
     * スコアの集計間隔(分)を設定
     *
     * @param calculateIntervalMinutes カテゴリマスターを更新
     */
    public void setCalculateIntervalMinutes(Integer calculateIntervalMinutes) {
        this.calculateIntervalMinutes = calculateIntervalMinutes;
    }

    /**
     * スコアの集計間隔(分)を設定
     *
     * @param calculateIntervalMinutes カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withCalculateIntervalMinutes(Integer calculateIntervalMinutes) {
        setCalculateIntervalMinutes(calculateIntervalMinutes);
        return this;
    }

    /** スコアの登録可能期間とするイベントマスター のGRN */
    private String entryPeriodEventId;

    /**
     * スコアの登録可能期間とするイベントマスター のGRNを取得
     *
     * @return カテゴリマスターを更新
     */
    public String getEntryPeriodEventId() {
        return entryPeriodEventId;
    }

    /**
     * スコアの登録可能期間とするイベントマスター のGRNを設定
     *
     * @param entryPeriodEventId カテゴリマスターを更新
     */
    public void setEntryPeriodEventId(String entryPeriodEventId) {
        this.entryPeriodEventId = entryPeriodEventId;
    }

    /**
     * スコアの登録可能期間とするイベントマスター のGRNを設定
     *
     * @param entryPeriodEventId カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withEntryPeriodEventId(String entryPeriodEventId) {
        setEntryPeriodEventId(entryPeriodEventId);
        return this;
    }

    /** アクセス可能期間とするイベントマスター のGRN */
    private String accessPeriodEventId;

    /**
     * アクセス可能期間とするイベントマスター のGRNを取得
     *
     * @return カテゴリマスターを更新
     */
    public String getAccessPeriodEventId() {
        return accessPeriodEventId;
    }

    /**
     * アクセス可能期間とするイベントマスター のGRNを設定
     *
     * @param accessPeriodEventId カテゴリマスターを更新
     */
    public void setAccessPeriodEventId(String accessPeriodEventId) {
        this.accessPeriodEventId = accessPeriodEventId;
    }

    /**
     * アクセス可能期間とするイベントマスター のGRNを設定
     *
     * @param accessPeriodEventId カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withAccessPeriodEventId(String accessPeriodEventId) {
        setAccessPeriodEventId(accessPeriodEventId);
        return this;
    }

    /** ランキングの世代 */
    private String generation;

    /**
     * ランキングの世代を取得
     *
     * @return カテゴリマスターを更新
     */
    public String getGeneration() {
        return generation;
    }

    /**
     * ランキングの世代を設定
     *
     * @param generation カテゴリマスターを更新
     */
    public void setGeneration(String generation) {
        this.generation = generation;
    }

    /**
     * ランキングの世代を設定
     *
     * @param generation カテゴリマスターを更新
     * @return this
     */
    public UpdateCategoryModelMasterRequest withGeneration(String generation) {
        setGeneration(generation);
        return this;
    }

}