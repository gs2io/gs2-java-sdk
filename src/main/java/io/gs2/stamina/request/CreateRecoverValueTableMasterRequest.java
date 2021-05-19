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
 * スタミナ回復量テーブルマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateRecoverValueTableMasterRequest extends Gs2BasicRequest<CreateRecoverValueTableMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スタミナ回復量テーブルマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナ回復量テーブルマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナ回復量テーブルマスターを新規作成
     * @return this
     */
    public CreateRecoverValueTableMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** スタミナ回復量テーブル名 */
    private String name;

    /**
     * スタミナ回復量テーブル名を取得
     *
     * @return スタミナ回復量テーブルマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * スタミナ回復量テーブル名を設定
     *
     * @param name スタミナ回復量テーブルマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * スタミナ回復量テーブル名を設定
     *
     * @param name スタミナ回復量テーブルマスターを新規作成
     * @return this
     */
    public CreateRecoverValueTableMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** スタミナ回復量テーブルマスターの説明 */
    private String description;

    /**
     * スタミナ回復量テーブルマスターの説明を取得
     *
     * @return スタミナ回復量テーブルマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * スタミナ回復量テーブルマスターの説明を設定
     *
     * @param description スタミナ回復量テーブルマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * スタミナ回復量テーブルマスターの説明を設定
     *
     * @param description スタミナ回復量テーブルマスターを新規作成
     * @return this
     */
    public CreateRecoverValueTableMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** スタミナ回復量テーブルのメタデータ */
    private String metadata;

    /**
     * スタミナ回復量テーブルのメタデータを取得
     *
     * @return スタミナ回復量テーブルマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * スタミナ回復量テーブルのメタデータを設定
     *
     * @param metadata スタミナ回復量テーブルマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * スタミナ回復量テーブルのメタデータを設定
     *
     * @param metadata スタミナ回復量テーブルマスターを新規作成
     * @return this
     */
    public CreateRecoverValueTableMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 経験値の種類マスター のGRN */
    private String experienceModelId;

    /**
     * 経験値の種類マスター のGRNを取得
     *
     * @return スタミナ回復量テーブルマスターを新規作成
     */
    public String getExperienceModelId() {
        return experienceModelId;
    }

    /**
     * 経験値の種類マスター のGRNを設定
     *
     * @param experienceModelId スタミナ回復量テーブルマスターを新規作成
     */
    public void setExperienceModelId(String experienceModelId) {
        this.experienceModelId = experienceModelId;
    }

    /**
     * 経験値の種類マスター のGRNを設定
     *
     * @param experienceModelId スタミナ回復量テーブルマスターを新規作成
     * @return this
     */
    public CreateRecoverValueTableMasterRequest withExperienceModelId(String experienceModelId) {
        setExperienceModelId(experienceModelId);
        return this;
    }

    /** ランク毎のスタミナ回復量テーブル */
    private List<Integer> values;

    /**
     * ランク毎のスタミナ回復量テーブルを取得
     *
     * @return スタミナ回復量テーブルマスターを新規作成
     */
    public List<Integer> getValues() {
        return values;
    }

    /**
     * ランク毎のスタミナ回復量テーブルを設定
     *
     * @param values スタミナ回復量テーブルマスターを新規作成
     */
    public void setValues(List<Integer> values) {
        this.values = values;
    }

    /**
     * ランク毎のスタミナ回復量テーブルを設定
     *
     * @param values スタミナ回復量テーブルマスターを新規作成
     * @return this
     */
    public CreateRecoverValueTableMasterRequest withValues(List<Integer> values) {
        setValues(values);
        return this;
    }

}