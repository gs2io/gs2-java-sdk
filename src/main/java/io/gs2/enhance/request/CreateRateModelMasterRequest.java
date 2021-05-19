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

package io.gs2.enhance.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.enhance.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 強化レートマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateRateModelMasterRequest extends Gs2BasicRequest<CreateRateModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 強化レートマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 強化レートマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 強化レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 強化レート名 */
    private String name;

    /**
     * 強化レート名を取得
     *
     * @return 強化レートマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * 強化レート名を設定
     *
     * @param name 強化レートマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 強化レート名を設定
     *
     * @param name 強化レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 強化レートマスターの説明 */
    private String description;

    /**
     * 強化レートマスターの説明を取得
     *
     * @return 強化レートマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * 強化レートマスターの説明を設定
     *
     * @param description 強化レートマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 強化レートマスターの説明を設定
     *
     * @param description 強化レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 強化レートのメタデータ */
    private String metadata;

    /**
     * 強化レートのメタデータを取得
     *
     * @return 強化レートマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 強化レートのメタデータを設定
     *
     * @param metadata 強化レートマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 強化レートのメタデータを設定
     *
     * @param metadata 強化レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 強化対象に使用できるインベントリモデル のGRN */
    private String targetInventoryModelId;

    /**
     * 強化対象に使用できるインベントリモデル のGRNを取得
     *
     * @return 強化レートマスターを新規作成
     */
    public String getTargetInventoryModelId() {
        return targetInventoryModelId;
    }

    /**
     * 強化対象に使用できるインベントリモデル のGRNを設定
     *
     * @param targetInventoryModelId 強化レートマスターを新規作成
     */
    public void setTargetInventoryModelId(String targetInventoryModelId) {
        this.targetInventoryModelId = targetInventoryModelId;
    }

    /**
     * 強化対象に使用できるインベントリモデル のGRNを設定
     *
     * @param targetInventoryModelId 強化レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withTargetInventoryModelId(String targetInventoryModelId) {
        setTargetInventoryModelId(targetInventoryModelId);
        return this;
    }

    /** GS2-Experience で入手した経験値を格納する プロパティID に付与するサフィックス */
    private String acquireExperienceSuffix;

    /**
     * GS2-Experience で入手した経験値を格納する プロパティID に付与するサフィックスを取得
     *
     * @return 強化レートマスターを新規作成
     */
    public String getAcquireExperienceSuffix() {
        return acquireExperienceSuffix;
    }

    /**
     * GS2-Experience で入手した経験値を格納する プロパティID に付与するサフィックスを設定
     *
     * @param acquireExperienceSuffix 強化レートマスターを新規作成
     */
    public void setAcquireExperienceSuffix(String acquireExperienceSuffix) {
        this.acquireExperienceSuffix = acquireExperienceSuffix;
    }

    /**
     * GS2-Experience で入手した経験値を格納する プロパティID に付与するサフィックスを設定
     *
     * @param acquireExperienceSuffix 強化レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withAcquireExperienceSuffix(String acquireExperienceSuffix) {
        setAcquireExperienceSuffix(acquireExperienceSuffix);
        return this;
    }

    /** 強化素材に使用できるインベントリモデル のGRN */
    private String materialInventoryModelId;

    /**
     * 強化素材に使用できるインベントリモデル のGRNを取得
     *
     * @return 強化レートマスターを新規作成
     */
    public String getMaterialInventoryModelId() {
        return materialInventoryModelId;
    }

    /**
     * 強化素材に使用できるインベントリモデル のGRNを設定
     *
     * @param materialInventoryModelId 強化レートマスターを新規作成
     */
    public void setMaterialInventoryModelId(String materialInventoryModelId) {
        this.materialInventoryModelId = materialInventoryModelId;
    }

    /**
     * 強化素材に使用できるインベントリモデル のGRNを設定
     *
     * @param materialInventoryModelId 強化レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withMaterialInventoryModelId(String materialInventoryModelId) {
        setMaterialInventoryModelId(materialInventoryModelId);
        return this;
    }

    /** 入手経験値を格納しているメタデータのJSON階層 */
    private List<String> acquireExperienceHierarchy;

    /**
     * 入手経験値を格納しているメタデータのJSON階層を取得
     *
     * @return 強化レートマスターを新規作成
     */
    public List<String> getAcquireExperienceHierarchy() {
        return acquireExperienceHierarchy;
    }

    /**
     * 入手経験値を格納しているメタデータのJSON階層を設定
     *
     * @param acquireExperienceHierarchy 強化レートマスターを新規作成
     */
    public void setAcquireExperienceHierarchy(List<String> acquireExperienceHierarchy) {
        this.acquireExperienceHierarchy = acquireExperienceHierarchy;
    }

    /**
     * 入手経験値を格納しているメタデータのJSON階層を設定
     *
     * @param acquireExperienceHierarchy 強化レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withAcquireExperienceHierarchy(List<String> acquireExperienceHierarchy) {
        setAcquireExperienceHierarchy(acquireExperienceHierarchy);
        return this;
    }

    /** 獲得できる経験値の種類マスター のGRN */
    private String experienceModelId;

    /**
     * 獲得できる経験値の種類マスター のGRNを取得
     *
     * @return 強化レートマスターを新規作成
     */
    public String getExperienceModelId() {
        return experienceModelId;
    }

    /**
     * 獲得できる経験値の種類マスター のGRNを設定
     *
     * @param experienceModelId 強化レートマスターを新規作成
     */
    public void setExperienceModelId(String experienceModelId) {
        this.experienceModelId = experienceModelId;
    }

    /**
     * 獲得できる経験値の種類マスター のGRNを設定
     *
     * @param experienceModelId 強化レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withExperienceModelId(String experienceModelId) {
        setExperienceModelId(experienceModelId);
        return this;
    }

    /** 経験値獲得量ボーナス */
    private List<BonusRate> bonusRates;

    /**
     * 経験値獲得量ボーナスを取得
     *
     * @return 強化レートマスターを新規作成
     */
    public List<BonusRate> getBonusRates() {
        return bonusRates;
    }

    /**
     * 経験値獲得量ボーナスを設定
     *
     * @param bonusRates 強化レートマスターを新規作成
     */
    public void setBonusRates(List<BonusRate> bonusRates) {
        this.bonusRates = bonusRates;
    }

    /**
     * 経験値獲得量ボーナスを設定
     *
     * @param bonusRates 強化レートマスターを新規作成
     * @return this
     */
    public CreateRateModelMasterRequest withBonusRates(List<BonusRate> bonusRates) {
        setBonusRates(bonusRates);
        return this;
    }

}