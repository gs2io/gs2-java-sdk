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

package io.gs2.inbox.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.inbox.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * ネームスペースを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {

    /** プレゼントボックス名 */
    private String name;

    /**
     * プレゼントボックス名を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * プレゼントボックス名を設定
     *
     * @param name ネームスペースを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * プレゼントボックス名を設定
     *
     * @param name ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 説明文 */
    private String description;

    /**
     * 説明文を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明文を設定
     *
     * @param description ネームスペースを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 説明文を設定
     *
     * @param description ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 開封したメッセージを自動的に削除するか */
    private Boolean isAutomaticDeletingEnabled;

    /**
     * 開封したメッセージを自動的に削除するかを取得
     *
     * @return ネームスペースを新規作成
     */
    public Boolean getIsAutomaticDeletingEnabled() {
        return isAutomaticDeletingEnabled;
    }

    /**
     * 開封したメッセージを自動的に削除するかを設定
     *
     * @param isAutomaticDeletingEnabled ネームスペースを新規作成
     */
    public void setIsAutomaticDeletingEnabled(Boolean isAutomaticDeletingEnabled) {
        this.isAutomaticDeletingEnabled = isAutomaticDeletingEnabled;
    }

    /**
     * 開封したメッセージを自動的に削除するかを設定
     *
     * @param isAutomaticDeletingEnabled ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withIsAutomaticDeletingEnabled(Boolean isAutomaticDeletingEnabled) {
        setIsAutomaticDeletingEnabled(isAutomaticDeletingEnabled);
        return this;
    }

    /** メッセージ受信時 に実行されるスクリプト のGRN */
    private String receiveMessageTriggerScriptId;

    /**
     * メッセージ受信時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getReceiveMessageTriggerScriptId() {
        return receiveMessageTriggerScriptId;
    }

    /**
     * メッセージ受信時 に実行されるスクリプト のGRNを設定
     *
     * @param receiveMessageTriggerScriptId ネームスペースを新規作成
     */
    public void setReceiveMessageTriggerScriptId(String receiveMessageTriggerScriptId) {
        this.receiveMessageTriggerScriptId = receiveMessageTriggerScriptId;
    }

    /**
     * メッセージ受信時 に実行されるスクリプト のGRNを設定
     *
     * @param receiveMessageTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withReceiveMessageTriggerScriptId(String receiveMessageTriggerScriptId) {
        setReceiveMessageTriggerScriptId(receiveMessageTriggerScriptId);
        return this;
    }

    /** メッセージ受信完了時 に実行されるスクリプト のGRN */
    private String receiveMessageDoneTriggerScriptId;

    /**
     * メッセージ受信完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getReceiveMessageDoneTriggerScriptId() {
        return receiveMessageDoneTriggerScriptId;
    }

    /**
     * メッセージ受信完了時 に実行されるスクリプト のGRNを設定
     *
     * @param receiveMessageDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setReceiveMessageDoneTriggerScriptId(String receiveMessageDoneTriggerScriptId) {
        this.receiveMessageDoneTriggerScriptId = receiveMessageDoneTriggerScriptId;
    }

    /**
     * メッセージ受信完了時 に実行されるスクリプト のGRNを設定
     *
     * @param receiveMessageDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withReceiveMessageDoneTriggerScriptId(String receiveMessageDoneTriggerScriptId) {
        setReceiveMessageDoneTriggerScriptId(receiveMessageDoneTriggerScriptId);
        return this;
    }

    /** メッセージ受信完了時 にジョブが登録されるネームスペース のGRN */
    private String receiveMessageDoneTriggerNamespaceId;

    /**
     * メッセージ受信完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getReceiveMessageDoneTriggerNamespaceId() {
        return receiveMessageDoneTriggerNamespaceId;
    }

    /**
     * メッセージ受信完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param receiveMessageDoneTriggerNamespaceId ネームスペースを新規作成
     */
    public void setReceiveMessageDoneTriggerNamespaceId(String receiveMessageDoneTriggerNamespaceId) {
        this.receiveMessageDoneTriggerNamespaceId = receiveMessageDoneTriggerNamespaceId;
    }

    /**
     * メッセージ受信完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param receiveMessageDoneTriggerNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withReceiveMessageDoneTriggerNamespaceId(String receiveMessageDoneTriggerNamespaceId) {
        setReceiveMessageDoneTriggerNamespaceId(receiveMessageDoneTriggerNamespaceId);
        return this;
    }

    /** メッセージ開封時 に実行されるスクリプト のGRN */
    private String readMessageTriggerScriptId;

    /**
     * メッセージ開封時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getReadMessageTriggerScriptId() {
        return readMessageTriggerScriptId;
    }

    /**
     * メッセージ開封時 に実行されるスクリプト のGRNを設定
     *
     * @param readMessageTriggerScriptId ネームスペースを新規作成
     */
    public void setReadMessageTriggerScriptId(String readMessageTriggerScriptId) {
        this.readMessageTriggerScriptId = readMessageTriggerScriptId;
    }

    /**
     * メッセージ開封時 に実行されるスクリプト のGRNを設定
     *
     * @param readMessageTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withReadMessageTriggerScriptId(String readMessageTriggerScriptId) {
        setReadMessageTriggerScriptId(readMessageTriggerScriptId);
        return this;
    }

    /** メッセージ開封完了時 に実行されるスクリプト のGRN */
    private String readMessageDoneTriggerScriptId;

    /**
     * メッセージ開封完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getReadMessageDoneTriggerScriptId() {
        return readMessageDoneTriggerScriptId;
    }

    /**
     * メッセージ開封完了時 に実行されるスクリプト のGRNを設定
     *
     * @param readMessageDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setReadMessageDoneTriggerScriptId(String readMessageDoneTriggerScriptId) {
        this.readMessageDoneTriggerScriptId = readMessageDoneTriggerScriptId;
    }

    /**
     * メッセージ開封完了時 に実行されるスクリプト のGRNを設定
     *
     * @param readMessageDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withReadMessageDoneTriggerScriptId(String readMessageDoneTriggerScriptId) {
        setReadMessageDoneTriggerScriptId(readMessageDoneTriggerScriptId);
        return this;
    }

    /** メッセージ開封完了時 にジョブが登録されるネームスペース のGRN */
    private String readMessageDoneTriggerNamespaceId;

    /**
     * メッセージ開封完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getReadMessageDoneTriggerNamespaceId() {
        return readMessageDoneTriggerNamespaceId;
    }

    /**
     * メッセージ開封完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param readMessageDoneTriggerNamespaceId ネームスペースを新規作成
     */
    public void setReadMessageDoneTriggerNamespaceId(String readMessageDoneTriggerNamespaceId) {
        this.readMessageDoneTriggerNamespaceId = readMessageDoneTriggerNamespaceId;
    }

    /**
     * メッセージ開封完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param readMessageDoneTriggerNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withReadMessageDoneTriggerNamespaceId(String readMessageDoneTriggerNamespaceId) {
        setReadMessageDoneTriggerNamespaceId(readMessageDoneTriggerNamespaceId);
        return this;
    }

    /** メッセージ削除時 に実行されるスクリプト のGRN */
    private String deleteMessageTriggerScriptId;

    /**
     * メッセージ削除時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDeleteMessageTriggerScriptId() {
        return deleteMessageTriggerScriptId;
    }

    /**
     * メッセージ削除時 に実行されるスクリプト のGRNを設定
     *
     * @param deleteMessageTriggerScriptId ネームスペースを新規作成
     */
    public void setDeleteMessageTriggerScriptId(String deleteMessageTriggerScriptId) {
        this.deleteMessageTriggerScriptId = deleteMessageTriggerScriptId;
    }

    /**
     * メッセージ削除時 に実行されるスクリプト のGRNを設定
     *
     * @param deleteMessageTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDeleteMessageTriggerScriptId(String deleteMessageTriggerScriptId) {
        setDeleteMessageTriggerScriptId(deleteMessageTriggerScriptId);
        return this;
    }

    /** メッセージ削除完了時 に実行されるスクリプト のGRN */
    private String deleteMessageDoneTriggerScriptId;

    /**
     * メッセージ削除完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDeleteMessageDoneTriggerScriptId() {
        return deleteMessageDoneTriggerScriptId;
    }

    /**
     * メッセージ削除完了時 に実行されるスクリプト のGRNを設定
     *
     * @param deleteMessageDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setDeleteMessageDoneTriggerScriptId(String deleteMessageDoneTriggerScriptId) {
        this.deleteMessageDoneTriggerScriptId = deleteMessageDoneTriggerScriptId;
    }

    /**
     * メッセージ削除完了時 に実行されるスクリプト のGRNを設定
     *
     * @param deleteMessageDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDeleteMessageDoneTriggerScriptId(String deleteMessageDoneTriggerScriptId) {
        setDeleteMessageDoneTriggerScriptId(deleteMessageDoneTriggerScriptId);
        return this;
    }

    /** メッセージ削除完了時 にジョブが登録されるネームスペース のGRN */
    private String deleteMessageDoneTriggerNamespaceId;

    /**
     * メッセージ削除完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDeleteMessageDoneTriggerNamespaceId() {
        return deleteMessageDoneTriggerNamespaceId;
    }

    /**
     * メッセージ削除完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param deleteMessageDoneTriggerNamespaceId ネームスペースを新規作成
     */
    public void setDeleteMessageDoneTriggerNamespaceId(String deleteMessageDoneTriggerNamespaceId) {
        this.deleteMessageDoneTriggerNamespaceId = deleteMessageDoneTriggerNamespaceId;
    }

    /**
     * メッセージ削除完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param deleteMessageDoneTriggerNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDeleteMessageDoneTriggerNamespaceId(String deleteMessageDoneTriggerNamespaceId) {
        setDeleteMessageDoneTriggerNamespaceId(deleteMessageDoneTriggerNamespaceId);
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

    /** メッセージを受信したときのプッシュ通知 */
    private NotificationSetting receiveNotification;

    /**
     * メッセージを受信したときのプッシュ通知を取得
     *
     * @return ネームスペースを新規作成
     */
    public NotificationSetting getReceiveNotification() {
        return receiveNotification;
    }

    /**
     * メッセージを受信したときのプッシュ通知を設定
     *
     * @param receiveNotification ネームスペースを新規作成
     */
    public void setReceiveNotification(NotificationSetting receiveNotification) {
        this.receiveNotification = receiveNotification;
    }

    /**
     * メッセージを受信したときのプッシュ通知を設定
     *
     * @param receiveNotification ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withReceiveNotification(NotificationSetting receiveNotification) {
        setReceiveNotification(receiveNotification);
        return this;
    }

}