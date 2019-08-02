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
 * 経験値の種類マスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateExperienceModelMasterRequest extends Gs2BasicRequest<CreateExperienceModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 経験値の種類マスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 経験値の種類マスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 経験値の種類マスターを新規作成
     * @return this
     */
    public CreateExperienceModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 経験値の種類名 */
    private String name;

    /**
     * 経験値の種類名を取得
     *
     * @return 経験値の種類マスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * 経験値の種類名を設定
     *
     * @param name 経験値の種類マスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 経験値の種類名を設定
     *
     * @param name 経験値の種類マスターを新規作成
     * @return this
     */
    public CreateExperienceModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 経験値の種類マスターの説明 */
    private String description;

    /**
     * 経験値の種類マスターの説明を取得
     *
     * @return 経験値の種類マスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * 経験値の種類マスターの説明を設定
     *
     * @param description 経験値の種類マスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 経験値の種類マスターの説明を設定
     *
     * @param description 経験値の種類マスターを新規作成
     * @return this
     */
    public CreateExperienceModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 経験値の種類のメタデータ */
    private String metadata;

    /**
     * 経験値の種類のメタデータを取得
     *
     * @return 経験値の種類マスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 経験値の種類のメタデータを設定
     *
     * @param metadata 経験値の種類マスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 経験値の種類のメタデータを設定
     *
     * @param metadata 経験値の種類マスターを新規作成
     * @return this
     */
    public CreateExperienceModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 経験値の初期値 */
    private Long defaultExperience;

    /**
     * 経験値の初期値を取得
     *
     * @return 経験値の種類マスターを新規作成
     */
    public Long getDefaultExperience() {
        return defaultExperience;
    }

    /**
     * 経験値の初期値を設定
     *
     * @param defaultExperience 経験値の種類マスターを新規作成
     */
    public void setDefaultExperience(Long defaultExperience) {
        this.defaultExperience = defaultExperience;
    }

    /**
     * 経験値の初期値を設定
     *
     * @param defaultExperience 経験値の種類マスターを新規作成
     * @return this
     */
    public CreateExperienceModelMasterRequest withDefaultExperience(Long defaultExperience) {
        setDefaultExperience(defaultExperience);
        return this;
    }

    /** ランクキャップの初期値 */
    private Long defaultRankCap;

    /**
     * ランクキャップの初期値を取得
     *
     * @return 経験値の種類マスターを新規作成
     */
    public Long getDefaultRankCap() {
        return defaultRankCap;
    }

    /**
     * ランクキャップの初期値を設定
     *
     * @param defaultRankCap 経験値の種類マスターを新規作成
     */
    public void setDefaultRankCap(Long defaultRankCap) {
        this.defaultRankCap = defaultRankCap;
    }

    /**
     * ランクキャップの初期値を設定
     *
     * @param defaultRankCap 経験値の種類マスターを新規作成
     * @return this
     */
    public CreateExperienceModelMasterRequest withDefaultRankCap(Long defaultRankCap) {
        setDefaultRankCap(defaultRankCap);
        return this;
    }

    /** ランクキャップの最大値 */
    private Long maxRankCap;

    /**
     * ランクキャップの最大値を取得
     *
     * @return 経験値の種類マスターを新規作成
     */
    public Long getMaxRankCap() {
        return maxRankCap;
    }

    /**
     * ランクキャップの最大値を設定
     *
     * @param maxRankCap 経験値の種類マスターを新規作成
     */
    public void setMaxRankCap(Long maxRankCap) {
        this.maxRankCap = maxRankCap;
    }

    /**
     * ランクキャップの最大値を設定
     *
     * @param maxRankCap 経験値の種類マスターを新規作成
     * @return this
     */
    public CreateExperienceModelMasterRequest withMaxRankCap(Long maxRankCap) {
        setMaxRankCap(maxRankCap);
        return this;
    }

    /** ランク計算に用いる */
    private String rankThresholdId;

    /**
     * ランク計算に用いるを取得
     *
     * @return 経験値の種類マスターを新規作成
     */
    public String getRankThresholdId() {
        return rankThresholdId;
    }

    /**
     * ランク計算に用いるを設定
     *
     * @param rankThresholdId 経験値の種類マスターを新規作成
     */
    public void setRankThresholdId(String rankThresholdId) {
        this.rankThresholdId = rankThresholdId;
    }

    /**
     * ランク計算に用いるを設定
     *
     * @param rankThresholdId 経験値の種類マスターを新規作成
     * @return this
     */
    public CreateExperienceModelMasterRequest withRankThresholdId(String rankThresholdId) {
        setRankThresholdId(rankThresholdId);
        return this;
    }

}