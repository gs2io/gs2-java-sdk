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

    /** ミッションを達成したときに実行するスクリプト */
    private ScriptSetting missionCompleteScript;

    /**
     * ミッションを達成したときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getMissionCompleteScript() {
        return missionCompleteScript;
    }

    /**
     * ミッションを達成したときに実行するスクリプトを設定
     *
     * @param missionCompleteScript ネームスペースを新規作成
     */
    public void setMissionCompleteScript(ScriptSetting missionCompleteScript) {
        this.missionCompleteScript = missionCompleteScript;
    }

    /**
     * ミッションを達成したときに実行するスクリプトを設定
     *
     * @param missionCompleteScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withMissionCompleteScript(ScriptSetting missionCompleteScript) {
        setMissionCompleteScript(missionCompleteScript);
        return this;
    }

    /** カウンターを上昇したときに実行するスクリプト */
    private ScriptSetting counterIncrementScript;

    /**
     * カウンターを上昇したときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getCounterIncrementScript() {
        return counterIncrementScript;
    }

    /**
     * カウンターを上昇したときに実行するスクリプトを設定
     *
     * @param counterIncrementScript ネームスペースを新規作成
     */
    public void setCounterIncrementScript(ScriptSetting counterIncrementScript) {
        this.counterIncrementScript = counterIncrementScript;
    }

    /**
     * カウンターを上昇したときに実行するスクリプトを設定
     *
     * @param counterIncrementScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCounterIncrementScript(ScriptSetting counterIncrementScript) {
        setCounterIncrementScript(counterIncrementScript);
        return this;
    }

    /** 報酬を受け取ったときに実行するスクリプト */
    private ScriptSetting receiveRewardsScript;

    /**
     * 報酬を受け取ったときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getReceiveRewardsScript() {
        return receiveRewardsScript;
    }

    /**
     * 報酬を受け取ったときに実行するスクリプトを設定
     *
     * @param receiveRewardsScript ネームスペースを新規作成
     */
    public void setReceiveRewardsScript(ScriptSetting receiveRewardsScript) {
        this.receiveRewardsScript = receiveRewardsScript;
    }

    /**
     * 報酬を受け取ったときに実行するスクリプトを設定
     *
     * @param receiveRewardsScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withReceiveRewardsScript(ScriptSetting receiveRewardsScript) {
        setReceiveRewardsScript(receiveRewardsScript);
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

    /** ログの出力設定 */
    private LogSetting logSetting;

    /**
     * ログの出力設定を取得
     *
     * @return ネームスペースを新規作成
     */
    public LogSetting getLogSetting() {
        return logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを新規作成
     */
    public void setLogSetting(LogSetting logSetting) {
        this.logSetting = logSetting;
    }

    /**
     * ログの出力設定を設定
     *
     * @param logSetting ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withLogSetting(LogSetting logSetting) {
        setLogSetting(logSetting);
        return this;
    }

}