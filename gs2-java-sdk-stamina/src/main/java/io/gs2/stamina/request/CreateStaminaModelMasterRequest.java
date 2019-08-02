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
 * スタミナモデルマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateStaminaModelMasterRequest extends Gs2BasicRequest<CreateStaminaModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スタミナモデルマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナモデルマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタミナモデルマスターを新規作成
     * @return this
     */
    public CreateStaminaModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** スタミナの種類名 */
    private String name;

    /**
     * スタミナの種類名を取得
     *
     * @return スタミナモデルマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param name スタミナモデルマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * スタミナの種類名を設定
     *
     * @param name スタミナモデルマスターを新規作成
     * @return this
     */
    public CreateStaminaModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** スタミナモデルマスターの説明 */
    private String description;

    /**
     * スタミナモデルマスターの説明を取得
     *
     * @return スタミナモデルマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * スタミナモデルマスターの説明を設定
     *
     * @param description スタミナモデルマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * スタミナモデルマスターの説明を設定
     *
     * @param description スタミナモデルマスターを新規作成
     * @return this
     */
    public CreateStaminaModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** スタミナの種類のメタデータ */
    private String metadata;

    /**
     * スタミナの種類のメタデータを取得
     *
     * @return スタミナモデルマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * スタミナの種類のメタデータを設定
     *
     * @param metadata スタミナモデルマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * スタミナの種類のメタデータを設定
     *
     * @param metadata スタミナモデルマスターを新規作成
     * @return this
     */
    public CreateStaminaModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** スタミナを回復する速度(分) */
    private Integer recoverIntervalMinutes;

    /**
     * スタミナを回復する速度(分)を取得
     *
     * @return スタミナモデルマスターを新規作成
     */
    public Integer getRecoverIntervalMinutes() {
        return recoverIntervalMinutes;
    }

    /**
     * スタミナを回復する速度(分)を設定
     *
     * @param recoverIntervalMinutes スタミナモデルマスターを新規作成
     */
    public void setRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
        this.recoverIntervalMinutes = recoverIntervalMinutes;
    }

    /**
     * スタミナを回復する速度(分)を設定
     *
     * @param recoverIntervalMinutes スタミナモデルマスターを新規作成
     * @return this
     */
    public CreateStaminaModelMasterRequest withRecoverIntervalMinutes(Integer recoverIntervalMinutes) {
        setRecoverIntervalMinutes(recoverIntervalMinutes);
        return this;
    }

    /** 時間経過後に回復する量 */
    private Integer recoverValue;

    /**
     * 時間経過後に回復する量を取得
     *
     * @return スタミナモデルマスターを新規作成
     */
    public Integer getRecoverValue() {
        return recoverValue;
    }

    /**
     * 時間経過後に回復する量を設定
     *
     * @param recoverValue スタミナモデルマスターを新規作成
     */
    public void setRecoverValue(Integer recoverValue) {
        this.recoverValue = recoverValue;
    }

    /**
     * 時間経過後に回復する量を設定
     *
     * @param recoverValue スタミナモデルマスターを新規作成
     * @return this
     */
    public CreateStaminaModelMasterRequest withRecoverValue(Integer recoverValue) {
        setRecoverValue(recoverValue);
        return this;
    }

    /** スタミナの最大値の初期値 */
    private Integer initialCapacity;

    /**
     * スタミナの最大値の初期値を取得
     *
     * @return スタミナモデルマスターを新規作成
     */
    public Integer getInitialCapacity() {
        return initialCapacity;
    }

    /**
     * スタミナの最大値の初期値を設定
     *
     * @param initialCapacity スタミナモデルマスターを新規作成
     */
    public void setInitialCapacity(Integer initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    /**
     * スタミナの最大値の初期値を設定
     *
     * @param initialCapacity スタミナモデルマスターを新規作成
     * @return this
     */
    public CreateStaminaModelMasterRequest withInitialCapacity(Integer initialCapacity) {
        setInitialCapacity(initialCapacity);
        return this;
    }

    /** 最大値を超えて回復するか */
    private Boolean isOverflow;

    /**
     * 最大値を超えて回復するかを取得
     *
     * @return スタミナモデルマスターを新規作成
     */
    public Boolean getIsOverflow() {
        return isOverflow;
    }

    /**
     * 最大値を超えて回復するかを設定
     *
     * @param isOverflow スタミナモデルマスターを新規作成
     */
    public void setIsOverflow(Boolean isOverflow) {
        this.isOverflow = isOverflow;
    }

    /**
     * 最大値を超えて回復するかを設定
     *
     * @param isOverflow スタミナモデルマスターを新規作成
     * @return this
     */
    public CreateStaminaModelMasterRequest withIsOverflow(Boolean isOverflow) {
        setIsOverflow(isOverflow);
        return this;
    }

    /** 溢れた状況での最大値 */
    private Integer maxCapacity;

    /**
     * 溢れた状況での最大値を取得
     *
     * @return スタミナモデルマスターを新規作成
     */
    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * 溢れた状況での最大値を設定
     *
     * @param maxCapacity スタミナモデルマスターを新規作成
     */
    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * 溢れた状況での最大値を設定
     *
     * @param maxCapacity スタミナモデルマスターを新規作成
     * @return this
     */
    public CreateStaminaModelMasterRequest withMaxCapacity(Integer maxCapacity) {
        setMaxCapacity(maxCapacity);
        return this;
    }

    /** GS2-Experience のランクによって最大スタミナ値を決定するスタミナの最大値テーブルマスター のGRN */
    private String maxStaminaTableId;

    /**
     * GS2-Experience のランクによって最大スタミナ値を決定するスタミナの最大値テーブルマスター のGRNを取得
     *
     * @return スタミナモデルマスターを新規作成
     */
    public String getMaxStaminaTableId() {
        return maxStaminaTableId;
    }

    /**
     * GS2-Experience のランクによって最大スタミナ値を決定するスタミナの最大値テーブルマスター のGRNを設定
     *
     * @param maxStaminaTableId スタミナモデルマスターを新規作成
     */
    public void setMaxStaminaTableId(String maxStaminaTableId) {
        this.maxStaminaTableId = maxStaminaTableId;
    }

    /**
     * GS2-Experience のランクによって最大スタミナ値を決定するスタミナの最大値テーブルマスター のGRNを設定
     *
     * @param maxStaminaTableId スタミナモデルマスターを新規作成
     * @return this
     */
    public CreateStaminaModelMasterRequest withMaxStaminaTableId(String maxStaminaTableId) {
        setMaxStaminaTableId(maxStaminaTableId);
        return this;
    }

}