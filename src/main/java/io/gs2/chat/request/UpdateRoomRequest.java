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
 * ルームを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateRoomRequest extends Gs2BasicRequest<UpdateRoomRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ルームを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ルームを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ルームを更新
     * @return this
     */
    public UpdateRoomRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ルーム名 */
    private String roomName;

    /**
     * ルーム名を取得
     *
     * @return ルームを更新
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * ルーム名を設定
     *
     * @param roomName ルームを更新
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * ルーム名を設定
     *
     * @param roomName ルームを更新
     * @return this
     */
    public UpdateRoomRequest withRoomName(String roomName) {
        setRoomName(roomName);
        return this;
    }

    /** メタデータ */
    private String metadata;

    /**
     * メタデータを取得
     *
     * @return ルームを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ルームを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ルームを更新
     * @return this
     */
    public UpdateRoomRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** メッセージを投稿するために必要となるパスワード */
    private String password;

    /**
     * メッセージを投稿するために必要となるパスワードを取得
     *
     * @return ルームを更新
     */
    public String getPassword() {
        return password;
    }

    /**
     * メッセージを投稿するために必要となるパスワードを設定
     *
     * @param password ルームを更新
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * メッセージを投稿するために必要となるパスワードを設定
     *
     * @param password ルームを更新
     * @return this
     */
    public UpdateRoomRequest withPassword(String password) {
        setPassword(password);
        return this;
    }

    /** ルームに参加可能なユーザIDリスト */
    private List<String> whiteListUserIds;

    /**
     * ルームに参加可能なユーザIDリストを取得
     *
     * @return ルームを更新
     */
    public List<String> getWhiteListUserIds() {
        return whiteListUserIds;
    }

    /**
     * ルームに参加可能なユーザIDリストを設定
     *
     * @param whiteListUserIds ルームを更新
     */
    public void setWhiteListUserIds(List<String> whiteListUserIds) {
        this.whiteListUserIds = whiteListUserIds;
    }

    /**
     * ルームに参加可能なユーザIDリストを設定
     *
     * @param whiteListUserIds ルームを更新
     * @return this
     */
    public UpdateRoomRequest withWhiteListUserIds(List<String> whiteListUserIds) {
        setWhiteListUserIds(whiteListUserIds);
        return this;
    }

}