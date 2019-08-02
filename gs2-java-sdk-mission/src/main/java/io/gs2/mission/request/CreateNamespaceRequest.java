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

    /** ミッション達成時 に実行されるスクリプト のGRN */
    private String missionCompleteTriggerScriptId;

    /**
     * ミッション達成時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getMissionCompleteTriggerScriptId() {
        return missionCompleteTriggerScriptId;
    }

    /**
     * ミッション達成時 に実行されるスクリプト のGRNを設定
     *
     * @param missionCompleteTriggerScriptId ネームスペースを新規作成
     */
    public void setMissionCompleteTriggerScriptId(String missionCompleteTriggerScriptId) {
        this.missionCompleteTriggerScriptId = missionCompleteTriggerScriptId;
    }

    /**
     * ミッション達成時 に実行されるスクリプト のGRNを設定
     *
     * @param missionCompleteTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withMissionCompleteTriggerScriptId(String missionCompleteTriggerScriptId) {
        setMissionCompleteTriggerScriptId(missionCompleteTriggerScriptId);
        return this;
    }

    /** ミッション達成完了時 に実行されるスクリプト のGRN */
    private String missionCompleteDoneTriggerScriptId;

    /**
     * ミッション達成完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getMissionCompleteDoneTriggerScriptId() {
        return missionCompleteDoneTriggerScriptId;
    }

    /**
     * ミッション達成完了時 に実行されるスクリプト のGRNを設定
     *
     * @param missionCompleteDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setMissionCompleteDoneTriggerScriptId(String missionCompleteDoneTriggerScriptId) {
        this.missionCompleteDoneTriggerScriptId = missionCompleteDoneTriggerScriptId;
    }

    /**
     * ミッション達成完了時 に実行されるスクリプト のGRNを設定
     *
     * @param missionCompleteDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withMissionCompleteDoneTriggerScriptId(String missionCompleteDoneTriggerScriptId) {
        setMissionCompleteDoneTriggerScriptId(missionCompleteDoneTriggerScriptId);
        return this;
    }

    /** ミッション達成完了時 にジョブが登録されるネームスペース のGRN */
    private String missionCompleteDoneTriggerQueueNamespaceId;

    /**
     * ミッション達成完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getMissionCompleteDoneTriggerQueueNamespaceId() {
        return missionCompleteDoneTriggerQueueNamespaceId;
    }

    /**
     * ミッション達成完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param missionCompleteDoneTriggerQueueNamespaceId ネームスペースを新規作成
     */
    public void setMissionCompleteDoneTriggerQueueNamespaceId(String missionCompleteDoneTriggerQueueNamespaceId) {
        this.missionCompleteDoneTriggerQueueNamespaceId = missionCompleteDoneTriggerQueueNamespaceId;
    }

    /**
     * ミッション達成完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param missionCompleteDoneTriggerQueueNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withMissionCompleteDoneTriggerQueueNamespaceId(String missionCompleteDoneTriggerQueueNamespaceId) {
        setMissionCompleteDoneTriggerQueueNamespaceId(missionCompleteDoneTriggerQueueNamespaceId);
        return this;
    }

    /** カウンター上昇時 に実行されるスクリプト のGRN */
    private String counterIncrementTriggerScriptId;

    /**
     * カウンター上昇時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getCounterIncrementTriggerScriptId() {
        return counterIncrementTriggerScriptId;
    }

    /**
     * カウンター上昇時 に実行されるスクリプト のGRNを設定
     *
     * @param counterIncrementTriggerScriptId ネームスペースを新規作成
     */
    public void setCounterIncrementTriggerScriptId(String counterIncrementTriggerScriptId) {
        this.counterIncrementTriggerScriptId = counterIncrementTriggerScriptId;
    }

    /**
     * カウンター上昇時 に実行されるスクリプト のGRNを設定
     *
     * @param counterIncrementTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCounterIncrementTriggerScriptId(String counterIncrementTriggerScriptId) {
        setCounterIncrementTriggerScriptId(counterIncrementTriggerScriptId);
        return this;
    }

    /** カウンター上昇完了時 に実行されるスクリプト のGRN */
    private String counterIncrementDoneTriggerScriptId;

    /**
     * カウンター上昇完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getCounterIncrementDoneTriggerScriptId() {
        return counterIncrementDoneTriggerScriptId;
    }

    /**
     * カウンター上昇完了時 に実行されるスクリプト のGRNを設定
     *
     * @param counterIncrementDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setCounterIncrementDoneTriggerScriptId(String counterIncrementDoneTriggerScriptId) {
        this.counterIncrementDoneTriggerScriptId = counterIncrementDoneTriggerScriptId;
    }

    /**
     * カウンター上昇完了時 に実行されるスクリプト のGRNを設定
     *
     * @param counterIncrementDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCounterIncrementDoneTriggerScriptId(String counterIncrementDoneTriggerScriptId) {
        setCounterIncrementDoneTriggerScriptId(counterIncrementDoneTriggerScriptId);
        return this;
    }

    /** カウンター上昇完了時 にジョブが登録されるネームスペース のGRN */
    private String counterIncrementDoneTriggerQueueNamespaceId;

    /**
     * カウンター上昇完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getCounterIncrementDoneTriggerQueueNamespaceId() {
        return counterIncrementDoneTriggerQueueNamespaceId;
    }

    /**
     * カウンター上昇完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param counterIncrementDoneTriggerQueueNamespaceId ネームスペースを新規作成
     */
    public void setCounterIncrementDoneTriggerQueueNamespaceId(String counterIncrementDoneTriggerQueueNamespaceId) {
        this.counterIncrementDoneTriggerQueueNamespaceId = counterIncrementDoneTriggerQueueNamespaceId;
    }

    /**
     * カウンター上昇完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param counterIncrementDoneTriggerQueueNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCounterIncrementDoneTriggerQueueNamespaceId(String counterIncrementDoneTriggerQueueNamespaceId) {
        setCounterIncrementDoneTriggerQueueNamespaceId(counterIncrementDoneTriggerQueueNamespaceId);
        return this;
    }

    /** 報酬受け取り時 に実行されるスクリプト のGRN */
    private String receiveRewardsTriggerScriptId;

    /**
     * 報酬受け取り時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getReceiveRewardsTriggerScriptId() {
        return receiveRewardsTriggerScriptId;
    }

    /**
     * 報酬受け取り時 に実行されるスクリプト のGRNを設定
     *
     * @param receiveRewardsTriggerScriptId ネームスペースを新規作成
     */
    public void setReceiveRewardsTriggerScriptId(String receiveRewardsTriggerScriptId) {
        this.receiveRewardsTriggerScriptId = receiveRewardsTriggerScriptId;
    }

    /**
     * 報酬受け取り時 に実行されるスクリプト のGRNを設定
     *
     * @param receiveRewardsTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withReceiveRewardsTriggerScriptId(String receiveRewardsTriggerScriptId) {
        setReceiveRewardsTriggerScriptId(receiveRewardsTriggerScriptId);
        return this;
    }

    /** 報酬受け取り完了時 に実行されるスクリプト のGRN */
    private String receiveRewardsDoneTriggerScriptId;

    /**
     * 報酬受け取り完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getReceiveRewardsDoneTriggerScriptId() {
        return receiveRewardsDoneTriggerScriptId;
    }

    /**
     * 報酬受け取り完了時 に実行されるスクリプト のGRNを設定
     *
     * @param receiveRewardsDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setReceiveRewardsDoneTriggerScriptId(String receiveRewardsDoneTriggerScriptId) {
        this.receiveRewardsDoneTriggerScriptId = receiveRewardsDoneTriggerScriptId;
    }

    /**
     * 報酬受け取り完了時 に実行されるスクリプト のGRNを設定
     *
     * @param receiveRewardsDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withReceiveRewardsDoneTriggerScriptId(String receiveRewardsDoneTriggerScriptId) {
        setReceiveRewardsDoneTriggerScriptId(receiveRewardsDoneTriggerScriptId);
        return this;
    }

    /** 報酬受け取り完了時 にジョブが登録されるネームスペース のGRN */
    private String receiveRewardsDoneTriggerQueueNamespaceId;

    /**
     * 報酬受け取り完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getReceiveRewardsDoneTriggerQueueNamespaceId() {
        return receiveRewardsDoneTriggerQueueNamespaceId;
    }

    /**
     * 報酬受け取り完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param receiveRewardsDoneTriggerQueueNamespaceId ネームスペースを新規作成
     */
    public void setReceiveRewardsDoneTriggerQueueNamespaceId(String receiveRewardsDoneTriggerQueueNamespaceId) {
        this.receiveRewardsDoneTriggerQueueNamespaceId = receiveRewardsDoneTriggerQueueNamespaceId;
    }

    /**
     * 報酬受け取り完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param receiveRewardsDoneTriggerQueueNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withReceiveRewardsDoneTriggerQueueNamespaceId(String receiveRewardsDoneTriggerQueueNamespaceId) {
        setReceiveRewardsDoneTriggerQueueNamespaceId(receiveRewardsDoneTriggerQueueNamespaceId);
        return this;
    }

    /** 報酬付与処理をジョブとして追加するキューネームスペース のGRN */
    private String queueNamespaceId;

    /**
     * 報酬付与処理をジョブとして追加するキューネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getQueueNamespaceId() {
        return queueNamespaceId;
    }

    /**
     * 報酬付与処理をジョブとして追加するキューネームスペース のGRNを設定
     *
     * @param queueNamespaceId ネームスペースを新規作成
     */
    public void setQueueNamespaceId(String queueNamespaceId) {
        this.queueNamespaceId = queueNamespaceId;
    }

    /**
     * 報酬付与処理をジョブとして追加するキューネームスペース のGRNを設定
     *
     * @param queueNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withQueueNamespaceId(String queueNamespaceId) {
        setQueueNamespaceId(queueNamespaceId);
        return this;
    }

    /** 報酬付与処理のスタンプシートで使用する暗号鍵GRN */
    private String keyId;

    /**
     * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを設定
     *
     * @param keyId ネームスペースを新規作成
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを設定
     *
     * @param keyId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

    /** ミッションのタスクを達成したときのプッシュ通知 */
    private NotificationSetting completeNotification;

    /**
     * ミッションのタスクを達成したときのプッシュ通知を取得
     *
     * @return ネームスペースを新規作成
     */
    public NotificationSetting getCompleteNotification() {
        return completeNotification;
    }

    /**
     * ミッションのタスクを達成したときのプッシュ通知を設定
     *
     * @param completeNotification ネームスペースを新規作成
     */
    public void setCompleteNotification(NotificationSetting completeNotification) {
        this.completeNotification = completeNotification;
    }

    /**
     * ミッションのタスクを達成したときのプッシュ通知を設定
     *
     * @param completeNotification ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCompleteNotification(NotificationSetting completeNotification) {
        setCompleteNotification(completeNotification);
        return this;
    }

}