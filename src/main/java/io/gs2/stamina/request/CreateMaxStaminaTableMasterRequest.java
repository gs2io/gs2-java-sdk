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
import io.gs2.core.control.Gs2BasicRequest;

/**
 * スタミナの最大値テーブルマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateMaxStaminaTableMasterRequest extends Gs2BasicRequest<CreateMaxStaminaTableMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スタミナの最大値テーブルマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナの最大値テーブルマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナの最大値テーブルマスターを新規作成
     * @return this
     */
    public CreateMaxStaminaTableMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 最大スタミナ値テーブル名 */
    private String name;

    /**
     * 最大スタミナ値テーブル名を取得
     *
     * @return スタミナの最大値テーブルマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * 最大スタミナ値テーブル名を設定
     *
     * @param name スタミナの最大値テーブルマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 最大スタミナ値テーブル名を設定
     *
     * @param name スタミナの最大値テーブルマスターを新規作成
     * @return this
     */
    public CreateMaxStaminaTableMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** スタミナの最大値テーブルマスターの説明 */
    private String description;

    /**
     * スタミナの最大値テーブルマスターの説明を取得
     *
     * @return スタミナの最大値テーブルマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * スタミナの最大値テーブルマスターの説明を設定
     *
     * @param description スタミナの最大値テーブルマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * スタミナの最大値テーブルマスターの説明を設定
     *
     * @param description スタミナの最大値テーブルマスターを新規作成
     * @return this
     */
    public CreateMaxStaminaTableMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 最大スタミナ値テーブルのメタデータ */
    private String metadata;

    /**
     * 最大スタミナ値テーブルのメタデータを取得
     *
     * @return スタミナの最大値テーブルマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 最大スタミナ値テーブルのメタデータを設定
     *
     * @param metadata スタミナの最大値テーブルマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 最大スタミナ値テーブルのメタデータを設定
     *
     * @param metadata スタミナの最大値テーブルマスターを新規作成
     * @return this
     */
    public CreateMaxStaminaTableMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 経験値の種類マスター のGRN */
    private String experienceModelId;

    /**
     * 経験値の種類マスター のGRNを取得
     *
     * @return スタミナの最大値テーブルマスターを新規作成
     */
    public String getExperienceModelId() {
        return experienceModelId;
    }

    /**
     * 経験値の種類マスター のGRNを設定
     *
     * @param experienceModelId スタミナの最大値テーブルマスターを新規作成
     */
    public void setExperienceModelId(String experienceModelId) {
        this.experienceModelId = experienceModelId;
    }

    /**
     * 経験値の種類マスター のGRNを設定
     *
     * @param experienceModelId スタミナの最大値テーブルマスターを新規作成
     * @return this
     */
    public CreateMaxStaminaTableMasterRequest withExperienceModelId(String experienceModelId) {
        setExperienceModelId(experienceModelId);
        return this;
    }

    /** ランク毎のスタミナの最大値テーブル */
    private List<Integer> values;

    /**
     * ランク毎のスタミナの最大値テーブルを取得
     *
     * @return スタミナの最大値テーブルマスターを新規作成
     */
    public List<Integer> getValues() {
        return values;
    }

    /**
     * ランク毎のスタミナの最大値テーブルを設定
     *
     * @param values スタミナの最大値テーブルマスターを新規作成
     */
    public void setValues(List<Integer> values) {
        this.values = values;
    }

    /**
     * ランク毎のスタミナの最大値テーブルを設定
     *
     * @param values スタミナの最大値テーブルマスターを新規作成
     * @return this
     */
    public CreateMaxStaminaTableMasterRequest withValues(List<Integer> values) {
        setValues(values);
        return this;
    }

}