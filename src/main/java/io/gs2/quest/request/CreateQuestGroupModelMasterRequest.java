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
import io.gs2.core.control.Gs2BasicRequest;

/**
 * クエストグループマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateQuestGroupModelMasterRequest extends Gs2BasicRequest<CreateQuestGroupModelMasterRequest> {

    /** カテゴリ名 */
    private String namespaceName;

    /**
     * カテゴリ名を取得
     *
     * @return クエストグループマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストグループマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストグループマスターを新規作成
     * @return this
     */
    public CreateQuestGroupModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** クエストグループモデル名 */
    private String name;

    /**
     * クエストグループモデル名を取得
     *
     * @return クエストグループマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * クエストグループモデル名を設定
     *
     * @param name クエストグループマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * クエストグループモデル名を設定
     *
     * @param name クエストグループマスターを新規作成
     * @return this
     */
    public CreateQuestGroupModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** クエストグループマスターの説明 */
    private String description;

    /**
     * クエストグループマスターの説明を取得
     *
     * @return クエストグループマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * クエストグループマスターの説明を設定
     *
     * @param description クエストグループマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * クエストグループマスターの説明を設定
     *
     * @param description クエストグループマスターを新規作成
     * @return this
     */
    public CreateQuestGroupModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** クエストグループのメタデータ */
    private String metadata;

    /**
     * クエストグループのメタデータを取得
     *
     * @return クエストグループマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * クエストグループのメタデータを設定
     *
     * @param metadata クエストグループマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * クエストグループのメタデータを設定
     *
     * @param metadata クエストグループマスターを新規作成
     * @return this
     */
    public CreateQuestGroupModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 挑戦可能な期間を指定するイベントマスター のGRN */
    private String challengePeriodEventId;

    /**
     * 挑戦可能な期間を指定するイベントマスター のGRNを取得
     *
     * @return クエストグループマスターを新規作成
     */
    public String getChallengePeriodEventId() {
        return challengePeriodEventId;
    }

    /**
     * 挑戦可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId クエストグループマスターを新規作成
     */
    public void setChallengePeriodEventId(String challengePeriodEventId) {
        this.challengePeriodEventId = challengePeriodEventId;
    }

    /**
     * 挑戦可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId クエストグループマスターを新規作成
     * @return this
     */
    public CreateQuestGroupModelMasterRequest withChallengePeriodEventId(String challengePeriodEventId) {
        setChallengePeriodEventId(challengePeriodEventId);
        return this;
    }

}