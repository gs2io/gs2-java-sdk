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

package io.gs2.stamina.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.stamina.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * スタミナの最大値テーブルマスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateMaxStaminaTableMasterRequest extends Gs2BasicRequest<UpdateMaxStaminaTableMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スタミナの最大値テーブルマスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナの最大値テーブルマスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナの最大値テーブルマスターを更新
     * @return this
     */
    public UpdateMaxStaminaTableMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 最大スタミナ値テーブル名 */
    private String maxStaminaTableName;

    /**
     * 最大スタミナ値テーブル名を取得
     *
     * @return スタミナの最大値テーブルマスターを更新
     */
    public String getMaxStaminaTableName() {
        return maxStaminaTableName;
    }

    /**
     * 最大スタミナ値テーブル名を設定
     *
     * @param maxStaminaTableName スタミナの最大値テーブルマスターを更新
     */
    public void setMaxStaminaTableName(String maxStaminaTableName) {
        this.maxStaminaTableName = maxStaminaTableName;
    }

    /**
     * 最大スタミナ値テーブル名を設定
     *
     * @param maxStaminaTableName スタミナの最大値テーブルマスターを更新
     * @return this
     */
    public UpdateMaxStaminaTableMasterRequest withMaxStaminaTableName(String maxStaminaTableName) {
        setMaxStaminaTableName(maxStaminaTableName);
        return this;
    }

    /** スタミナの最大値テーブルマスターの説明 */
    private String description;

    /**
     * スタミナの最大値テーブルマスターの説明を取得
     *
     * @return スタミナの最大値テーブルマスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * スタミナの最大値テーブルマスターの説明を設定
     *
     * @param description スタミナの最大値テーブルマスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * スタミナの最大値テーブルマスターの説明を設定
     *
     * @param description スタミナの最大値テーブルマスターを更新
     * @return this
     */
    public UpdateMaxStaminaTableMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 最大スタミナ値テーブルのメタデータ */
    private String metadata;

    /**
     * 最大スタミナ値テーブルのメタデータを取得
     *
     * @return スタミナの最大値テーブルマスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 最大スタミナ値テーブルのメタデータを設定
     *
     * @param metadata スタミナの最大値テーブルマスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 最大スタミナ値テーブルのメタデータを設定
     *
     * @param metadata スタミナの最大値テーブルマスターを更新
     * @return this
     */
    public UpdateMaxStaminaTableMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 経験値の種類マスター のGRN */
    private String experienceModelId;

    /**
     * 経験値の種類マスター のGRNを取得
     *
     * @return スタミナの最大値テーブルマスターを更新
     */
    public String getExperienceModelId() {
        return experienceModelId;
    }

    /**
     * 経験値の種類マスター のGRNを設定
     *
     * @param experienceModelId スタミナの最大値テーブルマスターを更新
     */
    public void setExperienceModelId(String experienceModelId) {
        this.experienceModelId = experienceModelId;
    }

    /**
     * 経験値の種類マスター のGRNを設定
     *
     * @param experienceModelId スタミナの最大値テーブルマスターを更新
     * @return this
     */
    public UpdateMaxStaminaTableMasterRequest withExperienceModelId(String experienceModelId) {
        setExperienceModelId(experienceModelId);
        return this;
    }

    /** ランク毎のスタミナの最大値テーブル */
    private List<Integer> values;

    /**
     * ランク毎のスタミナの最大値テーブルを取得
     *
     * @return スタミナの最大値テーブルマスターを更新
     */
    public List<Integer> getValues() {
        return values;
    }

    /**
     * ランク毎のスタミナの最大値テーブルを設定
     *
     * @param values スタミナの最大値テーブルマスターを更新
     */
    public void setValues(List<Integer> values) {
        this.values = values;
    }

    /**
     * ランク毎のスタミナの最大値テーブルを設定
     *
     * @param values スタミナの最大値テーブルマスターを更新
     * @return this
     */
    public UpdateMaxStaminaTableMasterRequest withValues(List<Integer> values) {
        setValues(values);
        return this;
    }

}