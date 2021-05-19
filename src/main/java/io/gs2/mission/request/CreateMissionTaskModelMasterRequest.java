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

package io.gs2.mission.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.mission.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ミッションタスクマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateMissionTaskModelMasterRequest extends Gs2BasicRequest<CreateMissionTaskModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ミッションタスクマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ミッションタスクマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ミッションタスクマスターを新規作成
     * @return this
     */
    public CreateMissionTaskModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ミッショングループ名 */
    private String missionGroupName;

    /**
     * ミッショングループ名を取得
     *
     * @return ミッションタスクマスターを新規作成
     */
    public String getMissionGroupName() {
        return missionGroupName;
    }

    /**
     * ミッショングループ名を設定
     *
     * @param missionGroupName ミッションタスクマスターを新規作成
     */
    public void setMissionGroupName(String missionGroupName) {
        this.missionGroupName = missionGroupName;
    }

    /**
     * ミッショングループ名を設定
     *
     * @param missionGroupName ミッションタスクマスターを新規作成
     * @return this
     */
    public CreateMissionTaskModelMasterRequest withMissionGroupName(String missionGroupName) {
        setMissionGroupName(missionGroupName);
        return this;
    }

    /** タスク名 */
    private String name;

    /**
     * タスク名を取得
     *
     * @return ミッションタスクマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * タスク名を設定
     *
     * @param name ミッションタスクマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * タスク名を設定
     *
     * @param name ミッションタスクマスターを新規作成
     * @return this
     */
    public CreateMissionTaskModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** メタデータ */
    private String metadata;

    /**
     * メタデータを取得
     *
     * @return ミッションタスクマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ミッションタスクマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ミッションタスクマスターを新規作成
     * @return this
     */
    public CreateMissionTaskModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** ミッションタスクの説明 */
    private String description;

    /**
     * ミッションタスクの説明を取得
     *
     * @return ミッションタスクマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * ミッションタスクの説明を設定
     *
     * @param description ミッションタスクマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ミッションタスクの説明を設定
     *
     * @param description ミッションタスクマスターを新規作成
     * @return this
     */
    public CreateMissionTaskModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** カウンター名 */
    private String counterName;

    /**
     * カウンター名を取得
     *
     * @return ミッションタスクマスターを新規作成
     */
    public String getCounterName() {
        return counterName;
    }

    /**
     * カウンター名を設定
     *
     * @param counterName ミッションタスクマスターを新規作成
     */
    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    /**
     * カウンター名を設定
     *
     * @param counterName ミッションタスクマスターを新規作成
     * @return this
     */
    public CreateMissionTaskModelMasterRequest withCounterName(String counterName) {
        setCounterName(counterName);
        return this;
    }

    /** 目標値 */
    private Long targetValue;

    /**
     * 目標値を取得
     *
     * @return ミッションタスクマスターを新規作成
     */
    public Long getTargetValue() {
        return targetValue;
    }

    /**
     * 目標値を設定
     *
     * @param targetValue ミッションタスクマスターを新規作成
     */
    public void setTargetValue(Long targetValue) {
        this.targetValue = targetValue;
    }

    /**
     * 目標値を設定
     *
     * @param targetValue ミッションタスクマスターを新規作成
     * @return this
     */
    public CreateMissionTaskModelMasterRequest withTargetValue(Long targetValue) {
        setTargetValue(targetValue);
        return this;
    }

    /** ミッション達成時の報酬 */
    private List<AcquireAction> completeAcquireActions;

    /**
     * ミッション達成時の報酬を取得
     *
     * @return ミッションタスクマスターを新規作成
     */
    public List<AcquireAction> getCompleteAcquireActions() {
        return completeAcquireActions;
    }

    /**
     * ミッション達成時の報酬を設定
     *
     * @param completeAcquireActions ミッションタスクマスターを新規作成
     */
    public void setCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
        this.completeAcquireActions = completeAcquireActions;
    }

    /**
     * ミッション達成時の報酬を設定
     *
     * @param completeAcquireActions ミッションタスクマスターを新規作成
     * @return this
     */
    public CreateMissionTaskModelMasterRequest withCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
        setCompleteAcquireActions(completeAcquireActions);
        return this;
    }

    /** 達成報酬の受け取り可能な期間を指定するイベントマスター のGRN */
    private String challengePeriodEventId;

    /**
     * 達成報酬の受け取り可能な期間を指定するイベントマスター のGRNを取得
     *
     * @return ミッションタスクマスターを新規作成
     */
    public String getChallengePeriodEventId() {
        return challengePeriodEventId;
    }

    /**
     * 達成報酬の受け取り可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId ミッションタスクマスターを新規作成
     */
    public void setChallengePeriodEventId(String challengePeriodEventId) {
        this.challengePeriodEventId = challengePeriodEventId;
    }

    /**
     * 達成報酬の受け取り可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId ミッションタスクマスターを新規作成
     * @return this
     */
    public CreateMissionTaskModelMasterRequest withChallengePeriodEventId(String challengePeriodEventId) {
        setChallengePeriodEventId(challengePeriodEventId);
        return this;
    }

    /** このタスクに挑戦するために達成しておく必要のあるタスクの名前 */
    private String premiseMissionTaskName;

    /**
     * このタスクに挑戦するために達成しておく必要のあるタスクの名前を取得
     *
     * @return ミッションタスクマスターを新規作成
     */
    public String getPremiseMissionTaskName() {
        return premiseMissionTaskName;
    }

    /**
     * このタスクに挑戦するために達成しておく必要のあるタスクの名前を設定
     *
     * @param premiseMissionTaskName ミッションタスクマスターを新規作成
     */
    public void setPremiseMissionTaskName(String premiseMissionTaskName) {
        this.premiseMissionTaskName = premiseMissionTaskName;
    }

    /**
     * このタスクに挑戦するために達成しておく必要のあるタスクの名前を設定
     *
     * @param premiseMissionTaskName ミッションタスクマスターを新規作成
     * @return this
     */
    public CreateMissionTaskModelMasterRequest withPremiseMissionTaskName(String premiseMissionTaskName) {
        setPremiseMissionTaskName(premiseMissionTaskName);
        return this;
    }

}