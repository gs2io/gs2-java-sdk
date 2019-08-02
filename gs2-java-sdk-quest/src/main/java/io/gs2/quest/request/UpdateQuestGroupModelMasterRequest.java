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

package io.gs2.quest.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.quest.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * クエストグループマスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateQuestGroupModelMasterRequest extends Gs2BasicRequest<UpdateQuestGroupModelMasterRequest> {

    /** カテゴリ名 */
    private String namespaceName;

    /**
     * カテゴリ名を取得
     *
     * @return クエストグループマスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストグループマスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストグループマスターを更新
     * @return this
     */
    public UpdateQuestGroupModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** クエストグループモデル名 */
    private String questGroupName;

    /**
     * クエストグループモデル名を取得
     *
     * @return クエストグループマスターを更新
     */
    public String getQuestGroupName() {
        return questGroupName;
    }

    /**
     * クエストグループモデル名を設定
     *
     * @param questGroupName クエストグループマスターを更新
     */
    public void setQuestGroupName(String questGroupName) {
        this.questGroupName = questGroupName;
    }

    /**
     * クエストグループモデル名を設定
     *
     * @param questGroupName クエストグループマスターを更新
     * @return this
     */
    public UpdateQuestGroupModelMasterRequest withQuestGroupName(String questGroupName) {
        setQuestGroupName(questGroupName);
        return this;
    }

    /** クエストグループマスターの説明 */
    private String description;

    /**
     * クエストグループマスターの説明を取得
     *
     * @return クエストグループマスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * クエストグループマスターの説明を設定
     *
     * @param description クエストグループマスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * クエストグループマスターの説明を設定
     *
     * @param description クエストグループマスターを更新
     * @return this
     */
    public UpdateQuestGroupModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** クエストグループのメタデータ */
    private String metadata;

    /**
     * クエストグループのメタデータを取得
     *
     * @return クエストグループマスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * クエストグループのメタデータを設定
     *
     * @param metadata クエストグループマスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * クエストグループのメタデータを設定
     *
     * @param metadata クエストグループマスターを更新
     * @return this
     */
    public UpdateQuestGroupModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 挑戦可能な期間を指定するイベントマスター のGRN */
    private String challengePeriodEventId;

    /**
     * 挑戦可能な期間を指定するイベントマスター のGRNを取得
     *
     * @return クエストグループマスターを更新
     */
    public String getChallengePeriodEventId() {
        return challengePeriodEventId;
    }

    /**
     * 挑戦可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId クエストグループマスターを更新
     */
    public void setChallengePeriodEventId(String challengePeriodEventId) {
        this.challengePeriodEventId = challengePeriodEventId;
    }

    /**
     * 挑戦可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId クエストグループマスターを更新
     * @return this
     */
    public UpdateQuestGroupModelMasterRequest withChallengePeriodEventId(String challengePeriodEventId) {
        setChallengePeriodEventId(challengePeriodEventId);
        return this;
    }

}