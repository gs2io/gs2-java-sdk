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
 * ネームスペースを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {

    /** ネームスペース名 */
    private String name;

    /**
     * ネームスペース名を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withName(String name) {
        setName(name);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** ランクキャップ取得時 に実行されるスクリプト のGRN */
    private String experienceCapScriptId;

    /**
     * ランクキャップ取得時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getExperienceCapScriptId() {
        return experienceCapScriptId;
    }

    /**
     * ランクキャップ取得時 に実行されるスクリプト のGRNを設定
     *
     * @param experienceCapScriptId ネームスペースを新規作成
     */
    public void setExperienceCapScriptId(String experienceCapScriptId) {
        this.experienceCapScriptId = experienceCapScriptId;
    }

    /**
     * ランクキャップ取得時 に実行されるスクリプト のGRNを設定
     *
     * @param experienceCapScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withExperienceCapScriptId(String experienceCapScriptId) {
        setExperienceCapScriptId(experienceCapScriptId);
        return this;
    }

    /** 経験値変化時 に実行されるスクリプト のGRN */
    private String changeExperienceTriggerScriptId;

    /**
     * 経験値変化時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getChangeExperienceTriggerScriptId() {
        return changeExperienceTriggerScriptId;
    }

    /**
     * 経験値変化時 に実行されるスクリプト のGRNを設定
     *
     * @param changeExperienceTriggerScriptId ネームスペースを新規作成
     */
    public void setChangeExperienceTriggerScriptId(String changeExperienceTriggerScriptId) {
        this.changeExperienceTriggerScriptId = changeExperienceTriggerScriptId;
    }

    /**
     * 経験値変化時 に実行されるスクリプト のGRNを設定
     *
     * @param changeExperienceTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withChangeExperienceTriggerScriptId(String changeExperienceTriggerScriptId) {
        setChangeExperienceTriggerScriptId(changeExperienceTriggerScriptId);
        return this;
    }

    /** 経験値変化完了時 に実行されるスクリプト のGRN */
    private String changeExperienceDoneTriggerScriptId;

    /**
     * 経験値変化完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getChangeExperienceDoneTriggerScriptId() {
        return changeExperienceDoneTriggerScriptId;
    }

    /**
     * 経験値変化完了時 に実行されるスクリプト のGRNを設定
     *
     * @param changeExperienceDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setChangeExperienceDoneTriggerScriptId(String changeExperienceDoneTriggerScriptId) {
        this.changeExperienceDoneTriggerScriptId = changeExperienceDoneTriggerScriptId;
    }

    /**
     * 経験値変化完了時 に実行されるスクリプト のGRNを設定
     *
     * @param changeExperienceDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withChangeExperienceDoneTriggerScriptId(String changeExperienceDoneTriggerScriptId) {
        setChangeExperienceDoneTriggerScriptId(changeExperienceDoneTriggerScriptId);
        return this;
    }

    /** 経験値変化完了時 にジョブが登録されるネームスペース のGRN */
    private String changeExperienceDoneTriggerNamespaceId;

    /**
     * 経験値変化完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getChangeExperienceDoneTriggerNamespaceId() {
        return changeExperienceDoneTriggerNamespaceId;
    }

    /**
     * 経験値変化完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param changeExperienceDoneTriggerNamespaceId ネームスペースを新規作成
     */
    public void setChangeExperienceDoneTriggerNamespaceId(String changeExperienceDoneTriggerNamespaceId) {
        this.changeExperienceDoneTriggerNamespaceId = changeExperienceDoneTriggerNamespaceId;
    }

    /**
     * 経験値変化完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param changeExperienceDoneTriggerNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withChangeExperienceDoneTriggerNamespaceId(String changeExperienceDoneTriggerNamespaceId) {
        setChangeExperienceDoneTriggerNamespaceId(changeExperienceDoneTriggerNamespaceId);
        return this;
    }

    /** ランク変化時 に実行されるスクリプト のGRN */
    private String changeRankTriggerScriptId;

    /**
     * ランク変化時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getChangeRankTriggerScriptId() {
        return changeRankTriggerScriptId;
    }

    /**
     * ランク変化時 に実行されるスクリプト のGRNを設定
     *
     * @param changeRankTriggerScriptId ネームスペースを新規作成
     */
    public void setChangeRankTriggerScriptId(String changeRankTriggerScriptId) {
        this.changeRankTriggerScriptId = changeRankTriggerScriptId;
    }

    /**
     * ランク変化時 に実行されるスクリプト のGRNを設定
     *
     * @param changeRankTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withChangeRankTriggerScriptId(String changeRankTriggerScriptId) {
        setChangeRankTriggerScriptId(changeRankTriggerScriptId);
        return this;
    }

    /** ランク変化時 にジョブが登録されるネームスペース のGRN */
    private String changeRankTriggerNamespaceId;

    /**
     * ランク変化時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getChangeRankTriggerNamespaceId() {
        return changeRankTriggerNamespaceId;
    }

    /**
     * ランク変化時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param changeRankTriggerNamespaceId ネームスペースを新規作成
     */
    public void setChangeRankTriggerNamespaceId(String changeRankTriggerNamespaceId) {
        this.changeRankTriggerNamespaceId = changeRankTriggerNamespaceId;
    }

    /**
     * ランク変化時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param changeRankTriggerNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withChangeRankTriggerNamespaceId(String changeRankTriggerNamespaceId) {
        setChangeRankTriggerNamespaceId(changeRankTriggerNamespaceId);
        return this;
    }

    /** ランクキャップ変化時 に実行されるスクリプト のGRN */
    private String changeRankCapTriggerScriptId;

    /**
     * ランクキャップ変化時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getChangeRankCapTriggerScriptId() {
        return changeRankCapTriggerScriptId;
    }

    /**
     * ランクキャップ変化時 に実行されるスクリプト のGRNを設定
     *
     * @param changeRankCapTriggerScriptId ネームスペースを新規作成
     */
    public void setChangeRankCapTriggerScriptId(String changeRankCapTriggerScriptId) {
        this.changeRankCapTriggerScriptId = changeRankCapTriggerScriptId;
    }

    /**
     * ランクキャップ変化時 に実行されるスクリプト のGRNを設定
     *
     * @param changeRankCapTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withChangeRankCapTriggerScriptId(String changeRankCapTriggerScriptId) {
        setChangeRankCapTriggerScriptId(changeRankCapTriggerScriptId);
        return this;
    }

    /** ランクキャップ変化完了時 に実行されるスクリプト のGRN */
    private String changeRankCapDoneTriggerScriptId;

    /**
     * ランクキャップ変化完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getChangeRankCapDoneTriggerScriptId() {
        return changeRankCapDoneTriggerScriptId;
    }

    /**
     * ランクキャップ変化完了時 に実行されるスクリプト のGRNを設定
     *
     * @param changeRankCapDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setChangeRankCapDoneTriggerScriptId(String changeRankCapDoneTriggerScriptId) {
        this.changeRankCapDoneTriggerScriptId = changeRankCapDoneTriggerScriptId;
    }

    /**
     * ランクキャップ変化完了時 に実行されるスクリプト のGRNを設定
     *
     * @param changeRankCapDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withChangeRankCapDoneTriggerScriptId(String changeRankCapDoneTriggerScriptId) {
        setChangeRankCapDoneTriggerScriptId(changeRankCapDoneTriggerScriptId);
        return this;
    }

    /** ランクキャップ変化完了時 にジョブが登録されるネームスペース のGRN */
    private String changeRankCapDoneTriggerNamespaceId;

    /**
     * ランクキャップ変化完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getChangeRankCapDoneTriggerNamespaceId() {
        return changeRankCapDoneTriggerNamespaceId;
    }

    /**
     * ランクキャップ変化完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param changeRankCapDoneTriggerNamespaceId ネームスペースを新規作成
     */
    public void setChangeRankCapDoneTriggerNamespaceId(String changeRankCapDoneTriggerNamespaceId) {
        this.changeRankCapDoneTriggerNamespaceId = changeRankCapDoneTriggerNamespaceId;
    }

    /**
     * ランクキャップ変化完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param changeRankCapDoneTriggerNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withChangeRankCapDoneTriggerNamespaceId(String changeRankCapDoneTriggerNamespaceId) {
        setChangeRankCapDoneTriggerNamespaceId(changeRankCapDoneTriggerNamespaceId);
        return this;
    }

}