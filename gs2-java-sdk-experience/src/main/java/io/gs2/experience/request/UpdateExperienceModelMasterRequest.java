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

package io.gs2.experience.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.experience.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * 経験値の種類マスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateExperienceModelMasterRequest extends Gs2BasicRequest<UpdateExperienceModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 経験値の種類マスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 経験値の種類マスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 経験値の種類マスターを更新
     * @return this
     */
    public UpdateExperienceModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 経験値の種類名 */
    private String experienceName;

    /**
     * 経験値の種類名を取得
     *
     * @return 経験値の種類マスターを更新
     */
    public String getExperienceName() {
        return experienceName;
    }

    /**
     * 経験値の種類名を設定
     *
     * @param experienceName 経験値の種類マスターを更新
     */
    public void setExperienceName(String experienceName) {
        this.experienceName = experienceName;
    }

    /**
     * 経験値の種類名を設定
     *
     * @param experienceName 経験値の種類マスターを更新
     * @return this
     */
    public UpdateExperienceModelMasterRequest withExperienceName(String experienceName) {
        setExperienceName(experienceName);
        return this;
    }

    /** 経験値の種類マスターの説明 */
    private String description;

    /**
     * 経験値の種類マスターの説明を取得
     *
     * @return 経験値の種類マスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * 経験値の種類マスターの説明を設定
     *
     * @param description 経験値の種類マスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 経験値の種類マスターの説明を設定
     *
     * @param description 経験値の種類マスターを更新
     * @return this
     */
    public UpdateExperienceModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 経験値の種類のメタデータ */
    private String metadata;

    /**
     * 経験値の種類のメタデータを取得
     *
     * @return 経験値の種類マスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 経験値の種類のメタデータを設定
     *
     * @param metadata 経験値の種類マスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 経験値の種類のメタデータを設定
     *
     * @param metadata 経験値の種類マスターを更新
     * @return this
     */
    public UpdateExperienceModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 経験値の初期値 */
    private Long defaultExperience;

    /**
     * 経験値の初期値を取得
     *
     * @return 経験値の種類マスターを更新
     */
    public Long getDefaultExperience() {
        return defaultExperience;
    }

    /**
     * 経験値の初期値を設定
     *
     * @param defaultExperience 経験値の種類マスターを更新
     */
    public void setDefaultExperience(Long defaultExperience) {
        this.defaultExperience = defaultExperience;
    }

    /**
     * 経験値の初期値を設定
     *
     * @param defaultExperience 経験値の種類マスターを更新
     * @return this
     */
    public UpdateExperienceModelMasterRequest withDefaultExperience(Long defaultExperience) {
        setDefaultExperience(defaultExperience);
        return this;
    }

    /** ランクキャップの初期値 */
    private Long defaultRankCap;

    /**
     * ランクキャップの初期値を取得
     *
     * @return 経験値の種類マスターを更新
     */
    public Long getDefaultRankCap() {
        return defaultRankCap;
    }

    /**
     * ランクキャップの初期値を設定
     *
     * @param defaultRankCap 経験値の種類マスターを更新
     */
    public void setDefaultRankCap(Long defaultRankCap) {
        this.defaultRankCap = defaultRankCap;
    }

    /**
     * ランクキャップの初期値を設定
     *
     * @param defaultRankCap 経験値の種類マスターを更新
     * @return this
     */
    public UpdateExperienceModelMasterRequest withDefaultRankCap(Long defaultRankCap) {
        setDefaultRankCap(defaultRankCap);
        return this;
    }

    /** ランクキャップの最大値 */
    private Long maxRankCap;

    /**
     * ランクキャップの最大値を取得
     *
     * @return 経験値の種類マスターを更新
     */
    public Long getMaxRankCap() {
        return maxRankCap;
    }

    /**
     * ランクキャップの最大値を設定
     *
     * @param maxRankCap 経験値の種類マスターを更新
     */
    public void setMaxRankCap(Long maxRankCap) {
        this.maxRankCap = maxRankCap;
    }

    /**
     * ランクキャップの最大値を設定
     *
     * @param maxRankCap 経験値の種類マスターを更新
     * @return this
     */
    public UpdateExperienceModelMasterRequest withMaxRankCap(Long maxRankCap) {
        setMaxRankCap(maxRankCap);
        return this;
    }

    /** ランク計算に用いる */
    private String rankThresholdId;

    /**
     * ランク計算に用いるを取得
     *
     * @return 経験値の種類マスターを更新
     */
    public String getRankThresholdId() {
        return rankThresholdId;
    }

    /**
     * ランク計算に用いるを設定
     *
     * @param rankThresholdId 経験値の種類マスターを更新
     */
    public void setRankThresholdId(String rankThresholdId) {
        this.rankThresholdId = rankThresholdId;
    }

    /**
     * ランク計算に用いるを設定
     *
     * @param rankThresholdId 経験値の種類マスターを更新
     * @return this
     */
    public UpdateExperienceModelMasterRequest withRankThresholdId(String rankThresholdId) {
        setRankThresholdId(rankThresholdId);
        return this;
    }

}