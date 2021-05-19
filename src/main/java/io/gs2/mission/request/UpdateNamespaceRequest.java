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
 * ネームスペースを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ネームスペースを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ネームスペースを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return ネームスペースを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** ミッションを達成したときに実行するスクリプト */
    private ScriptSetting missionCompleteScript;

    /**
     * ミッションを達成したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getMissionCompleteScript() {
        return missionCompleteScript;
    }

    /**
     * ミッションを達成したときに実行するスクリプトを設定
     *
     * @param missionCompleteScript ネームスペースを更新
     */
    public void setMissionCompleteScript(ScriptSetting missionCompleteScript) {
        this.missionCompleteScript = missionCompleteScript;
    }

    /**
     * ミッションを達成したときに実行するスクリプトを設定
     *
     * @param missionCompleteScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withMissionCompleteScript(ScriptSetting missionCompleteScript) {
        setMissionCompleteScript(missionCompleteScript);
        return this;
    }

    /** カウンターを上昇したときに実行するスクリプト */
    private ScriptSetting counterIncrementScript;

    /**
     * カウンターを上昇したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getCounterIncrementScript() {
        return counterIncrementScript;
    }

    /**
     * カウンターを上昇したときに実行するスクリプトを設定
     *
     * @param counterIncrementScript ネームスペースを更新
     */
    public void setCounterIncrementScript(ScriptSetting counterIncrementScript) {
        this.counterIncrementScript = counterIncrementScript;
    }

    /**
     * カウンターを上昇したときに実行するスクリプトを設定
     *
     * @param counterIncrementScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCounterIncrementScript(ScriptSetting counterIncrementScript) {
        setCounterIncrementScript(counterIncrementScript);
        return this;
    }

    /** 報酬を受け取ったときに実行するスクリプト */
    private ScriptSetting receiveRewardsScript;

    /**
     * 報酬を受け取ったときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getReceiveRewardsScript() {
        return receiveRewardsScript;
    }

    /**
     * 報酬を受け取ったときに実行するスクリプトを設定
     *
     * @param receiveRewardsScript ネームスペースを更新
     */
    public void setReceiveRewardsScript(ScriptSetting receiveRewardsScript) {
        this.receiveRewardsScript = receiveRewardsScript;
    }

    /**
     * 報酬を受け取ったときに実行するスクリプトを設定
     *
     * @param receiveRewardsScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withReceiveRewardsScript(ScriptSetting receiveRewardsScript) {
        setReceiveRewardsScript(receiveRewardsScript);
        return this;
    }

    /** 報酬付与処理をジョブとして追加するキューネームスペース のGRN */
    private String queueNamespaceId;

    /**
     * 報酬付与処理をジョブとして追加するキューネームスペース のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getQueueNamespaceId() {
        return queueNamespaceId;
    }

    /**
     * 報酬付与処理をジョブとして追加するキューネームスペース のGRNを設定
     *
     * @param queueNamespaceId ネームスペースを更新
     */
    public void setQueueNamespaceId(String queueNamespaceId) {
        this.queueNamespaceId = queueNamespaceId;
    }

    /**
     * 報酬付与処理をジョブとして追加するキューネームスペース のGRNを設定
     *
     * @param queueNamespaceId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withQueueNamespaceId(String queueNamespaceId) {
        setQueueNamespaceId(queueNamespaceId);
        return this;
    }

    /** 報酬付与処理のスタンプシートで使用する暗号鍵GRN */
    private String keyId;

    /**
     * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを設定
     *
     * @param keyId ネームスペースを更新
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * 報酬付与処理のスタンプシートで使用する暗号鍵GRNを設定
     *
     * @param keyId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

    /** ミッションのタスクを達成したときのプッシュ通知 */
    private NotificationSetting completeNotification;

    /**
     * ミッションのタスクを達成したときのプッシュ通知を取得
     *
     * @return ネームスペースを更新
     */
    public NotificationSetting getCompleteNotification() {
        return completeNotification;
    }

    /**
     * ミッションのタスクを達成したときのプッシュ通知を設定
     *
     * @param completeNotification ネームスペースを更新
     */
    public void setCompleteNotification(NotificationSetting completeNotification) {
        this.completeNotification = completeNotification;
    }

    /**
     * ミッションのタスクを達成したときのプッシュ通知を設定
     *
     * @param completeNotification ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCompleteNotification(NotificationSetting completeNotification) {
        setCompleteNotification(completeNotification);
        return this;
    }

    /** ログの出力設定 */
    private LogSetting logSetting;

    /**
     * ログの出力設定を取得
     *
     * @return ネームスペースを更新
     */
    public LogSetting getLogSetting() {
        return logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを更新
     */
    public void setLogSetting(LogSetting logSetting) {
        this.logSetting = logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withLogSetting(LogSetting logSetting) {
        setLogSetting(logSetting);
        return this;
    }

}