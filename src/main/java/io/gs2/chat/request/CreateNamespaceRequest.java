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

package io.gs2.chat.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.chat.model.*;
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

    /** ゲームプレイヤーによるルームの作成を許可するか */
    private Boolean allowCreateRoom;

    /**
     * ゲームプレイヤーによるルームの作成を許可するかを取得
     *
     * @return ネームスペースを新規作成
     */
    public Boolean getAllowCreateRoom() {
        return allowCreateRoom;
    }

    /**
     * ゲームプレイヤーによるルームの作成を許可するかを設定
     *
     * @param allowCreateRoom ネームスペースを新規作成
     */
    public void setAllowCreateRoom(Boolean allowCreateRoom) {
        this.allowCreateRoom = allowCreateRoom;
    }

    /**
     * ゲームプレイヤーによるルームの作成を許可するかを設定
     *
     * @param allowCreateRoom ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withAllowCreateRoom(Boolean allowCreateRoom) {
        setAllowCreateRoom(allowCreateRoom);
        return this;
    }

    /** メッセージを投稿したときに実行するスクリプト */
    private ScriptSetting postMessageScript;

    /**
     * メッセージを投稿したときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getPostMessageScript() {
        return postMessageScript;
    }

    /**
     * メッセージを投稿したときに実行するスクリプトを設定
     *
     * @param postMessageScript ネームスペースを新規作成
     */
    public void setPostMessageScript(ScriptSetting postMessageScript) {
        this.postMessageScript = postMessageScript;
    }

    /**
     * メッセージを投稿したときに実行するスクリプトを設定
     *
     * @param postMessageScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withPostMessageScript(ScriptSetting postMessageScript) {
        setPostMessageScript(postMessageScript);
        return this;
    }

    /** ルームを作成したときに実行するスクリプト */
    private ScriptSetting createRoomScript;

    /**
     * ルームを作成したときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getCreateRoomScript() {
        return createRoomScript;
    }

    /**
     * ルームを作成したときに実行するスクリプトを設定
     *
     * @param createRoomScript ネームスペースを新規作成
     */
    public void setCreateRoomScript(ScriptSetting createRoomScript) {
        this.createRoomScript = createRoomScript;
    }

    /**
     * ルームを作成したときに実行するスクリプトを設定
     *
     * @param createRoomScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCreateRoomScript(ScriptSetting createRoomScript) {
        setCreateRoomScript(createRoomScript);
        return this;
    }

    /** ルームを削除したときに実行するスクリプト */
    private ScriptSetting deleteRoomScript;

    /**
     * ルームを削除したときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getDeleteRoomScript() {
        return deleteRoomScript;
    }

    /**
     * ルームを削除したときに実行するスクリプトを設定
     *
     * @param deleteRoomScript ネームスペースを新規作成
     */
    public void setDeleteRoomScript(ScriptSetting deleteRoomScript) {
        this.deleteRoomScript = deleteRoomScript;
    }

    /**
     * ルームを削除したときに実行するスクリプトを設定
     *
     * @param deleteRoomScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDeleteRoomScript(ScriptSetting deleteRoomScript) {
        setDeleteRoomScript(deleteRoomScript);
        return this;
    }

    /** ルームを購読したときに実行するスクリプト */
    private ScriptSetting subscribeRoomScript;

    /**
     * ルームを購読したときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getSubscribeRoomScript() {
        return subscribeRoomScript;
    }

    /**
     * ルームを購読したときに実行するスクリプトを設定
     *
     * @param subscribeRoomScript ネームスペースを新規作成
     */
    public void setSubscribeRoomScript(ScriptSetting subscribeRoomScript) {
        this.subscribeRoomScript = subscribeRoomScript;
    }

    /**
     * ルームを購読したときに実行するスクリプトを設定
     *
     * @param subscribeRoomScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withSubscribeRoomScript(ScriptSetting subscribeRoomScript) {
        setSubscribeRoomScript(subscribeRoomScript);
        return this;
    }

    /** ルームの購読を解除したときに実行するスクリプト */
    private ScriptSetting unsubscribeRoomScript;

    /**
     * ルームの購読を解除したときに実行するスクリプトを取得
     *
     * @return ネームスペースを新規作成
     */
    public ScriptSetting getUnsubscribeRoomScript() {
        return unsubscribeRoomScript;
    }

    /**
     * ルームの購読を解除したときに実行するスクリプトを設定
     *
     * @param unsubscribeRoomScript ネームスペースを新規作成
     */
    public void setUnsubscribeRoomScript(ScriptSetting unsubscribeRoomScript) {
        this.unsubscribeRoomScript = unsubscribeRoomScript;
    }

    /**
     * ルームの購読を解除したときに実行するスクリプトを設定
     *
     * @param unsubscribeRoomScript ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withUnsubscribeRoomScript(ScriptSetting unsubscribeRoomScript) {
        setUnsubscribeRoomScript(unsubscribeRoomScript);
        return this;
    }

    /** 購読しているルームに新しい投稿がきたときのプッシュ通知 */
    private NotificationSetting postNotification;

    /**
     * 購読しているルームに新しい投稿がきたときのプッシュ通知を取得
     *
     * @return ネームスペースを新規作成
     */
    public NotificationSetting getPostNotification() {
        return postNotification;
    }

    /**
     * 購読しているルームに新しい投稿がきたときのプッシュ通知を設定
     *
     * @param postNotification ネームスペースを新規作成
     */
    public void setPostNotification(NotificationSetting postNotification) {
        this.postNotification = postNotification;
    }

    /**
     * 購読しているルームに新しい投稿がきたときのプッシュ通知を設定
     *
     * @param postNotification ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withPostNotification(NotificationSetting postNotification) {
        setPostNotification(postNotification);
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