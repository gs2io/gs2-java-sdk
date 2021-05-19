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

package io.gs2.friend.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.friend.model.*;
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

    /** フォローされたときに実行するスクリプト */
    private ScriptSetting followScript;

    /**
     * フォローされたときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getFollowScript() {
        return followScript;
    }

    /**
     * フォローされたときに実行するスクリプトを設定
     *
     * @param followScript ネームスペースを更新
     */
    public void setFollowScript(ScriptSetting followScript) {
        this.followScript = followScript;
    }

    /**
     * フォローされたときに実行するスクリプトを設定
     *
     * @param followScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withFollowScript(ScriptSetting followScript) {
        setFollowScript(followScript);
        return this;
    }

    /** アンフォローされたときに実行するスクリプト */
    private ScriptSetting unfollowScript;

    /**
     * アンフォローされたときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getUnfollowScript() {
        return unfollowScript;
    }

    /**
     * アンフォローされたときに実行するスクリプトを設定
     *
     * @param unfollowScript ネームスペースを更新
     */
    public void setUnfollowScript(ScriptSetting unfollowScript) {
        this.unfollowScript = unfollowScript;
    }

    /**
     * アンフォローされたときに実行するスクリプトを設定
     *
     * @param unfollowScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withUnfollowScript(ScriptSetting unfollowScript) {
        setUnfollowScript(unfollowScript);
        return this;
    }

    /** フレンドリクエストを発行したときに実行するスクリプト */
    private ScriptSetting sendRequestScript;

    /**
     * フレンドリクエストを発行したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getSendRequestScript() {
        return sendRequestScript;
    }

    /**
     * フレンドリクエストを発行したときに実行するスクリプトを設定
     *
     * @param sendRequestScript ネームスペースを更新
     */
    public void setSendRequestScript(ScriptSetting sendRequestScript) {
        this.sendRequestScript = sendRequestScript;
    }

    /**
     * フレンドリクエストを発行したときに実行するスクリプトを設定
     *
     * @param sendRequestScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withSendRequestScript(ScriptSetting sendRequestScript) {
        setSendRequestScript(sendRequestScript);
        return this;
    }

    /** フレンドリクエストをキャンセルしたときに実行するスクリプト */
    private ScriptSetting cancelRequestScript;

    /**
     * フレンドリクエストをキャンセルしたときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getCancelRequestScript() {
        return cancelRequestScript;
    }

    /**
     * フレンドリクエストをキャンセルしたときに実行するスクリプトを設定
     *
     * @param cancelRequestScript ネームスペースを更新
     */
    public void setCancelRequestScript(ScriptSetting cancelRequestScript) {
        this.cancelRequestScript = cancelRequestScript;
    }

    /**
     * フレンドリクエストをキャンセルしたときに実行するスクリプトを設定
     *
     * @param cancelRequestScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCancelRequestScript(ScriptSetting cancelRequestScript) {
        setCancelRequestScript(cancelRequestScript);
        return this;
    }

    /** フレンドリクエストを承諾したときに実行するスクリプト */
    private ScriptSetting acceptRequestScript;

    /**
     * フレンドリクエストを承諾したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getAcceptRequestScript() {
        return acceptRequestScript;
    }

    /**
     * フレンドリクエストを承諾したときに実行するスクリプトを設定
     *
     * @param acceptRequestScript ネームスペースを更新
     */
    public void setAcceptRequestScript(ScriptSetting acceptRequestScript) {
        this.acceptRequestScript = acceptRequestScript;
    }

    /**
     * フレンドリクエストを承諾したときに実行するスクリプトを設定
     *
     * @param acceptRequestScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withAcceptRequestScript(ScriptSetting acceptRequestScript) {
        setAcceptRequestScript(acceptRequestScript);
        return this;
    }

    /** フレンドリクエストを拒否したときに実行するスクリプト */
    private ScriptSetting rejectRequestScript;

    /**
     * フレンドリクエストを拒否したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getRejectRequestScript() {
        return rejectRequestScript;
    }

    /**
     * フレンドリクエストを拒否したときに実行するスクリプトを設定
     *
     * @param rejectRequestScript ネームスペースを更新
     */
    public void setRejectRequestScript(ScriptSetting rejectRequestScript) {
        this.rejectRequestScript = rejectRequestScript;
    }

    /**
     * フレンドリクエストを拒否したときに実行するスクリプトを設定
     *
     * @param rejectRequestScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withRejectRequestScript(ScriptSetting rejectRequestScript) {
        setRejectRequestScript(rejectRequestScript);
        return this;
    }

    /** フレンドを削除したときに実行するスクリプト */
    private ScriptSetting deleteFriendScript;

    /**
     * フレンドを削除したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getDeleteFriendScript() {
        return deleteFriendScript;
    }

    /**
     * フレンドを削除したときに実行するスクリプトを設定
     *
     * @param deleteFriendScript ネームスペースを更新
     */
    public void setDeleteFriendScript(ScriptSetting deleteFriendScript) {
        this.deleteFriendScript = deleteFriendScript;
    }

    /**
     * フレンドを削除したときに実行するスクリプトを設定
     *
     * @param deleteFriendScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withDeleteFriendScript(ScriptSetting deleteFriendScript) {
        setDeleteFriendScript(deleteFriendScript);
        return this;
    }

    /** プロフィールを更新したときに実行するスクリプト */
    private ScriptSetting updateProfileScript;

    /**
     * プロフィールを更新したときに実行するスクリプトを取得
     *
     * @return ネームスペースを更新
     */
    public ScriptSetting getUpdateProfileScript() {
        return updateProfileScript;
    }

    /**
     * プロフィールを更新したときに実行するスクリプトを設定
     *
     * @param updateProfileScript ネームスペースを更新
     */
    public void setUpdateProfileScript(ScriptSetting updateProfileScript) {
        this.updateProfileScript = updateProfileScript;
    }

    /**
     * プロフィールを更新したときに実行するスクリプトを設定
     *
     * @param updateProfileScript ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withUpdateProfileScript(ScriptSetting updateProfileScript) {
        setUpdateProfileScript(updateProfileScript);
        return this;
    }

    /** フォローされたときのプッシュ通知 */
    private NotificationSetting followNotification;

    /**
     * フォローされたときのプッシュ通知を取得
     *
     * @return ネームスペースを更新
     */
    public NotificationSetting getFollowNotification() {
        return followNotification;
    }

    /**
     * フォローされたときのプッシュ通知を設定
     *
     * @param followNotification ネームスペースを更新
     */
    public void setFollowNotification(NotificationSetting followNotification) {
        this.followNotification = followNotification;
    }

    /**
     * フォローされたときのプッシュ通知を設定
     *
     * @param followNotification ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withFollowNotification(NotificationSetting followNotification) {
        setFollowNotification(followNotification);
        return this;
    }

    /** フレンドリクエストが届いたときのプッシュ通知 */
    private NotificationSetting receiveRequestNotification;

    /**
     * フレンドリクエストが届いたときのプッシュ通知を取得
     *
     * @return ネームスペースを更新
     */
    public NotificationSetting getReceiveRequestNotification() {
        return receiveRequestNotification;
    }

    /**
     * フレンドリクエストが届いたときのプッシュ通知を設定
     *
     * @param receiveRequestNotification ネームスペースを更新
     */
    public void setReceiveRequestNotification(NotificationSetting receiveRequestNotification) {
        this.receiveRequestNotification = receiveRequestNotification;
    }

    /**
     * フレンドリクエストが届いたときのプッシュ通知を設定
     *
     * @param receiveRequestNotification ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withReceiveRequestNotification(NotificationSetting receiveRequestNotification) {
        setReceiveRequestNotification(receiveRequestNotification);
        return this;
    }

    /** フレンドリクエストが承認されたときのプッシュ通知 */
    private NotificationSetting acceptRequestNotification;

    /**
     * フレンドリクエストが承認されたときのプッシュ通知を取得
     *
     * @return ネームスペースを更新
     */
    public NotificationSetting getAcceptRequestNotification() {
        return acceptRequestNotification;
    }

    /**
     * フレンドリクエストが承認されたときのプッシュ通知を設定
     *
     * @param acceptRequestNotification ネームスペースを更新
     */
    public void setAcceptRequestNotification(NotificationSetting acceptRequestNotification) {
        this.acceptRequestNotification = acceptRequestNotification;
    }

    /**
     * フレンドリクエストが承認されたときのプッシュ通知を設定
     *
     * @param acceptRequestNotification ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withAcceptRequestNotification(NotificationSetting acceptRequestNotification) {
        setAcceptRequestNotification(acceptRequestNotification);
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