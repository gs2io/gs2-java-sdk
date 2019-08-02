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
 * クエストモデルマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateQuestModelMasterRequest extends Gs2BasicRequest<CreateQuestModelMasterRequest> {

    /** カテゴリ名 */
    private String namespaceName;

    /**
     * カテゴリ名を取得
     *
     * @return クエストモデルマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストモデルマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストモデルマスターを新規作成
     * @return this
     */
    public CreateQuestModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** クエストグループモデル名 */
    private String questGroupName;

    /**
     * クエストグループモデル名を取得
     *
     * @return クエストモデルマスターを新規作成
     */
    public String getQuestGroupName() {
        return questGroupName;
    }

    /**
     * クエストグループモデル名を設定
     *
     * @param questGroupName クエストモデルマスターを新規作成
     */
    public void setQuestGroupName(String questGroupName) {
        this.questGroupName = questGroupName;
    }

    /**
     * クエストグループモデル名を設定
     *
     * @param questGroupName クエストモデルマスターを新規作成
     * @return this
     */
    public CreateQuestModelMasterRequest withQuestGroupName(String questGroupName) {
        setQuestGroupName(questGroupName);
        return this;
    }

    /** クエスト名 */
    private String name;

    /**
     * クエスト名を取得
     *
     * @return クエストモデルマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * クエスト名を設定
     *
     * @param name クエストモデルマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * クエスト名を設定
     *
     * @param name クエストモデルマスターを新規作成
     * @return this
     */
    public CreateQuestModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** クエストモデルの説明 */
    private String description;

    /**
     * クエストモデルの説明を取得
     *
     * @return クエストモデルマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * クエストモデルの説明を設定
     *
     * @param description クエストモデルマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * クエストモデルの説明を設定
     *
     * @param description クエストモデルマスターを新規作成
     * @return this
     */
    public CreateQuestModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** クエストのメタデータ */
    private String metadata;

    /**
     * クエストのメタデータを取得
     *
     * @return クエストモデルマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * クエストのメタデータを設定
     *
     * @param metadata クエストモデルマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * クエストのメタデータを設定
     *
     * @param metadata クエストモデルマスターを新規作成
     * @return this
     */
    public CreateQuestModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** クエストの内容 */
    private List<Contents> contents;

    /**
     * クエストの内容を取得
     *
     * @return クエストモデルマスターを新規作成
     */
    public List<Contents> getContents() {
        return contents;
    }

    /**
     * クエストの内容を設定
     *
     * @param contents クエストモデルマスターを新規作成
     */
    public void setContents(List<Contents> contents) {
        this.contents = contents;
    }

    /**
     * クエストの内容を設定
     *
     * @param contents クエストモデルマスターを新規作成
     * @return this
     */
    public CreateQuestModelMasterRequest withContents(List<Contents> contents) {
        setContents(contents);
        return this;
    }

    /** 挑戦可能な期間を指定するイベントマスター のGRN */
    private String challengePeriodEventId;

    /**
     * 挑戦可能な期間を指定するイベントマスター のGRNを取得
     *
     * @return クエストモデルマスターを新規作成
     */
    public String getChallengePeriodEventId() {
        return challengePeriodEventId;
    }

    /**
     * 挑戦可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId クエストモデルマスターを新規作成
     */
    public void setChallengePeriodEventId(String challengePeriodEventId) {
        this.challengePeriodEventId = challengePeriodEventId;
    }

    /**
     * 挑戦可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId クエストモデルマスターを新規作成
     * @return this
     */
    public CreateQuestModelMasterRequest withChallengePeriodEventId(String challengePeriodEventId) {
        setChallengePeriodEventId(challengePeriodEventId);
        return this;
    }

    /** クエストの参加料 */
    private List<ConsumeAction> consumeActions;

    /**
     * クエストの参加料を取得
     *
     * @return クエストモデルマスターを新規作成
     */
    public List<ConsumeAction> getConsumeActions() {
        return consumeActions;
    }

    /**
     * クエストの参加料を設定
     *
     * @param consumeActions クエストモデルマスターを新規作成
     */
    public void setConsumeActions(List<ConsumeAction> consumeActions) {
        this.consumeActions = consumeActions;
    }

    /**
     * クエストの参加料を設定
     *
     * @param consumeActions クエストモデルマスターを新規作成
     * @return this
     */
    public CreateQuestModelMasterRequest withConsumeActions(List<ConsumeAction> consumeActions) {
        setConsumeActions(consumeActions);
        return this;
    }

    /** クエスト失敗時の報酬 */
    private List<AcquireAction> failedAcquireActions;

    /**
     * クエスト失敗時の報酬を取得
     *
     * @return クエストモデルマスターを新規作成
     */
    public List<AcquireAction> getFailedAcquireActions() {
        return failedAcquireActions;
    }

    /**
     * クエスト失敗時の報酬を設定
     *
     * @param failedAcquireActions クエストモデルマスターを新規作成
     */
    public void setFailedAcquireActions(List<AcquireAction> failedAcquireActions) {
        this.failedAcquireActions = failedAcquireActions;
    }

    /**
     * クエスト失敗時の報酬を設定
     *
     * @param failedAcquireActions クエストモデルマスターを新規作成
     * @return this
     */
    public CreateQuestModelMasterRequest withFailedAcquireActions(List<AcquireAction> failedAcquireActions) {
        setFailedAcquireActions(failedAcquireActions);
        return this;
    }

    /** クエストに挑戦するためにクリアしておく必要のあるクエスト名 */
    private List<String> premiseQuestNames;

    /**
     * クエストに挑戦するためにクリアしておく必要のあるクエスト名を取得
     *
     * @return クエストモデルマスターを新規作成
     */
    public List<String> getPremiseQuestNames() {
        return premiseQuestNames;
    }

    /**
     * クエストに挑戦するためにクリアしておく必要のあるクエスト名を設定
     *
     * @param premiseQuestNames クエストモデルマスターを新規作成
     */
    public void setPremiseQuestNames(List<String> premiseQuestNames) {
        this.premiseQuestNames = premiseQuestNames;
    }

    /**
     * クエストに挑戦するためにクリアしておく必要のあるクエスト名を設定
     *
     * @param premiseQuestNames クエストモデルマスターを新規作成
     * @return this
     */
    public CreateQuestModelMasterRequest withPremiseQuestNames(List<String> premiseQuestNames) {
        setPremiseQuestNames(premiseQuestNames);
        return this;
    }

}