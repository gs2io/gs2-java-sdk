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
 * スタミナモデルマスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateStaminaModelMasterRequest extends Gs2BasicRequest<UpdateStaminaModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スタミナモデルマスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナモデルマスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナモデルマスターを更新
     * @return this
     */
    public UpdateStaminaModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** スタミナの種類名 */
    private String staminaName;

    /**
     * スタミナの種類名を取得
     *
     * @return スタミナモデルマスターを更新
     */
    public String getStaminaName() {
        return staminaName;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param staminaName スタミナモデルマスターを更新
     */
    public void setStaminaName(String staminaName) {
        this.staminaName = staminaName;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param staminaName スタミナモデルマスターを更新
     * @return this
     */
    public UpdateStaminaModelMasterRequest withStaminaName(String staminaName) {
        setStaminaName(staminaName);
        return this;
    }

    /** スタミナモデルマスターの説明 */
    private String description;

    /**
     * スタミナモデルマスターの説明を取得
     *
     * @return スタミナモデルマスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * スタミナモデルマスターの説明を設定
     *
     * @param description スタミナモデルマスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * スタミナモデルマスターの説明を設定
     *
     * @param description スタミナモデルマスターを更新
     * @return this
     */
    public UpdateStaminaModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** スタミナの種類のメタデータ */
    private String metadata;

    /**
     * スタミナの種類のメタデータを取得
     *
     * @return スタミナモデルマスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * スタミナの種類のメタデータを設定
     *
     * @param metadata スタミナモデルマスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * スタミナの種類のメタデータを設定
     *
     * @param metadata スタミナモデルマスターを更新
     * @return this
     */
    public UpdateStaminaModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** スタミナを回復する速度(分) */
    private Integer recoverIntervalMinutes;

    /**
     * スタミナを回復する速度(分)を取得
     *
     * @return スタミナモデルマスターを更新
     */
    public Integer getRecoverIntervalMinutes() {
        return recoverIntervalMinutes;
    }

    /**
     * スタミナを回復する速度(分)を設定
     *
     * @param recoverIntervalMinutes スタミナモデルマスターを更新
     */
    public void setRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
        this.recoverIntervalMinutes = recoverIntervalMinutes;
    }

    /**
     * スタミナを回復する速度(分)を設定
     *
     * @param recoverIntervalMinutes スタミナモデルマスターを更新
     * @return this
     */
    public UpdateStaminaModelMasterRequest withRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
        setRecoverIntervalMinutes(recoverIntervalMinutes);
        return this;
    }

    /** 時間経過後に回復する量 */
    private Integer recoverValue;

    /**
     * 時間経過後に回復する量を取得
     *
     * @return スタミナモデルマスターを更新
     */
    public Integer getRecoverValue() {
        return recoverValue;
    }

    /**
     * 時間経過後に回復する量を設定
     *
     * @param recoverValue スタミナモデルマスターを更新
     */
    public void setRecoverValue(Integer recoverValue) {
        this.recoverValue = recoverValue;
    }

    /**
     * 時間経過後に回復する量を設定
     *
     * @param recoverValue スタミナモデルマスターを更新
     * @return this
     */
    public UpdateStaminaModelMasterRequest withRecoverValue(Integer recoverValue) {
        setRecoverValue(recoverValue);
        return this;
    }

    /** スタミナの最大値の初期値 */
    private Integer initialCapacity;

    /**
     * スタミナの最大値の初期値を取得
     *
     * @return スタミナモデルマスターを更新
     */
    public Integer getInitialCapacity() {
        return initialCapacity;
    }

    /**
     * スタミナの最大値の初期値を設定
     *
     * @param initialCapacity スタミナモデルマスターを更新
     */
    public void setInitialCapacity(Integer initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    /**
     * スタミナの最大値の初期値を設定
     *
     * @param initialCapacity スタミナモデルマスターを更新
     * @return this
     */
    public UpdateStaminaModelMasterRequest withInitialCapacity(Integer initialCapacity) {
        setInitialCapacity(initialCapacity);
        return this;
    }

    /** 最大値を超えて回復するか */
    private Boolean isOverflow;

    /**
     * 最大値を超えて回復するかを取得
     *
     * @return スタミナモデルマスターを更新
     */
    public Boolean getIsOverflow() {
        return isOverflow;
    }

    /**
     * 最大値を超えて回復するかを設定
     *
     * @param isOverflow スタミナモデルマスターを更新
     */
    public void setIsOverflow(Boolean isOverflow) {
        this.isOverflow = isOverflow;
    }

    /**
     * 最大値を超えて回復するかを設定
     *
     * @param isOverflow スタミナモデルマスターを更新
     * @return this
     */
    public UpdateStaminaModelMasterRequest withIsOverflow(Boolean isOverflow) {
        setIsOverflow(isOverflow);
        return this;
    }

    /** 溢れた状況での最大値 */
    private Integer maxCapacity;

    /**
     * 溢れた状況での最大値を取得
     *
     * @return スタミナモデルマスターを更新
     */
    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * 溢れた状況での最大値を設定
     *
     * @param maxCapacity スタミナモデルマスターを更新
     */
    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * 溢れた状況での最大値を設定
     *
     * @param maxCapacity スタミナモデルマスターを更新
     * @return this
     */
    public UpdateStaminaModelMasterRequest withMaxCapacity(Integer maxCapacity) {
        setMaxCapacity(maxCapacity);
        return this;
    }

    /** GS2-Experience のランクによって最大スタミナ値を決定するスタミナ最大値テーブル名 */
    private String maxStaminaTableName;

    /**
     * GS2-Experience のランクによって最大スタミナ値を決定するスタミナ最大値テーブル名を取得
     *
     * @return スタミナモデルマスターを更新
     */
    public String getMaxStaminaTableName() {
        return maxStaminaTableName;
    }

    /**
     * GS2-Experience のランクによって最大スタミナ値を決定するスタミナ最大値テーブル名を設定
     *
     * @param maxStaminaTableName スタミナモデルマスターを更新
     */
    public void setMaxStaminaTableName(String maxStaminaTableName) {
        this.maxStaminaTableName = maxStaminaTableName;
    }

    /**
     * GS2-Experience のランクによって最大スタミナ値を決定するスタミナ最大値テーブル名を設定
     *
     * @param maxStaminaTableName スタミナモデルマスターを更新
     * @return this
     */
    public UpdateStaminaModelMasterRequest withMaxStaminaTableName(String maxStaminaTableName) {
        setMaxStaminaTableName(maxStaminaTableName);
        return this;
    }

    /** GS2-Experience のランクによってスタミナの回復間隔を決定する回復間隔テーブル名 */
    private String recoverIntervalTableName;

    /**
     * GS2-Experience のランクによってスタミナの回復間隔を決定する回復間隔テーブル名を取得
     *
     * @return スタミナモデルマスターを更新
     */
    public String getRecoverIntervalTableName() {
        return recoverIntervalTableName;
    }

    /**
     * GS2-Experience のランクによってスタミナの回復間隔を決定する回復間隔テーブル名を設定
     *
     * @param recoverIntervalTableName スタミナモデルマスターを更新
     */
    public void setRecoverIntervalTableName(String recoverIntervalTableName) {
        this.recoverIntervalTableName = recoverIntervalTableName;
    }

    /**
     * GS2-Experience のランクによってスタミナの回復間隔を決定する回復間隔テーブル名を設定
     *
     * @param recoverIntervalTableName スタミナモデルマスターを更新
     * @return this
     */
    public UpdateStaminaModelMasterRequest withRecoverIntervalTableName(String recoverIntervalTableName) {
        setRecoverIntervalTableName(recoverIntervalTableName);
        return this;
    }

    /** GS2-Experience のランクによってスタミナの回復量を決定する回復量テーブル名 */
    private String recoverValueTableName;

    /**
     * GS2-Experience のランクによってスタミナの回復量を決定する回復量テーブル名を取得
     *
     * @return スタミナモデルマスターを更新
     */
    public String getRecoverValueTableName() {
        return recoverValueTableName;
    }

    /**
     * GS2-Experience のランクによってスタミナの回復量を決定する回復量テーブル名を設定
     *
     * @param recoverValueTableName スタミナモデルマスターを更新
     */
    public void setRecoverValueTableName(String recoverValueTableName) {
        this.recoverValueTableName = recoverValueTableName;
    }

    /**
     * GS2-Experience のランクによってスタミナの回復量を決定する回復量テーブル名を設定
     *
     * @param recoverValueTableName スタミナモデルマスターを更新
     * @return this
     */
    public UpdateStaminaModelMasterRequest withRecoverValueTableName(String recoverValueTableName) {
        setRecoverValueTableName(recoverValueTableName);
        return this;
    }

}