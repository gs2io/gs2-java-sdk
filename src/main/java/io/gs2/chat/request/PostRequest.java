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
 * メッセージを投稿 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class PostRequest extends Gs2BasicRequest<PostRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return メッセージを投稿
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName メッセージを投稿
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName メッセージを投稿
     * @return this
     */
    public PostRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ルーム名 */
    private String roomName;

    /**
     * ルーム名を取得
     *
     * @return メッセージを投稿
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * ルーム名を設定
     *
     * @param roomName メッセージを投稿
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * ルーム名を設定
     *
     * @param roomName メッセージを投稿
     * @return this
     */
    public PostRequest withRoomName(String roomName) {
        setRoomName(roomName);
        return this;
    }

    /** メッセージの種類を分類したい時の種類番号 */
    private Integer category;

    /**
     * メッセージの種類を分類したい時の種類番号を取得
     *
     * @return メッセージを投稿
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * メッセージの種類を分類したい時の種類番号を設定
     *
     * @param category メッセージを投稿
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * メッセージの種類を分類したい時の種類番号を設定
     *
     * @param category メッセージを投稿
     * @return this
     */
    public PostRequest withCategory(Integer category) {
        setCategory(category);
        return this;
    }

    /** メタデータ */
    private String metadata;

    /**
     * メタデータを取得
     *
     * @return メッセージを投稿
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata メッセージを投稿
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata メッセージを投稿
     * @return this
     */
    public PostRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** メッセージを投稿するために必要となるパスワード */
    private String password;

    /**
     * メッセージを投稿するために必要となるパスワードを取得
     *
     * @return メッセージを投稿
     */
    public String getPassword() {
        return password;
    }

    /**
     * メッセージを投稿するために必要となるパスワードを設定
     *
     * @param password メッセージを投稿
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * メッセージを投稿するために必要となるパスワードを設定
     *
     * @param password メッセージを投稿
     * @return this
     */
    public PostRequest withPassword(String password) {
        setPassword(password);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return メッセージを投稿
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider メッセージを投稿
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider メッセージを投稿
     * @return this
     */
    public PostRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

    /** アクセストークン */
    private String accessToken;

    /**
     * アクセストークンを取得
     *
     * @return アクセストークン
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     * @return this
     */
    public PostRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}