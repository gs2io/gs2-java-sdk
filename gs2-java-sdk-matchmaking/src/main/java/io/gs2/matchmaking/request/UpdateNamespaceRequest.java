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

package io.gs2.matchmaking.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.matchmaking.model.*;
import io.gs2.control.Gs2BasicRequest;

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

    /** ギャザリング新規作成時のアクション */
    private String createGatheringTriggerType;

    /**
     * ギャザリング新規作成時のアクションを取得
     *
     * @return ネームスペースを更新
     */
    public String getCreateGatheringTriggerType() {
        return createGatheringTriggerType;
    }

    /**
     * ギャザリング新規作成時のアクションを設定
     *
     * @param createGatheringTriggerType ネームスペースを更新
     */
    public void setCreateGatheringTriggerType(String createGatheringTriggerType) {
        this.createGatheringTriggerType = createGatheringTriggerType;
    }

    /**
     * ギャザリング新規作成時のアクションを設定
     *
     * @param createGatheringTriggerType ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCreateGatheringTriggerType(String createGatheringTriggerType) {
        setCreateGatheringTriggerType(createGatheringTriggerType);
        return this;
    }

    /** ギャザリング新規作成時 にルームを作成するネームスペース のGRN */
    private String createGatheringTriggerRealtimeNamespaceId;

    /**
     * ギャザリング新規作成時 にルームを作成するネームスペース のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getCreateGatheringTriggerRealtimeNamespaceId() {
        return createGatheringTriggerRealtimeNamespaceId;
    }

    /**
     * ギャザリング新規作成時 にルームを作成するネームスペース のGRNを設定
     *
     * @param createGatheringTriggerRealtimeNamespaceId ネームスペースを更新
     */
    public void setCreateGatheringTriggerRealtimeNamespaceId(String createGatheringTriggerRealtimeNamespaceId) {
        this.createGatheringTriggerRealtimeNamespaceId = createGatheringTriggerRealtimeNamespaceId;
    }

    /**
     * ギャザリング新規作成時 にルームを作成するネームスペース のGRNを設定
     *
     * @param createGatheringTriggerRealtimeNamespaceId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCreateGatheringTriggerRealtimeNamespaceId(String createGatheringTriggerRealtimeNamespaceId) {
        setCreateGatheringTriggerRealtimeNamespaceId(createGatheringTriggerRealtimeNamespaceId);
        return this;
    }

    /** ギャザリング新規作成時 に実行されるスクリプト のGRN */
    private String createGatheringTriggerScriptId;

    /**
     * ギャザリング新規作成時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getCreateGatheringTriggerScriptId() {
        return createGatheringTriggerScriptId;
    }

    /**
     * ギャザリング新規作成時 に実行されるスクリプト のGRNを設定
     *
     * @param createGatheringTriggerScriptId ネームスペースを更新
     */
    public void setCreateGatheringTriggerScriptId(String createGatheringTriggerScriptId) {
        this.createGatheringTriggerScriptId = createGatheringTriggerScriptId;
    }

    /**
     * ギャザリング新規作成時 に実行されるスクリプト のGRNを設定
     *
     * @param createGatheringTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCreateGatheringTriggerScriptId(String createGatheringTriggerScriptId) {
        setCreateGatheringTriggerScriptId(createGatheringTriggerScriptId);
        return this;
    }

    /** マッチメイキング完了時のアクション */
    private String completeMatchmakingTriggerType;

    /**
     * マッチメイキング完了時のアクションを取得
     *
     * @return ネームスペースを更新
     */
    public String getCompleteMatchmakingTriggerType() {
        return completeMatchmakingTriggerType;
    }

    /**
     * マッチメイキング完了時のアクションを設定
     *
     * @param completeMatchmakingTriggerType ネームスペースを更新
     */
    public void setCompleteMatchmakingTriggerType(String completeMatchmakingTriggerType) {
        this.completeMatchmakingTriggerType = completeMatchmakingTriggerType;
    }

    /**
     * マッチメイキング完了時のアクションを設定
     *
     * @param completeMatchmakingTriggerType ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCompleteMatchmakingTriggerType(String completeMatchmakingTriggerType) {
        setCompleteMatchmakingTriggerType(completeMatchmakingTriggerType);
        return this;
    }

    /** マッチメイキング完了時 にルームを作成するネームスペース のGRN */
    private String completeMatchmakingTriggerRealtimeNamespaceId;

    /**
     * マッチメイキング完了時 にルームを作成するネームスペース のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getCompleteMatchmakingTriggerRealtimeNamespaceId() {
        return completeMatchmakingTriggerRealtimeNamespaceId;
    }

    /**
     * マッチメイキング完了時 にルームを作成するネームスペース のGRNを設定
     *
     * @param completeMatchmakingTriggerRealtimeNamespaceId ネームスペースを更新
     */
    public void setCompleteMatchmakingTriggerRealtimeNamespaceId(String completeMatchmakingTriggerRealtimeNamespaceId) {
        this.completeMatchmakingTriggerRealtimeNamespaceId = completeMatchmakingTriggerRealtimeNamespaceId;
    }

    /**
     * マッチメイキング完了時 にルームを作成するネームスペース のGRNを設定
     *
     * @param completeMatchmakingTriggerRealtimeNamespaceId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCompleteMatchmakingTriggerRealtimeNamespaceId(String completeMatchmakingTriggerRealtimeNamespaceId) {
        setCompleteMatchmakingTriggerRealtimeNamespaceId(completeMatchmakingTriggerRealtimeNamespaceId);
        return this;
    }

    /** マッチメイキング完了時 に実行されるスクリプト のGRN */
    private String completeMatchmakingTriggerScriptId;

    /**
     * マッチメイキング完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getCompleteMatchmakingTriggerScriptId() {
        return completeMatchmakingTriggerScriptId;
    }

    /**
     * マッチメイキング完了時 に実行されるスクリプト のGRNを設定
     *
     * @param completeMatchmakingTriggerScriptId ネームスペースを更新
     */
    public void setCompleteMatchmakingTriggerScriptId(String completeMatchmakingTriggerScriptId) {
        this.completeMatchmakingTriggerScriptId = completeMatchmakingTriggerScriptId;
    }

    /**
     * マッチメイキング完了時 に実行されるスクリプト のGRNを設定
     *
     * @param completeMatchmakingTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCompleteMatchmakingTriggerScriptId(String completeMatchmakingTriggerScriptId) {
        setCompleteMatchmakingTriggerScriptId(completeMatchmakingTriggerScriptId);
        return this;
    }

    /** ギャザリングに新規プレイヤーが参加したときのプッシュ通知 */
    private NotificationSetting joinNotification;

    /**
     * ギャザリングに新規プレイヤーが参加したときのプッシュ通知を取得
     *
     * @return ネームスペースを更新
     */
    public NotificationSetting getJoinNotification() {
        return joinNotification;
    }

    /**
     * ギャザリングに新規プレイヤーが参加したときのプッシュ通知を設定
     *
     * @param joinNotification ネームスペースを更新
     */
    public void setJoinNotification(NotificationSetting joinNotification) {
        this.joinNotification = joinNotification;
    }

    /**
     * ギャザリングに新規プレイヤーが参加したときのプッシュ通知を設定
     *
     * @param joinNotification ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withJoinNotification(NotificationSetting joinNotification) {
        setJoinNotification(joinNotification);
        return this;
    }

    /** ギャザリングからプレイヤーが離脱したときのプッシュ通知 */
    private NotificationSetting leaveNotification;

    /**
     * ギャザリングからプレイヤーが離脱したときのプッシュ通知を取得
     *
     * @return ネームスペースを更新
     */
    public NotificationSetting getLeaveNotification() {
        return leaveNotification;
    }

    /**
     * ギャザリングからプレイヤーが離脱したときのプッシュ通知を設定
     *
     * @param leaveNotification ネームスペースを更新
     */
    public void setLeaveNotification(NotificationSetting leaveNotification) {
        this.leaveNotification = leaveNotification;
    }

    /**
     * ギャザリングからプレイヤーが離脱したときのプッシュ通知を設定
     *
     * @param leaveNotification ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withLeaveNotification(NotificationSetting leaveNotification) {
        setLeaveNotification(leaveNotification);
        return this;
    }

    /** マッチメイキングが完了したときのプッシュ通知 */
    private NotificationSetting completeNotification;

    /**
     * マッチメイキングが完了したときのプッシュ通知を取得
     *
     * @return ネームスペースを更新
     */
    public NotificationSetting getCompleteNotification() {
        return completeNotification;
    }

    /**
     * マッチメイキングが完了したときのプッシュ通知を設定
     *
     * @param completeNotification ネームスペースを更新
     */
    public void setCompleteNotification(NotificationSetting completeNotification) {
        this.completeNotification = completeNotification;
    }

    /**
     * マッチメイキングが完了したときのプッシュ通知を設定
     *
     * @param completeNotification ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCompleteNotification(NotificationSetting completeNotification) {
        setCompleteNotification(completeNotification);
        return this;
    }

}