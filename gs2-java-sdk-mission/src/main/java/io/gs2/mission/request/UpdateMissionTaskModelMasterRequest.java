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
import io.gs2.control.Gs2BasicRequest;

/**
 * ミッションタスクマスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateMissionTaskModelMasterRequest extends Gs2BasicRequest<UpdateMissionTaskModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ミッションタスクマスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ミッションタスクマスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ミッションタスクマスターを更新
     * @return this
     */
    public UpdateMissionTaskModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ミッショングループ名 */
    private String missionGroupName;

    /**
     * ミッショングループ名を取得
     *
     * @return ミッションタスクマスターを更新
     */
    public String getMissionGroupName() {
        return missionGroupName;
    }

    /**
     * ミッショングループ名を設定
     *
     * @param missionGroupName ミッションタスクマスターを更新
     */
    public void setMissionGroupName(String missionGroupName) {
        this.missionGroupName = missionGroupName;
    }

    /**
     * ミッショングループ名を設定
     *
     * @param missionGroupName ミッションタスクマスターを更新
     * @return this
     */
    public UpdateMissionTaskModelMasterRequest withMissionGroupName(String missionGroupName) {
        setMissionGroupName(missionGroupName);
        return this;
    }

    /** タスク名 */
    private String missionTaskName;

    /**
     * タスク名を取得
     *
     * @return ミッションタスクマスターを更新
     */
    public String getMissionTaskName() {
        return missionTaskName;
    }

    /**
     * タスク名を設定
     *
     * @param missionTaskName ミッションタスクマスターを更新
     */
    public void setMissionTaskName(String missionTaskName) {
        this.missionTaskName = missionTaskName;
    }

    /**
     * タスク名を設定
     *
     * @param missionTaskName ミッションタスクマスターを更新
     * @return this
     */
    public UpdateMissionTaskModelMasterRequest withMissionTaskName(String missionTaskName) {
        setMissionTaskName(missionTaskName);
        return this;
    }

    /** メタデータ */
    private String metadata;

    /**
     * メタデータを取得
     *
     * @return ミッションタスクマスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ミッションタスクマスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ミッションタスクマスターを更新
     * @return this
     */
    public UpdateMissionTaskModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** ミッションタスクの説明 */
    private String description;

    /**
     * ミッションタスクの説明を取得
     *
     * @return ミッションタスクマスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * ミッションタスクの説明を設定
     *
     * @param description ミッションタスクマスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ミッションタスクの説明を設定
     *
     * @param description ミッションタスクマスターを更新
     * @return this
     */
    public UpdateMissionTaskModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** カウンター名 */
    private String counterName;

    /**
     * カウンター名を取得
     *
     * @return ミッションタスクマスターを更新
     */
    public String getCounterName() {
        return counterName;
    }

    /**
     * カウンター名を設定
     *
     * @param counterName ミッションタスクマスターを更新
     */
    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    /**
     * カウンター名を設定
     *
     * @param counterName ミッションタスクマスターを更新
     * @return this
     */
    public UpdateMissionTaskModelMasterRequest withCounterName(String counterName) {
        setCounterName(counterName);
        return this;
    }

    /** リセットタイミング */
    private String resetType;

    /**
     * リセットタイミングを取得
     *
     * @return ミッションタスクマスターを更新
     */
    public String getResetType() {
        return resetType;
    }

    /**
     * リセットタイミングを設定
     *
     * @param resetType ミッションタスクマスターを更新
     */
    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    /**
     * リセットタイミングを設定
     *
     * @param resetType ミッションタスクマスターを更新
     * @return this
     */
    public UpdateMissionTaskModelMasterRequest withResetType(String resetType) {
        setResetType(resetType);
        return this;
    }

    /** 目標値 */
    private Long targetValue;

    /**
     * 目標値を取得
     *
     * @return ミッションタスクマスターを更新
     */
    public Long getTargetValue() {
        return targetValue;
    }

    /**
     * 目標値を設定
     *
     * @param targetValue ミッションタスクマスターを更新
     */
    public void setTargetValue(Long targetValue) {
        this.targetValue = targetValue;
    }

    /**
     * 目標値を設定
     *
     * @param targetValue ミッションタスクマスターを更新
     * @return this
     */
    public UpdateMissionTaskModelMasterRequest withTargetValue(Long targetValue) {
        setTargetValue(targetValue);
        return this;
    }

    /** ミッション達成時の報酬 */
    private List<AcquireAction> completeAcquireActions;

    /**
     * ミッション達成時の報酬を取得
     *
     * @return ミッションタスクマスターを更新
     */
    public List<AcquireAction> getCompleteAcquireActions() {
        return completeAcquireActions;
    }

    /**
     * ミッション達成時の報酬を設定
     *
     * @param completeAcquireActions ミッションタスクマスターを更新
     */
    public void setCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
        this.completeAcquireActions = completeAcquireActions;
    }

    /**
     * ミッション達成時の報酬を設定
     *
     * @param completeAcquireActions ミッションタスクマスターを更新
     * @return this
     */
    public UpdateMissionTaskModelMasterRequest withCompleteAcquireActions(List<AcquireAction> completeAcquireActions) {
        setCompleteAcquireActions(completeAcquireActions);
        return this;
    }

    /** 達成報酬の受け取り可能な期間を指定するイベントマスター のGRN */
    private String challengePeriodEventId;

    /**
     * 達成報酬の受け取り可能な期間を指定するイベントマスター のGRNを取得
     *
     * @return ミッションタスクマスターを更新
     */
    public String getChallengePeriodEventId() {
        return challengePeriodEventId;
    }

    /**
     * 達成報酬の受け取り可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId ミッションタスクマスターを更新
     */
    public void setChallengePeriodEventId(String challengePeriodEventId) {
        this.challengePeriodEventId = challengePeriodEventId;
    }

    /**
     * 達成報酬の受け取り可能な期間を指定するイベントマスター のGRNを設定
     *
     * @param challengePeriodEventId ミッションタスクマスターを更新
     * @return this
     */
    public UpdateMissionTaskModelMasterRequest withChallengePeriodEventId(String challengePeriodEventId) {
        setChallengePeriodEventId(challengePeriodEventId);
        return this;
    }

    /** このタスクに挑戦するために達成しておく必要のあるタスクの名前 */
    private String premiseMissionTaskName;

    /**
     * このタスクに挑戦するために達成しておく必要のあるタスクの名前を取得
     *
     * @return ミッションタスクマスターを更新
     */
    public String getPremiseMissionTaskName() {
        return premiseMissionTaskName;
    }

    /**
     * このタスクに挑戦するために達成しておく必要のあるタスクの名前を設定
     *
     * @param premiseMissionTaskName ミッションタスクマスターを更新
     */
    public void setPremiseMissionTaskName(String premiseMissionTaskName) {
        this.premiseMissionTaskName = premiseMissionTaskName;
    }

    /**
     * このタスクに挑戦するために達成しておく必要のあるタスクの名前を設定
     *
     * @param premiseMissionTaskName ミッションタスクマスターを更新
     * @return this
     */
    public UpdateMissionTaskModelMasterRequest withPremiseMissionTaskName(String premiseMissionTaskName) {
        setPremiseMissionTaskName(premiseMissionTaskName);
        return this;
    }

}