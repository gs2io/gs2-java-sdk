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
 * ルームを作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateRoomFromBackendRequest extends Gs2BasicRequest<CreateRoomFromBackendRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ルームを作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ルームを作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ルームを作成
     * @return this
     */
    public CreateRoomFromBackendRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ルーム名 */
    private String name;

    /**
     * ルーム名を取得
     *
     * @return ルームを作成
     */
    public String getName() {
        return name;
    }

    /**
     * ルーム名を設定
     *
     * @param name ルームを作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ルーム名を設定
     *
     * @param name ルームを作成
     * @return this
     */
    public CreateRoomFromBackendRequest withName(String name) {
        setName(name);
        return this;
    }

    /** ユーザID */
    private String userId;

    /**
     * ユーザIDを取得
     *
     * @return ルームを作成
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIDを設定
     *
     * @param userId ルームを作成
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザIDを設定
     *
     * @param userId ルームを作成
     * @return this
     */
    public CreateRoomFromBackendRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** メタデータ */
    private String metadata;

    /**
     * メタデータを取得
     *
     * @return ルームを作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ルームを作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ルームを作成
     * @return this
     */
    public CreateRoomFromBackendRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** メッセージを投稿するために必要となるパスワード */
    private String password;

    /**
     * メッセージを投稿するために必要となるパスワードを取得
     *
     * @return ルームを作成
     */
    public String getPassword() {
        return password;
    }

    /**
     * メッセージを投稿するために必要となるパスワードを設定
     *
     * @param password ルームを作成
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * メッセージを投稿するために必要となるパスワードを設定
     *
     * @param password ルームを作成
     * @return this
     */
    public CreateRoomFromBackendRequest withPassword(String password) {
        setPassword(password);
        return this;
    }

    /** ルームに参加可能なユーザIDリスト */
    private List<String> whiteListUserIds;

    /**
     * ルームに参加可能なユーザIDリストを取得
     *
     * @return ルームを作成
     */
    public List<String> getWhiteListUserIds() {
        return whiteListUserIds;
    }

    /**
     * ルームに参加可能なユーザIDリストを設定
     *
     * @param whiteListUserIds ルームを作成
     */
    public void setWhiteListUserIds(List<String> whiteListUserIds) {
        this.whiteListUserIds = whiteListUserIds;
    }

    /**
     * ルームに参加可能なユーザIDリストを設定
     *
     * @param whiteListUserIds ルームを作成
     * @return this
     */
    public CreateRoomFromBackendRequest withWhiteListUserIds(List<String> whiteListUserIds) {
        setWhiteListUserIds(whiteListUserIds);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ルームを作成
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ルームを作成
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ルームを作成
     * @return this
     */
    public CreateRoomFromBackendRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}