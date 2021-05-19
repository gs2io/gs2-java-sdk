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
 * ユーザIDを指定してルームを購読 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SubscribeByUserIdRequest extends Gs2BasicRequest<SubscribeByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してルームを購読
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してルームを購読
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してルームを購読
     * @return this
     */
    public SubscribeByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ルーム名 */
    private String roomName;

    /**
     * ルーム名を取得
     *
     * @return ユーザIDを指定してルームを購読
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * ルーム名を設定
     *
     * @param roomName ユーザIDを指定してルームを購読
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * ルーム名を設定
     *
     * @param roomName ユーザIDを指定してルームを購読
     * @return this
     */
    public SubscribeByUserIdRequest withRoomName(String roomName) {
        setRoomName(roomName);
        return this;
    }

    /** 購読するユーザID */
    private String userId;

    /**
     * 購読するユーザIDを取得
     *
     * @return ユーザIDを指定してルームを購読
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 購読するユーザIDを設定
     *
     * @param userId ユーザIDを指定してルームを購読
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 購読するユーザIDを設定
     *
     * @param userId ユーザIDを指定してルームを購読
     * @return this
     */
    public SubscribeByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 新着メッセージ通知を受け取るカテゴリリスト */
    private List<NotificationType> notificationTypes;

    /**
     * 新着メッセージ通知を受け取るカテゴリリストを取得
     *
     * @return ユーザIDを指定してルームを購読
     */
    public List<NotificationType> getNotificationTypes() {
        return notificationTypes;
    }

    /**
     * 新着メッセージ通知を受け取るカテゴリリストを設定
     *
     * @param notificationTypes ユーザIDを指定してルームを購読
     */
    public void setNotificationTypes(List<NotificationType> notificationTypes) {
        this.notificationTypes = notificationTypes;
    }

    /**
     * 新着メッセージ通知を受け取るカテゴリリストを設定
     *
     * @param notificationTypes ユーザIDを指定してルームを購読
     * @return this
     */
    public SubscribeByUserIdRequest withNotificationTypes(List<NotificationType> notificationTypes) {
        setNotificationTypes(notificationTypes);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してルームを購読
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してルームを購読
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してルームを購読
     * @return this
     */
    public SubscribeByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}